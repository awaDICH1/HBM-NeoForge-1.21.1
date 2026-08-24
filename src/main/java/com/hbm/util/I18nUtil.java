package com.hbm.util;

import net.minecraft.client.gui.Font;
import net.minecraft.client.resources.language.I18n;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.fml.loading.FMLEnvironment;

import java.util.ArrayList;
import java.util.List;

/**
 * 迁移自 1.12.2 com.hbm.util.I18nUtil。
 * 变更：FMLCommonHandler.instance().getSide().isClient() → FMLEnvironment.dist.isClient()；
 * FontRenderer → Font（getStringWidth → width）；I18n 包路径迁移（format → get）；
 * @SideOnly → @OnlyIn。
 */
public class I18nUtil {

    public static String resolveKey(String s, Object... args) {
        return FMLEnvironment.dist.isClient() ? ClientOnly.format(s, args) : s;
    }

    @OnlyIn(Dist.CLIENT)
    private static class ClientOnly {
        private static String format(String s, Object... args) {
            return I18n.get(s, args);
        }
    }

    public static String[] resolveKeyArray(String s, Object... args) {
        return resolveKey(s, args).split("\\$");
    }

    @OnlyIn(Dist.CLIENT)
    public static List<String> autoBreak(Font fontRenderer, String text, int width) {

        List<String> lines = new ArrayList<>();
        //split the text by all spaces
        String[] words = text.split(" ");

        //add the first word to the first line, no matter what
        lines.add(words[0]);
        //starting indent is the width of the first word
        int indent = fontRenderer.width(words[0]);

        for (int w = 1; w < words.length; w++) {

            //increment the indent by the width of the next word + leading space
            indent += fontRenderer.width(" " + words[w]);

            //if the indent is within bounds
            if (indent <= width) {
                //add the next word to the last line (i.e. the one in question)
                String last = lines.get(lines.size() - 1);
                lines.set(lines.size() - 1, last + (" " + words[w]));
            } else {
                //otherwise, start a new line and reset the indent
                lines.add(words[w]);
                indent = fontRenderer.width(words[w]);
            }
        }

        return lines;
    }
}
