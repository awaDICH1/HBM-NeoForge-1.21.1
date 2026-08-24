package com.hbm.config;

import net.neoforged.neoforge.common.ModConfigSpec;

/**
 * 迁移自 1.12.2 com.hbm.config.BombConfig。
 * Configuration → ModConfigSpec；键名/类别（03_nukes / 06_explosions）与原 .cfg 一致。
 */
public class BombConfig {

    public static int gadgetRadius = 150;
    public static int boyRadius = 120;
    public static int manRadius = 175;
    public static int mikeRadius = 250;
    public static int tsarRadius = 500;
    public static int prototypeRadius = 150;
    public static int fleijaRadius = 50;
    public static int soliniumRadius = 150;
    public static int n2Radius = 200;
    public static int missileRadius = 100;
    public static int mirvRadius = 100;
    public static int fatmanRadius = 35;
    public static int nukaRadius = 25;
    public static int aSchrabRadius = 20;
    public static int riggedStarRange = 50;
    public static int riggedStarTicks = 60 * 20;

    public static int maxCustomTNTRadius = 150;
    public static int maxCustomNukeRadius = 200;
    public static int maxCustomHydroRadius = 350;
    public static int maxCustomDirtyRadius = 200;
    public static int maxCustomBaleRadius = 350;
    public static int maxCustomSchrabRadius = 250;
    public static int maxCustomSolRadius = 350;
    public static int maxCustomEuphLvl = 20;

    public static int mk5 = 50;
    public static int blastSpeed = 1024;
    public static int falloutRange = 100;
    public static int fChunkSpeed = 5;
    public static int falloutDelay = 4;
    public static int limitExplosionLifespan = 0;
    public static boolean disableNuclear = false;
    public static boolean enableNukeClouds = true;
    public static boolean enableNukeNBTSaving = true;
    public static boolean chunkloading = true;
    public static int explosionAlgorithm = 2;
    public static int maxThreads = -1;
    public static boolean safeCommit = false;

    private static ModConfigSpec.IntValue GADGET_RADIUS;
    private static ModConfigSpec.IntValue BOY_RADIUS;
    private static ModConfigSpec.IntValue MAN_RADIUS;
    private static ModConfigSpec.IntValue MIKE_RADIUS;
    private static ModConfigSpec.IntValue TSAR_RADIUS;
    private static ModConfigSpec.IntValue PROTOTYPE_RADIUS;
    private static ModConfigSpec.IntValue FLEIJA_RADIUS;
    private static ModConfigSpec.IntValue SOLINIUM_RADIUS;
    private static ModConfigSpec.IntValue N2_RADIUS;
    private static ModConfigSpec.IntValue MISSILE_RADIUS;
    private static ModConfigSpec.IntValue MIRV_RADIUS;
    private static ModConfigSpec.IntValue FATMAN_RADIUS;
    private static ModConfigSpec.IntValue NUKA_RADIUS;
    private static ModConfigSpec.IntValue A_SCHRAB_RADIUS;
    private static ModConfigSpec.IntValue RIGGED_STAR_RANGE;
    private static ModConfigSpec.IntValue RIGGED_STAR_TICKS;
    private static ModConfigSpec.IntValue MAX_CUSTOM_TNT_RADIUS;
    private static ModConfigSpec.IntValue MAX_CUSTOM_NUKE_RADIUS;
    private static ModConfigSpec.IntValue MAX_CUSTOM_HYDRO_RADIUS;
    private static ModConfigSpec.IntValue MAX_CUSTOM_DIRTY_RADIUS;
    private static ModConfigSpec.IntValue MAX_CUSTOM_BALE_RADIUS;
    private static ModConfigSpec.IntValue MAX_CUSTOM_SCHRAB_RADIUS;
    private static ModConfigSpec.IntValue MAX_CUSTOM_SOL_RADIUS;
    private static ModConfigSpec.IntValue MAX_CUSTOM_EUPH_LVL;
    private static ModConfigSpec.IntValue LIMIT_EXPLOSION_LIFESPAN;
    private static ModConfigSpec.IntValue BLAST_SPEED;
    private static ModConfigSpec.IntValue MK5;
    private static ModConfigSpec.IntValue FALLOUT_RANGE;
    private static ModConfigSpec.IntValue F_CHUNK_SPEED;
    private static ModConfigSpec.IntValue FALLOUT_DELAY;
    private static ModConfigSpec.BooleanValue DISABLE_NUCLEAR;
    private static ModConfigSpec.BooleanValue ENABLE_NUKE_CLOUDS;
    private static ModConfigSpec.BooleanValue ENABLE_NUKE_NBT_SAVING;
    private static ModConfigSpec.BooleanValue CHUNKLOADING;
    private static ModConfigSpec.IntValue EXPLOSION_ALGORITHM;
    private static ModConfigSpec.IntValue MAX_THREADS;
    private static ModConfigSpec.BooleanValue SAFE_COMMIT;

