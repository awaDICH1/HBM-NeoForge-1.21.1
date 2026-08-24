package com.hbm.entity.mob;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.level.Level;

public class EntityDummy extends PathfinderMob {

    public EntityDummy(EntityType<? extends PathfinderMob> type, Level level) {
        super(type, level);
    }
}
