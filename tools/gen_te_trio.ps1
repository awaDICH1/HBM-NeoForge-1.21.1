# gen_te_trio.ps1 — 机器三件套生成器模板（TE + Container + GUI + 注册×3）
# 用途：为给定机器生成三件套骨架（Barrel 先例模式）。
# 参数（编辑本文件顶部）：$NAME（注册名，如 "gas_cent"）、$CLASS（类名，如 "GasCentrifuge"）、$SLOTS（槽数）、$CTOR_ARGS（TE 构造器额外参数，如 "true, false"）
# usage: Get-Content gen_te_trio.ps1 -Raw -Encoding UTF8 | Invoke-Expression

$NAME = "gas_cent"
$CLASS = "GasCentrifuge"
$SLOTS = 4
$CTOR_ARGS = "true, false"   # enableFluidWrapper, enableEnergyWrapper

function To-Field([string]$n) { return (($n -split '_' | ForEach-Object { $_.ToUpperInvariant() }) -join '_') }
$FIELD = To-Field $NAME

# --- 1. TE ---
$te = @"
package com.hbm.tileentity.machine;

import com.hbm.tileentity.ModTileEntities;
import com.hbm.tileentity.TileEntityMachineBase;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

/**
 * $CLASS（P5.2 三件套模板生成）。
 * 基于 TileEntityMachineBase；具体机器逻辑按 CE 适配。
 */
public class TileEntity$CLASS extends TileEntityMachineBase {

    public TileEntity$CLASS(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state, $SLOTS, $CTOR_ARGS);
    }

    public static TileEntity$CLASS create(BlockPos pos, BlockState state) {
        return new TileEntity$CLASS(ModTileEntities.${FIELD}.get(), pos, state);
    }

    @Override
    public String getDefaultName() {
        return "container.$NAME";
    }
}
"@

# --- 2. Container ---
$ct = @"
package com.hbm.inventory.container;

import com.hbm.inventory.ModMenus;
import com.hbm.tileentity.machine.TileEntity$CLASS;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.items.SlotItemHandler;

public class Container$CLASS extends AbstractContainerMenu {

    private final TileEntity$CLASS tile;

    public Container$CLASS(int id, Inventory playerInventory, TileEntity$CLASS tile) {
        super(ModMenus.${FIELD}.get(), id);
        this.tile = tile;

        for (int i = 0; i < $SLOTS; i++) {
            this.addSlot(new SlotItemHandler(tile.inventory, i, 62 + i * 18, 17));
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
        return tile.isUseableByPlayer(player);
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        ItemStack stack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot != null && slot.hasItem()) {
            ItemStack current = slot.getItem();
            stack = current.copy();
            if (index < $SLOTS) {
                if (!this.moveItemStackTo(current, $SLOTS, this.slots.size(), true)) return ItemStack.EMPTY;
            } else {
                if (!this.moveItemStackTo(current, 0, $SLOTS, false)) return ItemStack.EMPTY;
            }
            if (current.isEmpty()) slot.set(ItemStack.EMPTY);
            else slot.setChanged();
        }
        return stack;
    }
}
"@

# --- 3. GUI ---
$gu = @"
package com.hbm.inventory.gui;

import com.hbm.Tags;
import com.hbm.inventory.container.Container$CLASS;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class GUI$CLASS extends AbstractContainerScreen<Container$CLASS> {

    // TODO P8: 替换为 hbm:textures/gui/machine/gui_$NAME.png（资源未就绪，暂用 ashpit 占位）
    private static final ResourceLocation TEXTURE =
            ResourceLocation.fromNamespaceAndPath(Tags.MODID, "textures/gui/machine/gui_ashpit.png");

    public GUI$CLASS(Container$CLASS menu, Inventory inv, Component title) {
        super(menu, inv, title);
        this.imageWidth = 176;
        this.imageHeight = 166;
    }

    @Override
    protected void renderBg(GuiGraphics gui, float partialTick, int mouseX, int mouseY) {
        gui.blit(TEXTURE, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, 256, 256);
    }

    @Override
    protected void renderLabels(GuiGraphics gui, int mouseX, int mouseY) {
        gui.drawString(this.font, this.title, 8, 6, 0x404040, false);
        gui.drawString(this.font, this.playerInventoryTitle, 8, this.imageHeight - 96 + 2, 0x404040, false);
    }

    @Override
    public void render(GuiGraphics gui, int mouseX, int mouseY, float partialTick) {
        super.render(gui, mouseX, mouseY, partialTick);
        this.renderTooltip(gui, mouseX, mouseY);
    }
}
"@

# --- 输出 ---
$base = "D:\用户文件\文档\HBM1.21.1 Pro\HBM-NeoForge\src\main\java\com\hbm"
[System.IO.File]::WriteAllText("$base\tileentity\machine\TileEntity$CLASS.java", $te, [System.Text.UTF8Encoding]::new($false))
[System.IO.File]::WriteAllText("$base\inventory\container\Container$CLASS.java", $ct, [System.Text.UTF8Encoding]::new($false))
[System.IO.File]::WriteAllText("$base\inventory\gui\GUI$CLASS.java", $gu, [System.Text.UTF8Encoding]::new($false))
Write-Output ("Generated: TileEntity$CLASS / Container$CLASS / GUI$CLASS (reg=$NAME)")
Write-Output "NOTE: 还需手动添加 ModMenus/ModTileEntities/ModBlocks/ClientSetup 注册（参考 Barrel 三件套模式）"
