package com.hbm.inventory.recipes;

import com.hbm.items.ModItems;
import com.hbm.util.ComparableStack;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 铁砧合成配方系统（P5.9 迁移版）。
 *
 * 迁移自 1.12.2 com.hbm.inventory.recipes.AnvilRecipes。
 * 1.21.1 变更：运行时配方注册保留（非 JSON 数据驱动），使用 ComparableStack 键。
 *
 * 机制：输入物品 + 锤子 → 输出物品（消耗锤子耐久）。
 */
public class AnvilRecipes {

    public static LinkedHashMap<ComparableStack, AnvilRecipe> recipes = new LinkedHashMap<>();

    public static void register() {

        // Iron ingot -> Iron plate
        addRecipe(new ItemStack(Items.IRON_INGOT), new ItemStack(ModItems.PLATE_IRON.get()), 2);

        // Gold ingot -> Gold plate
        addRecipe(new ItemStack(Items.GOLD_INGOT), new ItemStack(ModItems.PLATE_GOLD.get()), 2);

        // Copper ingot -> Copper plate
        addRecipe(new ItemStack(ModItems.INGOT_COPPER.get()), new ItemStack(ModItems.PLATE_COPPER.get()), 2);

        // Lead ingot -> Lead plate
        addRecipe(new ItemStack(ModItems.INGOT_LEAD.get()), new ItemStack(ModItems.PLATE_LEAD.get()), 2);

        // Aluminium ingot -> Aluminium plate
        addRecipe(new ItemStack(ModItems.INGOT_ALUMINIUM.get()), new ItemStack(ModItems.PLATE_ALUMINIUM.get()), 2);

        // Steel ingot -> Steel plate
        addRecipe(new ItemStack(ModItems.INGOT_STEEL.get()), new ItemStack(ModItems.PLATE_STEEL.get()), 3);

        // Tungsten ingot -> Tungsten plate
        addRecipe(new ItemStack(ModItems.INGOT_TUNGSTEN.get()), new ItemStack(ModItems.PLATE_TUNGSTEN.get()), 4);

        // Beryllium ingot -> Beryllium plate
        addRecipe(new ItemStack(ModItems.INGOT_BERYLLIUM.get()), new ItemStack(ModItems.PLATE_BERYLLIUM.get()), 4);

        // Nickel ingot -> Nickel plate
        addRecipe(new ItemStack(ModItems.INGOT_NICKEL.get()), new ItemStack(ModItems.PLATE_NICKEL.get()), 3);

        // Chromium ingot -> Chromium plate
        addRecipe(new ItemStack(ModItems.INGOT_CHROMIUM.get()), new ItemStack(ModItems.PLATE_CHROMIUM.get()), 3);

        // Molybdenum ingot -> Molybdenum plate
        addRecipe(new ItemStack(ModItems.INGOT_MOLYBDENUM.get()), new ItemStack(ModItems.PLATE_MOLYBDENUM.get()), 4);

        // Silicon ingot -> Silicon wafer
        addRecipe(new ItemStack(ModItems.INGOT_SILICON.get()), new ItemStack(ModItems.PLATE_SILICON.get()), 2);

        // Niobium ingot -> Niobium plate
        addRecipe(new ItemStack(ModItems.INGOT_NIOBIUM.get()), new ItemStack(ModItems.PLATE_NIOBIUM.get()), 4);

        // Tantalium ingot -> Tantalium plate
        addRecipe(new ItemStack(ModItems.INGOT_TANTALIUM.get()), new ItemStack(ModItems.PLATE_TANTALIUM.get()), 4);

        // Lanthanium ingot -> Lanthanium plate
        addRecipe(new ItemStack(ModItems.INGOT_LANTHANIUM.get()), new ItemStack(ModItems.PLATE_LANTHANIUM.get()), 4);

        // Uranium ingot -> Uranium plate
        addRecipe(new ItemStack(ModItems.INGOT_URANIUM.get()), new ItemStack(ModItems.PLATE_URANIUM.get()), 5);

        // Titanium ingot -> Titanium plate
        addRecipe(new ItemStack(ModItems.INGOT_TITANIUM.get()), new ItemStack(ModItems.PLATE_TITANIUM.get()), 5);

        // ===== Batch 2: Advanced material plates =====
        addRecipe(new ItemStack(ModItems.INGOT_DURA_STEEL.get()), new ItemStack(ModItems.PLATE_DURA_STEEL.get()), 6);
        addRecipe(new ItemStack(ModItems.INGOT_GUNMETAL.get()), new ItemStack(ModItems.PLATE_GUNMETAL.get()), 4);
        addRecipe(new ItemStack(ModItems.INGOT_WEAPONSTEEL.get()), new ItemStack(ModItems.PLATE_WEAPONSTEEL.get()), 6);
        addRecipe(new ItemStack(ModItems.INGOT_COMBINE_STEEL.get()), new ItemStack(ModItems.PLATE_COMBINE_STEEL.get()), 8);
        addRecipe(new ItemStack(ModItems.INGOT_SATURNITE.get()), new ItemStack(ModItems.PLATE_SATURNITE.get()), 7);
        addRecipe(new ItemStack(ModItems.INGOT_DESH.get()), new ItemStack(ModItems.PLATE_DESH.get()), 8);
        addRecipe(new ItemStack(ModItems.INGOT_DINEUTRONIUM.get()), new ItemStack(ModItems.PLATE_DINEUTRONIUM.get()), 10);
        addRecipe(new ItemStack(ModItems.INGOT_SCHRABIDIUM.get()), new ItemStack(ModItems.PLATE_SCHRABIDIUM.get()), 9);
        addRecipe(new ItemStack(ModItems.INGOT_POLYMER.get()), new ItemStack(ModItems.PLATE_POLYMER.get()), 3);
        addRecipe(new ItemStack(ModItems.INGOT_TUNGSTEN_CARBIDE.get()), new ItemStack(ModItems.PLATE_TUNGSTEN.get()), 6);
        addRecipe(new ItemStack(ModItems.INGOT_RED_COPPER.get()), new ItemStack(ModItems.PLATE_COPPER.get()), 3);

        // ===== Batch 3: Armor plates (composite materials) =====
        addRecipe(new ItemStack(ModItems.INGOT_TITANIUM.get()), new ItemStack(ModItems.PLATE_ARMOR_TITANIUM.get()), 8);
        addRecipe(new ItemStack(ModItems.INGOT_COMBINE_STEEL.get()), new ItemStack(ModItems.PLATE_ARMOR_AJR.get()), 10);
        addRecipe(new ItemStack(ModItems.INGOT_COMBINE_STEEL.get()), new ItemStack(ModItems.PLATE_ARMOR_HEV.get()), 10);
        addRecipe(new ItemStack(ModItems.INGOT_SATURNITE.get()), new ItemStack(ModItems.PLATE_ARMOR_LUNAR.get()), 9);
        addRecipe(new ItemStack(ModItems.INGOT_DURA_STEEL.get()), new ItemStack(ModItems.PLATE_ARMOR_FAU.get()), 8);
        addRecipe(new ItemStack(ModItems.INGOT_DINEUTRONIUM.get()), new ItemStack(ModItems.PLATE_ARMOR_DNT.get()), 12);

        // ===== Batch 4: Ingot processing (3 ingots -> 1 block, 1 block -> 9 ingots via anvil) =====
        addRecipe(new ItemStack(ModItems.INGOT_STEEL.get(), 3), new ItemStack(ModItems.INGOT_DURA_STEEL.get()), 5);
        addRecipe(new ItemStack(ModItems.INGOT_COPPER.get(), 2), new ItemStack(ModItems.INGOT_RED_COPPER.get()), 3);
        addRecipe(new ItemStack(ModItems.INGOT_STEEL.get(), 2), new ItemStack(ModItems.INGOT_WEAPONSTEEL.get()), 5);
        addRecipe(new ItemStack(ModItems.INGOT_COPPER.get(), 2), new ItemStack(ModItems.INGOT_GUNMETAL.get()), 4);
        addRecipe(new ItemStack(ModItems.INGOT_TUNGSTEN.get()), new ItemStack(ModItems.INGOT_MAGNETIZED_TUNGSTEN.get()), 6);
        addRecipe(new ItemStack(Items.IRON_INGOT), new ItemStack(ModItems.INGOT_STEEL.get()), 3);

        // ===== Batch 5: Nugget decomposition (ingot -> 9 nuggets) =====
        addRecipe(new ItemStack(ModItems.INGOT_URANIUM.get()), new ItemStack(ModItems.NUGGET_URANIUM.get(), 9), 2);
        addRecipe(new ItemStack(ModItems.INGOT_PLUTONIUM.get()), new ItemStack(ModItems.NUGGET_PLUTONIUM.get(), 9), 3);
        addRecipe(new ItemStack(ModItems.INGOT_BERYLLIUM.get()), new ItemStack(ModItems.NUGGET_BERYLLIUM.get(), 9), 2);
        addRecipe(new ItemStack(ModItems.INGOT_LEAD.get()), new ItemStack(ModItems.NUGGET_LEAD.get(), 9), 2);
        addRecipe(new ItemStack(ModItems.INGOT_BISMUTH.get()), new ItemStack(ModItems.NUGGET_BISMUTH.get(), 9), 2);
        addRecipe(new ItemStack(ModItems.INGOT_COBALT.get()), new ItemStack(ModItems.NUGGET_COBALT.get(), 9), 2);
        addRecipe(new ItemStack(ModItems.INGOT_NIOBIUM.get()), new ItemStack(ModItems.NUGGET_NIOBIUM.get(), 9), 3);
        addRecipe(new ItemStack(ModItems.INGOT_TANTALIUM.get()), new ItemStack(ModItems.NUGGET_TANTALIUM.get(), 9), 3);
        addRecipe(new ItemStack(ModItems.INGOT_ZIRCONIUM.get()), new ItemStack(ModItems.NUGGET_ZIRCONIUM.get(), 9), 2);
        addRecipe(new ItemStack(ModItems.INGOT_SILICON.get()), new ItemStack(ModItems.NUGGET_SILICON.get(), 9), 2);

        // ===== Batch 6: Special material processing =====
        addRecipe(new ItemStack(ModItems.INGOT_GRAPHITE.get()), new ItemStack(Items.COAL), 1);
        addRecipe(new ItemStack(ModItems.INGOT_BORON.get()), new ItemStack(ModItems.NUGGET_LEAD.get(), 3), 3);
        addRecipe(new ItemStack(ModItems.INGOT_CADMIUM.get()), new ItemStack(ModItems.NUGGET_LEAD.get(), 2), 2);
        addRecipe(new ItemStack(Items.IRON_BLOCK), new ItemStack(ModItems.INGOT_STEEL.get(), 3), 8);
    }

    public static void addRecipe(ItemStack input, ItemStack output, int hammerCost) {
        recipes.put(new ComparableStack(input.getItem(), 1, input.getDamageValue()),
                new AnvilRecipe(output, hammerCost));
    }

    public static AnvilRecipe getOutput(ItemStack stack) {
        if (stack == null || stack.isEmpty()) return null;
        return recipes.get(new ComparableStack(stack.getItem(), 1, stack.getDamageValue()));
    }

    public static Map<ComparableStack, AnvilRecipe> getRecipes() {
        return recipes;
    }

    public static class AnvilRecipe {
        public ItemStack output;
        public int hammerCost;

        public AnvilRecipe(ItemStack output, int hammerCost) {
            this.output = output;
            this.hammerCost = hammerCost;
        }
    }
}
