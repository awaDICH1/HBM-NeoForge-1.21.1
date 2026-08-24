package com.hbm.inventory;

import com.hbm.blocks.ModBlocks;
import com.hbm.config.GeneralConfig;
import com.hbm.hazard.HazardData;
import com.hbm.hazard.HazardEntry;
import com.hbm.hazard.HazardRegistry;
import com.hbm.hazard.HazardSystem;
import com.hbm.inventory.material.MaterialShapes;
import com.hbm.inventory.material.Mats;
import com.hbm.inventory.material.NTMMaterial;
import com.hbm.items.ModItems;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.*;

import static com.hbm.blocks.BlockEnums.EnumBasaltOreType;
import static com.hbm.blocks.BlockEnums.EnumStoneType;
import static com.hbm.blocks.ModBlocks.*;
import static com.hbm.inventory.OreDictManager.DictFrame.fromAll;
import static com.hbm.inventory.OreDictManager.DictFrame.fromOne;
import static com.hbm.inventory.material.MaterialShapes.*;
import static com.hbm.items.ItemEnums.EnumAshType;
import static com.hbm.items.ItemEnums.EnumBriquetteType;
import static com.hbm.items.ItemEnums.EnumChunkType;
import static com.hbm.items.ItemEnums.EnumCokeType;
import static com.hbm.items.ItemEnums.EnumTarType;
import static com.hbm.items.ModItems.*;

//mlbv: all future changes to this class should stay additive
//the more i optimize this, the more it starts looking like gregtech
public class OreDictManager {

    /*
     * Standard keys
     */
    public static final String KEY_STICK = "stickWood";                    //if there's no "any" or "<shape>Any" prefix required, simply use a String key instead of a DictFrame
    public static final String KEY_ANYGLASS = "blockGlass";
    public static final String KEY_CLEARGLASS = "blockGlassColorless";
    public static final String KEY_ANYPANE = "paneGlass";
    public static final String KEY_CLEARPANE = "paneGlassColorless";
    public static final String KEY_BRICK = "ingotBrick";
    public static final String KEY_NETHERBRICK = "ingotBrickNether";
    public static final String KEY_SLIME = "slimeball";
    public static final String KEY_LOG = "logWood";
    public static final String KEY_PLANKS = "plankWood";
    public static final String KEY_SLAB = "slabWood";
    public static final String KEY_LEAVES = "treeLeaves";
    public static final String KEY_SAPLING = "treeSapling";
    public static final String KEY_SAND = "sand";
    public static final String KEY_COBBLESTONE = "cobblestone";
    public static final String KEY_GRAVEL = "gravel";
    public static final String KEY_BLACK = "dyeBlack";
    public static final String KEY_RED = "dyeRed";
    public static final String KEY_GREEN = "dyeGreen";
    public static final String KEY_BROWN = "dyeBrown";
    public static final String KEY_BLUE = "dyeBlue";
    public static final String KEY_PURPLE = "dyePurple";
    public static final String KEY_CYAN = "dyeCyan";
    public static final String KEY_LIGHTGRAY = "dyeLightGray";
    public static final String KEY_GRAY = "dyeGray";
    public static final String KEY_PINK = "dyePink";
    public static final String KEY_LIME = "dyeLime";
    public static final String KEY_YELLOW = "dyeYellow";
    public static final String KEY_LIGHTBLUE = "dyeLightBlue";
    public static final String KEY_MAGENTA = "dyeMagenta";
    public static final String KEY_ORANGE = "dyeOrange";
    public static final String KEY_WHITE = "dyeWhite";
    public static final String KEY_OIL_TAR = "oiltar";
    public static final String KEY_CRACK_TAR = "cracktar";
    public static final String KEY_COAL_TAR = "coaltar";
    public static final String KEY_WOOD_TAR = "woodtar";
    public static final String KEY_UNIVERSAL_TANK = "ntmuniversaltank";
    public static final String KEY_HAZARD_TANK = "ntmhazardtank";
    public static final String KEY_UNIVERSAL_BARREL = "ntmuniversalbarrel";
    public static final String KEY_TOOL_SCREWDRIVER = "ntmscrewdriver";
    public static final String KEY_TOOL_HANDDRILL = "ntmhanddrill";
    public static final String KEY_TOOL_CHEMISTRYSET = "ntmchemistryset";
    public static final String KEY_TOOL_TORCH = "ntmtorch";

