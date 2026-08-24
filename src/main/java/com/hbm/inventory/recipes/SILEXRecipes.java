package com.hbm.inventory.recipes;

import com.hbm.inventory.OreDictManager;

import com.hbm.blocks.ModBlocks;
import com.hbm.util.ComparableStack;
import com.hbm.inventory.fluid.Fluids;
import com.hbm.items.ItemEnums;
import com.hbm.items.ModItems;
import com.hbm.items.machine.ItemFELCrystal.EnumWavelengths;
import com.hbm.items.special.ItemWasteLong;
import com.hbm.items.special.ItemWasteShort;
import com.hbm.util.WeightedRandomObject;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.item.ItemStack;

import java.util.*;
import java.util.Map.Entry;

import static com.hbm.inventory.OreDictManager.*;

public class SILEXRecipes {

	public static LinkedHashMap<Object, SILEXRecipe> recipes = new LinkedHashMap<>();
	public static HashMap<ComparableStack, ComparableStack> itemTranslation = new HashMap<>();
	public static HashMap<String, String> dictTranslation = new HashMap<>();

	public static void register() {

		itemTranslation.put(new ComparableStack(ModItems.FLUID_ICON.get(), 1, Fluids.UF6.getID()), new ComparableStack(ModItems.INGOT_URANIUM.get()));
		dictTranslation.put(U.dust(), U.ingot());
		recipes.put(U.ingot(), new SILEXRecipe(900, 100, EnumWavelengths.VISIBLE)
				.addOut(new ItemStack(ModItems.NUGGET_U235.get()), 1)
				.addOut(new ItemStack(ModItems.NUGGET_U238.get()), 11)
		);

		recipes.put(new ComparableStack(ModItems.INGOT_PU_MIX.get()), new SILEXRecipe(900, 100, 2)
				.addOut(new ItemStack(ModItems.NUGGET_PU239.get()), 6)
				.addOut(new ItemStack(ModItems.NUGGET_PU240.get()), 3)
		);

		recipes.put(new ComparableStack(ModItems.INGOT_AM_MIX.get()), new SILEXRecipe(900, 100, 2)
				.addOut(new ItemStack(ModItems.NUGGET_AM241.get()), 3)
				.addOut(new ItemStack(ModItems.NUGGET_AM242.get()), 6)
		);

		itemTranslation.put(new ComparableStack(ModItems.FLUID_ICON.get(), 1, Fluids.PUF6.getID()), new ComparableStack(ModItems.INGOT_PLUTONIUM.get()));
		dictTranslation.put(PU.dust(), PU.ingot());
		recipes.put(PU.ingot(), new SILEXRecipe(900, 100, 2)
				.addOut(new ItemStack(ModItems.NUGGET_PU238.get()), 3)
				.addOut(new ItemStack(ModItems.NUGGET_PU239.get()), 4)
				.addOut(new ItemStack(ModItems.NUGGET_PU240.get()), 2)
		);

		recipes.put(new ComparableStack(ModItems.INGOT_SCHRARANIUM.get()), new SILEXRecipe(900, 100, 2)
				.addOut(new ItemStack(ModItems.NUGGET_SCHRABIDIUM.get()), 4)
				.addOut(new ItemStack(ModItems.NUGGET_URANIUM.get()), 3)
				.addOut(new ItemStack(ModItems.NUGGET_NEPTUNIUM.get()), 2)
		);

		itemTranslation.put(new ComparableStack(ModItems.POWDER_AUSTRALIUM.get()), new ComparableStack(ModItems.INGOT_AUSTRALIUM.get()));
		recipes.put(new ComparableStack(ModItems.INGOT_AUSTRALIUM.get()), new SILEXRecipe(900, 100, 2)
				.addOut(new ItemStack(ModItems.NUGGET_AUSTRALIUM_LESSER.get()), 5)
				.addOut(new ItemStack(ModItems.NUGGET_AUSTRALIUM_GREATER.get()), 1)
		);

		recipes.put(new ComparableStack(ModItems.CRYSTAL_SCHRARANIUM.get()), new SILEXRecipe(900, 100, 3)
				.addOut(new ItemStack(ModItems.NUGGET_SCHRABIDIUM.get()), 5)
				.addOut(new ItemStack(ModItems.NUGGET_URANIUM.get()), 2)
				.addOut(new ItemStack(ModItems.NUGGET_NEPTUNIUM.get()), 2)
		);

		recipes.put(new ComparableStack(ModBlocks.ORE_TIKITE.get()), new SILEXRecipe(900, 100, EnumWavelengths.UV)
				.addOut(new ItemStack(ModItems.POWDER_PLUTONIUM.get()), 2)
				.addOut(new ItemStack(ModItems.POWDER_COBALT.get()), 3)
				.addOut(new ItemStack(ModItems.POWDER_NIOBIUM.get()), 3)
				.addOut(new ItemStack(ModItems.POWDER_NITAN_MIX.get()), 2)
		);

		recipes.put(new ComparableStack(ModItems.CRYSTAL_TRIXITE.get()), new SILEXRecipe(1200, 100, EnumWavelengths.UV)
				.addOut(new ItemStack(ModItems.POWDER_PLUTONIUM.get()), 2)
				.addOut(new ItemStack(ModItems.POWDER_COBALT.get()), 3)
				.addOut(new ItemStack(ModItems.POWDER_NIOBIUM.get()), 3)
				.addOut(new ItemStack(ModItems.POWDER_NITAN_MIX.get()), 1)
				.addOut(new ItemStack(ModItems.POWDER_SPARK_MIX.get()), 1)
		);

		itemTranslation.put(new ComparableStack(ModItems.POWDER_LAPIS.get()), new ComparableStack(Items.BLUE_DYE));
		recipes.put(new ComparableStack(Items.BLUE_DYE), new SILEXRecipe(100, 100 ,1)
				.addOut(new ItemStack(ModItems.SULFUR.get()), 4)
				.addOut(new ItemStack(ModItems.POWDER_ALUMINIUM.get()), 3)
				.addOut(new ItemStack(ModItems.POWDER_COBALT.get()), 3)
		);

		recipes.put(new ComparableStack(ModItems.FLUID_ICON.get(), 1, Fluids.DEATH.getID()), new SILEXRecipe(1000, 1000, 4)
				.addOut(new ItemStack(ModItems.POWDER_IMPURE_OSMIRIDIUM.get()), 1)
		);

		recipes.put(new ComparableStack(ModItems.FLUID_ICON.get(), 1, Fluids.VITRIOL.getID()), new SILEXRecipe(1000, 300, EnumWavelengths.IR)
				.addOut(new ItemStack(ModItems.POWDER_BROMINE.get()), 5)
				.addOut(new ItemStack(ModItems.POWDER_IODINE.get()), 5)
				.addOut(new ItemStack(ModItems.POWDER_IRON.get()), 5)
				.addOut(new ItemStack(ModItems.SULFUR.get()), 15)
		);

		recipes.put(new ComparableStack(ModItems.FLUID_ICON.get(), 1, Fluids.REDMUD.getID()), new SILEXRecipe(300, 50, EnumWavelengths.VISIBLE)
				.addOut(new ItemStack(ModItems.POWDER_ALUMINIUM.get()), 10)
				.addOut(new ItemStack(ModItems.POWDER_NEODYMIUM_TINY.get(), 3), 5)
				.addOut(new ItemStack(ModItems.POWDER_BORON_TINY.get(), 3), 5)
				.addOut(new ItemStack(ModItems.NUGGET_ZIRCONIUM.get()), 5)
				.addOut(new ItemStack(ModItems.POWDER_IRON.get()), 20)
				.addOut(new ItemStack(ModItems.POWDER_TITANIUM.get()), 15)
				.addOut(new ItemStack(ModItems.POWDER_SODIUM.get()), 10)
		);

		for(int i = 0; i < 5; i++) {

			// UEU //
			recipes.put(new ComparableStack(ModItems.RBMK_PELLET_UEU.get(), 1, i), new SILEXRecipe(600, 100, 1) 	//NU and MEU will breed more plutonium due to their higher concentrations of U-238
					.addOut(new ItemStack(ModItems.NUGGET_URANIUM.get()), 86 - i * 11)	//NU is unenriched to the point where it'll always be lower burnup; so more Pu239 for longer
					.addOut(i < 2 ? new ItemStack(ModItems.NUGGET_PU239.get()) : new ItemStack(ModItems.NUGGET_PU_MIX.get()), 10 + i * 3)
					.addOut(stack(ModItems.NUCLEAR_WASTE_LONG_TINY.get(), 1, ItemWasteLong.WasteClass.URANIUM235.ordinal()), 2 + 3 * i)
					.addOut(stack(ModItems.NUCLEAR_WASTE_SHORT_TINY.get(), 1, ItemWasteShort.WasteClass.URANIUM235.ordinal()), 2 + 5 * i) );

			recipes.put(new ComparableStack(ModItems.RBMK_PELLET_UEU.get(), 1, i + 5), new SILEXRecipe(600, 100, 1)
					.addOut(new ItemStack(ModItems.POWDER_XE135_TINY.get()), 1)
					.addOut(new ItemStack(ModItems.NUGGET_URANIUM.get()), 86 - i * 11)
					.addOut(i < 2 ? new ItemStack(ModItems.NUGGET_PU239.get()) : new ItemStack(ModItems.NUGGET_PU_MIX.get()), 10 + i * 3)
					.addOut(stack(ModItems.NUCLEAR_WASTE_LONG_TINY.get(), 1, ItemWasteLong.WasteClass.URANIUM235.ordinal()), 2 + 3 * i)
					.addOut(stack(ModItems.NUCLEAR_WASTE_SHORT_TINY.get(), 1, ItemWasteShort.WasteClass.URANIUM235.ordinal()), 1 + 5 * i) );

			// MEU //
			recipes.put(new ComparableStack(ModItems.RBMK_PELLET_MEU.get(), 1, i), new SILEXRecipe(600, 100, 1)
					.addOut(new ItemStack(ModItems.NUGGET_URANIUM_FUEL.get()), 84 - i * 16)
					.addOut(i < 1 ? new ItemStack(ModItems.NUGGET_PU239.get()) : new ItemStack(ModItems.NUGGET_PU_MIX.get()), 6 + i * 4)
					.addOut(stack(ModItems.NUCLEAR_WASTE_LONG_TINY.get(), 1, ItemWasteLong.WasteClass.URANIUM235.ordinal()), 4 + 5 * i)
					.addOut(stack(ModItems.NUCLEAR_WASTE_SHORT_TINY.get(), 1, ItemWasteShort.WasteClass.URANIUM235.ordinal()), 6 + 7 * i) );

			recipes.put(new ComparableStack(ModItems.RBMK_PELLET_MEU.get(), 1, i + 5), new SILEXRecipe(600, 100, 1)
					.addOut(new ItemStack(ModItems.POWDER_XE135_TINY.get()), 1)
					.addOut(new ItemStack(ModItems.NUGGET_URANIUM_FUEL.get()), 83 - i * 16)
					.addOut(i < 1 ? new ItemStack(ModItems.NUGGET_PU239.get()) : new ItemStack(ModItems.NUGGET_PU_MIX.get()), 6 + i * 4)
					.addOut(stack(ModItems.NUCLEAR_WASTE_LONG_TINY.get(), 1, ItemWasteLong.WasteClass.URANIUM235.ordinal()), 4 + 5 * i)
					.addOut(stack(ModItems.NUCLEAR_WASTE_SHORT_TINY.get(), 1, ItemWasteShort.WasteClass.URANIUM235.ordinal()), 6 + 7 * i) );

			// HEU233 //
			recipes.put(new ComparableStack(ModItems.RBMK_PELLET_HEU233.get(), 1, i), new SILEXRecipe(600, 100, 1)
					.addOut(new ItemStack(ModItems.NUGGET_U233.get()), 90 - i * 20)
					.addOut(stack(ModItems.NUCLEAR_WASTE_LONG_TINY.get(), 1, ItemWasteLong.WasteClass.URANIUM233.ordinal()), 4 + 8 * i)
					.addOut(stack(ModItems.NUCLEAR_WASTE_SHORT_TINY.get(), 1, ItemWasteShort.WasteClass.URANIUM233.ordinal()), 6 + 12 * i) );

			recipes.put(new ComparableStack(ModItems.RBMK_PELLET_HEU233.get(), 1, i + 5), new SILEXRecipe(600, 100, 1)
					.addOut(new ItemStack(ModItems.POWDER_XE135_TINY.get()), 1)
					.addOut(new ItemStack(ModItems.NUGGET_U233.get()), 89 - i * 20)
					.addOut(stack(ModItems.NUCLEAR_WASTE_LONG_TINY.get(), 1, ItemWasteLong.WasteClass.URANIUM233.ordinal()), 4 + 8 * i)
					.addOut(stack(ModItems.NUCLEAR_WASTE_SHORT_TINY.get(), 1, ItemWasteShort.WasteClass.URANIUM233.ordinal()), 6 + 12 * i) );

			// HEU235 //
			recipes.put(new ComparableStack(ModItems.RBMK_PELLET_HEU235.get(), 1, i), new SILEXRecipe(600, 100, 1)
					.addOut(new ItemStack(ModItems.NUGGET_U235.get()), 90 - i * 20)
					.addOut(stack(ModItems.NUCLEAR_WASTE_LONG_TINY.get(), 1, ItemWasteLong.WasteClass.URANIUM235.ordinal()), 4 + 8 * i)
					.addOut(stack(ModItems.NUCLEAR_WASTE_SHORT_TINY.get(), 1, ItemWasteShort.WasteClass.URANIUM235.ordinal()), 6 + 12 * i) );

			recipes.put(new ComparableStack(ModItems.RBMK_PELLET_HEU235.get(), 1, i + 5), new SILEXRecipe(600, 100, 1)
					.addOut(new ItemStack(ModItems.POWDER_XE135_TINY.get()), 1)
					.addOut(new ItemStack(ModItems.NUGGET_U235.get()), 89 - i * 20)
					.addOut(stack(ModItems.NUCLEAR_WASTE_LONG_TINY.get(), 1, ItemWasteLong.WasteClass.URANIUM235.ordinal()), 4 + 8 * i)
					.addOut(stack(ModItems.NUCLEAR_WASTE_SHORT_TINY.get(), 1, ItemWasteShort.WasteClass.URANIUM235.ordinal()), 6 + 12 * i) );

			// UZH //
			recipes.put(new ComparableStack(ModItems.RBMK_PELLET_UZH.get(), 1, i), new SILEXRecipe(600, 100, 1)
					.addOut(new ItemStack(ModItems.NUGGET_ZIRCONIUM.get()), 75)
					.addOut(new ItemStack(ModItems.NUGGET_URANIUM_FUEL.get()), 20 - i * 4)
					.addOut(new ItemStack(ModItems.NUGGET_PU_MIX.get()), 3 + i * 3)
					.addOut(stack(ModItems.NUCLEAR_WASTE_LONG_TINY.get(), 1, ItemWasteLong.WasteClass.URANIUM235.ordinal()), 1 + i * 1)
					.addOut(stack(ModItems.NUCLEAR_WASTE_SHORT_TINY.get(), 1, ItemWasteShort.WasteClass.URANIUM235.ordinal()), 1 + i * 1) );

			recipes.put(new ComparableStack(ModItems.RBMK_PELLET_UZH.get(), 1, i + 5), new SILEXRecipe(600, 100, 1)
					.addOut(new ItemStack(ModItems.POWDER_XE135_TINY.get()), 1)
					.addOut(new ItemStack(ModItems.NUGGET_ZIRCONIUM.get()), 75)
					.addOut(new ItemStack(ModItems.NUGGET_URANIUM_FUEL.get()), 19 - i * 4)
					.addOut(new ItemStack(ModItems.NUGGET_PU_MIX.get()), 3 + i * 3)
					.addOut(stack(ModItems.NUCLEAR_WASTE_LONG_TINY.get(), 1, ItemWasteLong.WasteClass.URANIUM235.ordinal()), 1 + i * 1)
					.addOut(stack(ModItems.NUCLEAR_WASTE_SHORT_TINY.get(), 1, ItemWasteShort.WasteClass.URANIUM235.ordinal()), 1 + i * 1) );

			// TH232 //
			recipes.put(new ComparableStack(ModItems.RBMK_PELLET_THMEU.get(), 1, i), new SILEXRecipe(600, 100, 1)
					.addOut(new ItemStack(ModItems.NUGGET_THORIUM_FUEL.get()), 84 - i * 20)
					.addOut(new ItemStack(ModItems.NUGGET_U233.get()), 6 + i * 4)
					.addOut(stack(ModItems.NUCLEAR_WASTE_LONG_TINY.get(), 1, ItemWasteLong.WasteClass.THORIUM.ordinal()), 10 + 16 * i) );

			recipes.put(new ComparableStack(ModItems.RBMK_PELLET_THMEU.get(), 1, i + 5), new SILEXRecipe(600, 100, 1)
					.addOut(new ItemStack(ModItems.POWDER_XE135_TINY.get()), 1)
					.addOut(new ItemStack(ModItems.NUGGET_THORIUM_FUEL.get()), 83 - i * 20)
					.addOut(new ItemStack(ModItems.NUGGET_U233.get()), 6 + i * 4)
					.addOut(stack(ModItems.NUCLEAR_WASTE_LONG_TINY.get(), 1, ItemWasteLong.WasteClass.THORIUM.ordinal()), 10 + 16 * i) );

			// LEP //
			recipes.put(new ComparableStack(ModItems.RBMK_PELLET_LEP.get(), 1, i), new SILEXRecipe(600, 100, 1)
					.addOut(new ItemStack(ModItems.NUGGET_PLUTONIUM_FUEL.get()), 84 - i * 14)
					.addOut(i < 1 ? new ItemStack(ModItems.NUGGET_PU239.get()) : new ItemStack(ModItems.NUGGET_PU_MIX.get()), 6 + i * 2)
					.addOut(stack(ModItems.NUCLEAR_WASTE_SHORT_TINY.get(), 1, ItemWasteShort.WasteClass.PLUTONIUM239.ordinal()), 7 + 8 * i)
					.addOut(stack(ModItems.NUCLEAR_WASTE_SHORT_TINY.get(), 1, ItemWasteShort.WasteClass.PLUTONIUM240.ordinal()), 3 + 4 * i) );

			recipes.put(new ComparableStack(ModItems.RBMK_PELLET_LEP.get(), 1, i + 5), new SILEXRecipe(600, 100, 1)
					.addOut(new ItemStack(ModItems.POWDER_XE135_TINY.get()), 1)
					.addOut(new ItemStack(ModItems.NUGGET_PLUTONIUM_FUEL.get()), 83 - i * 14)
					.addOut(i < 1 ? new ItemStack(ModItems.NUGGET_PU239.get()) : new ItemStack(ModItems.NUGGET_PU_MIX.get()), 6 + i * 2)
					.addOut(stack(ModItems.NUCLEAR_WASTE_SHORT_TINY.get(), 1, ItemWasteShort.WasteClass.PLUTONIUM239.ordinal()), 7 + 8 * i)
					.addOut(stack(ModItems.NUCLEAR_WASTE_SHORT_TINY.get(), 1, ItemWasteShort.WasteClass.PLUTONIUM240.ordinal()), 3 + 4 * i) );

			// MEP //
			recipes.put(new ComparableStack(ModItems.RBMK_PELLET_MEP.get(), 1, i), new SILEXRecipe(600, 100, 1)
					.addOut(new ItemStack(ModItems.NUGGET_PU_MIX.get()), 85 - i * 20)
					.addOut(stack(ModItems.NUCLEAR_WASTE_SHORT_TINY.get(), 1, ItemWasteShort.WasteClass.PLUTONIUM239.ordinal()), 10 + 10 * i)
					.addOut(stack(ModItems.NUCLEAR_WASTE_SHORT_TINY.get(), 1, ItemWasteShort.WasteClass.PLUTONIUM240.ordinal()), 5 + 5 * i) );

			recipes.put(new ComparableStack(ModItems.RBMK_PELLET_MEP.get(), 1, i + 5), new SILEXRecipe(600, 100, 1)
					.addOut(new ItemStack(ModItems.POWDER_XE135_TINY.get()), 1)
					.addOut(new ItemStack(ModItems.NUGGET_PU_MIX.get()), 84 - i * 20)
					.addOut(stack(ModItems.NUCLEAR_WASTE_SHORT_TINY.get(), 1, ItemWasteShort.WasteClass.PLUTONIUM239.ordinal()), 10 + 10 * i)
					.addOut(stack(ModItems.NUCLEAR_WASTE_SHORT_TINY.get(), 1, ItemWasteShort.WasteClass.PLUTONIUM240.ordinal()), 5 + 5 * i) );

			// HEP239 //
			recipes.put(new ComparableStack(ModItems.RBMK_PELLET_HEP239.get(), 1, i), new SILEXRecipe(600, 100, 1)
					.addOut(new ItemStack(ModItems.NUGGET_PU239.get()), 85 - i * 20)
					.addOut(stack(ModItems.NUCLEAR_WASTE_SHORT_TINY.get(), 1, ItemWasteShort.WasteClass.PLUTONIUM239.ordinal()), 15 + 20 * i) );

			recipes.put(new ComparableStack(ModItems.RBMK_PELLET_HEP239.get(), 1, i + 5), new SILEXRecipe(600, 100, 1)
					.addOut(new ItemStack(ModItems.POWDER_XE135_TINY.get()), 1)
					.addOut(new ItemStack(ModItems.NUGGET_PU239.get()), 84 - i * 20)
					.addOut(stack(ModItems.NUCLEAR_WASTE_SHORT_TINY.get(), 1, ItemWasteShort.WasteClass.PLUTONIUM239.ordinal()), 15 + 20 * i) );

			// HEP241 //
			recipes.put(new ComparableStack(ModItems.RBMK_PELLET_HEP241.get(), 1, i), new SILEXRecipe(600, 100, 2)
					.addOut(new ItemStack(ModItems.NUGGET_PU241.get()), 85 - i * 20)
					.addOut(stack(ModItems.NUCLEAR_WASTE_SHORT_TINY.get(), 1, ItemWasteShort.WasteClass.PLUTONIUM241.ordinal()), 15 + 20 * i) );

			recipes.put(new ComparableStack(ModItems.RBMK_PELLET_HEP241.get(), 1, i + 5), new SILEXRecipe(600, 100, 2)
					.addOut(new ItemStack(ModItems.POWDER_XE135_TINY.get()), 1)
					.addOut(new ItemStack(ModItems.NUGGET_PU241.get()), 84 - i * 20)
					.addOut(stack(ModItems.NUCLEAR_WASTE_SHORT_TINY.get(), 1, ItemWasteShort.WasteClass.PLUTONIUM241.ordinal()), 15 + 20 * i) );

			// MEN //
			recipes.put(new ComparableStack(ModItems.RBMK_PELLET_MEN.get(), 1, i), new SILEXRecipe(600, 100, 1)
					.addOut(new ItemStack(ModItems.NUGGET_NEPTUNIUM_FUEL.get()), 84 - i * 14)
					.addOut(i < 1 ? new ItemStack(ModItems.NUGGET_PU239.get()) : new ItemStack(ModItems.NUGGET_PU_MIX.get()), 6 + i * 2)
					.addOut(stack(ModItems.NUCLEAR_WASTE_LONG_TINY.get(), 1, ItemWasteLong.WasteClass.NEPTUNIUM.ordinal()), 4 + 5 * i)
					.addOut(stack(ModItems.NUCLEAR_WASTE_SHORT_TINY.get(), 1, ItemWasteShort.WasteClass.NEPTUNIUM.ordinal()), 6 + 7 * i) );

			recipes.put(new ComparableStack(ModItems.RBMK_PELLET_MEN.get(), 1, i + 5), new SILEXRecipe(600, 100, 1)
					.addOut(new ItemStack(ModItems.POWDER_XE135_TINY.get()), 1)
					.addOut(new ItemStack(ModItems.NUGGET_NEPTUNIUM_FUEL.get()), 83 - i * 14)
					.addOut(i < 1 ? new ItemStack(ModItems.NUGGET_PU239.get()) : new ItemStack(ModItems.NUGGET_PU_MIX.get()), 6 + i * 2)
					.addOut(stack(ModItems.NUCLEAR_WASTE_LONG_TINY.get(), 1, ItemWasteLong.WasteClass.NEPTUNIUM.ordinal()), 4 + 5 * i)
					.addOut(stack(ModItems.NUCLEAR_WASTE_SHORT_TINY.get(), 1, ItemWasteShort.WasteClass.NEPTUNIUM.ordinal()), 6 + 7 * i) );

			// HEN //
			recipes.put(new ComparableStack(ModItems.RBMK_PELLET_HEN.get(), 1, i), new SILEXRecipe(600, 100, 1)
					.addOut(new ItemStack(ModItems.NUGGET_NEPTUNIUM.get()), 90 - i * 20)
					.addOut(stack(ModItems.NUCLEAR_WASTE_LONG_TINY.get(), 1, ItemWasteLong.WasteClass.NEPTUNIUM.ordinal()), 4 + 8 * i)
					.addOut(stack(ModItems.NUCLEAR_WASTE_SHORT_TINY.get(), 1, ItemWasteShort.WasteClass.NEPTUNIUM.ordinal()), 6 + 12 * i) );

			recipes.put(new ComparableStack(ModItems.RBMK_PELLET_HEN.get(), 1, i + 5), new SILEXRecipe(600, 100, 1)
					.addOut(new ItemStack(ModItems.POWDER_XE135_TINY.get()), 1)
					.addOut(new ItemStack(ModItems.NUGGET_NEPTUNIUM.get()), 89 - i * 20)
					.addOut(stack(ModItems.NUCLEAR_WASTE_LONG_TINY.get(), 1, ItemWasteLong.WasteClass.NEPTUNIUM.ordinal()), 4 + 8 * i)
					.addOut(stack(ModItems.NUCLEAR_WASTE_SHORT_TINY.get(), 1, ItemWasteShort.WasteClass.NEPTUNIUM.ordinal()), 6 + 12 * i) );

			// MOX //
			recipes.put(new ComparableStack(ModItems.RBMK_PELLET_MOX.get(), 1, i), new SILEXRecipe(600, 100, 1)
					.addOut(new ItemStack(ModItems.NUGGET_MOX_FUEL.get()), 84 - i * 20)
					.addOut(new ItemStack(ModItems.NUGGET_PU_MIX.get()), 6 + i * 4)
					.addOut(stack(ModItems.NUCLEAR_WASTE_LONG_TINY.get(), 1, ItemWasteLong.WasteClass.URANIUM235.ordinal()), 2 + 3 * i)
					.addOut(stack(ModItems.NUCLEAR_WASTE_SHORT_TINY.get(), 1, ItemWasteShort.WasteClass.URANIUM235.ordinal()), 3 + 5 * i)
					.addOut(stack(ModItems.NUCLEAR_WASTE_SHORT_TINY.get(), 1, ItemWasteShort.WasteClass.PLUTONIUM239.ordinal()), 5 + 8 * i) );

			recipes.put(new ComparableStack(ModItems.RBMK_PELLET_MOX.get(), 1, i + 5), new SILEXRecipe(600, 100, 1)
					.addOut(new ItemStack(ModItems.POWDER_XE135_TINY.get()), 1)
					.addOut(new ItemStack(ModItems.NUGGET_MOX_FUEL.get()), 83 - i * 20)
					.addOut(new ItemStack(ModItems.NUGGET_PU_MIX.get()), 6 + i * 4)
					.addOut(stack(ModItems.NUCLEAR_WASTE_LONG_TINY.get(), 1, ItemWasteLong.WasteClass.URANIUM235.ordinal()), 2 + 3 * i)
					.addOut(stack(ModItems.NUCLEAR_WASTE_SHORT_TINY.get(), 1, ItemWasteShort.WasteClass.URANIUM235.ordinal()), 3 + 5 * i)
					.addOut(stack(ModItems.NUCLEAR_WASTE_SHORT_TINY.get(), 1, ItemWasteShort.WasteClass.PLUTONIUM239.ordinal()), 5 + 8 * i) );

			// LEAUS //
			recipes.put(new ComparableStack(ModItems.RBMK_PELLET_LEAUS.get(), 1, i), new SILEXRecipe(600, 100, 2)
					.addOut(new ItemStack(ModItems.NUGGET_AUSTRALIUM_LESSER.get()), 90 - i * 20)
					.addOut(new ItemStack(ModItems.NUGGET_LEAD.get()), 6 + 12 * i)
					.addOut(new ItemStack(ModItems.NUGGET_PB209.get()), 4 + 8 * i) );

			recipes.put(new ComparableStack(ModItems.RBMK_PELLET_LEAUS.get(), 1, i + 5), new SILEXRecipe(600, 100, 2)
					.addOut(new ItemStack(ModItems.POWDER_XE135_TINY.get()), 1)
					.addOut(new ItemStack(ModItems.NUGGET_AUSTRALIUM_LESSER.get()), 89 - i * 20)
					.addOut(new ItemStack(ModItems.NUGGET_LEAD.get()), 6 + 12 * i)
					.addOut(new ItemStack(ModItems.NUGGET_PB209.get()), 4 + 8 * i) );

			// HEAUS //
			recipes.put(new ComparableStack(ModItems.RBMK_PELLET_HEAUS.get(), 1, i), new SILEXRecipe(600, 100, 2)
					.addOut(new ItemStack(ModItems.NUGGET_AUSTRALIUM_GREATER.get()), 90 - i * 20)
					.addOut(new ItemStack(ModItems.NUGGET_AU198.get()), 5 + 10 * i)
					.addOut(new ItemStack(Items.GOLD_NUGGET), 3 + 6 * i)
					.addOut(new ItemStack(ModItems.NUGGET_PB209.get()), 2 + 4 * i) );

			recipes.put(new ComparableStack(ModItems.RBMK_PELLET_HEAUS.get(), 1, i + 5), new SILEXRecipe(600, 100, 2)
					.addOut(new ItemStack(ModItems.POWDER_XE135_TINY.get()), 1)
					.addOut(new ItemStack(ModItems.NUGGET_AUSTRALIUM_GREATER.get()), 89 - i * 20)
					.addOut(new ItemStack(ModItems.NUGGET_AU198.get()), 5 + 10 * i)
					.addOut(new ItemStack(Items.GOLD_NUGGET), 3 + 6 * i)
					.addOut(new ItemStack(ModItems.NUGGET_PB209.get()), 2 + 4 * i) );

			// LES //
			recipes.put(new ComparableStack(ModItems.RBMK_PELLET_LES.get(), 1, i), new SILEXRecipe(600, 100, 2)
					.addOut(new ItemStack(ModItems.NUGGET_LES.get()), 90 - i * 20)
					.addOut(stack(ModItems.NUCLEAR_WASTE_LONG_TINY.get(), 1, ItemWasteLong.WasteClass.NEPTUNIUM.ordinal()), 2 + 3 * i)
					.addOut(stack(ModItems.NUCLEAR_WASTE_SHORT_TINY.get(), 1, ItemWasteShort.WasteClass.NEPTUNIUM.ordinal()), 2 + 5 * i)
					.addOut(stack(ModItems.NUCLEAR_WASTE_LONG_TINY.get(), 1, ItemWasteLong.WasteClass.SCHRABIDIUM.ordinal()), 1 + 2 * i)
					.addOut(stack(ModItems.NUCLEAR_WASTE_SHORT_TINY.get(), 1, ItemWasteShort.WasteClass.SCHRABIDIUM.ordinal()), 1 + 2 * i)
					.addOut(new ItemStack(ModItems.POWDER_COAL_TINY.get()), 4 + 8 * i) );

			//TODO: Readd xenon processing if/when the NEI handler can display more than 6 outputs properly
			recipes.put(new ComparableStack(ModItems.RBMK_PELLET_LES.get(), 1, i + 5), new SILEXRecipe(600, 100, 2)	//I'd rather not fuck up the NEI handler, so six items it is
					.addOut(new ItemStack(ModItems.NUGGET_LES.get()), 90 - i * 20)			//Just bullshit something about "not enough np237 for extractable amounts of xe135"
					.addOut(stack(ModItems.NUCLEAR_WASTE_LONG_TINY.get(), 1, ItemWasteLong.WasteClass.NEPTUNIUM.ordinal()), 2 + 3 * i)
					.addOut(stack(ModItems.NUCLEAR_WASTE_SHORT_TINY.get(), 1, ItemWasteShort.WasteClass.NEPTUNIUM.ordinal()), 2 + 5 * i)
					.addOut(stack(ModItems.NUCLEAR_WASTE_LONG_TINY.get(), 1, ItemWasteLong.WasteClass.SCHRABIDIUM.ordinal()), 1 + 2 * i)
					.addOut(stack(ModItems.NUCLEAR_WASTE_SHORT_TINY.get(), 1, ItemWasteShort.WasteClass.SCHRABIDIUM.ordinal()), 1 + 2 * i)
					.addOut(new ItemStack(ModItems.POWDER_COAL_TINY.get()), 4 + 8 * i) );

			// MES //
			recipes.put(new ComparableStack(ModItems.RBMK_PELLET_MES.get(), 1, i), new SILEXRecipe(600, 100, 2)
					.addOut(new ItemStack(ModItems.NUGGET_SCHRABIDIUM_FUEL.get()), 90 - i * 20)
					.addOut(stack(ModItems.NUCLEAR_WASTE_LONG_TINY.get(), 1, ItemWasteLong.WasteClass.NEPTUNIUM.ordinal()), 1 + 3 * i)
					.addOut(stack(ModItems.NUCLEAR_WASTE_SHORT_TINY.get(), 1, ItemWasteShort.WasteClass.NEPTUNIUM.ordinal()), 2 + 4 * i)
					.addOut(stack(ModItems.NUCLEAR_WASTE_LONG_TINY.get(), 1, ItemWasteLong.WasteClass.SCHRABIDIUM.ordinal()), 1 + 3 * i)
					.addOut(stack(ModItems.NUCLEAR_WASTE_SHORT_TINY.get(), 1, ItemWasteShort.WasteClass.SCHRABIDIUM.ordinal()), 2 + 4 * i)
					.addOut(new ItemStack(ModItems.POWDER_COAL_TINY.get()), 4 + 6 * i) );

			//TODO: Readd xenon processing if/when the NEI handler can display more than 6 outputs properly
			recipes.put(new ComparableStack(ModItems.RBMK_PELLET_MES.get(), 1, i + 5), new SILEXRecipe(600, 100, 2)
					.addOut(new ItemStack(ModItems.NUGGET_SCHRABIDIUM_FUEL.get()), 90 - i * 20) //ditto
					.addOut(stack(ModItems.NUCLEAR_WASTE_LONG_TINY.get(), 1, ItemWasteLong.WasteClass.NEPTUNIUM.ordinal()), 1 + 3 * i)
					.addOut(stack(ModItems.NUCLEAR_WASTE_SHORT_TINY.get(), 1, ItemWasteShort.WasteClass.NEPTUNIUM.ordinal()), 2 + 4 * i)
					.addOut(stack(ModItems.NUCLEAR_WASTE_LONG_TINY.get(), 1, ItemWasteLong.WasteClass.SCHRABIDIUM.ordinal()), 1 + 3 * i)
					.addOut(stack(ModItems.NUCLEAR_WASTE_SHORT_TINY.get(), 1, ItemWasteShort.WasteClass.SCHRABIDIUM.ordinal()), 2 + 4 * i)
					.addOut(new ItemStack(ModItems.POWDER_COAL_TINY.get()), 4 + 6 * i) );

			// HES //
			recipes.put(new ComparableStack(ModItems.RBMK_PELLET_HES.get(), 1, i), new SILEXRecipe(600, 100, 2)
					.addOut(new ItemStack(ModItems.NUGGET_HES.get()), 90 - i * 20)
					.addOut(stack(ModItems.NUCLEAR_WASTE_LONG_TINY.get(), 1, ItemWasteLong.WasteClass.NEPTUNIUM.ordinal()), 1 + 2 * i)
					.addOut(stack(ModItems.NUCLEAR_WASTE_SHORT_TINY.get(), 1, ItemWasteShort.WasteClass.NEPTUNIUM.ordinal()), 1 + 3 * i)
					.addOut(stack(ModItems.NUCLEAR_WASTE_LONG_TINY.get(), 1, ItemWasteLong.WasteClass.SCHRABIDIUM.ordinal()), 2 + 5 * i)
					.addOut(stack(ModItems.NUCLEAR_WASTE_SHORT_TINY.get(), 1, ItemWasteShort.WasteClass.SCHRABIDIUM.ordinal()), 4 + 6 * i)
					.addOut(new ItemStack(ModItems.POWDER_COAL_TINY.get()), 2 + 4 * i) );

			//TODO: Readd xenon processing if/when the NEI handler can display more than 6 outputs properly
			recipes.put(new ComparableStack(ModItems.RBMK_PELLET_HES.get(), 1, i + 5), new SILEXRecipe(600, 100, 2)
					.addOut(new ItemStack(ModItems.NUGGET_HES.get()), 90 - i * 20) //ditto
					.addOut(stack(ModItems.NUCLEAR_WASTE_LONG_TINY.get(), 1, ItemWasteLong.WasteClass.NEPTUNIUM.ordinal()), 1 + 2 * i)
					.addOut(stack(ModItems.NUCLEAR_WASTE_SHORT_TINY.get(), 1, ItemWasteShort.WasteClass.NEPTUNIUM.ordinal()), 1 + 3 * i)
					.addOut(stack(ModItems.NUCLEAR_WASTE_LONG_TINY.get(), 1, ItemWasteLong.WasteClass.SCHRABIDIUM.ordinal()), 2 + 5 * i)
					.addOut(stack(ModItems.NUCLEAR_WASTE_SHORT_TINY.get(), 1, ItemWasteShort.WasteClass.SCHRABIDIUM.ordinal()), 4 + 6 * i)
					.addOut(new ItemStack(ModItems.POWDER_COAL_TINY.get()), 2 + 4 * i) );

			// BALEFIRE //
			recipes.put(new ComparableStack(ModItems.RBMK_PELLET_BALEFIRE.get(), 1, i), new SILEXRecipe(400, 100, 3)
					.addOut(new ItemStack(ModItems.POWDER_BALEFIRE.get()), 90 - i * 20)
					.addOut(new ItemStack(ModItems.NUCLEAR_WASTE_TINY.get()), 10 + 20 * i) );

			// FLASHGOLD //
			recipes.put(new ComparableStack(ModItems.RBMK_PELLET_BALEFIRE_GOLD.get(), 1, i), new SILEXRecipe(600, 100, 2)
					.addOut(new ItemStack(ModItems.NUGGET_AU198.get()), 90 - 20 * i)
					.addOut(new ItemStack(ModItems.POWDER_BALEFIRE.get()), 10 + 20 * i) );

			// FLASHLEAD //
			recipes.put(new ComparableStack(ModItems.RBMK_PELLET_FLASHLEAD.get(), 1, i), new SILEXRecipe(600, 100, 2)
					.addOut(new ItemStack(ModItems.NUGGET_AU198.get()), 44 - 10 * i)
					.addOut(new ItemStack(ModItems.NUGGET_PB209.get()), 44 - 10 * i)
					.addOut(new ItemStack(ModItems.NUGGET_BISMUTH.get()), 1 + 6 * i)
					.addOut(new ItemStack(ModItems.NUGGET_MERCURY.get()), 1 + 6 * i)
					.addOut(new ItemStack(ModItems.NUGGET_GH336.get()), 10 + 8 * i) ); //Reimumunch

			// POBE //
			recipes.put(new ComparableStack(ModItems.RBMK_PELLET_PO210BE.get(), 1, i), new SILEXRecipe(600, 100, 1)
					.addOut(new ItemStack(ModItems.NUGGET_POLONIUM.get()), 45 - 10 * i)
					.addOut(new ItemStack(ModItems.NUGGET_BERYLLIUM.get()), 45 - 10 * i)
					.addOut(new ItemStack(ModItems.NUGGET_LEAD.get()), 5 + 10 * i)
					.addOut(new ItemStack(ModItems.POWDER_COAL_TINY.get()), 5 + 10 * i) );

			// PUBE //
			recipes.put(new ComparableStack(ModItems.RBMK_PELLET_PU238BE.get(), 1, i), new SILEXRecipe(600, 100, 1)
					.addOut(new ItemStack(ModItems.NUGGET_PU238.get()), 45 - 10 * i)
					.addOut(new ItemStack(ModItems.NUGGET_BERYLLIUM.get()), 45 - 10 * i)
					.addOut(new ItemStack(ModItems.NUGGET_LEAD.get()), 3 + 5 * i)
					.addOut(new ItemStack(ModItems.NUCLEAR_WASTE_TINY.get()), 2 + 5 * i)
					.addOut(new ItemStack(ModItems.POWDER_COAL_TINY.get()), 5 + 10 * i) );

			recipes.put(new ComparableStack(ModItems.RBMK_PELLET_PU238BE.get(), 1, i + 5), new SILEXRecipe(600, 100, 1)
					.addOut(new ItemStack(ModItems.POWDER_XE135_TINY.get()), 1)
					.addOut(new ItemStack(ModItems.NUGGET_PU238.get()), 44 - 10 * i)
					.addOut(new ItemStack(ModItems.NUGGET_BERYLLIUM.get()), 45 - 10 * i)
					.addOut(new ItemStack(ModItems.NUGGET_LEAD.get()), 3 + 5 * i)
					.addOut(new ItemStack(ModItems.NUCLEAR_WASTE_TINY.get()), 2 + 5 * i)
					.addOut(new ItemStack(ModItems.POWDER_COAL_TINY.get()), 5 + 10 * i) );

			// RABE //
			recipes.put(new ComparableStack(ModItems.RBMK_PELLET_RA226BE.get(), 1, i), new SILEXRecipe(600, 100, 1)
					.addOut(new ItemStack(ModItems.NUGGET_RA226.get()), 45 - 10 * i)
					.addOut(new ItemStack(ModItems.NUGGET_BERYLLIUM.get()), 45 - 10 * i)
					.addOut(new ItemStack(ModItems.NUGGET_LEAD.get()), 3 + 5 * i)
					.addOut(new ItemStack(ModItems.NUGGET_POLONIUM.get()), 2 + 5 * i)
					.addOut(new ItemStack(ModItems.POWDER_COAL_TINY.get()), 5 + 10 * i) );

			// DRX //
			recipes.put(new ComparableStack(ModItems.RBMK_PELLET_DRX.get(), 1, i), new SILEXRecipe(600, 100, 4)
					.addOut(new ItemStack(ModItems.NOTHING.get()), 1)
					.addOut(new ItemStack(ModItems.NOTHING.get()), 1)
					.addOut(new ItemStack(ModItems.NOTHING.get()), 1)
					.addOut(new ItemStack(ModItems.NOTHING.get()), 1)
					.addOut(new ItemStack(ModItems.NOTHING.get()), 1)
					.addOut(new ItemStack(ModItems.NOTHING.get()), 1) );

			recipes.put(new ComparableStack(ModItems.RBMK_PELLET_DRX.get(), 1, i + 5), new SILEXRecipe(600, 100, 4)
					.addOut(new ItemStack(ModItems.NOTHING.get()), 1)
					.addOut(new ItemStack(ModItems.NOTHING.get()), 1)
					.addOut(new ItemStack(ModItems.NOTHING.get()), 1)
					.addOut(new ItemStack(ModItems.NOTHING.get()), 1)
					.addOut(new ItemStack(ModItems.NOTHING.get()), 1)
					.addOut(new ItemStack(ModItems.NOTHING.get()), 1) );

			// ZFB BI //
			recipes.put(new ComparableStack(ModItems.RBMK_PELLET_ZFB_BISMUTH.get(), 1, i), new SILEXRecipe(600, 100, 2)
					.addOut(new ItemStack(ModItems.NUGGET_URANIUM.get()), 50 - i * 10)
					.addOut(new ItemStack(ModItems.NUGGET_PU241.get()), 50 - i * 10)
					.addOut(new ItemStack(ModItems.NUGGET_BISMUTH.get()), 50 + i * 20)
					.addOut(new ItemStack(ModItems.NUGGET_ZIRCONIUM.get()), 150) );

			recipes.put(new ComparableStack(ModItems.RBMK_PELLET_ZFB_BISMUTH.get(), 1, i + 5), new SILEXRecipe(600, 100, 2)
					.addOut(new ItemStack(ModItems.POWDER_XE135_TINY.get()), 3)
					.addOut(new ItemStack(ModItems.NUGGET_URANIUM.get()), 50 - i * 10)
					.addOut(new ItemStack(ModItems.NUGGET_PU241.get()), 50 - i * 10)
					.addOut(new ItemStack(ModItems.NUGGET_BISMUTH.get()), 50 + i * 20)
					.addOut(new ItemStack(ModItems.NUGGET_ZIRCONIUM.get()), 147) );

			// ZFB PU-241 //
			recipes.put(new ComparableStack(ModItems.RBMK_PELLET_ZFB_PU241.get(), 1, i), new SILEXRecipe(600, 100, 2)
					.addOut(new ItemStack(ModItems.NUGGET_U235.get()), 50 - i * 10)
					.addOut(new ItemStack(ModItems.NUGGET_PU240.get()), 50 - i * 10)
					.addOut(new ItemStack(ModItems.NUGGET_PU241.get()), 50 + i * 20)
					.addOut(new ItemStack(ModItems.NUGGET_ZIRCONIUM.get()), 150) );

			recipes.put(new ComparableStack(ModItems.RBMK_PELLET_ZFB_PU241.get(), 1, i + 5), new SILEXRecipe(600, 100, 2)
					.addOut(new ItemStack(ModItems.POWDER_XE135_TINY.get()), 3)
					.addOut(new ItemStack(ModItems.NUGGET_U235.get()), 50 - i * 10)
					.addOut(new ItemStack(ModItems.NUGGET_PU240.get()), 50 - i * 10)
					.addOut(new ItemStack(ModItems.NUGGET_PU241.get()), 50 + i * 20)
					.addOut(new ItemStack(ModItems.NUGGET_ZIRCONIUM.get()), 147) );

			// ZFB RG-AM //
			recipes.put(new ComparableStack(ModItems.RBMK_PELLET_ZFB_AM_MIX.get(), 1, i), new SILEXRecipe(600, 100, 2)
					.addOut(new ItemStack(ModItems.NUGGET_PU241.get()), 100 - i * 20)
					.addOut(new ItemStack(ModItems.NUGGET_AM_MIX.get()), 50 + i * 20)
					.addOut(new ItemStack(ModItems.NUGGET_ZIRCONIUM.get()), 150) );

			recipes.put(new ComparableStack(ModItems.RBMK_PELLET_ZFB_AM_MIX.get(), 1, i + 5), new SILEXRecipe(600, 100, 2)
					.addOut(new ItemStack(ModItems.POWDER_XE135_TINY.get()), 3)
					.addOut(new ItemStack(ModItems.NUGGET_PU241.get()), 100 - i * 20)
					.addOut(new ItemStack(ModItems.NUGGET_AM_MIX.get()), 50 + i * 20)
					.addOut(new ItemStack(ModItems.NUGGET_ZIRCONIUM.get()), 147) );
		}

		recipes.put(new ComparableStack(ModItems.NUCLEAR_WASTE_LONG.get(), 1, ItemWasteLong.WasteClass.URANIUM235.ordinal()), new SILEXRecipe(900, 100, 1)
				.addOut(new ItemStack(ModItems.NUGGET_NEPTUNIUM.get()), 20)
				.addOut(new ItemStack(ModItems.NUGGET_PU239.get()), 45)
				.addOut(new ItemStack(ModItems.NUGGET_PU240.get()), 20)
				.addOut(new ItemStack(ModItems.NUGGET_TECHNETIUM.get()), 15)
		);
		recipes.put(new ComparableStack(ModItems.NUCLEAR_WASTE_LONG_DEPLETED.get(), 1, ItemWasteLong.WasteClass.URANIUM235.ordinal()), new SILEXRecipe(900, 100, 1)
				.addOut(new ItemStack(ModItems.NUGGET_LEAD.get()), 65)
				.addOut(new ItemStack(ModItems.NUGGET_BISMUTH.get()), 20)
				.addOut(new ItemStack(ModItems.DUST_TINY.get()), 15)
		);
		recipes.put(new ComparableStack(ModItems.NUCLEAR_WASTE_SHORT.get(), 1, ItemWasteShort.WasteClass.URANIUM235.ordinal()), new SILEXRecipe(900, 100, 1)
				.addOut(new ItemStack(ModItems.NUGGET_PU238.get()), 12)
				.addOut(new ItemStack(ModItems.POWDER_SR90_TINY.get()), 10)
				.addOut(new ItemStack(ModItems.POWDER_I131_TINY.get()), 10)
				.addOut(new ItemStack(ModItems.POWDER_CS137_TINY.get()), 12)
				.addOut(new ItemStack(ModItems.NUCLEAR_WASTE_TINY.get()), 56)
		);
		recipes.put(new ComparableStack(ModItems.NUCLEAR_WASTE_SHORT_DEPLETED.get(), 1, ItemWasteShort.WasteClass.URANIUM235.ordinal()), new SILEXRecipe(900, 100, 1)
				.addOut(new ItemStack(ModItems.NUGGET_ZIRCONIUM.get()), 10)
				.addOut(new ItemStack(ModItems.DUST_TINY.get()), 32)
				.addOut(new ItemStack(ModItems.NUGGET_LEAD.get()), 22)
				.addOut(new ItemStack(ModItems.NUGGET_U238.get()), 5)
				.addOut(new ItemStack(ModItems.NUGGET_BISMUTH.get()), 15)
				.addOut(new ItemStack(ModItems.NUCLEAR_WASTE_TINY.get()), 16)
		);

		recipes.put(new ComparableStack(ModItems.NUCLEAR_WASTE_LONG.get(), 1, ItemWasteLong.WasteClass.URANIUM233.ordinal()), new SILEXRecipe(900, 100, 1)
				.addOut(new ItemStack(ModItems.NUGGET_U235.get()), 15)
				.addOut(new ItemStack(ModItems.NUGGET_NEPTUNIUM.get()), 25)
				.addOut(new ItemStack(ModItems.NUGGET_PU239.get()), 45)
				.addOut(new ItemStack(ModItems.NUGGET_TECHNETIUM.get()), 15)
		);
		recipes.put(new ComparableStack(ModItems.NUCLEAR_WASTE_LONG_DEPLETED.get(), 1, ItemWasteLong.WasteClass.URANIUM233.ordinal()), new SILEXRecipe(900, 100, 1)
				.addOut(new ItemStack(ModItems.NUGGET_LEAD.get()), 60)
				.addOut(new ItemStack(ModItems.NUGGET_BISMUTH.get()), 25)
				.addOut(new ItemStack(ModItems.DUST_TINY.get()), 15)
		);
		recipes.put(new ComparableStack(ModItems.NUCLEAR_WASTE_SHORT.get(), 1, ItemWasteShort.WasteClass.URANIUM233.ordinal()), new SILEXRecipe(900, 100, 1)
				.addOut(new ItemStack(ModItems.NUGGET_PU238.get()), 4)
				.addOut(new ItemStack(ModItems.POWDER_SR90_TINY.get()), 12)
				.addOut(new ItemStack(ModItems.POWDER_I131_TINY.get()), 10)
				.addOut(new ItemStack(ModItems.POWDER_CS137_TINY.get()), 14)
				.addOut(new ItemStack(ModItems.NUCLEAR_WASTE_TINY.get()), 60)
		);
		recipes.put(new ComparableStack(ModItems.NUCLEAR_WASTE_SHORT_DEPLETED.get(), 1, ItemWasteShort.WasteClass.URANIUM233.ordinal()), new SILEXRecipe(900, 100, 1)
				.addOut(new ItemStack(ModItems.NUGGET_ZIRCONIUM.get()), 12)
				.addOut(new ItemStack(ModItems.DUST_TINY.get()), 34)
				.addOut(new ItemStack(ModItems.NUGGET_LEAD.get()), 13)
				.addOut(new ItemStack(ModItems.NUGGET_U238.get()), 2)
				.addOut(new ItemStack(ModItems.NUGGET_BISMUTH.get()), 10)
				.addOut(new ItemStack(ModItems.NUCLEAR_WASTE_TINY.get()), 29)
		);

		recipes.put(new ComparableStack(ModItems.NUCLEAR_WASTE_SHORT.get(), 1, ItemWasteShort.WasteClass.PLUTONIUM239.ordinal()), new SILEXRecipe(900, 100, 1)
				.addOut(new ItemStack(ModItems.NUGGET_PU240.get()), 10)
				.addOut(new ItemStack(ModItems.NUGGET_PU241.get()), 25)
				.addOut(new ItemStack(ModItems.POWDER_SR90_TINY.get()), 2)
				.addOut(new ItemStack(ModItems.POWDER_I131_TINY.get()), 5)
				.addOut(new ItemStack(ModItems.POWDER_CS137_TINY.get()), 6)
				.addOut(new ItemStack(ModItems.NUCLEAR_WASTE_TINY.get()), 52)
		);
		recipes.put(new ComparableStack(ModItems.NUCLEAR_WASTE_SHORT_DEPLETED.get(), 1, ItemWasteShort.WasteClass.PLUTONIUM239.ordinal()), new SILEXRecipe(900, 100, 1)
				.addOut(new ItemStack(ModItems.NUGGET_ZIRCONIUM.get()), 2)
				.addOut(new ItemStack(ModItems.DUST_TINY.get()), 16)
				.addOut(new ItemStack(ModItems.NUGGET_LEAD.get()), 40)
				.addOut(new ItemStack(ModItems.NUGGET_U238.get()), 3)
				.addOut(new ItemStack(ModItems.NUCLEAR_WASTE_TINY.get()), 39)
		);

		recipes.put(new ComparableStack(ModItems.NUCLEAR_WASTE_SHORT.get(), 1, ItemWasteShort.WasteClass.PLUTONIUM240.ordinal()), new SILEXRecipe(900, 100, 1)
				.addOut(new ItemStack(ModItems.NUGGET_PU241.get()), 15)
				.addOut(new ItemStack(ModItems.NUGGET_NEPTUNIUM.get()), 5)
				.addOut(new ItemStack(ModItems.POWDER_SR90_TINY.get()), 2)
				.addOut(new ItemStack(ModItems.POWDER_I131_TINY.get()), 5)
				.addOut(new ItemStack(ModItems.POWDER_CS137_TINY.get()), 7)
				.addOut(new ItemStack(ModItems.NUCLEAR_WASTE_TINY.get()), 66)
		);
		recipes.put(new ComparableStack(ModItems.NUCLEAR_WASTE_SHORT_DEPLETED.get(), 1, ItemWasteShort.WasteClass.PLUTONIUM240.ordinal()), new SILEXRecipe(900, 100, 1)
				.addOut(new ItemStack(ModItems.NUGGET_ZIRCONIUM.get()), 2)
				.addOut(new ItemStack(ModItems.DUST_TINY.get()), 22)
				.addOut(new ItemStack(ModItems.NUGGET_BISMUTH.get()), 20)
				.addOut(new ItemStack(ModItems.NUGGET_LEAD.get()), 17)
				.addOut(new ItemStack(ModItems.NUGGET_U238.get()), 3)
				.addOut(new ItemStack(ModItems.NUCLEAR_WASTE_TINY.get()), 36)
		);

		recipes.put(new ComparableStack(ModItems.NUCLEAR_WASTE_SHORT.get(), 1, ItemWasteShort.WasteClass.PLUTONIUM241.ordinal()), new SILEXRecipe(900, 100, 2)
				.addOut(new ItemStack(ModItems.NUGGET_AM241.get()), 25)
				.addOut(new ItemStack(ModItems.NUGGET_AM242.get()), 35)
				.addOut(new ItemStack(ModItems.NUGGET_TECHNETIUM.get()), 5)
				.addOut(new ItemStack(ModItems.POWDER_I131_TINY.get()), 3)
				.addOut(new ItemStack(ModItems.POWDER_CS137_TINY.get()), 7)
				.addOut(new ItemStack(ModItems.NUCLEAR_WASTE_TINY.get()), 25)
		);
		recipes.put(new ComparableStack(ModItems.NUCLEAR_WASTE_SHORT_DEPLETED.get(), 1, ItemWasteShort.WasteClass.PLUTONIUM241.ordinal()), new SILEXRecipe(900, 100, 2)
				.addOut(new ItemStack(ModItems.NUGGET_BISMUTH.get()), 60)
				.addOut(new ItemStack(ModItems.DUST_TINY.get()), 20)
				.addOut(new ItemStack(ModItems.NUGGET_LEAD.get()), 15)
				.addOut(new ItemStack(ModItems.NUCLEAR_WASTE_TINY.get()), 5)
		);

		recipes.put(new ComparableStack(ModItems.NUCLEAR_WASTE_LONG.get(), 1, ItemWasteLong.WasteClass.THORIUM.ordinal()), new SILEXRecipe(900, 100, 1)
				.addOut(new ItemStack(ModItems.NUGGET_U233.get()), 40)
				.addOut(new ItemStack(ModItems.NUGGET_U235.get()), 35)
				.addOut(new ItemStack(ModItems.NUCLEAR_WASTE_TINY.get()), 25)
		);
		recipes.put(new ComparableStack(ModItems.NUCLEAR_WASTE_LONG_DEPLETED.get(), 1, ItemWasteLong.WasteClass.THORIUM.ordinal()), new SILEXRecipe(900, 100, 1)
				.addOut(new ItemStack(ModItems.NUGGET_LEAD.get()), 35)
				.addOut(new ItemStack(ModItems.NUGGET_BISMUTH.get()), 40)
				.addOut(new ItemStack(ModItems.DUST_TINY.get()), 15)
				.addOut(new ItemStack(ModItems.NUCLEAR_WASTE_TINY.get()), 10)
		);

		recipes.put(new ComparableStack(ModItems.NUCLEAR_WASTE_LONG.get(), 1, ItemWasteLong.WasteClass.NEPTUNIUM.ordinal()), new SILEXRecipe(900, 100, 1)
				.addOut(new ItemStack(ModItems.NUGGET_U238.get()), 15)
				.addOut(new ItemStack(ModItems.NUGGET_PU239.get()), 40)
				.addOut(new ItemStack(ModItems.NUGGET_PU240.get()), 15)
				.addOut(new ItemStack(ModItems.NUGGET_TECHNETIUM.get()), 15)
				.addOut(new ItemStack(ModItems.NUCLEAR_WASTE_TINY.get()), 15)
		);
		recipes.put(new ComparableStack(ModItems.NUCLEAR_WASTE_LONG_DEPLETED.get(), 1, ItemWasteLong.WasteClass.NEPTUNIUM.ordinal()), new SILEXRecipe(900, 100, 1)
				.addOut(new ItemStack(ModItems.NUGGET_U238.get()), 16)
				.addOut(new ItemStack(ModItems.NUGGET_LEAD.get()), 55)
				.addOut(new ItemStack(ModItems.DUST_TINY.get()), 20)
				.addOut(new ItemStack(ModItems.NUCLEAR_WASTE_TINY.get()), 9)
		);
		recipes.put(new ComparableStack(ModItems.NUCLEAR_WASTE_SHORT.get(), 1, ItemWasteShort.WasteClass.NEPTUNIUM.ordinal()), new SILEXRecipe(900, 100, 1)
				.addOut(new ItemStack(ModItems.NUGGET_PU238.get()), 40)
				.addOut(new ItemStack(ModItems.POWDER_SR90_TINY.get()), 7)
				.addOut(new ItemStack(ModItems.POWDER_I131_TINY.get()), 5)
				.addOut(new ItemStack(ModItems.POWDER_CS137_TINY.get()), 8)
				.addOut(new ItemStack(ModItems.NUCLEAR_WASTE_TINY.get()), 40)
		);
		recipes.put(new ComparableStack(ModItems.NUCLEAR_WASTE_SHORT_DEPLETED.get(), 1, ItemWasteShort.WasteClass.NEPTUNIUM.ordinal()), new SILEXRecipe(900, 100, 1)
				.addOut(new ItemStack(ModItems.NUGGET_ZIRCONIUM.get()), 7)
				.addOut(new ItemStack(ModItems.DUST_TINY.get()), 29)
				.addOut(new ItemStack(ModItems.NUGGET_U238.get()), 2)
				.addOut(new ItemStack(ModItems.NUGGET_LEAD.get()), 45)
				.addOut(new ItemStack(ModItems.NUCLEAR_WASTE_TINY.get()), 17)
		);

		recipes.put(new ComparableStack(ModItems.NUCLEAR_WASTE_LONG.get(), 1, ItemWasteLong.WasteClass.SCHRABIDIUM.ordinal()), new SILEXRecipe(900, 100, 1)
				.addOut(new ItemStack(ModItems.NUGGET_SOLINIUM.get()), 25)
				.addOut(new ItemStack(ModItems.NUGGET_EUPHEMIUM.get()), 18)
				.addOut(new ItemStack(ModItems.NUGGET_GH336.get()), 16)
				.addOut(new ItemStack(ModItems.NUGGET_TANTALIUM.get()), 8)
				.addOut(new ItemStack(ModItems.POWDER_NEODYMIUM_TINY.get()), 8)
				.addOut(new ItemStack(ModItems.NUCLEAR_WASTE_TINY.get()), 25)
		);
		recipes.put(new ComparableStack(ModItems.NUCLEAR_WASTE_LONG_DEPLETED.get(), 1, ItemWasteLong.WasteClass.SCHRABIDIUM.ordinal()), new SILEXRecipe(900, 100, 1)
				.addOut(new ItemStack(ModItems.NUGGET_SOLINIUM.get()), 20)
				.addOut(new ItemStack(ModItems.NUGGET_EUPHEMIUM.get()), 18)
				.addOut(new ItemStack(ModItems.NUGGET_GH336.get()), 15)
				.addOut(new ItemStack(ModItems.NUGGET_TANTALIUM.get()), 8)
				.addOut(new ItemStack(ModItems.POWDER_NEODYMIUM_TINY.get()), 8)
				.addOut(new ItemStack(ModItems.NUCLEAR_WASTE_TINY.get()), 31)
		);
		recipes.put(new ComparableStack(ModItems.NUCLEAR_WASTE_SHORT.get(), 1, ItemWasteShort.WasteClass.SCHRABIDIUM.ordinal()), new SILEXRecipe(900, 100, 1)
				.addOut(new ItemStack(ModItems.NUGGET_PB209.get()), 7) //We don't have any spicy lanthanides, and lead 209 + gold 198 is already *severely* pushing it, but there's no
				.addOut(new ItemStack(ModItems.NUGGET_AU198.get()), 7) //point in contributing to pointless item bloat, so this will have to do
				.addOut(new ItemStack(ModItems.POWDER_CS137_TINY.get()), 5)
				.addOut(new ItemStack(ModItems.POWDER_I131_TINY.get()), 5)
				.addOut(new ItemStack(ModItems.NUCLEAR_WASTE_TINY.get()), 76)
		);
		recipes.put(new ComparableStack(ModItems.NUCLEAR_WASTE_SHORT_DEPLETED.get(), 1, ItemWasteShort.WasteClass.SCHRABIDIUM.ordinal()), new SILEXRecipe(900, 100, 1)
				.addOut(new ItemStack(ModItems.NUGGET_BISMUTH.get()), 7)
				.addOut(new ItemStack(ModItems.NUGGET_MERCURY.get()), 12)
				.addOut(new ItemStack(ModItems.POWDER_CERIUM_TINY.get()), 14)
				.addOut(new ItemStack(ModItems.POWDER_LANTHANIUM_TINY.get()), 15)
				.addOut(new ItemStack(ModItems.DUST_TINY.get()), 20)
				.addOut(new ItemStack(ModItems.NUCLEAR_WASTE_TINY.get()), 32)
		);

		recipes.put(new ComparableStack(ModItems.FALLOUT.get(), 1), new SILEXRecipe(900, 100, 2)
				.addOut(new ItemStack(ModItems.DUST_TINY.get()), 90)
				.addOut(new ItemStack(ModItems.NUGGET_CO60.get()), 2)
				.addOut(new ItemStack(ModItems.POWDER_SR90_TINY.get()), 3)
				.addOut(new ItemStack(ModItems.POWDER_I131_TINY.get()), 1)
				.addOut(new ItemStack(ModItems.POWDER_CS137_TINY.get()), 3)
				.addOut(new ItemStack(ModItems.NUGGET_AU198.get()), 1)
		);

		recipes.put(new ComparableStack(Blocks.GRAVEL, 1), new SILEXRecipe(1000, 250, EnumWavelengths.VISIBLE)
				.addOut(new ItemStack(Items.FLINT), 80)
				.addOut(new ItemStack(ModItems.POWDER_BORON.get()), 5)
				.addOut(new ItemStack(ModItems.POWDER_LITHIUM.get()), 10)
				.addOut(new ItemStack(ModItems.FLUORITE.get()), 5)
		);

		recipes.put(new ComparableStack(ModItems.FLUID_ICON.get(), 1, Fluids.FULLERENE.getID()),
				new SILEXRecipe(1_000, 1_000, EnumWavelengths.VISIBLE).addOut(DictFrame.fromOne(ModItems.POWDER_ASH.get(), ItemEnums.EnumAshType.FULLERENE), 1));
	}

