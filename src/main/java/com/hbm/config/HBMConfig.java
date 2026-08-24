package com.hbm.config;

import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.common.ModConfigSpec;

/**
 * HBM 中央配置（迁移自 1.12.2 MainRegistry.reloadConfig() 的单一 hbm.cfg）。
 *
 * 1.12.2：Configuration（.cfg 文本）→ 1.21.1：ModConfigSpec（.toml），
 * 由 HBM 构造器注册：
 *   modContainer.registerConfig(ModConfig.Type.COMMON, HBMConfig.SPEC, "hbm/hbm.toml");
 *   modBus.addListener(HBMConfig::onLoad);
 * 生成文件：config/hbm/hbm.toml
 *
 * 各配置类的 build() 在静态块中按序挂到同一个 builder（类别独立，顺序无影响）；
 * load() 在 ModConfigEvent.Loading/Reloading 时把 SpecValue 拷回静态字段。
 *
 * ⚠️ 格式变更说明：.cfg → .toml 无法自动迁移旧文件，用户需手动重设值；
 * 键名与原 .cfg 完全一致，可对照旧 hbm.cfg 逐项复制。
 */
public class HBMConfig {

    public static final ModConfigSpec SPEC;

    static {
        ModConfigSpec.Builder builder = new ModConfigSpec.Builder();

        // ===== cfg 型配置类（键名与原 .cfg 一致） =====
        GeneralConfig.build(builder);
        WorldConfig.build(builder);
        RadiationConfig.build(builder);
        BombConfig.build(builder);
        MobConfig.build(builder);
        ToolConfig.build(builder);
        WeaponConfig.build(builder);
        PotionConfig.build(builder);
        StructureConfig.build(builder);
        MachineConfig.build(builder);
        CompatibilityConfig.build(builder);
        // TODO 剩余 cfg 类（tools/gen_config.py 生成）：
        // VersatileConfig.build(builder); // 依赖 HbmPotion（P5）

        SPEC = builder.build();
    }

    /** 注册为 mod 总线监听器（@Mod 构造器中 modBus.addListener(HBMConfig::onLoad)） */
    public static void onLoad(ModConfigEvent event) {
        if (event.getConfig().getSpec() != SPEC) return;

        GeneralConfig.load();
        WorldConfig.load();
        RadiationConfig.load();
        BombConfig.load();
        MobConfig.load();
        ToolConfig.load();
        WeaponConfig.load();
        PotionConfig.load();
        StructureConfig.load();
        MachineConfig.load();
        CompatibilityConfig.load();
        // TODO 剩余 cfg 类：
        // VersatileConfig.load();
    }
}
