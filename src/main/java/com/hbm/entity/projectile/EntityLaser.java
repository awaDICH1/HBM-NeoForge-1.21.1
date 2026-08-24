package com.hbm.entity.projectile;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

public class EntityLaser extends EntityProjectileBase {

    public EntityLaser(EntityType<? extends EntityLaser> type, Level level) {
        super(type, level);
        initLaserConfig();
    }

    public EntityLaser(EntityType<? extends EntityLaser> type, Level level, LivingEntity shooter) {
        super(type, level, null, shooter);
        initLaserConfig();
        this.shootFromEntity(shooter, 10.0F, 0.0F);
    }

    private void initLaserConfig() {
        BulletConfig cfg = new BulletConfig();
        cfg.velocity = 10.0F;
        cfg.spread = 0.0F;
        cfg.dmgMin = 8F;
        cfg.dmgMax = 12F;
        cfg.gravity = 0.0F;
        cfg.maxAge = 60;
        cfg.doesPenetrate = true;
        cfg.isSpectral = true;
        cfg.liveAfterImpact = true;
        cfg.style = BulletConfig.STYLE_LASER;
        this.config = cfg;
    }
}
