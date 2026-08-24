package com.hbm.config;

import com.hbm.main.HBM;
import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.neoforge.common.ModConfigSpec;

import java.util.List;
import java.util.Set;

/**
 * 迁移自 1.12.2 com.hbm.config.GeneralConfig。
 *
 * Configuration → ModConfigSpec；键名/类别（01_general / 528 / LESS BULLSHIT MODE）与原 .cfg 一致。
 * 特殊逻辑处理：
 *   - trueExp()：原引用 PrecAssRecipes.INSTANCE.modified（P4 配方系统）→ 当前暂返回 enableExpensiveMode（TODO P4）
 *   - load() 中 GL 3.3 检查：原引用 GLCompat.error（P8 渲染系统）→ 当前 advancedRenderingSupported 暂为 true（TODO P8）
 *   - addCustomCategoryComment（528/LBSM 类别警告）→ ModConfigSpec 不支持类别注释，跳过
 */
public class GeneralConfig {

    public static boolean enableFluidContainersV2 = false;
    public static double conversionRateHeToRF = 1.0F;
    public static boolean autoCableConversion = true;
    public static boolean enablePacketThreading = true;
    public static int packetThreadingCoreCount = 1;
    public static int packetThreadingMaxCount = 2;
    public static boolean packetThreadingErrorBypass = false;
    public static boolean enableZeroCopyCompatibilityMode = false;
    public static boolean enableThreadedNodeSpaceUpdate = true;
    public static boolean enableDebugMode = false;
    public static boolean enableDebugWorldGen = false;
    public static boolean enableSkyboxes = true;
    public static boolean enableImpactWorldProvider = true;
    public static boolean enableKeybindOverlap = true;
    public static boolean enableFluidContainerCompat = true;
    public static Set<String> leadSafeForgeContainerWhitelist = new ObjectOpenHashSet<>();
    public static boolean enableMycelium = false;
    public static boolean enablePlutoniumOre = false;
    public static boolean enableDungeons = true;
    public static boolean enableMDOres = true;
    public static boolean enableMines = true;
    public static boolean enableRad = true;
    public static boolean enableNITAN = true;
    public static boolean enableAutoCleanup = false;
    public static boolean enableBomberShortMode = false;
    public static boolean enableVaults = true;
    public static boolean enableRads = true;
    public static boolean enableCoalGas = true;
    public static boolean enableAsbestosDust = true;
    public static boolean enableRadon = true;
    public static boolean enableCarbonMonoxide = true;
    public static boolean enableFlammableGas = true;
    public static boolean enableExplosiveGas = true;
    public static boolean enableMeltdownGas = true;
    public static boolean advancedRadiation = true;
    public static boolean enableCataclysm = false;
    public static boolean enableExtendedLogging = false;
    public static boolean enableGuns = true;
    public static boolean ssgAnim = true;
    public static boolean enableVirus = true;
    public static boolean enableCrosshairs = true;
    public static boolean enableReflectorCompat = false;
    public static boolean instancedParticles = true;
    public static boolean callListModels = true;
    public static boolean useShaders = false;
    public static boolean useShaders2 = false;
    public static boolean bloom = true;
    public static boolean heatDistortion = true;
    public static boolean recipes = true;
    public static boolean jei = true;
    public static boolean changelog = true;
    public static boolean registerTanks = true;
    public static boolean duckButton = true;
    public static boolean enableMOTD = true;
    public static boolean enableGuideBook = true;
    public static boolean depthEffects = true;
    public static boolean flashlight = true;
    public static boolean flashlightVolumetric = true;
    public static boolean bulletHoleNormalMapping = true;
    public static int flowingDecalAmountMax = 20;
    public static boolean bloodFX = true;
    public static int decoToIngotRate = 25;
    public static int crucibleMaxCharges = 16;
    public static boolean enableReEval = true;
    public static boolean enableServerRecipeSync = true;
    public static boolean enableMachineGravity = false;
    public static boolean enableExpensiveMode = false;

    public static boolean trueExp() {
        // TODO P4: 原 return enableExpensiveMode && !PrecAssRecipes.INSTANCE.modified;
        //          配方系统迁移（com.hbm.inventory.recipes.PrecAssRecipes）后恢复
        return enableExpensiveMode;
    }

    public static boolean enable528 = false;
    public static boolean enable528ReasimBoilers = true;
    public static boolean enable528ColtanDeposit = true;
    public static boolean enable528ColtanSpawn = false;
    public static boolean enable528BosniaSimulator = true;
    public static boolean enable528NetherBurn = true;
    public static boolean enable528PressurizedRecipes = true;
    public static boolean enable528ExplosiveEnergistics = true;
    public static boolean enable528MachineGravity = true;
    public static int coltanRate = 2;

    public static boolean true528() {
        return enable528 && enable528ReasimBoilers && !enable528ColtanSpawn && enable528BosniaSimulator &&
                enable528NetherBurn && enable528PressurizedRecipes && enable528ExplosiveEnergistics &&
                enable528MachineGravity && coltanRate <= 2;
    }

    public static int bedrockRate = 50;
    public static boolean enableThreadedAtmospheres = true;

    public static boolean enableLBSM = false;
    public static boolean enableLBSMFullSchrab = true;
    public static boolean enableLBSMShorterDecay = true;
    public static boolean enableLBSMSimpleArmorRecipes = true;
    public static boolean enableLBSMSimpleToolRecipes = true;
    public static boolean enableLBSMSimpleAlloy = true;
    public static boolean enableLBSMSimpleChemsitry = true;
    public static boolean enableLBSMSimpleCentrifuge = true;
    public static boolean enableLBSMUnlockAnvil = true;
    public static boolean enableLBSMSimpleCrafting = true;
    public static boolean enableLBSMSimpleMedicineRecipes = true;
    public static boolean enableLBSMSafeCrates = true;
    public static boolean enableLBSMSafeMEDrives = true;
    public static boolean enableLBSMIGen = true;

    public static boolean enableBlockReplcement = false;
    public static boolean enableAdvancements = true;