    public static void build(ModConfigSpec.Builder builder) {
        GADGET_RADIUS = CommonConfig.configInt(builder, CommonConfig.CATEGORY_NUKES, "3.00_gadgetRadius", "Radius of the Gadget", 150);
        BOY_RADIUS = CommonConfig.configInt(builder, CommonConfig.CATEGORY_NUKES, "3.01_boyRadius", "Radius of Little Boy", 120);
        MAN_RADIUS = CommonConfig.configInt(builder, CommonConfig.CATEGORY_NUKES, "3.02_manRadius", "Radius of Fat Man", 175);
        MIKE_RADIUS = CommonConfig.configInt(builder, CommonConfig.CATEGORY_NUKES, "3.03_mikeRadius", "Radius of Ivy Mike", 250);
        TSAR_RADIUS = CommonConfig.configInt(builder, CommonConfig.CATEGORY_NUKES, "3.04_tsarRadius", "Radius of the Tsar Bomba", 500);
        PROTOTYPE_RADIUS = CommonConfig.configInt(builder, CommonConfig.CATEGORY_NUKES, "3.05_prototypeRadius", "Radius of the Prototype", 150);
        FLEIJA_RADIUS = CommonConfig.configInt(builder, CommonConfig.CATEGORY_NUKES, "3.06_fleijaRadius", "Radius of F.L.E.I.J.A.", 50);
        MISSILE_RADIUS = CommonConfig.configInt(builder, CommonConfig.CATEGORY_NUKES, "3.07_missileRadius", "Radius of the nuclear missile", 100);
        MIRV_RADIUS = CommonConfig.configInt(builder, CommonConfig.CATEGORY_NUKES, "3.08_mirvRadius", "Radius of a MIRV", 70);
        FATMAN_RADIUS = CommonConfig.configInt(builder, CommonConfig.CATEGORY_NUKES, "3.09_fatmanRadius", "Radius of the Fatman Launcher", 35);
        NUKA_RADIUS = CommonConfig.configInt(builder, CommonConfig.CATEGORY_NUKES, "3.10_nukaRadius", "Radius of the nuka grenade", 25);
        A_SCHRAB_RADIUS = CommonConfig.configInt(builder, CommonConfig.CATEGORY_NUKES, "3.11_aSchrabRadius", "Radius of dropped anti schrabidium", 20);
        SOLINIUM_RADIUS = CommonConfig.configInt(builder, CommonConfig.CATEGORY_NUKES, "3.12_soliniumRadius", "Radius of the blue rinse", 150);
        N2_RADIUS = CommonConfig.configInt(builder, CommonConfig.CATEGORY_NUKES, "3.13_n2Radius", "Radius of the N2 mine", 200);
        RIGGED_STAR_RANGE = CommonConfig.configInt(builder, CommonConfig.CATEGORY_NUKES, "3.14_riggedStarRadius", "Radius of the Rigged Star Blaster Energy Cell", 50);
        RIGGED_STAR_TICKS = CommonConfig.configInt(builder, CommonConfig.CATEGORY_NUKES, "3.15_riggedStarFuse", "Time in ticks before the Rigged Star Blaster Energy Cell explodes after being dropped - default 60s", 1200);
        MAX_CUSTOM_TNT_RADIUS = CommonConfig.configInt(builder, CommonConfig.CATEGORY_NUKES, "3.16_maxCustomTNTRadius", "Maximum TNT radius of custom nukes - default 150m", 150);
        MAX_CUSTOM_NUKE_RADIUS = CommonConfig.configInt(builder, CommonConfig.CATEGORY_NUKES, "3.17_maxCustomNukeRadius", "Maximum Nuke radius of custom nukes - default 200m", 200);
        MAX_CUSTOM_HYDRO_RADIUS = CommonConfig.configInt(builder, CommonConfig.CATEGORY_NUKES, "3.18_maxCustomHydroRadius", "Maximum Thermonuclear radius of custom nukes - default 350m", 350);
        MAX_CUSTOM_DIRTY_RADIUS = CommonConfig.configInt(builder, CommonConfig.CATEGORY_NUKES, "3.19_maxCustomDirtyRadius", "Maximum fallout additional radius that can be added to custom nukes - default 200m", 200);
        MAX_CUSTOM_BALE_RADIUS = CommonConfig.configInt(builder, CommonConfig.CATEGORY_NUKES, "3.20_maxCustomBaleRadius", "Maximum balefire radius of custom nukes - default 350m", 350);
        MAX_CUSTOM_SCHRAB_RADIUS = CommonConfig.configInt(builder, CommonConfig.CATEGORY_NUKES, "3.21_maxCustomSchrabRadius", "Maximum Antischrabidium radius of custom nukes - default 250m", 250);
        MAX_CUSTOM_SOL_RADIUS = CommonConfig.configInt(builder, CommonConfig.CATEGORY_NUKES, "3.22_maxCustomSolRadius", "Maximum Solinium radius of custom nukes - default 350m", 350);
        MAX_CUSTOM_EUPH_LVL = CommonConfig.configInt(builder, CommonConfig.CATEGORY_NUKES, "3.23_maxCustomEuphLvl", "Maximum Euphemium Lvl of custom nukes (1Lvl = 100 Rays) - default 20", 20);

        LIMIT_EXPLOSION_LIFESPAN = CommonConfig.configInt(builder, CommonConfig.CATEGORY_EXPLOSIONS, "6.00_limitExplosionLifespan", "How long an explosion can be unloaded until it dies in seconds. Based of system time. 0 disables the effect", 0);
        BLAST_SPEED = CommonConfig.configInt(builder, CommonConfig.CATEGORY_EXPLOSIONS, "6.01_blastSpeed", "Base speed of MK3 system (old and schrabidium) detonations (Blocks / tick)", 1024);
        MK5 = CommonConfig.configInt(builder, CommonConfig.CATEGORY_EXPLOSIONS, "6.02_mk5BlastTime", "Maximum amount of milliseconds per tick allocated for mk5 chunk processing", 40);
        FALLOUT_RANGE = CommonConfig.configInt(builder, CommonConfig.CATEGORY_EXPLOSIONS, "6.03_falloutRange", "Radius of fallout area (base radius * value in percent)", 100);
        F_CHUNK_SPEED = CommonConfig.configInt(builder, CommonConfig.CATEGORY_EXPLOSIONS, "6.04_falloutChunkSpeed", "Process a Chunk every nth tick by the fallout rain", 5);
        FALLOUT_DELAY = CommonConfig.configInt(builder, CommonConfig.CATEGORY_EXPLOSIONS, "6.05_falloutTime", "Maximum amount of milliseconds per tick allocated for fallout chunk processing", 30);
        DISABLE_NUCLEAR = CommonConfig.configBool(builder, CommonConfig.CATEGORY_EXPLOSIONS, "6.07_disableNuclear", "Disable the nuclear part of nukes", false);
        ENABLE_NUKE_CLOUDS = CommonConfig.configBool(builder, CommonConfig.CATEGORY_EXPLOSIONS, "6.08_enableMushroomClouds", "WARNING: AN OLD CONFIG OPTION. Allows for nuclear explosion to even happen.", true);
        ENABLE_NUKE_NBT_SAVING = CommonConfig.configBool(builder, CommonConfig.CATEGORY_EXPLOSIONS, "6.09_enableNukeNBTSaving", "If true then nukes will save the blocks they want to destroy so they can resume work rather then restart after a crash/reload. For big nukes this can take a while tho.", true);
        CHUNKLOADING = CommonConfig.configBool(builder, CommonConfig.CATEGORY_EXPLOSIONS, "6.10_enableChunkLoading", "Allows mk5 explosion to generate new chunks.", true);
        EXPLOSION_ALGORITHM = CommonConfig.configInt(builder, CommonConfig.CATEGORY_EXPLOSIONS, "6.11_explosionAlgorithm", "Configures the algorithm of mk5 explosion. \n0 = Legacy, 1 = Threaded DDA, 2 = Threaded DDA with damage accumulation.", 2);
        MAX_THREADS = CommonConfig.configInt(builder, CommonConfig.CATEGORY_EXPLOSIONS, "6.11.1_explosionMaxThreads", "Configures the maximum thread count for the threaded DDA explosion algorithm.\n -N = CPU count - N, 0 = CPU count, N = N", -1);
        SAFE_COMMIT = CommonConfig.configBool(builder, CommonConfig.CATEGORY_EXPLOSIONS, "6.11.2_safeCommit", "Prefer safety over performance(~30% slower). Affects algorithm 1, 2, and fallout rain effect.", false);
    }

