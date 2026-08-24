package com.hbm.config;

import net.neoforged.neoforge.common.ModConfigSpec;

/**
 * 迁移自 1.12.2 com.hbm.config.RadiationConfig。
 *
 * ⚠️ 修复了原 CE 的一个 bug：原 loadFromConfig 中
 *   railgunBuffer/railgunUse 的读取结果被错误地赋给了 railgunDamage
 *   （railgunDamage = railBuffer.getInt(); railgunDamage = railUse.getInt();），
 *   迁移时改为赋给各自的字段。
 */
public class RadiationConfig {

    // ===== 字段（与原 CE 同名） =====
    public static int rain = 0;
    public static int cont = 0;
    public static int fogRad = 100;
    public static int fogCh = 50;
    public static int worldRad = 10;
    public static int worldRadThreshold = 20;
    public static boolean worldRadEffects = true;
    public static boolean enableContamination = true;
    public static boolean enableContaminationOnGround = false;
    public static int blocksFallCh = 100;

    public static int railgunDamage = 1000;
    public static int railgunBuffer = 500000000;
    public static int railgunUse = 250000000;
    public static int fireDuration = 4 * 20;
    public static boolean neutronActivation = false;
    public static int neutronActivationThreshold = 15;

    public static int digammaX = 16;
    public static int digammaY = 18;

    public static int hazardRate = 5;
    public static boolean disableAsbestos = false;
    public static boolean disableBlinding = false;
    public static boolean disableCoal = false;
    public static boolean disableExplosive = false;
    public static boolean disableHydro = false;
    public static boolean disableHot = false;
    public static boolean disableCold = false;
    public static boolean disableToxic = false;

    public static boolean enablePollution = true;
    public static boolean enableLeadFromBlocks = true;
    public static boolean enableLeadPoisoning = true;
    public static boolean enableSootFog = true;
    public static boolean enablePoison = true;
    public static double buffMobThreshold = 15D;
    public static double sootFogThreshold = 35D;
    public static double sootFogDivisor = 120D;
    public static double smokeStackSootMult = 0.8;
    public static int radTickRate = 1;
    public static double radHalfLifeSeconds = 120D;
    public static double radDiffusivity = 10.0;

    // ===== SpecValue =====
    private static ModConfigSpec.IntValue RAIN;
    private static ModConfigSpec.IntValue CONT;
    private static ModConfigSpec.IntValue FOG_RAD;
    private static ModConfigSpec.IntValue FOG_CH;
    private static ModConfigSpec.IntValue WORLD_RAD;
    private static ModConfigSpec.IntValue WORLD_RAD_THRESHOLD;
    private static ModConfigSpec.BooleanValue WORLD_RAD_EFFECTS;
    private static ModConfigSpec.BooleanValue ENABLE_CONTAMINATION;
    private static ModConfigSpec.BooleanValue ENABLE_CONTAMINATION_ON_GROUND;
    private static ModConfigSpec.IntValue BLOCKS_FALL_CH;
    private static ModConfigSpec.IntValue RAILGUN_DAMAGE;
    private static ModConfigSpec.IntValue RAILGUN_BUFFER;
    private static ModConfigSpec.IntValue RAILGUN_USE;
    private static ModConfigSpec.IntValue FIRE_DURATION;
    private static ModConfigSpec.BooleanValue NEUTRON_ACTIVATION;
    private static ModConfigSpec.IntValue NEUTRON_ACTIVATION_THRESHOLD;
    private static ModConfigSpec.IntValue DIGAMMA_X;
    private static ModConfigSpec.IntValue DIGAMMA_Y;
    private static ModConfigSpec.IntValue RAD_TICK_RATE;
    private static ModConfigSpec.DoubleValue RAD_HALF_LIFE_SECONDS;
    private static ModConfigSpec.DoubleValue RAD_DIFFUSIVITY;
    private static ModConfigSpec.IntValue HAZARD_RATE;
    private static ModConfigSpec.BooleanValue DISABLE_ASBESTOS;
    private static ModConfigSpec.BooleanValue DISABLE_BLINDING;
    private static ModConfigSpec.BooleanValue DISABLE_COAL;
    private static ModConfigSpec.BooleanValue DISABLE_EXPLOSIVE;
    private static ModConfigSpec.BooleanValue DISABLE_HYDRO;
    private static ModConfigSpec.BooleanValue DISABLE_HOT;
    private static ModConfigSpec.BooleanValue DISABLE_COLD;
    private static ModConfigSpec.BooleanValue DISABLE_TOXIC;
    private static ModConfigSpec.BooleanValue ENABLE_POLLUTION;
    private static ModConfigSpec.BooleanValue ENABLE_LEAD_FROM_BLOCKS;
    private static ModConfigSpec.BooleanValue ENABLE_LEAD_POISONING;
    private static ModConfigSpec.BooleanValue ENABLE_SOOT_FOG;
    private static ModConfigSpec.BooleanValue ENABLE_POISON;
    private static ModConfigSpec.DoubleValue BUFF_MOB_THRESHOLD;
    private static ModConfigSpec.DoubleValue SOOT_FOG_THRESHOLD;
    private static ModConfigSpec.DoubleValue SOOT_FOG_DIVISOR;
    private static ModConfigSpec.DoubleValue SMOKE_STACK_SOOT_MULT;

