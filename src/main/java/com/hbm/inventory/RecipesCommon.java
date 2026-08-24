package com.hbm.inventory;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.hbm.util.AStack;
import com.hbm.util.ComparableStack;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import javax.annotation.concurrent.Immutable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 迁移自 1.12.2 com.hbm.inventory.RecipesCommon。
 *
 * ⚠️ AStack / ComparableStack 已提取到 com.hbm.util（P5 HazardSystem 前置），此处改为 import。
 *
 * 1.21.1 变更：
 *   - NbtComparableStack：hasTagCompound/getTagCompound → hasTag/getTag；
 *     Library.tagContainsOther → 内联 tagContainsOther（required ⊆ input 语义）
 *   - OreDictStack：OreDictionary.getOres/getOreIDs/getOreName → tag 查询（TODO P4 OreDictManager 键名映射）
 *   - MetaBlock：Block.REGISTRY.getNameForObject → BuiltInRegistries.BLOCK.getKey；
 *     metaOf(BlockState)（1.13+ 无 meta）→ metaOf(BlockState, 0)
 *   - extractForJEI 的 MainRegistry.proxy.getSubItems → TODO P8（客户端子物品枚举）
 */
public class RecipesCommon {

    private static final LoadingCache<Block, MetaBlock[]> META_POOLS =
            CacheBuilder.newBuilder().maximumSize(2048).concurrencyLevel(Runtime.getRuntime().availableProcessors()).build(new CacheLoader<>() {
                @Override
                public MetaBlock[] load(Block key) {
                    return new MetaBlock[16];
                }
            });

    /** 原 OreDictionary.WILDCARD_VALUE == -1 */
    private static final int WILDCARD = -1;

    @Contract("null -> null; !null -> !null")
    public static ItemStack[] copyStackArray(ItemStack[] array) {

        if (array == null) return null;

        ItemStack[] clone = new ItemStack[array.length];

        for (int i = 0; i < array.length; i++) {

            if (array[i] != null) clone[i] = array[i].copy();
        }

        return clone;
    }

    @Contract("null -> null; !null -> new")
    public static ItemStack[] objectToStackArray(Object[] array) {

        if (array == null) return null;

        ItemStack[] clone = new ItemStack[array.length];

        for (int i = 0; i < array.length; i++) {

            if (array[i] instanceof ItemStack) clone[i] = (ItemStack) array[i];
        }

        return clone;
    }

    /**
     * 原 Library.tagContainsOther(required, input)：input 是否包含 required 的所有键（值相等）。
     * P5 Library 迁移后可改回调用。
     */
    private static boolean tagContainsOther(CompoundTag required, CompoundTag input) {
        if (required == null || required.isEmpty()) return true;
        if (input == null) return false;
        for (String key : required.getAllKeys()) {
            if (!input.contains(key) || !required.get(key).equals(input.get(key))) return false;
        }
        return true;
    }

    /**
     * This is mutable!
     */
    public static class NbtComparableStack extends ComparableStack {

        ItemStack stack;

        public NbtComparableStack(ItemStack stack) {
            super(stack);
            this.stack = stack.copy();
        }

        @Override
        @Contract("-> new")
        public ComparableStack makeSingular() {
            ItemStack st = stack.copy();
            st.setCount(1);
            return new NbtComparableStack(st);
        }

        @Override
        @Contract(mutates = "this")
        public AStack singulize() {
            stack.setCount(1);
            this.stacksize = 1;
            return this;
        }

        @Override
        @Contract("-> !null")
        public ItemStack toStack() {
            return stack.copy();
        }

        @Override
        @Contract("-> !null")
        public ItemStack getStack() {
            return toStack();
        }

        @Override
        @Contract(pure = true)
        public int hashCode() {
            // 1.21.1：hasTag/getTag → has(DataComponents.CUSTOM_DATA) / get(...).copyTag()
            if (!stack.has(DataComponents.CUSTOM_DATA)) return super.hashCode();
            else return super.hashCode() * 31 + stack.get(DataComponents.CUSTOM_DATA).copyTag().hashCode();
        }

        @Override
        @Contract("-> new")
        public AStack copy() {
            return new NbtComparableStack(stack);
        }

        @Override
        @Contract(value = "null -> false", pure = true)
        public boolean equals(Object obj) {
            if (!stack.has(DataComponents.CUSTOM_DATA) || !(obj instanceof NbtComparableStack)) {
                return super.equals(obj);
            } else {
                return super.equals(obj) && tagContainsOther(stack.get(DataComponents.CUSTOM_DATA).copyTag(), ((NbtComparableStack) obj).stack.get(DataComponents.CUSTOM_DATA).copyTag());
            }
        }

        @Override
        @Contract(value = "null, _ -> false", pure = true)
        public boolean matchesRecipe(ItemStack stack, boolean ignoreSize) {
            return super.matchesRecipe(stack, ignoreSize) && tagContainsOther(
                    this.stack.has(DataComponents.CUSTOM_DATA) ? this.stack.get(DataComponents.CUSTOM_DATA).copyTag() : null,
                    stack.has(DataComponents.CUSTOM_DATA) ? stack.get(DataComponents.CUSTOM_DATA).copyTag() : null);
        }

