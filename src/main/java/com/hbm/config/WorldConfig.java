package com.hbm.config;

import net.neoforged.neoforge.common.ModConfigSpec;

/**
 * 迁移自 1.12.2 com.hbm.config.WorldConfig。
 *
 * Configuration → ModConfigSpec：
 *   - build()：定义键值（键名与原 .cfg 完全一致，类别 02_ores / 17_biomes / 05_meteors）
 *   - load()：SpecValue.get() → 静态字段（全项目引用点不变）
 *   - float 配置：原 createConfigDouble + (float) 强转 → configDouble + load 时 (float) 强转
 */
public class WorldConfig {

    // ===== 字段（与原 CE 同名） =====
    public static boolean newBedrockOres = true;
    public static int limestoneSpawn = 1;
    public static boolean enableHematite = true;
    public static boolean enableMalachite = true;
    public static boolean enableBauxite = true;
    public static boolean enableSulfurCave = true;
    public static boolean enableAsbestosCave = true;
    public static boolean enableMeteorStrikes = true;
    public static boolean enableMeteorShowers = true;
    public static boolean enableMeteorTails = true;
    public static boolean enableSpecialMeteors = true;
    public static int meteorStrikeChance = 20 * 60 * 60 * 5;
    public static int meteorShowerChance = 20 * 60 * 15;
    public static int meteorShowerDuration = 20 * 60 * 30;
    public static boolean enableCraterBiomes = true;
    public static float craterBiomeRad = 5F;
    public static float craterBiomeInnerRad = 25F;
    public static float craterBiomeOuterRad = 0.5F;
    public static float craterBiomeWaterMult = 5F;
    public static int bedrockGlowstoneSpawn = 100;
    public static int bedrockPhosphorusSpawn = 50;
    public static int bedrockQuartzSpawn = 100;

    // ===== SpecValue =====
    private static ModConfigSpec.BooleanValue NEW_BEDROCK_ORES;
    private static ModConfigSpec.IntValue LIMESTONE_SPAWN;
    private static ModConfigSpec.BooleanValue ENABLE_HEMATITE;
    private static ModConfigSpec.BooleanValue ENABLE_MALACHITE;
    private static ModConfigSpec.BooleanValue ENABLE_BAUXITE;
    private static ModConfigSpec.BooleanValue ENABLE_SULFUR_CAVE;
    private static ModConfigSpec.BooleanValue ENABLE_ASBESTOS_CAVE;
    private static ModConfigSpec.BooleanValue ENABLE_CRATER_BIOMES;
    private static ModConfigSpec.DoubleValue CRATER_BIOME_RAD;
    private static ModConfigSpec.DoubleValue CRATER_BIOME_INNER_RAD;
    private static ModConfigSpec.DoubleValue CRATER_BIOME_OUTER_RAD;
    private static ModConfigSpec.DoubleValue CRATER_BIOME_WATER_MULT;
    private static ModConfigSpec.BooleanValue ENABLE_METEOR_STRIKES;
    private static ModConfigSpec.BooleanValue ENABLE_METEOR_SHOWERS;
    private static ModConfigSpec.BooleanValue ENABLE_METEOR_TAILS;
    private static ModConfigSpec.BooleanValue ENABLE_SPECIAL_METEORS;
    private static ModConfigSpec.IntValue METEOR_STRIKE_CHANCE;
    private static ModConfigSpec.IntValue METEOR_SHOWER_CHANCE;
    private static ModConfigSpec.IntValue METEOR_SHOWER_DURATION;

