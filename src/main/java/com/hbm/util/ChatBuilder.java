package com.hbm.util;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * 迁移自 1.12.2 com.hbm.util.ChatBuilder。
 * 重构：1.12 的 TextComponentString/ITextComponent/Style.setColor/appendSibling
 * → 1.21.1 的 MutableComponent/Component.literal/translatable/append/withStyle（组件不可变，
 * MutableComponent.withStyle 原地修改自身并返回 this）。
 */
public final class ChatBuilder {

    private final MutableComponent text;
    private MutableComponent last;

    private ChatBuilder(String text) {
        this.text = Component.literal(text);
        this.last = this.text;
    }

    public static ChatBuilder start(String text) {
        return new ChatBuilder(text);
    }

    public static ChatBuilder startTranslation(String key, Object... args) {
        return new ChatBuilder("").nextTranslation(key, args);
    }

    public ChatBuilder next(String text) {
        MutableComponent append = Component.literal(text);
        this.last.append(append);
        this.last = append;
        return this;
    }

    public ChatBuilder nextTranslation(String key, Object... args) {
        MutableComponent append = Component.translatable(key, args);
        this.last.append(append);
        this.last = append;
        return this;
    }

    public ChatBuilder color(ChatFormatting format) {
        this.last.withStyle(format);
        return this;
    }

    /**
     * Recursively applies the color to the root component and all its siblings.
     */
    public ChatBuilder colorAll(ChatFormatting format) {
        List<Component> list = new ArrayList<>();
        list.add(text);

        ListIterator<Component> it = list.listIterator();
        while (it.hasNext()) {
            Component component = it.next();
            if (component instanceof MutableComponent mc) {
                mc.withStyle(format);
            }
            for (Component s : component.getSiblings()) it.add(s);
        }
        return this;
    }

    /** 原返回 TextComponentString；1.21.1 返回 MutableComponent（即 Component） */
    public MutableComponent flush() {
        return this.text;
    }
}
