package com.hbm.potion;

import com.hbm.Tags;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

/**
 * 状态效果注册中心。迁移自 1.12.2 HbmPotion.preinit() + registerPotions()。
 *
 * ⚠️ 注册名变更：原 "hbm:potion.hbm_xxx" → 新 "hbm:xxx"（如 hbm:radiation）。
 * 理由：效果不写入存档 NBT，无旧存档兼容问题；且 /effect give @p hbm:radiation 60 0 可直接使用。
 * 语言键：effect.hbm.<name>（见 assets/hbm/lang/en_us.json）。
 */
public class ModMobEffects {

    public static final DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister.create(Registries.MOB_EFFECT, Tags.MODID);

    public static final DeferredHolder<MobEffect, MobEffect> TAINT = MOB_EFFECTS.register("taint", () -> HbmPotion.taint);
    public static final DeferredHolder<MobEffect, MobEffect> RADIATION = MOB_EFFECTS.register("radiation", () -> HbmPotion.radiation);
    public static final DeferredHolder<MobEffect, MobEffect> BANG = MOB_EFFECTS.register("bang", () -> HbmPotion.bang);
    public static final DeferredHolder<MobEffect, MobEffect> MUTATION = MOB_EFFECTS.register("mutation", () -> HbmPotion.mutation);
    public static final DeferredHolder<MobEffect, MobEffect> RADX = MOB_EFFECTS.register("radx", () -> HbmPotion.radx);
    public static final DeferredHolder<MobEffect, MobEffect> LEAD = MOB_EFFECTS.register("lead", () -> HbmPotion.lead);
    public static final DeferredHolder<MobEffect, MobEffect> RADAWAY = MOB_EFFECTS.register("radaway", () -> HbmPotion.radaway);
    public static final DeferredHolder<MobEffect, MobEffect> TELEKINESIS = MOB_EFFECTS.register("telekinesis", () -> HbmPotion.telekinesis);
    public static final DeferredHolder<MobEffect, MobEffect> PHOSPHORUS = MOB_EFFECTS.register("phosphorus", () -> HbmPotion.phosphorus);
    public static final DeferredHolder<MobEffect, MobEffect> STABILITY = MOB_EFFECTS.register("stability", () -> HbmPotion.stability);
    public static final DeferredHolder<MobEffect, MobEffect> POTIONSICKNESS = MOB_EFFECTS.register("potionsickness", () -> HbmPotion.potionsickness);
    public static final DeferredHolder<MobEffect, MobEffect> DEATH = MOB_EFFECTS.register("death", () -> HbmPotion.death);
}
