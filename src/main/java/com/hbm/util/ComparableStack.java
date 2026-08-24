package com.hbm.util;

import com.hbm.main.HBM;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;

/**
 * 迁移自 1.12.2 com.hbm.inventory.RecipesCommon.ComparableStack（自 RecipesCommon 提取，P5 HazardSystem 前置）。
 * 包路径：com.hbm.inventory.RecipesCommon.ComparableStack → com.hbm.util.ComparableStack。
 *
 * 1.21.1 变更：
 *   - Item.REGISTRY.getNameForObject / Item.getIdFromItem → BuiltInRegistries.ITEM.getKey / getId
 *   - metadata：getItemDamage / new ItemStack(item, count, meta) → getDamageValue / setDamageValue
 *   - Item.getItemFromBlock(block) → block.asItem()
 *   - OreDictionary.WILDCARD_VALUE(-1) → 常量 WILDCARD；getDictKeys（矿辞查询）→ TODO P4（tag 映射）
 *   - ModItems.nothing 占位 → Items.AIR / ItemStack.EMPTY（P3 物品批后恢复 ModItems.nothing）
 *
 * ⚠️ equals/hashCode/compareTo/matchesRecipe 逻辑原样保留（仅注册表与 meta API 换新）。
 */
public class ComparableStack extends AStack {

    /** 原 OreDictionary.WILDCARD_VALUE == -1（1.13+ 无 OreDictionary） */
    private static final int WILDCARD = -1;

    public Item item;
    public int meta;

    public ComparableStack(ItemStack stack) {
        this.item = stack.getItem();
        this.stacksize = stack.getCount();
        this.meta = stack.getDamageValue();
    }

    public ComparableStack(Item item) {
        this.item = item != null ? item : Items.AIR;   // TODO P3: 原 ModItems.nothing
        this.stacksize = 1;
        this.meta = 0;
    }

    public ComparableStack(Block item) {
        this.item = item.asItem();   // 原 Item.getItemFromBlock(item)
        this.stacksize = 1;
        this.meta = 0;
    }

    public ComparableStack(Item item, int stacksize) {
        this(item);
        this.stacksize = stacksize;
    }

    public ComparableStack(Item item, int stacksize, int meta) {
        this(item, stacksize);
        this.meta = meta;
    }

    public ComparableStack(Item item, int stacksize, Enum<?> theEnum) {
        this(item, stacksize);
        this.meta = theEnum.ordinal();
    }

    public ComparableStack(Block item, int stacksize) {
        this.item = item.asItem();
        this.stacksize = stacksize;
        this.meta = 0;
    }

    public ComparableStack(Block item, int stacksize, int meta) {
        this.item = item.asItem();
        this.stacksize = stacksize;
        this.meta = meta;
    }

    public ComparableStack(Block item, int stacksize, Enum<?> theEnum) {
        this(item, stacksize, theEnum.ordinal());
    }

    @Contract(mutates = "this")
    public ComparableStack makeSingular() {
        stacksize = 1;
        return this;
    }

    @Contract("-> new")
    public ItemStack toStack() {
        ItemStack stack = new ItemStack(item == null ? Items.AIR : item, stacksize);   // TODO P3: 原 ModItems.nothing
        stack.setDamageValue(meta);
        return stack;
    }

    @Override
    @Contract("-> new")
    public ItemStack getStack() {
        return toStack();
    }

    @Override
    @Contract("-> new")
    public List<ItemStack> getStackList() {
        return Collections.singletonList(getStack());
    }

    @Contract("-> !null")
    public String[] getDictKeys() {
        // P4: 原 OreDictionary.getOreIDs(getOreName) → 1.21.1 tag 查询
        // 通过 BuiltInRegistries 查找匹配的矿辞键
        if (item == null || item == Items.AIR) return new String[0];
        ResourceLocation rl = BuiltInRegistries.ITEM.getKey(item);
        if (rl == null) return new String[0];
        // 基础矿辞键：items/<namespace>/<path>
        String baseKey = "item." + rl.getNamespace() + "." + rl.getPath();
        return new String[]{baseKey};
    }

    //mlbv: the hashmap lookup + string hashing are really heavy, we should only mix the id + meta + stack integers if possible
    @Override
    @Contract(pure = true)
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        if (item == null) {
            HBM.LOGGER.error("ComparableStack has a null item! This is a serious issue!");
            Thread.dumpStack();
            item = Items.AIR;   // TODO P3: 原 ModItems.nothing
        }

        ResourceLocation name = BuiltInRegistries.ITEM.getKey(item);   // 原 Item.REGISTRY.getNameForObject

        if (name == null) {
            HBM.LOGGER.error("ComparableStack holds an item that does not seem to be registered. How does that even happen?");
            Thread.dumpStack();
            item = Items.AIR;
        }

        if (name != null)
            result = prime * result + BuiltInRegistries.ITEM.getKey(item).hashCode(); //using the int ID will cause fucky-wuckys if IDs are scrambled
        result = prime * result + meta;
        result = prime * result + stacksize;
        return result;
    }

    @Override
    @Contract(value = "null -> false", pure = true)
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof ComparableStack other)) return false;
        if (item == null) {
            if (other.item != null) return false;
        } else if (!item.equals(other.item)) return false;
        if (meta != WILDCARD && other.meta != WILDCARD && meta != other.meta) return false;
        return stacksize == other.stacksize;
    }

    @Override
    @Contract(pure = true)
    public int compareTo(@NotNull AStack stack) {

        if (stack instanceof ComparableStack comp) {

            int thisID = BuiltInRegistries.ITEM.getId(item);
            int thatID = BuiltInRegistries.ITEM.getId(comp.item);

            if (thisID > thatID) return 1;
            if (thatID > thisID) return -1;

            return Integer.compare(meta, comp.meta);
        }

        // if compared with an ODStack, the CStack will take priority —— TODO P4: OreDictStack 类型
        return 0;
    }

    @Override
    @Contract(value = "null, _ -> false", pure = true)
    public boolean matchesRecipe(ItemStack stack, boolean ignoreSize) {

        if (stack == null) return false;

        if (stack.getItem() != this.item) return false;

        if (this.meta != WILDCARD && stack.getDamageValue() != this.meta) return false;

        return ignoreSize || stack.getCount() >= this.stacksize;
    }

    @Override
    @Contract("-> new")
    public AStack copy() {
        return new ComparableStack(item, stacksize, meta);
    }

    @Override
    @Contract(pure = true)
    public String toString() {
        return "ComparableStack: { " + stacksize + " x " + BuiltInRegistries.ITEM.getKey(item) + "@" + meta + " }";
    }

    @Override
    @Contract("-> new")
    public List<ItemStack> extractForJEI() {
        return Collections.singletonList(this.toStack());
    }

    @Contract(pure = true)
    public boolean isEmpty() {
        return item == Items.AIR || stacksize <= 0 || meta < -32768 || meta > 65535;
    }
}
