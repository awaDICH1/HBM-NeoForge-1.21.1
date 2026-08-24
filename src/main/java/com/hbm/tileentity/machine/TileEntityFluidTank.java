package com.hbm.tileentity.machine;

import com.hbm.inventory.container.ContainerFluidTank;
import com.hbm.inventory.fluid.Fluids;
import com.hbm.inventory.fluid.tank.FluidTankNTM;
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
 * 流体罐（P5.7 精简迁移版）。
 *
 * 迁移自 1.12.2 com.hbm.tileentity.machine.TileEntityFluidTank。
 * 保留：流体存储（FluidTankNTM）、槽位加载/卸载、网络同步。
 * 精简：仅保留基础存储功能，fluidmk2 网络节点和高级交互后续批次实现。
 */
public class TileEntityFluidTank extends TileEntityMachineBase implements IPersistentNBT {

    public FluidTankNTM tankNew;
    private boolean shouldDrop = true;

    public TileEntityFluidTank(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state, 2, true, false);
        tankNew = new FluidTankNTM(Fluids.NONE, 64000).withOwner(this);
    }

    public static TileEntityFluidTank create(BlockPos pos, BlockState state) {
        return new TileEntityFluidTank(ModTileEntities.FLUID_TANK_TE.get(), pos, state);
    }

    public void tick() {
        if (this.level == null) return;
        if (!this.level.isClientSide) {
            tankNew.setType(0, 1, inventory);
            tankNew.loadTank(0, 1, inventory);
            this.networkPackNT(50);
        }
    }

    @Override
    public void serialize(ByteBuf buf) {
        super.serialize(buf);
        tankNew.serialize(buf);
    }

    @Override
    public void deserialize(ByteBuf buf) {
        super.deserialize(buf);
        tankNew.deserialize(buf);
    }

    @Override
    public String getDefaultName() {
        return "container.fluid_tank";
    }

    public MenuProvider getMenuProvider() {
        return new SimpleMenuProvider((id, inv, player) -> new ContainerFluidTank(id, inv, this),
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
        tankNew.writeToNBT(nbt, "tank");
    }

    @Override
    public void readNBT(CompoundTag nbt) {
        tankNew.readFromNBT(nbt, "tank");
    }
}
