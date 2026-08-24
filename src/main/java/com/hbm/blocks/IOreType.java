package com.hbm.blocks;

import com.hbm.lib.TriFunction;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.item.ItemStack;

import java.util.Random;
import java.util.function.BiFunction;

public interface IOreType {
    BiFunction<BlockState, Random, ItemStack> getDropFunction();
    TriFunction<BlockState, Integer, Random, Integer> getQuantityFunction();
}
