package com.hbm.packet.toclient;

import com.hbm.tileentity.IBufPacketReceiver;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.neoforge.network.handling.IPayloadContext;

/**
 * 通用 ByteBuf 数据包（P5.1b-1 迁移版）。
 *
 * 迁移自 1.12.2 com.hbm.packet.toclient.BufPacket（77 行，SimpleNetworkWrapper + PrecompiledPacket）。
 * 1.21.1：CustomPacketPayload + StreamCodec（ByteBufCodecs），客户端处理经 IBufPacketReceiver.deserialize。
 */
public record BufPacket(int x, int y, int z, byte[] data) implements CustomPacketPayload {

    public static final Type<BufPacket> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath("hbm", "buf"));

    public static final StreamCodec<ByteBuf, BufPacket> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.VAR_INT, BufPacket::x,
            ByteBufCodecs.VAR_INT, BufPacket::y,
            ByteBufCodecs.VAR_INT, BufPacket::z,
            ByteBufCodecs.byteArray(1_048_576), BufPacket::data,
            BufPacket::new);

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void handle(final BufPacket msg, final IPayloadContext ctx) {
        ctx.enqueueWork(() -> {
            if (ctx.flow().isClientbound() && Minecraft.getInstance().level != null) {
                BlockEntity te = Minecraft.getInstance().level.getBlockEntity(new BlockPos(msg.x, msg.y, msg.z));
                if (te instanceof IBufPacketReceiver rec) {
                    rec.deserialize(Unpooled.wrappedBuffer(msg.data));
                }
            }
        });
    }
}