    public static final String KEY_GLYPHID_MEAT = "glyphidMeat";
    /*
     * MATERIALS
     */
    /*
     * VANILLA
     */
    public static final DictFrame WOOD = new DictFrame("Wood");
    public static final DictFrame BONE = new DictFrame("Bone");
    public static final DictFrame COAL = new DictFrame("Coal");
    public static final DictFrame IRON = new DictFrame("Iron");
    public static final DictFrame GOLD = new DictFrame("Gold");
    public static final DictFrame LAPIS = new DictFrame("Lapis");
    public static final DictFrame REDSTONE = new DictFrame("Redstone");
    public static final DictFrame QUARTZ = new DictFrame("Quartz");
    public static final DictFrame NETHERQUARTZ = new DictFrame("NetherQuartz");
    public static final DictFrame DIAMOND = new DictFrame("Diamond");
    public static final DictFrame EMERALD = new DictFrame("Emerald");
    /*
     * RADIOACTIVE
     */
    public static final DictFrame U = new DictFrame("Uranium");
    public static final DictFrame U233 = new DictFrame("Uranium233", "U233");
    public static final DictFrame U235 = new DictFrame("Uranium235", "U235");
    public static final DictFrame U238 = new DictFrame("Uranium238", "U238");
    public static final DictFrame TH232 = new DictFrame("Thorium232", "Th232", "Thorium");
    public static final DictFrame PU = new DictFrame("Plutonium");
    public static final DictFrame PURG = new DictFrame("PlutoniumRG");
    public static final DictFrame PU238 = new DictFrame("Plutonium238", "Pu238");
    public static final DictFrame PU239 = new DictFrame("Plutonium239", "Pu239");
    public static final DictFrame PU240 = new DictFrame("Plutonium240", "Pu240");
    public static final DictFrame PU241 = new DictFrame("Plutonium241", "Pu241");
    public static final DictFrame AM241 = new DictFrame("Americium241", "Am241");
    public static final DictFrame AM242 = new DictFrame("Americium242", "Am242");
    public static final DictFrame AMRG = new DictFrame("AmericiumRG");
    public static final DictFrame NP237 = new DictFrame("Neptunium237", "Np237", "Neptunium");
    public static final DictFrame PO210 = new DictFrame("Polonium210", "Po210", "Polonium");
    public static final DictFrame TC99 = new DictFrame("Technetium99", "Tc99");
    public static final DictFrame RA226 = new DictFrame("Radium226", "Ra226");
    public static final DictFrame AC227 = new DictFrame("Actinium227", "Ac227");
    public static final DictFrame CO60 = new DictFrame("Cobalt60", "Co60");
    public static final DictFrame AU198 = new DictFrame("Gold198", "Au198");
    public static final DictFrame PB209 = new DictFrame("Lead209", "Pb209");
    public static final DictFrame SA326 = new DictFrame("Schrabidium");
    public static final DictFrame SA327 = new DictFrame("Solinium");
    public static final DictFrame SBD = new DictFrame("Schrabidate");
    public static final DictFrame SRN = new DictFrame("Schraranium");
    public static final DictFrame GH336 = new DictFrame("Ghiorsium336", "Gh336");
    public static final DictFrame MUD = new DictFrame("WatzMud");
    /* RADIOACTIVE FUELS */
    public static final DictFrame U_FUEL = new DictFrame("UraniumFuel");
    public static final DictFrame TH_FUEL = new DictFrame("ThoriumFuel");
    public static final DictFrame PU_FUEL = new DictFrame("PlutoniumFuel");
    public static final DictFrame NP_FUEL = new DictFrame("NeptuniumFuel");
    public static final DictFrame MOX_FUEL = new DictFrame("MoxFuel", "Mox");
    public static final DictFrame AM_FUEL = new DictFrame("AmericiumFuel");
    public static final DictFrame SCH_FUEL = new DictFrame("ScharbidiumFuel");
    public static final DictFrame LES_FUEL = new DictFrame("LesFuel", "Les");
    public static final DictFrame HES_FUEL = new DictFrame("HesFuel", "Hes");
    /*
     * STABLE
     */
    public static final DictFrame NITANIUM = new DictFrame("Nitanium");
    /**
     * TITANIUM
     */
    public static final DictFrame TI = new DictFrame("Titanium");
    /**
     * COPPER
     */
    public static final DictFrame CU = new DictFrame("Copper");
    public static final DictFrame MINGRADE = new DictFrame("Mingrade");
    /**
     * TUNGSTEN
     */
    public static final DictFrame W = new DictFrame("Tungsten");
    public static final DictFrame WC = new DictFrame("TungstenCarbide");
    /**
     * ALUMINUM
     */
    public static final DictFrame AL = new DictFrame("Aluminum");
    public static final DictFrame STEEL = new DictFrame("Steel");
    /**
     * TECHNETIUM STEEL
     */
    public static final DictFrame TCALLOY = new DictFrame("TcAlloy");
    /**
     * CADMIUM STEEL
     */
    public static final DictFrame CDALLOY = new DictFrame("CdAlloy");
    /**
     * BISMUTH BRONZE
     */
    public static final DictFrame BBRONZE = new DictFrame("BismuthBronze");
    /**
     * ARSENIC BRONZE
     */
    public static final DictFrame ABRONZE = new DictFrame("ArsenicBronze");
    /**
     * BISMUTH STRONTIUM CALCIUM COPPER OXIDE
     */
    public static final DictFrame BSCCO = new DictFrame("BSCCO");
    /**
     * LEAD
     */
    public static final DictFrame PB = new DictFrame("Lead");
    public static final DictFrame BI = new DictFrame("Bismuth");
    public static final DictFrame CD = new DictFrame("Cadmium");
    public static final DictFrame AS = new DictFrame("Arsenic");
    public static final DictFrame CA = new DictFrame("Calcium");
    /**
     * TANTALUM
     */
    public static final DictFrame TA = new DictFrame("Tantalum");
    public static final DictFrame COLTAN = new DictFrame("Coltan");
    /**
     * NIOBIUM
     */
    public static final DictFrame NB = new DictFrame("Niobium");
    /**
     * BERYLLIUM
     */
    public static final DictFrame BE = new DictFrame("Beryllium");
    /**
     * COBALT
     */
    public static final DictFrame CO = new DictFrame("Cobalt");
    /**
     * BORON
     */
    public static final DictFrame B = new DictFrame("Boron");
    /**
     * SILICON
     */
    public static final DictFrame SI = new DictFrame("Silicon");
    public static final DictFrame GRAPHITE = new DictFrame("Graphite");
    public static final DictFrame CARBON = new DictFrame("Carbon");
    public static final DictFrame DURA = new DictFrame("DuraSteel");
    public static final DictFrame POLYMER = new DictFrame("Polymer");
    public static final DictFrame BAKELITE = new DictFrame("Bakelite");
    public static final DictFrame PET = new DictFrame("PET");
    public static final DictFrame PC = new DictFrame("Polycarbonate");
    public static final DictFrame PVC = new DictFrame("PVC");
    public static final DictFrame LATEX = new DictFrame("Latex");
    public static final DictFrame RUBBER = new DictFrame("Rubber");
    public static final DictFrame MAGTUNG = new DictFrame("MagnetizedTungsten");
    public static final DictFrame CMB = new DictFrame("CMBSteel");
    public static final DictFrame DESH = new DictFrame("WorkersAlloy");
    public static final DictFrame STAR = new DictFrame("Starmetal");
    public static final DictFrame GUNMETAL = new DictFrame("GunMetal");
    public static final DictFrame WEAPONSTEEL = new DictFrame("WeaponSteel");
    public static final DictFrame BIGMT = new DictFrame("Saturnite");
    public static final DictFrame FERRO = new DictFrame("Ferrouranium");
    public static final DictFrame EUPH = new DictFrame("Euphemium");
    public static final DictFrame DNT = new DictFrame("Dineutronium");
    public static final DictFrame FIBER = new DictFrame("Fiberglass");
    public static final DictFrame ASBESTOS = new DictFrame("Asbestos");
    public static final DictFrame OSMIRIDIUM = new DictFrame("Osmiridium");
    /**
     * SULFUR
     */
    public static final DictFrame S = new DictFrame("Sulfur");
    /*
     * DUST AND GEM ORES
     */
    /**
     * SALTPETER/NITER
     */
    public static final DictFrame KNO = new DictFrame("Saltpeter");
    /**
     * FLUORITE
     */
    public static final DictFrame F = new DictFrame("Fluorite");
    public static final DictFrame LIGNITE = new DictFrame("Lignite");
    public static final DictFrame COALCOKE = new DictFrame("CoalCoke");
    public static final DictFrame PETCOKE = new DictFrame("PetCoke");
    public static final DictFrame LIGCOKE = new DictFrame("LigniteCoke");
    public static final DictFrame CINNABAR = new DictFrame("Cinnabar");
    public static final DictFrame BORAX = new DictFrame("Borax");
    public static final DictFrame CHLOROCALCITE = new DictFrame("Chlorocalcite");
    public static final DictFrame MOLYSITE = new DictFrame("Molysite");
    public static final DictFrame SODALITE = new DictFrame("Sodalite");
    public static final DictFrame VOLCANIC = new DictFrame("Volcanic");
    public static final DictFrame HEMATITE = new DictFrame("Hematite");
    public static final DictFrame MALACHITE = new DictFrame("Malachite");
    public static final DictFrame LIMESTONE = new DictFrame("Limestone");
    public static final DictFrame SLAG = new DictFrame("Slag");
    public static final DictFrame INFERNAL = new DictFrame("InfernalCoal");
    public static final DictFrame BAUXITE = new DictFrame("Bauxite");
    public static final DictFrame CRYOLITE = new DictFrame("Cryolite");
    public static final DictFrame ALEXANDRITE = new DictFrame("Alexandrite");
    /*
     * HAZARDS, MISC
     */
    /**
     * LITHIUM
     */
    public static final DictFrame LI = new DictFrame("Lithium");
    /**
     * SODIUM
     */
    public static final DictFrame NA = new DictFrame("Sodium");
    /*
     * PHOSPHORUS
     */
    public static final DictFrame P_WHITE = new DictFrame("WhitePhosphorus");
    public static final DictFrame P_RED = new DictFrame("RedPhosphorus");
    /*
     * RARE METALS
     */
    public static final DictFrame AUSTRALIUM = new DictFrame("Australium");
    /*
     * RARE EARTHS
     */
    public static final DictFrame RAREEARTH = new DictFrame("RareEarth");
    /**
     * LANTHANUM
     */
    public static final DictFrame LA = new DictFrame("Lanthanum");
    /**
     * ACTINIUM
     */
    public static final DictFrame AC = new DictFrame("Actinium");
    /**
     * ZIRCONIUM
     */
    public static final DictFrame ZR = new DictFrame("Zirconium");
    /**
     * NEODYMIUM
     */
    public static final DictFrame ND = new DictFrame("Neodymium");
    /**
     * CERIUM
     */
    public static final DictFrame CE = new DictFrame("Cerium");
    /**
     * IODINE
     */
    public static final DictFrame I = new DictFrame("Iodine");
    /*
     * NITAN
     */
    /**
     * ASTATINE
     */
    public static final DictFrame AT = new DictFrame("Astatine");
    /**
     * CAESIUM
     */
    public static final DictFrame CS = new DictFrame("Caesium");
    /**
     * STRONTIUM
     */
    public static final DictFrame SR = new DictFrame("Strontium");
    /**
     * BROMINE
     */
    public static final DictFrame BR = new DictFrame("Bromine");
    /**
     * TENNESSINE
     */
    public static final DictFrame TS = new DictFrame("Tennessine");
    /*
     * FISSION FRAGMENTS
     */
    public static final DictFrame SR90 = new DictFrame("Strontium90", "Sr90");
    public static final DictFrame I131 = new DictFrame("Iodine131", "I131");
    public static final DictFrame XE135 = new DictFrame("Xenon135", "Xe135");
    public static final DictFrame CS137 = new DictFrame("Caesium137", "Cs137");
    public static final DictFrame AT209 = new DictFrame("Astatine209", "At209");
    /**
     * Any form of elastic polymer
     */
    public static final DictGroup ANY_RUBBER = new DictGroup("AnyRubber", LATEX, RUBBER);

    /*
     * COLLECTIONS
     */
    /**
     * Any post oil polymer like teflon ("polymer") or bakelite
     */
    public static final DictGroup ANY_PLASTIC = new DictGroup("AnyPlastic", POLYMER, BAKELITE);        //using the Any prefix means that it's just the secondary prefix, and that shape prefixes are applicable
    /**
     * Any post vacuum polymer like PET or PVC
     */
    public static final DictGroup ANY_HARDPLASTIC = new DictGroup("AnyHardPlastic", PC, PVC);
    /**
     * Any post RBMK bronze like BB or AB
     */
    public static final DictGroup ANY_BISMOIDBRONZE = new DictGroup("AnyBismoidBronze", BBRONZE, ABRONZE);
    /**
     * Any post nuclear steel like TCA or CDA
     */
    public static final DictGroup ANY_RESISTANTALLOY = new DictGroup("AnyResistantAlloy", TCALLOY, CDALLOY);
    /**
     * Any "powder" propellant like gunpowder, ballistite and cordite
     */
    public static final DictFrame ANY_GUNPOWDER = new DictFrame("AnyPropellant");
    /**
     * Any smokeless powder like ballistite and cordite
     */
    public static final DictFrame ANY_SMOKELESS = new DictFrame("AnySmokeless");
    /**
     * Any plastic explosive like semtex H or C-4
     */
    public static final DictFrame ANY_PLASTICEXPLOSIVE = new DictFrame("AnyPlasticexplosive");
    /**
     * Any higher tier high explosive (therefore excluding dynamite) like TNT
     */
    public static final DictFrame ANY_HIGHEXPLOSIVE = new DictFrame("AnyHighexplosive");
    public static final DictFrame ANY_COKE = new DictFrame("AnyCoke", "Coke");
    public static final DictFrame ANY_CONCRETE = new DictFrame("Concrete");            //no any prefix means that any has to be appended with the any() or anys() getters, registering works with the any (i.e. no shape) setter
    public static final DictGroup ANY_TAR = new DictGroup("Tar", KEY_OIL_TAR, KEY_COAL_TAR, KEY_CRACK_TAR, KEY_WOOD_TAR);
    /**
     * Any special psot-RBMK gating material, namely bismuth and arsenic
     */
    public static final DictFrame ANY_BISMOID = new DictFrame("AnyBismoid");
    public static final DictFrame ANY_ASH = new DictFrame("Ash");
    /**
     * Alternate, additional names for ore dict registration. Used mostly for DictGroups
     */
    private static final HashMap<String, HashSet<String>> reRegistration = new HashMap<>();
    private static boolean recursionBrake = false;

    /* ===== P4.2: 自研矿辞注册表（1.12 OreDictionary 的 1.21 替代——1.21 无运行时 tag 注册） ===== */
    public static final HashMap<String, List<ItemStack>> oreDict = new HashMap<>();

    public static void registerOre(String key, Object... stacks) {
        List<ItemStack> list = oreDict.computeIfAbsent(key, k -> new ArrayList<>());
        for (Object o : stacks) {
            if (o == null) continue;
            if (o instanceof ItemStack s) {
                if (!list.contains(s)) list.add(s.copy());
            } else if (o instanceof net.minecraft.world.level.ItemLike il) {
                ItemStack s = new ItemStack(il);
                if (!list.contains(s)) list.add(s);
            }
        }
    }

    public static List<ItemStack> getOres(String key) {
        return oreDict.getOrDefault(key, java.util.Collections.emptyList());
    }