    public static void build(ModConfigSpec.Builder builder) {
        NEW_BEDROCK_ORES = CommonConfig.configBool(builder, CommonConfig.CATEGORY_ORES, "2.NB_newBedrockOres", "Enables the generation of bedrock ores", true);
        LIMESTONE_SPAWN = CommonConfig.configInt(builder, CommonConfig.CATEGORY_ORES, "2.L02_limestoneSpawn", "Amount of limestone block veins per chunk", 1);
        ENABLE_HEMATITE = CommonConfig.configBool(builder, CommonConfig.CATEGORY_ORES, "2.L00_enableHematite", "Toggles hematite deposits", true);
        ENABLE_MALACHITE = CommonConfig.configBool(builder, CommonConfig.CATEGORY_ORES, "2.L01_enableMalachite", "Toggles malachite deposits", true);
        ENABLE_BAUXITE = CommonConfig.configBool(builder, CommonConfig.CATEGORY_ORES, "2.L02_enableBauxite", "Toggles bauxite deposits", true);
        ENABLE_SULFUR_CAVE = CommonConfig.configBool(builder, CommonConfig.CATEGORY_ORES, "2.C00_enableSulfurCave", "Toggles sulfur caves", true);
        ENABLE_ASBESTOS_CAVE = CommonConfig.configBool(builder, CommonConfig.CATEGORY_ORES, "2.C01_enableAsbestosCave", "Toggles asbestos caves", true);
        ENABLE_CRATER_BIOMES = CommonConfig.configBool(builder, CommonConfig.CATEGORY_BIOMES, "17.B_toggle", "Enables the biome change caused by nuclear explosions", true);
        CRATER_BIOME_RAD = CommonConfig.configDouble(builder, CommonConfig.CATEGORY_BIOMES, "17.R00_craterBiomeRad", "RAD/s for the crater biome", 5D);
        CRATER_BIOME_INNER_RAD = CommonConfig.configDouble(builder, CommonConfig.CATEGORY_BIOMES, "17.R01_craterBiomeInnerRad", "RAD/s for the inner crater biome", 25D);
        CRATER_BIOME_OUTER_RAD = CommonConfig.configDouble(builder, CommonConfig.CATEGORY_BIOMES, "17.R02_craterBiomeOuterRad", "RAD/s for the outer crater biome", 0.5D);
        CRATER_BIOME_WATER_MULT = CommonConfig.configDouble(builder, CommonConfig.CATEGORY_BIOMES, "17.R03_craterBiomeWaterMult", "Multiplier for RAD/s in crater biomes when in water", 5D);
        ENABLE_METEOR_STRIKES = CommonConfig.configBool(builder, CommonConfig.CATEGORY_METEORS, "5.00_enableMeteorStrikes", "Toggles the spawning of meteors", true);
        ENABLE_METEOR_SHOWERS = CommonConfig.configBool(builder, CommonConfig.CATEGORY_METEORS, "5.01_enableMeteorShowers", "Toggles meteor showers, which start with a 1% chance for every spawned meteor", true);
        ENABLE_METEOR_TAILS = CommonConfig.configBool(builder, CommonConfig.CATEGORY_METEORS, "5.02_enableMeteorTails", "Toggles the particle effect created by falling meteors", true);
        ENABLE_SPECIAL_METEORS = CommonConfig.configBool(builder, CommonConfig.CATEGORY_METEORS, "5.03_enableSpecialMeteors", "Toggles rare, special meteor types with different impact effects", true);
        METEOR_STRIKE_CHANCE = CommonConfig.configInt(builder, CommonConfig.CATEGORY_METEORS, "5.03_meteorStrikeChance", "The probability of a meteor spawning (an average of once every nTH ticks)", 20 * 60 * 60 * 5);
        METEOR_SHOWER_CHANCE = CommonConfig.configInt(builder, CommonConfig.CATEGORY_METEORS, "5.04_meteorShowerChance", "The probability of a meteor spawning during meteor shower (an average of once every nTH ticks)", 20 * 60 * 15);
        METEOR_SHOWER_DURATION = CommonConfig.configInt(builder, CommonConfig.CATEGORY_METEORS, "5.05_meteorShowerDuration", "Max duration of meteor shower in ticks", 20 * 60 * 30);
    }

    public static void load() {
        newBedrockOres = NEW_BEDROCK_ORES.get();
        limestoneSpawn = LIMESTONE_SPAWN.get();
        enableHematite = ENABLE_HEMATITE.get();
        enableMalachite = ENABLE_MALACHITE.get();
        enableBauxite = ENABLE_BAUXITE.get();
        enableSulfurCave = ENABLE_SULFUR_CAVE.get();
        enableAsbestosCave = ENABLE_ASBESTOS_CAVE.get();
        enableCraterBiomes = ENABLE_CRATER_BIOMES.get();
        craterBiomeRad = (float) (double) CRATER_BIOME_RAD.get();
        craterBiomeInnerRad = (float) (double) CRATER_BIOME_INNER_RAD.get();
        craterBiomeOuterRad = (float) (double) CRATER_BIOME_OUTER_RAD.get();
        craterBiomeWaterMult = (float) (double) CRATER_BIOME_WATER_MULT.get();
        enableMeteorStrikes = ENABLE_METEOR_STRIKES.get();
        enableMeteorShowers = ENABLE_METEOR_SHOWERS.get();
        enableMeteorTails = ENABLE_METEOR_TAILS.get();
        enableSpecialMeteors = ENABLE_SPECIAL_METEORS.get();
        meteorStrikeChance = METEOR_STRIKE_CHANCE.get();
        meteorShowerChance = METEOR_SHOWER_CHANCE.get();
        meteorShowerDuration = METEOR_SHOWER_DURATION.get();
    }

    public static int convertToInt(Object e) {
        if (e == null) return 0;
        return (int) e;
    }
}
