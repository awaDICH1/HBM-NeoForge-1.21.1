package com.hbm.blocks;

import com.hbm.tileentity.TileEntityMachineBase;
import com.hbm.util.I18nUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

import java.util.List;

public class BlockBase extends Block {

    public BlockBase(BlockBehaviour.Properties properties) {
        super(properties);
    }

    public BlockBase() {
        this(BlockBehaviour.Properties.of());
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> list, TooltipFlag advanced) {
        float hardness = getExplosionResistance();
        if (hardness > 50) {
            list.add(Component.literal(I18nUtil.resolveKey("trait.blastres", hardness)).withStyle(ChatFormatting.GOLD));
        }
    }

    @Override
    public void neighborChanged(BlockState state, Level level, BlockPos pos, Block neighbor, BlockPos neighborPos, boolean moved) {
        if (!level.isClientSide) {
            boolean powered = level.hasNeighborSignal(pos);
            if (level.getBlockEntity(pos) instanceof TileEntityMachineBase te) {
                te.setPowered(powered);
            }
        }
    }
}
