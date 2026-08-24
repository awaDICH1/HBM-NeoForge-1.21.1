package com.hbm.entity.mob;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class EntityHunterChopper extends PathfinderMob {

    public EntityHunterChopper(EntityType<? extends PathfinderMob> type, Level level) {
        super(type, level);
        this.setNoGravity(true);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new RandomStrollGoal(this, 1.0D, 20));
        this.goalSelector.addGoal(1, new LookAtPlayerGoal(this, Player.class, 12.0F));
        this.goalSelector.addGoal(2, new RandomLookAroundGoal(this));
    }
}
