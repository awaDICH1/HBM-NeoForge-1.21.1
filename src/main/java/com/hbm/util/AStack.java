package com.hbm.util;

import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Contract;

import java.util.List;

/**
 * 迁移自 1.12.2 com.hbm.inventory.RecipesCommon.AStack（自 RecipesCommon 提取，P5 HazardSystem 前置）。
 *
 * ⚠️ TODO 占位（依赖未迁移系统）：
 *   - isApplicable(ItemStack)：原用 NbtComparableStack（RecipesCommon 嵌套类，P4）→ 暂用普通 ComparableStack
 *   - isApplicable(ComparableStack) 的 OreDictStack 分支（OreDictionary，P4 tag 映射）
 *   - extractForCyclingDisplay：原 ModItems.nothing 占位 → ItemStack.EMPTY（P3 物品批后恢复）
 */
public abstract class AStack implements Comparable<AStack> {

    public int stacksize;

    @Contract(pure = true)
    public boolean isApplicable(ItemStack stack) {
        // TODO P4: 原 return isApplicable(new NbtComparableStack(stack));（NBT 感知栈）
        return isApplicable(new ComparableStack(stack));
    }

    @Contract(mutates = "this")
    public AStack singulize() {
        stacksize = 1;
        return this;
    }

    @Contract(pure = true)
    public int count() {
        return stacksize;
    }

    @Contract(mutates = "this")
    public void setCount(int c) {
        stacksize = c;
    }

    /**
     * Count sensitive for ComparableStacks.
     */
    public boolean isApplicable(ComparableStack comp) {

        if (this instanceof ComparableStack) {
            return this.equals(comp);
        }

        // TODO P4: 原 OreDictStack 分支（OreDictionary.getOres(name) → 1.21.1 tag 查询）
        return false;
    }

    public abstract boolean matchesRecipe(ItemStack stack, boolean ignoreSize);

    public abstract AStack copy();

    public abstract ItemStack getStack();

    public abstract List<ItemStack> getStackList();

    @Override
    @Contract(pure = true)
    public String toString() {
        return "AStack: size, " + stacksize;
    }

    /**
     * Generates either an ItemStack or an ArrayList of ItemStacks
     */
    public abstract List<ItemStack> extractForJEI();

    @Contract("_, -> !null")
    public ItemStack extractForCyclingDisplay(int cycle) {
        List<ItemStack> list = extractForJEI();
        cycle *= 50;

        if (list.isEmpty()) return ItemStack.EMPTY;   // TODO P3: 原 new ItemStack(ModItems.nothing)
        return list.get((int) (System.currentTimeMillis() % (cycle * list.size()) / cycle));
    }
}
