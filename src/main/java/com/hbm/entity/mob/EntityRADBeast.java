package com.hbm.entity.mob;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

/**
 * 辐射野兽（EntityRADBeast）- HBM 模组中的独特生物。
 * 
 * 特性：高移动速度、远程辐射攻击、群体攻击
 */
public class EntityRADBeast extends Monster {

    public EntityRADBeast(EntityType<? extends EntityRADBeast> type, Level level) {
        super(type, level);
    }

    @Override
    protected void registerGoals() {
        // 优先级越高，执行越早
        this.goalSelector.addGoal(0, new FloatGoal(this));
        
        // 追逐目标
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.2D, true));
        
        // 随机游走
        this.goalSelector.addGoal(2, new WaterAvoidingRandomStrollGoal(this, 0.9D));
        
        // 观察玩家
        this.goalSelector.addGoal(3, new LookAtPlayerGoal(this, Player.class, 16.0F));
        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));
        
        // 目标选择
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.targetSelector.addGoal(2, new HurtByTargetGoal(this));
    }
}
