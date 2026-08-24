package com.hbm.items.machine;

import com.hbm.items.ItemBase;
import net.minecraft.world.item.ItemStack;

/**
 * RBMK 燃料棒（P5.2 骨架版）。
 *
 * 迁移自 1.12.2 com.hbm.items.machine.ItemRBMKRod（597 行）。
 * 当前仅含配方类（RBMKFuelRecipes）所需的字段与静态方法桩；
 * 完整 RBMK 燃料系统（NBT 存取/燃烧/毒化/中子）待 P5.2 机器批。
 */
public class ItemRBMKRod extends ItemBase {

    public ItemRBMKPellet pellet;
    public String fullName = "";
    public double reactivity;
    public double selfRate;
    public double xGen = 0.5D;
    public double xBurn = 50D;
    public double heat = 1D;
    public double yield;
    public double meltingPoint = 1000D;
    public double diffusion = 0.02D;
    public int colorTint = 0x304825;

    public ItemRBMKRod(Properties properties) {
        super(properties);
    }

    // TODO P5.2: 完整 NBT 存取（1.21 用 DataComponents.CUSTOM_DATA）与燃烧逻辑
    public static void setCoreHeat(ItemStack stack, double heat) { }
    public static double getCoreHeat(ItemStack stack) { return 0; }
    public static void setHullHeat(ItemStack stack, double heat) { }
    public static double getHullHeat(ItemStack stack) { return 0; }
    public static void setPoison(ItemStack stack, double xenon) { }
    public static double getPoison(ItemStack stack) { return 0; }
    public static void setYield(ItemStack stack, double yield) { }
    public static double getYield(ItemStack stack) { return 0; }
    public static double getEnrichment(ItemStack stack) { return 0; }
    public static double getPoisonLevel(ItemStack stack) { return 0; }
}
