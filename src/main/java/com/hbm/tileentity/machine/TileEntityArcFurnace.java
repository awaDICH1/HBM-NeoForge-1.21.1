package com.hbm.tileentity.machine;

import com.hbm.inventory.container.ContainerArcFurnace;
import com.hbm.inventory.recipes.ArcFurnaceRecipes;
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

public class TileEntityArcFurnace extends TileEntityMachineBase implements IPersistentNBT {

    public int progress = 0;
    public int maxProgress = 300;
    public int power = 0;
    public int maxPower = 20000;
    private static final int ENERGY_COST = 15;
    private boolean shouldDrop = true;

    public TileEntityArcFurnace(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state, 5, true, false);
    }

    public static TileEntityArcFurnace create(BlockPos pos, BlockState state) {
        return new TileEntityArcFurnace(ModTileEntities.ARC_FURNACE_TE.get(), pos, state);
    }

    public void tick() {
        if (this.level == null || this.level.isClientSide) return;

        chargeFromBattery(3);

        ItemStack input = inventory.getStackInSlot(0);
        ItemStack output = inventory.getStackInSlot(4);

        if (!input.isEmpty() && power >= ENERGY_COST) {
            ArcFurnaceRecipes.ArcFurnaceRecipe recipe = ArcFurnaceRecipes.getOutput(input);
            if (recipe != null && canFitOutput(recipe.output, output)) {
                progress++;
                power -= ENERGY_COST;
                if (progress >= maxProgress) {
                    finishProcessing(input, recipe.output);
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
            power = Math.min(maxPower, power + 100);
            battery.shrink(1);
        }
    }

    private boolean canFitOutput(ItemStack result, ItemStack existing) {
        if (existing.isEmpty()) return true;
        if (existing.getItem() != result.getItem()) return false;
        return existing.getCount() + result.getCount() <= existing.getMaxStackSize();
    }

    private void finishProcessing(ItemStack input, ItemStack result) {
        ItemStack output = inventory.getStackInSlot(4);
        if (output.isEmpty()) {
            inventory.setStackInSlot(4, result.copy());
        } else {
            output.grow(result.getCount());
        }
        input.shrink(1);
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
        return "container.arc_furnace";
    }

    public MenuProvider getMenuProvider() {
        return new SimpleMenuProvider((id, inv, player) -> new ContainerArcFurnace(id, inv, this),
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
