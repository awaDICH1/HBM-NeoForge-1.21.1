package com.hbm.entity.mob.glyphid;

import com.hbm.entity.mob.EntityMobBase;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;

public class EntityGlyphid extends EntityMobBase {

    public EntityGlyphid(EntityType<? extends EntityGlyphid> type, Level level) {
        super(type, level);
    }
}