    public static void load() {
        gadgetRadius = GADGET_RADIUS.get();
        boyRadius = BOY_RADIUS.get();
        manRadius = MAN_RADIUS.get();
        mikeRadius = MIKE_RADIUS.get();
        tsarRadius = TSAR_RADIUS.get();
        prototypeRadius = PROTOTYPE_RADIUS.get();
        fleijaRadius = FLEIJA_RADIUS.get();
        soliniumRadius = SOLINIUM_RADIUS.get();
        n2Radius = N2_RADIUS.get();
        missileRadius = MISSILE_RADIUS.get();
        mirvRadius = MIRV_RADIUS.get();
        fatmanRadius = FATMAN_RADIUS.get();
        nukaRadius = NUKA_RADIUS.get();
        aSchrabRadius = A_SCHRAB_RADIUS.get();
        riggedStarRange = RIGGED_STAR_RANGE.get();
        riggedStarTicks = RIGGED_STAR_TICKS.get();
        maxCustomTNTRadius = MAX_CUSTOM_TNT_RADIUS.get();
        maxCustomNukeRadius = MAX_CUSTOM_NUKE_RADIUS.get();
        maxCustomHydroRadius = MAX_CUSTOM_HYDRO_RADIUS.get();
        maxCustomDirtyRadius = MAX_CUSTOM_DIRTY_RADIUS.get();
        maxCustomBaleRadius = MAX_CUSTOM_BALE_RADIUS.get();
        maxCustomSchrabRadius = MAX_CUSTOM_SCHRAB_RADIUS.get();
        maxCustomSolRadius = MAX_CUSTOM_SOL_RADIUS.get();
        maxCustomEuphLvl = MAX_CUSTOM_EUPH_LVL.get();
        limitExplosionLifespan = LIMIT_EXPLOSION_LIFESPAN.get();
        blastSpeed = BLAST_SPEED.get();
        mk5 = MK5.get();
        falloutRange = FALLOUT_RANGE.get();
        fChunkSpeed = F_CHUNK_SPEED.get();
        falloutDelay = FALLOUT_DELAY.get();
        disableNuclear = DISABLE_NUCLEAR.get();
        enableNukeClouds = ENABLE_NUKE_CLOUDS.get();
        enableNukeNBTSaving = ENABLE_NUKE_NBT_SAVING.get();
        chunkloading = CHUNKLOADING.get();
        explosionAlgorithm = EXPLOSION_ALGORITHM.get();
        maxThreads = MAX_THREADS.get();
        safeCommit = SAFE_COMMIT.get();
    }
}
