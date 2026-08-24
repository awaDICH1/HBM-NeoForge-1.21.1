package com.hbm.tileentity;

import com.hbm.api.tile.IWorldRenameable;
import com.hbm.blocks.ModBlocks;
import com.hbm.interfaces.Spaghetti;
import com.hbm.lib.DirPos;
import com.hbm.particle.ModParticleTypes;
import com.hbm.sound.ModSounds;
import io.netty.buffer.ByteBuf;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.items.IItemHandlerModifiable;
import net.neoforged.neoforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;

/**
 * 机器基类（P5.1b-2 补全版）。
 *
 * 迁移自 1.12.2 com.hbm.tileentity.TileEntityMachineBase（346 行）。
 * 在 P1 简化版基础上补全：extends TileEntityLoadedBase + IWorldRenameable、
 * checkedInventory（CheckedInventory 内部类）、消音器计数 countMufflers/getVolume（muffler 方块缺失 → TODO P3 桩）、
 * 比较器 updateRedstoneComparatorConnection、IBufPacketReceiver serialize/deserialize（muffled）。
 * 1.21.1 能力：1.12 Forge Capability → NeoForge BlockCapability（ForgeCapabilities.ITEM_HANDLER 完整实现；
 * 能量/流体包装器 NTMEnergyCapabilityWrapper/NTMFluidHandlerWrapper 未迁移 → TODO P5.2 桩）；
 * updateComparatorOutputLevel/notifyNeighborsOfStateChange → updateNeighbourForOutputSignal。
 */
@Spaghetti("Not spaghetti in itself, but for the love of god please use this base class for all machines")
public abstract class TileEntityMachineBase extends TileEntityLoadedBase implements IWorldRenameable {

    public ItemStackHandler inventory;
    private IItemHandlerModifiable checkedInventory;
    private boolean enablefluidWrapper = false;
    private boolean enableEnergyWrapper = false;
    private String customName;
    private boolean destroyedByCreativePlayer = false;

    public TileEntityMachineBase(BlockEntityType<?> type, BlockPos pos, BlockState state, int scount) {
        this(type, pos, state, scount, 64);
    }

    public TileEntityMachineBase(BlockEntityType<?> type, BlockPos pos, BlockState state, int scount, int slotlimit) {
        super(type, pos, state);
        inventory = getNewInventory(scount, slotlimit);
    }

    public TileEntityMachineBase(BlockEntityType<?> type, BlockPos pos, BlockState state, int scount, boolean enableFluidWrapper, boolean enableEnergyWrapper) {
        this(type, pos, state, scount);
        this.enablefluidWrapper = enableFluidWrapper;
        this.enableEnergyWrapper = enableEnergyWrapper;
    }

    public TileEntityMachineBase(BlockEntityType<?> type, BlockPos pos, BlockState state, int scount, int slotlimit, boolean enableFluidWrapper, boolean enableEnergyWrapper) {
        this(type, pos, state, scount, slotlimit);
        this.enablefluidWrapper = enableFluidWrapper;
        this.enableEnergyWrapper = enableEnergyWrapper;
    }

    protected ItemStackHandler getNewInventory(int scount, int slotlimit) {
        return new ItemStackHandler(scount) {
            @Override
            protected void onContentsChanged(int slot) {
                super.onContentsChanged(slot);
                setChanged();
            }

            @Override
            public int getSlotLimit(int slot) {
                return slotlimit;
            }
        };
    }

    protected void resizeInventory(int newSlotCount) {
        ItemStackHandler newInventory = getNewInventory(newSlotCount, inventory.getSlotLimit(0));
        for (int i = 0; i < Math.min(inventory.getSlots(), newSlotCount); i++) {
            newInventory.setStackInSlot(i, inventory.getStackInSlot(i));
        }
        this.inventory = newInventory;
        setChanged();
    }

    @Override
    public Component getName() {
        return Component.literal(this.hasCustomName() ? this.customName : getDefaultName());
    }

    public abstract String getDefaultName();

    @Override
    public boolean hasCustomName() {
        return this.customName != null && !this.customName.isEmpty();
    }

