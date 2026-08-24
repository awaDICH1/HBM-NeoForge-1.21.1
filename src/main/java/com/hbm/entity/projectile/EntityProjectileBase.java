package com.hbm.entity.projectile;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.event.EventHooks;
import net.minecraft.world.level.block.FireBlock;
import com.hbm.potion.ModMobEffects;

public class EntityProjectileBase extends net.minecraft.world.entity.projectile.Projectile {

    protected BulletConfig config;
    public LivingEntity shooter;
    public float overrideDamage = 0;
    public int overrideMaxAge = -1;

    public EntityProjectileBase(EntityType<? extends net.minecraft.world.entity.projectile.Projectile> type, Level level) {
        super(type, level);
    }

    public EntityProjectileBase(EntityType<? extends net.minecraft.world.entity.projectile.Projectile> type, Level level, BulletConfig config, LivingEntity shooter) {
        super(type, level);
        this.config = config;
        this.shooter = shooter;
        this.setOwner(shooter);
    }

    public BulletConfig getConfig() {
        return config;
    }

    public void setConfig(BulletConfig config) {
        this.config = config;
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
    }

    @Override
    public void shoot(double x, double y, double z, float velocity, float inaccuracy) {
        Vec3 vec = new Vec3(x, y, z).normalize().add(
            this.random.nextGaussian() * inaccuracy * 0.5,
            this.random.nextGaussian() * inaccuracy * 0.5,
            this.random.nextGaussian() * inaccuracy * 0.5
        ).scale(velocity);
        this.setDeltaMovement(vec);
        float f = (float)(vec.x * vec.x + vec.y * vec.y + vec.z * vec.z);
        this.setYRot((float)(Math.atan2(vec.x, vec.z) * (180.0 / Math.PI)));
        this.setXRot((float)(Math.atan2(vec.y, Math.sqrt(f)) * (180.0 / Math.PI)));
        this.yRotO = this.getYRot();
        this.xRotO = this.getXRot();
    }

    public void shootFromEntity(LivingEntity entity, float velocity, float inaccuracy) {
        this.shooter = entity;
        this.setOwner(entity);
        Vec3 look = entity.getLookAngle();
        this.shoot(look.x, look.y, look.z, velocity, inaccuracy);
        this.setPos(entity.getX() + look.x * 0.2, entity.getEyeY() - 0.1, entity.getZ() + look.z * 0.2);
    }

    @Override
    public void tick() {
        super.tick();
        if (this.isRemoved()) return;

        if (config == null) {
            this.discard();
            return;
        }
        if (config.maxAge == 0) {
            this.discard();
            return;
        }

        Vec3 origin = this.position();
        Vec3 motion = this.getDeltaMovement();
        float vel = config.velocity;
        Vec3 destination = origin.add(motion.scale(vel));

        HitResult hitResult = this.level().clip(new net.minecraft.world.level.ClipContext(
            origin, destination,
            net.minecraft.world.level.ClipContext.Block.COLLIDER,
            net.minecraft.world.level.ClipContext.Fluid.NONE, this));

        EntityHitResult entityHit = this.findHitEntity(origin, destination);
        if (entityHit != null) {
            hitResult = entityHit;
        }

        if (hitResult.getType() != HitResult.Type.MISS) {
            if (!EventHooks.onProjectileImpact(this, hitResult)) {
                this.onHit(hitResult);
            }
        }

        if (!this.isRemoved()) {
            this.applyMovement();
        }

        if (this.tickCount > config.maxAge || (overrideMaxAge != -1 && this.tickCount > overrideMaxAge)) {
            this.discard();
        }
    }

    protected void applyMovement() {
        Vec3 motion = this.getDeltaMovement();
        if (config.gravity != 0) {
            motion = motion.add(0, -config.gravity, 0);
        }
        float vel = config.velocity;
        this.setPos(this.getX() + motion.x * vel, this.getY() + motion.y * vel, this.getZ() + motion.z * vel);
        this.setDeltaMovement(motion);

        float horizontalDist = (float)Math.sqrt(motion.x * motion.x + motion.z * motion.z);
        this.setYRot((float)(Math.atan2(motion.x, motion.z) * (180.0 / Math.PI)));
        this.setXRot((float)(Math.atan2(motion.y, horizontalDist) * (180.0 / Math.PI)));
    }

    protected EntityHitResult findHitEntity(Vec3 start, Vec3 end) {
        Vec3 hitVec = end;
        Entity closestEntity = null;
        double closestDist = Double.MAX_VALUE;

        Vec3 motionBox = end.subtract(start);
        double expandX = Math.abs(motionBox.x) + 1.0;
        double expandY = Math.abs(motionBox.y) + 1.0;
        double expandZ = Math.abs(motionBox.z) + 1.0;

        for (Entity entity : this.level().getEntities(this, this.getBoundingBox().expandTowards(
                motionBox.x, motionBox.y, motionBox.z).inflate(1.0))) {
            if (canHitEntity(entity)) {
                net.minecraft.world.phys.AABB box = entity.getBoundingBox().inflate(0.3);
                var hitOpt = box.clip(start, end);
                if (hitOpt.isPresent()) {
                    Vec3 hit = hitOpt.get();
                    double dist = start.distanceToSqr(hit);
                    if (dist < closestDist) {
                        closestDist = dist;
                        closestEntity = entity;
                        hitVec = hit;
                    }
                }
            }
        }

        if (closestEntity != null) {
            return new EntityHitResult(closestEntity, hitVec);
        }
        return null;
    }

