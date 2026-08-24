package com.hbm.entity.projectile;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

import java.util.ArrayList;
import java.util.List;

public class BulletConfig {

    public float velocity = 5.0F;
    public float spread = 0.0F;
    public int wear = 10;
    public int bulletsMin = 1;
    public int bulletsMax = 1;
    public float dmgMin = 5.0F;
    public float dmgMax = 5.0F;
    public float headshotMult = 1.0F;
    public float gravity = 0.0F;
    public int maxAge = 100;

    public boolean doesRicochet = false;
    public float ricochetAngle = 30.0F;
    public int LBRC = 100;
    public int HBRC = 0;
    public float bounceMod = 0.5F;
    public boolean doesPenetrate = false;
    public boolean isSpectral = false;
    public boolean doesBreakGlass = false;
    public boolean liveAfterImpact = false;

    public int incendiary = 0;
    public int emp = 0;
    public float explosive = 0;
    public float blockDamage = 0;
    public int jolt = 0;
    public int rainbow = 0;
    public int nuke = 0;
    public int shrapnel = 0;
    public int chlorine = 0;
    public int leadChance = 0;
    public int caustic = 0;
    public boolean instakill = false;
    public boolean destroysBlocks = false;
    public boolean destroysWood = false;

    public int style = STYLE_NONE;
    public int trail = 0;
    public int plink = 0;

    public List<MobEffectInstance> effects = new ArrayList<>();

    public IBulletHurtBehavior bHurt;
    public IBulletHitBehavior bHit;
    public IBulletRicochetBehavior bRicochet;
    public IBulletImpactBehavior bImpact;
    public IBulletUpdateBehavior bUpdate;

    public static final int STYLE_NONE = -1;
    public static final int STYLE_PISTOL = 0;
    public static final int STYLE_HEAVY = 1;
    public static final int STYLE_BUCKSHOT = 2;
    public static final int STYLE_ROCKET = 3;
    public static final int STYLE_TRACER = 4;
    public static final int STYLE_FLAMER = 5;
    public static final int STYLE_LASER = 6;
    public static final int STYLE_BOLT = 7;
    public static final int STYLE_ZOMG = 8;
    public static final int STYLE_NIGHTMARE = 9;
    public static final int STYLE_LEADBURSTER = 18;

    public BulletConfig clone() {
        try {
            return (BulletConfig) super.clone();
        } catch (CloneNotSupportedException e) {
            return new BulletConfig();
        }
    }

    public interface IBulletHurtBehavior {
        void behaveEntityHurt(EntityProjectileBase bullet, Entity hit);
    }

    public interface IBulletHitBehavior {
        void behaveEntityHit(EntityProjectileBase bullet, Entity hit);
    }

    public interface IBulletRicochetBehavior {
        void behaveBlockRicochet(EntityProjectileBase bullet, int x, int y, int z);
    }

    public interface IBulletImpactBehavior {
        void behaveBlockHit(EntityProjectileBase bullet, int x, int y, int z);
    }

    public interface IBulletUpdateBehavior {
        void behaveUpdate(EntityProjectileBase bullet);
    }
}