	private static final HashMap<Item, Item> tinyWasteTranslation = new HashMap<>();

	static {
		tinyWasteTranslation.put(ModItems.NUCLEAR_WASTE_SHORT_TINY.get(), ModItems.NUCLEAR_WASTE_SHORT.get());
		tinyWasteTranslation.put(ModItems.NUCLEAR_WASTE_LONG_TINY.get(), ModItems.NUCLEAR_WASTE_LONG.get());
		tinyWasteTranslation.put(ModItems.NUCLEAR_WASTE_SHORT_DEPLETED_TINY.get(), ModItems.NUCLEAR_WASTE_SHORT_DEPLETED.get());
		tinyWasteTranslation.put(ModItems.NUCLEAR_WASTE_LONG_DEPLETED_TINY.get(), ModItems.NUCLEAR_WASTE_LONG_DEPLETED.get());
	}

	public static void addRecipe(int wavelength, int solution, int consumption, ItemStack input, ItemStack[] outputItems, int[] outputWeights){
		SILEXRecipe newRecipe = new SILEXRecipe(solution, consumption, EnumWavelengths.values()[wavelength]);
		for(int i = 0; i < outputItems.length; i++){
			newRecipe = newRecipe.addOut(new WeightedRandomObject(outputItems[i], outputWeights[i]));
		}
		recipes.put(new ComparableStack(input), newRecipe);
	}

