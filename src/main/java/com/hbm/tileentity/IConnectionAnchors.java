package com.hbm.tileentity;

import com.hbm.lib.DirPos;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;

public interface IConnectionAnchors {

    DirPos[] getConPos();

    static void notifyAnchors(BlockEntity te) {
        if (te == null) return;
        Level w = te.getLevel();
        if (w == null || w.isClientSide) return;
        Block source = te.getBlockState().getBlock();
        BlockPos from = te.getBlockPos();
        if (te instanceof IConnectionAnchors anchors) {
            for (DirPos d : anchors.getConPos()) {
                w.neighborChanged(d.getBlockPos(), source, from);
            }
        } else {
            w.updateNeighborsAt(from, source);
        }
    }
}
