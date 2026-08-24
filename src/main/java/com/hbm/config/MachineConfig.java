package com.hbm.config;

import com.hbm.interfaces.IDoor;
import net.neoforged.neoforge.common.ModConfigSpec;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;

/**
 * 迁移自 1.12.2 com.hbm.config.MachineConfig。
 * doorConf（HashMap<String, IDoor.Mode>）由 configStringList 读取后 load 时解析。
 */
public class MachineConfig {

    protected static boolean scaleRTGPower = false;
    protected static boolean doRTGsDecay = true;
    protected static boolean disableMachines = false;
    public static boolean holdDoorRedstone = false;
    public static int crateByteSize = 8192;
    public static int rbmkJumpTemp = 1250;
    public static HashMap<String, IDoor.Mode> doorConf = new HashMap<>();

    private static ModConfigSpec.BooleanValue SCALE_RTG_POWER;
    private static ModConfigSpec.BooleanValue DO_RTGS_DECAY;
    private static ModConfigSpec.BooleanValue DISABLE_MACHINES;
    private static ModConfigSpec.BooleanValue HOLD_DOOR_REDSTONE;
    private static ModConfigSpec.ConfigValue<List<? extends String>> DOOR_CONF;

    public static void build(ModConfigSpec.Builder builder) {
        SCALE_RTG_POWER = CommonConfig.configBool(builder, CommonConfig.CATEGORY_MACHINES, "9.01_scaleRTGPower", "Should RTG/Betavoltaic fuel power scale down as it decays?", false);
        DO_RTGS_DECAY = CommonConfig.configBool(builder, CommonConfig.CATEGORY_MACHINES, "9.02_doRTGsDecay", "Should RTG/Betavoltaic fuel decay at all?", true);
        DISABLE_MACHINES = CommonConfig.configBool(builder, CommonConfig.CATEGORY_MACHINES, "9.00_disableMachines", "Prevent mod from registering any Machines? (WARNING: THIS WILL BREAK PREEXISTING WORLDS)", false);
        HOLD_DOOR_REDSTONE = CommonConfig.configBool(builder, CommonConfig.CATEGORY_MACHINES, "9.99_CE_03_holdDoorRedstone", "Whether the door requires a continuous redstone signal to stay open, or toggles on each activation.", false);
        DOOR_CONF = CommonConfig.configStringList(builder, CommonConfig.CATEGORY_MACHINES, "9.99_CE_02_doorConf", "Configuration for door modes. Format: 'modid:door_name:MODE' (e.g. 'hbm:vault_door:REDSTONE') or 'ALL:MODE'. Modes: DEFAULT, TOOLABLE, REDSTONE", new String[]{});
    }

    public static void load() {
        scaleRTGPower = SCALE_RTG_POWER.get();
        doRTGsDecay = DO_RTGS_DECAY.get();
        disableMachines = DISABLE_MACHINES.get();
        holdDoorRedstone = HOLD_DOOR_REDSTONE.get();

        doorConf.clear();
        for (String entry : DOOR_CONF.get()) {
            if (entry == null) continue;
            String trimmed = entry.trim();
            if (trimmed.isEmpty()) continue;
            String[] split = trimmed.split(":");
            try {
                if (split.length == 2 && "ALL".equalsIgnoreCase(split[0])) {
                    IDoor.Mode mode = IDoor.Mode.valueOf(split[1].toUpperCase(Locale.ROOT));
                    doorConf.put("ALL", mode);
                } else if (split.length == 3) {
                    IDoor.Mode mode = IDoor.Mode.valueOf(split[2].toUpperCase(Locale.ROOT));
                    doorConf.put(split[0] + ":" + split[1], mode);
                }
            } catch (IllegalArgumentException e) {
                // Ignore invalid modes
            }
        }
    }
}