    protected boolean canHitEntity(Entity entity) {
        return entity.isAlive() && entity.isPickable() && entity != this.shooter && !(entity instanceof net.minecraft.world.entity.projectile.Projectile);
    }

    @Override
    protected void onHit(HitResult result) {
        if (result.getType() == HitResult.Type.ENTITY) {
            onHitEntity((EntityHitResult) result);
        } else if (result.getType() == HitResult.Type.BLOCK) {
            onHitBlock((BlockHitResult) result);
        }
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        if (this.level().isClientSide) return;

        Entity target = result.getEntity();
        float damage = this.random.nextFloat() * (config.dmgMax - config.dmgMin) + config.dmgMin;
        if (overrideDamage != 0) damage = overrideDamage;

        DamageSource source;
        if (this.shooter != null) {
            source = this.damageSources().mobAttack(shooter);
        } else {
            source = this.damageSources().generic();
        }

        target.hurt(source, damage);

        if (config.bHurt != null) {
            config.bHurt.behaveEntityHurt(this, target);
        }

        applyEntityEffects(target);

        if (!config.doesPenetrate) {
            if (config.bHit != null) {
                config.bHit.behaveEntityHit(this, target);
            }
            if (!config.liveAfterImpact) {
                this.discard();
            }
        }
    }

    @Override
    protected void onHitBlock(BlockHitResult result) {
        if (this.level().isClientSide) return;

        BlockPos pos = result.getBlockPos();
        BlockState state = this.level().getBlockState(pos);

        if (config.bImpact != null) {
            config.bImpact.behaveBlockHit(this, pos.getX(), pos.getY(), pos.getZ());
        }

        applyBlockImpact(pos, state, result);

        if (!config.isSpectral && !config.doesRicochet) {
            if (!config.liveAfterImpact) {
                this.discard();
            }
        } else if (config.doesRicochet) {
            handleRicochet(result);
        }
    }

    protected void applyEntityEffects(Entity target) {
        if (config.incendiary > 0 && !this.level().isClientSide) {
            target.igniteForSeconds(config.incendiary);
        }

        if (config.leadChance > 0 && !this.level().isClientSide
                && this.random.nextInt(100) < config.leadChance
                && target instanceof LivingEntity living) {
            living.addEffect(new MobEffectInstance(ModMobEffects.LEAD, 600, 0));
        }

        if (config.instakill && target instanceof LivingEntity living && !this.level().isClientSide) {
            living.kill();
        }

        if (config.effects != null && !config.effects.isEmpty() && target instanceof LivingEntity living && !this.level().isClientSide) {
            for (MobEffectInstance effect : config.effects) {
                living.addEffect(new MobEffectInstance(effect));
            }
        }
    }

    protected void applyBlockImpact(BlockPos pos, BlockState state, BlockHitResult result) {
        if (config.incendiary > 0 && !this.level().isClientSide) {
            igniteSurrounding(pos);
        }

        if (config.explosive > 0 && !this.level().isClientSide) {
            this.level().explode(this, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5,
                config.explosive, config.incendiary > 0, Level.ExplosionInteraction.TNT);
        }

        if (config.destroysBlocks && !this.level().isClientSide) {
            float hardness = state.getDestroySpeed(this.level(), pos);
            if (hardness >= 0 && hardness <= 120) {
                this.level().removeBlock(pos, false);
            }
        } else if (config.doesBreakGlass && !this.level().isClientSide) {
            float resistance = state.getExplosionResistance(this.level(), pos, null);
            if (resistance < 0.6F && state.canOcclude()) {
                this.level().destroyBlock(pos, false);
            }
        }
    }

    protected void igniteSurrounding(BlockPos pos) {
        Level level = this.level();
        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                for (int dz = -1; dz <= 1; dz++) {
                    if (this.random.nextInt(3) == 0) {
                        BlockPos firePos = pos.offset(dx, dy, dz);
                        if (level.isEmptyBlock(firePos)) {
                            level.setBlock(firePos, net.minecraft.world.level.block.Blocks.FIRE.defaultBlockState(), 3);
                        }
                    }
                }
            }
        }
    }

    protected void handleRicochet(BlockHitResult result) {
        Vec3 motion = this.getDeltaMovement();
        net.minecraft.core.Direction dir = result.getDirection();
        switch (dir.getAxis()) {
            case X: motion = new Vec3(-motion.x, motion.y, motion.z); break;
            case Y: motion = new Vec3(motion.x, -motion.y, motion.z); break;
            case Z: motion = new Vec3(motion.x, motion.y, -motion.z); break;
        }
        motion = motion.scale(config.bounceMod);
        this.setDeltaMovement(motion);

        if (config.bRicochet != null) {
            config.bRicochet.behaveBlockRicochet(this,
                result.getBlockPos().getX(), result.getBlockPos().getY(), result.getBlockPos().getZ());
        }

        if (config.plink == 1) {
            this.level().playSound(null, this.getX(), this.getY(), this.getZ(),
                net.minecraft.sounds.SoundEvents.ANVIL_LAND, net.minecraft.sounds.SoundSource.NEUTRAL, 0.25F, 1.0F);
        }
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag tag) {
        // Config cannot be directly serialized; subclasses handle this
        overrideDamage = tag.getFloat("damage");
        overrideMaxAge = tag.getInt("overrideMaxAge");
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag tag) {
        tag.putFloat("damage", overrideDamage);
        tag.putInt("overrideMaxAge", overrideMaxAge);
    }

}
