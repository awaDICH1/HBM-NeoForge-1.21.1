package com.hbm.tileentity.machine;

import com.hbm.inventory.container.ContainerHeatExchanger;
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
 * 热交换器（P5.15 迁移版）。
 *
 * 迁移自 1.12.2 com.hbm.tileentity.machine.TileEntityHeatExchanger。
 * 机制：热流体输入 + 冷却液 → 冷却后流体 + 热能输出。
 * 当前简化版：2 输入槽 + 2 输出槽 + 热量交换逻辑。
 */
public class TileEntityHeatExchanger extends TileEntityMachineBase implements IPersistentNBT {

    public int progress = 0;
    public int maxProgress = 100;
    public int heat = 0;
    public int maxHeat = 20000;
    private boolean shouldDrop = true;

    public TileEntityHeatExchanger(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state, 4, true, false);
    }

    public static TileEntityHeatExchanger create(BlockPos pos, BlockState state) {
        return new TileEntityHeatExchanger(ModTileEntities.HEAT_EXCHANGER_TE.get(), pos, state);
    }

    public void tick() {
        if (this.level == null || this.level.isClientSide) return;

        if (!inventory.getStackInSlot(0).isEmpty() && !inventory.getStackInSlot(1).isEmpty()
                && inventory.getStackInSlot(2).isEmpty()) {
            progress++;
            heat = Math.min(heat + 5, maxHeat);
            if (progress >= maxProgress) {
                inventory.setStackInSlot(2, inventory.getStackInSlot(0).copy());
                inventory.setStackInSlot(3, inventory.getStackInSlot(1).copy());
                inventory.getStackInSlot(0).shrink(1);
                inventory.getStackInSlot(1).shrink(1);
                progress = 0;
            }
        } else {
            progress = 0;
            heat = Math.max(heat - 2, 0);
        }

        this.networkPackNT(50);
    }

    @Override
    public void serialize(ByteBuf buf) {
        super.serialize(buf);
        buf.writeInt(progress);
        buf.writeInt(heat);
        buf.writeInt(maxHeat);
    }

    @Override
    public void deserialize(ByteBuf buf) {
        super.deserialize(buf);
        progress = buf.readInt();
        heat = buf.readInt();
        maxHeat = buf.readInt();
    }

    @Override
    public String getDefaultName() {
        return "container.heat_exchanger";
    }

    public MenuProvider getMenuProvider() {
        return new SimpleMenuProvider((id, inv, player) -> new ContainerHeatExchanger(id, inv, this),
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
        nbt.putInt("heat", heat);
        nbt.putInt("maxHeat", maxHeat);
    }

    @Override
    public void readNBT(CompoundTag nbt) {
        progress = nbt.getInt("progress");
        heat = nbt.getInt("heat");
        maxHeat = nbt.getInt("maxHeat");
    }
}
