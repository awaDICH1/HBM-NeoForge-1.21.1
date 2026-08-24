package com.hbm.inventory.container;

import com.hbm.inventory.ModMenus;
import com.hbm.tileentity.machine.TileEntityAssembler;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.items.SlotItemHandler;

/**
 * 组装机容器（P5.14 迁移版）。
 *
 * 槽位布局：3 输入槽 + 1 输出槽 + 1 电池槽 + 玩家背包。
 */
public class ContainerAssembler extends AbstractContainerMenu {

    private final TileEntityAssembler assembler;

    public ContainerAssembler(int id, Inventory playerInventory, TileEntityAssembler assembler) {
        super(ModMenus.ASSEMBLER.get(), id);
        this.assembler = assembler;

        this.addSlot(new SlotItemHandler(assembler.inventory, 0, 26, 35));
        this.addSlot(new SlotItemHandler(assembler.inventory, 1, 44, 35));
        this.addSlot(new SlotItemHandler(assembler.inventory, 2, 62, 35));
        this.addSlot(new SlotItemHandler(assembler.inventory, 3, 116, 35));
        this.addSlot(new SlotItemHandler(assembler.inventory, 4, 8, 53));

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
    public boolean stillValid(Player player) { return assembler.isUseableByPlayer(player); }

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
                if (!this.moveItemStackTo(current, 0, 3, false)) return ItemStack.EMPTY;
            }
            if (current.isEmpty()) slot.set(ItemStack.EMPTY);
            else slot.setChanged();
        }
        return stack;
    }

    public TileEntityAssembler getAssembler() { return assembler; }
}
