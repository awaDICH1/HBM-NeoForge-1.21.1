package com.hbm.blocks;

import com.hbm.handler.MultiblockHandlerXR;
import com.hbm.interfaces.ICopiable;
import com.hbm.lib.ForgeDirection;
import com.hbm.lib.InventoryHelper;
import com.hbm.tileentity.IPersistentNBT;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.util.Mth;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

/**
 * 多方块系统基类（P5.1b-2 重写版）。
 *
 * 迁移自 1.12.2 com.hbm.blocks.BlockDummyable（945 行）。
 * 1.21.1 变更：
 *  - BlockContainer → Block + EntityBlock（abstract newBlockEntity）；
 *  - Material 构造器 → BlockBehaviour.Properties；
 *  - PropertyInteger（1.12）→ IntegerProperty（1.21）；createBlockState → createBlockStateDefinition；
 *    getStateFromMeta/getMetaFromState 删除（1.21 无 meta 体系）；
 *  - setBlockToAir → removeBlock(pos, false)；withProperty → setValue；getDefaultState → defaultBlockState；
 *  - breakBlock → onRemove(BlockState, Level, BlockPos, BlockState, boolean)；
 *  - pl.capabilities.isCreativeMode → getAbilities().instabuild；pl.inventory → getInventory()；
 *    Item.getItemFromBlock → asItem()；player.openGui → TODO P4.2（openMenu）；
 *  - Library.blockPosToLong/fromLong/getBlockPosX/Y/Z → BlockPos.asLong()/BlockPos.of()（内联）；
 *  - getBlockFaceShape/isOpaqueCube/isBlockNormalCube/isNormalCube/shouldSideBeRendered/isFullCube/
 *    canCreatureSpawn/getBoundingBox/addCollisionBoxToList 删除（1.21 无对应覆写）→ getShape；
 *  - 渲染层（shouldDrawHighlight/drawHighlight/bakeModel/registerModel/registerSprite/getRenderType/
 *    getRenderLayer/canRenderInLayer/getStateMapper/drawPlacementHighlight + ICustomBlockHighlight/
 *    IDynamicModels/INBTBlockTransformable 接口 + GL11/Tessellator/ModelBakeEvent 等）→ TODO P8；
 *  - transformMeta（INBTBlockTransformable）删除。
 */
public abstract class BlockDummyable extends Block implements EntityBlock, ICopiable {

    public static final IntegerProperty META = IntegerProperty.create("meta", 0, 15);
    /// BLOCK METADATA ///
    //0-5 		dummy rotation 		(for dummy neighbor checks)
    //6-11 		extra 				(6 rotations with flag, for pipe connectors and the like)
    //12-15 	block rotation 		(for rendering the TE)
    //meta offset from dummy to TE rotation
    public static final int offset = 10;
    //meta offset from dummy to extra rotation
    public static final int extra = 6;
    private static final long NO_CORE = Long.MIN_VALUE;
    private static final AABB DETAIL_AABB = new AABB(0.0F, 0.0F, 0.0F, 1.0F, 0.999F, 1.0F);
    public static boolean safeRem = false;
    public List<AABB> bounding = new ArrayList<>();

