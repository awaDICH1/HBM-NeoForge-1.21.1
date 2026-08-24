package com.hbm.hazard.transformer;

import com.hbm.hazard.HazardEntry;
import net.minecraft.world.item.ItemStack;

import java.util.List;

/**
 * 迁移自 1.12.2 com.hbm.hazard.transformer.IHazardTransformer（纯接口）。
 */
public interface IHazardTransformer {

    void transformPre(ItemStack stack, List<HazardEntry> entries);

    void transformPost(ItemStack stack, List<HazardEntry> entries);
}
