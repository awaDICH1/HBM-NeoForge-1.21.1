package com.hbm.blocks.generic;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

import java.util.List;

/**
 * 迁移自 1.12.2 com.hbm.blocks.generic.BlockHydroreactive。
 *
 * 重构：
 *   - getMaterial() == Material.WATER → state.getFluidState().is(FluidTags.WATER)（1.13+ 无 Material）
 *   - world.newExplosion(Entity, x,y,z, power, flaming, damaging)
 *     → level.explode(Entity, x,y,z, power, false, Level.ExplosionInteraction.TNT)
 *   - onBlockAdded → onPlace；neighborChanged 增加 movedByPiston 参数
 *   - randomDisplayTick → animateTick(Level, BlockPos, RandomSource)
 *   - world.spawnParticle(EnumParticleTypes.SMOKE_LARGE,...) → level.addParticle(ParticleTypes.LARGE_SMOKE,...)
 *   - world.isRainingAt(pos.up()) → level.isRainingAt(pos.above())
 *   - 构造器改 Properties；注册名/ALL_BLOCKS 删除
 */
public class BlockHydroreactive extends Block {

    public BlockHydroreactive(BlockBehaviour.Properties properties) {
        super(properties);
    }

    private boolean touchesWater(Level world, BlockPos pos) {
        if (world.isClientSide) return false;
        return world.getBlockState(pos.east()).getFluidState().is(FluidTags.WATER) ||
                world.getBlockState(pos.west()).getFluidState().is(FluidTags.WATER) ||
                world.getBlockState(pos.above()).getFluidState().is(FluidTags.WATER) ||
                world.getBlockState(pos.below()).getFluidState().is(FluidTags.WATER) ||
                world.getBlockState(pos.south()).getFluidState().is(FluidTags.WATER) ||
                world.getBlockState(pos.north()).getFluidState().is(FluidTags.WATER);
    }

    private void explode(Level world, BlockPos pos) {
        world.destroyBlock(pos, false);
        world.explode(null, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, 15.0F, false, Level.ExplosionInteraction.TNT);
    }

    @Override
    public void neighborChanged(BlockState state, Level world, BlockPos pos, Block neighborBlock, BlockPos neighborPos, boolean movedByPiston) {
        if (touchesWater(world, pos)) explode(world, pos);
    }

    @Override
    public void onPlace(BlockState state, Level world, BlockPos pos, BlockState oldState, boolean movedByPiston) {
        if (touchesWater(world, pos)) explode(world, pos);
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltip, TooltipFlag advanced) {
        tooltip.add(Component.literal("It's not my fault you didn't pay"));
        tooltip.add(Component.literal("attention in chemistry class."));
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void animateTick(BlockState stateIn, Level world, BlockPos pos, RandomSource rand) {
        if (world.isRainingAt(pos.above())) {
            float ox = rand.nextFloat();
            float oz = rand.nextFloat();
            world.addParticle(ParticleTypes.LARGE_SMOKE, pos.getX() + ox, pos.getY() + 1, pos.getZ() + oz, 0.0D, 0.0D, 0.0D);
        }
    }
}