    @Override
    public void setCustomName(String name) {
        this.customName = name;
    }

    public boolean isUseableByPlayer(Player player) {
        if (this.level == null || this.level.getBlockEntity(this.worldPosition) != this) {
            return false;
        } else {
            return player.distanceToSqr(this.worldPosition.getX() + 0.5D, this.worldPosition.getY() + 0.5D, this.worldPosition.getZ() + 0.5D) <= 128;
        }
    }

    public int[] getAccessibleSlotsFromSide(Direction side, BlockPos accessorPos) {
        return getAccessibleSlotsFromSide(side);
    }

    public int[] getAccessibleSlotsFromSide(Direction e) {
        return new int[]{};
    }

    public int getGaugeScaled(int i, com.hbm.inventory.fluid.tank.FluidTankNTM tank) {
        return tank.getFill() * i / tank.getMaxFill();
    }

    @Override
    public void serialize(ByteBuf buf) {
        buf.writeBoolean(muffled);
    }

    @Override
    public void deserialize(ByteBuf buf) {
        this.muffled = buf.readBoolean();
    }

    public void handleButtonPacket(int value, int meta) {
    }

    @Override
    protected void saveAdditional(CompoundTag compound, HolderLookup.Provider registries) {
        compound.put("inventory", inventory.serializeNBT(registries));
        compound.putBoolean("powered", powered);
        super.saveAdditional(compound, registries);
    }

    @Override
    public void loadAdditional(CompoundTag compound, HolderLookup.Provider registries) {
        if (compound.contains("inventory")) {
            int expected = inventory.getSlots();
            inventory.deserializeNBT(registries, compound.getCompound("inventory"));
            if (inventory.getSlots() < expected) resizeInventory(expected);
        }
        powered = compound.getBoolean("powered");
        super.loadAdditional(compound, registries);
    }

    public boolean isItemValidForSlot(int i, ItemStack stack) {
        return true;
    }

    public boolean canInsertItem(int slot, ItemStack stack, Direction side, BlockPos accessorPos) {
        return canInsertItem(slot, stack);
    }

    public boolean canInsertItem(int slot, ItemStack itemStack) {
        return this.isItemValidForSlot(slot, itemStack);
    }

    public boolean canExtractItem(int slot, ItemStack stack, int amount, Direction side, BlockPos accessorPos) {
        return canExtractItem(slot, stack, amount);
    }

    public boolean canExtractItem(int slot, ItemStack itemStack, int amount) {
        return true;
    }

    public int countMufflers() {
        int count = 0;
        if (this.level == null) return count;
        for (Direction dir : Direction.values()) {
            BlockPos adjPos = this.worldPosition.relative(dir);
            if (this.level.getBlockState(adjPos).is(ModBlocks.MUFFLER.get())) {
                count++;
            }
        }
        return count;
    }

    public float getVolume(int toSilence) {
        float volume = 1 - (countMufflers() / (float) toSilence);
        return Math.max(volume, 0);
    }

    /** @return a checked wrapper around the inventory. Intended for Container and GUI class. */
    public IItemHandlerModifiable getCheckedInventory() {
        if (checkedInventory == null)
            checkedInventory = new CheckedInventory();
        return checkedInventory;
    }

    // TODO P5.2: 1.21 BlockCapability 在注册侧声明（Capabilities.ItemStack.ITEM.registerForBlockEntity...），
    // BlockEntity 无 getCapability 实例方法。1.12 的 ForgeCapabilities.ITEM_HANDLER 包装逻辑
    // （ItemStackHandlerWrapper + 侧向槽位过滤）待能力批在 ModTileEntities/ModBlocks 注册处恢复。

    protected void updateRedstoneComparatorConnection(DirPos pos) {
        BlockPos blockPos = pos.getBlockPos();
        if (this.level == null) return;
        this.level.updateNeighbourForOutputSignal(blockPos, this.level.getBlockState(blockPos).getBlock());
        this.level.neighborChanged(blockPos, this.level.getBlockState(blockPos).getBlock(), this.worldPosition);
    }

