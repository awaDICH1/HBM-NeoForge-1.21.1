package com.hbm.config;

import com.hbm.main.HBM;
import net.neoforged.neoforge.common.ModConfigSpec;

import java.util.Locale;

/**
 * 迁移自 1.12.2 com.hbm.config.StructureConfig。
 * MainRegistry.logger → HBM.LOGGER；setDef 校验与 min>max 回退逻辑保留在 load()。
 */
public class StructureConfig {

    public static int enableStructures = 2;

    public static int structureMinChunks = 4;
    public static int structureMaxChunks = 12;

    public static double lootAmountFactor = 1D;

    public static boolean debugStructures = false;
    public static boolean enableRuins = true;
    public static boolean enableOceanStructures = true;

    public static int ruinsASpawnWeight = 10;
    public static int ruinsBSpawnWeight = 12;
    public static int ruinsCSpawnWeight = 12;
    public static int ruinsDSpawnWeight = 12;
    public static int ruinsESpawnWeight = 12;
    public static int ruinsFSpawnWeight = 12;
    public static int ruinsGSpawnWeight = 12;
    public static int ruinsHSpawnWeight = 12;
    public static int ruinsISpawnWeight = 12;
    public static int ruinsJSpawnWeight = 12;

    public static int plane1SpawnWeight = 25;
    public static int plane2SpawnWeight = 25;

    public static int desertShack1SpawnWeight = 18;
    public static int desertShack2SpawnWeight = 20;
    public static int desertShack3SpawnWeight = 22;

    public static int laboratorySpawnWeight = 20;
    public static int lighthouseSpawnWeight = 4;
    public static int oilRigSpawnWeight = 5;
    public static int broadcastingTowerSpawnWeight = 25;
    public static int beachedPatrolSpawnWeight = 15;
    public static int vertibirdSpawnWeight = 6;
    public static int vertibirdCrashedSpawnWeight = 10;

    public static int factorySpawnWeight = 40;
    public static int radioSpawnWeight = 30;
    public static int forestChemSpawnWeight = 30;
    public static int forestPostSpawnWeight = 30;

    public static int spireSpawnWeight = 2;
    public static int craneSpawnWeight = 20;
    public static int bunkerSpawnWeight = 6;
    public static int dishSpawnWeight = 20;
    public static int featuresSpawnWeight = 50;

    public static int aircraftCarrierSpawnWeight = 3;

    public static int meteorDungeonSpawnWeight = 1;

    public static int plainsNullWeight = 4;
    public static int oceanNullWeight = 15;

    public static boolean enableDynamicStructureSaving = false;

