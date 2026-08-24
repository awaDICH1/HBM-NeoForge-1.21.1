package com.hbm.util;

import net.minecraft.world.item.ItemStack;

/**
 * 物品堆叠工具（P4.1 批次C 骨架版）。
 *
 * 迁移自 1.12.2 com.hbm.util.ItemStackUtil（313 行）。
 * 当前仅含 SerializableRecipe 所需的 addNBTFromString 桩；
 * 完整版（getOreDictNames/addStacksToNBT/Forge IItemHandler 工具等）待 P4.2 OreDictManager + P5。
 */
public class ItemStackUtil {

    public static void addNBTFromString(ItemStack stack, String nbt) {
        // TODO P5: 1.21 JSON→NBT 解析（原 1.12 JsonToNBT.getTagFromJson + NBTException）；暂忽略配方文件中的 NBT 输入
    }

    // TODO P5: 完整 ItemStackUtil
}
