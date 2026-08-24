package com.hbm.entity.projectile;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;

public class EntityRocket extends EntityProjectileBase {

    private static final BulletConfig DEFAULT_ROCKET_CONFIG;

    static {
        DEFAULT_ROCKET_CONFIG = new BulletConfig();
        DEFAULT_ROCKET_CONFIG.velocity = 1.5F;
        DEFAULT_ROCKET_CONFIG.spread = 0.05F;
        DEFAULT_ROCKET_CONFIG.dmgMin = 10F;
        DEFAULT_ROCKET_CONFIG.dmgMax = 15F;
        DEFAULT_ROCKET_CONFIG.gravity = 0.0F;
        DEFAULT_ROCKET_CONFIG.maxAge = 200;
        DEFAULT_ROCKET_CONFIG.explosive = 2.5F;
        DEFAULT_ROCKET_CONFIG.blockDamage = 2.0F;
        DEFAULT_ROCKET_CONFIG.style = BulletConfig.STYLE_ROCKET;
        DEFAULT_ROCKET_CONFIG.doesRicochet = false;
        DEFAULT_ROCKET_CONFIG.doesPenetrate = false;
    }

    public EntityRocket(EntityType<? extends EntityRocket> type, Level level) {
        super(type, level);
        this.config = DEFAULT_ROCKET_CONFIG;
    }

    public EntityRocket(EntityType<? extends EntityRocket> type, Level level, LivingEntity shooter) {
        super(type, level, DEFAULT_ROCKET_CONFIG, shooter);
        this.shootFromEntity(shooter, 1.5F, 0.05F);
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        super.onHitEntity(result);
        if (!this.level().isClientSide && !this.isRemoved()) {
            this.level().explode(this, this.getX(), this.getY(), this.getZ(),
                DEFAULT_ROCKET_CONFIG.explosive, true, Level.ExplosionInteraction.TNT);
        }
    }

    @Override
    protected void onHitBlock(BlockHitResult result) {
        super.onHitBlock(result);
        if (!this.level().isClientSide && !this.isRemoved()) {
            this.level().explode(this, this.getX(), this.getY(), this.getZ(),
                DEFAULT_ROCKET_CONFIG.explosive, true, Level.ExplosionInteraction.TNT);
            this.discard();
        }
    }
}
