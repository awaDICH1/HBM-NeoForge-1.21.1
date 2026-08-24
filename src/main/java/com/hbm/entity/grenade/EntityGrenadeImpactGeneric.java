package com.hbm.entity.grenade;

import com.hbm.entity.projectile.EntityThrowableBase;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;

public class EntityGrenadeImpactGeneric extends EntityThrowableBase {

    private float explosionStrength = 2.0F;

    public EntityGrenadeImpactGeneric(EntityType<? extends EntityGrenadeImpactGeneric> type, Level level) {
        super(type, level);
    }

    public EntityGrenadeImpactGeneric(EntityType<? extends EntityGrenadeImpactGeneric> type, Level level, LivingEntity thrower) {
        super(type, level, thrower);
    }

    public void setExplosionStrength(float strength) {
        this.explosionStrength = strength;
    }

    @Override
    protected void onImpact(HitResult result) {
        if (!this.level().isClientSide) {
            this.level().explode(this, this.getX(), this.getY(), this.getZ(),
                explosionStrength, false, Level.ExplosionInteraction.TNT);
            this.discard();
        }
    }

    @Override
    protected float throwForce() { return 2.0F; }

    @Override
    protected float getGravityVelocity() { return 0.03F; }

    @Override
    protected int groundDespawn() { return 100; }
}
