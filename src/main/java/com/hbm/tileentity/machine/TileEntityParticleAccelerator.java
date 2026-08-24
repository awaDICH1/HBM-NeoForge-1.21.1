package com.hbm.tileentity.machine;

import com.hbm.inventory.container.ContainerParticleAccelerator;
import com.hbm.tileentity.IPersistentNBT;
import com.hbm.tileentity.ModTileEntities;
import com.hbm.tileentity.TileEntityMachineBase;
import io.netty.buffer.ByteBuf;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

/**
 * 粒子加速器（P5.16 迁移版）。
 *
 * 迁移自 1.12.2 com.hbm.tileentity.machine.TileEntityParticleAccelerator。
 * 机制：输入粒子源 + 大量能量 → 加速粒子（用于反物质、同位素生产）。
 * 当前简化版：1 输入槽 + 1 输出槽 + 1 电池槽 + 1 目标槽。
 */
public class TileEntityParticleAccelerator extends TileEntityMachineBase implements IPersistentNBT {

    public int progress = 0;
    public int maxProgress = 2000;
    public int power = 0;
    public int maxPower = 500000;
    public int fieldStrength = 0;
    public int maxFieldStrength = 100;
    private boolean shouldDrop = true;

    public TileEntityParticleAccelerator(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state, 4, true, false);
    }

    public static TileEntityParticleAccelerator create(BlockPos pos, BlockState state) {
        return new TileEntityParticleAccelerator(ModTileEntities.PARTICLE_ACCELERATOR_TE.get(), pos, state);
    }

    public void tick() {
        if (this.level == null || this.level.isClientSide) return;

        if (!inventory.getStackInSlot(0).isEmpty() && inventory.getStackInSlot(1).isEmpty() && power > 100) {
            progress++;
            power -= 100;
            fieldStrength = Math.min(fieldStrength + 1, maxFieldStrength);
            if (progress >= maxProgress) {
                inventory.setStackInSlot(1, inventory.getStackInSlot(0).copy());
                inventory.getStackInSlot(0).shrink(1);
                progress = 0;
                fieldStrength = 0;
            }
        } else {
            progress = 0;
            fieldStrength = Math.max(fieldStrength - 1, 0);
        }

        this.networkPackNT(50);
    }

    @Override
    public void serialize(ByteBuf buf) {
        super.serialize(buf);
        buf.writeInt(progress);
        buf.writeInt(power);
        buf.writeInt(maxPower);
        buf.writeInt(fieldStrength);
    }

    @Override
    public void deserialize(ByteBuf buf) {
        super.deserialize(buf);
        progress = buf.readInt();
        power = buf.readInt();
        maxPower = buf.readInt();
        fieldStrength = buf.readInt();
    }

    @Override
    public String getDefaultName() {
        return "container.particle_accelerator";
    }

    public MenuProvider getMenuProvider() {
        return new SimpleMenuProvider((id, inv, player) -> new ContainerParticleAccelerator(id, inv, this),
                Component.literal(getDefaultName()));
    }

    @Override
    public boolean shouldDrop() { return IPersistentNBT.super.shouldDrop() && shouldDrop; }

    @Override
    public void setDestroyedByCreativePlayer() { shouldDrop = false; }

    @Override
    public boolean isDestroyedByCreativePlayer() { return !shouldDrop; }

    @Override
    public void writeNBT(CompoundTag nbt) {
        nbt.putInt("progress", progress);
        nbt.putInt("power", power);
        nbt.putInt("maxPower", maxPower);
        nbt.putInt("fieldStrength", fieldStrength);
    }

    @Override
    public void readNBT(CompoundTag nbt) {
        progress = nbt.getInt("progress");
        power = nbt.getInt("power");
        maxPower = nbt.getInt("maxPower");
        fieldStrength = nbt.getInt("fieldStrength");
    }
}
