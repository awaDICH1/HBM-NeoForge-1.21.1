package com.hbm.entity.missile;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;

public class EntityMissileTier2 extends EntityMissileBase {

    public EntityMissileTier2(EntityType<? extends EntityMissileTier2> type, Level level) {
        super(type, level);
        this.explosionStrength = 6.0F;
        this.missileSpeed = 0.6F;
        this.launchTime = 25;
    }
}
