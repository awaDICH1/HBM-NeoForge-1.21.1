package com.hbm.datagen;

import com.hbm.main.CraftingManager;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;

import java.util.concurrent.CompletableFuture;

/**
 * HBM Recipe Provider for NeoForge 1.21.1 DataGen.
 *
 * Replaces 1.12.2's runtime ForgeRegistry recipe registration.
 * During DataGen, buildRecipes() is called which delegates to CraftingManager.init()
 * to collect all recipe definitions and output them as JSON files.
 *
 * Also registers smelting/blasting recipes for mod-specific items.
 */
public class HBMRecipeProvider extends RecipeProvider {

    public HBMRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {
        CraftingManager.init(recipeOutput);
        addSmeltingRecipes(recipeOutput);
    }

    private void addSmeltingRecipes(RecipeOutput output) {
        addSmelting(output, net.minecraft.world.item.Items.COAL, com.hbm.items.ModItems.INGOT_GRAPHITE.get(), 0.2f, 200, "coal_to_graphite");
        addSmelting(output, com.hbm.items.ModItems.INGOT_C4.get(), com.hbm.items.ModItems.INGOT_HES.get(), 0.5f, 400, "c4_to_hes");
        addSmelting(output, com.hbm.items.ModItems.INGOT_HES.get(), com.hbm.items.ModItems.INGOT_LES.get(), 0.5f, 400, "hes_to_les");
        addSmelting(output, com.hbm.items.ModItems.INGOT_PC.get(), com.hbm.items.ModItems.INGOT_PVC.get(), 0.3f, 200, "pc_to_pvc");
        addBlasting(output, net.minecraft.world.item.Items.IRON_INGOT, com.hbm.items.ModItems.INGOT_STEEL.get(), 0.5f, 400, "iron_to_steel_blast");
        addSmelting(output, com.hbm.items.ModItems.INGOT_BAKELITE.get(), com.hbm.items.ModItems.INGOT_POLYMER.get(), 0.3f, 300, "bakelite_to_polymer");
        addSmelting(output, com.hbm.items.ModItems.INGOT_PHOSPHORUS.get(), com.hbm.items.ModItems.INGOT_PVC.get(), 0.3f, 300, "phosphorus_to_pvc");
        addSmelting(output, com.hbm.items.ModItems.INGOT_SEMTEX.get(), com.hbm.items.ModItems.INGOT_C4.get(), 0.5f, 400, "semtex_to_c4");
    }

    private static void addSmelting(RecipeOutput output, ItemLike input, ItemLike result, float experience, int cookingTime, String name) {
        SimpleCookingRecipeBuilder.smelting(
                Ingredient.of(input), RecipeCategory.MISC, result, experience, cookingTime)
                .unlockedBy("has_" + name, has(input))
                .save(output, ResourceLocation.fromNamespaceAndPath("hbm", "smelting/" + name));
    }

    private static void addBlasting(RecipeOutput output, ItemLike input, ItemLike result, float experience, int cookingTime, String name) {
        SimpleCookingRecipeBuilder.blasting(
                Ingredient.of(input), RecipeCategory.MISC, result, experience, cookingTime)
                .unlockedBy("has_" + name, has(input))
                .save(output, ResourceLocation.fromNamespaceAndPath("hbm", "blasting/" + name));
    }
}
