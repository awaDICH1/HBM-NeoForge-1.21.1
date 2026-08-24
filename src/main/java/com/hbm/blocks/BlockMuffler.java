package com.hbm.blocks;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;

/**
 * 消音器方块（P3 迁移版）。
 *
 * 放置于机器方块周围可降低其运行噪音。
 * 迁移自 1.12.2 com.hbm.blocks.blocks.machine.MufflerBlock。
 */
public class BlockMuffler extends Block {

    public BlockMuffler() {
        super(BlockBehaviour.Properties.of()
                .strength(3.0F, 10.0F)
                .sound(SoundType.STONE));
    }
}
