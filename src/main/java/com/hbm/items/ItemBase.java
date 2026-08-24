package com.hbm.items;

import net.minecraft.world.item.Item;

/**
 * 迁移自 1.12.2 com.hbm.items.ItemBase。
 *
 * 构造器变更：(String s) → (Item.Properties properties)。
 * 删除的内容及去向：
 *   - setRegistryName / setTranslationKey → 注册名由 DeferredRegister 提供
 *   - setCreativeTab(MainRegistry.controlTab) → ModCreativeTabs 的 displayItems
 *   - ModItems.ALL_ITEMS.add(this) → 注册由 DeferredRegister 统一完成
 */
public class ItemBase extends Item {

    public ItemBase(Item.Properties properties) {
        super(properties);
    }
}
