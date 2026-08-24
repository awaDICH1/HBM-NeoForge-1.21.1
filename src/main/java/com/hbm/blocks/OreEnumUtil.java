package com.hbm.blocks;

import com.hbm.lib.TriFunction;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Random;
import java.util.function.BiFunction;

/**
 * 矿方块掉落工具（P4.1 批次B 骨架版）。
 *
 * 迁移自 1.12.2 com.hbm.blocks.OreEnumUtil（111 行）。
 * 当前保留：纯数量函数（base2Rand3Fortune 等 10 个）+ OreEnum 全部 29 个枚举值（结构占位）。
 * 桩化（TODO P5）：drop 函数（getMeteorTreasure/phosphorusNetherDrop/blockMeteorDrop——依赖
 * ItemPool/ItemPoolsSingle/ModBlocks.block_meteor）；OreEnum 的 drop/quantity 函数置 null
 * （依赖 ModItems 全量物品字段：lignite/chunk_ore/gem_rad/trinitite/nugget_zirconium/
 * powder_nitan_mix/oil_tar/ingot_phosphorus/powder_fire 等 9 个未迁移）。P3 物品全量 + P5 方块批后恢复。
 */
public class OreEnumUtil {

    public static int base2Rand3Fortune(BlockState state, int fortune, Random rand) { return 2 + rand.nextInt(3) + fortune; }
    public static int base2Rand2Fortune(BlockState state, int fortune, Random rand) { return 2 + rand.nextInt(2) + fortune; }
    public static int base1Rand2Fortune(BlockState state, int fortune, Random rand) { return 1 + rand.nextInt(2) + fortune; }
    public static int base1Rand3(BlockState state, int fortune, Random rand) { return 1 + rand.nextInt(3); }
    public static int const1(BlockState state, int fortune, Random rand) { return 1; }
    public static int vanillaFortune(BlockState state, int fortune, Random rand) { return 1 + applyFortune(rand, fortune); }
    public static int cobaltAmount(BlockState state, int fortune, Random rand) { return 4 + rand.nextInt(6); }
    public static int alexandriteAmount(BlockState state, int fortune, Random rand) { return Math.min(1 + rand.nextInt(2) + fortune, 2); }
    public static int cobaltNetherAmount(BlockState state, int fortune, Random rand) { return 5 + rand.nextInt(8); }
    public static int applyFortune(Random rand, int fortune) { return fortune <= 0 ? 0 : rand.nextInt(fortune); }

    // TODO P5: getMeteorTreasure / phosphorusNetherDrop / blockMeteorDrop（依赖 ItemPool/ModItems/ModBlocks）
    // public static ItemStack getMeteorTreasure(BlockState state, Random rand) { ... }
    // public static ItemStack phosphorusNetherDrop(BlockState state, Random rand) { ... }
    // public static ItemStack blockMeteorDrop(BlockState state, Random rand) { ... }

    // --- OreEnum ---

    public enum OreEnum implements IOreType {

        COAL,
        DIAMOND,
        EMERALD,

        ASBESTOS,
        SULFUR,
        NITER,
        FLUORITE,
        METEORITE_FRAG,
        METEORITE_TREASURE,
        COBALT,
        COBALT_NETHER,
        PHOSPHORUS_NETHER,
        LIGNITE,
        RARE_EARTHS,
        BLOCK_METEOR,
        CINNABAR,
        ALEXANDRITE,
        COLTAN,
        RAD_GEM,
        WASTE_TRINITE,
        ZIRCON,
        NEODYMIUM,
        NITAN,
        OIL,

        CLUSTER_IRON,
        CLUSTER_TITANIUM,
        CLUSTER_ALUMINIUM,
        CLUSTER_COPPER,
        CLUSTER_TUNGSTEN,
        ;

        public final BiFunction<BlockState, Random, ItemStack> dropFunction;
        public final TriFunction<BlockState, Integer, Random, Integer> quantityFunction;

        // TODO P5: 恢复 CE 原版双构造器（Supplier<ItemStack>/BiFunction + TriFunction，引用 ModItems 全量字段）
        OreEnum() {
            this.dropFunction = null;
            this.quantityFunction = null;
        }

        @Override
        public BiFunction<BlockState, Random, ItemStack> getDropFunction() { return this.dropFunction; }

        @Override
        public TriFunction<BlockState, Integer, Random, Integer> getQuantityFunction() { return this.quantityFunction; }
    }


}
