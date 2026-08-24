package com.hbm.util;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

/**
 * 迁移自 1.12.2 com.hbm.util.CompatBlockReplacer。
 * 变更：
 *   - BlockState → BlockState
 *   - missingBlock.getBlock().getRegistryName() → BuiltInRegistries.BLOCK.getKey(...)（1.21.1 无 getRegistryName）
 *   - getStateFromMeta/getMetaFromState 已删除（1.13+ 无 metadata）→ defaultBlockState()
 *   - Blocks.HARDENED_CLAY → Blocks.TERRACOTTA（1.13 改名）
 */
public class CompatBlockReplacer {

    /**
     * The blocks to be replaced.
     * <p>Meta will be automatically copied. (you cannot set it manually)
     * <p>Keep in mind that if SpecialReplacer for the same block exists, that is prioritized instead.
     */
    public static final Map<String, Block> replacementMap = new HashMap<>();
    /**
     * Replacement function for more customizable replacement.
     */
    public static final Map<String, BiFunction<String, BlockState, BlockState>> specialReplacer = new HashMap<>();

    static {
        replacementMap.put("hbm:waste_ice", Blocks.ICE);
        replacementMap.put("hbm:waste_snow", Blocks.SNOW);   // 1.21 改名：雪层 SNOW_LAYER → SNOW
        replacementMap.put("hbm:waste_snow_block", Blocks.SNOW_BLOCK);   // 1.21 改名：雪块 SNOW → SNOW_BLOCK
        replacementMap.put("hbm:waste_dirt", Blocks.DIRT);
        replacementMap.put("hbm:waste_gravel", Blocks.GRAVEL);
        replacementMap.put("hbm:waste_sand", Blocks.SAND);
        replacementMap.put("hbm:waste_sandstone", Blocks.SANDSTONE);
        replacementMap.put("hbm:waste_sand_red", Blocks.SAND);
        replacementMap.put("hbm:waste_red_sandstone", Blocks.RED_SANDSTONE);
        replacementMap.put("hbm:waste_terracotta", Blocks.TERRACOTTA);   // 原 HARDENED_CLAY
    }

    public static @NotNull BlockState replaceBlock(BlockState missingBlock) {
        if (missingBlock == null) return Blocks.AIR.defaultBlockState();
        try {
            // 原 getBlock().getRegistryName()；1.21.1 用注册表反查
            String name = BuiltInRegistries.BLOCK.getKey(missingBlock.getBlock()).toString();
            if (specialReplacer.containsKey(name)) {
                BiFunction<String, BlockState, BlockState> processor = specialReplacer.get(name);
                return processor.apply(name, missingBlock);
            } else {
                Block newBlock = replacementMap.get(name);
                if (newBlock == null) return missingBlock;
                // 原 getStateFromMeta(getMetaFromState(...))；1.13+ 无 metadata，直接用默认状态
                return newBlock.defaultBlockState();
            }
        } catch (RuntimeException ex) {
            return missingBlock;
        }
    }
}
