package com.hbm.inventory.container;

import com.hbm.inventory.ModMenus;
import com.hbm.tileentity.machine.TileEntityBarrel;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.items.SlotItemHandler;

/**
 * 流体桶容器（P5.2 迁移版）。
 *
 * 迁移自 1.12.2 com.hbm.inventory.container.ContainerBarrel。
 * 1.21.1 变更：AbstractContainerMenu + SlotItemHandler（MachineBase.inventory）；
 * 客户端经 MenuType（IMenuTypeExtension.create + buf.readBlockPos）重建；
 * 槽位布局：6 槽（62 + i*18, 17）+ 玩家背包（86/144 行）。
 */
public class ContainerBarrel extends AbstractContainerMenu {

    private final TileEntityBarrel barrel;

    public ContainerBarrel(int id, Inventory playerInventory, TileEntityBarrel barrel) {
        super(ModMenus.BARREL.get(), id);
        this.barrel = barrel;

        for (int i = 0; i < 6; i++) {
            this.addSlot(new SlotItemHandler(barrel.inventory, i, 62 + i * 18, 17));
        }

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
        return barrel.isUseableByPlayer(player);
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        ItemStack stack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot != null && slot.hasItem()) {
            ItemStack current = slot.getItem();
            stack = current.copy();
            if (index < 6) {
                if (!this.moveItemStackTo(current, 6, this.slots.size(), true)) return ItemStack.EMPTY;
            } else {
                if (!this.moveItemStackTo(current, 0, 6, false)) return ItemStack.EMPTY;
            }
            if (current.isEmpty()) slot.set(ItemStack.EMPTY);
            else slot.setChanged();
        }
        return stack;
    }

    public TileEntityBarrel getBarrel() {
        return barrel;
    }
}
