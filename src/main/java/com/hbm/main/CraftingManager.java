package com.hbm.main;

import com.hbm.Tags;
import com.hbm.config.GeneralConfig;
import net.minecraft.core.NonNullList;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * CraftingManager (1.21.1 NeoForge migration)
 *
 * Original 1.12.2 CE used runtime ForgeRegistry registration.
 * NeoForge 1.21.1 uses JSON data-driven recipes via RecipeProvider/RecipeOutput.
 *
 * Migration strategy (per DeepSeek directive):
 * - Keep addXxx method signatures for compatibility
 * - Method bodies use RecipeBuilder API instead of runtime registration
 * - Recipes collected during DataGen phase, output as JSON
 */
public class CraftingManager {

    private static RecipeOutput recipeOutput;
    private static int recipeCounter = 0;

    /**
     * Called by HBMRecipeProvider during DataGen.
     * Sets the RecipeOutput sink and runs all recipe registration.
     */
    public static void init(RecipeOutput output) {
        if (!GeneralConfig.recipes) return;

        recipeOutput = output;
        recipeCounter = 0;

        addCrafting();

        // Custom recipe systems (P5.9)
        com.hbm.inventory.recipes.AnvilRecipes.register();
        com.hbm.inventory.recipes.AssemblyMachineRecipes.register();
        com.hbm.inventory.recipes.ArcFurnaceRecipes.register();

        // Sub-registries (TODO: migrate as their classes become available)
        // SmeltingRecipes.AddSmeltingRec();
        // MineralRecipes.register();
        // RodRecipes.register();
        // ToolRecipes.register();
        // ArmorRecipes.register();
        // WeaponRecipes.register();
        // ConsumableRecipes.register();
        // PowderRecipes.register();
        // ExclusiveRecipes.register();
    }

