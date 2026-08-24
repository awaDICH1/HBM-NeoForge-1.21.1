package com.hbm.hazard.type;

import com.hbm.config.GeneralConfig;
import com.hbm.hazard.modifier.IHazardModifier;
import com.hbm.util.I18nUtil;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

import java.util.List;

/**
 * 迁移自 1.12.2 com.hbm.hazard.type.HazardTypeRadiation。
 *
 * ⚠️ TODO 占位（依赖未迁移系统）：
 *   - onUpdate：原 HazardHelper.isHoldingReacher(target)（ModItems 依赖，P5/P3）、
 *     ContaminationUtil.contaminate(...)（P5）；BobMathUtil.sqrt → Math.sqrt（原类 Tier C）
 *   - addHazardInformation：原 Library.roundFloat(x, 3) → 内联 Math.round(x*1000)/1000（P5 Library 迁移后恢复）
 * getNewValue/getSuffix（静态格式化工具，ContaminationUtil.addNeutronRadInfo 依赖）完整迁移。
 */
public class HazardTypeRadiation implements IHazardType {

    @Override
    public void onUpdate(final LivingEntity target, double level, final ItemStack stack) {

        // TODO P5: final boolean reacher = HazardHelper.isHoldingReacher(target);（HazardHelper 依赖 ModItems）
        final boolean reacher = false;

        level *= stack.getCount();

        if (level > 0) {
            double rad = level / 20D;

            if (GeneralConfig.enable528 && reacher) {
                rad = rad / 49D;    //More realistic function for 528: x / distance^2
            } else if (reacher) {
                rad = Math.sqrt(rad); // 原 BobMathUtil.sqrt(rad)
            }

            // TODO P5: ContaminationUtil.contaminate(target, HazardType.RADIATION, ContaminationType.CREATIVE, rad * hazardRate);
        }
    }

    @Override
    public void updateEntity(final ItemEntity item, final double level) {
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void addHazardInformation(final Player player, final List<String> list, double level, final ItemStack stack, final List<IHazardModifier> modifiers) {

        level = IHazardModifier.evalAllModifiers(stack, player, level, modifiers);
        if (level == 0) return;
        list.add("§a[" + I18nUtil.resolveKey("trait.radioactive") + "]");
        // 原 Library.roundFloat(x, 3) → Math.round(x*1000D)/1000D（P5 Library 迁移后恢复）
        list.add(" §e" + (Math.round(getNewValue(level) * 1000D) / 1000D + getSuffix(level) + " " + I18nUtil.resolveKey("desc.rads")));

        if (stack.getCount() > 1) {
            double stackRad = level * stack.getCount();
            list.add(" §e" + I18nUtil.resolveKey("desc.stack") + " " + Math.round(getNewValue(stackRad) * 1000D) / 1000D + getSuffix(stackRad) + " " + I18nUtil.resolveKey("desc.rads"));
        }
    }

    public static double getNewValue(double radiation) {
        if (radiation < 1000000) {
            return radiation;
        } else if (radiation < 1000000000) {
            return radiation * 0.000001D;
        } else {
            return radiation * 0.000000001D;
        }
    }

    public static String getSuffix(double radiation) {
        if (radiation < 1000000) {
            return "";
        } else if (radiation < 1000000000) {
            return I18nUtil.resolveKey("desc.mil");
        } else {
            return I18nUtil.resolveKey("desc.bil");
        }
    }
}
