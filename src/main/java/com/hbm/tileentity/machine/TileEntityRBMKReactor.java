package com.hbm.tileentity.machine;

import com.hbm.inventory.container.ContainerRBMKReactor;
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
 * RBMK 核反应堆（P5.15 迁移版）。
 *
 * 迁移自 1.12.2 com.hbm.tileentity.machine.TileEntityRBMKReactor。
 * 机制：核燃料 + 中子慢化剂 → 热能 + 副产物（废料）。
 * 当前简化版：1 燃料槽 + 1 废料槽 + 1 冷却液槽 + 1 输出槽。
 */
public class TileEntityRBMKReactor extends TileEntityMachineBase implements IPersistentNBT {

    public int progress = 0;
    public int maxProgress = 1000;
    public int heat = 0;
    public int maxHeat = 50000;
    public int reactivity = 0;
    public int maxReactivity = 100;
    private boolean shouldDrop = true;

    public TileEntityRBMKReactor(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state, 4, true, false);
    }

    public static TileEntityRBMKReactor create(BlockPos pos, BlockState state) {
        return new TileEntityRBMKReactor(ModTileEntities.RBMK_REACTOR_TE.get(), pos, state);
    }

    public void tick() {
        if (this.level == null || this.level.isClientSide) return;

        if (!inventory.getStackInSlot(0).isEmpty()) {
            progress++;
            heat = Math.min(heat + 10, maxHeat);
            reactivity = Math.min(reactivity + 1, maxReactivity);

            if (progress >= maxProgress) {
                inventory.setStackInSlot(1, inventory.getStackInSlot(0).copy());
                inventory.getStackInSlot(0).shrink(1);
                progress = 0;
                reactivity = Math.max(reactivity - 20, 0);
            }
        } else {
            heat = Math.max(heat - 5, 0);
            reactivity = Math.max(reactivity - 1, 0);
            progress = 0;
        }

        this.networkPackNT(50);
    }

    @Override
    public void serialize(ByteBuf buf) {
        super.serialize(buf);
        buf.writeInt(progress);
        buf.writeInt(heat);
        buf.writeInt(maxHeat);
        buf.writeInt(reactivity);
    }

    @Override
    public void deserialize(ByteBuf buf) {
        super.deserialize(buf);
        progress = buf.readInt();
        heat = buf.readInt();
        maxHeat = buf.readInt();
        reactivity = buf.readInt();
    }

    @Override
    public String getDefaultName() {
        return "container.rbmk_reactor";
    }

    public MenuProvider getMenuProvider() {
        return new SimpleMenuProvider((id, inv, player) -> new ContainerRBMKReactor(id, inv, this),
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
        nbt.putInt("reactivity", reactivity);
    }

    @Override
    public void readNBT(CompoundTag nbt) {
        progress = nbt.getInt("progress");
        heat = nbt.getInt("heat");
        maxHeat = nbt.getInt("maxHeat");
        reactivity = nbt.getInt("reactivity");
    }
}
