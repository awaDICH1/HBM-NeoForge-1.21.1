package com.hbm.inventory.gui;

import com.hbm.Tags;
import com.hbm.inventory.container.*;
import com.hbm.tileentity.machine.TileEntityAssembler;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;

/**
 * 机器 GUI 集合（F-1 增强版）。
 *
 * 为所有机器提供 GUI 屏幕，包含进度条和能量条渲染。
 * Base 基类提供通用的进度条/能量条绘制方法，子类可覆盖自定义纹理和位置。
 */
public class MachineGUIs {

    private static final ResourceLocation TEXTURE_BASE =
            ResourceLocation.fromNamespaceAndPath(Tags.MODID, "textures/gui/machine/gui_ashpit.png");

    private static abstract class MachineBase<T extends AbstractContainerMenu> extends AbstractContainerScreen<T> {

        MachineBase(T menu, Inventory inv, Component title) {
            super(menu, inv, title);
            this.imageWidth = 176;
            this.imageHeight = 168;
        }

        protected ResourceLocation getTexture() { return TEXTURE_BASE; }

        /** 进度条在纹理中的坐标（u, v, w, h），子类覆盖 */
        protected int getProgressU() { return 176; }
        protected int getProgressV() { return 0; }
        protected int getProgressW() { return 22; }
        protected int getProgressH() { return 16; }
        protected int getProgressX() { return 79; }
        protected int getProgressY() { return 34; }

        /** 能量条在纹理中的坐标，子类覆盖 */
        protected int getEnergyU() { return 176; }
        protected int getEnergyV() { return 16; }
        protected int getEnergyW() { return 16; }
        protected int getEnergyH() { return 52; }
        protected int getEnergyX() { return 8; }
        protected int getEnergyY() { return 17; }

        protected int getProgress() { return 0; }
        protected int getMaxProgress() { return 1; }
        protected int getPower() { return 0; }
        protected int getMaxPower() { return 1; }

        @Override
        protected void renderBg(GuiGraphics g, float p, int mx, int my) {
            renderBackground(g, mx, my, p);
            g.blit(getTexture(), this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, 256, 256);

            // 绘制进度条（从左到右）
            int progress = getProgress();
            int maxProgress = getMaxProgress();
            if (maxProgress > 0 && progress > 0) {
                int scaled = progress * getProgressW() / maxProgress;
                if (scaled > getProgressW()) scaled = getProgressW();
                g.blit(getTexture(),
                        this.leftPos + getProgressX(), this.topPos + getProgressY(),
                        getProgressU(), getProgressV(),
                        scaled, getProgressH(),
                        256, 256);
            }

            // 绘制能量条（从下到上）
            int power = getPower();
            int maxPower = getMaxPower();
            if (maxPower > 0 && power > 0) {
                int scaled = power * getEnergyH() / maxPower;
                if (scaled > getEnergyH()) scaled = getEnergyH();
                g.blit(getTexture(),
                        this.leftPos + getEnergyX(), this.topPos + getEnergyY() + getEnergyH() - scaled,
                        getEnergyU(), getEnergyV() + getEnergyH() - scaled,
                        getEnergyW(), scaled,
                        256, 256);
            }
        }

        @Override
        protected void renderLabels(GuiGraphics g, int mx, int my) {
            int w = this.font.width(this.title);
            g.drawString(this.font, this.title, this.imageWidth / 2 - w / 2, 6, 0x404040, false);
            g.drawString(this.font, Component.translatable("container.inventory"), 8, this.imageHeight - 96 + 2, 0x404040, false);
        }

        @Override
        public void render(GuiGraphics g, int mx, int my, float p) {
            super.render(g, mx, my, p);
            this.renderTooltip(g, mx, my);
        }
    }

    // ========== 各机器 GUI 子类 ==========

    public static class Assembler extends MachineBase<ContainerAssembler> {
        private final TileEntityAssembler assembler;

        public Assembler(ContainerAssembler m, Inventory i, Component t) {
            super(m, i, t);
            this.assembler = m.getAssembler();
        }

        @Override protected int getProgress() { return assembler.progress; }
        @Override protected int getMaxProgress() { return assembler.maxProgress; }
        @Override protected int getPower() { return assembler.power; }
        @Override protected int getMaxPower() { return assembler.maxPower; }

        @Override
        protected int getProgressX() { return 80; }
        @Override
        protected int getProgressY() { return 36; }
        @Override
        protected int getProgressW() { return 24; }
        @Override
        protected int getProgressH() { return 16; }
    }

    public static class Compressor extends MachineBase<ContainerCompressor> {
        public Compressor(ContainerCompressor m, Inventory i, Component t) { super(m, i, t); }
    }

    public static class FluidTank extends MachineBase<ContainerFluidTank> {
        public FluidTank(ContainerFluidTank m, Inventory i, Component t) { super(m, i, t); }
    }

    public static class RBMKConsole extends MachineBase<ContainerRBMKConsole> {
        public RBMKConsole(ContainerRBMKConsole m, Inventory i, Component t) { super(m, i, t); }
    }

    public static class ChemicalReactor extends MachineBase<ContainerChemicalReactor> {
        public ChemicalReactor(ContainerChemicalReactor m, Inventory i, Component t) { super(m, i, t); }
    }

    public static class ArcFurnace extends MachineBase<ContainerArcFurnace> {
        public ArcFurnace(ContainerArcFurnace m, Inventory i, Component t) { super(m, i, t); }
    }

    public static class Centrifuge extends MachineBase<ContainerCentrifuge> {
        public Centrifuge(ContainerCentrifuge m, Inventory i, Component t) { super(m, i, t); }
    }

    public static class Crusher extends MachineBase<ContainerCrusher> {
        public Crusher(ContainerCrusher m, Inventory i, Component t) { super(m, i, t); }
    }

    public static class FluidReactor extends MachineBase<ContainerFluidReactor> {
        public FluidReactor(ContainerFluidReactor m, Inventory i, Component t) { super(m, i, t); }
    }

    public static class RBMKReactor extends MachineBase<ContainerRBMKReactor> {
        public RBMKReactor(ContainerRBMKReactor m, Inventory i, Component t) { super(m, i, t); }
    }

    public static class HeatExchanger extends MachineBase<ContainerHeatExchanger> {
        public HeatExchanger(ContainerHeatExchanger m, Inventory i, Component t) { super(m, i, t); }
    }

    public static class ParticleAccelerator extends MachineBase<ContainerParticleAccelerator> {
        public ParticleAccelerator(ContainerParticleAccelerator m, Inventory i, Component t) { super(m, i, t); }
    }

    public static class Laser extends MachineBase<ContainerLaser> {
        public Laser(ContainerLaser m, Inventory i, Component t) { super(m, i, t); }
    }
}
