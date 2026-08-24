package com.hbm.items.machine;

import com.hbm.inventory.fluid.FluidStack;
import com.hbm.inventory.fluid.FluidType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

/**
 * 流体图标物品（P5.2 骨架版）。
 *
 * 迁移自 1.12.2 com.hbm.items.machine.ItemFluidIcon（127 行）。
 * 当前仅含配方类（GasCentrifugeRecipes）所需的 make 静态方法桩；
 * 完整流体图标系统（流体桶/图标渲染）待 P5.2。
 */
public class ItemFluidIcon extends Item {

    public ItemFluidIcon(Properties properties) {
        super(properties);
    }

    // TODO P5.2: 完整 make 实现（流体图标 ItemStack）
    public static ItemStack make(FluidStack stack) {
        return ItemStack.EMPTY;
    }

    public static ItemStack make(FluidType fluid, int i) {
        return ItemStack.EMPTY;
    }

    public static ItemStack make(FluidType fluid, int i, int pressure) {
        return ItemStack.EMPTY;
    }
}