    // ===== SpecValue =====
    private static ModConfigSpec.BooleanValue ENABLE_FLUID_CONTAINERS_V2;
    private static ModConfigSpec.DoubleValue CONVERSION_RATE_HE_TO_RF;
    private static ModConfigSpec.BooleanValue AUTO_CABLE_CONVERSION;
    private static ModConfigSpec.BooleanValue ENABLE_PACKET_THREADING;
    private static ModConfigSpec.IntValue PACKET_THREADING_CORE_COUNT;
    private static ModConfigSpec.IntValue PACKET_THREADING_MAX_COUNT;
    private static ModConfigSpec.BooleanValue PACKET_THREADING_ERROR_BYPASS;
    private static ModConfigSpec.BooleanValue ENABLE_ZERO_COPY_COMPATIBILITY_MODE;
    private static ModConfigSpec.BooleanValue ENABLE_THREADED_NODE_SPACE_UPDATE;
    private static ModConfigSpec.BooleanValue ENABLE_DEBUG_MODE;
    private static ModConfigSpec.BooleanValue ENABLE_DEBUG_WORLD_GEN;
    private static ModConfigSpec.BooleanValue ENABLE_SKYBOXES;
    private static ModConfigSpec.BooleanValue ENABLE_IMPACT_WORLD_PROVIDER;
    private static ModConfigSpec.BooleanValue ENABLE_KEYBIND_OVERLAP;
    private static ModConfigSpec.BooleanValue ENABLE_FLUID_CONTAINER_COMPAT;
    private static ModConfigSpec.ConfigValue<List<? extends String>> LEAD_SAFE_WHITELIST;
    private static ModConfigSpec.BooleanValue ENABLE_MYCELIUM;
    private static ModConfigSpec.BooleanValue ENABLE_PLUTONIUM_ORE;
    private static ModConfigSpec.BooleanValue ENABLE_DUNGEONS;
    private static ModConfigSpec.BooleanValue ENABLE_MD_ORES;
    private static ModConfigSpec.BooleanValue ENABLE_MINES;
    private static ModConfigSpec.BooleanValue ENABLE_RAD;
    private static ModConfigSpec.BooleanValue ENABLE_NITAN;
    private static ModConfigSpec.BooleanValue ENABLE_AUTO_CLEANUP;
    private static ModConfigSpec.BooleanValue ENABLE_BOMBER_SHORT_MODE;
    private static ModConfigSpec.BooleanValue ENABLE_VAULTS;
    private static ModConfigSpec.BooleanValue ENABLE_RADS;
    private static ModConfigSpec.BooleanValue ENABLE_COAL_GAS;
    private static ModConfigSpec.BooleanValue ENABLE_ASBESTOS_DUST;
    private static ModConfigSpec.BooleanValue ENABLE_RADON;
    private static ModConfigSpec.BooleanValue ENABLE_CARBON_MONOXIDE;
    private static ModConfigSpec.BooleanValue ENABLE_FLAMMABLE_GAS;
    private static ModConfigSpec.BooleanValue ENABLE_EXPLOSIVE_GAS;
    private static ModConfigSpec.BooleanValue ENABLE_MELTDOWN_GAS;
    private static ModConfigSpec.BooleanValue ADVANCED_RADIATION;
    private static ModConfigSpec.BooleanValue ENABLE_CATACLYSM;
    private static ModConfigSpec.BooleanValue ENABLE_EXTENDED_LOGGING;
    private static ModConfigSpec.BooleanValue ENABLE_GUNS;
    private static ModConfigSpec.BooleanValue SSG_ANIM;
    private static ModConfigSpec.BooleanValue ENABLE_VIRUS;
    private static ModConfigSpec.BooleanValue ENABLE_CROSSHAIRS;
    private static ModConfigSpec.BooleanValue ENABLE_REFLECTOR_COMPAT;
    private static ModConfigSpec.BooleanValue INSTANCED_PARTICLES;
    private static ModConfigSpec.BooleanValue CALL_LIST_MODELS;
    private static ModConfigSpec.BooleanValue USE_SHADERS2;
    private static ModConfigSpec.BooleanValue BLOOM;
    private static ModConfigSpec.BooleanValue HEAT_DISTORTION;
    private static ModConfigSpec.BooleanValue RECIPES;
    private static ModConfigSpec.BooleanValue JEI;
    private static ModConfigSpec.BooleanValue CHANGELOG;
    private static ModConfigSpec.BooleanValue REGISTER_TANKS;
    private static ModConfigSpec.BooleanValue DUCK_BUTTON;
    private static ModConfigSpec.BooleanValue ENABLE_MOTD;
    private static ModConfigSpec.BooleanValue ENABLE_GUIDE_BOOK;
    private static ModConfigSpec.BooleanValue DEPTH_EFFECTS;
    private static ModConfigSpec.BooleanValue FLASHLIGHT;
    private static ModConfigSpec.BooleanValue FLASHLIGHT_VOLUMETRIC;
    private static ModConfigSpec.BooleanValue BULLET_HOLE_NORMAL_MAPPING;
    private static ModConfigSpec.IntValue FLOWING_DECAL_AMOUNT_MAX;
    private static ModConfigSpec.BooleanValue BLOOD_FX;
    private static ModConfigSpec.IntValue DECO_TO_INGOT_RATE;
    private static ModConfigSpec.IntValue CRUCIBLE_MAX_CHARGES;
    private static ModConfigSpec.BooleanValue ENABLE_THREADED_ATMOSPHERES;
    private static ModConfigSpec.BooleanValue ENABLE_RE_EVAL;
    private static ModConfigSpec.BooleanValue ENABLE_SERVER_RECIPE_SYNC;
    private static ModConfigSpec.BooleanValue ENABLE_MACHINE_GRAVITY;
    private static ModConfigSpec.BooleanValue ENABLE_EXPENSIVE_MODE;
    private static ModConfigSpec.BooleanValue ENABLE_528;
    private static ModConfigSpec.BooleanValue ENABLE_528_REASIM_BOILERS;
    private static ModConfigSpec.BooleanValue ENABLE_528_COLTAN_DEPOSIT;
    private static ModConfigSpec.BooleanValue ENABLE_528_COLTAN_SPAWN;
    private static ModConfigSpec.BooleanValue ENABLE_528_BOSNIA_SIMULATOR;
    private static ModConfigSpec.BooleanValue ENABLE_528_NETHER_BURN;
    private static ModConfigSpec.BooleanValue ENABLE_528_PRESSURIZED_RECIPES;
    private static ModConfigSpec.BooleanValue ENABLE_528_EXPLOSIVE_ENERGISTICS;
    private static ModConfigSpec.BooleanValue ENABLE_528_MACHINE_GRAVITY;
    private static ModConfigSpec.IntValue COLTAN_RATE;
    private static ModConfigSpec.BooleanValue ENABLE_LBSM;
    private static ModConfigSpec.BooleanValue ENABLE_LBSM_FULL_SCHRAB;
    private static ModConfigSpec.BooleanValue ENABLE_LBSM_SHORTER_DECAY;
    private static ModConfigSpec.BooleanValue ENABLE_LBSM_SIMPLE_ARMOR_RECIPES;
    private static ModConfigSpec.BooleanValue ENABLE_LBSM_SIMPLE_TOOL_RECIPES;
    private static ModConfigSpec.BooleanValue ENABLE_LBSM_SIMPLE_ALLOY;
    private static ModConfigSpec.BooleanValue ENABLE_LBSM_SIMPLE_CHEMSITRY;
    private static ModConfigSpec.BooleanValue ENABLE_LBSM_SIMPLE_CENTRIFUGE;
    private static ModConfigSpec.BooleanValue ENABLE_LBSM_UNLOCK_ANVIL;
    private static ModConfigSpec.BooleanValue ENABLE_LBSM_SIMPLE_CRAFTING;
    private static ModConfigSpec.BooleanValue ENABLE_LBSM_SIMPLE_MEDICINE_RECIPES;
    private static ModConfigSpec.BooleanValue ENABLE_LBSM_SAFE_CRATES;
    private static ModConfigSpec.BooleanValue ENABLE_LBSM_SAFE_ME_DRIVES;
    private static ModConfigSpec.BooleanValue ENABLE_LBSM_I_GEN;
    private static ModConfigSpec.BooleanValue ENABLE_BLOCK_REPLCEMENT;
    private static ModConfigSpec.BooleanValue ENABLE_ADVANCEMENTS;

