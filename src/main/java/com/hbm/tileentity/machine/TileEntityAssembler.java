package com.hbm.tileentity.machine;

import com.hbm.inventory.container.ContainerAssembler;
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
 * 组装机（P5.14 迁移版）。
 *
 * 迁移自 1.12.2 com.hbm.tileentity.machine.TileEntityAssembler。
 * 机制：多输入物品 + 能量 → 输出物品（装配机配方的执行方）。
 * 当前简化版：3输入槽 + 1输出槽 + 1电池槽。
 */
public class TileEntityAssembler extends TileEntityMachineBase implements IPersistentNBT {

    public int progress = 0;
    public int maxProgress = 150;
    public int power = 0;
    public int maxPower = 8000;
    private boolean shouldDrop = true;

    public TileEntityAssembler(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state, 5, true, false);
    }

    public static TileEntityAssembler create(BlockPos pos, BlockState state) {
        return new TileEntityAssembler(ModTileEntities.ASSEMBLER_TE.get(), pos, state);
    }

    public void tick() {
        if (this.level == null || this.level.isClientSide) return;

        if (!inventory.getStackInSlot(0).isEmpty() && inventory.getStackInSlot(3).isEmpty() && power > 0) {
            progress++;
            power -= 8;
            if (progress >= maxProgress) {
                inventory.setStackInSlot(3, inventory.getStackInSlot(0).copy());
                inventory.getStackInSlot(0).shrink(1);
                if (!inventory.getStackInSlot(1).isEmpty()) inventory.getStackInSlot(1).shrink(1);
                if (!inventory.getStackInSlot(2).isEmpty()) inventory.getStackInSlot(2).shrink(1);
                progress = 0;
            }
        } else {
            progress = 0;
        }

        this.networkPackNT(50);
    }

    @Override
    public void serialize(ByteBuf buf) {
        super.serialize(buf);
        buf.writeInt(progress);
        buf.writeInt(power);
        buf.writeInt(maxPower);
    }

    @Override
    public void deserialize(ByteBuf buf) {
        super.deserialize(buf);
        progress = buf.readInt();
        power = buf.readInt();
        maxPower = buf.readInt();
    }

    @Override
    public String getDefaultName() {
        return "container.assembler";
    }

    public MenuProvider getMenuProvider() {
        return new SimpleMenuProvider((id, inv, player) -> new ContainerAssembler(id, inv, this),
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
    }

    @Override
    public void readNBT(CompoundTag nbt) {
        progress = nbt.getInt("progress");
        power = nbt.getInt("power");
        maxPower = nbt.getInt("maxPower");
    }
}
