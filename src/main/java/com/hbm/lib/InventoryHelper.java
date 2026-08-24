package com.hbm.lib;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.items.IItemHandler;

import java.util.Random;
import java.util.stream.IntStream;

/**
 * 迁移自 1.12.2 com.hbm.lib.InventoryHelper。
 * 重构（1.21.1 能力系统）：
 *   - ICapabilityProvider + CapabilityItemHandler.ITEM_HANDLER_CAPABILITY
 *     → Level.getCapability(Capabilities.ItemHandler.BLOCK, pos, direction)
 *   - EntityItem → ItemEntity；worldIn.spawnEntity → level.addFreshEntity
 *   - entityitem.motionX/Y/Z → setDeltaMovement；stack.splitStack → stack.split
 */
public class InventoryHelper {

    public static final Random RANDOM = new Random();

    /** 原 (World, BlockPos, ICapabilityProvider) 版本 → BlockEntity + BlockCapability 查询 */
    public static void dropInventoryItems(Level world, BlockPos pos, BlockEntity te) {
        if (te == null) return;
        IItemHandler inventory = world.getCapability(Capabilities.ItemHandler.BLOCK, pos, null);
        if (inventory == null) return;
        for (int i = 0; i < inventory.getSlots(); ++i) {
            ItemStack itemstack = inventory.getStackInSlot(i);
            if (!itemstack.isEmpty()) {
                spawnItemStack(world, pos.getX(), pos.getY(), pos.getZ(), itemstack);
            }
        }
    }

    public static void dropInventoryItems(Level world, BlockPos pos, IItemHandler inventory) {
        IntStream.range(0, inventory.getSlots()).mapToObj(inventory::getStackInSlot).filter(itemstack ->
                !itemstack.isEmpty()).forEach(itemstack -> spawnItemStack(world, pos.getX(), pos.getY(), pos.getZ(), itemstack));
    }

    public static void dropInventoryItems(Level world, BlockPos pos, BlockEntity te, int beginSlot, int endSlot) {
        if (te == null) return;
        IItemHandler inventory = world.getCapability(Capabilities.ItemHandler.BLOCK, pos, null);
        if (inventory == null) return;
        for (int i = beginSlot; i <= endSlot; ++i) {
            ItemStack itemstack = inventory.getStackInSlot(i);
            if (!itemstack.isEmpty()) {
                spawnItemStack(world, pos.getX(), pos.getY(), pos.getZ(), itemstack);
            }
        }
    }

    /**
     * DO NOT ADD 0.5 to x, y, z if you are using this with a BlockPos!
     */
    public static void spawnItemStack(Level worldIn, double x, double y, double z, ItemStack stack) {
        float xOffset = RANDOM.nextFloat() * 0.8F + 0.1F;
        float yOffset = RANDOM.nextFloat() * 0.8F + 0.1F;
        float zOffset = RANDOM.nextFloat() * 0.8F + 0.1F;

        while (!stack.isEmpty()) {
            ItemEntity entityitem = new ItemEntity(worldIn, x + (double) xOffset, y + (double) yOffset, z + (double) zOffset, stack.split(RANDOM.nextInt(21) + 10));
            entityitem.setDeltaMovement(RANDOM.nextGaussian() * 0.05F, RANDOM.nextGaussian() * 0.05F + 0.2F, RANDOM.nextGaussian() * 0.05F);
            worldIn.addFreshEntity(entityitem);
        }
    }
}
