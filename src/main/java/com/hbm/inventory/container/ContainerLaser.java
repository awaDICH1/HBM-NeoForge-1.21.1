package com.hbm.inventory.container;

import com.hbm.inventory.ModMenus;
import com.hbm.tileentity.machine.TileEntityLaser;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.items.SlotItemHandler;

/**
 * 激光器容器（P5.16 迁移版）。
 *
 * 槽位布局：1 输入槽 + 1 输出槽 + 1 电池槽 + 1 透镜槽 + 玩家背包。
 */
public class ContainerLaser extends AbstractContainerMenu {

    private final TileEntityLaser laser;

    public ContainerLaser(int id, Inventory playerInventory, TileEntityLaser laser) {
        super(ModMenus.LASER.get(), id);
        this.laser = laser;

        this.addSlot(new SlotItemHandler(laser.inventory, 0, 44, 17));
        this.addSlot(new SlotItemHandler(laser.inventory, 1, 116, 17));
        this.addSlot(new SlotItemHandler(laser.inventory, 2, 8, 53));
        this.addSlot(new SlotItemHandler(laser.inventory, 3, 80, 53));

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
    public boolean stillValid(Player player) { return laser.isUseableByPlayer(player); }

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

    public TileEntityLaser getLaser() { return laser; }
}
