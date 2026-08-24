package com.hbm.tileentity.machine;

import com.hbm.inventory.container.ContainerChemicalReactor;
import com.hbm.inventory.recipes.AssemblyMachineRecipes;
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

public class TileEntityChemicalReactor extends TileEntityMachineBase implements IPersistentNBT {

    public int progress = 0;
    public int maxProgress = 200;
    public int power = 0;
    public int maxPower = 10000;
    private static final int ENERGY_COST = 8;
    private boolean shouldDrop = true;

    public TileEntityChemicalReactor(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state, 6, true, false);
    }

    public static TileEntityChemicalReactor create(BlockPos pos, BlockState state) {
        return new TileEntityChemicalReactor(ModTileEntities.CHEMICAL_REACTOR_TE.get(), pos, state);
    }

    public void tick() {
        if (this.level == null || this.level.isClientSide) return;

        chargeFromBattery(4);

        ItemStack[] inputs = new ItemStack[4];
        for (int i = 0; i < 4; i++) {
            inputs[i] = inventory.getStackInSlot(i);
        }
        ItemStack output = inventory.getStackInSlot(5);

        boolean hasInput = false;
        for (ItemStack s : inputs) {
            if (!s.isEmpty()) { hasInput = true; break; }
        }

        if (hasInput && power >= ENERGY_COST) {
            AssemblyMachineRecipes.AssemblyRecipe recipe = AssemblyMachineRecipes.getOutput(inputs);
            if (recipe != null && canFitOutput(recipe.output, output)) {
                progress++;
                power -= ENERGY_COST;
                if (progress >= maxProgress) {
                    finishProcessing(inputs, recipe.output);
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

    private void finishProcessing(ItemStack[] inputs, ItemStack result) {
        for (int i = 0; i < 4; i++) {
            if (!inputs[i].isEmpty()) {
                inputs[i].shrink(1);
            }
        }
        ItemStack output = inventory.getStackInSlot(5);
        if (output.isEmpty()) {
            inventory.setStackInSlot(5, result.copy());
        } else {
            output.grow(result.getCount());
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
        return "container.chemical_reactor";
    }

    public MenuProvider getMenuProvider() {
        return new SimpleMenuProvider((id, inv, player) -> new ContainerChemicalReactor(id, inv, this),
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
