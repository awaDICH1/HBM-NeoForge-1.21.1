package com.hbm.items;

import net.minecraft.world.item.Item;

/**
 * 迁移自 1.12.2 com.hbm.items.EffectItem。
 * 类用于不可获得的物品（排除 JEI 与创造栏），为效果提供占位。
 * 构造器 (String s) → (Item.Properties properties)（与 ItemBase 迁移一致）。
 */
public class EffectItem extends ItemBase {

    public EffectItem(Item.Properties properties) {
        super(properties);
    }
}
