package com.hbm.particle;

import com.hbm.Tags;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

/**
 * Particle type registration for HBM mod.
 * Registers SimpleParticleType for all custom HBM particles.
 */
public class ModParticleTypes {

    @SuppressWarnings("unchecked")
    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES =
            (DeferredRegister<ParticleType<?>>) (DeferredRegister<?>) DeferredRegister.create(BuiltInRegistries.PARTICLE_TYPE, Tags.MODID);

    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> ASHES =
            PARTICLE_TYPES.register("ashes", () -> new SimpleParticleType(false));
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> BLACK_POWDER_SMOKE =
            PARTICLE_TYPES.register("black_powder_smoke", () -> new SimpleParticleType(false));
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> BLACK_POWDER_SPARK =
            PARTICLE_TYPES.register("black_powder_spark", () -> new SimpleParticleType(false));
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> BLOOD =
            PARTICLE_TYPES.register("blood", () -> new SimpleParticleType(false));
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> CLOUD_FX =
            PARTICLE_TYPES.register("cloud_fx", () -> new SimpleParticleType(false));
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> COOLING_TOWER =
            PARTICLE_TYPES.register("cooling_tower", () -> new SimpleParticleType(false));
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> DEBRIS =
            PARTICLE_TYPES.register("debris", () -> new SimpleParticleType(false));
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> EXPLOSION_SMALL =
            PARTICLE_TYPES.register("explosion_small", () -> new SimpleParticleType(false));
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> EX_SMOKE =
            PARTICLE_TYPES.register("ex_smoke", () -> new SimpleParticleType(false));
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> FLAME_NT =
            PARTICLE_TYPES.register("flame_nt", () -> new SimpleParticleType(false));
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> FLAMETHROWER =
            PARTICLE_TYPES.register("flamethrower", () -> new SimpleParticleType(false));
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> GAS_FLAME =
            PARTICLE_TYPES.register("gas_flame", () -> new SimpleParticleType(false));
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> GIBLET =
            PARTICLE_TYPES.register("giblet", () -> new SimpleParticleType(false));
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> HADRONS =
            PARTICLE_TYPES.register("hadrons", () -> new SimpleParticleType(false));
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> HAZE =
            PARTICLE_TYPES.register("haze", () -> new SimpleParticleType(false));
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> HBM_SPARK =
            PARTICLE_TYPES.register("hbm_spark", () -> new SimpleParticleType(false));
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> HEAT_DISTORTION =
            PARTICLE_TYPES.register("heat_distortion", () -> new SimpleParticleType(false));
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> JETPACK_TRAIL =
            PARTICLE_TYPES.register("jetpack_trail", () -> new SimpleParticleType(false));
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> LARGE_FLAME =
            PARTICLE_TYPES.register("large_flame", () -> new SimpleParticleType(false));
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> LIGHTNING =
            PARTICLE_TYPES.register("lightning", () -> new SimpleParticleType(false));
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> LIQUID_SPLASH =
            PARTICLE_TYPES.register("liquid_splash", () -> new SimpleParticleType(false));
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> MUKE_CLOUD =
            PARTICLE_TYPES.register("muke_cloud", () -> new SimpleParticleType(false));
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> MUKE_FLASH =
            PARTICLE_TYPES.register("muke_flash", () -> new SimpleParticleType(false));
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> PLASMA_BLAST =
            PARTICLE_TYPES.register("plasma_blast", () -> new SimpleParticleType(false));
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> RADIATION_FOG =
            PARTICLE_TYPES.register("radiation_fog", () -> new SimpleParticleType(false));
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> RBMK_FLAME =
            PARTICLE_TYPES.register("rbmk_flame", () -> new SimpleParticleType(false));
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> RBMK_MUSH =
            PARTICLE_TYPES.register("rbmk_mush", () -> new SimpleParticleType(false));
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> RBMK_STEAM =
            PARTICLE_TYPES.register("rbmk_steam", () -> new SimpleParticleType(false));
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> RIFT =
            PARTICLE_TYPES.register("rift", () -> new SimpleParticleType(false));
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> ROCKET_FLAME =
            PARTICLE_TYPES.register("rocket_flame", () -> new SimpleParticleType(false));
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> SMOKE_PLUME =
            PARTICLE_TYPES.register("smoke_plume", () -> new SimpleParticleType(false));
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> SPARK =
            PARTICLE_TYPES.register("spark", () -> new SimpleParticleType(false));
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> SPENT_CASING =
            PARTICLE_TYPES.register("spent_casing", () -> new SimpleParticleType(false));
}