    /**
     * Main recipe registration method.
     * Original 1.12.2 had ~500 lines of recipe calls here.
     * Recipes are added incrementally as items/blocks are migrated.
     */
    public static void addCrafting() {
        // ===== Batch 1: 1to9 / 9to1 compression recipes (ingot <-> block) =====
        // Only for items that are already migrated to 1.21.1

        add9To1(new ItemStack(com.hbm.items.ModItems.INGOT_URANIUM.get()),
                new ItemStack(com.hbm.blocks.ModBlocks.BLOCK_URANIUM.get()));
        add1To9(new ItemStack(com.hbm.blocks.ModBlocks.BLOCK_URANIUM.get()),
                new ItemStack(com.hbm.items.ModItems.INGOT_URANIUM.get(), 9));

        add9To1(new ItemStack(com.hbm.items.ModItems.INGOT_ALUMINIUM.get()),
                new ItemStack(com.hbm.blocks.ModBlocks.BLOCK_ALUMINIUM.get()));
        add1To9(new ItemStack(com.hbm.blocks.ModBlocks.BLOCK_ALUMINIUM.get()),
                new ItemStack(com.hbm.items.ModItems.INGOT_ALUMINIUM.get(), 9));

        add9To1(new ItemStack(com.hbm.items.ModItems.INGOT_ASBESTOS.get()),
                new ItemStack(com.hbm.blocks.ModBlocks.BLOCK_ASBESTOS.get()));
        add1To9(new ItemStack(com.hbm.blocks.ModBlocks.BLOCK_ASBESTOS.get()),
                new ItemStack(com.hbm.items.ModItems.INGOT_ASBESTOS.get(), 9));

        add9To1(new ItemStack(com.hbm.items.ModItems.INGOT_ACTINIUM.get()),
                new ItemStack(com.hbm.blocks.ModBlocks.BLOCK_ACTINIUM.get()));
        add1To9(new ItemStack(com.hbm.blocks.ModBlocks.BLOCK_ACTINIUM.get()),
                new ItemStack(com.hbm.items.ModItems.INGOT_ACTINIUM.get(), 9));

        add9To1(new ItemStack(com.hbm.items.ModItems.INGOT_AUSTRALIUM.get()),
                new ItemStack(com.hbm.blocks.ModBlocks.BLOCK_AUSTRALIUM.get()));
        add1To9(new ItemStack(com.hbm.blocks.ModBlocks.BLOCK_AUSTRALIUM.get()),
                new ItemStack(com.hbm.items.ModItems.INGOT_AUSTRALIUM.get(), 9));

        add9To1(new ItemStack(com.hbm.items.ModItems.INGOT_STEEL.get()),
                new ItemStack(com.hbm.blocks.ModBlocks.BLOCK_STEEL.get()));
        add1To9(new ItemStack(com.hbm.blocks.ModBlocks.BLOCK_STEEL.get()),
                new ItemStack(com.hbm.items.ModItems.INGOT_STEEL.get(), 9));

        add9To1(new ItemStack(com.hbm.items.ModItems.INGOT_LEAD.get()),
                new ItemStack(com.hbm.blocks.ModBlocks.BLOCK_LEAD.get()));
        add1To9(new ItemStack(com.hbm.blocks.ModBlocks.BLOCK_LEAD.get()),
                new ItemStack(com.hbm.items.ModItems.INGOT_LEAD.get(), 9));

        add9To1(new ItemStack(com.hbm.items.ModItems.INGOT_COPPER.get()),
                new ItemStack(com.hbm.blocks.ModBlocks.BLOCK_COPPER.get()));
        add1To9(new ItemStack(com.hbm.blocks.ModBlocks.BLOCK_COPPER.get()),
                new ItemStack(com.hbm.items.ModItems.INGOT_COPPER.get(), 9));

        add9To1(new ItemStack(com.hbm.items.ModItems.INGOT_BERYLLIUM.get()),
                new ItemStack(com.hbm.blocks.ModBlocks.BLOCK_BERYLLIUM.get()));
        add1To9(new ItemStack(com.hbm.blocks.ModBlocks.BLOCK_BERYLLIUM.get()),
                new ItemStack(com.hbm.items.ModItems.INGOT_BERYLLIUM.get(), 9));

        add9To1(new ItemStack(com.hbm.items.ModItems.INGOT_TUNGSTEN.get()),
                new ItemStack(com.hbm.blocks.ModBlocks.BLOCK_TUNGSTEN.get()));
        add1To9(new ItemStack(com.hbm.blocks.ModBlocks.BLOCK_TUNGSTEN.get()),
                new ItemStack(com.hbm.items.ModItems.INGOT_TUNGSTEN.get(), 9));

        // Nickel
        add9To1(new ItemStack(com.hbm.items.ModItems.INGOT_NICKEL.get()),
                new ItemStack(com.hbm.blocks.ModBlocks.BLOCK_NICKEL.get()));
        add1To9(new ItemStack(com.hbm.blocks.ModBlocks.BLOCK_NICKEL.get()),
                new ItemStack(com.hbm.items.ModItems.INGOT_NICKEL.get(), 9));

        // Chromium
        add9To1(new ItemStack(com.hbm.items.ModItems.INGOT_CHROMIUM.get()),
                new ItemStack(com.hbm.blocks.ModBlocks.BLOCK_CHROMIUM.get()));
        add1To9(new ItemStack(com.hbm.blocks.ModBlocks.BLOCK_CHROMIUM.get()),
                new ItemStack(com.hbm.items.ModItems.INGOT_CHROMIUM.get(), 9));

        // Molybdenum
        add9To1(new ItemStack(com.hbm.items.ModItems.INGOT_MOLYBDENUM.get()),
                new ItemStack(com.hbm.blocks.ModBlocks.BLOCK_MOLYBDENUM.get()));
        add1To9(new ItemStack(com.hbm.blocks.ModBlocks.BLOCK_MOLYBDENUM.get()),
                new ItemStack(com.hbm.items.ModItems.INGOT_MOLYBDENUM.get(), 9));

        // Silicon
        add9To1(new ItemStack(com.hbm.items.ModItems.INGOT_SILICON.get()),
                new ItemStack(com.hbm.blocks.ModBlocks.BLOCK_SILICON.get()));
        add1To9(new ItemStack(com.hbm.blocks.ModBlocks.BLOCK_SILICON.get()),
                new ItemStack(com.hbm.items.ModItems.INGOT_SILICON.get(), 9));

        // Niobium
        add9To1(new ItemStack(com.hbm.items.ModItems.INGOT_NIOBIUM.get()),
                new ItemStack(com.hbm.blocks.ModBlocks.BLOCK_NIOBIUM.get()));
        add1To9(new ItemStack(com.hbm.blocks.ModBlocks.BLOCK_NIOBIUM.get()),
                new ItemStack(com.hbm.items.ModItems.INGOT_NIOBIUM.get(), 9));

        // Tantalium
        add9To1(new ItemStack(com.hbm.items.ModItems.INGOT_TANTALIUM.get()),
                new ItemStack(com.hbm.blocks.ModBlocks.BLOCK_TANTALIUM.get()));
        add1To9(new ItemStack(com.hbm.blocks.ModBlocks.BLOCK_TANTALIUM.get()),
                new ItemStack(com.hbm.items.ModItems.INGOT_TANTALIUM.get(), 9));

        // Lanthanium
        add9To1(new ItemStack(com.hbm.items.ModItems.INGOT_LANTHANIUM.get()),
                new ItemStack(com.hbm.blocks.ModBlocks.BLOCK_LANTHANIUM.get()));
        add1To9(new ItemStack(com.hbm.blocks.ModBlocks.BLOCK_LANTHANIUM.get()),
                new ItemStack(com.hbm.items.ModItems.INGOT_LANTHANIUM.get(), 9));

        // ===== Batch 2: Tool recipes (5 materials x 5 tools = 25 recipes) =====

        // Uranium tools
        addSword(com.hbm.items.ModItems.INGOT_URANIUM.get(), com.hbm.items.ModItems.SWORD_URANIUM.get());
        addPickaxe(com.hbm.items.ModItems.INGOT_URANIUM.get(), com.hbm.items.ModItems.PICKAXE_URANIUM.get());
        addAxe(com.hbm.items.ModItems.INGOT_URANIUM.get(), com.hbm.items.ModItems.AXE_URANIUM.get());
        addShovel(com.hbm.items.ModItems.INGOT_URANIUM.get(), com.hbm.items.ModItems.SHOVEL_URANIUM.get());
        addHoe(com.hbm.items.ModItems.INGOT_URANIUM.get(), com.hbm.items.ModItems.HOE_URANIUM.get());

        // Copper tools
        addSword(com.hbm.items.ModItems.INGOT_COPPER.get(), com.hbm.items.ModItems.SWORD_COPPER.get());
        addPickaxe(com.hbm.items.ModItems.INGOT_COPPER.get(), com.hbm.items.ModItems.PICKAXE_COPPER.get());
        addAxe(com.hbm.items.ModItems.INGOT_COPPER.get(), com.hbm.items.ModItems.AXE_COPPER.get());
        addShovel(com.hbm.items.ModItems.INGOT_COPPER.get(), com.hbm.items.ModItems.SHOVEL_COPPER.get());
        addHoe(com.hbm.items.ModItems.INGOT_COPPER.get(), com.hbm.items.ModItems.HOE_COPPER.get());

        // Lead tools
        addSword(com.hbm.items.ModItems.INGOT_LEAD.get(), com.hbm.items.ModItems.SWORD_LEAD.get());
        addPickaxe(com.hbm.items.ModItems.INGOT_LEAD.get(), com.hbm.items.ModItems.PICKAXE_LEAD.get());
        addAxe(com.hbm.items.ModItems.INGOT_LEAD.get(), com.hbm.items.ModItems.AXE_LEAD.get());
        addShovel(com.hbm.items.ModItems.INGOT_LEAD.get(), com.hbm.items.ModItems.SHOVEL_LEAD.get());
        addHoe(com.hbm.items.ModItems.INGOT_LEAD.get(), com.hbm.items.ModItems.HOE_LEAD.get());

        // Steel tools
        addSword(com.hbm.items.ModItems.INGOT_STEEL.get(), com.hbm.items.ModItems.SWORD_STEEL.get());
        addPickaxe(com.hbm.items.ModItems.INGOT_STEEL.get(), com.hbm.items.ModItems.PICKAXE_STEEL.get());
        addAxe(com.hbm.items.ModItems.INGOT_STEEL.get(), com.hbm.items.ModItems.AXE_STEEL.get());
        addShovel(com.hbm.items.ModItems.INGOT_STEEL.get(), com.hbm.items.ModItems.SHOVEL_STEEL.get());
        addHoe(com.hbm.items.ModItems.INGOT_STEEL.get(), com.hbm.items.ModItems.HOE_STEEL.get());

        // Aluminium tools
        addSword(com.hbm.items.ModItems.INGOT_ALUMINIUM.get(), com.hbm.items.ModItems.SWORD_ALUMINIUM.get());
        addPickaxe(com.hbm.items.ModItems.INGOT_ALUMINIUM.get(), com.hbm.items.ModItems.PICKAXE_ALUMINIUM.get());
        addAxe(com.hbm.items.ModItems.INGOT_ALUMINIUM.get(), com.hbm.items.ModItems.AXE_ALUMINIUM.get());
        addShovel(com.hbm.items.ModItems.INGOT_ALUMINIUM.get(), com.hbm.items.ModItems.SHOVEL_ALUMINIUM.get());
        addHoe(com.hbm.items.ModItems.INGOT_ALUMINIUM.get(), com.hbm.items.ModItems.HOE_ALUMINIUM.get());

        // Nickel tools
        addSword(com.hbm.items.ModItems.INGOT_NICKEL.get(), com.hbm.items.ModItems.SWORD_NICKEL.get());
        addPickaxe(com.hbm.items.ModItems.INGOT_NICKEL.get(), com.hbm.items.ModItems.PICKAXE_NICKEL.get());
        addAxe(com.hbm.items.ModItems.INGOT_NICKEL.get(), com.hbm.items.ModItems.AXE_NICKEL.get());
        addShovel(com.hbm.items.ModItems.INGOT_NICKEL.get(), com.hbm.items.ModItems.SHOVEL_NICKEL.get());
        addHoe(com.hbm.items.ModItems.INGOT_NICKEL.get(), com.hbm.items.ModItems.HOE_NICKEL.get());

        // Chromium tools
        addSword(com.hbm.items.ModItems.INGOT_CHROMIUM.get(), com.hbm.items.ModItems.SWORD_CHROMIUM.get());
        addPickaxe(com.hbm.items.ModItems.INGOT_CHROMIUM.get(), com.hbm.items.ModItems.PICKAXE_CHROMIUM.get());
        addAxe(com.hbm.items.ModItems.INGOT_CHROMIUM.get(), com.hbm.items.ModItems.AXE_CHROMIUM.get());
        addShovel(com.hbm.items.ModItems.INGOT_CHROMIUM.get(), com.hbm.items.ModItems.SHOVEL_CHROMIUM.get());
        addHoe(com.hbm.items.ModItems.INGOT_CHROMIUM.get(), com.hbm.items.ModItems.HOE_CHROMIUM.get());

        // Molybdenum tools
        addSword(com.hbm.items.ModItems.INGOT_MOLYBDENUM.get(), com.hbm.items.ModItems.SWORD_MOLYBDENUM.get());
        addPickaxe(com.hbm.items.ModItems.INGOT_MOLYBDENUM.get(), com.hbm.items.ModItems.PICKAXE_MOLYBDENUM.get());
        addAxe(com.hbm.items.ModItems.INGOT_MOLYBDENUM.get(), com.hbm.items.ModItems.AXE_MOLYBDENUM.get());
        addShovel(com.hbm.items.ModItems.INGOT_MOLYBDENUM.get(), com.hbm.items.ModItems.SHOVEL_MOLYBDENUM.get());
        addHoe(com.hbm.items.ModItems.INGOT_MOLYBDENUM.get(), com.hbm.items.ModItems.HOE_MOLYBDENUM.get());

        // Silicon tools
        addSword(com.hbm.items.ModItems.INGOT_SILICON.get(), com.hbm.items.ModItems.SWORD_SILICON.get());
        addPickaxe(com.hbm.items.ModItems.INGOT_SILICON.get(), com.hbm.items.ModItems.PICKAXE_SILICON.get());
        addAxe(com.hbm.items.ModItems.INGOT_SILICON.get(), com.hbm.items.ModItems.AXE_SILICON.get());
        addShovel(com.hbm.items.ModItems.INGOT_SILICON.get(), com.hbm.items.ModItems.SHOVEL_SILICON.get());
        addHoe(com.hbm.items.ModItems.INGOT_SILICON.get(), com.hbm.items.ModItems.HOE_SILICON.get());

        // Niobium tools
        addSword(com.hbm.items.ModItems.INGOT_NIOBIUM.get(), com.hbm.items.ModItems.SWORD_NIOBIUM.get());
        addPickaxe(com.hbm.items.ModItems.INGOT_NIOBIUM.get(), com.hbm.items.ModItems.PICKAXE_NIOBIUM.get());
        addAxe(com.hbm.items.ModItems.INGOT_NIOBIUM.get(), com.hbm.items.ModItems.AXE_NIOBIUM.get());
        addShovel(com.hbm.items.ModItems.INGOT_NIOBIUM.get(), com.hbm.items.ModItems.SHOVEL_NIOBIUM.get());
        addHoe(com.hbm.items.ModItems.INGOT_NIOBIUM.get(), com.hbm.items.ModItems.HOE_NIOBIUM.get());

        // Tantalium tools
        addSword(com.hbm.items.ModItems.INGOT_TANTALIUM.get(), com.hbm.items.ModItems.SWORD_TANTALIUM.get());
        addPickaxe(com.hbm.items.ModItems.INGOT_TANTALIUM.get(), com.hbm.items.ModItems.PICKAXE_TANTALIUM.get());
        addAxe(com.hbm.items.ModItems.INGOT_TANTALIUM.get(), com.hbm.items.ModItems.AXE_TANTALIUM.get());
        addShovel(com.hbm.items.ModItems.INGOT_TANTALIUM.get(), com.hbm.items.ModItems.SHOVEL_TANTALIUM.get());
        addHoe(com.hbm.items.ModItems.INGOT_TANTALIUM.get(), com.hbm.items.ModItems.HOE_TANTALIUM.get());

        // Lanthanium tools
        addSword(com.hbm.items.ModItems.INGOT_LANTHANIUM.get(), com.hbm.items.ModItems.SWORD_LANTHANIUM.get());
        addPickaxe(com.hbm.items.ModItems.INGOT_LANTHANIUM.get(), com.hbm.items.ModItems.PICKAXE_LANTHANIUM.get());
        addAxe(com.hbm.items.ModItems.INGOT_LANTHANIUM.get(), com.hbm.items.ModItems.AXE_LANTHANIUM.get());
        addShovel(com.hbm.items.ModItems.INGOT_LANTHANIUM.get(), com.hbm.items.ModItems.SHOVEL_LANTHANIUM.get());
        addHoe(com.hbm.items.ModItems.INGOT_LANTHANIUM.get(), com.hbm.items.ModItems.HOE_LANTHANIUM.get());

        // ===== Batch 3: Slab/Stair recipes (6 blocks x 3 recipes = 18 recipes) =====
        addSlabStair(com.hbm.blocks.ModBlocks.ASPHALT_SLAB.get(), com.hbm.blocks.ModBlocks.ASPHALT_STAIRS.get(), com.hbm.blocks.ModBlocks.ASPHALT.get());
        addSlabStair(com.hbm.blocks.ModBlocks.BRICK_RED_SLAB.get(), com.hbm.blocks.ModBlocks.BRICK_RED_STAIRS.get(), com.hbm.blocks.ModBlocks.BRICK_RED.get());
        addSlabStair(com.hbm.blocks.ModBlocks.CONCRETE_SLAB.get(), com.hbm.blocks.ModBlocks.CONCRETE_STAIRS.get(), com.hbm.blocks.ModBlocks.CONCRETE.get());
        addSlabStair(com.hbm.blocks.ModBlocks.CONCRETE_SMOOTH_SLAB.get(), com.hbm.blocks.ModBlocks.CONCRETE_SMOOTH_STAIRS.get(), com.hbm.blocks.ModBlocks.CONCRETE_SMOOTH.get());
        addSlabStair(com.hbm.blocks.ModBlocks.DUCRETE_SLAB.get(), com.hbm.blocks.ModBlocks.DUCRETE_STAIRS.get(), com.hbm.blocks.ModBlocks.DUCRETE.get());
        addSlabStair(com.hbm.blocks.ModBlocks.DUCRETE_SMOOTH_SLAB.get(), com.hbm.blocks.ModBlocks.DUCRETE_SMOOTH_STAIRS.get(), com.hbm.blocks.ModBlocks.DUCRETE_SMOOTH.get());

        // ===== Batch 4: Building material recipes (9 recipes) =====
        // Glass panes (3 recipes)
        addRecipeAuto(new ItemStack(net.minecraft.world.item.Items.GLASS_PANE, 16),
                "GGG", 'G', net.minecraft.world.level.block.Blocks.GLASS);
        addRecipeAuto(new ItemStack(net.minecraft.world.item.Items.WHITE_STAINED_GLASS_PANE, 16),
                "GGG", 'G', net.minecraft.world.level.block.Blocks.WHITE_STAINED_GLASS);
        addRecipeAuto(new ItemStack(net.minecraft.world.item.Items.BLACK_STAINED_GLASS_PANE, 16),
                "GGG", 'G', net.minecraft.world.level.block.Blocks.BLACK_STAINED_GLASS);

        // Rails (1 recipe)
        addRecipeAuto(new ItemStack(net.minecraft.world.item.Items.RAIL, 16),
                "I I", "ISI", "I I",
                'I', net.minecraft.world.item.Items.IRON_INGOT,
                'S', net.minecraft.world.item.Items.STICK);

        // Concrete from powder (5 recipes, shapeless)
        addShapelessAuto(new ItemStack(net.minecraft.world.level.block.Blocks.WHITE_CONCRETE, 4),
                net.minecraft.world.level.block.Blocks.WHITE_CONCRETE_POWDER,
                net.minecraft.world.level.block.Blocks.WHITE_CONCRETE_POWDER,
                net.minecraft.world.level.block.Blocks.WHITE_CONCRETE_POWDER,
                net.minecraft.world.level.block.Blocks.WHITE_CONCRETE_POWDER,
                net.minecraft.world.item.Items.WATER_BUCKET);
        addShapelessAuto(new ItemStack(net.minecraft.world.level.block.Blocks.BLACK_CONCRETE, 4),
                net.minecraft.world.level.block.Blocks.BLACK_CONCRETE_POWDER,
                net.minecraft.world.level.block.Blocks.BLACK_CONCRETE_POWDER,
                net.minecraft.world.level.block.Blocks.BLACK_CONCRETE_POWDER,
                net.minecraft.world.level.block.Blocks.BLACK_CONCRETE_POWDER,
                net.minecraft.world.item.Items.WATER_BUCKET);
        addShapelessAuto(new ItemStack(net.minecraft.world.level.block.Blocks.RED_CONCRETE, 4),
                net.minecraft.world.level.block.Blocks.RED_CONCRETE_POWDER,
                net.minecraft.world.level.block.Blocks.RED_CONCRETE_POWDER,
                net.minecraft.world.level.block.Blocks.RED_CONCRETE_POWDER,
                net.minecraft.world.level.block.Blocks.RED_CONCRETE_POWDER,
                net.minecraft.world.item.Items.WATER_BUCKET);
        addShapelessAuto(new ItemStack(net.minecraft.world.level.block.Blocks.BLUE_CONCRETE, 4),
                net.minecraft.world.level.block.Blocks.BLUE_CONCRETE_POWDER,
                net.minecraft.world.level.block.Blocks.BLUE_CONCRETE_POWDER,
                net.minecraft.world.level.block.Blocks.BLUE_CONCRETE_POWDER,
                net.minecraft.world.level.block.Blocks.BLUE_CONCRETE_POWDER,
                net.minecraft.world.item.Items.WATER_BUCKET);
        addShapelessAuto(new ItemStack(net.minecraft.world.level.block.Blocks.GREEN_CONCRETE, 4),
                net.minecraft.world.level.block.Blocks.GREEN_CONCRETE_POWDER,
                net.minecraft.world.level.block.Blocks.GREEN_CONCRETE_POWDER,
                net.minecraft.world.level.block.Blocks.GREEN_CONCRETE_POWDER,
                net.minecraft.world.level.block.Blocks.GREEN_CONCRETE_POWDER,
                net.minecraft.world.item.Items.WATER_BUCKET);

        // ===== Batch 5: Machine block recipes (7 recipes) =====
        // Furnace: 8 cobblestone -> 1 furnace
        addRecipeAuto(new ItemStack(net.minecraft.world.level.block.Blocks.FURNACE, 1),
                "CCC", "C C", "CCC",
                'C', net.minecraft.world.level.block.Blocks.COBBLESTONE);

        // Blast furnace: 5 iron ingots + 1 furnace -> 1 blast furnace
        addRecipeAuto(new ItemStack(net.minecraft.world.level.block.Blocks.BLAST_FURNACE, 1),
                "III", "IFI", "III",
                'I', net.minecraft.world.item.Items.IRON_INGOT,
                'F', net.minecraft.world.level.block.Blocks.FURNACE);

        // Smoker: 4 oak logs + 1 furnace -> 1 smoker
        addRecipeAuto(new ItemStack(net.minecraft.world.level.block.Blocks.SMOKER, 1),
                "L L", "LFL", "L L",
                'L', net.minecraft.world.level.block.Blocks.OAK_LOG,
                'F', net.minecraft.world.level.block.Blocks.FURNACE);

        // Stonecutter: 3 stone + 1 iron ingot -> 1 stonecutter
        addRecipeAuto(new ItemStack(net.minecraft.world.level.block.Blocks.STONECUTTER, 1),
                " S ", "III",
                'S', net.minecraft.world.level.block.Blocks.STONE,
                'I', net.minecraft.world.item.Items.IRON_INGOT);

        // Crafting table: 4 oak planks -> 1 crafting table
        addRecipeAuto(new ItemStack(net.minecraft.world.level.block.Blocks.CRAFTING_TABLE, 1),
                "PP", "PP",
                'P', net.minecraft.world.level.block.Blocks.OAK_PLANKS);

        // Chest: 8 oak planks -> 1 chest
        addRecipeAuto(new ItemStack(net.minecraft.world.level.block.Blocks.CHEST, 1),
                "PPP", "P P", "PPP",
                'P', net.minecraft.world.level.block.Blocks.OAK_PLANKS);

        // Barrel: 6 oak planks + 1 oak slab -> 1 barrel
        addRecipeAuto(new ItemStack(net.minecraft.world.level.block.Blocks.BARREL, 1),
                "P P", "P P", "PSP",
                'P', net.minecraft.world.level.block.Blocks.OAK_PLANKS,
                'S', net.minecraft.world.level.block.Blocks.OAK_SLAB);

        // ===== Batch 9: Compressor recipe =====
        // Compressor: 7 iron ingots + 1 piston + 1 redstone -> 1 compressor
        addRecipeAuto(new ItemStack(com.hbm.blocks.ModBlocks.COMPRESSOR.get(), 1),
                "III", "IPR", "III",
                'I', net.minecraft.world.item.Items.IRON_INGOT,
                'P', net.minecraft.world.level.block.Blocks.PISTON,
                'R', net.minecraft.world.item.Items.REDSTONE);

        // ===== Batch 11: FluidTank + RBMKConsole recipes =====
        // FluidTank: 8 iron ingots + 1 glass -> 1 fluid_tank
        addRecipeAuto(new ItemStack(com.hbm.blocks.ModBlocks.FLUID_TANK.get(), 1),
                "III", "IGI", "III",
                'I', net.minecraft.world.item.Items.IRON_INGOT,
                'G', net.minecraft.world.level.block.Blocks.GLASS);

        // RBMKConsole: 4 iron blocks + 1 redstone + 1 iron ingot + 1 piston -> 1 rbmk_console
        addRecipeAuto(new ItemStack(com.hbm.blocks.ModBlocks.RBMK_CONSOLE.get(), 1),
                "IBI", "BRB", "IPI",
                'I', net.minecraft.world.item.Items.IRON_INGOT,
                'B', net.minecraft.world.level.block.Blocks.IRON_BLOCK,
                'R', net.minecraft.world.item.Items.REDSTONE,
                'P', net.minecraft.world.level.block.Blocks.PISTON);

        // ===== Batch 12: ChemicalReactor recipe =====
        // ChemicalReactor: 4 iron + 2 glass + 1 copper + 1 redstone + 1 bucket -> 1 chemical_reactor
        addRecipeAuto(new ItemStack(com.hbm.blocks.ModBlocks.CHEMICAL_REACTOR.get(), 1),
                "IGI", "CRC", "IGI",
                'I', net.minecraft.world.item.Items.IRON_INGOT,
                'G', net.minecraft.world.level.block.Blocks.GLASS,
                'R', net.minecraft.world.item.Items.REDSTONE,
                'C', net.minecraft.world.item.Items.COPPER_INGOT);

        // ===== Batch 13: ArcFurnace recipe =====
        // ArcFurnace: 7 bricks + 1 iron ingot + 1 redstone -> 1 arc_furnace
        addRecipeAuto(new ItemStack(com.hbm.blocks.ModBlocks.ARC_FURNACE.get(), 1),
                "BBB", "BIB", "BRB",
                'B', net.minecraft.world.level.block.Blocks.BRICKS,
                'I', net.minecraft.world.item.Items.IRON_INGOT,
                'R', net.minecraft.world.item.Items.REDSTONE);

        // ===== Batch 14: Centrifuge recipe =====
        // Centrifuge: 8 iron + 1 redstone -> 1 centrifuge
        addRecipeAuto(new ItemStack(com.hbm.blocks.ModBlocks.CENTRIFUGE.get(), 1),
                "III", "IRI", "III",
                'I', net.minecraft.world.item.Items.IRON_INGOT,
                'R', net.minecraft.world.item.Items.REDSTONE);

        // ===== Batch 15: Crusher recipe =====
        // Crusher: 6 iron + 1 piston + 1 redstone + 1 cobblestone -> 1 crusher
        addRecipeAuto(new ItemStack(com.hbm.blocks.ModBlocks.CRUSHER.get(), 1),
                "III", "IPC", "IRI",
                'I', net.minecraft.world.item.Items.IRON_INGOT,
                'P', net.minecraft.world.level.block.Blocks.PISTON,
                'R', net.minecraft.world.item.Items.REDSTONE,
                'C', net.minecraft.world.level.block.Blocks.COBBLESTONE);

        // ===== Batch 16: FluidReactor recipe =====
        // FluidReactor: 4 iron + 2 copper + 1 glass + 1 redstone + 1 bucket -> 1 fluid_reactor
        addRecipeAuto(new ItemStack(com.hbm.blocks.ModBlocks.FLUID_REACTOR.get(), 1),
                "IGI", "CRC", "IGI",
                'I', net.minecraft.world.item.Items.IRON_INGOT,
                'G', net.minecraft.world.level.block.Blocks.GLASS,
                'R', net.minecraft.world.item.Items.REDSTONE,
                'C', net.minecraft.world.item.Items.COPPER_INGOT);

        // ===== Batch 17: Assembler recipe =====
        // Assembler: 5 iron + 1 piston + 1 redstone + 1 crafting table + 1 copper -> 1 assembler
        addRecipeAuto(new ItemStack(com.hbm.blocks.ModBlocks.ASSEMBLER.get(), 1),
                "III", "PCR", "ICI",
                'I', net.minecraft.world.item.Items.IRON_INGOT,
                'P', net.minecraft.world.level.block.Blocks.PISTON,
                'C', net.minecraft.world.item.Items.COPPER_INGOT,
                'R', net.minecraft.world.item.Items.REDSTONE);

        // ===== Batch 18: RBMKReactor recipe =====
        // RBMKReactor: 4 iron blocks + 2 redstone blocks + 1 piston + 1 iron ingot + 1 bucket -> 1 rbmk_reactor
        addRecipeAuto(new ItemStack(com.hbm.blocks.ModBlocks.RBMK_REACTOR.get(), 1),
                "IBI", "BRB", "IPI",
                'I', net.minecraft.world.item.Items.IRON_INGOT,
                'B', net.minecraft.world.level.block.Blocks.IRON_BLOCK,
                'R', net.minecraft.world.item.Items.REDSTONE,
                'P', net.minecraft.world.level.block.Blocks.PISTON);

        // ===== Batch 19: HeatExchanger recipe =====
        // HeatExchanger: 6 copper + 2 iron + 1 glass -> 1 heat_exchanger
        addRecipeAuto(new ItemStack(com.hbm.blocks.ModBlocks.HEAT_EXCHANGER.get(), 1),
                "ICI", "CGC", "ICI",
                'I', net.minecraft.world.item.Items.IRON_INGOT,
                'C', net.minecraft.world.item.Items.COPPER_INGOT,
                'G', net.minecraft.world.level.block.Blocks.GLASS);

        // ===== Batch 20: ParticleAccelerator recipe =====
        // ParticleAccelerator: 6 iron + 2 redstone + 1 iron block -> 1 particle_accelerator
        addRecipeAuto(new ItemStack(com.hbm.blocks.ModBlocks.PARTICLE_ACCELERATOR.get(), 1),
                "IRI", "IBI", "IRI",
                'I', net.minecraft.world.item.Items.IRON_INGOT,
                'R', net.minecraft.world.item.Items.REDSTONE,
                'B', net.minecraft.world.level.block.Blocks.IRON_BLOCK);

        // ===== Batch 21: Laser recipe =====
        // Laser: 4 iron + 2 redstone + 1 glass + 1 diamond + 1 copper -> 1 laser
        addRecipeAuto(new ItemStack(com.hbm.blocks.ModBlocks.LASER.get(), 1),
                "IRI", "DGD", "ICI",
                'I', net.minecraft.world.item.Items.IRON_INGOT,
                'R', net.minecraft.world.item.Items.REDSTONE,
                'D', net.minecraft.world.item.Items.DIAMOND,
                'G', net.minecraft.world.level.block.Blocks.GLASS,
                'C', net.minecraft.world.item.Items.COPPER_INGOT);

        // ===== Batch 5b: Nugget <-> Ingot mineral sets (28 materials x 2 = 56 recipes) =====
        addNuggetIngotPair(com.hbm.items.ModItems.NUGGET_URANIUM.get(), com.hbm.items.ModItems.INGOT_URANIUM.get());
        addNuggetIngotPair(com.hbm.items.ModItems.NUGGET_LEAD.get(), com.hbm.items.ModItems.INGOT_LEAD.get());
        addNuggetIngotPair(com.hbm.items.ModItems.NUGGET_BERYLLIUM.get(), com.hbm.items.ModItems.INGOT_BERYLLIUM.get());
        addNuggetIngotPair(com.hbm.items.ModItems.NUGGET_COBALT.get(), com.hbm.items.ModItems.INGOT_COBALT.get());
        addNuggetIngotPair(com.hbm.items.ModItems.NUGGET_BISMUTH.get(), com.hbm.items.ModItems.INGOT_BISMUTH.get());
        addNuggetIngotPair(com.hbm.items.ModItems.NUGGET_NIOBIUM.get(), com.hbm.items.ModItems.INGOT_NIOBIUM.get());
        addNuggetIngotPair(com.hbm.items.ModItems.NUGGET_TANTALIUM.get(), com.hbm.items.ModItems.INGOT_TANTALIUM.get());
        addNuggetIngotPair(com.hbm.items.ModItems.NUGGET_SILICON.get(), com.hbm.items.ModItems.INGOT_SILICON.get());
        addNuggetIngotPair(com.hbm.items.ModItems.NUGGET_ACTINIUM.get(), com.hbm.items.ModItems.INGOT_ACTINIUM.get());
        addNuggetIngotPair(com.hbm.items.ModItems.NUGGET_AUSTRALIUM.get(), com.hbm.items.ModItems.INGOT_AUSTRALIUM.get());
        addNuggetIngotPair(com.hbm.items.ModItems.NUGGET_NEPTUNIUM.get(), com.hbm.items.ModItems.INGOT_NEPTUNIUM.get());
        addNuggetIngotPair(com.hbm.items.ModItems.NUGGET_PLUTONIUM.get(), com.hbm.items.ModItems.INGOT_PLUTONIUM.get());
        addNuggetIngotPair(com.hbm.items.ModItems.NUGGET_POLONIUM.get(), com.hbm.items.ModItems.INGOT_POLONIUM.get());
        addNuggetIngotPair(com.hbm.items.ModItems.NUGGET_PU238.get(), com.hbm.items.ModItems.INGOT_PU238.get());
        addNuggetIngotPair(com.hbm.items.ModItems.NUGGET_PU239.get(), com.hbm.items.ModItems.INGOT_PU239.get());
        addNuggetIngotPair(com.hbm.items.ModItems.NUGGET_PU240.get(), com.hbm.items.ModItems.INGOT_PU240.get());
        addNuggetIngotPair(com.hbm.items.ModItems.NUGGET_PU_MIX.get(), com.hbm.items.ModItems.INGOT_PU_MIX.get());
        addNuggetIngotPair(com.hbm.items.ModItems.NUGGET_U233.get(), com.hbm.items.ModItems.INGOT_U233.get());
        addNuggetIngotPair(com.hbm.items.ModItems.NUGGET_U235.get(), com.hbm.items.ModItems.INGOT_U235.get());
        addNuggetIngotPair(com.hbm.items.ModItems.NUGGET_U238.get(), com.hbm.items.ModItems.INGOT_U238.get());
        addNuggetIngotPair(com.hbm.items.ModItems.NUGGET_RA226.get(), com.hbm.items.ModItems.INGOT_RA226.get());
        addNuggetIngotPair(com.hbm.items.ModItems.NUGGET_DESH.get(), com.hbm.items.ModItems.INGOT_DESH.get());
        addNuggetIngotPair(com.hbm.items.ModItems.NUGGET_DINEUTRONIUM.get(), com.hbm.items.ModItems.INGOT_DINEUTRONIUM.get());
        addNuggetIngotPair(com.hbm.items.ModItems.NUGGET_EUPHEMIUM.get(), com.hbm.items.ModItems.INGOT_EUPHEMIUM.get());
        addNuggetIngotPair(com.hbm.items.ModItems.NUGGET_SCHRABIDIUM.get(), com.hbm.items.ModItems.INGOT_SCHRABIDIUM.get());
        addNuggetIngotPair(com.hbm.items.ModItems.NUGGET_SOLINIUM.get(), com.hbm.items.ModItems.INGOT_SOLINIUM.get());
        addNuggetIngotPair(com.hbm.items.ModItems.NUGGET_ZIRCONIUM.get(), com.hbm.items.ModItems.INGOT_ZIRCONIUM.get());

        // ===== Batch 5c: Extended nugget <-> ingot pairs (10 materials x 2 = 20 recipes) =====
        addNuggetIngotPair(com.hbm.items.ModItems.NUGGET_AM_MIX.get(), com.hbm.items.ModItems.INGOT_AM_MIX.get());
        addNuggetIngotPair(com.hbm.items.ModItems.NUGGET_AM241.get(), com.hbm.items.ModItems.INGOT_AM241.get());
        addNuggetIngotPair(com.hbm.items.ModItems.NUGGET_AM242.get(), com.hbm.items.ModItems.INGOT_AM242.get());
        addNuggetIngotPair(com.hbm.items.ModItems.NUGGET_ARSENIC.get(), com.hbm.items.ModItems.INGOT_ARSENIC.get());
        addNuggetIngotPair(com.hbm.items.ModItems.NUGGET_AU198.get(), com.hbm.items.ModItems.INGOT_AU198.get());
        addNuggetIngotPair(com.hbm.items.ModItems.NUGGET_CO60.get(), com.hbm.items.ModItems.INGOT_CO60.get());
        addNuggetIngotPair(com.hbm.items.ModItems.NUGGET_GH336.get(), com.hbm.items.ModItems.INGOT_GH336.get());
        addNuggetIngotPair(com.hbm.items.ModItems.NUGGET_OSMIRIDIUM.get(), com.hbm.items.ModItems.INGOT_OSMIRIDIUM.get());
        addNuggetIngotPair(com.hbm.items.ModItems.NUGGET_PB209.get(), com.hbm.items.ModItems.INGOT_PB209.get());
        addNuggetIngotPair(com.hbm.items.ModItems.NUGGET_PU241.get(), com.hbm.items.ModItems.INGOT_PU241.get());

        // ===== Batch 22: Plate recipes (20 materials x 1 = 20 recipes) =====
        addPlate(net.minecraft.world.item.Items.IRON_INGOT, com.hbm.items.ModItems.PLATE_IRON.get());
        addPlate(com.hbm.items.ModItems.INGOT_COPPER.get(), com.hbm.items.ModItems.PLATE_COPPER.get());
        addPlate(com.hbm.items.ModItems.INGOT_TITANIUM.get(), com.hbm.items.ModItems.PLATE_TITANIUM.get());
        addPlate(com.hbm.items.ModItems.INGOT_ALUMINIUM.get(), com.hbm.items.ModItems.PLATE_ALUMINIUM.get());
        addPlate(net.minecraft.world.item.Items.GOLD_INGOT, com.hbm.items.ModItems.PLATE_GOLD.get());
        addPlate(com.hbm.items.ModItems.INGOT_STEEL.get(), com.hbm.items.ModItems.PLATE_STEEL.get());
        addPlate(com.hbm.items.ModItems.INGOT_TUNGSTEN.get(), com.hbm.items.ModItems.PLATE_TUNGSTEN.get());
        addPlate(com.hbm.items.ModItems.INGOT_BERYLLIUM.get(), com.hbm.items.ModItems.PLATE_BERYLLIUM.get());
        addPlate(com.hbm.items.ModItems.INGOT_NICKEL.get(), com.hbm.items.ModItems.PLATE_NICKEL.get());
        addPlate(com.hbm.items.ModItems.INGOT_CHROMIUM.get(), com.hbm.items.ModItems.PLATE_CHROMIUM.get());
        addPlate(com.hbm.items.ModItems.INGOT_MOLYBDENUM.get(), com.hbm.items.ModItems.PLATE_MOLYBDENUM.get());
        addPlate(com.hbm.items.ModItems.INGOT_SILICON.get(), com.hbm.items.ModItems.PLATE_SILICON.get());
        addPlate(com.hbm.items.ModItems.INGOT_NIOBIUM.get(), com.hbm.items.ModItems.PLATE_NIOBIUM.get());
        addPlate(com.hbm.items.ModItems.INGOT_TANTALIUM.get(), com.hbm.items.ModItems.PLATE_TANTALIUM.get());
        addPlate(com.hbm.items.ModItems.INGOT_LANTHANIUM.get(), com.hbm.items.ModItems.PLATE_LANTHANIUM.get());
        addPlate(com.hbm.items.ModItems.INGOT_URANIUM.get(), com.hbm.items.ModItems.PLATE_URANIUM.get());
        addPlate(com.hbm.items.ModItems.INGOT_COMBINE_STEEL.get(), com.hbm.items.ModItems.PLATE_COMBINE_STEEL.get());
        addPlate(com.hbm.items.ModItems.INGOT_DURA_STEEL.get(), com.hbm.items.ModItems.PLATE_DURA_STEEL.get());
        addPlate(com.hbm.items.ModItems.INGOT_GUNMETAL.get(), com.hbm.items.ModItems.PLATE_GUNMETAL.get());
        addPlate(com.hbm.items.ModItems.INGOT_DESH.get(), com.hbm.items.ModItems.PLATE_DESH.get());
        addPlate(com.hbm.items.ModItems.INGOT_DINEUTRONIUM.get(), com.hbm.items.ModItems.PLATE_DINEUTRONIUM.get());
        addPlate(com.hbm.items.ModItems.INGOT_POLYMER.get(), com.hbm.items.ModItems.PLATE_POLYMER.get());

        // ===== Batch 23: Crystal decomposition recipes (5 crystals x 1 = 5 recipes) =====
        addCrystalDecomp(com.hbm.items.ModItems.CRYSTAL_IRON.get(), net.minecraft.world.item.Items.IRON_INGOT);
        addCrystalDecomp(com.hbm.items.ModItems.CRYSTAL_GOLD.get(), net.minecraft.world.item.Items.GOLD_INGOT);
        addCrystalDecomp(com.hbm.items.ModItems.CRYSTAL_REDSTONE.get(), net.minecraft.world.item.Items.REDSTONE);
        addCrystalDecomp(com.hbm.items.ModItems.CRYSTAL_LAPIS.get(), net.minecraft.world.item.Items.LAPIS_LAZULI);
        addCrystalDecomp(com.hbm.items.ModItems.CRYSTAL_DIAMOND.get(), net.minecraft.world.item.Items.DIAMOND);

        // ===== Batch 24: Wire recipes (2 types) =====
        addRecipeAuto(new ItemStack(com.hbm.items.ModItems.WIRE_FINE.get(), 4),
                "I", "I", "I",
                'I', net.minecraft.world.item.Items.COPPER_INGOT);
        addRecipeAuto(new ItemStack(com.hbm.items.ModItems.WIRE_DENSE.get(), 2),
                "III", "III", "III",
                'I', net.minecraft.world.item.Items.COPPER_INGOT);

        // ===== Batch 25: Alloy & compound recipes (10 recipes) =====
        // Steel: 1 iron + 1 coal -> 1 steel ingot (shapeless, represents blast furnace)
        addShapelessAuto(new ItemStack(com.hbm.items.ModItems.INGOT_STEEL.get(), 1),
                net.minecraft.world.item.Items.IRON_INGOT, net.minecraft.world.item.Items.COAL);
        // Red copper: 1 copper + 1 redstone -> 1 red copper ingot
        addShapelessAuto(new ItemStack(com.hbm.items.ModItems.INGOT_RED_COPPER.get(), 1),
                com.hbm.items.ModItems.INGOT_COPPER.get(), net.minecraft.world.item.Items.REDSTONE);
        // CD alloy: 1 cadmium + 1 copper -> 2 cd alloy
        addShapelessAuto(new ItemStack(com.hbm.items.ModItems.INGOT_CDALLOY.get(), 2),
                com.hbm.items.ModItems.INGOT_CADMIUM.get(), com.hbm.items.ModItems.INGOT_COPPER.get());
        // TC alloy: 1 tungsten + 1 copper -> 2 tc alloy
        addShapelessAuto(new ItemStack(com.hbm.items.ModItems.INGOT_TCALLOY.get(), 2),
                com.hbm.items.ModItems.INGOT_TUNGSTEN.get(), com.hbm.items.ModItems.INGOT_COPPER.get());
        // Arsenic bronze: 1 arsenic + 1 copper -> 2 arsenic bronze
        addShapelessAuto(new ItemStack(com.hbm.items.ModItems.INGOT_ARSENIC_BRONZE.get(), 2),
                com.hbm.items.ModItems.INGOT_ARSENIC.get(), com.hbm.items.ModItems.INGOT_COPPER.get());
        // Bismuth bronze: 1 bismuth + 1 copper -> 2 bismuth bronze
        addShapelessAuto(new ItemStack(com.hbm.items.ModItems.INGOT_BISMUTH_BRONZE.get(), 2),
                com.hbm.items.ModItems.INGOT_BISMUTH.get(), com.hbm.items.ModItems.INGOT_COPPER.get());
        // Gunmetal: 1 copper + 1 tin (zinc substitute) -> 2 gunmetal
        addShapelessAuto(new ItemStack(com.hbm.items.ModItems.INGOT_GUNMETAL.get(), 2),
                com.hbm.items.ModItems.INGOT_COPPER.get(), net.minecraft.world.item.Items.IRON_INGOT);
        // Combine steel: 2 steel + 1 tungsten -> 1 combine steel
        addShapelessAuto(new ItemStack(com.hbm.items.ModItems.INGOT_COMBINE_STEEL.get(), 1),
                com.hbm.items.ModItems.INGOT_STEEL.get(), com.hbm.items.ModItems.INGOT_STEEL.get(),
                com.hbm.items.ModItems.INGOT_TUNGSTEN.get());
        // Dura steel: 2 steel + 1 titanium -> 1 dura steel
        addShapelessAuto(new ItemStack(com.hbm.items.ModItems.INGOT_DURA_STEEL.get(), 1),
                com.hbm.items.ModItems.INGOT_STEEL.get(), com.hbm.items.ModItems.INGOT_STEEL.get(),
                com.hbm.items.ModItems.INGOT_TITANIUM.get());
        // Ferrouranium: 1 iron + 1 uranium -> 2 ferrouranium
        addShapelessAuto(new ItemStack(com.hbm.items.ModItems.INGOT_FERROURANIUM.get(), 2),
                net.minecraft.world.item.Items.IRON_INGOT, com.hbm.items.ModItems.INGOT_URANIUM.get());

        // ===== Batch 26: Pile rod recipes (3 rods) =====
        addRod(com.hbm.items.ModItems.NUGGET_URANIUM.get(), com.hbm.items.ModItems.PILE_ROD_URANIUM.get());
        addRod(com.hbm.items.ModItems.NUGGET_PU239.get(), com.hbm.items.ModItems.PILE_ROD_PU239.get());
        addRod(com.hbm.items.ModItems.NUGGET_PLUTONIUM.get(), com.hbm.items.ModItems.PILE_ROD_PLUTONIUM.get());

        // ===== Batch 6: Wall/Fence recipes (6 walls + 4 fences = 10 recipes) =====

        addWallFence(com.hbm.blocks.ModBlocks.ASPHALT_WALL.get(), com.hbm.blocks.ModBlocks.ASPHALT_FENCE.get(), com.hbm.blocks.ModBlocks.ASPHALT.get());
        addWallFence(com.hbm.blocks.ModBlocks.BRICK_RED_WALL.get(), com.hbm.blocks.ModBlocks.BRICK_RED_FENCE.get(), com.hbm.blocks.ModBlocks.BRICK_RED.get());
        addWallFence(com.hbm.blocks.ModBlocks.CONCRETE_WALL.get(), com.hbm.blocks.ModBlocks.CONCRETE_FENCE.get(), com.hbm.blocks.ModBlocks.CONCRETE.get());
        addWallFence(com.hbm.blocks.ModBlocks.CONCRETE_SMOOTH_WALL.get(), com.hbm.blocks.ModBlocks.CONCRETE_SMOOTH_FENCE.get(), com.hbm.blocks.ModBlocks.CONCRETE_SMOOTH.get());
        addWall(com.hbm.blocks.ModBlocks.DUCRETE_WALL.get(), com.hbm.blocks.ModBlocks.DUCRETE.get());
        addWall(com.hbm.blocks.ModBlocks.DUCRETE_SMOOTH_WALL.get(), com.hbm.blocks.ModBlocks.DUCRETE_SMOOTH.get());

        // ===== Batch 8: Vanilla recipe supplements (11 recipes) =====

        // Bookshelf: 6 oak planks + 3 books -> 1 bookshelf
        addRecipeAuto(new ItemStack(net.minecraft.world.level.block.Blocks.BOOKSHELF, 1),
                "PPP", "BBB", "PPP",
                'P', net.minecraft.world.level.block.Blocks.OAK_PLANKS,
                'B', net.minecraft.world.item.Items.BOOK);

        // Ladder: 7 sticks -> 3 ladders
        addRecipeAuto(new ItemStack(net.minecraft.world.level.block.Blocks.LADDER, 3),
                "I I", "III", "I I",
                'I', net.minecraft.world.item.Items.STICK);

        // Torch (charcoal): 1 charcoal + 1 stick -> 4 torches
        addRecipeAuto(new ItemStack(net.minecraft.world.level.block.Blocks.TORCH, 4),
                "C", "S",
                'C', net.minecraft.world.item.Items.CHARCOAL,
                'S', net.minecraft.world.item.Items.STICK);

        // White dye: 1 bone meal -> 1 white dye
        addShapelessAuto(new ItemStack(net.minecraft.world.item.Items.WHITE_DYE, 1),
                net.minecraft.world.item.Items.BONE_MEAL);

        // Black dye: 1 ink sac -> 1 black dye
        addShapelessAuto(new ItemStack(net.minecraft.world.item.Items.BLACK_DYE, 1),
                net.minecraft.world.item.Items.INK_SAC);

        // Red dye: 1 poppy -> 1 red dye
        addShapelessAuto(new ItemStack(net.minecraft.world.item.Items.RED_DYE, 1),
                net.minecraft.world.item.Items.POPPY);

        // Yellow dye: 1 dandelion -> 1 yellow dye
        addShapelessAuto(new ItemStack(net.minecraft.world.item.Items.YELLOW_DYE, 1),
                net.minecraft.world.item.Items.DANDELION);

        // Orange dye: 1 orange tulip -> 1 orange dye
        addShapelessAuto(new ItemStack(net.minecraft.world.item.Items.ORANGE_DYE, 1),
                net.minecraft.world.item.Items.ORANGE_TULIP);

        // Blue dye: 1 cornflower -> 1 blue dye
        addShapelessAuto(new ItemStack(net.minecraft.world.item.Items.BLUE_DYE, 1),
                net.minecraft.world.item.Items.CORNFLOWER);

        // Green dye: 1 cactus -> 1 green dye
        addShapelessAuto(new ItemStack(net.minecraft.world.item.Items.GREEN_DYE, 1),
                net.minecraft.world.level.block.Blocks.CACTUS);

        // Bowl: 3 planks -> 4 bowls
        addRecipeAuto(new ItemStack(net.minecraft.world.item.Items.BOWL, 4),
                "P P", " P ",
                'P', net.minecraft.world.level.block.Blocks.OAK_PLANKS);

        // ===== Batch 7: More 1to9/9to1 recipes (39 materials x 2 = 78 recipes) =====

        // --- Metals ---
        add1To9Pair(com.hbm.blocks.ModBlocks.BLOCK_TITANIUM.get(), com.hbm.items.ModItems.INGOT_TITANIUM.get());
        add1To9Pair(com.hbm.blocks.ModBlocks.BLOCK_COBALT.get(), com.hbm.items.ModItems.INGOT_COBALT.get());
        add1To9Pair(com.hbm.blocks.ModBlocks.BLOCK_ZIRCONIUM.get(), com.hbm.items.ModItems.INGOT_ZIRCONIUM.get());
        add1To9Pair(com.hbm.blocks.ModBlocks.BLOCK_BISMUTH.get(), com.hbm.items.ModItems.INGOT_BISMUTH.get());
        add1To9Pair(com.hbm.blocks.ModBlocks.BLOCK_NIOBIUM.get(), com.hbm.items.ModItems.INGOT_NIOBIUM.get());
        add1To9Pair(com.hbm.blocks.ModBlocks.BLOCK_TANTALIUM.get(), com.hbm.items.ModItems.INGOT_TANTALIUM.get());
        add1To9Pair(com.hbm.blocks.ModBlocks.BLOCK_LANTHANIUM.get(), com.hbm.items.ModItems.INGOT_LANTHANIUM.get());
        add1To9Pair(com.hbm.blocks.ModBlocks.BLOCK_RED_COPPER.get(), com.hbm.items.ModItems.INGOT_RED_COPPER.get());

        // --- Nuclear materials ---
        add1To9Pair(com.hbm.blocks.ModBlocks.BLOCK_NEPTUNIUM.get(), com.hbm.items.ModItems.INGOT_NEPTUNIUM.get());
        add1To9Pair(com.hbm.blocks.ModBlocks.BLOCK_PLUTONIUM.get(), com.hbm.items.ModItems.INGOT_PLUTONIUM.get());
        add1To9Pair(com.hbm.blocks.ModBlocks.BLOCK_POLONIUM.get(), com.hbm.items.ModItems.INGOT_POLONIUM.get());
        add1To9Pair(com.hbm.blocks.ModBlocks.BLOCK_U233.get(), com.hbm.items.ModItems.INGOT_U233.get());
        add1To9Pair(com.hbm.blocks.ModBlocks.BLOCK_U235.get(), com.hbm.items.ModItems.INGOT_U235.get());
        add1To9Pair(com.hbm.blocks.ModBlocks.BLOCK_U238.get(), com.hbm.items.ModItems.INGOT_U238.get());
        add1To9Pair(com.hbm.blocks.ModBlocks.BLOCK_RA226.get(), com.hbm.items.ModItems.INGOT_RA226.get());

        // --- Plutonium variants ---
        add1To9Pair(com.hbm.blocks.ModBlocks.BLOCK_PU238.get(), com.hbm.items.ModItems.INGOT_PU238.get());
        add1To9Pair(com.hbm.blocks.ModBlocks.BLOCK_PU239.get(), com.hbm.items.ModItems.INGOT_PU239.get());
        add1To9Pair(com.hbm.blocks.ModBlocks.BLOCK_PU240.get(), com.hbm.items.ModItems.INGOT_PU240.get());
        add1To9Pair(com.hbm.blocks.ModBlocks.BLOCK_PU_MIX.get(), com.hbm.items.ModItems.INGOT_PU_MIX.get());

        // --- Alloys and compounds ---
        add1To9Pair(com.hbm.blocks.ModBlocks.BLOCK_CADMIUM.get(), com.hbm.items.ModItems.INGOT_CADMIUM.get());
        add1To9Pair(com.hbm.blocks.ModBlocks.BLOCK_BORON.get(), com.hbm.items.ModItems.INGOT_BORON.get());
        add1To9Pair(com.hbm.blocks.ModBlocks.BLOCK_GRAPHITE.get(), com.hbm.items.ModItems.INGOT_GRAPHITE.get());
        add1To9Pair(com.hbm.blocks.ModBlocks.BLOCK_BAKELITE.get(), com.hbm.items.ModItems.INGOT_BAKELITE.get());
        add1To9Pair(com.hbm.blocks.ModBlocks.BLOCK_CDALLOY.get(), com.hbm.items.ModItems.INGOT_CDALLOY.get());
        add1To9Pair(com.hbm.blocks.ModBlocks.BLOCK_COMBINE_STEEL.get(), com.hbm.items.ModItems.INGOT_COMBINE_STEEL.get());
        add1To9Pair(com.hbm.blocks.ModBlocks.BLOCK_DESH.get(), com.hbm.items.ModItems.INGOT_DESH.get());
        add1To9Pair(com.hbm.blocks.ModBlocks.BLOCK_DINEUTRONIUM.get(), com.hbm.items.ModItems.INGOT_DINEUTRONIUM.get());
        add1To9Pair(com.hbm.blocks.ModBlocks.BLOCK_DURA_STEEL.get(), com.hbm.items.ModItems.INGOT_DURA_STEEL.get());
        add1To9Pair(com.hbm.blocks.ModBlocks.BLOCK_TCALLOY.get(), com.hbm.items.ModItems.INGOT_TCALLOY.get());

        // --- Advanced/exotic materials ---
        add1To9Pair(com.hbm.blocks.ModBlocks.BLOCK_EUPHEMIUM.get(), com.hbm.items.ModItems.INGOT_EUPHEMIUM.get());
        add1To9Pair(com.hbm.blocks.ModBlocks.BLOCK_FIBERGLASS.get(), com.hbm.items.ModItems.INGOT_FIBERGLASS.get());
        add1To9Pair(com.hbm.blocks.ModBlocks.BLOCK_POLYMER.get(), com.hbm.items.ModItems.INGOT_POLYMER.get());
        add1To9Pair(com.hbm.blocks.ModBlocks.BLOCK_RUBBER.get(), com.hbm.items.ModItems.INGOT_RUBBER.get());
        add1To9Pair(com.hbm.blocks.ModBlocks.BLOCK_SCHRABIDATE.get(), com.hbm.items.ModItems.INGOT_SCHRABIDATE.get());
        add1To9Pair(com.hbm.blocks.ModBlocks.BLOCK_SCHRABIDIUM.get(), com.hbm.items.ModItems.INGOT_SCHRABIDIUM.get());
        add1To9Pair(com.hbm.blocks.ModBlocks.BLOCK_SCHRARANIUM.get(), com.hbm.items.ModItems.INGOT_SCHRARANIUM.get());
        add1To9Pair(com.hbm.blocks.ModBlocks.BLOCK_SOLINIUM.get(), com.hbm.items.ModItems.INGOT_SOLINIUM.get());
        add1To9Pair(com.hbm.blocks.ModBlocks.BLOCK_STARMETAL.get(), com.hbm.items.ModItems.INGOT_STARMETAL.get());
        add1To9Pair(com.hbm.blocks.ModBlocks.BLOCK_MAGNETIZED_TUNGSTEN.get(), com.hbm.items.ModItems.INGOT_MAGNETIZED_TUNGSTEN.get());

        // ===== Batch 10: Extended tool recipes (13 materials x 5 tools = 65 recipes) =====

        // --- Titanium tools ---
        addSword(com.hbm.items.ModItems.INGOT_TITANIUM.get(), com.hbm.items.ModItems.SWORD_TITANIUM.get());
        addPickaxe(com.hbm.items.ModItems.INGOT_TITANIUM.get(), com.hbm.items.ModItems.PICKAXE_TITANIUM.get());
        addAxe(com.hbm.items.ModItems.INGOT_TITANIUM.get(), com.hbm.items.ModItems.AXE_TITANIUM.get());
        addShovel(com.hbm.items.ModItems.INGOT_TITANIUM.get(), com.hbm.items.ModItems.SHOVEL_TITANIUM.get());
        addHoe(com.hbm.items.ModItems.INGOT_TITANIUM.get(), com.hbm.items.ModItems.HOE_TITANIUM.get());

        // --- Tungsten tools ---
        addSword(com.hbm.items.ModItems.INGOT_TUNGSTEN.get(), com.hbm.items.ModItems.SWORD_TUNGSTEN.get());
        addPickaxe(com.hbm.items.ModItems.INGOT_TUNGSTEN.get(), com.hbm.items.ModItems.PICKAXE_TUNGSTEN.get());
        addAxe(com.hbm.items.ModItems.INGOT_TUNGSTEN.get(), com.hbm.items.ModItems.AXE_TUNGSTEN.get());
        addShovel(com.hbm.items.ModItems.INGOT_TUNGSTEN.get(), com.hbm.items.ModItems.SHOVEL_TUNGSTEN.get());
        addHoe(com.hbm.items.ModItems.INGOT_TUNGSTEN.get(), com.hbm.items.ModItems.HOE_TUNGSTEN.get());

        // --- Cobalt tools ---
        addSword(com.hbm.items.ModItems.INGOT_COBALT.get(), com.hbm.items.ModItems.SWORD_COBALT.get());
        addPickaxe(com.hbm.items.ModItems.INGOT_COBALT.get(), com.hbm.items.ModItems.PICKAXE_COBALT.get());
        addAxe(com.hbm.items.ModItems.INGOT_COBALT.get(), com.hbm.items.ModItems.AXE_COBALT.get());
        addShovel(com.hbm.items.ModItems.INGOT_COBALT.get(), com.hbm.items.ModItems.SHOVEL_COBALT.get());
        addHoe(com.hbm.items.ModItems.INGOT_COBALT.get(), com.hbm.items.ModItems.HOE_COBALT.get());

        // --- Beryllium tools ---
        addSword(com.hbm.items.ModItems.INGOT_BERYLLIUM.get(), com.hbm.items.ModItems.SWORD_BERYLLIUM.get());
        addPickaxe(com.hbm.items.ModItems.INGOT_BERYLLIUM.get(), com.hbm.items.ModItems.PICKAXE_BERYLLIUM.get());
        addAxe(com.hbm.items.ModItems.INGOT_BERYLLIUM.get(), com.hbm.items.ModItems.AXE_BERYLLIUM.get());
        addShovel(com.hbm.items.ModItems.INGOT_BERYLLIUM.get(), com.hbm.items.ModItems.SHOVEL_BERYLLIUM.get());
        addHoe(com.hbm.items.ModItems.INGOT_BERYLLIUM.get(), com.hbm.items.ModItems.HOE_BERYLLIUM.get());

        // --- Zirconium tools ---
        addSword(com.hbm.items.ModItems.INGOT_ZIRCONIUM.get(), com.hbm.items.ModItems.SWORD_ZIRCONIUM.get());
        addPickaxe(com.hbm.items.ModItems.INGOT_ZIRCONIUM.get(), com.hbm.items.ModItems.PICKAXE_ZIRCONIUM.get());
        addAxe(com.hbm.items.ModItems.INGOT_ZIRCONIUM.get(), com.hbm.items.ModItems.AXE_ZIRCONIUM.get());
        addShovel(com.hbm.items.ModItems.INGOT_ZIRCONIUM.get(), com.hbm.items.ModItems.SHOVEL_ZIRCONIUM.get());
        addHoe(com.hbm.items.ModItems.INGOT_ZIRCONIUM.get(), com.hbm.items.ModItems.HOE_ZIRCONIUM.get());

        // --- Cadmium tools ---
        addSword(com.hbm.items.ModItems.INGOT_CADMIUM.get(), com.hbm.items.ModItems.SWORD_CADMIUM.get());
        addPickaxe(com.hbm.items.ModItems.INGOT_CADMIUM.get(), com.hbm.items.ModItems.PICKAXE_CADMIUM.get());
        addAxe(com.hbm.items.ModItems.INGOT_CADMIUM.get(), com.hbm.items.ModItems.AXE_CADMIUM.get());
        addShovel(com.hbm.items.ModItems.INGOT_CADMIUM.get(), com.hbm.items.ModItems.SHOVEL_CADMIUM.get());
        addHoe(com.hbm.items.ModItems.INGOT_CADMIUM.get(), com.hbm.items.ModItems.HOE_CADMIUM.get());

        // --- Boron tools ---
        addSword(com.hbm.items.ModItems.INGOT_BORON.get(), com.hbm.items.ModItems.SWORD_BORON.get());
        addPickaxe(com.hbm.items.ModItems.INGOT_BORON.get(), com.hbm.items.ModItems.PICKAXE_BORON.get());
        addAxe(com.hbm.items.ModItems.INGOT_BORON.get(), com.hbm.items.ModItems.AXE_BORON.get());
        addShovel(com.hbm.items.ModItems.INGOT_BORON.get(), com.hbm.items.ModItems.SHOVEL_BORON.get());
        addHoe(com.hbm.items.ModItems.INGOT_BORON.get(), com.hbm.items.ModItems.HOE_BORON.get());

        // --- Neptunium tools ---
        addSword(com.hbm.items.ModItems.INGOT_NEPTUNIUM.get(), com.hbm.items.ModItems.SWORD_NEPTUNIUM.get());
        addPickaxe(com.hbm.items.ModItems.INGOT_NEPTUNIUM.get(), com.hbm.items.ModItems.PICKAXE_NEPTUNIUM.get());
        addAxe(com.hbm.items.ModItems.INGOT_NEPTUNIUM.get(), com.hbm.items.ModItems.AXE_NEPTUNIUM.get());
        addShovel(com.hbm.items.ModItems.INGOT_NEPTUNIUM.get(), com.hbm.items.ModItems.SHOVEL_NEPTUNIUM.get());
        addHoe(com.hbm.items.ModItems.INGOT_NEPTUNIUM.get(), com.hbm.items.ModItems.HOE_NEPTUNIUM.get());

        // --- Plutonium tools ---
        addSword(com.hbm.items.ModItems.INGOT_PLUTONIUM.get(), com.hbm.items.ModItems.SWORD_PLUTONIUM.get());
        addPickaxe(com.hbm.items.ModItems.INGOT_PLUTONIUM.get(), com.hbm.items.ModItems.PICKAXE_PLUTONIUM.get());
        addAxe(com.hbm.items.ModItems.INGOT_PLUTONIUM.get(), com.hbm.items.ModItems.AXE_PLUTONIUM.get());
        addShovel(com.hbm.items.ModItems.INGOT_PLUTONIUM.get(), com.hbm.items.ModItems.SHOVEL_PLUTONIUM.get());
        addHoe(com.hbm.items.ModItems.INGOT_PLUTONIUM.get(), com.hbm.items.ModItems.HOE_PLUTONIUM.get());

        // --- Polonium tools ---
        addSword(com.hbm.items.ModItems.INGOT_POLONIUM.get(), com.hbm.items.ModItems.SWORD_POLONIUM.get());
        addPickaxe(com.hbm.items.ModItems.INGOT_POLONIUM.get(), com.hbm.items.ModItems.PICKAXE_POLONIUM.get());
        addAxe(com.hbm.items.ModItems.INGOT_POLONIUM.get(), com.hbm.items.ModItems.AXE_POLONIUM.get());
        addShovel(com.hbm.items.ModItems.INGOT_POLONIUM.get(), com.hbm.items.ModItems.SHOVEL_POLONIUM.get());
        addHoe(com.hbm.items.ModItems.INGOT_POLONIUM.get(), com.hbm.items.ModItems.HOE_POLONIUM.get());

        // --- U233 tools ---
        addSword(com.hbm.items.ModItems.INGOT_U233.get(), com.hbm.items.ModItems.SWORD_U233.get());
        addPickaxe(com.hbm.items.ModItems.INGOT_U233.get(), com.hbm.items.ModItems.PICKAXE_U233.get());
        addAxe(com.hbm.items.ModItems.INGOT_U233.get(), com.hbm.items.ModItems.AXE_U233.get());
        addShovel(com.hbm.items.ModItems.INGOT_U233.get(), com.hbm.items.ModItems.SHOVEL_U233.get());
        addHoe(com.hbm.items.ModItems.INGOT_U233.get(), com.hbm.items.ModItems.HOE_U233.get());

        // --- U235 tools ---
        addSword(com.hbm.items.ModItems.INGOT_U235.get(), com.hbm.items.ModItems.SWORD_U235.get());
        addPickaxe(com.hbm.items.ModItems.INGOT_U235.get(), com.hbm.items.ModItems.PICKAXE_U235.get());
        addAxe(com.hbm.items.ModItems.INGOT_U235.get(), com.hbm.items.ModItems.AXE_U235.get());
        addShovel(com.hbm.items.ModItems.INGOT_U235.get(), com.hbm.items.ModItems.SHOVEL_U235.get());
        addHoe(com.hbm.items.ModItems.INGOT_U235.get(), com.hbm.items.ModItems.HOE_U235.get());

        // --- U238 tools ---
        addSword(com.hbm.items.ModItems.INGOT_U238.get(), com.hbm.items.ModItems.SWORD_U238.get());
        addPickaxe(com.hbm.items.ModItems.INGOT_U238.get(), com.hbm.items.ModItems.PICKAXE_U238.get());
        addAxe(com.hbm.items.ModItems.INGOT_U238.get(), com.hbm.items.ModItems.AXE_U238.get());
        addShovel(com.hbm.items.ModItems.INGOT_U238.get(), com.hbm.items.ModItems.SHOVEL_U238.get());
        addHoe(com.hbm.items.ModItems.INGOT_U238.get(), com.hbm.items.ModItems.HOE_U238.get());

        // ===== Batch 27: Nuclear waste recipes (8 items) =====
        // Short waste variants
        addShapelessAuto(new ItemStack(com.hbm.items.ModItems.NUCLEAR_WASTE_SHORT.get(), 1),
                com.hbm.items.ModItems.NUGGET_URANIUM.get(), com.hbm.items.ModItems.NUGGET_URANIUM.get());
        addShapelessAuto(new ItemStack(com.hbm.items.ModItems.NUCLEAR_WASTE_SHORT_DEPLETED.get(), 1),
                com.hbm.items.ModItems.NUGGET_URANIUM.get(), com.hbm.items.ModItems.NUGGET_PLUTONIUM.get());
        addShapelessAuto(new ItemStack(com.hbm.items.ModItems.NUCLEAR_WASTE_SHORT_TINY.get(), 4),
                com.hbm.items.ModItems.NUGGET_URANIUM.get());
        addShapelessAuto(new ItemStack(com.hbm.items.ModItems.NUCLEAR_WASTE_SHORT_DEPLETED_TINY.get(), 4),
                com.hbm.items.ModItems.NUGGET_PLUTONIUM.get());

        // Long waste variants
        addShapelessAuto(new ItemStack(com.hbm.items.ModItems.NUCLEAR_WASTE_LONG.get(), 1),
                com.hbm.items.ModItems.INGOT_URANIUM.get(), com.hbm.items.ModItems.INGOT_URANIUM.get());
        addShapelessAuto(new ItemStack(com.hbm.items.ModItems.NUCLEAR_WASTE_LONG_DEPLETED.get(), 1),
                com.hbm.items.ModItems.INGOT_URANIUM.get(), com.hbm.items.ModItems.INGOT_PLUTONIUM.get());
        addShapelessAuto(new ItemStack(com.hbm.items.ModItems.NUCLEAR_WASTE_LONG_TINY.get(), 4),
                com.hbm.items.ModItems.INGOT_URANIUM.get());
        addShapelessAuto(new ItemStack(com.hbm.items.ModItems.NUCLEAR_WASTE_LONG_DEPLETED_TINY.get(), 4),
                com.hbm.items.ModItems.INGOT_PLUTONIUM.get());
    }

