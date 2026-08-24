package com.hbm.render.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

/**
 * Empty placeholder renderer for entities.
 * Renders nothing visible but prevents "Missing entity renderer" errors.
 */
public class RenderEmpty extends EntityRenderer<Entity> {

    private static final ResourceLocation TEXTURE = ResourceLocation.parse("minecraft:textures/misc/white.png");

    public RenderEmpty(Context context) {
        super(context);
    }

    @Override
    public void render(Entity entity, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
    }

    @Override
    public ResourceLocation getTextureLocation(Entity entity) {
        return TEXTURE;
    }
}
