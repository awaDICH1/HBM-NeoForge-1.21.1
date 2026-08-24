package com.hbm.api.energymk2;

import com.hbm.config.GeneralConfig;
import com.hbm.handler.threading.PacketThreading;
import com.hbm.lib.ForgeDirection;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.energy.IEnergyStorage;

/**
 * If it sends energy, use this
 */
public interface IEnergyProviderMK2 extends IEnergyHandlerMK2 {

    /**
     * Uses up available power, default implementation has no sanity checking, make sure that the requested power is lequal to the current power
     *
     * @param power The amount of power to use. Ensure this value is less than or equal to the current power.
     */
    default void usePower(long power) {
        // Subtract the specified power from the current power and update the power level
        this.setPower(this.getPower() - power);
    }

    /**
     * Retrieves the maximum speed at which the energy provider can send energy.
     * By default, this method returns the maximum power capacity of the provider.
     *
     * @return The maximum energy transfer speed, represented by the provider's maximum power capacity.
     */
    default long getProviderSpeed() {
        // Return the maximum power capacity as the default provider speed
        return this.getMaxPower();
    }

    /**
     * Attempts to provide energy to a target tile entity at specific coordinates.
     * It checks for HBM's native energy interfaces first, and then checks for Forge Energy capability
     *
     * @param world The game world.
     * @param x     The x-coordinate of the <b>target tile entity</b> (the potential receiver).
     * @param y     The y-coordinate of the <b>target tile entity</b>.
     * @param z     The z-coordinate of the <b>target tile entity</b>.
     * @param dir   The {@link ForgeDirection} from this provider to the target tile entity.
     */
    default void tryProvide(Level world, int x, int y, int z, ForgeDirection dir) {
        BlockPos targetPos = new BlockPos(x, y, z);
        BlockEntity targetTE = world.getBlockEntity(targetPos);

        if (targetTE == null) return;

        boolean connectedToNetwork = false;
        boolean powerTransferred = false;

        if (targetTE instanceof IEnergyConductorMK2 con) {
            if (con.canConnect(dir.getOpposite())) {
                Nodespace.PowerNode node = Nodespace.getNode(world, targetPos);
                if (node != null && node.net != null) {
                    node.net.addProvider(this);
                    connectedToNetwork = true;
                }
            }
        }

        if (targetTE instanceof IEnergyReceiverMK2 rec && targetTE != this) {
            if (rec.canConnect(dir.getOpposite()) && rec.allowDirectProvision()) {
                long canProvide = Math.min(this.getPower(), this.getProviderSpeed());
                long canReceive = Math.min(rec.getMaxPower() - rec.getPower(), rec.getReceiverSpeed());
                long toTransfer = Math.min(canProvide, canReceive);

                if (toTransfer > 0) {
                    long rejected = rec.transferPower(toTransfer, false);
                    long accepted = toTransfer - rejected;
                    if (accepted > 0) {
                        this.usePower(accepted);
                        powerTransferred = true;
                    }
                }
            }
        } else if (targetTE != this) {
            Direction targetFace = dir.getOpposite().toDirection();
            // 原 targetTE.getCapability(CapabilityEnergy.ENERGY, targetFace) → NeoForge BlockCapability
            IEnergyStorage cap = world.getCapability(Capabilities.EnergyStorage.BLOCK, targetPos, targetFace);
            boolean ready = cap != null && cap.canReceive() && GeneralConfig.conversionRateHeToRF > 0 && this.getPower() > 0 && this.getProviderSpeed() > 0;
            if (ready) {
                long heBudget = Math.min(this.getPower(), this.getProviderSpeed());
                long feBudget = (long) Math.floor(heBudget * GeneralConfig.conversionRateHeToRF);
                if (feBudget > 0) {
                    int feToSend = (int) Math.min(feBudget, Integer.MAX_VALUE);
                    int feAccepted = cap.receiveEnergy(feToSend, false);
                    if (feAccepted > 0) {
                        long heDrained = (long) Math.ceil(feAccepted / GeneralConfig.conversionRateHeToRF);
                        this.usePower(heDrained);
                        powerTransferred = true;
                    }
                }
            }
        }

        if (particleDebug && (connectedToNetwork || powerTransferred)) {
            // TODO P8: 原粒子调试（AuxParticlePacketNT + HbmEffectNT.Network + PacketThreading + TargetPoint）——粒子系统迁移后恢复
        }
    }

    default void tryProvide(Level world, BlockPos pos, ForgeDirection dir) {
        tryProvide(world, pos.getX(), pos.getY(), pos.getZ(), dir);
    }
}