    // ===== Core recipe registration methods =====

    /**
     * Shaped recipe with auto ore-dict/tag support.
     * Replaces 1.12.2 ShapedOreRecipe runtime registration.
     */
    public static void addRecipeAuto(ItemStack output, Object... args) {
        if (recipeOutput == null || output.isEmpty()) return;

        try {
            ShapedRecipeBuilder builder = ShapedRecipeBuilder.shaped(
                    net.minecraft.data.recipes.RecipeCategory.MISC, output.getItem(), output.getCount());

            // Parse pattern and ingredients from args
            List<Object> ingredients = new ArrayList<>();
            int patternEnd = 0;
            for (int i = 0; i < args.length; i++) {
                if (args[i] instanceof String s && s.length() <= 3 && patternEnd < 3) {
                    builder.pattern(s);
                    patternEnd++;
                } else {
                    ingredients.add(args[i]);
                }
            }

            // Process key-ingredient pairs
            for (int i = 0; i < ingredients.size(); i += 2) {
                if (i + 1 >= ingredients.size()) break;
                if (ingredients.get(i) instanceof Character key) {
                    Object val = ingredients.get(i + 1);
                    Ingredient ing = toIngredient(val);
                    if (ing != null && !ing.isEmpty()) {
                        builder.define(key, ing);
                    }
                }
            }

            builder.unlockedBy("has_output", has(output.getItem()));
            builder.save(recipeOutput, makeRecipeId(output));
        } catch (Exception e) {
            HBM.LOGGER.warn("Failed to register shaped recipe for {}: {}", output, e.getMessage());
        }
    }

