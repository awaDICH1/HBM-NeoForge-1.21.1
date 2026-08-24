package com.hbm.blocks.generic;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootParams;

import java.util.List;

/**
 * 迁移自 1.12.2 com.hbm.blocks.generic.BlockNoDrop。
 * getItemDropped → getDrops（不掉落自身）；构造器改 Properties；注册名/ALL_BLOCKS 删除。
 */
public class BlockNoDrop extends Block {

    public BlockNoDrop(BlockBehaviour.Properties properties) {
        super(properties);
    }

    @Override
    public List<ItemStack> getDrops(BlockState state, LootParams.Builder params) {
        return List.of();
    }
}
