package com.hbm.inventory.recipes;

import com.hbm.items.ModItems;
import com.hbm.util.ComparableStack;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;

import java.util.LinkedHashMap;
import java.util.Map;

public class CrusherRecipes {

    public static LinkedHashMap<ComparableStack, ItemStack> recipes = new LinkedHashMap<>();

    public static void register() {
        addRecipe(new ItemStack(Blocks.IRON_ORE), new ItemStack(Items.RAW_IRON, 2));
        addRecipe(new ItemStack(Blocks.GOLD_ORE), new ItemStack(Items.RAW_GOLD, 2));
        addRecipe(new ItemStack(Blocks.COPPER_ORE), new ItemStack(Items.RAW_COPPER, 2));
        addRecipe(new ItemStack(Blocks.DEEPSLATE_IRON_ORE), new ItemStack(Items.RAW_IRON, 2));
        addRecipe(new ItemStack(Blocks.DEEPSLATE_GOLD_ORE), new ItemStack(Items.RAW_GOLD, 2));
        addRecipe(new ItemStack(Blocks.DEEPSLATE_COPPER_ORE), new ItemStack(Items.RAW_COPPER, 2));
        addRecipe(new ItemStack(Blocks.COBBLESTONE), new ItemStack(Blocks.GRAVEL));
        addRecipe(new ItemStack(Blocks.GRAVEL), new ItemStack(Blocks.SAND));
        addRecipe(new ItemStack(Blocks.SANDSTONE), new ItemStack(Blocks.SAND, 2));
        addRecipe(new ItemStack(Blocks.RED_SANDSTONE), new ItemStack(Blocks.RED_SAND, 2));
        addRecipe(new ItemStack(Blocks.NETHERRACK), new ItemStack(Items.NETHER_BRICK, 2));
        addRecipe(new ItemStack(Blocks.STONE), new ItemStack(Blocks.COBBLESTONE));
        addRecipe(new ItemStack(Blocks.DEEPSLATE), new ItemStack(Blocks.COBBLED_DEEPSLATE));
        addRecipe(new ItemStack(Blocks.BONE_BLOCK), new ItemStack(Items.BONE_MEAL, 3));
        addRecipe(new ItemStack(Blocks.CLAY), new ItemStack(Items.CLAY_BALL, 4));
        addRecipe(new ItemStack(Blocks.MOSSY_COBBLESTONE), new ItemStack(Blocks.COBBLESTONE));
        addRecipe(new ItemStack(Items.IRON_INGOT), new ItemStack(ModItems.DUST_TINY.get(), 2));
        addRecipe(new ItemStack(Items.GOLD_INGOT), new ItemStack(ModItems.DUST_TINY.get(), 2));
        addRecipe(new ItemStack(Items.COPPER_INGOT), new ItemStack(ModItems.DUST_TINY.get(), 2));
    }

    public static void addRecipe(ItemStack input, ItemStack output) {
        recipes.put(new ComparableStack(input.getItem(), 1, input.getDamageValue()), output);
    }

    public static ItemStack getOutput(ItemStack stack) {
        if (stack == null || stack.isEmpty()) return ItemStack.EMPTY;
        ItemStack output = recipes.get(new ComparableStack(stack.getItem(), 1, stack.getDamageValue()));
        return output != null ? output.copy() : ItemStack.EMPTY;
    }

    public static Map<ComparableStack, ItemStack> getRecipes() {
        return recipes;
    }
}