    /** 1.12 stackOf(item, meta) 的 1.21 等价（meta → 损坏值） */
    public static ItemStack stackOf(net.minecraft.world.level.ItemLike item, int meta) {
        ItemStack s = new ItemStack(item, 1);
        s.setDamageValue(meta);
        return s;
    }

    // order: nugget billet ingot dust dustTiny block crystal plate gem ore oreNether
    // mlbv: Do not target full upstream parity! We use oredict for crystals and gems.
    public static void registerOres() {
//VANILLA - Fixed
        COAL.gem(Items.COAL).dustSmall(POWDER_COAL_TINY).dust(POWDER_COAL);
        IRON.crystal(CRYSTAL_IRON).plate(PLATE_IRON).dust(POWDER_IRON).ore(ORE_GNEISS_IRON.get());
        GOLD.crystal(CRYSTAL_GOLD).plate(PLATE_GOLD).dust(POWDER_GOLD).ore(ORE_GNEISS_GOLD.get());
        LAPIS.crystal(CRYSTAL_LAPIS).dust(POWDER_LAPIS);
        NETHERQUARTZ.gem(Items.QUARTZ).dust(POWDER_QUARTZ).ore(Blocks.NETHER_QUARTZ_ORE);
        QUARTZ.dust(POWDER_QUARTZ);
        DIAMOND.crystal(CRYSTAL_DIAMOND).dust(POWDER_DIAMOND).ore(GRAVEL_DIAMOND.get(), ORE_SELLAFIELD_DIAMOND.get());
        EMERALD.dust(POWDER_EMERALD).ore(ORE_SELLAFIELD_EMERALD.get());
        REDSTONE.crystal(CRYSTAL_REDSTONE);

        /*
         * RADIOACTIVE
         */
        U.rad(HazardRegistry.u).nugget(NUGGET_URANIUM).billet(BILLET_URANIUM).crystal(CRYSTAL_URANIUM).ingot(INGOT_URANIUM).dust(POWDER_URANIUM).block(BLOCK_URANIUM.get()).ore(ORE_URANIUM.get(), ORE_URANIUM_SCORCHED.get(), ORE_GNEISS_URANIUM.get(), ORE_GNEISS_URANIUM_SCORCHED.get(), ORE_NETHER_URANIUM.get(), ORE_NETHER_URANIUM_SCORCHED.get(), ORE_SELLAFIELD_URANIUM_SCORCHED.get()).oreNether(ORE_NETHER_URANIUM.get(), ORE_NETHER_URANIUM_SCORCHED.get());
        U233.rad(HazardRegistry.u233).nugget(NUGGET_U233).billet(BILLET_U233).ingot(INGOT_U233).block(BLOCK_U233.get());
        U235.rad(HazardRegistry.u235).nugget(NUGGET_U235).billet(BILLET_U235).ingot(INGOT_U235).block(BLOCK_U235.get());
        U238.rad(HazardRegistry.u238).nugget(NUGGET_U238).billet(BILLET_U238).ingot(INGOT_U238).block(BLOCK_U238.get());
        TH232.rad(HazardRegistry.th232).nugget(NUGGET_TH232).billet(BILLET_TH232).crystal(CRYSTAL_THORIUM).ingot(INGOT_TH232).dust(POWDER_THORIUM).block(BLOCK_THORIUM.get()).ore(ORE_THORIUM.get());
        PU.rad(HazardRegistry.pu).nugget(NUGGET_PLUTONIUM).billet(BILLET_PLUTONIUM).crystal(CRYSTAL_PLUTONIUM).ingot(INGOT_PLUTONIUM).dust(POWDER_PLUTONIUM).block(BLOCK_PLUTONIUM.get()).ore(ORE_NETHER_PLUTONIUM.get()).oreNether(ORE_NETHER_PLUTONIUM.get());
        PURG.rad(HazardRegistry.purg).nugget(NUGGET_PU_MIX).billet(BILLET_PU_MIX).ingot(INGOT_PU_MIX).block(BLOCK_PU_MIX.get());
        PU238.rad(HazardRegistry.pu238).hot(3F).nugget(NUGGET_PU238).billet(BILLET_PU238).ingot(INGOT_PU238).block(BLOCK_PU238.get());
        PU239.rad(HazardRegistry.pu239).nugget(NUGGET_PU239).billet(BILLET_PU239).ingot(INGOT_PU239).block(BLOCK_PU239.get());
        PU240.rad(HazardRegistry.pu240).nugget(NUGGET_PU240).billet(BILLET_PU240).ingot(INGOT_PU240).block(BLOCK_PU240.get());
        PU241.rad(HazardRegistry.pu241).nugget(NUGGET_PU241).billet(BILLET_PU241).ingot(INGOT_PU241);                                                                //.block(block_pu241);
        AM241.rad(HazardRegistry.am241).nugget(NUGGET_AM241).billet(BILLET_AM241).ingot(INGOT_AM241);
        AM242.rad(HazardRegistry.am242).nugget(NUGGET_AM242).billet(BILLET_AM242).ingot(INGOT_AM242);
        AMRG.rad(HazardRegistry.amrg).nugget(NUGGET_AM_MIX).billet(BILLET_AM_MIX).ingot(INGOT_AM_MIX);
        NP237.rad(HazardRegistry.np237).nugget(NUGGET_NEPTUNIUM).billet(BILLET_NEPTUNIUM).ingot(INGOT_NEPTUNIUM).dust(POWDER_NEPTUNIUM).block(BLOCK_NEPTUNIUM.get());
        PO210.rad(HazardRegistry.po210).hot(3).nugget(NUGGET_POLONIUM).billet(BILLET_POLONIUM).ingot(INGOT_POLONIUM).dust(POWDER_POLONIUM).block(BLOCK_POLONIUM.get());
        TC99.rad(HazardRegistry.tc99).nugget(NUGGET_TECHNETIUM).billet(BILLET_TECHNETIUM).ingot(INGOT_TECHNETIUM);
        RA226.rad(HazardRegistry.ra226).nugget(NUGGET_RA226).billet(BILLET_RA226).ingot(INGOT_RA226).dust(POWDER_RA226).block(BLOCK_RA226.get());
        AC227.rad(HazardRegistry.ac227).nugget(NUGGET_ACTINIUM).billet(BILLET_ACTINIUM).ingot(INGOT_ACTINIUM).dust(POWDER_ACTINIUM).block(BLOCK_ACTINIUM.get()).dustSmall(POWDER_ACTINIUM_TINY);
        CO60.rad(HazardRegistry.co60).hot(1).nugget(NUGGET_CO60).billet(BILLET_CO60).ingot(INGOT_CO60).dust(POWDER_CO60).dustSmall(POWDER_CO60_TINY);
        AU198.rad(HazardRegistry.au198).hot(5).nugget(NUGGET_AU198).billet(BILLET_AU198).ingot(INGOT_AU198).dust(POWDER_AU198).dustSmall(POWDER_AU198_TINY);
        PB209.rad(HazardRegistry.pb209).blinding(50F).hot(7).nugget(NUGGET_PB209).billet(BILLET_PB209).ingot(INGOT_PB209).dust(POWDER_PB209).dustSmall(POWDER_PB209_TINY);
        SA326.rad(HazardRegistry.sa326).blinding(50F).nugget(NUGGET_SCHRABIDIUM).billet(BILLET_SCHRABIDIUM).crystal(CRYSTAL_SCHRABIDIUM).ingot(INGOT_SCHRABIDIUM).dust(POWDER_SCHRABIDIUM).plate(PLATE_SCHRABIDIUM).plateCast(Mats.MAT_SCHRABIDIUM.make(PLATE_CAST.get())).block(BLOCK_SCHRABIDIUM.get()).ore(ORE_SCHRABIDIUM.get(), ORE_GNEISS_SCHRABIDIUM.get(), ORE_NETHER_SCHRABIDIUM.get(), ORE_SELLAFIELD_SCHRABIDIUM.get()).oreNether(ORE_NETHER_SCHRABIDIUM.get());
        SA327.rad(HazardRegistry.sa327).blinding(50F).nugget(NUGGET_SOLINIUM).billet(BILLET_SOLINIUM).ingot(INGOT_SOLINIUM).block(BLOCK_SOLINIUM.get());
        SBD.rad(HazardRegistry.sb).blinding(50F).ingot(INGOT_SCHRABIDATE).dust(POWDER_SCHRABIDATE).block(BLOCK_SCHRABIDATE.get());
        SRN.rad(HazardRegistry.sr).blinding(50F).ingot(INGOT_SCHRARANIUM).block(BLOCK_SCHRARANIUM.get()).crystal(CRYSTAL_SCHRARANIUM);
        GH336.rad(HazardRegistry.gh336).nugget(NUGGET_GH336).billet(BILLET_GH336).ingot(INGOT_GH336);
        MUD.rad(HazardRegistry.mud).ingot(INGOT_MUD);

        /*
         * STABLE
         */
        TI.ingot(INGOT_TITANIUM).crystal(CRYSTAL_TITANIUM).dust(POWDER_TITANIUM).plate(PLATE_TITANIUM).block(BLOCK_TITANIUM.get()).ore(ORE_TITANIUM.get());
        CU.ingot(INGOT_COPPER).crystal(CRYSTAL_COPPER).dust(POWDER_COPPER).plate(PLATE_COPPER).block(BLOCK_COPPER.get()).ore(ORE_COPPER.get(), ORE_GNEISS_COPPER.get());
        MINGRADE.ingot(INGOT_RED_COPPER).dust(POWDER_RED_COPPER).block(BLOCK_RED_COPPER.get());
        W.ingot(INGOT_TUNGSTEN).dust(POWDER_TUNGSTEN).crystal(CRYSTAL_TUNGSTEN).block(BLOCK_TUNGSTEN.get()).ore(ORE_TUNGSTEN.get(), ORE_NETHER_TUNGSTEN.get()).oreNether(ORE_NETHER_TUNGSTEN.get());
        WC.ingot(INGOT_TUNGSTEN_CARBIDE);
        AL.ingot(INGOT_ALUMINIUM).dust(POWDER_ALUMINIUM).crystal(CRYSTAL_ALUMINIUM).plate(PLATE_ALUMINIUM).block(BLOCK_ALUMINIUM.get()).ore(ORE_ALUMINIUM.get());
        STEEL.ingot(INGOT_STEEL).dustSmall(POWDER_STEEL_TINY).dust(POWDER_STEEL).plate(PLATE_STEEL).block(BLOCK_STEEL.get());
        TCALLOY.ingot(INGOT_TCALLOY).dust(POWDER_TCALLOY).block(BLOCK_TCALLOY.get());
        CDALLOY.ingot(INGOT_CDALLOY).block(BLOCK_CDALLOY.get());
        BBRONZE.ingot(INGOT_BISMUTH_BRONZE);
        ABRONZE.ingot(INGOT_ARSENIC_BRONZE);
        BSCCO.ingot(INGOT_BSCCO);
        PB.nugget(NUGGET_LEAD).ingot(INGOT_LEAD).crystal(CRYSTAL_LEAD).dust(POWDER_LEAD).plate(PLATE_LEAD).block(BLOCK_LEAD.get()).ore(ORE_LEAD.get());
        BI.nugget(NUGGET_BISMUTH).billet(BILLET_BISMUTH).ingot(INGOT_BISMUTH).dust(POWDER_BISMUTH).block(BLOCK_BISMUTH.get());
        AS.nugget(NUGGET_ARSENIC).ingot(INGOT_ARSENIC);
        CA.ingot(INGOT_CALCIUM).dust(POWDER_CALCIUM);
        CD.ingot(INGOT_CADMIUM).dust(POWDER_CADMIUM).block(BLOCK_CADMIUM.get());
        TA.nugget(NUGGET_TANTALIUM).gem(GEM_TANTALIUM).ingot(INGOT_TANTALIUM).dust(POWDER_TANTALIUM).block(BLOCK_TANTALIUM.get());
        COLTAN.ingot(FRAGMENT_COLTAN).dust(POWDER_COLTAN_ORE).block(BLOCK_COLTAN.get()).ore(ORE_COLTAN.get());
        NB.nugget(NUGGET_NIOBIUM, FRAGMENT_NIOBIUM).ingot(INGOT_NIOBIUM).dustSmall(POWDER_NIOBIUM_TINY).dust(POWDER_NIOBIUM).block(BLOCK_NIOBIUM.get());
        BE.nugget(NUGGET_BERYLLIUM).billet(BILLET_BERYLLIUM).crystal(CRYSTAL_BERYLLIUM).ingot(INGOT_BERYLLIUM).dust(POWDER_BERYLLIUM).block(BLOCK_BERYLLIUM.get()).ore(ORE_BERYLLIUM.get());
        CO.nugget(FRAGMENT_COBALT).nugget(NUGGET_COBALT).billet(BILLET_COBALT).crystal(CRYSTAL_COBALT).ingot(INGOT_COBALT).dust(POWDER_COBALT).dustSmall(POWDER_COBALT_TINY).block(BLOCK_COBALT.get()).ore(ORE_COBALT.get(), ORE_NETHER_COBALT.get());
        B.nugget(FRAGMENT_BORON).ingot(INGOT_BORON).dustSmall(POWDER_BORON_TINY).dust(POWDER_BORON).block(BLOCK_BORON.get());
        SI.nugget(NUGGET_SILICON).billet(BILLET_SILICON).ingot(INGOT_SILICON);
        GRAPHITE.ingot(INGOT_GRAPHITE).block(BLOCK_GRAPHITE.get());
        CARBON.ingot(INGOT_GRAPHITE).block(BLOCK_GRAPHITE.get());
        DURA.ingot(INGOT_DURA_STEEL).dust(POWDER_DURA_STEEL).plate(PLATE_DURA_STEEL).block(BLOCK_DURA_STEEL.get());
        POLYMER.ingot(INGOT_POLYMER).dust(POWDER_POLYMER).block(BLOCK_POLYMER.get());
        BAKELITE.ingot(INGOT_BAKELITE).dust(POWDER_BAKELITE).block(BLOCK_BAKELITE.get());
        LATEX.gem(BALL_RESIN).ingot(INGOT_BIORUBBER);
        RUBBER.ingot(INGOT_RUBBER).block(BLOCK_RUBBER.get());
        //PET																	.ingot(ingot_pet); Oh yeah this one was commented by HBM himself for some reason
        PC.ingot(INGOT_PC);
        PVC.ingot(INGOT_PVC);
        MAGTUNG.ingot(INGOT_MAGNETIZED_TUNGSTEN).dust(POWDER_MAGNETIZED_TUNGSTEN).block(BLOCK_MAGNETIZED_TUNGSTEN.get());
        CMB.ingot(INGOT_COMBINE_STEEL).dust(POWDER_COMBINE_STEEL).plate(PLATE_COMBINE_STEEL).block(BLOCK_COMBINE_STEEL.get());
        DESH.nugget(NUGGET_DESH).ingot(INGOT_DESH).dust(POWDER_DESH).block(BLOCK_DESH.get());
        STAR.ingot(INGOT_STARMETAL).block(BLOCK_STARMETAL.get()).crystal(CRYSTAL_STARMETAL);
        GUNMETAL.ingot(INGOT_GUNMETAL).plate(PLATE_GUNMETAL);
        WEAPONSTEEL.ingot(INGOT_WEAPONSTEEL).plate(PLATE_WEAPONSTEEL);
        BIGMT.ingot(INGOT_SATURNITE).plate(PLATE_SATURNITE);
        FERRO.ingot(INGOT_FERROURANIUM);
        EUPH.nugget(NUGGET_EUPHEMIUM).ingot(INGOT_EUPHEMIUM).dust(POWDER_EUPHEMIUM).block(BLOCK_EUPHEMIUM.get());
        DNT.nugget(NUGGET_DINEUTRONIUM).ingot(INGOT_DINEUTRONIUM).dust(POWDER_DINEUTRONIUM).block(BLOCK_DINEUTRONIUM.get());
        FIBER.ingot(INGOT_FIBERGLASS).block(BLOCK_FIBERGLASS.get());
        ASBESTOS.asbestos(1F).ingot(INGOT_ASBESTOS).crystal(CRYSTAL_ASBESTOS).dust(POWDER_ASBESTOS).block(BLOCK_ASBESTOS.get()).ore(ORE_ASBESTOS.get(), ORE_GNEISS_ASBESTOS.get(), DictFrame.fromOne(BASALT_ORE.get(), EnumBasaltOreType.ASBESTOS), DictFrame.fromOne(STONE_RESOURCE.get(), EnumStoneType.ASBESTOS));
        OSMIRIDIUM.nugget(NUGGET_OSMIRIDIUM).ingot(INGOT_OSMIRIDIUM).crystal(CRYSTAL_OSMIRIDIUM);

        /*
         * DUST AND GEM ORES
         */
        S.dust(SULFUR).block(BLOCK_SULFUR.get()).crystal(CRYSTAL_SULFUR).ore(ORE_SULFUR.get(), ORE_NETHER_SULFUR.get() ,DictFrame.fromOne(BASALT_ORE.get(), EnumBasaltOreType.SULFUR), DictFrame.fromOne(STONE_RESOURCE.get(), EnumStoneType.SULFUR)).oreNether(ORE_NETHER_SULFUR.get());
        KNO.dust(NITER).block(BLOCK_NITER.get()).crystal(CRYSTAL_NITER).ore(ORE_NITER.get());
        F.dust(FLUORITE).block(BLOCK_FLUORITE.get()).crystal(CRYSTAL_FLUORITE).ore(ORE_FLUORITE.get(), DictFrame.fromOne(BASALT_ORE.get(), EnumBasaltOreType.FLUORITE));
        LIGNITE.gem(LIGNITE).dust(POWDER_LIGNITE).ore(ORE_LIGNITE.get());
        COALCOKE.gem(fromOne(COKE.get(), EnumCokeType.COAL)).block(fromOne(BLOCK_COKE.get(), EnumCokeType.COAL));
        PETCOKE.gem(fromOne(COKE.get(), EnumCokeType.PETROLEUM)).block(fromOne(BLOCK_COKE.get(), EnumCokeType.PETROLEUM));
        LIGCOKE.gem(fromOne(COKE.get(), EnumCokeType.LIGNITE)).block(fromOne(BLOCK_COKE.get(), EnumCokeType.LIGNITE));
        CINNABAR.crystal(CINNABAR).gem(CINNABAR).ore(ORE_CINNABAR.get(), ORE_DEPTH_CINNABAR.get());
        BORAX.dust(POWDER_BORAX).ore(ORE_DEPTH_BORAX.get());
        CHLOROCALCITE.dust(POWDER_CHLOROCALCITE);
        MOLYSITE.dust(POWDER_MOLYSITE).ore(DictFrame.fromOne(BASALT_ORE.get(), EnumBasaltOreType.MOLYSITE));
        SODALITE.gem(GEM_SODALITE);
        ALEXANDRITE.gem(GEM_ALEXANDRITE);

        VOLCANIC.gem(GEM_VOLCANIC).ore(DictFrame.fromOne(BASALT_ORE.get(), EnumBasaltOreType.GEM));
        HEMATITE.ore(fromOne(STONE_RESOURCE.get(), EnumStoneType.HEMATITE));
        MALACHITE.ingot(DictFrame.fromOne(CHUNK_ORE.get(), EnumChunkType.MALACHITE)).ore(fromOne(STONE_RESOURCE.get(), EnumStoneType.MALACHITE));
        LIMESTONE.dust(POWDER_LIMESTONE).ore(fromOne(STONE_RESOURCE.get(), EnumStoneType.LIMESTONE));
        BAUXITE.gem(fromOne(STONE_RESOURCE.get(), EnumStoneType.BAUXITE));
        CRYOLITE.crystal(fromOne(CHUNK_ORE.get(), EnumChunkType.CRYOLITE));
        SLAG.block(BLOCK_SLAG.get());

        /*
         * HAZARDS, MISC
         */
        LI.hydro(1F).ingot(LITHIUM).dustSmall(POWDER_LITHIUM_TINY).dust(POWDER_LITHIUM).crystal(CRYSTAL_LITHIUM).block(BLOCK_LITHIUM.get()).ore(ORE_GNEISS_LITHIUM.get());
        NA.hydro(1F).hazIngot().dust(POWDER_SODIUM);

        /*
         * PHOSPHORUS
         */
        P_WHITE.hot(5).ingot(INGOT_PHOSPHORUS).block(BLOCK_WHITE_PHOSPHORUS.get());
        P_RED.dust(POWDER_FIRE).block(BLOCK_RED_PHOSPHORUS.get()).crystal(CRYSTAL_PHOSPHORUS);

        /*
         * RARE METALS
         */
        AUSTRALIUM.nugget(NUGGET_AUSTRALIUM).billet(BILLET_AUSTRALIUM).ingot(INGOT_AUSTRALIUM).dust(POWDER_AUSTRALIUM).block(BLOCK_AUSTRALIUM.get()).ore(ORE_AUSTRALIUM.get());

        /*
         * RARE EARTHS
         */

        RAREEARTH.ingot(DictFrame.fromOne(ModItems.CHUNK_ORE.get(), EnumChunkType.RARE)).ore(ORE_RARE.get(), ORE_GNEISS_RARE.get());

        LA.nugget(FRAGMENT_LANTHANIUM).ingot(INGOT_LANTHANIUM).dustSmall(POWDER_LANTHANIUM_TINY).dust(POWDER_LANTHANIUM).block(BLOCK_LANTHANIUM.get());
        ZR.nugget(NUGGET_ZIRCONIUM).ingot(INGOT_ZIRCONIUM).billet(BILLET_ZIRCONIUM).dust(POWDER_ZIRCONIUM).block(BLOCK_ZIRCONIUM.get()).ore(ORE_DEPTH_ZIRCONIUM.get());
        ND.nugget(FRAGMENT_NEODYMIUM).dustSmall(POWDER_NEODYMIUM_TINY).dust(POWDER_NEODYMIUM).ore(ORE_DEPTH_NETHER_NEODYMIUM.get()).oreNether(ORE_DEPTH_NETHER_NEODYMIUM.get());
        CE.nugget(FRAGMENT_CERIUM).dustSmall(POWDER_CERIUM_TINY).dust(POWDER_CERIUM);

        /*
         * NITAN
         */
        I.ingot(INGOT_IODINE).dust(POWDER_IODINE).dustSmall(POWDER_IODINE_TINY);
        AT.ingot(INGOT_ASTATINE).dust(POWDER_ASTATINE);
        CS.ingot(INGOT_CAESIUM).dust(POWDER_CAESIUM);
//        SR.ingot(ingot_strontium).dust(POWDER_STRONTIUM);
        BR.ingot(INGOT_BROMINE).dust(POWDER_BROMINE);
        TS.ingot(INGOT_TENNESSINE).dust(POWDER_TENNESSINE);

        /*
         * FISSION FRAGMENTS
         */
        SR.hot(1F).hydro(1F).hazIngot().dust(POWDER_STRONTIUM);
        SR90.rad(HazardRegistry.sr90).hot(1F).hydro(1F).dustSmall(POWDER_SR90_TINY).dust(POWDER_SR90).ingot(INGOT_SR90).billet(BILLET_SR90).nugget(NUGGET_SR90);
        I131.rad(HazardRegistry.i131).hot(1F).ingot(INGOT_I131).dustSmall(POWDER_I131_TINY).dust(POWDER_I131);
        XE135.rad(HazardRegistry.xe135).hot(10F).dustSmall(POWDER_XE135_TINY).dust(POWDER_XE135);
        CS137.rad(HazardRegistry.cs137).hot(3F).hydro(3F).dustSmall(POWDER_CS137_TINY).dust(POWDER_CS137);
        AT209.rad(HazardRegistry.at209).hot(20F).dust(POWDER_AT209).dustSmall(POWDER_AT209_TINY);

        /*
         * COLLECTIONS
         */
        ANY_GUNPOWDER.dust(Items.GUNPOWDER, BALLISTITE, CORDITE);
        ANY_SMOKELESS.dust(BALLISTITE, CORDITE);
        ANY_PLASTICEXPLOSIVE.ingot(INGOT_SEMTEX, INGOT_C4);
        ANY_HIGHEXPLOSIVE.ingot(BALL_TNT).ingot(BALL_TATB);
        ANY_CONCRETE.any(CONCRETE.get(), CONCRETE_SMOOTH.get(), CONCRETE_ASBESTOS.get(), DUCRETE.get(), DUCRETE_SMOOTH.get());
            for(int i = 0; i < 16; i++) { ANY_CONCRETE.any(stackOf(ModBlocks.CONCRETE_COLORED.get(), i)); }
            for(int i = 0; i < 8; i++) { ANY_CONCRETE.any(stackOf(ModBlocks.CONCRETE_COLORED_EXT.get(), i)); }
        ANY_COKE.gem(fromAll(COKE.get(), EnumCokeType.VALUES)).block(fromAll(BLOCK_COKE.get(), EnumCokeType.VALUES));
        ANY_BISMOID.ingot(INGOT_BISMUTH, INGOT_ARSENIC).nugget(NUGGET_BISMUTH, NUGGET_ARSENIC).block(BLOCK_BISMUTH.get());
        ANY_ASH.any(fromOne(ModItems.POWDER_ASH.get(), EnumAshType.WOOD), fromOne(ModItems.POWDER_ASH.get(), EnumAshType.COAL), fromOne(ModItems.POWDER_ASH.get(), EnumAshType.MISC), fromOne(ModItems.POWDER_ASH.get(), EnumAshType.FLY), fromOne(ModItems.POWDER_ASH.get(), EnumAshType.SOOT));


        /*
         * TAR
         */
            registerOre(KEY_OIL_TAR, fromOne(OIL_TAR.get(), EnumTarType.CRUDE));
            registerOre(KEY_CRACK_TAR, fromOne(OIL_TAR.get(), EnumTarType.CRACK));
            registerOre(KEY_COAL_TAR, fromOne(OIL_TAR.get(), EnumTarType.COAL));
            registerOre(KEY_WOOD_TAR, fromOne(OIL_TAR.get(), EnumTarType.WOOD));

        /*
         * TANKS
         */
            registerOre(KEY_UNIVERSAL_TANK, stackOf(FLUID_TANK_FULL, 0));
            registerOre(KEY_HAZARD_TANK, stackOf(FLUID_TANK_LEAD_FULL, 0));
            registerOre(KEY_UNIVERSAL_BARREL, stackOf(FLUID_BARREL_FULL, 0));
        if (GeneralConfig.enableFluidContainersV2) {
            registerOre(KEY_UNIVERSAL_TANK, stackOf(FLUID_TANK_V2, 0));
            registerOre(KEY_HAZARD_TANK, stackOf(FLUID_TANK_LEAD_V2, 0));
            registerOre(KEY_UNIVERSAL_BARREL, stackOf(FLUID_BARREL_V2, 0));
        }

        /*
         * TOOLS
         */
            registerOre(KEY_TOOL_SCREWDRIVER, stackOf(SCREWDRIVER, 0));
            registerOre(KEY_TOOL_SCREWDRIVER, stackOf(SCREWDRIVER_DESH, 0));
            registerOre(KEY_TOOL_HANDDRILL, stackOf(HAND_DRILL, 0));
            registerOre(KEY_TOOL_HANDDRILL, stackOf(HAND_DRILL_DESH, 0));
            registerOre(KEY_TOOL_CHEMISTRYSET, stackOf(CHEMISTRY_SET, 0));
            registerOre(KEY_TOOL_CHEMISTRYSET, stackOf(CHEMISTRY_SET_BORON, 0));
            registerOre(KEY_TOOL_TORCH, stackOf(BLOWTORCH, 0));
            registerOre(KEY_TOOL_TORCH, stackOf(ACETYLENE_TORCH, 0));

        /*
         * GLYPHID M E A T
         */
            registerOre(KEY_GLYPHID_MEAT, new ItemStack(GLYPHID_MEAT.get()));
            registerOre(KEY_GLYPHID_MEAT, new ItemStack(GLYPHID_MEAT_GRILLED.get()));

        for (NTMMaterial mat : Mats.orderedList) {
            if (mat.smeltable == NTMMaterial.SmeltingBehavior.SMELTABLE) {
            if (mat.autogen.contains(MaterialShapes.CASTPLATE)) for (String name : mat.names)
            registerOre(MaterialShapes.CASTPLATE.name() + name, stackOf(ModItems.PLATE_CAST.get(), mat.id));
            if (mat.autogen.contains(MaterialShapes.WELDEDPLATE)) for (String name : mat.names)
            registerOre(MaterialShapes.WELDEDPLATE.name() + name, stackOf(ModItems.PLATE_WELDED.get(), mat.id));
            if (mat.autogen.contains(MaterialShapes.HEAVY_COMPONENT)) for (String name : mat.names)
            registerOre(MaterialShapes.HEAVY_COMPONENT.name() + name, stackOf(ModItems.HEAVY_COMPONENT.get(), mat.id));
            if (mat.autogen.contains(MaterialShapes.DENSEWIRE)) for (String name : mat.names)
            registerOre(MaterialShapes.DENSEWIRE.name() + name, stackOf(ModItems.WIRE_DENSE.get(), mat.id));
            }
            if (mat.autogen.contains(MaterialShapes.BOLT)) for (String name : mat.names)
            registerOre(MaterialShapes.BOLT.name() + name, stackOf(ModItems.BOLT.get(), mat.id));
            if (mat.autogen.contains(MaterialShapes.INGOT)) for (String name : mat.names)
            registerOre(MaterialShapes.INGOT.name() + name, stackOf(ModItems.INGOT_RAW.get(), mat.id));
            if (mat.autogen.contains(MaterialShapes.SHELL)) for (String name : mat.names)
            registerOre(MaterialShapes.SHELL.name() + name, stackOf(ModItems.SHELL.get(), mat.id));
            if (mat.autogen.contains(MaterialShapes.PIPE)) for (String name : mat.names)
            registerOre(MaterialShapes.PIPE.name() + name, stackOf(ModItems.PIPE.get(), mat.id));
            if (mat.autogen.contains(MaterialShapes.FRAGMENT)) for (String name : mat.names)
            registerOre(MaterialShapes.FRAGMENT.name() + name, stackOf(ModItems.BEDROCK_ORE_FRAGMENT.get(), mat.id));
            if (mat.autogen.contains(MaterialShapes.WIRE)) for (String name : mat.names)
            registerOre(MaterialShapes.WIRE.name() + name, stackOf(ModItems.WIRE_FINE.get(), mat.id));
            if (mat.autogen.contains(MaterialShapes.LIGHTBARREL)) for (String name : mat.names)
            registerOre(MaterialShapes.LIGHTBARREL.name() + name, stackOf(ModItems.PART_BARREL_LIGHT.get(), mat.id));
            if (mat.autogen.contains(MaterialShapes.HEAVYBARREL)) for (String name : mat.names)
            registerOre(MaterialShapes.HEAVYBARREL.name() + name, stackOf(ModItems.PART_BARREL_HEAVY.get(), mat.id));
            if (mat.autogen.contains(MaterialShapes.LIGHTRECEIVER)) for (String name : mat.names)
            registerOre(MaterialShapes.LIGHTRECEIVER.name() + name, stackOf(ModItems.PART_RECEIVER_LIGHT.get(), mat.id));
            if (mat.autogen.contains(MaterialShapes.HEAVYRECEIVER)) for (String name : mat.names)
            registerOre(MaterialShapes.HEAVYRECEIVER.name() + name, stackOf(ModItems.PART_RECEIVER_HEAVY.get(), mat.id));
            if (mat.autogen.contains(MaterialShapes.MECHANISM)) for (String name : mat.names)
            registerOre(MaterialShapes.MECHANISM.name() + name, stackOf(ModItems.PART_MECHANISM.get(), mat.id));
            if (mat.autogen.contains(MaterialShapes.STOCK)) for (String name : mat.names)
            registerOre(MaterialShapes.STOCK.name() + name, stackOf(ModItems.PART_STOCK.get(), mat.id));
            if (mat.autogen.contains(MaterialShapes.GRIP)) for (String name : mat.names)
            registerOre(MaterialShapes.GRIP.name() + name, stackOf(ModItems.PART_GRIP.get(), mat.id));
        }
            registerOre("itemRubber", INGOT_RUBBER);
            registerOre("coalCoke", fromOne(COKE.get(), EnumCokeType.COAL));
            for (String name : new String[]{"fuelCoke", "COKE"}) {
            registerOre(name, fromOne(COKE.get(), EnumCokeType.COAL));
            registerOre(name, fromOne(COKE.get(), EnumCokeType.LIGNITE));
            registerOre(name, fromOne(COKE.get(), EnumCokeType.PETROLEUM));
        }

            registerOre("briquetteCoal", fromOne(BRIQUETTE.get(), EnumBriquetteType.COAL));
            registerOre("briquetteLignite", fromOne(BRIQUETTE.get(), EnumBriquetteType.LIGNITE));
            registerOre("briquetteWood", fromOne(BRIQUETTE.get(), EnumBriquetteType.WOOD));
            registerOre(getReflector(), NEUTRON_REFLECTOR);
            registerOre("logWood", PINK_LOG.get());
            registerOre("logWoodPink", PINK_LOG.get());
            registerOre("plankWood", PINK_PLANKS.get());
            registerOre("plankWoodPink", PINK_PLANKS.get());
            registerOre("slabWood", PINK_SLAB.get());
            registerOre("slabWoodPink", PINK_SLAB.get());
            registerOre("stairWood", PINK_STAIRS.get());
            registerOre("stairWoodPink", PINK_STAIRS.get());

        //TODO
        String[] dyes = {"Black", "Red", "Green", "Brown", "Blue", "Purple", "Cyan", "LightGray", "Gray", "Pink", "Lime", "Yellow", "LightBlue", "Magenta", "Orange", "White"};
        for (int i = 0; i < 16; i++) {
            String dyeName = "dye" + dyes[i];
            registerOre(dyeName, stackOf(ModItems.CHEMICAL_DYE.get(), i));
            registerOre(dyeName, stackOf(ModItems.CRAYON.get(), i));
        }
            registerOre("dye", stackOf(CHEMICAL_DYE, 0));
            registerOre("dye", stackOf(CRAYON, 0));
            registerOre("dyeRed", CINNABAR);
            registerOre("dye", CINNABAR);
            registerOre("dyeYellow", SULFUR);
            registerOre("dye", SULFUR);
            registerOre("dyeBlack", POWDER_COAL);
            registerOre("dye", POWDER_COAL);
            registerOre("dyeBrown", POWDER_LIGNITE);
            registerOre("dye", POWDER_LIGNITE);
            registerOre("dyeLightGray", POWDER_TITANIUM);
            registerOre("dye", POWDER_TITANIUM);
            registerOre("dyeWhite", FLUORITE);
            registerOre("dye", FLUORITE);
            registerOre("dyeBlue", POWDER_LAPIS);
            registerOre("dye", POWDER_LAPIS);
            registerOre("dyeBlack", fromOne(OIL_TAR.get(), EnumTarType.CRUDE));
            registerOre("dyeBlack", fromOne(OIL_TAR.get(), EnumTarType.CRACK));
            registerOre("dyeGray", fromOne(OIL_TAR.get(), EnumTarType.COAL));
            registerOre("dyeBrown", fromOne(OIL_TAR.get(), EnumTarType.WOOD));
            registerOre("dyeCyan", fromOne(OIL_TAR.get(), EnumTarType.WAX));
            registerOre("dyeWhite", fromOne(OIL_TAR.get(), EnumTarType.PARAFFIN));
            registerOre("dye", stackOf(OIL_TAR, 0));
            registerOre("dyeOrange", POWDER_CADMIUM);
            registerOre("dye", POWDER_CADMIUM);
            registerOre("dyeLightGray", fromOne(POWDER_ASH.get(), EnumAshType.WOOD));
            registerOre("dyeBlack", fromOne(POWDER_ASH.get(), EnumAshType.COAL));
            registerOre("dyeGray", fromOne(POWDER_ASH.get(), EnumAshType.MISC));
            registerOre("dyeBrown", fromOne(POWDER_ASH.get(), EnumAshType.FLY));
            registerOre("dyeBlack", fromOne(POWDER_ASH.get(), EnumAshType.SOOT));
            registerOre("dyeMagenta", fromOne(POWDER_ASH.get(), EnumAshType.FULLERENE));
            registerOre("dye", stackOf(POWDER_ASH, 0));
            registerOre("blockGlass", GLASS_BORON.get());
            registerOre("blockGlass", GLASS_LEAD.get());
            registerOre("blockGlass", GLASS_URANIUM.get());
            registerOre("blockGlass", GLASS_TRINITITE.get());
            registerOre("blockGlass", GLASS_POLONIUM.get());
            registerOre("blockGlass", GLASS_ASH.get());
            registerOre("blockGlassYellow", GLASS_URANIUM.get());
            registerOre("blockGlassLime", GLASS_TRINITITE.get());
            registerOre("blockGlassRed", GLASS_POLONIUM.get());
            registerOre("blockGlassBlack", GLASS_ASH.get());
            registerOre("container1000lubricant", BDCL);
            registerOre("container1000water", Items.WATER_BUCKET);
            registerOre("itemSilicon", BILLET_SILICON);

		            // TODO P4 (CraftingManager): for(NTMMaterial mat : Mats.orderedList) {
            // TODO P4 (CraftingManager): if(mat.autogen.contains(MaterialShapes.FRAGMENT)) {
            // TODO P4 (CraftingManager): String name = mat.names[0];
            // TODO P4 (CraftingManager): if(!OreDictionary.getOres(MaterialShapes.DUST.name() + name).isEmpty()) CraftingManager.add9To1ForODM(mat.make(ModItems.BEDROCK_ORE_FRAGMENT.get()), OreDictionary.getOres(MaterialShapes.DUST.name() + name).get(0));
            // TODO P4 (CraftingManager): else if(!OreDictionary.getOres(MaterialShapes.GEM.name() + name).isEmpty()) CraftingManager.add9To1ForODM(mat.make(ModItems.BEDROCK_ORE_FRAGMENT.get()), OreDictionary.getOres(MaterialShapes.GEM.name() + name).get(0));
            // TODO P4 (CraftingManager): else if(!OreDictionary.getOres(MaterialShapes.CRYSTAL.name() + name).isEmpty()) CraftingManager.add9To1ForODM(mat.make(ModItems.BEDROCK_ORE_FRAGMENT.get()), OreDictionary.getOres(MaterialShapes.CRYSTAL.name() + name).get(0));
            // TODO P4 (CraftingManager): else if(!OreDictionary.getOres(MaterialShapes.INGOT.name() + name).isEmpty()) CraftingManager.add9To1ForODM(mat.make(ModItems.BEDROCK_ORE_FRAGMENT.get()), OreDictionary.getOres(MaterialShapes.INGOT.name() + name).get(0));
            // TODO P4 (CraftingManager): else if(!OreDictionary.getOres(MaterialShapes.BILLET.name() + name).isEmpty()) CraftingManager.addBilletFragmentForODM(OreDictionary.getOres(MaterialShapes.BILLET.name() + name).get(0), mat.make(ModItems.BEDROCK_ORE_FRAGMENT.get()));
            // TODO P4 (CraftingManager): else CraftingManager.add9To1ForODM(mat.make(ModItems.BEDROCK_ORE_FRAGMENT.get()), new ItemStack(ModItems.NOTHING.get()));
            // TODO P4 (CraftingManager): }

        MaterialShapes.registerCompatShapes();
        compensateMojangSpaghettiBullshit();
    }

