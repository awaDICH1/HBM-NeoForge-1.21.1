package com.hbm.packet.toclient;

import com.hbm.Tags;
import com.hbm.tileentity.machine.TileEntityAshpit;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.neoforge.network.handling.IPayloadContext;

/**
 * 灰烬槽状态同步包（服务端 → 客户端）。
 *
 * 迁移自 1.12.2 com.hbm.packet.toclient.SurveyPacket 的通信模式
 * （原 SimpleNetworkWrapper + IMessage/IMessageHandler；现为 record + CustomPacketPayload + StreamCodec）。
 *
 * 发送方：
 *   - 菜单打开时：PacketDistributor.PLAYER.with(() -> serverPlayer).send(packet)   [原 wrapper.sendTo]
 *   - 每 20 tick：PacketDistributor.TRACKING_CHUNK.with(() -> chunk).send(packet)  [原 networkPackNT]
 *
 * 处理方：IPayloadContext#enqueueWork 回主线程（原 Minecraft.getMinecraft().addScheduledTask）。
 *
 * @param pos         机器位置
 * @param playersUsing 正在使用 GUI 的玩家数（门动画）
 * @param isFull       物品栏是否非空
 */
public record AshpitSyncPacket(BlockPos pos, int playersUsing, boolean isFull) implements CustomPacketPayload {

    public static final Type<AshpitSyncPacket> TYPE =
            new Type<>(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "ashpit_sync"));

    public static final StreamCodec<FriendlyByteBuf, AshpitSyncPacket> STREAM_CODEC = StreamCodec.composite(
            BlockPos.STREAM_CODEC, AshpitSyncPacket::pos,
            ByteBufCodecs.VAR_INT, AshpitSyncPacket::playersUsing,
            ByteBufCodecs.BOOL, AshpitSyncPacket::isFull,
            AshpitSyncPacket::new);

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void handle(final AshpitSyncPacket packet, final IPayloadContext context) {
        // 网络线程 → 主线程（原 addScheduledTask）
        context.enqueueWork(() -> {
            if (context.flow().isClientbound() && Minecraft.getInstance().level != null) {
                BlockEntity be = Minecraft.getInstance().level.getBlockEntity(packet.pos());
                if (be instanceof TileEntityAshpit ashpit) {
                    ashpit.playersUsing = packet.playersUsing();
                    ashpit.isFull = packet.isFull();
                }
            }
        });
    }
}