    /**
     * Shapeless recipe with auto ore-dict/tag support.
     * Replaces 1.12.2 ShapelessOreRecipe runtime registration.
     */
    public static void addShapelessAuto(ItemStack output, Object... args) {
        if (recipeOutput == null || output.isEmpty()) return;

        try {
            ShapelessRecipeBuilder builder = ShapelessRecipeBuilder.shapeless(
                    net.minecraft.data.recipes.RecipeCategory.MISC, output.getItem(), output.getCount());

            for (Object arg : args) {
                Ingredient ing = toIngredient(arg);
                if (ing != null && !ing.isEmpty()) {
                    builder.requires(ing);
                }
            }

            builder.unlockedBy("has_output", has(output.getItem()));
            builder.save(recipeOutput, makeRecipeId(output));
        } catch (Exception e) {
            HBM.LOGGER.warn("Failed to register shapeless recipe for {}: {}", output, e.getMessage());
        }
    }

    /**
     * Shapeless ore-dict recipe (explicit ore variant).
     */
    public static void addRecipeAutoOreShapeless(ItemStack output, Object... args) {
        addShapelessAuto(output, args);
    }

    // ===== Helper methods (preserved from 1.12.2 signatures) =====

    public static void addSlabStair(Block slab, Block stairs, Block source) {
        addRecipeAuto(new ItemStack(stairs, 4), "#  ", "## ", "###", '#', source);
        addRecipeAuto(new ItemStack(slab, 6), "###", '#', source);
        addShapelessAuto(new ItemStack(source, 1), slab, slab);
    }

