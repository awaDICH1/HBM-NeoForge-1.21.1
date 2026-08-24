package com.hbm.inventory.container;

import com.hbm.inventory.ModMenus;
import com.hbm.tileentity.machine.TileEntityRBMKConsole;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.items.SlotItemHandler;

/**
 * RBMK 控制台容器（P5.7 迁移版）。
 *
 * 槽位布局：1 槽（80, 35）+ 玩家背包（86/144 行）。
 */
public class ContainerRBMKConsole extends AbstractContainerMenu {

    private final TileEntityRBMKConsole console;

    public ContainerRBMKConsole(int id, Inventory playerInventory, TileEntityRBMKConsole console) {
        super(ModMenus.RBMK_CONSOLE.get(), id);
        this.console = console;

        this.addSlot(new SlotItemHandler(console.inventory, 0, 80, 35));

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
        return console.isUseableByPlayer(player);
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        ItemStack stack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot != null && slot.hasItem()) {
            ItemStack current = slot.getItem();
            stack = current.copy();
            if (index < 1) {
                if (!this.moveItemStackTo(current, 1, this.slots.size(), true)) return ItemStack.EMPTY;
            } else {
                if (!this.moveItemStackTo(current, 0, 1, false)) return ItemStack.EMPTY;
            }
            if (current.isEmpty()) slot.set(ItemStack.EMPTY);
            else slot.setChanged();
        }
        return stack;
    }

    public TileEntityRBMKConsole getConsole() {
        return console;
    }
}
