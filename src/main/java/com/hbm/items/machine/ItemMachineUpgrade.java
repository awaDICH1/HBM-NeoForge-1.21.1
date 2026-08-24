package com.hbm.items.machine;

import net.minecraft.world.item.Item;

/**
 * 机器升级物品（P5.1a 骨架版）。
 *
 * 迁移自 1.12.2 com.hbm.items.machine.ItemMachineUpgrade（198 行）。
 * 当前仅含 IUpgradeInfoProvider 所需的 UpgradeType 枚举；
 * 完整物品类（注册/效果应用）待 P5.2 机器批。
 */
public class ItemMachineUpgrade extends Item {

    public enum UpgradeType {
        SPEED,
        EFFECT,
        POWER,
        FORTUNE,
        AFTERBURN,
        OVERDRIVE,
        NULLIFIER,
        SCREAM,
        SPECIAL
    }

    public ItemMachineUpgrade(Properties properties) {
        super(properties);
    }
}
