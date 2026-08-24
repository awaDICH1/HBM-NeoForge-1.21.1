package com.hbm.inventory.recipes;

import com.hbm.items.ModItems;
import com.hbm.util.ComparableStack;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 电弧熔炉配方系统（P5.13 迁移版）。
 *
 * 迁移自 1.12.2 com.hbm.inventory.recipes.ArcFurnaceRecipes。
 * 机制：矿石/原料 + 电力 → 锭/成品（高温冶炼）。
 */
public class ArcFurnaceRecipes {

    public static LinkedHashMap<ComparableStack, ArcFurnaceRecipe> recipes = new LinkedHashMap<>();

    public static void register() {

        // Vanilla ores -> ingots
        addRecipe(new ItemStack(Blocks.IRON_ORE), new ItemStack(Items.IRON_INGOT, 2), 200);
        addRecipe(new ItemStack(Blocks.GOLD_ORE), new ItemStack(Items.GOLD_INGOT, 2), 200);
        addRecipe(new ItemStack(Blocks.COPPER_ORE), new ItemStack(Items.COPPER_INGOT, 2), 200);
        addRecipe(new ItemStack(Blocks.DEEPSLATE_IRON_ORE), new ItemStack(Items.IRON_INGOT, 2), 250);
        addRecipe(new ItemStack(Blocks.DEEPSLATE_GOLD_ORE), new ItemStack(Items.GOLD_INGOT, 2), 250);
        addRecipe(new ItemStack(Blocks.DEEPSLATE_COPPER_ORE), new ItemStack(Items.COPPER_INGOT, 2), 250);

        // Raw materials -> ingots
        addRecipe(new ItemStack(Items.RAW_IRON), new ItemStack(Items.IRON_INGOT), 150);
        addRecipe(new ItemStack(Items.RAW_GOLD), new ItemStack(Items.GOLD_INGOT), 150);
        addRecipe(new ItemStack(Items.RAW_COPPER), new ItemStack(Items.COPPER_INGOT), 150);

        // HBM ores -> ingots
        addRecipe(new ItemStack(ModItems.INGOT_URANIUM.get()), new ItemStack(ModItems.INGOT_URANIUM.get()), 300);
        addRecipe(new ItemStack(ModItems.INGOT_TITANIUM.get()), new ItemStack(ModItems.INGOT_TITANIUM.get()), 350);
        addRecipe(new ItemStack(ModItems.INGOT_TUNGSTEN.get()), new ItemStack(ModItems.INGOT_TUNGSTEN.get()), 400);
        addRecipe(new ItemStack(ModItems.INGOT_ALUMINIUM.get()), new ItemStack(ModItems.INGOT_ALUMINIUM.get()), 250);
        addRecipe(new ItemStack(ModItems.INGOT_LEAD.get()), new ItemStack(ModItems.INGOT_LEAD.get()), 200);
        addRecipe(new ItemStack(ModItems.INGOT_STEEL.get()), new ItemStack(ModItems.INGOT_STEEL.get()), 300);
        addRecipe(new ItemStack(ModItems.INGOT_BERYLLIUM.get()), new ItemStack(ModItems.INGOT_BERYLLIUM.get()), 400);

        // Sand -> Glass (bonus)
        addRecipe(new ItemStack(Blocks.SAND), new ItemStack(Blocks.GLASS, 2), 100);
        addRecipe(new ItemStack(Blocks.RED_SAND), new ItemStack(Blocks.GLASS, 2), 100);

        // Cobblestone -> Stone
        addRecipe(new ItemStack(Blocks.COBBLESTONE), new ItemStack(Blocks.STONE), 50);

        // ===== Batch 2: HBM advanced material smelting =====
        addRecipe(new ItemStack(ModItems.INGOT_TITANIUM.get()), new ItemStack(ModItems.INGOT_TITANIUM.get()), 350);
        addRecipe(new ItemStack(ModItems.INGOT_COBALT.get()), new ItemStack(ModItems.INGOT_COBALT.get()), 300);
        addRecipe(new ItemStack(ModItems.INGOT_NICKEL.get()), new ItemStack(ModItems.INGOT_NICKEL.get()), 300);
        addRecipe(new ItemStack(ModItems.INGOT_CHROMIUM.get()), new ItemStack(ModItems.INGOT_CHROMIUM.get()), 350);
        addRecipe(new ItemStack(ModItems.INGOT_MOLYBDENUM.get()), new ItemStack(ModItems.INGOT_MOLYBDENUM.get()), 400);
        addRecipe(new ItemStack(ModItems.INGOT_NIOBIUM.get()), new ItemStack(ModItems.INGOT_NIOBIUM.get()), 450);
        addRecipe(new ItemStack(ModItems.INGOT_TANTALIUM.get()), new ItemStack(ModItems.INGOT_TANTALIUM.get()), 450);
        addRecipe(new ItemStack(ModItems.INGOT_ZIRCONIUM.get()), new ItemStack(ModItems.INGOT_ZIRCONIUM.get()), 300);
        addRecipe(new ItemStack(ModItems.INGOT_BISMUTH.get()), new ItemStack(ModItems.INGOT_BISMUTH.get()), 200);
        addRecipe(new ItemStack(ModItems.INGOT_BORON.get()), new ItemStack(ModItems.INGOT_BORON.get()), 300);
        addRecipe(new ItemStack(ModItems.INGOT_GRAPHITE.get()), new ItemStack(ModItems.INGOT_GRAPHITE.get()), 150);
        addRecipe(new ItemStack(ModItems.INGOT_CADMIUM.get()), new ItemStack(ModItems.INGOT_CADMIUM.get()), 250);

        // ===== Batch 3: Advanced material smelting =====
        addRecipe(new ItemStack(ModItems.INGOT_ACTINIUM.get()), new ItemStack(ModItems.INGOT_ACTINIUM.get()), 400);
        addRecipe(new ItemStack(ModItems.INGOT_NEPTUNIUM.get()), new ItemStack(ModItems.INGOT_NEPTUNIUM.get()), 500);
        addRecipe(new ItemStack(ModItems.INGOT_PLUTONIUM.get()), new ItemStack(ModItems.INGOT_PLUTONIUM.get()), 500);
        addRecipe(new ItemStack(ModItems.INGOT_TH232.get()), new ItemStack(ModItems.INGOT_TH232.get()), 350);
        addRecipe(new ItemStack(ModItems.INGOT_U235.get()), new ItemStack(ModItems.INGOT_U235.get()), 450);
        addRecipe(new ItemStack(ModItems.INGOT_U238.get()), new ItemStack(ModItems.INGOT_U238.get()), 350);
        addRecipe(new ItemStack(ModItems.INGOT_CO60.get()), new ItemStack(ModItems.INGOT_CO60.get()), 400);
        addRecipe(new ItemStack(ModItems.INGOT_SR90.get()), new ItemStack(ModItems.INGOT_SR90.get()), 400);
        addRecipe(new ItemStack(ModItems.INGOT_RA226.get()), new ItemStack(ModItems.INGOT_RA226.get()), 450);
        addRecipe(new ItemStack(ModItems.INGOT_AU198.get()), new ItemStack(ModItems.INGOT_AU198.get()), 400);
        addRecipe(new ItemStack(ModItems.INGOT_I131.get()), new ItemStack(ModItems.INGOT_I131.get()), 300);
        addRecipe(new ItemStack(ModItems.INGOT_PB209.get()), new ItemStack(ModItems.INGOT_PB209.get()), 350);

        // ===== Batch 4: Block -> ingot decomposition via arc furnace =====
        addRecipe(new ItemStack(com.hbm.blocks.ModBlocks.BLOCK_URANIUM.get()), new ItemStack(ModItems.INGOT_URANIUM.get(), 9), 500);
        addRecipe(new ItemStack(com.hbm.blocks.ModBlocks.BLOCK_ALUMINIUM.get()), new ItemStack(ModItems.INGOT_ALUMINIUM.get(), 9), 400);
        addRecipe(new ItemStack(com.hbm.blocks.ModBlocks.BLOCK_ASBESTOS.get()), new ItemStack(ModItems.INGOT_ASBESTOS.get(), 9), 400);
        addRecipe(new ItemStack(com.hbm.blocks.ModBlocks.BLOCK_ACTINIUM.get()), new ItemStack(ModItems.INGOT_ACTINIUM.get(), 9), 600);

        // ===== Batch 5: Special material processing =====
        addRecipe(new ItemStack(net.minecraft.world.level.block.Blocks.NETHERRACK), new ItemStack(Items.NETHER_BRICK, 2), 150);
        addRecipe(new ItemStack(net.minecraft.world.level.block.Blocks.STONE), new ItemStack(net.minecraft.world.level.block.Blocks.SMOOTH_STONE), 50);
        addRecipe(new ItemStack(net.minecraft.world.level.block.Blocks.COBBLED_DEEPSLATE), new ItemStack(net.minecraft.world.level.block.Blocks.DEEPSLATE), 80);
        addRecipe(new ItemStack(Items.RAW_IRON_BLOCK), new ItemStack(Items.IRON_BLOCK, 2), 600);
        addRecipe(new ItemStack(Items.RAW_GOLD_BLOCK), new ItemStack(Items.GOLD_BLOCK, 2), 600);
        addRecipe(new ItemStack(Items.RAW_COPPER_BLOCK), new ItemStack(Items.COPPER_BLOCK, 2), 600);
    }

    public static void addRecipe(ItemStack input, ItemStack output, int fluxCost) {
        recipes.put(new ComparableStack(input.getItem(), 1, input.getDamageValue()),
                new ArcFurnaceRecipe(output, fluxCost));
    }

    public static ArcFurnaceRecipe getOutput(ItemStack stack) {
        if (stack == null || stack.isEmpty()) return null;
        return recipes.get(new ComparableStack(stack.getItem(), 1, stack.getDamageValue()));
    }

    public static Map<ComparableStack, ArcFurnaceRecipe> getRecipes() {
        return recipes;
    }

    public static class ArcFurnaceRecipe {
        public ItemStack output;
        public int fluxCost;

        public ArcFurnaceRecipe(ItemStack output, int fluxCost) {
            this.output = output;
            this.fluxCost = fluxCost;
        }
    }
}
