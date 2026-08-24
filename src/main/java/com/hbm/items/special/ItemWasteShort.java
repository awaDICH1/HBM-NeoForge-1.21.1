package com.hbm.items.special;

import com.hbm.items.ItemBase;
import net.minecraft.world.item.Item;

/**
 * 短寿命废料（P5.2 基础版）。
 *
 * 迁移自 1.12.2 com.hbm.items.special.ItemWasteShort。
 * 1.21.1 中 getSubItems 已不再使用，创造模式物品由 ModCreativeTabs 统一管理。
 */
public class ItemWasteShort extends ItemBase {

	public ItemWasteShort(Item.Properties properties) {
		super(properties);
	}

	// WasteClass 枚举完整保留（SILEXRecipes 依赖 ordinal）
	public enum WasteClass {
		URANIUM233("Uranium-233", 50, 100),
		URANIUM235("Uranium-235", 0, 100),
		NEPTUNIUM("Neptunium-237", 150, 500),
		PLUTONIUM239("Plutonium-239", 250, 1000),
		PLUTONIUM240("Plutonium-240", 350, 1000),
		PLUTONIUM241("Plutonium-241", 500, 1000),
		AMERICIUM242("Americium-242", 750, 1000),
		SCHRABIDIUM("Schrabidium-326", 1000, 1000);

		public static final WasteClass[] VALUES = values();

		public String name;
		public int liquid;
		public int gas;

		private WasteClass(String name, int liquid, int gas){
			this.name = name;
			this.liquid = liquid;
			this.gas = gas;
		}
	}
}
