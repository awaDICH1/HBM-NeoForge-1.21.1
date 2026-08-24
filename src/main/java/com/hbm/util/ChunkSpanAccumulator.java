package com.hbm.util;

import net.minecraft.world.level.block.entity.BlockEntity;

import java.util.ArrayList;

/**
 * 迁移自 1.12.2 com.hbm.util.ChunkSpanAccumulator（BlockEntity → BlockEntity）。
 * 用于自定义区块渲染（RenderChunk 后台线程）的跨区块 TESR 累积器，P8 渲染迁移时接入。
 */
public final class ChunkSpanAccumulator {

    public static final ThreadLocal<ChunkSpanAccumulator> LOCAL = ThreadLocal.withInitial(ChunkSpanAccumulator::new);

    public int negX, posX, negY, posY, negZ, posZ;
    public final ArrayList<BlockEntity> spanningTesrs = new ArrayList<>();

    public void reset() {
        negX = 0;
        posX = 0;
        negY = 0;
        posY = 0;
        negZ = 0;
        posZ = 0;
        spanningTesrs.clear();
    }
}
