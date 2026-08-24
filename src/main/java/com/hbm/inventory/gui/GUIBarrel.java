package com.hbm.inventory.gui;

import com.hbm.Tags;
import com.hbm.inventory.container.ContainerBarrel;
import com.hbm.tileentity.machine.TileEntityBarrel;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

/**
 * 流体桶 GUI（P5.2 迁移版）。
 *
 * 迁移自 1.12.2 com.hbm.inventory.gui.GUIBarrel。
 * 1.21.1 变更：AbstractContainerScreen + GuiGraphics；
 * 纹理：原 gui_barrel.png 资源未就绪 → 暂用 gui_ashpit.png 占位（TODO P8 资源批替换）；
 * 流体信息经 renderTankInfoText 文本呈现（P8 渲染迁移后恢复流体条绘制）。
 */
@OnlyIn(Dist.CLIENT)
public class GUIBarrel extends AbstractContainerScreen<ContainerBarrel> {

    // TODO P8: 替换为 hbm:textures/gui/machine/gui_barrel.png（资源未就绪，暂用 ashpit 占位）
    private static final ResourceLocation TEXTURE =
            ResourceLocation.fromNamespaceAndPath(Tags.MODID, "textures/gui/machine/gui_ashpit.png");

    private final TileEntityBarrel barrel;

    public GUIBarrel(ContainerBarrel menu, Inventory inv, Component title) {
        super(menu, inv, title);
        this.barrel = menu.getBarrel();
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

        // P8 渲染迁移前：流体信息文本呈现（原 renderTankInfoText 同款）
        java.util.List<String> info = new java.util.ArrayList<>();
        this.barrel.tankNew.renderTankInfoText(info);
        int y = 20;
        for (String line : info) {
            gui.drawString(this.font, line, 62, y, 0xFFFFFF, false);
            y += 10;
        }
    }

    @Override
    public void render(GuiGraphics gui, int mouseX, int mouseY, float partialTick) {
        super.render(gui, mouseX, mouseY, partialTick);
        this.renderTooltip(gui, mouseX, mouseY);
    }
}
