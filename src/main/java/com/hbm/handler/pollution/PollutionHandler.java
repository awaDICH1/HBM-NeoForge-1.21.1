package com.hbm.handler.pollution;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;

/**
 * 污染系统（P4.1 批次A 骨架版）。
 *
 * 迁移自 1.12.2 com.hbm.handler.pollution.PollutionHandler（396 行）。
 * 当前仅含流体特质（FT_Polluting）所需的 PollutionType 枚举与 incrementPollution 桩；
 * 完整污染系统（SOOT/SMOKE 等扩散、粒子、getPollution、事件订阅）待 P5。
 */
public class PollutionHandler {

    public static enum PollutionType {
        SOOT("trait.ptype.soot"),
        POISON("trait.ptype.poison"),
        HEAVYMETAL("trait.ptype.heavymetal"),
        FALLOUT("trait.ptype.fallout");

        public static final PollutionType[] VALUES = values();

        public String name;

        private PollutionType(String name) {
            this.name = name;
        }
    }

    // TODO P5: 完整污染扩散系统（Chunk 级污染图、粒子、rampant 事件）
    public static void incrementPollution(Level world, BlockPos pos, PollutionType type, float amount) { }

    /* 每秒污染基础速率（CE 原值，Fluids 的 SOOT_* / POISON_* 常量依赖） */
    public static final float SOOT_PER_SECOND = 1F / 25F;
    public static final float HEAVY_METAL_PER_SECOND = 1F / 50F;
    public static final float POISON_PER_SECOND = 1F / 50F;
}
