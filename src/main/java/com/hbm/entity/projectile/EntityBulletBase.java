package com.hbm.entity.projectile;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

public class EntityBulletBase extends EntityProjectileBase {

    private int configId = 0;

    public EntityBulletBase(EntityType<? extends EntityBulletBase> type, Level level) {
        super(type, level);
    }

    public EntityBulletBase(EntityType<? extends EntityBulletBase> type, Level level, BulletConfig config, LivingEntity shooter) {
        super(type, level, config, shooter);
        if (config != null) {
            this.configId = config.style;
        }
    }

    public EntityBulletBase(EntityType<? extends EntityBulletBase> type, Level level, BulletConfig config, LivingEntity shooter, double tx, double ty, double tz, float velocity, float inaccuracy) {
        this(type, level, config, shooter);
        double dx = tx - this.getX();
        double dy = ty - this.getY();
        double dz = tz - this.getZ();
        this.shoot(dx, dy, dz, velocity, inaccuracy);
    }

    public int getConfigId() {
        return configId;
    }

    public void setConfigId(int id) {
        this.configId = id;
    }
}
