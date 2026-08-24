package com.hbm.potion;

import com.hbm.config.PotionConfig;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;

import java.util.HashSet;

/**
 * 迁移自 1.12.2 com.hbm.potion.HbmDetox。
 * Potion.getPotionFromResourceLocation → BuiltInRegistries.MOB_EFFECT.get(ResourceLocation.parse(...))；
 * Potion → MobEffect；依赖的 PotionConfig.potionBlacklist（config 包已迁移）✓。
 */
public class HbmDetox {

    public static HashSet<MobEffect> blacklistedPotions = new HashSet<>();
    public static MobEffect viral;

    public static void init() {
        for (String s : PotionConfig.potionBlacklist) {
            MobEffect p = BuiltInRegistries.MOB_EFFECT.get(ResourceLocation.parse(s));
            if (p != null) {
                blacklistedPotions.add(p);
            }
        }
    }

    public static boolean isBlacklisted(MobEffect p) {
        return blacklistedPotions.contains(p);
    }
}
