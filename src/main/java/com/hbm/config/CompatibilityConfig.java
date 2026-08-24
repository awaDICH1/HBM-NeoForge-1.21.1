package com.hbm.config;

import net.neoforged.neoforge.common.ModConfigSpec;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * 迁移自 1.12.2 com.hbm.config.CompatibilityConfig。
 *
 * ⚠️ 与简报描述不符的事实修正：
 *   - 本类主体是 76 个 HashMap（维度→数值）+ 5 个 HashSet + 6 个布尔开关，非"纯开关式"
 *   - 无 [00_compatibility] 类别；使用既有类别（13_radiation / 02_ores / 15_structures /
 *     04_dungeons / 12_mobs / 03_nukes），键名与原 .cfg 完全一致
 *
 * 迁移方式：
 *   - createConfigHashMap/HashSet → configStringList + load() 时 CommonConfig.parseHashMap/parseHashSet
 *   - isWarDim(World) 重载删除（原 world.provider.getDimension() 在 1.21.1 已移除，
 *     维度改为 ResourceKey；P7 世界生成迁移时按 ResourceKey 重构）；isWarDim(int) 保留
 */
public class CompatibilityConfig {

    public static HashMap<Integer, Float> dimensionRad;
    public static HashSet<String> bedrockOreBlacklist;

    public static HashMap<Integer, Integer> uraniumSpawn;
    public static HashMap<Integer, Integer> thoriumSpawn;
    public static HashMap<Integer, Integer> titaniumSpawn;
    public static HashMap<Integer, Integer> sulfurSpawn;
    public static HashMap<Integer, Integer> aluminiumSpawn;
    public static HashMap<Integer, Integer> copperSpawn;
    public static HashMap<Integer, Integer> fluoriteSpawn;
    public static HashMap<Integer, Integer> niterSpawn;
    public static HashMap<Integer, Integer> tungstenSpawn;
    public static HashMap<Integer, Integer> leadSpawn;
    public static HashMap<Integer, Integer> berylliumSpawn;
    public static HashMap<Integer, Integer> ligniteSpawn;
    public static HashMap<Integer, Integer> asbestosSpawn;
    public static HashMap<Integer, Integer> rareSpawn;
    public static HashMap<Integer, Integer> lithiumSpawn;
    public static HashMap<Integer, Integer> cinnabarSpawn;
    public static HashMap<Integer, Integer> oilcoalSpawn;
    public static HashMap<Integer, Integer> gassshaleSpawn;
    public static HashMap<Integer, Integer> gasbubbleSpawn;
    public static HashMap<Integer, Integer> explosivebubbleSpawn;
    public static HashMap<Integer, Integer> alexandriteSpawn;
    public static HashMap<Integer, Integer> oilBubbleSpawn;
    public static HashMap<Integer, Integer> cobaltSpawn;
    public static HashMap<Integer, Integer> gneissIronSpawn;
    public static HashMap<Integer, Integer> gneissGoldSpawn;

    public static HashMap<Integer, Integer> ironClusterSpawn;
    public static HashMap<Integer, Integer> titaniumClusterSpawn;
    public static HashMap<Integer, Integer> aluminiumClusterSpawn;
    public static HashMap<Integer, Integer> copperClusterSpawn;

    public static HashMap<Integer, Integer> malachiteSpawn;

    public static HashMap<Integer, Integer> reiiumSpawn;
    public static HashMap<Integer, Integer> weidaniumSpawn;
    public static HashMap<Integer, Integer> australiumSpawn;
    public static HashMap<Integer, Integer> verticiumSpawn;
    public static HashMap<Integer, Integer> unobtainiumSpawn;
    public static HashMap<Integer, Integer> daffergonSpawn;

    public static HashMap<Integer, Integer> netherUraniumSpawn;
    public static HashMap<Integer, Integer> netherTungstenSpawn;
    public static HashMap<Integer, Integer> netherSulfurSpawn;
    public static HashMap<Integer, Integer> netherPhosphorusSpawn;
    public static HashMap<Integer, Integer> netherCoalSpawn;
    public static HashMap<Integer, Integer> netherPlutoniumSpawn;
    public static HashMap<Integer, Integer> netherCobaltSpawn;

    public static HashMap<Integer, Integer> endTixiteSpawn;

    public static HashMap<Integer, Integer> bedrockOilSpawn;

    public static HashMap<Integer, Integer> dunaOilSpawn;
    public static HashMap<Integer, Integer> laytheOilSpawn;
    public static HashMap<Integer, Integer> eveGasSpawn;

    public static HashMap<Integer, Integer> radioStructure;
    public static HashMap<Integer, Integer> antennaStructure;
    public static HashMap<Integer, Integer> atomStructure;
    public static HashMap<Integer, Integer> vertibirdStructure;
    public static HashMap<Integer, Integer> dungeonStructure;
    public static HashMap<Integer, Integer> relayStructure;
    public static HashMap<Integer, Integer> satelliteStructure;
    public static HashMap<Integer, Integer> bunkerStructure;
    public static HashMap<Integer, Integer> siloStructure;
    public static HashMap<Integer, Integer> factoryStructure;
    public static HashMap<Integer, Integer> dudStructure;
    public static HashMap<Integer, Integer> spaceshipStructure;
    public static HashMap<Integer, Integer> barrelStructure;
    public static HashMap<Integer, Integer> geyserChlorine;
    public static HashMap<Integer, Integer> geyserVapor;
    public static HashMap<Integer, Integer> geyserNether;
    public static HashMap<Integer, Integer> capsuleStructure;
    public static HashMap<Integer, Integer> broadcaster;
    public static HashMap<Integer, Integer> minefreq;
    public static HashMap<Integer, Integer> radfreq;
    public static HashMap<Integer, Integer> vaultfreq;
    public static HashMap<Integer, Integer> arcticStructure;
    public static HashMap<Integer, Integer> jungleStructure;
    public static HashMap<Integer, Integer> pyramidStructure;

    public static HashMap<Integer, Integer> meteorStrikeChance;
    public static HashMap<Integer, Integer> meteorShowerChance;
    public static HashMap<Integer, Integer> meteorShowerDuration;

    public static HashMap<String, Float> mobModRadresistance;
    public static HashSet<String> mobModRadimmune;

    public static HashMap<String, Float> mobRadresistance;
    public static HashSet<String> mobRadimmune;

    public static boolean mobGear = true;

    public static boolean modLoot = true;

    public static boolean doEvaporateWater = true;
    public static boolean doFillCraterWithWater = true;
    public static HashMap<Integer, Integer> fillCraterWithWater;

    public static boolean peaceDimensionsIsWhitelist = true;
    public static HashSet<Integer> peaceDimensions;

