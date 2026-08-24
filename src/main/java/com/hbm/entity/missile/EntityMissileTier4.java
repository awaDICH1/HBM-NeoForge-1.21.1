package com.hbm.entity.missile;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;

public class EntityMissileTier4 extends EntityMissileBase {

    public EntityMissileTier4(EntityType<? extends EntityMissileTier4> type, Level level) {
        super(type, level);
        this.explosionStrength = 15.0F;
        this.missileSpeed = 0.8F;
        this.launchTime = 35;
    }
}
