package com.hbm.tileentity.machine;

import com.hbm.inventory.container.ContainerFluidReactor;
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
 * 流体反应器（P5.14 迁移版）。
 *
 * 迁移自 1.12.2 com.hbm.tileentity.machine.TileEntityFluidReactor。
 * 机制：双流体输入 + 催化剂 → 流体输出 + 副产物。
 * 当前简化版：2输入槽 + 1输出槽 + 1电池槽，基于 tick 的加工逻辑。
 */
public class TileEntityFluidReactor extends TileEntityMachineBase implements IPersistentNBT {

    public int progress = 0;
    public int maxProgress = 200;
    public int power = 0;
    public int maxPower = 10000;
    public int heat = 0;
    public int maxHeat = 5000;
    private boolean shouldDrop = true;

    public TileEntityFluidReactor(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state, 4, true, false);
    }

    public static TileEntityFluidReactor create(BlockPos pos, BlockState state) {
        return new TileEntityFluidReactor(ModTileEntities.FLUID_REACTOR_TE.get(), pos, state);
    }

    public void tick() {
        if (this.level == null || this.level.isClientSide) return;

        if (!inventory.getStackInSlot(0).isEmpty() && !inventory.getStackInSlot(1).isEmpty()
                && inventory.getStackInSlot(2).isEmpty() && power > 0) {
            progress++;
            power -= 10;
            heat = Math.min(heat + 2, maxHeat);
            if (progress >= maxProgress) {
                inventory.setStackInSlot(2, inventory.getStackInSlot(0).copy());
                inventory.getStackInSlot(0).shrink(1);
                inventory.getStackInSlot(1).shrink(1);
                progress = 0;
            }
        } else {
            progress = 0;
            heat = Math.max(heat - 1, 0);
        }

        this.networkPackNT(50);
    }

    @Override
    public void serialize(ByteBuf buf) {
        super.serialize(buf);
        buf.writeInt(progress);
        buf.writeInt(power);
        buf.writeInt(maxPower);
        buf.writeInt(heat);
        buf.writeInt(maxHeat);
    }

    @Override
    public void deserialize(ByteBuf buf) {
        super.deserialize(buf);
        progress = buf.readInt();
        power = buf.readInt();
        maxPower = buf.readInt();
        heat = buf.readInt();
        maxHeat = buf.readInt();
    }

    @Override
    public String getDefaultName() {
        return "container.fluid_reactor";
    }

    public MenuProvider getMenuProvider() {
        return new SimpleMenuProvider((id, inv, player) -> new ContainerFluidReactor(id, inv, this),
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
        nbt.putInt("heat", heat);
        nbt.putInt("maxHeat", maxHeat);
    }

    @Override
    public void readNBT(CompoundTag nbt) {
        progress = nbt.getInt("progress");
        power = nbt.getInt("power");
        maxPower = nbt.getInt("maxPower");
        heat = nbt.getInt("heat");
        maxHeat = nbt.getInt("maxHeat");
    }
}