    // ===== SpecValue（全部为字符串列表 + 布尔） =====
    private static ModConfigSpec.ConfigValue<List<? extends String>> DIMENSION_RAD;
    private static ModConfigSpec.ConfigValue<List<? extends String>> URANIUM_SPAWN;
    private static ModConfigSpec.ConfigValue<List<? extends String>> THORIUM_SPAWN;
    private static ModConfigSpec.ConfigValue<List<? extends String>> TITANIUM_SPAWN;
    private static ModConfigSpec.ConfigValue<List<? extends String>> SULFUR_SPAWN;
    private static ModConfigSpec.ConfigValue<List<? extends String>> ALUMINIUM_SPAWN;
    private static ModConfigSpec.ConfigValue<List<? extends String>> COPPER_SPAWN;
    private static ModConfigSpec.ConfigValue<List<? extends String>> FLUORITE_SPAWN;
    private static ModConfigSpec.ConfigValue<List<? extends String>> NITER_SPAWN;
    private static ModConfigSpec.ConfigValue<List<? extends String>> TUNGSTEN_SPAWN;
    private static ModConfigSpec.ConfigValue<List<? extends String>> LEAD_SPAWN;
    private static ModConfigSpec.ConfigValue<List<? extends String>> BERYLLIUM_SPAWN;
    private static ModConfigSpec.ConfigValue<List<? extends String>> LIGNITE_SPAWN;
    private static ModConfigSpec.ConfigValue<List<? extends String>> ASBESTOS_SPAWN;
    private static ModConfigSpec.ConfigValue<List<? extends String>> RARE_SPAWN;
    private static ModConfigSpec.ConfigValue<List<? extends String>> LITHIUM_SPAWN;
    private static ModConfigSpec.ConfigValue<List<? extends String>> CINNABAR_SPAWN;
    private static ModConfigSpec.ConfigValue<List<? extends String>> OILCOAL_SPAWN;
    private static ModConfigSpec.ConfigValue<List<? extends String>> GASSSHALE_SPAWN;
    private static ModConfigSpec.ConfigValue<List<? extends String>> GASBUBBLE_SPAWN;
    private static ModConfigSpec.ConfigValue<List<? extends String>> EXPLOSIVEBUBBLE_SPAWN;
    private static ModConfigSpec.ConfigValue<List<? extends String>> ALEXANDRITE_SPAWN;
    private static ModConfigSpec.ConfigValue<List<? extends String>> OIL_BUBBLE_SPAWN;
    private static ModConfigSpec.ConfigValue<List<? extends String>> COBALT_SPAWN;
    private static ModConfigSpec.ConfigValue<List<? extends String>> GNEISS_IRON_SPAWN;
    private static ModConfigSpec.ConfigValue<List<? extends String>> GNEISS_GOLD_SPAWN;
    private static ModConfigSpec.ConfigValue<List<? extends String>> IRON_CLUSTER_SPAWN;
    private static ModConfigSpec.ConfigValue<List<? extends String>> TITANIUM_CLUSTER_SPAWN;
    private static ModConfigSpec.ConfigValue<List<? extends String>> ALUMINIUM_CLUSTER_SPAWN;
    private static ModConfigSpec.ConfigValue<List<? extends String>> COPPER_CLUSTER_SPAWN;
    private static ModConfigSpec.ConfigValue<List<? extends String>> MALACHITE_SPAWN;
    private static ModConfigSpec.ConfigValue<List<? extends String>> REIIUM_SPAWN;
    private static ModConfigSpec.ConfigValue<List<? extends String>> WEIDANIUM_SPAWN;
    private static ModConfigSpec.ConfigValue<List<? extends String>> AUSTRALIUM_SPAWN;
    private static ModConfigSpec.ConfigValue<List<? extends String>> VERTICIUM_SPAWN;
    private static ModConfigSpec.ConfigValue<List<? extends String>> UNOBTAINIUM_SPAWN;
    private static ModConfigSpec.ConfigValue<List<? extends String>> DAFFERGON_SPAWN;
    private static ModConfigSpec.ConfigValue<List<? extends String>> NETHER_URANIUM_SPAWN;
    private static ModConfigSpec.ConfigValue<List<? extends String>> NETHER_TUNGSTEN_SPAWN;
    private static ModConfigSpec.ConfigValue<List<? extends String>> NETHER_SULFUR_SPAWN;
    private static ModConfigSpec.ConfigValue<List<? extends String>> NETHER_PHOSPHORUS_SPAWN;
    private static ModConfigSpec.ConfigValue<List<? extends String>> NETHER_COAL_SPAWN;
    private static ModConfigSpec.ConfigValue<List<? extends String>> NETHER_PLUTONIUM_SPAWN;
    private static ModConfigSpec.ConfigValue<List<? extends String>> NETHER_COBALT_SPAWN;
    private static ModConfigSpec.ConfigValue<List<? extends String>> END_TIXITE_SPAWN;
    private static ModConfigSpec.ConfigValue<List<? extends String>> BEDROCK_OIL_SPAWN;
    private static ModConfigSpec.ConfigValue<List<? extends String>> DUNA_OIL_SPAWN;
    private static ModConfigSpec.ConfigValue<List<? extends String>> LAYTHE_OIL_SPAWN;
    private static ModConfigSpec.ConfigValue<List<? extends String>> EVE_GAS_SPAWN;
    private static ModConfigSpec.ConfigValue<List<? extends String>> RADIO_STRUCTURE;
    private static ModConfigSpec.ConfigValue<List<? extends String>> ANTENNA_STRUCTURE;
    private static ModConfigSpec.ConfigValue<List<? extends String>> ATOM_STRUCTURE;
    private static ModConfigSpec.ConfigValue<List<? extends String>> VERTIBIRD_STRUCTURE;
    private static ModConfigSpec.ConfigValue<List<? extends String>> DUNGEON_STRUCTURE;
    private static ModConfigSpec.ConfigValue<List<? extends String>> RELAY_STRUCTURE;
    private static ModConfigSpec.ConfigValue<List<? extends String>> SATELLITE_STRUCTURE;
    private static ModConfigSpec.ConfigValue<List<? extends String>> BUNKER_STRUCTURE;
    private static ModConfigSpec.ConfigValue<List<? extends String>> SILO_STRUCTURE;
    private static ModConfigSpec.ConfigValue<List<? extends String>> FACTORY_STRUCTURE;
    private static ModConfigSpec.ConfigValue<List<? extends String>> DUD_STRUCTURE;
    private static ModConfigSpec.ConfigValue<List<? extends String>> SPACESHIP_STRUCTURE;
    private static ModConfigSpec.ConfigValue<List<? extends String>> BARREL_STRUCTURE;
    private static ModConfigSpec.ConfigValue<List<? extends String>> BROADCASTER;
    private static ModConfigSpec.ConfigValue<List<? extends String>> MINEFREQ;
    private static ModConfigSpec.ConfigValue<List<? extends String>> RADFREQ;
    private static ModConfigSpec.ConfigValue<List<? extends String>> VAULTFREQ;
    private static ModConfigSpec.ConfigValue<List<? extends String>> GEYSER_CHLORINE;
    private static ModConfigSpec.ConfigValue<List<? extends String>> GEYSER_VAPOR;
    private static ModConfigSpec.ConfigValue<List<? extends String>> GEYSER_NETHER;
    private static ModConfigSpec.ConfigValue<List<? extends String>> CAPSULE_STRUCTURE;
    private static ModConfigSpec.ConfigValue<List<? extends String>> ARCTIC_STRUCTURE;
    private static ModConfigSpec.ConfigValue<List<? extends String>> JUNGLE_STRUCTURE;
    private static ModConfigSpec.ConfigValue<List<? extends String>> PYRAMID_STRUCTURE;
    private static ModConfigSpec.ConfigValue<List<? extends String>> MOB_MOD_RADRESISTANCE;
    private static ModConfigSpec.ConfigValue<List<? extends String>> MOB_RADRESISTANCE;
    private static ModConfigSpec.ConfigValue<List<? extends String>> MOB_MOD_RADIMMUNE;
    private static ModConfigSpec.ConfigValue<List<? extends String>> MOB_RADIMMUNE;
    private static ModConfigSpec.BooleanValue MOB_GEAR;
    private static ModConfigSpec.BooleanValue MOD_LOOT;
    private static ModConfigSpec.BooleanValue DO_FILL_CRATER_WITH_WATER;
    private static ModConfigSpec.ConfigValue<List<? extends String>> FILL_CRATER_WITH_WATER;
    private static ModConfigSpec.BooleanValue PEACE_DIMENSIONS_IS_WHITELIST;
    private static ModConfigSpec.ConfigValue<List<? extends String>> PEACE_DIMENSIONS;
    private static ModConfigSpec.ConfigValue<List<? extends String>> BEDROCK_ORE_BLACKLIST;