    public static void addWallFence(Block wall, Block fence, Block source) {
        addWall(wall, source);
        addFence(fence, source);
    }

    public static void addWall(Block wall, Block source) {
        addRecipeAuto(new ItemStack(wall, 6), "###", "###", '#', source);
    }

    public static void addFence(Block fence, Block source) {
        addRecipeAuto(new ItemStack(fence, 3), "#B#", "#B#", '#', source, 'B', net.minecraft.world.item.Items.STICK);
    }

    public static void add1To9Pair(Item one, Item nine) {
        add1To9(new ItemStack(one), new ItemStack(nine, 9));
        add9To1(new ItemStack(nine), new ItemStack(one));
    }

    public static void add1To9Pair(Block one, Item nine) {
        add1To9(new ItemStack(one), new ItemStack(nine, 9));
        add9To1(new ItemStack(nine), new ItemStack(one));
    }

    public static void add1To9(Block one, Item nine) {
        add1To9(new ItemStack(one), new ItemStack(nine, 9));
    }

    public static void add1To9(Item one, Item nine) {
        add1To9(new ItemStack(one), new ItemStack(nine, 9));
    }

    public static void add1To9(ItemStack one, ItemStack nine) {
        addShapelessAuto(nine, one);
    }

    public static void add9To1(Item nine, Block one) {
        add9To1(new ItemStack(nine), new ItemStack(one));
    }

