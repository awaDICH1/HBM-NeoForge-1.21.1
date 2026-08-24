package com.hbm.handler;

import com.hbm.blocks.BlockDummyable;
import com.hbm.lib.ForgeDirection;
import net.minecraft.core.BlockPos;
import net.minecraft.core.BlockPos.MutableBlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.AABB;

/**
 * 多方块空间处理器（P5.1b-2 迁移版）。
 *
 * 迁移自 1.12.2 com.hbm.handler.MultiblockHandlerXR（173 行）。
 * 1.21.1 变更：
 *  - setPos → set（MutableBlockPos）；getStateFromMeta → defaultBlockState().setValue(BlockDummyable.META)；
 *  - setBlockToAir → removeBlock；isReplaceable 签名保持；
 *  - Library.checkForPlayerEyePositions → TODO P5 桩（暂返回 true）。
 */
public class MultiblockHandlerXR {
	
	//when looking north
	//											U  D  N  S  W  E
	public static int[] uni = 		new int[] { 3, 0, 4, 4, 4, 4 };
	
	public static boolean checkSpace(Level world, int x, int y, int z, int[] dim, int ox, int oy, int oz, ForgeDirection dir) {
		return checkSpace(world, x, y, z, dim, ox, oy, oz, dir.toDirection());
	}
	
	public static boolean checkSpace(Level world, int x, int y, int z, int[] dim, int ox, int oy, int oz, Direction dir) {
		MutableBlockPos pos = new BlockPos.MutableBlockPos();
		if(dim == null || dim.length != 6)
			return false;
		
		int count = 0;
		
		int[] rot = rotate(dim, dir);

		for(int a = x - rot[4]; a <= x + rot[5]; a++) {
			for(int b = y - rot[1]; b <= y + rot[0]; b++) {
				for(int c = z - rot[2]; c <= z + rot[3]; c++) {
					
					//if the position matches the just placed block, the space counts as unoccupied
					if(a == ox && b == oy && c == oz)
						continue;
					
					//1.12 canBeReplaced()（空气/液体等可替换方块）→ 1.21 isAir()（无 BlockPlaceContext 时的等价检查）
					if(!world.getBlockState(pos.set(a, b, c)).isAir()) {
						return false;
					}
					
					count++;
					
					if(count > 2000) {
						System.out.println("checkspace: ded " + a + " " + b + " " + c + " " + x + " " + y + " " + z);
						return false;
					}
				}
			}
		}

		AABB aabb = new AABB(
				x - rot[4], y - rot[1], z - rot[2],
				x + rot[5] + 1, y + rot[0] + 1, z + rot[3] + 1);

        // TODO P5: Library.checkForPlayerEyePositions(world, aabb) —— 玩家眼睛位置检查（防止玩家被多方块覆盖）
        return true;
    }

	public static void fillSpace(Level world, int x, int y, int z, int[] dim, Block block, ForgeDirection dir) {
		fillSpace(world, x, y, z, dim, block, dir.toDirection());
	}
	
	@SuppressWarnings("deprecation")
	public static void fillSpace(Level world, int x, int y, int z, int[] dim, Block block, Direction dir) {
		MutableBlockPos pos = new BlockPos.MutableBlockPos();
		if(dim == null || dim.length != 6)
			return;
		
		int count = 0;
		
		int[] rot = rotate(dim, dir);
		
		BlockDummyable.safeRem = true;

		for(int a = x - rot[4]; a <= x + rot[5]; a++) {
			for(int b = y - rot[1]; b <= y + rot[0]; b++) {
				for(int c = z - rot[2]; c <= z + rot[3]; c++) {
					
					int meta = 0;
					
					if(b < y) {
						meta = ForgeDirection.DOWN.ordinal();
					} else if(b > y) {
						meta = ForgeDirection.UP.ordinal();
					} else if(a < x) {
						meta = ForgeDirection.WEST.ordinal();
					} else if(a > x) {
						meta = ForgeDirection.EAST.ordinal();
					} else if(c < z) {
						meta = ForgeDirection.NORTH.ordinal();
					} else if(c > z) {
						meta = ForgeDirection.SOUTH.ordinal();
					} else {
						continue;
					}
					
					world.setBlock(pos.set(a, b, c), block.defaultBlockState().setValue(BlockDummyable.META, meta), 3);
					
					count++;
					
					if(count > 2000) {
						System.out.println("fillspace: ded " + a + " " + b + " " + c + " " + x + " " + y + " " + z);
						
						BlockDummyable.safeRem = false;
						return;
					}
				}
			}
		}
		BlockDummyable.safeRem = false;
	}
	
	@Deprecated
	public static void emptySpace(Level world, int x, int y, int z, int[] dim, Block block, Direction dir) {
		MutableBlockPos pos = new BlockPos.MutableBlockPos();
		if(dim == null || dim.length != 6)
			return;

		int count = 0;
		
		System.out.println("emptyspace is deprecated and shouldn't even be executed");
		
		int[] rot = rotate(dim, dir);

		for(int a = x - rot[4]; a <= x + rot[5]; a++) {
			for(int b = y - rot[1]; b <= y + rot[0]; b++) {
				for(int c = z - rot[2]; c <= z + rot[3]; c++) {
					
					if(world.getBlockState(pos.set(a, b, c)).getBlock() == block)
						world.removeBlock(pos.set(a, b, c), false);
					
					count++;
					
					if(count > 2000) {
						System.out.println("emptyspace: ded " + a + " " + b + " " + c);
						return;
					}
				}
			}
		}
	}
	
	public static int[] rotate(int[] dim, Direction dir) {
		
		if(dim == null)
			return null;
		
		if(dir == Direction.SOUTH)
			return dim;
		
		if(dir == Direction.NORTH) {
			//                 U       D       N       S       W       E
			return new int[] { dim[0], dim[1], dim[3], dim[2], dim[5], dim[4] };
		}
		
		if(dir == Direction.EAST) {
			//                 U       D       N       S       W       E
			return new int[] { dim[0], dim[1], dim[5], dim[4], dim[2], dim[3] };
		}
		
		if(dir == Direction.WEST) {
			//                 U       D       N       S       W       E
			return new int[] { dim[0], dim[1], dim[4], dim[5], dim[3], dim[2] };
		}
		
		return dim;
	}

}
