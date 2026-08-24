package com.hbm.config;

import net.neoforged.neoforge.common.ModConfigSpec;

/**
 * 迁移自 1.12.2 com.hbm.config.ToolConfig。
 * 键名与原 .cfg 一致（注意原文件 recursionDepth 等键名重复的写法原样保留）。
 */
public class ToolConfig {

    public static int recursionDepth = 500;
    public static boolean recursiveStone = true;
    public static boolean recursiveNetherrack = true;

    public static boolean abilityHammer = true;
    public static boolean abilityVein = true;
    public static boolean abilityLuck = true;
    public static boolean abilitySilk = true;
    public static boolean abilityFurnace = true;
    public static boolean abilityShredder = true;
    public static boolean abilityCentrifuge = true;
    public static boolean abilityCrystallizer = true;
    public static boolean abilityMercury = true;
    public static boolean abilityExplosion = true;

    private static ModConfigSpec.IntValue RECURSION_DEPTH;
    private static ModConfigSpec.BooleanValue RECURSIVE_STONE;
    private static ModConfigSpec.BooleanValue RECURSIVE_NETHERRACK;
    private static ModConfigSpec.BooleanValue ABILITY_HAMMER;
    private static ModConfigSpec.BooleanValue ABILITY_VEIN;
    private static ModConfigSpec.BooleanValue ABILITY_LUCK;
    private static ModConfigSpec.BooleanValue ABILITY_SILK;
    private static ModConfigSpec.BooleanValue ABILITY_FURNACE;
    private static ModConfigSpec.BooleanValue ABILITY_SHREDDER;
    private static ModConfigSpec.BooleanValue ABILITY_CENTRIFUGE;
    private static ModConfigSpec.BooleanValue ABILITY_CRYSTALLIZER;
    private static ModConfigSpec.BooleanValue ABILITY_MERCURY;
    private static ModConfigSpec.BooleanValue ABILITY_EXPLOSION;

    public static void build(ModConfigSpec.Builder builder) {
        RECURSION_DEPTH = CommonConfig.configInt(builder, CommonConfig.CATEGORY_TOOLS, "11.00_recursionDepth", "Limits veinminer's recursive function. Usually not an issue, unless you're using bukkit which is especially sensitive for some reason.", 1000);
        RECURSIVE_STONE = CommonConfig.configBool(builder, CommonConfig.CATEGORY_TOOLS, "11.01_recursionDepth", "Determines whether veinminer can break stone", false);
        RECURSIVE_NETHERRACK = CommonConfig.configBool(builder, CommonConfig.CATEGORY_TOOLS, "11.02_recursionDepth", "Determines whether veinminer can break netherrack", false);
        ABILITY_HAMMER = CommonConfig.configBool(builder, CommonConfig.CATEGORY_TOOLS, "11.03_hammerAbility", "Allows AoE ability", true);
        ABILITY_VEIN = CommonConfig.configBool(builder, CommonConfig.CATEGORY_TOOLS, "11.04_abilityVein", "Allows veinminer ability", true);
        ABILITY_LUCK = CommonConfig.configBool(builder, CommonConfig.CATEGORY_TOOLS, "11.05_abilityLuck", "Allow luck (fortune) ability", true);
        ABILITY_SILK = CommonConfig.configBool(builder, CommonConfig.CATEGORY_TOOLS, "11.06_abilitySilk", "Allow silk touch ability", true);
        ABILITY_FURNACE = CommonConfig.configBool(builder, CommonConfig.CATEGORY_TOOLS, "11.07_abilityFurnace", "Allow auto-smelter ability", true);
        ABILITY_SHREDDER = CommonConfig.configBool(builder, CommonConfig.CATEGORY_TOOLS, "11.08_abilityShredder", "Allow auto-shredder ability", true);
        ABILITY_CENTRIFUGE = CommonConfig.configBool(builder, CommonConfig.CATEGORY_TOOLS, "11.09_abilityCentrifuge", "Allow auto-centrifuge ability", true);
        ABILITY_CRYSTALLIZER = CommonConfig.configBool(builder, CommonConfig.CATEGORY_TOOLS, "11.10_abilityCrystallizer", "Allow auto-crystallizer ability", true);
        ABILITY_MERCURY = CommonConfig.configBool(builder, CommonConfig.CATEGORY_TOOLS, "11.11_abilityMercury", "Allow mercury touch ability (digging redstone gives mercury)", true);
        ABILITY_EXPLOSION = CommonConfig.configBool(builder, CommonConfig.CATEGORY_TOOLS, "11.12_abilityExplosion", "Allow explosion ability", true);
    }

    public static void load() {
        recursionDepth = RECURSION_DEPTH.get();
        recursiveStone = RECURSIVE_STONE.get();
        recursiveNetherrack = RECURSIVE_NETHERRACK.get();
        abilityHammer = ABILITY_HAMMER.get();
        abilityVein = ABILITY_VEIN.get();
        abilityLuck = ABILITY_LUCK.get();
        abilitySilk = ABILITY_SILK.get();
        abilityFurnace = ABILITY_FURNACE.get();
        abilityShredder = ABILITY_SHREDDER.get();
        abilityCentrifuge = ABILITY_CENTRIFUGE.get();
        abilityCrystallizer = ABILITY_CRYSTALLIZER.get();
        abilityMercury = ABILITY_MERCURY.get();
        abilityExplosion = ABILITY_EXPLOSION.get();
    }
}
