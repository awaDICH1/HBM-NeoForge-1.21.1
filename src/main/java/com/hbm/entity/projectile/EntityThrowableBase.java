package com.hbm.entity.projectile;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.event.EventHooks;

public abstract class EntityThrowableBase extends ThrowableProjectile {

    public static final int NO_STUCK = -1;
    private int stuckTicks = 0;
    private int stuckX = NO_STUCK;
    private int stuckY = NO_STUCK;
    private int stuckZ = NO_STUCK;

    public EntityThrowableBase(EntityType<? extends EntityThrowableBase> type, Level level) {
        super(type, level);
    }

    public EntityThrowableBase(EntityType<? extends EntityThrowableBase> type, Level level, LivingEntity thrower) {
        super(type, thrower, level);
    }

    protected float throwForce() { return 1.5F; }
    protected float headingForceMult() { return 0.0075F; }
    protected float throwAngle() { return 0.0F; }
    protected float motionMult() { return 1.0F; }
    protected float getAirDrag() { return 0.99F; }
    protected float getWaterDrag() { return 0.8F; }
    protected float getGravityVelocity() { return 0.03F; }
    protected int groundDespawn() { return 1200; }
    protected boolean doesImpactEntities() { return true; }
    protected boolean doesPenetrate() { return false; }
    protected boolean isSpectral() { return false; }
    protected int selfDamageDelay() { return 0; }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
    }

    @Override
    public void tick() {
        if (isStuck()) {
            tickStuck();
            super.tick();
            return;
        }
        super.tick();
        if (this.isRemoved()) return;
        onTickActive();
    }

    protected void onTickActive() {
        Vec3 motion = this.getDeltaMovement();
        if (this.isInWater()) {
            motion = motion.scale(getWaterDrag());
        } else {
            motion = motion.scale(getAirDrag());
        }
        motion = motion.add(0, -getGravityVelocity(), 0);
        this.setDeltaMovement(motion);

        this.setPos(this.getX() + motion.x, this.getY() + motion.y, this.getZ() + motion.z);

        if (this.tickCount > groundDespawn() + 200) {
            this.discard();
        }
    }

    @Override
    protected void onHit(HitResult result) {
        if (!EventHooks.onProjectileImpact(this, result)) {
            super.onHit(result);
        }
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        if (!doesImpactEntities()) return;
        Entity entity = result.getEntity();
        if (!doesPenetrate()) {
            onImpact(result);
        }
    }

    @Override
    protected void onHitBlock(BlockHitResult result) {
        if (isSpectral()) return;
        onImpact(result);
        if (!doesPenetrate()) {
            stickToBlock(result);
        }
    }

    protected void stickToBlock(BlockHitResult result) {
        BlockPos pos = result.getBlockPos();
        this.stuckX = pos.getX();
        this.stuckY = pos.getY();
        this.stuckZ = pos.getZ();
        this.stuckTicks = 0;
        this.setDeltaMovement(Vec3.ZERO);
    }

    protected boolean isStuck() {
        return stuckX != NO_STUCK;
    }

    protected void tickStuck() {
        stuckTicks++;
        if (stuckTicks > groundDespawn()) {
            this.discard();
        }
    }

    protected abstract void onImpact(HitResult result);

    @Override
    protected void readAdditionalSaveData(CompoundTag tag) {
        stuckX = tag.getInt("stuckX");
        if (stuckX == 0) stuckX = NO_STUCK;
        stuckY = tag.getInt("stuckY");
        stuckZ = tag.getInt("stuckZ");
        stuckTicks = tag.getInt("stuckTicks");
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag tag) {
        tag.putInt("stuckX", stuckX);
        tag.putInt("stuckY", stuckY);
        tag.putInt("stuckZ", stuckZ);
        tag.putInt("stuckTicks", stuckTicks);
    }

}
