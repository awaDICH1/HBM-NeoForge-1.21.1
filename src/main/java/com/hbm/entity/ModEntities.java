package com.hbm.entity;

import com.hbm.Tags;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import com.hbm.entity.effect.EntityBlackHole;
import com.hbm.entity.effect.EntityCloudFleija;
import com.hbm.entity.effect.EntityCloudFleijaRainbow;
import com.hbm.entity.effect.EntityCloudSolinium;
import com.hbm.entity.effect.EntityCloudTom;
import com.hbm.entity.effect.EntityEMPBlast;
import com.hbm.entity.effect.EntityFalloutRain;
import com.hbm.entity.effect.EntityFireLingering;
import com.hbm.entity.effect.EntityMist;
import com.hbm.entity.effect.EntityNukeTorex;
import com.hbm.entity.effect.EntityQuasar;
import com.hbm.entity.effect.EntityRagingVortex;
import com.hbm.entity.effect.EntitySpear;
import com.hbm.entity.effect.EntityVortex;
import com.hbm.entity.grenade.EntityDisperserCanister;
import com.hbm.entity.grenade.EntityGrenadeBouncyGeneric;
import com.hbm.entity.grenade.EntityGrenadeImpactGeneric;
import com.hbm.entity.grenade.EntityGrenadeUniversal;
import com.hbm.entity.item.EntityDeliveryDrone;
import com.hbm.entity.item.EntityFireworks;
import com.hbm.entity.item.EntityItemBuoyant;
import com.hbm.entity.item.EntityMovingItem;
import com.hbm.entity.item.EntityMovingPackage;
import com.hbm.entity.item.EntityParachuteCrate;
import com.hbm.entity.item.EntityRequestDrone;
import com.hbm.entity.item.EntityTNTPrimedBase;
import com.hbm.entity.logic.EntityBalefire;
import com.hbm.entity.logic.EntityBomber;
import com.hbm.entity.logic.EntityC130;
import com.hbm.entity.logic.EntityDeathBlast;
import com.hbm.entity.logic.EntityEMP;
import com.hbm.entity.logic.EntityNukeExplosionMK3;
import com.hbm.entity.logic.EntityNukeExplosionMK5;
import com.hbm.entity.logic.EntityTomBlast;
import com.hbm.entity.missile.EntityBobmazon;
import com.hbm.entity.missile.EntityBombletSelena;
import com.hbm.entity.missile.EntityMinerRocket;
import com.hbm.entity.missile.EntityMIRV;
import com.hbm.entity.missile.EntityMissileAntiBallistic;
import com.hbm.entity.missile.EntityMissileCustom;
import com.hbm.entity.missile.EntityMissileShuttle;
import com.hbm.entity.missile.EntityMissileStealth;
import com.hbm.entity.missile.EntityMissileTier0;
import com.hbm.entity.missile.EntityMissileTier1;
import com.hbm.entity.missile.EntityMissileTier2;
import com.hbm.entity.missile.EntityMissileTier3;
import com.hbm.entity.missile.EntityMissileTier4;
import com.hbm.entity.missile.EntitySoyuz;
import com.hbm.entity.missile.EntitySoyuzCapsule;
import com.hbm.entity.mob.EntityCreeperGold;
import com.hbm.entity.mob.EntityCreeperNuclear;
import com.hbm.entity.mob.EntityCreeperPhosgene;
import com.hbm.entity.mob.EntityCreeperTainted;
import com.hbm.entity.mob.EntityCreeperVolatile;
import com.hbm.entity.mob.EntityCyberCrab;
import com.hbm.entity.mob.EntityDuck;
import com.hbm.entity.mob.EntityDummy;
import com.hbm.entity.mob.EntityFBI;
import com.hbm.entity.mob.EntityFBIDrone;
import com.hbm.entity.mob.EntityGlowingOne;
import com.hbm.entity.mob.EntityHunterChopper;
import com.hbm.entity.mob.EntityMaskMan;
import com.hbm.entity.mob.EntityParasiteMaggot;
import com.hbm.entity.mob.EntityPigeon;
import com.hbm.entity.mob.EntityQuackos;
import com.hbm.entity.mob.EntityRADBeast;
import com.hbm.entity.mob.EntityTaintCrab;
import com.hbm.entity.mob.EntityTeslaCrab;
import com.hbm.entity.mob.EntityUFO;
import com.hbm.entity.mob.EntityUndeadSoldier;
import com.hbm.entity.mob.botprime.EntityBOTPrimeBody;
import com.hbm.entity.mob.botprime.EntityBOTPrimeHead;
import com.hbm.entity.mob.glyphid.EntityGlyphid;
import com.hbm.entity.mob.glyphid.EntityGlyphidBehemoth;
import com.hbm.entity.mob.glyphid.EntityGlyphidBlaster;
import com.hbm.entity.mob.glyphid.EntityGlyphidBombardier;
import com.hbm.entity.mob.glyphid.EntityGlyphidBrawler;
import com.hbm.entity.mob.glyphid.EntityGlyphidBrenda;
import com.hbm.entity.mob.glyphid.EntityGlyphidDigger;
import com.hbm.entity.mob.glyphid.EntityGlyphidNuclear;
import com.hbm.entity.mob.glyphid.EntityGlyphidScout;
import com.hbm.entity.particle.EntityModFXShadow;
import com.hbm.entity.projectile.EntityAAShell;
import com.hbm.entity.projectile.EntityAcidBomb;
import com.hbm.entity.projectile.EntityArtilleryRocket;
import com.hbm.entity.projectile.EntityArtilleryShell;
import com.hbm.entity.projectile.EntityBeamVortex;
import com.hbm.entity.projectile.EntityBombletZeta;
import com.hbm.entity.projectile.EntityBoxcar;
import com.hbm.entity.projectile.EntityBuilding;
import com.hbm.entity.projectile.EntityBullet;
import com.hbm.entity.projectile.EntityBulletBase;
import com.hbm.entity.projectile.EntityBulletBaseMK4;
import com.hbm.entity.projectile.EntityBulletBaseMK4CL;
import com.hbm.entity.projectile.EntityBulletBaseNT;
import com.hbm.entity.projectile.EntityBulletBeamBase;
import com.hbm.entity.projectile.EntityBurningFOEQ;
import com.hbm.entity.projectile.EntityChemical;
import com.hbm.entity.projectile.EntityChopperMine;
import com.hbm.entity.projectile.EntityCog;
import com.hbm.entity.projectile.EntityCoin;
import com.hbm.entity.projectile.EntityCombineBall;
import com.hbm.entity.projectile.EntityDischarge;
import com.hbm.entity.projectile.EntityDuchessGambit;
import com.hbm.entity.projectile.EntityExplosiveBeam;
import com.hbm.entity.projectile.EntityFallingNuke;
import com.hbm.entity.projectile.EntityFire;
import com.hbm.entity.projectile.EntityLaser;
import com.hbm.entity.projectile.EntityLaserBeam;
import com.hbm.entity.projectile.EntityLN2;
import com.hbm.entity.projectile.EntityMeteor;
import com.hbm.entity.projectile.EntityMinerBeam;
import com.hbm.entity.projectile.EntityMiniMIRV;
import com.hbm.entity.projectile.EntityMiniNuke;
import com.hbm.entity.projectile.EntityModBeam;
import com.hbm.entity.projectile.EntityPlasmaBeam;
import com.hbm.entity.projectile.EntityRailgunBlast;
import com.hbm.entity.projectile.EntityRainbow;
import com.hbm.entity.projectile.EntityRBMKDebris;
import com.hbm.entity.projectile.EntityRocket;
import com.hbm.entity.projectile.EntityRubble;
import com.hbm.entity.projectile.EntitySawblade;
import com.hbm.entity.projectile.EntitySchrab;
import com.hbm.entity.projectile.EntityShrapnel;
import com.hbm.entity.projectile.EntitySparkBeam;
import com.hbm.entity.projectile.EntityTom;
import com.hbm.entity.projectile.EntityTorpedo;
import com.hbm.entity.projectile.EntityZirnoxDebris;

