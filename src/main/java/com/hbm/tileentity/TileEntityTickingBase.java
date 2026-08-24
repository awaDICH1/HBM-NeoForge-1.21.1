package com.hbm.tileentity;

import com.hbm.inventory.fluid.tank.FluidTankNTM;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

/**
 * 常驻同步基类（P5.1b-1 恢复版）。
 *
 * 迁移自 1.12.2 com.hbm.tileentity.TileEntityTickingBase（13 行）。
 * 1.21.1 变更：ITickable 接口删除（1.21 BlockEntity 用 tick() 约定 + getTicker）；
 * FluidTank（Forge）→ FluidTankNTM（HBM 自研）。
 */
public abstract class TileEntityTickingBase extends TileEntityLoadedBase {

    public TileEntityTickingBase(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    public abstract String getInventoryName();

    public int getGaugeScaled(int i, FluidTankNTM tank) {
        return tank.getFill() * i / tank.getMaxFill();
    }
}