    public static void add9To1(Item nine, Item one) {
        add9To1(new ItemStack(nine), new ItemStack(one));
    }

    public static void add9To1(ItemStack nine, ItemStack one) {
        addRecipeAuto(one, "###", "###", "###", '#', nine);
    }

    public static void addMineralSet(Item nugget, Item ingot, Block block) {
        add1To9(new ItemStack(ingot), new ItemStack(nugget, 9));
        add9To1(new ItemStack(nugget), new ItemStack(ingot));
        add1To9(new ItemStack(block), new ItemStack(ingot, 9));
        add9To1(new ItemStack(ingot), new ItemStack(block));
    }

    public static void addNuggetIngotPair(Item nugget, Item ingot) {
        add1To9(new ItemStack(ingot), new ItemStack(nugget, 9));
        add9To1(new ItemStack(nugget), new ItemStack(ingot));
    }

    public static void addPlate(Item ingot, Item plate) {
        addRecipeAuto(new ItemStack(plate, 3),
                "III", 'I', ingot);
    }

    public static void addCrystalDecomp(Item crystal, Item output) {
        addShapelessAuto(new ItemStack(output, 2), crystal);
    }

    public static void addWire(Item ingot, Item wire) {
        addRecipeAuto(new ItemStack(wire, 4),
                "I", "I", "I", 'I', ingot);
    }

