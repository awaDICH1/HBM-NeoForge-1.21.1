package com.hbm.inventory.recipes;

import com.hbm.blocks.ModBlocks;
import com.hbm.items.ModItems;
import com.hbm.util.ComparableStack;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 装配机配方系统（P5.9 迁移版）。
 *
 * 迁移自 1.12.2 com.hbm.inventory.recipes.AssemblyMachineRecipes。
 * 1.21.1 变更：运行时配方注册保留（非 JSON 数据驱动），使用 ComparableStack 键。
 *
 * 机制：多个输入物品 + 能量 → 输出物品。
 */
public class AssemblyMachineRecipes {

    public static LinkedHashMap<List<Object>, AssemblyRecipe> recipes = new LinkedHashMap<>();

    public static void register() {

        // Iron ingot x2 + Redstone -> Iron compound
        addRecipe(new ItemStack(ModItems.INGOT_STEEL.get(), 2),
                100, new ItemStack(Items.IRON_INGOT), new ItemStack(Items.IRON_INGOT),
                new ItemStack(Items.REDSTONE));

        // Copper ingot x3 + Redstone -> Copper compound
        addRecipe(new ItemStack(ModItems.INGOT_COPPER.get(), 3),
                100, new ItemStack(Items.COPPER_INGOT), new ItemStack(Items.COPPER_INGOT),
                new ItemStack(Items.COPPER_INGOT), new ItemStack(Items.REDSTONE));

        // Iron ingot + Gold ingot + Redstone -> Steel compound
        addRecipe(new ItemStack(ModItems.INGOT_STEEL.get()),
                200, new ItemStack(Items.IRON_INGOT), new ItemStack(Items.GOLD_INGOT),
                new ItemStack(Items.REDSTONE));

        // Lead ingot + Redstone -> Lead compound
        addRecipe(new ItemStack(ModItems.INGOT_LEAD.get(), 2),
                150, new ItemStack(Items.IRON_INGOT), new ItemStack(Items.REDSTONE));

        // Aluminium ingot + Copper ingot -> Aluminium compound
        addRecipe(new ItemStack(ModItems.INGOT_ALUMINIUM.get(), 2),
                150, new ItemStack(ModItems.INGOT_COPPER.get()),
                new ItemStack(ModItems.INGOT_COPPER.get()));

        // Tungsten ingot + Steel ingot -> Tungsten compound
        addRecipe(new ItemStack(ModItems.INGOT_TUNGSTEN.get()),
                300, new ItemStack(ModItems.INGOT_STEEL.get()),
                new ItemStack(ModItems.INGOT_STEEL.get()),
                new ItemStack(Items.REDSTONE));

        // Beryllium ingot + Copper ingot + Redstone -> Beryllium compound
        addRecipe(new ItemStack(ModItems.INGOT_BERYLLIUM.get()),
                400, new ItemStack(ModItems.INGOT_COPPER.get()),
                new ItemStack(ModItems.INGOT_COPPER.get()),
                new ItemStack(Items.REDSTONE));

        // Nickel ingot + Chromium ingot -> Alloy compound
        addRecipe(new ItemStack(ModItems.INGOT_NICKEL.get(), 2),
                200, new ItemStack(ModItems.INGOT_CHROMIUM.get()),
                new ItemStack(Items.REDSTONE));

        // Iron ingot x3 -> Steel ingot
        addRecipe(new ItemStack(ModItems.INGOT_STEEL.get()),
                250, new ItemStack(Items.IRON_INGOT),
                new ItemStack(Items.IRON_INGOT),
                new ItemStack(Items.IRON_INGOT),
                new ItemStack(Items.COAL));

        // Lead ingot + Redstone + Iron ingot -> Lead compound
        addRecipe(new ItemStack(ModItems.INGOT_LEAD.get(), 3),
                200, new ItemStack(Items.IRON_INGOT),
                new ItemStack(Items.REDSTONE),
                new ItemStack(Items.REDSTONE));

        // Gold ingot + Redstone -> Gold compound
        addRecipe(new ItemStack(Items.GOLD_INGOT, 2),
                200, new ItemStack(Items.GOLD_INGOT),
                new ItemStack(Items.REDSTONE));

        // Copper ingot x2 + Iron ingot -> Bronze compound
        addRecipe(new ItemStack(ModItems.INGOT_COPPER.get(), 3),
                250, new ItemStack(Items.IRON_INGOT),
                new ItemStack(Items.REDSTONE));

        // Titanium ingot + Steel ingot -> Titanium compound
        addRecipe(new ItemStack(ModItems.INGOT_TITANIUM.get()),
                500, new ItemStack(ModItems.INGOT_STEEL.get()),
                new ItemStack(ModItems.INGOT_STEEL.get()),
                new ItemStack(Items.REDSTONE));

        // Silicon ingot + Redstone -> Silicon compound
        addRecipe(new ItemStack(ModItems.INGOT_SILICON.get(), 2),
                150, new ItemStack(Items.REDSTONE));

        // ===== Batch 2: Plate fabrication (ingot -> plate via assembly) =====
        addRecipe(new ItemStack(ModItems.PLATE_IRON.get()),
                100, new ItemStack(Items.IRON_INGOT));
        addRecipe(new ItemStack(ModItems.PLATE_GOLD.get()),
                100, new ItemStack(Items.GOLD_INGOT));
        addRecipe(new ItemStack(ModItems.PLATE_COPPER.get()),
                100, new ItemStack(Items.COPPER_INGOT));
        addRecipe(new ItemStack(ModItems.PLATE_STEEL.get()),
                150, new ItemStack(ModItems.INGOT_STEEL.get()));
        addRecipe(new ItemStack(ModItems.PLATE_TITANIUM.get()),
                200, new ItemStack(ModItems.INGOT_TITANIUM.get()));
        addRecipe(new ItemStack(ModItems.PLATE_ALUMINIUM.get()),
                100, new ItemStack(ModItems.INGOT_ALUMINIUM.get()));
        addRecipe(new ItemStack(ModItems.PLATE_TUNGSTEN.get()),
                250, new ItemStack(ModItems.INGOT_TUNGSTEN.get()));
        addRecipe(new ItemStack(ModItems.PLATE_BERYLLIUM.get()),
                250, new ItemStack(ModItems.INGOT_BERYLLIUM.get()));
        addRecipe(new ItemStack(ModItems.PLATE_LEAD.get()),
                100, new ItemStack(ModItems.INGOT_LEAD.get()));
        addRecipe(new ItemStack(ModItems.PLATE_NICKEL.get()),
                150, new ItemStack(ModItems.INGOT_NICKEL.get()));
        addRecipe(new ItemStack(ModItems.PLATE_CHROMIUM.get()),
                150, new ItemStack(ModItems.INGOT_CHROMIUM.get()));
        addRecipe(new ItemStack(ModItems.PLATE_SILICON.get()),
                100, new ItemStack(ModItems.INGOT_SILICON.get()));

        // ===== Batch 3: Advanced alloy creation =====
        addRecipe(new ItemStack(ModItems.INGOT_TUNGSTEN_CARBIDE.get()),
                400, new ItemStack(ModItems.INGOT_TUNGSTEN.get()),
                new ItemStack(Items.COAL));
        addRecipe(new ItemStack(ModItems.INGOT_RED_COPPER.get()),
                200, new ItemStack(Items.COPPER_INGOT),
                new ItemStack(Items.REDSTONE));
        addRecipe(new ItemStack(ModItems.INGOT_GUNMETAL.get()),
                250, new ItemStack(ModItems.INGOT_COPPER.get()),
                new ItemStack(Items.IRON_INGOT));
        addRecipe(new ItemStack(ModItems.INGOT_WEAPONSTEEL.get()),
                350, new ItemStack(ModItems.INGOT_STEEL.get()),
                new ItemStack(ModItems.INGOT_TITANIUM.get()));
        addRecipe(new ItemStack(ModItems.INGOT_DURA_STEEL.get()),
                400, new ItemStack(ModItems.INGOT_STEEL.get()),
                new ItemStack(Items.IRON_INGOT),
                new ItemStack(Items.REDSTONE));
        addRecipe(new ItemStack(ModItems.INGOT_BISMUTH_BRONZE.get()),
                200, new ItemStack(ModItems.INGOT_BISMUTH.get()),
                new ItemStack(Items.COPPER_INGOT));
        addRecipe(new ItemStack(ModItems.INGOT_ARSENIC_BRONZE.get()),
                200, new ItemStack(ModItems.INGOT_ARSENIC.get()),
                new ItemStack(Items.COPPER_INGOT));

        // ===== Batch 4: Nuclear fuel assembly =====
        addRecipe(new ItemStack(ModItems.INGOT_URANIUM_FUEL.get()),
                300, new ItemStack(ModItems.INGOT_URANIUM.get()),
                new ItemStack(ModItems.INGOT_URANIUM.get()),
                new ItemStack(Items.REDSTONE));
        addRecipe(new ItemStack(ModItems.INGOT_PLUTONIUM_FUEL.get()),
                400, new ItemStack(ModItems.INGOT_PLUTONIUM.get()),
                new ItemStack(ModItems.INGOT_PLUTONIUM.get()),
                new ItemStack(Items.REDSTONE));
        addRecipe(new ItemStack(ModItems.INGOT_THORIUM_FUEL.get()),
                350, new ItemStack(ModItems.INGOT_TH232.get()),
                new ItemStack(Items.REDSTONE));
        addRecipe(new ItemStack(ModItems.INGOT_MOX_FUEL.get()),
                500, new ItemStack(ModItems.INGOT_URANIUM.get()),
                new ItemStack(ModItems.INGOT_PLUTONIUM.get()),
                new ItemStack(Items.REDSTONE));

        // ===== Batch 5: More plate fabrication =====
        addRecipe(new ItemStack(ModItems.PLATE_MOLYBDENUM.get()),
                250, new ItemStack(ModItems.INGOT_MOLYBDENUM.get()));
        addRecipe(new ItemStack(ModItems.PLATE_NIOBIUM.get()),
                300, new ItemStack(ModItems.INGOT_NIOBIUM.get()));
        addRecipe(new ItemStack(ModItems.PLATE_TANTALIUM.get()),
                300, new ItemStack(ModItems.INGOT_TANTALIUM.get()));
        addRecipe(new ItemStack(ModItems.PLATE_LANTHANIUM.get()),
                300, new ItemStack(ModItems.INGOT_LANTHANIUM.get()));
        addRecipe(new ItemStack(ModItems.PLATE_URANIUM.get()),
                350, new ItemStack(ModItems.INGOT_URANIUM.get()));
        addRecipe(new ItemStack(ModItems.PLATE_DURA_STEEL.get()),
                400, new ItemStack(ModItems.INGOT_DURA_STEEL.get()));
        addRecipe(new ItemStack(ModItems.PLATE_GUNMETAL.get()),
                250, new ItemStack(ModItems.INGOT_GUNMETAL.get()));
        addRecipe(new ItemStack(ModItems.PLATE_WEAPONSTEEL.get()),
                350, new ItemStack(ModItems.INGOT_WEAPONSTEEL.get()));
        addRecipe(new ItemStack(ModItems.PLATE_COMBINE_STEEL.get()),
                500, new ItemStack(ModItems.INGOT_COMBINE_STEEL.get()));
        addRecipe(new ItemStack(ModItems.PLATE_SATURNITE.get()),
                400, new ItemStack(ModItems.INGOT_SATURNITE.get()));
        addRecipe(new ItemStack(ModItems.PLATE_DESH.get()),
                450, new ItemStack(ModItems.INGOT_DESH.get()));
        addRecipe(new ItemStack(ModItems.PLATE_DINEUTRONIUM.get()),
                600, new ItemStack(ModItems.INGOT_DINEUTRONIUM.get()));
        addRecipe(new ItemStack(ModItems.PLATE_SCHRABIDIUM.get()),
                500, new ItemStack(ModItems.INGOT_SCHRABIDIUM.get()));
        addRecipe(new ItemStack(ModItems.PLATE_POLYMER.get()),
                200, new ItemStack(ModItems.INGOT_POLYMER.get()));

        // ===== Batch 6: Isotope processing =====
        addRecipe(new ItemStack(ModItems.INGOT_U235.get()),
                600, new ItemStack(ModItems.INGOT_URANIUM.get()),
                new ItemStack(ModItems.INGOT_URANIUM.get()),
                new ItemStack(Items.REDSTONE));
        addRecipe(new ItemStack(ModItems.INGOT_U238.get()),
                400, new ItemStack(ModItems.INGOT_URANIUM.get()),
                new ItemStack(Items.COAL));
        addRecipe(new ItemStack(ModItems.INGOT_PU239.get()),
                800, new ItemStack(ModItems.INGOT_PLUTONIUM.get()),
                new ItemStack(Items.REDSTONE));
        addRecipe(new ItemStack(ModItems.INGOT_PU240.get()),
                700, new ItemStack(ModItems.INGOT_PLUTONIUM.get()),
                new ItemStack(Items.REDSTONE));
        addRecipe(new ItemStack(ModItems.INGOT_PU241.get()),
                900, new ItemStack(ModItems.INGOT_PLUTONIUM.get()),
                new ItemStack(Items.REDSTONE));
        addRecipe(new ItemStack(ModItems.INGOT_PU238.get()),
                750, new ItemStack(ModItems.INGOT_PLUTONIUM.get()),
                new ItemStack(Items.COAL));
        addRecipe(new ItemStack(ModItems.INGOT_U233.get()),
                700, new ItemStack(ModItems.INGOT_TH232.get()),
                new ItemStack(Items.REDSTONE));
        addRecipe(new ItemStack(ModItems.INGOT_TH232.get()),
                300, new ItemStack(ModItems.INGOT_URANIUM.get()),
                new ItemStack(Items.COAL));

        // ===== Batch 7: Chemical compound creation =====
        addRecipe(new ItemStack(ModItems.INGOT_BAKELITE.get()),
                200, new ItemStack(Items.COAL),
                new ItemStack(Items.COAL),
                new ItemStack(Items.IRON_INGOT));
        addRecipe(new ItemStack(ModItems.INGOT_RUBBER.get()),
                150, new ItemStack(Items.COAL),
                new ItemStack(Items.STICK));
        addRecipe(new ItemStack(ModItems.INGOT_FIBERGLASS.get()),
                250, new ItemStack(Items.IRON_INGOT),
                new ItemStack(Items.GLASS));
        addRecipe(new ItemStack(ModItems.INGOT_PVC.get()),
                200, new ItemStack(Items.COAL),
                new ItemStack(Items.REDSTONE));
        addRecipe(new ItemStack(ModItems.INGOT_BIORUBBER.get()),
                300, new ItemStack(Items.SUGAR_CANE),
                new ItemStack(Items.REDSTONE));
        addRecipe(new ItemStack(ModItems.INGOT_POLYMER.get()),
                350, new ItemStack(Items.IRON_INGOT),
                new ItemStack(Items.REDSTONE));
        addRecipe(new ItemStack(ModItems.INGOT_GRAPHITE.get()),
                150, new ItemStack(Items.COAL),
                new ItemStack(Items.COAL));
        addRecipe(new ItemStack(ModItems.INGOT_FIREBRICK.get()),
                200, new ItemStack(net.minecraft.world.level.block.Blocks.BRICKS),
                new ItemStack(Items.COAL));

        // ===== Batch 8: Advanced alloy creation =====
        addRecipe(new ItemStack(ModItems.INGOT_OSMIRIDIUM.get()),
                500, new ItemStack(ModItems.INGOT_PLUTONIUM.get()),
                new ItemStack(ModItems.INGOT_TUNGSTEN.get()));
        addRecipe(new ItemStack(ModItems.INGOT_SCHRABIDIUM.get()),
                800, new ItemStack(ModItems.INGOT_URANIUM.get()),
                new ItemStack(ModItems.INGOT_PLUTONIUM.get()),
                new ItemStack(Items.REDSTONE));
        addRecipe(new ItemStack(ModItems.INGOT_SCHRABIDATE.get()),
                900, new ItemStack(ModItems.INGOT_SCHRABIDIUM.get()),
                new ItemStack(Items.REDSTONE));
        addRecipe(new ItemStack(ModItems.INGOT_SCHRARANIUM.get()),
                600, new ItemStack(ModItems.INGOT_SCHRABIDIUM.get()),
                new ItemStack(ModItems.INGOT_URANIUM.get()));
        addRecipe(new ItemStack(ModItems.INGOT_TECHNETIUM.get()),
                600, new ItemStack(ModItems.INGOT_PLUTONIUM.get()),
                new ItemStack(ModItems.INGOT_URANIUM.get()));
        addRecipe(new ItemStack(ModItems.INGOT_STARMETAL.get()),
                700, new ItemStack(ModItems.INGOT_TITANIUM.get()),
                new ItemStack(ModItems.INGOT_TUNGSTEN.get()),
                new ItemStack(Items.REDSTONE));
        addRecipe(new ItemStack(ModItems.INGOT_AUSTRALIUM.get()),
                600, new ItemStack(Items.GOLD_INGOT),
                new ItemStack(Items.REDSTONE));
        addRecipe(new ItemStack(ModItems.INGOT_REIIUM.get()),
                500, new ItemStack(ModItems.INGOT_TITANIUM.get()),
                new ItemStack(Items.GOLD_INGOT));
        addRecipe(new ItemStack(ModItems.INGOT_SOLINIUM.get()),
                550, new ItemStack(ModItems.INGOT_SILICON.get()),
                new ItemStack(Items.REDSTONE));
        addRecipe(new ItemStack(ModItems.INGOT_VERTICIUM.get()),
                500, new ItemStack(ModItems.INGOT_COBALT.get()),
                new ItemStack(ModItems.INGOT_TITANIUM.get()));

        // ===== Batch 9: Nuclear fuel variants =====
        addRecipe(new ItemStack(ModItems.INGOT_AMERICIUM_FUEL.get()),
                600, new ItemStack(ModItems.INGOT_AM241.get()),
                new ItemStack(Items.REDSTONE));
        addRecipe(new ItemStack(ModItems.INGOT_NEPTUNIUM_FUEL.get()),
                500, new ItemStack(ModItems.INGOT_NEPTUNIUM.get()),
                new ItemStack(Items.REDSTONE));
        addRecipe(new ItemStack(ModItems.INGOT_SCHRABIDIUM_FUEL.get()),
                800, new ItemStack(ModItems.INGOT_SCHRABIDIUM.get()),
                new ItemStack(Items.REDSTONE));
        addRecipe(new ItemStack(ModItems.INGOT_URANIUM_FUEL.get()),
                400, new ItemStack(ModItems.INGOT_U235.get()),
                new ItemStack(Items.REDSTONE));
        addRecipe(new ItemStack(ModItems.INGOT_PLUTONIUM_FUEL.get()),
                500, new ItemStack(ModItems.INGOT_PU239.get()),
                new ItemStack(Items.REDSTONE));

        // ===== Batch 10: Material conversion =====
        addRecipe(new ItemStack(ModItems.INGOT_CO60.get()),
                500, new ItemStack(ModItems.INGOT_COBALT.get()),
                new ItemStack(Items.REDSTONE));
        addRecipe(new ItemStack(ModItems.INGOT_AU198.get()),
                500, new ItemStack(Items.GOLD_INGOT),
                new ItemStack(Items.REDSTONE));
        addRecipe(new ItemStack(ModItems.INGOT_PB209.get()),
                400, new ItemStack(ModItems.INGOT_LEAD.get()),
                new ItemStack(Items.REDSTONE));
        addRecipe(new ItemStack(ModItems.INGOT_RA226.get()),
                600, new ItemStack(ModItems.INGOT_URANIUM.get()),
                new ItemStack(ModItems.INGOT_LEAD.get()));
        addRecipe(new ItemStack(ModItems.INGOT_I131.get()),
                400, new ItemStack(ModItems.INGOT_URANIUM.get()),
                new ItemStack(Items.REDSTONE));
        addRecipe(new ItemStack(ModItems.INGOT_SR90.get()),
                400, new ItemStack(ModItems.INGOT_PLUTONIUM.get()),
                new ItemStack(Items.REDSTONE));
        addRecipe(new ItemStack(ModItems.INGOT_CFT.get()),
                500, new ItemStack(ModItems.INGOT_URANIUM.get()),
                new ItemStack(ModItems.INGOT_TITANIUM.get()));
        addRecipe(new ItemStack(ModItems.INGOT_AM_MIX.get()),
                500, new ItemStack(ModItems.INGOT_AM241.get()),
                new ItemStack(ModItems.INGOT_AM242.get()));
        addRecipe(new ItemStack(ModItems.INGOT_PU_MIX.get()),
                400, new ItemStack(ModItems.INGOT_PU239.get()),
                new ItemStack(ModItems.INGOT_PU240.get()));

        // ===== Batch 11: Special compounds =====
        addRecipe(new ItemStack(ModItems.INGOT_CDALLOY.get()),
                300, new ItemStack(ModItems.INGOT_CADMIUM.get()),
                new ItemStack(Items.IRON_INGOT));
        addRecipe(new ItemStack(ModItems.INGOT_TCALLOY.get()),
                300, new ItemStack(ModItems.INGOT_TITANIUM.get()),
                new ItemStack(Items.IRON_INGOT));
        addRecipe(new ItemStack(ModItems.INGOT_GH336.get()),
                400, new ItemStack(ModItems.INGOT_NICKEL.get()),
                new ItemStack(ModItems.INGOT_CHROMIUM.get()));
        addRecipe(new ItemStack(ModItems.INGOT_FERROURANIUM.get()),
                350, new ItemStack(ModItems.INGOT_URANIUM.get()),
                new ItemStack(Items.IRON_INGOT));
        addRecipe(new ItemStack(ModItems.INGOT_CERIUM.get()),
                300, new ItemStack(ModItems.INGOT_LANTHANIUM.get()),
                new ItemStack(Items.REDSTONE));
        addRecipe(new ItemStack(ModItems.INGOT_CAESIUM.get()),
                350, new ItemStack(ModItems.INGOT_BISMUTH.get()),
                new ItemStack(Items.REDSTONE));
        addRecipe(new ItemStack(ModItems.INGOT_CALCIUM.get()),
                200, new ItemStack(net.minecraft.world.level.block.Blocks.BONE_BLOCK),
                new ItemStack(Items.REDSTONE));
        addRecipe(new ItemStack(ModItems.INGOT_PHOSPHORUS.get()),
                250, new ItemStack(net.minecraft.world.level.block.Blocks.BONE_BLOCK),
                new ItemStack(Items.COAL));
        addRecipe(new ItemStack(ModItems.INGOT_BROMINE.get()),
                300, new ItemStack(Items.REDSTONE),
                new ItemStack(Items.IRON_INGOT));
        addRecipe(new ItemStack(ModItems.INGOT_IODINE.get()),
                300, new ItemStack(Items.REDSTONE),
                new ItemStack(Items.GOLD_INGOT));

        // ===== Batch 12: Additional plates =====
        addRecipe(new ItemStack(ModItems.PLATE_BISMUTH.get()),
                200, new ItemStack(ModItems.INGOT_BISMUTH.get(), 2),
                new ItemStack(ModItems.INGOT_URANIUM.get()),
                new ItemStack(ModItems.POWDER_NIOBIUM.get()));
        addRecipe(new ItemStack(ModItems.PLATE_DESH.get(), 4),
                200, new ItemStack(ModItems.INGOT_DESH.get(), 4),
                new ItemStack(ModItems.INGOT_POLYMER.get(), 2),
                new ItemStack(ModItems.INGOT_DURA_STEEL.get()));
        addRecipe(new ItemStack(ModItems.PLATE_DALEKANIUM.get()),
                200, new ItemStack(ModBlocks.BLOCK_METEOR.get()));
        addRecipe(new ItemStack(ModItems.PLATE_MIXED.get(), 4),
                50, new ItemStack(ModItems.PLATE_COPPER.get(), 2),
                new ItemStack(ModItems.INGOT_SATURNITE.get()),
                new ItemStack(ModItems.PLATE_DURA_STEEL.get()));

        // ===== Batch 13: Machine parts =====
        addRecipe(new ItemStack(ModItems.CENTRIFUGE_ELEMENT.get()),
                100, new ItemStack(ModItems.PLATE_DURA_STEEL.get(), 4),
                new ItemStack(ModItems.PLATE_TITANIUM.get(), 4),
                new ItemStack(ModItems.MOTOR.get()));
        addRecipe(new ItemStack(ModItems.REACTOR_CORE.get()),
                100, new ItemStack(ModItems.PLATE_LEAD.get(), 4),
                new ItemStack(ModItems.INGOT_BERYLLIUM.get(), 8),
                new ItemStack(ModItems.PLATE_DURA_STEEL.get(), 8),
                new ItemStack(ModItems.POWDER_ASBESTOS.get(), 4));
        addRecipe(new ItemStack(ModItems.THERMO_ELEMENT.get()),
                60, new ItemStack(ModItems.PLATE_STEEL.get()),
                new ItemStack(ModItems.POWDER_NEODYMIUM.get(), 2));
        addRecipe(new ItemStack(ModItems.RTG_UNIT.get()),
                100, new ItemStack(ModItems.PLATE_LEAD.get(), 2),
                new ItemStack(ModItems.PLATE_COPPER.get(), 4),
                new ItemStack(ModItems.THERMO_ELEMENT.get(), 2));
        addRecipe(new ItemStack(ModItems.MAGNETRON.get()),
                40, new ItemStack(ModItems.PLATE_COPPER.get(), 3),
                new ItemStack(ModItems.INGOT_TUNGSTEN.get(), 4));
        addRecipe(new ItemStack(ModItems.DRILL_TITANIUM.get()),
                100, new ItemStack(ModItems.PLATE_DURA_STEEL.get()),
                new ItemStack(ModItems.PLATE_TITANIUM.get(), 8));
        addRecipe(new ItemStack(ModItems.ENTANGLEMENT_KIT.get()),
                200, new ItemStack(ModItems.PLATE_DURA_STEEL.get(), 4),
                new ItemStack(ModItems.PLATE_COPPER.get(), 24),
                new ItemStack(Items.GOLD_INGOT, 16));
        addRecipe(new ItemStack(ModItems.DYSFUNCTIONAL_REACTOR.get()),
                200, new ItemStack(ModItems.INGOT_STEEL.get(), 4),
                new ItemStack(ModItems.PLATE_LEAD.get(), 4),
                new ItemStack(ModItems.ROD_QUAD_EMPTY.get(), 10));

        // ===== Batch 14: Cloth and filters =====
        addRecipe(new ItemStack(ModItems.HAZMAT_CLOTH.get(), 4),
                50, new ItemStack(ModItems.POWDER_LEAD.get(), 4),
                new ItemStack(Items.STRING, 8));
        addRecipe(new ItemStack(ModItems.ASBESTOS_CLOTH.get(), 4),
                50, new ItemStack(ModItems.POWDER_ASBESTOS.get()),
                new ItemStack(Items.STRING, 8));
        addRecipe(new ItemStack(ModItems.FILTER_COAL.get()),
                50, new ItemStack(ModItems.POWDER_COAL.get(), 4),
                new ItemStack(Items.STRING, 2),
                new ItemStack(Items.PAPER));

        // ===== Batch 15: Cyclotron parts =====
        addRecipe(new ItemStack(ModItems.PART_LITHIUM.get(), 8),
                40, new ItemStack(ModItems.POWDER_LITHIUM.get()));
        addRecipe(new ItemStack(ModItems.PART_BERYLLIUM.get(), 8),
                40, new ItemStack(ModItems.POWDER_BERYLLIUM.get()));
        addRecipe(new ItemStack(ModItems.PART_CARBON.get(), 8),
                40, new ItemStack(ModItems.POWDER_COAL.get()));
        addRecipe(new ItemStack(ModItems.PART_COPPER.get(), 8),
                40, new ItemStack(ModItems.POWDER_COPPER.get()));
        addRecipe(new ItemStack(ModItems.PART_PLUTONIUM.get(), 8),
                40, new ItemStack(ModItems.POWDER_PLUTONIUM.get()));

        // ===== Batch 16: Bunker blocks =====
        addRecipe(new ItemStack(ModBlocks.CMB_BRICK.get(), 8),
                100, new ItemStack(ModBlocks.CONCRETE_SMOOTH.get(), 4),
                new ItemStack(ModItems.PLATE_COMBINE_STEEL.get(), 4));
        addRecipe(new ItemStack(ModBlocks.CMB_BRICK_REINFORCED.get(), 8),
                100, new ItemStack(ModItems.INGOT_SATURNITE.get(), 8),
                new ItemStack(ModBlocks.CMB_BRICK.get(), 4));
        addRecipe(new ItemStack(ModBlocks.SEAL_FRAME.get()),
                100, new ItemStack(ModItems.INGOT_DURA_STEEL.get()),
                new ItemStack(ModItems.PLATE_STEEL.get()));
        addRecipe(new ItemStack(ModBlocks.SEAL_CONTROLLER.get()),
                100, new ItemStack(ModItems.INGOT_DURA_STEEL.get()),
                new ItemStack(ModItems.PLATE_STEEL.get()),
                new ItemStack(ModItems.INGOT_POLYMER.get(), 4));

        // ===== Batch 17: Nuclear doors =====
        addRecipe(new ItemStack(ModBlocks.VAULT_DOOR.get()),
                600, new ItemStack(ModItems.INGOT_STEEL.get(), 32),
                new ItemStack(ModItems.INGOT_DURA_STEEL.get(), 32),
                new ItemStack(ModItems.PLATE_LEAD.get(), 8),
                new ItemStack(ModItems.INGOT_POLYMER.get(), 12),
                new ItemStack(ModItems.MOTOR.get(), 3));
        addRecipe(new ItemStack(ModBlocks.BLAST_DOOR.get()),
                200, new ItemStack(ModItems.INGOT_STEEL.get(), 12),
                new ItemStack(ModItems.PLATE_LEAD.get(), 6),
                new ItemStack(ModItems.INGOT_POLYMER.get(), 2),
                new ItemStack(ModItems.MOTOR.get()));
        addRecipe(new ItemStack(ModBlocks.FIRE_DOOR.get()),
                300, new ItemStack(ModItems.PLATE_STEEL.get(), 16),
                new ItemStack(ModItems.MOTOR.get(), 2));
        addRecipe(new ItemStack(ModBlocks.SLIDING_BLAST_DOOR.get()),
                200, new ItemStack(ModItems.PLATE_STEEL.get(), 16),
                new ItemStack(ModItems.INGOT_TUNGSTEN.get(), 8),
                new ItemStack(ModBlocks.REINFORCED_GLASS.get(), 4),
                new ItemStack(ModItems.INGOT_POLYMER.get(), 4),
                new ItemStack(ModItems.MOTOR.get(), 2));
        addRecipe(new ItemStack(ModBlocks.WATER_DOOR.get()),
                200, new ItemStack(ModItems.PLATE_STEEL.get(), 16),
                new ItemStack(ModItems.MOTOR.get()));
        addRecipe(new ItemStack(ModBlocks.LARGE_VEHICLE_DOOR.get()),
                400, new ItemStack(ModItems.PLATE_STEEL.get(), 16),
                new ItemStack(ModItems.PLATE_POLYMER.get(), 4),
                new ItemStack(ModItems.MOTOR.get(), 4));
        addRecipe(new ItemStack(ModBlocks.ROUND_AIRLOCK_DOOR.get()),
                400, new ItemStack(ModItems.PLATE_STEEL.get(), 12),
                new ItemStack(ModItems.PLATE_POLYMER.get(), 16),
                new ItemStack(ModItems.MOTOR.get(), 4));
        addRecipe(new ItemStack(ModBlocks.SECURE_ACCESS_DOOR.get()),
                400, new ItemStack(ModItems.PLATE_STEEL.get(), 12),
                new ItemStack(ModItems.PLATE_POLYMER.get(), 8),
                new ItemStack(ModItems.MOTOR.get(), 4));
        addRecipe(new ItemStack(ModBlocks.SLIDING_SEAL_DOOR.get()),
                200, new ItemStack(ModItems.PLATE_STEEL.get(), 12),
                new ItemStack(ModItems.PLATE_POLYMER.get(), 4),
                new ItemStack(ModItems.MOTOR.get(), 2));
        addRecipe(new ItemStack(ModBlocks.SILO_HATCH.get()),
                200, new ItemStack(ModItems.PLATE_STEEL.get(), 4),
                new ItemStack(ModItems.PLATE_POLYMER.get(), 4),
                new ItemStack(ModItems.MOTOR.get(), 2));
        addRecipe(new ItemStack(ModBlocks.SILO_HATCH_LARGE.get()),
                300, new ItemStack(ModItems.PLATE_STEEL.get(), 6),
                new ItemStack(ModItems.PLATE_POLYMER.get(), 8),
                new ItemStack(ModItems.MOTOR.get(), 2));
        addRecipe(new ItemStack(ModBlocks.QE_CONTAINMENT.get()),
                400, new ItemStack(ModItems.PLATE_STEEL.get(), 6),
                new ItemStack(ModItems.PLATE_POLYMER.get(), 8),
                new ItemStack(ModItems.MOTOR.get(), 2));
        addRecipe(new ItemStack(ModBlocks.TRANSITION_SEAL.get()),
                1200, new ItemStack(ModBlocks.CMB_BRICK_REINFORCED.get(), 16),
                new ItemStack(ModItems.PLATE_STEEL.get(), 64),
                new ItemStack(ModItems.INGOT_POLYMER.get(), 36),
                new ItemStack(ModItems.MOTOR_DESH.get(), 16));

        // ===== Batch 18: Waste barrels =====
        addRecipe(new ItemStack(ModBlocks.YELLOW_BARREL.get()),
                400, new ItemStack(ModItems.TANK_STEEL.get()),
                new ItemStack(ModItems.PLATE_LEAD.get(), 2),
                new ItemStack(ModItems.NUCLEAR_WASTE.get(), 10));
        addRecipe(new ItemStack(ModBlocks.VITRIFIED_BARREL.get()),
                400, new ItemStack(ModItems.TANK_STEEL.get()),
                new ItemStack(ModItems.PLATE_LEAD.get(), 2),
                new ItemStack(ModItems.NUCLEAR_WASTE_VITRIFIED.get(), 10));

        // ===== Batch 19: Basic machine blocks =====
        addRecipe(new ItemStack(ModBlocks.MACHINE_SHREDDER.get()),
                100, new ItemStack(ModItems.PLATE_STEEL.get(), 8),
                new ItemStack(ModItems.PLATE_COPPER.get(), 4),
                new ItemStack(ModItems.MOTOR.get(), 2));
        addRecipe(new ItemStack(ModBlocks.MACHINE_ASSEMBLY_MACHINE.get()),
                200, new ItemStack(ModItems.INGOT_STEEL.get(), 4),
                new ItemStack(ModItems.PLATE_COPPER.get(), 4),
                new ItemStack(ModItems.MOTOR.get(), 2),
                new ItemStack(ModItems.CIRCUIT.get()));
        addRecipe(new ItemStack(ModBlocks.MACHINE_CHEMICAL_PLANT.get()),
                200, new ItemStack(ModItems.INGOT_STEEL.get(), 8),
                new ItemStack(ModItems.PLATE_POLYMER.get(), 16),
                new ItemStack(ModItems.MOTOR.get(), 2),
                new ItemStack(ModItems.COIL_TUNGSTEN.get(), 2),
                new ItemStack(ModItems.CIRCUIT.get()));
        addRecipe(new ItemStack(ModBlocks.MACHINE_CENTRIFUGE.get()),
                200, new ItemStack(ModItems.CENTRIFUGE_ELEMENT.get()),
                new ItemStack(ModItems.INGOT_POLYMER.get(), 4),
                new ItemStack(ModItems.PLATE_STEEL.get(), 8),
                new ItemStack(ModItems.PLATE_COPPER.get(), 4),
                new ItemStack(ModItems.CIRCUIT.get()));
        addRecipe(new ItemStack(ModBlocks.MACHINE_GASCENT.get()),
                400, new ItemStack(ModItems.CENTRIFUGE_ELEMENT.get(), 4),
                new ItemStack(ModItems.INGOT_POLYMER.get(), 8),
                new ItemStack(ModItems.INGOT_DESH.get(), 2),
                new ItemStack(ModItems.PLATE_STEEL.get(), 8),
                new ItemStack(ModItems.CIRCUIT.get()));
        addRecipe(new ItemStack(ModBlocks.MACHINE_ARC_FURNACE.get()),
                200, new ItemStack(ModItems.INGOT_FIREBRICK.get(), 16),
                new ItemStack(ModItems.PLATE_STEEL.get(), 8),
                new ItemStack(ModBlocks.MACHINE_TRANSFORMER.get()),
                new ItemStack(ModItems.CIRCUIT.get()));
        addRecipe(new ItemStack(ModBlocks.MACHINE_CRYSTALLIZER.get()),
                200, new ItemStack(ModItems.PLATE_STEEL.get(), 2),
                new ItemStack(ModItems.INGOT_DESH.get(), 4),
                new ItemStack(ModItems.MOTOR.get()),
                new ItemStack(ModItems.CIRCUIT.get(), 2));
        addRecipe(new ItemStack(ModBlocks.MACHINE_ELECTROLYSER.get()),
                200, new ItemStack(ModItems.PLATE_STEEL.get(), 8),
                new ItemStack(ModItems.PLATE_COPPER.get(), 16),
                new ItemStack(ModItems.INGOT_POLYMER.get(), 8),
                new ItemStack(ModItems.INGOT_FIREBRICK.get(), 16),
                new ItemStack(ModItems.COIL_COPPER.get(), 16),
                new ItemStack(ModItems.CIRCUIT.get(), 8));
        addRecipe(new ItemStack(ModBlocks.MACHINE_RTG_GREY.get()),
                200, new ItemStack(ModItems.RTG_UNIT.get(), 3),
                new ItemStack(ModItems.PLATE_STEEL.get(), 4),
                new ItemStack(ModItems.INGOT_POLYMER.get(), 4));

        // ===== Batch 20: Oil processing machines =====
        addRecipe(new ItemStack(ModBlocks.MACHINE_WELL.get()),
                200, new ItemStack(ModItems.PLATE_STEEL.get(), 8),
                new ItemStack(ModItems.PLATE_COPPER.get(), 2),
                new ItemStack(ModItems.MOTOR.get()),
                new ItemStack(ModItems.DRILL_TITANIUM.get()));
        addRecipe(new ItemStack(ModBlocks.MACHINE_PUMPJACK.get()),
                400, new ItemStack(ModItems.PLATE_DURA_STEEL.get(), 8),
                new ItemStack(ModItems.PLATE_STEEL.get(), 8),
                new ItemStack(ModItems.MOTOR_DESH.get()),
                new ItemStack(ModItems.DRILL_TITANIUM.get()));
        addRecipe(new ItemStack(ModBlocks.MACHINE_FRACKING_TOWER.get()),
                600, new ItemStack(ModItems.INGOT_STEEL.get(), 24),
                new ItemStack(ModItems.DRILL_TITANIUM.get()),
                new ItemStack(ModItems.MOTOR_DESH.get(), 2),
                new ItemStack(ModItems.PLATE_DESH.get(), 24),
                new ItemStack(ModItems.CIRCUIT.get(), 16));
        addRecipe(new ItemStack(ModBlocks.MACHINE_FLARE.get()),
                100, new ItemStack(ModItems.PLATE_STEEL.get(), 12),
                new ItemStack(ModItems.PLATE_COPPER.get(), 4),
                new ItemStack(ModItems.THERMO_ELEMENT.get(), 3));
        addRecipe(new ItemStack(ModBlocks.MACHINE_REFINERY.get()),
                200, new ItemStack(ModItems.PLATE_STEEL.get(), 3),
                new ItemStack(ModItems.PLATE_COPPER.get(), 8),
                new ItemStack(ModItems.PLATE_POLYMER.get(), 8),
                new ItemStack(ModItems.CIRCUIT.get(), 3));
        addRecipe(new ItemStack(ModBlocks.MACHINE_CATALYTIC_CRACKER.get()),
                200, new ItemStack(ModBlocks.STEEL_SCAFFOLD.get(), 16),
                new ItemStack(ModItems.INGOT_DESH.get(), 12),
                new ItemStack(ModItems.INGOT_NIOBIUM.get(), 4));
        addRecipe(new ItemStack(ModBlocks.MACHINE_RADIOLYSIS.get()),
                200, new ItemStack(ModItems.PLATE_LEAD.get(), 12),
                new ItemStack(ModItems.PLATE_COPPER.get(), 4),
                new ItemStack(ModItems.INGOT_POLYMER.get(), 8),
                new ItemStack(ModItems.THERMO_ELEMENT.get(), 8));
        addRecipe(new ItemStack(ModBlocks.MACHINE_COKER.get()),
                200, new ItemStack(ModItems.PLATE_STEEL.get(), 8),
                new ItemStack(ModItems.PLATE_COPPER.get(), 8),
                new ItemStack(ModItems.INGOT_POLYMER.get(), 4),
                new ItemStack(ModItems.INGOT_NIOBIUM.get(), 4));
        addRecipe(new ItemStack(ModBlocks.MACHINE_VACUUM_DISTILL.get()),
                200, new ItemStack(ModItems.PLATE_STEEL.get(), 16),
                new ItemStack(ModItems.PLATE_COPPER.get(), 16),
                new ItemStack(ModItems.SPHERE_STEEL.get()),
                new ItemStack(ModItems.MOTOR_DESH.get(), 3),
                new ItemStack(ModItems.CIRCUIT.get(), 4));
        addRecipe(new ItemStack(ModBlocks.MACHINE_CATALYTIC_REFORMER.get()),
                200, new ItemStack(ModItems.PLATE_STEEL.get(), 12),
                new ItemStack(ModItems.PLATE_COPPER.get(), 8),
                new ItemStack(ModItems.INGOT_NIOBIUM.get(), 8),
                new ItemStack(ModItems.MOTOR.get()),
                new ItemStack(ModItems.CIRCUIT.get()));
        addRecipe(new ItemStack(ModBlocks.MACHINE_HYDROTREATER.get()),
                200, new ItemStack(ModItems.PLATE_STEEL.get(), 8),
                new ItemStack(ModItems.PLATE_COPPER.get(), 4),
                new ItemStack(ModItems.INGOT_NIOBIUM.get(), 8),
                new ItemStack(ModItems.MOTOR_DESH.get(), 2),
                new ItemStack(ModItems.CIRCUIT.get()));

        // ===== Batch 21: Fluid machines =====
        addRecipe(new ItemStack(ModBlocks.MACHINE_LIQUEFACTOR.get()),
                200, new ItemStack(ModItems.PLATE_STEEL.get(), 4),
                new ItemStack(ModItems.PLATE_COPPER.get(), 12),
                new ItemStack(ModItems.CIRCUIT.get(), 12),
                new ItemStack(ModItems.COIL_TUNGSTEN.get(), 8));
        addRecipe(new ItemStack(ModBlocks.MACHINE_SOLIDIFIER.get()),
                200, new ItemStack(ModItems.PLATE_STEEL.get(), 4),
                new ItemStack(ModItems.PLATE_ALUMINIUM.get(), 12),
                new ItemStack(ModItems.INGOT_POLYMER.get(), 4),
                new ItemStack(ModItems.CIRCUIT.get(), 12),
                new ItemStack(ModItems.COIL_COPPER.get(), 4));
        addRecipe(new ItemStack(ModBlocks.MACHINE_COMPRESSOR.get()),
                200, new ItemStack(ModItems.PLATE_STEEL.get(), 8),
                new ItemStack(ModItems.PLATE_COPPER.get(), 4),
                new ItemStack(ModItems.MOTOR.get(), 3),
                new ItemStack(ModItems.CIRCUIT.get()));
        addRecipe(new ItemStack(ModBlocks.MACHINE_EPRESS.get()),
                100, new ItemStack(ModItems.PLATE_STEEL.get(), 8),
                new ItemStack(ModItems.INGOT_POLYMER.get(), 4),
                new ItemStack(ModItems.CIRCUIT.get()));
        addRecipe(new ItemStack(ModBlocks.MACHINE_SILEX.get()),
                400, new ItemStack(ModBlocks.GLASS_QUARTZ.get(), 16),
                new ItemStack(ModItems.PLATE_STEEL.get(), 8),
                new ItemStack(ModItems.INGOT_DESH.get(), 4),
                new ItemStack(ModItems.INGOT_POLYMER.get(), 8));
        addRecipe(new ItemStack(ModBlocks.MACHINE_EXCAVATOR.get()),
                200, new ItemStack(Blocks.STONE_BRICKS, 8),
                new ItemStack(ModItems.INGOT_STEEL.get(), 8),
                new ItemStack(Items.IRON_INGOT, 8),
                new ItemStack(ModItems.MOTOR.get(), 2),
                new ItemStack(ModItems.CIRCUIT.get()));
        addRecipe(new ItemStack(ModBlocks.MACHINE_ORE_SLOPPER.get()),
                200, new ItemStack(ModItems.PLATE_STEEL.get(), 6),
                new ItemStack(ModItems.PLATE_TITANIUM.get(), 8),
                new ItemStack(ModItems.MOTOR.get(), 3),
                new ItemStack(ModItems.CIRCUIT.get()));
        addRecipe(new ItemStack(ModBlocks.MACHINE_MINING_LASER.get()),
                400, new ItemStack(ModItems.PLATE_STEEL.get(), 16),
                new ItemStack(ModItems.PLATE_DURA_STEEL.get(), 4),
                new ItemStack(ModItems.CRYSTAL_REDSTONE.get(), 3),
                new ItemStack(Items.DIAMOND, 3),
                new ItemStack(ModItems.INGOT_POLYMER.get(), 8),
                new ItemStack(ModItems.MOTOR.get(), 3));

        // ===== Batch 22: Generators =====
        addRecipe(new ItemStack(ModBlocks.MACHINE_DIESEL.get()),
                200, new ItemStack(ModItems.PLATE_STEEL.get()),
                new ItemStack(ModItems.PLATE_COPPER.get(), 2),
                new ItemStack(ModItems.COIL_COPPER.get(), 4));
        addRecipe(new ItemStack(ModBlocks.MACHINE_COMBUSTION_ENGINE.get()),
                300, new ItemStack(ModItems.PLATE_STEEL.get(), 16),
                new ItemStack(ModItems.INGOT_COPPER.get(), 12),
                new ItemStack(ModItems.CANISTER_EMPTY.get(), 4),
                new ItemStack(ModItems.CIRCUIT.get()));
        addRecipe(new ItemStack(ModBlocks.MACHINE_TURBOFAN.get()),
                300, new ItemStack(ModItems.PLATE_TITANIUM.get(), 8),
                new ItemStack(ModItems.INGOT_POLYMER.get(), 12),
                new ItemStack(ModItems.TURBINE_TUNGSTEN.get()),
                new ItemStack(Items.GOLD_INGOT, 12),
                new ItemStack(ModItems.CIRCUIT.get(), 3));
        addRecipe(new ItemStack(ModBlocks.MACHINE_TURBINEGAS.get()),
                400, new ItemStack(ModItems.INGOT_STEEL.get(), 10),
                new ItemStack(Items.GOLD_INGOT, 12),
                new ItemStack(ModItems.TURBINE_TUNGSTEN.get()),
                new ItemStack(ModItems.INGOT_POLYMER.get(), 12),
                new ItemStack(ModItems.CIRCUIT.get(), 3));
        addRecipe(new ItemStack(ModBlocks.MACHINE_HEPHAESTUS.get()),
                200, new ItemStack(ModItems.INGOT_STEEL.get(), 24),
                new ItemStack(ModItems.PLATE_COPPER.get(), 24),
                new ItemStack(ModItems.INGOT_NIOBIUM.get(), 4),
                new ItemStack(ModItems.INGOT_POLYMER.get(), 12),
                new ItemStack(ModBlocks.GLASS_QUARTZ.get(), 16));
        addRecipe(new ItemStack(ModBlocks.MACHINE_INDUSTRIAL_TURBINE.get()),
                200, new ItemStack(ModItems.PLATE_STEEL.get(), 12),
                new ItemStack(ModItems.INGOT_POLYMER.get(), 4),
                new ItemStack(ModItems.TURBINE_TITANIUM.get(), 2),
                new ItemStack(Items.GOLD_INGOT, 6),
                new ItemStack(ModItems.CIRCUIT.get(), 4));
        addRecipe(new ItemStack(ModBlocks.MACHINE_CHUNGUS.get()),
                600, new ItemStack(ModItems.INGOT_STEEL.get(), 6),
                new ItemStack(ModItems.PLATE_STEEL.get(), 16),
                new ItemStack(ModItems.PLATE_TITANIUM.get(), 12),
                new ItemStack(ModItems.TURBINE_TUNGSTEN.get(), 5),
                new ItemStack(ModItems.TURBINE_TITANIUM.get(), 3),
                new ItemStack(ModItems.FLYWHEEL_BERYLLIUM.get()),
                new ItemStack(Items.GOLD_INGOT, 48));
        addRecipe(new ItemStack(ModBlocks.MACHINE_RADGEN.get()),
                400, new ItemStack(ModItems.INGOT_STEEL.get(), 8),
                new ItemStack(ModItems.PLATE_STEEL.get(), 32),
                new ItemStack(ModItems.REACTOR_CORE.get(), 3),
                new ItemStack(ModItems.CIRCUIT.get(), 16));

        // ===== Batch 23: Fluid tanks and power storage =====
        addRecipe(new ItemStack(ModBlocks.MACHINE_FLUIDTANK.get()),
                200, new ItemStack(ModItems.PLATE_STEEL.get(), 8),
                new ItemStack(ModItems.INGOT_STEEL.get(), 4));
        addRecipe(new ItemStack(ModBlocks.MACHINE_BAT9000.get()),
                200, new ItemStack(ModItems.PLATE_STEEL.get(), 16),
                new ItemStack(ModBlocks.STEEL_SCAFFOLD.get(), 16));
        addRecipe(new ItemStack(ModBlocks.MACHINE_BIGASSTANK.get()),
                200, new ItemStack(ModItems.PLATE_STEEL.get(), 16),
                new ItemStack(ModBlocks.STEEL_SCAFFOLD.get(), 16));
        addRecipe(new ItemStack(ModBlocks.MACHINE_ORBUS.get()),
                300, new ItemStack(ModItems.PLATE_DURA_STEEL.get(), 8),
                new ItemStack(ModItems.INGOT_SATURNITE.get(), 4));

        // ===== Batch 24: Reactor blocks =====
        addRecipe(new ItemStack(ModBlocks.MACHINE_REACTOR_BREEDING.get()),
                200, new ItemStack(ModItems.REACTOR_CORE.get()),
                new ItemStack(ModItems.INGOT_STEEL.get(), 12),
                new ItemStack(ModItems.PLATE_LEAD.get(), 16),
                new ItemStack(ModBlocks.REINFORCED_GLASS.get(), 4),
                new ItemStack(ModItems.POWDER_ASBESTOS.get(), 4),
                new ItemStack(ModItems.CRT_DISPLAY.get()));
        addRecipe(new ItemStack(ModBlocks.REACTOR_RESEARCH.get()),
                200, new ItemStack(ModItems.INGOT_STEEL.get(), 8),
                new ItemStack(ModItems.MOTOR_DESH.get(), 2),
                new ItemStack(ModItems.INGOT_BERYLLIUM.get(), 5),
                new ItemStack(ModItems.PLATE_LEAD.get(), 8),
                new ItemStack(ModItems.CRT_DISPLAY.get(), 3),
                new ItemStack(ModItems.CIRCUIT.get(), 4));
        addRecipe(new ItemStack(ModBlocks.REACTOR_ZIRNOX.get()),
                600, new ItemStack(ModItems.INGOT_STEEL.get(), 4),
                new ItemStack(ModItems.INGOT_BERYLLIUM.get(), 8),
                new ItemStack(ModItems.INGOT_GRAPHITE.get(), 16),
                new ItemStack(ModItems.INGOT_POLYMER.get(), 16),
                new ItemStack(ModBlocks.CONCRETE_SMOOTH.get(), 16),
                new ItemStack(ModItems.CIRCUIT.get(), 4));
        addRecipe(new ItemStack(ModBlocks.RBMK_BLANK.get()),
                100, new ItemStack(ModBlocks.CONCRETE_ASBESTOS.get(), 4),
                new ItemStack(ModItems.PLATE_STEEL.get(), 2),
                new ItemStack(ModItems.PLATE_COPPER.get(), 4),
                new ItemStack(ModItems.INGOT_POLYMER.get(), 2));
        addRecipe(new ItemStack(ModBlocks.RBMK_AUTOLOADER.get()),
                100, new ItemStack(ModItems.PLATE_STEEL.get(), 4),
                new ItemStack(ModItems.PLATE_LEAD.get(), 4),
                new ItemStack(ModItems.INGOT_BERYLLIUM.get(), 4),
                new ItemStack(ModItems.MOTOR.get(), 3));

        // ===== Batch 25: Accelerator parts =====
        addRecipe(new ItemStack(ModBlocks.PA_BEAMLINE.get()),
                200, new ItemStack(ModItems.PLATE_STEEL.get(), 8),
                new ItemStack(ModItems.PLATE_COPPER.get(), 16),
                new ItemStack(Items.GOLD_INGOT, 4));
        addRecipe(new ItemStack(ModBlocks.PA_RFC.get()),
                400, new ItemStack(ModBlocks.PA_BEAMLINE.get(), 3),
                new ItemStack(ModItems.PLATE_STEEL.get(), 16),
                new ItemStack(ModItems.PLATE_COPPER.get(), 64),
                new ItemStack(ModItems.MAGNETRON.get(), 16));
        addRecipe(new ItemStack(ModBlocks.PA_QUADRUPOLE.get()),
                400, new ItemStack(ModBlocks.PA_BEAMLINE.get()),
                new ItemStack(ModItems.PLATE_STEEL.get(), 16),
                new ItemStack(ModItems.CIRCUIT.get()));
        addRecipe(new ItemStack(ModBlocks.PA_DIPOLE.get()),
                400, new ItemStack(ModBlocks.PA_BEAMLINE.get(), 2),
                new ItemStack(ModItems.PLATE_STEEL.get(), 16),
                new ItemStack(ModItems.CIRCUIT.get(), 4));
        addRecipe(new ItemStack(ModBlocks.PA_SOURCE.get()),
                400, new ItemStack(ModBlocks.PA_BEAMLINE.get(), 3),
                new ItemStack(ModItems.PLATE_STEEL.get(), 16),
                new ItemStack(ModItems.MAGNETRON.get(), 16),
                new ItemStack(ModItems.CIRCUIT.get()));
        addRecipe(new ItemStack(ModBlocks.PA_DETECTOR.get()),
                400, new ItemStack(ModBlocks.PA_BEAMLINE.get(), 3),
                new ItemStack(ModItems.PLATE_STEEL.get(), 24),
                new ItemStack(Items.GOLD_INGOT, 16),
                new ItemStack(ModItems.CIRCUIT.get(), 4));
        addRecipe(new ItemStack(ModBlocks.MACHINE_EXPOSURE_CHAMBER.get()),
                200, new ItemStack(ModItems.PLATE_ALUMINIUM.get(), 12),
                new ItemStack(Items.GOLD_INGOT, 32),
                new ItemStack(ModItems.MOTOR_DESH.get(), 2),
                new ItemStack(ModItems.CIRCUIT.get(), 4),
                new ItemStack(ModBlocks.GLASS_QUARTZ.get(), 16));

        // ===== Batch 26: Factory machines =====
        addRecipe(new ItemStack(ModBlocks.MACHINE_ASSEMBLY_FACTORY.get()),
                400, new ItemStack(ModItems.INGOT_DURA_STEEL.get(), 16),
                new ItemStack(ModItems.INGOT_POLYMER.get(), 16),
                new ItemStack(ModItems.INGOT_BERYLLIUM.get(), 8),
                new ItemStack(ModItems.INGOT_STEEL.get(), 4),
                new ItemStack(ModItems.MOTOR.get(), 12),
                new ItemStack(ModItems.CIRCUIT.get(), 16));
        addRecipe(new ItemStack(ModBlocks.MACHINE_CHEMICAL_FACTORY.get()),
                400, new ItemStack(ModItems.INGOT_DURA_STEEL.get(), 16),
                new ItemStack(ModItems.INGOT_POLYMER.get(), 16),
                new ItemStack(ModItems.INGOT_STEEL.get(), 12),
                new ItemStack(ModItems.MOTOR_DESH.get(), 4),
                new ItemStack(ModItems.COIL_TUNGSTEN.get(), 16),
                new ItemStack(ModItems.CIRCUIT.get(), 16));
        addRecipe(new ItemStack(ModBlocks.MACHINE_PYROOVEN.get()),
                300, new ItemStack(ModItems.PLATE_STEEL.get(), 16),
                new ItemStack(ModItems.INGOT_POLYMER.get(), 16),
                new ItemStack(ModItems.INGOT_CFT.get(), 4),
                new ItemStack(ModItems.MOTOR_DESH.get()),
                new ItemStack(ModItems.CIRCUIT.get()));

        // ===== Batch 27: Condenser and transformer =====
        addRecipe(new ItemStack(ModBlocks.MACHINE_CONDENSER_POWERED.get()),
                600, new ItemStack(ModItems.PLATE_STEEL.get(), 8),
                new ItemStack(ModItems.PLATE_DURA_STEEL.get(), 4),
                new ItemStack(ModItems.MOTOR_DESH.get(), 3));
        addRecipe(new ItemStack(ModBlocks.MACHINE_FORCEFIELD.get()),
                600, new ItemStack(ModItems.PLATE_DURA_STEEL.get(), 8),
                new ItemStack(ModItems.PLATE_DESH.get(), 4),
                new ItemStack(ModItems.COIL_GOLD_TORUS.get(), 6),
                new ItemStack(ModItems.COIL_TUNGSTEN.get(), 12),
                new ItemStack(ModItems.MOTOR.get()),
                new ItemStack(ModItems.CIRCUIT.get(), 4),
                new ItemStack(ModBlocks.MACHINE_TRANSFORMER.get()));
        addRecipe(new ItemStack(ModBlocks.MACHINE_TELEPORTER.get()),
                100, new ItemStack(ModItems.PLATE_TITANIUM.get(), 12),
                new ItemStack(ModItems.PLATE_DURA_STEEL.get(), 12),
                new ItemStack(Items.GOLD_INGOT, 32),
                new ItemStack(ModItems.ENTANGLEMENT_KIT.get()));
        addRecipe(new ItemStack(ModBlocks.MACHINE_RADAR.get()),
                300, new ItemStack(ModItems.PLATE_STEEL.get(), 12),
                new ItemStack(ModItems.INGOT_POLYMER.get(), 12),
                new ItemStack(ModItems.MAGNETRON.get(), 5),
                new ItemStack(ModItems.MOTOR.get()),
                new ItemStack(ModItems.CIRCUIT.get(), 8),
                new ItemStack(ModItems.CRT_DISPLAY.get(), 4));
        addRecipe(new ItemStack(ModBlocks.MACHINE_RADAR_LARGE.get()),
                400, new ItemStack(ModItems.PLATE_STEEL.get(), 6),
                new ItemStack(ModItems.INGOT_POLYMER.get(), 24),
                new ItemStack(ModItems.MAGNETRON.get(), 16),
                new ItemStack(ModItems.MOTOR_DESH.get()),
                new ItemStack(ModItems.CIRCUIT.get(), 4),
                new ItemStack(ModItems.CRT_DISPLAY.get(), 4));

        // ===== Batch 28: Decoration - cap blocks =====
        addRecipe(new ItemStack(ModBlocks.BLOCK_CAP.get()),
                10, new ItemStack(ModItems.CAP_NUKA.get(), 64),
                new ItemStack(ModItems.CAP_NUKA.get(), 64));
        addRecipe(new ItemStack(ModBlocks.BLOCK_CAP.get()),
                10, new ItemStack(ModItems.CAP_QUANTUM.get(), 64),
                new ItemStack(ModItems.CAP_QUANTUM.get(), 64));
        addRecipe(new ItemStack(ModBlocks.BLOCK_CAP.get()),
                10, new ItemStack(ModItems.CAP_SPARKLE.get(), 64),
                new ItemStack(ModItems.CAP_SPARKLE.get(), 64));
        addRecipe(new ItemStack(ModBlocks.BLOCK_CAP.get()),
                10, new ItemStack(ModItems.CAP_RAD.get(), 64),
                new ItemStack(ModItems.CAP_RAD.get(), 64));

        // ===== Batch 29: PUREX and Precass =====
        addRecipe(new ItemStack(ModBlocks.MACHINE_PUREX.get()),
                300, new ItemStack(ModItems.INGOT_STEEL.get(), 4),
                new ItemStack(ModItems.PLATE_LEAD.get(), 4),
                new ItemStack(ModItems.INGOT_POLYMER.get(), 8),
                new ItemStack(ModItems.MOTOR_DESH.get()),
                new ItemStack(ModItems.CIRCUIT.get(), 4));
        addRecipe(new ItemStack(ModBlocks.MACHINE_PRECASS.get()),
                1200, new ItemStack(ModItems.PLATE_STEEL.get(), 8),
                new ItemStack(ModItems.INGOT_ZIRCONIUM.get(), 8),
                new ItemStack(ModItems.MOTOR.get(), 4),
                new ItemStack(ModItems.CIRCUIT.get(), 4));

        // ===== Batch 30: Strand caster and more =====
        addRecipe(new ItemStack(ModBlocks.MACHINE_STRAND_CASTER.get()),
                200, new ItemStack(ModItems.INGOT_FIREBRICK.get(), 16),
                new ItemStack(ModItems.PLATE_STEEL.get(), 6),
                new ItemStack(ModItems.PLATE_COPPER.get(), 2),
                new ItemStack(ModBlocks.CONCRETE_SMOOTH.get(), 8));
        addRecipe(new ItemStack(ModBlocks.MACHINE_COMPRESSOR_COMPACT.get()),
                200, new ItemStack(ModItems.PLATE_STEEL.get(), 8),
                new ItemStack(ModItems.PLATE_TITANIUM.get(), 4),
                new ItemStack(ModItems.MOTOR.get(), 2),
                new ItemStack(ModItems.CIRCUIT.get(), 4));
        addRecipe(new ItemStack(ModBlocks.MACHINE_FEL.get()),
                400, new ItemStack(Items.GOLD_INGOT, 64),
                new ItemStack(ModItems.PLATE_STEEL.get(), 12),
                new ItemStack(ModItems.INGOT_POLYMER.get(), 16),
                new ItemStack(ModItems.CIRCUIT.get(), 16));

        // ===== Batch 31: Cyclotron =====
        addRecipe(new ItemStack(ModBlocks.MACHINE_CYCLOTRON.get()),
                600, new ItemStack(ModItems.POWDER_NEODYMIUM.get(), 32),
                new ItemStack(ModItems.PLATE_STEEL.get(), 32),
                new ItemStack(ModItems.PLATE_ALUMINIUM.get(), 32),
                new ItemStack(ModItems.INGOT_POLYMER.get(), 24),
                new ItemStack(ModItems.INGOT_POLYMER.get(), 24),
                new ItemStack(ModItems.PLATE_COPPER.get(), 8),
                new ItemStack(ModItems.CIRCUIT.get(), 16));
    }

