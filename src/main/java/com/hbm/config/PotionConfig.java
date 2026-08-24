package com.hbm.config;

import net.neoforged.neoforge.common.ModConfigSpec;

import java.util.HashSet;
import java.util.List;

/**
 * 迁移自 1.12.2 com.hbm.config.PotionConfig。
 * createConfigHashSet → configStringList + load 时构建 HashSet。
 */
public class PotionConfig {

    public static boolean doJumpBoost = true;
    public static int potionSickness = 0;
    public static HashSet<String> potionBlacklist;

    private static ModConfigSpec.ConfigValue<List<? extends String>> POTION_BLACKLIST;
    private static ModConfigSpec.BooleanValue DO_JUMP_BOOST;
    private static ModConfigSpec.ConfigValue<String> POTION_SICKNESS;

    public static void build(ModConfigSpec.Builder builder) {
        POTION_BLACKLIST = CommonConfig.configStringList(builder, CommonConfig.CATEGORY_POTION, "08.01_hazmatPotionBlacklist", "List of Potions that get blocked while wearing a hazmat suit with bacteria protection - <potion> (String)", new String[]{"srparasites:coth", "srparasites:viral"});
        DO_JUMP_BOOST = CommonConfig.configBool(builder, CommonConfig.CATEGORY_POTION, "8.02_doJumpBoost", "Whether Servos and Armors should give Jumpboost", true);
        POTION_SICKNESS = CommonConfig.configString(builder, CommonConfig.CATEGORY_POTION, "8.03_potionSickness", "Valid configs include \"NORMAL\" and \"TERRARIA\", otherwise potion sickness is turned off", "OFF");
    }

    public static void load() {
        potionBlacklist = new HashSet<>(POTION_BLACKLIST.get());
        doJumpBoost = DO_JUMP_BOOST.get();
        potionSickness = 0;
        String s = POTION_SICKNESS.get();
        if ("normal".equalsIgnoreCase(s)) potionSickness = 1;
        if ("terraria".equalsIgnoreCase(s)) potionSickness = 2;
    }
}