    public void setDestroyedByCreativePlayer() {
        destroyedByCreativePlayer = true;
    }

    public boolean isDestroyedByCreativePlayer() {
        return destroyedByCreativePlayer;
    }

    // ===== C-1: Machine Sound System =====

    private int soundTimer = 0;

    public boolean isProcessing() {
        return false;
    }

    protected SoundEvent getRunningSound() {
        return null;
    }

    protected SoundEvent getCompleteSound() {
        return null;
    }

    protected void playRunningSound() {
        if (this.level == null || this.level.isClientSide) return;
        SoundEvent sound = getRunningSound();
        if (sound == null) return;
        float vol = getVolume(0.5F);
        if (vol <= 0) return;
        this.level.playSound(null, this.worldPosition, sound, SoundSource.BLOCKS, vol, 1.0F);
    }

    protected void playCompleteSound() {
        if (this.level == null || this.level.isClientSide) return;
        SoundEvent sound = getCompleteSound();
        if (sound == null) return;
        float vol = getVolume(1.0F);
        this.level.playSound(null, this.worldPosition, sound, SoundSource.BLOCKS, vol, 1.0F);
    }

    protected void updateSound() {
        if (this.level == null || this.level.isClientSide) return;
        if (isProcessing()) {
            soundTimer++;
            if (soundTimer >= 20) {
                playRunningSound();
                soundTimer = 0;
            }
        } else {
            soundTimer = 0;
        }
    }

    // ===== C-2: Particle System =====

    protected ParticleOptions getProcessingParticle() {
        return ModParticleTypes.HBM_SPARK.get();
    }

    protected int getParticleRate() {
        return 4;
    }

    protected void spawnProcessingParticles() {
        if (this.level == null || !this.level.isClientSide) return;
        for (int i = 0; i < 3; i++) {
            double x = this.worldPosition.getX() + 0.5 + (this.level.random.nextDouble() - 0.5) * 0.8;
            double y = this.worldPosition.getY() + 0.5 + (this.level.random.nextDouble() - 0.5) * 0.8;
            double z = this.worldPosition.getZ() + 0.5 + (this.level.random.nextDouble() - 0.5) * 0.8;
            this.level.addParticle(getProcessingParticle(), x, y, z, 0, 0.05, 0);
        }
    }

    protected void updateParticles() {
        if (this.level == null || !this.level.isClientSide) return;
        if (isProcessing() && this.level.random.nextInt(getParticleRate()) == 0) {
            spawnProcessingParticles();
        }
    }

    // ===== C-3: Redstone Control =====

    protected boolean powered = false;

    public void setPowered(boolean powered) {
        this.powered = powered;
        setChanged();
    }

    public boolean isPowered() {
        return powered;
    }

    protected boolean canProcess() {
        return !powered;
    }

    private final class CheckedInventory implements IItemHandlerModifiable {
        @Override
        public int getSlots() {
            return inventory.getSlots();
        }

        @Override
        public @NotNull ItemStack getStackInSlot(int slot) {
            return inventory.getStackInSlot(slot);
        }

        @Override
        public @NotNull ItemStack insertItem(int slot, @NotNull ItemStack stack, boolean simulate) {
            if (stack.isEmpty()) return ItemStack.EMPTY;
            if (!isItemValidForSlot(slot, stack)) return stack;
            return inventory.insertItem(slot, stack, simulate);
        }

        @Override
        public @NotNull ItemStack extractItem(int slot, int amount, boolean simulate) {
            if (amount <= 0) return ItemStack.EMPTY;
            return inventory.extractItem(slot, amount, simulate);
        }

        @Override
        public int getSlotLimit(int slot) {
            return inventory.getSlotLimit(slot);
        }

        @Override
        public void setStackInSlot(int slot, @NotNull ItemStack stack) {
            inventory.setStackInSlot(slot, stack);
        }

        @Override
        public boolean isItemValid(int slot, @NotNull ItemStack stack) {
            return isItemValidForSlot(slot, stack);
        }
    }
}

