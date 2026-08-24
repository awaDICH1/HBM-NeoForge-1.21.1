package com.hbm.tileentity;

import com.hbm.api.energymk2.IEnergyReceiverMK2;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

/**
 * 能量接收代理（P5.1b-2 迁移版）。
 *
 * 迁移自 1.12.2 com.hbm.tileentity.TileEntityProxyEnergy（68 行）。
 * 1.21.1 变更：setPower/getPower/getMaxPower 转发核心 TE（保持）；1.12 Forge Capability
 * （CapabilityEnergy.ENERGY + NTMEnergyCapabilityWrapper）→ 1.21 NeoForge BlockCapability
 * （Capabilities.EnergyStorage.BLOCK，直接转发核心 TE 能力）。
 */
//can be used as a source too since the core TE handles that anyway
public class TileEntityProxyEnergy extends TileEntityProxyBase implements IEnergyReceiverMK2 {

	public TileEntityProxyEnergy(BlockEntityType<?> type, BlockPos pos, BlockState state) {
		super(type, pos, state);
	}

	@Override
	public void setPower(long i) {

		BlockEntity te = getTE();

		if(te instanceof IEnergyReceiverMK2) {
			((IEnergyReceiverMK2) te).setPower(i);
		}
	}

	@Override
	public long getPower() {

		BlockEntity te = getTE();

		if(te instanceof IEnergyReceiverMK2) {
			return ((IEnergyReceiverMK2) te).getPower();
		}

		return 0;
	}

	@Override
	public long getMaxPower() {

		BlockEntity te = getTE();

		if(te instanceof IEnergyReceiverMK2) {
			return ((IEnergyReceiverMK2) te).getMaxPower();
		}

		return 0;
	}

	// TODO P5.2: 1.12 ForgeCapabilities.ENERGY 转发 → 1.21 注册侧 BlockCapability（Capabilities.EnergyStorage.BLOCK，getTE() 核心 TE）
}
