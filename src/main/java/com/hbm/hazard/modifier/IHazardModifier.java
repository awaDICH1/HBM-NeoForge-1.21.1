package com.hbm.hazard.modifier;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

import java.util.List;

/**
 * 迁移自 1.12.2 com.hbm.hazard.modifier.IHazardModifier（纯接口，EntityLivingBase→LivingEntity）。
 */
public interface IHazardModifier {

    double modify(ItemStack stack, LivingEntity holder, double level);

    /**
     * Returns the level after applying all modifiers to it, in order.
     *
     * @param stack
     * @param entity nullable
     * @param level
     * @param mods
     * @return
     */
    static double evalAllModifiers(final ItemStack stack, final LivingEntity entity, double level, final List<IHazardModifier> mods) {
        for (final IHazardModifier mod : mods) {
            level = mod.modify(stack, entity, level);
        }
        return level;
    }
}
