package com.hbm.tileentity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

/**
 * 库存代理（P5.1b-2 迁移版）。
 *
 * 迁移自 1.12.2 com.hbm.tileentity.TileEntityProxyInventory（31 行）。
 * 1.21.1 变更：1.12 Forge Capability（CapabilityItemHandler）→ 1.21 NeoForge BlockCapability。
 * 1.21 的 BlockEntity 无 getCapability 实例方法，代理的能力转发需在注册侧声明
 * （Capabilities.ItemStack.ITEM.registerForBlockEntity ... 指向 getTE() 核心 TE）→ TODO P5.2 能力批。
 */
public class TileEntityProxyInventory extends TileEntityProxyBase {

	public TileEntityProxyInventory(BlockEntityType<?> type, BlockPos pos, BlockState state) {
		super(type, pos, state);
	}

	// TODO P5.2: 1.12 ForgeCapabilities.ITEM_HANDLER 转发 → 1.21 注册侧 BlockCapability（getTE() 核心 TE）
}
