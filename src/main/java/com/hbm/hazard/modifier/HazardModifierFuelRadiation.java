package com.hbm.hazard.modifier;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

/**
 * 迁移自 1.12.2 com.hbm.hazard.modifier.HazardModifierFuelRadiation（纯逻辑，getDurabilityForDisplay 签名不变）。
 */
public class HazardModifierFuelRadiation implements IHazardModifier {

    double target;

    public HazardModifierFuelRadiation(final double target) {
        this.target = target;
    }

    @Override
    public double modify(final ItemStack stack, final LivingEntity holder, double level) {
        // 原 getDurabilityForDisplay()（damage/maxDamage）→ 1.21.1 由 getDamageValue/getMaxDamage 计算
        final int max = stack.getMaxDamage();
        final double depletion = max <= 0 ? 0D : Math.pow(stack.getDamageValue() / (double) max, 0.4D);
        level = (level + (this.target - level) * depletion);

        return level;
    }
}
