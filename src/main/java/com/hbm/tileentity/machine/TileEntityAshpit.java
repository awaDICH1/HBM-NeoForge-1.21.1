package com.hbm.tileentity.machine;

import com.hbm.network.ModNetwork;
import com.hbm.packet.toclient.AshpitSyncPacket;
import com.hbm.tileentity.ModTileEntities;
import com.hbm.tileentity.TileEntityMachineBase;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

/**
 * 灰烬槽机器。迁移自 1.12.2 com.hbm.tileentity.machine.TileEntityAshpit。
 *
 * 原实现要点：
 *   - 5 槽取走专用物品栏（ContainerAshpit 中 take-only 槽）
 *   - ashLevelWood/Coal/Misc/Fly/Soot 累积值，达到阈值产出粉末（依赖污染系统，P4 恢复）
 *   - playersUsing + doorAngle/prevDoorAngle 门动画（原由 networkPackNT(50) 同步）
 *   - IGUIProvider → 1.21.1 由 MenuType + MenuProvider 取代
 *   - IConfigurableMachine（JSON 配置）→ P4 迁 ModConfigSpec
 *
 * 1.21.1 变更：
 *   - ITickable.update() → 静态 tick(Level, BlockPos, BlockState, TE)，经方块 getTicker 注册
 *   - networkPackNT(50) → AshpitSyncPacket（NetworkChannel + PacketDistributor）
 *   - readFromNBT/writeToNBT → loadAdditional/saveAdditional（NBT 键名不变，兼容旧存档）
 */
public class TileEntityAshpit extends TileEntityMachineBase {

    public int playersUsing = 0;
    public float doorAngle = 0;
    public float prevDoorAngle = 0;
    public boolean isFull;

    public int ashLevelWood;
    public int ashLevelCoal;
    public int ashLevelMisc;
    public int ashLevelFly;
    public int ashLevelSoot;

    public TileEntityAshpit(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state, 5, 64);
    }

    /** BlockEntityType.Builder.of 需要的工厂签名：(BlockPos, BlockState) -> T */
    public static TileEntityAshpit create(BlockPos pos, BlockState state) {
        return new TileEntityAshpit(ModTileEntities.ASHPIT.get(), pos, state);
    }

    @Override
    public String getDefaultName() {
        return "container.ashpit";
    }

    public static void tick(Level level, BlockPos pos, BlockState state, TileEntityAshpit te) {
        if (!level.isClientSide) {
            // 原 update() 中的 processAsh(...) 依赖污染系统与粉末物品（ModItems.powder_ash），
            // P4 接入污染系统后恢复；此处保留 isFull 计算与网络广播。
            te.isFull = false;
            for (int i = 0; i < te.inventory.getSlots(); i++) {
                if (!te.inventory.getStackInSlot(i).isEmpty()) {
                    te.isFull = true;
                    break;
                }
            }

            // 周期广播（原 networkPackNT(50)）：把 playersUsing/isFull 同步给追踪该区块的玩家
            if (level.getGameTime() % 20 == 0) {
                ModNetwork.CHANNEL.sendToTrackingChunk((ServerLevel) level, new ChunkPos(pos),
                        new AshpitSyncPacket(pos, te.playersUsing, te.isFull));
            }
        } else {
            // 门动画（仅客户端）
            te.prevDoorAngle = te.doorAngle;
            float swingSpeed = (te.doorAngle / 10F) + 3;
            te.doorAngle = Mth.clamp(te.doorAngle + (te.playersUsing > 0 ? swingSpeed : -swingSpeed), 0F, 135F);
        }
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        tag.putInt("ashLevelWood", this.ashLevelWood);
        tag.putInt("ashLevelCoal", this.ashLevelCoal);
        tag.putInt("ashLevelMisc", this.ashLevelMisc);
        tag.putInt("ashLevelFly", this.ashLevelFly);
        tag.putInt("ashLevelSoot", this.ashLevelSoot);
    }

    @Override
    public void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        this.ashLevelWood = tag.getInt("ashLevelWood");
        this.ashLevelCoal = tag.getInt("ashLevelCoal");
        this.ashLevelMisc = tag.getInt("ashLevelMisc");
        this.ashLevelFly = tag.getInt("ashLevelFly");
        this.ashLevelSoot = tag.getInt("ashLevelSoot");
    }
}
