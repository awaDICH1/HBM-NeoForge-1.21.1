package com.hbm.network;

import com.hbm.Tags;
import com.hbm.packet.toclient.AshpitSyncPacket;
import com.hbm.packet.toclient.BufPacket;
import com.hbm.packet.toclient.PlayerInformPacketLegacy;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.ChunkPos;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

/**
 * 网络通道。迁移自 1.12.2 com.hbm.packet.PacketDispatcher + com.hbm.main.NetworkHandler。
 *
 * 1.12.2：SimpleNetworkWrapper（int 索引 + 预编译 codec + PacketThreading 线程化）。
 * 1.21.1（21.1.128）：不存在 NetworkChannel / SimpleChannel（Forge 类），
 * 改用 NeoForge 标准 payload 体系：
 *   - 注册：RegisterPayloadHandlersEvent（PayloadRegistrar.playToClient，按 TYPE/STREAM_CODEC/handler）
 *   - 发送：PacketDistributor 静态方法（sendToPlayer / sendToPlayersNear / sendToPlayersTrackingEntity /
 *     sendToPlayersInDimension / sendToAllPlayers / sendToPlayersTrackingChunk / sendToServer）
 *   - 线程安全：跨线程发包统一经 MinecraftServer.execute 入队主线程（见 PacketThreading）
 */
@EventBusSubscriber(modid = Tags.MODID, bus = EventBusSubscriber.Bus.MOD)
public class ModNetwork {

    /**
     * 与旧调用点兼容的发送门面：
     * ModNetwork.CHANNEL.sendToPlayer(player, payload) → PacketDistributor.sendToPlayer(player, payload)
     */
    public static final SendFacade CHANNEL = new SendFacade();

    public static class SendFacade {
        public void sendToPlayer(ServerPlayer player, CustomPacketPayload payload) {
            PacketDistributor.sendToPlayer(player, payload);
        }

        public void sendToPlayersNear(ServerLevel level, ServerPlayer except, double x, double y, double z, double range, CustomPacketPayload payload) {
            PacketDistributor.sendToPlayersNear(level, except, x, y, z, range, payload);
        }

        public void sendToAllTracking(Entity entity, CustomPacketPayload payload) {
            PacketDistributor.sendToPlayersTrackingEntity(entity, payload);
        }

        public void sendToDimension(ServerLevel level, CustomPacketPayload payload) {
            PacketDistributor.sendToPlayersInDimension(level, payload);
        }

        public void sendToAll(CustomPacketPayload payload) {
            PacketDistributor.sendToAllPlayers(payload);
        }

        public void sendToTrackingChunk(ServerLevel level, ChunkPos pos, CustomPacketPayload payload) {
            PacketDistributor.sendToPlayersTrackingChunk(level, pos, payload);
        }
    }

    @SubscribeEvent
    public static void registerPayloads(final RegisterPayloadHandlersEvent event) {
        final PayloadRegistrar registrar = event.registrar(Tags.MODID);
        registrar.playToClient(AshpitSyncPacket.TYPE, AshpitSyncPacket.STREAM_CODEC, AshpitSyncPacket::handle);
        registrar.playToClient(PlayerInformPacketLegacy.TYPE, PlayerInformPacketLegacy.STREAM_CODEC, PlayerInformPacketLegacy::handle);
        registrar.playToClient(BufPacket.TYPE, BufPacket.STREAM_CODEC, BufPacket::handle);
    }

    /** 兼容旧调用（HBM 构造器），payload 注册已由 @EventBusSubscriber(MOD) 自动完成 */
    public static void init() {
    }

    private ModNetwork() {
    }
}