    private static ModConfigSpec.ConfigValue<String> ENABLE_STRUCTURES_STR;
    private static ModConfigSpec.IntValue STRUCTURE_MIN_CHUNKS;
    private static ModConfigSpec.IntValue STRUCTURE_MAX_CHUNKS;
    private static ModConfigSpec.DoubleValue LOOT_AMOUNT_FACTOR;
    private static ModConfigSpec.BooleanValue DEBUG_STRUCTURES;
    private static ModConfigSpec.BooleanValue ENABLE_RUINS;
    private static ModConfigSpec.BooleanValue ENABLE_OCEAN_STRUCTURES;
    private static ModConfigSpec.IntValue SPIRE_SPAWN_WEIGHT;
    private static ModConfigSpec.IntValue FEATURES_SPAWN_WEIGHT;
    private static ModConfigSpec.IntValue BUNKER_SPAWN_WEIGHT;
    private static ModConfigSpec.IntValue VERTIBIRD_SPAWN_WEIGHT;
    private static ModConfigSpec.IntValue VERTIBIRD_CRASHED_SPAWN_WEIGHT;
    private static ModConfigSpec.IntValue AIRCRAFT_CARRIER_SPAWN_WEIGHT;
    private static ModConfigSpec.IntValue OIL_RIG_SPAWN_WEIGHT;
    private static ModConfigSpec.IntValue LIGHTHOUSE_SPAWN_WEIGHT;
    private static ModConfigSpec.IntValue BEACHED_PATROL_SPAWN_WEIGHT;
    private static ModConfigSpec.IntValue DISH_SPAWN_WEIGHT;
    private static ModConfigSpec.IntValue FOREST_CHEM_SPAWN_WEIGHT;
    private static ModConfigSpec.IntValue PLANE1_SPAWN_WEIGHT;
    private static ModConfigSpec.IntValue PLANE2_SPAWN_WEIGHT;
    private static ModConfigSpec.IntValue DESERT_SHACK1_SPAWN_WEIGHT;
    private static ModConfigSpec.IntValue DESERT_SHACK2_SPAWN_WEIGHT;
    private static ModConfigSpec.IntValue DESERT_SHACK3_SPAWN_WEIGHT;
    private static ModConfigSpec.IntValue LABORATORY_SPAWN_WEIGHT;
    private static ModConfigSpec.IntValue FOREST_POST_SPAWN_WEIGHT;
    private static ModConfigSpec.IntValue RUINS_A_SPAWN_WEIGHT;
    private static ModConfigSpec.IntValue RUINS_B_SPAWN_WEIGHT;
    private static ModConfigSpec.IntValue RUINS_C_SPAWN_WEIGHT;
    private static ModConfigSpec.IntValue RUINS_D_SPAWN_WEIGHT;
    private static ModConfigSpec.IntValue RUINS_E_SPAWN_WEIGHT;
    private static ModConfigSpec.IntValue RUINS_F_SPAWN_WEIGHT;
    private static ModConfigSpec.IntValue RUINS_G_SPAWN_WEIGHT;
    private static ModConfigSpec.IntValue RUINS_H_SPAWN_WEIGHT;
    private static ModConfigSpec.IntValue RUINS_I_SPAWN_WEIGHT;
    private static ModConfigSpec.IntValue RUINS_J_SPAWN_WEIGHT;
    private static ModConfigSpec.IntValue RADIO_SPAWN_WEIGHT;
    private static ModConfigSpec.IntValue FACTORY_SPAWN_WEIGHT;
    private static ModConfigSpec.IntValue PLAINS_NULL_WEIGHT;
    private static ModConfigSpec.IntValue OCEAN_NULL_WEIGHT;
    private static ModConfigSpec.IntValue CRANE_SPAWN_WEIGHT;
    private static ModConfigSpec.IntValue BROADCASTING_TOWER_SPAWN_WEIGHT;
    private static ModConfigSpec.IntValue METEOR_DUNGEON_SPAWN_WEIGHT;
    private static ModConfigSpec.BooleanValue ENABLE_DYNAMIC_STRUCTURE_SAVING;

