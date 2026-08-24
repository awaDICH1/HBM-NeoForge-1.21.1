package com.hbm.tileentity.machine;

import com.hbm.inventory.container.ContainerRBMKConsole;
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
 * RBMK 控制台（P5.7 精简迁移版）。
 *
 * 迁移自 1.12.2 com.hbm.tileentity.machine.TileEntityRBMKConsole。
 * 保留：控制台槽位、网络同步、基础 UI。
 * 精简：仅保留基础控制功能，RBMK 多方块结构联动后续批次实现。
 */
public class TileEntityRBMKConsole extends TileEntityMachineBase implements IPersistentNBT {

    public short controlRodLevel = 0;
    public boolean isActive = false;
    private boolean shouldDrop = true;

    public TileEntityRBMKConsole(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state, 1, true, false);
    }

    public static TileEntityRBMKConsole create(BlockPos pos, BlockState state) {
        return new TileEntityRBMKConsole(ModTileEntities.RBMK_CONSOLE_TE.get(), pos, state);
    }

    public void tick() {
        if (this.level == null) return;
        if (!this.level.isClientSide) {
            this.networkPackNT(50);
        }
    }

    @Override
    public void serialize(ByteBuf buf) {
        super.serialize(buf);
        buf.writeShort(controlRodLevel);
        buf.writeBoolean(isActive);
    }

    @Override
    public void deserialize(ByteBuf buf) {
        super.deserialize(buf);
        controlRodLevel = buf.readShort();
        isActive = buf.readBoolean();
    }

    @Override
    public String getDefaultName() {
        return "container.rbmk_console";
    }

    public MenuProvider getMenuProvider() {
        return new SimpleMenuProvider((id, inv, player) -> new ContainerRBMKConsole(id, inv, this),
                Component.literal(getDefaultName()));
    }

    @Override
    public boolean shouldDrop() {
        return IPersistentNBT.super.shouldDrop() && shouldDrop;
    }

    @Override
    public void setDestroyedByCreativePlayer() {
        shouldDrop = false;
    }

    @Override
    public boolean isDestroyedByCreativePlayer() {
        return !shouldDrop;
    }

    @Override
    public void writeNBT(CompoundTag nbt) {
        nbt.putShort("controlRodLevel", controlRodLevel);
        nbt.putBoolean("isActive", isActive);
    }

    @Override
    public void readNBT(CompoundTag nbt) {
        controlRodLevel = nbt.getShort("controlRodLevel");
        isActive = nbt.getBoolean("isActive");
    }
}
