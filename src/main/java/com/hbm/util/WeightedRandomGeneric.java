package com.hbm.util;

/**
 * 迁移自 1.12.2 com.hbm.util.WeightedRandomGeneric。
 * 1.21.1：WeightedRandom.Item 基类已移除；本项目内部使用（get()/getWeight()），
 * 不再实现 net.minecraft.util.random.WeightedEntry（其 getWeight() 返回 Weight 而非 int）。
 */
public class WeightedRandomGeneric<T> {

    T item;
    private final int weight;

    public WeightedRandomGeneric(T o, int weight) {
        this.item = o;
        this.weight = weight;
    }

    public int getWeight() {
        return this.weight;
    }

    public T get() {
        return item;
    }
}
