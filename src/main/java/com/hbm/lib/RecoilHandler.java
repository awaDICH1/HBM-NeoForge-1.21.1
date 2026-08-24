package com.hbm.lib;

import net.minecraft.util.Mth;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.client.event.ViewportEvent;

/**
 * 迁移自 1.12.2 com.hbm.lib.RecoilHandler。
 * 变更：
 *   - EntityViewRenderEvent.CameraSetup → ViewportEvent.ComputeCameraAngles（1.20.1+ 改名）
 *   - MathHelper → Mth；@SideOnly → @OnlyIn
 * 事件订阅在 P8 客户端渲染阶段接入（原 ClientProxy 中注册）。
 */
@OnlyIn(Dist.CLIENT)
public class RecoilHandler {

    private static long lastRenderTime;
    public static float verticalVelocity;
    public static float verticalRecoil;

    /** 原 EntityViewRenderEvent.CameraSetup 处理器 */
    public static void modifiyCamera(ViewportEvent.ComputeCameraAngles e) {
        long currentTime = System.currentTimeMillis();
        float scale = (currentTime - lastRenderTime) / 1000F;
        final float settle = 20F * Mth.clamp(verticalRecoil / 4, 0, 200);

        verticalRecoil = Math.max(0, verticalRecoil - scale * settle + verticalVelocity);
        verticalVelocity *= 0.35 * scale;
        e.setPitch(e.getPitch() - verticalRecoil);
        lastRenderTime = currentTime;
    }
}
