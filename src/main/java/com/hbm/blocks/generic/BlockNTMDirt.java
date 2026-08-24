package com.hbm.blocks.generic;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootParams;

import java.util.List;

/**
 * 迁移自 1.12.2 com.hbm.blocks.generic.BlockNTMDirt。
 *
 * 重构：
 *   - BlockDirt → DirtBlock（构造器需 Properties）→ 1.21.1 无 DirtBlock，改 extends Block
 *   - getSubBlocks（1.12 创造栏子物品枚举）→ 删除
 *   - getLocalizedName（返回原版泥土名）→ 删除（1.21.1 显示名由 lang 键 block.hbm.ntm_dirt 决定）
 *   - getItemDropped → getDrops（掉落原版泥土）
 *   - 注册名/ALL_BLOCKS 删除
 */
public class BlockNTMDirt extends Block {

    public BlockNTMDirt(BlockBehaviour.Properties properties) {
        super(properties);
    }

    @Override
    public List<ItemStack> getDrops(BlockState state, LootParams.Builder params) {
        return List.of(new ItemStack(Blocks.DIRT));
    }
}
