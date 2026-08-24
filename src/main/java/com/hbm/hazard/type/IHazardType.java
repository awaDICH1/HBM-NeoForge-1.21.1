package com.hbm.hazard.type;

import com.hbm.config.RadiationConfig;
import com.hbm.hazard.modifier.IHazardModifier;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

import java.util.List;

/**
 * 迁移自 1.12.2 com.hbm.hazard.type.IHazardType。
 * EntityLivingBase→LivingEntity、EntityItem→ItemEntity、EntityPlayer→Player、@SideOnly→@OnlyIn。
 */
public interface IHazardType {

    int hazardRate = RadiationConfig.hazardRate;

    /**
     * Does the thing. Called by HazardEntry.applyHazard
     *
     * @param target the holder
     * @param level  the final level after calculating all the modifiers
     */
    void onUpdate(LivingEntity target, double level, ItemStack stack);

    /**
     * Updates the hazard for dropped items. Used for things like explosive and hydroactive items.
     *
     * @param item
     * @param level
     */
    void updateEntity(ItemEntity item, double level);

    /**
     * Adds item tooltip info. Called by Item.addInformation
     *
     * @param player
     * @param list
     * @param level     the base level, mods are passed separately
     * @param stack
     * @param modifiers
     */
    @OnlyIn(Dist.CLIENT)
    void addHazardInformation(Player player, List<String> list, double level, ItemStack stack, List<IHazardModifier> modifiers);

    @FunctionalInterface
    interface HazardInfoConsumer {
        void accept(Player player, List<String> list, double level, ItemStack stack, List<IHazardModifier> modifiers);
    }
}
