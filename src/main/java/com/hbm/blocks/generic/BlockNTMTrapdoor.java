package com.hbm.blocks.generic;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.TrapDoorBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

/**
 * 迁移自 1.12.2 com.hbm.blocks.generic.BlockNTMTrapdoor。
 *
 * ⚠️ 简化说明（P3 占位版，P8 渲染重构后补充视觉细节）：
 *   - BlockTrapDoor → TrapDoorBlock（FACING/OPEN/HALF 属性由父类管理）
 *   - 原自定义 LADDER 属性 + getActualState（仅服务于 1.12 自定义烘焙模型）→ 删除
 *   - bakeModel/registerModel/registerSprite/getStateMapper（1.12 Forge ModelBakeEvent 管线）→ 删除，
 *     模型改为数据驱动（blockstate json，见 assets/hbm/blockstates/trapdoor_steel.json）
 *   - onBlockActivated → useWithoutItem；cycleProperty → cycle；playEvent → levelEvent
 *   - getBoundingBox/getCollisionBoundingBox → getShape/getCollisionShape（VoxelShape）
 *   - isLadder 逻辑保留（1.21.1 仍是 Block 方法，供爬梯判定）
 */
public class BlockNTMTrapdoor extends TrapDoorBlock {

    private static final VoxelShape LADDER_OPEN_NORTH = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 2.0D);
    private static final VoxelShape LADDER_OPEN_SOUTH = Block.box(0.0D, 0.0D, 14.0D, 16.0D, 16.0D, 16.0D);
    private static final VoxelShape LADDER_OPEN_WEST = Block.box(0.0D, 0.0D, 0.0D, 2.0D, 16.0D, 16.0D);
    private static final VoxelShape LADDER_OPEN_EAST = Block.box(14.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);

    public BlockNTMTrapdoor(BlockBehaviour.Properties properties) {
        super(BlockSetType.IRON, properties);
    }

    @Override
    public boolean isLadder(BlockState state, LevelReader world, BlockPos pos, LivingEntity entity) {
        if (!state.getValue(OPEN)) return false;
        BlockPos belowPos = pos.below();
        BlockState belowState = world.getBlockState(belowPos);
        return belowState.getBlock().isLadder(belowState, world, belowPos, entity);
    }

    /** getShape 的 BlockGetter 参数可能是 Level；梯子形状仅在下方是梯子时生效 */
    private boolean ladderShapeActive(BlockState state, BlockGetter source, BlockPos pos) {
        if (!state.getValue(OPEN) || !(source instanceof Level level)) return false;
        BlockPos belowPos = pos.below();
        BlockState belowState = level.getBlockState(belowPos);
        return belowState.getBlock().isLadder(belowState, level, belowPos, null);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter source, BlockPos pos, CollisionContext ctx) {
        if (ladderShapeActive(state, source, pos)) {
            return getLadderShape(state);
        }
        return super.getShape(state, source, pos, ctx);
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext ctx) {
        if (ladderShapeActive(state, world, pos)) {
            return getLadderShape(state);
        }
        return super.getCollisionShape(state, world, pos, ctx);
    }

    private VoxelShape getLadderShape(BlockState state) {
        return switch (state.getValue(FACING)) {
            case SOUTH -> LADDER_OPEN_SOUTH;
            case WEST -> LADDER_OPEN_WEST;
            case EAST -> LADDER_OPEN_EAST;
            default -> LADDER_OPEN_NORTH;
        };
    }

    @Override
    public InteractionResult useWithoutItem(BlockState state, Level world, BlockPos pos, Player player, BlockHitResult hit) {
        if (world.isClientSide) {
            return InteractionResult.SUCCESS;
        }
        BlockState toggledState = state.cycle(OPEN);
        world.setBlock(pos, toggledState, 2);
        world.levelEvent(null, 1003, pos, 0);
        boolean nowOpen = toggledState.getValue(OPEN);
        SoundEvent sound = nowOpen ? SoundEvents.WOODEN_TRAPDOOR_OPEN : SoundEvents.WOODEN_TRAPDOOR_CLOSE;
        world.playSound(null, pos, sound, SoundSource.BLOCKS, 1.0F, world.random.nextFloat() * 0.1F + 0.9F);
        return InteractionResult.SUCCESS;
    }
}
