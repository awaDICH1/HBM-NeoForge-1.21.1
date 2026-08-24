package com.hbm.tileentity.machine;

import com.hbm.inventory.container.ContainerCrusher;
import com.hbm.inventory.recipes.CrusherRecipes;
import com.hbm.tileentity.IPersistentNBT;
import com.hbm.tileentity.ModTileEntities;
import com.hbm.tileentity.TileEntityMachineBase;
import io.netty.buffer.ByteBuf;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class TileEntityCrusher extends TileEntityMachineBase implements IPersistentNBT {

    public int progress = 0;
    public int maxProgress = 80;
    public int power = 0;
    public int maxPower = 3000;
    private static final int ENERGY_COST = 3;
    private boolean shouldDrop = true;

    public TileEntityCrusher(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state, 3, true, false);
    }

    public static TileEntityCrusher create(BlockPos pos, BlockState state) {
        return new TileEntityCrusher(ModTileEntities.CRUSHER_TE.get(), pos, state);
    }

    public void tick() {
        if (this.level == null || this.level.isClientSide) return;

        chargeFromBattery(2);

        ItemStack input = inventory.getStackInSlot(0);
        ItemStack output = inventory.getStackInSlot(1);

        if (!input.isEmpty() && power >= ENERGY_COST) {
            ItemStack result = CrusherRecipes.getOutput(input);
            if (!result.isEmpty() && canFitOutput(result, output)) {
                progress++;
                power -= ENERGY_COST;
                if (progress >= maxProgress) {
                    finishProcessing(input, result);
                    progress = 0;
                }
            } else {
                progress = 0;
            }
        } else {
            progress = 0;
        }

        this.networkPackNT(50);
    }

    private void chargeFromBattery(int slot) {
        ItemStack battery = inventory.getStackInSlot(slot);
        if (!battery.isEmpty() && battery.is(Items.REDSTONE) && power < maxPower) {
            power = Math.min(maxPower, power + 50);
            battery.shrink(1);
        }
    }

    private boolean canFitOutput(ItemStack result, ItemStack existing) {
        if (existing.isEmpty()) return true;
        if (existing.getItem() != result.getItem()) return false;
        return existing.getCount() + result.getCount() <= existing.getMaxStackSize();
    }

    private void finishProcessing(ItemStack input, ItemStack result) {
        ItemStack output = inventory.getStackInSlot(1);
        if (output.isEmpty()) {
            inventory.setStackInSlot(1, result.copy());
        } else {
            output.grow(result.getCount());
        }
        input.shrink(1);
    }

    @Override
    public void serialize(ByteBuf buf) {
        super.serialize(buf);
        buf.writeInt(progress);
        buf.writeInt(power);
        buf.writeInt(maxPower);
    }

    @Override
    public void deserialize(ByteBuf buf) {
        super.deserialize(buf);
        progress = buf.readInt();
        power = buf.readInt();
        maxPower = buf.readInt();
    }

    @Override
    public String getDefaultName() {
        return "container.crusher";
    }

    public MenuProvider getMenuProvider() {
        return new SimpleMenuProvider((id, inv, player) -> new ContainerCrusher(id, inv, this),
                Component.literal(getDefaultName()));
    }

    @Override
    public boolean shouldDrop() { return IPersistentNBT.super.shouldDrop() && shouldDrop; }

    @Override
    public void setDestroyedByCreativePlayer() { shouldDrop = false; }

    @Override
    public boolean isDestroyedByCreativePlayer() { return !shouldDrop; }

    @Override
    public void writeNBT(CompoundTag nbt) {
        nbt.putInt("progress", progress);
        nbt.putInt("power", power);
        nbt.putInt("maxPower", maxPower);
    }

    @Override
    public void readNBT(CompoundTag nbt) {
        progress = nbt.getInt("progress");
        power = nbt.getInt("power");
        maxPower = nbt.getInt("maxPower");
    }
}
