package com.hbm.tileentity;

import com.hbm.lib.ItemStackHandlerWrapper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.ItemStackHandler;

/**
 * 库存基类（P5.1a 迁移版）。
 *
 * 迁移自 1.12.2 com.hbm.tileentity.TileEntityInventoryBase（76 行）。
 * 1.21.1 变更：
 *  - BlockEntity 构造器 (BlockEntityType, BlockPos, BlockState, int)（1.12: (int)）；
 *  - writeToNBT/readFromNBT → saveAdditional/loadAdditional（HolderLookup.Provider）；
 *  - world/pos → level/worldPosition；
 *  - 1.12 Forge Capability（getCapability(Capability, Direction)）→ 1.21 NeoForge BlockCapability
 *    （覆写 getCapability(BlockCapability<T,D>, D)，Capabilities.ItemStack.ITEM）；
 *  - NBT 键名 "inventory" 原样保留（旧存档兼容）。
 */
public abstract class TileEntityInventoryBase extends BlockEntity {

	public ItemStackHandler inventory;
	
	private String customName;
	
	public TileEntityInventoryBase(BlockEntityType<?> type, BlockPos pos, BlockState state, int scount) {
		super(type, pos, state);
		inventory = new ItemStackHandler(scount);
	}
	
	public String getInventoryName() {
		return this.hasCustomInventoryName() ? this.customName : getName();
	}
	
	public abstract String getName();

	public boolean hasCustomInventoryName() {
		return this.customName != null && this.customName.length() > 0;
	}
	
	public void setCustomName(String name) {
		this.customName = name;
	}
	
	public boolean isUseableByPlayer(Player player) {
		if(this.level == null || this.level.getBlockEntity(this.worldPosition) != this) {
			return false;
		} else {
			return player.distanceToSqr(this.worldPosition.getX() + 0.5D, this.worldPosition.getY() + 0.5D, this.worldPosition.getZ() + 0.5D) <= 128;
		}
	}
	
	@Override
	protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
		tag.put("inventory", inventory.serializeNBT(registries));
		super.saveAdditional(tag, registries);
	}
	
	@Override
	public void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
		if(tag.contains("inventory"))
			inventory.deserializeNBT(registries, tag.getCompound("inventory"));
		super.loadAdditional(tag, registries);
	}
	
	public int[] getAccessibleSlotsFromSide(Direction side) {
		return new int[] { 0 };
	}

	// 1.21.1 方块能力改为 RegisterCapabilitiesEvent 注册（Capabilities.ItemHandler.BLOCK），
	// TE 不再覆写 getCapability；各机器 TE 注册时经本方法提供按侧过滤的处理器（P5.1b 批量注册）。
	public IItemHandler getInventoryForSide(Direction facing) {
		if (inventory == null) return null;
		if (facing == null) return inventory;
		return new ItemStackHandlerWrapper(inventory, getAccessibleSlotsFromSide(facing));
	}
}
