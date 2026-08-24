package com.hbm.tileentity;

import com.hbm.blocks.BlockDummyable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

/**
 * 代理基类（P5.1b-2 迁移版）。
 *
 * 迁移自 1.12.2 com.hbm.tileentity.TileEntityProxyBase（71 行）。
 * 1.21.1 变更：Compat.getTileStandard → getBlockEntity；readFromNBT/writeToNBT →
 * loadAdditional/saveAdditional；world/pos → level/worldPosition；
 * MachineDiFurnaceExtension/TileEntityDiFurnace 分支 → TODO P5.2 桩。
 */
public class TileEntityProxyBase extends TileEntityLoadedBase {

	public BlockPos cachedPosition;

	public TileEntityProxyBase(BlockEntityType<?> type, BlockPos pos, BlockState state) {
		super(type, pos, state);
	}

	public BlockEntity getTE() {

		if(cachedPosition != null) {
			BlockEntity te = this.level != null ? this.level.getBlockEntity(cachedPosition) : null;
			if(te != null && !(te instanceof TileEntityProxyBase)) return te;
			cachedPosition = null;
			this.setChanged();
		}

		if(this.getBlockState().getBlock() instanceof BlockDummyable dummy) {

            int[] pos = dummy.findCore(this.level, this.worldPosition.getX(), this.worldPosition.getY(), this.worldPosition.getZ());

			if(pos != null) {

				BlockEntity te = this.level.getBlockEntity(new BlockPos(pos[0], pos[1], pos[2]));

				if(te != null)
					return te;
			}
		}

		/// this spares me the hassle of registering a new child class TE that aims at the right target ///
		// TODO P5.2: MachineDiFurnaceExtension/TileEntityDiFurnace 分支（机器方块未迁移）

		return null;
	}

	@Override
	public void loadAdditional(CompoundTag nbt, HolderLookup.Provider registries) {
		super.loadAdditional(nbt, registries);

		if(nbt.getBoolean("hasPos")) cachedPosition = new BlockPos(nbt.getInt("pX"), nbt.getInt("pY"), nbt.getInt("pZ"));
	}

	@Override
	protected void saveAdditional(CompoundTag nbt, HolderLookup.Provider registries) {
		super.saveAdditional(nbt, registries);

		if(this.cachedPosition != null) {
			nbt.putBoolean("hasPos", true);
			nbt.putInt("pX", this.cachedPosition.getX());
			nbt.putInt("pY", this.cachedPosition.getY());
			nbt.putInt("pZ", this.cachedPosition.getZ());
		}
	}
}
