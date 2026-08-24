package com.hbm.util;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

/**
 * 迁移自 1.12.2 com.hbm.util.WeightedRandomObject。
 * 1.21.1：WeightedRandom.Item 基类已移除；本项目内部使用（asStack()/asItem()/asString()），
 * 不再实现 net.minecraft.util.random.WeightedEntry（其 getWeight() 返回 Weight 而非 int）。
 */
public class WeightedRandomObject {

    Object item;
    private final int weight;

    public WeightedRandomObject(Object o, int weight) {
        this.item = o;
        this.weight = weight;
    }

    public int getWeight() {
        return this.weight;
    }

    public ItemStack asStack() {
        if (item instanceof ItemStack) {
            return ((ItemStack) item).copy();
        }
        return null;
    }

    public Item asItem() {
        if (item instanceof Item) {
            return (Item) item;
        }
        return null;
    }

    public String asString() {
        if (item instanceof String) {
            return (String) item;
        }
        return null;
    }
}
