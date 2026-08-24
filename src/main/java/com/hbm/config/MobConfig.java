package com.hbm.config;

import net.neoforged.neoforge.common.ModConfigSpec;

import java.util.List;

/**
 * 迁移自 1.12.2 com.hbm.config.MobConfig。
 * createConfigIntList → configIntList + CommonConfig.toIntArray；
 * addCustomCategoryComment（类别注释）→ ModConfigSpec 不支持，跳过；
 * if(rampantMode) 联动逻辑保留在 load()（含对 RadiationConfig.sootFogThreshold 的修改）。
 */
public class MobConfig {

    public static boolean enableMaskman = true;
    public static int maskmanDelay = 60 * 60 * 60;
    public static int maskmanChance = 3;
    public static int maskmanMinRad = 50;
    public static boolean maskmanUnderground = true;

    public static boolean enableRaids = false;
    public static int raidDelay = 30 * 60 * 60;
    public static int raidChance = 3;
    public static int raidAmount = 15;
    public static int raidDrones = 5;
    public static int raidAttackDelay = 40;
    public static int raidAttackReach = 2;
    public static int raidAttackDistance = 32;

    public static boolean enableElementals = true;
    public static int elementalDelay = 30 * 60 * 60;
    public static int elementalChance = 2;
    public static int elementalAmount = 10;
    public static int elementalDistance = 32;
    public static boolean enableDucks = true;
    public static boolean enableMobGear = true;
    public static boolean enableMobWeapons = true;
    public static double mobWeaponSootReduction = 0;

    public static boolean enableHives = true;
    public static int hiveSpawn = 256;
    public static double scoutThreshold = 5;
    public static int scoutSwarmSpawnChance = 2;
    public static boolean waypointDebug = false;
    public static int largeHiveChance = 5;
    public static int largeHiveThreshold = 30;

    public static int swarmCooldown = 120 * 20;

    public static int baseSwarmSize = 5;
    public static double swarmScalingMult = 1.2;
    public static int sootStep = 50;

    public static int[] glyphidChance = {50, -40, 0};
    public static int[] brawlerChance = {5, 35, 1};
    public static int[] bombardierChance = {20, -15, 1};
    public static int[] blasterChance = {-15, 40, 5};
    public static int[] diggerChance = {-15, 25, 5};
    public static int[] behemothChance = {-30, 45, 10};
    public static int[] brendaChance = {-50, 60, 20};
    public static int[] johnsonChance = {-50, 60, 50};

    public static double spawnMax = 50;
    public static boolean enableInfestation = true;
    public static double baseInfestChance = 5;
    public static double targetingThreshold = 1;

    public static boolean rampantMode = false;
    public static boolean rampantNaturalScoutSpawn = false;
    public static double rampantScoutSpawnThresh = 14;
    public static int rampantScoutSpawnChance = 1400;
    public static boolean scoutInitialSpawn = false;
    public static boolean rampantExtendedTargetting = false;
    public static boolean rampantDig = false;
    public static boolean rampantGlyphidGuidance = false;
    public static double rampantSmokeStackOverride = 0.4;
    public static double pollutionMult = 3;

