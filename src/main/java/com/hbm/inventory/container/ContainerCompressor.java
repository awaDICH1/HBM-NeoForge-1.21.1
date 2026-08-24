package com.hbm.inventory.container;

import com.hbm.inventory.ModMenus;
import com.hbm.tileentity.machine.TileEntityCompressor;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.items.SlotItemHandler;

/**
 * 压缩机容器（F-1 数据同步增强版）。
 *
 * 添加 ContainerData 用于同步进度和能量到客户端 GUI。
 */
public class ContainerCompressor extends AbstractContainerMenu {

    private final TileEntityCompressor compressor;
    private final ContainerData data;

    public ContainerCompressor(int id, Inventory playerInventory, TileEntityCompressor compressor) {
        this(id, playerInventory, compressor, new SimpleContainerData(4));
    }

    public ContainerCompressor(int id, Inventory playerInventory, TileEntityCompressor compressor, ContainerData data) {
        super(ModMenus.COMPRESSOR.get(), id);
        this.compressor = compressor;
        this.data = data;

        this.addSlot(new SlotItemHandler(compressor.inventory, 0, 44, 17));
        this.addSlot(new SlotItemHandler(compressor.inventory, 1, 44, 53));
        this.addSlot(new SlotItemHandler(compressor.inventory, 2, 116, 35));

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                this.addSlot(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 86 + i * 18));
            }
        }
        for (int i = 0; i < 9; i++) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 144));
        }

        this.addDataSlots(data);
    }

    public int getProgress() { return data.get(0); }
    public int getMaxProgress() { return data.get(1); }
    public int getPower() { return data.get(2); }
    public int getMaxPower() { return data.get(3); }

    @Override
    public boolean stillValid(Player player) {
        return compressor.isUseableByPlayer(player);
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        ItemStack stack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot != null && slot.hasItem()) {
            ItemStack current = slot.getItem();
            stack = current.copy();
            if (index < 3) {
                if (!this.moveItemStackTo(current, 3, this.slots.size(), true)) return ItemStack.EMPTY;
            } else {
                if (!this.moveItemStackTo(current, 0, 2, false)) return ItemStack.EMPTY;
            }
            if (current.isEmpty()) slot.set(ItemStack.EMPTY);
            else slot.setChanged();
        }
        return stack;
    }

    public TileEntityCompressor getCompressor() {
        return compressor;
    }
}
