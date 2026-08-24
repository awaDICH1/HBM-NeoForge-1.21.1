package com.hbm.tileentity;

import com.hbm.api.tile.IWorldRenameable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.Nameable;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.item.component.CustomData;

/**
 * Persistent block data helper（P5.1a 迁移版）。
 *
 * 迁移自 1.12.2 com.hbm.tileentity.IPersistentNBT（96 行）。
 * 1.21.1 变更：
 *  - IWorldNameable → net.minecraft.world.Nameable；
 *  - Item.getItemFromBlock/damageDropped → block.asItem() + setDamageValue(0)（meta 移除）；
 *  - itemstack.setTag → DataComponents.CUSTOM_DATA + CustomData.of；
 *  - Block.spawnAsEntity → Block.popResource；
 *  - hasComparatorInputOverride/updateComparatorOutputLevel → hasAnalogOutputSignal/updateNeighbourForOutputSignal；
 *  - player.capabilities.isCreativeMode → player.getAbilities().instabuild；
 *  - CompatExternal.getCoreFromPos → TODO P5.1b 桩（BlockDummyable 迁移后恢复，临时用 getBlockEntity(pos)）。
 */
public interface IPersistentNBT {

    String NBT_PERSISTENT_KEY = "persistent";

    static boolean breakBlock(Level worldIn, BlockPos pos, BlockState state) {
        // intentionally avoided CompatExternal.getCoreFromPos to prevent duplicates, so that only the block that has the core TE would drop items
        BlockEntity tile = worldIn.getBlockEntity(pos);
        final boolean flag;
        if (tile instanceof IPersistentNBT persistentTE && persistentTE.shouldDrop()) {
            ItemStack itemstack = new ItemStack(state.getBlock().asItem(), 1);
            itemstack.setDamageValue(0);
            CompoundTag data = new CompoundTag();
            persistentTE.writeNBT(data);
            if (!data.isEmpty()) itemstack.set(DataComponents.CUSTOM_DATA, CustomData.of(data));
            if (tile instanceof Nameable nameable && nameable.hasCustomName()) {
                itemstack.set(DataComponents.CUSTOM_NAME, nameable.getName());
                if (tile instanceof IWorldRenameable rn) rn.setCustomName("");
            }
            Block.popResource(worldIn, pos, itemstack);
            flag = true;
        } else flag = false;
        if (state.hasAnalogOutputSignal()) {
            worldIn.updateNeighbourForOutputSignal(pos, state.getBlock());
        }
        return flag;
    }

    static void onBlockPlacedBy(Level worldIn, BlockPos pos, ItemStack stack) {
        if (!worldIn.isClientSide && stack.has(DataComponents.CUSTOM_DATA) && worldIn.getBlockEntity(pos) instanceof IPersistentNBT persistentTE) {
            persistentTE.readNBT(stack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag());
        }
        if (stack.has(DataComponents.CUSTOM_NAME) && worldIn.getBlockEntity(pos) instanceof IWorldRenameable renameable) {
            renameable.setCustomName(stack.getHoverName().getString());
        }
    }

    static void onBlockHarvested(Level worldIn, BlockPos pos, Player player){
        // TODO P5.1b: CompatExternal.getCoreFromPos(worldIn, pos) —— BlockDummyable 迁移后恢复（获取多方块核心 TE）
        if (player.getAbilities().instabuild && worldIn.getBlockEntity(pos) instanceof IPersistentNBT persistentTE) {
            persistentTE.setDestroyedByCreativePlayer();
        }
    }

    default boolean shouldDrop() {
        return !isDestroyedByCreativePlayer();
    }

    void setDestroyedByCreativePlayer();

    boolean isDestroyedByCreativePlayer();

    void writeNBT(CompoundTag nbt);

    void readNBT(CompoundTag nbt);
}
