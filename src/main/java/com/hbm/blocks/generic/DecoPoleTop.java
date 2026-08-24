package com.hbm.blocks.generic;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;

/**
 * 迁移自 1.12.2 com.hbm.blocks.generic.DecoPoleTop。
 *
 * 1.13+ 删除的渲染方法及替代：
 *   - getBlockFaceShape / isOpaqueCube / isFullCube / isNormalCube / isBlockNormalCube → 全部删除，
 *     由注册时 Properties.noOcclusion() 取代
 *   - canRenderInLayer(CUTOUT) → 模型 json 的 "render_type": "minecraft:cutout"（NeoForge blockstate 扩展）
 *   - getRenderType → 删除（RenderShape 由模型决定）
 * 碰撞形状默认全立方（pole top 原实现未自定义碰撞箱）。
 */
public class DecoPoleTop extends Block {

    public DecoPoleTop(BlockBehaviour.Properties properties) {
        super(properties);
    }
}