    public static void build(ModConfigSpec.Builder builder) {
        ENABLE_STRUCTURES_STR = CommonConfig.configString(builder, CommonConfig.CATEGORY_STRUCTURES, "15.00_enableStructures", "Flag for whether modern NTM structures will spawn. Valid values are true|false|flag - flag will respect the \"Generate Structures\" world flag.", "flag");
        STRUCTURE_MIN_CHUNKS = CommonConfig.configInt(builder, CommonConfig.CATEGORY_STRUCTURES, "15.01_structureMinChunks", "Minimum non-zero distance between structures in chunks (Settings lower than 8 may be problematic).", 4);
        STRUCTURE_MAX_CHUNKS = CommonConfig.configInt(builder, CommonConfig.CATEGORY_STRUCTURES, "15.02_structureMaxChunks", "Maximum non-zero distance between structures in chunks.", 16);
        LOOT_AMOUNT_FACTOR = CommonConfig.configDouble(builder, CommonConfig.CATEGORY_STRUCTURES, "15.03_lootAmountFactor", "General factor for loot spawns. Applies to spawned IInventories, not loot blocks.", 1D);
        DEBUG_STRUCTURES = CommonConfig.configBool(builder, CommonConfig.CATEGORY_STRUCTURES, "15.04_debugStructures", "If enabled, special structure blocks like jigsaw blocks will not be transformed after generating", false);
        ENABLE_RUINS = CommonConfig.configBool(builder, CommonConfig.CATEGORY_STRUCTURES, "15.05_enableRuins", "Toggle for all ruin structures (A through J)", true);
        ENABLE_OCEAN_STRUCTURES = CommonConfig.configBool(builder, CommonConfig.CATEGORY_STRUCTURES, "15.06_enableOceanStructures", "Toggle for ocean structures. (Aircraft carrier, oil rig, lighthouse.)", true);
        SPIRE_SPAWN_WEIGHT = CommonConfig.configInt(builder, CommonConfig.CATEGORY_STRUCTURES, "15.07_spireSpawnWeight", "Spawn weight for spire structure.", 2);
        FEATURES_SPAWN_WEIGHT = CommonConfig.configInt(builder, CommonConfig.CATEGORY_STRUCTURES, "15.08_featuresSpawnWeight", "Spawn weight for misc structures (ex. Houses, offices.)", 50);
        BUNKER_SPAWN_WEIGHT = CommonConfig.configInt(builder, CommonConfig.CATEGORY_STRUCTURES, "15.09_bunkerSpawnWeight", "Spawn weight for bunker structure.", 6);
        VERTIBIRD_SPAWN_WEIGHT = CommonConfig.configInt(builder, CommonConfig.CATEGORY_STRUCTURES, "15.10_vertibirdSpawnWeight", "Spawn weight for vertibird structure.", 6);
        VERTIBIRD_CRASHED_SPAWN_WEIGHT = CommonConfig.configInt(builder, CommonConfig.CATEGORY_STRUCTURES, "15.11_crashedVertibirdSpawnWeight", "Spawn weight for crashed vertibird structure.", 10);
        AIRCRAFT_CARRIER_SPAWN_WEIGHT = CommonConfig.configInt(builder, CommonConfig.CATEGORY_STRUCTURES, "15.12_aircraftCarrierSpawnWeight", "Spawn weight for aircraft carrier structure.", 3);
        OIL_RIG_SPAWN_WEIGHT = CommonConfig.configInt(builder, CommonConfig.CATEGORY_STRUCTURES, "15.13_oilRigSpawnWeight", "Spawn weight for oil rig structure.", 5);
        LIGHTHOUSE_SPAWN_WEIGHT = CommonConfig.configInt(builder, CommonConfig.CATEGORY_STRUCTURES, "15.14_lighthouseSpawnWeight", "Spawn weight for lighthouse structure.", 1);
        BEACHED_PATROL_SPAWN_WEIGHT = CommonConfig.configInt(builder, CommonConfig.CATEGORY_STRUCTURES, "15.15_beachedPatrolSpawnWeight", "Spawn weight for beached patrol structure.", 15);
        DISH_SPAWN_WEIGHT = CommonConfig.configInt(builder, CommonConfig.CATEGORY_STRUCTURES, "15.16_dishSpawnWeight", "Spawn weight for dish structures.", 10);
        FOREST_CHEM_SPAWN_WEIGHT = CommonConfig.configInt(builder, CommonConfig.CATEGORY_STRUCTURES, "15.17_forestChemSpawnWeight", "Spawn weight for forest chemical plant structure.", 30);
        PLANE1_SPAWN_WEIGHT = CommonConfig.configInt(builder, CommonConfig.CATEGORY_STRUCTURES, "15.18_plane1SpawnWeight", "Spawn weight for crashed plane 1 structure.", 25);
        PLANE2_SPAWN_WEIGHT = CommonConfig.configInt(builder, CommonConfig.CATEGORY_STRUCTURES, "15.19_plane2SpawnWeight", "Spawn weight for crashed plane 2 structure.", 25);
        DESERT_SHACK1_SPAWN_WEIGHT = CommonConfig.configInt(builder, CommonConfig.CATEGORY_STRUCTURES, "15.20_desertShack1SpawnWeight", "Spawn weight for desert shack 1 structure.", 18);
        DESERT_SHACK2_SPAWN_WEIGHT = CommonConfig.configInt(builder, CommonConfig.CATEGORY_STRUCTURES, "15.21_desertShack2SpawnWeight", "Spawn weight for desert shack 2 structure.", 20);
        DESERT_SHACK3_SPAWN_WEIGHT = CommonConfig.configInt(builder, CommonConfig.CATEGORY_STRUCTURES, "15.22_desertShack3SpawnWeight", "Spawn weight for desert shack 3 structure.", 22);
        LABORATORY_SPAWN_WEIGHT = CommonConfig.configInt(builder, CommonConfig.CATEGORY_STRUCTURES, "15.23_laboratorySpawnWeight", "Spawn weight for laboratory structure/", 20);
        FOREST_POST_SPAWN_WEIGHT = CommonConfig.configInt(builder, CommonConfig.CATEGORY_STRUCTURES, "15.24_forestPostSpawnWeight", "Spawn weight for forest post structure.", 30);
        RUINS_A_SPAWN_WEIGHT = CommonConfig.configInt(builder, CommonConfig.CATEGORY_STRUCTURES, "15.25_ruinASpawnWeight", "Spawn weight for ruin A structure.", 10);
        RUINS_B_SPAWN_WEIGHT = CommonConfig.configInt(builder, CommonConfig.CATEGORY_STRUCTURES, "15.26_ruinBSpawnWeight", "Spawn weight for ruin B structure.", 12);
        RUINS_C_SPAWN_WEIGHT = CommonConfig.configInt(builder, CommonConfig.CATEGORY_STRUCTURES, "15.27_ruinCSpawnWeight", "Spawn weight for ruin C structure.", 12);
        RUINS_D_SPAWN_WEIGHT = CommonConfig.configInt(builder, CommonConfig.CATEGORY_STRUCTURES, "15.28_ruinDSpawnWeight", "Spawn weight for ruin D structure.", 12);
        RUINS_E_SPAWN_WEIGHT = CommonConfig.configInt(builder, CommonConfig.CATEGORY_STRUCTURES, "15.29_ruinESpawnWeight", "Spawn weight for ruin E structure.", 12);
        RUINS_F_SPAWN_WEIGHT = CommonConfig.configInt(builder, CommonConfig.CATEGORY_STRUCTURES, "15.30_ruinFSpawnWeight", "Spawn weight for ruin F structure.", 12);
        RUINS_G_SPAWN_WEIGHT = CommonConfig.configInt(builder, CommonConfig.CATEGORY_STRUCTURES, "15.31_ruinGSpawnWeight", "Spawn weight for ruin G structure.", 12);
        RUINS_H_SPAWN_WEIGHT = CommonConfig.configInt(builder, CommonConfig.CATEGORY_STRUCTURES, "15.32_ruinHSpawnWeight", "Spawn weight for ruin H structure.", 12);
        RUINS_I_SPAWN_WEIGHT = CommonConfig.configInt(builder, CommonConfig.CATEGORY_STRUCTURES, "15.33_ruinISpawnWeight", "Spawn weight for ruin I structure.", 12);
        RUINS_J_SPAWN_WEIGHT = CommonConfig.configInt(builder, CommonConfig.CATEGORY_STRUCTURES, "15.34_ruinJSpawnWeight", "Spawn weight for ruin J structure.", 12);
        RADIO_SPAWN_WEIGHT = CommonConfig.configInt(builder, CommonConfig.CATEGORY_STRUCTURES, "15.35_radioSpawnWeight", "Spawn weight for radio structure.", 25);
        FACTORY_SPAWN_WEIGHT = CommonConfig.configInt(builder, CommonConfig.CATEGORY_STRUCTURES, "15.36_factorySpawnWeight", "Spawn weight for factory structure.", 40);
        PLAINS_NULL_WEIGHT = CommonConfig.configInt(builder, CommonConfig.CATEGORY_STRUCTURES, "15.37_plainsNullWeight", "Null spawn weight for plains biome", 20);
        OCEAN_NULL_WEIGHT = CommonConfig.configInt(builder, CommonConfig.CATEGORY_STRUCTURES, "15.38_oceanNullWeight", "Null spawn weight for ocean biomes", 35);
        CRANE_SPAWN_WEIGHT = CommonConfig.configInt(builder, CommonConfig.CATEGORY_STRUCTURES, "15.39_craneSpawnWeight", "Spawn weight for crane structure.", 20);
        BROADCASTING_TOWER_SPAWN_WEIGHT = CommonConfig.configInt(builder, CommonConfig.CATEGORY_STRUCTURES, "15.40_broadcastingTowerSpawnWeight", "Spawn weight for broadcasting tower structure.", 25);
        METEOR_DUNGEON_SPAWN_WEIGHT = CommonConfig.configInt(builder, CommonConfig.CATEGORY_STRUCTURES, "15.41_meteorDungeonSpawnWeight", "Spawn weight for meteor dungeons.", 1);
        ENABLE_DYNAMIC_STRUCTURE_SAVING = CommonConfig.configBool(builder, CommonConfig.CATEGORY_STRUCTURES, "15.99_CE_01_enableDynamicStructureSaving", "Whether dynamic structure scheduled for generation but didn't meet generation requirements should be persisted to resume generation.\nAffects small structures like ores, glyphid hives, flowers, etc. Will slightly increase world save size. Default: false", false);
    }