    public static String getReflector() {
        return GeneralConfig.enableReflectorCompat ? "plateDenseLead" : "plateTungCar"; //let's just mangle the name into "tungCar" so that it can't conflict with anything ever
    }

    public static void registerGroups() {
        ANY_RUBBER.addPrefix(INGOT, true);
        ANY_PLASTIC.addPrefix(INGOT, true).addPrefix(MaterialShapes.DUST, true).addPrefix(BLOCK, true).addPrefix(GRIP, true).addPrefix(STOCK, true);
        ANY_HARDPLASTIC.addPrefix(INGOT, true).addPrefix(STOCK, true).addPrefix(GRIP, true);
        ANY_RESISTANTALLOY.addPrefix(INGOT, true).addPrefix(MaterialShapes.DUST, true).addPrefix(CASTPLATE, true).addPrefix(WELDEDPLATE, true).addPrefix(MaterialShapes.HEAVY_COMPONENT, true).addPrefix(BLOCK, true)
                .addPrefix(LIGHTBARREL, true).addPrefix(HEAVYBARREL, true).addPrefix(LIGHTRECEIVER, true).addPrefix(HEAVYRECEIVER, true);
        ANY_BISMOIDBRONZE.addPrefix(INGOT, true).addPrefix(CASTPLATE, true).addPrefix(LIGHTBARREL, true).addPrefix(HEAVYBARREL, true).addPrefix(LIGHTRECEIVER, true).addPrefix(HEAVYRECEIVER, true);
        ANY_TAR.addPrefix(ANY, false);
    }