    public static void build(ModConfigSpec.Builder builder) {
        DIMENSION_RAD = CommonConfig.configStringList(builder, CommonConfig.CATEGORY_RADIATION, "01.01_dimensionRadiation", "Amount of background radiation in the dimension in Rad/s - <dimID:Rad> (Int:Float)", new String[]{
                "-1:0.1", "1:0.01",
                "-13:2.5", "-61:5", "-60:5",
                "-27:0.05", "-26:0.05",
                "-28:0.1",
                "-29:0.1", "-67:0.15", "-66:0.15",
                "-1502:0.3", "-1503:0.4",
                "-30:1",
                "-31:0.05", "-63:4", "-62:4",
                "-20:5", "-65:5", "-64:5",
                "-15:5", "-69:20", "-68:20",
                "-1500:6", "-1501:5", "-1506:4.5", "-1505:4",
                "-16:6", "-71:16", "-70:16",
                "-1507:4", "-1508:2", "-1511:1",
                "-17:5", "-73:12", "-72:12",
                "-1510:4", "-1509:2",
                "-18:4", "-75:8", "-74:8",
                "-1504:1.5",
                "-19:0.3", "-77:0.3", "-76:0.3",
                "-21:0.1", "-79:0.1", "-78:0.1",
        });

        URANIUM_SPAWN = CommonConfig.configStringList(builder, CommonConfig.CATEGORY_ORES, "01.01_uraniumSpawnrate", "Amount of uranium ore veins per chunk - <dimID:amount> (Int:Int)", new String[]{"0:7", "-6:7"});
        TITANIUM_SPAWN = CommonConfig.configStringList(builder, CommonConfig.CATEGORY_ORES, "01.02_titaniumSpawnrate", "Amount of titanium ore veins per chunk - <dimID:amount> (Int:Int)", new String[]{"0:8", "-6:8"});
        SULFUR_SPAWN = CommonConfig.configStringList(builder, CommonConfig.CATEGORY_ORES, "01.03_sulfurSpawnrate", "Amount of sulfur ore veins per chunk - <dimID:amount> (Int:Int)", new String[]{"0:5", "-6:5"});
        ALUMINIUM_SPAWN = CommonConfig.configStringList(builder, CommonConfig.CATEGORY_ORES, "01.04_aluminiumSpawnrate", "Amount of aluminium ore veins per chunk - <dimID:amount> (Int:Int)", new String[]{"0:7", "-6:7"});
        COPPER_SPAWN = CommonConfig.configStringList(builder, CommonConfig.CATEGORY_ORES, "01.05_copperSpawnrate", "Amount of copper ore veins per chunk - <dimID:amount> (Int:Int)", new String[]{"0:12", "-6:12"});
        FLUORITE_SPAWN = CommonConfig.configStringList(builder, CommonConfig.CATEGORY_ORES, "01.06_fluoriteSpawnrate", "Amount of fluorite ore veins per chunk - <dimID:amount> (Int:Int)", new String[]{"0:6", "-6:6"});
        NITER_SPAWN = CommonConfig.configStringList(builder, CommonConfig.CATEGORY_ORES, "01.07_niterSpawnrate", "Amount of niter ore veins per chunk - <dimID:amount> (Int:Int)", new String[]{"0:6", "-6:6"});
        TUNGSTEN_SPAWN = CommonConfig.configStringList(builder, CommonConfig.CATEGORY_ORES, "01.08_tungstenSpawnrate", "Amount of tungsten ore veins per chunk - <dimID:amount> (Int:Int)", new String[]{"0:10", "-6:10"});
        LEAD_SPAWN = CommonConfig.configStringList(builder, CommonConfig.CATEGORY_ORES, "01.09_leadSpawnrate", "Amount of lead ore veins per chunk - <dimID:amount> (Int:Int)", new String[]{"0:6", "-6:6"});
        BERYLLIUM_SPAWN = CommonConfig.configStringList(builder, CommonConfig.CATEGORY_ORES, "01.10_berylliumSpawnrate", "Amount of beryllium ore veins per chunk - <dimID:amount> (Int:Int)", new String[]{"0:6", "-6:6"});
        THORIUM_SPAWN = CommonConfig.configStringList(builder, CommonConfig.CATEGORY_ORES, "01.11_thoriumSpawnrate", "Amount of thorium ore veins per chunk - <dimID:amount> (Int:Int)", new String[]{"0:7", "-6:7"});
        LIGNITE_SPAWN = CommonConfig.configStringList(builder, CommonConfig.CATEGORY_ORES, "01.12_ligniteSpawnrate", "Amount of lignite ore veins per chunk - <dimID:amount> (Int:Int)", new String[]{"0:2", "-6:2"});
        ASBESTOS_SPAWN = CommonConfig.configStringList(builder, CommonConfig.CATEGORY_ORES, "01.13_asbestosSpawnRate", "Amount of asbestos ore veins per chunk - <dimID:amount> (Int:Int)", new String[]{"0:2", "-6:2"});
        LITHIUM_SPAWN = CommonConfig.configStringList(builder, CommonConfig.CATEGORY_ORES, "01.14_lithiumSpawnRate", "Amount of schist lithium ore veins per chunk - <dimID:amount> (Int:Int)", new String[]{"0:6", "-6:6"});
        RARE_SPAWN = CommonConfig.configStringList(builder, CommonConfig.CATEGORY_ORES, "01.15_rareEarthSpawnRate", "Amount of rare earth ore veins per chunk - <dimID:amount> (Int:Int)", new String[]{"0:6", "-6:6"});
        OILCOAL_SPAWN = CommonConfig.configStringList(builder, CommonConfig.CATEGORY_ORES, "01.16_oilCoalSpawnRate", "Spawns an oily coal vein every nTH chunk - <dimID:amount> (Int:Int)", new String[]{"0:128", "-6:128"});
        GASSSHALE_SPAWN = CommonConfig.configStringList(builder, CommonConfig.CATEGORY_ORES, "01.17_gasShaleSpawnRate", "Amount of oil shale veins per chunk - <dimID:amount> (Int:Int)", new String[]{"0:5", "-6:5"});
        EXPLOSIVEBUBBLE_SPAWN = CommonConfig.configStringList(builder, CommonConfig.CATEGORY_ORES, "01.18_explosiveBubbleSpawnRate", "Spawns an explosive gas bubble every nTH chunk - <dimID:amount> (Int:Int)", new String[]{"0:80", "-6:80"});
        GASBUBBLE_SPAWN = CommonConfig.configStringList(builder, CommonConfig.CATEGORY_ORES, "01.19_gasBubbleSpawnRate", "Spawns a gas bubble every nTH chunk - <dimID:amount> (Int:Int)", new String[]{"0:40", "-6:40"});
        CINNABAR_SPAWN = CommonConfig.configStringList(builder, CommonConfig.CATEGORY_ORES, "01.20_cinnabarSpawnRate", "Amount of cinnabar ore veins per chunk - <dimID:amount> (Int:Int)", new String[]{"0:1", "-6:1"});
        COBALT_SPAWN = CommonConfig.configStringList(builder, CommonConfig.CATEGORY_ORES, "01.21_cobaltSpawnRate", "Amount of cobalt ore veins per chunk - <dimID:amount> (Int:Int)", new String[]{"0:2", "-6:2"});
        GNEISS_IRON_SPAWN = CommonConfig.configStringList(builder, CommonConfig.CATEGORY_ORES, "01.34_gneissIronSpawnrate", "Amount of iron ore veins per chunk in Gneiss - <dimID:amount> (Int:Int)", new String[]{"0:25"});
        GNEISS_GOLD_SPAWN = CommonConfig.configStringList(builder, CommonConfig.CATEGORY_ORES, "01.35_gneissGoldSpawnrate", "Amount of gold ore veins per chunk in Gneiss - <dimID:amount> (Int:Int)", new String[]{"0:10"});
        IRON_CLUSTER_SPAWN = CommonConfig.configStringList(builder, CommonConfig.CATEGORY_ORES, "01.22_ironClusterSpawn", "Amount of iron cluster veins per chunk - <dimID:amount> (Int:Int)", new String[]{"0:4", "-6:4"});
        TITANIUM_CLUSTER_SPAWN = CommonConfig.configStringList(builder, CommonConfig.CATEGORY_ORES, "01.23_titaniumClusterSpawn", "Amount of titanium cluster veins per chunk - <dimID:amount> (Int:Int)", new String[]{"0:2", "-6:2"});
        ALUMINIUM_CLUSTER_SPAWN = CommonConfig.configStringList(builder, CommonConfig.CATEGORY_ORES, "01.24_aluminiumClusterSpawn", "Amount of aluminium cluster veins per chunk - <dimID:amount> (Int:Int)", new String[]{"0:3", "-6:3"});
        COPPER_CLUSTER_SPAWN = CommonConfig.configStringList(builder, CommonConfig.CATEGORY_ORES, "01.24_copperClusterSpawn", "Amount of copper cluster veins per chunk - <dimID:amount> (Int:Int)", new String[]{"0:3", "-6:3"});
        MALACHITE_SPAWN = CommonConfig.configStringList(builder, CommonConfig.CATEGORY_ORES, "01.24_malachiteSpawn", "Amount of malachite veins per chunk - <dimID:amount> (Int:Int)", new String[]{"0:1"});
        REIIUM_SPAWN = CommonConfig.configStringList(builder, CommonConfig.CATEGORY_ORES, "01.25_reiiumSpawnRate", "Amount of reiium ore veins per chunk - <dimID:amount> (Int:Int)", new String[]{"-29:1"});
        WEIDANIUM_SPAWN = CommonConfig.configStringList(builder, CommonConfig.CATEGORY_ORES, "01.26_weidaniumSpawnRate", "Amount of weidanium ore veins per chunk - <dimID:amount> (Int:Int)", new String[]{"-31:1"});
        AUSTRALIUM_SPAWN = CommonConfig.configStringList(builder, CommonConfig.CATEGORY_ORES, "01.27_australiumSpawnRate", "Amount of australium ore veins per chunk - <dimID:amount> (Int:Int)", new String[]{"-31:1"});
        VERTICIUM_SPAWN = CommonConfig.configStringList(builder, CommonConfig.CATEGORY_ORES, "01.28_verticiumSpawnRate", "Amount of verticium ore veins per chunk - <dimID:amount> (Int:Int)", new String[]{"-30:1"});
        UNOBTAINIUM_SPAWN = CommonConfig.configStringList(builder, CommonConfig.CATEGORY_ORES, "01.29_unobtainiumSpawnRate", "Amount of unobtainium ore veins per chunk - <dimID:amount> (Int:Int)", new String[]{"-28:1"});
        DAFFERGON_SPAWN = CommonConfig.configStringList(builder, CommonConfig.CATEGORY_ORES, "01.30_daffergonSpawnRate", "Amount of daffergon ore veins per chunk - <dimID:amount> (Int:Int)", new String[]{"-30:1"});
        BEDROCK_OIL_SPAWN = CommonConfig.configStringList(builder, CommonConfig.CATEGORY_ORES, "01.31_bedrockOilSpawnRate", "Spawn bedrock oil every nTH chunk - <dimID:amount> (Int:Int)", new String[]{"0:200", "-6:200"});
        ALEXANDRITE_SPAWN = CommonConfig.configStringList(builder, CommonConfig.CATEGORY_ORES, "01.32_alexandriteSpawnRate", "Spawns an alexandrite vein every nTH chunk - <dimID:amount> (Int:Int)", new String[]{"0:100"});
        OIL_BUBBLE_SPAWN = CommonConfig.configStringList(builder, CommonConfig.CATEGORY_ORES, "01.33_oilSpawnRate", "Spawns an oil bubble every nTH chunk - <dimID:amount> (Int:Int)", new String[]{"0:100"});
        NETHER_URANIUM_SPAWN = CommonConfig.configStringList(builder, CommonConfig.CATEGORY_ORES, "02.N00_uraniumSpawnrate", "Amount of nether uranium per chunk - <dimID:amount> (Int:Int)", new String[]{"-1:8"});
        NETHER_TUNGSTEN_SPAWN = CommonConfig.configStringList(builder, CommonConfig.CATEGORY_ORES, "02.N01_tungstenSpawnrate", "Amount of nether tungsten per chunk - <dimID:amount> (Int:Int)", new String[]{"-1:10"});
        NETHER_SULFUR_SPAWN = CommonConfig.configStringList(builder, CommonConfig.CATEGORY_ORES, "02.N02_sulfurSpawnrate", "Amount of nether sulfur per chunk - <dimID:amount> (Int:Int)", new String[]{"-1:26"});
        NETHER_PHOSPHORUS_SPAWN = CommonConfig.configStringList(builder, CommonConfig.CATEGORY_ORES, "02.N03_phosphorusSpawnrate", "Amount of nether phosphorus per chunk - <dimID:amount> (Int:Int)", new String[]{"-1:24"});
        NETHER_COAL_SPAWN = CommonConfig.configStringList(builder, CommonConfig.CATEGORY_ORES, "02.N04_coalSpawnrate", "Amount of nether coal per chunk - <dimID:amount> (Int:Int)", new String[]{"-1:24"});
        NETHER_PLUTONIUM_SPAWN = CommonConfig.configStringList(builder, CommonConfig.CATEGORY_ORES, "02.N05_plutoniumSpawnrate", "Amount of nether plutonium per chunk, if enabled - <dimID:amount> (Int:Int)", new String[]{"-1:8"});
        NETHER_COBALT_SPAWN = CommonConfig.configStringList(builder, CommonConfig.CATEGORY_ORES, "02.N06_cobaltSpawnrate", "Amount of nether cobalt per chunk - <dimID:amount> (Int:Int)", new String[]{"-1:2"});
        DUNA_OIL_SPAWN = CommonConfig.configStringList(builder, CommonConfig.CATEGORY_ORES, "0.2S1_oilSpawnRate", "Spawns an oil bubble every nTH chunk (on Duna) - <dimID:amount> (Int:Int)", new String[]{"16:100"});
        LAYTHE_OIL_SPAWN = CommonConfig.configStringList(builder, CommonConfig.CATEGORY_ORES, "0.2S2_oilSpawnRate", "Spawns a DS oil bubble every nTH chunk (on Laythe) - <dimID:amount> (Int:Int)", new String[]{"22:100"});
        EVE_GAS_SPAWN = CommonConfig.configStringList(builder, CommonConfig.CATEGORY_ORES, "0.2S3_gasSpawnRate", "Spawns a natural gas bubble every nTH chunk (on Eve) - <dimID:amount> (Int:Int)", new String[]{"18:100"});
        END_TIXITE_SPAWN = CommonConfig.configStringList(builder, CommonConfig.CATEGORY_ORES, "03.E01_tixiteSpawnrate", "Amount of end trixite per chunk - <dimID:amount> (Int:Int)", new String[]{"1:8"});

        RADIO_STRUCTURE = CommonConfig.configStringList(builder, CommonConfig.CATEGORY_STRUCTURES, "03.01_radioSpawn", "Spawn radio station on every nTH chunk - <dimID:n> (Int:Int)", new String[]{"0:1000"});
        ANTENNA_STRUCTURE = CommonConfig.configStringList(builder, CommonConfig.CATEGORY_STRUCTURES, "03.02_antennaSpawn", "Spawn antenna on every nTH chunk - <dimID:n> (Int:Int)", new String[]{"0:750"});
        ATOM_STRUCTURE = CommonConfig.configStringList(builder, CommonConfig.CATEGORY_STRUCTURES, "03.03_atomSpawn", "Spawn power plant on every nTH chunk - <dimID:n> (Int:Int)", new String[]{"0:500"});
        VERTIBIRD_STRUCTURE = CommonConfig.configStringList(builder, CommonConfig.CATEGORY_STRUCTURES, "03.04_vertibirdSpawn", "Spawn vertibird on every nTH chunk - <dimID:n> (Int:Int)", new String[]{"0:500"});
        DUNGEON_STRUCTURE = CommonConfig.configStringList(builder, CommonConfig.CATEGORY_STRUCTURES, "03.05_dungeonSpawn", "Spawn library dungeon on every nTH chunk - <dimID:n> (Int:Int)", new String[]{"0:64"});
        RELAY_STRUCTURE = CommonConfig.configStringList(builder, CommonConfig.CATEGORY_STRUCTURES, "03.06_relaySpawn", "Spawn relay on every nTH chunk - <dimID:n> (Int:Int)", new String[]{"0:500"});
        SATELLITE_STRUCTURE = CommonConfig.configStringList(builder, CommonConfig.CATEGORY_STRUCTURES, "03.07_satelliteSpawn", "Spawn satellite dish on every nTH chunk - <dimID:n> (Int:Int)", new String[]{"0:500"});
        BUNKER_STRUCTURE = CommonConfig.configStringList(builder, CommonConfig.CATEGORY_STRUCTURES, "03.08_bunkerSpawn", "Spawn bunker on every nTH chunk - <dimID:n> (Int:Int)", new String[]{"0:1000"});
        SILO_STRUCTURE = CommonConfig.configStringList(builder, CommonConfig.CATEGORY_STRUCTURES, "03.09_siloSpawn", "Spawn missile silo on every nTH chunk - <dimID:n> (Int:Int)", new String[]{"0:1000"});
        FACTORY_STRUCTURE = CommonConfig.configStringList(builder, CommonConfig.CATEGORY_STRUCTURES, "03.10_factorySpawn", "Spawn factory on every nTH chunk - <dimID:n> (Int:Int)", new String[]{"0:1000"});
        DUD_STRUCTURE = CommonConfig.configStringList(builder, CommonConfig.CATEGORY_STRUCTURES, "03.11_dudSpawn", "Spawn dud on every nTH chunk - <dimID:n> (Int:Int)", new String[]{"0:500"});
        SPACESHIP_STRUCTURE = CommonConfig.configStringList(builder, CommonConfig.CATEGORY_STRUCTURES, "03.12_spaceshipSpawn", "Spawn spaceship on every nTH chunk - <dimID:n> (Int:Int)", new String[]{"0:1000"});
        BARREL_STRUCTURE = CommonConfig.configStringList(builder, CommonConfig.CATEGORY_STRUCTURES, "03.13_barrelSpawn", "Spawn waste tank on every nTH chunk - <dimID:n> (Int:Int)", new String[]{"0:5000"});
        BROADCASTER = CommonConfig.configStringList(builder, CommonConfig.CATEGORY_STRUCTURES, "03.14_broadcasterSpawn", "Spawn corrupt broadcaster on every nTH chunk - <dimID:n> (Int:Int)", new String[]{"0:5000"});
        MINEFREQ = CommonConfig.configStringList(builder, CommonConfig.CATEGORY_STRUCTURES, "03.15_landmineSpawn", "Spawn AP landmine on every nTH chunk - <dimID:n> (Int:Int)", new String[]{"0:64"});
        RADFREQ = CommonConfig.configStringList(builder, CommonConfig.CATEGORY_STRUCTURES, "03.17_radHotsoptSpawn", "Spawn big radiation hotspot on every nTH chunk - <dimID:n> (Int:Int)", new String[]{"0:5000"});
        VAULTFREQ = CommonConfig.configStringList(builder, CommonConfig.CATEGORY_STRUCTURES, "03.18_vaultSpawn", "Spawn locked safe on every nTH chunk - <dimID:n> (Int:Int)", new String[]{"0:2500"});
        GEYSER_CHLORINE = CommonConfig.configStringList(builder, CommonConfig.CATEGORY_STRUCTURES, "03.20_geyserChlorineSpawn", "Spawn poison geyser on every nTH chunk - <dimID:n> (Int:Int)", new String[]{"0:3000"});
        GEYSER_VAPOR = CommonConfig.configStringList(builder, CommonConfig.CATEGORY_STRUCTURES, "03.21_geyserVaporSpawn", "Spawn vapor geyser on every nTH chunk - <dimID:n> (Int:Int)", new String[]{"0:500"});
        GEYSER_NETHER = CommonConfig.configStringList(builder, CommonConfig.CATEGORY_STRUCTURES, "03.22_geyserNetherSpawn", "Spawn nether geyser on every nTH chunk - <dimID:n> (Int:Int)", new String[]{"-1:2"});
        CAPSULE_STRUCTURE = CommonConfig.configStringList(builder, CommonConfig.CATEGORY_DUNGEONS, "03.24_capsuleSpawn", "Spawn landing capsule on every nTH chunk - <dimID:n> (Int:Int)", new String[]{"0:100"});
        ARCTIC_STRUCTURE = CommonConfig.configStringList(builder, CommonConfig.CATEGORY_DUNGEONS, "03.25_arcticVaultSpawn", "Spawn artic code vault on every nTH chunk - <dimID:n> (Int:Int)", new String[]{"0:500"});
        JUNGLE_STRUCTURE = CommonConfig.configStringList(builder, CommonConfig.CATEGORY_DUNGEONS, "03.26_jungleDungeonSpawn", "Spawn jungle dungeon on every nTH chunk - <dimID:n> (Int:Int)", new String[]{"0:2000"});
        PYRAMID_STRUCTURE = CommonConfig.configStringList(builder, CommonConfig.CATEGORY_DUNGEONS, "03.27_pyramidSpawn", "Spawn pyramid on every nTH chunk - <dimID:n> (Int:Int)", new String[]{"0:4000"});

        MOB_MOD_RADRESISTANCE = CommonConfig.configStringList(builder, CommonConfig.CATEGORY_MOBS, "12.01_mob_Mod_Radresistance", "Amount of radiation resistance all the mobs of that mod get. Radresistance s is calculated as s=(1-0.1^r). So a resistance value of 3.0 means that 99.9%=(1-0.1^3.0) of the radiation gets blocked. - <mod=radresistance> (String:Float)", new String[]{"srparasites=0.2", "thaumcraft=0.75"});
        MOB_RADRESISTANCE = CommonConfig.configStringList(builder, CommonConfig.CATEGORY_MOBS, "12.02_mob_Radresistance", "Amount of radiation resistance the mob gets. Radresistance s is calculated as s=(1-0.1^r). So a resistance value of 3.0 means that 99.9%=(1-0.1^3.0) of the radiation gets blocked. - <mod:mobitentifier=radresistance> (String:Float)", new String[]{
                "minecraft:parrot=0.5", "minecraft:rabbit=1.0", "techguns:ghastling=1.2",
                "minecraft:enderman=1.5", "minecraft:blaze=2.0", "techguns:alienbug=2.2",
                "minecraft:bat=2.5", "minecraft:ghast=3.0", "mutantbeasts:mutant_creeper=3.2",
                "minecraft:squid=3.5", "minecraft:spider=4.0", "mutantbeasts:mutant_enderman=4.2",
                "techguns:outcast=4.5", "minecraft:cave_spider=5.0", "minecraft:silverfish=6.0", "techguns:stormtrooper=6.4",
                "techguns:cyberdemon=6.5", "minecraft:endermite=7.0", "pvj:pvj_beach_starfish=7.5", "pvj:pvj_clam=7.8", "minecraft:shulker=8.0",
                "pvj:pvj_starfish=8.2", "techguns:attackhelicopter=8.5", "minecraft:ender_dragon=9.0", "pvj:pvj_snail=9.1", "pvj:pvj_firefly=10.0", "pvj:pvj_fly=11.0",
                "mysticalworld:entity_hell_sprout=2.0", "mysticalworld:entity_lava_cat=2.0",
                "thaumcraft:taintseed=1.2", "thaumcraft:taintseedprime=4.0"
        });
        MOB_MOD_RADIMMUNE = CommonConfig.configStringList(builder, CommonConfig.CATEGORY_MOBS, "12.03_mob_Mod_Radimmune", "List of mods whose entities should all be immune to radiation. - <mod> (String)", new String[]{
                "biomesoplenty", "galacticraftcore", "galacticraftplanets", "extraplanets",
                "thaumicaugmentation", "enderskills", "thaumadditions", "cyberware", "rewired"
        });
        MOB_RADIMMUNE = CommonConfig.configStringList(builder, CommonConfig.CATEGORY_MOBS, "12.04_mob_Radimmune", "List of mobs that are immune to radiation. - <mod:mobitentifier> (String)", new String[]{
                "minecraft:magma_cube", "minecraft:slime", "minecraft:vex", "minecraft:villager_golem", "minecraft:snowman", "minecraft:witch",
                "pvj:pvj_icecube", "tconstruct:blueslime",
                "mutantbeasts:mutant_snow_golem", "mutantbeasts:mutant_zombie", "mutantbeasts:mutant_skeleton",
                "techguns:zombieminer", "techguns:zombiefarmer", "techguns:zombiesoldier", "techguns:zombiepigmansoldier", "techguns:zombiepoliceman", "techguns:skeletonsoldier",
                "techguns:supermutantbasic", "techguns:supermutantelite", "techguns:supermutantheavy",
                "deepmoblearning:glitch",
                "divinefavor:entity.ping", "divinefavor:entity.rope_barrier", "divinefavor:entity.rope_explosive", "divinefavor:entity.rope_glowing", "divinefavor:entity.rope_guide",
                "divinefavor:entity.rope_inert", "divinefavor:entity.rope_luminous", "divinefavor:entity.rope_teleporting", "divinefavor:entity.spell_arrow", "divinefavor:entity.spooky",
                "divinefavor:entity.stoneball",
                "embers:ancient_golem", "embers:ember_light", "embers:ember_packet", "embers:ember_projectile", "embers:magma_projectile",
                "thaumcraft:eldritchwarden", "thaumcraft:eldritchguardian", "thaumcraft:cultistportalgreater", "thaumcraft:cultistportallesser", "thaumcraft:eldritchgolem",
                "thaumcraft:arcanebore", "thaumcraft:fluxrift", "thaumcraft:golem", "thaumcraft:mindspider", "thaumcraft:spellbat", "thaumcraft:turretadvanced", "thaumcraft:turretbasic",
                "tombstone:ghostly_shape"
        });
        MOB_GEAR = CommonConfig.configBool(builder, CommonConfig.CATEGORY_MOBS, "12.05_mobGear", "If true then mobs will be given gear (armor/weapons/gasmasks) from this mod when spawned", true);
        MOD_LOOT = CommonConfig.configBool(builder, CommonConfig.CATEGORY_MOBS, "12.06_modLoot", "If true then this mod will generarte loot for chests", true);

        DO_FILL_CRATER_WITH_WATER = CommonConfig.configBool(builder, CommonConfig.CATEGORY_NUKES, "03.04_doFillCraterWithWater", "If true then nukes will fill the crater with water if it is in a wet place. It creates a bit of lagg but looks better than without it.", true);
        FILL_CRATER_WITH_WATER = CommonConfig.configStringList(builder, CommonConfig.CATEGORY_NUKES, "03.04_fillCraterWithWater", "Waterlevel per dimension which the nuke uses to fill the crater. {+n=>waterlevel height, 0=>dimension waterlevel, -n=> n blocks below dimension waterlevel } - <dimID:n> (Int:Int)", new String[]{"0:0"});
        PEACE_DIMENSIONS_IS_WHITELIST = CommonConfig.configBool(builder, CommonConfig.CATEGORY_NUKES, "03.05_peaceDimensionsIsWhitelist", "If true then the listed dimensions below are all peacefull. If false then the listed dimensions are the only ones where destruction happens.", true);
        PEACE_DIMENSIONS = CommonConfig.configStringList(builder, CommonConfig.CATEGORY_ORES, "03.06_peaceDimensions", "List of Dimensions where block destruction and damage is disabled (Used for server lobbies/science servers/pvp arenas) - <dimID> (Int)", new String[]{});

        BEDROCK_ORE_BLACKLIST = CommonConfig.configStringList(builder, CommonConfig.CATEGORY_ORES, "08.01_bedrockOreBlacklist", "List of OreDict entries that should not have bedrock ores - <ore> (String)", new String[]{"oreTh232", "oreThorium232", "oreVolcanic", "oreSteel"});
    }

