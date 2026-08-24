package com.hbm.entity.missile;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;

public class EntityMissileTier1 extends EntityMissileBase {

    public EntityMissileTier1(EntityType<? extends EntityMissileTier1> type, Level level) {
        super(type, level);
        this.explosionStrength = 4.0F;
        this.missileSpeed = 0.5F;
        this.launchTime = 20;
    }
}