    private static void addReRegistration(String original, String additional) {

        HashSet<String> strings = reRegistration.get(original);

        if (strings == null)
            strings = new HashSet<>();

        strings.add(additional);

        reRegistration.put(original, strings);
    }

    /*
     * P4.2: onRegisterOre(OreRegisterEvent) 已删除——1.12 的 OreDictionary 事件系统在 1.21.1 不存在。
     * 外部 mod 的矿辞兼容将改为 tag 注册（TODO P4.2）。
     */

    public static final HashSet<com.hbm.util.ComparableStack> arcSmeltable = new HashSet<>();

    /** Vanilla item ore dict registration events never actually register in the ODM because vanilla items are registered so early that the ODM event handler doesn't exist yet. */
    public static void compensateMojangSpaghettiBullshit() {

        arcSmeltable.add(new com.hbm.util.ComparableStack(Blocks.GOLD_ORE));
        arcSmeltable.add(new com.hbm.util.ComparableStack(Blocks.IRON_ORE));
        arcSmeltable.add(new com.hbm.util.ComparableStack(Blocks.LAPIS_ORE));
        arcSmeltable.add(new com.hbm.util.ComparableStack(Blocks.DIAMOND_ORE));
        arcSmeltable.add(new com.hbm.util.ComparableStack(Blocks.REDSTONE_ORE));
        arcSmeltable.add(new com.hbm.util.ComparableStack(Blocks.EMERALD_ORE));
        arcSmeltable.add(new com.hbm.util.ComparableStack(Blocks.NETHER_QUARTZ_ORE));   // 原 QUARTZ_ORE
        arcSmeltable.add(new com.hbm.util.ComparableStack(Blocks.GOLD_BLOCK));
        arcSmeltable.add(new com.hbm.util.ComparableStack(Blocks.IRON_BLOCK));
        arcSmeltable.add(new com.hbm.util.ComparableStack(Blocks.LAPIS_BLOCK));
        arcSmeltable.add(new com.hbm.util.ComparableStack(Blocks.DIAMOND_BLOCK));
        arcSmeltable.add(new com.hbm.util.ComparableStack(Blocks.REDSTONE_BLOCK));
        arcSmeltable.add(new com.hbm.util.ComparableStack(Blocks.EMERALD_BLOCK));
        arcSmeltable.add(new com.hbm.util.ComparableStack(Blocks.QUARTZ_BLOCK));
        arcSmeltable.add(new com.hbm.util.ComparableStack(Items.IRON_INGOT));
        arcSmeltable.add(new com.hbm.util.ComparableStack(Items.GOLD_INGOT));
        arcSmeltable.add(new com.hbm.util.ComparableStack(Items.BRICK));
        arcSmeltable.add(new com.hbm.util.ComparableStack(Items.NETHER_BRICK));   // 原 NETHERBRICK
    }

