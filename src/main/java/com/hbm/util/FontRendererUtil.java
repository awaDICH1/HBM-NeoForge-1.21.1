package com.hbm.util;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

/**
 * 迁移自 1.12.2 com.hbm.util.FontRendererUtil。
 * 1.21.1 变更：
 *   - FontRenderer → Font（getStringWidth → width）
 *   - GlStateManager → 1.21.1 渲染统一走 GuiGraphics（内部 PoseStack；pushMatrix/popMatrix 已移除）
 */
@OnlyIn(Dist.CLIENT)
public class FontRendererUtil {

    public static void drawFittingString(GuiGraphics gui, String text, int x, int y, int color, int maxWidth) {
        if (text == null || text.isEmpty()) return;
        Font fr = Minecraft.getInstance().font;   // 1.21.1 GuiGraphics 无 getFont()
        int textWidth = fr.width(text);

        float scale = 1.0F;
        if (textWidth > maxWidth) scale = (float) maxWidth / textWidth;

        PoseStack pose = gui.pose();
        pose.pushPose();
        pose.translate(x, y, 0);
        pose.scale(scale, scale, 1.0F);
        gui.drawString(fr, text, 0, 0, color);
        pose.popPose();
    }
}