    public static void load() {
        dimensionRad = CommonConfig.parseHashMap(DIMENSION_RAD.get(), ":", Integer.class, Float.class);
        uraniumSpawn = CommonConfig.parseHashMap(URANIUM_SPAWN.get(), ":", Integer.class, Integer.class);
        thoriumSpawn = CommonConfig.parseHashMap(THORIUM_SPAWN.get(), ":", Integer.class, Integer.class);
        titaniumSpawn = CommonConfig.parseHashMap(TITANIUM_SPAWN.get(), ":", Integer.class, Integer.class);
        sulfurSpawn = CommonConfig.parseHashMap(SULFUR_SPAWN.get(), ":", Integer.class, Integer.class);
        aluminiumSpawn = CommonConfig.parseHashMap(ALUMINIUM_SPAWN.get(), ":", Integer.class, Integer.class);
        copperSpawn = CommonConfig.parseHashMap(COPPER_SPAWN.get(), ":", Integer.class, Integer.class);
        fluoriteSpawn = CommonConfig.parseHashMap(FLUORITE_SPAWN.get(), ":", Integer.class, Integer.class);
        niterSpawn = CommonConfig.parseHashMap(NITER_SPAWN.get(), ":", Integer.class, Integer.class);
        tungstenSpawn = CommonConfig.parseHashMap(TUNGSTEN_SPAWN.get(), ":", Integer.class, Integer.class);
        leadSpawn = CommonConfig.parseHashMap(LEAD_SPAWN.get(), ":", Integer.class, Integer.class);
        berylliumSpawn = CommonConfig.parseHashMap(BERYLLIUM_SPAWN.get(), ":", Integer.class, Integer.class);
        ligniteSpawn = CommonConfig.parseHashMap(LIGNITE_SPAWN.get(), ":", Integer.class, Integer.class);
        asbestosSpawn = CommonConfig.parseHashMap(ASBESTOS_SPAWN.get(), ":", Integer.class, Integer.class);
        rareSpawn = CommonConfig.parseHashMap(RARE_SPAWN.get(), ":", Integer.class, Integer.class);
        lithiumSpawn = CommonConfig.parseHashMap(LITHIUM_SPAWN.get(), ":", Integer.class, Integer.class);
        cinnabarSpawn = CommonConfig.parseHashMap(CINNABAR_SPAWN.get(), ":", Integer.class, Integer.class);
        oilcoalSpawn = CommonConfig.parseHashMap(OILCOAL_SPAWN.get(), ":", Integer.class, Integer.class);
        gassshaleSpawn = CommonConfig.parseHashMap(GASSSHALE_SPAWN.get(), ":", Integer.class, Integer.class);
        gasbubbleSpawn = CommonConfig.parseHashMap(GASBUBBLE_SPAWN.get(), ":", Integer.class, Integer.class);
        explosivebubbleSpawn = CommonConfig.parseHashMap(EXPLOSIVEBUBBLE_SPAWN.get(), ":", Integer.class, Integer.class);
        alexandriteSpawn = CommonConfig.parseHashMap(ALEXANDRITE_SPAWN.get(), ":", Integer.class, Integer.class);
        oilBubbleSpawn = CommonConfig.parseHashMap(OIL_BUBBLE_SPAWN.get(), ":", Integer.class, Integer.class);
        cobaltSpawn = CommonConfig.parseHashMap(COBALT_SPAWN.get(), ":", Integer.class, Integer.class);
        gneissIronSpawn = CommonConfig.parseHashMap(GNEISS_IRON_SPAWN.get(), ":", Integer.class, Integer.class);
        gneissGoldSpawn = CommonConfig.parseHashMap(GNEISS_GOLD_SPAWN.get(), ":", Integer.class, Integer.class);
        ironClusterSpawn = CommonConfig.parseHashMap(IRON_CLUSTER_SPAWN.get(), ":", Integer.class, Integer.class);
        titaniumClusterSpawn = CommonConfig.parseHashMap(TITANIUM_CLUSTER_SPAWN.get(), ":", Integer.class, Integer.class);
        aluminiumClusterSpawn = CommonConfig.parseHashMap(ALUMINIUM_CLUSTER_SPAWN.get(), ":", Integer.class, Integer.class);
        copperClusterSpawn = CommonConfig.parseHashMap(COPPER_CLUSTER_SPAWN.get(), ":", Integer.class, Integer.class);
        malachiteSpawn = CommonConfig.parseHashMap(MALACHITE_SPAWN.get(), ":", Integer.class, Integer.class);
        reiiumSpawn = CommonConfig.parseHashMap(REIIUM_SPAWN.get(), ":", Integer.class, Integer.class);
        weidaniumSpawn = CommonConfig.parseHashMap(WEIDANIUM_SPAWN.get(), ":", Integer.class, Integer.class);
        australiumSpawn = CommonConfig.parseHashMap(AUSTRALIUM_SPAWN.get(), ":", Integer.class, Integer.class);
        verticiumSpawn = CommonConfig.parseHashMap(VERTICIUM_SPAWN.get(), ":", Integer.class, Integer.class);
        unobtainiumSpawn = CommonConfig.parseHashMap(UNOBTAINIUM_SPAWN.get(), ":", Integer.class, Integer.class);
        daffergonSpawn = CommonConfig.parseHashMap(DAFFERGON_SPAWN.get(), ":", Integer.class, Integer.class);
        netherUraniumSpawn = CommonConfig.parseHashMap(NETHER_URANIUM_SPAWN.get(), ":", Integer.class, Integer.class);
        netherTungstenSpawn = CommonConfig.parseHashMap(NETHER_TUNGSTEN_SPAWN.get(), ":", Integer.class, Integer.class);
        netherSulfurSpawn = CommonConfig.parseHashMap(NETHER_SULFUR_SPAWN.get(), ":", Integer.class, Integer.class);
        netherPhosphorusSpawn = CommonConfig.parseHashMap(NETHER_PHOSPHORUS_SPAWN.get(), ":", Integer.class, Integer.class);
        netherCoalSpawn = CommonConfig.parseHashMap(NETHER_COAL_SPAWN.get(), ":", Integer.class, Integer.class);
        netherPlutoniumSpawn = CommonConfig.parseHashMap(NETHER_PLUTONIUM_SPAWN.get(), ":", Integer.class, Integer.class);
        netherCobaltSpawn = CommonConfig.parseHashMap(NETHER_COBALT_SPAWN.get(), ":", Integer.class, Integer.class);
        endTixiteSpawn = CommonConfig.parseHashMap(END_TIXITE_SPAWN.get(), ":", Integer.class, Integer.class);
        bedrockOilSpawn = CommonConfig.parseHashMap(BEDROCK_OIL_SPAWN.get(), ":", Integer.class, Integer.class);
        dunaOilSpawn = CommonConfig.parseHashMap(DUNA_OIL_SPAWN.get(), ":", Integer.class, Integer.class);
        laytheOilSpawn = CommonConfig.parseHashMap(LAYTHE_OIL_SPAWN.get(), ":", Integer.class, Integer.class);
        eveGasSpawn = CommonConfig.parseHashMap(EVE_GAS_SPAWN.get(), ":", Integer.class, Integer.class);

        radioStructure = CommonConfig.parseHashMap(RADIO_STRUCTURE.get(), ":", Integer.class, Integer.class);
        antennaStructure = CommonConfig.parseHashMap(ANTENNA_STRUCTURE.get(), ":", Integer.class, Integer.class);
        atomStructure = CommonConfig.parseHashMap(ATOM_STRUCTURE.get(), ":", Integer.class, Integer.class);
        vertibirdStructure = CommonConfig.parseHashMap(VERTIBIRD_STRUCTURE.get(), ":", Integer.class, Integer.class);
        dungeonStructure = CommonConfig.parseHashMap(DUNGEON_STRUCTURE.get(), ":", Integer.class, Integer.class);
        relayStructure = CommonConfig.parseHashMap(RELAY_STRUCTURE.get(), ":", Integer.class, Integer.class);
        satelliteStructure = CommonConfig.parseHashMap(SATELLITE_STRUCTURE.get(), ":", Integer.class, Integer.class);
        bunkerStructure = CommonConfig.parseHashMap(BUNKER_STRUCTURE.get(), ":", Integer.class, Integer.class);
        siloStructure = CommonConfig.parseHashMap(SILO_STRUCTURE.get(), ":", Integer.class, Integer.class);
        factoryStructure = CommonConfig.parseHashMap(FACTORY_STRUCTURE.get(), ":", Integer.class, Integer.class);
        dudStructure = CommonConfig.parseHashMap(DUD_STRUCTURE.get(), ":", Integer.class, Integer.class);
        spaceshipStructure = CommonConfig.parseHashMap(SPACESHIP_STRUCTURE.get(), ":", Integer.class, Integer.class);
        barrelStructure = CommonConfig.parseHashMap(BARREL_STRUCTURE.get(), ":", Integer.class, Integer.class);
        geyserChlorine = CommonConfig.parseHashMap(GEYSER_CHLORINE.get(), ":", Integer.class, Integer.class);
        geyserVapor = CommonConfig.parseHashMap(GEYSER_VAPOR.get(), ":", Integer.class, Integer.class);
        geyserNether = CommonConfig.parseHashMap(GEYSER_NETHER.get(), ":", Integer.class, Integer.class);
        capsuleStructure = CommonConfig.parseHashMap(CAPSULE_STRUCTURE.get(), ":", Integer.class, Integer.class);
        broadcaster = CommonConfig.parseHashMap(BROADCASTER.get(), ":", Integer.class, Integer.class);
        minefreq = CommonConfig.parseHashMap(MINEFREQ.get(), ":", Integer.class, Integer.class);
        radfreq = CommonConfig.parseHashMap(RADFREQ.get(), ":", Integer.class, Integer.class);
        vaultfreq = CommonConfig.parseHashMap(VAULTFREQ.get(), ":", Integer.class, Integer.class);
        arcticStructure = CommonConfig.parseHashMap(ARCTIC_STRUCTURE.get(), ":", Integer.class, Integer.class);
        jungleStructure = CommonConfig.parseHashMap(JUNGLE_STRUCTURE.get(), ":", Integer.class, Integer.class);
        pyramidStructure = CommonConfig.parseHashMap(PYRAMID_STRUCTURE.get(), ":", Integer.class, Integer.class);

        mobModRadresistance = CommonConfig.parseHashMap(MOB_MOD_RADRESISTANCE.get(), "=", String.class, Float.class);
        mobRadresistance = CommonConfig.parseHashMap(MOB_RADRESISTANCE.get(), "=", String.class, Float.class);
        mobModRadimmune = CommonConfig.parseHashSet(MOB_MOD_RADIMMUNE.get(), String.class);
        mobRadimmune = CommonConfig.parseHashSet(MOB_RADIMMUNE.get(), String.class);
        mobGear = MOB_GEAR.get();
        modLoot = MOD_LOOT.get();

        doFillCraterWithWater = DO_FILL_CRATER_WITH_WATER.get();
        fillCraterWithWater = CommonConfig.parseHashMap(FILL_CRATER_WITH_WATER.get(), ":", Integer.class, Integer.class);
        peaceDimensionsIsWhitelist = PEACE_DIMENSIONS_IS_WHITELIST.get();
        peaceDimensions = CommonConfig.parseHashSet(PEACE_DIMENSIONS.get(), Integer.class);

        bedrockOreBlacklist = CommonConfig.parseHashSet(BEDROCK_ORE_BLACKLIST.get(), String.class);
    }

    /** 原 isWarDim(World) 重载删除：world.provider.getDimension() 在 1.21.1 已移除（维度改为 ResourceKey）。P7 重构 */
    public static boolean isWarDim(int dimID) {
        if (peaceDimensionsIsWhitelist)
            return !peaceDimensions.contains(dimID);
        else
            return peaceDimensions.contains(dimID);
    }
}