    public static class DictFrame {
        public String[] mats;
        float hazMult = 1.0F;
        List<HazardEntry> hazards = new ArrayList<>();

        public DictFrame(String... mats) {
            this.mats = mats;
        }

        /**
         * Returns an ItemStack composed of the supplied item with the meta being the enum's ordinal. Purely syntactic candy
         */
        public static ItemStack fromOne(Item item, Enum<?> en) {
            ItemStack s = new ItemStack(item, 1); s.setDamageValue(en.ordinal()); return s;
        }

        public static ItemStack fromOne(Block block, Enum<?> en) {
            ItemStack s = new ItemStack(block, 1); s.setDamageValue(en.ordinal()); return s;
        }

        public static ItemStack fromOne(Item item, Enum<?> en, int stacksize) {
            ItemStack s = new ItemStack(item, stacksize); s.setDamageValue(en.ordinal()); return s;
        }

        public static ItemStack fromOne(Block block, Enum<?> en, int stacksize) {
            ItemStack s = new ItemStack(block, stacksize); s.setDamageValue(en.ordinal()); return s;
        }

        /**
         * @deprecated Use {@link #fromAll(Item, Enum[])} instead.
         */
        @Deprecated(forRemoval = true, since = "1.5.1.1")
        public static Object[] fromAll(Item item, Class<? extends Enum<?>> en) {
            return fromAll(item, en.getEnumConstants());
        }

