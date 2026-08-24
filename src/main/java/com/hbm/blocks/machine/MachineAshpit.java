package com.hbm.blocks.machine;

import com.hbm.inventory.container.ContainerAshpit;
import com.hbm.network.ModNetwork;
import com.hbm.packet.toclient.AshpitSyncPacket;
import com.hbm.tileentity.machine.TileEntityAshpit;
import com.hbm.tileentity.ModTileEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

/**
 * 灰烬槽方块。迁移自 1.12.2 com.hbm.blocks.machine.MachineAshpit。
 *
 * ⚠️ 简化说明：原实现 extends BlockDummyable（多块结构：核心块 + 占位块），
 * BlockDummyable 系统在 P5 随机器体系整体迁移；P1 切片先用单方块简化版
 * （普通 Block + EntityBlock），保持类名/包名与 CE 一致，后续可直接替换父类。
 *
 * 1.21.1 变更：
 *   - onBlockActivated → useWithoutItem；打开 GUI 用 player.openMenu(MenuProvider)
 *     （原 NetworkRegistry.INSTANCE.registerGuiHandler(int id) 分发整体删除）
 *   - getTicker 注册 TE 的 tick
 */
public class MachineAshpit extends Block implements EntityBlock {

    public MachineAshpit(BlockBehaviour.Properties properties) {
        super(properties);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new TileEntityAshpit(ModTileEntities.ASHPIT.get(), pos, state);
    }

    @Override
    public InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hit) {
        if (!level.isClientSide && level.getBlockEntity(pos) instanceof TileEntityAshpit te) {
            player.openMenu(provider(te), buf -> buf.writeBlockPos(te.getBlockPos()));
        }
        return InteractionResult.sidedSuccess(level.isClientSide);
    }

    /**
     * 原 TileEntityAshpit.provideContainer / provideGUI（IGUIProvider）。
     * createMenu 仅服务端调用：计数 playersUsing 并向该玩家发送同步包（PacketDistributor.sendToPlayer）。
     * openMenu 第二参数写入 extraData（BlockPos），客户端经 IMenuTypeExtension 工厂重建菜单。
     */
    private MenuProvider provider(TileEntityAshpit te) {
        return new SimpleMenuProvider(
                (id, inv, p) -> {
                    te.playersUsing++;
                    ModNetwork.CHANNEL.sendToPlayer((ServerPlayer) p,
                            new AshpitSyncPacket(te.getBlockPos(), te.playersUsing, te.isFull));
                    return new ContainerAshpit(id, inv, te);
                },
                Component.translatable("container.ashpit"));
    }

    @Nullable
    @SuppressWarnings("unchecked")
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        if (type != ModTileEntities.ASHPIT.get()) return null;
        return (BlockEntityTicker<T>) (BlockEntityTicker<TileEntityAshpit>) TileEntityAshpit::tick;
    }
}
