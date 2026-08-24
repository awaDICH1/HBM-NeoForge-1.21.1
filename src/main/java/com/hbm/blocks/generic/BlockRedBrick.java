package com.hbm.blocks.generic;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.storage.loot.LootParams;

import java.util.List;

/**
 * 迁移自 1.12.2 com.hbm.blocks.generic.BlockRedBrick。
 *
 * 重构（1.13+ 无 metadata）：
 *   - PropertyInteger → IntegerProperty；BlockStateContainer → StateDefinition（createBlockStateDefinition）
 *   - getStateFromMeta/getMetaFromState 删除；旧存档的 meta 由 blockstate json 的 properties 迁移
 *   - getItemDropped → getDrops（不掉落自身）
 *   - getStateForPlacement(Level,pos,facing,...) → getStateForPlacement(BlockPlaceContext)
 *   - Direction.getDirectionFromMob → Direction.getNearest(相对位置)
 *   - setRegistryName/setTranslationKey/ModBlocks.ALL_BLOCKS 删除
 */
public class BlockRedBrick extends Block {

    public static final IntegerProperty META = IntegerProperty.create("meta", 0, 6);

    public BlockRedBrick(BlockBehaviour.Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(META, 0));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(META);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        LivingEntity placer = context.getPlayer();
        if (placer != null) {
            BlockPos pos = context.getClickedPos();
            Direction dir = Direction.getNearest(
                    placer.getX() - pos.getX() - 0.5,
                    placer.getY() - pos.getY(),
                    placer.getZ() - pos.getZ() - 0.5);
            return this.defaultBlockState().setValue(META, dir.get3DDataValue());
        }
        return this.defaultBlockState();
    }

    @Override
    public List<ItemStack> getDrops(BlockState state, LootParams.Builder params) {
        return List.of();   // 原 getItemDropped → Items.AIR
    }
}