        /**
         * Same as fromOne but with an array of ItemStacks. The array type is Object[] so that the ODM methods work with it. Generates ItemStacks for the entire enum class.
         */
        public static Object[] fromAll(Item item, Enum<?>[] vals) {
            Object[] stacks = new Object[vals.length];

            for (int i = 0; i < vals.length; i++) {
                ItemStack s = new ItemStack(item, 1);
                s.setDamageValue(vals[i].ordinal());
                stacks[i] = s;
            }
            return stacks;
        }

        /**
         * @deprecated Use {@link #fromAll(Block, Enum[])} instead.
         */
        @Deprecated(forRemoval = true, since = "1.5.1.1")
        public static Object[] fromAll(Block block, Class<? extends Enum<?>> en) {
            return fromAll(block, en.getEnumConstants());
        }

        public static Object[] fromAll(Block block, Enum<?>[] vals) {
            Object[] stacks = new Object[vals.length];

            for (int i = 0; i < vals.length; i++) {
                ItemStack s = new ItemStack(block, 1);
                s.setDamageValue(vals[i].ordinal());
                stacks[i] = s;
            }
            return stacks;
        }

        /**
         * @deprecated Creates a separate HazardData per call, breaking alias sharing.
         *             Internal callers should use {@link #buildSharedHazardData()} instead.
         */
        @Deprecated(forRemoval = true, since = "2.3.0.1")
        public static void registerHazards(List<HazardEntry> hazards, float hazMult, String dictKey) {

            if (!hazards.isEmpty() && hazMult > 0F) {
                HazardData data = new HazardData().setMutex(0b1);

                for (HazardEntry hazard : hazards) {
                    data.addEntry(hazard.clone(hazMult));
                }

                HazardSystem.register(dictKey, data);
            }
        }

        /*
         * Quick access methods to grab ore names for recipes.
         */
        public String any() {
            return ANY.name() + mats[0];
        }

        public String nugget() {
            return MaterialShapes.NUGGET.name() + mats[0];
        }

        public String tiny() {
            return TINY.name() + mats[0];
        }

        public String bolt() {
            return MaterialShapes.BOLT.name() + mats[0];
        }

        public String ingot() {
            return INGOT.name() + mats[0];
        }

        public String dustTiny() {
            return DUSTTINY.name() + mats[0];
        }

        public String dust() {
            return MaterialShapes.DUST.name() + mats[0];
        }

        public String gem() {
            return GEM.name() + mats[0];
        }

        public String crystal() {
            return CRYSTAL.name() + mats[0];
        }

        public String plate() {
            return PLATE.name() + mats[0];
        }

        public String plateCast() {
            return CASTPLATE.name() + mats[0];
        }

        public String plateWelded() {
            return WELDEDPLATE.name() + mats[0];
        }

        public String heavyComp() {
            return MaterialShapes.HEAVY_COMPONENT.name() + mats[0];
        }

        public String wireFine() {
            return WIRE.name() + mats[0];
        }

        public String wireDense() {
            return DENSEWIRE.name() + mats[0];
        }

        public String shell() {
            return MaterialShapes.SHELL.name() + mats[0];
        }

        public String pipe() {
            return MaterialShapes.PIPE.name() + mats[0];
        }

        public String billet() {
            return BILLET.name() + mats[0];
        }

        public String block() {
            return BLOCK.name() + mats[0];
        }

        public String ore() {
            return ORE.name() + mats[0];
        }

        public String fragment() {
            return FRAGMENT.name() + mats[0];
        }

        public String lightBarrel() {
            return LIGHTBARREL.name() + mats[0];
        }

        public String heavyBarrel() {
            return HEAVYBARREL.name() + mats[0];
        }

        public String lightReceiver() {
            return LIGHTRECEIVER.name() + mats[0];
        }

        public String heavyReceiver() {
            return HEAVYRECEIVER.name() + mats[0];
        }

        public String mechanism() {
            return MECHANISM.name() + mats[0];
        }

        public String stock() {
            return STOCK.name() + mats[0];
        }

        public String grip() {
            return GRIP.name() + mats[0];
        }

        public String[] all(MaterialShapes shape) {
            return appendToAll(shape.prefixes);
        }

        /**
         * Returns cast (triple) plates if 528 mode is enabled or normal plates if not
         */
        public String plate528() {
            return GeneralConfig.enable528 ? plateCast() : plate();
        }