    public static void build(ModConfigSpec.Builder builder) {
        ENABLE_PACKET_THREADING = CommonConfig.configBool(builder, CommonConfig.CATEGORY_GENERAL, "0.01_enablePacketThreading", "Enables creation of a separate thread to increase packet processing speed on servers. Disable this if you are having anomalous crashes related to memory connections.", true);
        PACKET_THREADING_CORE_COUNT = CommonConfig.configInt(builder, CommonConfig.CATEGORY_GENERAL, "0.02_packetThreadingCoreCount", "Number of core threads to create for packets (recommended 1).", 1);
        PACKET_THREADING_MAX_COUNT = CommonConfig.configInt(builder, CommonConfig.CATEGORY_GENERAL, "0.03_packetThreadingMaxCount", "Maximum number of threads to create for packet threading. Must be greater than or equal to 0.02_packetThreadingCoreCount.", 2);
        PACKET_THREADING_ERROR_BYPASS = CommonConfig.configBool(builder, CommonConfig.CATEGORY_GENERAL, "0.04_packetThreadingErrorBypass", "Forces the bypassing of most packet threading errors, only enable this if directed to or if you know what you're doing.", false);
        ENABLE_SERVER_RECIPE_SYNC = CommonConfig.configBool(builder, CommonConfig.CATEGORY_GENERAL, "0.05_enableServerRecipeSync", "Syncs any recipes customised via JSON to clients connecting to the server.", true);
        ENABLE_ZERO_COPY_COMPATIBILITY_MODE = CommonConfig.configBool(builder, CommonConfig.CATEGORY_GENERAL, "0.06_enableZeroCopyCompatibilityMode", "Routes non-NTM packets back through Forge's default networking path so mods with broken ByteBuf reference counting do not touch NTM's zero-copy hook.", false);
        ENABLE_THREADED_NODE_SPACE_UPDATE = CommonConfig.configBool(builder, CommonConfig.CATEGORY_GENERAL, "0.07_enableThreadedNodeSpaceUpdate", "Enables threaded updating of the nodespace. This can improve performance, but may cause issues with certain mods.", true);
        ENABLE_BLOCK_REPLCEMENT = CommonConfig.configBool(builder, CommonConfig.CATEGORY_GENERAL, "0.99_CE_01_enableBlockAutoReplacing", "Enables automatic block replacement for missing blocks to avoid giant holes in the ground when they got removed. This may severely impact chunkloading performance,\nonly enable when you are sure that we removed some blocks AND we added that to this replacement system AND you are absolutely sure about what you are doing.\nCurrently only works for hbm:waste_*.", false);
        ENABLE_ADVANCEMENTS = CommonConfig.configBool(builder, CommonConfig.CATEGORY_GENERAL, "0.99_CE_02_enableAdvancements", "Set to false to disable all NTM advancements.", true);
        ENABLE_DEBUG_MODE = CommonConfig.configBool(builder, CommonConfig.CATEGORY_GENERAL, "1.00_enableDebugMode", "Enable debugging mode", false);
        ENABLE_DEBUG_WORLD_GEN = CommonConfig.configBool(builder, CommonConfig.CATEGORY_GENERAL, "1.00_enableDebugWorldGen", "Enable debugging mode for phased structure generation. Separate from the previous option!", false);
        ENABLE_SKYBOXES = CommonConfig.configBool(builder, CommonConfig.CATEGORY_GENERAL, "1.00_enableSkybox", "If enabled, will try to use NTM's custom skyboxes.", true);
        ENABLE_MYCELIUM = CommonConfig.configBool(builder, CommonConfig.CATEGORY_GENERAL, "1.01_enableMyceliumSpread", "Allows glowing mycelium to spread", false);
        ENABLE_PLUTONIUM_ORE = CommonConfig.configBool(builder, CommonConfig.CATEGORY_GENERAL, "1.02_enablePlutoniumNetherOre", "Enables plutonium ore generation in the nether", false);
        ENABLE_DUNGEONS = CommonConfig.configBool(builder, CommonConfig.CATEGORY_GENERAL, "1.03_enableDungeonSpawn", "Allows structures and dungeons to spawn.", true);
        ENABLE_MD_ORES = CommonConfig.configBool(builder, CommonConfig.CATEGORY_GENERAL, "1.04_enableOresInModdedDimensions", "Allows NTM ores to generate in modded dimensions", true);
        ENABLE_MINES = CommonConfig.configBool(builder, CommonConfig.CATEGORY_GENERAL, "1.05_enableLandmineSpawn", "Allows landmines to generate", true);
        ENABLE_RAD = CommonConfig.configBool(builder, CommonConfig.CATEGORY_GENERAL, "1.06_enableRadHotspotSpawn", "Allows radiation hotspots to generate", true);
        ENABLE_NITAN = CommonConfig.configBool(builder, CommonConfig.CATEGORY_GENERAL, "1.07_enableNITANChestSpawn", "Allows chests to spawn at specific coordinates full of powders", true);
        ENABLE_AUTO_CLEANUP = CommonConfig.configBool(builder, CommonConfig.CATEGORY_GENERAL, "1.09_enableAutomaticRadCleanup", "Allows for waste earth blocks (dirt, grass, mycellium) to turn back into dirt immediately.", false);
        ENABLE_BOMBER_SHORT_MODE = CommonConfig.configBool(builder, CommonConfig.CATEGORY_GENERAL, "1.14_enableBomberShortMode", "Has bomber planes spawn in closer to the target for use with smaller render distances", false);
        ENABLE_VAULTS = CommonConfig.configBool(builder, CommonConfig.CATEGORY_GENERAL, "1.15_enableVaultSpawn", "Allows locked safes to spawn", true);
        ENABLE_RADS = CommonConfig.configBool(builder, CommonConfig.CATEGORY_GENERAL, "1.16_enableRadiation", "GENERAL SWITCH: Enables radiation system", true);
        ENABLE_CATACLYSM = CommonConfig.configBool(builder, CommonConfig.CATEGORY_GENERAL, "1.17_enableCataclysm", "Causes satellites to fall whenever a mob dies", false);
        ENABLE_EXTENDED_LOGGING = CommonConfig.configBool(builder, CommonConfig.CATEGORY_GENERAL, "1.18_enableExtendedLogging", "Logs uses of the detonator, nuclear explosions, missile launches, grenades, etc.", false);
        ENABLE_GUNS = CommonConfig.configBool(builder, CommonConfig.CATEGORY_GENERAL, "1.20_enableGuns", "Prevents new system guns to be fired", true);
        ENABLE_VIRUS = CommonConfig.configBool(builder, CommonConfig.CATEGORY_GENERAL, "1.21_enableVirus", "Allows virus blocks to spread", false);
        ENABLE_CROSSHAIRS = CommonConfig.configBool(builder, CommonConfig.CATEGORY_GENERAL, "1.22_enableCrosshairs", "Shows custom crosshairs when an NTM gun is being held", true);
        USE_SHADERS2 = CommonConfig.configBool(builder, CommonConfig.CATEGORY_GENERAL, "1.23_enableShaders2", "Enables old NTM Reloaded shaders, courtesy of Drillgon. NOT RECOMMENDED TO TURN IT ON", false);
        SSG_ANIM = CommonConfig.configBool(builder, CommonConfig.CATEGORY_GENERAL, "1.24_ssgAnimType", "Which supershotgun reload animation to use. True is Drillgon's animation, false is Bob's animation", true);
        ENABLE_REFLECTOR_COMPAT = CommonConfig.configBool(builder, CommonConfig.CATEGORY_GENERAL, "1.24_enableReflectorCompat", "Enable old reflector oredict name (\"plateDenseLead\") instead of new \"plateTungCar\"", false);
        INSTANCED_PARTICLES = CommonConfig.configBool(builder, CommonConfig.CATEGORY_GENERAL, "1.25_instancedParticles", "Enables instanced particle rendering for supported particles, including Torex cloudlets and RBMK particles, which makes them render several times faster. May not work on all computers, and will break with shaders.", true);
        DEPTH_EFFECTS = CommonConfig.configBool(builder, CommonConfig.CATEGORY_GENERAL, "1.25_depthBufferEffects", "Enables effects that make use of reading from the depth buffer", true);
        FLASHLIGHT = CommonConfig.configBool(builder, CommonConfig.CATEGORY_GENERAL, "1.25_flashlights", "Enables dynamic directional lights", true);
        FLASHLIGHT_VOLUMETRIC = CommonConfig.configBool(builder, CommonConfig.CATEGORY_GENERAL, "1.25_flashlight_volumetrics", "Enables volumetric lighting for directional lights", true);
        BULLET_HOLE_NORMAL_MAPPING = CommonConfig.configBool(builder, CommonConfig.CATEGORY_GENERAL, "1.25_bullet_hole_normal_mapping", "Enables normal mapping on bullet holes, which can improve visuals", true);
        FLOWING_DECAL_AMOUNT_MAX = CommonConfig.configInt(builder, CommonConfig.CATEGORY_GENERAL, "1.25_flowing_decal_max", "The maximum number of 'flowing' decals that can exist at once (eg blood that can flow down walls)", 20);
        CALL_LIST_MODELS = CommonConfig.configBool(builder, CommonConfig.CATEGORY_GENERAL, "1.26_callListModels", "Enables call lists for a few models, making them render extremely fast", true);
        ENABLE_COAL_GAS = CommonConfig.configBool(builder, CommonConfig.CATEGORY_GENERAL, "1.26_enableCoalDust", "Allows the coal gas to spawn (e.g. after breaking coal ore).", true);
        ENABLE_ASBESTOS_DUST = CommonConfig.configBool(builder, CommonConfig.CATEGORY_GENERAL, "1.26_enableAsbestosDust", "Allows the asbestos gas to spawn (e.g. after breaking asbestos ore or chrysotile).", true);
        ENABLE_RADON = CommonConfig.configBool(builder, CommonConfig.CATEGORY_GENERAL, "1.26_enableRadonGas", "Allows the radon gas to spawn (e.g. after breaking uranium ore).", true);
        ENABLE_CARBON_MONOXIDE = CommonConfig.configBool(builder, CommonConfig.CATEGORY_GENERAL, "1.26_enableCarbonMonoxide", "Allows the carbon monoxide gas to spawn (e.g. after breaking nether coal ore).", true);
        ENABLE_FLAMMABLE_GAS = CommonConfig.configBool(builder, CommonConfig.CATEGORY_GENERAL, "1.26_enableFlammableGas", "Allows the flammable gas to spawn in the world.", true);
        ENABLE_EXPLOSIVE_GAS = CommonConfig.configBool(builder, CommonConfig.CATEGORY_GENERAL, "1.26_enableExplosiveGas", "Allows the explosive gas to spawn in the world.", true);
        ENABLE_MELTDOWN_GAS = CommonConfig.configBool(builder, CommonConfig.CATEGORY_GENERAL, "1.26_enableMeltdownGas", "Allows the meltdown gas to spawn (e.g. after ZIRNOX explosion).", true);
        ENABLE_RE_EVAL = CommonConfig.configBool(builder, CommonConfig.CATEGORY_GENERAL, "1.27_enableReEval", "Allows re-evaluating power networks on link remove instead of destroying and recreating", true);
        RECIPES = CommonConfig.configBool(builder, CommonConfig.CATEGORY_GENERAL, "1.28_enableRecipes", "A general switch for ALL crafting table/smelting recipes. If set to false, all recipes will be disabled.", true);
        REGISTER_TANKS = CommonConfig.configBool(builder, CommonConfig.CATEGORY_GENERAL, "1.28_registerTanks", "A general switch for ALL the tanks items in the mod (e.g. universal fluid, lead, barrels, packed containers). If set to false, they won't be registered as items in the game.", true);
        JEI = CommonConfig.configBool(builder, CommonConfig.CATEGORY_GENERAL, "1.28_enableJei", "Enables JEI compatibility", true);
        CHANGELOG = CommonConfig.configBool(builder, CommonConfig.CATEGORY_GENERAL, "1.28_enableChangelog", "Enables the update notification in the chat. NOT USED FOR NOW", true);
        DUCK_BUTTON = CommonConfig.configBool(builder, CommonConfig.CATEGORY_GENERAL, "1.28_enableDuckButton", "Allows you to summon the duck via pressing O", true);
        BLOOM = CommonConfig.configBool(builder, CommonConfig.CATEGORY_GENERAL, "1.30_enableBloom", "Enables the bloom effect which can be visible on the Crucible. Only active if enableShaders2 is set to true.", true);
        HEAT_DISTORTION = CommonConfig.configBool(builder, CommonConfig.CATEGORY_GENERAL, "1.30_enableHeatDistortion", "Enables the heat distortion effect. Only active if enableShaders2 is set to true.", true);
        ADVANCED_RADIATION = CommonConfig.configBool(builder, CommonConfig.CATEGORY_GENERAL, "1.31_enableAdvancedRadiation", "Enables a 3 dimensional version of the radiation system that also allows some blocks (like concrete bricks) to stop it from spreading", true);
        ENABLE_IMPACT_WORLD_PROVIDER = CommonConfig.configBool(builder, CommonConfig.CATEGORY_GENERAL, "1.32_enableImpactWorldProvider", "If enabled, registers a custom overworld provider which modifies lighting and sky colors for post-impact effects.", true);
        BLOOD_FX = CommonConfig.configBool(builder, CommonConfig.CATEGORY_GENERAL, "1.32_enable_blood_effects", "Enables the over-the-top blood visual effects for some weapons", true);
        CRUCIBLE_MAX_CHARGES = CommonConfig.configInt(builder, CommonConfig.CATEGORY_GENERAL, "1.33_crucible_max_charges", "How many times you can use the crucible before recharge", 16);
        CONVERSION_RATE_HE_TO_RF = CommonConfig.configDouble(builder, CommonConfig.CATEGORY_GENERAL, "1.35_conversionRateHeToRF", "One HE is (insert number) RF - <number> (double)", 1.0D);
        AUTO_CABLE_CONVERSION = CommonConfig.configBool(builder, CommonConfig.CATEGORY_GENERAL, "1.35.1_autoCableConversion", "If enabled, NTM cables will automatically convert FE <-> HE. Note: WILL MAKE ALL OTHER MODS' CABLES USELESS", true);
        ENABLE_MOTD = CommonConfig.configBool(builder, CommonConfig.CATEGORY_GENERAL, "1.36_enableMOTD", "If enabled, shows the 'Loaded mod!' chat message as well as update notifications when joining a world", true);
        ENABLE_FLUID_CONTAINER_COMPAT = CommonConfig.configBool(builder, CommonConfig.CATEGORY_GENERAL, "1.37_enableFluidContainerCompat", "If enabled, fluid containers will be oredicted and interchangable in recipes with other mods' containers. Should probably work with things like IE's/GC oil properly.", true);
        ENABLE_GUIDE_BOOK = CommonConfig.configBool(builder, CommonConfig.CATEGORY_GENERAL, "1.38_enableGuideBook", "If enabled, gives players the guide book when joining the world for the first time", true);
        DECO_TO_INGOT_RATE = CommonConfig.configInt(builder, CommonConfig.CATEGORY_GENERAL, "1.39_decoToIngotConversionRate", "Chance of successful turning a deco block into an ingot. Default is 25%", 25);
        ENABLE_THREADED_ATMOSPHERES = CommonConfig.configBool(builder, CommonConfig.CATEGORY_GENERAL, "1.40_threadedAtmospheres", "If enabled, will run atmosphere blobbing in a separate thread for performance", true);
        ENABLE_KEYBIND_OVERLAP = CommonConfig.configBool(builder, CommonConfig.CATEGORY_GENERAL, "1.42_enableKeybindOverlap", "If enabled, will handle keybinds that would otherwise be ignored due to overlapping.", true);
        ENABLE_MACHINE_GRAVITY = CommonConfig.configBool(builder, CommonConfig.CATEGORY_GENERAL, "1.44_enableMachineGravity", "Requires large large machines to have a proper foundation, or else they tilt and break. Independent from the 528 version of this config, which does the same, but only works with 528 enabled.", false);
        ENABLE_FLUID_CONTAINERS_V2 = CommonConfig.configBool(builder, CommonConfig.CATEGORY_GENERAL, "1.99_CE_enableFluidContainersV2", "If enabled, 3 new enhanced version of base fluid barrels that supports partial fill and drain are added.", false);
        LEAD_SAFE_WHITELIST = CommonConfig.configStringList(builder, CommonConfig.CATEGORY_GENERAL, "1.99_CE_forgeFluidLeadSafeContainers", "Exact generic Forge Fluid containers that should be treated as lead-safe. Entries must use the format modid:item:meta. Default empty means generic Forge Fluid containers are not lead-safe.", new String[]{});
        ENABLE_EXPENSIVE_MODE = CommonConfig.configBool(builder, CommonConfig.CATEGORY_GENERAL, "1.99_enableExpensiveMode", "It does what the name implies.", false);

        ENABLE_528 = CommonConfig.configBool(builder, CommonConfig.CATEGORY_528, "enable528Mode", "The central toggle for 528 mode.", false);
        ENABLE_528_REASIM_BOILERS = CommonConfig.configBool(builder, CommonConfig.CATEGORY_528, "X528_forceReasimBoilers", "Keeps the RBMK dial for ReaSim boilers on, preventing use of non-ReaSim boiler columns and forcing the use of steam in-/outlets", true);
        ENABLE_528_COLTAN_DEPOSIT = CommonConfig.configBool(builder, CommonConfig.CATEGORY_528, "X528_enableColtanDepsoit", "Enables the coltan deposit. A large amount of coltan will spawn around a single random location in the world.", true);
        ENABLE_528_COLTAN_SPAWN = CommonConfig.configBool(builder, CommonConfig.CATEGORY_528, "X528_enableColtanSpawning", "Enables coltan ore as a random spawn in the world. Unlike the deposit option, coltan will not just spawn in one central location.", false);
        ENABLE_528_BOSNIA_SIMULATOR = CommonConfig.configBool(builder, CommonConfig.CATEGORY_528, "X528_enableBosniaSimulator", "Enables anti tank mines spawning all over the world.", true);
        ENABLE_528_NETHER_BURN = CommonConfig.configBool(builder, CommonConfig.CATEGORY_528, "X528_enable528NetherBurn", "Whether players burn in the nether", true);
        ENABLE_528_PRESSURIZED_RECIPES = CommonConfig.configBool(builder, CommonConfig.CATEGORY_528, "X528_enable528PressurizedRecipes", "Sets some recipes to require pressurized input fluid", true);
        ENABLE_528_EXPLOSIVE_ENERGISTICS = CommonConfig.configBool(builder, CommonConfig.CATEGORY_528, "X528_enable528ExplosiveEnergistics", "Renders AE2 unusable.", true);
        ENABLE_528_MACHINE_GRAVITY = CommonConfig.configBool(builder, CommonConfig.CATEGORY_528, "X528_enable528MachineGravity", "Requires most large machines to have a proper foundation, or else they tilt and break.", true);
        COLTAN_RATE = CommonConfig.configInt(builder, CommonConfig.CATEGORY_528, "X528_oreColtanFrequency", "Determines how many coltan ore veins are to be expected in a chunk. These values do not affect the frequency in deposits, and only apply if random coltan spanwing is enabled.", 2);

        ENABLE_LBSM = CommonConfig.configBool(builder, CommonConfig.CATEGORY_LBSM, "enableLessBullshitMode", "The central toggle for LBS mode. Forced OFF when 528 is enabled!", false);
        ENABLE_LBSM_FULL_SCHRAB = CommonConfig.configBool(builder, CommonConfig.CATEGORY_LBSM, "LBSM_fullSchrab", "When enabled, this will replace schraranium with full schrabidium ingots in the transmutator's output", true);
        ENABLE_LBSM_SHORTER_DECAY = CommonConfig.configBool(builder, CommonConfig.CATEGORY_LBSM, "LBSM_shortDecay", "When enabled, this will highly accelerate the speed at which nuclear waste disposal drums decay their contents. 60x faster than 528 mode and 5-12x faster than on normal mode.", true);
        ENABLE_LBSM_SIMPLE_ARMOR_RECIPES = CommonConfig.configBool(builder, CommonConfig.CATEGORY_LBSM, "LBSM_recipeSimpleArmor", "When enabled, simplifies the recipe for armor sets like starmetal or schrabidium.", true);
        ENABLE_LBSM_SIMPLE_TOOL_RECIPES = CommonConfig.configBool(builder, CommonConfig.CATEGORY_LBSM, "LBSM_recipeSimpleTool", "When enabled, simplifies the recipe for tool sets like starmetal or scrhabidium", true);
        ENABLE_LBSM_SIMPLE_ALLOY = CommonConfig.configBool(builder, CommonConfig.CATEGORY_LBSM, "LBSM_recipeSimpleAlloy", "When enabled, adds some blast furnace recipes to make certain things cheaper", true);
        ENABLE_LBSM_SIMPLE_CHEMSITRY = CommonConfig.configBool(builder, CommonConfig.CATEGORY_LBSM, "LBSM_recipeSimpleChemistry", "When enabled, simplifies some chemical plant recipes", true);
        ENABLE_LBSM_SIMPLE_CENTRIFUGE = CommonConfig.configBool(builder, CommonConfig.CATEGORY_LBSM, "LBSM_recipeSimpleCentrifuge", "When enabled, enhances centrifuge outputs to make rare materials more common", true);
        ENABLE_LBSM_UNLOCK_ANVIL = CommonConfig.configBool(builder, CommonConfig.CATEGORY_LBSM, "LBSM_recipeUnlockAnvil", "When enabled, all anvil recipes are available at tier 1", true);
        ENABLE_LBSM_SIMPLE_CRAFTING = CommonConfig.configBool(builder, CommonConfig.CATEGORY_LBSM, "LBSM_recipeSimpleCrafting", "When enabled, some uncraftable or more expansive items get simple crafting recipes. Scorched uranium also becomes washable", true);
        ENABLE_LBSM_SIMPLE_MEDICINE_RECIPES = CommonConfig.configBool(builder, CommonConfig.CATEGORY_LBSM, "LBSM_recipeSimpleMedicine", "When enabled, makes some medicine recipes (like ones that require bismuth) much more affordable", true);
        ENABLE_LBSM_SAFE_CRATES = CommonConfig.configBool(builder, CommonConfig.CATEGORY_LBSM, "LBSM_safeCrates", "When enabled, prevents crates from becoming radioactive", true);
        ENABLE_LBSM_SAFE_ME_DRIVES = CommonConfig.configBool(builder, CommonConfig.CATEGORY_LBSM, "LBSM_safeMEDrives", "When enabled, prevents ME Drives and Portable Cells from becoming radioactive", true);
        ENABLE_LBSM_I_GEN = CommonConfig.configBool(builder, CommonConfig.CATEGORY_LBSM, "LBSM_iGen", "When enabled, restores the industrial generator to pre-nerf power", true);
    }

