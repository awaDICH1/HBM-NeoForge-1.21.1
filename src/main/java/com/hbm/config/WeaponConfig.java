package com.hbm.config;

import net.neoforged.neoforge.common.ModConfigSpec;

/**
 * 迁移自 1.12.2 com.hbm.config.WeaponConfig。
 * 键名/类别（07_missile_machines / 10_dangerous_drops）与原 .cfg 一致。
 */
public class WeaponConfig {

    public static int radarRange = 1000;
    public static int radarBuffer = 30;
    public static int radarAltitude = 55;
    public static int ciwsHitrate = 50;

    public static boolean dropMissileParts = true;

    public static boolean dropCell = true;
    public static boolean dropSing = true;
    public static boolean dropStar = true;
    public static boolean dropCrys = true;
    public static boolean dropDead = true;

    private static ModConfigSpec.IntValue RADAR_RANGE;
    private static ModConfigSpec.IntValue RADAR_BUFFER;
    private static ModConfigSpec.IntValue RADAR_ALTITUDE;
    private static ModConfigSpec.IntValue CIWS_HITRATE;
    private static ModConfigSpec.BooleanValue DROP_MISSILE_PARTS;
    private static ModConfigSpec.BooleanValue DROP_CELL;
    private static ModConfigSpec.BooleanValue DROP_SING;
    private static ModConfigSpec.BooleanValue DROP_STAR;
    private static ModConfigSpec.BooleanValue DROP_CRYS;
    private static ModConfigSpec.BooleanValue DROP_DEAD;

    public static void build(ModConfigSpec.Builder builder) {
        RADAR_RANGE = CommonConfig.configInt(builder, CommonConfig.CATEGORY_MISSILE, "7.00_radarRange", "Range of the radar, 50 will result in 100x100 block area covered", 1000);
        RADAR_BUFFER = CommonConfig.configInt(builder, CommonConfig.CATEGORY_MISSILE, "7.01_radarBuffer", "How high entities have to be above the radar to be detected", 30);
        RADAR_ALTITUDE = CommonConfig.configInt(builder, CommonConfig.CATEGORY_MISSILE, "7.02_radarAltitude", "Y height required for the radar to work", 55);
        CIWS_HITRATE = CommonConfig.configInt(builder, CommonConfig.CATEGORY_MISSILE, "7.03_ciwsAccuracy", "Additional modifier for CIWS accuracy", 50);
        DROP_MISSILE_PARTS = CommonConfig.configBool(builder, CommonConfig.CATEGORY_MISSILE, "7.03_dropMissileParts", "Whether shot-down missiles drop items", true);
        DROP_CELL = CommonConfig.configBool(builder, CommonConfig.CATEGORY_DROPS, "10.00_dropCell", "Whether antimatter cells should explode when dropped", true);
        DROP_SING = CommonConfig.configBool(builder, CommonConfig.CATEGORY_DROPS, "10.01_dropBHole", "Whether singularities and blaack holes should spawn when dropped", true);
        DROP_STAR = CommonConfig.configBool(builder, CommonConfig.CATEGORY_DROPS, "10.02_dropStar", "Whether rigged star blaster cells should explode when dropped", true);
        DROP_CRYS = CommonConfig.configBool(builder, CommonConfig.CATEGORY_DROPS, "10.04_dropCrys", "Whether xen crystals should move blocks when dropped", true);
        DROP_DEAD = CommonConfig.configBool(builder, CommonConfig.CATEGORY_DROPS, "10.05_dropDead", "Whether dead man's explosives should explode when dropped", true);
    }

    public static void load() {
        radarRange = RADAR_RANGE.get();
        radarBuffer = RADAR_BUFFER.get();
        radarAltitude = RADAR_ALTITUDE.get();
        ciwsHitrate = CIWS_HITRATE.get();
        dropMissileParts = DROP_MISSILE_PARTS.get();
        dropCell = DROP_CELL.get();
        dropSing = DROP_SING.get();
        dropStar = DROP_STAR.get();
        dropCrys = DROP_CRYS.get();
        dropDead = DROP_DEAD.get();
    }
}