    private static ModConfigSpec.BooleanValue ENABLE_MASKMAN;
    private static ModConfigSpec.IntValue MASKMAN_DELAY;
    private static ModConfigSpec.IntValue MASKMAN_CHANCE;
    private static ModConfigSpec.IntValue MASKMAN_MIN_RAD;
    private static ModConfigSpec.BooleanValue MASKMAN_UNDERGROUND;
    private static ModConfigSpec.BooleanValue ENABLE_RAIDS;
    private static ModConfigSpec.IntValue RAID_DELAY;
    private static ModConfigSpec.IntValue RAID_CHANCE;
    private static ModConfigSpec.IntValue RAID_AMOUNT;
    private static ModConfigSpec.IntValue RAID_ATTACK_DELAY;
    private static ModConfigSpec.IntValue RAID_ATTACK_REACH;
    private static ModConfigSpec.IntValue RAID_ATTACK_DISTANCE;
    private static ModConfigSpec.IntValue RAID_DRONES;
    private static ModConfigSpec.BooleanValue ENABLE_ELEMENTALS;
    private static ModConfigSpec.IntValue ELEMENTAL_DELAY;
    private static ModConfigSpec.IntValue ELEMENTAL_CHANCE;
    private static ModConfigSpec.IntValue ELEMENTAL_AMOUNT;
    private static ModConfigSpec.IntValue ELEMENTAL_DISTANCE;
    private static ModConfigSpec.BooleanValue ENABLE_DUCKS;
    private static ModConfigSpec.BooleanValue ENABLE_MOB_GEAR;
    private static ModConfigSpec.BooleanValue ENABLE_MOB_WEAPONS;
    private static ModConfigSpec.DoubleValue MOB_WEAPON_SOOT_REDUCTION;
    private static ModConfigSpec.BooleanValue ENABLE_HIVES;
    private static ModConfigSpec.IntValue HIVE_SPAWN;
    private static ModConfigSpec.DoubleValue SCOUT_THRESHOLD;
    private static ModConfigSpec.DoubleValue SPAWN_MAX;
    private static ModConfigSpec.DoubleValue TARGETING_THRESHOLD;
    private static ModConfigSpec.IntValue SCOUT_SWARM_SPAWN_CHANCE;
    private static ModConfigSpec.IntValue LARGE_HIVE_CHANCE;
    private static ModConfigSpec.IntValue LARGE_HIVE_THRESHOLD;
    private static ModConfigSpec.BooleanValue WAYPOINT_DEBUG;
    private static ModConfigSpec.BooleanValue ENABLE_INFESTATION;
    private static ModConfigSpec.DoubleValue BASE_INFEST_CHANCE;
    private static ModConfigSpec.IntValue BASE_SWARM_SIZE;
    private static ModConfigSpec.DoubleValue SWARM_SCALING_MULT;
    private static ModConfigSpec.IntValue SOOT_STEP;
    private static ModConfigSpec.IntValue SWARM_COOLDOWN;
    private static ModConfigSpec.ConfigValue<List<? extends Integer>> GLYPHID_CHANCE;
    private static ModConfigSpec.ConfigValue<List<? extends Integer>> BRAWLER_CHANCE;
    private static ModConfigSpec.ConfigValue<List<? extends Integer>> BOMBARDIER_CHANCE;
    private static ModConfigSpec.ConfigValue<List<? extends Integer>> BLASTER_CHANCE;
    private static ModConfigSpec.ConfigValue<List<? extends Integer>> DIGGER_CHANCE;
    private static ModConfigSpec.ConfigValue<List<? extends Integer>> BEHEMOTH_CHANCE;
    private static ModConfigSpec.ConfigValue<List<? extends Integer>> BRENDA_CHANCE;
    private static ModConfigSpec.ConfigValue<List<? extends Integer>> JOHNSON_CHANCE;
    private static ModConfigSpec.BooleanValue RAMPANT_MODE;
    private static ModConfigSpec.BooleanValue RAMPANT_NATURAL_SCOUT_SPAWN;
    private static ModConfigSpec.DoubleValue RAMPANT_SCOUT_SPAWN_THRESH;
    private static ModConfigSpec.IntValue RAMPANT_SCOUT_SPAWN_CHANCE;
    private static ModConfigSpec.BooleanValue SCOUT_INITIAL_SPAWN;
    private static ModConfigSpec.BooleanValue RAMPANT_EXTENDED_TARGETTING;
    private static ModConfigSpec.BooleanValue RAMPANT_DIG;
    private static ModConfigSpec.BooleanValue RAMPANT_GLYPHID_GUIDANCE;
    private static ModConfigSpec.DoubleValue RAMPANT_SMOKE_STACK_OVERRIDE;
    private static ModConfigSpec.DoubleValue POLLUTION_MULT;