        @Override
        @Contract(pure = true)
        public String toString() {
            return "NbtComparableStack: " + stack.toString();
        }
    }

    /**
     * This is mutable!
     */
    public static class OreDictStack extends AStack {

        public String name;

        public OreDictStack(String name) {
            this.name = name;
            this.stacksize = 1;
        }

        public OreDictStack(String name, int stacksize) {
            this(name);
            this.stacksize = stacksize;
        }

        @Contract("-> !null")
        public List<ItemStack> toStacks() {
            // P4.2: 原 OreDictionary.getOres(name) → OreDictManager 自研矿辞表（1.21 无运行时 tag 注册）
            return OreDictManager.getOres(name);
        }

        @Override
        @Contract("-> !null")
        public ItemStack getStack() {
            List<ItemStack> stacks = toStacks();
            if (stacks.isEmpty()) return ItemStack.EMPTY;
            ItemStack stack = stacks.get(0);
            ItemStack out = new ItemStack(stack.getItem(), stacksize);
            out.setDamageValue(stack.getDamageValue());
            return out;
        }

        @Override
        @Contract("-> !null")
        public List<ItemStack> getStackList() {
            List<ItemStack> list = new ArrayList<>();
            for (ItemStack s : toStacks()) {
                ItemStack copy = s.copy();
                copy.setCount(this.stacksize);
                list.add(copy);
            }
            return list;
        }

        @Override
        @Contract(pure = true)
        public int hashCode() {
            return (name + this.stacksize).hashCode();
        }

        @Override
        @Contract(pure = true)
        public int compareTo(@NotNull AStack stack) {

            if (stack instanceof OreDictStack comp) {
                return name.compareTo(comp.name);
            }

            //if compared with a CStack, the ODStack will yield
            if (stack instanceof ComparableStack) return -1;

            return 0;
        }

        @Override
        @Contract(value = "null, _ -> false", pure = true)
        public boolean matchesRecipe(ItemStack stack, boolean ignoreSize) {

            if (stack == null || stack.isEmpty()) return false;

            if (!ignoreSize && stack.getCount() < this.stacksize) return false;

            // TODO P4: 原 OreDictionary.getOreIDs/getOreName → 1.21.1 tag；name↔tag 映射待 OreDictManager
            for (TagKey<Item> tagKey : stack.getTags().toList()) {
                if (this.name.equals(tagKey.location().toString())) return true;
            }

            return false;
        }

        @Override
        @Contract(value = "null -> false", pure = true)
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null) return false;
            if (!(obj instanceof OreDictStack other)) return false;
            if (name == null) {
                if (other.name != null) return false;
            } else if (!name.equals(other.name)) return false;
            return stacksize == other.stacksize;
        }

        @Override
        @Contract("-> new")
        public AStack copy() {
            return new OreDictStack(name, stacksize);
        }

        @Override
        @Contract(pure = true)
        public String toString() {
            return "OreDictStack: name, " + name + ", stacksize, " + stacksize;
        }

        @Override
        @Contract("-> !null")
        public List<ItemStack> extractForJEI() {

            List<ItemStack> fromDict = toStacks();
            List<ItemStack> ores = new ArrayList<>();

            for (ItemStack stack : fromDict) {

                ItemStack copy = stack.copy();
                copy.setCount(this.stacksize);

                if (stack.getDamageValue() != WILDCARD) {
                    ores.add(copy);
                } else {
                    // TODO P8: 原 MainRegistry.proxy.getSubItems(copy)（客户端子物品枚举）
                }
            }

            return ores;
        }
    }

    public static MetaBlock metaOf(Block b, int meta) {
        final MetaBlock[] pool = META_POOLS.getUnchecked(b);
        final int m = meta & 15;
        MetaBlock mb = pool[m];
        if (mb == null) {
            mb = new MetaBlock(b, m);
            // mlbv: yes it races, but who cares?
            pool[m] = mb;
        }
        return mb;
    }

    /** 原 metaOf(BlockState)：1.13+ 无 metadata，meta 恒为 0（TODO P8 若需要按 BlockState 重建） */
    public static MetaBlock metaOf(BlockState state) {
        final Block b = state.getBlock();
        return metaOf(b, 0);
    }

    public static void onServerStopping() {
        META_POOLS.invalidateAll();
    }

    @Immutable
    public static final class MetaBlock {

        public final Block block;
        public final int meta;

        /**
         * @deprecated Use {@link #metaOf(Block, int)} or {@link #metaOf(BlockState)} instead.
         */
        @Deprecated
        public MetaBlock(Block block, int meta) {
            this.block = block;
            this.meta = meta;
        }

        public MetaBlock(Block block) {
            this(block, 0);
        }

        @Override
        @Contract(pure = true)
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + BuiltInRegistries.BLOCK.getKey(block).hashCode();
            result = prime * result + meta;
            return result;
        }

        @Override
        @Contract(value = "null -> false", pure = true)
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null) return false;
            if (getClass() != obj.getClass()) return false;
            MetaBlock other = (MetaBlock) obj;
            if (block == null) {
                if (other.block != null) return false;
            } else if (!block.equals(other.block)) return false;
            return meta == other.meta;
        }

        @Deprecated
        @Contract(pure = true)
        public int getID() {
            return hashCode();
        }
    }
}