    public static void load() {
        enablePacketThreading = ENABLE_PACKET_THREADING.get();
        packetThreadingCoreCount = PACKET_THREADING_CORE_COUNT.get();
        packetThreadingMaxCount = PACKET_THREADING_MAX_COUNT.get();
        packetThreadingErrorBypass = PACKET_THREADING_ERROR_BYPASS.get();
        enableServerRecipeSync = ENABLE_SERVER_RECIPE_SYNC.get();
        enableZeroCopyCompatibilityMode = ENABLE_ZERO_COPY_COMPATIBILITY_MODE.get();
        enableThreadedNodeSpaceUpdate = ENABLE_THREADED_NODE_SPACE_UPDATE.get();
        enableBlockReplcement = ENABLE_BLOCK_REPLCEMENT.get();
        enableAdvancements = ENABLE_ADVANCEMENTS.get();
        enableDebugMode = ENABLE_DEBUG_MODE.get();
        enableDebugWorldGen = ENABLE_DEBUG_WORLD_GEN.get();
        enableSkyboxes = ENABLE_SKYBOXES.get();
        enableMycelium = ENABLE_MYCELIUM.get();
        enablePlutoniumOre = ENABLE_PLUTONIUM_ORE.get();
        enableDungeons = ENABLE_DUNGEONS.get();
        enableMDOres = ENABLE_MD_ORES.get();
        enableMines = ENABLE_MINES.get();
        enableRad = ENABLE_RAD.get();
        enableNITAN = ENABLE_NITAN.get();
        enableAutoCleanup = ENABLE_AUTO_CLEANUP.get();
        enableBomberShortMode = ENABLE_BOMBER_SHORT_MODE.get();
        enableVaults = ENABLE_VAULTS.get();
        enableRads = ENABLE_RADS.get();
        enableCataclysm = ENABLE_CATACLYSM.get();
        enableExtendedLogging = ENABLE_EXTENDED_LOGGING.get();
        enableGuns = ENABLE_GUNS.get();
        enableVirus = ENABLE_VIRUS.get();
        enableCrosshairs = ENABLE_CROSSHAIRS.get();
        useShaders2 = USE_SHADERS2.get();
        ssgAnim = SSG_ANIM.get();
        enableReflectorCompat = ENABLE_REFLECTOR_COMPAT.get();
        instancedParticles = INSTANCED_PARTICLES.get();
        depthEffects = DEPTH_EFFECTS.get();
        flashlight = FLASHLIGHT.get();
        flashlightVolumetric = FLASHLIGHT_VOLUMETRIC.get();
        bulletHoleNormalMapping = BULLET_HOLE_NORMAL_MAPPING.get();
        flowingDecalAmountMax = FLOWING_DECAL_AMOUNT_MAX.get();
        callListModels = CALL_LIST_MODELS.get();
        enableCoalGas = ENABLE_COAL_GAS.get();
        enableAsbestosDust = ENABLE_ASBESTOS_DUST.get();
        enableRadon = ENABLE_RADON.get();
        enableCarbonMonoxide = ENABLE_CARBON_MONOXIDE.get();
        enableFlammableGas = ENABLE_FLAMMABLE_GAS.get();
        enableExplosiveGas = ENABLE_EXPLOSIVE_GAS.get();
        enableMeltdownGas = ENABLE_MELTDOWN_GAS.get();
        enableReEval = ENABLE_RE_EVAL.get();
        recipes = RECIPES.get();
        registerTanks = REGISTER_TANKS.get();
        jei = JEI.get();
        changelog = CHANGELOG.get();
        duckButton = DUCK_BUTTON.get();
        bloom = BLOOM.get();
        heatDistortion = HEAT_DISTORTION.get();
        advancedRadiation = ADVANCED_RADIATION.get();
        enableImpactWorldProvider = ENABLE_IMPACT_WORLD_PROVIDER.get();
        bloodFX = BLOOD_FX.get();

        // ===== 特殊逻辑 1：GL 3.3 检查（原 FMLCommonHandler 侧判定 + GLCompat.error） =====
        boolean clientSide = FMLEnvironment.dist.isClient();
        // TODO P8: 原 advancedRenderingSupported = GLCompat.error.isEmpty();（com.hbm.render.GLCompat）
        //           P8 渲染系统迁移时实现 OpenGL 3.3 检测
        boolean advancedRenderingSupported = true;

        if (instancedParticles && !advancedRenderingSupported) {
            if (clientSide) {
                HBM.LOGGER.error("Warning - Open GL 3.3 not supported! Disabling instanced particles...");
            }
            instancedParticles = false;
        }
        if ((depthEffects || flowingDecalAmountMax > 0 || bloodFX || bloom || heatDistortion) && (!advancedRenderingSupported || !useShaders2)) {
            if (clientSide && !advancedRenderingSupported) {
                HBM.LOGGER.error("Warning - Open GL 3.3 not supported! Disabling shader-driven effects...");
            }
            if (!useShaders2) {
                HBM.LOGGER.error("Shader effects manually disabled");
            }
            depthEffects = false;
            flowingDecalAmountMax = 0;
            bloodFX = false;
            useShaders2 = false;
            bloom = false;
            heatDistortion = false;
        }
        if (!depthEffects) {
            flashlight = false;
            bulletHoleNormalMapping = false;
        }
        if (!flashlight) {
            flashlightVolumetric = false;
        }

        crucibleMaxCharges = CRUCIBLE_MAX_CHARGES.get();
        if (crucibleMaxCharges <= 0) {
            crucibleMaxCharges = 16;
        }

        conversionRateHeToRF = CONVERSION_RATE_HE_TO_RF.get();
        autoCableConversion = AUTO_CABLE_CONVERSION.get();
        enableMOTD = ENABLE_MOTD.get();
        enableFluidContainerCompat = ENABLE_FLUID_CONTAINER_COMPAT.get();
        enableGuideBook = ENABLE_GUIDE_BOOK.get();
        decoToIngotRate = DECO_TO_INGOT_RATE.get();
        enableThreadedAtmospheres = ENABLE_THREADED_ATMOSPHERES.get();
        enableKeybindOverlap = ENABLE_KEYBIND_OVERLAP.get();
        enableMachineGravity = ENABLE_MACHINE_GRAVITY.get();
        enableFluidContainersV2 = ENABLE_FLUID_CONTAINERS_V2.get();
        leadSafeForgeContainerWhitelist = loadLeadSafeForgeContainerWhitelist();
        enableExpensiveMode = ENABLE_EXPENSIVE_MODE.get();

        enable528 = ENABLE_528.get();
        enable528ReasimBoilers = ENABLE_528_REASIM_BOILERS.get();
        enable528ColtanDeposit = ENABLE_528_COLTAN_DEPOSIT.get();
        enable528ColtanSpawn = ENABLE_528_COLTAN_SPAWN.get();
        enable528BosniaSimulator = ENABLE_528_BOSNIA_SIMULATOR.get();
        enable528NetherBurn = ENABLE_528_NETHER_BURN.get();
        enable528PressurizedRecipes = ENABLE_528_PRESSURIZED_RECIPES.get();
        enable528ExplosiveEnergistics = ENABLE_528_EXPLOSIVE_ENERGISTICS.get();
        enable528MachineGravity = ENABLE_528_MACHINE_GRAVITY.get();
        coltanRate = COLTAN_RATE.get();

        enableLBSM = ENABLE_LBSM.get();
        enableLBSMFullSchrab = ENABLE_LBSM_FULL_SCHRAB.get();
        enableLBSMShorterDecay = ENABLE_LBSM_SHORTER_DECAY.get();
        enableLBSMSimpleArmorRecipes = ENABLE_LBSM_SIMPLE_ARMOR_RECIPES.get();
        enableLBSMSimpleToolRecipes = ENABLE_LBSM_SIMPLE_TOOL_RECIPES.get();
        enableLBSMSimpleAlloy = ENABLE_LBSM_SIMPLE_ALLOY.get();
        enableLBSMSimpleChemsitry = ENABLE_LBSM_SIMPLE_CHEMSITRY.get();
        enableLBSMSimpleCentrifuge = ENABLE_LBSM_SIMPLE_CENTRIFUGE.get();
        enableLBSMUnlockAnvil = ENABLE_LBSM_UNLOCK_ANVIL.get();
        enableLBSMSimpleCrafting = ENABLE_LBSM_SIMPLE_CRAFTING.get();
        enableLBSMSimpleMedicineRecipes = ENABLE_LBSM_SIMPLE_MEDICINE_RECIPES.get();
        enableLBSMSafeCrates = ENABLE_LBSM_SAFE_CRATES.get();
        enableLBSMSafeMEDrives = ENABLE_LBSM_SAFE_ME_DRIVES.get();
        enableLBSMIGen = ENABLE_LBSM_I_GEN.get();

        // ===== 特殊逻辑 2：528 ↔ LBSM 联动 =====
        if (enable528) enableLBSM = false;
        if (!enable528) {
            enable528ReasimBoilers = false;
            enable528BosniaSimulator = false;
            enable528NetherBurn = false;
            enable528PressurizedRecipes = false;
            enable528ExplosiveEnergistics = false;
            enable528MachineGravity = false;
        }
    }

    private static Set<String> loadLeadSafeForgeContainerWhitelist() {
        List<? extends String> entries = LEAD_SAFE_WHITELIST.get();
        Set<String> result = new ObjectOpenHashSet<>(entries.size());
        for (String entry : entries) {
            result.add(normalizeLeadSafeForgeContainerEntry(entry));
        }
        return result;
    }

    private static String normalizeLeadSafeForgeContainerEntry(String entry) {
        String trimmed = entry.trim();
        int split = trimmed.lastIndexOf(':');
        if (split <= 0 || split == trimmed.length() - 1) {
            throw new IllegalArgumentException("Invalid forge fluid lead-safe container override '" + entry + "'. Expected modid:item:meta.");
        }
        ResourceLocation itemId = ResourceLocation.parse(trimmed.substring(0, split));   // 原 new ResourceLocation(...)
        int meta;
        try {
            meta = Integer.parseInt(trimmed.substring(split + 1));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid forge fluid lead-safe container override '" + entry + "'. Meta must be an integer.", e);
        }
        if (meta < 0) {
            throw new IllegalArgumentException("Invalid forge fluid lead-safe container override '" + entry + "'. Meta must be >= 0.");
        }
        return itemId + ":" + meta;
    }
}
