package com.hbm.main;

import com.hbm.Tags;
import com.hbm.capability.HbmLivingCapability;
import com.hbm.entity.ModEntities;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.Attributes;

/**
 * Mod 总线事件订阅器。
 * 迁移自 1.12.2 MainRegistry 中的注册类调用（能力/属性/加载完成）。
 */
@EventBusSubscriber(modid = Tags.MODID, bus = EventBusSubscriber.Bus.MOD)
public class ModEvents {

    @SubscribeEvent
    public static void registerCapabilities(RegisterCapabilitiesEvent event) {
        // 原 MainRegistry.preInit()：
        //   CapabilityManager.INSTANCE.register(HbmLivingCapability.IEntityHbmProps.class,
        //       new HbmLivingCapability.EntityHbmPropsStorage(), HbmLivingCapability.EntityHbmProps.FACTORY);
        // 1.21.1：registerEntity 需要 EntityType（原 Class<LivingEntity> 全量注册已不支持）；
        // TODO P6：ModEntities 全量注册后按 EntityType 逐个补登
        event.registerEntity(HbmLivingCapability.ENT_HBM_PROPS_CAP, EntityType.PLAYER,
                (entity, ctx) -> new HbmLivingCapability.EntityHbmProps());
    }

    @SubscribeEvent
    public static void entityAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntities.C_RE_EP_ER_GO_LD.get(), Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 20.0)
                .add(Attributes.MOVEMENT_SPEED, 0.25)
                .add(Attributes.ATTACK_DAMAGE, 2.0).build());
        event.put(ModEntities.C_RE_EP_ER_NU_CL_EA_R.get(), Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 20.0)
                .add(Attributes.MOVEMENT_SPEED, 0.25)
                .add(Attributes.ATTACK_DAMAGE, 2.0).build());
        event.put(ModEntities.C_RE_EP_ER_PH_OS_GE_NE.get(), Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 20.0)
                .add(Attributes.MOVEMENT_SPEED, 0.25)
                .add(Attributes.ATTACK_DAMAGE, 2.0).build());
        event.put(ModEntities.C_RE_EP_ER_TA_IN_TE_D.get(), Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 20.0)
                .add(Attributes.MOVEMENT_SPEED, 0.25)
                .add(Attributes.ATTACK_DAMAGE, 2.0).build());
        event.put(ModEntities.C_RE_EP_ER_VO_LA_TI_LE.get(), Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 20.0)
                .add(Attributes.MOVEMENT_SPEED, 0.25)
                .add(Attributes.ATTACK_DAMAGE, 2.0).build());
        event.put(ModEntities.C_YB_ER_CR_AB.get(), Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 10.0)
                .add(Attributes.MOVEMENT_SPEED, 0.3)
                .add(Attributes.ATTACK_DAMAGE, 1.0).build());
        event.put(ModEntities.D_UC_K.get(), Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 4.0)
                .add(Attributes.MOVEMENT_SPEED, 0.25).build());
        event.put(ModEntities.D_UM_MY.get(), Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 20.0)
                .add(Attributes.MOVEMENT_SPEED, 0.0).build());
        event.put(ModEntities.F_BI.get(), Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 20.0)
                .add(Attributes.MOVEMENT_SPEED, 0.3)
                .add(Attributes.ATTACK_DAMAGE, 4.0).build());
        event.put(ModEntities.F_BI_DR_ON_E.get(), Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 10.0)
                .add(Attributes.MOVEMENT_SPEED, 0.4).build());
        event.put(ModEntities.G_LO_WI_NG_ON_E.get(), Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 30.0)
                .add(Attributes.MOVEMENT_SPEED, 0.2)
                .add(Attributes.ATTACK_DAMAGE, 5.0).build());
        event.put(ModEntities.H_UN_TE_RC_HO_PP_ER.get(), Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 40.0)
                .add(Attributes.MOVEMENT_SPEED, 0.3)
                .add(Attributes.ATTACK_DAMAGE, 6.0).build());
        event.put(ModEntities.M_AS_KM_AN.get(), Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 50.0)
                .add(Attributes.MOVEMENT_SPEED, 0.35)
                .add(Attributes.ATTACK_DAMAGE, 8.0).build());
        event.put(ModEntities.P_AR_AS_IT_EM_AG_GO_T.get(), Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 6.0)
                .add(Attributes.MOVEMENT_SPEED, 0.35)
                .add(Attributes.ATTACK_DAMAGE, 1.0).build());
        event.put(ModEntities.P_IG_EO_N.get(), Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 4.0)
                .add(Attributes.MOVEMENT_SPEED, 0.25).build());
        event.put(ModEntities.Q_UA_CK_OS.get(), Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 100.0)
                .add(Attributes.MOVEMENT_SPEED, 0.3)
                .add(Attributes.ATTACK_DAMAGE, 10.0).build());
        event.put(ModEntities.R_AD_BE_AS_T.get(), Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 30.0)
                .add(Attributes.MOVEMENT_SPEED, 0.25)
                .add(Attributes.ATTACK_DAMAGE, 5.0).build());
        event.put(ModEntities.T_AI_NT_CR_AB.get(), Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 12.0)
                .add(Attributes.MOVEMENT_SPEED, 0.3)
                .add(Attributes.ATTACK_DAMAGE, 2.0).build());
        event.put(ModEntities.T_ES_LA_CR_AB.get(), Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 12.0)
                .add(Attributes.MOVEMENT_SPEED, 0.3)
                .add(Attributes.ATTACK_DAMAGE, 2.0).build());
        event.put(ModEntities.U_FO.get(), Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 50.0)
                .add(Attributes.MOVEMENT_SPEED, 0.5).build());
        event.put(ModEntities.U_ND_EA_DS_OL_DI_ER.get(), Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 20.0)
                .add(Attributes.MOVEMENT_SPEED, 0.3)
                .add(Attributes.ATTACK_DAMAGE, 4.0).build());
        event.put(ModEntities.B_OT_PR_IM_EB_OD_Y.get(), Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 200.0)
                .add(Attributes.MOVEMENT_SPEED, 0.2)
                .add(Attributes.ATTACK_DAMAGE, 15.0).build());
        event.put(ModEntities.B_OT_PR_IM_EH_EA_D.get(), Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 200.0)
                .add(Attributes.MOVEMENT_SPEED, 0.2)
                .add(Attributes.ATTACK_DAMAGE, 15.0).build());
        event.put(ModEntities.G_LY_PH_ID.get(), Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 15.0)
                .add(Attributes.MOVEMENT_SPEED, 0.3)
                .add(Attributes.ATTACK_DAMAGE, 3.0).build());
        event.put(ModEntities.G_LY_PH_ID_BE_HE_MO_TH.get(), Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 40.0)
                .add(Attributes.MOVEMENT_SPEED, 0.25)
                .add(Attributes.ATTACK_DAMAGE, 6.0).build());
        event.put(ModEntities.G_LY_PH_ID_BL_AS_TE_R.get(), Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 20.0)
                .add(Attributes.MOVEMENT_SPEED, 0.3)
                .add(Attributes.ATTACK_DAMAGE, 4.0).build());
        event.put(ModEntities.G_LY_PH_ID_BO_MB_AR_DI_ER.get(), Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 20.0)
                .add(Attributes.MOVEMENT_SPEED, 0.3)
                .add(Attributes.ATTACK_DAMAGE, 4.0).build());
        event.put(ModEntities.G_LY_PH_ID_BR_AW_LE_R.get(), Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 25.0)
                .add(Attributes.MOVEMENT_SPEED, 0.3)
                .add(Attributes.ATTACK_DAMAGE, 5.0).build());
        event.put(ModEntities.G_LY_PH_ID_BR_EN_DA.get(), Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 100.0)
                .add(Attributes.MOVEMENT_SPEED, 0.25)
                .add(Attributes.ATTACK_DAMAGE, 8.0).build());
        event.put(ModEntities.G_LY_PH_ID_DI_GG_ER.get(), Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 15.0)
                .add(Attributes.MOVEMENT_SPEED, 0.3)
                .add(Attributes.ATTACK_DAMAGE, 3.0).build());
        event.put(ModEntities.G_LY_PH_ID_NU_CL_EA_R.get(), Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 30.0)
                .add(Attributes.MOVEMENT_SPEED, 0.25)
                .add(Attributes.ATTACK_DAMAGE, 5.0).build());
        event.put(ModEntities.G_LY_PH_ID_SC_OU_T.get(), Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 10.0)
                .add(Attributes.MOVEMENT_SPEED, 0.35)
                .add(Attributes.ATTACK_DAMAGE, 2.0).build());
    }

    @SubscribeEvent
    public static void loadComplete(FMLLoadCompleteEvent event) {
        // 原 MainRegistry.loadComplete(FMLLoadCompleteEvent)：
        //   RadiationSystemNT.onLoadComplete(); ModBlocks.initializeHazardsAndPlacables(); ...
        HBM.LOGGER.info("HBM load complete");
    }
}
