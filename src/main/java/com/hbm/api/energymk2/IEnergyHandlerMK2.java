package com.hbm.api.energymk2;

import com.hbm.api.tile.ILoadedTile;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.phys.Vec3;

/** DO NOT USE DIRECTLY! This is simply the common ancestor to providers and receivers, because all this behavior has to be excluded from conductors! */
public interface IEnergyHandlerMK2 extends IEnergyConnectorMK2, ILoadedTile {

    public long getPower();
    public void setPower(long power);
    public long getMaxPower();

    public static final boolean particleDebug = false;

    public default Vec3 getDebugParticlePosMK2() {
        BlockEntity te = (BlockEntity) this;
        Vec3 vec = new Vec3(te.getBlockPos().getX() + 0.5, te.getBlockPos().getY() + 1, te.getBlockPos().getZ() + 0.5);
        return vec;
    }

}