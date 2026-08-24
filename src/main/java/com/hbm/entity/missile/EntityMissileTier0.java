package com.hbm.entity.missile;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;

public class EntityMissileTier0 extends EntityMissileBase {

    public EntityMissileTier0(EntityType<? extends EntityMissileTier0> type, Level level) {
        super(type, level);
        this.explosionStrength = 3.0F;
        this.missileSpeed = 0.4F;
        this.launchTime = 15;
    }
}