    public static void addGear(Item ingot, Item gear) {
        addRecipeAuto(new ItemStack(gear, 1),
                " I ", "I I", " I ", 'I', ingot);
    }

    public static void addSword(Item ingot, Item sword) {
        addRecipeAuto(new ItemStack(sword), "I", "I", "S", 'I', ingot, 'S', net.minecraft.world.item.Items.STICK);
    }

    public static void addPickaxe(Item ingot, Item pick) {
        addRecipeAuto(new ItemStack(pick), "III", " S ", " S ", 'I', ingot, 'S', net.minecraft.world.item.Items.STICK);
    }

    public static void addAxe(Item ingot, Item axe) {
        addRecipeAuto(new ItemStack(axe), "II", "IS", " S", 'I', ingot, 'S', net.minecraft.world.item.Items.STICK);
    }

    public static void addShovel(Item ingot, Item shovel) {
        addRecipeAuto(new ItemStack(shovel), "I", "S", "S", 'I', ingot, 'S', net.minecraft.world.item.Items.STICK);
    }

    public static void addHoe(Item ingot, Item hoe) {
        addRecipeAuto(new ItemStack(hoe), "II", " S", " S", 'I', ingot, 'S', net.minecraft.world.item.Items.STICK);
    }

    public static void addRod(Item nugget, Item out) {
        addShapelessAuto(new ItemStack(out),
                com.hbm.items.ModItems.ROD_EMPTY.get(), nugget, nugget, nugget, nugget, nugget, nugget);
    }

