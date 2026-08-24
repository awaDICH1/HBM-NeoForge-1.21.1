package com.hbm.util;

import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.ItemEnchantments;

/**
 * 迁移自 1.12.2 com.hbm.util.EnchantmentUtil。
 * 重构（1.20.5+ 附魔数据化）：
 *   - stack.addEnchantment(Enchantment, int) → stack.enchant(Holder<Enchantment>, int)
 *   - stack.getEnchantmentTagList()（NBT "ench"）→ stack.getEnchantments()（ItemEnchantments 组件）
 *   - 1.21.1 Enchantment 无 builtInRegistryHolder()（数据驱动注册表）→ 方法签名直接收 Holder<Enchantment>
 *   - 玩家经验字段：experience/experienceTotal → experienceProgress/totalExperience；xpBarCap() → getXpNeededForNextLevel()
 */
public class EnchantmentUtil {

    public static void addEnchantment(ItemStack stack, Holder<Enchantment> enchantment, int level) {
        stack.enchant(enchantment, level);
    }

    public static void removeEnchantment(ItemStack stack, Holder<Enchantment> enchantment) {
        ItemEnchantments enchantments = stack.getEnchantments();
        if (enchantments.isEmpty()) {
            return;
        }
        ItemEnchantments.Mutable mutable = new ItemEnchantments.Mutable(enchantments);
        mutable.removeIf(holder -> holder.is(enchantment));
        stack.set(DataComponents.ENCHANTMENTS, mutable.toImmutable());
    }

    /**
     * Removes an amount of experience from a player and updates their level
     *
     * @param player the player to remove experience from
     * @param amount the amount of experience to remove
     */
    public static void removeExperience(Player player, float amount) {
        if (player.totalExperience < amount) {
            player.experienceLevel = 0;
            player.experienceProgress = 0;
            player.totalExperience = 0;
            return;
        }

        player.totalExperience -= (int) amount;
        if (player.experienceProgress * (float) player.getXpNeededForNextLevel() < amount) {
            amount -= player.experienceProgress * (float) player.getXpNeededForNextLevel();
            player.experienceProgress = 1.0F;
            player.experienceLevel--;
        }

        while (player.getXpNeededForNextLevel() < amount) {
            amount -= player.getXpNeededForNextLevel();
            player.experienceLevel--;
        }
        player.experienceProgress -= amount / (float) player.getXpNeededForNextLevel();
    }

    public static int getEnchantmentLevel(ItemStack stack, Holder<Enchantment> enchantment) {
        if (stack.isEmpty() || enchantment == null) {
            return 0;
        }
        return stack.getEnchantments().getLevel(enchantment);
    }
}