	public static void removeRecipe(ItemStack input){
		recipes.remove(new ComparableStack(input));
	}
	
	public static SILEXRecipe getOutput(ItemStack stack) {
		
		if(stack == null || stack.isEmpty())
			return null;
		
		ComparableStack comp = translateItem(stack);
		
		if(recipes.containsKey(comp))
			return recipes.get(comp);
		
		String[] dictKeys = comp.getDictKeys();
		
		for(String key : dictKeys) {
			
			String translation = translateDict(key);
			if(recipes.containsKey(translation))
				return recipes.get(translation);
		}
		if(tinyWasteTranslation.containsKey(comp.item)) {
			SILEXRecipe result = getOutput(stack(tinyWasteTranslation.get(comp.item), comp.stacksize, comp.meta));

			if(result != null) {
				// This way it rounds down if somehow the recipe's fluid produced is not divisible by 900
				int fluidProduced = (result.fluidProduced / 900) * 100;
				SILEXRecipe tinyVersion = new SILEXRecipe(fluidProduced, result.fluidConsumed, result.laserStrength);
				// Shared ownership shouldn't be an issue since the resulting recipe isn't modified by the caller
				tinyVersion.outputs = result.outputs;

				// TODO: Cache? Might break saving recipes, IDK
				// recipes.put(comp, tinyVersion);

				return tinyVersion;
			}
		}
		
		return null;
	}
	
