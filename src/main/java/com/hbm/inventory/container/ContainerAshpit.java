package com.hbm.inventory.container;

import com.hbm.inventory.ModMenus;
import com.hbm.tileentity.machine.TileEntityAshpit;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.items.SlotItemHandler;

/**
 * 灰烬槽容器。迁移自 1.12.2 com.hbm.inventory.container.ContainerAshpit。
 *
 * 1.21.1 变更：
 *   - Container → AbstractContainerMenu；canInteractWith → stillValid
 *   - transferStackInSlot → quickMoveStack（原 TransferStrategy 系统 P4 迁移，此处先手写简化版）
 *   - 客户端重建：MenuType 带 StreamCodec（encode 写 BlockPos），客户端工厂从 buf 读 pos 取 TE
 *   - 关闭计数：原 GUI 的 onGuiClosed → 本类 removed(Player)（两侧都会调用，服务端才递减）
 */
public class ContainerAshpit extends AbstractContainerMenu {

    private final TileEntityAshpit ashpit;

    /** 服务端入口 / 客户端正常入口 */
    public ContainerAshpit(int id, Inventory playerInventory, TileEntityAshpit ashpit) {
        super(ModMenus.ASHPIT.get(), id);
        this.ashpit = ashpit;

        // 5 个取走专用槽（原 SlotFiltered.takeOnly：不可放入，仅可取出）
        for (int i = 0; i < 5; i++) {
            final int slotIndex = i;
            this.addSlot(new SlotItemHandler(ashpit.inventory, i, 44 + i * 18, 27) {
                @Override
                public boolean mayPlace(ItemStack stack) {
                    return false;
                }
            });
        }

        // 玩家背包 3x9 + 快捷栏 1x9（坐标与原版一致）
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                this.addSlot(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 86 + i * 18));
            }
        }
        for (int i = 0; i < 9; i++) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 144));
        }
    }

    /** 客户端工厂：从 MenuType codec 写入的 extraData 中读取 BlockPos 并取 TE */
    public ContainerAshpit(int id, Inventory playerInventory, FriendlyByteBuf extraData) {
        this(id, playerInventory, extraData.readBlockPos());
    }

    /** 对 null 背包容错（MenuType codec 的 decode 侧占位用，正常打开流程不经过） */
    public ContainerAshpit(int id, Inventory playerInventory, BlockPos pos) {
        this(id, playerInventory, teAt(playerInventory, pos));
    }

    private static TileEntityAshpit teAt(Inventory playerInventory, BlockPos pos) {
        if (playerInventory != null && playerInventory.player.level().getBlockEntity(pos) instanceof TileEntityAshpit te) {
            return te;
        }
        return null;
    }

    public TileEntityAshpit getAshpit() {
        return this.ashpit;
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        Slot slot = this.slots.get(index);
        if (!slot.hasItem()) {
            return ItemStack.EMPTY;
        }
        ItemStack stack = slot.getItem();
        ItemStack copy = stack.copy();
        if (index < 5) {
            // 机器槽 → 玩家背包
            if (!this.moveItemStackTo(stack, 5, 41, true)) {
                return ItemStack.EMPTY;
            }
        } else {
            // 玩家背包 → 机器槽（take-only 槽的 mayPlace=false 会被 moveItemStackTo 拦截）
            if (!this.moveItemStackTo(stack, 0, 5, false)) {
                return ItemStack.EMPTY;
            }
        }
        if (stack.isEmpty()) {
            slot.set(ItemStack.EMPTY);
        } else {
            slot.setChanged();
        }
        if (stack.getCount() == copy.getCount()) {
            return ItemStack.EMPTY;
        }
        slot.onTake(player, stack);
        return copy;
    }

    @Override
    public boolean stillValid(Player player) {
        return this.ashpit != null && this.ashpit.isUseableByPlayer(player);
    }

    @Override
    public void removed(Player player) {
        super.removed(player);
        // 原 GUIAshpit.onGuiClosed：菜单关闭时递减（仅服务端计数）
        if (this.ashpit != null && !player.level().isClientSide) {
            this.ashpit.playersUsing--;
        }
    }
}
