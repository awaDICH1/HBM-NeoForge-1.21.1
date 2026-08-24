package com.hbm.config;

import com.hbm.main.HBM;
import net.neoforged.neoforge.common.ModConfigSpec;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;

/**
 * 迁移自 1.12.2 com.hbm.config.CommonConfig。
 *
 * 变更：
 *   - 原 createConfigBool/Int/Double/String/StringList（接收 net.minecraftforge.common.config.Configuration）
 *     → configBool/Int/Double/String/StringList（接收 ModConfigSpec.Builder，返回 SpecValue）。
 *     每个辅助方法内部 push(category) → define → pop，保证键名与原 .cfg 完全一致。
 *   - setDef/setDefZero 的 MainRegistry.logger → HBM.LOGGER
 */
public class CommonConfig {

    // ===== 类别常量（与原 .cfg 完全一致，保证 TOML 结构与旧 cfg 对应） =====
    public static final String CATEGORY_GENERAL = "01_general";
    public static final String CATEGORY_ORES = "02_ores";
    public static final String CATEGORY_NUKES = "03_nukes";
    public static final String CATEGORY_DUNGEONS = "04_dungeons";
    public static final String CATEGORY_METEORS = "05_meteors";
    public static final String CATEGORY_EXPLOSIONS = "06_explosions";
    public static final String CATEGORY_MISSILE = "07_missile_machines";
    public static final String CATEGORY_POTION = "08_potion_effects";
    public static final String CATEGORY_MACHINES = "09_machines";
    public static final String CATEGORY_DROPS = "10_dangerous_drops";
    public static final String CATEGORY_TOOLS = "11_tools";
    public static final String CATEGORY_MOBS = "12_mobs";
    public static final String CATEGORY_RADIATION = "13_radiation";
    public static final String CATEGORY_HAZARD = "14_hazard";
    public static final String CATEGORY_STRUCTURES = "15_structures";
    public static final String CATEGORY_POLLUTION = "16_pollution";
    public static final String CATEGORY_BIOMES = "17_biomes";
    public static final String CATEGORY_WEAPONS = "18_weapons";

    public static final String CATEGORY_528 = "528";
    public static final String CATEGORY_LBSM = "LESS BULLSHIT MODE";

    // ===== ModConfigSpec 辅助方法（签名形状与原 createConfigXxx 对齐） =====

    public static ModConfigSpec.BooleanValue configBool(ModConfigSpec.Builder builder, String category, String name, String comment, boolean def) {
        builder.push(category);
        ModConfigSpec.BooleanValue v = builder.comment(comment).define(name, def);
        builder.pop();
        return v;
    }

    public static ModConfigSpec.IntValue configInt(ModConfigSpec.Builder builder, String category, String name, String comment, int def) {
        builder.push(category);
        // 原 cfg 无范围约束 → 用全 int 范围
        ModConfigSpec.IntValue v = builder.comment(comment).defineInRange(name, def, Integer.MIN_VALUE, Integer.MAX_VALUE);
        builder.pop();
        return v;
    }

    public static ModConfigSpec.DoubleValue configDouble(ModConfigSpec.Builder builder, String category, String name, String comment, double def) {
        builder.push(category);
        ModConfigSpec.DoubleValue v = builder.comment(comment).defineInRange(name, def, Double.MIN_VALUE, Double.MAX_VALUE);
        builder.pop();
        return v;
    }

    public static ModConfigSpec.ConfigValue<String> configString(ModConfigSpec.Builder builder, String category, String name, String comment, String def) {
        builder.push(category);
        ModConfigSpec.ConfigValue<String> v = builder.comment(comment).define(name, def);
        builder.pop();
        return v;
    }

    public static ModConfigSpec.ConfigValue<List<? extends String>> configStringList(ModConfigSpec.Builder builder, String category, String name, String comment, String[] def) {
        builder.push(category);
        ModConfigSpec.ConfigValue<List<? extends String>> v = builder.comment(comment).defineListAllowEmpty(name, List.of(def), entry -> entry instanceof String);
        builder.pop();
        return v;
    }

    public static ModConfigSpec.ConfigValue<List<? extends Integer>> configIntList(ModConfigSpec.Builder builder, String category, String name, String comment, int[] def) {
        builder.push(category);
        List<Integer> defList = new ArrayList<>(def.length);
        for (int i : def) defList.add(i);
        ModConfigSpec.ConfigValue<List<? extends Integer>> v = builder.comment(comment).defineListAllowEmpty(name, defList, entry -> entry instanceof Integer);
        builder.pop();
        return v;
    }

    /** List<Integer>（SpecValue.get() 返回）→ int[] */
    public static int[] toIntArray(List<? extends Integer> list) {
        int[] arr = new int[list.size()];
        for (int i = 0; i < arr.length; i++) arr[i] = list.get(i);
        return arr;
    }

    /** List<String> → HashMap（原 createConfigHashMap 的解析逻辑；splitReg 如 ":" / "="） */
    @SuppressWarnings("unchecked")
    public static <K, V> HashMap<K, V> parseHashMap(List<? extends String> entries, String splitReg, Class<K> keyType, Class<V> valueType) {
        HashMap<K, V> configDictionary = new HashMap<>();
        for (String entry : entries) {
            String[] pairs = entry.split(splitReg, 0);
            configDictionary.put((K) parseType(pairs[0], keyType), (V) parseType(pairs[1], valueType));
        }
        return configDictionary;
    }

    /** List<String> → HashSet（原 createConfigHashSet 的解析逻辑） */
    @SuppressWarnings("unchecked")
    public static <T> HashSet<T> parseHashSet(List<? extends String> entries, Class<T> valueType) {
        HashSet<T> configSet = new HashSet<>();
        for (String entry : entries) configSet.add((T) parseType(entry, valueType));
        return configSet;
    }

    private static Object parseType(String value, Class<?> type) {
        if (type == Float.class) return Float.parseFloat(value);
        if (type == Integer.class) return Integer.parseInt(value);
        if (type == Long.class) return Long.parseLong(value);
        if (type == Double.class) return Double.parseDouble(value);
        if (type.isEnum()) return Enum.valueOf((Class<Enum>) type, value);
        return value;
    }

    // ===== 校验辅助（原 setDefZero / setDef） =====

    public static int setDefZero(int value, int def) {
        if (value < 0) {
            HBM.LOGGER.error("Fatal error config: Randomizer value has been below zero, despite bound having to be positive integer!");
            HBM.LOGGER.error(String.format("Errored value will default back to %d, PLEASE REVIEW CONFIGURATION DESCRIPTION BEFORE MEDDLING WITH VALUES!", def));
            return def;
        }
        return value;
    }

    public static int setDef(int value, int def) {
        if (value <= 0) {
            HBM.LOGGER.error("Fatal error config: Randomizer value has been set to zero, despite bound having to be positive integer!");
            HBM.LOGGER.error(String.format("Errored value will default back to %d, PLEASE REVIEW CONFIGURATION DESCRIPTION BEFORE MEDDLING WITH VALUES!", def));
            return def;
        }
        return value;
    }

    public static int parseStructureFlag(String flag) {
        if (flag == null) flag = "";
        return switch (flag.toLowerCase(Locale.US)) {
            case "true", "on", "yes" -> 1;
            case "false", "off", "no" -> 0;
            default -> 2;
        };
    }
}
