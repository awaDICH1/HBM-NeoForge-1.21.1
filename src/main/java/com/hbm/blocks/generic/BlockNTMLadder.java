package com.hbm.blocks.generic;

import com.hbm.blocks.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LadderBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

/**
 * 迁移自 1.12.2 com.hbm.blocks.generic.BlockNTMLadder。
 *
 * 重构：
 *   - BlockLadder → LadderBlock（FACING 属性由父类管理）
 *   - canPlaceBlockOnSide(Level,pos,side) → canSurvive(BlockGetter,pos,state)
 *   - getBoundingBox(...).setMaxY(0.25) → getShape(...) 返回 Block.box(0,0,0,16,4,16)
 *   - getStateForPlacement(Level,pos,facing,...,placer) → getStateForPlacement(BlockPlaceContext)
 *   - 引用的 ModBlocks.ladder_red_top 改为 ModBlocks.LADDER_RED_TOP.get()（12 个梯子本批注册）
 */
public class BlockNTMLadder extends LadderBlock {

    public BlockNTMLadder(BlockBehaviour.Properties properties) {
        super(properties);
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader world, BlockPos pos) {
        // 原: this == ModBlocks.ladder_red_top || super.canPlaceBlockOnSide(...)
        return this == ModBlocks.LADDER_RED_TOP.get() || super.canSurvive(state, world, pos);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter source, BlockPos pos, CollisionContext ctx) {
        if (this == ModBlocks.LADDER_RED_TOP.get()) {
            // 原: super.getBoundingBox(...).setMaxY(0.25) —— 红色梯顶是一块 4px 高的平台
            return Block.box(0.0D, 0.0D, 0.0D, 16.0D, 4.0D, 16.0D);
        }
        return super.getShape(state, source, pos, ctx);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        if (this == ModBlocks.LADDER_RED_TOP.get()) {
            // 原: FACING = placer.getHorizontalFacing().getOpposite()
            Direction facing = context.getHorizontalDirection().getOpposite();
            return this.defaultBlockState().setValue(FACING, facing);
        }
        return super.getStateForPlacement(context);
    }
}
