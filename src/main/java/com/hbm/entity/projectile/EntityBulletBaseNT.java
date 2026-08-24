package com.hbm.entity.projectile;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

public class EntityBulletBaseNT extends EntityProjectileBase {

    private int configId = 0;

    public EntityBulletBaseNT(EntityType<? extends EntityBulletBaseNT> type, Level level) {
        super(type, level);
    }

    public EntityBulletBaseNT(EntityType<? extends EntityBulletBaseNT> type, Level level, BulletConfig config, LivingEntity shooter) {
        super(type, level, config, shooter);
        if (config != null) {
            this.configId = config.style;
        }
    }

    public int getConfigId() {
        return configId;
    }

    public void setConfigId(int id) {
        this.configId = id;
    }
}
