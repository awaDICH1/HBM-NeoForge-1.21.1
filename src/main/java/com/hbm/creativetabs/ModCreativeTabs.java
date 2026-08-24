package com.hbm.creativetabs;

import com.hbm.Tags;
import com.hbm.blocks.ModBlocks;
import com.hbm.items.ModItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Consumer;

/**
 * 闂佸憡甯楅惄顖炲焵椤掑倸校闁绘牬鍠栭埢鏃堝Ω閵夈儲鏉告繛鎴炴惄閸樿偐缂撴ィ鍐ㄎ? * 闁哄鏅欓懗鏈电昂闂?1.12.2 MainRegistry 婵炴垶鎼╅崢鎯р枔?11 婵?CreativeTabs 闁诲孩绋掗〃鍡涱敊? * 闂佹寧绋戝鐪厀 PartsTab(CreativeTabs.getNextID(), "tabParts") 缂備焦绋戦¨鈧紒杈ㄥ哺婵? *
 * 1.21.1 闂佸憡鐟﹁摫婵炴彃娼￠弫? *   - CreativeTabs 闂佺娉涢埀顒傚枙閺夌晫绱?闂?CreativeModeTab.builder()闂? *   - getNextID() 闂佸憡甯炴繛鈧繛鍛叄閺佸秹宕奸悢鍛婃畼闂佸憡鍔曢懟顖炲箖閺囥垹纭€闁告劕寮堕弳婊冣槈閹绢垰浜鹃梺鍝勭Т濞测晠鎯侀幋锔芥櫖濠㈣埖绋撻獮? *   - 闂佸搫鍊婚幊鎾愁焽?闂佺粯銇涢弲娑㈠箹瑜庣粙澶婎吋閸涱厽娅?setCreativeTab(...)闂佹寧绋戦張顒勫极閸忚偐鈻旈柛婵嗗闊?displayItems 婵?output.accept(...)闂? */
