package com.hbm.util;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

/**
 * 迁移自 1.12.2 com.hbm.util.Clock（@OnlyIn(Dist.CLIENT) → @OnlyIn(Dist.CLIENT)）。
 */
@OnlyIn(Dist.CLIENT)
public class Clock {

    private static long time_ms;

    public static void update() {
        time_ms = System.currentTimeMillis();
    }

    public static long get_ms() {
        return time_ms;
    }
}
