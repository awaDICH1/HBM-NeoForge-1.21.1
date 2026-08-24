package com.hbm.entity.grenade;

import com.hbm.entity.projectile.EntityThrowableBase;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;

public class EntityGrenadeBouncyGeneric extends EntityThrowableBase {

    private float explosionStrength = 1.5F;
    private int fuseTicks = 80;

    public EntityGrenadeBouncyGeneric(EntityType<? extends EntityGrenadeBouncyGeneric> type, Level level) {
        super(type, level);
    }

    public EntityGrenadeBouncyGeneric(EntityType<? extends EntityGrenadeBouncyGeneric> type, Level level, LivingEntity thrower) {
        super(type, level, thrower);
    }

    public void setExplosionStrength(float strength) {
        this.explosionStrength = strength;
    }

    @Override
    protected void onTickActive() {
        super.onTickActive();
        if (!this.level().isClientSide && !isStuck()) {
            fuseTicks--;
            if (fuseTicks <= 0) {
                explode();
            }
        }
    }

    @Override
    protected void onImpact(HitResult result) {
        if (fuseTicks > 0) {
            this.discard();
        }
    }

    private void explode() {
        if (!this.level().isClientSide) {
            this.level().explode(this, this.getX(), this.getY(), this.getZ(),
                explosionStrength, false, Level.ExplosionInteraction.TNT);
            this.discard();
        }
    }

    @Override
    protected float throwForce() { return 1.2F; }

    @Override
    protected float getGravityVelocity() { return 0.04F; }

    @Override
    protected int groundDespawn() { return 100; }

    @Override
    protected boolean doesImpactEntities() { return false; }
}