        private String[] appendToAll(String... prefix) {

            String[] names = new String[mats.length * prefix.length];

            for (int i = 0; i < mats.length; i++) {
                for (int j = 0; j < prefix.length; j++) {
                    names[i * prefix.length + j] = prefix[j] + mats[i];
                }
            }
            return names;
        }

        public DictFrame rad(float rad) {
            return this.haz(new HazardEntry(HazardRegistry.RADIATION, rad));
        }

        public DictFrame hot(float time) {
            return this.haz(new HazardEntry(HazardRegistry.HOT, time));
        }

        public DictFrame blinding(float time) {
            return this.haz(new HazardEntry(HazardRegistry.BLINDING, time));
        }

        public DictFrame asbestos(float asb) {
            return this.haz(new HazardEntry(HazardRegistry.ASBESTOS, asb));
        }

        public DictFrame hydro(float h) {
            return this.haz(new HazardEntry(HazardRegistry.HYDROACTIVE, h));
        }

        public DictFrame coal(float h) {
            return this.haz(new HazardEntry(HazardRegistry.COAL, h));
        }

        public DictFrame exposive(float h) {
            return this.haz(new HazardEntry(HazardRegistry.EXPLOSIVE, h));
        }

        public DictFrame digamma(float h) {
            return this.haz(new HazardEntry(HazardRegistry.DIGAMMA, h));
        }

        public DictFrame haz(HazardEntry hazard) {
            hazards.add(hazard);
            return this;
        }

        public DictFrame any(Object... thing) {
            return makeObject(ANY, thing);
        }

        public DictFrame nugget(Object... nugget) {
            hazMult = HazardRegistry.nugget;
            return makeObject(MaterialShapes.NUGGET, nugget).makeObject(TINY, nugget);
        }

        public DictFrame ingot(Object... ingot) {
            hazMult = HazardRegistry.ingot;
            return makeObject(INGOT, ingot);
        }

        public DictFrame dustSmall(Object... dustSmall) {
            hazMult = HazardRegistry.powder_tiny;
            return makeObject(DUSTTINY, dustSmall);
        }

        public DictFrame dust(Object... dust) {
            hazMult = HazardRegistry.powder;
            return makeObject(MaterialShapes.DUST, dust);
        }

        public DictFrame gem(Object... gem) {
            hazMult = HazardRegistry.gem;
            return makeObject(GEM, gem);
        }

        public DictFrame crystal(Object... crystal) {
            hazMult = HazardRegistry.crystal;
            return makeObject(CRYSTAL, crystal);
        }

        public DictFrame plate(Object... plate) {
            hazMult = HazardRegistry.plate;
            return makeObject(PLATE, plate);
        }

        public DictFrame plateCast(Object... plate) {
            hazMult = HazardRegistry.plateCast;
            return makeObject(CASTPLATE, plate);
        }

        public DictFrame billet(Object... billet) {
            hazMult = HazardRegistry.billet;
            return makeObject(BILLET, billet);
        }

        public DictFrame block(Object... block) {
            hazMult = HazardRegistry.block;
            return makeObject(BLOCK, block);
        }

        public DictFrame ore(Object... ore) {
            hazMult = HazardRegistry.ore;
            return makeObject(ORE, ore);
        }

        public DictFrame oreNether(Object... oreNether) {
            hazMult = HazardRegistry.ore;
            return makeObject(ORENETHER, oreNether);
        }

        public DictFrame makeObject(MaterialShapes shape, Object... objects) {

            String tag = shape.name();
            for (Object o : objects) {
                if (o instanceof Item) registerStack(tag, new ItemStack((Item) o));
                if (o instanceof Block) registerStack(tag, new ItemStack((Block) o));
                if (o instanceof ItemStack) registerStack(tag, (ItemStack) o);
            }

            return this;
        }

        public DictFrame makeItem(String tag, Item... items) {
            for (Item i : items) registerStack(tag, new ItemStack(i));
            return this;
        }

        public DictFrame makeStack(String tag, ItemStack... stacks) {
            for (ItemStack s : stacks) registerStack(tag, s);
            return this;
        }

        public DictFrame makeBlocks(String tag, Block... blocks) {
            for (Block b : blocks) registerStack(tag, new ItemStack(b));
            return this;
        }

        public DictFrame hazIngot() {
            hazMult = HazardRegistry.ingot;
            return autoRegHazard(INGOT);
        }

        // TODO: rethink this. currently, keys are only registered on-demand if the dict frame has a valid entry, even though we can maximize compatibility
        // by simply registereing all known shapes in the haz reg, whether it exists or not
        public DictFrame autoRegHazard(MaterialShapes shape) {
            HazardData sharedData = buildSharedHazardData();
            if (sharedData != null) {
                String tag = shape.name();
                for (String mat : mats) {
                    HazardSystem.register(tag + mat, sharedData);
                }
            }
            return this;
        }

        public void registerStack(String tag, ItemStack stack) {
            // All oredict aliases for the same shape share a single HazardData so that
            // modifications through any alias key are visible through all of them.
            HazardData sharedData = buildSharedHazardData();

            for (String mat : mats) {
                // TODO P4.2: registerOre(tag + mat, stack) -> 1.21 tag 注册
                if (sharedData != null) HazardSystem.register(tag + mat, sharedData);
            }

            /*
             * Fix for a small oddity in nuclearcraft: many radioactive elements do not have an ore prefix and the sizes
             * seem generally inconsistent (TH and U are 20 "tiny"s per ingot while boron is 12), so we assume those to be ingots.
             * Therefore we register all ingots a second time but without prefix. TODO: add a config option to disable this compat.
             * I'd imagine greg's OD system might not like things without prefixes.
             */
            if ("ingot".equals(tag)) {
                for (String mat : mats) {
                    // TODO P4.2: registerOre(mat, stack) -> 1.21 tag 注册
                    if (sharedData != null) HazardSystem.register(mat, sharedData);
                }
            }
        }

        /**
         * <p>DO NOT AIM FOR UPSTREAM PARITY FOR THIS!! DO NOT REMOVE, MODIFY, OR REFACTOR</p>
         * This supersedes {@link DictFrame#registerHazards(List, float, String)}
         *
         * @author movblock
         */
        private HazardData buildSharedHazardData() {
            if (hazards.isEmpty() || hazMult <= 0F) return null;
            HazardData data = new HazardData().setMutex(0b1);
            for (HazardEntry hazard : hazards) {
                data.addEntry(hazard.clone(hazMult));
            }
            return data;
        }
    }

    public static class DictGroup {
        private String groupName;
        private HashSet<String> names = new HashSet<>();

        public DictGroup(String groupName) {
            this.groupName = groupName;
        }

        public DictGroup(String groupName, String... names) {
            this(groupName);
            this.addNames(names);
        }

        public DictGroup(String groupName, DictFrame... frames) {
            this(groupName);
            this.addFrames(frames);
        }

        public DictGroup addNames(String... names) {
            Collections.addAll(this.names, names);
            return this;
        }

        public DictGroup addFrames(DictFrame... frames) {
            for (DictFrame frame : frames) this.addNames(frame.mats);
            return this;
        }

        /**
         * Will add a reregistration entry for every mat name of every added DictFrame for the given prefix
         *
         * @param shape The prefix of both the input and result of the reregistration
         * @return
         */
        public DictGroup addPrefix(MaterialShapes shape, boolean inputPrefix) {

            String prefix = shape.name();
            String group = prefix + groupName;

            for (String name : names) {
                String original = (inputPrefix ? prefix : "") + name;
                addReRegistration(original, group);
            }

            return this;
        }

        /**
         * Same thing as addPrefix, but the input for the reregistration is not bound by the prefix or any mat names
         *
         * @param prefix   The prefix for the resulting reregistration entry (in full: prefix + group name)
         * @param original The full original ore dict key, not bound by any naming conventions
         * @return
         */
        public DictGroup addFixed(String prefix, String original) {

            String group = prefix + groupName;
            addReRegistration(original, group);
            return this;
        }

        public String any() {
            return ANY.name() + groupName;
        }

        public String nugget() {
            return MaterialShapes.NUGGET.name() + groupName;
        }

        public String tiny() {
            return TINY.name() + groupName;
        }

        public String bolt() {
            return MaterialShapes.BOLT.name() + groupName;
        }

        public String ingot() {
            return INGOT.name() + groupName;
        }

        public String dustTiny() {
            return DUSTTINY.name() + groupName;
        }

        public String dust() {
            return MaterialShapes.DUST.name() + groupName;
        }

        public String gem() {
            return GEM.name() + groupName;
        }

        public String crystal() {
            return CRYSTAL.name() + groupName;
        }

        public String plate() {
            return PLATE.name() + groupName;
        }

        public String plateCast() {
            return CASTPLATE.name() + groupName;
        }

        public String plateWelded() {
            return WELDEDPLATE.name() + groupName;
        }

        public String heavyComp() {
            return MaterialShapes.HEAVY_COMPONENT.name() + groupName;
        }

        public String wireFine() {
            return WIRE.name() + groupName;
        }

        public String wireDense() {
            return DENSEWIRE.name() + groupName;
        }

        public String billet() {
            return BILLET.name() + groupName;
        }

        public String block() {
            return BLOCK.name() + groupName;
        }

        public String ore() {
            return ORE.name() + groupName;
        }

        public String lightBarrel() {
            return LIGHTBARREL.name() + groupName;
        }

        public String heavyBarrel() {
            return HEAVYBARREL.name() + groupName;
        }

        public String lightReceiver() {
            return LIGHTRECEIVER.name() + groupName;
        }

        public String heavyReceiver() {
            return HEAVYRECEIVER.name() + groupName;
        }

        public String mechanism() {
            return MECHANISM.name() + groupName;
        }

        public String stock() {
            return STOCK.name() + groupName;
        }

        public String grip() {
            return GRIP.name() + groupName;
        }

        public String wire() {
            return WIRE.name() + groupName;
        }

        public String part() {
            return PART.name() + groupName;
        }
    }
}