    public static void build(ModConfigSpec.Builder builder) {
        RAIN = CommonConfig.configInt(builder, CommonConfig.CATEGORY_RADIATION, "13.12_falloutRainDuration", "Duration of the thunderstorm after fallout in ticks (only large explosions)", 2000);
        CONT = CommonConfig.configInt(builder, CommonConfig.CATEGORY_RADIATION, "13.13_falloutRainRadiation", "Radiation in 100th RADs created by fallout rain", 1000);
        FOG_RAD = CommonConfig.configInt(builder, CommonConfig.CATEGORY_RADIATION, "13.14_fogThreshold", "Radiation in RADs required for fog to spawn", 100);
        FOG_CH = CommonConfig.configInt(builder, CommonConfig.CATEGORY_RADIATION, "13.14_fogChance", "1:n chance of fog spawning every second - default 1/50", 50);
        WORLD_RAD = CommonConfig.configInt(builder, CommonConfig.CATEGORY_RADIATION, "13.15_worldRadCount", "How many block operations radiation can perform per tick", 10);
        WORLD_RAD_THRESHOLD = CommonConfig.configInt(builder, CommonConfig.CATEGORY_RADIATION, "13.16_worldRadThreshold", "The least amount of RADs required for block modification to happen", 40);
        WORLD_RAD_EFFECTS = CommonConfig.configBool(builder, CommonConfig.CATEGORY_RADIATION, "13.17_worldRadEffects", "Whether high radiation levels should perform changes in the world", true);
        ENABLE_CONTAMINATION = CommonConfig.configBool(builder, CommonConfig.CATEGORY_RADIATION, "13.18_enableContamination", "Toggles player contamination (and negative effects from radiation poisoning)", true);
        ENABLE_CONTAMINATION_ON_GROUND = CommonConfig.configBool(builder, CommonConfig.CATEGORY_RADIATION, "13.18.1_enableContaminationOnGround", "Toggles contamination for items being on-ground", false);
        BLOCKS_FALL_CH = CommonConfig.configInt(builder, CommonConfig.CATEGORY_RADIATION, "13.19_blocksFallingChance", "The chance (in percentage form) that a block with low blast resistance will fall down. -1 Disables falling", 100);

        RAILGUN_DAMAGE = CommonConfig.configInt(builder, CommonConfig.CATEGORY_EXPLOSIONS, "6.20_railgunDamage", "How much damage a railgun death blast does per tick", 1000);
        RAILGUN_BUFFER = CommonConfig.configInt(builder, CommonConfig.CATEGORY_EXPLOSIONS, "6.21_railgunBuffer", "How much RF the railgun can store", 500000000);
        RAILGUN_USE = CommonConfig.configInt(builder, CommonConfig.CATEGORY_EXPLOSIONS, "6.22_railgunConsumption", "How much RF the railgun requires per shot", 250000000);
        FIRE_DURATION = CommonConfig.configInt(builder, CommonConfig.CATEGORY_EXPLOSIONS, "6.23_fireDuration", "How long the fire blast will last in ticks", 15 * 20);

        NEUTRON_ACTIVATION = CommonConfig.configBool(builder, CommonConfig.CATEGORY_RADIATION, "7.01_itemContamination", "Whether high radiation levels should radiate items in inventory. WARNING: extremely laggy and and buggy. Keep it off unless you know what you are doing", false);
        NEUTRON_ACTIVATION_THRESHOLD = CommonConfig.configInt(builder, CommonConfig.CATEGORY_RADIATION, "7.01_itemContaminationThreshold", "Minimum recieved Rads/s threshold at which items get irradiated", 15);
        DIGAMMA_X = CommonConfig.configInt(builder, CommonConfig.CATEGORY_RADIATION, "7.02_digammaX", "X Coordinate of the digamma diagnostic gui (x=0 is on the right)", 16);
        DIGAMMA_Y = CommonConfig.configInt(builder, CommonConfig.CATEGORY_RADIATION, "7.03_digammaY", "Y Coordinate of the digamma diagnostic gui (y=0 is on the bottom)", 18);
        RAD_TICK_RATE = CommonConfig.configInt(builder, CommonConfig.CATEGORY_RADIATION, "7.99_CE_01_radTickRate", "How many ticks between each radiation system updates. 1 = once per tick", 1);
        RAD_HALF_LIFE_SECONDS = CommonConfig.configDouble(builder, CommonConfig.CATEGORY_RADIATION, "7.99_CE_02_radHalfLifeSeconds", "The half life of chunk radiation in seconds", 120);
        RAD_DIFFUSIVITY = CommonConfig.configDouble(builder, CommonConfig.CATEGORY_RADIATION, "7.99_CE_03_radDiffusivity", "The diffusivity of chunk radiation.", 10.0);

        HAZARD_RATE = CommonConfig.configInt(builder, CommonConfig.CATEGORY_HAZARD, "14.99_CE_04_hazardRate", "Ticks between application of effects for the hazards", 5);
        DISABLE_ASBESTOS = CommonConfig.configBool(builder, CommonConfig.CATEGORY_HAZARD, "14.99_CE_05_disableAsbestos", "Setting it true makes Asbestos Hazard to do nothing", false);
        DISABLE_BLINDING = CommonConfig.configBool(builder, CommonConfig.CATEGORY_HAZARD, "14.99_CE_06_disableBlinding", "Setting it true makes Blinding Hazard to do nothing", false);
        DISABLE_COAL = CommonConfig.configBool(builder, CommonConfig.CATEGORY_HAZARD, "14.99_CE_07_disableCoal", "Setting it true makes Coal Hazard to do nothing", false);
        DISABLE_EXPLOSIVE = CommonConfig.configBool(builder, CommonConfig.CATEGORY_HAZARD, "14.99_CE_08_disableExplosive", "Setting it true makes Explosive Hazard to do nothing", false);
        DISABLE_HYDRO = CommonConfig.configBool(builder, CommonConfig.CATEGORY_HAZARD, "14.99_CE_09_disableHydro", "Setting it true makes Hydro Hazard to do nothing", false);
        DISABLE_HOT = CommonConfig.configBool(builder, CommonConfig.CATEGORY_HAZARD, "14.99_CE_10_disableHot", "Setting it true makes Hot Hazard to do nothing", false);
        DISABLE_COLD = CommonConfig.configBool(builder, CommonConfig.CATEGORY_HAZARD, "14.99_CE_11_disableCold", "Setting it true makes Cold Hazard to do nothing", false);
        DISABLE_TOXIC = CommonConfig.configBool(builder, CommonConfig.CATEGORY_HAZARD, "14.99_CE_12_disableToxic", "Setting it true makes Toxic Hazard to do nothing", false);

        ENABLE_POLLUTION = CommonConfig.configBool(builder, CommonConfig.CATEGORY_POLLUTION, "16.01_enablePollution", "If disabled, none of the polltuion related things will work", true);
        ENABLE_LEAD_FROM_BLOCKS = CommonConfig.configBool(builder, CommonConfig.CATEGORY_POLLUTION, "16.02_enableLeadFromBlocks", "Whether breaking blocks in heavy metal polluted areas will poison the player", true);
        ENABLE_LEAD_POISONING = CommonConfig.configBool(builder, CommonConfig.CATEGORY_POLLUTION, "16.03_enableLeadPoisoning", "Whether being in a heavy metal polluted area will poison the player", true);
        ENABLE_SOOT_FOG = CommonConfig.configBool(builder, CommonConfig.CATEGORY_POLLUTION, "16.04_enableSootFog", "Whether smog should be visible", true);
        ENABLE_POISON = CommonConfig.configBool(builder, CommonConfig.CATEGORY_POLLUTION, "16.05_enablePoison", "Whether being in a poisoned area will affect the player", true);
        BUFF_MOB_THRESHOLD = CommonConfig.configDouble(builder, CommonConfig.CATEGORY_POLLUTION, "16.06_buffMobThreshold", "The amount of soot required to buff naturally spawning mobs", 15D);
        SOOT_FOG_THRESHOLD = CommonConfig.configDouble(builder, CommonConfig.CATEGORY_POLLUTION, "16.07_sootFogThreshold", "How much soot is required for smog to become visible", 35D);
        SOOT_FOG_DIVISOR = CommonConfig.configDouble(builder, CommonConfig.CATEGORY_POLLUTION, "16.08_sootFogDivisor", "The divisor for smog, higher numbers will require more soot for the same smog density", 120D);
        SMOKE_STACK_SOOT_MULT = CommonConfig.configDouble(builder, CommonConfig.CATEGORY_POLLUTION, "16.09_smokeStackSootMult", "How much does smokestack multiply soot by, with decimal values reducing the soot", 0.8);
    }

