package com.hbm.api.energymk2;

import com.hbm.lib.DirPos;
import com.hbm.lib.ForgeDirection;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.core.BlockPos;

public interface IEnergyConductorMK2 extends IEnergyConnectorMK2 {

    public default Nodespace.PowerNode createNode() {
        BlockEntity tile = (BlockEntity) this;
        int x = tile.getBlockPos().getX();
        int y = tile.getBlockPos().getY();
        int z = tile.getBlockPos().getZ();
        return new Nodespace.PowerNode(new BlockPos(x, y, z)).setConnections(
                new DirPos(x + 1, y, z, ForgeDirection.EAST),
                new DirPos(x - 1, y, z, ForgeDirection.WEST),
                new DirPos(x, y + 1, z, ForgeDirection.UP),
                new DirPos(x, y - 1, z, ForgeDirection.DOWN),
                new DirPos(x, y, z + 1, ForgeDirection.SOUTH),
                new DirPos(x, y, z - 1, ForgeDirection.NORTH)
        );
    }
}
