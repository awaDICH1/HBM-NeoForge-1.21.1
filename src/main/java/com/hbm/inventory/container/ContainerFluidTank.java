package com.hbm.inventory.container;

import com.hbm.inventory.ModMenus;
import com.hbm.tileentity.machine.TileEntityFluidTank;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.items.SlotItemHandler;

/**
 * 流体罐容器（P5.7 迁移版）。
 *
 * 槽位布局：2 槽（62, 17 / 62, 53）+ 玩家背包（86/144 行）。
 */
public class ContainerFluidTank extends AbstractContainerMenu {

    private final TileEntityFluidTank tank;

    public ContainerFluidTank(int id, Inventory playerInventory, TileEntityFluidTank tank) {
        super(ModMenus.FLUID_TANK.get(), id);
        this.tank = tank;

        this.addSlot(new SlotItemHandler(tank.inventory, 0, 62, 17));
        this.addSlot(new SlotItemHandler(tank.inventory, 1, 62, 53));

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                this.addSlot(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 86 + i * 18));
            }
        }
        for (int i = 0; i < 9; i++) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 144));
        }
    }

    @Override
    public boolean stillValid(Player player) {
        return tank.isUseableByPlayer(player);
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        ItemStack stack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot != null && slot.hasItem()) {
            ItemStack current = slot.getItem();
            stack = current.copy();
            if (index < 2) {
                if (!this.moveItemStackTo(current, 2, this.slots.size(), true)) return ItemStack.EMPTY;
            } else {
                if (!this.moveItemStackTo(current, 0, 2, false)) return ItemStack.EMPTY;
            }
            if (current.isEmpty()) slot.set(ItemStack.EMPTY);
            else slot.setChanged();
        }
        return stack;
    }

    public TileEntityFluidTank getTank() {
        return tank;
    }
}
