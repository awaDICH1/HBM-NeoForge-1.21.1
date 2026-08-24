package com.hbm.packet.toclient;

import com.hbm.Tags;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.ComponentSerialization;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.network.handling.IPayloadContext;

/**
 * 迁移自 1.12.2 com.hbm.packet.toclient.PlayerInformPacketLegacy。
 *
 * IMessage/IMessageHandler + ByteBufUtils → CustomPacketPayload + StreamCodec；
 * ITextComponent → Component（ComponentSerialization.TRUSTED_STREAM_CODEC）。
 * 原 4 个构造器（String/Component × 有无 millis）→ 4 个静态工厂。
 *
 * ⚠️ 客户端显示：原 MainRegistry.proxy.displayTooltipLegacy（自定义提示条系统，P8 渲染批），
 * 当前以 action bar 消息显示文本，保证能力信息可见。
 */
public record PlayerInformPacketLegacy(boolean fancy, String dmesg, Component component, int id, int millis) implements CustomPacketPayload {

    public static final Type<PlayerInformPacketLegacy> TYPE =
            new Type<>(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "player_inform_legacy"));

    // ComponentSerialization.TRUSTED_STREAM_CODEC 是 RegistryFriendlyByteBuf codec → 整体用 RegistryFriendlyByteBuf
    public static final StreamCodec<RegistryFriendlyByteBuf, PlayerInformPacketLegacy> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.BOOL, PlayerInformPacketLegacy::fancy,
            ByteBufCodecs.STRING_UTF8, PlayerInformPacketLegacy::dmesg,
            ComponentSerialization.TRUSTED_STREAM_CODEC, PlayerInformPacketLegacy::component,
            ByteBufCodecs.VAR_INT, PlayerInformPacketLegacy::id,
            ByteBufCodecs.VAR_INT, PlayerInformPacketLegacy::millis,
            PlayerInformPacketLegacy::new);

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    // ===== 原构造器 (String, int) / (String, int, int) / (Component, int) / (Component, int, int) =====

    public static PlayerInformPacketLegacy text(String dmesg, int id) {
        return new PlayerInformPacketLegacy(false, dmesg, Component.empty(), id, 0);
    }

    public static PlayerInformPacketLegacy text(String dmesg, int id, int millis) {
        return new PlayerInformPacketLegacy(false, dmesg, Component.empty(), id, millis);
    }

    public static PlayerInformPacketLegacy component(Component component, int id) {
        return new PlayerInformPacketLegacy(true, "", component, id, 0);
    }

    public static PlayerInformPacketLegacy component(Component component, int id, int millis) {
        return new PlayerInformPacketLegacy(true, "", component, id, millis);
    }

    public static void handle(final PlayerInformPacketLegacy packet, final IPayloadContext context) {
        context.enqueueWork(() -> {
            if (context.flow().isClientbound() && Minecraft.getInstance().player != null) {
                // 原 MainRegistry.proxy.displayTooltipLegacy(...) —— P8 自定义提示条系统迁移后替换
                String text = packet.fancy() ? packet.component().getString() : packet.dmesg();
                Minecraft.getInstance().player.displayClientMessage(Component.literal(text), true);
            }
        });
    }
}
