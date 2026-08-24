package com.hbm.tileentity;

import com.hbm.api.tile.ILoadedTile;
import com.hbm.config.GeneralConfig;
import com.hbm.handler.threading.PacketThreading;
import com.hbm.lib.Library;
import com.hbm.packet.toclient.BufPacket;
import com.hbm.sound.AudioWrapper;
import com.hbm.sound.ModSounds;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

/**
 * 加载/同步基类（P5.1b-1 迁移版）。
 *
 * 迁移自 1.12.2 com.hbm.tileentity.TileEntityLoadedBase（290 行）。
 * 1.21.1 变更：
 *  - BlockEntity 构造器 (BlockEntityType, BlockPos, BlockState)；
 *  - readFromNBT/writeToNBT → loadAdditional/saveAdditional；getUpdateTag()/handleUpdateTag(tag)
 *    → getUpdateTag(Provider)/handleUpdateTag(tag, Provider)；getUpdatePacket/onDataPacket 删除
 *    （1.21 BlockEntity 更新系统自动）；
 *  - world/pos → level/worldPosition；markChunkDirty → setBlocksDirty；isRemote → isClientSide；
 *  - networkPackNT/MK2：BufPacket(payload) + PacketThreading.createAllAroundThreadedPacket(ServerLevel)；
 *    Library.fnv1a64 去重 + TargetPoint/dimension 删除（TODO P8 恢复去重）；
 *  - checkTilt：getMaterial/isNormalCube/isSideSolid/Material 材质系统 → isSolid 简化 + TODO P8；
 *    HBMSoundHandler.metalImpact → ModSounds.METAL_IMPACT.get()；SoundCategory → SoundSource；
 *    ModBlocks.dirt_dead/oily/stone_cracked 缺失 → TODO P3 方块桩；
 *  - NBT 键 "muffled"/"tilted"/"hbmSync" 保留。
 */
public class TileEntityLoadedBase extends BlockEntity implements ILoadedTile, IBufPacketReceiver {

    public boolean isLoaded = true;
    public boolean muffled = false;
    public boolean tilted = false;
    public int tiltBlocksChecked = 0;
    public int tiltBlocksValid = 0;

    protected boolean hasDataChanged = true;
    private long lastPackedBufHash = 0L;