    public static void load() {
        rain = RAIN.get();
        cont = CONT.get();
        fogRad = FOG_RAD.get();
        fogCh = FOG_CH.get();
        worldRad = WORLD_RAD.get();
        worldRadThreshold = WORLD_RAD_THRESHOLD.get();
        worldRadEffects = WORLD_RAD_EFFECTS.get();
        enableContamination = ENABLE_CONTAMINATION.get();
        enableContaminationOnGround = ENABLE_CONTAMINATION_ON_GROUND.get();
        blocksFallCh = BLOCKS_FALL_CH.get();

        railgunDamage = RAILGUN_DAMAGE.get();
        railgunBuffer = RAILGUN_BUFFER.get();   // 修复原 bug：原代码错误赋值给 railgunDamage
        railgunUse = RAILGUN_USE.get();         // 修复原 bug：同上
        fireDuration = FIRE_DURATION.get();

        // 原: fogCh = CommonConfig.setDef(RadiationConfig.fogCh, 20);
        fogCh = CommonConfig.setDef(fogCh, 20);

        neutronActivation = NEUTRON_ACTIVATION.get();
        neutronActivationThreshold = NEUTRON_ACTIVATION_THRESHOLD.get();
        digammaX = DIGAMMA_X.get();
        digammaY = DIGAMMA_Y.get();
        radTickRate = RAD_TICK_RATE.get();
        radHalfLifeSeconds = RAD_HALF_LIFE_SECONDS.get();
        radDiffusivity = RAD_DIFFUSIVITY.get();

        hazardRate = HAZARD_RATE.get();
        disableAsbestos = DISABLE_ASBESTOS.get();
        disableBlinding = DISABLE_BLINDING.get();
        disableCoal = DISABLE_COAL.get();
        disableExplosive = DISABLE_EXPLOSIVE.get();
        disableHydro = DISABLE_HYDRO.get();
        disableHot = DISABLE_HOT.get();
        disableCold = DISABLE_COLD.get();
        disableToxic = DISABLE_TOXIC.get();

        enablePollution = ENABLE_POLLUTION.get();
        enableLeadFromBlocks = ENABLE_LEAD_FROM_BLOCKS.get();
        enableLeadPoisoning = ENABLE_LEAD_POISONING.get();
        enableSootFog = ENABLE_SOOT_FOG.get();
        enablePoison = ENABLE_POISON.get();
        buffMobThreshold = BUFF_MOB_THRESHOLD.get();
        sootFogThreshold = SOOT_FOG_THRESHOLD.get();
        sootFogDivisor = SOOT_FOG_DIVISOR.get();
        smokeStackSootMult = SMOKE_STACK_SOOT_MULT.get();
    }
}
