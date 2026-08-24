package com.hbm.inventory.container;

import com.hbm.inventory.ModMenus;
import com.hbm.tileentity.machine.TileEntityParticleAccelerator;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.items.SlotItemHandler;

/**
 * 粒子加速器容器（P5.16 迁移版）。
 *
 * 槽位布局：1 粒子源槽 + 1 输出槽 + 1 电池槽 + 1 目标槽 + 玩家背包。
 */
public class ContainerParticleAccelerator extends AbstractContainerMenu {

    private final TileEntityParticleAccelerator accelerator;

    public ContainerParticleAccelerator(int id, Inventory playerInventory, TileEntityParticleAccelerator accelerator) {
        super(ModMenus.PARTICLE_ACCELERATOR.get(), id);
        this.accelerator = accelerator;

        this.addSlot(new SlotItemHandler(accelerator.inventory, 0, 44, 17));
        this.addSlot(new SlotItemHandler(accelerator.inventory, 1, 116, 17));
        this.addSlot(new SlotItemHandler(accelerator.inventory, 2, 8, 53));
        this.addSlot(new SlotItemHandler(accelerator.inventory, 3, 80, 53));

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
    public boolean stillValid(Player player) { return accelerator.isUseableByPlayer(player); }

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

    public TileEntityParticleAccelerator getAccelerator() { return accelerator; }
}
