package com.hbm.tileentity;

import com.hbm.api.energymk2.IEnergyConductorMK2;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

/**
 * 能量导体代理（P5.1b-2 迁移版）。
 * 迁移自 1.12.2 com.hbm.tileentity.TileEntityProxyConductor（9 行）。
 */
public class TileEntityProxyConductor extends TileEntityProxyBase implements IEnergyConductorMK2 {

	public TileEntityProxyConductor(BlockEntityType<?> type, BlockPos pos, BlockState state) {
		super(type, pos, state);
	}
}
