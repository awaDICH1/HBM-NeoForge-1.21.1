package com.hbm.main;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.server.ServerStoppingEvent;

/**
 * 游戏总线事件处理器。
 * 迁移自 1.12.2 com.hbm.main.ModEventHandler（经 MinecraftForge.EVENT_BUS.register 注册）。
 * 原类持有实例字段（如 RBMK_COL_HEIGHT_MAP），故沿用实例注册而非静态订阅。
 */
public class ModEventHandler {

    // 原：public static final Map<BlockPos, Float> RBMK_COL_HEIGHT_MAP = new HashMap<>();
    // P5 迁移 RBMK 高度图/污染/伤害等逻辑时在此补充

    @SubscribeEvent
    public void onServerStopping(ServerStoppingEvent event) {
        // 原 MainRegistry.serverStopping()：RadiationSystemNT.onServerStopping() 等
    }
}
