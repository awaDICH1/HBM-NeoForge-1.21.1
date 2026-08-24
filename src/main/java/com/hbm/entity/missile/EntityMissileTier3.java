package com.hbm.entity.missile;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;

public class EntityMissileTier3 extends EntityMissileBase {

    public EntityMissileTier3(EntityType<? extends EntityMissileTier3> type, Level level) {
        super(type, level);
        this.explosionStrength = 10.0F;
        this.missileSpeed = 0.7F;
        this.launchTime = 30;
    }
}
