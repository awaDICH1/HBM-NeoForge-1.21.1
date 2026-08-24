package com.hbm.tileentity.machine;

import com.hbm.inventory.container.ContainerLaser;
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
 * 激光器（P5.16 迁移版）。
 *
 * 迁移自 1.12.2 com.hbm.tileentity.machine.TileEntityLaser。
 * 机制：输入能量 + 透镜 → 激光加工（切割、焊接、蚀刻）。
 * 当前简化版：1 输入槽 + 1 输出槽 + 1 电池槽 + 1 透镜槽。
 */
public class TileEntityLaser extends TileEntityMachineBase implements IPersistentNBT {

    public int progress = 0;
    public int maxProgress = 50;
    public int power = 0;
    public int maxPower = 20000;
    public int beamIntensity = 0;
    public int maxBeamIntensity = 100;
    private boolean shouldDrop = true;

    public TileEntityLaser(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state, 4, true, false);
    }

    public static TileEntityLaser create(BlockPos pos, BlockState state) {
        return new TileEntityLaser(ModTileEntities.LASER_TE.get(), pos, state);
    }

    public void tick() {
        if (this.level == null || this.level.isClientSide) return;

        if (!inventory.getStackInSlot(0).isEmpty() && inventory.getStackInSlot(1).isEmpty() && power > 0) {
            progress++;
            power -= 20;
            beamIntensity = Math.min(beamIntensity + 2, maxBeamIntensity);
            if (progress >= maxProgress) {
                inventory.setStackInSlot(1, inventory.getStackInSlot(0).copy());
                inventory.getStackInSlot(0).shrink(1);
                progress = 0;
            }
        } else {
            progress = 0;
            beamIntensity = Math.max(beamIntensity - 1, 0);
        }

        this.networkPackNT(50);
    }

    @Override
    public void serialize(ByteBuf buf) {
        super.serialize(buf);
        buf.writeInt(progress);
        buf.writeInt(power);
        buf.writeInt(maxPower);
        buf.writeInt(beamIntensity);
    }

    @Override
    public void deserialize(ByteBuf buf) {
        super.deserialize(buf);
        progress = buf.readInt();
        power = buf.readInt();
        maxPower = buf.readInt();
        beamIntensity = buf.readInt();
    }

    @Override
    public String getDefaultName() {
        return "container.laser";
    }

    public MenuProvider getMenuProvider() {
        return new SimpleMenuProvider((id, inv, player) -> new ContainerLaser(id, inv, this),
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
        nbt.putInt("beamIntensity", beamIntensity);
    }

    @Override
    public void readNBT(CompoundTag nbt) {
        progress = nbt.getInt("progress");
        power = nbt.getInt("power");
        maxPower = nbt.getInt("maxPower");
        beamIntensity = nbt.getInt("beamIntensity");
    }
}
