package com.hbm.inventory.container;

import com.hbm.inventory.ModMenus;
import com.hbm.tileentity.machine.TileEntityArcFurnace;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.items.SlotItemHandler;

/**
 * 电弧熔炉容器（P5.11 迁移版）。
 *
 * 槽位布局：3 输入槽（44,17 / 62,17 / 44,35）+
 *          1 电池槽（8,53）+ 1 输出槽（116,35）+ 玩家背包。
 */
public class ContainerArcFurnace extends AbstractContainerMenu {

    private final TileEntityArcFurnace furnace;

    public ContainerArcFurnace(int id, Inventory playerInventory, TileEntityArcFurnace furnace) {
        super(ModMenus.ARC_FURNACE.get(), id);
        this.furnace = furnace;

        this.addSlot(new SlotItemHandler(furnace.inventory, 0, 44, 17));
        this.addSlot(new SlotItemHandler(furnace.inventory, 1, 62, 17));
        this.addSlot(new SlotItemHandler(furnace.inventory, 2, 44, 35));
        this.addSlot(new SlotItemHandler(furnace.inventory, 3, 8, 53));
        this.addSlot(new SlotItemHandler(furnace.inventory, 4, 116, 35));

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
        return furnace.isUseableByPlayer(player);
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        ItemStack stack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot != null && slot.hasItem()) {
            ItemStack current = slot.getItem();
            stack = current.copy();
            if (index < 5) {
                if (!this.moveItemStackTo(current, 5, this.slots.size(), true)) return ItemStack.EMPTY;
            } else {
                if (!this.moveItemStackTo(current, 0, 4, false)) return ItemStack.EMPTY;
            }
            if (current.isEmpty()) slot.set(ItemStack.EMPTY);
            else slot.setChanged();
        }
        return stack;
    }

    public TileEntityArcFurnace getFurnace() {
        return furnace;
    }
}
