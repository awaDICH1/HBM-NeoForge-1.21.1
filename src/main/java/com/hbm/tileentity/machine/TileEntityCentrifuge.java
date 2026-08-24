package com.hbm.tileentity.machine;

import com.hbm.inventory.container.ContainerCentrifuge;
import com.hbm.items.ModItems;
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

import java.util.HashMap;
import java.util.Map;

public class TileEntityCentrifuge extends TileEntityMachineBase implements IPersistentNBT {

    public int progress = 0;
    public int maxProgress = 500;
    public int power = 0;
    public int maxPower = 8000;
    private static final int ENERGY_COST = 10;
    private boolean shouldDrop = true;

    private static final Map<net.minecraft.world.item.Item, ItemStack[]> CENTRIFUGE_RECIPES = new HashMap<>();

    static {
        CENTRIFUGE_RECIPES.put(ModItems.INGOT_URANIUM.get(),
                new ItemStack[]{new ItemStack(ModItems.INGOT_U235.get()), new ItemStack(ModItems.INGOT_U238.get())});
        CENTRIFUGE_RECIPES.put(ModItems.INGOT_PLUTONIUM.get(),
                new ItemStack[]{new ItemStack(ModItems.INGOT_PU239.get()), new ItemStack(ModItems.INGOT_PU240.get())});
        CENTRIFUGE_RECIPES.put(ModItems.INGOT_TH232.get(),
                new ItemStack[]{new ItemStack(ModItems.INGOT_U233.get()), new ItemStack(ModItems.INGOT_TH232.get())});
    }

    public TileEntityCentrifuge(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state, 4, true, false);
    }

    public static TileEntityCentrifuge create(BlockPos pos, BlockState state) {
        return new TileEntityCentrifuge(ModTileEntities.CENTRIFUGE_TE.get(), pos, state);
    }

    public void tick() {
        if (this.level == null || this.level.isClientSide) return;

        chargeFromBattery(3);

        ItemStack input = inventory.getStackInSlot(0);
        ItemStack output1 = inventory.getStackInSlot(1);
        ItemStack output2 = inventory.getStackInSlot(2);

        if (!input.isEmpty() && power >= ENERGY_COST) {
            ItemStack[] result = CENTRIFUGE_RECIPES.get(input.getItem());
            if (result != null && canFitOutput(result[0], output1) && canFitOutput(result[1], output2)) {
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
            power = Math.min(maxPower, power + 80);
            battery.shrink(1);
        }
    }

    private boolean canFitOutput(ItemStack result, ItemStack existing) {
        if (existing.isEmpty()) return true;
        if (existing.getItem() != result.getItem()) return false;
        return existing.getCount() + result.getCount() <= existing.getMaxStackSize();
    }

    private void finishProcessing(ItemStack input, ItemStack[] result) {
        input.shrink(1);
        ItemStack out1 = inventory.getStackInSlot(1);
        if (out1.isEmpty()) {
            inventory.setStackInSlot(1, result[0].copy());
        } else {
            out1.grow(result[0].getCount());
        }
        ItemStack out2 = inventory.getStackInSlot(2);
        if (out2.isEmpty()) {
            inventory.setStackInSlot(2, result[1].copy());
        } else {
            out2.grow(result[1].getCount());
        }
    }

    @Override
    public void serialize(ByteBuf buf) {
        super.serialize(buf);
        buf.writeInt(progress);
        buf.writeInt(maxProgress);
        buf.writeInt(power);
        buf.writeInt(maxPower);
    }

    @Override
    public void deserialize(ByteBuf buf) {
        super.deserialize(buf);
        progress = buf.readInt();
        maxProgress = buf.readInt();
        power = buf.readInt();
        maxPower = buf.readInt();
    }

    @Override
    public String getDefaultName() {
        return "container.centrifuge";
    }

    public MenuProvider getMenuProvider() {
        return new SimpleMenuProvider((id, inv, player) -> new ContainerCentrifuge(id, inv, this),
                Component.literal(getDefaultName()));
    }

    @Override
    public boolean shouldDrop() {
        return IPersistentNBT.super.shouldDrop() && shouldDrop;
    }

    @Override
    public void setDestroyedByCreativePlayer() {
        shouldDrop = false;
    }

    @Override
    public boolean isDestroyedByCreativePlayer() {
        return !shouldDrop;
    }

    @Override
    public void writeNBT(CompoundTag nbt) {
        nbt.putInt("progress", progress);
        nbt.putInt("maxProgress", maxProgress);
        nbt.putInt("power", power);
        nbt.putInt("maxPower", maxPower);
    }

    @Override
    public void readNBT(CompoundTag nbt) {
        progress = nbt.getInt("progress");
        maxProgress = nbt.getInt("maxProgress");
        power = nbt.getInt("power");
        maxPower = nbt.getInt("maxPower");
    }
}
