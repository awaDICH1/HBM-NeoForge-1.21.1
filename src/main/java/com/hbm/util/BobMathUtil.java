package com.hbm.util;

/**
 * 数学工具（P4.1 批次A 骨架版）。
 *
 * 迁移自 1.12.2 com.hbm.util.BobMathUtil（561 行）。
 * 当前仅含流体特质（FT_Combustible/FT_Flammable/FT_Rocket）所需的 getShortNumber；
 * 完整版（客户端渲染数学：GL11/Matrix4f/Quat4f/ActiveRenderInfo 等）待 P8 客户端系统迁移。
 */
public class BobMathUtil {

    public static String getShortNumber(long number) {
        if (number < 1000) {
            return String.valueOf(number);
        } else if (number < 1_000_000) {
            return String.format("%.2fk", number / 1000.0);
        } else if (number < 1_000_000_000) {
            return String.format("%.2fM", number / 1_000_000.0);
        } else if (number < 1_000_000_000_000L) {
            return String.format("%.2fG", number / 1_000_000_000.0);
        } else if (number < 1_000_000_000_000_000L) {
            return String.format("%.2fT", number / 1_000_000_000_000.0);
        } else if (number < 1_000_000_000_000_000_000L) {
            return String.format("%.2fE", number / 1_000_000_000_000_000.0);
        } else {
            return "INFINITE";
        }
    }

    // TODO P8: 完整 BobMathUtil（getBlend/getClosestAxis/旋转矩阵/GL 渲染辅助等）

    /* ===== P5.1a 补充：纯 Java 集合工具（CE 原实现） ===== */
    public static int[] intCollectionToArray(java.util.Collection<Integer> in) {
        return intCollectionToArray(in, i -> (int) i);
    }

    public static int[] intCollectionToArray(java.util.Collection<Integer> in, java.util.function.ToIntFunction<? super Object> mapper) {
        return java.util.Arrays.stream(in.toArray()).mapToInt(mapper).toArray();
    }

    public static int[] collectionToIntArray(java.util.Collection<? extends Object> in, java.util.function.ToIntFunction<? super Object> mapper) {
        return java.util.Arrays.stream(in.toArray()).mapToInt(mapper).toArray();
    }

    public static void shuffleIntArray(int[] array) {
        java.util.Random rand = java.util.concurrent.ThreadLocalRandom.current();
        for (int i = array.length - 1; i > 0; i--) {
            int index = rand.nextInt(i + 1);
            int a = array[index];
            array[index] = array[i];
            array[i] = a;
        }
    }
}