	public static ComparableStack translateItem(ItemStack stack) {
		ComparableStack orig = new ComparableStack(stack.getItem(), 1, stack.getDamageValue());
		ComparableStack translation = itemTranslation.get(orig);
		
		if(translation != null)
			return translation;
		
		return orig;
	}
	
	public static String translateDict(String key) {
		
		String translation = dictTranslation.get(key);
		
		if(translation != null)
			return translation;
		
		return key;
	}
	
	public static List<Object> getAllIngredients() {
		List<Object> ing = new ArrayList<>();
		
		for(Entry<Object, SILEXRecipe> entry : SILEXRecipes.recipes.entrySet()) {
			ing.add(entry.getKey());
		}
		for(Entry<ComparableStack, ComparableStack> entry : SILEXRecipes.itemTranslation.entrySet()) {
			ing.add(entry.getKey());
		}
		for(Entry<String, String> entry : SILEXRecipes.dictTranslation.entrySet()) {
			ing.add(entry.getKey());
		}
		
		return ing;
	}

	public static Map<List<ItemStack>, SILEXRecipe> getRecipes() {
		
		Map<List<ItemStack>, SILEXRecipe> recipes = new LinkedHashMap<>();
		List<Object> ing = getAllIngredients();
		
		for(Object ingredient : ing) {
			
			if(ingredient instanceof String) {
				List<ItemStack> ingredients = OreDictManager.getOres((String)ingredient);
				if(!ingredients.isEmpty()) {
					SILEXRecipe output = getOutput(ingredients.getFirst());
					if(output != null)
						recipes.put(ingredients, output);
				}
				
			} else if(ingredient instanceof ComparableStack) {
				SILEXRecipe output = getOutput(((ComparableStack) ingredient).toStack());
				List<ItemStack> ingredients = new ArrayList<>(1);
				if(output != null){
					ingredients.add(((ComparableStack)ingredient).toStack());
					recipes.put(ingredients, output);
				}
			}
		}
		
		return recipes;
	}
	
