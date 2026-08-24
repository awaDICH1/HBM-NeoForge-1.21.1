package com.hbm.inventory.container;

import com.hbm.inventory.ModMenus;
import com.hbm.tileentity.machine.TileEntityCentrifuge;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.items.SlotItemHandler;

/**
 * 离心机容器（P5.12 迁移版）。
 *
 * 槽位布局：1 输入槽（44,17）+ 2 输出槽（116,17 / 116,53）+
 *          1 电池槽（8,53）+ 玩家背包。
 */
public class ContainerCentrifuge extends AbstractContainerMenu {

    private final TileEntityCentrifuge centrifuge;

    public ContainerCentrifuge(int id, Inventory playerInventory, TileEntityCentrifuge centrifuge) {
        super(ModMenus.CENTRIFUGE.get(), id);
        this.centrifuge = centrifuge;

        this.addSlot(new SlotItemHandler(centrifuge.inventory, 0, 44, 17));
        this.addSlot(new SlotItemHandler(centrifuge.inventory, 1, 116, 17));
        this.addSlot(new SlotItemHandler(centrifuge.inventory, 2, 116, 53));
        this.addSlot(new SlotItemHandler(centrifuge.inventory, 3, 8, 53));

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
        return centrifuge.isUseableByPlayer(player);
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        ItemStack stack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot != null && slot.hasItem()) {
            ItemStack current = slot.getItem();
            stack = current.copy();
            if (index < 4) {
                if (!this.moveItemStackTo(current, 4, this.slots.size(), true)) return ItemStack.EMPTY;
            } else {
                if (!this.moveItemStackTo(current, 0, 1, false)) return ItemStack.EMPTY;
            }
            if (current.isEmpty()) slot.set(ItemStack.EMPTY);
            else slot.setChanged();
        }
        return stack;
    }

    public TileEntityCentrifuge getCentrifuge() {
        return centrifuge;
    }
}