    public static void load() {
        enableStructures = CommonConfig.parseStructureFlag(ENABLE_STRUCTURES_STR.get());
        structureMinChunks = STRUCTURE_MIN_CHUNKS.get();
        structureMaxChunks = STRUCTURE_MAX_CHUNKS.get();
        lootAmountFactor = LOOT_AMOUNT_FACTOR.get();
        debugStructures = DEBUG_STRUCTURES.get();
        enableRuins = ENABLE_RUINS.get();
        enableOceanStructures = ENABLE_OCEAN_STRUCTURES.get();
        spireSpawnWeight = SPIRE_SPAWN_WEIGHT.get();
        featuresSpawnWeight = FEATURES_SPAWN_WEIGHT.get();
        bunkerSpawnWeight = BUNKER_SPAWN_WEIGHT.get();
        vertibirdSpawnWeight = VERTIBIRD_SPAWN_WEIGHT.get();
        vertibirdCrashedSpawnWeight = VERTIBIRD_CRASHED_SPAWN_WEIGHT.get();
        aircraftCarrierSpawnWeight = AIRCRAFT_CARRIER_SPAWN_WEIGHT.get();
        oilRigSpawnWeight = OIL_RIG_SPAWN_WEIGHT.get();
        lighthouseSpawnWeight = LIGHTHOUSE_SPAWN_WEIGHT.get();
        beachedPatrolSpawnWeight = BEACHED_PATROL_SPAWN_WEIGHT.get();
        dishSpawnWeight = DISH_SPAWN_WEIGHT.get();
        forestChemSpawnWeight = FOREST_CHEM_SPAWN_WEIGHT.get();
        plane1SpawnWeight = PLANE1_SPAWN_WEIGHT.get();
        plane2SpawnWeight = PLANE2_SPAWN_WEIGHT.get();
        desertShack1SpawnWeight = DESERT_SHACK1_SPAWN_WEIGHT.get();
        desertShack2SpawnWeight = DESERT_SHACK2_SPAWN_WEIGHT.get();
        desertShack3SpawnWeight = DESERT_SHACK3_SPAWN_WEIGHT.get();
        laboratorySpawnWeight = LABORATORY_SPAWN_WEIGHT.get();
        forestPostSpawnWeight = FOREST_POST_SPAWN_WEIGHT.get();
        ruinsASpawnWeight = RUINS_A_SPAWN_WEIGHT.get();
        ruinsBSpawnWeight = RUINS_B_SPAWN_WEIGHT.get();
        ruinsCSpawnWeight = RUINS_C_SPAWN_WEIGHT.get();
        ruinsDSpawnWeight = RUINS_D_SPAWN_WEIGHT.get();
        ruinsESpawnWeight = RUINS_E_SPAWN_WEIGHT.get();
        ruinsFSpawnWeight = RUINS_F_SPAWN_WEIGHT.get();
        ruinsGSpawnWeight = RUINS_G_SPAWN_WEIGHT.get();
        ruinsHSpawnWeight = RUINS_H_SPAWN_WEIGHT.get();
        ruinsISpawnWeight = RUINS_I_SPAWN_WEIGHT.get();
        ruinsJSpawnWeight = RUINS_J_SPAWN_WEIGHT.get();
        radioSpawnWeight = RADIO_SPAWN_WEIGHT.get();
        factorySpawnWeight = FACTORY_SPAWN_WEIGHT.get();
        plainsNullWeight = PLAINS_NULL_WEIGHT.get();
        oceanNullWeight = OCEAN_NULL_WEIGHT.get();
        craneSpawnWeight = CRANE_SPAWN_WEIGHT.get();
        broadcastingTowerSpawnWeight = BROADCASTING_TOWER_SPAWN_WEIGHT.get();
        meteorDungeonSpawnWeight = METEOR_DUNGEON_SPAWN_WEIGHT.get();
        enableDynamicStructureSaving = ENABLE_DYNAMIC_STRUCTURE_SAVING.get();

        structureMinChunks = CommonConfig.setDef(structureMinChunks, 4);
        structureMaxChunks = CommonConfig.setDef(structureMaxChunks, 12);

        if (structureMinChunks > structureMaxChunks) {
            HBM.LOGGER.error("Fatal error config: Minimum value has been set higher than the maximum value!");
            HBM.LOGGER.error(String.format(Locale.US, "Errored values will default back to %1$d and %2$d respectively, PLEASE REVIEW CONFIGURATION DESCRIPTION BEFORE MEDDLING WITH VALUES!", 8, 24));
            structureMinChunks = 8;
            structureMaxChunks = 24;
        }
    }
}
