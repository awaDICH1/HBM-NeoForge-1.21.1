package com.hbm.inventory.container;

import com.hbm.inventory.ModMenus;
import com.hbm.tileentity.machine.TileEntityChemicalReactor;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.items.SlotItemHandler;

/**
 * 化学反应器容器（P5.10 迁移版）。
 *
 * 槽位布局：4 输入槽（26,17 / 44,17 / 26,35 / 44,35）+
 *          1 电池槽（8,53）+ 1 输出槽（116,35）+ 玩家背包。
 */
public class ContainerChemicalReactor extends AbstractContainerMenu {

    private final TileEntityChemicalReactor reactor;

    public ContainerChemicalReactor(int id, Inventory playerInventory, TileEntityChemicalReactor reactor) {
        super(ModMenus.CHEMICAL_REACTOR.get(), id);
        this.reactor = reactor;

        this.addSlot(new SlotItemHandler(reactor.inventory, 0, 26, 17));
        this.addSlot(new SlotItemHandler(reactor.inventory, 1, 44, 17));
        this.addSlot(new SlotItemHandler(reactor.inventory, 2, 26, 35));
        this.addSlot(new SlotItemHandler(reactor.inventory, 3, 44, 35));
        this.addSlot(new SlotItemHandler(reactor.inventory, 4, 8, 53));
        this.addSlot(new SlotItemHandler(reactor.inventory, 5, 116, 35));

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
        return reactor.isUseableByPlayer(player);
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
                if (!this.moveItemStackTo(current, 0, 5, false)) return ItemStack.EMPTY;
            }
            if (current.isEmpty()) slot.set(ItemStack.EMPTY);
            else slot.setChanged();
        }
        return stack;
    }

    public TileEntityChemicalReactor getReactor() {
        return reactor;
    }
}
