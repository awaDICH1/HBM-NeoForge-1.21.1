package com.hbm.interfaces;

import com.hbm.util.Either;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.entity.player.Player;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.Level;

public interface ICopiable {

    CompoundTag getSettings(Level world, int x, int y, int z);

    void pasteSettings(CompoundTag nbt, int index, Level world, Player player, int x, int y, int z);

    default String getSettingsSourceID(Either<BlockEntity, Block> self) {
        Block block = self.isLeft() ? self.left().getBlockState().getBlock() : self.right();
        return block.getDescriptionId();
    }

    default String getSettingsSourceDisplay(Either<BlockEntity, Block> self) {
        Block block = self.isLeft() ? self.left().getBlockState().getBlock() : self.right();
        return block.getDescriptionId();
    }

    default String[] infoForDisplay(Level world, int x, int y, int z){
        return null;
    }
}
