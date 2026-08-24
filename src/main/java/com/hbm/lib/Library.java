package com.hbm.lib;

import io.netty.buffer.ByteBuf;
import java.util.Arrays;

/**
 * 迁移自 1.12.2 com.hbm.lib.Library（P5 骨架：方向常量；其余工具方法按需补充）。
 */
public class Library {

    public static final ForgeDirection POS_X = ForgeDirection.EAST;
    public static final ForgeDirection NEG_X = ForgeDirection.WEST;
    public static final ForgeDirection POS_Y = ForgeDirection.UP;
    public static final ForgeDirection NEG_Y = ForgeDirection.DOWN;
    public static final ForgeDirection POS_Z = ForgeDirection.SOUTH;
    public static final ForgeDirection NEG_Z = ForgeDirection.NORTH;

    private Library() {
    }

    /**
     * P8: FNV-1a 64 位哈希（用于网络包去重）
     * 迁移自原 CE 的 Library.fnv1a64(ByteBuf buf)
     */
    public static long fnv1a64(ByteBuf buf) {
        if (buf == null || !buf.isReadable()) return 0L;
        long hash = 0xCBF29CE484222325L; // FNV offset basis (unsigned: 14003157727322219845)
        byte[] bytes = new byte[buf.readableBytes()];
        buf.getBytes(buf.readerIndex(), bytes);
        for (byte b : bytes) {
            hash ^= (long) b & 0xFFL;
            hash *= 0x100000001B3L; // FNV prime (1099511628211)
        }
        return hash;
    }

    /**
     * P8: FNV-1a 64 位哈希（用于网络包去重）
     * 重载版本，接受 byte[]
     */
    public static long fnv1a64(byte[] data) {
        if (data == null || data.length == 0) return 0L;
        long hash = 0xCBF29CE484222325L; // FNV offset basis
        for (byte b : data) {
            hash ^= (long) b & 0xFFL;
            hash *= 0x100000001B3L; // FNV prime
        }
        return hash;
    }

    /**
     * P8: 简化版 CRC32 哈希（用于快速去重）
     */
    public static long simpleHash(byte[] data) {
        if (data == null || data.length == 0) return 0L;
        long hash = 0x5BD1E995L;
        int length = data.length;
        for (int i = 0; i < length; i++) {
            hash = hash * 0x5BD1E995L ^ (long) data[i] & 0xFFL;
        }
        return hash ^ (hash >>> 33);
    }

    /**
     * 字节数组比较（用于去重检查）
     */
    public static boolean arraysEqual(byte[] a, byte[] b) {
        if (a == b) return true;
        if (a == null || b == null) return false;
        return Arrays.equals(a, b);
    }
}
