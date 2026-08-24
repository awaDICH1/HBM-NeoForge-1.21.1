package com.hbm.handler.threading;

import com.hbm.config.GeneralConfig;
import com.hbm.main.HBM;
import com.hbm.network.ModNetwork;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.server.ServerLifecycleHooks;

/**
 * 迁移自 1.12.2 com.hbm.handler.threading.PacketThreading。
 *
 * ⚠️ 简化说明：原实现是 1.12 专属的高性能管线——ThreadedPacket 预编译 ByteBuf +
 * NetworkHandler 直写通道（flushServer/flushClient）+ UnsafeHolder 字段偏移 + jctools 队列，
 * NeoForge 无对应物。1.21.1 的正确跨线程发包语义：
 *   NetworkChannel 不保证从任意线程发送安全 → 统一经 MinecraftServer.execute 入队主线程执行。
 * 保留原公开 API 形状（createSendTo* / createAllAround*ThreadedPacket），
 * 参数类型改为 CustomPacketPayload + 目标对象（TargetPoint → 坐标参数）。
 */
public class PacketThreading {

    /** 原 init() 按 GeneralConfig.enablePacketThreading 建线程池；1.21.1 由 MinecraftServer.execute 承担队列 */
    public static void init() {
        if (GeneralConfig.enablePacketThreading) {
            HBM.LOGGER.info("PacketThreading: 线程化发包已启用（经服务端主线程队列）");
        }
    }

    /** 在服务端主线程执行发包（线程安全；当前无服务端时直接执行） */
    private static void dispatch(Runnable send) {
        MinecraftServer server = ServerLifecycleHooks.getCurrentServer();
        if (server != null) {
            server.execute(send);
        } else {
            send.run();
        }
    }

    /** 原 createSendToThreadedPacket(IMessage, ServerPlayer) */
    public static void createSendToThreadedPacket(CustomPacketPayload message, ServerPlayer player) {
        dispatch(() -> ModNetwork.CHANNEL.sendToPlayer(player, message));
    }

    /** 原 createAllAroundThreadedPacket(IMessage, TargetPoint)——TargetPoint(dim,x,y,z,range) → sendToPlayersNear */
    public static void createAllAroundThreadedPacket(CustomPacketPayload message, ServerLevel level, double x, double y, double z, double range) {
        dispatch(() -> ModNetwork.CHANNEL.sendToPlayersNear(level, null, x, y, z, range, message));
    }

    /** 原 createSendToAllTrackingThreadedPacket(IMessage, TargetPoint) */
    public static void createSendToAllTrackingThreadedPacket(CustomPacketPayload message, ServerLevel level, double x, double y, double z, double range) {
        dispatch(() -> ModNetwork.CHANNEL.sendToPlayersNear(level, null, x, y, z, range, message));
    }

    /** 原 createSendToAllTrackingThreadedPacket(IMessage, Entity) */
    public static void createSendToAllTrackingThreadedPacket(CustomPacketPayload message, Entity entity) {
        dispatch(() -> ModNetwork.CHANNEL.sendToAllTracking(entity, message));
    }

    /** 原 createSendToDimensionThreadedPacket(IMessage, int)——int 维度 ID 在 1.21.1 改为 ServerLevel */
    public static void createSendToDimensionThreadedPacket(CustomPacketPayload message, ServerLevel level) {
        dispatch(() -> ModNetwork.CHANNEL.sendToDimension(level, message));
    }

    /** 原 createSendToAllThreadedPacket(IMessage) */
    public static void createSendToAllThreadedPacket(CustomPacketPayload message) {
        dispatch(() -> ModNetwork.CHANNEL.sendToAll(message));
    }

    /** 原 createSendToServerThreadedPacket(IMessage) */
    public static void createSendToServerThreadedPacket(CustomPacketPayload message) {
        dispatch(() -> PacketDistributor.sendToServer(message));
    }
}
