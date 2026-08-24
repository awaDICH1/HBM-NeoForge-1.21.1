package com.hbm.entity.missile;

import com.hbm.entity.projectile.EntityProjectileBase;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;

public abstract class EntityMissileBase extends EntityProjectileBase {

    protected double targetX;
    protected double targetY;
    protected double targetZ;
    protected boolean hasTarget = false;
    protected int launchPhase = 0;
    protected int launchTime = 20;
    protected float missileSpeed = 0.5F;
    protected float turnSpeed = 2.0F;
    protected float explosionStrength = 4.0F;

    public EntityMissileBase(EntityType<? extends EntityMissileBase> type, Level level) {
        super(type, level);
        this.config = createMissileConfig();
    }

    public EntityMissileBase(EntityType<? extends EntityMissileBase> type, Level level, LivingEntity shooter) {
        super(type, level, createMissileConfig(), shooter);
    }

    protected static com.hbm.entity.projectile.BulletConfig createMissileConfig() {
        com.hbm.entity.projectile.BulletConfig cfg = new com.hbm.entity.projectile.BulletConfig();
        cfg.velocity = 1.0F;
        cfg.spread = 0.0F;
        cfg.dmgMin = 20F;
        cfg.dmgMax = 30F;
        cfg.gravity = 0.0F;
        cfg.maxAge = 600;
        cfg.explosive = 4.0F;
        cfg.blockDamage = 4.0F;
        cfg.doesRicochet = false;
        cfg.doesPenetrate = false;
        cfg.style = com.hbm.entity.projectile.BulletConfig.STYLE_ROCKET;
        return cfg;
    }

    public void setTarget(double x, double y, double z) {
        this.targetX = x;
        this.targetY = y;
        this.targetZ = z;
        this.hasTarget = true;
    }

    @Override
    public void tick() {
        if (launchPhase < launchTime) {
            launchPhase++;
            Vec3 motion = new Vec3(0, missileSpeed * 0.5, 0);
            this.setDeltaMovement(motion);
            this.setPos(this.getX(), this.getY() + motion.y, this.getZ());
            spawnLaunchParticles();
            if (this.tickCount > config.maxAge) {
                this.discard();
            }
            return;
        }

        if (hasTarget) {
            guideToTarget();
        }

        spawnFlightTrail();
        super.tick();
    }

    protected void spawnLaunchParticles() {
        if (this.level().isClientSide) {
            for (int i = 0; i < 5; i++) {
                double dx = (this.random.nextDouble() - 0.5) * 0.3;
                double dz = (this.random.nextDouble() - 0.5) * 0.3;
                this.level().addParticle(net.minecraft.core.particles.ParticleTypes.FLAME,
                    this.getX() + dx, this.getY() - 0.5, this.getZ() + dz,
                    dx * 0.5, -0.1, dz * 0.5);
                this.level().addParticle(net.minecraft.core.particles.ParticleTypes.LARGE_SMOKE,
                    this.getX() + dx, this.getY() - 0.8, this.getZ() + dz,
                    dx * 0.3, -0.05, dz * 0.3);
            }
        }
    }

    protected void spawnFlightTrail() {
        if (this.level().isClientSide) {
            Vec3 motion = this.getDeltaMovement();
            for (int i = 0; i < 3; i++) {
                double ox = (this.random.nextDouble() - 0.5) * 0.15;
                double oy = (this.random.nextDouble() - 0.5) * 0.15;
                double oz = (this.random.nextDouble() - 0.5) * 0.15;
                this.level().addParticle(net.minecraft.core.particles.ParticleTypes.SMOKE,
                    this.getX() + ox - motion.x * 0.5, this.getY() + oy - motion.y * 0.5, this.getZ() + oz - motion.z * 0.5,
                    -motion.x * 0.1, -motion.y * 0.1, -motion.z * 0.1);
                if (this.random.nextInt(3) == 0) {
                    this.level().addParticle(net.minecraft.core.particles.ParticleTypes.FLAME,
                        this.getX() + ox, this.getY() + oy, this.getZ() + oz,
                        -motion.x * 0.05, -motion.y * 0.05, -motion.z * 0.05);
                }
            }
        }
    }

    protected void guideToTarget() {
        Vec3 pos = this.position();
        Vec3 target = new Vec3(targetX, targetY, targetZ);
        Vec3 desiredMotion = target.subtract(pos).normalize().scale(missileSpeed);
        Vec3 currentMotion = this.getDeltaMovement();

        double turnRad = Math.toRadians(turnSpeed);
        Vec3 newMotion = currentMotion.scale(1 - turnRad).add(desiredMotion.scale(turnRad));
        if (newMotion.lengthSqr() > 0) {
            newMotion = newMotion.normalize().scale(missileSpeed);
        }
        this.setDeltaMovement(newMotion);
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        explode();
        super.onHitEntity(result);
    }

    @Override
    protected void onHitBlock(BlockHitResult result) {
        explode();
        super.onHitBlock(result);
    }

    protected void explode() {
        if (!this.level().isClientSide && !this.isRemoved()) {
            this.level().explode(this, this.getX(), this.getY(), this.getZ(),
                explosionStrength, true, Level.ExplosionInteraction.TNT);
            this.discard();
        }
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        targetX = tag.getDouble("targetX");
        targetY = tag.getDouble("targetY");
        targetZ = tag.getDouble("targetZ");
        hasTarget = tag.getBoolean("hasTarget");
        launchPhase = tag.getInt("launchPhase");
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putDouble("targetX", targetX);
        tag.putDouble("targetY", targetY);
        tag.putDouble("targetZ", targetZ);
        tag.putBoolean("hasTarget", hasTarget);
        tag.putInt("launchPhase", launchPhase);
    }
}