    public TileEntityLoadedBase(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    @Override
    public boolean isLoaded() {
        return isLoaded;
    }

    @Override
    public void onLoad() {
        super.onLoad();
        isLoaded = true;
    }

    /** 1.21.1 BlockEntity 无 onChunkUnload（已移除）→ 保留为自定义回调，由卸载流程显式调用 */
    public void onChunkUnload() {
        isLoaded = false;
    }

    /** The "chunks is modified, pls don't forget to save me" effect of markDirty, minus the block updates */
    public void markChanged() {
        if (this.level != null) this.level.setBlocksDirty(this.worldPosition, this.getBlockState(), this.getBlockState());
    }

    public AudioWrapper createAudioLoop() {
        return null;
    } //Vidarin: Remember to override this if you use rebootAudio!!

    public AudioWrapper rebootAudio(AudioWrapper wrapper) {
        wrapper.stopSound();
        AudioWrapper audio = createAudioLoop();
        if (audio != null) audio.startSound();
        return audio;
    }

    @Override
    public void loadAdditional(CompoundTag nbt, HolderLookup.Provider registries) {
        super.loadAdditional(nbt, registries);
        muffled = nbt.getBoolean("muffled");
        tilted = nbt.getBoolean("tilted");
        hasDataChanged = true;
    }

    @Override
    protected void saveAdditional(CompoundTag nbt, HolderLookup.Provider registries) {
        nbt.putBoolean("muffled", muffled);
        nbt.putBoolean("tilted", tilted);
        super.saveAdditional(nbt, registries);
    }

    public float getVolume(float baseVolume) {
        return muffled ? baseVolume * 0.1F : baseVolume;
    }

    public void setMuffled(boolean muffled) {
        this.muffled = muffled;
        dataChanged();
    }

    public void dataChanged() {
        hasDataChanged = true;
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
        CompoundTag tag = super.getUpdateTag(registries);
        ByteBuf scratch = Unpooled.buffer(64);
        serializeInitial(scratch);
        byte[] bytes = new byte[scratch.readableBytes()];
        scratch.readBytes(bytes);
        tag.putByteArray("hbmSync", bytes);
        return tag;
    }

    @Override
    public void handleUpdateTag(CompoundTag tag, HolderLookup.Provider registries) {
        super.handleUpdateTag(tag, registries);
        if (tag.contains("hbmSync")) {
            ByteBuf buf = Unpooled.wrappedBuffer(tag.getByteArray("hbmSync"));
            deserializeInitial(buf);
        }
    }

    @Override
    public void serialize(ByteBuf buf) {
        buf.writeBoolean(muffled);
        buf.writeBoolean(tilted);
    }

    @Override
    public void deserialize(ByteBuf buf) {
        muffled = buf.readBoolean();
        tilted = buf.readBoolean();
    }

    /** Payload emitted once per chunk-load sync via {@link #getUpdateTag(HolderLookup.Provider)}. */
    public void serializeInitial(ByteBuf buf) {
        serialize(buf);
    }

    /** Symmetric counterpart to {@link #serializeInitial(ByteBuf)}. */
    public void deserializeInitial(ByteBuf buf) {
        deserialize(buf);
    }

    /** Sends a sync packet that uses ByteBuf for efficient information-cramming */
    public void networkPackNT(int range) {
        if (this.level == null || this.level.isClientSide) return;
        BufPacket packet = buildBufPacket();
        // P8: FNV-1a 哈希去重（原 preHash == lastPackedBufHash 跳过重复包）
        long preHash = Library.fnv1a64(packet.data());
        if (preHash == lastPackedBufHash) return; // 数据未变化，跳过发送
        lastPackedBufHash = preHash;
        PacketThreading.createAllAroundThreadedPacket(packet, (ServerLevel) this.level,
                this.worldPosition.getX(), this.worldPosition.getY(), this.worldPosition.getZ(), range);
    }

    /** Sends a sync packet, skipping compilation entirely when data has not changed. */
    public void networkPackMK2(int range) {
        if (this.level == null || this.level.isClientSide) return;
        if (!hasDataChanged) return;
        BufPacket packet = buildBufPacket();
        PacketThreading.createAllAroundThreadedPacket(packet, (ServerLevel) this.level,
                this.worldPosition.getX(), this.worldPosition.getY(), this.worldPosition.getZ(), range);
        hasDataChanged = false;
    }

    private BufPacket buildBufPacket() {
        ByteBuf preBuf = Unpooled.buffer();
        this.serialize(preBuf);
        byte[] data = new byte[preBuf.readableBytes()];
        preBuf.readBytes(data);
        return new BufPacket(this.worldPosition.getX(), this.worldPosition.getY(), this.worldPosition.getZ(), data);
    }

    public enum TiltType {
        UNAVOIDABLE, CONFIG
    }

    public void checkTilt(TiltType cfg, boolean extraHeavy) {
        boolean doesTilt = false;
        if (cfg == TiltType.UNAVOIDABLE) doesTilt = true;
        if (cfg == TiltType.CONFIG && GeneralConfig.enableMachineGravity) doesTilt = true;
        if (cfg == TiltType.CONFIG && GeneralConfig.enable528MachineGravity) doesTilt = true;

        if (!doesTilt) { this.tilted = false; return; }
        if (this.getFloorCount() <= 0) { this.tilted = false; return; }
        if ((this.level == null) || ((this.level.getGameTime() + (this.worldPosition.getY() + this.worldPosition.getZ() * 27644437) * 27644437L + this.worldPosition.getX()) % 20 != 0)) return;

        if (this.tiltBlocksChecked >= this.getFloorCount()) {

            if (this.tiltBlocksValid >= this.tiltBlocksChecked * 0.95) {
                this.tilted = false;
            } else {
                if (!this.tilted && this.level != null) {
                    this.level.playSound(null, this.worldPosition.getX() + 0.5, this.worldPosition.getY() + 0.5, this.worldPosition.getZ() + 0.5,
                            ModSounds.METAL_IMPACT.get(), SoundSource.BLOCKS, 3F, 1F);
                }
                this.tilted = true;
            }

            this.markChanged();
            this.tiltBlocksChecked = 0;
            this.tiltBlocksValid = 0;
        }

        BlockPos floorPos = getFloorPosFromIndex(this.tiltBlocksChecked);
        if (floorPos == null || this.level == null) return;

        BlockState ground = this.level.getBlockState(floorPos);
        this.tiltBlocksChecked++;

        if (extraHeavy) {
            // TODO P8: 原材质系统检查（getMaterial/isNormalCube/Material.SAND|CLOTH|GROUND + 爆炸抗性对比 stone）
            if (!ground.isSolid()) return;
            if (ground.getExplosionResistance(this.level, floorPos, null) < Blocks.STONE.defaultBlockState().getExplosionResistance(this.level, floorPos, null)) return;
            this.tiltBlocksValid++;
        } else {
            if (!ground.isSolid()) return; // TODO P8: 原 isSideSolid(world, pos, UP)
            // TODO P3: ModBlocks.dirt_dead/oily/stone_cracked 缺失——恢复后加回排除判断
            this.tiltBlocksValid++;
        }
    }

    public int getFloorCount() { return 0; }
    public BlockPos getFloorPosFromIndex(int index) { return null; }

    public BlockPos standardFloor3x3(int index) {
        return new BlockPos(this.worldPosition.getX() - 1 + (index / 2) * 2, this.worldPosition.getY() - 1, this.worldPosition.getZ() - 1 + (index % 2) * 2);
    }
    public BlockPos standardFloor5x5(int index) {
        return new BlockPos(this.worldPosition.getX() - 2 + (index / 3) * 2, this.worldPosition.getY() - 1, this.worldPosition.getZ() - 2 + (index % 3) * 2);
    }
    public BlockPos standardFloor7x7(int index) {
        return new BlockPos(this.worldPosition.getX() - 3 + (index / 4) * 2, this.worldPosition.getY() - 1, this.worldPosition.getZ() - 3 + (index % 4) * 2);
    }
}
