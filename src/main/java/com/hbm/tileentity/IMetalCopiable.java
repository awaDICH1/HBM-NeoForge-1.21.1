package com.hbm.tileentity;

import com.hbm.interfaces.ICopiable;
import com.hbm.inventory.material.Mats;
import net.minecraft.world.entity.player.Player;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.Level;

public interface IMetalCopiable extends ICopiable {

    int[] getMatsToCopy();

    @Override
    default CompoundTag getSettings(Level world, int x, int y, int z) {
        CompoundTag tag = new CompoundTag();
        if(getMatsToCopy().length > 0) tag.putIntArray("matFilter", getMatsToCopy());
        return tag;
    }

    @Override
    default String[] infoForDisplay(Level world, int x, int y, int z) {
        int[] ids = getMatsToCopy();
        String[] names = new String[ids.length];
        for(int i = 0; i < ids.length; i++) names[i] = Mats.matById.get(ids[i]).getTranslationKey();
        return names;
    }

    @Override
    default void pasteSettings(CompoundTag nbt, int index, Level world, Player player, int x, int y, int z) { };
}