	public static class SILEXRecipe {

		public int fluidProduced;
		public int fluidConsumed;
		public EnumWavelengths laserStrength;
		public List<WeightedRandomObject> outputs = new ArrayList<>();

		public SILEXRecipe(int fluidProduced, int fluidConsumed, EnumWavelengths laserStrength) {
			this.fluidProduced = fluidProduced;
			this.fluidConsumed = fluidConsumed;
			this.laserStrength = laserStrength;
		}

		public SILEXRecipe(int fluidProduced, int fluidConsumed, int laserStrength) {
			this(fluidProduced, fluidConsumed, EnumWavelengths.values()[laserStrength]);
		}

		public SILEXRecipe addOut(ItemStack stack, int weight) {
			return addOut(new WeightedRandomObject(stack, weight));
		}
		
		public SILEXRecipe addOut(WeightedRandomObject entry) {
			outputs.add(entry);
			return this;
		}

        public SILEXRecipe addOutAll(List<WeightedRandomObject> outputs) {
            outputs.forEach(this::addOut);
            return this;
        }
    }


    /** 1.12 new ItemStack(item, count, meta) 的 1.21 等价（meta → 损坏值） */
    private static ItemStack stack(ItemLike item, int count, int meta) {
        ItemStack s = new ItemStack(item, count);
        s.setDamageValue(meta);
        return s;
    }
}

