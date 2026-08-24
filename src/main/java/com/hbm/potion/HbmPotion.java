package com.hbm.potion;

import com.hbm.Tags;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;

/**
 * 迁移自 1.12.2 com.hbm.potion.HbmPotion。
 *
 * Potion → MobEffect：
 *   - 构造器 (boolean isBad, int color, String name, int x, int y) → (MobEffectCategory, int color, String iconPath)
 *   - performEffect → applyEffectTick；isReady → shouldApplyEffectTickThisTick
 *   - PotionEffect → MobEffectInstance；addPotionEffect → addEffect；getActivePotionEffect → getEffect
 *   - motionX/Y/Z 扰动 → push(...)；getRNG → getRandom；setFire → setSecondsOnFire
 *   - 图标：原 setIconIndex + getStatusIconIndex（自定义图集）→ getIcon() 返回
 *     textures/mob_effect/<name>.png（单图标，由 tools 从 CE potions.png 裁剪）
 *   - 注册：原 preinit + registerPotions → ModMobEffects（注册名 hbm:taint 等，见该文件注释）
 *
 * ⚠️ 依赖未迁移系统（P5/P6/P7/P8）的分支以 TODO 占位：
 *   taint：EntityCreeperTainted / ModDamageSource.taint / ModBlocks.taint / ServerConfig（P5/P6）
 *   radiation/radaway：ContaminationUtil / HbmLivingCapability（P5）
 *   bang：ModDamageSource.bang / ExplosionLarge / HBMSoundHandler（P5/P6/P8）
 *   lead：ModDamageSource.lead（P6）
 *   phosphorus：isWarDim 维度判定（P7）
 */
public class HbmPotion extends MobEffect {

    public static HbmPotion taint;
    public static HbmPotion radiation;
    public static HbmPotion bang;
    public static HbmPotion mutation;
    public static HbmPotion radx;
    public static HbmPotion lead;
    public static HbmPotion radaway;
    public static HbmPotion telekinesis;
    public static HbmPotion phosphorus;
    public static HbmPotion stability;
    public static HbmPotion potionsickness;
    public static HbmPotion death;

    private final String iconPath;

    static {
        taint = new HbmPotion(MobEffectCategory.HARMFUL, 8388736, "taint");
        radiation = new HbmPotion(MobEffectCategory.HARMFUL, 8700200, "radiation");
        bang = new HbmPotion(MobEffectCategory.HARMFUL, 1118481, "bang");
        mutation = new HbmPotion(MobEffectCategory.BENEFICIAL, 0xFF8132, "mutation");
        radx = new HbmPotion(MobEffectCategory.BENEFICIAL, 0x225900, "radx");
        lead = new HbmPotion(MobEffectCategory.HARMFUL, 0x767682, "lead");
        radaway = new HbmPotion(MobEffectCategory.BENEFICIAL, 0xFFE400, "radaway");
        telekinesis = new HbmPotion(MobEffectCategory.HARMFUL, 0x00F3FF, "telekinesis");
        phosphorus = new HbmPotion(MobEffectCategory.HARMFUL, 0xFF3A00, "phosphorus");
        stability = new HbmPotion(MobEffectCategory.BENEFICIAL, 0xD0D0D0, "stability");
        potionsickness = new HbmPotion(MobEffectCategory.BENEFICIAL, 0xFF8080, "potionsickness");
        death = new HbmPotion(MobEffectCategory.BENEFICIAL, 0x111111, "death");
    }

    public HbmPotion(MobEffectCategory category, int color, String iconPath) {
        super(category, color);
        this.iconPath = iconPath;
    }
    // 1.21.1 MobEffect 无 getIcon()：图标由注册名派生（textures/mob_effect/<name>.png），
    // 原 getIcon() 覆写已删除；iconPath 字段保留供 P8 自定义图标映射参考

    @Override
    public boolean applyEffectTick(LivingEntity entity, int amplifier) {
        // 原 performEffect 开头 world.isRemote return
        if (entity.level().isClientSide) return true;

        if (this == taint) {
            // TODO P6: EntityCreeperTainted 判定 + ModDamageSource.taint 伤害
            // TODO P5: ModBlocks.taint 方块放置（原 ServerConfig.TAINT_TRAILS + BlockTaint.TAINTAGE 14）
            // TODO P7: CompatibilityConfig.isWarDim(Level)（原 isWarDim(entity.world)）
        } else if (this == radiation) {
            // TODO P5: ContaminationUtil.contaminate(entity, HazardType.RADIATION, ContaminationType.CREATIVE, (amplifier + 1F) * 0.05F)
        } else if (this == radaway) {
            // TODO P5: HbmLivingCapability.EntityHbmPropsProvider.ENT_HBM_PROPS_CAP → decreaseRads((amplifier + 1) * 0.05F)
        } else if (this == bang) {
            // TODO P5/P6: ModDamageSource.bang 伤害 + onDeath + setHealth(0)
            // TODO P8: HBMSoundHandler.laserBang 音效 + ExplosionLarge.spawnParticles
        } else if (this == lead) {
            // TODO P6: ModDamageSource.lead 伤害
        } else if (this == telekinesis) {
            int remaining = entity.getEffect(BuiltInRegistries.MOB_EFFECT.wrapAsHolder(this)).getDuration();
            if (remaining > 1) {
                entity.push((entity.getRandom().nextFloat() - 0.5F) * (amplifier + 1) * 0.5,
                        (entity.getRandom().nextFloat() - 0.5F) * (amplifier + 1) * 0.5,
                        (entity.getRandom().nextFloat() - 0.5F) * (amplifier + 1) * 0.5);
            }
        } else if (this == phosphorus && !entity.level().isClientSide) {
            // TODO P7: CompatibilityConfig.isWarDim 维度判定
            entity.igniteForTicks((amplifier + 1) * 20);   // 原 setSecondsOnFire(int 秒) → igniteForTicks(int tick)
        } else if (this == potionsickness && !entity.level().isClientSide) {
            if (entity.getRandom().nextInt(128) == 0) {
                entity.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 8 * 20, 0));
            }
        }
        return true;
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        if (this == taint || this == potionsickness) {
            return duration % 2 == 0;
        } else if (this == radiation || this == radaway || this == telekinesis || this == phosphorus) {
            return true;
        } else if (this == bang) {
            return duration <= 10;
        } else if (this == lead) {
            int k = 60;
            return k > 0 ? duration % k == 0 : true;
        }
        return false;
    }
}
