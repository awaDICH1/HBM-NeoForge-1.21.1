package com.hbm.tileentity;

import net.minecraft.world.level.Level;

public interface IOverpressurable {

	public void explode(Level world, int x, int y, int z);
}
