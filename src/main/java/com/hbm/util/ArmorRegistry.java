package com.hbm.util;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;

/**
 * 防护注册表（P4.1 批次A 骨架版）。
 *
 * 迁移自 1.12.2 com.hbm.util.ArmorRegistry（128 行）。
 * 当前仅含流体特质（FT_Toxin）所需的 HazardClass 枚举与 hasAllProtection 桩；
 * 完整防护注册（防毒面具/防化服注册表）待 P5。
 */
public class ArmorRegistry {

    public static enum HazardClass {
        GAS_LUNG("hazard.gasChlorine"),                //also attacks eyes -> no half mask
        GAS_MONOXIDE("hazard.gasMonoxide"),                //only affects lungs
        GAS_INERT("hazard.gasInert"),                    //SA
        PARTICLE_COARSE("hazard.particleCoarse"),        //only affects lungs
        PARTICLE_FINE("hazard.particleFine"),            //only affects lungs
        BACTERIA("hazard.bacteria"),                    //no half masks
        NERVE_AGENT("hazard.nerveAgent"),				//aggressive nerve agent, also attacks skin, NOT USED UPSTREAM
        GAS_BLISTERING("hazard.corrosive"),                //corrosive substance, also attacks skin
        SAND("hazard.sand"),                            //blinding sand particles
        LIGHT("hazard.light");                            //blinding light

        public final String lang;

        private HazardClass(String lang) {
            this.lang = lang;
        }
    }

    // TODO P5: 完整防护注册（hasAllProtection 需查护甲注册表）
    public static boolean hasAllProtection(LivingEntity entity, EquipmentSlot slot, HazardClass clazz) {
        return false;
    }
}
