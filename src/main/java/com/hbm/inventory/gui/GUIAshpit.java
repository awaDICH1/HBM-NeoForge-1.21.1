package com.hbm.inventory.gui;

import com.hbm.Tags;
import com.hbm.inventory.container.ContainerAshpit;
import com.hbm.tileentity.machine.TileEntityAshpit;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

/**
 * 灰烬槽 GUI。迁移自 1.12.2 com.hbm.inventory.gui.GUIAshpit。
 *
 * 1.21.1 变更：
 *   - GuiContainer → AbstractContainerScreen<ContainerAshpit>
 *   - drawGuiContainerBackgroundLayer → renderBg；drawGuiContainerForegroundLayer → renderLabels
 *   - drawString → GuiGraphics.drawString；drawTexturedModalRect → GuiGraphics.blit
 *   - GlStateManager.color / bindTexture 由 GuiGraphics 内部处理
 *   - initGui/onGuiClosed 中的 playersUsing 计数移到 ContainerAshpit（服务端），此处仅展示同步结果
 */
public class GUIAshpit extends AbstractContainerScreen<ContainerAshpit> {

    private static final ResourceLocation TEXTURE =
            ResourceLocation.fromNamespaceAndPath(Tags.MODID, "textures/gui/machine/gui_ashpit.png");

    private final TileEntityAshpit ashpit;

    public GUIAshpit(ContainerAshpit menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
        this.ashpit = menu.getAshpit();
        this.imageWidth = 176;
        this.imageHeight = 168;
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float partialTick, int mouseX, int mouseY) {
        renderBackground(guiGraphics, mouseX, mouseY, partialTick);
        guiGraphics.blit(TEXTURE, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, 256, 256);
    }

    @Override
    protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
        int w = this.font.width(this.title);
        guiGraphics.drawString(this.font, this.title, this.imageWidth / 2 - w / 2, 6, 0x404040, false);
        guiGraphics.drawString(this.font, Component.translatable("container.inventory"), 8, this.imageHeight - 96 + 2, 0x404040, false);

        // 网络同步验收指标：该值由 AshpitSyncPacket 从服务端同步（playersUsing 在菜单打开时 +1）
        if (this.ashpit != null) {
            guiGraphics.drawString(this.font,
                    Component.literal("Players using: " + this.ashpit.playersUsing),
                    8, this.imageHeight - 96 + 14, 0x404040, false);
        }
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        super.render(guiGraphics, mouseX, mouseY, partialTick);
        this.renderTooltip(guiGraphics, mouseX, mouseY);
    }
}
