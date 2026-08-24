package com.hbm.items.special;

import com.hbm.inventory.material.MaterialShapes;
import com.hbm.inventory.material.Mats;
import com.hbm.inventory.material.NTMMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 自动生成物品基类（P3.2 骨架版）。
 *
 * 迁移自 1.12.2 com.hbm.items.special.ItemAutogen（214 行）。
 * 保留：构造器注册语义（INSTANCES）、aot/oun（纹理覆盖/名称覆盖数据）、getTexturePath（纯数据）、
 * getDescriptionId(ItemStack) 名称覆写。
 * 删除（TODO P8 渲染批）：registerModels/bakeModel/registerSprite/registerColorHandlers/ownsModelLocation/
 * getSubItems/getItemStackDisplayName 及全部 1.12 客户端 API（ModelBakeEvent/IModel/ModelLoader/
 * TextureAtlasSprite/IItemColor/ModelResourceLocation/CreativeTabs/NonNullList/I18n）与
 * IModelRegister/IClaimedModelLocation 接口（未迁移，P8）。
 */
public class ItemAutogen extends Item {

    public static List<ItemAutogen> INSTANCES = new ArrayList<>();
    MaterialShapes shape;
    private HashMap<NTMMaterial, String> textureOverrides = new HashMap<>();
    public static HashMap<NTMMaterial, Object> iconMap = new HashMap<>(); // P8: TextureAtlasSprite
    private String overrideUnlocalizedName = null;

    public ItemAutogen(MaterialShapes shape, String s) {
        super(new Item.Properties());
        this.shape = shape;
        INSTANCES.add(this);
        // TODO P8: ModItems.ALL_ITEMS.add(this); ClaimedModelLocationRegistry.register(this);
    }

    /**
     * add override texture
     */
    public ItemAutogen aot(NTMMaterial mat, String tex) {
        textureOverrides.put(mat, tex);
        return this;
    }

    public ItemAutogen oun(String overrideUnlocalizedName) {
        this.overrideUnlocalizedName = overrideUnlocalizedName;
        return this;
    }

    public String getTexturePath(NTMMaterial mat) {
        if (textureOverrides.containsKey(mat)) {
            return "items/" + textureOverrides.get(mat);
        } else {
            // 原 1.12: getRegistryName().getPath() → 1.21: BuiltInRegistries.ITEM.getKey(this).getPath()
            return "items/" + net.minecraft.core.registries.BuiltInRegistries.ITEM.getKey(this).getPath() + "-" + mat.names[0];
        }
    }

    @Override
    public String getDescriptionId(ItemStack stack) {
        return overrideUnlocalizedName != null ? "item." + overrideUnlocalizedName : super.getDescriptionId(stack);
    }

    // TODO P8: registerModels/bakeModel/registerSprites/registerSprite/ownsModelLocation/registerColorHandlers/getSubItems/getItemStackDisplayName
    // （依赖 IModelRegister/IClaimedModelLocation/ClaimedModelLocationRegistry + 1.12 渲染 API，渲染批迁移）
}
