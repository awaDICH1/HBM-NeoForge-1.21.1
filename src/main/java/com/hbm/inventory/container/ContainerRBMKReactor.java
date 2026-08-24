package com.hbm.inventory.container;

import com.hbm.inventory.ModMenus;
import com.hbm.tileentity.machine.TileEntityRBMKReactor;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.items.SlotItemHandler;

/**
 * RBMK 核反应堆容器（P5.15 迁移版）。
 *
 * 槽位布局：1 燃料槽 + 1 废料槽 + 1 冷却液槽 + 1 输出槽 + 玩家背包。
 */
public class ContainerRBMKReactor extends AbstractContainerMenu {

    private final TileEntityRBMKReactor reactor;

    public ContainerRBMKReactor(int id, Inventory playerInventory, TileEntityRBMKReactor reactor) {
        super(ModMenus.RBMK_REACTOR.get(), id);
        this.reactor = reactor;

        this.addSlot(new SlotItemHandler(reactor.inventory, 0, 44, 17));
        this.addSlot(new SlotItemHandler(reactor.inventory, 1, 116, 17));
        this.addSlot(new SlotItemHandler(reactor.inventory, 2, 8, 53));
        this.addSlot(new SlotItemHandler(reactor.inventory, 3, 44, 53));

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
    public boolean stillValid(Player player) { return reactor.isUseableByPlayer(player); }

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

    public TileEntityRBMKReactor getReactor() { return reactor; }
}
