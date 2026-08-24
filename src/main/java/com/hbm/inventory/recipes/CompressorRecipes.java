package com.hbm.inventory.recipes;

import com.hbm.items.ModItems;
import com.hbm.util.ComparableStack;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.LinkedHashMap;
import java.util.Map;

public class CompressorRecipes {

    public static LinkedHashMap<ComparableStack, ItemStack> recipes = new LinkedHashMap<>();

    public static void register() {
        addRecipe(new ItemStack(Items.IRON_INGOT), new ItemStack(ModItems.PLATE_IRON.get()));
        addRecipe(new ItemStack(Items.COPPER_INGOT), new ItemStack(ModItems.PLATE_COPPER.get()));
        addRecipe(new ItemStack(Items.GOLD_INGOT), new ItemStack(ModItems.PLATE_GOLD.get()));
        addRecipe(new ItemStack(ModItems.INGOT_TITANIUM.get()), new ItemStack(ModItems.PLATE_TITANIUM.get()));
        addRecipe(new ItemStack(ModItems.INGOT_ALUMINIUM.get()), new ItemStack(ModItems.PLATE_ALUMINIUM.get()));
        addRecipe(new ItemStack(ModItems.INGOT_STEEL.get()), new ItemStack(ModItems.PLATE_STEEL.get()));
        addRecipe(new ItemStack(ModItems.INGOT_TUNGSTEN.get()), new ItemStack(ModItems.PLATE_TUNGSTEN.get()));
        addRecipe(new ItemStack(ModItems.INGOT_BERYLLIUM.get()), new ItemStack(ModItems.PLATE_BERYLLIUM.get()));
        addRecipe(new ItemStack(ModItems.INGOT_NICKEL.get()), new ItemStack(ModItems.PLATE_NICKEL.get()));
        addRecipe(new ItemStack(ModItems.INGOT_CHROMIUM.get()), new ItemStack(ModItems.PLATE_CHROMIUM.get()));
        addRecipe(new ItemStack(ModItems.INGOT_MOLYBDENUM.get()), new ItemStack(ModItems.PLATE_MOLYBDENUM.get()));
        addRecipe(new ItemStack(ModItems.INGOT_NIOBIUM.get()), new ItemStack(ModItems.PLATE_NIOBIUM.get()));
        addRecipe(new ItemStack(ModItems.INGOT_TANTALIUM.get()), new ItemStack(ModItems.PLATE_TANTALIUM.get()));
        addRecipe(new ItemStack(ModItems.INGOT_URANIUM.get()), new ItemStack(ModItems.PLATE_URANIUM.get()));
        addRecipe(new ItemStack(ModItems.INGOT_SILICON.get()), new ItemStack(ModItems.PLATE_SILICON.get()));
        addRecipe(new ItemStack(ModItems.INGOT_LANTHANIUM.get()), new ItemStack(ModItems.PLATE_LANTHANIUM.get()));
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