    public static void build(ModConfigSpec.Builder builder) {
        ENABLE_MASKMAN = CommonConfig.configBool(builder, CommonConfig.CATEGORY_MOBS, "12.M00_enableMaskman", "Whether mask man should spawn", true);
        MASKMAN_DELAY = CommonConfig.configInt(builder, CommonConfig.CATEGORY_MOBS, "12.M01_maskmanDelay", "How many world ticks need to pass for a check to be performed", 60 * 60 * 60);
        MASKMAN_CHANCE = CommonConfig.configInt(builder, CommonConfig.CATEGORY_MOBS, "12.M02_maskmanChance", "1:x chance to spawn mask man, must be at least 1", 3);
        MASKMAN_MIN_RAD = CommonConfig.configInt(builder, CommonConfig.CATEGORY_MOBS, "12.M03_maskmanMinRad", "The amount of radiation needed for mask man to spawn", 50);
        MASKMAN_UNDERGROUND = CommonConfig.configBool(builder, CommonConfig.CATEGORY_MOBS, "12.M04_maskmanUnderound", "Whether players need to be underground for mask man to spawn", true);
        ENABLE_RAIDS = CommonConfig.configBool(builder, CommonConfig.CATEGORY_MOBS, "12.F00_enableFBIRaids", "Whether there should be FBI raids", false);
        RAID_DELAY = CommonConfig.configInt(builder, CommonConfig.CATEGORY_MOBS, "12.F01_raidDelay", "How many world ticks need to pass for a check to be performed", 30 * 60 * 60);
        RAID_CHANCE = CommonConfig.configInt(builder, CommonConfig.CATEGORY_MOBS, "12.F02_raidChance", "1:x chance to spawn a raid, must be at least 1", 3);
        RAID_AMOUNT = CommonConfig.configInt(builder, CommonConfig.CATEGORY_MOBS, "12.F03_raidAmount", "How many FBI agents are spawned each raid", 15);
        RAID_ATTACK_DELAY = CommonConfig.configInt(builder, CommonConfig.CATEGORY_MOBS, "12.F04_raidAttackDelay", "Time between individual attempts to break machines", 40);
        RAID_ATTACK_REACH = CommonConfig.configInt(builder, CommonConfig.CATEGORY_MOBS, "12.F05_raidAttackReach", "How far away machines can be broken", 2);
        RAID_ATTACK_DISTANCE = CommonConfig.configInt(builder, CommonConfig.CATEGORY_MOBS, "12.F06_raidAttackDistance", "How far away agents will spawn from the targeted player", 32);
        RAID_DRONES = CommonConfig.configInt(builder, CommonConfig.CATEGORY_MOBS, "12.F07_raidDrones", "How many quadcopter drones are spawned each raid", 5);
        ENABLE_ELEMENTALS = CommonConfig.configBool(builder, CommonConfig.CATEGORY_MOBS, "12.E00_enableMeltdownElementals", "Whether there should be radiation elementals", true);
        ELEMENTAL_DELAY = CommonConfig.configInt(builder, CommonConfig.CATEGORY_MOBS, "12.E01_elementalDelay", "How many world ticks need to pass for a check to be performed", 30 * 60 * 60);
        ELEMENTAL_CHANCE = CommonConfig.configInt(builder, CommonConfig.CATEGORY_MOBS, "12.E02_elementalChance", "1:x chance to spawn elementals, must be at least 1", 2);
        ELEMENTAL_AMOUNT = CommonConfig.configInt(builder, CommonConfig.CATEGORY_MOBS, "12.E03_elementalAmount", "How many elementals are spawned each raid", 10);
        ELEMENTAL_DISTANCE = CommonConfig.configInt(builder, CommonConfig.CATEGORY_MOBS, "12.E04_elementalAttackDistance", "How far away elementals will spawn from the targeted player", 32);
        ENABLE_DUCKS = CommonConfig.configBool(builder, CommonConfig.CATEGORY_MOBS, "12.D00_enableDucks", "Whether pressing O should allow the player to duck", true);
        ENABLE_MOB_GEAR = CommonConfig.configBool(builder, CommonConfig.CATEGORY_MOBS, "12.D01_enableMobGear", "Whether zombies and skeletons should have additional gear when spawning", true);
        ENABLE_MOB_WEAPONS = CommonConfig.configBool(builder, CommonConfig.CATEGORY_MOBS, "12.D02_enableMobWeapons", "Whether skeletons should have bows replaced with guns when spawning at higher soot levels", true);
        MOB_WEAPON_SOOT_REDUCTION = CommonConfig.configDouble(builder, CommonConfig.CATEGORY_MOBS, "12.D03_mobWeaponSootReduction", "Reduces the amount of soot needed for skeleton guns to appear", 0D);
        ENABLE_HIVES = CommonConfig.configBool(builder, CommonConfig.CATEGORY_MOBS, "12.G00_enableHives", "Whether glyphid hives should spawn", true);
        HIVE_SPAWN = CommonConfig.configInt(builder, CommonConfig.CATEGORY_MOBS, "12.G01_hiveSpawn", "The average amount of chunks per hive", 256);
        SCOUT_THRESHOLD = CommonConfig.configDouble(builder, CommonConfig.CATEGORY_MOBS, "12.G02_scoutThreshold", "Minimum amount of soot for scouts to spawn", 1);
        SPAWN_MAX = CommonConfig.configDouble(builder, CommonConfig.CATEGORY_MOBS, "12.G07_spawnMax", "Maximum amount of glyphids being able to exist at once through natural spawning", 50);
        TARGETING_THRESHOLD = CommonConfig.configDouble(builder, CommonConfig.CATEGORY_MOBS, "12.G08_targetingThreshold", "Minimum amount of soot required for glyphids' extended targeting range to activate", 1D);
        SCOUT_SWARM_SPAWN_CHANCE = CommonConfig.configInt(builder, CommonConfig.CATEGORY_MOBS, "12.G10_scoutSwarmSpawn", "How likely are scouts to spawn in swarms, 1 in x chance format", 3);
        LARGE_HIVE_CHANCE = CommonConfig.configInt(builder, CommonConfig.CATEGORY_MOBS, "12.G11_largeHiveChance", "The chance for a large hive to spawn, formula: 1/x", 5);
        LARGE_HIVE_THRESHOLD = CommonConfig.configInt(builder, CommonConfig.CATEGORY_MOBS, "12.G12_largeHiveThreshold", "The soot threshold for a large hive to spawn", 20);
        WAYPOINT_DEBUG = CommonConfig.configBool(builder, CommonConfig.CATEGORY_MOBS, "12.G13_waypointDebug", "Allows glyphid waypoints to be seen, mainly used for debugging, also useful as an aid against them", false);
        ENABLE_INFESTATION = CommonConfig.configBool(builder, CommonConfig.CATEGORY_MOBS, "12.I01_enableInfestation", "Whether structures infested with glyphids should spawn", true);
        BASE_INFEST_CHANCE = CommonConfig.configDouble(builder, CommonConfig.CATEGORY_MOBS, "12.I02_baseInfestChance", "The chance for infested structures to spawn", 5);
        BASE_SWARM_SIZE = CommonConfig.configInt(builder, CommonConfig.CATEGORY_MOBS, "12.GS01_baseSwarmSize", "The basic, soot-less swarm size", 5);
        SWARM_SCALING_MULT = CommonConfig.configDouble(builder, CommonConfig.CATEGORY_MOBS, "12.GS02_swarmScalingMult", "By how much should swarm size scale by per soot amount determined below", 1.2);
        SOOT_STEP = CommonConfig.configInt(builder, CommonConfig.CATEGORY_MOBS, "12.GS03_sootStep", "The soot amount the above multiplier applies to the swarm size", 50);
        SWARM_COOLDOWN = CommonConfig.configInt(builder, CommonConfig.CATEGORY_MOBS, "12.GS04_swarmCooldown", "How often do glyphid swarms spawn, in seconds", 120);
        GLYPHID_CHANCE = CommonConfig.configIntList(builder, CommonConfig.CATEGORY_MOBS, "12.GC01_glyphidChance", "Base Spawn chance and soot modifier for a glyphid grunt", new int[]{50, -45, 0});
        BRAWLER_CHANCE = CommonConfig.configIntList(builder, CommonConfig.CATEGORY_MOBS, "12.GC02_brawlerChance", "Base Spawn chance and soot modifier for a glyphid brawler", new int[]{10, 30, 1});
        BOMBARDIER_CHANCE = CommonConfig.configIntList(builder, CommonConfig.CATEGORY_MOBS, "12.GC03_bombardierChance", "Base Spawn chance and soot modifier for a glyphid bombardier", new int[]{20, -15, 1});
        BLASTER_CHANCE = CommonConfig.configIntList(builder, CommonConfig.CATEGORY_MOBS, "12.GC04_blasterChance", "Base Spawn chance and soot modifier for a glyphid blaster", new int[]{-5, 40, 5});
        DIGGER_CHANCE = CommonConfig.configIntList(builder, CommonConfig.CATEGORY_MOBS, "12.GC05_diggerChance", "Base Spawn chance and soot modifier for a glyphid digger", new int[]{-15, 25, 5});
        BEHEMOTH_CHANCE = CommonConfig.configIntList(builder, CommonConfig.CATEGORY_MOBS, "12.GC06_behemothChance", "Base Spawn chance and soot modifier for a glyphid behemoth", new int[]{-30, 45, 10});
        BRENDA_CHANCE = CommonConfig.configIntList(builder, CommonConfig.CATEGORY_MOBS, "12.GC07_brendaChance", "Base Spawn chance and soot modifier for a glyphid brenda", new int[]{-50, 60, 20});
        JOHNSON_CHANCE = CommonConfig.configIntList(builder, CommonConfig.CATEGORY_MOBS, "12.GC08_johnsonChance", "Base Spawn chance and soot modifier for Big Man Johnson", new int[]{-50, 60, 50});
        RAMPANT_MODE = CommonConfig.configBool(builder, CommonConfig.CATEGORY_MOBS, "12.R01_rampantMode", "The main rampant mode toggle, enables all other features associated with it", false);
        RAMPANT_NATURAL_SCOUT_SPAWN = CommonConfig.configBool(builder, CommonConfig.CATEGORY_MOBS, "12.R02_rampantScoutSpawn", "Whether scouts should spawn natually in highly polluted chunks", false);
        RAMPANT_SCOUT_SPAWN_THRESH = CommonConfig.configDouble(builder, CommonConfig.CATEGORY_MOBS, "12.R02.1_rampantScoutSpawnThresh", "How much soot is needed for scouts to naturally spawn", 13);
        RAMPANT_SCOUT_SPAWN_CHANCE = CommonConfig.configInt(builder, CommonConfig.CATEGORY_MOBS, "12.R02.2_rampantScoutSpawnChance", "How often scouts naturally spawn per mob population, 1/x format, the bigger the number, the more uncommon the scouts", 1400);
        RAMPANT_EXTENDED_TARGETTING = CommonConfig.configBool(builder, CommonConfig.CATEGORY_MOBS, "12.R03_rampantExtendedTargeting", "Whether Glyphids should have the extended targetting always enabled", false);
        RAMPANT_DIG = CommonConfig.configBool(builder, CommonConfig.CATEGORY_MOBS, "12.R04_rampantDig", "Whether Glyphids should be able to dig to waypoints", false);
        RAMPANT_GLYPHID_GUIDANCE = CommonConfig.configBool(builder, CommonConfig.CATEGORY_MOBS, "12.R05_rampantGlyphidGuidance", "Whether Glyphids should always expand toward a player's spawnpoint", false);
        RAMPANT_SMOKE_STACK_OVERRIDE = CommonConfig.configDouble(builder, CommonConfig.CATEGORY_MOBS, "12.R06_rampantSmokeStackOverride", "How much should the smokestack multiply soot by when on rampant mode", 0.4);
        SCOUT_INITIAL_SPAWN = CommonConfig.configBool(builder, CommonConfig.CATEGORY_MOBS, "12.R07_scoutInitialSpawn", "Whether glyphid scouts should be able to spawn on the first swarm of a hive, causes glyphids to expand significantly faster", false);
        POLLUTION_MULT = CommonConfig.configDouble(builder, CommonConfig.CATEGORY_MOBS, "12.R08_pollutionMult", "A multiplier for soot emitted, whether you want to increase or decrease it", 1);
    }

