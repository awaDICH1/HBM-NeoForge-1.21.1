package com.hbm.api.fluidmk2;

import com.hbm.inventory.fluid.FluidType;
import com.hbm.lib.DirPos;
import com.hbm.lib.Library;
import net.minecraft.world.level.block.entity.BlockEntity;

/**
 * IFluidConnectorMK2 with added node creation method
 * @author hbm
 */
public interface IFluidPipeMK2 extends IFluidConnectorMK2 {
    default FluidNode createNode(FluidType type) {
        BlockEntity tile = (BlockEntity) this;
        return new FluidNode(type.getNetworkProvider(), tile.getBlockPos()).setConnections(
                new DirPos(tile.getBlockPos().getX() + 1, tile.getBlockPos().getY(), tile.getBlockPos().getZ(), Library.POS_X),
                new DirPos(tile.getBlockPos().getX() - 1, tile.getBlockPos().getY(), tile.getBlockPos().getZ(), Library.NEG_X),
                new DirPos(tile.getBlockPos().getX(), tile.getBlockPos().getY() + 1, tile.getBlockPos().getZ(), Library.POS_Y),
                new DirPos(tile.getBlockPos().getX(), tile.getBlockPos().getY() - 1, tile.getBlockPos().getZ(), Library.NEG_Y),
                new DirPos(tile.getBlockPos().getX(), tile.getBlockPos().getY(), tile.getBlockPos().getZ() + 1, Library.POS_Z),
                new DirPos(tile.getBlockPos().getX(), tile.getBlockPos().getY(), tile.getBlockPos().getZ() - 1, Library.NEG_Z)
        );
    }
}