    public BlockDummyable(Properties properties, String s) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(META, 12));
        // TODO P8: ModBlocks.ALL_BLOCKS.add(this); IDynamicModels.INSTANCES.add(this);
    }

    public BlockDummyable(Properties properties, String s, boolean ignoredDontUseIDynamicModel) {
        this(properties, s);
    }

    protected int getMaxCoreSearchSteps() {
        return 512;
    }

    protected boolean isSameMultiblock(Block other) {
        return other == this;
    }

    private long findCoreSerialized(BlockGetter world, BlockPos pos, BlockPos.MutableBlockPos scratch) {
        return findCoreSerialized(world, pos.getX(), pos.getY(), pos.getZ(), scratch);
    }

    private long findCoreSerialized(BlockGetter world, int x, int y, int z, BlockPos.MutableBlockPos scratch) {
        for (int steps = 0, max = getMaxCoreSearchSteps(); steps < max; steps++) {
            scratch.set(x, y, z);
            BlockState state = world.getBlockState(scratch);
            if (!isSameMultiblock(state.getBlock())) return NO_CORE;
            int meta = state.getValue(META);
            if (meta >= 12) return new BlockPos(x, y, z).asLong();
            if (meta >= extra) meta -= extra;
            ForgeDirection dir = ForgeDirection.getOrientation(meta).getOpposite();
            x += dir.offsetX;
            y += dir.offsetY;
            z += dir.offsetZ;
        }
        return NO_CORE;
    }

    @Nullable
    public BlockPos findCore(BlockGetter world, BlockPos pos) {
        BlockPos.MutableBlockPos scratch = new BlockPos.MutableBlockPos();
        long core = findCoreSerialized(world, pos, scratch);
        if (core == NO_CORE) return null;
        return BlockPos.of(core);
    }

    @Nullable
    public BlockEntity findCoreTE(BlockGetter world, BlockPos pos) {
        BlockPos.MutableBlockPos scratch = new BlockPos.MutableBlockPos();
        long core = findCoreSerialized(world, pos, scratch);
        if (core == NO_CORE) return null;
        scratch.set(BlockPos.of(core));
        return world.getBlockEntity(scratch);
    }

    @Nullable
    public BlockEntity findCoreTE(BlockGetter world, int x, int y, int z) {
        BlockPos.MutableBlockPos scratch = new BlockPos.MutableBlockPos();
        long core = findCoreSerialized(world, x, y, z, scratch);
        if (core == NO_CORE) return null;
        scratch.set(BlockPos.of(core));
        return world.getBlockEntity(scratch);
    }

    public int @Nullable [] findCore(BlockGetter world, int x, int y, int z) {
        BlockPos.MutableBlockPos scratch = new BlockPos.MutableBlockPos();
        long core = findCoreSerialized(world, x, y, z, scratch);
        if (core == NO_CORE) return null;
        BlockPos p = BlockPos.of(core);
        return new int[]{p.getX(), p.getY(), p.getZ()};
    }

    @Override
    public void neighborChanged(@NotNull BlockState state, Level world, @NotNull BlockPos pos, @NotNull Block blockIn, @NotNull BlockPos fromPos, boolean isMoving) {
        if (world.isClientSide || safeRem) return;
        cascadeOrphans(world, pos, state);
    }

    @Override
    public void tick(@NotNull BlockState state, @NotNull net.minecraft.server.level.ServerLevel world, @NotNull BlockPos pos, @NotNull net.minecraft.util.RandomSource rand) {
        super.tick(state, world, pos, rand);
        if (world.isClientSide) return;
        cascadeOrphans(world, pos, state);
    }

    private boolean isOrphan(BlockGetter world, BlockPos pos, BlockState state) {
        int meta = state.getValue(META);
        if (meta >= 12) return false; // core, not a dummy
        if (meta >= extra) meta -= extra;
        ForgeDirection dir = ForgeDirection.getOrientation(meta).getOpposite();
        BlockPos other = pos.offset(dir.offsetX, dir.offsetY, dir.offsetZ);
        return !isSameMultiblock(world.getBlockState(other).getBlock());
    }

    private void cascadeOrphans(Level world, BlockPos start, BlockState startState) {
        if (startState.getBlock() != this || !isOrphan(world, start, startState)) return;
        safeRem = true;
        try {
            ArrayDeque<BlockPos> queue = new ArrayDeque<>();
            queue.add(start);
            while (!queue.isEmpty()) {
                BlockPos p = queue.poll();
                BlockState s = world.getBlockState(p);
                if (s.getBlock() != this) continue;
                if (!isOrphan(world, p, s)) continue;
                world.removeBlock(p, false);
                for (ForgeDirection d : ForgeDirection.VALID_DIRECTIONS) {
                    queue.add(p.offset(d.offsetX, d.offsetY, d.offsetZ));
                }
            }
        } finally {
            safeRem = false;
        }
    }

    @Override
    public void setPlacedBy(@NotNull Level world, @NotNull BlockPos pos, @NotNull BlockState state, @Nullable LivingEntity player, @NotNull ItemStack itemStack) {
    	if(!(player instanceof Player pl))
			return;
		safeRem = true;
    	world.removeBlock(pos, false);
		safeRem = false;

        InteractionHand hand = pl.getMainHandItem() == itemStack ? InteractionHand.MAIN_HAND : InteractionHand.OFF_HAND;
		
		int i = Mth.floor(player.getYRot() * 4.0F / 360.0F + 0.5D) & 3;
		int o = -getOffset();
		pos = new BlockPos(pos.getX(), pos.getY() + getHeightOffset(), pos.getZ());

		ForgeDirection dir = switch (i) {
            case 0 -> ForgeDirection.getOrientation(2);
            case 1 -> ForgeDirection.getOrientation(5);
            case 2 -> ForgeDirection.getOrientation(3);
            case 3 -> ForgeDirection.getOrientation(4);
            default -> ForgeDirection.NORTH;
        };
		
		dir = getDirModified(dir);
		
		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();
		
		if(!checkRequirement(world, x, y, z, dir, o)) {
			if(!pl.getAbilities().instabuild) {
				ItemStack stack = pl.getInventory().getItem(pl.getInventory().selected);
				Item item = this.asItem();
				
				if(stack.isEmpty()) {
					pl.getInventory().setItem(pl.getInventory().selected, new ItemStack(this));
				} else {
					if(stack.getItem() != item || stack.getCount() == stack.getMaxStackSize()) {
						pl.getInventory().add(new ItemStack(this));
					} else {
						pl.getItemInHand(hand).grow(1);
					}
				}
			}
			
			return;
		}
		
		if(!world.isClientSide){
			BlockPos cur = new BlockPos(x + dir.offsetX * o , y + dir.offsetY * o, z + dir.offsetZ * o);
            int meta = getMetaForCore(world, cur, pl, dir.ordinal() + offset);
			world.setBlock(cur, this.defaultBlockState().setValue(META, meta), 3);
			IPersistentNBT.onBlockPlacedBy(world, cur, itemStack);
			fillSpace(world, x, y, z, dir, o);
		}
		pos = new BlockPos(pos.getX(), pos.getY() - getHeightOffset(), pos.getZ());
		world.scheduleTick(pos, this, 1);
		world.scheduleTick(pos, this, 2);

    	super.setPlacedBy(world, pos, state, player, itemStack);
    }

	protected boolean standardOpenBehavior(Level world, BlockPos pos, Player player, int id){
		return this.standardOpenBehavior(world, pos.getX(), pos.getY(), pos.getZ(), player, id);
	}

    protected boolean standardOpenBehavior(Level world, int x, int y, int z, Player player, int id) {
		
		if(world.isClientSide) {
			return true;
		} else if(!player.isShiftKeyDown()) {
			int[] pos = this.findCore(world, x, y, z);

			if(pos == null)
				return false;

			// TODO P4.2: player.openGui(MainRegistry.instance, id, world, pos[0], pos[1], pos[2]) → 1.21 player.openMenu(MenuProvider)
			return true;
		} else {
			return true;
		}
	}

    protected int getMetaForCore(Level world, BlockPos pos, Player player, int original) {
        return original;
    }

    public ForgeDirection getDirModified(ForgeDirection dir) {
		return dir;
	}

	protected final Direction getDirModified(Direction dir) {
        if (dir == null) return null;
        ForgeDirection modified = getDirModified(ForgeDirection.getOrientation(dir));
        Direction facing = modified.toDirection();
		return facing != null ? facing : dir;
	}

    public boolean checkRequirement(Level world, int x, int y, int z, ForgeDirection dir, int o) {
		return MultiblockHandlerXR.checkSpace(world, x + dir.offsetX * o , y + dir.offsetY * o, z + dir.offsetZ * o, getDimensions(), x, y, z, dir);
	}
	
	protected void fillSpace(Level world, int x, int y, int z, ForgeDirection dir, int o) {
		MultiblockHandlerXR.fillSpace(world, x + dir.offsetX * o , y + dir.offsetY * o, z + dir.offsetZ * o, getDimensions(), this, dir);
	}

	public void makeExtra(Level world, int x, int y, int z) {
		BlockPos pos = new BlockPos(x, y, z);
		if(world.getBlockState(pos).getBlock() != this)
			return;
		
		int meta = world.getBlockState(pos).getValue(META);
		
		if(meta > 5)
			return;
			
		safeRem = true;
		world.setBlock(pos, this.defaultBlockState().setValue(META, meta + extra), 3);
		safeRem = false;
	}
	
	public void removeExtra(Level world, int x, int y, int z) {
		BlockPos pos = new BlockPos(x, y, z);
		if(world.getBlockState(pos).getBlock() != this)
			return;
		
		int meta = world.getBlockState(pos).getValue(META);
		
		if(meta <= 5 || meta >= 12)
			return;
			
		safeRem = true;
		world.setBlock(pos, this.defaultBlockState().setValue(META, meta - extra), 3);
		safeRem = false;
	}
		
	public boolean hasExtra(int meta) {
		return meta > 5 && meta < 12;
	}
	
	@Override
	public void onRemove(@NotNull BlockState state, @NotNull Level world, @NotNull BlockPos pos, BlockState newState, boolean isMoving) {
		int i = state.getValue(META);
		if(i < 12 && !safeRem) {

			if(i >= extra)
				i -= extra;

            ForgeDirection dir = ForgeDirection.getOrientation(i).getOpposite();

            BlockPos.MutableBlockPos scratch = new BlockPos.MutableBlockPos();
            long core = findCoreSerialized(world, pos.getX() + dir.offsetX, pos.getY() + dir.offsetY, pos.getZ() + dir.offsetZ, scratch);

            if (core != NO_CORE) {
                scratch.set(BlockPos.of(core));
                world.removeBlock(scratch, false);
            }
        }
        InventoryHelper.dropInventoryItems(world, pos, world.getBlockEntity(pos));
        super.onRemove(state, world, pos, newState, isMoving);
    }

	public boolean useDetailedHitbox() {
		return !bounding.isEmpty();
	}

	private ForgeDirection getRotationFromState(BlockState state) {
		int meta = state.getValue(META);
		return ForgeDirection.getOrientation(meta - offset).getRotation(ForgeDirection.UP);
	}

	public static AABB getAABBRotationOffset(AABB aabb, double x, double y, double z, ForgeDirection dir) {
		AABB newBox = null;

		if (dir == ForgeDirection.NORTH) {
			newBox = new AABB(aabb.minX, aabb.minY, aabb.minZ, aabb.maxX, aabb.maxY, aabb.maxZ);
		} else if (dir == ForgeDirection.EAST) {
			newBox = new AABB(-aabb.maxZ, aabb.minY, aabb.minX, -aabb.minZ, aabb.maxY, aabb.maxX);
		} else if (dir == ForgeDirection.SOUTH) {
			newBox = new AABB(-aabb.maxX, aabb.minY, -aabb.maxZ, -aabb.minX, aabb.maxY, -aabb.minZ);
		} else if (dir == ForgeDirection.WEST) {
			newBox = new AABB(aabb.minZ, aabb.minY, -aabb.maxX, aabb.maxZ, aabb.maxY, -aabb.minX);
		}

		if (newBox != null) {
			return newBox.move(x, y, z);
		}

		return new AABB(aabb.minX, aabb.minY, aabb.minZ, aabb.maxX, aabb.maxY, aabb.maxZ).move(x + 0.5, y + 0.5, z + 0.5);
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(META);
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
		return this.useDetailedHitbox() ? Shapes.create(DETAIL_AABB) : Shapes.block();
	}

	@Override
	public VoxelShape getCollisionShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
		return this.useDetailedHitbox() ? Shapes.create(DETAIL_AABB) : Shapes.block();
	}

	/**
	 * @returns an int array with six fields, describing the amount of dummy blocks in each direction around the core. order is UP, DOWN, FORWARD, BACKWARD, LEFT, RIGHT
	 */
	public abstract int[] getDimensions();

	public abstract int getOffset();
	
	public int getHeightOffset() {
		return 0;
	}

	public int[][] getAllDimensions() {
        return new int[][] { getDimensions() };
    }

    public double[][] getAABBExtras() {
        return new double[0][0];
    }

	@Override
	public CompoundTag getSettings(Level world, int x, int y, int z) {
        BlockPos.MutableBlockPos scratch = new BlockPos.MutableBlockPos();
        long core = findCoreSerialized(world, x, y, z, scratch);
        if (core == NO_CORE) return null;

        scratch.set(BlockPos.of(core));
        BlockEntity tile = world.getBlockEntity(scratch);
        if (tile instanceof ICopiable) return ((ICopiable) tile).getSettings(world, scratch.getX(), scratch.getY(), scratch.getZ());
        else return null;
    }

    @Override
    public void pasteSettings(CompoundTag nbt, int index, Level world, Player player, int x, int y, int z) {
        BlockPos.MutableBlockPos scratch = new BlockPos.MutableBlockPos();
        long core = findCoreSerialized(world, x, y, z, scratch);
        if (core == NO_CORE) return;
        scratch.set(BlockPos.of(core));
        BlockEntity tile = world.getBlockEntity(scratch);
        if (tile instanceof ICopiable) ((ICopiable) tile).pasteSettings(nbt, index, world, player, scratch.getX(), scratch.getY(), scratch.getZ());
    }

    @Override
    public String[] infoForDisplay(Level world, int x, int y, int z) {
        BlockPos.MutableBlockPos scratch = new BlockPos.MutableBlockPos();
        long core = findCoreSerialized(world, x, y, z, scratch);
        if (core == NO_CORE) return null;
        scratch.set(BlockPos.of(core));
        BlockEntity tile = world.getBlockEntity(scratch);
        if (tile instanceof ICopiable) return ((ICopiable) tile).infoForDisplay(world, x, y, z);
        return null;
    }

	// TODO P8: 渲染层（shouldDrawHighlight/drawHighlight/bakeModel/registerModel/registerSprite/
	// getRenderType/getRenderLayer/canRenderInLayer/getStateMapper/drawPlacementHighlight +
	// ICustomBlockHighlight/IDynamicModels/INBTBlockTransformable + GL11/Tessellator/ModelBakeEvent）
}
