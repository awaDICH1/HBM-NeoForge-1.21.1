package com.hbm.items.special;

import com.hbm.items.ItemBase;
import net.minecraft.world.item.Item;

/**
 * 长寿命废料（P5.2 基础版）。
 *
 * 迁移自 1.12.2 com.hbm.items.special.ItemWasteLong。
 * 1.21.1 中 getSubItems 已不再使用，创造模式物品由 ModCreativeTabs 统一管理。
 */
public class ItemWasteLong extends ItemBase {

	public ItemWasteLong(Item.Properties properties) {
		super(properties);
	}

	// WasteClass 枚举完整保留（SILEXRecipes 依赖 ordinal）
	public enum WasteClass {
		THORIUM("Thorium-232", 0, 0),
		URANIUM233("Uranium-233", 0, 50),
		URANIUM235("Uranium-235", 0, 0),
		NEPTUNIUM("Neptunium-237", 0, 100),
		SCHRABIDIUM("Schrabidium-326", 0, 250);

		public static final WasteClass[] VALUES = values();
		
		public String name;
		public int liquid;
		public int gas;
		
		private WasteClass(String name, int liquid, int gas) {
			this.name = name;
			this.liquid = liquid;
			this.gas = gas;
		}
	}
}