public class ModCreativeTabs {

    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Tags.MODID);

    private static CreativeModeTab tab(String langKey, Consumer<CreativeModeTab.Output> items) {
        return CreativeModeTab.builder()
                .title(Component.translatable(langKey))
                // .icon(() -> new ItemStack(ModItems.XXX.get()))   // P3 婵犻潧顦介崑鍕矗閸℃稑瑙﹂柛鏇ㄥ墰閸╁霉閻欏懐绉柕鍡楀暣閹囧煛娴ｈ鍎?                .displayItems((params, output) -> items.accept(output))
                .build();
    }

    // 闁荤姴娴傞崣鈧柍璇茬墦閺屻劑顢欐禒瀣姤闂?CE en_us.lang闂佹寧绋戝鐒mGroup.tabXxx闂佹寧绋戦¨鈧紒杈ㄧ箘閹?src/main/resources/assets/hbm/lang/en_us.json
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> PARTS_TAB = TABS.register("parts",
            () -> tab("itemGroup.tabParts", output -> {
                output.accept(ModItems.INGOT_URANIUM.get());   // 闂?ingot_uranium.setCreativeTab(partsTab)
            
                output.accept(ModItems.POWDER_IRON.get());
                output.accept(ModItems.POWDER_GOLD.get());
                output.accept(ModItems.POWDER_DIAMOND.get());
                output.accept(ModItems.POWDER_EMERALD.get());
                output.accept(ModItems.POWDER_LAPIS.get());
                output.accept(ModItems.POWDER_TITANIUM.get());
                output.accept(ModItems.POWDER_TUNGSTEN.get());
                output.accept(ModItems.POWDER_SODIUM.get());
                output.accept(ModItems.POWDER_CHLOROCALCITE.get());
                output.accept(ModItems.POWDER_MOLYSITE.get());
                output.accept(ModItems.POWDER_COPPER.get());
                output.accept(ModItems.POWDER_BERYLLIUM.get());
                output.accept(ModItems.POWDER_ALUMINIUM.get());
                output.accept(ModItems.POWDER_COMBINE_STEEL.get());
                output.accept(ModItems.POWDER_CDALLOY.get());
                output.accept(ModItems.POWDER_CHLOROPHYTE.get());
                output.accept(ModItems.POWDER_RED_COPPER.get());
                output.accept(ModItems.POWDER_STEEL.get());
                output.accept(ModItems.POWDER_STEEL_TINY.get());
                output.accept(ModItems.REDSTONE_DEPLETED.get());
                output.accept(ModItems.POWDER_QUARTZ.get());
                output.accept(ModItems.POWDER_BORAX.get());
                output.accept(ModItems.POWDER_LANTHANIUM_TINY.get());
                output.accept(ModItems.POWDER_ACTINIUM_TINY.get());
                output.accept(ModItems.POWDER_BORON_TINY.get());
                output.accept(ModItems.POWDER_SEMTEX_MIX.get());
                output.accept(ModItems.POWDER_DESH.get());
                output.accept(ModItems.POWDER_ZIRCONIUM.get());
                output.accept(ModItems.POWDER_ASBESTOS.get());
                output.accept(ModItems.POWDER_CADMIUM.get());
                output.accept(ModItems.POWDER_BISMUTH.get());
                output.accept(ModItems.POWDER_CAESIUM.get());
                output.accept(ModItems.POWDER_STRONTIUM.get());
                output.accept(ModItems.POWDER_CALCIUM.get());
                output.accept(ModItems.POWDER_ICE.get());
                output.accept(ModItems.POWDER_LIMESTONE.get());
                output.accept(ModItems.POWDER_DESH_MIX.get());
                output.accept(ModItems.POWDER_DESH_READY.get());
                output.accept(ModItems.POWDER_METEORITE.get());
                output.accept(ModItems.POWDER_METEORITE_TINY.get());
                output.accept(ModItems.POWDER_COLTAN.get());
                output.accept(ModItems.POWDER_POISON.get());
                output.accept(ModItems.SULFUR.get());
                output.accept(ModItems.NITER.get());
                output.accept(ModItems.FLUORITE.get());
                output.accept(ModItems.SCRAP.get());
                output.accept(ModItems.SCRAP_OIL.get());
                output.accept(ModItems.DUST.get());
                output.accept(ModItems.RING_STARMETAL.get());
                output.accept(ModItems.FLYWHEEL_BERYLLIUM.get());
                output.accept(ModItems.CORDITE.get());
                output.accept(ModItems.BALLISTITE.get());
                output.accept(ModItems.BALL_DYNAMITE.get());
                output.accept(ModItems.BALL_TNT.get());
                output.accept(ModItems.BALL_TATB.get());
                output.accept(ModItems.BALL_FIRECLAY.get());
                output.accept(ModItems.NEUTRON_REFLECTOR.get());
                output.accept(ModItems.RTG_UNIT.get());
                output.accept(ModItems.THERMO_UNIT_EMPTY.get());
                output.accept(ModItems.LEVITATION_UNIT.get());
                output.accept(ModItems.PELLET_BUCKSHOT.get());
                output.accept(ModItems.PELLET_FLECHETTE.get());
                output.accept(ModItems.PELLET_CHLOROPHYTE.get());
                output.accept(ModItems.PELLET_METEORITE.get());
                output.accept(ModItems.PELLET_CANISTER.get());
                output.accept(ModItems.PELLET_CLAWS.get());
                output.accept(ModItems.CINNABAR.get());
                output.accept(ModItems.DUCTTAPE.get());
                output.accept(ModItems.CATALYST_CLAY.get());
                output.accept(ModItems.PHOTO_PANEL.get());
                output.accept(ModItems.SAT_BASE.get());
                output.accept(ModItems.THRUSTER_NUCLEAR.get());
                output.accept(ModItems.BLADE_TITANIUM.get());
                output.accept(ModItems.TURBINE_TITANIUM.get());
                output.accept(ModItems.BLADE_TUNGSTEN.get());
                output.accept(ModItems.TURBINE_TUNGSTEN.get());
                output.accept(ModItems.BOARD_COPPER.get());
                output.accept(ModItems.PIPES_STEEL.get());
                output.accept(ModItems.DRILL_TITANIUM.get());
                output.accept(ModItems.BOLT_COMPOUND.get());
                output.accept(ModItems.HAZMAT_CLOTH.get());
                output.accept(ModItems.HAZMAT_CLOTH_RED.get());
                output.accept(ModItems.HAZMAT_CLOTH_GREY.get());
                output.accept(ModItems.ASBESTOS_CLOTH.get());
                output.accept(ModItems.RAG_DAMP.get());
                output.accept(ModItems.RAG_PISS.get());
                output.accept(ModItems.FILTER_COAL.get());
                output.accept(ModItems.MAGNET_CIRCULAR.get());
                output.accept(ModItems.CENTRIFUGE_ELEMENT.get());
                output.accept(ModItems.REACTOR_CORE.get());
                output.accept(ModItems.FRAGMENT_NEODYMIUM.get());
                output.accept(ModItems.FRAGMENT_COBALT.get());
                output.accept(ModItems.FRAGMENT_NIOBIUM.get());
                output.accept(ModItems.FRAGMENT_CERIUM.get());
                output.accept(ModItems.FRAGMENT_LANTHANIUM.get());
                output.accept(ModItems.FRAGMENT_ACTINIUM.get());
                output.accept(ModItems.FRAGMENT_METEORITE.get());
                output.accept(ModItems.FRAGMENT_BORON.get());
                output.accept(ModItems.FRAGMENT_COLTAN.get());
                output.accept(ModItems.PLATE_IRON.get());
                output.accept(ModItems.PLATE_COPPER.get());
                output.accept(ModItems.PLATE_TITANIUM.get());
                output.accept(ModItems.PLATE_ALUMINIUM.get());
                output.accept(ModItems.PLATE_GOLD.get());
                output.accept(ModItems.PLATE_STEEL.get());
                output.accept(ModItems.PLATE_COMBINE_STEEL.get());
                output.accept(ModItems.PLATE_SATURNITE.get());
                output.accept(ModItems.PLATE_DALEKANIUM.get());
                output.accept(ModItems.PLATE_MIXED.get());
                output.accept(ModItems.PLATE_KEVLAR.get());
                output.accept(ModItems.PLATE_POLYMER.get());
                output.accept(ModItems.PLATE_DESH.get());
                output.accept(ModItems.PLATE_DINEUTRONIUM.get());
                output.accept(ModItems.PLATE_ARMOR_TITANIUM.get());
                output.accept(ModItems.PLATE_ARMOR_AJR.get());
                output.accept(ModItems.PLATE_ARMOR_HEV.get());
                output.accept(ModItems.PLATE_ARMOR_LUNAR.get());
                output.accept(ModItems.PLATE_ARMOR_FAU.get());
                output.accept(ModItems.PLATE_ARMOR_DNT.get());
                output.accept(ModItems.PLATE_DURA_STEEL.get());
                output.accept(ModItems.PLATE_GUNMETAL.get());
                output.accept(ModItems.PLATE_WEAPONSTEEL.get());
                output.accept(ModItems.CRYSTAL_IRON.get());
                output.accept(ModItems.CRYSTAL_GOLD.get());
                output.accept(ModItems.CRYSTAL_REDSTONE.get());
                output.accept(ModItems.CRYSTAL_LAPIS.get());
                output.accept(ModItems.CRYSTAL_DIAMOND.get());
                output.accept(ModItems.CRYSTAL_TITANIUM.get());
                output.accept(ModItems.CRYSTAL_SULFUR.get());
                output.accept(ModItems.CRYSTAL_NITER.get());
                output.accept(ModItems.CRYSTAL_COPPER.get());
                output.accept(ModItems.CRYSTAL_TUNGSTEN.get());
                output.accept(ModItems.CRYSTAL_ALUMINIUM.get());
                output.accept(ModItems.CRYSTAL_FLUORITE.get());
                output.accept(ModItems.CRYSTAL_BERYLLIUM.get());
                output.accept(ModItems.CRYSTAL_RARE.get());
                output.accept(ModItems.CRYSTAL_CINNABAR.get());
                output.accept(ModItems.CRYSTAL_COBALT.get());
                output.accept(ModItems.CRYSTAL_STARMETAL.get());
                output.accept(ModItems.GEM_ALEXANDRITE.get());
                output.accept(ModItems.DEUTERIUM_FILTER.get());
                output.accept(ModItems.SAWBLADE.get());
                output.accept(ModItems.MECHANISM_REVOLVER_1.get());
                output.accept(ModItems.MECHANISM_REVOLVER_2.get());
                output.accept(ModItems.MECHANISM_RIFLE_1.get());
                output.accept(ModItems.MECHANISM_RIFLE_2.get());
                output.accept(ModItems.MECHANISM_LAUNCHER_1.get());
                output.accept(ModItems.MECHANISM_LAUNCHER_2.get());
                output.accept(ModItems.MECHANISM_SPECIAL.get());
                output.accept(ModItems.PRIMER_357.get());
                output.accept(ModItems.PRIMER_44.get());
                output.accept(ModItems.PRIMER_9.get());
                output.accept(ModItems.PRIMER_50.get());
                output.accept(ModItems.PRIMER_BUCKSHOT.get());
                output.accept(ModItems.CASING_357.get());
                output.accept(ModItems.CASING_44.get());
                output.accept(ModItems.CASING_9.get());
                output.accept(ModItems.CASING_50.get());
                output.accept(ModItems.CASING_BUCKSHOT.get());
                output.accept(ModItems.COIL_GOLD.get());
                output.accept(ModItems.COIL_GOLD_TORUS.get());
                output.accept(ModItems.COIL_TUNGSTEN.get());
                output.accept(ModItems.COIL_COPPER.get());
                output.accept(ModItems.COIL_COPPER_TORUS.get());
                output.accept(ModItems.ASSEMBLY_NUKE.get());
                output.accept(ModItems.MISSILE_ASSEMBLY.get());
                output.accept(ModItems.WARHEAD_GENERIC_SMALL.get());
                output.accept(ModItems.WARHEAD_INCENDIARY_SMALL.get());
                output.accept(ModItems.WARHEAD_CLUSTER_SMALL.get());
                output.accept(ModItems.WARHEAD_BUSTER_SMALL.get());
                output.accept(ModItems.WARHEAD_GENERIC_MEDIUM.get());
                output.accept(ModItems.WARHEAD_INCENDIARY_MEDIUM.get());
                output.accept(ModItems.WARHEAD_CLUSTER_MEDIUM.get());
                output.accept(ModItems.WARHEAD_BUSTER_MEDIUM.get());
                output.accept(ModItems.WARHEAD_GENERIC_LARGE.get());
                output.accept(ModItems.WARHEAD_INCENDIARY_LARGE.get());
                output.accept(ModItems.WARHEAD_CLUSTER_LARGE.get());
                output.accept(ModItems.WARHEAD_BUSTER_LARGE.get());
                output.accept(ModItems.WARHEAD_N2.get());
                output.accept(ModItems.WARHEAD_NUCLEAR.get());
                output.accept(ModItems.WARHEAD_MIRVLET.get());
                output.accept(ModItems.WARHEAD_MIRV.get());
                output.accept(ModItems.WARHEAD_VOLCANO.get());
                output.accept(ModItems.WARHEAD_THERMO_ENDO.get());
                output.accept(ModItems.WARHEAD_THERMO_EXO.get());
                output.accept(ModItems.THRUSTER_SMALL.get());
                output.accept(ModItems.THRUSTER_MEDIUM.get());
                output.accept(ModItems.THRUSTER_LARGE.get());
                output.accept(ModItems.CAP_ALUMINIUM.get());
                output.accept(ModItems.FINS_FLAT.get());
                output.accept(ModItems.FINS_SMALL_STEEL.get());
                output.accept(ModItems.FINS_BIG_STEEL.get());
                output.accept(ModItems.FINS_TRI_STEEL.get());
                output.accept(ModItems.FINS_QUAD_TITANIUM.get());
                output.accept(ModItems.SPHERE_STEEL.get());
                output.accept(ModItems.PEDESTAL_STEEL.get());
                output.accept(ModItems.DYSFUNCTIONAL_REACTOR.get());
                output.accept(ModItems.ROTOR_STEEL.get());
                output.accept(ModItems.GENERATOR_STEEL.get());
                output.accept(ModItems.SAT_HEAD_MAPPER.get());
                output.accept(ModItems.SAT_HEAD_SCANNER.get());
                output.accept(ModItems.SAT_HEAD_RADAR.get());
                output.accept(ModItems.SAT_HEAD_LASER.get());
                output.accept(ModItems.SAT_HEAD_RESONATOR.get());
                output.accept(ModItems.SEG_10.get());
                output.accept(ModItems.SEG_15.get());
                output.accept(ModItems.SEG_20.get());
                output.accept(ModItems.FUEL_TANK_SMALL.get());
                output.accept(ModItems.FUEL_TANK_MEDIUM.get());
                output.accept(ModItems.FUEL_TANK_LARGE.get());
                output.accept(ModItems.TANK_STEEL.get());
                output.accept(ModItems.CHOPPER_HEAD.get());
                output.accept(ModItems.CHOPPER_GUN.get());
                output.accept(ModItems.CHOPPER_TORSO.get());
                output.accept(ModItems.CHOPPER_TAIL.get());
                output.accept(ModItems.CHOPPER_WING.get());
                output.accept(ModItems.CHOPPER_BLADES.get());
                output.accept(ModItems.COMBINE_SCRAP.get());
                output.accept(ModItems.SHIMMER_HEAD.get());
                output.accept(ModItems.SHIMMER_AXE_HEAD.get());
                output.accept(ModItems.SHIMMER_HANDLE.get());
                output.accept(ModItems.INGOT_ASBESTOS.get());
                output.accept(ModItems.GEM_VOLCANIC.get());
                output.accept(ModItems.INGOT_ACTINIUM.get());
                output.accept(ModItems.INGOT_ALUMINIUM.get());
                output.accept(ModItems.INGOT_AM_MIX.get());
                output.accept(ModItems.INGOT_AM241.get());
                output.accept(ModItems.INGOT_AM242.get());
                output.accept(ModItems.INGOT_AMERICIUM_FUEL.get());
                output.accept(ModItems.INGOT_ARSENIC.get());
                output.accept(ModItems.INGOT_ARSENIC_BRONZE.get());
                output.accept(ModItems.INGOT_ASTATINE.get());
                output.accept(ModItems.INGOT_AU198.get());
                output.accept(ModItems.INGOT_AUSTRALIUM.get());
                output.accept(ModItems.INGOT_BAKELITE.get());
                output.accept(ModItems.INGOT_BERYLLIUM.get());
                output.accept(ModItems.INGOT_BIORUBBER.get());
                output.accept(ModItems.INGOT_BISMUTH.get());
                output.accept(ModItems.INGOT_BISMUTH_BRONZE.get());
                output.accept(ModItems.INGOT_BORON.get());
                output.accept(ModItems.INGOT_BROMINE.get());
                output.accept(ModItems.INGOT_BSCCO.get());
                output.accept(ModItems.INGOT_C4.get());
                output.accept(ModItems.INGOT_CADMIUM.get());
                output.accept(ModItems.INGOT_CAESIUM.get());
                output.accept(ModItems.INGOT_CALCIUM.get());
                output.accept(ModItems.INGOT_CDALLOY.get());
                output.accept(ModItems.INGOT_CERIUM.get());
                output.accept(ModItems.INGOT_CFT.get());
                output.accept(ModItems.INGOT_CO60.get());
                output.accept(ModItems.INGOT_COBALT.get());
                output.accept(ModItems.INGOT_COMBINE_STEEL.get());
                output.accept(ModItems.INGOT_COPPER.get());
                output.accept(ModItems.INGOT_DAFFERGON.get());
                output.accept(ModItems.INGOT_DESH.get());
                output.accept(ModItems.INGOT_DINEUTRONIUM.get());
                output.accept(ModItems.INGOT_DURA_STEEL.get());
                output.accept(ModItems.INGOT_EUPHEMIUM.get());
                output.accept(ModItems.INGOT_FERROURANIUM.get());
                output.accept(ModItems.INGOT_FIBERGLASS.get());
                output.accept(ModItems.INGOT_FIREBRICK.get());
                output.accept(ModItems.INGOT_GH336.get());
                output.accept(ModItems.INGOT_GRAPHITE.get());
                output.accept(ModItems.INGOT_GUNMETAL.get());
                output.accept(ModItems.INGOT_HES.get());
                output.accept(ModItems.INGOT_I131.get());
                output.accept(ModItems.INGOT_IODINE.get());
                output.accept(ModItems.INGOT_LANTHANIUM.get());
                output.accept(ModItems.INGOT_LEAD.get());
                output.accept(ModItems.INGOT_LES.get());
                output.accept(ModItems.INGOT_MAGNETIZED_TUNGSTEN.get());
                output.accept(ModItems.INGOT_MOX_FUEL.get());
                output.accept(ModItems.INGOT_MUD.get());
                output.accept(ModItems.INGOT_NEPTUNIUM.get());
                output.accept(ModItems.INGOT_NEPTUNIUM_FUEL.get());
                output.accept(ModItems.INGOT_NIOBIUM.get());
                output.accept(ModItems.INGOT_OSMIRIDIUM.get());
                output.accept(ModItems.INGOT_PB209.get());
                output.accept(ModItems.INGOT_PC.get());
                output.accept(ModItems.INGOT_PHOSPHORUS.get());
                output.accept(ModItems.INGOT_PLUTONIUM.get());
                output.accept(ModItems.INGOT_PLUTONIUM_FUEL.get());
                output.accept(ModItems.INGOT_POLONIUM.get());
                output.accept(ModItems.INGOT_POLYMER.get());
                output.accept(ModItems.INGOT_PU_MIX.get());
                output.accept(ModItems.INGOT_PU238.get());
                output.accept(ModItems.INGOT_PU239.get());
                output.accept(ModItems.INGOT_PU240.get());
                output.accept(ModItems.INGOT_PU241.get());
                output.accept(ModItems.INGOT_PVC.get());
                output.accept(ModItems.INGOT_RA226.get());
                output.accept(ModItems.INGOT_RED_COPPER.get());
                output.accept(ModItems.INGOT_REIIUM.get());
                output.accept(ModItems.INGOT_RUBBER.get());
                output.accept(ModItems.INGOT_SATURNITE.get());
                output.accept(ModItems.INGOT_SCHRABIDATE.get());
                output.accept(ModItems.INGOT_SCHRABIDIUM.get());
                output.accept(ModItems.INGOT_SCHRABIDIUM_FUEL.get());
                output.accept(ModItems.INGOT_SCHRARANIUM.get());
                output.accept(ModItems.INGOT_SILICON.get());
                output.accept(ModItems.INGOT_SOLINIUM.get());
                output.accept(ModItems.INGOT_SR90.get());
                output.accept(ModItems.INGOT_STARMETAL.get());
                output.accept(ModItems.INGOT_STEEL.get());
                output.accept(ModItems.INGOT_TANTALIUM.get());
                output.accept(ModItems.INGOT_TCALLOY.get());
                output.accept(ModItems.INGOT_TECHNETIUM.get());
                output.accept(ModItems.INGOT_TENNESSINE.get());
                output.accept(ModItems.INGOT_TH232.get());
                output.accept(ModItems.INGOT_THORIUM_FUEL.get());
                output.accept(ModItems.INGOT_TITANIUM.get());
                output.accept(ModItems.INGOT_TUNGSTEN.get());
                output.accept(ModItems.INGOT_TUNGSTEN_CARBIDE.get());
                output.accept(ModItems.INGOT_U233.get());
                output.accept(ModItems.INGOT_U235.get());
                output.accept(ModItems.INGOT_U238.get());
                output.accept(ModItems.INGOT_UNOBTAINIUM.get());
                output.accept(ModItems.INGOT_URANIUM_FUEL.get());
                output.accept(ModItems.INGOT_VERTICIUM.get());
                output.accept(ModItems.INGOT_WEAPONSTEEL.get());
                output.accept(ModItems.INGOT_WEIDANIUM.get());
                output.accept(ModItems.INGOT_ZIRCONIUM.get());
                // P3.2 registerOres 婵炴挻纰嶇换鍡欑矉閸℃瑢鍋撳☉娆樻畷妞ゆ柨鐭傞弫?3 婵炴垶鎼╂禍椋庢濠曠櫘rtsTab闂?                output.accept(ModItems.PLATE_CAST.get());
                output.accept(ModItems.PLATE_WELDED.get());
                output.accept(ModItems.HEAVY_COMPONENT.get());
                output.accept(ModItems.WIRE_DENSE.get());
                output.accept(ModItems.BOLT.get());
                output.accept(ModItems.INGOT_RAW.get());
                output.accept(ModItems.SHELL.get());
                output.accept(ModItems.PIPE.get());
                output.accept(ModItems.WIRE_FINE.get());
                output.accept(ModItems.PART_BARREL_LIGHT.get());
                output.accept(ModItems.PART_BARREL_HEAVY.get());
                output.accept(ModItems.PART_RECEIVER_LIGHT.get());
                output.accept(ModItems.PART_RECEIVER_HEAVY.get());
                output.accept(ModItems.PART_MECHANISM.get());
                output.accept(ModItems.PART_STOCK.get());
                output.accept(ModItems.PART_GRIP.get());
                output.accept(ModItems.CHUNK_ORE.get());
                output.accept(ModItems.POWDER_ASH.get());
                output.accept(ModItems.CHEMICAL_DYE.get());
                output.accept(ModItems.CRAYON.get());
                output.accept(ModItems.NOTHING.get());
                output.accept(ModItems.BEDROCK_ORE_FRAGMENT.get());
                output.accept(ModItems.SCRAPS.get());
                output.accept(ModItems.BEDROCK_ORE_FRAGMENT.get()); // partsTab
                output.accept(ModItems.BOLT.get()); // partsTab
                output.accept(ModItems.HEAVY_COMPONENT.get()); // partsTab
                output.accept(ModItems.INGOT_RAW.get()); // partsTab
                output.accept(ModItems.PART_BARREL_HEAVY.get()); // partsTab
                output.accept(ModItems.PART_BARREL_LIGHT.get()); // partsTab
                output.accept(ModItems.PART_GRIP.get()); // partsTab
                output.accept(ModItems.PART_MECHANISM.get()); // partsTab
                output.accept(ModItems.PART_RECEIVER_HEAVY.get()); // partsTab
                output.accept(ModItems.PART_RECEIVER_LIGHT.get()); // partsTab
                output.accept(ModItems.PART_STOCK.get()); // partsTab
                output.accept(ModItems.PIPE.get()); // partsTab
                output.accept(ModItems.PLATE_CAST.get()); // partsTab
                output.accept(ModItems.PLATE_WELDED.get()); // partsTab
                output.accept(ModItems.SHELL.get()); // partsTab
                output.accept(ModItems.WIRE_DENSE.get()); // partsTab
                output.accept(ModItems.WIRE_FINE.get()); // partsTab
                output.accept(ModItems.BRIQUETTE.get()); // partsTab
                output.accept(ModItems.COKE.get()); // partsTab
                output.accept(ModItems.CHUNK_ORE.get()); // partsTab
                output.accept(ModItems.OIL_TAR.get()); // partsTab
                output.accept(ModItems.INGOT_SEMTEX.get()); // partsTab
                output.accept(ModItems.HAND_DRILL.get()); // partsTab
                output.accept(ModItems.HAND_DRILL_DESH.get()); // partsTab
                output.accept(ModItems.SCREWDRIVER.get()); // partsTab
                output.accept(ModItems.SCREWDRIVER_DESH.get()); // partsTab
                output.accept(ModItems.TELEPAD.get());}));
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> CONTROL_TAB = TABS.register("control",
            () -> tab("itemGroup.tabControl", output -> {
            
                output.accept(ModItems.PISTON_SELENIUM.get());
                output.accept(ModItems.THERMO_ELEMENT.get());
                output.accept(ModItems.CATALYTIC_CONVERTER.get());
                output.accept(ModItems.PART_LITHIUM.get());
                output.accept(ModItems.PART_BERYLLIUM.get());
                output.accept(ModItems.PART_CARBON.get());
                output.accept(ModItems.PART_COPPER.get());
                output.accept(ModItems.PART_PLUTONIUM.get());
                output.accept(ModItems.GAS_EMPTY.get());
                output.accept(ModItems.ROD_EMPTY.get());
                output.accept(ModItems.ROD_DUAL_EMPTY.get());
                output.accept(ModItems.ROD_QUAD_EMPTY.get());
                output.accept(ModItems.ROD_ZIRNOX_EMPTY.get());
                output.accept(ModItems.ROD_ZIRNOX_TRITIUM.get());
                output.accept(ModItems.MOLD_BASE.get());
                output.accept(ModItems.DEBRIS_GRAPHITE.get());
                output.accept(ModItems.DEBRIS_METAL.get());
                output.accept(ModItems.DEBRIS_FUEL.get());
                output.accept(ModItems.DEBRIS_CONCRETE.get());
                output.accept(ModItems.DEBRIS_SHRAPNEL.get());
                output.accept(ModItems.DEBRIS_EXCHANGER.get());
                output.accept(ModItems.DEBRIS_ELEMENT.get());
                output.accept(ModItems.RBMK_FUEL_EMPTY.get());
                output.accept(ModItems.ICF_PELLET_EMPTY.get());
                output.accept(ModItems.ICF_PELLET_DEPLETED.get());
                output.accept(ModItems.PARTICLE_EMPTY.get());
                output.accept(ModItems.PARTICLE_HYDROGEN.get());
                output.accept(ModItems.PARTICLE_COPPER.get());
                output.accept(ModItems.PARTICLE_LEAD.get());
                output.accept(ModItems.PARTICLE_MUON.get());
                output.accept(ModItems.PARTICLE_AMAT.get());
                output.accept(ModItems.PARTICLE_ASCHRAB.get());
                output.accept(ModItems.PARTICLE_HIGGS.get());
                output.accept(ModItems.PARTICLE_TACHYON.get());
                output.accept(ModItems.PARTICLE_DARK.get());
                output.accept(ModItems.PARTICLE_STRANGE.get());
                output.accept(ModItems.PARTICLE_SPARKTICLE.get());
                output.accept(ModItems.AMS_CATALYST_BLANK.get());
                output.accept(ModItems.DUCC.get());}));
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> TEMPLATE_TAB = TABS.register("template",
            () -> tab("itemGroup.tabTemplate", output -> {
            
                output.accept(ModItems.TEMPLATE_FOLDER.get());
                output.accept(ModItems.JOURNAL_PIP.get());
                output.accept(ModItems.JOURNAL_BJ.get());
                output.accept(ModItems.JOURNAL_SILVER.get());}));
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> RESOURCE_TAB = TABS.register("resource",
            () -> tab("itemGroup.tabResource", output -> {
            }));
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> BLOCK_TAB = TABS.register("blocks",
            () -> tab("itemGroup.tabBlocks", output -> {
                output.accept(ModBlocks.ASPHALT.get());        // 闂?asphalt.setCreativeTab(blockTab)
                output.accept(ModBlocks.BLOCK_LITHIUM.get());  // 闂?block_lithium.setCreativeTab(blockTab)
                output.accept(ModBlocks.BRICK_CONCRETE_MARKED.get()); // 闂?brick_concrete_marked
                output.accept(ModBlocks.POLE_TOP.get());       // 闂?pole_top.setCreativeTab(blockTab)
                // 濠殿喖顭崹浼存偤?闁?2闂佹寧绋戦悧鍡欐暜?ladder_* .setCreativeTab(blockTab)闂?                output.accept(ModBlocks.LADDER_STURDY.get());
                output.accept(ModBlocks.LADDER_IRON.get());
                output.accept(ModBlocks.LADDER_GOLD.get());
                output.accept(ModBlocks.LADDER_ALUMINIUM.get());
                output.accept(ModBlocks.LADDER_COPPER.get());
                output.accept(ModBlocks.LADDER_TITANIUM.get());
                output.accept(ModBlocks.LADDER_LEAD.get());
                output.accept(ModBlocks.LADDER_COBALT.get());
                output.accept(ModBlocks.LADDER_STEEL.get());
                output.accept(ModBlocks.LADDER_TUNGSTEN.get());
                output.accept(ModBlocks.LADDER_RED.get());
                output.accept(ModBlocks.LADDER_RED_TOP.get());
                // 闂備礁顫曢崹褰掑春濡も偓鐓ょ紓浣股戠欢鏌ユ⒒?                output.accept(ModBlocks.TRAPDOOR_STEEL.get());
            
                output.accept(ModBlocks.BASALT_ORE.get().asItem());
                output.accept(ModBlocks.BLOCK_ACTINIUM.get().asItem());
                output.accept(ModBlocks.BLOCK_ALUMINIUM.get().asItem());
                output.accept(ModBlocks.BLOCK_ASBESTOS.get().asItem());
                output.accept(ModBlocks.BLOCK_AUSTRALIUM.get().asItem());
                output.accept(ModBlocks.BLOCK_BAKELITE.get().asItem());
                output.accept(ModBlocks.BLOCK_BERYLLIUM.get().asItem());
                output.accept(ModBlocks.BLOCK_BISMUTH.get().asItem());
                output.accept(ModBlocks.BLOCK_BORON.get().asItem());
                output.accept(ModBlocks.BLOCK_CADMIUM.get().asItem());
                output.accept(ModBlocks.BLOCK_CDALLOY.get().asItem());
                output.accept(ModBlocks.BLOCK_COBALT.get().asItem());
                output.accept(ModBlocks.BLOCK_COKE.get().asItem());
                output.accept(ModBlocks.BLOCK_COLTAN.get().asItem());
                output.accept(ModBlocks.BLOCK_COMBINE_STEEL.get().asItem());
                output.accept(ModBlocks.BLOCK_COPPER.get().asItem());
                output.accept(ModBlocks.BLOCK_DESH.get().asItem());
                output.accept(ModBlocks.BLOCK_DINEUTRONIUM.get().asItem());
                output.accept(ModBlocks.BLOCK_DURA_STEEL.get().asItem());
                output.accept(ModBlocks.BLOCK_EUPHEMIUM.get().asItem());
                output.accept(ModBlocks.BLOCK_FIBERGLASS.get().asItem());
                output.accept(ModBlocks.BLOCK_FLUORITE.get().asItem());
                output.accept(ModBlocks.BLOCK_GRAPHITE.get().asItem());
                output.accept(ModBlocks.BLOCK_LANTHANIUM.get().asItem());
                output.accept(ModBlocks.BLOCK_LEAD.get().asItem());
                output.accept(ModBlocks.BLOCK_MAGNETIZED_TUNGSTEN.get().asItem());
                output.accept(ModBlocks.BLOCK_NEPTUNIUM.get().asItem());
                output.accept(ModBlocks.BLOCK_NIOBIUM.get().asItem());
                output.accept(ModBlocks.BLOCK_NITER.get().asItem());
                output.accept(ModBlocks.BLOCK_PLUTONIUM.get().asItem());
                output.accept(ModBlocks.BLOCK_POLONIUM.get().asItem());
                output.accept(ModBlocks.BLOCK_POLYMER.get().asItem());
                output.accept(ModBlocks.BLOCK_PU_MIX.get().asItem());
                output.accept(ModBlocks.BLOCK_PU238.get().asItem());
                output.accept(ModBlocks.BLOCK_PU239.get().asItem());
                output.accept(ModBlocks.BLOCK_PU240.get().asItem());
                output.accept(ModBlocks.BLOCK_RA226.get().asItem());
                output.accept(ModBlocks.BLOCK_RED_COPPER.get().asItem());
                output.accept(ModBlocks.BLOCK_RED_PHOSPHORUS.get().asItem());
                output.accept(ModBlocks.BLOCK_RUBBER.get().asItem());
                output.accept(ModBlocks.BLOCK_SCHRABIDATE.get().asItem());
                output.accept(ModBlocks.BLOCK_SCHRABIDIUM.get().asItem());
                output.accept(ModBlocks.BLOCK_SCHRARANIUM.get().asItem());
                output.accept(ModBlocks.BLOCK_SLAG.get().asItem());
                output.accept(ModBlocks.BLOCK_SOLINIUM.get().asItem());
                output.accept(ModBlocks.BLOCK_STARMETAL.get().asItem());
                output.accept(ModBlocks.BLOCK_STEEL.get().asItem());
                output.accept(ModBlocks.BLOCK_SULFUR.get().asItem());
                output.accept(ModBlocks.BLOCK_TANTALIUM.get().asItem());
                output.accept(ModBlocks.BLOCK_TCALLOY.get().asItem());
                output.accept(ModBlocks.BLOCK_THORIUM.get().asItem());
                output.accept(ModBlocks.BLOCK_TITANIUM.get().asItem());
                output.accept(ModBlocks.BLOCK_TUNGSTEN.get().asItem());
                output.accept(ModBlocks.BLOCK_U233.get().asItem());
                output.accept(ModBlocks.BLOCK_U235.get().asItem());
                output.accept(ModBlocks.BLOCK_U238.get().asItem());
                output.accept(ModBlocks.BLOCK_URANIUM.get().asItem());
                output.accept(ModBlocks.BLOCK_WHITE_PHOSPHORUS.get().asItem());
                output.accept(ModBlocks.BLOCK_ZIRCONIUM.get().asItem());
                output.accept(ModBlocks.CONCRETE.get().asItem());
                output.accept(ModBlocks.CONCRETE_ASBESTOS.get().asItem());
                output.accept(ModBlocks.CONCRETE_COLORED.get().asItem());
                output.accept(ModBlocks.CONCRETE_COLORED_EXT.get().asItem());
                output.accept(ModBlocks.CONCRETE_SMOOTH.get().asItem());
                output.accept(ModBlocks.DUCRETE.get().asItem());
                output.accept(ModBlocks.DUCRETE_SMOOTH.get().asItem());
                output.accept(ModBlocks.GLASS_ASH.get().asItem());
                output.accept(ModBlocks.GLASS_BORON.get().asItem());
                output.accept(ModBlocks.GLASS_LEAD.get().asItem());
                output.accept(ModBlocks.GLASS_POLONIUM.get().asItem());
                output.accept(ModBlocks.GLASS_TRINITITE.get().asItem());
                output.accept(ModBlocks.GLASS_URANIUM.get().asItem());
                output.accept(ModBlocks.GRAVEL_DIAMOND.get().asItem());
                output.accept(ModBlocks.ORE_ALUMINIUM.get().asItem());
                output.accept(ModBlocks.ORE_ASBESTOS.get().asItem());
                output.accept(ModBlocks.ORE_AUSTRALIUM.get().asItem());
                output.accept(ModBlocks.ORE_BERYLLIUM.get().asItem());
                output.accept(ModBlocks.ORE_CINNABAR.get().asItem());
                output.accept(ModBlocks.ORE_COBALT.get().asItem());
                output.accept(ModBlocks.ORE_COLTAN.get().asItem());
                output.accept(ModBlocks.ORE_COPPER.get().asItem());
                output.accept(ModBlocks.ORE_DEPTH_BORAX.get().asItem());
                output.accept(ModBlocks.ORE_DEPTH_CINNABAR.get().asItem());
                output.accept(ModBlocks.ORE_DEPTH_NETHER_NEODYMIUM.get().asItem());
                output.accept(ModBlocks.ORE_DEPTH_ZIRCONIUM.get().asItem());
                output.accept(ModBlocks.ORE_FLUORITE.get().asItem());
                output.accept(ModBlocks.ORE_GNEISS_ASBESTOS.get().asItem());
                output.accept(ModBlocks.ORE_GNEISS_COPPER.get().asItem());
                output.accept(ModBlocks.ORE_GNEISS_GOLD.get().asItem());
                output.accept(ModBlocks.ORE_GNEISS_IRON.get().asItem());
                output.accept(ModBlocks.ORE_GNEISS_LITHIUM.get().asItem());
                output.accept(ModBlocks.ORE_GNEISS_RARE.get().asItem());
                output.accept(ModBlocks.ORE_GNEISS_SCHRABIDIUM.get().asItem());
                output.accept(ModBlocks.ORE_GNEISS_URANIUM.get().asItem());
                output.accept(ModBlocks.ORE_GNEISS_URANIUM_SCORCHED.get().asItem());
                output.accept(ModBlocks.ORE_LEAD.get().asItem());
                output.accept(ModBlocks.ORE_LIGNITE.get().asItem());
                output.accept(ModBlocks.ORE_NETHER_COBALT.get().asItem());
                output.accept(ModBlocks.ORE_NETHER_PLUTONIUM.get().asItem());
                output.accept(ModBlocks.ORE_NETHER_SCHRABIDIUM.get().asItem());
                output.accept(ModBlocks.ORE_NETHER_SULFUR.get().asItem());
                output.accept(ModBlocks.ORE_NETHER_TUNGSTEN.get().asItem());
                output.accept(ModBlocks.ORE_NETHER_URANIUM.get().asItem());
                output.accept(ModBlocks.ORE_NETHER_URANIUM_SCORCHED.get().asItem());
                output.accept(ModBlocks.ORE_NITER.get().asItem());
                output.accept(ModBlocks.ORE_RARE.get().asItem());
                output.accept(ModBlocks.ORE_SCHRABIDIUM.get().asItem());
                output.accept(ModBlocks.ORE_SELLAFIELD_DIAMOND.get().asItem());
                output.accept(ModBlocks.ORE_SELLAFIELD_EMERALD.get().asItem());
                output.accept(ModBlocks.ORE_SELLAFIELD_SCHRABIDIUM.get().asItem());
                output.accept(ModBlocks.ORE_SELLAFIELD_URANIUM_SCORCHED.get().asItem());
                output.accept(ModBlocks.ORE_SULFUR.get().asItem());
                output.accept(ModBlocks.ORE_THORIUM.get().asItem());
                output.accept(ModBlocks.ORE_TITANIUM.get().asItem());
                output.accept(ModBlocks.ORE_TUNGSTEN.get().asItem());
                output.accept(ModBlocks.ORE_URANIUM.get().asItem());
                output.accept(ModBlocks.ORE_URANIUM_SCORCHED.get().asItem());
                output.accept(ModBlocks.PINK_PLANKS.get().asItem());
                output.accept(ModBlocks.PINK_SLAB.get().asItem());
                output.accept(ModBlocks.PINK_STAIRS.get().asItem());
                output.accept(ModBlocks.STONE_RESOURCE.get().asItem());}));
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> MACHINE_TAB = TABS.register("machine",
            () -> tab("itemGroup.tabMachine", output -> {
                output.accept(ModBlocks.ASHPIT.get());         // 闂?machine_ashpit.setCreativeTab(machineTab)
            }));
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> NUKE_TAB = TABS.register("nuke",
            () -> tab("itemGroup.tabNuke", output -> {
            
                output.accept(ModItems.GADGET_EXPLOSIVE.get());
                output.accept(ModItems.MAN_EXPLOSIVE.get());}));
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> MISSILE_TAB = TABS.register("missile",
            () -> tab("itemGroup.tabMissile", output -> {
            }));
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> WEAPON_TAB = TABS.register("weapon",
            () -> tab("itemGroup.tabWeapon", output -> {
            
                output.accept(ModItems.DISPERSER_CANISTER_EMPTY.get());
                output.accept(ModItems.GLYPHID_GLAND_EMPTY.get());}));
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> CONSUMABLE_TAB = TABS.register("consumable",
            () -> tab("itemGroup.tabConsumable", output -> {
            
                output.accept(ModItems.SYRINGE_EMPTY.get());
                output.accept(ModItems.SYRINGE_METAL_EMPTY.get());
                output.accept(ModItems.CAP_NUKA.get());
                output.accept(ModItems.CAP_QUANTUM.get());
                output.accept(ModItems.CAP_SPARKLE.get());
                output.accept(ModItems.CAP_RAD.get());
                output.accept(ModItems.CAP_KORL.get());
                output.accept(ModItems.CAP_FRITZ.get());
                output.accept(ModItems.RING_PULL.get());
                output.accept(ModItems.BOTTLE_EMPTY.get());
                output.accept(ModItems.BOTTLE2_EMPTY.get());
                output.accept(ModItems.EGG_GLYPHID.get());
                output.accept(ModItems.CAN_KEY.get());
                output.accept(ModItems.ACETYLENE_TORCH.get());
                output.accept(ModItems.BALL_RESIN.get());
                output.accept(ModItems.BDCL.get());
                output.accept(ModItems.BILLET_ACTINIUM.get());
                output.accept(ModItems.BILLET_AM_MIX.get());
                output.accept(ModItems.BILLET_AM241.get());
                output.accept(ModItems.BILLET_AM242.get());
                output.accept(ModItems.BILLET_AU198.get());
                output.accept(ModItems.BILLET_AUSTRALIUM.get());
                output.accept(ModItems.BILLET_BERYLLIUM.get());
                output.accept(ModItems.BILLET_BISMUTH.get());
                output.accept(ModItems.BILLET_CO60.get());
                output.accept(ModItems.BILLET_COBALT.get());
                output.accept(ModItems.BILLET_GH336.get());
                output.accept(ModItems.BILLET_NEPTUNIUM.get());
                output.accept(ModItems.BILLET_PB209.get());
                output.accept(ModItems.BILLET_PLUTONIUM.get());
                output.accept(ModItems.BILLET_POLONIUM.get());
                output.accept(ModItems.BILLET_PU_MIX.get());
                output.accept(ModItems.BILLET_PU238.get());
                output.accept(ModItems.BILLET_PU239.get());
                output.accept(ModItems.BILLET_PU240.get());
                output.accept(ModItems.BILLET_PU241.get());
                output.accept(ModItems.BILLET_RA226.get());
                output.accept(ModItems.BILLET_SCHRABIDIUM.get());
                output.accept(ModItems.BILLET_SILICON.get());
                output.accept(ModItems.BILLET_SOLINIUM.get());
                output.accept(ModItems.BILLET_SR90.get());
                output.accept(ModItems.BILLET_TECHNETIUM.get());
                output.accept(ModItems.BILLET_TH232.get());
                output.accept(ModItems.BILLET_U233.get());
                output.accept(ModItems.BILLET_U235.get());
                output.accept(ModItems.BILLET_U238.get());
                output.accept(ModItems.BILLET_URANIUM.get());
                output.accept(ModItems.BILLET_ZIRCONIUM.get());
                output.accept(ModItems.BLOWTORCH.get());
                output.accept(ModItems.CHEMISTRY_SET.get());
                output.accept(ModItems.CHEMISTRY_SET_BORON.get());
                output.accept(ModItems.CRYSTAL_ASBESTOS.get());
                output.accept(ModItems.CRYSTAL_LEAD.get());
                output.accept(ModItems.CRYSTAL_LITHIUM.get());
                output.accept(ModItems.CRYSTAL_OSMIRIDIUM.get());
                output.accept(ModItems.CRYSTAL_PHOSPHORUS.get());
                output.accept(ModItems.CRYSTAL_PLUTONIUM.get());
                output.accept(ModItems.CRYSTAL_SCHRABIDIUM.get());
                output.accept(ModItems.CRYSTAL_SCHRARANIUM.get());
                output.accept(ModItems.CRYSTAL_THORIUM.get());
                output.accept(ModItems.CRYSTAL_URANIUM.get());
                output.accept(ModItems.FLUID_BARREL_FULL.get());
                output.accept(ModItems.FLUID_BARREL_V2.get());
                output.accept(ModItems.FLUID_TANK_FULL.get());
                output.accept(ModItems.FLUID_TANK_LEAD_FULL.get());
                output.accept(ModItems.FLUID_TANK_LEAD_V2.get());
                output.accept(ModItems.FLUID_TANK_V2.get());
                output.accept(ModItems.GEM_SODALITE.get());
                output.accept(ModItems.GEM_TANTALIUM.get());
                output.accept(ModItems.LIGNITE.get());
                output.accept(ModItems.LITHIUM.get());
                output.accept(ModItems.NUGGET_ACTINIUM.get());
                output.accept(ModItems.NUGGET_AM_MIX.get());
                output.accept(ModItems.NUGGET_AM241.get());
                output.accept(ModItems.NUGGET_AM242.get());
                output.accept(ModItems.NUGGET_ARSENIC.get());
                output.accept(ModItems.NUGGET_AU198.get());
                output.accept(ModItems.NUGGET_AUSTRALIUM.get());
                output.accept(ModItems.NUGGET_BERYLLIUM.get());
                output.accept(ModItems.NUGGET_BISMUTH.get());
                output.accept(ModItems.NUGGET_CO60.get());
                output.accept(ModItems.NUGGET_COBALT.get());
                output.accept(ModItems.NUGGET_DESH.get());
                output.accept(ModItems.NUGGET_DINEUTRONIUM.get());
                output.accept(ModItems.NUGGET_EUPHEMIUM.get());
                output.accept(ModItems.NUGGET_GH336.get());
                output.accept(ModItems.NUGGET_LEAD.get());
                output.accept(ModItems.NUGGET_NEPTUNIUM.get());
                output.accept(ModItems.NUGGET_NIOBIUM.get());
                output.accept(ModItems.NUGGET_OSMIRIDIUM.get());
                output.accept(ModItems.NUGGET_PB209.get());
                output.accept(ModItems.NUGGET_PLUTONIUM.get());
                output.accept(ModItems.NUGGET_POLONIUM.get());
                output.accept(ModItems.NUGGET_PU_MIX.get());
                output.accept(ModItems.NUGGET_PU238.get());
                output.accept(ModItems.NUGGET_PU239.get());
                output.accept(ModItems.NUGGET_PU240.get());
                output.accept(ModItems.NUGGET_PU241.get());
                output.accept(ModItems.NUGGET_RA226.get());
                output.accept(ModItems.NUGGET_SCHRABIDIUM.get());
                output.accept(ModItems.NUGGET_SILICON.get());
                output.accept(ModItems.NUGGET_SOLINIUM.get());
                output.accept(ModItems.NUGGET_SR90.get());
                output.accept(ModItems.NUGGET_TANTALIUM.get());
                output.accept(ModItems.NUGGET_TECHNETIUM.get());
                output.accept(ModItems.NUGGET_TH232.get());
                output.accept(ModItems.NUGGET_U233.get());
                output.accept(ModItems.NUGGET_U235.get());
                output.accept(ModItems.NUGGET_U238.get());
                output.accept(ModItems.NUGGET_URANIUM.get());
                output.accept(ModItems.NUGGET_ZIRCONIUM.get());
                output.accept(ModItems.PLATE_LEAD.get());
                output.accept(ModItems.PLATE_SCHRABIDIUM.get());
                output.accept(ModItems.POWDER_ACTINIUM.get());
                output.accept(ModItems.POWDER_ASTATINE.get());
                output.accept(ModItems.POWDER_AT209.get());
                output.accept(ModItems.POWDER_AT209_TINY.get());
                output.accept(ModItems.POWDER_AU198.get());
                output.accept(ModItems.POWDER_AU198_TINY.get());
                output.accept(ModItems.POWDER_AUSTRALIUM.get());
                output.accept(ModItems.POWDER_BAKELITE.get());
                output.accept(ModItems.POWDER_BORON.get());
                output.accept(ModItems.POWDER_BROMINE.get());
                output.accept(ModItems.POWDER_CERIUM.get());
                output.accept(ModItems.POWDER_CERIUM_TINY.get());
                output.accept(ModItems.POWDER_CO60.get());
                output.accept(ModItems.POWDER_CO60_TINY.get());
                output.accept(ModItems.POWDER_COAL.get());
                output.accept(ModItems.POWDER_COAL_TINY.get());
                output.accept(ModItems.POWDER_COBALT.get());
                output.accept(ModItems.POWDER_COBALT_TINY.get());
                output.accept(ModItems.POWDER_COLTAN_ORE.get());
                output.accept(ModItems.POWDER_CS137.get());
                output.accept(ModItems.POWDER_CS137_TINY.get());
                output.accept(ModItems.POWDER_DINEUTRONIUM.get());
                output.accept(ModItems.POWDER_DURA_STEEL.get());
                output.accept(ModItems.POWDER_EUPHEMIUM.get());
                output.accept(ModItems.POWDER_FIRE.get());
                output.accept(ModItems.POWDER_I131.get());
                output.accept(ModItems.POWDER_I131_TINY.get());
                output.accept(ModItems.POWDER_IODINE.get());
                output.accept(ModItems.POWDER_IODINE_TINY.get());
                output.accept(ModItems.POWDER_LANTHANIUM.get());
                output.accept(ModItems.POWDER_LEAD.get());
                output.accept(ModItems.POWDER_LIGNITE.get());
                output.accept(ModItems.POWDER_LITHIUM.get());
                output.accept(ModItems.POWDER_LITHIUM_TINY.get());
                output.accept(ModItems.POWDER_MAGNETIZED_TUNGSTEN.get());
                output.accept(ModItems.POWDER_NEODYMIUM.get());
                output.accept(ModItems.POWDER_NEODYMIUM_TINY.get());
                output.accept(ModItems.POWDER_NEPTUNIUM.get());
                output.accept(ModItems.POWDER_NIOBIUM.get());
                output.accept(ModItems.POWDER_NIOBIUM_TINY.get());
                output.accept(ModItems.POWDER_PB209.get());
                output.accept(ModItems.POWDER_PB209_TINY.get());
                output.accept(ModItems.POWDER_PLUTONIUM.get());
                output.accept(ModItems.POWDER_POLONIUM.get());
                output.accept(ModItems.POWDER_POLYMER.get());
                output.accept(ModItems.POWDER_RA226.get());
                output.accept(ModItems.POWDER_SCHRABIDATE.get());
                output.accept(ModItems.POWDER_SCHRABIDIUM.get());
                output.accept(ModItems.POWDER_SR90.get());
                output.accept(ModItems.POWDER_SR90_TINY.get());
                output.accept(ModItems.POWDER_TANTALIUM.get());
                output.accept(ModItems.POWDER_TCALLOY.get());
                output.accept(ModItems.POWDER_TENNESSINE.get());
                output.accept(ModItems.POWDER_THORIUM.get());
                output.accept(ModItems.POWDER_URANIUM.get());
                output.accept(ModItems.POWDER_XE135.get());
                output.accept(ModItems.POWDER_XE135_TINY.get());
                output.accept(ModItems.GLYPHID_MEAT.get()); // consumableTab
                output.accept(ModItems.GLYPHID_MEAT_GRILLED.get()); // consumableTab
                output.accept(ModItems.NUGGET.get()); // consumableTab
                output.accept(ModItems.CAN_EMPTY.get());}));
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> TEST_TAB = TABS.register("test",
            () -> tab("itemGroup.tabTest", output -> {
            }));
}