/**
 * 实体注册中心。
 * 原 1.12.2 由 @AutoRegister 注解处理器生成的 GeneratedHBMRegistrar.registerEntities() 负责；
 * 移植 @AutoRegister 处理器后（推荐），生成代码改为向本 DeferredRegister 添加条目。
 */
public class ModEntities {

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(Registries.ENTITY_TYPE, Tags.MODID);

    public static final DeferredHolder<EntityType<?>, EntityType<EntityBlackHole>> B_LA_CK_HO_LE =
            ENTITY_TYPES.register("entity_black_hole",
                    () -> EntityType.Builder.of(EntityBlackHole::new, MobCategory.MISC)
                            .sized(0.6F, 1.8F).build("entity_black_hole"));
    public static final DeferredHolder<EntityType<?>, EntityType<EntityCloudFleija>> C_LO_UD_FL_EI_JA =
            ENTITY_TYPES.register("entity_cloud_fleija",
                    () -> EntityType.Builder.of(EntityCloudFleija::new, MobCategory.MISC)
                            .sized(0.6F, 1.8F).build("entity_cloud_fleija"));
    public static final DeferredHolder<EntityType<?>, EntityType<EntityCloudFleijaRainbow>> C_LO_UD_FL_EI_JA_RA_IN_BO_W =
            ENTITY_TYPES.register("entity_fleija_rainbow",
                    () -> EntityType.Builder.of(EntityCloudFleijaRainbow::new, MobCategory.MISC)
                            .sized(0.6F, 1.8F).build("entity_fleija_rainbow"));
    public static final DeferredHolder<EntityType<?>, EntityType<EntityCloudSolinium>> C_LO_UD_SO_LI_NI_UM =
            ENTITY_TYPES.register("entity_clound_solinium",
                    () -> EntityType.Builder.of(EntityCloudSolinium::new, MobCategory.MISC)
                            .sized(0.6F, 1.8F).build("entity_clound_solinium"));
    public static final DeferredHolder<EntityType<?>, EntityType<EntityCloudTom>> C_LO_UD_TO_M =
            ENTITY_TYPES.register("entity_moonstone_blast",
                    () -> EntityType.Builder.of(EntityCloudTom::new, MobCategory.MISC)
                            .sized(0.6F, 1.8F).build("entity_moonstone_blast"));
    public static final DeferredHolder<EntityType<?>, EntityType<EntityEMPBlast>> E_MP_BL_AS_T =
            ENTITY_TYPES.register("entity_emp_blast",
                    () -> EntityType.Builder.of(EntityEMPBlast::new, MobCategory.MISC)
                            .sized(0.6F, 1.8F).build("entity_emp_blast"));
    public static final DeferredHolder<EntityType<?>, EntityType<EntityFalloutRain>> F_AL_LO_UT_RA_IN =
            ENTITY_TYPES.register("entity_fallout_rain",
                    () -> EntityType.Builder.of(EntityFalloutRain::new, MobCategory.MISC)
                            .sized(0.6F, 1.8F).build("entity_fallout_rain"));
    public static final DeferredHolder<EntityType<?>, EntityType<EntityFireLingering>> F_IR_EL_IN_GE_RI_NG =
            ENTITY_TYPES.register("entity_fire_lingering",
                    () -> EntityType.Builder.<EntityFireLingering>of(EntityFireLingering::new, MobCategory.MISC)
                            .sized(0.6F, 1.8F).build("entity_fire_lingering"));
    public static final DeferredHolder<EntityType<?>, EntityType<EntityMist>> M_IS_T =
            ENTITY_TYPES.register("entity_mist",
                    () -> EntityType.Builder.of(EntityMist::new, MobCategory.MISC)
                            .sized(0.6F, 1.8F).build("entity_mist"));
    public static final DeferredHolder<EntityType<?>, EntityType<EntityNukeTorex>> N_UK_ET_OR_EX =
            ENTITY_TYPES.register("entity_effect_torex",
                    () -> EntityType.Builder.of(EntityNukeTorex::new, MobCategory.MISC)
                            .sized(0.6F, 1.8F).build("entity_effect_torex"));
    public static final DeferredHolder<EntityType<?>, EntityType<EntityQuasar>> Q_UA_SA_R =
            ENTITY_TYPES.register("entity_digamma_quasar",
                    () -> EntityType.Builder.of(EntityQuasar::new, MobCategory.MISC)
                            .sized(0.6F, 1.8F).build("entity_digamma_quasar"));
    public static final DeferredHolder<EntityType<?>, EntityType<EntityRagingVortex>> R_AG_IN_GV_OR_TE_X =
            ENTITY_TYPES.register("entity_raging_vortex",
                    () -> EntityType.Builder.of(EntityRagingVortex::new, MobCategory.MISC)
                            .sized(0.6F, 1.8F).build("entity_raging_vortex"));
    public static final DeferredHolder<EntityType<?>, EntityType<EntitySpear>> S_PE_AR =
            ENTITY_TYPES.register("entity_spear",
                    () -> EntityType.Builder.of(EntitySpear::new, MobCategory.MISC)
                            .sized(0.6F, 1.8F).build("entity_spear"));
    public static final DeferredHolder<EntityType<?>, EntityType<EntityVortex>> V_OR_TE_X =
            ENTITY_TYPES.register("entity_vortex",
                    () -> EntityType.Builder.of(EntityVortex::new, MobCategory.MISC)
                            .sized(0.6F, 1.8F).build("entity_vortex"));
    public static final DeferredHolder<EntityType<?>, EntityType<EntityDisperserCanister>> D_IS_PE_RS_ER_CA_NI_ST_ER =
            ENTITY_TYPES.register("entity_disperser",
                    () -> EntityType.Builder.<EntityDisperserCanister>of(EntityDisperserCanister::new, MobCategory.MISC)
                            .sized(0.6F, 1.8F).build("entity_disperser"));
    public static final DeferredHolder<EntityType<?>, EntityType<EntityGrenadeBouncyGeneric>> G_RE_NA_DE_BO_UN_CY_GE_NE_RI_C =
            ENTITY_TYPES.register("entity_grenade_bouncy_generic",
                    () -> EntityType.Builder.<EntityGrenadeBouncyGeneric>of(EntityGrenadeBouncyGeneric::new, MobCategory.MISC)
                            .sized(0.6F, 1.8F).build("entity_grenade_bouncy_generic"));
    public static final DeferredHolder<EntityType<?>, EntityType<EntityGrenadeImpactGeneric>> G_RE_NA_DE_IM_PA_CT_GE_NE_RI_C =
            ENTITY_TYPES.register("entity_grenade_impact_generic",
                    () -> EntityType.Builder.<EntityGrenadeImpactGeneric>of(EntityGrenadeImpactGeneric::new, MobCategory.MISC)
                            .sized(0.6F, 1.8F).build("entity_grenade_impact_generic"));
    public static final DeferredHolder<EntityType<?>, EntityType<EntityGrenadeUniversal>> G_RE_NA_DE_UN_IV_ER_SA_L =
            ENTITY_TYPES.register("entity_grenade_universal",
                    () -> EntityType.Builder.<EntityGrenadeUniversal>of(EntityGrenadeUniversal::new, MobCategory.MISC)
                            .sized(0.6F, 1.8F).build("entity_grenade_universal"));
    public static final DeferredHolder<EntityType<?>, EntityType<EntityDeliveryDrone>> D_EL_IV_ER_YD_RO_NE =
            ENTITY_TYPES.register("entity_delivery_drone",
                    () -> EntityType.Builder.of(EntityDeliveryDrone::new, MobCategory.MISC)
                            .sized(0.6F, 1.8F).build("entity_delivery_drone"));
    public static final DeferredHolder<EntityType<?>, EntityType<EntityFireworks>> F_IR_EW_OR_KS =
            ENTITY_TYPES.register("entity_firework_ball",
                    () -> EntityType.Builder.<EntityFireworks>of(EntityFireworks::new, MobCategory.MISC)
                            .sized(0.6F, 1.8F).build("entity_firework_ball"));
    public static final DeferredHolder<EntityType<?>, EntityType<EntityItemBuoyant>> I_TE_MB_UO_YA_NT =
            ENTITY_TYPES.register("entity_item_buoyant",
                    () -> EntityType.Builder.of(EntityItemBuoyant::new, MobCategory.MISC)
                            .sized(0.6F, 1.8F).build("entity_item_buoyant"));
    public static final DeferredHolder<EntityType<?>, EntityType<EntityMovingItem>> M_OV_IN_GI_TE_M =
            ENTITY_TYPES.register("entity_c_item",
                    () -> EntityType.Builder.of(EntityMovingItem::new, MobCategory.MISC)
                            .sized(0.6F, 1.8F).build("entity_c_item"));
    public static final DeferredHolder<EntityType<?>, EntityType<EntityMovingPackage>> M_OV_IN_GP_AC_KA_GE =
            ENTITY_TYPES.register("entity_c_package",
                    () -> EntityType.Builder.of(EntityMovingPackage::new, MobCategory.MISC)
                            .sized(0.6F, 1.8F).build("entity_c_package"));
    public static final DeferredHolder<EntityType<?>, EntityType<EntityParachuteCrate>> P_AR_AC_HU_TE_CR_AT_E =
            ENTITY_TYPES.register("entity_parachute_crate",
                    () -> EntityType.Builder.of(EntityParachuteCrate::new, MobCategory.MISC)
                            .sized(0.6F, 1.8F).build("entity_parachute_crate"));
    public static final DeferredHolder<EntityType<?>, EntityType<EntityRequestDrone>> R_EQ_UE_ST_DR_ON_E =
            ENTITY_TYPES.register("entity_request_drone",
                    () -> EntityType.Builder.of(EntityRequestDrone::new, MobCategory.MISC)
                            .sized(0.6F, 1.8F).build("entity_request_drone"));
    public static final DeferredHolder<EntityType<?>, EntityType<EntityTNTPrimedBase>> T_NT_PR_IM_ED_BA_SE =
            ENTITY_TYPES.register("entity_ntm_tnt_primed",
                    () -> EntityType.Builder.of(EntityTNTPrimedBase::new, MobCategory.MISC)
                            .sized(0.6F, 1.8F).build("entity_ntm_tnt_primed"));
    public static final DeferredHolder<EntityType<?>, EntityType<EntityBalefire>> B_AL_EF_IR_E =
            ENTITY_TYPES.register("entity_balefire",
                    () -> EntityType.Builder.of(EntityBalefire::new, MobCategory.MISC)
                            .sized(0.6F, 1.8F).build("entity_balefire"));
    public static final DeferredHolder<EntityType<?>, EntityType<EntityBomber>> B_OM_BE_R =
            ENTITY_TYPES.register("entity_bomber",
                    () -> EntityType.Builder.of(EntityBomber::new, MobCategory.MISC)
                            .sized(0.6F, 1.8F).build("entity_bomber"));
    public static final DeferredHolder<EntityType<?>, EntityType<EntityC130>> C130 =
            ENTITY_TYPES.register("entity_c130",
                    () -> EntityType.Builder.of(EntityC130::new, MobCategory.MISC)
                            .sized(0.6F, 1.8F).build("entity_c130"));
    public static final DeferredHolder<EntityType<?>, EntityType<EntityDeathBlast>> D_EA_TH_BL_AS_T =
            ENTITY_TYPES.register("entity_laser_blast",
                    () -> EntityType.Builder.of(EntityDeathBlast::new, MobCategory.MISC)
                            .sized(0.6F, 1.8F).build("entity_laser_blast"));
    public static final DeferredHolder<EntityType<?>, EntityType<EntityEMP>> E_MP =
            ENTITY_TYPES.register("entity_emp",
                    () -> EntityType.Builder.of(EntityEMP::new, MobCategory.MISC)
                            .sized(0.6F, 1.8F).build("entity_emp"));
    public static final DeferredHolder<EntityType<?>, EntityType<EntityNukeExplosionMK3>> N_UK_EE_XP_LO_SI_ON_MK3 =
            ENTITY_TYPES.register("entity_nuke_mk3",
                    () -> EntityType.Builder.of(EntityNukeExplosionMK3::new, MobCategory.MISC)
                            .sized(0.6F, 1.8F).build("entity_nuke_mk3"));
    public static final DeferredHolder<EntityType<?>, EntityType<EntityNukeExplosionMK5>> N_UK_EE_XP_LO_SI_ON_MK5 =
            ENTITY_TYPES.register("entity_nuke_mk5",
                    () -> EntityType.Builder.of(EntityNukeExplosionMK5::new, MobCategory.MISC)
                            .sized(0.6F, 1.8F).build("entity_nuke_mk5"));
    public static final DeferredHolder<EntityType<?>, EntityType<EntityTomBlast>> T_OM_BL_AS_T =
            ENTITY_TYPES.register("entity_tom_bust",
                    () -> EntityType.Builder.<EntityTomBlast>of(EntityTomBlast::new, MobCategory.MISC)
                            .sized(0.6F, 1.8F).build("entity_tom_bust"));
    public static final DeferredHolder<EntityType<?>, EntityType<EntityBobmazon>> B_OB_MA_ZO_N =
            ENTITY_TYPES.register("entity_bobmazon",
                    () -> EntityType.Builder.<EntityBobmazon>of(EntityBobmazon::new, MobCategory.MISC)
                            .sized(0.6F, 1.8F).build("entity_bobmazon"));
    public static final DeferredHolder<EntityType<?>, EntityType<EntityBombletSelena>> B_OM_BL_ET_SE_LE_NA =
            ENTITY_TYPES.register("entity_selena",
                    () -> EntityType.Builder.<EntityBombletSelena>of(EntityBombletSelena::new, MobCategory.MISC)
                            .sized(0.6F, 1.8F).build("entity_selena"));
    public static final DeferredHolder<EntityType<?>, EntityType<EntityMinerRocket>> M_IN_ER_RO_CK_ET =
            ENTITY_TYPES.register("entity_miner_rocket",
                    () -> EntityType.Builder.<EntityMinerRocket>of(EntityMinerRocket::new, MobCategory.MISC)
                            .sized(0.6F, 1.8F).build("entity_miner_rocket"));
    public static final DeferredHolder<EntityType<?>, EntityType<EntityMIRV>> M_IR_V =
            ENTITY_TYPES.register("entity_mirvlet",
                    () -> EntityType.Builder.<EntityMIRV>of(EntityMIRV::new, MobCategory.MISC)
                            .sized(0.6F, 1.8F).build("entity_mirvlet"));
    public static final DeferredHolder<EntityType<?>, EntityType<EntityMissileAntiBallistic>> M_IS_SI_LE_AN_TI_BA_LL_IS_TI_C =
            ENTITY_TYPES.register("entity_missile_ab",
                    () -> EntityType.Builder.<EntityMissileAntiBallistic>of(EntityMissileAntiBallistic::new, MobCategory.MISC)
                            .sized(0.6F, 1.8F).build("entity_missile_ab"));
    public static final DeferredHolder<EntityType<?>, EntityType<EntityMissileCustom>> M_IS_SI_LE_CU_ST_OM =
            ENTITY_TYPES.register("entity_custom_missile",
                    () -> EntityType.Builder.<EntityMissileCustom>of(EntityMissileCustom::new, MobCategory.MISC)
                            .sized(0.6F, 1.8F).build("entity_custom_missile"));
    public static final DeferredHolder<EntityType<?>, EntityType<EntityMissileShuttle>> M_IS_SI_LE_SH_UT_TL_E =
            ENTITY_TYPES.register("missile_shuttle",
                    () -> EntityType.Builder.<EntityMissileShuttle>of(EntityMissileShuttle::new, MobCategory.MISC)
                            .sized(0.6F, 1.8F).build("missile_shuttle"));
    public static final DeferredHolder<EntityType<?>, EntityType<EntityMissileStealth>> M_IS_SI_LE_ST_EA_LT_H =
            ENTITY_TYPES.register("entity_missile_stealth",
                    () -> EntityType.Builder.<EntityMissileStealth>of(EntityMissileStealth::new, MobCategory.MISC)
                            .sized(0.6F, 1.8F).build("entity_missile_stealth"));
    public static final DeferredHolder<EntityType<?>, EntityType<EntityMissileTier0>> M_IS_SI_LE_TI_ER0 =
            ENTITY_TYPES.register("entity_missile_micro",
                    () -> EntityType.Builder.<EntityMissileTier0>of(EntityMissileTier0::new, MobCategory.MISC)
                            .sized(0.6F, 1.8F).build("entity_missile_micro"));
    public static final DeferredHolder<EntityType<?>, EntityType<EntityMissileTier1>> M_IS_SI_LE_TI_ER1 =
            ENTITY_TYPES.register("entity_missile_generic",
                    () -> EntityType.Builder.<EntityMissileTier1>of(EntityMissileTier1::new, MobCategory.MISC)
                            .sized(0.6F, 1.8F).build("entity_missile_generic"));
    public static final DeferredHolder<EntityType<?>, EntityType<EntityMissileTier2>> M_IS_SI_LE_TI_ER2 =
            ENTITY_TYPES.register("entity_missile_strong",
                    () -> EntityType.Builder.<EntityMissileTier2>of(EntityMissileTier2::new, MobCategory.MISC)
                            .sized(0.6F, 1.8F).build("entity_missile_strong"));
    public static final DeferredHolder<EntityType<?>, EntityType<EntityMissileTier3>> M_IS_SI_LE_TI_ER3 =
            ENTITY_TYPES.register("entity_missile_burst",
                    () -> EntityType.Builder.<EntityMissileTier3>of(EntityMissileTier3::new, MobCategory.MISC)
                            .sized(0.6F, 1.8F).build("entity_missile_burst"));
    public static final DeferredHolder<EntityType<?>, EntityType<EntityMissileTier4>> M_IS_SI_LE_TI_ER4 =
            ENTITY_TYPES.register("entity_missile_nuclear",
                    () -> EntityType.Builder.<EntityMissileTier4>of(EntityMissileTier4::new, MobCategory.MISC)
                            .sized(0.6F, 1.8F).build("entity_missile_nuclear"));
    public static final DeferredHolder<EntityType<?>, EntityType<EntitySoyuz>> S_OY_UZ =
            ENTITY_TYPES.register("entity_soyuz",
                    () -> EntityType.Builder.<EntitySoyuz>of(EntitySoyuz::new, MobCategory.MISC)
                            .sized(0.6F, 1.8F).build("entity_soyuz"));
    public static final DeferredHolder<EntityType<?>, EntityType<EntitySoyuzCapsule>> S_OY_UZ_CA_PS_UL_E =
            ENTITY_TYPES.register("entity_soyuz_capsule",
                    () -> EntityType.Builder.<EntitySoyuzCapsule>of(EntitySoyuzCapsule::new, MobCategory.MISC)
                            .sized(0.6F, 1.8F).build("entity_soyuz_capsule"));
    public static final DeferredHolder<EntityType<?>, EntityType<EntityCreeperGold>> C_RE_EP_ER_GO_LD =
            ENTITY_TYPES.register("entity_mob_gold_creeper",
                    () -> EntityType.Builder.of(EntityCreeperGold::new, MobCategory.MISC)
                            .sized(0.6F, 1.8F).build("entity_mob_gold_creeper"));
    public static final DeferredHolder<EntityType<?>, EntityType<EntityCreeperNuclear>> C_RE_EP_ER_NU_CL_EA_R =
            ENTITY_TYPES.register("entity_nuclear_creeper",
                    () -> EntityType.Builder.of(EntityCreeperNuclear::new, MobCategory.MISC)
                            .sized(0.6F, 1.8F).build("entity_nuclear_creeper"));
    public static final DeferredHolder<EntityType<?>, EntityType<EntityCreeperPhosgene>> C_RE_EP_ER_PH_OS_GE_NE =
            ENTITY_TYPES.register("entity_mob_phosgene_creeper",
                    () -> EntityType.Builder.of(EntityCreeperPhosgene::new, MobCategory.MISC)
                            .sized(0.6F, 1.8F).build("entity_mob_phosgene_creeper"));
    public static final DeferredHolder<EntityType<?>, EntityType<EntityCreeperTainted>> C_RE_EP_ER_TA_IN_TE_D =
            ENTITY_TYPES.register("entity_tainted_creeper",
                    () -> EntityType.Builder.of(EntityCreeperTainted::new, MobCategory.MISC)
                            .sized(0.6F, 1.8F).build("entity_tainted_creeper"));
    public static final DeferredHolder<EntityType<?>, EntityType<EntityCreeperVolatile>> C_RE_EP_ER_VO_LA_TI_LE =
            ENTITY_TYPES.register("entity_mob_volatile_creeper",
                    () -> EntityType.Builder.of(EntityCreeperVolatile::new, MobCategory.MISC)
                            .sized(0.6F, 1.8F).build("entity_mob_volatile_creeper"));
    public static final DeferredHolder<EntityType<?>, EntityType<EntityCyberCrab>> C_YB_ER_CR_AB =
            ENTITY_TYPES.register("entity_cyber_crab",
                    () -> EntityType.Builder.of(EntityCyberCrab::new, MobCategory.CREATURE)
                            .sized(0.6F, 1.8F).build("entity_cyber_crab"));
    public static final DeferredHolder<EntityType<?>, EntityType<EntityDuck>> D_UC_K =
            ENTITY_TYPES.register("entity_fucc_a_ducc",
                    () -> EntityType.Builder.of(EntityDuck::new, MobCategory.MISC)
                            .sized(0.6F, 1.8F).build("entity_fucc_a_ducc"));
    public static final DeferredHolder<EntityType<?>, EntityType<EntityDummy>> D_UM_MY =
            ENTITY_TYPES.register("entity_ntm_test_dummy",
                    () -> EntityType.Builder.of(EntityDummy::new, MobCategory.CREATURE)
                            .sized(0.6F, 1.8F).build("entity_ntm_test_dummy"));
    public static final DeferredHolder<EntityType<?>, EntityType<EntityFBI>> F_BI =
            ENTITY_TYPES.register("entity_ntm_fbi",
                    () -> EntityType.Builder.of(EntityFBI::new, MobCategory.CREATURE)
                            .sized(0.6F, 1.8F).build("entity_ntm_fbi"));
    public static final DeferredHolder<EntityType<?>, EntityType<EntityFBIDrone>> F_BI_DR_ON_E =
            ENTITY_TYPES.register("entity_ntm_fbi_drone",
                    () -> EntityType.Builder.of(EntityFBIDrone::new, MobCategory.MISC)
                            .sized(0.6F, 1.8F).build("entity_ntm_fbi_drone"));
    public static final DeferredHolder<EntityType<?>, EntityType<EntityGlowingOne>> G_LO_WI_NG_ON_E =
            ENTITY_TYPES.register("entity_glowing_one",
                    () -> EntityType.Builder.of(EntityGlowingOne::new, MobCategory.MISC)
                            .sized(0.6F, 1.8F).build("entity_glowing_one"));
    public static final DeferredHolder<EntityType<?>, EntityType<EntityHunterChopper>> H_UN_TE_RC_HO_PP_ER =
            ENTITY_TYPES.register("entity_hunter_chopper",
                    () -> EntityType.Builder.of(EntityHunterChopper::new, MobCategory.MISC)
                            .sized(0.6F, 1.8F).build("entity_hunter_chopper"));
    public static final DeferredHolder<EntityType<?>, EntityType<EntityMaskMan>> M_AS_KM_AN =
            ENTITY_TYPES.register("entity_mask_man",
                    () -> EntityType.Builder.of(EntityMaskMan::new, MobCategory.CREATURE)
                            .sized(0.6F, 1.8F).build("entity_mask_man"));
    public static final DeferredHolder<EntityType<?>, EntityType<EntityParasiteMaggot>> P_AR_AS_IT_EM_AG_GO_T =
            ENTITY_TYPES.register("entity_parasite_maggot",
                    () -> EntityType.Builder.of(EntityParasiteMaggot::new, MobCategory.CREATURE)
                            .sized(0.6F, 1.8F).build("entity_parasite_maggot"));
    public static final DeferredHolder<EntityType<?>, EntityType<EntityPigeon>> P_IG_EO_N =
            ENTITY_TYPES.register("entity_pigeon",
                    () -> EntityType.Builder.of(EntityPigeon::new, MobCategory.CREATURE)
                            .sized(0.6F, 1.8F).build("entity_pigeon"));
    public static final DeferredHolder<EntityType<?>, EntityType<EntityQuackos>> Q_UA_CK_OS =
            ENTITY_TYPES.register("entity_elder_one",
                    () -> EntityType.Builder.of(EntityQuackos::new, MobCategory.MISC)
                            .sized(0.6F, 1.8F).build("entity_elder_one"));
    public static final DeferredHolder<EntityType<?>, EntityType<EntityRADBeast>> R_AD_BE_AS_T =
            ENTITY_TYPES.register("entity_ntm_radiation_blaze",
                    () -> EntityType.Builder.of(EntityRADBeast::new, MobCategory.CREATURE)
                            .sized(0.6F, 1.8F).build("entity_ntm_radiation_blaze"));
    public static final DeferredHolder<EntityType<?>, EntityType<EntityTaintCrab>> T_AI_NT_CR_AB =
            ENTITY_TYPES.register("entity_taint_crab",
                    () -> EntityType.Builder.of(EntityTaintCrab::new, MobCategory.MISC)
                            .sized(0.6F, 1.8F).build("entity_taint_crab"));
    public static final DeferredHolder<EntityType<?>, EntityType<EntityTeslaCrab>> T_ES_LA_CR_AB =
            ENTITY_TYPES.register("entity_tesla_crab",
                    () -> EntityType.Builder.of(EntityTeslaCrab::new, MobCategory.MISC)
                            .sized(0.6F, 1.8F).build("entity_tesla_crab"));
    public static final DeferredHolder<EntityType<?>, EntityType<EntityUFO>> U_FO =
            ENTITY_TYPES.register("entity_ntm_ufo",
                    () -> EntityType.Builder.of(EntityUFO::new, MobCategory.MISC)
                            .sized(0.6F, 1.8F).build("entity_ntm_ufo"));
    public static final DeferredHolder<EntityType<?>, EntityType<EntityUndeadSoldier>> U_ND_EA_DS_OL_DI_ER =
            ENTITY_TYPES.register("entity_ntm_undead_soldier",
                    () -> EntityType.Builder.of(EntityUndeadSoldier::new, MobCategory.CREATURE)
                            .sized(0.6F, 1.8F).build("entity_ntm_undead_soldier"));
    public static final DeferredHolder<EntityType<?>, EntityType<EntityBOTPrimeBody>> B_OT_PR_IM_EB_OD_Y =
            ENTITY_TYPES.register("entity_balls_o_tron_seg",
                    () -> EntityType.Builder.of(EntityBOTPrimeBody::new, MobCategory.MISC)
                            .sized(0.6F, 1.8F).build("entity_balls_o_tron_seg"));
    public static final DeferredHolder<EntityType<?>, EntityType<EntityBOTPrimeHead>> B_OT_PR_IM_EH_EA_D =
            ENTITY_TYPES.register("entity_balls_o_tron",
                    () -> EntityType.Builder.of(EntityBOTPrimeHead::new, MobCategory.MISC)
                            .sized(0.6F, 1.8F).build("entity_balls_o_tron"));
    public static final DeferredHolder<EntityType<?>, EntityType<EntityGlyphid>> G_LY_PH_ID =
            ENTITY_TYPES.register("entity_glyphid",
                    () -> EntityType.Builder.of(EntityGlyphid::new, MobCategory.CREATURE)
                            .sized(0.6F, 1.8F).build("entity_glyphid"));
    public static final DeferredHolder<EntityType<?>, EntityType<EntityGlyphidBehemoth>> G_LY_PH_ID_BE_HE_MO_TH =
            ENTITY_TYPES.register("entity_glyphid_behemoth",
                    () -> EntityType.Builder.of(EntityGlyphidBehemoth::new, MobCategory.MISC)
                            .sized(0.6F, 1.8F).build("entity_glyphid_behemoth"));
    public static final DeferredHolder<EntityType<?>, EntityType<EntityGlyphidBlaster>> G_LY_PH_ID_BL_AS_TE_R =
            ENTITY_TYPES.register("entity_glyphid_blaster",
                    () -> EntityType.Builder.of(EntityGlyphidBlaster::new, MobCategory.MISC)
                            .sized(0.6F, 1.8F).build("entity_glyphid_blaster"));
    public static final DeferredHolder<EntityType<?>, EntityType<EntityGlyphidBombardier>> G_LY_PH_ID_BO_MB_AR_DI_ER =
            ENTITY_TYPES.register("entity_glyphid_bombardier",
                    () -> EntityType.Builder.of(EntityGlyphidBombardier::new, MobCategory.MISC)
                            .sized(0.6F, 1.8F).build("entity_glyphid_bombardier"));
    public static final DeferredHolder<EntityType<?>, EntityType<EntityGlyphidBrawler>> G_LY_PH_ID_BR_AW_LE_R =
            ENTITY_TYPES.register("entity_glyphid_brawler",
                    () -> EntityType.Builder.of(EntityGlyphidBrawler::new, MobCategory.MISC)
                            .sized(0.6F, 1.8F).build("entity_glyphid_brawler"));
    public static final DeferredHolder<EntityType<?>, EntityType<EntityGlyphidBrenda>> G_LY_PH_ID_BR_EN_DA =
            ENTITY_TYPES.register("entity_glyphid_brenda",
                    () -> EntityType.Builder.of(EntityGlyphidBrenda::new, MobCategory.MISC)
                            .sized(0.6F, 1.8F).build("entity_glyphid_brenda"));
    public static final DeferredHolder<EntityType<?>, EntityType<EntityGlyphidDigger>> G_LY_PH_ID_DI_GG_ER =
            ENTITY_TYPES.register("entity_glyphid_digger",
                    () -> EntityType.Builder.of(EntityGlyphidDigger::new, MobCategory.MISC)
                            .sized(0.6F, 1.8F).build("entity_glyphid_digger"));
    public static final DeferredHolder<EntityType<?>, EntityType<EntityGlyphidNuclear>> G_LY_PH_ID_NU_CL_EA_R =
            ENTITY_TYPES.register("entity_glyphid_nuclear",
                    () -> EntityType.Builder.of(EntityGlyphidNuclear::new, MobCategory.MISC)
                            .sized(0.6F, 1.8F).build("entity_glyphid_nuclear"));
    public static final DeferredHolder<EntityType<?>, EntityType<EntityGlyphidScout>> G_LY_PH_ID_SC_OU_T =
            ENTITY_TYPES.register("entity_glyphid_scout",
                    () -> EntityType.Builder.of(EntityGlyphidScout::new, MobCategory.MISC)
                            .sized(0.6F, 1.8F).build("entity_glyphid_scout"));
    public static final DeferredHolder<EntityType<?>, EntityType<EntityModFXShadow>> M_OD_FX_SH_AD_OW =
            ENTITY_TYPES.register("entity_mod_fx_shadow",
                    () -> EntityType.Builder.of(EntityModFXShadow::new, MobCategory.MISC)
                            .sized(0.6F, 1.8F).build("entity_mod_fx_shadow"));
    public static final DeferredHolder<EntityType<?>, EntityType<EntityAAShell>> A_AS_HE_LL =
            ENTITY_TYPES.register("entity_aa_shell",
                    () -> EntityType.Builder.<EntityAAShell>of(EntityAAShell::new, MobCategory.MISC)
                            .sized(0.6F, 1.8F).build("entity_aa_shell"));
    public static final DeferredHolder<EntityType<?>, EntityType<EntityAcidBomb>> A_CI_DB_OM_B =
            ENTITY_TYPES.register("entity_acid_bomb",
                    () -> EntityType.Builder.<EntityAcidBomb>of(EntityAcidBomb::new, MobCategory.MISC)
                            .sized(0.6F, 1.8F).build("entity_acid_bomb"));
    public static final DeferredHolder<EntityType<?>, EntityType<EntityArtilleryRocket>> A_RT_IL_LE_RY_RO_CK_ET =
            ENTITY_TYPES.register("entity_artillery_rocket",
                    () -> EntityType.Builder.<EntityArtilleryRocket>of(EntityArtilleryRocket::new, MobCategory.MISC)
                            .sized(0.6F, 1.8F).build("entity_artillery_rocket"));
    public static final DeferredHolder<EntityType<?>, EntityType<EntityArtilleryShell>> A_RT_IL_LE_RY_SH_EL_L =
            ENTITY_TYPES.register("entity_artillery_shell",
                    () -> EntityType.Builder.<EntityArtilleryShell>of(EntityArtilleryShell::new, MobCategory.MISC)
                            .sized(0.6F, 1.8F).build("entity_artillery_shell"));
    public static final DeferredHolder<EntityType<?>, EntityType<EntityBeamVortex>> B_EA_MV_OR_TE_X =
            ENTITY_TYPES.register("entity_vortex_beam",
                    () -> EntityType.Builder.<EntityBeamVortex>of(EntityBeamVortex::new, MobCategory.MISC)
                            .sized(0.6F, 1.8F).build("entity_vortex_beam"));
    public static final DeferredHolder<EntityType<?>, EntityType<EntityBombletZeta>> B_OM_BL_ET_ZE_TA =
            ENTITY_TYPES.register("entity_zeta",
                    () -> EntityType.Builder.<EntityBombletZeta>of(EntityBombletZeta::new, MobCategory.MISC)
                            .sized(0.6F, 1.8F).build("entity_zeta"));
    public static final DeferredHolder<EntityType<?>, EntityType<EntityBoxcar>> B_OX_CA_R =
            ENTITY_TYPES.register("entity_boxcar",
                    () -> EntityType.Builder.<EntityBoxcar>of(EntityBoxcar::new, MobCategory.MISC)
                            .sized(0.6F, 1.8F).build("entity_boxcar"));
    public static final DeferredHolder<EntityType<?>, EntityType<EntityBuilding>> B_UI_LD_IN_G =
            ENTITY_TYPES.register("entity_building",
                    () -> EntityType.Builder.<EntityBuilding>of(EntityBuilding::new, MobCategory.MISC)
                            .sized(0.6F, 1.8F).build("entity_building"));
    public static final DeferredHolder<EntityType<?>, EntityType<EntityBullet>> B_UL_LE_T =
            ENTITY_TYPES.register("entity_bullet",
                    () -> EntityType.Builder.<EntityBullet>of(EntityBullet::new, MobCategory.MISC)
                            .sized(0.6F, 1.8F).build("entity_bullet"));
    public static final DeferredHolder<EntityType<?>, EntityType<EntityBulletBase>> B_UL_LE_TB_AS_E =
            ENTITY_TYPES.register("entity_bullet_mk2",
                    () -> EntityType.Builder.<EntityBulletBase>of(EntityBulletBase::new, MobCategory.MISC)
                            .sized(0.6F, 1.8F).build("entity_bullet_mk2"));
    public static final DeferredHolder<EntityType<?>, EntityType<EntityBulletBaseMK4>> B_UL_LE_TB_AS_EM_K4 =
            ENTITY_TYPES.register("entity_bullet_mk4",
                    () -> EntityType.Builder.<EntityBulletBaseMK4>of(EntityBulletBaseMK4::new, MobCategory.MISC)
                            .sized(0.6F, 1.8F).build("entity_bullet_mk4"));
    public static final DeferredHolder<EntityType<?>, EntityType<EntityBulletBaseMK4CL>> B_UL_LE_TB_AS_EM_K4C_L =
            ENTITY_TYPES.register("entity_bullet_mk4_cl",
                    () -> EntityType.Builder.<EntityBulletBaseMK4CL>of(EntityBulletBaseMK4CL::new, MobCategory.MISC)
                            .sized(0.6F, 1.8F).build("entity_bullet_mk4_cl"));
    public static final DeferredHolder<EntityType<?>, EntityType<EntityBulletBaseNT>> B_UL_LE_TB_AS_EN_T =
            ENTITY_TYPES.register("entity_bullet_mk3",
                    () -> EntityType.Builder.<EntityBulletBaseNT>of(EntityBulletBaseNT::new, MobCategory.MISC)
                            .sized(0.6F, 1.8F).build("entity_bullet_mk3"));
    public static final DeferredHolder<EntityType<?>, EntityType<EntityBulletBeamBase>> B_UL_LE_TB_EA_MB_AS_E =
            ENTITY_TYPES.register("entity_beam_mk4",
                    () -> EntityType.Builder.<EntityBulletBeamBase>of(EntityBulletBeamBase::new, MobCategory.MISC)
                            .sized(0.6F, 1.8F).build("entity_beam_mk4"));
    public static final DeferredHolder<EntityType<?>, EntityType<EntityBurningFOEQ>> B_UR_NI_NG_FO_EQ =
            ENTITY_TYPES.register("entity_burning_foeq",
                    () -> EntityType.Builder.<EntityBurningFOEQ>of(EntityBurningFOEQ::new, MobCategory.MISC)
                            .sized(0.6F, 1.8F).build("entity_burning_foeq"));
    public static final DeferredHolder<EntityType<?>, EntityType<EntityChemical>> C_HE_MI_CA_L =
            ENTITY_TYPES.register("entity_chemthrower_splash",
                    () -> EntityType.Builder.<EntityChemical>of(EntityChemical::new, MobCategory.MISC)
                            .sized(0.6F, 1.8F).build("entity_chemthrower_splash"));
    public static final DeferredHolder<EntityType<?>, EntityType<EntityChopperMine>> C_HO_PP_ER_MI_NE =
            ENTITY_TYPES.register("entity_chopper_mine",
                    () -> EntityType.Builder.<EntityChopperMine>of(EntityChopperMine::new, MobCategory.MISC)
                            .sized(0.6F, 1.8F).build("entity_chopper_mine"));
    public static final DeferredHolder<EntityType<?>, EntityType<EntityCog>> C_OG =
            ENTITY_TYPES.register("entity_cog",
                    () -> EntityType.Builder.<EntityCog>of(EntityCog::new, MobCategory.MISC)
                            .sized(0.6F, 1.8F).build("entity_cog"));
    public static final DeferredHolder<EntityType<?>, EntityType<EntityCoin>> C_OI_N =
            ENTITY_TYPES.register("entity_coin",
                    () -> EntityType.Builder.<EntityCoin>of(EntityCoin::new, MobCategory.MISC)
                            .sized(0.6F, 1.8F).build("entity_coin"));
    public static final DeferredHolder<EntityType<?>, EntityType<EntityCombineBall>> C_OM_BI_NE_BA_LL =
            ENTITY_TYPES.register("entity_combine_ball",
                    () -> EntityType.Builder.<EntityCombineBall>of(EntityCombineBall::new, MobCategory.MISC)
                            .sized(0.6F, 1.8F).build("entity_combine_ball"));
    public static final DeferredHolder<EntityType<?>, EntityType<EntityDischarge>> D_IS_CH_AR_GE =
            ENTITY_TYPES.register("entity_discharge",
                    () -> EntityType.Builder.<EntityDischarge>of(EntityDischarge::new, MobCategory.MISC)
                            .sized(0.6F, 1.8F).build("entity_discharge"));
    public static final DeferredHolder<EntityType<?>, EntityType<EntityDuchessGambit>> D_UC_HE_SS_GA_MB_IT =
            ENTITY_TYPES.register("entity_duchessgambit",
                    () -> EntityType.Builder.<EntityDuchessGambit>of(EntityDuchessGambit::new, MobCategory.MISC)
                            .sized(0.6F, 1.8F).build("entity_duchessgambit"));
    public static final DeferredHolder<EntityType<?>, EntityType<EntityExplosiveBeam>> E_XP_LO_SI_VE_BE_AM =
            ENTITY_TYPES.register("entity_explosive_beam",
                    () -> EntityType.Builder.<EntityExplosiveBeam>of(EntityExplosiveBeam::new, MobCategory.MISC)
                            .sized(0.6F, 1.8F).build("entity_explosive_beam"));
    public static final DeferredHolder<EntityType<?>, EntityType<EntityFallingNuke>> F_AL_LI_NG_NU_KE =
            ENTITY_TYPES.register("entity_falling_bomb",
                    () -> EntityType.Builder.<EntityFallingNuke>of(EntityFallingNuke::new, MobCategory.MISC)
                            .sized(0.6F, 1.8F).build("entity_falling_bomb"));
    public static final DeferredHolder<EntityType<?>, EntityType<EntityFire>> F_IR_E =
            ENTITY_TYPES.register("entity_fire",
                    () -> EntityType.Builder.<EntityFire>of(EntityFire::new, MobCategory.MISC)
                            .sized(0.6F, 1.8F).build("entity_fire"));
    public static final DeferredHolder<EntityType<?>, EntityType<EntityLaser>> L_AS_ER =
            ENTITY_TYPES.register("entity_laser",
                    () -> EntityType.Builder.<EntityLaser>of(EntityLaser::new, MobCategory.MISC)
                            .sized(0.6F, 1.8F).build("entity_laser"));
    public static final DeferredHolder<EntityType<?>, EntityType<EntityLaserBeam>> L_AS_ER_BE_AM =
            ENTITY_TYPES.register("entity_laser_beam",
                    () -> EntityType.Builder.<EntityLaserBeam>of(EntityLaserBeam::new, MobCategory.MISC)
                            .sized(0.6F, 1.8F).build("entity_laser_beam"));
    public static final DeferredHolder<EntityType<?>, EntityType<EntityLN2>> L_N2 =
            ENTITY_TYPES.register("entity_ln2",
                    () -> EntityType.Builder.of(EntityLN2::new, MobCategory.MISC)
                            .sized(0.6F, 1.8F).build("entity_ln2"));
    public static final DeferredHolder<EntityType<?>, EntityType<EntityMeteor>> M_ET_EO_R =
            ENTITY_TYPES.register("entity_meteor",
                    () -> EntityType.Builder.<EntityMeteor>of(EntityMeteor::new, MobCategory.MISC)
                            .sized(0.6F, 1.8F).build("entity_meteor"));
    public static final DeferredHolder<EntityType<?>, EntityType<EntityMinerBeam>> M_IN_ER_BE_AM =
            ENTITY_TYPES.register("entity_miner_beam",
                    () -> EntityType.Builder.<EntityMinerBeam>of(EntityMinerBeam::new, MobCategory.MISC)
                            .sized(0.6F, 1.8F).build("entity_miner_beam"));
    public static final DeferredHolder<EntityType<?>, EntityType<EntityMiniMIRV>> M_IN_IM_IR_V =
            ENTITY_TYPES.register("entity_mini_mirv",
                    () -> EntityType.Builder.<EntityMiniMIRV>of(EntityMiniMIRV::new, MobCategory.MISC)
                            .sized(0.6F, 1.8F).build("entity_mini_mirv"));
    public static final DeferredHolder<EntityType<?>, EntityType<EntityMiniNuke>> M_IN_IN_UK_E =
            ENTITY_TYPES.register("entity_mini_nuke",
                    () -> EntityType.Builder.<EntityMiniNuke>of(EntityMiniNuke::new, MobCategory.MISC)
                            .sized(0.6F, 1.8F).build("entity_mini_nuke"));
    public static final DeferredHolder<EntityType<?>, EntityType<EntityModBeam>> M_OD_BE_AM =
            ENTITY_TYPES.register("entity_mod_beam",
                    () -> EntityType.Builder.<EntityModBeam>of(EntityModBeam::new, MobCategory.MISC)
                            .sized(0.6F, 1.8F).build("entity_mod_beam"));
    public static final DeferredHolder<EntityType<?>, EntityType<EntityPlasmaBeam>> P_LA_SM_AB_EA_M =
            ENTITY_TYPES.register("entity_plasma_beam",
                    () -> EntityType.Builder.<EntityPlasmaBeam>of(EntityPlasmaBeam::new, MobCategory.MISC)
                            .sized(0.6F, 1.8F).build("entity_plasma_beam"));
    public static final DeferredHolder<EntityType<?>, EntityType<EntityRailgunBlast>> R_AI_LG_UN_BL_AS_T =
            ENTITY_TYPES.register("entity_railgun_pellet",
                    () -> EntityType.Builder.of(EntityRailgunBlast::new, MobCategory.MISC)
                            .sized(0.6F, 1.8F).build("entity_railgun_pellet"));
    public static final DeferredHolder<EntityType<?>, EntityType<EntityRainbow>> R_AI_NB_OW =
            ENTITY_TYPES.register("entity_rainbow",
                    () -> EntityType.Builder.<EntityRainbow>of(EntityRainbow::new, MobCategory.MISC)
                            .sized(0.6F, 1.8F).build("entity_rainbow"));
    public static final DeferredHolder<EntityType<?>, EntityType<EntityRBMKDebris>> R_BM_KD_EB_RI_S =
            ENTITY_TYPES.register("entity_rbmk_debris",
                    () -> EntityType.Builder.<EntityRBMKDebris>of(EntityRBMKDebris::new, MobCategory.MISC)
                            .sized(0.6F, 1.8F).build("entity_rbmk_debris"));
    public static final DeferredHolder<EntityType<?>, EntityType<EntityRocket>> R_OC_KE_T =
            ENTITY_TYPES.register("entity_rocket",
                    () -> EntityType.Builder.<EntityRocket>of(EntityRocket::new, MobCategory.MISC)
                            .sized(0.6F, 1.8F).build("entity_rocket"));
    public static final DeferredHolder<EntityType<?>, EntityType<EntityRubble>> R_UB_BL_E =
            ENTITY_TYPES.register("entity_rubble",
                    () -> EntityType.Builder.<EntityRubble>of(EntityRubble::new, MobCategory.MISC)
                            .sized(0.6F, 1.8F).build("entity_rubble"));
    public static final DeferredHolder<EntityType<?>, EntityType<EntitySawblade>> S_AW_BL_AD_E =
            ENTITY_TYPES.register("entity_sawblade",
                    () -> EntityType.Builder.<EntitySawblade>of(EntitySawblade::new, MobCategory.MISC)
                            .sized(0.6F, 1.8F).build("entity_sawblade"));
    public static final DeferredHolder<EntityType<?>, EntityType<EntitySchrab>> S_CH_RA_B =
            ENTITY_TYPES.register("entity_schrab",
                    () -> EntityType.Builder.<EntitySchrab>of(EntitySchrab::new, MobCategory.MISC)
                            .sized(0.6F, 1.8F).build("entity_schrab"));
    public static final DeferredHolder<EntityType<?>, EntityType<EntityShrapnel>> S_HR_AP_NE_L =
            ENTITY_TYPES.register("entity_shrapnel",
                    () -> EntityType.Builder.<EntityShrapnel>of(EntityShrapnel::new, MobCategory.MISC)
                            .sized(0.6F, 1.8F).build("entity_shrapnel"));
    public static final DeferredHolder<EntityType<?>, EntityType<EntitySparkBeam>> S_PA_RK_BE_AM =
            ENTITY_TYPES.register("entity_spark_beam",
                    () -> EntityType.Builder.<EntitySparkBeam>of(EntitySparkBeam::new, MobCategory.MISC)
                            .sized(0.6F, 1.8F).build("entity_spark_beam"));
    public static final DeferredHolder<EntityType<?>, EntityType<EntityTom>> T_OM =
            ENTITY_TYPES.register("entity_tom_the_moonstone",
                    () -> EntityType.Builder.<EntityTom>of(EntityTom::new, MobCategory.MISC)
                            .sized(0.6F, 1.8F).build("entity_tom_the_moonstone"));
    public static final DeferredHolder<EntityType<?>, EntityType<EntityTorpedo>> T_OR_PE_DO =
            ENTITY_TYPES.register("entity_torpedo",
                    () -> EntityType.Builder.<EntityTorpedo>of(EntityTorpedo::new, MobCategory.MISC)
                            .sized(0.6F, 1.8F).build("entity_torpedo"));
    public static final DeferredHolder<EntityType<?>, EntityType<EntityZirnoxDebris>> Z_IR_NO_XD_EB_RI_S =
            ENTITY_TYPES.register("entity_zirnox_debris",
                    () -> EntityType.Builder.<EntityZirnoxDebris>of(EntityZirnoxDebris::new, MobCategory.MISC)
                            .sized(0.6F, 1.8F).build("entity_zirnox_debris"));
}