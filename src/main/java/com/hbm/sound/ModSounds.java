package com.hbm.sound;

import com.hbm.Tags;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.neoforge.registries.DeferredRegister;

/**
 * 音效注册中心。
 * 迁移自 1.12.2 com.hbm.lib.HBMSoundHandler（new SoundEvent(ResourceLocation) 直接实例化）。
 */
public class ModSounds {

    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(Registries.SOUND_EVENT, Tags.MODID);

    // ===== P8 迁移模板 =====
    //
    // 原 1.12.2：public static final SoundEvent rad_geiger = new SoundEvent(ResourceLocation.parse("hbm:item.geiger"));
    //
    // 1.21.1：
    //   public static final DeferredHolder<SoundEvent, SoundEvent> GEIGER = SOUNDS.register("item.geiger",
    //           () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "item.geiger")));

    // ===== P5.1b-1：TileEntityLoadedBase 所需 =====
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> METAL_IMPACT = SOUNDS.register("block.metalimpact",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "block.metalimpact")));

    // ===== P5.1b-1 声音批：CE HBMSoundHandler 379 项批量注册 =====
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> ALARM_HATCH = SOUNDS.register("alarm.hatch",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "alarm.hatch")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> ALARM_AUTOPILOT = SOUNDS.register("alarm.autopilot",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "alarm.autopilot")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> ALARM_AMSSIREN = SOUNDS.register("alarm.amssiren",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "alarm.amssiren")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> ALARM_BLASTDOORALARM = SOUNDS.register("alarm.blastdooralarm",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "alarm.blastdooralarm")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> ALARM_APCLOOP = SOUNDS.register("alarm.apcloop",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "alarm.apcloop")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> ALARM_KLAXON = SOUNDS.register("alarm.klaxon",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "alarm.klaxon")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> ALARM_FOKLAXONA = SOUNDS.register("alarm.foklaxona",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "alarm.foklaxona")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> ALARM_FOKLAXONB = SOUNDS.register("alarm.foklaxonb",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "alarm.foklaxonb")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> ALARM_REGULARSIREN = SOUNDS.register("alarm.regularsiren",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "alarm.regularsiren")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> ALARM_CLASSIC = SOUNDS.register("alarm.classic",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "alarm.classic")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> ALARM_BANKALARM = SOUNDS.register("alarm.bankalarm",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "alarm.bankalarm")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> ALARM_BEEPSIREN = SOUNDS.register("alarm.beepsiren",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "alarm.beepsiren")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> ALARM_CONTAINERALARM = SOUNDS.register("alarm.containeralarm",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "alarm.containeralarm")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> ALARM_SWEEPSIREN = SOUNDS.register("alarm.sweepsiren",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "alarm.sweepsiren")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> ALARM_STRIDERSIREN = SOUNDS.register("alarm.stridersiren",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "alarm.stridersiren")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> ALARM_AIRRAID = SOUNDS.register("alarm.airraid",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "alarm.airraid")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> ALARM_NOSTROMOSIREN = SOUNDS.register("alarm.nostromosiren",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "alarm.nostromosiren")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> ALARM_EASALARM = SOUNDS.register("alarm.easalarm",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "alarm.easalarm")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> ALARM_APCPASS = SOUNDS.register("alarm.apcpass",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "alarm.apcpass")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> ALARM_RAZORTRAINHORN = SOUNDS.register("alarm.razortrainhorn",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "alarm.razortrainhorn")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> ALARM_SOYUZED = SOUNDS.register("alarm.soyuzed",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "alarm.soyuzed")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> STEP_METAL = SOUNDS.register("step.metal",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "step.metal")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> STEP_IRON = SOUNDS.register("step.iron",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "step.iron")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> STEP_IRON_LAND = SOUNDS.register("step.iron_land",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "step.iron_land")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> STEP_IRON_JUMP = SOUNDS.register("step.iron_jump",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "step.iron_jump")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> STEP_POWERED = SOUNDS.register("step.powered",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "step.powered")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> MUSIC_RECORDLAMBDACORE = SOUNDS.register("music.recordlambdacore",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "music.recordlambdacore")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> MUSIC_RECORDSECTORSWEEP = SOUNDS.register("music.recordsectorsweep",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "music.recordsectorsweep")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> MUSIC_RECORDVORTALCOMBAT = SOUNDS.register("music.recordvortalcombat",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "music.recordvortalcombat")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> MUSIC_TRANSMISSION = SOUNDS.register("music.transmission",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "music.transmission")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> STEP_METALBLOCK = SOUNDS.register("step.metalblock",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "step.metalblock")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> WEAPON_EXPLOSION_SMALL_NEAR = SOUNDS.register("weapon.explosion_small_near",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "weapon.explosion_small_near")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> WEAPON_EXPLOSION_SMALL_FAR = SOUNDS.register("weapon.explosion_small_far",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "weapon.explosion_small_far")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> WEAPON_EXPLOSION_LARGE_NEAR = SOUNDS.register("weapon.explosion_large_near",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "weapon.explosion_large_near")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> WEAPON_EXPLOSION_LARGE_FAR = SOUNDS.register("weapon.explosion_large_far",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "weapon.explosion_large_far")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> BLOCK_FEL = SOUNDS.register("block.fel",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "block.fel")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> BLOCK_FENSUHUM = SOUNDS.register("block.fensuhum",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "block.fensuhum")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> BLOCK_HEPHAESTUSRUNNING = SOUNDS.register("block.hephaestusrunning",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "block.hephaestusrunning")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> ENTITY_METEORITEFALLINGLOOP = SOUNDS.register("entity.meteoritefallingloop",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "entity.meteoritefallingloop")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> BLOCK_PRESSOPERATE = SOUNDS.register("block.pressoperate",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "block.pressoperate")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> WEAPON_LASERBANG = SOUNDS.register("weapon.laserbang",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "weapon.laserbang")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> BLOCK_DEBRIS = SOUNDS.register("block.debris",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "block.debris")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> BLOCK_RBMKLID = SOUNDS.register("block.rbmklid",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "block.rbmklid")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> ITEM_SYRINGE = SOUNDS.register("item.syringe",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "item.syringe")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> WEAPON_SPARKSHOOT = SOUNDS.register("weapon.sparkshoot",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "weapon.sparkshoot")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> BLOCK_LEVERSTART = SOUNDS.register("block.leverstart",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "block.leverstart")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> BLOCK_LEVERSTOP = SOUNDS.register("block.leverstop",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "block.leverstop")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> BLOCK_SPARK = SOUNDS.register("block.spark",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "block.spark")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> WEAPON_B92RELOAD = SOUNDS.register("weapon.b92reload",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "weapon.b92reload")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> ITEM_TECHBLEEP = SOUNDS.register("item.techbleep",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "item.techbleep")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> ITEM_TECHBOOP = SOUNDS.register("item.techboop",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "item.techboop")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> BLOCK_HORNNEARSINGLE = SOUNDS.register("block.hornnearsingle",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "block.hornnearsingle")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> BLOCK_LARGETURBINE = SOUNDS.register("block.largeturbine",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "block.largeturbine")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> BLOCK_ENGINE = SOUNDS.register("block.engine",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "block.engine")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> BLOCK_HORNNEARDUAL = SOUNDS.register("block.hornneardual",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "block.hornneardual")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> BLOCK_HORNFARSINGLE = SOUNDS.register("block.hornfarsingle",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "block.hornfarsingle")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> BLOCK_HORNFARDUAL = SOUNDS.register("block.hornfardual",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "block.hornfardual")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> BLOCK_REACTORLOOP = SOUNDS.register("block.reactorloop",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "block.reactorloop")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> BLOCK_REACTORSTART = SOUNDS.register("block.reactorstart",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "block.reactorstart")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> BLOCK_REACTORSTOP = SOUNDS.register("block.reactorstop",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "block.reactorstop")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> BLOCK_CHEMICALPLANT = SOUNDS.register("block.chemicalplant",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "block.chemicalplant")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> POTATOS_RANDOM = SOUNDS.register("potatos.random",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "potatos.random")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> WEAPON_SPINDOWN = SOUNDS.register("weapon.spindown",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "weapon.spindown")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> WEAPON_SPINUP = SOUNDS.register("weapon.spinup",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "weapon.spinup")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> WEAPON_SAWSHOOT = SOUNDS.register("weapon.sawshoot",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "weapon.sawshoot")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> WEAPON_RPGSHOOT = SOUNDS.register("weapon.rpgshoot",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "weapon.rpgshoot")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> WEAPON_RELOADTURRET = SOUNDS.register("weapon.reloadturret",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "weapon.reloadturret")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> WEAPON_RIFLESHOOT = SOUNDS.register("weapon.rifleshoot",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "weapon.rifleshoot")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> WEAPON_DEFABSHOOT = SOUNDS.register("weapon.defabshoot",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "weapon.defabshoot")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> WEAPON_FLAMETHROWERIGNITE = SOUNDS.register("weapon.flamethrowerignite",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "weapon.flamethrowerignite")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> WEAPON_FLAMETHROWERSHOOT = SOUNDS.register("weapon.flamethrowershoot",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "weapon.flamethrowershoot")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> WEAPON_TAUSHOOT = SOUNDS.register("weapon.taushoot",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "weapon.taushoot")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> ENTITY_OLDEXPLOSION = SOUNDS.register("entity.oldexplosion",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "entity.oldexplosion")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> WEAPON_NUCLEAREXPLOSION = SOUNDS.register("weapon.nuclearexplosion",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "weapon.nuclearexplosion")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> WEAPON_ROBIN_EXPLOSION = SOUNDS.register("weapon.robin_explosion",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "weapon.robin_explosion")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> BLOCK_BOILER = SOUNDS.register("block.boiler",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "block.boiler")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> BLOCK_BOILERGROAN0 = SOUNDS.register("block.boilergroan0",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "block.boilergroan0")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> BLOCK_BOILERGROAN1 = SOUNDS.register("block.boilergroan1",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "block.boilergroan1")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> BLOCK_BOILERGROAN2 = SOUNDS.register("block.boilergroan2",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "block.boilergroan2")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> WEAPON_CIWSSPINDOWN = SOUNDS.register("weapon.ciwsspindown",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "weapon.ciwsspindown")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> WEAPON_CIWSSPINUP = SOUNDS.register("weapon.ciwsspinup",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "weapon.ciwsspinup")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> WEAPON_CIWSFIRINGLOOP = SOUNDS.register("weapon.ciwsfiringloop",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "weapon.ciwsfiringloop")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> BLOCK_WARNOVERSPEED = SOUNDS.register("block.warnoverspeed",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "block.warnoverspeed")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> ENTITY_PLANESHOTDOWN = SOUNDS.register("entity.planeshotdown",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "entity.planeshotdown")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> ENTITY_BOMBWHISTLE = SOUNDS.register("entity.bombwhistle",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "entity.bombwhistle")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> ENTITY_MORTARWHISTLE = SOUNDS.register("entity.mortarwhistle",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "entity.mortarwhistle")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> ENTITY_PLANECRASH = SOUNDS.register("entity.planecrash",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "entity.planecrash")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> WEAPON_MISSILETAKEOFF = SOUNDS.register("weapon.missiletakeoff",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "weapon.missiletakeoff")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> ENTITY_BOMBERSMALLLOOP = SOUNDS.register("entity.bombersmallloop",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "entity.bombersmallloop")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> ENTITY_BOMBERLOOP = SOUNDS.register("entity.bomberloop",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "entity.bomberloop")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> WEAPON_STINGERLOCKON = SOUNDS.register("weapon.stingerlockon",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "weapon.stingerlockon")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> ALARM_TRAINHORN = SOUNDS.register("alarm.trainhorn",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "alarm.trainhorn")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> ENTITY_BOMBDET = SOUNDS.register("entity.bombdet",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "entity.bombdet")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> MISC_RUMBLE = SOUNDS.register("misc.rumble",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "misc.rumble")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> ENTITY_PIPEFAIL = SOUNDS.register("entity.pipefail",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "entity.pipefail")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> MISC_LPWSTART = SOUNDS.register("misc.lpwstart",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "misc.lpwstart")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> MISC_LPWSTOP = SOUNDS.register("misc.lpwstop",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "misc.lpwstop")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> MISC_LPWLOOP = SOUNDS.register("misc.lpwloop",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "misc.lpwloop")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> MISC_HTRSTART = SOUNDS.register("misc.htrstart",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "misc.htrstart")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> MISC_HTRSTOP = SOUNDS.register("misc.htrstop",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "misc.htrstop")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> MISC_HTRLOOP = SOUNDS.register("misc.htrloop",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "misc.htrloop")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> ENTITY_ROCKETTAKEOFF = SOUNDS.register("entity.rockettakeoff",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "entity.rockettakeoff")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> ENTITY_ROCKETIGNITION = SOUNDS.register("entity.rocketignition",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "entity.rocketignition")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> ENTITY_ROCKETFLYLIGHT = SOUNDS.register("entity.rocketflylight",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "entity.rocketflylight")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> ENTITY_ROCKETFLYHEAVY = SOUNDS.register("entity.rocketflyheavy",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "entity.rocketflyheavy")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> WEAPON_SILENCERSHOOT = SOUNDS.register("weapon.silencershoot",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "weapon.silencershoot")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> WEAPON_RPGRELOAD = SOUNDS.register("weapon.rpgreload",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "weapon.rpgreload")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> WEAPON_HKRELOAD = SOUNDS.register("weapon.hkreload",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "weapon.hkreload")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> WEAPON_SHOTGUNRELOAD = SOUNDS.register("weapon.shotgunreload",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "weapon.shotgunreload")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> WEAPON_MAGRELOAD = SOUNDS.register("weapon.magreload",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "weapon.magreload")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> WEAPON_REVOLVERRELOAD = SOUNDS.register("weapon.revolverreload",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "weapon.revolverreload")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> WEAPON_FATMANRELOAD = SOUNDS.register("weapon.fatmanreload",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "weapon.fatmanreload")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> WEAPON_BOAT = SOUNDS.register("weapon.boat",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "weapon.boat")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> WEAPON_RICOCHET = SOUNDS.register("weapon.ricochet",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "weapon.ricochet")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> WEAPON_GBOUNCE = SOUNDS.register("weapon.gbounce",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "weapon.gbounce")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> ALARM_GAMBIT = SOUNDS.register("alarm.gambit",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "alarm.gambit")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> WEAPON_REVOLVERSHOOT = SOUNDS.register("weapon.revolvershoot",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "weapon.revolvershoot")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> WEAPON_HEAVYSHOOT = SOUNDS.register("weapon.heavyshoot",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "weapon.heavyshoot")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> WEAPON_SCHRABIDIUMSHOOT = SOUNDS.register("weapon.schrabidiumshoot",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "weapon.schrabidiumshoot")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> WEAPON_REVOLVERSHOOTALT = SOUNDS.register("weapon.revolvershootalt",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "weapon.revolvershootalt")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> WEAPON_HKSHOOT = SOUNDS.register("weapon.hkshoot",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "weapon.hkshoot")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> WEAPON_SHOTGUNSHOOT = SOUNDS.register("weapon.shotgunshoot",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "weapon.shotgunshoot")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> WEAPON_SHOTTYSHOOT = SOUNDS.register("weapon.shottyshoot",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "weapon.shottyshoot")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> WEAPON_UZISHOOT = SOUNDS.register("weapon.uzishoot",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "weapon.uzishoot")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> WEAPON_CALSHOOT = SOUNDS.register("weapon.calshoot",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "weapon.calshoot")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> WEAPON_LACUNAESHOOT = SOUNDS.register("weapon.lacunaeshoot",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "weapon.lacunaeshoot")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> WEAPON_FATMANSHOOT = SOUNDS.register("weapon.fatmanshoot",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "weapon.fatmanshoot")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> WEAPON_OSIPRSHOOT = SOUNDS.register("weapon.osiprshoot",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "weapon.osiprshoot")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> WEAPON_ZOMGSHOOT = SOUNDS.register("weapon.zomgshoot",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "weapon.zomgshoot")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> ITEM_JETPACKTANK = SOUNDS.register("item.jetpacktank",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "item.jetpacktank")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> WEAPON_SWITCHMODE1 = SOUNDS.register("weapon.switchmode1",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "weapon.switchmode1")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> WEAPON_SWITCHMODE2 = SOUNDS.register("weapon.switchmode2",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "weapon.switchmode2")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> MISC_NULLTAU = SOUNDS.register("misc.nulltau",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "misc.nulltau")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> MISC_NULLRADAR = SOUNDS.register("misc.nullradar",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "misc.nullradar")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> WEAPON_IMMOLATORIGNITE = SOUNDS.register("weapon.immolatorignite",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "weapon.immolatorignite")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> WEAPON_IMMOLATORSHOOT = SOUNDS.register("weapon.immolatorshoot",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "weapon.immolatorshoot")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> WEAPON_DEFABSPINUP = SOUNDS.register("weapon.defabspinup",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "weapon.defabspinup")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> WEAPON_CRYOLATORSHOOT = SOUNDS.register("weapon.cryolatorshoot",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "weapon.cryolatorshoot")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> WEAPON_SINGFLYBY = SOUNDS.register("weapon.singflyby",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "weapon.singflyby")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> WEAPON_OSIPRCHARGING = SOUNDS.register("weapon.osiprcharging",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "weapon.osiprcharging")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> WEAPON_LEVERACTIONRELOAD = SOUNDS.register("weapon.leveractionreload",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "weapon.leveractionreload")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> WEAPON_FOLLYOPEN = SOUNDS.register("weapon.follyopen",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "weapon.follyopen")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> WEAPON_FOLLYRELOAD = SOUNDS.register("weapon.follyreload",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "weapon.follyreload")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> WEAPON_FOLLYCLOSE = SOUNDS.register("weapon.follyclose",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "weapon.follyclose")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> WEAPON_FOLLYFIRE = SOUNDS.register("weapon.follyfire",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "weapon.follyfire")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> WEAPON_FOLLYBUZZER = SOUNDS.register("weapon.follybuzzer",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "weapon.follybuzzer")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> WEAPON_FOLLYAQUIRED = SOUNDS.register("weapon.follyaquired",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "weapon.follyaquired")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> ENTITY_CHOPPERDROP = SOUNDS.register("entity.chopperdrop",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "entity.chopperdrop")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> BLOCK_PYROOPERATE = SOUNDS.register("block.pyrooperate",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "block.pyrooperate")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> BLOCK_ELECTRICHUM = SOUNDS.register("block.electrichum",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "block.electrichum")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> BLOCK_CRATEBREAK = SOUNDS.register("block.cratebreak",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "block.cratebreak")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> ITEM_UNPACK = SOUNDS.register("item.unpack",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "item.unpack")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> BLOCK_CENTRIFUGEOPERATE = SOUNDS.register("block.centrifugeoperate",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "block.centrifugeoperate")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> BLOCK_BUTTONNO = SOUNDS.register("block.buttonno",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "block.buttonno")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> BLOCK_BUTTONYES = SOUNDS.register("block.buttonyes",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "block.buttonyes")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> BLOCK_RAILGUNFIRE = SOUNDS.register("block.railgunfire",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "block.railgunfire")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> BLOCK_RAILGUNORIENTATION = SOUNDS.register("block.railgunorientation",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "block.railgunorientation")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> BLOCK_RAILGUNCHARGE = SOUNDS.register("block.railguncharge",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "block.railguncharge")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> BLOCK_SHUTDOWN = SOUNDS.register("block.shutdown",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "block.shutdown")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> BLOCK_BROADCAST1 = SOUNDS.register("block.broadcast1",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "block.broadcast1")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> BLOCK_BROADCAST2 = SOUNDS.register("block.broadcast2",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "block.broadcast2")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> BLOCK_BROADCAST3 = SOUNDS.register("block.broadcast3",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "block.broadcast3")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> ITEM_GEIGER1 = SOUNDS.register("item.geiger1",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "item.geiger1")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> ITEM_GEIGER2 = SOUNDS.register("item.geiger2",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "item.geiger2")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> ITEM_GEIGER3 = SOUNDS.register("item.geiger3",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "item.geiger3")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> ITEM_GEIGER4 = SOUNDS.register("item.geiger4",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "item.geiger4")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> ITEM_GEIGER5 = SOUNDS.register("item.geiger5",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "item.geiger5")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> ITEM_GEIGER6 = SOUNDS.register("item.geiger6",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "item.geiger6")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> ITEM_VOICES1 = SOUNDS.register("item.voices1",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "item.voices1")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> ITEM_VOICES2 = SOUNDS.register("item.voices2",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "item.voices2")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> ITEM_VOICES3 = SOUNDS.register("item.voices3",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "item.voices3")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> ITEM_VOICES4 = SOUNDS.register("item.voices4",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "item.voices4")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> ITEM_VOICES5 = SOUNDS.register("item.voices5",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "item.voices5")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> ITEM_VOICES6 = SOUNDS.register("item.voices6",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "item.voices6")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> ITEM_VOICES7 = SOUNDS.register("item.voices7",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "item.voices7")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> ITEM_VOICES8 = SOUNDS.register("item.voices8",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "item.voices8")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> BLOCK_LOCKOPEN = SOUNDS.register("block.lockopen",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "block.lockopen")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> ITEM_PINBREAK = SOUNDS.register("item.pinbreak",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "item.pinbreak")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> ITEM_PINUNLOCK = SOUNDS.register("item.pinunlock",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "item.pinunlock")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> BLOCK_LOCKHANG = SOUNDS.register("block.lockhang",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "block.lockhang")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> BLOCK_VAULTSCRAPENEW = SOUNDS.register("block.vaultscrapenew",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "block.vaultscrapenew")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> BLOCK_VAULTTHUDNEW = SOUNDS.register("block.vaultthudnew",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "block.vaultthudnew")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> BLOCK_MISSILEASSEMBLY2 = SOUNDS.register("block.missileassembly2",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "block.missileassembly2")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> BLOCK_SONARPING = SOUNDS.register("block.sonarping",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "block.sonarping")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> ITEM_RADAWAY = SOUNDS.register("item.radaway",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "item.radaway")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> ITEM_GASMASKSCREW = SOUNDS.register("item.gasmaskscrew",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "item.gasmaskscrew")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> ITEM_SPRAY = SOUNDS.register("item.spray",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "item.spray")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> ITEM_REPAIR = SOUNDS.register("item.repair",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "item.repair")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> MISC_NULLCHOPPER = SOUNDS.register("misc.nullchopper",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "misc.nullchopper")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> ENTITY_CHOPPERCHARGE = SOUNDS.register("entity.choppercharge",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "entity.choppercharge")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> MISC_NULLCRASHING = SOUNDS.register("misc.nullcrashing",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "misc.nullcrashing")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> ENTITY_CHOPPERDAMAGE = SOUNDS.register("entity.chopperdamage",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "entity.chopperdamage")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> MISC_NULLMINE = SOUNDS.register("misc.nullmine",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "misc.nullmine")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> BLOCK_OPENDOOR = SOUNDS.register("block.opendoor",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "block.opendoor")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> BLOCK_CLOSEDOOR = SOUNDS.register("block.closedoor",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "block.closedoor")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> BLOCK_OPENC = SOUNDS.register("block.openc",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "block.openc")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> BLOCK_CLOSEC = SOUNDS.register("block.closec",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "block.closec")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> BLOCK_STEAMENGINEOPERATE = SOUNDS.register("block.steamengineoperate",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "block.steamengineoperate")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> ITEM_BOLTGUN = SOUNDS.register("item.boltgun",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "item.boltgun")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> WEAPON_BANG = SOUNDS.register("weapon.bang",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "weapon.bang")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> WEAPON_SLICE = SOUNDS.register("weapon.slice",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "weapon.slice")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> WEAPON_KAPENG = SOUNDS.register("weapon.kapeng",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "weapon.kapeng")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> BLOCK_PIPEPLACED = SOUNDS.register("block.pipeplaced",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "block.pipeplaced")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> WEAPON_TESLA = SOUNDS.register("weapon.tesla",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "weapon.tesla")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> ENTITY_CYBERCRAB = SOUNDS.register("entity.cybercrab",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "entity.cybercrab")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> WEAPON_OSIPRRELOAD = SOUNDS.register("weapon.osiprreload",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "weapon.osiprreload")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> BLOCK_SOYUZREADY = SOUNDS.register("block.soyuzready",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "block.soyuzready")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> ENTITY_SOYUZTAKEOFF = SOUNDS.register("entity.soyuztakeoff",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "entity.soyuztakeoff")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> ALARM_CHIME = SOUNDS.register("alarm.chime",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "alarm.chime")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> WEAPON_TAUCHARGELOOP2 = SOUNDS.register("weapon.tauchargeloop2",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "weapon.tauchargeloop2")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> ENTITY_CHOPPERFLYINGLOOP = SOUNDS.register("entity.chopperflyingloop",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "entity.chopperflyingloop")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> ENTITY_CHOPPERCRASHINGLOOP = SOUNDS.register("entity.choppercrashingloop",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "entity.choppercrashingloop")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> ENTITY_CHOPPERMINELOOP = SOUNDS.register("entity.choppermineloop",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "entity.choppermineloop")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> WEAPON_LACUNAESPINUP = SOUNDS.register("weapon.lacunaespinup",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "weapon.lacunaespinup")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> WEAPON_LACUNAESPINDOWN = SOUNDS.register("weapon.lacunaespindown",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "weapon.lacunaespindown")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> WEAPON_TESLASHOOT = SOUNDS.register("weapon.teslashoot",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "weapon.teslashoot")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> WEAPON_STOP = SOUNDS.register("weapon.stop",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "weapon.stop")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> WEAPON_BONK = SOUNDS.register("weapon.bonk",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "weapon.bonk")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> WEAPON_GLAUNCHER = SOUNDS.register("weapon.glauncher",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "weapon.glauncher")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> WEAPON_HKSSHOOT = SOUNDS.register("weapon.hksshoot",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "weapon.hksshoot")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> ITEM_VICE = SOUNDS.register("item.vice",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "item.vice")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> BLOCK_SCREM = SOUNDS.register("block.screm",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "block.screm")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> ITEM_UPGRADEPLUG = SOUNDS.register("item.upgradeplug",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "item.upgradeplug")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> WEAPON_TAUCHARGELOOP = SOUNDS.register("weapon.tauchargeloop",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "weapon.tauchargeloop")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> WEAPON_QUADRORELOAD = SOUNDS.register("weapon.quadroreload",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "weapon.quadroreload")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> WEAPON_FSTBMBSTART = SOUNDS.register("weapon.fstbmbstart",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "weapon.fstbmbstart")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> WEAPON_FSTBMBPING = SOUNDS.register("weapon.fstbmbping",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "weapon.fstbmbping")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> ENTITY_DUCC = SOUNDS.register("entity.ducc",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "entity.ducc")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> WEAPON_WHACK = SOUNDS.register("weapon.whack",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "weapon.whack")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> BLOCK_TURBOFANOPERATE = SOUNDS.register("block.turbofanoperate",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "block.turbofanoperate")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> ENTITY_SLICER = SOUNDS.register("entity.slicer",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "entity.slicer")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> ENTITY_MEGAQUACC = SOUNDS.register("entity.megaquacc",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "entity.megaquacc")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> WEAPON_CHAINSAW = SOUNDS.register("weapon.chainsaw",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "weapon.chainsaw")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> ITEM_BATTERY = SOUNDS.register("item.battery",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "item.battery")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> WEAPON_ROCKETFLAME = SOUNDS.register("weapon.rocketflame",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "weapon.rocketflame")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> ENTITY_ROCKETENGINE = SOUNDS.register("entity.rocketengine",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "entity.rocketengine")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> WEAPON_BALLSLASER = SOUNDS.register("weapon.ballslaser",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "weapon.ballslaser")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> WEAPON_DARTSHOOT = SOUNDS.register("weapon.dartshoot",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "weapon.dartshoot")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> WEAPON_JETPACK = SOUNDS.register("weapon.jetpack",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "weapon.jetpack")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> WEAPON_MUKEEXPLOSION = SOUNDS.register("weapon.mukeexplosion",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "weapon.mukeexplosion")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> WEAPON_EXPLOSION_MEDIUM = SOUNDS.register("weapon.explosion_medium",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "weapon.explosion_medium")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> WEAPON_EXPLOSION_TINY = SOUNDS.register("weapon.explosion_tiny",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "weapon.explosion_tiny")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> WEAPON_CRUCIBLE_START = SOUNDS.register("weapon.crucible_start",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "weapon.crucible_start")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> WEAPON_CRUCIBLE_END = SOUNDS.register("weapon.crucible_end",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "weapon.crucible_end")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> WEAPON_CRUCIBLE_SWING = SOUNDS.register("weapon.crucible_swing",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "weapon.crucible_swing")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> WEAPON_CRUCIBLE_LOOP = SOUNDS.register("weapon.crucible_loop",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "weapon.crucible_loop")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> WEAPON_CDEPLOY = SOUNDS.register("weapon.cdeploy",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "weapon.cdeploy")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> WEAPON_JSG_RELOAD0 = SOUNDS.register("weapon.jsg_reload0",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "weapon.jsg_reload0")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> WEAPON_JSG_RELOAD1 = SOUNDS.register("weapon.jsg_reload1",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "weapon.jsg_reload1")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> WEAPON_MOB_GIB = SOUNDS.register("weapon.mob_gib",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "weapon.mob_gib")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> WEAPON_BLOOD_SPLAT = SOUNDS.register("weapon.blood_splat",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "weapon.blood_splat")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> WEAPON_HIT_DIRT = SOUNDS.register("weapon.hit_dirt",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "weapon.hit_dirt")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> WEAPON_HIT_METAL = SOUNDS.register("weapon.hit_metal",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "weapon.hit_metal")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> WEAPON_HIT_FLESH = SOUNDS.register("weapon.hit_flesh",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "weapon.hit_flesh")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> ENTITY_VOMIT = SOUNDS.register("entity.vomit",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "entity.vomit")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> TURRET_CHEKHOV_FIRE = SOUNDS.register("turret.chekhov_fire",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "turret.chekhov_fire")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> TURRET_JEREMY_FIRE = SOUNDS.register("turret.jeremy_fire",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "turret.jeremy_fire")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> TURRET_JEREMY_RELOAD = SOUNDS.register("turret.jeremy_reload",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "turret.jeremy_reload")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> TURRET_RICHARD_FIRE = SOUNDS.register("turret.richard_fire",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "turret.richard_fire")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> TURRET_HOWARD_FIRE = SOUNDS.register("turret.howard_fire",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "turret.howard_fire")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> TURRET_HOWARD_RELOAD = SOUNDS.register("turret.howard_reload",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "turret.howard_reload")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> TURRET_SENTRY_FIRE = SOUNDS.register("turret.sentry_fire",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "turret.sentry_fire")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> TURRET_SENTRY_LOCKON = SOUNDS.register("turret.sentry_lockon",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "turret.sentry_lockon")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> BLOCK_RBMK_EXPLOSION = SOUNDS.register("block.rbmk_explosion",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "block.rbmk_explosion")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> BLOCK_RBMK_AZ5_COVER = SOUNDS.register("block.rbmk_az5_cover",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "block.rbmk_az5_cover")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> BLOCK_BOBBLE = SOUNDS.register("block.bobble",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "block.bobble")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> BLOCK_CRATEOPEN = SOUNDS.register("block.crateopen",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "block.crateopen")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> BLOCK_CRATECLOSE = SOUNDS.register("block.crateclose",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "block.crateclose")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> BLOCK_STORAGEOPEN = SOUNDS.register("block.storageopen",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "block.storageopen")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> BLOCK_STORAGECLOSE = SOUNDS.register("block.storageclose",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "block.storageclose")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> BLOCK_TURBINEGASRUNNING = SOUNDS.register("block.turbinegasrunning",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "block.turbinegasrunning")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> BLOCK_TURBINEGASSHUTDOWN = SOUNDS.register("block.turbinegasshutdown",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "block.turbinegasshutdown")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> BLOCK_TURBINEGASSTARTUP = SOUNDS.register("block.turbinegasstartup",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "block.turbinegasstartup")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> BLOCK_CHUNGUSLEVER = SOUNDS.register("block.chunguslever",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "block.chunguslever")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> BLOCK_CHUNGUSTURBINERUNNING = SOUNDS.register("block.chungusturbinerunning",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "block.chungusturbinerunning")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> WEAPON_DFLASH = SOUNDS.register("weapon.dflash",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "weapon.dflash")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> PLAYER_COUGH = SOUNDS.register("player.cough",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "player.cough")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> PLAYER_GULP = SOUNDS.register("player.gulp",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "player.gulp")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> PLAYER_GROAN = SOUNDS.register("player.groan",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "player.groan")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> ENTITY_UFOBEAM = SOUNDS.register("entity.ufobeam",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "entity.ufobeam")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> ENTITY_UFOBLAST = SOUNDS.register("entity.ufoblast",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "entity.ufoblast")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> BLOCK_IGENERATOROPERATE = SOUNDS.register("block.igeneratoroperate",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "block.igeneratoroperate")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> BLOCK_DOOR_TRANSITIONSEAL = SOUNDS.register("block.door.transitionseal",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "block.door.transitionseal")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> BLOCK_DOOR_SILOOPEN = SOUNDS.register("block.door.siloopen",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "block.door.siloopen")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> BLOCK_DOOR_SILOCLOSE = SOUNDS.register("block.door.siloclose",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "block.door.siloclose")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> BLOCK_DOOR_GARAGE = SOUNDS.register("block.door.garage",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "block.door.garage")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> BLOCK_DOOR_GARAGESTOP = SOUNDS.register("block.door.garagestop",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "block.door.garagestop")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> BLOCK_DOOR_LEVER = SOUNDS.register("block.door.lever",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "block.door.lever")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> BLOCK_DOOR_WGH_BIG_START = SOUNDS.register("block.door.wgh_big_start",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "block.door.wgh_big_start")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> BLOCK_DOOR_WGH_BIG_STOP = SOUNDS.register("block.door.wgh_big_stop",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "block.door.wgh_big_stop")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> BLOCK_DOOR_WGH_START = SOUNDS.register("block.door.wgh_start",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "block.door.wgh_start")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> BLOCK_DOOR_WGH_STOP = SOUNDS.register("block.door.wgh_stop",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "block.door.wgh_stop")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> BLOCK_DOOR_ALARM6 = SOUNDS.register("block.door.alarm6",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "block.door.alarm6")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> BLOCK_DOOR_QE_SLIDING_SHUT = SOUNDS.register("block.door.qe_sliding_shut",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "block.door.qe_sliding_shut")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> BLOCK_DOOR_QE_SLIDING_OPENED = SOUNDS.register("block.door.qe_sliding_opened",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "block.door.qe_sliding_opened")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> BLOCK_DOOR_QE_SLIDING_OPENING = SOUNDS.register("block.door.qe_sliding_opening",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "block.door.qe_sliding_opening")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> BLOCK_DOOR_HATCH_OPEN = SOUNDS.register("block.door.hatch_open",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "block.door.hatch_open")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> BLOCK_DOOR_SLIDING_SEAL_OPEN = SOUNDS.register("block.door.sliding_seal_open",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "block.door.sliding_seal_open")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> BLOCK_DOOR_SLIDING_SEAL_STOP = SOUNDS.register("block.door.sliding_seal_stop",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "block.door.sliding_seal_stop")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> WEAPON_RELOAD_REVOLVERCOCK = SOUNDS.register("weapon.reload.revolvercock",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "weapon.reload.revolvercock")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> WEAPON_RELOAD_MAGSMALLREMOVE = SOUNDS.register("weapon.reload.magsmallremove",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "weapon.reload.magsmallremove")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> WEAPON_RELOAD_MAGSMALLINSERT = SOUNDS.register("weapon.reload.magsmallinsert",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "weapon.reload.magsmallinsert")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> WEAPON_RELOAD_REVOLVERCLOSE = SOUNDS.register("weapon.reload.revolverclose",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "weapon.reload.revolverclose")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> WEAPON_RELOAD_DRYFIRECLICK = SOUNDS.register("weapon.reload.dryfireclick",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "weapon.reload.dryfireclick")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> WEAPON_RELOAD_REVOLVERSPIN = SOUNDS.register("weapon.reload.revolverspin",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "weapon.reload.revolverspin")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> WEAPON_RELOAD_LEVERCOCK = SOUNDS.register("weapon.reload.levercock",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "weapon.reload.levercock")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> WEAPON_RELOAD_OPENLATCH = SOUNDS.register("weapon.reload.openlatch",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "weapon.reload.openlatch")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> WEAPON_RELOAD_MAGREMOVE = SOUNDS.register("weapon.reload.magremove",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "weapon.reload.magremove")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> WEAPON_RELOAD_MAGINSERT = SOUNDS.register("weapon.reload.maginsert",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "weapon.reload.maginsert")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> WEAPON_RELOAD_PISTOLCOCK = SOUNDS.register("weapon.reload.pistolcock",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "weapon.reload.pistolcock")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> WEAPON_RELOAD_SHOTGUNRELOAD = SOUNDS.register("weapon.reload.shotgunreload",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "weapon.reload.shotgunreload")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> WEAPON_RELOAD_INSERTCANISTER = SOUNDS.register("weapon.reload.insertcanister",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "weapon.reload.insertcanister")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> WEAPON_RELOAD_IMPACT = SOUNDS.register("weapon.reload.impact",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "weapon.reload.impact")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> WEAPON_GLRELOAD = SOUNDS.register("weapon.glreload",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "weapon.glreload")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> WEAPON_GLOPEN = SOUNDS.register("weapon.glopen",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "weapon.glopen")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> WEAPON_GLCLOSE = SOUNDS.register("weapon.glclose",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "weapon.glclose")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> WEAPON_FIRE_FLAMELOOP = SOUNDS.register("weapon.fire.flameloop",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "weapon.fire.flameloop")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> WEAPON_RELOAD_PRESSUREVALVE = SOUNDS.register("weapon.reload.pressurevalve",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "weapon.reload.pressurevalve")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> WEAPON_RELOAD_SHOTGUNCOCKOPEN = SOUNDS.register("weapon.reload.shotguncockopen",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "weapon.reload.shotguncockopen")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> WEAPON_RELOAD_SHOTGUNCOCK = SOUNDS.register("weapon.reload.shotguncock",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "weapon.reload.shotguncock")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> WEAPON_RELOAD_SHOTGUNCOCKCLOSE = SOUNDS.register("weapon.reload.shotguncockclose",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "weapon.reload.shotguncockclose")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> WEAPON_FOLEY_GUNWHACK = SOUNDS.register("weapon.foley.gunwhack",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "weapon.foley.gunwhack")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> WEAPON_FIRE_LOCKON = SOUNDS.register("weapon.fire.lockon",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "weapon.fire.lockon")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> WEAPON_RELOAD_BOLTOPEN = SOUNDS.register("weapon.reload.boltopen",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "weapon.reload.boltopen")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> WEAPON_RELOAD_BOLTCLOSE = SOUNDS.register("weapon.reload.boltclose",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "weapon.reload.boltclose")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> WEAPON_RELOAD_GRENADETECH = SOUNDS.register("weapon.reload.grenadetech",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "weapon.reload.grenadetech")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> WEAPON_RELOAD_GRENADENUKA = SOUNDS.register("weapon.reload.grenadenuka",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "weapon.reload.grenadenuka")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> WEAPON_FIRE_SHREDDERCYCLE = SOUNDS.register("weapon.fire.shreddercycle",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "weapon.fire.shreddercycle")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> WEAPON_RELOAD_RIFLECOCK = SOUNDS.register("weapon.reload.riflecock",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "weapon.reload.riflecock")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> WEAPON_RELOAD_SCREW = SOUNDS.register("weapon.reload.screw",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "weapon.reload.screw")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> WEAPON_RELOAD_INSERTROCKET = SOUNDS.register("weapon.reload.insertrocket",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "weapon.reload.insertrocket")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> WEAPON_FIRE_TAU = SOUNDS.register("weapon.fire.tau",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "weapon.fire.tau")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> WEAPON_FIRE_TAULOOP = SOUNDS.register("weapon.fire.tauloop",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "weapon.fire.tauloop")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> WEAPON_RELOAD_FATMANFULL = SOUNDS.register("weapon.reload.fatmanfull",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "weapon.reload.fatmanfull")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> WEAPON_COILGUNRELOAD = SOUNDS.register("weapon.coilgunreload",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "weapon.coilgunreload")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> WEAPON_FIRE_SMACK = SOUNDS.register("weapon.fire.smack",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "weapon.fire.smack")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> BLOCK_SQUEAKYTOY = SOUNDS.register("block.squeakytoy",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "block.squeakytoy")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> BLOCK_HUNDUNSMAGNIFICENTHOWL = SOUNDS.register("block.hundunsmagnificenthowl",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "block.hundunsmagnificenthowl")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> BLOCK_MOTOR = SOUNDS.register("block.motor",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "block.motor")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> WEAPON_FIRE_SILENCED = SOUNDS.register("weapon.fire.silenced",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "weapon.fire.silenced")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> WEAPON_FIRE_PISTOL = SOUNDS.register("weapon.fire.pistol",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "weapon.fire.pistol")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> WEAPON_FIRE_PISTOLLIGHT = SOUNDS.register("weapon.fire.pistollight",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "weapon.fire.pistollight")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> WEAPON_FIRE_BLACKPOWDER = SOUNDS.register("weapon.fire.blackpowder",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "weapon.fire.blackpowder")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> WEAPON_FIRE_UZI = SOUNDS.register("weapon.fire.uzi",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "weapon.fire.uzi")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> WEAPON_FIRE_GREASEGUN = SOUNDS.register("weapon.fire.greasegun",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "weapon.fire.greasegun")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> WEAPON_FIRE_ABERRATOR = SOUNDS.register("weapon.fire.aberrator",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "weapon.fire.aberrator")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> WEAPON_COILGUNSHOOT = SOUNDS.register("weapon.coilgunshoot",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "weapon.coilgunshoot")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> WEAPON_FIRE_TAURELEASE = SOUNDS.register("weapon.fire.taurelease",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "weapon.fire.taurelease")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> WEAPON_FIRE_FATMAN = SOUNDS.register("weapon.fire.fatman",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "weapon.fire.fatman")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> WEAPON_FIRE_RIFLE = SOUNDS.register("weapon.fire.rifle",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "weapon.fire.rifle")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> WEAPON_44SHOOT = SOUNDS.register("weapon.44shoot",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "weapon.44shoot")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> WEAPON_FIRE_SHOTGUN = SOUNDS.register("weapon.fire.shotgun",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "weapon.fire.shotgun")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> WEAPON_FIRE_LOUDESTNOISEONEARTH = SOUNDS.register("weapon.fire.loudestnoiseonearth",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "weapon.fire.loudestnoiseonearth")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> WEAPON_FIRE_TESLA = SOUNDS.register("weapon.fire.tesla",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "weapon.fire.tesla")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> WEAPON_FIRE_LASER = SOUNDS.register("weapon.fire.laser",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "weapon.fire.laser")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> WEAPON_FIRE_SHOTGUNALT = SOUNDS.register("weapon.fire.shotgunalt",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "weapon.fire.shotgunalt")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> WEAPON_FIRE_SHOTGUNAUTO = SOUNDS.register("weapon.fire.shotgunauto",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "weapon.fire.shotgunauto")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> WEAPON_GLSHOOT = SOUNDS.register("weapon.glshoot",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "weapon.glshoot")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> WEAPON_FIRE_MK108 = SOUNDS.register("weapon.fire.mk108",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "weapon.fire.mk108")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> WEAPON_FIRE_LASERGATLING = SOUNDS.register("weapon.fire.lasergatling",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "weapon.fire.lasergatling")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> WEAPON_FIRE_RIFLEHEAVY = SOUNDS.register("weapon.fire.rifleheavy",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "weapon.fire.rifleheavy")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> WEAPON_FIRE_ASSAULT = SOUNDS.register("weapon.fire.assault",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "weapon.fire.assault")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> WEAPON_FIRE_DISINTEGRATION = SOUNDS.register("weapon.fire.disintegration",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "weapon.fire.disintegration")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> WEAPON_FIRE_LASERPISTOL = SOUNDS.register("weapon.fire.laserpistol",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "weapon.fire.laserpistol")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> WEAPON_FIRE_STAB = SOUNDS.register("weapon.fire.stab",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "weapon.fire.stab")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> WEAPON_FIRE_GRENADE = SOUNDS.register("weapon.fire.grenade",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "weapon.fire.grenade")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> WEAPON_FIRE_AMAT = SOUNDS.register("weapon.fire.amat",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "weapon.fire.amat")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> WEAPON_EXTINGUISHER = SOUNDS.register("weapon.extinguisher",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "weapon.extinguisher")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> BLOCK_ASSEMBLERSTRIKE = SOUNDS.register("block.assemblerstrike",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "block.assemblerstrike")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> BLOCK_ASSEMBLERSTART = SOUNDS.register("block.assemblerstart",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "block.assemblerstart")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> BLOCK_ASSEMBLERSTOP = SOUNDS.register("block.assemblerstop",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "block.assemblerstop")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> BLOCK_ASSEMBLERCUT = SOUNDS.register("block.assemblercut",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "block.assemblercut")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> WEAPON_RELOAD_TUBEFWOOMP = SOUNDS.register("weapon.reload.tubefwoomp",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "weapon.reload.tubefwoomp")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> BLOCK_FUSIONREACTORRUNNING = SOUNDS.register("block.fusionreactorrunning",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "block.fusionreactorrunning")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> WEAPON_CASING_SHELL = SOUNDS.register("weapon.casing.shell",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "weapon.casing.shell")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> WEAPON_CASING_SMALL = SOUNDS.register("weapon.casing.small",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "weapon.casing.small")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> WEAPON_CASING_MEDIUM = SOUNDS.register("weapon.casing.medium",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "weapon.casing.medium")));
    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> WEAPON_CASING_LARGE = SOUNDS.register("weapon.casing.large",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "weapon.casing.large")));
}