    public static void load() {
        enableMaskman = ENABLE_MASKMAN.get();
        maskmanDelay = MASKMAN_DELAY.get();
        maskmanChance = MASKMAN_CHANCE.get();
        maskmanMinRad = MASKMAN_MIN_RAD.get();
        maskmanUnderground = MASKMAN_UNDERGROUND.get();
        enableRaids = ENABLE_RAIDS.get();
        raidDelay = RAID_DELAY.get();
        raidChance = RAID_CHANCE.get();
        raidAmount = RAID_AMOUNT.get();
        raidAttackDelay = RAID_ATTACK_DELAY.get();
        raidAttackReach = RAID_ATTACK_REACH.get();
        raidAttackDistance = RAID_ATTACK_DISTANCE.get();
        raidDrones = RAID_DRONES.get();
        enableElementals = ENABLE_ELEMENTALS.get();
        elementalDelay = ELEMENTAL_DELAY.get();
        elementalChance = ELEMENTAL_CHANCE.get();
        elementalAmount = ELEMENTAL_AMOUNT.get();
        elementalDistance = ELEMENTAL_DISTANCE.get();
        enableDucks = ENABLE_DUCKS.get();
        enableMobGear = ENABLE_MOB_GEAR.get();
        enableMobWeapons = ENABLE_MOB_WEAPONS.get();
        mobWeaponSootReduction = MOB_WEAPON_SOOT_REDUCTION.get();
        enableHives = ENABLE_HIVES.get();
        hiveSpawn = HIVE_SPAWN.get();
        scoutThreshold = SCOUT_THRESHOLD.get();
        spawnMax = SPAWN_MAX.get();
        targetingThreshold = TARGETING_THRESHOLD.get();
        scoutSwarmSpawnChance = SCOUT_SWARM_SPAWN_CHANCE.get();
        largeHiveChance = LARGE_HIVE_CHANCE.get();
        largeHiveThreshold = LARGE_HIVE_THRESHOLD.get();
        waypointDebug = WAYPOINT_DEBUG.get();
        enableInfestation = ENABLE_INFESTATION.get();
        baseInfestChance = BASE_INFEST_CHANCE.get();
        baseSwarmSize = BASE_SWARM_SIZE.get();
        swarmScalingMult = SWARM_SCALING_MULT.get();
        sootStep = SOOT_STEP.get();
        swarmCooldown = SWARM_COOLDOWN.get() * 20;
        glyphidChance = CommonConfig.toIntArray(GLYPHID_CHANCE.get());
        brawlerChance = CommonConfig.toIntArray(BRAWLER_CHANCE.get());
        bombardierChance = CommonConfig.toIntArray(BOMBARDIER_CHANCE.get());
        blasterChance = CommonConfig.toIntArray(BLASTER_CHANCE.get());
        diggerChance = CommonConfig.toIntArray(DIGGER_CHANCE.get());
        behemothChance = CommonConfig.toIntArray(BEHEMOTH_CHANCE.get());
        brendaChance = CommonConfig.toIntArray(BRENDA_CHANCE.get());
        johnsonChance = CommonConfig.toIntArray(JOHNSON_CHANCE.get());
        rampantMode = RAMPANT_MODE.get();
        rampantNaturalScoutSpawn = RAMPANT_NATURAL_SCOUT_SPAWN.get();
        rampantScoutSpawnThresh = RAMPANT_SCOUT_SPAWN_THRESH.get();
        rampantScoutSpawnChance = RAMPANT_SCOUT_SPAWN_CHANCE.get();
        rampantExtendedTargetting = RAMPANT_EXTENDED_TARGETTING.get();
        rampantDig = RAMPANT_DIG.get();
        rampantGlyphidGuidance = RAMPANT_GLYPHID_GUIDANCE.get();
        rampantSmokeStackOverride = RAMPANT_SMOKE_STACK_OVERRIDE.get();
        scoutInitialSpawn = SCOUT_INITIAL_SPAWN.get();
        pollutionMult = POLLUTION_MULT.get();

        if (rampantMode) {
            rampantNaturalScoutSpawn = true;
            rampantExtendedTargetting = true;
            rampantDig = true;
            rampantGlyphidGuidance = true;
            scoutSwarmSpawnChance = 1;
            scoutThreshold = 0.1;
            if (pollutionMult == 1) {
                pollutionMult = 3;
            }
            if (bombardierChance[2] == 1) {
                bombardierChance[2] = 0;
            }
            RadiationConfig.sootFogThreshold *= pollutionMult;
        }
    }

    public static boolean trueRam() {
        return rampantMode && rampantNaturalScoutSpawn && scoutThreshold <= 0.1 && rampantExtendedTargetting && rampantDig && rampantGlyphidGuidance;
    }
}
