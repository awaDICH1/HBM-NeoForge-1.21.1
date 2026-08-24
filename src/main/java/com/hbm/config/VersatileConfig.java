package com.hbm.config;

import com.hbm.potion.HbmPotion;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;

/**
 * 迁移自 1.12.2 com.hbm.config.VersatileConfig（纯辅助类，无配置键）。
 * 依赖的 GeneralConfig / MachineConfig / PotionConfig / HbmPotion 均已迁移 ✓。
 * 变更：PotionEffect → MobEffectInstance；addPotionEffect → addEffect；isPotionActive → hasEffect。
 */
public class VersatileConfig {

    public static int getSchrabOreChance() {
        if (GeneralConfig.enableLBSM) return 20;
        return 100;
    }

    public static void applyPotionSickness(LivingEntity entity, int duration) {
        if (PotionConfig.potionSickness == 0) return;
        if (PotionConfig.potionSickness == 2) duration *= 12;
        entity.addEffect(new MobEffectInstance(BuiltInRegistries.MOB_EFFECT.wrapAsHolder(HbmPotion.potionsickness), duration * 20));
    }

    public static boolean hasPotionSickness(LivingEntity entity) {
        return entity.hasEffect(BuiltInRegistries.MOB_EFFECT.wrapAsHolder(HbmPotion.potionsickness));
    }

    public static boolean rtgDecay() {
        return GeneralConfig.enable528 || MachineConfig.doRTGsDecay;
    }

    static int minute = 60 * 20;
    static int hour = 60 * minute;

    public static int getLongDecayChance() {
        return GeneralConfig.enable528 ? 15 * hour : (GeneralConfig.enableLBSM && GeneralConfig.enableLBSMShorterDecay) ? 15 * minute : 3 * hour;
    }

    public static int getShortDecayChance() {
        return GeneralConfig.enable528 ? 15 * hour : (GeneralConfig.enableLBSM && GeneralConfig.enableLBSMShorterDecay) ? 15 * minute : 3 * hour;
    }
}
