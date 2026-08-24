package com.hbm.util;

import com.hbm.capability.HbmLivingCapability;
import com.hbm.capability.HbmLivingProps;
import com.hbm.config.RadiationConfig;
import com.hbm.lib.ModDamageSource;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

/**
 * 污染系统工具类（P5续）。
 * 迁移自 1.12.2 com.hbm.util.ContaminationUtil。
 * 
 * 提供以下核心功能：
 * - 辐射剂量计算与管理
 * - 污染效果应用
 * - 防化服检查
 * - 区块辐射扩散
 */
public class ContaminationUtil {

    /**
     * 枚举：污染类型
     */
    public enum ContaminationType {
        /** 创意模式（调试用，不产生实际效果） */
        CREATIVE,
        /** 正常游戏污染 */
        NORMAL,
        /** 轻度污染 */
        LIGHT,
        /** 重度污染 */
        HEAVY
    }

    /**
     * 枚举：危险物类型
     */
    public enum HazardType {
        /** 辐射 */
        RADIATION,
        /** γ射线 */
        DIGAMMA,
        /** 污染 */
        TAIN,
        /** 石棉肺 */
        ASBESTOS,
        /** 黑肺病 */
        BLACKLUNG,
        /** 铅中毒 */
        LEAD
    }

    /**
     * 向实体施加辐射伤害
     * 
     * @param entity 目标实体
     * @param radAmount 辐射量（RAD）
     * @param type 污染类型
     * @param hazardType 危险物类型
     */
    public static void contaminate(LivingEntity entity, double radAmount, 
                                    ContaminationType type, HazardType hazardType) {
        if (entity == null || !RadiationConfig.enableContamination) return;
        
        // 创意模式不产生实际效果
        if (type == ContaminationType.CREATIVE) return;
        
        // 根据污染类型调整辐射量
        double adjustedRad = radAmount;
        switch (type) {
            case LIGHT:
                adjustedRad *= 0.5;
                break;
            case HEAVY:
                adjustedRad *= 2.0;
                break;
            default:
                break;
        }
        
        // 增加辐射值
        HbmLivingProps.incrementRadiation(entity, adjustedRad);
        
        // 应用相应的效果
        applyHazardEffect(entity, hazardType, adjustedRad);
    }

    /**
     * 应用危险物效果
     */
    private static void applyHazardEffect(LivingEntity entity, HazardType type, double radAmount) {
        if (entity instanceof Player player) {
            switch (type) {
                case RADIATION:
                    // 辐射伤害
                    if (radAmount > 1000) {
                        entity.hurt(ModDamageSource.radiation, (float) (radAmount * 0.01));
                    }
                    break;
                    
                case DIGAMMA:
                    // γ射线效果
                    HbmLivingProps.incrementDigamma(entity, radAmount * 0.01);
                    break;
                    
                case LEAD:
                    // 铅中毒
                    entity.addEffect(new net.minecraft.world.effect.MobEffectInstance(
                        net.minecraft.world.effect.MobEffects.POISON, 100, 0));
                    break;
                    
                case ASBESTOS:
                    // 石棉肺 - 使用饥饿效果代替挖掘疲劳
                    entity.addEffect(new net.minecraft.world.effect.MobEffectInstance(
                        net.minecraft.world.effect.MobEffects.HUNGER, 100, 0));
                    break;
                    
                case BLACKLUNG:
                    // 黑肺病 - 使用困惑效果代替缓慢
                    entity.addEffect(new net.minecraft.world.effect.MobEffectInstance(
                        net.minecraft.world.effect.MobEffects.CONFUSION, 100, 0));
                    break;
                    
                default:
                    break;
            }
        }
    }

    /**
     * 检查实体是否穿着防化服
     * 
     * @param entity 目标实体
     * @return true 如果穿着完整防化服
     */
    public static boolean isWearingHazmatSuit(LivingEntity entity) {
        if (entity == null) return false;
        
        // TODO P5: 完整防化服检查逻辑（需要 ModItems 引用）
        // 暂返回 false，待 P3 物品全量后实现
        return false;
    }

    /**
     * 减少实体辐射值
     * 
     * @param entity 目标实体
     * @param amount 减少量
     * @return 剩余辐射值
     */
    public static double decreaseRads(LivingEntity entity, double amount) {
        if (entity == null) return 0;
        
        double current = HbmLivingProps.getRadiation(entity);
        double result = Math.max(0, current - amount);
        HbmLivingProps.setRadiation(entity, result);
        return result;
    }

    /**
     * 增加实体辐射值
     * 
     * @param entity 目标实体
     * @param amount 增加量
     */
    public static void addRads(LivingEntity entity, double amount) {
        if (entity == null) return;
        HbmLivingProps.incrementRadiation(entity, amount);
    }

    /**
     * 获取实体当前辐射值
     * 
     * @param entity 目标实体
     * @return 辐射值（RAD）
     */
    public static double getRadiation(LivingEntity entity) {
        if (entity == null) return 0;
        return HbmLivingProps.getRadiation(entity);
    }

    /**
     * 区块辐射扩散（简化版）
     * 
     * @param level 世界
     * @param pos 中心位置
     * @param intensity 辐射强度
     * @param radius 扩散半径
     */
    public static void diffuseRadiation(ServerLevel level, BlockPos pos, 
                                         double intensity, int radius) {
        if (level == null || intensity <= 0) return;
        
        // 简化实现：在中心区块标记高辐射区域
        // TODO P5: 完整区块辐射扩散逻辑（需要 ChunkRadiationManager）
        // 1. 计算中心区块辐射值
        // 2. 向周围区块扩散
        // 3. 应用半衰期衰减
        // 4. 处理污染方块生成
    }

    /**
     * 检查实体是否受到辐射影响
     * 
     * @param entity 目标实体
     * @return true 如果受到辐射影响
     */
    public static boolean isRadiated(LivingEntity entity) {
        if (entity == null) return false;
        return getRadiation(entity) > 0;
    }

    /**
     * 获取辐射影响程度（0-1）
     * 
     * @param entity 目标实体
     * @return 影响程度
     */
    public static double getRadiationLevel(LivingEntity entity) {
        if (entity == null) return 0;
        double rad = getRadiation(entity);
        // 0-25000000 范围映射到 0-1
        return Math.min(1.0, rad / 25000000.0);
    }

    /**
     * 应用辐射视觉效果（粒子效果）
     * 
     * @param entity 目标实体
     */
    public static void applyRadiationParticles(LivingEntity entity) {
        if (entity == null) return;
        Level level = entity.level();
        if (level == null || level.isClientSide()) return;
        
        // TODO P5: 辐射粒子效果（需要 particle 系统引用）
    }

    /**
     * 清理实体的辐射值（使用 RadAway）
     * 
     * @param entity 目标实体
     * @param amount 清理量
     */
    public static void cleanRadiation(LivingEntity entity, double amount) {
        if (entity == null) return;
        decreaseRads(entity, amount);
    }

    /**
     * 检查区块是否有高辐射
     * 
     * @param level 世界
     * @param pos 位置
     * @param threshold 阈值
     * @return true 如果辐射值超过阈值
     */
    public static boolean hasHighRadiation(Level level, BlockPos pos, double threshold) {
        if (level == null || !level.isClientSide) return false;
        
        // TODO P5: 区块辐射查询逻辑
        return false;
    }
}