    public static void addDualRod(Item ingot, Item nugget, Item out) {
        addShapelessAuto(new ItemStack(out),
                com.hbm.items.ModItems.ROD_DUAL_EMPTY.get(), ingot, nugget, nugget, nugget);
    }

    public static void addQuadRod(Item ingot, Item nugget, Item out) {
        addShapelessAuto(new ItemStack(out),
                com.hbm.items.ModItems.ROD_QUAD_EMPTY.get(), ingot, ingot, nugget, nugget, nugget, nugget, nugget, nugget);
    }

    // ===== Utility methods =====

    private static ResourceLocation makeRecipeId(ItemStack output) {
        String path = Objects.requireNonNull(output.getItem().getDescriptionId())
                .replace("item.", "").replace("block.", "").replace(":", ".");
        recipeCounter++;
        return ResourceLocation.fromNamespaceAndPath(Tags.MODID, path + (recipeCounter > 1 ? "_" + recipeCounter : ""));
    }

    private static Ingredient toIngredient(Object obj) {
        if (obj == null) return null;
        if (obj instanceof Ingredient ing) return ing;
        if (obj instanceof ItemStack stack) return Ingredient.of(stack);
        if (obj instanceof ItemLike itemLike) return Ingredient.of(itemLike);
        if (obj instanceof TagKey<?> tag) {
            // Convert TagKey to Ingredient
            if (tag.registry().equals(net.minecraft.core.registries.Registries.ITEM)) {
                @SuppressWarnings("unchecked")
                TagKey<net.minecraft.world.item.Item> itemTag = (TagKey<net.minecraft.world.item.Item>) tag;
                return Ingredient.of(itemTag);
            }
        }
        if (obj instanceof String oreName) {
            // 1.12 ore dict name -> 1.21 tag key (convention: forge:ingots/iron etc.)
            // This will be handled by OreDictManager tag mappings
            return null;
        }
        return null;
    }

    // has() criterion helper - uses InventoryChangeTrigger
    private static net.minecraft.advancements.Criterion<net.minecraft.advancements.critereon.InventoryChangeTrigger.TriggerInstance> has(Item item) {
        return net.minecraft.advancements.critereon.InventoryChangeTrigger.TriggerInstance.hasItems(item);
    }
}
