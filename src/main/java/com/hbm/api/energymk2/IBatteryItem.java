package com.hbm.api.energymk2;

import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;

/**
 * Do not use intanceof checks on this interface!
 *
 * @see Library#isBattery
 */
public interface IBatteryItem {

	/**
	 * Adds energy to the battery item.
	 * The implementation should ensure the charge does not exceed {@link #getMaxCharge(ItemStack)}.
	 *
	 * @param stack The ItemStack to charge.
	 * @param i     The amount of energy in HE (HBM Energy) to add.
	 */
	void chargeBattery(ItemStack stack, long i);

	/**
	 * Sets the energy level of the battery item to a specific value.
	 * The implementation should ensure the charge does not exceed {@link #getMaxCharge(ItemStack)}.
	 *
	 * @param stack The ItemStack to modify.
	 * @param i     The absolute amount of energy in HE to set.
	 */
	void setCharge(ItemStack stack, long i);

	/**
	 * Removes energy from the battery item.
	 * The implementation should ensure the charge does not fall below 0.
	 *
	 * @param stack The ItemStack to discharge.
	 * @param i     The amount of energy in HE to remove.
	 */
	void dischargeBattery(ItemStack stack, long i);

	/**
	 * Gets the current amount of stored energy in the item.
	 *
	 * @param stack The ItemStack to query.
	 * @return The current charge in HE.
	 */
	long getCharge(ItemStack stack);

	/**
	 * Gets the maximum amount of energy this item can store.
	 *
	 * @param stack The ItemStack to query.
	 * @return The maximum charge (capacity) in HE.
	 */
	long getMaxCharge(ItemStack stack);

	/**
	 * Gets the maximum rate at which this item can receive energy.
	 * This value is an intrinsic property of the battery type and does not depend
	 * on the item's current charge level.
	 *
	 * @return The maximum charge rate in HE per tick.
	 */
	long getChargeRate(ItemStack stack);

	/**
	 * Gets the maximum rate at which this item can provide energy.
	 * This value is an intrinsic property of the battery type and does not depend
	 * on the item's current charge level. The actual amount of energy that can be
	 * extracted per tick is also limited by the current charge.
	 *
	 * @return The maximum discharge rate in HE per tick.
	 */
	long getDischargeRate(ItemStack stack);

	/**
	 * Returns the NBT tag key used for storing the charge value.
	 * This allows for centralized management of the NBT data.
	 * Defaults to "charge".
	 *
	 * @return A string for the NBT tag name of the long storing power.
	 */
	default String getChargeTagName() {
		return "charge";
	}

	/**
	 * A static helper to get the NBT tag key for a given battery ItemStack.
	 *
	 * @param stack The battery ItemStack.
	 * @return The NBT tag name for the charge value.
	 */
	static String getChargeTagName(ItemStack stack) {
		return ((IBatteryItem) stack.getItem()).getChargeTagName();
	}

	/**
	 * Creates a copy of the given ItemStack with its charge set to 0.
	 * The original ItemStack is not modified.
	 *
	 * @param stack The ItemStack to create an empty version of.
	 * @return A new, empty battery ItemStack, or {@code null} if the input is not a valid {@link IBatteryItem}.
	 */
	static ItemStack emptyBattery(ItemStack stack) {
		if(stack != null && stack.getItem() instanceof IBatteryItem) {
			String keyName = getChargeTagName(stack);
			ItemStack stackOut = stack.copy();
			CompoundTag tag;
			if (stack.has(DataComponents.CUSTOM_DATA))
				tag = stack.get(DataComponents.CUSTOM_DATA).copyTag();
			else
				tag = new CompoundTag();
			tag.putLong(keyName, 0);
			stackOut.set(DataComponents.CUSTOM_DATA, CustomData.of(tag));
			return stackOut.copy();
		}
		return null;
	}

	/**
	 * Creates a new, empty ItemStack from the given Item.
	 *
	 * @param item The Item to create an empty battery from.
	 * @return A new, empty battery ItemStack, or {@code null} if the input is not a valid {@link IBatteryItem}.
	 */
	static ItemStack emptyBattery(Item item) {
		return item instanceof IBatteryItem ? emptyBattery(new ItemStack(item)) : null;
	}
}