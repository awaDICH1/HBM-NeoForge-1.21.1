package com.hbm.handler;

import net.minecraft.world.entity.LivingEntity;

/**
 * 护甲工具（P4.1 批次A 骨架版）。
 *
 * 迁移自 1.12.2 com.hbm.handler.ArmorUtil。
 * 当前仅含流体特质（FT_Toxin）所需的两个方法桩；
 * 完整护甲系统（防化服检查、过滤器耐久、材质判定）待 P5。
 */
public class ArmorUtil {

    @Deprecated public static boolean checkForHazmat(LivingEntity player) {
        // TODO P5: 完整防化服检查
        return false;
    }

    public static void damageGasMaskFilter(LivingEntity entity, int damage) {
        // TODO P5: 防毒面具过滤器耐久扣除
    }

    // TODO P5: 完整 ArmorUtil
}