    public static void addRecipe(ItemStack output, int fluxCost, ItemStack... inputs) {
        List<Object> key = new ArrayList<>();
        for (ItemStack input : inputs) {
            key.add(new ComparableStack(input.getItem(), input.getCount(), input.getDamageValue()));
        }
        recipes.put(key, new AssemblyRecipe(output, fluxCost));
    }

    public static AssemblyRecipe getOutput(ItemStack... inputs) {
        List<ComparableStack> inputList = new ArrayList<>();
        for (ItemStack input : inputs) {
            if (!input.isEmpty()) {
                inputList.add(new ComparableStack(input.getItem(), input.getCount(), input.getDamageValue()));
            }
        }

        for (Map.Entry<List<Object>, AssemblyRecipe> entry : recipes.entrySet()) {
            if (matchesInputs(inputList, entry.getKey())) {
                return entry.getValue();
            }
        }
        return null;
    }

    private static boolean matchesInputs(List<ComparableStack> inputs, List<Object> recipe) {
        if (inputs.size() != recipe.size()) return false;
        List<Object> recipeCopy = new ArrayList<>(recipe);
        for (ComparableStack input : inputs) {
            boolean found = false;
            for (int i = 0; i < recipeCopy.size(); i++) {
                if (recipeCopy.get(i) instanceof ComparableStack cs && cs.matchesRecipe(input.toStack(), true)) {
                    recipeCopy.remove(i);
                    found = true;
                    break;
                }
            }
            if (!found) return false;
        }
        return recipeCopy.isEmpty();
    }

    public static Map<List<Object>, AssemblyRecipe> getRecipes() {
        return recipes;
    }

    public static class AssemblyRecipe {
        public ItemStack output;
        public int fluxCost;

        public AssemblyRecipe(ItemStack output, int fluxCost) {
            this.output = output;
            this.fluxCost = fluxCost;
        }
    }
}
