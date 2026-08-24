package com.hbm.blocks.generic;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

import java.util.List;

/**
 * 迁移自 1.12.2 com.hbm.blocks.generic.BlockPinkLog。
 *
 * 重构：
 *   - BlockLog → RotatedPillarBlock（LOG_AXIS 属性由父类管理，无需重写 createBlockStateDefinition）
 *   - getStateFromMeta/getMetaFromState 删除（轴向存于 blockstate json）
 *   - getTranslationKey()+".desc" → getDescriptionId()+".desc"（语言键随 1.21.1 变为 block.hbm.pink_log.desc）
 */
public class BlockPinkLog extends RotatedPillarBlock {

    public BlockPinkLog(BlockBehaviour.Properties properties) {
        super(properties);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltip, TooltipFlag advanced) {
        tooltip.add(Component.translatable(this.getDescriptionId() + ".desc"));
        super.appendHoverText(stack, context, tooltip, advanced);
    }
}
