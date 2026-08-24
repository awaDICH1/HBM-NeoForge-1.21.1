package com.hbm.items.machine;

import com.hbm.inventory.material.MaterialShapes;
import com.hbm.inventory.material.Mats;
import com.hbm.inventory.material.NTMMaterial;
import com.hbm.items.ModItems;
import com.hbm.items.special.ItemAutogen;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;

/**
 * 碎片物品（P3.2 骨架版）。
 *
 * 迁移自 1.12.2 com.hbm.items.machine.ItemScraps（198 行）。
 * 保留：数据核心 getMats/create（材料映射 + NBT amount/liquid，Mats ✓ 已迁移）。
 * 删除（TODO P8 渲染批）：getSubItems/registerSprite/bakeModel/registerModels/
 * getItemStackDisplayName/addInformation/ownsModelLocation（1.12 客户端 API：
 * ModelBakeEvent/ModelBakery/IModel/ModelLoader/TextureMap/I18n/Keyboard/ITooltipFlag/CreativeTabs）。
 * NBT 迁移：setTagCompound → DataComponents.CUSTOM_DATA + CustomData。
 */
public class ItemScraps extends ItemAutogen {

    public ItemScraps(String s) {
        super(null, s);
    }

    public static Mats.MaterialStack getMats(ItemStack stack) {

        if(stack.getItem() != ModItems.SCRAPS.get()) return null;

        NTMMaterial mat = Mats.matById.get(stack.getDamageValue());
        if(mat == null) return null;

        int amount = MaterialShapes.INGOT.q(1);

        if(stack.has(DataComponents.CUSTOM_DATA)) {
            amount = stack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag().getInt("amount");
        }

        return new Mats.MaterialStack(mat, amount);
    }

    public static ItemStack create(Mats.MaterialStack stack) {
        return create(stack, false);
    }

    public static ItemStack create(Mats.MaterialStack stack, boolean liquid) {
        if(stack.material == null)
            return new ItemStack(ModItems.NOTHING.get()); //why do i bother adding checks for fucking everything when they don't work
        ItemStack scrap = new ItemStack(ModItems.SCRAPS.get(), 1);
        scrap.setDamageValue(stack.material.id);
        CompoundTag tag = new CompoundTag();
        tag.putInt("amount", stack.amount);
        if(liquid) tag.putBoolean("liquid", true);
        scrap.set(DataComponents.CUSTOM_DATA, CustomData.of(tag));
        return scrap;
    }

    // TODO P8: 渲染方法（registerSprite/bakeModel/registerModels/getItemStackDisplayName/addInformation/ownsModelLocation/getSubItems）
}
