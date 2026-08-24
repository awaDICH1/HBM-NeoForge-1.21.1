package com.hbm.inventory.recipes;

import com.hbm.items.ModItems;
import com.hbm.items.machine.ItemRBMKRod;
import net.minecraft.world.item.ItemStack;
import net.minecraft.util.Mth;

import java.util.LinkedHashMap;
import java.util.Random;

public class RBMKFuelRecipes {
	public static Random rand = new Random();
	public static LinkedHashMap<ItemStack, ItemStack> recipes = new LinkedHashMap<>();
	
	public static void registerRecipes() {
		addRod(ModItems.RBMK_FUEL_UEU.get());
		addRod(ModItems.RBMK_FUEL_MEU.get());
		addRod(ModItems.RBMK_FUEL_HEU233.get());
		addRod(ModItems.RBMK_FUEL_HEU235.get());
		addRod(ModItems.RBMK_FUEL_THMEU.get());
		addRod(ModItems.RBMK_FUEL_LEP.get());
		addRod(ModItems.RBMK_FUEL_MEP.get());
		addRod(ModItems.RBMK_FUEL_HEP239.get());
		addRod(ModItems.RBMK_FUEL_HEP241.get());
		addRod(ModItems.RBMK_FUEL_LEA.get());
		addRod(ModItems.RBMK_FUEL_MEA.get());
		addRod(ModItems.RBMK_FUEL_HEA241.get());
		addRod(ModItems.RBMK_FUEL_HEA242.get());
		addRod(ModItems.RBMK_FUEL_MEN.get());
		addRod(ModItems.RBMK_FUEL_HEN.get());
		addRod(ModItems.RBMK_FUEL_MOX.get());
		addRod(ModItems.RBMK_FUEL_LES.get());
		addRod(ModItems.RBMK_FUEL_MES.get());
		addRod(ModItems.RBMK_FUEL_HES.get());
		addRod(ModItems.RBMK_FUEL_LEAUS.get());
		addRod(ModItems.RBMK_FUEL_HEAUS.get());
		addRod(ModItems.RBMK_FUEL_RA226BE.get());
		addRod(ModItems.RBMK_FUEL_PO210BE.get());
		addRod(ModItems.RBMK_FUEL_PU238BE.get());
		addRod(ModItems.RBMK_FUEL_BALEFIRE_GOLD.get());
		addRod(ModItems.RBMK_FUEL_FLASHLEAD.get());
		addRod(ModItems.RBMK_FUEL_ZFB_BISMUTH.get());
		addRod(ModItems.RBMK_FUEL_ZFB_PU241.get());
		addRod(ModItems.RBMK_FUEL_ZFB_AM_MIX.get());
		addRod(ModItems.RBMK_FUEL_BALEFIRE.get());
		addRod(ModItems.RBMK_FUEL_DRX.get());
	}

	public static void addRod(ItemRBMKRod rod){
		for(int e = 0; e<5; e++){
			addRecipe(makeRBMKRod(rod, e, false), makeRBMKPellet(makeRBMKRod(rod, e, false)));
		}
		for(int e = 0; e<5; e++){
			addRecipe(makeRBMKRod(rod, e, true), makeRBMKPellet(makeRBMKRod(rod, e, true)));
		}
	}

	public static void addRecipe(ItemStack input, ItemStack output){
		if (input.isEmpty() || output.isEmpty()) return;
		recipes.put(input, output);
	}

	public static ItemStack makeRBMKRod(ItemRBMKRod rod, int enrichment, boolean xenon){
		ItemStack fuelRod = new ItemStack(rod);
		ItemRBMKRod.setCoreHeat(fuelRod, 20D+rand.nextDouble()*29.9D);
		ItemRBMKRod.setHullHeat(fuelRod, 20D+rand.nextDouble()*29.9D);
		ItemRBMKRod.setPoison(fuelRod, xenon ? 50D+rand.nextDouble()*50D : rand.nextDouble()*49.9D);
		ItemRBMKRod.setYield(fuelRod, rod.yield * Math.min(0.99D, ((-enrichment-1)*0.2D + 1D + rand.nextDouble()*0.199D)));
		return fuelRod;
	}

	public static ItemStack makeRBMKPellet(ItemStack rod){
		ItemRBMKRod rodItem = (ItemRBMKRod) rod.getItem();
		if (rodItem.pellet == null) {
			return ItemStack.EMPTY;
		}
		ItemStack result = new ItemStack(rodItem.pellet, 8);
		int enrichment = 4 - Mth.clamp((int)Math.ceil(ItemRBMKRod.getEnrichment(rod) * 5 - 1), 0, 4);
		int meta = enrichment + (ItemRBMKRod.getPoisonLevel(rod) >= 0.5D ? 5 : 0);
		result.setDamageValue(meta);
		return result;
	}
}
