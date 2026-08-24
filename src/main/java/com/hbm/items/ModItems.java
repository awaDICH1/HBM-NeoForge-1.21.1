package com.hbm.items;

import com.hbm.Tags;
import com.hbm.items.machine.ItemFluidIcon;
import com.hbm.items.machine.ItemRBMKPellet;
import com.hbm.items.machine.ItemRBMKRod;
import com.hbm.items.machine.ItemScraps;
import com.hbm.items.special.ItemWasteLong;
import com.hbm.items.special.ItemWasteShort;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tiers;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

/**
 * 闂備胶绮妵娑㈠疾濞戙垹绠圭憸宥夊煝閺冨牆惟闁靛鍎查弶鍛婄箾閹寸偞鎯勯柛妯垮亹缂傛挻銈ｉ崘銊庛儵鏌涚仦鍓х畼閻犱緤绲跨槐鎺撳緞鐎ｎ偒娼￠梺?1.12.2 com.hbm.items.ModItems闂?838 濠?Item 闂佽瀛╃粙鎺椼€冮崱娑辨晩鐎光偓閸曨剚娅栨繝銏ｆ硾閻楀啴宕?
 *
 * 缂傚倷鐒﹂〃蹇涘礂濞戞氨鍗氶柟缁㈠枛缁犮儵鏌ｈ閹芥粎绮?85 濠?ItemBase 闂備胶绮妵娑㈠疾濞戙垹绠圭憸鏃堝极瀹ュ閿ら柣顏呮敿ls/gen_items.ps1 闂備焦鐪归崹濠氬窗閹版澘鍨傛慨妯垮煐閺咁剙顭跨捄铏瑰闁?
 * 婵犵數鍋涢ˇ顓㈠礉瀹€鍕埞濞寸姴顑呯憴锔锯偓骞垮劚鐎氼噣鎮烽姀銈嗙厱?CE 闂佽娴烽幊鎾绘嚐椤栫偛鐭楅煫鍥ㄦ⒒閳绘棃鏌嶈閸撶喖骞婂☉娆愮秶闁告挆鍐偓鍧楁⒑閸涘﹤绗氶柣鈺婂灦閸┾偓妞ゆ帒鍊告牎闂佺粯鐗崰娑㈠Φ閹版澘绠抽柟瀵稿У閸撴垿鏌?ModCreativeTabs闂備焦瀵х粙鎴濐焽閹唶playItems闂備焦瀵х粙鎴βㄩ埀顒傜磼?
 * 闂備胶绮〃澶愶綖婢舵劖鍋╅柍鍝勫暟鐏忛潧霉閸忚偐鎳呯紒鈧崙顩歟mCustomLore 334 / ItemBakedBase 79 / ItemMissile 63 缂傚倷鐒︾粙鎴βㄩ埀顒傜磼鏉堛劌鍝虹€规洏鍎查幆鏃堟晲閸ャ劍姣庨梻渚€鈧稓绁锋い顐㈩樀椤㈡洟宕楅崗闂存唉婵炶揪绲垮ú鍛村煛閸涱厙?
 */
public class ModItems {

    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Tags.MODID);

    // ===== P1 闂傚倷绶氶弫顕€宕戦幘缁樷拺?=====
    // 闂? public static final Item ingot_uranium = new ItemCustomLore("ingot_uranium").setCreativeTab(partsTab);
    public static final DeferredItem<Item> INGOT_URANIUM = ITEMS.register("ingot_uranium",
            () -> new Item(new Item.Properties()));

    // ===== P3.2 registerOres 闂備胶鍎甸弲娑㈡偤閵娧勬殰閻庢稒顭囬悷瑙勭節闂堟冻鍔熸い鈺婂亰閹鈽夊▎妯荤暦濡炪倖鏌ㄩ惌鍌炲极?3 濠电偞鍨堕幖鈺傜妞嬪孩顫?026-08-18 闂備焦鐪归崹濠氬窗閹版澘鍨傛慨妯垮煐閺?=====
    // 闂備礁鎼ˇ顓㈠磿閼碱剝濮虫い鏇楀亾闁轰礁绉撮～銏ゅ礈閸楁€恑ctManager.registerOres()闂備焦瀵х粙鎴濓耿閹?缂?412 闂佽崵鍋炵粙鎴︽儗娓氣偓楠炴垿鏁愭径瀣珫婵犮垼娉涢敃锕傚级娴犲鈷掗柛鏇ㄥ亝閸ｅ湱绱掗幍浣规珚闁诡喕绮欐俊姝岊槼闁?22 濠电偞鍨堕幖鈺傜濠婂牊鍋ょ憸宥夊煝?
    //       + 闂備焦妞垮鍧楀礉瀹ュ鏄ユ繛鎴欏灩缁犱即鏌涢妷鎴濇噺濮ｅ孩淇婇锝嗙凡閻庢凹鍣ｅ鎶藉焵椤掑嫭鐓?scraps闂備焦瀵х粙鎴濓耿濞擃晪mScraps 缂傚倷绶￠崹闈涚暦閻㈤潧鍨濇繛宸簼閸嬨劍銇勯鐔风缂佲偓婵? 闂備礁鎼ú锔锯偓绗涘啰鏆﹂柡灞诲劜閺?
    // 婵犵數鍋涢ˇ顓㈠礉瀹€鍕埞濞寸姴顑呯憴锔锯偓骞垮劚鐎氼噣鎮烽姀銈嗙厱?CE 闂佽娴烽幊鎾绘嚐椤栫偛鐭楅煫鍥ㄦ⒒閳绘棃鏌嶈閸撶喖骞婂☉娆愮秶闁告挆鍐偓鍧楁⒑閸涘﹤绗氶柣鈺婂灦閸┾偓妞ゆ帒鍊告牎闂?partsTab闂備焦瀵х粙鎴︽偋婵犲嫧鍋撻崹顐ｇ婵炵⒈浜畷鎰版偆娴ｅ湱绉?ModCreativeTabs.displayItems闂?

    // TAB: partsTab 闂?闂傚倷绶￠崰鎾诲礉鐎ｎ亖鏋?/ 闁荤喐绮忛崺鍥垂缂佹ɑ鍙忛柛婵勫劤鐏忛潧霉閸忚偐鎳呯紒鈧?6 濠电偞鍨堕幖鈺傜妞嬪孩顫曟繝濠勭剻emBase闂?
    public static final DeferredItem<Item> PLATE_CAST = ITEMS.register("plate_cast",
            () -> new ItemBase(new Item.Properties()));
    public static final DeferredItem<Item> PLATE_WELDED = ITEMS.register("plate_welded",
            () -> new ItemBase(new Item.Properties()));
    public static final DeferredItem<Item> HEAVY_COMPONENT = ITEMS.register("heavy_component",
            () -> new ItemBase(new Item.Properties()));
    public static final DeferredItem<Item> WIRE_DENSE = ITEMS.register("wire_dense",
            () -> new ItemBase(new Item.Properties()));
    public static final DeferredItem<Item> BOLT = ITEMS.register("bolt",
            () -> new ItemBase(new Item.Properties()));
    public static final DeferredItem<Item> INGOT_RAW = ITEMS.register("ingot_raw",
            () -> new ItemBase(new Item.Properties()));
    public static final DeferredItem<Item> SHELL = ITEMS.register("shell",
            () -> new ItemBase(new Item.Properties()));
    public static final DeferredItem<Item> PIPE = ITEMS.register("pipe",
            () -> new ItemBase(new Item.Properties()));
    public static final DeferredItem<Item> WIRE_FINE = ITEMS.register("wire_fine",
            () -> new ItemBase(new Item.Properties()));
    public static final DeferredItem<Item> PART_BARREL_LIGHT = ITEMS.register("part_barrel_light",
            () -> new ItemBase(new Item.Properties()));
    public static final DeferredItem<Item> PART_BARREL_HEAVY = ITEMS.register("part_barrel_heavy",
            () -> new ItemBase(new Item.Properties()));
    public static final DeferredItem<Item> PART_RECEIVER_LIGHT = ITEMS.register("part_receiver_light",
            () -> new ItemBase(new Item.Properties()));
    public static final DeferredItem<Item> PART_RECEIVER_HEAVY = ITEMS.register("part_receiver_heavy",
            () -> new ItemBase(new Item.Properties()));
    public static final DeferredItem<Item> PART_MECHANISM = ITEMS.register("part_mechanism",
            () -> new ItemBase(new Item.Properties()));
    public static final DeferredItem<Item> PART_STOCK = ITEMS.register("part_stock",
            () -> new ItemBase(new Item.Properties()));
    public static final DeferredItem<Item> PART_GRIP = ITEMS.register("part_grip",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab 闂?闂備礁鎼ˇ浼村礉閺嶎厼鍑?/ 闂備礁鎼張顒勫箰婵犳艾鍑?/ 闂備礁鎲￠〃鍫熸叏瀹曞洨绀婇柛娑樼摠閺? 濠电偞鍨堕幖鈺傜妞嬪孩顫曟繝濠勭剻emBase闂?
    public static final DeferredItem<Item> CHUNK_ORE = ITEMS.register("chunk_ore",
            () -> new ItemBase(new Item.Properties())); // P8: ItemCustomLore闂備焦瀵х粙鎴濓耿閺勭本mChunkType闂備焦瀵х粙鎴λ囬弶妫电懓鈽夐姀鐘殿唹?
    public static final DeferredItem<Item> POWDER_ASH = ITEMS.register("powder_ash",
            () -> new ItemBase(new Item.Properties()));
    public static final DeferredItem<Item> CHEMICAL_DYE = ITEMS.register("chemical_dye",
            () -> new ItemBase(new Item.Properties()));
    public static final DeferredItem<Item> CRAYON = ITEMS.register("crayon",
            () -> new ItemBase(new Item.Properties()));
    // 婵犵數鍋涢ˇ浠嬫偂婢跺本顫曢柣褎銆峊HING 闁诲海鎳撻幉陇銇愰崘顔藉仱闁靛ň鏅涢幑鍫曟煏婵炲灝鈧鎳濋幆褉妲堥柟鍓ь劜閺€濠氭煛瀹€鈧崰鏍极瀹ュ閱囬柕蹇婃櫅閸?72 闂佽崵鍋炵粙鎴︽儗婢跺本顫曟慨妯垮煐閺咁剟鎮橀悙浣冩闁告柨鍟…璺ㄦ崉閸濆嫷浠圭紓浣诡殔椤︾敻寮荤仦绛嬪悑闁告粈鐒︿簺闂佽姘﹂～澶愭儗椤斿墽涓?

    // TAB: partsTab 闂?闂備礁鎲￠〃鍫熸叏瀹曞洨绀婇柛娑樼摠閺? 濠电偞鍨堕幖鈺傜妞嬪孩顫曟繝? 闂備礁鎼ú锔锯偓绗涘啰鏆﹂柡灞诲劜閺?
    public static final DeferredItem<Item> BEDROCK_ORE_FRAGMENT = ITEMS.register("bedrock_ore_fragment",
            () -> new ItemBase(new Item.Properties())); // P8: ItemCustomLore 闂備礁鎼ú锔锯偓绗涘啰鏆?
    public static final DeferredItem<Item> SCRAPS = ITEMS.register("scraps",
            () -> new ItemScraps("scraps"));

    // TAB: partsTab (P3.2 special; CE new ItemBase(new Item.Properties())); // P8: ItemEnumMulti<EnumBriquetteType>)
    public static final DeferredItem<Item> BRIQUETTE = ITEMS.register("briquette",
            () -> new ItemBase(new Item.Properties())); // P8: ItemEnumMulti<EnumBriquetteType>);
    // TAB: partsTab (P3.2 special; CE new ItemBase(new Item.Properties())); // P8: ItemEnumMulti<EnumCokeType>)
    public static final DeferredItem<Item> COKE = ITEMS.register("coke",
            () -> new ItemBase(new Item.Properties())); // P8: ItemEnumMulti<EnumCokeType>);
    // TAB: partsTab (P3.2 special; CE new ItemBase(new Item.Properties())); // P8: ItemEnumMulti<EnumTarType>)
    public static final DeferredItem<Item> OIL_TAR = ITEMS.register("oil_tar",
            () -> new ItemBase(new Item.Properties())); // P8: ItemEnumMulti<EnumTarType>);
    // TAB: consumableTab (P3.2 special; CE new ItemBase(new Item.Properties())); // P8: ItemLemon)
    public static final DeferredItem<Item> GLYPHID_MEAT = ITEMS.register("glyphid_meat",
            () -> new ItemBase(new Item.Properties())); // P8: ItemLemon);
    // TAB: consumableTab (P3.2 special; CE new ItemBase(new Item.Properties())); // P8: ItemLemon)
    public static final DeferredItem<Item> GLYPHID_MEAT_GRILLED = ITEMS.register("glyphid_meat_grilled",
            () -> new ItemBase(new Item.Properties())); // P8: ItemLemon);
    // TAB: partsTab (P3.2 special; CE new ItemBase(new Item.Properties())); // P8: ItemLemon)
    public static final DeferredItem<Item> INGOT_SEMTEX = ITEMS.register("ingot_semtex",
            () -> new ItemBase(new Item.Properties())); // P8: ItemLemon);
    // TAB: consumableTab (P3.2 special; CE new ItemBase(new Item.Properties())); // P8: ItemLemon)
    public static final DeferredItem<Item> NUGGET = ITEMS.register("nugget",
            () -> new ItemBase(new Item.Properties())); // P8: ItemLemon);
    // TAB: partsTab (P3.2 special; CE new ItemBase(new Item.Properties())); // P8: ItemTooling(HAND_DRILL))
    public static final DeferredItem<Item> HAND_DRILL = ITEMS.register("hand_drill",
            () -> new ItemBase(new Item.Properties())); // P8: ItemTooling(HAND_DRILL));
    // TAB: partsTab (P3.2 special; CE new ItemBase(new Item.Properties())); // P8: ItemTooling(HAND_DRILL))
    public static final DeferredItem<Item> HAND_DRILL_DESH = ITEMS.register("hand_drill_desh",
            () -> new ItemBase(new Item.Properties())); // P8: ItemTooling(HAND_DRILL));
    // TAB: partsTab (P3.2 special; CE new ItemBase(new Item.Properties())); // P8: ItemTooling(SCREWDRIVER))
    public static final DeferredItem<Item> SCREWDRIVER = ITEMS.register("screwdriver",
            () -> new ItemBase(new Item.Properties())); // P8: ItemTooling(SCREWDRIVER));
    // TAB: partsTab (P3.2 special; CE new ItemBase(new Item.Properties())); // P8: ItemTooling(SCREWDRIVER))
    public static final DeferredItem<Item> SCREWDRIVER_DESH = ITEMS.register("screwdriver_desh",
            () -> new ItemBase(new Item.Properties())); // P8: ItemTooling(SCREWDRIVER));

    // ===== P3 缂傚倷鐒﹂〃蹇涘礂濞戞氨鍗氶柟缁㈠枛缁犮儵鏌ｈ閹芥粎绮?85 濠?ItemBase 闂備胶绮妵娑㈠疾濞戙垹绠圭憸鏃堝极瀹ュ閿ら柟鑽ょ殸_items.ps1 闂備焦鐪归崹濠氬窗閹版澘鍨傛慨妯垮煐閺?=====
    // 闂備礁鎲￠…鍥窗鎼淬劍鍋ょ憸宥夊煝閹剧粯顥堟繛鎴炵矊閻撳倹绻? public static final Item xxx = new ItemBase("xxx").setCreativeTab(MainRegistry.yyyTab)
    // 闂佸搫顦弲娆撴嚄閺堢數鏄? 婵犵數鍋涢ˇ顓㈠礉瀹€鍕埞濞寸姴顑呯憴锔锯偓骞垮劚濞诧箓寮?ITEMS.register 闂備礁婀辩划顖炲礉閹烘梹顐介柣銏犳啞閺咁剚绻涙径澶愮崪tCreativeTab 闂備礁鎲＄敮鐐寸箾閳ь剚绻涢崨顓㈠弰闁轰礁绉瑰畷鐑姐€€缁€绀恟eativeTabs闂備焦瀵х粙鎴βㄩ埀顒傜磼鏉堫偂鍚ù鐙呯畵閹瑧鍒掗悷棰佸閻熸粍妫冮獮妤呭礂閸忕厧顫?Item.Properties

    // TAB: consumableTab
    // TODO: setFull3D 闂?1.21.1 no property, handled in model json
    public static final DeferredItem<Item> SYRINGE_EMPTY = ITEMS.register("syringe_empty",
            () -> new ItemBase(new Item.Properties()));

    // TAB: consumableTab
    // TODO: setFull3D 闂?1.21.1 no property, handled in model json
    public static final DeferredItem<Item> SYRINGE_METAL_EMPTY = ITEMS.register("syringe_metal_empty",
            () -> new ItemBase(new Item.Properties()));

    // TAB: controlTab
    public static final DeferredItem<Item> PISTON_SELENIUM = ITEMS.register("piston_selenium",
            () -> new ItemBase(new Item.Properties()));

    // TAB: controlTab
    public static final DeferredItem<Item> THERMO_ELEMENT = ITEMS.register("thermo_element",
            () -> new ItemBase(new Item.Properties()));

    // TAB: controlTab
    public static final DeferredItem<Item> CATALYTIC_CONVERTER = ITEMS.register("catalytic_converter",
            () -> new ItemBase(new Item.Properties().stacksTo(1)));

    // TAB: controlTab
    public static final DeferredItem<Item> PART_LITHIUM = ITEMS.register("part_lithium",
            () -> new ItemBase(new Item.Properties()));

    // TAB: controlTab
    public static final DeferredItem<Item> PART_BERYLLIUM = ITEMS.register("part_beryllium",
            () -> new ItemBase(new Item.Properties()));

    // TAB: controlTab
    public static final DeferredItem<Item> PART_CARBON = ITEMS.register("part_carbon",
            () -> new ItemBase(new Item.Properties()));

    // TAB: controlTab
    public static final DeferredItem<Item> PART_COPPER = ITEMS.register("part_copper",
            () -> new ItemBase(new Item.Properties()));

    // TAB: controlTab
    public static final DeferredItem<Item> PART_PLUTONIUM = ITEMS.register("part_plutonium",
            () -> new ItemBase(new Item.Properties()));

    // TAB: null
    public static final DeferredItem<Item> FUSION_CORE_INFINITE = ITEMS.register("fusion_core_infinite",
            () -> new ItemBase(new Item.Properties().stacksTo(1)));

    // TAB: controlTab
    public static final DeferredItem<Item> GAS_EMPTY = ITEMS.register("gas_empty",
            () -> new ItemBase(new Item.Properties()));

    // TAB: weaponTab
    public static final DeferredItem<Item> DISPERSER_CANISTER_EMPTY = ITEMS.register("disperser_canister_empty",
            () -> new ItemBase(new Item.Properties()));

    // TAB: weaponTab
    public static final DeferredItem<Item> GLYPHID_GLAND_EMPTY = ITEMS.register("glyphid_gland_empty",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> POWDER_IRON = ITEMS.register("powder_iron",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> POWDER_GOLD = ITEMS.register("powder_gold",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> POWDER_DIAMOND = ITEMS.register("powder_diamond",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> POWDER_EMERALD = ITEMS.register("powder_emerald",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> POWDER_LAPIS = ITEMS.register("powder_lapis",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> POWDER_TITANIUM = ITEMS.register("powder_titanium",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> POWDER_TUNGSTEN = ITEMS.register("powder_tungsten",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> POWDER_SODIUM = ITEMS.register("powder_sodium",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> POWDER_CHLOROCALCITE = ITEMS.register("powder_chlorocalcite",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> POWDER_MOLYSITE = ITEMS.register("powder_molysite",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> POWDER_COPPER = ITEMS.register("powder_copper",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> POWDER_BERYLLIUM = ITEMS.register("powder_beryllium",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> POWDER_ALUMINIUM = ITEMS.register("powder_aluminium",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> POWDER_COMBINE_STEEL = ITEMS.register("powder_combine_steel",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> POWDER_CDALLOY = ITEMS.register("powder_cdalloy",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> POWDER_CHLOROPHYTE = ITEMS.register("powder_chlorophyte",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> POWDER_RED_COPPER = ITEMS.register("powder_red_copper",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> POWDER_STEEL = ITEMS.register("powder_steel",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> POWDER_STEEL_TINY = ITEMS.register("powder_steel_tiny",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> REDSTONE_DEPLETED = ITEMS.register("redstone_depleted",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> POWDER_QUARTZ = ITEMS.register("powder_quartz",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> POWDER_BORAX = ITEMS.register("powder_borax",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> POWDER_LANTHANIUM_TINY = ITEMS.register("powder_lanthanium_tiny",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> POWDER_ACTINIUM_TINY = ITEMS.register("powder_actinium_tiny",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> POWDER_BORON_TINY = ITEMS.register("powder_boron_tiny",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> POWDER_SEMTEX_MIX = ITEMS.register("powder_semtex_mix",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> POWDER_DESH = ITEMS.register("powder_desh",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> POWDER_ZIRCONIUM = ITEMS.register("powder_zirconium",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> POWDER_ASBESTOS = ITEMS.register("powder_asbestos",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> POWDER_CADMIUM = ITEMS.register("powder_cadmium",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> POWDER_BISMUTH = ITEMS.register("powder_bismuth",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> POWDER_CAESIUM = ITEMS.register("powder_caesium",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> POWDER_STRONTIUM = ITEMS.register("powder_strontium",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> POWDER_CALCIUM = ITEMS.register("powder_calcium",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> POWDER_ICE = ITEMS.register("powder_ice",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> POWDER_LIMESTONE = ITEMS.register("powder_limestone",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> POWDER_DESH_MIX = ITEMS.register("powder_desh_mix",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> POWDER_DESH_READY = ITEMS.register("powder_desh_ready",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> POWDER_METEORITE = ITEMS.register("powder_meteorite",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> POWDER_METEORITE_TINY = ITEMS.register("powder_meteorite_tiny",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> POWDER_COLTAN = ITEMS.register("powder_coltan",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> POWDER_POISON = ITEMS.register("powder_poison",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> SULFUR = ITEMS.register("sulfur",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> NITER = ITEMS.register("niter",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> FLUORITE = ITEMS.register("fluorite",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> SCRAP = ITEMS.register("scrap",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> SCRAP_OIL = ITEMS.register("scrap_oil",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> DUST = ITEMS.register("dust",
            () -> new ItemBase(new Item.Properties()));

    // TAB: controlTab
    public static final DeferredItem<Item> ROD_EMPTY = ITEMS.register("rod_empty",
            () -> new ItemBase(new Item.Properties()));

    // TAB: controlTab
    public static final DeferredItem<Item> ROD_DUAL_EMPTY = ITEMS.register("rod_dual_empty",
            () -> new ItemBase(new Item.Properties()));

    // TAB: controlTab
    public static final DeferredItem<Item> ROD_QUAD_EMPTY = ITEMS.register("rod_quad_empty",
            () -> new ItemBase(new Item.Properties()));

    // TAB: controlTab
    public static final DeferredItem<Item> ROD_ZIRNOX_EMPTY = ITEMS.register("rod_zirnox_empty",
            () -> new ItemBase(new Item.Properties().stacksTo(64)));

    // TAB: controlTab
    public static final DeferredItem<Item> ROD_ZIRNOX_TRITIUM = ITEMS.register("rod_zirnox_tritium",
            () -> new ItemBase(new Item.Properties().stacksTo(1).craftRemainder(ModItems.ROD_ZIRNOX_EMPTY.get())));

    // TAB: partsTab
    public static final DeferredItem<Item> RING_STARMETAL = ITEMS.register("ring_starmetal",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> FLYWHEEL_BERYLLIUM = ITEMS.register("flywheel_beryllium",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> CORDITE = ITEMS.register("cordite",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> BALLISTITE = ITEMS.register("ballistite",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> BALL_DYNAMITE = ITEMS.register("ball_dynamite",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> BALL_TNT = ITEMS.register("ball_tnt",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> BALL_TATB = ITEMS.register("ball_tatb",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> BALL_FIRECLAY = ITEMS.register("ball_fireclay",
            () -> new ItemBase(new Item.Properties()));

    // TAB: controlTab
    public static final DeferredItem<Item> MOLD_BASE = ITEMS.register("mold_base",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> NEUTRON_REFLECTOR = ITEMS.register("neutron_reflector",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> RTG_UNIT = ITEMS.register("rtg_unit",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> THERMO_UNIT_EMPTY = ITEMS.register("thermo_unit_empty",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> LEVITATION_UNIT = ITEMS.register("levitation_unit",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> PELLET_BUCKSHOT = ITEMS.register("pellet_buckshot",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> PELLET_FLECHETTE = ITEMS.register("pellet_flechette",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> PELLET_CHLOROPHYTE = ITEMS.register("pellet_chlorophyte",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> PELLET_METEORITE = ITEMS.register("pellet_meteorite",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> PELLET_CANISTER = ITEMS.register("pellet_canister",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> PELLET_CLAWS = ITEMS.register("pellet_claws",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> CINNABAR = ITEMS.register("cinnabar",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> DUCTTAPE = ITEMS.register("ducttape",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> CATALYST_CLAY = ITEMS.register("catalyst_clay",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> PHOTO_PANEL = ITEMS.register("photo_panel",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> SAT_BASE = ITEMS.register("sat_base",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> THRUSTER_NUCLEAR = ITEMS.register("thruster_nuclear",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> BLADE_TITANIUM = ITEMS.register("blade_titanium",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> TURBINE_TITANIUM = ITEMS.register("turbine_titanium",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> BLADE_TUNGSTEN = ITEMS.register("blade_tungsten",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> TURBINE_TUNGSTEN = ITEMS.register("turbine_tungsten",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> BOARD_COPPER = ITEMS.register("board_copper",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> PIPES_STEEL = ITEMS.register("pipes_steel",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> DRILL_TITANIUM = ITEMS.register("drill_titanium",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> BOLT_COMPOUND = ITEMS.register("bolt_compound",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> HAZMAT_CLOTH = ITEMS.register("hazmat_cloth",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> HAZMAT_CLOTH_RED = ITEMS.register("hazmat_cloth_red",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> HAZMAT_CLOTH_GREY = ITEMS.register("hazmat_cloth_grey",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> ASBESTOS_CLOTH = ITEMS.register("asbestos_cloth",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> RAG_DAMP = ITEMS.register("rag_damp",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> RAG_PISS = ITEMS.register("rag_piss",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> FILTER_COAL = ITEMS.register("filter_coal",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> MAGNET_CIRCULAR = ITEMS.register("magnet_circular",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> CENTRIFUGE_ELEMENT = ITEMS.register("centrifuge_element",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> REACTOR_CORE = ITEMS.register("reactor_core",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> FRAGMENT_NEODYMIUM = ITEMS.register("fragment_neodymium",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> FRAGMENT_COBALT = ITEMS.register("fragment_cobalt",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> FRAGMENT_NIOBIUM = ITEMS.register("fragment_niobium",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> FRAGMENT_CERIUM = ITEMS.register("fragment_cerium",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> FRAGMENT_LANTHANIUM = ITEMS.register("fragment_lanthanium",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> FRAGMENT_ACTINIUM = ITEMS.register("fragment_actinium",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> FRAGMENT_METEORITE = ITEMS.register("fragment_meteorite",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> FRAGMENT_BORON = ITEMS.register("fragment_boron",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> FRAGMENT_COLTAN = ITEMS.register("fragment_coltan",
            () -> new ItemBase(new Item.Properties()));

    // TAB: consumableTab
    public static final DeferredItem<Item> CAP_NUKA = ITEMS.register("cap_nuka",
            () -> new ItemBase(new Item.Properties()));

    // TAB: consumableTab
    public static final DeferredItem<Item> CAP_QUANTUM = ITEMS.register("cap_quantum",
            () -> new ItemBase(new Item.Properties()));

    // TAB: consumableTab
    public static final DeferredItem<Item> CAP_SPARKLE = ITEMS.register("cap_sparkle",
            () -> new ItemBase(new Item.Properties()));

    // TAB: consumableTab
    public static final DeferredItem<Item> CAP_RAD = ITEMS.register("cap_rad",
            () -> new ItemBase(new Item.Properties()));

    // TAB: consumableTab
    public static final DeferredItem<Item> CAP_KORL = ITEMS.register("cap_korl",
            () -> new ItemBase(new Item.Properties()));

    // TAB: consumableTab
    public static final DeferredItem<Item> CAP_FRITZ = ITEMS.register("cap_fritz",
            () -> new ItemBase(new Item.Properties()));

    // TAB: consumableTab
    public static final DeferredItem<Item> RING_PULL = ITEMS.register("ring_pull",
            () -> new ItemBase(new Item.Properties()));

    // TAB: consumableTab
    public static final DeferredItem<Item> BOTTLE_EMPTY = ITEMS.register("bottle_empty",
            () -> new ItemBase(new Item.Properties()));

    // TAB: consumableTab
    public static final DeferredItem<Item> BOTTLE2_EMPTY = ITEMS.register("bottle2_empty",
            () -> new ItemBase(new Item.Properties()));

    // TAB: consumableTab
    public static final DeferredItem<Item> EGG_GLYPHID = ITEMS.register("egg_glyphid",
            () -> new ItemBase(new Item.Properties()));

    // TAB: consumableTab
    public static final DeferredItem<Item> CAN_KEY = ITEMS.register("can_key",
            () -> new ItemBase(new Item.Properties()));

    // TAB: consumableTab
    public static final DeferredItem<Item> CAN_EMPTY = ITEMS.register("can_empty",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> PLATE_IRON = ITEMS.register("plate_iron",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> PLATE_COPPER = ITEMS.register("plate_copper",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> PLATE_TITANIUM = ITEMS.register("plate_titanium",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> PLATE_ALUMINIUM = ITEMS.register("plate_aluminium",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> PLATE_GOLD = ITEMS.register("plate_gold",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> PLATE_STEEL = ITEMS.register("plate_steel",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> PLATE_TUNGSTEN = ITEMS.register("plate_tungsten",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> PLATE_BERYLLIUM = ITEMS.register("plate_beryllium",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> PLATE_NICKEL = ITEMS.register("plate_nickel",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> PLATE_CHROMIUM = ITEMS.register("plate_chromium",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> PLATE_MOLYBDENUM = ITEMS.register("plate_molybdenum",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> PLATE_SILICON = ITEMS.register("plate_silicon",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> PLATE_NIOBIUM = ITEMS.register("plate_niobium",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> PLATE_TANTALIUM = ITEMS.register("plate_tantalium",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> PLATE_LANTHANIUM = ITEMS.register("plate_lanthanium",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> PLATE_URANIUM = ITEMS.register("plate_uranium",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> PLATE_COMBINE_STEEL = ITEMS.register("plate_combine_steel",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> PLATE_SATURNITE = ITEMS.register("plate_saturnite",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> PLATE_DALEKANIUM = ITEMS.register("plate_dalekanium",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> PLATE_MIXED = ITEMS.register("plate_mixed",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> PLATE_KEVLAR = ITEMS.register("plate_kevlar",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> PLATE_POLYMER = ITEMS.register("plate_polymer",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> PLATE_DESH = ITEMS.register("plate_desh",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> PLATE_DINEUTRONIUM = ITEMS.register("plate_dineutronium",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> PLATE_ARMOR_TITANIUM = ITEMS.register("plate_armor_titanium",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> PLATE_ARMOR_AJR = ITEMS.register("plate_armor_ajr",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> PLATE_ARMOR_HEV = ITEMS.register("plate_armor_hev",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> PLATE_ARMOR_LUNAR = ITEMS.register("plate_armor_lunar",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> PLATE_ARMOR_FAU = ITEMS.register("plate_armor_fau",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> PLATE_ARMOR_DNT = ITEMS.register("plate_armor_dnt",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> PLATE_DURA_STEEL = ITEMS.register("plate_dura_steel",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> PLATE_GUNMETAL = ITEMS.register("plate_gunmetal",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> PLATE_WEAPONSTEEL = ITEMS.register("plate_weaponsteel",
            () -> new ItemBase(new Item.Properties()));

    // TAB: templateTab
    public static final DeferredItem<Item> TEMPLATE_FOLDER = ITEMS.register("template_folder",
            () -> new ItemBase(new Item.Properties().stacksTo(1)));

    // TAB: templateTab
    public static final DeferredItem<Item> JOURNAL_PIP = ITEMS.register("journal_pip",
            () -> new ItemBase(new Item.Properties().stacksTo(1)));

    // TAB: templateTab
    public static final DeferredItem<Item> JOURNAL_BJ = ITEMS.register("journal_bj",
            () -> new ItemBase(new Item.Properties().stacksTo(1)));

    // TAB: templateTab
    public static final DeferredItem<Item> JOURNAL_SILVER = ITEMS.register("journal_silver",
            () -> new ItemBase(new Item.Properties().stacksTo(1)));

    // TAB: partsTab
    public static final DeferredItem<Item> CRYSTAL_IRON = ITEMS.register("crystal_iron",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> CRYSTAL_GOLD = ITEMS.register("crystal_gold",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> CRYSTAL_REDSTONE = ITEMS.register("crystal_redstone",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> CRYSTAL_LAPIS = ITEMS.register("crystal_lapis",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> CRYSTAL_DIAMOND = ITEMS.register("crystal_diamond",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> CRYSTAL_TITANIUM = ITEMS.register("crystal_titanium",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> CRYSTAL_SULFUR = ITEMS.register("crystal_sulfur",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> CRYSTAL_NITER = ITEMS.register("crystal_niter",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> CRYSTAL_COPPER = ITEMS.register("crystal_copper",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> CRYSTAL_TUNGSTEN = ITEMS.register("crystal_tungsten",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> CRYSTAL_ALUMINIUM = ITEMS.register("crystal_aluminium",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> CRYSTAL_FLUORITE = ITEMS.register("crystal_fluorite",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> CRYSTAL_BERYLLIUM = ITEMS.register("crystal_beryllium",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> CRYSTAL_RARE = ITEMS.register("crystal_rare",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> CRYSTAL_CINNABAR = ITEMS.register("crystal_cinnabar",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> CRYSTAL_COBALT = ITEMS.register("crystal_cobalt",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> CRYSTAL_STARMETAL = ITEMS.register("crystal_starmetal",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> GEM_ALEXANDRITE = ITEMS.register("gem_alexandrite",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> DEUTERIUM_FILTER = ITEMS.register("deuterium_filter",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> SAWBLADE = ITEMS.register("sawblade",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> MECHANISM_REVOLVER_1 = ITEMS.register("mechanism_revolver_1",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> MECHANISM_REVOLVER_2 = ITEMS.register("mechanism_revolver_2",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> MECHANISM_RIFLE_1 = ITEMS.register("mechanism_rifle_1",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> MECHANISM_RIFLE_2 = ITEMS.register("mechanism_rifle_2",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> MECHANISM_LAUNCHER_1 = ITEMS.register("mechanism_launcher_1",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> MECHANISM_LAUNCHER_2 = ITEMS.register("mechanism_launcher_2",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> MECHANISM_SPECIAL = ITEMS.register("mechanism_special",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> PRIMER_357 = ITEMS.register("primer_357",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> PRIMER_44 = ITEMS.register("primer_44",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> PRIMER_9 = ITEMS.register("primer_9",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> PRIMER_50 = ITEMS.register("primer_50",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> PRIMER_BUCKSHOT = ITEMS.register("primer_buckshot",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> CASING_357 = ITEMS.register("casing_357",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> CASING_44 = ITEMS.register("casing_44",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> CASING_9 = ITEMS.register("casing_9",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> CASING_50 = ITEMS.register("casing_50",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> CASING_BUCKSHOT = ITEMS.register("casing_buckshot",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> COIL_GOLD = ITEMS.register("coil_gold",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> COIL_GOLD_TORUS = ITEMS.register("coil_gold_torus",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> COIL_TUNGSTEN = ITEMS.register("coil_tungsten",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> COIL_COPPER = ITEMS.register("coil_copper",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> COIL_COPPER_TORUS = ITEMS.register("coil_copper_torus",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> ASSEMBLY_NUKE = ITEMS.register("assembly_nuke",
            () -> new ItemBase(new Item.Properties()));

    // TAB: controlTab
    public static final DeferredItem<Item> DEBRIS_GRAPHITE = ITEMS.register("debris_graphite",
            () -> new ItemBase(new Item.Properties()));

    // TAB: controlTab
    public static final DeferredItem<Item> DEBRIS_METAL = ITEMS.register("debris_metal",
            () -> new ItemBase(new Item.Properties()));

    // TAB: controlTab
    public static final DeferredItem<Item> DEBRIS_FUEL = ITEMS.register("debris_fuel",
            () -> new ItemBase(new Item.Properties()));

    // TAB: controlTab
    public static final DeferredItem<Item> DEBRIS_CONCRETE = ITEMS.register("debris_concrete",
            () -> new ItemBase(new Item.Properties()));

    // TAB: controlTab
    public static final DeferredItem<Item> DEBRIS_SHRAPNEL = ITEMS.register("debris_shrapnel",
            () -> new ItemBase(new Item.Properties()));

    // TAB: controlTab
    public static final DeferredItem<Item> DEBRIS_EXCHANGER = ITEMS.register("debris_exchanger",
            () -> new ItemBase(new Item.Properties()));

    // TAB: controlTab
    public static final DeferredItem<Item> DEBRIS_ELEMENT = ITEMS.register("debris_element",
            () -> new ItemBase(new Item.Properties()));

    // TAB: controlTab
    public static final DeferredItem<Item> RBMK_FUEL_EMPTY = ITEMS.register("rbmk_fuel_empty",
            () -> new ItemBase(new Item.Properties().stacksTo(1)));

    // TAB: controlTab
    public static final DeferredItem<Item> ICF_PELLET_EMPTY = ITEMS.register("icf_pellet_empty",
            () -> new ItemBase(new Item.Properties()));

    // TAB: controlTab
    public static final DeferredItem<Item> ICF_PELLET_DEPLETED = ITEMS.register("icf_pellet_depleted",
            () -> new ItemBase(new Item.Properties().stacksTo(1)));

    // TAB: controlTab
    public static final DeferredItem<Item> PARTICLE_EMPTY = ITEMS.register("particle_empty",
            () -> new ItemBase(new Item.Properties()));

    // TAB: controlTab
    public static final DeferredItem<Item> PARTICLE_HYDROGEN = ITEMS.register("particle_hydrogen",
            () -> new ItemBase(new Item.Properties().craftRemainder(ModItems.PARTICLE_EMPTY.get())));

    // TAB: controlTab
    public static final DeferredItem<Item> PARTICLE_COPPER = ITEMS.register("particle_copper",
            () -> new ItemBase(new Item.Properties().craftRemainder(ModItems.PARTICLE_EMPTY.get())));

    // TAB: controlTab
    public static final DeferredItem<Item> PARTICLE_LEAD = ITEMS.register("particle_lead",
            () -> new ItemBase(new Item.Properties().craftRemainder(ModItems.PARTICLE_EMPTY.get())));

    // TAB: controlTab
    public static final DeferredItem<Item> PARTICLE_MUON = ITEMS.register("particle_muon",
            () -> new ItemBase(new Item.Properties().craftRemainder(ModItems.PARTICLE_EMPTY.get())));

    // TAB: controlTab
    public static final DeferredItem<Item> PARTICLE_AMAT = ITEMS.register("particle_amat",
            () -> new ItemBase(new Item.Properties().craftRemainder(ModItems.PARTICLE_EMPTY.get())));

    // TAB: controlTab
    public static final DeferredItem<Item> PARTICLE_ASCHRAB = ITEMS.register("particle_aschrab",
            () -> new ItemBase(new Item.Properties().craftRemainder(ModItems.PARTICLE_EMPTY.get())));

    // TAB: controlTab
    public static final DeferredItem<Item> PARTICLE_HIGGS = ITEMS.register("particle_higgs",
            () -> new ItemBase(new Item.Properties().craftRemainder(ModItems.PARTICLE_EMPTY.get())));

    // TAB: controlTab
    public static final DeferredItem<Item> PARTICLE_TACHYON = ITEMS.register("particle_tachyon",
            () -> new ItemBase(new Item.Properties().craftRemainder(ModItems.PARTICLE_EMPTY.get())));

    // TAB: controlTab
    public static final DeferredItem<Item> PARTICLE_DARK = ITEMS.register("particle_dark",
            () -> new ItemBase(new Item.Properties().craftRemainder(ModItems.PARTICLE_EMPTY.get())));

    // TAB: controlTab
    public static final DeferredItem<Item> PARTICLE_STRANGE = ITEMS.register("particle_strange",
            () -> new ItemBase(new Item.Properties().craftRemainder(ModItems.PARTICLE_EMPTY.get())));

    // TAB: controlTab
    public static final DeferredItem<Item> PARTICLE_SPARKTICLE = ITEMS.register("particle_sparkticle",
            () -> new ItemBase(new Item.Properties().craftRemainder(ModItems.PARTICLE_EMPTY.get())));

    // TAB: null
    public static final DeferredItem<Item> PELLET_COOLANT = ITEMS.register("pellet_coolant",
            () -> new ItemBase(new Item.Properties().stacksTo(1).durability(41400)));

    // TAB: controlTab
    public static final DeferredItem<Item> AMS_CATALYST_BLANK = ITEMS.register("ams_catalyst_blank",
            () -> new ItemBase(new Item.Properties().stacksTo(1)));

    // TAB: nukeTab
    public static final DeferredItem<Item> GADGET_EXPLOSIVE = ITEMS.register("gadget_explosive",
            () -> new ItemBase(new Item.Properties()));

    // TAB: nukeTab
    public static final DeferredItem<Item> MAN_EXPLOSIVE = ITEMS.register("man_explosive",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> MISSILE_ASSEMBLY = ITEMS.register("missile_assembly",
            () -> new ItemBase(new Item.Properties().stacksTo(1)));

    // TAB: partsTab
    public static final DeferredItem<Item> WARHEAD_GENERIC_SMALL = ITEMS.register("warhead_generic_small",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> WARHEAD_INCENDIARY_SMALL = ITEMS.register("warhead_incendiary_small",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> WARHEAD_CLUSTER_SMALL = ITEMS.register("warhead_cluster_small",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> WARHEAD_BUSTER_SMALL = ITEMS.register("warhead_buster_small",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> WARHEAD_GENERIC_MEDIUM = ITEMS.register("warhead_generic_medium",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> WARHEAD_INCENDIARY_MEDIUM = ITEMS.register("warhead_incendiary_medium",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> WARHEAD_CLUSTER_MEDIUM = ITEMS.register("warhead_cluster_medium",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> WARHEAD_BUSTER_MEDIUM = ITEMS.register("warhead_buster_medium",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> WARHEAD_GENERIC_LARGE = ITEMS.register("warhead_generic_large",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> WARHEAD_INCENDIARY_LARGE = ITEMS.register("warhead_incendiary_large",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> WARHEAD_CLUSTER_LARGE = ITEMS.register("warhead_cluster_large",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> WARHEAD_BUSTER_LARGE = ITEMS.register("warhead_buster_large",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> WARHEAD_N2 = ITEMS.register("warhead_n2",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> WARHEAD_NUCLEAR = ITEMS.register("warhead_nuclear",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> WARHEAD_MIRVLET = ITEMS.register("warhead_mirvlet",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> WARHEAD_MIRV = ITEMS.register("warhead_mirv",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> WARHEAD_VOLCANO = ITEMS.register("warhead_volcano",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> WARHEAD_THERMO_ENDO = ITEMS.register("warhead_thermo_endo",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> WARHEAD_THERMO_EXO = ITEMS.register("warhead_thermo_exo",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> THRUSTER_SMALL = ITEMS.register("thruster_small",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> THRUSTER_MEDIUM = ITEMS.register("thruster_medium",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> THRUSTER_LARGE = ITEMS.register("thruster_large",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> CAP_ALUMINIUM = ITEMS.register("cap_aluminium",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> FINS_FLAT = ITEMS.register("fins_flat",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> FINS_SMALL_STEEL = ITEMS.register("fins_small_steel",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> FINS_BIG_STEEL = ITEMS.register("fins_big_steel",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> FINS_TRI_STEEL = ITEMS.register("fins_tri_steel",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> FINS_QUAD_TITANIUM = ITEMS.register("fins_quad_titanium",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> SPHERE_STEEL = ITEMS.register("sphere_steel",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> PEDESTAL_STEEL = ITEMS.register("pedestal_steel",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> DYSFUNCTIONAL_REACTOR = ITEMS.register("dysfunctional_reactor",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> ROTOR_STEEL = ITEMS.register("rotor_steel",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> GENERATOR_STEEL = ITEMS.register("generator_steel",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> SAT_HEAD_MAPPER = ITEMS.register("sat_head_mapper",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> SAT_HEAD_SCANNER = ITEMS.register("sat_head_scanner",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> SAT_HEAD_RADAR = ITEMS.register("sat_head_radar",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> SAT_HEAD_LASER = ITEMS.register("sat_head_laser",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> SAT_HEAD_RESONATOR = ITEMS.register("sat_head_resonator",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> SEG_10 = ITEMS.register("seg_10",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> SEG_15 = ITEMS.register("seg_15",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> SEG_20 = ITEMS.register("seg_20",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> FUEL_TANK_SMALL = ITEMS.register("fuel_tank_small",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> FUEL_TANK_MEDIUM = ITEMS.register("fuel_tank_medium",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> FUEL_TANK_LARGE = ITEMS.register("fuel_tank_large",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> TANK_STEEL = ITEMS.register("tank_steel",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> CHOPPER_HEAD = ITEMS.register("chopper_head",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> CHOPPER_GUN = ITEMS.register("chopper_gun",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> CHOPPER_TORSO = ITEMS.register("chopper_torso",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> CHOPPER_TAIL = ITEMS.register("chopper_tail",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> CHOPPER_WING = ITEMS.register("chopper_wing",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> CHOPPER_BLADES = ITEMS.register("chopper_blades",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> COMBINE_SCRAP = ITEMS.register("combine_scrap",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> SHIMMER_HEAD = ITEMS.register("shimmer_head",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> SHIMMER_AXE_HEAD = ITEMS.register("shimmer_axe_head",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> SHIMMER_HANDLE = ITEMS.register("shimmer_handle",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab
    public static final DeferredItem<Item> TELEPAD = ITEMS.register("telepad",
            () -> new ItemBase(new Item.Properties()));

    // TAB: null
    public static final DeferredItem<Item> CLOUD1 = ITEMS.register("cloud1",
            () -> new EffectItem(new Item.Properties()));

    // TAB: null
    public static final DeferredItem<Item> CLOUD2 = ITEMS.register("cloud2",
            () -> new EffectItem(new Item.Properties()));

    // TAB: null
    public static final DeferredItem<Item> CLOUD3 = ITEMS.register("cloud3",
            () -> new EffectItem(new Item.Properties()));

    // TAB: null
    public static final DeferredItem<Item> CLOUD4 = ITEMS.register("cloud4",
            () -> new EffectItem(new Item.Properties()));

    // TAB: null
    public static final DeferredItem<Item> CLOUD5 = ITEMS.register("cloud5",
            () -> new EffectItem(new Item.Properties()));

    // TAB: null
    public static final DeferredItem<Item> CLOUD6 = ITEMS.register("cloud6",
            () -> new EffectItem(new Item.Properties()));

    // TAB: null
    public static final DeferredItem<Item> CLOUD7 = ITEMS.register("cloud7",
            () -> new EffectItem(new Item.Properties()));

    // TAB: null
    public static final DeferredItem<Item> CLOUD8 = ITEMS.register("cloud8",
            () -> new EffectItem(new Item.Properties()));

    // TAB: null
    public static final DeferredItem<Item> FLAME_1 = ITEMS.register("flame_1",
            () -> new EffectItem(new Item.Properties()));

    // TAB: null
    public static final DeferredItem<Item> FLAME_2 = ITEMS.register("flame_2",
            () -> new EffectItem(new Item.Properties()));

    // TAB: null
    public static final DeferredItem<Item> FLAME_3 = ITEMS.register("flame_3",
            () -> new EffectItem(new Item.Properties()));

    // TAB: null
    public static final DeferredItem<Item> FLAME_4 = ITEMS.register("flame_4",
            () -> new EffectItem(new Item.Properties()));

    // TAB: null
    public static final DeferredItem<Item> FLAME_5 = ITEMS.register("flame_5",
            () -> new EffectItem(new Item.Properties()));

    // TAB: null
    public static final DeferredItem<Item> FLAME_6 = ITEMS.register("flame_6",
            () -> new EffectItem(new Item.Properties()));

    // TAB: null
    public static final DeferredItem<Item> FLAME_7 = ITEMS.register("flame_7",
            () -> new EffectItem(new Item.Properties()));

    // TAB: null
    public static final DeferredItem<Item> FLAME_8 = ITEMS.register("flame_8",
            () -> new EffectItem(new Item.Properties()));

    // TAB: null
    public static final DeferredItem<Item> FLAME_9 = ITEMS.register("flame_9",
            () -> new EffectItem(new Item.Properties()));

    // TAB: null
    public static final DeferredItem<Item> FLAME_10 = ITEMS.register("flame_10",
            () -> new EffectItem(new Item.Properties()));

    // TAB: null
    public static final DeferredItem<Item> ORANGE1 = ITEMS.register("orange1",
            () -> new EffectItem(new Item.Properties()));

    // TAB: null
    public static final DeferredItem<Item> ORANGE2 = ITEMS.register("orange2",
            () -> new EffectItem(new Item.Properties()));

    // TAB: null
    public static final DeferredItem<Item> ORANGE3 = ITEMS.register("orange3",
            () -> new EffectItem(new Item.Properties()));

    // TAB: null
    public static final DeferredItem<Item> ORANGE4 = ITEMS.register("orange4",
            () -> new EffectItem(new Item.Properties()));

    // TAB: null
    public static final DeferredItem<Item> ORANGE5 = ITEMS.register("orange5",
            () -> new EffectItem(new Item.Properties()));

    // TAB: null
    public static final DeferredItem<Item> ORANGE6 = ITEMS.register("orange6",
            () -> new EffectItem(new Item.Properties()));

    // TAB: null
    public static final DeferredItem<Item> ORANGE7 = ITEMS.register("orange7",
            () -> new EffectItem(new Item.Properties()));

    // TAB: null
    public static final DeferredItem<Item> ORANGE8 = ITEMS.register("orange8",
            () -> new EffectItem(new Item.Properties()));

    // TAB: null
    public static final DeferredItem<Item> PC1 = ITEMS.register("pc1",
            () -> new EffectItem(new Item.Properties()));

    // TAB: null
    public static final DeferredItem<Item> PC2 = ITEMS.register("pc2",
            () -> new EffectItem(new Item.Properties()));

    // TAB: null
    public static final DeferredItem<Item> PC3 = ITEMS.register("pc3",
            () -> new EffectItem(new Item.Properties()));

    // TAB: null
    public static final DeferredItem<Item> PC4 = ITEMS.register("pc4",
            () -> new EffectItem(new Item.Properties()));

    // TAB: null
    public static final DeferredItem<Item> PC5 = ITEMS.register("pc5",
            () -> new EffectItem(new Item.Properties()));

    // TAB: null
    public static final DeferredItem<Item> PC6 = ITEMS.register("pc6",
            () -> new EffectItem(new Item.Properties()));

    // TAB: null
    public static final DeferredItem<Item> PC7 = ITEMS.register("pc7",
            () -> new EffectItem(new Item.Properties()));

    // TAB: null
    public static final DeferredItem<Item> PC8 = ITEMS.register("pc8",
            () -> new EffectItem(new Item.Properties()));

    // TAB: null
    public static final DeferredItem<Item> CHLORINE1 = ITEMS.register("chlorine1",
            () -> new EffectItem(new Item.Properties()));

    // TAB: null
    public static final DeferredItem<Item> CHLORINE2 = ITEMS.register("chlorine2",
            () -> new EffectItem(new Item.Properties()));

    // TAB: null
    public static final DeferredItem<Item> CHLORINE3 = ITEMS.register("chlorine3",
            () -> new EffectItem(new Item.Properties()));

    // TAB: null
    public static final DeferredItem<Item> CHLORINE4 = ITEMS.register("chlorine4",
            () -> new EffectItem(new Item.Properties()));

    // TAB: null
    public static final DeferredItem<Item> CHLORINE5 = ITEMS.register("chlorine5",
            () -> new EffectItem(new Item.Properties()));

    // TAB: null
    public static final DeferredItem<Item> CHLORINE6 = ITEMS.register("chlorine6",
            () -> new EffectItem(new Item.Properties()));

    // TAB: null
    public static final DeferredItem<Item> CHLORINE7 = ITEMS.register("chlorine7",
            () -> new EffectItem(new Item.Properties()));

    // TAB: null
    public static final DeferredItem<Item> CHLORINE8 = ITEMS.register("chlorine8",
            () -> new EffectItem(new Item.Properties()));

    // TAB: null
    public static final DeferredItem<Item> LN2_1 = ITEMS.register("ln2_1",
            () -> new EffectItem(new Item.Properties()));

    // TAB: null
    public static final DeferredItem<Item> LN2_2 = ITEMS.register("ln2_2",
            () -> new EffectItem(new Item.Properties()));

    // TAB: null
    public static final DeferredItem<Item> LN2_3 = ITEMS.register("ln2_3",
            () -> new EffectItem(new Item.Properties()));

    // TAB: null
    public static final DeferredItem<Item> LN2_4 = ITEMS.register("ln2_4",
            () -> new EffectItem(new Item.Properties()));

    // TAB: null
    public static final DeferredItem<Item> LN2_5 = ITEMS.register("ln2_5",
            () -> new EffectItem(new Item.Properties()));

    // TAB: null
    public static final DeferredItem<Item> LN2_6 = ITEMS.register("ln2_6",
            () -> new EffectItem(new Item.Properties()));

    // TAB: null
    public static final DeferredItem<Item> LN2_7 = ITEMS.register("ln2_7",
            () -> new EffectItem(new Item.Properties()));

    // TAB: null
    public static final DeferredItem<Item> LN2_8 = ITEMS.register("ln2_8",
            () -> new EffectItem(new Item.Properties()));

    // TAB: null
    public static final DeferredItem<Item> LN2_9 = ITEMS.register("ln2_9",
            () -> new EffectItem(new Item.Properties()));

    // TAB: null
    public static final DeferredItem<Item> LN2_10 = ITEMS.register("ln2_10",
            () -> new EffectItem(new Item.Properties()));

    // TAB: null
    public static final DeferredItem<Item> NOTHING = ITEMS.register("nothing",
            () -> new EffectItem(new Item.Properties()));

    // TAB: controlTab
    public static final DeferredItem<Item> DUCC = ITEMS.register("ducc",
            () -> new ItemBase(new Item.Properties()));

    // TAB: null
    public static final DeferredItem<Item> DISCHARGE = ITEMS.register("discharge",
            () -> new EffectItem(new Item.Properties()));

    // TAB: partsTab闂備焦瀵х粙鎴濓耿?.1 闂備線鈧稓绁锋い顐㈩樀椤㈡洜鍖?闂佽崵鍋炵粙蹇涘磿闁秴鐭楅煫鍥ㄧ⊕閺咁剚鎱ㄥ鍡楀闁?CE 濠?ItemBakedBase/ItemCustomLore闂備焦瀵х粙鎴︻敆? 闂佸搫顦弲娆撴嚄閺堢數鏄傞梻浣告啞閼瑰墽鈧稈鏅涢湁婵炴垯鍨圭粻鏌ユ煏韫囧鐏辨い顐畵閺屻劌鈽夊Ο缁樻嫳闂佽桨鑳堕崑銈呯暦濡棿娌柤鎭掑劚閳ь剙鍟块埥澶愬箻瀹曞泦銏㈡喐閺夋妯€闁?
    public static final DeferredItem<Item> INGOT_ASBESTOS = ITEMS.register("ingot_asbestos",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab闂備焦瀵х粙鎴濓耿?.1 闂備線鈧稓绁锋い顐㈩樀椤㈡洜鍖?闂佽崵鍋炵粙蹇涘磿闁秴鐭楅煫鍥ㄧ⊕閺?
    public static final DeferredItem<Item> GEM_VOLCANIC = ITEMS.register("gem_volcanic",
            () -> new ItemBase(new Item.Properties()));
    // P5.2 recipe-line placeholder (CE: ItemRBMKRod rbmk_fuel_balefire)
    public static final DeferredItem<ItemRBMKRod> RBMK_FUEL_BALEFIRE = ITEMS.register("rbmk_fuel_balefire",
            () -> new ItemRBMKRod(new Item.Properties()));
    // P5.2 recipe-line placeholder (CE: ItemRBMKRod rbmk_fuel_balefire_gold)
    public static final DeferredItem<ItemRBMKRod> RBMK_FUEL_BALEFIRE_GOLD = ITEMS.register("rbmk_fuel_balefire_gold",
            () -> new ItemRBMKRod(new Item.Properties()));
    // P5.2 recipe-line placeholder (CE: ItemRBMKRod rbmk_fuel_drx)
    public static final DeferredItem<ItemRBMKRod> RBMK_FUEL_DRX = ITEMS.register("rbmk_fuel_drx",
            () -> new ItemRBMKRod(new Item.Properties()));
    // P5.2 recipe-line placeholder (CE: ItemRBMKRod rbmk_fuel_flashlead)
    public static final DeferredItem<ItemRBMKRod> RBMK_FUEL_FLASHLEAD = ITEMS.register("rbmk_fuel_flashlead",
            () -> new ItemRBMKRod(new Item.Properties()));
    // P5.2 recipe-line placeholder (CE: ItemRBMKRod rbmk_fuel_hea241)
    public static final DeferredItem<ItemRBMKRod> RBMK_FUEL_HEA241 = ITEMS.register("rbmk_fuel_hea241",
            () -> new ItemRBMKRod(new Item.Properties()));
    // P5.2 recipe-line placeholder (CE: ItemRBMKRod rbmk_fuel_hea242)
    public static final DeferredItem<ItemRBMKRod> RBMK_FUEL_HEA242 = ITEMS.register("rbmk_fuel_hea242",
            () -> new ItemRBMKRod(new Item.Properties()));
    // P5.2 recipe-line placeholder (CE: ItemRBMKRod rbmk_fuel_heaus)
    public static final DeferredItem<ItemRBMKRod> RBMK_FUEL_HEAUS = ITEMS.register("rbmk_fuel_heaus",
            () -> new ItemRBMKRod(new Item.Properties()));
    // P5.2 recipe-line placeholder (CE: ItemRBMKRod rbmk_fuel_hen)
    public static final DeferredItem<ItemRBMKRod> RBMK_FUEL_HEN = ITEMS.register("rbmk_fuel_hen",
            () -> new ItemRBMKRod(new Item.Properties()));
    // P5.2 recipe-line placeholder (CE: ItemRBMKRod rbmk_fuel_hep239)
    public static final DeferredItem<ItemRBMKRod> RBMK_FUEL_HEP239 = ITEMS.register("rbmk_fuel_hep",
            () -> new ItemRBMKRod(new Item.Properties()));
    // P5.2 recipe-line placeholder (CE: ItemRBMKRod rbmk_fuel_hep241)
    public static final DeferredItem<ItemRBMKRod> RBMK_FUEL_HEP241 = ITEMS.register("rbmk_fuel_hep241",
            () -> new ItemRBMKRod(new Item.Properties()));
    // P5.2 recipe-line placeholder (CE: ItemRBMKRod rbmk_fuel_hes)
    public static final DeferredItem<ItemRBMKRod> RBMK_FUEL_HES = ITEMS.register("rbmk_fuel_hes",
            () -> new ItemRBMKRod(new Item.Properties()));
    // P5.2 recipe-line placeholder (CE: ItemRBMKRod rbmk_fuel_heu233)
    public static final DeferredItem<ItemRBMKRod> RBMK_FUEL_HEU233 = ITEMS.register("rbmk_fuel_heu233",
            () -> new ItemRBMKRod(new Item.Properties()));
    // P5.2 recipe-line placeholder (CE: ItemRBMKRod rbmk_fuel_heu235)
    public static final DeferredItem<ItemRBMKRod> RBMK_FUEL_HEU235 = ITEMS.register("rbmk_fuel_heu235",
            () -> new ItemRBMKRod(new Item.Properties()));
    // P5.2 recipe-line placeholder (CE: ItemRBMKRod rbmk_fuel_lea)
    public static final DeferredItem<ItemRBMKRod> RBMK_FUEL_LEA = ITEMS.register("rbmk_fuel_lea",
            () -> new ItemRBMKRod(new Item.Properties()));
    // P5.2 recipe-line placeholder (CE: ItemRBMKRod rbmk_fuel_leaus)
    public static final DeferredItem<ItemRBMKRod> RBMK_FUEL_LEAUS = ITEMS.register("rbmk_fuel_leaus",
            () -> new ItemRBMKRod(new Item.Properties()));
    // P5.2 recipe-line placeholder (CE: ItemRBMKRod rbmk_fuel_lep)
    public static final DeferredItem<ItemRBMKRod> RBMK_FUEL_LEP = ITEMS.register("rbmk_fuel_lep",
            () -> new ItemRBMKRod(new Item.Properties()));
    // P5.2 recipe-line placeholder (CE: ItemRBMKRod rbmk_fuel_les)
    public static final DeferredItem<ItemRBMKRod> RBMK_FUEL_LES = ITEMS.register("rbmk_fuel_les",
            () -> new ItemRBMKRod(new Item.Properties()));
    // P5.2 recipe-line placeholder (CE: ItemRBMKRod rbmk_fuel_mea)
    public static final DeferredItem<ItemRBMKRod> RBMK_FUEL_MEA = ITEMS.register("rbmk_fuel_mea",
            () -> new ItemRBMKRod(new Item.Properties()));
    // P5.2 recipe-line placeholder (CE: ItemRBMKRod rbmk_fuel_men)
    public static final DeferredItem<ItemRBMKRod> RBMK_FUEL_MEN = ITEMS.register("rbmk_fuel_men",
            () -> new ItemRBMKRod(new Item.Properties()));
    // P5.2 recipe-line placeholder (CE: ItemRBMKRod rbmk_fuel_mep)
    public static final DeferredItem<ItemRBMKRod> RBMK_FUEL_MEP = ITEMS.register("rbmk_fuel_mep",
            () -> new ItemRBMKRod(new Item.Properties()));
    // P5.2 recipe-line placeholder (CE: ItemRBMKRod rbmk_fuel_mes)
    public static final DeferredItem<ItemRBMKRod> RBMK_FUEL_MES = ITEMS.register("rbmk_fuel_mes",
            () -> new ItemRBMKRod(new Item.Properties()));
    // P5.2 recipe-line placeholder (CE: ItemRBMKRod rbmk_fuel_meu)
    public static final DeferredItem<ItemRBMKRod> RBMK_FUEL_MEU = ITEMS.register("rbmk_fuel_meu",
            () -> new ItemRBMKRod(new Item.Properties()));
    // P5.2 recipe-line placeholder (CE: ItemRBMKRod rbmk_fuel_mox)
    public static final DeferredItem<ItemRBMKRod> RBMK_FUEL_MOX = ITEMS.register("rbmk_fuel_mox",
            () -> new ItemRBMKRod(new Item.Properties()));
    // P5.2 recipe-line placeholder (CE: ItemRBMKRod rbmk_fuel_po210be)
    public static final DeferredItem<ItemRBMKRod> RBMK_FUEL_PO210BE = ITEMS.register("rbmk_fuel_po210be",
            () -> new ItemRBMKRod(new Item.Properties()));
    // P5.2 recipe-line placeholder (CE: ItemRBMKRod rbmk_fuel_pu238be)
    public static final DeferredItem<ItemRBMKRod> RBMK_FUEL_PU238BE = ITEMS.register("rbmk_fuel_pu238be",
            () -> new ItemRBMKRod(new Item.Properties()));
    // P5.2 recipe-line placeholder (CE: ItemRBMKRod rbmk_fuel_ra226be)
    public static final DeferredItem<ItemRBMKRod> RBMK_FUEL_RA226BE = ITEMS.register("rbmk_fuel_ra226be",
            () -> new ItemRBMKRod(new Item.Properties()));
    // P5.2 recipe-line placeholder (CE: ItemRBMKRod rbmk_fuel_thmeu)
    public static final DeferredItem<ItemRBMKRod> RBMK_FUEL_THMEU = ITEMS.register("rbmk_fuel_thmeu",
            () -> new ItemRBMKRod(new Item.Properties()));
    // P5.2 recipe-line placeholder (CE: ItemRBMKRod rbmk_fuel_ueu)
    public static final DeferredItem<ItemRBMKRod> RBMK_FUEL_UEU = ITEMS.register("rbmk_fuel_ueu",
            () -> new ItemRBMKRod(new Item.Properties()));
    // P5.2 recipe-line placeholder (CE: ItemRBMKRod rbmk_fuel_zfb_am_mix)
    public static final DeferredItem<ItemRBMKRod> RBMK_FUEL_ZFB_AM_MIX = ITEMS.register("rbmk_fuel_zfb_am_mix",
            () -> new ItemRBMKRod(new Item.Properties()));
    // P5.2 recipe-line placeholder (CE: ItemRBMKRod rbmk_fuel_zfb_bismuth)
    public static final DeferredItem<ItemRBMKRod> RBMK_FUEL_ZFB_BISMUTH = ITEMS.register("rbmk_fuel_zfb_bismuth",
            () -> new ItemRBMKRod(new Item.Properties()));
    // P5.2 recipe-line placeholder (CE: ItemRBMKRod rbmk_fuel_zfb_pu241)
    public static final DeferredItem<ItemRBMKRod> RBMK_FUEL_ZFB_PU241 = ITEMS.register("rbmk_fuel_zfb_pu241",
            () -> new ItemRBMKRod(new Item.Properties()));
    // P5.2 recipe-line placeholder
    public static final DeferredItem<Item> NUGGET_URANIUM_FUEL = ITEMS.register("nugget_uranium_fuel",
            () -> new ItemBase(new Item.Properties()));
    // P5.2 recipe-line placeholder
    public static final DeferredItem<Item> NUCLEAR_WASTE_TINY = ITEMS.register("nuclear_waste_tiny",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P4.2 ingot batch; CE type ItemCustomLore, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> INGOT_ACTINIUM = ITEMS.register("ingot_actinium",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P4.2 ingot batch; CE type ItemBakedBase, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> INGOT_ALUMINIUM = ITEMS.register("ingot_aluminium",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P4.2 ingot batch; CE type ItemCustomLore, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> INGOT_AM_MIX = ITEMS.register("ingot_am_mix",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P4.2 ingot batch; CE type ItemCustomLore, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> INGOT_AM241 = ITEMS.register("ingot_am241",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P4.2 ingot batch; CE type ItemCustomLore, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> INGOT_AM242 = ITEMS.register("ingot_am242",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P4.2 ingot batch; CE type ItemCustomLore, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> INGOT_AMERICIUM_FUEL = ITEMS.register("ingot_americium_fuel",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P4.2 ingot batch; CE type ItemCustomLore, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> INGOT_ARSENIC = ITEMS.register("ingot_arsenic",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P4.2 ingot batch; CE type ItemBakedBase, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> INGOT_ARSENIC_BRONZE = ITEMS.register("ingot_arsenic_bronze",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P4.2 ingot batch; CE type ItemCustomLore, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> INGOT_ASTATINE = ITEMS.register("ingot_astatine",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P4.2 ingot batch; CE type ItemBakedBase, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> INGOT_AU198 = ITEMS.register("ingot_au198",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P4.2 ingot batch; CE type ItemCustomLore, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> INGOT_AUSTRALIUM = ITEMS.register("ingot_australium",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P4.2 ingot batch; CE type ItemCustomLore, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> INGOT_BAKELITE = ITEMS.register("ingot_bakelite",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P4.2 ingot batch; CE type ItemBakedBase, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> INGOT_BERYLLIUM = ITEMS.register("ingot_beryllium",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P4.2 ingot batch; CE type ItemCustomLore, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> INGOT_BIORUBBER = ITEMS.register("ingot_biorubber",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P4.2 ingot batch; CE type ItemCustomLore, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> INGOT_BISMUTH = ITEMS.register("ingot_bismuth",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P4.2 ingot batch; CE type ItemBakedBase, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> INGOT_BISMUTH_BRONZE = ITEMS.register("ingot_bismuth_bronze",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P4.2 ingot batch; CE type ItemBakedBase, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> INGOT_BORON = ITEMS.register("ingot_boron",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P4.2 ingot batch; CE type ItemCustomLore, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> INGOT_BROMINE = ITEMS.register("ingot_bromine",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P4.2 ingot batch; CE type ItemBakedBase, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> INGOT_BSCCO = ITEMS.register("ingot_bscco",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P4.2 ingot batch; CE type ItemFuel, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> INGOT_C4 = ITEMS.register("ingot_c4",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P4.2 ingot batch; CE type ItemBakedBase, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> INGOT_CADMIUM = ITEMS.register("ingot_cadmium",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P4.2 ingot batch; CE type ItemBakedBase, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> INGOT_CAESIUM = ITEMS.register("ingot_caesium",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P4.2 ingot batch; CE type ItemBakedBase, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> INGOT_CALCIUM = ITEMS.register("ingot_calcium",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P4.2 ingot batch; CE type ItemBakedBase, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> INGOT_CDALLOY = ITEMS.register("ingot_cdalloy",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P4.2 ingot batch; CE type ItemCustomLore, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> INGOT_CERIUM = ITEMS.register("ingot_cerium",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P4.2 ingot batch; CE type ItemBakedBase, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> INGOT_CFT = ITEMS.register("ingot_cft",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P4.2 ingot batch; CE type ItemBakedBase, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> INGOT_CO60 = ITEMS.register("ingot_co60",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P4.2 ingot batch; CE type ItemCustomLore, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> INGOT_COBALT = ITEMS.register("ingot_cobalt",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P4.2 ingot batch; CE type ItemCustomLore, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> INGOT_COMBINE_STEEL = ITEMS.register("ingot_combine_steel",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P4.2 ingot batch; CE type ItemBakedBase, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> INGOT_COPPER = ITEMS.register("ingot_copper",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P4.2 ingot batch; CE type ItemCustomLore, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> INGOT_DAFFERGON = ITEMS.register("ingot_daffergon",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P4.2 ingot batch; CE type ItemCustomLore, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> INGOT_DESH = ITEMS.register("ingot_desh",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P4.2 ingot batch; CE type ItemCustomLore, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> INGOT_DINEUTRONIUM = ITEMS.register("ingot_dineutronium",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P4.2 ingot batch; CE type ItemCustomLore, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> INGOT_DURA_STEEL = ITEMS.register("ingot_dura_steel",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P4.2 ingot batch; CE type ItemCustomLore, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> INGOT_EUPHEMIUM = ITEMS.register("ingot_euphemium",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P4.2 ingot batch; CE type ItemBakedBase, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> INGOT_FERROURANIUM = ITEMS.register("ingot_ferrouranium",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P4.2 ingot batch; CE type ItemCustomLore, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> INGOT_FIBERGLASS = ITEMS.register("ingot_fiberglass",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P4.2 ingot batch; CE type ItemBakedBase, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> INGOT_FIREBRICK = ITEMS.register("ingot_firebrick",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P4.2 ingot batch; CE type ItemCustomLore, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> INGOT_GH336 = ITEMS.register("ingot_gh336",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P4.2 ingot batch; CE type ItemFuel, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> INGOT_GRAPHITE = ITEMS.register("ingot_graphite",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P4.2 ingot batch; CE type ItemBakedBase, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> INGOT_GUNMETAL = ITEMS.register("ingot_gunmetal",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P4.2 ingot batch; CE type ItemCustomLore, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> INGOT_HES = ITEMS.register("ingot_hes",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P4.2 ingot batch; CE type ItemBakedBase, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> INGOT_I131 = ITEMS.register("ingot_i131",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P4.2 ingot batch; CE type ItemCustomLore, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> INGOT_IODINE = ITEMS.register("ingot_iodine",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P4.2 ingot batch; CE type ItemCustomLore, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> INGOT_LANTHANIUM = ITEMS.register("ingot_lanthanium",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P4.2 ingot batch; CE type ItemBakedBase, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> INGOT_LEAD = ITEMS.register("ingot_lead",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P4.2 ingot batch; CE type ItemCustomLore, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> INGOT_LES = ITEMS.register("ingot_les",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P4.2 ingot batch; CE type ItemBakedBase, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> INGOT_MAGNETIZED_TUNGSTEN = ITEMS.register("ingot_magnetized_tungsten",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P4.2 ingot batch; CE type ItemCustomLore, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> INGOT_MOX_FUEL = ITEMS.register("ingot_mox_fuel",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P4.2 ingot batch; CE type ItemBakedBase, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> INGOT_MUD = ITEMS.register("ingot_mud",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P4.2 ingot batch; CE type ItemCustomLore, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> INGOT_NEPTUNIUM = ITEMS.register("ingot_neptunium",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P4.2 ingot batch; CE type ItemCustomLore, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> INGOT_NEPTUNIUM_FUEL = ITEMS.register("ingot_neptunium_fuel",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P4.2 ingot batch; CE type ItemCustomLore, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> INGOT_NIOBIUM = ITEMS.register("ingot_niobium",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P4.2 ingot batch; CE type ItemCustomLore, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> INGOT_OSMIRIDIUM = ITEMS.register("ingot_osmiridium",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P4.2 ingot batch; CE type ItemBakedBase, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> INGOT_PB209 = ITEMS.register("ingot_pb209",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P4.2 ingot batch; CE type ItemCustomLore, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> INGOT_PC = ITEMS.register("ingot_pc",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P4.2 ingot batch; CE type ItemCustomLore, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> INGOT_PHOSPHORUS = ITEMS.register("ingot_phosphorus",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P4.2 ingot batch; CE type ItemCustomLore, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> INGOT_PLUTONIUM = ITEMS.register("ingot_plutonium",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P4.2 ingot batch; CE type ItemCustomLore, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> INGOT_PLUTONIUM_FUEL = ITEMS.register("ingot_plutonium_fuel",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P4.2 ingot batch; CE type ItemCustomLore, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> INGOT_POLONIUM = ITEMS.register("ingot_polonium",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P4.2 ingot batch; CE type ItemCustomLore, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> INGOT_POLYMER = ITEMS.register("ingot_polymer",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P4.2 ingot batch; CE type ItemCustomLore, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> INGOT_PU_MIX = ITEMS.register("ingot_pu_mix",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P4.2 ingot batch; CE type ItemCustomLore, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> INGOT_PU238 = ITEMS.register("ingot_pu238",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P4.2 ingot batch; CE type ItemCustomLore, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> INGOT_PU239 = ITEMS.register("ingot_pu239",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P4.2 ingot batch; CE type ItemCustomLore, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> INGOT_PU240 = ITEMS.register("ingot_pu240",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P4.2 ingot batch; CE type ItemCustomLore, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> INGOT_PU241 = ITEMS.register("ingot_pu241",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P4.2 ingot batch; CE type ItemCustomLore, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> INGOT_PVC = ITEMS.register("ingot_pvc",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P4.2 ingot batch; CE type ItemBakedBase, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> INGOT_RA226 = ITEMS.register("ingot_ra226",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P4.2 ingot batch; CE type ItemBakedBase, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> INGOT_RED_COPPER = ITEMS.register("ingot_red_copper",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P4.2 ingot batch; CE type ItemCustomLore, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> INGOT_REIIUM = ITEMS.register("ingot_reiium",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P4.2 ingot batch; CE type ItemCustomLore, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> INGOT_RUBBER = ITEMS.register("ingot_rubber",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P4.2 ingot batch; CE type ItemCustomLore, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> INGOT_SATURNITE = ITEMS.register("ingot_saturnite",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P4.2 ingot batch; CE type ItemCustomLore, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> INGOT_SCHRABIDATE = ITEMS.register("ingot_schrabidate",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P4.2 ingot batch; CE type ItemCustomLore, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> INGOT_SCHRABIDIUM = ITEMS.register("ingot_schrabidium",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P4.2 ingot batch; CE type ItemCustomLore, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> INGOT_SCHRABIDIUM_FUEL = ITEMS.register("ingot_schrabidium_fuel",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P4.2 ingot batch; CE type ItemSchraranium, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> INGOT_SCHRARANIUM = ITEMS.register("ingot_schraranium",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P4.2 ingot batch; CE type ItemBakedBase, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> INGOT_SILICON = ITEMS.register("ingot_silicon",
            () -> new ItemBase(new Item.Properties()));
    public static final DeferredItem<Item> INGOT_NICKEL = ITEMS.register("ingot_nickel",
            () -> new ItemBase(new Item.Properties()));
    public static final DeferredItem<Item> INGOT_CHROMIUM = ITEMS.register("ingot_chromium",
            () -> new ItemBase(new Item.Properties()));
    public static final DeferredItem<Item> INGOT_MOLYBDENUM = ITEMS.register("ingot_molybdenum",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P4.2 ingot batch; CE type ItemCustomLore, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> INGOT_SOLINIUM = ITEMS.register("ingot_solinium",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P4.2 ingot batch; CE type ItemBakedBase, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> INGOT_SR90 = ITEMS.register("ingot_sr90",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P4.2 ingot batch; CE type ItemCustomLore, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> INGOT_STARMETAL = ITEMS.register("ingot_starmetal",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P4.2 ingot batch; CE type ItemBakedBase, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> INGOT_STEEL = ITEMS.register("ingot_steel",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P4.2 ingot batch; CE type ItemCustomLore, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> INGOT_TANTALIUM = ITEMS.register("ingot_tantalium",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P4.2 ingot batch; CE type ItemBakedBase, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> INGOT_TCALLOY = ITEMS.register("ingot_tcalloy",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P4.2 ingot batch; CE type ItemBakedBase, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> INGOT_TECHNETIUM = ITEMS.register("ingot_technetium",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P4.2 ingot batch; CE type ItemCustomLore, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> INGOT_TENNESSINE = ITEMS.register("ingot_tennessine",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P4.2 ingot batch; CE type ItemCustomLore, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> INGOT_TH232 = ITEMS.register("ingot_th232",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P4.2 ingot batch; CE type ItemCustomLore, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> INGOT_THORIUM_FUEL = ITEMS.register("ingot_thorium_fuel",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P4.2 ingot batch; CE type ItemBakedBase, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> INGOT_TITANIUM = ITEMS.register("ingot_titanium",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P4.2 ingot batch; CE type ItemBakedBase, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> INGOT_TUNGSTEN = ITEMS.register("ingot_tungsten",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P4.2 ingot batch; CE type ItemBakedBase, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> INGOT_TUNGSTEN_CARBIDE = ITEMS.register("ingot_tungsten_carbide",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P4.2 ingot batch; CE type ItemCustomLore, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> INGOT_U233 = ITEMS.register("ingot_u233",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P4.2 ingot batch; CE type ItemCustomLore, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> INGOT_U235 = ITEMS.register("ingot_u235",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P4.2 ingot batch; CE type ItemCustomLore, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> INGOT_U238 = ITEMS.register("ingot_u238",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P4.2 ingot batch; CE type ItemCustomLore, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> INGOT_UNOBTAINIUM = ITEMS.register("ingot_unobtainium",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P4.2 ingot batch; CE type ItemCustomLore, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> INGOT_URANIUM_FUEL = ITEMS.register("ingot_uranium_fuel",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P4.2 ingot batch; CE type ItemCustomLore, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> INGOT_VERTICIUM = ITEMS.register("ingot_verticium",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P4.2 ingot batch; CE type ItemBakedBase, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> INGOT_WEAPONSTEEL = ITEMS.register("ingot_weaponsteel",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P4.2 ingot batch; CE type ItemCustomLore, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> INGOT_WEIDANIUM = ITEMS.register("ingot_weidanium",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P4.2 ingot batch; CE type ItemBakedBase, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> INGOT_ZIRCONIUM = ITEMS.register("ingot_zirconium",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P3.2 registerOres batch; CE type ItemBlowtorch, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> ACETYLENE_TORCH = ITEMS.register("acetylene_torch",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P3.2 registerOres batch; CE type ItemFuel, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> BALL_RESIN = ITEMS.register("ball_resin",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P3.2 registerOres batch; CE type ItemBDCL, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> BDCL = ITEMS.register("bdcl",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P3.2 registerOres batch; CE type ItemCustomLore, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> BILLET_ACTINIUM = ITEMS.register("billet_actinium",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P3.2 registerOres batch; CE type ItemCustomLore, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> BILLET_AM_MIX = ITEMS.register("billet_am_mix",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P3.2 registerOres batch; CE type ItemCustomLore, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> BILLET_AM241 = ITEMS.register("billet_am241",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P3.2 registerOres batch; CE type ItemCustomLore, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> BILLET_AM242 = ITEMS.register("billet_am242",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P3.2 registerOres batch; CE type ItemCustomLore, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> BILLET_AU198 = ITEMS.register("billet_au198",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P3.2 registerOres batch; CE type ItemCustomLore, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> BILLET_AUSTRALIUM = ITEMS.register("billet_australium",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P3.2 registerOres batch; CE type ItemBakedBase, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> BILLET_BERYLLIUM = ITEMS.register("billet_beryllium",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P3.2 registerOres batch; CE type ItemBakedBase, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> BILLET_BISMUTH = ITEMS.register("billet_bismuth",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P3.2 registerOres batch; CE type ItemCustomLore, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> BILLET_CO60 = ITEMS.register("billet_co60",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P3.2 registerOres batch; CE type ItemBakedBase, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> BILLET_COBALT = ITEMS.register("billet_cobalt",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P3.2 registerOres batch; CE type ItemCustomLore, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> BILLET_GH336 = ITEMS.register("billet_gh336",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P3.2 registerOres batch; CE type ItemCustomLore, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> BILLET_NEPTUNIUM = ITEMS.register("billet_neptunium",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P3.2 registerOres batch; CE type ItemCustomLore, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> BILLET_PB209 = ITEMS.register("billet_pb209",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P3.2 registerOres batch; CE type ItemCustomLore, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> BILLET_PLUTONIUM = ITEMS.register("billet_plutonium",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P3.2 registerOres batch; CE type ItemCustomLore, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> BILLET_POLONIUM = ITEMS.register("billet_polonium",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P3.2 registerOres batch; CE type ItemCustomLore, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> BILLET_PU_MIX = ITEMS.register("billet_pu_mix",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P3.2 registerOres batch; CE type ItemCustomLore, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> BILLET_PU238 = ITEMS.register("billet_pu238",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P3.2 registerOres batch; CE type ItemCustomLore, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> BILLET_PU239 = ITEMS.register("billet_pu239",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P3.2 registerOres batch; CE type ItemCustomLore, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> BILLET_PU240 = ITEMS.register("billet_pu240",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P3.2 registerOres batch; CE type ItemCustomLore, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> BILLET_PU241 = ITEMS.register("billet_pu241",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P3.2 registerOres batch; CE type ItemCustomLore, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> BILLET_RA226 = ITEMS.register("billet_ra226",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P3.2 registerOres batch; CE type ItemCustomLore, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> BILLET_SCHRABIDIUM = ITEMS.register("billet_schrabidium",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P3.2 registerOres batch; CE type ItemCustomLore, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> BILLET_SILICON = ITEMS.register("billet_silicon",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P3.2 registerOres batch; CE type ItemCustomLore, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> BILLET_SOLINIUM = ITEMS.register("billet_solinium",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P3.2 registerOres batch; CE type ItemCustomLore, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> BILLET_SR90 = ITEMS.register("billet_sr90",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P3.2 registerOres batch; CE type ItemCustomLore, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> BILLET_TECHNETIUM = ITEMS.register("billet_technetium",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P3.2 registerOres batch; CE type ItemCustomLore, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> BILLET_TH232 = ITEMS.register("billet_th232",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P3.2 registerOres batch; CE type ItemCustomLore, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> BILLET_U233 = ITEMS.register("billet_u233",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P3.2 registerOres batch; CE type ItemCustomLore, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> BILLET_U235 = ITEMS.register("billet_u235",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P3.2 registerOres batch; CE type ItemCustomLore, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> BILLET_U238 = ITEMS.register("billet_u238",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P3.2 registerOres batch; CE type ItemCustomLore, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> BILLET_URANIUM = ITEMS.register("billet_uranium",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P3.2 registerOres batch; CE type ItemBakedBase, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> BILLET_ZIRCONIUM = ITEMS.register("billet_zirconium",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P3.2 registerOres batch; CE type ItemBlowtorch, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> BLOWTORCH = ITEMS.register("blowtorch",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P3.2 registerOres batch; CE type ItemCraftingDegradation, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> CHEMISTRY_SET = ITEMS.register("chemistry_set",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P3.2 registerOres batch; CE type ItemCraftingDegradation, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> CHEMISTRY_SET_BORON = ITEMS.register("chemistry_set_boron",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P3.2 registerOres batch; CE type ItemCustomLore, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> CRYSTAL_ASBESTOS = ITEMS.register("crystal_asbestos",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P3.2 registerOres batch; CE type ItemCustomLore, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> CRYSTAL_LEAD = ITEMS.register("crystal_lead",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P3.2 registerOres batch; CE type ItemCustomLore, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> CRYSTAL_LITHIUM = ITEMS.register("crystal_lithium",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P3.2 registerOres batch; CE type ItemCustomLore, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> CRYSTAL_OSMIRIDIUM = ITEMS.register("crystal_osmiridium",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P3.2 registerOres batch; CE type ItemCustomLore, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> CRYSTAL_PHOSPHORUS = ITEMS.register("crystal_phosphorus",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P3.2 registerOres batch; CE type ItemCustomLore, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> CRYSTAL_PLUTONIUM = ITEMS.register("crystal_plutonium",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P3.2 registerOres batch; CE type ItemCustomLore, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> CRYSTAL_SCHRABIDIUM = ITEMS.register("crystal_schrabidium",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P3.2 registerOres batch; CE type ItemCustomLore, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> CRYSTAL_SCHRARANIUM = ITEMS.register("crystal_schraranium",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P3.2 registerOres batch; CE type ItemCustomLore, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> CRYSTAL_THORIUM = ITEMS.register("crystal_thorium",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P3.2 registerOres batch; CE type ItemCustomLore, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> CRYSTAL_URANIUM = ITEMS.register("crystal_uranium",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P3.2 registerOres batch; CE type ItemFluidTank, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> FLUID_BARREL_FULL = ITEMS.register("fluid_barrel_full",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P3.2 registerOres batch; CE type ItemFluidTankV2, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> FLUID_BARREL_V2 = ITEMS.register("fluid_barrel_v2",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P3.2 registerOres batch; CE type ItemFluidTank, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> FLUID_TANK_FULL = ITEMS.register("fluid_tank_full",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P3.2 registerOres batch; CE type ItemFluidTank, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> FLUID_TANK_LEAD_FULL = ITEMS.register("fluid_tank_lead_full",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P3.2 registerOres batch; CE type ItemFluidTankV2, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> FLUID_TANK_LEAD_V2 = ITEMS.register("fluid_tank_lead_v2",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P3.2 registerOres batch; CE type ItemFluidTankV2, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> FLUID_TANK_V2 = ITEMS.register("fluid_tank_v2",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P3.2 registerOres batch; CE type ItemCustomLore, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> GEM_SODALITE = ITEMS.register("gem_sodalite",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P3.2 registerOres batch; CE type ItemCustomLore, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> GEM_TANTALIUM = ITEMS.register("gem_tantalium",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P3.2 registerOres batch; CE type ItemFuel, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> LIGNITE = ITEMS.register("lignite",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P3.2 registerOres batch; CE type ItemCustomLore, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> LITHIUM = ITEMS.register("lithium",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P3.2 registerOres batch; CE type ItemBakedBase, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> NUGGET_ACTINIUM = ITEMS.register("nugget_actinium",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P3.2 registerOres batch; CE type ItemCustomLore, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> NUGGET_AM_MIX = ITEMS.register("nugget_am_mix",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P3.2 registerOres batch; CE type ItemCustomLore, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> NUGGET_AM241 = ITEMS.register("nugget_am241",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P3.2 registerOres batch; CE type ItemCustomLore, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> NUGGET_AM242 = ITEMS.register("nugget_am242",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P3.2 registerOres batch; CE type ItemCustomLore, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> NUGGET_ARSENIC = ITEMS.register("nugget_arsenic",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P3.2 registerOres batch; CE type ItemCustomLore, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> NUGGET_AU198 = ITEMS.register("nugget_au198",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P3.2 registerOres batch; CE type ItemCustomLore, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> NUGGET_AUSTRALIUM = ITEMS.register("nugget_australium",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P3.2 registerOres batch; CE type ItemBakedBase, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> NUGGET_BERYLLIUM = ITEMS.register("nugget_beryllium",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P3.2 registerOres batch; CE type ItemBakedBase, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> NUGGET_BISMUTH = ITEMS.register("nugget_bismuth",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P3.2 registerOres batch; CE type ItemCustomLore, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> NUGGET_CO60 = ITEMS.register("nugget_co60",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P3.2 registerOres batch; CE type ItemCustomLore, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> NUGGET_COBALT = ITEMS.register("nugget_cobalt",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P3.2 registerOres batch; CE type ItemCustomLore, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> NUGGET_DESH = ITEMS.register("nugget_desh",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P3.2 registerOres batch; CE type ItemCustomLore, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> NUGGET_DINEUTRONIUM = ITEMS.register("nugget_dineutronium",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P3.2 registerOres batch; CE type ItemCustomLore, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> NUGGET_EUPHEMIUM = ITEMS.register("nugget_euphemium",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P3.2 registerOres batch; CE type ItemCustomLore, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> NUGGET_GH336 = ITEMS.register("nugget_gh336",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P3.2 registerOres batch; CE type ItemCustomLore, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> NUGGET_LEAD = ITEMS.register("nugget_lead",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P3.2 registerOres batch; CE type ItemCustomLore, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> NUGGET_NEPTUNIUM = ITEMS.register("nugget_neptunium",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P3.2 registerOres batch; CE type ItemBakedBase, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> NUGGET_NIOBIUM = ITEMS.register("nugget_niobium",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P3.2 registerOres batch; CE type ItemCustomLore, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> NUGGET_OSMIRIDIUM = ITEMS.register("nugget_osmiridium",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P3.2 registerOres batch; CE type ItemCustomLore, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> NUGGET_PB209 = ITEMS.register("nugget_pb209",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P3.2 registerOres batch; CE type ItemCustomLore, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> NUGGET_PLUTONIUM = ITEMS.register("nugget_plutonium",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P3.2 registerOres batch; CE type ItemCustomLore, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> NUGGET_POLONIUM = ITEMS.register("nugget_polonium",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P3.2 registerOres batch; CE type ItemCustomLore, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> NUGGET_PU_MIX = ITEMS.register("nugget_pu_mix",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P3.2 registerOres batch; CE type ItemCustomLore, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> NUGGET_PU238 = ITEMS.register("nugget_pu238",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P3.2 registerOres batch; CE type ItemCustomLore, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> NUGGET_PU239 = ITEMS.register("nugget_pu239",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P3.2 registerOres batch; CE type ItemCustomLore, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> NUGGET_PU240 = ITEMS.register("nugget_pu240",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P3.2 registerOres batch; CE type ItemCustomLore, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> NUGGET_PU241 = ITEMS.register("nugget_pu241",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P3.2 registerOres batch; CE type ItemCustomLore, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> NUGGET_RA226 = ITEMS.register("nugget_ra226",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P3.2 registerOres batch; CE type ItemCustomLore, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> NUGGET_SCHRABIDIUM = ITEMS.register("nugget_schrabidium",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P3.2 registerOres batch; CE type ItemBakedBase, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> NUGGET_SILICON = ITEMS.register("nugget_silicon",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P3.2 registerOres batch; CE type ItemCustomLore, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> NUGGET_SOLINIUM = ITEMS.register("nugget_solinium",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P3.2 registerOres batch; CE type ItemCustomLore, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> NUGGET_SR90 = ITEMS.register("nugget_sr90",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P3.2 registerOres batch; CE type ItemCustomLore, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> NUGGET_TANTALIUM = ITEMS.register("nugget_tantalium",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P3.2 registerOres batch; CE type ItemCustomLore, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> NUGGET_TECHNETIUM = ITEMS.register("nugget_technetium",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P3.2 registerOres batch; CE type ItemCustomLore, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> NUGGET_TH232 = ITEMS.register("nugget_th232",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P3.2 registerOres batch; CE type ItemCustomLore, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> NUGGET_U233 = ITEMS.register("nugget_u233",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P3.2 registerOres batch; CE type ItemCustomLore, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> NUGGET_U235 = ITEMS.register("nugget_u235",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P3.2 registerOres batch; CE type ItemCustomLore, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> NUGGET_U238 = ITEMS.register("nugget_u238",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P3.2 registerOres batch; CE type ItemCustomLore, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> NUGGET_URANIUM = ITEMS.register("nugget_uranium",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P3.2 registerOres batch; CE type ItemCustomLore, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> NUGGET_ZIRCONIUM = ITEMS.register("nugget_zirconium",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P3.2 registerOres batch; CE type ItemCustomLore, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> PLATE_LEAD = ITEMS.register("plate_lead",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P3.2 registerOres batch; CE type ItemCustomLore, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> PLATE_SCHRABIDIUM = ITEMS.register("plate_schrabidium",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P3.2 registerOres batch; CE type ItemCustomLore, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> POWDER_ACTINIUM = ITEMS.register("powder_actinium",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P3.2 registerOres batch; CE type ItemCustomLore, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> POWDER_ASTATINE = ITEMS.register("powder_astatine",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P3.2 registerOres batch; CE type ItemCustomLore, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> POWDER_AT209 = ITEMS.register("powder_at209",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P3.2 registerOres batch; CE type ItemCustomLore, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> POWDER_AT209_TINY = ITEMS.register("powder_at209_tiny",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P3.2 registerOres batch; CE type ItemCustomLore, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> POWDER_AU198 = ITEMS.register("powder_au198",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P3.2 registerOres batch; CE type ItemCustomLore, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> POWDER_AU198_TINY = ITEMS.register("powder_au198_tiny",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P3.2 registerOres batch; CE type ItemCustomLore, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> POWDER_AUSTRALIUM = ITEMS.register("powder_australium",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P3.2 registerOres batch; CE type ItemCustomLore, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> POWDER_BAKELITE = ITEMS.register("powder_bakelite",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P3.2 registerOres batch; CE type ItemCustomLore, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> POWDER_BORON = ITEMS.register("powder_boron",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P3.2 registerOres batch; CE type ItemCustomLore, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> POWDER_BROMINE = ITEMS.register("powder_bromine",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P3.2 registerOres batch; CE type ItemCustomLore, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> POWDER_CERIUM = ITEMS.register("powder_cerium",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P3.2 registerOres batch; CE type ItemCustomLore, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> POWDER_CERIUM_TINY = ITEMS.register("powder_cerium_tiny",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P3.2 registerOres batch; CE type ItemCustomLore, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> POWDER_CO60 = ITEMS.register("powder_co60",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P3.2 registerOres batch; CE type ItemCustomLore, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> POWDER_CO60_TINY = ITEMS.register("powder_co60_tiny",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P3.2 registerOres batch; CE type ItemFuel, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> POWDER_COAL = ITEMS.register("powder_coal",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P3.2 registerOres batch; CE type ItemFuel, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> POWDER_COAL_TINY = ITEMS.register("powder_coal_tiny",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P3.2 registerOres batch; CE type ItemCustomLore, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> POWDER_COBALT = ITEMS.register("powder_cobalt",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P3.2 registerOres batch; CE type ItemCustomLore, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> POWDER_COBALT_TINY = ITEMS.register("powder_cobalt_tiny",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P3.2 registerOres batch; CE type ItemCustomLore, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> POWDER_COLTAN_ORE = ITEMS.register("powder_coltan_ore",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P3.2 registerOres batch; CE type ItemCustomLore, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> POWDER_CS137 = ITEMS.register("powder_cs137",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P3.2 registerOres batch; CE type ItemCustomLore, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> POWDER_CS137_TINY = ITEMS.register("powder_cs137_tiny",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P3.2 registerOres batch; CE type ItemCustomLore, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> POWDER_DINEUTRONIUM = ITEMS.register("powder_dineutronium",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P3.2 registerOres batch; CE type ItemCustomLore, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> POWDER_DURA_STEEL = ITEMS.register("powder_dura_steel",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P3.2 registerOres batch; CE type ItemCustomLore, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> POWDER_EUPHEMIUM = ITEMS.register("powder_euphemium",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P3.2 registerOres batch; CE type ItemFuel, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> POWDER_FIRE = ITEMS.register("powder_fire",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P3.2 registerOres batch; CE type ItemCustomLore, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> POWDER_I131 = ITEMS.register("powder_i131",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P3.2 registerOres batch; CE type ItemCustomLore, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> POWDER_I131_TINY = ITEMS.register("powder_i131_tiny",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P3.2 registerOres batch; CE type ItemCustomLore, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> POWDER_IODINE = ITEMS.register("powder_iodine",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P3.2 registerOres batch; CE type ItemCustomLore, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> POWDER_IODINE_TINY = ITEMS.register("powder_iodine_tiny",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P3.2 registerOres batch; CE type ItemCustomLore, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> POWDER_LANTHANIUM = ITEMS.register("powder_lanthanium",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P3.2 registerOres batch; CE type ItemCustomLore, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> POWDER_LEAD = ITEMS.register("powder_lead",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P3.2 registerOres batch; CE type ItemFuel, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> POWDER_LIGNITE = ITEMS.register("powder_lignite",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P3.2 registerOres batch; CE type ItemCustomLore, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> POWDER_LITHIUM = ITEMS.register("powder_lithium",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P3.2 registerOres batch; CE type ItemCustomLore, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> POWDER_LITHIUM_TINY = ITEMS.register("powder_lithium_tiny",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P3.2 registerOres batch; CE type ItemCustomLore, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> POWDER_MAGNETIZED_TUNGSTEN = ITEMS.register("powder_magnetized_tungsten",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P3.2 registerOres batch; CE type ItemCustomLore, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> POWDER_NEODYMIUM = ITEMS.register("powder_neodymium",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P3.2 registerOres batch; CE type ItemCustomLore, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> POWDER_NEODYMIUM_TINY = ITEMS.register("powder_neodymium_tiny",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P3.2 registerOres batch; CE type ItemCustomLore, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> POWDER_NEPTUNIUM = ITEMS.register("powder_neptunium",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P3.2 registerOres batch; CE type ItemCustomLore, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> POWDER_NIOBIUM = ITEMS.register("powder_niobium",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P3.2 registerOres batch; CE type ItemCustomLore, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> POWDER_NIOBIUM_TINY = ITEMS.register("powder_niobium_tiny",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P3.2 registerOres batch; CE type ItemCustomLore, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> POWDER_PB209 = ITEMS.register("powder_pb209",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P3.2 registerOres batch; CE type ItemCustomLore, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> POWDER_PB209_TINY = ITEMS.register("powder_pb209_tiny",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P3.2 registerOres batch; CE type ItemCustomLore, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> POWDER_PLUTONIUM = ITEMS.register("powder_plutonium",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P3.2 registerOres batch; CE type ItemCustomLore, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> POWDER_POLONIUM = ITEMS.register("powder_polonium",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P3.2 registerOres batch; CE type ItemCustomLore, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> POWDER_POLYMER = ITEMS.register("powder_polymer",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P3.2 registerOres batch; CE type ItemCustomLore, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> POWDER_RA226 = ITEMS.register("powder_ra226",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P3.2 registerOres batch; CE type ItemCustomLore, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> POWDER_SCHRABIDATE = ITEMS.register("powder_schrabidate",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P3.2 registerOres batch; CE type ItemCustomLore, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> POWDER_SCHRABIDIUM = ITEMS.register("powder_schrabidium",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P3.2 registerOres batch; CE type ItemCustomLore, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> POWDER_SR90 = ITEMS.register("powder_sr90",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P3.2 registerOres batch; CE type ItemCustomLore, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> POWDER_SR90_TINY = ITEMS.register("powder_sr90_tiny",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P3.2 registerOres batch; CE type ItemCustomLore, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> POWDER_TANTALIUM = ITEMS.register("powder_tantalium",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P3.2 registerOres batch; CE type ItemCustomLore, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> POWDER_TCALLOY = ITEMS.register("powder_tcalloy",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P3.2 registerOres batch; CE type ItemCustomLore, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> POWDER_TENNESSINE = ITEMS.register("powder_tennessine",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P3.2 registerOres batch; CE type ItemCustomLore, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> POWDER_THORIUM = ITEMS.register("powder_thorium",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P3.2 registerOres batch; CE type ItemCustomLore, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> POWDER_URANIUM = ITEMS.register("powder_uranium",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P3.2 registerOres batch; CE type ItemCustomLore, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> POWDER_XE135 = ITEMS.register("powder_xe135",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (P3.2 registerOres batch; CE type ItemCustomLore, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> POWDER_XE135_TINY = ITEMS.register("powder_xe135_tiny",
            () -> new ItemBase(new Item.Properties()));

    // ===== P5.2 SILEXRecipes 依赖补注册（CE 注册名一致）=====
    // TAB: partsTab (CE: ItemRBMKPellet rbmk_pellet_ueu)
    public static final DeferredItem<ItemRBMKPellet> RBMK_PELLET_UEU = ITEMS.register("rbmk_pellet_ueu",
            () -> new ItemRBMKPellet(new Item.Properties()));
    // TAB: partsTab (CE: ItemRBMKPellet rbmk_pellet_meu)
    public static final DeferredItem<ItemRBMKPellet> RBMK_PELLET_MEU = ITEMS.register("rbmk_pellet_meu",
            () -> new ItemRBMKPellet(new Item.Properties()));
    // TAB: partsTab (CE: ItemRBMKPellet rbmk_pellet_heu233)
    public static final DeferredItem<ItemRBMKPellet> RBMK_PELLET_HEU233 = ITEMS.register("rbmk_pellet_heu233",
            () -> new ItemRBMKPellet(new Item.Properties()));
    // TAB: partsTab (CE: ItemRBMKPellet rbmk_pellet_heu235)
    public static final DeferredItem<ItemRBMKPellet> RBMK_PELLET_HEU235 = ITEMS.register("rbmk_pellet_heu235",
            () -> new ItemRBMKPellet(new Item.Properties()));
    // TAB: partsTab (CE: ItemRBMKPellet rbmk_pellet_uzh)
    public static final DeferredItem<ItemRBMKPellet> RBMK_PELLET_UZH = ITEMS.register("rbmk_pellet_uzh",
            () -> new ItemRBMKPellet(new Item.Properties()));
    // TAB: partsTab (CE: ItemRBMKPellet rbmk_pellet_thmeu)
    public static final DeferredItem<ItemRBMKPellet> RBMK_PELLET_THMEU = ITEMS.register("rbmk_pellet_thmeu",
            () -> new ItemRBMKPellet(new Item.Properties()));
    // TAB: partsTab (CE: ItemRBMKPellet rbmk_pellet_lep)
    public static final DeferredItem<ItemRBMKPellet> RBMK_PELLET_LEP = ITEMS.register("rbmk_pellet_lep",
            () -> new ItemRBMKPellet(new Item.Properties()));
    // TAB: partsTab (CE: ItemRBMKPellet rbmk_pellet_mep)
    public static final DeferredItem<ItemRBMKPellet> RBMK_PELLET_MEP = ITEMS.register("rbmk_pellet_mep",
            () -> new ItemRBMKPellet(new Item.Properties()));
    // TAB: partsTab (CE: ItemRBMKPellet rbmk_pellet_hep239)
    public static final DeferredItem<ItemRBMKPellet> RBMK_PELLET_HEP239 = ITEMS.register("rbmk_pellet_hep239",
            () -> new ItemRBMKPellet(new Item.Properties()));
    // TAB: partsTab (CE: ItemRBMKPellet rbmk_pellet_hep241)
    public static final DeferredItem<ItemRBMKPellet> RBMK_PELLET_HEP241 = ITEMS.register("rbmk_pellet_hep241",
            () -> new ItemRBMKPellet(new Item.Properties()));
    // TAB: partsTab (CE: ItemRBMKPellet rbmk_pellet_men)
    public static final DeferredItem<ItemRBMKPellet> RBMK_PELLET_MEN = ITEMS.register("rbmk_pellet_men",
            () -> new ItemRBMKPellet(new Item.Properties()));
    // TAB: partsTab (CE: ItemRBMKPellet rbmk_pellet_hen)
    public static final DeferredItem<ItemRBMKPellet> RBMK_PELLET_HEN = ITEMS.register("rbmk_pellet_hen",
            () -> new ItemRBMKPellet(new Item.Properties()));
    // TAB: partsTab (CE: ItemRBMKPellet rbmk_pellet_mox)
    public static final DeferredItem<ItemRBMKPellet> RBMK_PELLET_MOX = ITEMS.register("rbmk_pellet_mox",
            () -> new ItemRBMKPellet(new Item.Properties()));
    // TAB: partsTab (CE: ItemRBMKPellet rbmk_pellet_leaus)
    public static final DeferredItem<ItemRBMKPellet> RBMK_PELLET_LEAUS = ITEMS.register("rbmk_pellet_leaus",
            () -> new ItemRBMKPellet(new Item.Properties()));
    // TAB: partsTab (CE: ItemRBMKPellet rbmk_pellet_heaus)
    public static final DeferredItem<ItemRBMKPellet> RBMK_PELLET_HEAUS = ITEMS.register("rbmk_pellet_heaus",
            () -> new ItemRBMKPellet(new Item.Properties()));
    // TAB: partsTab (CE: ItemRBMKPellet rbmk_pellet_les)
    public static final DeferredItem<ItemRBMKPellet> RBMK_PELLET_LES = ITEMS.register("rbmk_pellet_les",
            () -> new ItemRBMKPellet(new Item.Properties()));
    // TAB: partsTab (CE: ItemRBMKPellet rbmk_pellet_mes)
    public static final DeferredItem<ItemRBMKPellet> RBMK_PELLET_MES = ITEMS.register("rbmk_pellet_mes",
            () -> new ItemRBMKPellet(new Item.Properties()));
    // TAB: partsTab (CE: ItemRBMKPellet rbmk_pellet_hes)
    public static final DeferredItem<ItemRBMKPellet> RBMK_PELLET_HES = ITEMS.register("rbmk_pellet_hes",
            () -> new ItemRBMKPellet(new Item.Properties()));
    // TAB: partsTab (CE: ItemRBMKPellet rbmk_pellet_balefire)
    public static final DeferredItem<ItemRBMKPellet> RBMK_PELLET_BALEFIRE = ITEMS.register("rbmk_pellet_balefire",
            () -> new ItemRBMKPellet(new Item.Properties()));
    // TAB: partsTab (CE: ItemRBMKPellet rbmk_pellet_balefire_gold)
    public static final DeferredItem<ItemRBMKPellet> RBMK_PELLET_BALEFIRE_GOLD = ITEMS.register("rbmk_pellet_balefire_gold",
            () -> new ItemRBMKPellet(new Item.Properties()));
    // TAB: partsTab (CE: ItemRBMKPellet rbmk_pellet_flashlead)
    public static final DeferredItem<ItemRBMKPellet> RBMK_PELLET_FLASHLEAD = ITEMS.register("rbmk_pellet_flashlead",
            () -> new ItemRBMKPellet(new Item.Properties()));
    // TAB: partsTab (CE: ItemRBMKPellet rbmk_pellet_po210be)
    public static final DeferredItem<ItemRBMKPellet> RBMK_PELLET_PO210BE = ITEMS.register("rbmk_pellet_po210be",
            () -> new ItemRBMKPellet(new Item.Properties()));
    // TAB: partsTab (CE: ItemRBMKPellet rbmk_pellet_pu238be)
    public static final DeferredItem<ItemRBMKPellet> RBMK_PELLET_PU238BE = ITEMS.register("rbmk_pellet_pu238be",
            () -> new ItemRBMKPellet(new Item.Properties()));
    // TAB: partsTab (CE: ItemRBMKPellet rbmk_pellet_ra226be)
    public static final DeferredItem<ItemRBMKPellet> RBMK_PELLET_RA226BE = ITEMS.register("rbmk_pellet_ra226be",
            () -> new ItemRBMKPellet(new Item.Properties()));
    // TAB: partsTab (CE: ItemRBMKPellet rbmk_pellet_drx)
    public static final DeferredItem<ItemRBMKPellet> RBMK_PELLET_DRX = ITEMS.register("rbmk_pellet_drx",
            () -> new ItemRBMKPellet(new Item.Properties()));
    // TAB: partsTab (CE: ItemRBMKPellet rbmk_pellet_zfb_bismuth)
    public static final DeferredItem<ItemRBMKPellet> RBMK_PELLET_ZFB_BISMUTH = ITEMS.register("rbmk_pellet_zfb_bismuth",
            () -> new ItemRBMKPellet(new Item.Properties()));
    // TAB: partsTab (CE: ItemRBMKPellet rbmk_pellet_zfb_pu241)
    public static final DeferredItem<ItemRBMKPellet> RBMK_PELLET_ZFB_PU241 = ITEMS.register("rbmk_pellet_zfb_pu241",
            () -> new ItemRBMKPellet(new Item.Properties()));
    // TAB: partsTab (CE: ItemRBMKPellet rbmk_pellet_zfb_am_mix)
    public static final DeferredItem<ItemRBMKPellet> RBMK_PELLET_ZFB_AM_MIX = ITEMS.register("rbmk_pellet_zfb_am_mix",
            () -> new ItemRBMKPellet(new Item.Properties()));

    // TAB: null (CE: ItemFluidIcon fluid_icon)
    public static final DeferredItem<ItemFluidIcon> FLUID_ICON = ITEMS.register("fluid_icon",
            () -> new ItemFluidIcon(new Item.Properties()));
    // TAB: partsTab (CE: ItemWasteLong nuclear_waste_long)
    public static final DeferredItem<ItemWasteLong> NUCLEAR_WASTE_LONG = ITEMS.register("nuclear_waste_long",
            () -> new ItemWasteLong(new Item.Properties()));
    // TAB: partsTab (CE: ItemWasteLong nuclear_waste_long_depleted)
    public static final DeferredItem<ItemWasteLong> NUCLEAR_WASTE_LONG_DEPLETED = ITEMS.register("nuclear_waste_long_depleted",
            () -> new ItemWasteLong(new Item.Properties()));
    // TAB: partsTab (CE: ItemWasteLong nuclear_waste_long_tiny)
    public static final DeferredItem<ItemWasteLong> NUCLEAR_WASTE_LONG_TINY = ITEMS.register("nuclear_waste_long_tiny",
            () -> new ItemWasteLong(new Item.Properties()));
    // TAB: partsTab (CE: ItemWasteLong nuclear_waste_long_depleted_tiny)
    public static final DeferredItem<ItemWasteLong> NUCLEAR_WASTE_LONG_DEPLETED_TINY = ITEMS.register("nuclear_waste_long_depleted_tiny",
            () -> new ItemWasteLong(new Item.Properties()));
    // TAB: partsTab (CE: ItemWasteShort nuclear_waste_short)
    public static final DeferredItem<ItemWasteShort> NUCLEAR_WASTE_SHORT = ITEMS.register("nuclear_waste_short",
            () -> new ItemWasteShort(new Item.Properties()));
    // TAB: partsTab (CE: ItemWasteShort nuclear_waste_short_depleted)
    public static final DeferredItem<ItemWasteShort> NUCLEAR_WASTE_SHORT_DEPLETED = ITEMS.register("nuclear_waste_short_depleted",
            () -> new ItemWasteShort(new Item.Properties()));
    // TAB: partsTab (CE: ItemWasteShort nuclear_waste_short_tiny)
    public static final DeferredItem<ItemWasteShort> NUCLEAR_WASTE_SHORT_TINY = ITEMS.register("nuclear_waste_short_tiny",
            () -> new ItemWasteShort(new Item.Properties()));
    // TAB: partsTab (CE: ItemWasteShort nuclear_waste_short_depleted_tiny)
    public static final DeferredItem<ItemWasteShort> NUCLEAR_WASTE_SHORT_DEPLETED_TINY = ITEMS.register("nuclear_waste_short_depleted_tiny",
            () -> new ItemWasteShort(new Item.Properties()));

    // TAB: partsTab (CE: ItemCustomLore nugget_australium_greater, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> NUGGET_AUSTRALIUM_GREATER = ITEMS.register("nugget_australium_greater",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (CE: ItemCustomLore nugget_australium_lesser, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> NUGGET_AUSTRALIUM_LESSER = ITEMS.register("nugget_australium_lesser",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (CE: ItemCustomLore nugget_hes, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> NUGGET_HES = ITEMS.register("nugget_hes",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (CE: ItemCustomLore nugget_les, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> NUGGET_LES = ITEMS.register("nugget_les",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (CE: ItemCustomLore nugget_mercury, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> NUGGET_MERCURY = ITEMS.register("nugget_mercury",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (CE: ItemCustomLore nugget_mox_fuel, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> NUGGET_MOX_FUEL = ITEMS.register("nugget_mox_fuel",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (CE: ItemCustomLore nugget_neptunium_fuel, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> NUGGET_NEPTUNIUM_FUEL = ITEMS.register("nugget_neptunium_fuel",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (CE: ItemCustomLore nugget_plutonium_fuel, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> NUGGET_PLUTONIUM_FUEL = ITEMS.register("nugget_plutonium_fuel",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (CE: ItemCustomLore nugget_schrabidium_fuel, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> NUGGET_SCHRABIDIUM_FUEL = ITEMS.register("nugget_schrabidium_fuel",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (CE: ItemCustomLore nugget_thorium_fuel, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> NUGGET_THORIUM_FUEL = ITEMS.register("nugget_thorium_fuel",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab (CE: ItemCustomLore powder_balefire, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> POWDER_BALEFIRE = ITEMS.register("powder_balefire",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (CE: ItemCustomLore powder_impure_osmiridium, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> POWDER_IMPURE_OSMIRIDIUM = ITEMS.register("powder_impure_osmiridium",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (CE: ItemCustomLore powder_nitan_mix, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> POWDER_NITAN_MIX = ITEMS.register("powder_nitan_mix",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (CE: ItemCustomLore powder_spark_mix, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> POWDER_SPARK_MIX = ITEMS.register("powder_spark_mix",
            () -> new ItemBase(new Item.Properties()));

    // TAB: partsTab (CE: ItemBakedBase dust_tiny, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> DUST_TINY = ITEMS.register("dust_tiny",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (CE: ItemBakedBase falloutitem/fallout, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> FALLOUT = ITEMS.register("falloutitem",
            () -> new ItemBase(new Item.Properties()));
    // TAB: partsTab (CE: ItemCustomLore crystal_trixite, ItemBase placeholder, P8 replacement)
    public static final DeferredItem<Item> CRYSTAL_TRIXITE = ITEMS.register("crystal_trixite",
            () -> new ItemBase(new Item.Properties()));

    // ===== P5.3 Tool items (5 materials x 5 tools = 25 items) =====
    // Materials: uranium, copper, lead, steel, aluminium
    // NeoForge 1.21.1 tool constructors: Tier + Item.Properties only (no attack damage/speed params)

    // --- Uranium tools ---
    public static final DeferredItem<SwordItem> SWORD_URANIUM = ITEMS.register("sword_uranium", () -> new SwordItem(Tiers.IRON, new Item.Properties()));
    public static final DeferredItem<PickaxeItem> PICKAXE_URANIUM = ITEMS.register("pickaxe_uranium", () -> new PickaxeItem(Tiers.IRON, new Item.Properties()));
    public static final DeferredItem<AxeItem> AXE_URANIUM = ITEMS.register("axe_uranium", () -> new AxeItem(Tiers.IRON, new Item.Properties()));
    public static final DeferredItem<ShovelItem> SHOVEL_URANIUM = ITEMS.register("shovel_uranium", () -> new ShovelItem(Tiers.IRON, new Item.Properties()));
    public static final DeferredItem<HoeItem> HOE_URANIUM = ITEMS.register("hoe_uranium", () -> new HoeItem(Tiers.IRON, new Item.Properties()));

    // --- Copper tools ---
    public static final DeferredItem<SwordItem> SWORD_COPPER = ITEMS.register("sword_copper", () -> new SwordItem(Tiers.IRON, new Item.Properties()));
    public static final DeferredItem<PickaxeItem> PICKAXE_COPPER = ITEMS.register("pickaxe_copper", () -> new PickaxeItem(Tiers.IRON, new Item.Properties()));
    public static final DeferredItem<AxeItem> AXE_COPPER = ITEMS.register("axe_copper", () -> new AxeItem(Tiers.IRON, new Item.Properties()));
    public static final DeferredItem<ShovelItem> SHOVEL_COPPER = ITEMS.register("shovel_copper", () -> new ShovelItem(Tiers.IRON, new Item.Properties()));
    public static final DeferredItem<HoeItem> HOE_COPPER = ITEMS.register("hoe_copper", () -> new HoeItem(Tiers.IRON, new Item.Properties()));

    // --- Lead tools ---
    public static final DeferredItem<SwordItem> SWORD_LEAD = ITEMS.register("sword_lead", () -> new SwordItem(Tiers.IRON, new Item.Properties()));
    public static final DeferredItem<PickaxeItem> PICKAXE_LEAD = ITEMS.register("pickaxe_lead", () -> new PickaxeItem(Tiers.IRON, new Item.Properties()));
    public static final DeferredItem<AxeItem> AXE_LEAD = ITEMS.register("axe_lead", () -> new AxeItem(Tiers.IRON, new Item.Properties()));
    public static final DeferredItem<ShovelItem> SHOVEL_LEAD = ITEMS.register("shovel_lead", () -> new ShovelItem(Tiers.IRON, new Item.Properties()));
    public static final DeferredItem<HoeItem> HOE_LEAD = ITEMS.register("hoe_lead", () -> new HoeItem(Tiers.IRON, new Item.Properties()));

    // --- Steel tools ---
    public static final DeferredItem<SwordItem> SWORD_STEEL = ITEMS.register("sword_steel", () -> new SwordItem(Tiers.IRON, new Item.Properties()));
    public static final DeferredItem<PickaxeItem> PICKAXE_STEEL = ITEMS.register("pickaxe_steel", () -> new PickaxeItem(Tiers.IRON, new Item.Properties()));
    public static final DeferredItem<AxeItem> AXE_STEEL = ITEMS.register("axe_steel", () -> new AxeItem(Tiers.IRON, new Item.Properties()));
    public static final DeferredItem<ShovelItem> SHOVEL_STEEL = ITEMS.register("shovel_steel", () -> new ShovelItem(Tiers.IRON, new Item.Properties()));
    public static final DeferredItem<HoeItem> HOE_STEEL = ITEMS.register("hoe_steel", () -> new HoeItem(Tiers.IRON, new Item.Properties()));

    // --- Aluminium tools ---
    public static final DeferredItem<SwordItem> SWORD_ALUMINIUM = ITEMS.register("sword_aluminium", () -> new SwordItem(Tiers.IRON, new Item.Properties()));
    public static final DeferredItem<PickaxeItem> PICKAXE_ALUMINIUM = ITEMS.register("pickaxe_aluminium", () -> new PickaxeItem(Tiers.IRON, new Item.Properties()));
    public static final DeferredItem<AxeItem> AXE_ALUMINIUM = ITEMS.register("axe_aluminium", () -> new AxeItem(Tiers.IRON, new Item.Properties()));
    public static final DeferredItem<ShovelItem> SHOVEL_ALUMINIUM = ITEMS.register("shovel_aluminium", () -> new ShovelItem(Tiers.IRON, new Item.Properties()));
    public static final DeferredItem<HoeItem> HOE_ALUMINIUM = ITEMS.register("hoe_aluminium", () -> new HoeItem(Tiers.IRON, new Item.Properties()));

    // --- Titanium tools ---
    public static final DeferredItem<SwordItem> SWORD_TITANIUM = ITEMS.register("sword_titanium", () -> new SwordItem(Tiers.IRON, new Item.Properties()));
    public static final DeferredItem<PickaxeItem> PICKAXE_TITANIUM = ITEMS.register("pickaxe_titanium", () -> new PickaxeItem(Tiers.IRON, new Item.Properties()));
    public static final DeferredItem<AxeItem> AXE_TITANIUM = ITEMS.register("axe_titanium", () -> new AxeItem(Tiers.IRON, new Item.Properties()));
    public static final DeferredItem<ShovelItem> SHOVEL_TITANIUM = ITEMS.register("shovel_titanium", () -> new ShovelItem(Tiers.IRON, new Item.Properties()));
    public static final DeferredItem<HoeItem> HOE_TITANIUM = ITEMS.register("hoe_titanium", () -> new HoeItem(Tiers.IRON, new Item.Properties()));

    // --- Tungsten tools ---
    public static final DeferredItem<SwordItem> SWORD_TUNGSTEN = ITEMS.register("sword_tungsten", () -> new SwordItem(Tiers.IRON, new Item.Properties()));
    public static final DeferredItem<PickaxeItem> PICKAXE_TUNGSTEN = ITEMS.register("pickaxe_tungsten", () -> new PickaxeItem(Tiers.IRON, new Item.Properties()));
    public static final DeferredItem<AxeItem> AXE_TUNGSTEN = ITEMS.register("axe_tungsten", () -> new AxeItem(Tiers.IRON, new Item.Properties()));
    public static final DeferredItem<ShovelItem> SHOVEL_TUNGSTEN = ITEMS.register("shovel_tungsten", () -> new ShovelItem(Tiers.IRON, new Item.Properties()));
    public static final DeferredItem<HoeItem> HOE_TUNGSTEN = ITEMS.register("hoe_tungsten", () -> new HoeItem(Tiers.IRON, new Item.Properties()));

    // --- Cobalt tools ---
    public static final DeferredItem<SwordItem> SWORD_COBALT = ITEMS.register("sword_cobalt", () -> new SwordItem(Tiers.IRON, new Item.Properties()));
    public static final DeferredItem<PickaxeItem> PICKAXE_COBALT = ITEMS.register("pickaxe_cobalt", () -> new PickaxeItem(Tiers.IRON, new Item.Properties()));
    public static final DeferredItem<AxeItem> AXE_COBALT = ITEMS.register("axe_cobalt", () -> new AxeItem(Tiers.IRON, new Item.Properties()));
    public static final DeferredItem<ShovelItem> SHOVEL_COBALT = ITEMS.register("shovel_cobalt", () -> new ShovelItem(Tiers.IRON, new Item.Properties()));
    public static final DeferredItem<HoeItem> HOE_COBALT = ITEMS.register("hoe_cobalt", () -> new HoeItem(Tiers.IRON, new Item.Properties()));

    // --- Beryllium tools ---
    public static final DeferredItem<SwordItem> SWORD_BERYLLIUM = ITEMS.register("sword_beryllium", () -> new SwordItem(Tiers.IRON, new Item.Properties()));
    public static final DeferredItem<PickaxeItem> PICKAXE_BERYLLIUM = ITEMS.register("pickaxe_beryllium", () -> new PickaxeItem(Tiers.IRON, new Item.Properties()));
    public static final DeferredItem<AxeItem> AXE_BERYLLIUM = ITEMS.register("axe_beryllium", () -> new AxeItem(Tiers.IRON, new Item.Properties()));
    public static final DeferredItem<ShovelItem> SHOVEL_BERYLLIUM = ITEMS.register("shovel_beryllium", () -> new ShovelItem(Tiers.IRON, new Item.Properties()));
    public static final DeferredItem<HoeItem> HOE_BERYLLIUM = ITEMS.register("hoe_beryllium", () -> new HoeItem(Tiers.IRON, new Item.Properties()));

    // --- Zirconium tools ---
    public static final DeferredItem<SwordItem> SWORD_ZIRCONIUM = ITEMS.register("sword_zirconium", () -> new SwordItem(Tiers.IRON, new Item.Properties()));
    public static final DeferredItem<PickaxeItem> PICKAXE_ZIRCONIUM = ITEMS.register("pickaxe_zirconium", () -> new PickaxeItem(Tiers.IRON, new Item.Properties()));
    public static final DeferredItem<AxeItem> AXE_ZIRCONIUM = ITEMS.register("axe_zirconium", () -> new AxeItem(Tiers.IRON, new Item.Properties()));
    public static final DeferredItem<ShovelItem> SHOVEL_ZIRCONIUM = ITEMS.register("shovel_zirconium", () -> new ShovelItem(Tiers.IRON, new Item.Properties()));
    public static final DeferredItem<HoeItem> HOE_ZIRCONIUM = ITEMS.register("hoe_zirconium", () -> new HoeItem(Tiers.IRON, new Item.Properties()));

    // --- Cadmium tools ---
    public static final DeferredItem<SwordItem> SWORD_CADMIUM = ITEMS.register("sword_cadmium", () -> new SwordItem(Tiers.IRON, new Item.Properties()));
    public static final DeferredItem<PickaxeItem> PICKAXE_CADMIUM = ITEMS.register("pickaxe_cadmium", () -> new PickaxeItem(Tiers.IRON, new Item.Properties()));
    public static final DeferredItem<AxeItem> AXE_CADMIUM = ITEMS.register("axe_cadmium", () -> new AxeItem(Tiers.IRON, new Item.Properties()));
    public static final DeferredItem<ShovelItem> SHOVEL_CADMIUM = ITEMS.register("shovel_cadmium", () -> new ShovelItem(Tiers.IRON, new Item.Properties()));
    public static final DeferredItem<HoeItem> HOE_CADMIUM = ITEMS.register("hoe_cadmium", () -> new HoeItem(Tiers.IRON, new Item.Properties()));

    // --- Boron tools ---
    public static final DeferredItem<SwordItem> SWORD_BORON = ITEMS.register("sword_boron", () -> new SwordItem(Tiers.IRON, new Item.Properties()));
    public static final DeferredItem<PickaxeItem> PICKAXE_BORON = ITEMS.register("pickaxe_boron", () -> new PickaxeItem(Tiers.IRON, new Item.Properties()));
    public static final DeferredItem<AxeItem> AXE_BORON = ITEMS.register("axe_boron", () -> new AxeItem(Tiers.IRON, new Item.Properties()));
    public static final DeferredItem<ShovelItem> SHOVEL_BORON = ITEMS.register("shovel_boron", () -> new ShovelItem(Tiers.IRON, new Item.Properties()));
    public static final DeferredItem<HoeItem> HOE_BORON = ITEMS.register("hoe_boron", () -> new HoeItem(Tiers.IRON, new Item.Properties()));

    // --- Neptunium tools ---
    public static final DeferredItem<SwordItem> SWORD_NEPTUNIUM = ITEMS.register("sword_neptunium", () -> new SwordItem(Tiers.IRON, new Item.Properties()));
    public static final DeferredItem<PickaxeItem> PICKAXE_NEPTUNIUM = ITEMS.register("pickaxe_neptunium", () -> new PickaxeItem(Tiers.IRON, new Item.Properties()));
    public static final DeferredItem<AxeItem> AXE_NEPTUNIUM = ITEMS.register("axe_neptunium", () -> new AxeItem(Tiers.IRON, new Item.Properties()));
    public static final DeferredItem<ShovelItem> SHOVEL_NEPTUNIUM = ITEMS.register("shovel_neptunium", () -> new ShovelItem(Tiers.IRON, new Item.Properties()));
    public static final DeferredItem<HoeItem> HOE_NEPTUNIUM = ITEMS.register("hoe_neptunium", () -> new HoeItem(Tiers.IRON, new Item.Properties()));

    // --- Plutonium tools ---
    public static final DeferredItem<SwordItem> SWORD_PLUTONIUM = ITEMS.register("sword_plutonium", () -> new SwordItem(Tiers.IRON, new Item.Properties()));
    public static final DeferredItem<PickaxeItem> PICKAXE_PLUTONIUM = ITEMS.register("pickaxe_plutonium", () -> new PickaxeItem(Tiers.IRON, new Item.Properties()));
    public static final DeferredItem<AxeItem> AXE_PLUTONIUM = ITEMS.register("axe_plutonium", () -> new AxeItem(Tiers.IRON, new Item.Properties()));
    public static final DeferredItem<ShovelItem> SHOVEL_PLUTONIUM = ITEMS.register("shovel_plutonium", () -> new ShovelItem(Tiers.IRON, new Item.Properties()));
    public static final DeferredItem<HoeItem> HOE_PLUTONIUM = ITEMS.register("hoe_plutonium", () -> new HoeItem(Tiers.IRON, new Item.Properties()));

    // --- Polonium tools ---
    public static final DeferredItem<SwordItem> SWORD_POLONIUM = ITEMS.register("sword_polonium", () -> new SwordItem(Tiers.IRON, new Item.Properties()));
    public static final DeferredItem<PickaxeItem> PICKAXE_POLONIUM = ITEMS.register("pickaxe_polonium", () -> new PickaxeItem(Tiers.IRON, new Item.Properties()));
    public static final DeferredItem<AxeItem> AXE_POLONIUM = ITEMS.register("axe_polonium", () -> new AxeItem(Tiers.IRON, new Item.Properties()));
    public static final DeferredItem<ShovelItem> SHOVEL_POLONIUM = ITEMS.register("shovel_polonium", () -> new ShovelItem(Tiers.IRON, new Item.Properties()));
    public static final DeferredItem<HoeItem> HOE_POLONIUM = ITEMS.register("hoe_polonium", () -> new HoeItem(Tiers.IRON, new Item.Properties()));

    // --- U233 tools ---
    public static final DeferredItem<SwordItem> SWORD_U233 = ITEMS.register("sword_u233", () -> new SwordItem(Tiers.IRON, new Item.Properties()));
    public static final DeferredItem<PickaxeItem> PICKAXE_U233 = ITEMS.register("pickaxe_u233", () -> new PickaxeItem(Tiers.IRON, new Item.Properties()));
    public static final DeferredItem<AxeItem> AXE_U233 = ITEMS.register("axe_u233", () -> new AxeItem(Tiers.IRON, new Item.Properties()));
    public static final DeferredItem<ShovelItem> SHOVEL_U233 = ITEMS.register("shovel_u233", () -> new ShovelItem(Tiers.IRON, new Item.Properties()));
    public static final DeferredItem<HoeItem> HOE_U233 = ITEMS.register("hoe_u233", () -> new HoeItem(Tiers.IRON, new Item.Properties()));

    // --- U235 tools ---
    public static final DeferredItem<SwordItem> SWORD_U235 = ITEMS.register("sword_u235", () -> new SwordItem(Tiers.IRON, new Item.Properties()));
    public static final DeferredItem<PickaxeItem> PICKAXE_U235 = ITEMS.register("pickaxe_u235", () -> new PickaxeItem(Tiers.IRON, new Item.Properties()));
    public static final DeferredItem<AxeItem> AXE_U235 = ITEMS.register("axe_u235", () -> new AxeItem(Tiers.IRON, new Item.Properties()));
    public static final DeferredItem<ShovelItem> SHOVEL_U235 = ITEMS.register("shovel_u235", () -> new ShovelItem(Tiers.IRON, new Item.Properties()));
    public static final DeferredItem<HoeItem> HOE_U235 = ITEMS.register("hoe_u235", () -> new HoeItem(Tiers.IRON, new Item.Properties()));

    // --- U238 tools ---
    public static final DeferredItem<SwordItem> SWORD_U238 = ITEMS.register("sword_u238", () -> new SwordItem(Tiers.IRON, new Item.Properties()));
    public static final DeferredItem<PickaxeItem> PICKAXE_U238 = ITEMS.register("pickaxe_u238", () -> new PickaxeItem(Tiers.IRON, new Item.Properties()));
    public static final DeferredItem<AxeItem> AXE_U238 = ITEMS.register("axe_u238", () -> new AxeItem(Tiers.IRON, new Item.Properties()));
    public static final DeferredItem<ShovelItem> SHOVEL_U238 = ITEMS.register("shovel_u238", () -> new ShovelItem(Tiers.IRON, new Item.Properties()));
    public static final DeferredItem<HoeItem> HOE_U238 = ITEMS.register("hoe_u238", () -> new HoeItem(Tiers.IRON, new Item.Properties()));

    // --- Nickel tools ---
    public static final DeferredItem<SwordItem> SWORD_NICKEL = ITEMS.register("sword_nickel", () -> new SwordItem(Tiers.IRON, new Item.Properties()));
    public static final DeferredItem<PickaxeItem> PICKAXE_NICKEL = ITEMS.register("pickaxe_nickel", () -> new PickaxeItem(Tiers.IRON, new Item.Properties()));
    public static final DeferredItem<AxeItem> AXE_NICKEL = ITEMS.register("axe_nickel", () -> new AxeItem(Tiers.IRON, new Item.Properties()));
    public static final DeferredItem<ShovelItem> SHOVEL_NICKEL = ITEMS.register("shovel_nickel", () -> new ShovelItem(Tiers.IRON, new Item.Properties()));
    public static final DeferredItem<HoeItem> HOE_NICKEL = ITEMS.register("hoe_nickel", () -> new HoeItem(Tiers.IRON, new Item.Properties()));

    // --- Chromium tools ---
    public static final DeferredItem<SwordItem> SWORD_CHROMIUM = ITEMS.register("sword_chromium", () -> new SwordItem(Tiers.IRON, new Item.Properties()));
    public static final DeferredItem<PickaxeItem> PICKAXE_CHROMIUM = ITEMS.register("pickaxe_chromium", () -> new PickaxeItem(Tiers.IRON, new Item.Properties()));
    public static final DeferredItem<AxeItem> AXE_CHROMIUM = ITEMS.register("axe_chromium", () -> new AxeItem(Tiers.IRON, new Item.Properties()));
    public static final DeferredItem<ShovelItem> SHOVEL_CHROMIUM = ITEMS.register("shovel_chromium", () -> new ShovelItem(Tiers.IRON, new Item.Properties()));
    public static final DeferredItem<HoeItem> HOE_CHROMIUM = ITEMS.register("hoe_chromium", () -> new HoeItem(Tiers.IRON, new Item.Properties()));

    // --- Molybdenum tools ---
    public static final DeferredItem<SwordItem> SWORD_MOLYBDENUM = ITEMS.register("sword_molybdenum", () -> new SwordItem(Tiers.IRON, new Item.Properties()));
    public static final DeferredItem<PickaxeItem> PICKAXE_MOLYBDENUM = ITEMS.register("pickaxe_molybdenum", () -> new PickaxeItem(Tiers.IRON, new Item.Properties()));
    public static final DeferredItem<AxeItem> AXE_MOLYBDENUM = ITEMS.register("axe_molybdenum", () -> new AxeItem(Tiers.IRON, new Item.Properties()));
    public static final DeferredItem<ShovelItem> SHOVEL_MOLYBDENUM = ITEMS.register("shovel_molybdenum", () -> new ShovelItem(Tiers.IRON, new Item.Properties()));
    public static final DeferredItem<HoeItem> HOE_MOLYBDENUM = ITEMS.register("hoe_molybdenum", () -> new HoeItem(Tiers.IRON, new Item.Properties()));

    // --- Niobium tools ---
    public static final DeferredItem<SwordItem> SWORD_NIOBIUM = ITEMS.register("sword_niobium", () -> new SwordItem(Tiers.IRON, new Item.Properties()));
    public static final DeferredItem<PickaxeItem> PICKAXE_NIOBIUM = ITEMS.register("pickaxe_niobium", () -> new PickaxeItem(Tiers.IRON, new Item.Properties()));
    public static final DeferredItem<AxeItem> AXE_NIOBIUM = ITEMS.register("axe_niobium", () -> new AxeItem(Tiers.IRON, new Item.Properties()));
    public static final DeferredItem<ShovelItem> SHOVEL_NIOBIUM = ITEMS.register("shovel_niobium", () -> new ShovelItem(Tiers.IRON, new Item.Properties()));
    public static final DeferredItem<HoeItem> HOE_NIOBIUM = ITEMS.register("hoe_niobium", () -> new HoeItem(Tiers.IRON, new Item.Properties()));

    // --- Tantalium tools ---
    public static final DeferredItem<SwordItem> SWORD_TANTALIUM = ITEMS.register("sword_tantalium", () -> new SwordItem(Tiers.IRON, new Item.Properties()));
    public static final DeferredItem<PickaxeItem> PICKAXE_TANTALIUM = ITEMS.register("pickaxe_tantalium", () -> new PickaxeItem(Tiers.IRON, new Item.Properties()));
    public static final DeferredItem<AxeItem> AXE_TANTALIUM = ITEMS.register("axe_tantalium", () -> new AxeItem(Tiers.IRON, new Item.Properties()));
    public static final DeferredItem<ShovelItem> SHOVEL_TANTALIUM = ITEMS.register("shovel_tantalium", () -> new ShovelItem(Tiers.IRON, new Item.Properties()));
    public static final DeferredItem<HoeItem> HOE_TANTALIUM = ITEMS.register("hoe_tantalium", () -> new HoeItem(Tiers.IRON, new Item.Properties()));

    // --- Lanthanium tools ---
    public static final DeferredItem<SwordItem> SWORD_LANTHANIUM = ITEMS.register("sword_lanthanium", () -> new SwordItem(Tiers.IRON, new Item.Properties()));
    public static final DeferredItem<PickaxeItem> PICKAXE_LANTHANIUM = ITEMS.register("pickaxe_lanthanium", () -> new PickaxeItem(Tiers.IRON, new Item.Properties()));
    public static final DeferredItem<AxeItem> AXE_LANTHANIUM = ITEMS.register("axe_lanthanium", () -> new AxeItem(Tiers.IRON, new Item.Properties()));
    public static final DeferredItem<ShovelItem> SHOVEL_LANTHANIUM = ITEMS.register("shovel_lanthanium", () -> new ShovelItem(Tiers.IRON, new Item.Properties()));
    public static final DeferredItem<HoeItem> HOE_LANTHANIUM = ITEMS.register("hoe_lanthanium", () -> new HoeItem(Tiers.IRON, new Item.Properties()));

    // --- Silicon tools ---
    public static final DeferredItem<SwordItem> SWORD_SILICON = ITEMS.register("sword_silicon", () -> new SwordItem(Tiers.IRON, new Item.Properties()));
    public static final DeferredItem<PickaxeItem> PICKAXE_SILICON = ITEMS.register("pickaxe_silicon", () -> new PickaxeItem(Tiers.IRON, new Item.Properties()));
    public static final DeferredItem<AxeItem> AXE_SILICON = ITEMS.register("axe_silicon", () -> new AxeItem(Tiers.IRON, new Item.Properties()));
    public static final DeferredItem<ShovelItem> SHOVEL_SILICON = ITEMS.register("shovel_silicon", () -> new ShovelItem(Tiers.IRON, new Item.Properties()));
    public static final DeferredItem<HoeItem> HOE_SILICON = ITEMS.register("hoe_silicon", () -> new HoeItem(Tiers.IRON, new Item.Properties()));


// =====================================================
// Missing Items: CE (1.12.2) items not yet registered
// in the NeoForge version of ModItems.java
// Total missing items: 1127
// Generated automatically - register as simple Item type
// =====================================================

public static final DeferredItem<Item> TURRET_MOB_FILTER = ITEMS.register("turret_mob_filter",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> REDSTONE_SWORD = ITEMS.register("redstone_sword",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> BIG_SWORD = ITEMS.register("big_sword",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> DOSIMETER = ITEMS.register("dosimeter",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> GEIGER_COUNTER = ITEMS.register("geiger_counter",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> DIGAMMA_DIAGNOSTIC = ITEMS.register("digamma_diagnostic",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> LUNG_DIAGNOSTIC = ITEMS.register("lung_diagnostic",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> WRENCH_ARCHINEER = ITEMS.register("wrench_archineer",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> BOLTGUN = ITEMS.register("boltgun",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> REACHER = ITEMS.register("reacher",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> BISMUTH_TOOL = ITEMS.register("bismuth_tool",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> WIRING_RED_COPPER = ITEMS.register("wiring_red_copper",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> SURVEY_SCANNER = ITEMS.register("survey_scanner",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> OIL_DETECTOR = ITEMS.register("oil_detector",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> MIRROR_TOOL = ITEMS.register("mirror_tool",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> RBMK_TOOL = ITEMS.register("rbmk_tool",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> COLTAN_TOOL = ITEMS.register("coltan_tool",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> POWER_NET_TOOL = ITEMS.register("power_net_tool",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> ANALYSIS_TOOL = ITEMS.register("analysis_tool",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> LINKER = ITEMS.register("linker",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> REACTOR_SENSOR = ITEMS.register("reactor_sensor",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> DRONE_LINKER = ITEMS.register("drone_linker",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> RADAR_LINKER = ITEMS.register("radar_linker",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> SETTINGS_TOOL = ITEMS.register("settings_tool",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> RTTY_PAGER = ITEMS.register("rtty_pager",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> POLLUTION_DETECTOR = ITEMS.register("pollution_detector",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> ORE_DENSITY_SCANNER = ITEMS.register("ore_density_scanner",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> REBAR_PLACER = ITEMS.register("rebar_placer",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> SYRINGE_AWESOME = ITEMS.register("syringe_awesome",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> SYRINGE_ANTIDOTE = ITEMS.register("syringe_antidote",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> SYRINGE_POISON = ITEMS.register("syringe_poison",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> SYRINGE_METAL_MEDX = ITEMS.register("syringe_metal_medx",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> SYRINGE_METAL_PSYCHO = ITEMS.register("syringe_metal_psycho",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> SYRINGE_METAL_STIMPAK = ITEMS.register("syringe_metal_stimpak",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> SYRINGE_METAL_SUPER = ITEMS.register("syringe_metal_super",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> SYRINGE_TAINT = ITEMS.register("syringe_taint",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> SYRINGE_MKUNICORN = ITEMS.register("syringe_mkunicorn",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> MED_BAG = ITEMS.register("med_bag",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> RADX = ITEMS.register("radx",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> SIOX = ITEMS.register("siox",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> IV_XP_EMPTY = ITEMS.register("iv_xp_empty",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> PILL_HERBAL = ITEMS.register("pill_herbal",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> XANAX = ITEMS.register("xanax",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> IV_XP = ITEMS.register("iv_xp",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> FMN = ITEMS.register("fmn",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> FIVE_HTP = ITEMS.register("five_htp",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> IV_EMPTY = ITEMS.register("iv_empty",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> PILL_IODINE = ITEMS.register("pill_iodine",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> PLAN_C = ITEMS.register("plan_c",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> PILL_RED = ITEMS.register("pill_red",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> IV_BLOOD = ITEMS.register("iv_blood",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> STEALTH_BOY = ITEMS.register("stealth_boy",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> JETPACK_TANK = ITEMS.register("jetpack_tank",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> RADAWAY = ITEMS.register("radaway",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> GUN_KIT_1 = ITEMS.register("gun_kit_1",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> GUN_KIT_2 = ITEMS.register("gun_kit_2",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> RADAWAY_STRONG = ITEMS.register("radaway_strong",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> EUPHEMIUM_KIT = ITEMS.register("euphemium_kit",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> CBT_DEVICE = ITEMS.register("cbt_device",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> RADAWAY_FLUSH = ITEMS.register("radaway_flush",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> GAS_MASK_FILTER_RAG = ITEMS.register("gas_mask_filter_rag",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> GAS_MASK_FILTER_PISS = ITEMS.register("gas_mask_filter_piss",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> GAS_MASK_FILTER_MONO = ITEMS.register("gas_mask_filter_mono",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> GAS_MASK_FILTER = ITEMS.register("gas_mask_filter",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> GAS_MASK_FILTER_COMBO = ITEMS.register("gas_mask_filter_combo",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> ATTACHMENT_MASK = ITEMS.register("attachment_mask",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> ATTACHMENT_MASK_MONO = ITEMS.register("attachment_mask_mono",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> CIGARETTE = ITEMS.register("cigarette",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> CRACKPIPE = ITEMS.register("crackpipe",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> BACK_TESLA = ITEMS.register("back_tesla",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> PADS_RUBBER = ITEMS.register("pads_rubber",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> PADS_SLIME = ITEMS.register("pads_slime",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> PADS_STATIC = ITEMS.register("pads_static",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> CLADDING_PAINT = ITEMS.register("cladding_paint",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> CLADDING_RUBBER = ITEMS.register("cladding_rubber",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> CLADDING_LEAD = ITEMS.register("cladding_lead",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> CLADDING_DESH = ITEMS.register("cladding_desh",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> CLADDING_GHIORSIUM = ITEMS.register("cladding_ghiorsium",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> CLADDING_IRON = ITEMS.register("cladding_iron",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> CLADDING_OBSIDIAN = ITEMS.register("cladding_obsidian",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> INSERT_KEVLAR = ITEMS.register("insert_kevlar",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> INSERT_SAPI = ITEMS.register("insert_sapi",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> INSERT_ESAPI = ITEMS.register("insert_esapi",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> INSERT_XSAPI = ITEMS.register("insert_xsapi",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> INSERT_STEEL = ITEMS.register("insert_steel",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> INSERT_DU = ITEMS.register("insert_du",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> INSERT_POLONIUM = ITEMS.register("insert_polonium",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> INSERT_GHIORSIUM = ITEMS.register("insert_ghiorsium",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> INSERT_ERA = ITEMS.register("insert_era",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> INSERT_YHARONITE = ITEMS.register("insert_yharonite",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> INSERT_DOXIUM = ITEMS.register("insert_doxium",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> ARMOR_POLISH = ITEMS.register("armor_polish",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> BANDAID = ITEMS.register("bandaid",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> SERUM = ITEMS.register("serum",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> QUARTZ_PLUTONIUM = ITEMS.register("quartz_plutonium",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> MORNING_GLORY = ITEMS.register("morning_glory",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> LODESTONE = ITEMS.register("lodestone",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> HORSESHOE_MAGNET = ITEMS.register("horseshoe_magnet",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> INDUSTRIAL_MAGNET = ITEMS.register("industrial_magnet",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> BATHWATER = ITEMS.register("bathwater",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> BATHWATER_MK2 = ITEMS.register("bathwater_mk2",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> SPIDER_MILK = ITEMS.register("spider_milk",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> INK = ITEMS.register("ink",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> HEART_PIECE = ITEMS.register("heart_piece",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> HEART_CONTAINER = ITEMS.register("heart_container",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> HEART_BOOSTER = ITEMS.register("heart_booster",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> HEART_FAB = ITEMS.register("heart_fab",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> BLACK_DIAMOND = ITEMS.register("black_diamond",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> WD40 = ITEMS.register("wd40",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> SCRUMPY = ITEMS.register("scrumpy",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> WILD_P = ITEMS.register("wild_p",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> FABSOLS_VODKA = ITEMS.register("fabsols_vodka",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> SHACKLES = ITEMS.register("shackles",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> INJECTOR_5HTP = ITEMS.register("injector_5htp",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> INJECTOR_KNIFE = ITEMS.register("injector_knife",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> MEDAL_LIQUIDATOR = ITEMS.register("medal_liquidator",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> BOTTLED_CLOUD = ITEMS.register("bottled_cloud",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> SERVO_SET = ITEMS.register("servo_set",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> SERVO_SET_DESH = ITEMS.register("servo_set_desh",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> V1 = ITEMS.register("v1",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> NEUTRINO_LENS = ITEMS.register("neutrino_lens",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> GAS_TESTER = ITEMS.register("gas_tester",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> DEFUSER_GOLD = ITEMS.register("defuser_gold",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> BALLISTIC_GAUNTLET = ITEMS.register("ballistic_gauntlet",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> NIGHT_VISION = ITEMS.register("night_vision",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> CARD_AOS = ITEMS.register("card_aos",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> CARD_QOS = ITEMS.register("card_qos",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> PROTECTION_CHARM = ITEMS.register("protection_charm",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> METEOR_CHARM = ITEMS.register("meteor_charm",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> STAMP_STONE_FLAT = ITEMS.register("stamp_stone_flat",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> STAMP_STONE_PLATE = ITEMS.register("stamp_stone_plate",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> STAMP_STONE_WIRE = ITEMS.register("stamp_stone_wire",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> STAMP_STONE_CIRCUIT = ITEMS.register("stamp_stone_circuit",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> STAMP_IRON_FLAT = ITEMS.register("stamp_iron_flat",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> STAMP_IRON_PLATE = ITEMS.register("stamp_iron_plate",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> STAMP_IRON_WIRE = ITEMS.register("stamp_iron_wire",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> STAMP_IRON_CIRCUIT = ITEMS.register("stamp_iron_circuit",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> STAMP_STEEL_FLAT = ITEMS.register("stamp_steel_flat",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> STAMP_STEEL_PLATE = ITEMS.register("stamp_steel_plate",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> STAMP_STEEL_WIRE = ITEMS.register("stamp_steel_wire",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> STAMP_STEEL_CIRCUIT = ITEMS.register("stamp_steel_circuit",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> STAMP_TITANIUM_FLAT = ITEMS.register("stamp_titanium_flat",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> STAMP_TITANIUM_PLATE = ITEMS.register("stamp_titanium_plate",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> STAMP_TITANIUM_WIRE = ITEMS.register("stamp_titanium_wire",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> STAMP_TITANIUM_CIRCUIT = ITEMS.register("stamp_titanium_circuit",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> STAMP_OBSIDIAN_FLAT = ITEMS.register("stamp_obsidian_flat",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> STAMP_OBSIDIAN_PLATE = ITEMS.register("stamp_obsidian_plate",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> STAMP_OBSIDIAN_WIRE = ITEMS.register("stamp_obsidian_wire",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> STAMP_OBSIDIAN_CIRCUIT = ITEMS.register("stamp_obsidian_circuit",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> STAMP_DESH_FLAT = ITEMS.register("stamp_desh_flat",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> STAMP_DESH_PLATE = ITEMS.register("stamp_desh_plate",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> STAMP_DESH_WIRE = ITEMS.register("stamp_desh_wire",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> STAMP_DESH_CIRCUIT = ITEMS.register("stamp_desh_circuit",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> STAMP_DESH_357 = ITEMS.register("stamp_desh_357",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> STAMP_DESH_44 = ITEMS.register("stamp_desh_44",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> STAMP_DESH_9 = ITEMS.register("stamp_desh_9",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> STAMP_DESH_50 = ITEMS.register("stamp_desh_50",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> STAMP_357 = ITEMS.register("stamp_357",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> STAMP_44 = ITEMS.register("stamp_44",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> STAMP_9 = ITEMS.register("stamp_9",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> STAMP_50 = ITEMS.register("stamp_50",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> STAMP_BOOK = ITEMS.register("stamp_book",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> BLADES_STEEL = ITEMS.register("blades_steel",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> BLADES_TITANIUM = ITEMS.register("blades_titanium",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> BLADES_DESH = ITEMS.register("blades_desh",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> FUSE = ITEMS.register("fuse",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> REDCOIL_CAPACITOR = ITEMS.register("redcoil_capacitor",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> EUPHEMIUM_CAPACITOR = ITEMS.register("euphemium_capacitor",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> OVERFUSE = ITEMS.register("overfuse",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> ARC_ELECTRODE = ITEMS.register("arc_electrode",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> ARC_ELECTRODE_BURNT = ITEMS.register("arc_electrode_burnt",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> PISTON_SET = ITEMS.register("piston_set",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> DRILLBIT = ITEMS.register("drillbit",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> FUSION_SHIELD_TUNGSTEN = ITEMS.register("fusion_shield_tungsten",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> FUSION_SHIELD_DESH = ITEMS.register("fusion_shield_desh",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> FUSION_SHIELD_CHLOROPHYTE = ITEMS.register("fusion_shield_chlorophyte",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> FUSION_SHIELD_VAPORWAVE = ITEMS.register("fusion_shield_vaporwave",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> BATTERY_SC = ITEMS.register("battery_sc",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> BATTERY_SC_URANIUM = ITEMS.register("battery_sc_uranium",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> BATTERY_SC_TECHNETIUM = ITEMS.register("battery_sc_technetium",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> BATTERY_SC_PLUTONIUM = ITEMS.register("battery_sc_plutonium",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> BATTERY_SC_POLONIUM = ITEMS.register("battery_sc_polonium",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> BATTERY_SC_GOLD = ITEMS.register("battery_sc_gold",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> BATTERY_SC_LEAD = ITEMS.register("battery_sc_lead",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> BATTERY_SC_AMERICIUM = ITEMS.register("battery_sc_americium",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> BATTERY_PACK = ITEMS.register("battery_pack",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> BATTERY_CREATIVE = ITEMS.register("battery_creative",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> BATTERY_GENERIC = ITEMS.register("battery_generic",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> BATTERY_RED_CELL = ITEMS.register("battery_red_cell",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> BATTERY_RED_CELL_6 = ITEMS.register("battery_red_cell_6",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> BATTERY_RED_CELL_24 = ITEMS.register("battery_red_cell_24",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> BATTERY_ADVANCED = ITEMS.register("battery_advanced",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> BATTERY_ADVANCED_CELL = ITEMS.register("battery_advanced_cell",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> BATTERY_ADVANCED_CELL_4 = ITEMS.register("battery_advanced_cell_4",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> BATTERY_ADVANCED_CELL_12 = ITEMS.register("battery_advanced_cell_12",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> BATTERY_LITHIUM = ITEMS.register("battery_lithium",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> BATTERY_LITHIUM_CELL = ITEMS.register("battery_lithium_cell",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> BATTERY_LITHIUM_CELL_3 = ITEMS.register("battery_lithium_cell_3",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> BATTERY_LITHIUM_CELL_6 = ITEMS.register("battery_lithium_cell_6",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> BATTERY_SCHRABIDIUM = ITEMS.register("battery_schrabidium",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> BATTERY_SCHRABIDIUM_CELL = ITEMS.register("battery_schrabidium_cell",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> BATTERY_SCHRABIDIUM_CELL_2 = ITEMS.register("battery_schrabidium_cell_2",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> BATTERY_SCHRABIDIUM_CELL_4 = ITEMS.register("battery_schrabidium_cell_4",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> BATTERY_TRIXITE = ITEMS.register("battery_trixite",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> BATTERY_SPARK = ITEMS.register("battery_spark",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> BATTERY_SPARK_CELL_6 = ITEMS.register("battery_spark_cell_6",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> BATTERY_SPARK_CELL_25 = ITEMS.register("battery_spark_cell_25",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> BATTERY_SPARK_CELL_100 = ITEMS.register("battery_spark_cell_100",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> BATTERY_SPARK_CELL_1000 = ITEMS.register("battery_spark_cell_1000",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> BATTERY_SPARK_CELL_2500 = ITEMS.register("battery_spark_cell_2500",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> BATTERY_SPARK_CELL_10000 = ITEMS.register("battery_spark_cell_10000",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> BATTERY_SPARK_CELL_POWER = ITEMS.register("battery_spark_cell_power",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> BATTERY_POTATO = ITEMS.register("battery_potato",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> BATTERY_POTATOS = ITEMS.register("battery_potatos",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> HEV_BATTERY = ITEMS.register("hev_battery",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> FUSION_CORE = ITEMS.register("fusion_core",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> ENERGY_CORE = ITEMS.register("energy_core",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> LASER_CRYSTAL_CO2 = ITEMS.register("laser_crystal_co2",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> LASER_CRYSTAL_BISMUTH = ITEMS.register("laser_crystal_bismuth",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> LASER_CRYSTAL_CMB = ITEMS.register("laser_crystal_cmb",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> LASER_CRYSTAL_BALE = ITEMS.register("laser_crystal_bale",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> LASER_CRYSTAL_DIGAMMA = ITEMS.register("laser_crystal_digamma",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> UPGRADE_SPEED_1 = ITEMS.register("upgrade_speed_1",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> UPGRADE_SPEED_2 = ITEMS.register("upgrade_speed_2",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> UPGRADE_SPEED_3 = ITEMS.register("upgrade_speed_3",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> UPGRADE_EFFECT_1 = ITEMS.register("upgrade_effect_1",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> UPGRADE_EFFECT_2 = ITEMS.register("upgrade_effect_2",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> UPGRADE_EFFECT_3 = ITEMS.register("upgrade_effect_3",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> UPGRADE_POWER_1 = ITEMS.register("upgrade_power_1",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> UPGRADE_POWER_2 = ITEMS.register("upgrade_power_2",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> UPGRADE_POWER_3 = ITEMS.register("upgrade_power_3",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> UPGRADE_FORTUNE_1 = ITEMS.register("upgrade_fortune_1",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> UPGRADE_FORTUNE_2 = ITEMS.register("upgrade_fortune_2",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> UPGRADE_FORTUNE_3 = ITEMS.register("upgrade_fortune_3",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> UPGRADE_AFTERBURN_1 = ITEMS.register("upgrade_afterburn_1",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> UPGRADE_AFTERBURN_2 = ITEMS.register("upgrade_afterburn_2",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> UPGRADE_AFTERBURN_3 = ITEMS.register("upgrade_afterburn_3",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> UPGRADE_RADIUS = ITEMS.register("upgrade_radius",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> UPGRADE_HEALTH = ITEMS.register("upgrade_health",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> UPGRADE_OVERDRIVE_1 = ITEMS.register("upgrade_overdrive_1",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> UPGRADE_OVERDRIVE_2 = ITEMS.register("upgrade_overdrive_2",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> UPGRADE_OVERDRIVE_3 = ITEMS.register("upgrade_overdrive_3",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> UPGRADE_SMELTER = ITEMS.register("upgrade_smelter",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> UPGRADE_SHREDDER = ITEMS.register("upgrade_shredder",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> UPGRADE_CENTRIFUGE = ITEMS.register("upgrade_centrifuge",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> UPGRADE_CRYSTALLIZER = ITEMS.register("upgrade_crystallizer",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> UPGRADE_NULLIFIER = ITEMS.register("upgrade_nullifier",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> UPGRADE_SCREM = ITEMS.register("upgrade_screm",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> UPGRADE_GC_SPEED = ITEMS.register("upgrade_gc_speed",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> UPGRADE_5G = ITEMS.register("upgrade_5g",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> UPGRADE_EJECTOR_1 = ITEMS.register("upgrade_ejector_1",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> UPGRADE_EJECTOR_2 = ITEMS.register("upgrade_ejector_2",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> UPGRADE_EJECTOR_3 = ITEMS.register("upgrade_ejector_3",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> UPGRADE_STACK_1 = ITEMS.register("upgrade_stack_1",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> UPGRADE_STACK_2 = ITEMS.register("upgrade_stack_2",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> UPGRADE_STACK_3 = ITEMS.register("upgrade_stack_3",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> FUEL_ADDITIVE = ITEMS.register("fuel_additive",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> CANISTER_EMPTY = ITEMS.register("canister_empty",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> CANISTER_FULL = ITEMS.register("canister_fuel",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> CANISTER_NAPALM = ITEMS.register("canister_napalm",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> GAS_FULL = ITEMS.register("gas_full",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> CELL = ITEMS.register("cell",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> CELL_BALEFIRE = ITEMS.register("cell_balefire",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> FLUID_TANK_EMPTY = ITEMS.register("fluid_tank_empty",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> FLUID_TANK_LEAD_EMPTY = ITEMS.register("fluid_tank_lead_empty",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> FLUID_BARREL_EMPTY = ITEMS.register("fluid_barrel_empty",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> FLUID_BARREL_INFINITE = ITEMS.register("fluid_barrel_infinite",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> FLUID_PACK_EMPTY = ITEMS.register("fluid_pack_empty",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> FLUID_PACK_FULL = ITEMS.register("fluid_pack_full",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> PIPETTE = ITEMS.register("pipette",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> PIPETTE_BORON = ITEMS.register("pipette_boron",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> PIPETTE_LABORATORY = ITEMS.register("pipette_laboratory",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> SIPHON = ITEMS.register("siphon",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> INF_WATER = ITEMS.register("inf_water",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> INF_WATER_MK2 = ITEMS.register("inf_water_mk2",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> DISPERSER_CANISTER = ITEMS.register("disperser_canister",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> GLYPHID_GLAND = ITEMS.register("glyphid_gland",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> DETONATOR = ITEMS.register("detonator",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> DETONATOR_MULTI = ITEMS.register("detonator_multi",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> DETONATOR_LASER = ITEMS.register("detonator_laser",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> DETONATOR_DEADMAN = ITEMS.register("detonator_deadman",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> DETONATOR_DE = ITEMS.register("detonator_de",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> IGNITER = ITEMS.register("igniter",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> SPAWN_CHOPPER = ITEMS.register("chopper",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> SPAWN_WORM = ITEMS.register("spawn_worm",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> SPAWN_UFO = ITEMS.register("spawn_ufo",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> BOMB_CALLER = ITEMS.register("bomb_caller",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> CRATE_CALLER = ITEMS.register("crate_caller",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> METEOR_REMOTE = ITEMS.register("meteor_remote",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> ANCHOR_REMOTE = ITEMS.register("anchor_remote",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> HAZMAT_HELMET = ITEMS.register("hazmat_helmet",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> HAZMAT_PLATE = ITEMS.register("hazmat_plate",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> HAZMAT_LEGS = ITEMS.register("hazmat_legs",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> HAZMAT_BOOTS = ITEMS.register("hazmat_boots",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> HAZMAT_HELMET_RED = ITEMS.register("hazmat_helmet_red",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> HAZMAT_PLATE_RED = ITEMS.register("hazmat_plate_red",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> HAZMAT_LEGS_RED = ITEMS.register("hazmat_legs_red",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> HAZMAT_BOOTS_RED = ITEMS.register("hazmat_boots_red",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> HAZMAT_HELMET_GREY = ITEMS.register("hazmat_helmet_grey",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> HAZMAT_PLATE_GREY = ITEMS.register("hazmat_plate_grey",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> HAZMAT_LEGS_GREY = ITEMS.register("hazmat_legs_grey",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> HAZMAT_BOOTS_GREY = ITEMS.register("hazmat_boots_grey",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> LIQUIDATOR_HELMET = ITEMS.register("liquidator_helmet",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> LIQUIDATOR_PLATE = ITEMS.register("liquidator_plate",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> LIQUIDATOR_LEGS = ITEMS.register("liquidator_legs",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> LIQUIDATOR_BOOTS = ITEMS.register("liquidator_boots",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> HAZMAT_PAA_HELMET = ITEMS.register("hazmat_paa_helmet",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> HAZMAT_PAA_PLATE = ITEMS.register("hazmat_paa_plate",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> HAZMAT_PAA_LEGS = ITEMS.register("hazmat_paa_legs",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> HAZMAT_PAA_BOOTS = ITEMS.register("hazmat_paa_boots",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> PAA_PLATE = ITEMS.register("paa_plate",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> PAA_LEGS = ITEMS.register("paa_legs",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> PAA_BOOTS = ITEMS.register("paa_boots",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> AUSTRALIUM_III = ITEMS.register("australium_iii",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> ARMOR_BATTERY = ITEMS.register("armor_battery",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> ARMOR_BATTERY_MK2 = ITEMS.register("armor_battery_mk2",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> ARMOR_BATTERY_MK3 = ITEMS.register("armor_battery_mk3",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> ASBESTOS_HELMET = ITEMS.register("asbestos_helmet",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> ASBESTOS_PLATE = ITEMS.register("asbestos_plate",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> ASBESTOS_LEGS = ITEMS.register("asbestos_legs",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> ASBESTOS_BOOTS = ITEMS.register("asbestos_boots",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> EUPHEMIUM_HELMET = ITEMS.register("euphemium_helmet",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> EUPHEMIUM_PLATE = ITEMS.register("euphemium_plate",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> EUPHEMIUM_LEGS = ITEMS.register("euphemium_legs",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> EUPHEMIUM_BOOTS = ITEMS.register("euphemium_boots",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> JACKT = ITEMS.register("jackt",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> JACKT2 = ITEMS.register("jackt2",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> CHAINSAW = ITEMS.register("chainsaw",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> STEEL_HELMET = ITEMS.register("steel_helmet",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> STEEL_PLATE = ITEMS.register("steel_plate",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> STEEL_LEGS = ITEMS.register("steel_legs",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> STEEL_BOOTS = ITEMS.register("steel_boots",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> TITANIUM_HELMET = ITEMS.register("titanium_helmet",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> TITANIUM_PLATE = ITEMS.register("titanium_plate",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> TITANIUM_LEGS = ITEMS.register("titanium_legs",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> TITANIUM_BOOTS = ITEMS.register("titanium_boots",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> ALLOY_HELMET = ITEMS.register("alloy_helmet",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> ALLOY_PLATE = ITEMS.register("alloy_plate",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> ALLOY_LEGS = ITEMS.register("alloy_legs",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> ALLOY_BOOTS = ITEMS.register("alloy_boots",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> COBALT_HELMET = ITEMS.register("cobalt_helmet",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> COBALT_PLATE = ITEMS.register("cobalt_plate",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> COBALT_LEGS = ITEMS.register("cobalt_legs",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> COBALT_BOOTS = ITEMS.register("cobalt_boots",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> SECURITY_HELMET = ITEMS.register("security_helmet",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> SECURITY_PLATE = ITEMS.register("security_plate",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> SECURITY_LEGS = ITEMS.register("security_legs",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> SECURITY_BOOTS = ITEMS.register("security_boots",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> STARMETAL_HELMET = ITEMS.register("starmetal_helmet",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> STARMETAL_PLATE = ITEMS.register("starmetal_plate",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> STARMETAL_LEGS = ITEMS.register("starmetal_legs",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> STARMETAL_BOOTS = ITEMS.register("starmetal_boots",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> ROBES_HELMET = ITEMS.register("robes_helmet",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> ROBES_PLATE = ITEMS.register("robes_plate",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> ROBES_LEGS = ITEMS.register("robes_legs",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> ROBES_BOOTS = ITEMS.register("robes_boots",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> ZIRCONIUM_LEGS = ITEMS.register("zirconium_legs",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> DNT_HELMET = ITEMS.register("dnt_helmet",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> DNT_PLATE = ITEMS.register("dnt_plate",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> DNT_LEGS = ITEMS.register("dnt_legs",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> DNT_BOOTS = ITEMS.register("dnt_boots",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> CMB_HELMET = ITEMS.register("cmb_helmet",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> CMB_PLATE = ITEMS.register("cmb_plate",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> CMB_LEGS = ITEMS.register("cmb_legs",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> CMB_BOOTS = ITEMS.register("cmb_boots",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> SCHRABIDIUM_HELMET = ITEMS.register("schrabidium_helmet",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> SCHRABIDIUM_PLATE = ITEMS.register("schrabidium_plate",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> SCHRABIDIUM_LEGS = ITEMS.register("schrabidium_legs",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> SCHRABIDIUM_BOOTS = ITEMS.register("schrabidium_boots",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> T51_HELMET = ITEMS.register("t51_helmet",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> T51_PLATE = ITEMS.register("t51_plate",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> T51_LEGS = ITEMS.register("t51_legs",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> T51_BOOTS = ITEMS.register("t51_boots",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> STEAMSUIT_HELMET = ITEMS.register("steamsuit_helmet",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> STEAMSUIT_PLATE = ITEMS.register("steamsuit_plate",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> STEAMSUIT_LEGS = ITEMS.register("steamsuit_legs",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> STEAMSUIT_BOOTS = ITEMS.register("steamsuit_boots",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> TRENCHMASTER_HELMET = ITEMS.register("trenchmaster_helmet",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> TRENCHMASTER_PLATE = ITEMS.register("trenchmaster_plate",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> TRENCHMASTER_LEGS = ITEMS.register("trenchmaster_legs",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> TRENCHMASTER_BOOTS = ITEMS.register("trenchmaster_boots",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> TAURUN_HELMET = ITEMS.register("taurun_helmet",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> TAURUN_PLATE = ITEMS.register("taurun_plate",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> TAURUN_LEGS = ITEMS.register("taurun_legs",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> TAURUN_BOOTS = ITEMS.register("taurun_boots",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> BISMUTH_HELMET = ITEMS.register("bismuth_helmet",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> BISMUTH_PLATE = ITEMS.register("bismuth_plate",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> BISMUTH_LEGS = ITEMS.register("bismuth_legs",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> BISMUTH_BOOTS = ITEMS.register("bismuth_boots",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> ENVSUIT_HELMET = ITEMS.register("envsuit_helmet",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> ENVSUIT_PLATE = ITEMS.register("envsuit_plate",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> ENVSUIT_LEGS = ITEMS.register("envsuit_legs",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> ENVSUIT_BOOTS = ITEMS.register("envsuit_boots",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> DIESELSUIT_HELMET = ITEMS.register("dieselsuit_helmet",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> DIESELSUIT_PLATE = ITEMS.register("dieselsuit_plate",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> DIESELSUIT_LEGS = ITEMS.register("dieselsuit_legs",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> DIESELSUIT_BOOTS = ITEMS.register("dieselsuit_boots",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> AJR_HELMET = ITEMS.register("ajr_helmet",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> AJR_PLATE = ITEMS.register("ajr_plate",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> AJR_LEGS = ITEMS.register("ajr_legs",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> AJR_BOOTS = ITEMS.register("ajr_boots",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> AJRO_HELMET = ITEMS.register("ajro_helmet",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> AJRO_PLATE = ITEMS.register("ajro_plate",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> AJRO_LEGS = ITEMS.register("ajro_legs",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> AJRO_BOOTS = ITEMS.register("ajro_boots",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> HEV_HELMET = ITEMS.register("hev_helmet",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> HEV_PLATE = ITEMS.register("hev_plate",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> HEV_LEGS = ITEMS.register("hev_legs",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> HEV_BOOTS = ITEMS.register("hev_boots",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> BJ_HELMET = ITEMS.register("bj_helmet",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> BJ_PLATE = ITEMS.register("bj_plate",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> BJ_PLATE_JETPACK = ITEMS.register("bj_plate_jetpack",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> BJ_LEGS = ITEMS.register("bj_legs",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> BJ_BOOTS = ITEMS.register("bj_boots",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> RPA_HELMET = ITEMS.register("rpa_helmet",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> RPA_LEGS = ITEMS.register("rpa_legs",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> NCRPA_HELMET = ITEMS.register("ncrpa_helmet",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> NCRPA_PLATE = ITEMS.register("ncrpa_plate",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> NCRPA_LEGS = ITEMS.register("ncrpa_legs",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> NCRPA_BOOTS = ITEMS.register("ncrpa_boots",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> FAU_HELMET = ITEMS.register("fau_helmet",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> FAU_PLATE = ITEMS.register("fau_plate",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> FAU_LEGS = ITEMS.register("fau_legs",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> FAU_BOOTS = ITEMS.register("fau_boots",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> DNS_HELMET = ITEMS.register("dns_helmet",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> DNS_PLATE = ITEMS.register("dns_plate",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> DNS_LEGS = ITEMS.register("dns_legs",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> DNS_BOOTS = ITEMS.register("dns_boots",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> GOGGLES = ITEMS.register("goggles",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> ASHGLASSES = ITEMS.register("ashglasses",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> MASK_RAG = ITEMS.register("mask_rag",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> MASK_DAMP = ITEMS.register("mask_damp",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> MASK_PISS = ITEMS.register("mask_piss",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> GAS_MASK = ITEMS.register("gas_mask",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> GAS_MASK_M65 = ITEMS.register("gas_mask_m65",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> GAS_MASK_MONO = ITEMS.register("gas_mask_mono",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> GAS_MASK_OLDE = ITEMS.register("gas_mask_olde",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> HAT = ITEMS.register("nossy_hat",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> NO9 = ITEMS.register("no9",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> BETA = ITEMS.register("beta",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> JETPACK_FLY = ITEMS.register("jetpack_fly",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> JETPACK_BREAK = ITEMS.register("jetpack_break",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> JETPACK_VECTOR = ITEMS.register("jetpack_vector",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> JETPACK_BOOST = ITEMS.register("jetpack_boost",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> JETPACK_GLIDER = ITEMS.register("jetpack_glider",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> WINGS_MURK = ITEMS.register("wings_murk",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> WINGS_LIMP = ITEMS.register("wings_limp",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> CAPE_RADIATION = ITEMS.register("cape_radiation",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> CAPE_GASMASK = ITEMS.register("cape_gasmask",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> CAPE_SCHRABIDIUM = ITEMS.register("cape_schrabidium",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> SCHRABIDIUM_HAMMER = ITEMS.register("schrabidium_hammer",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> SHIMMER_SLEDGE = ITEMS.register("shimmer_sledge",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> SHIMMER_AXE = ITEMS.register("shimmer_axe",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> ULLAPOOL_CABER = ITEMS.register("ullapool_caber",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> EUPHEMIUM_STOPPER = ITEMS.register("euphemium_stopper",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> MATCHSTICK = ITEMS.register("matchstick",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> BALEFIRE_AND_STEEL = ITEMS.register("balefire_and_steel",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> WRENCH = ITEMS.register("wrench",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> WRENCH_FLIPPED = ITEMS.register("wrench_flipped",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> MEMESPOON = ITEMS.register("memespoon",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> WOOD_GAVEL = ITEMS.register("wood_gavel",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> LEAD_GAVEL = ITEMS.register("lead_gavel",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> DIAMOND_GAVEL = ITEMS.register("diamond_gavel",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> MULTITOOL_HIT = ITEMS.register("multitool_hit",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> MULTITOOL_DIG = ITEMS.register("multitool_dig",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> MULTITOOL_SILK = ITEMS.register("multitool_silk",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> MULTITOOL_EXT = ITEMS.register("multitool_ext",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> MULTITOOL_MINER = ITEMS.register("multitool_miner",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> MULTITOOL_BEAM = ITEMS.register("multitool_beam",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> MULTITOOL_SKY = ITEMS.register("multitool_sky",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> MULTITOOL_MEGA = ITEMS.register("multitool_mega",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> MULTITOOL_JOULE = ITEMS.register("multitool_joule",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> MULTITOOL_DECON = ITEMS.register("multitool_decon",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> GUN_B92 = ITEMS.register("gun_b92",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> GUN_B93 = ITEMS.register("gun_b93",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> GUN_SUPERSHOTGUN = ITEMS.register("gun_supershotgun",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> GUN_VORTEX = ITEMS.register("gun_vortex",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> GUN_MOIST_NUGGET = ITEMS.register("gun_moist_nugget",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> CRUCIBLE = ITEMS.register("crucible",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> STICK_DYNAMITE = ITEMS.register("stick_dynamite",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> STICK_DYNAMITE_FISHING = ITEMS.register("stick_dynamite_fishing",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> STICK_TNT = ITEMS.register("stick_tnt",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> STICK_SEMTEX = ITEMS.register("stick_semtex",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> STICK_C4 = ITEMS.register("stick_c4",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> INGOT_U238M2 = ITEMS.register("ingot_u238m2",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> INGOT_SMORE = ITEMS.register("ingot_smore",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> INGOT_ELECTRONIUM = ITEMS.register("ingot_electronium",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> INGOT_STEEL_DUSTED = ITEMS.register("ingot_steel_dusted",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> INGOT_CHAINSTEEL = ITEMS.register("ingot_chainsteel",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> INGOT_METEORITE = ITEMS.register("ingot_meteorite",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> INGOT_METEORITE_FORGED = ITEMS.register("ingot_meteorite_forged",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> BLADE_METEORITE = ITEMS.register("blade_meteorite",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> BILLET_UZH = ITEMS.register("billet_uzh",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> BILLET_ZFB_BISMUTH = ITEMS.register("billet_zfb_bismuth",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> BILLET_ZFB_PU241 = ITEMS.register("billet_zfb_pu241",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> BILLET_ZFB_AM_MIX = ITEMS.register("billet_zfb_am_mix",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> BILLET_THORIUM_FUEL = ITEMS.register("billet_thorium_fuel",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> BILLET_URANIUM_FUEL = ITEMS.register("billet_uranium_fuel",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> BILLET_MOX_FUEL = ITEMS.register("billet_mox_fuel",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> BILLET_PLUTONIUM_FUEL = ITEMS.register("billet_plutonium_fuel",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> BILLET_NEPTUNIUM_FUEL = ITEMS.register("billet_neptunium_fuel",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> BILLET_AMERICIUM_FUEL = ITEMS.register("billet_americium_fuel",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> BILLET_LES = ITEMS.register("billet_les",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> BILLET_SCHRABIDIUM_FUEL = ITEMS.register("billet_schrabidium_fuel",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> BILLET_HES = ITEMS.register("billet_hes",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> BILLET_PO210BE = ITEMS.register("billet_po210be",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> BILLET_RA226BE = ITEMS.register("billet_ra226be",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> BILLET_PU238BE = ITEMS.register("billet_pu238be",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> BILLET_AUSTRALIUM_LESSER = ITEMS.register("billet_australium_lesser",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> BILLET_AUSTRALIUM_GREATER = ITEMS.register("billet_australium_greater",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> BILLET_UNOBTAINIUM = ITEMS.register("billet_unobtainium",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> BILLET_YHARONITE = ITEMS.register("billet_yharonite",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> BILLET_BALEFIRE_GOLD = ITEMS.register("billet_balefire_gold",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> BILLET_FLASHLEAD = ITEMS.register("billet_flashlead",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> BILLET_NUCLEAR_WASTE = ITEMS.register("billet_nuclear_waste",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> BIO_WAFER = ITEMS.register("bio_wafer",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> NUGGET_U238M2 = ITEMS.register("nugget_u238m2",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> NUGGET_AMERICIUM_FUEL = ITEMS.register("nugget_americium_fuel",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> NUGGET_CADMIUM = ITEMS.register("nugget_cadmium",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> NUGGET_STRONTIUM = ITEMS.register("nugget_strontium",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> NUGGET_REIIUM = ITEMS.register("nugget_reiium",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> NUGGET_WEIDANIUM = ITEMS.register("nugget_weidanium",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> NUGGET_VERTICIUM = ITEMS.register("nugget_verticium",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> NUGGET_UNOBTAINIUM = ITEMS.register("nugget_unobtainium",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> NUGGET_UNOBTAINIUM_LESSER = ITEMS.register("nugget_unobtainium_lesser",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> NUGGET_UNOBTAINIUM_GREATER = ITEMS.register("nugget_unobtainium_greater",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> NUGGET_DAFFERGON = ITEMS.register("nugget_daffergon",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> NUGGET_MERCURY_TINY = ITEMS.register("nugget_mercury_tiny",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> BOTTLE_MERCURY = ITEMS.register("bottle_mercury",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> POWDER_YELLOWCAKE = ITEMS.register("powder_yellowcake",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> POWDER_REIIUM = ITEMS.register("powder_reiium",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> POWDER_WEIDANIUM = ITEMS.register("powder_weidanium",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> POWDER_VERTICIUM = ITEMS.register("powder_verticium",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> POWDER_UNOBTAINIUM = ITEMS.register("powder_unobtainium",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> POWDER_DAFFERGON = ITEMS.register("powder_daffergon",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> POWDER_FLUX = ITEMS.register("powder_flux",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> POWDER_FERTILIZER = ITEMS.register("powder_fertilizer",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> POWDER_TEKTITE = ITEMS.register("powder_tektite",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> POWDER_PALEOGENITE_TINY = ITEMS.register("powder_paleogenite_tiny",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> POWDER_PALEOGENITE = ITEMS.register("powder_paleogenite",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> POWDER_OSMIRIDIUM = ITEMS.register("powder_osmiridium",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> POWDER_MAGIC = ITEMS.register("powder_magic",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> POWDER_CLOUD = ITEMS.register("powder_cloud",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> POWDER_SAWDUST = ITEMS.register("powder_sawdust",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> POWDER_THERMITE = ITEMS.register("powder_thermite",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> POWDER_POWER = ITEMS.register("powder_power",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> NITRA = ITEMS.register("nitra",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> NITRA_SMALL = ITEMS.register("nitra_small",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> TRINITITE = ITEMS.register("trinitite",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> NUCLEAR_WASTE = ITEMS.register("nuclear_waste",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> NUCLEAR_WASTE_VITRIFIED = ITEMS.register("nuclear_waste_vitrified",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> NUCLEAR_WASTE_VITRIFIED_TINY = ITEMS.register("nuclear_waste_vitrified_tiny",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> SCRAP_PLASTIC = ITEMS.register("scrap_plastic",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> SCRAP_NUCLEAR = ITEMS.register("scrap_nuclear",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> CONTAINMENT_BOX = ITEMS.register("containment_box",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> PLASTIC_BAG = ITEMS.register("plastic_bag",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> AMMO_BAG = ITEMS.register("ammo_bag",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> AMMO_BAG_INFINITE = ITEMS.register("ammo_bag_infinite",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> CASING_BAG = ITEMS.register("casing_bag",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> TRITIUM_DEUTERIUM_CAKE = ITEMS.register("tritium_deuterium_cake",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> PILE_ROD_URANIUM = ITEMS.register("pile_rod_uranium",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> PILE_ROD_PU239 = ITEMS.register("pile_rod_pu239",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> PILE_ROD_PLUTONIUM = ITEMS.register("pile_rod_plutonium",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> PILE_ROD_SOURCE = ITEMS.register("pile_rod_source",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> PILE_ROD_BORON = ITEMS.register("pile_rod_boron",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> PILE_ROD_LITHIUM = ITEMS.register("pile_rod_lithium",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> PILE_ROD_DETECTOR = ITEMS.register("pile_rod_detector",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> ROD_ZIRNOX = ITEMS.register("rod_zirnox",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> ROD_ZIRNOX_DEPLETED = ITEMS.register("rod_zirnox_depleted",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> WASTE_NATURAL_URANIUM = ITEMS.register("waste_natural_uranium",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> WASTE_URANIUM = ITEMS.register("waste_uranium",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> WASTE_THORIUM = ITEMS.register("waste_thorium",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> WASTE_MOX = ITEMS.register("waste_mox",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> WASTE_PLUTONIUM = ITEMS.register("waste_plutonium",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> WASTE_U233 = ITEMS.register("waste_u233",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> WASTE_U235 = ITEMS.register("waste_u235",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> WASTE_SCHRABIDIUM = ITEMS.register("waste_schrabidium",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> WASTE_ZFB_MOX = ITEMS.register("waste_zfb_mox",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> WASTE_PLATE_U233 = ITEMS.register("waste_plate_u233",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> WASTE_PLATE_U235 = ITEMS.register("waste_plate_u235",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> WASTE_PLATE_MOX = ITEMS.register("waste_plate_mox",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> WASTE_PLATE_PU239 = ITEMS.register("waste_plate_pu239",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> WASTE_PLATE_SA326 = ITEMS.register("waste_plate_sa326",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> WASTE_PLATE_RA226BE = ITEMS.register("waste_plate_ra226be",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> WASTE_PLATE_PU238BE = ITEMS.register("waste_plate_pu238be",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> PLATE_FUEL_U233 = ITEMS.register("plate_fuel_u233",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> PLATE_FUEL_U235 = ITEMS.register("plate_fuel_u235",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> PLATE_FUEL_MOX = ITEMS.register("plate_fuel_mox",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> PLATE_FUEL_PU239 = ITEMS.register("plate_fuel_pu239",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> PLATE_FUEL_SA326 = ITEMS.register("plate_fuel_sa326",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> PLATE_FUEL_RA226BE = ITEMS.register("plate_fuel_ra226be",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> PLATE_FUEL_PU238BE = ITEMS.register("plate_fuel_pu238be",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> PWR_FUEL_HOT = ITEMS.register("pwr_fuel_hot",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> PWR_FUEL_DEPLETED = ITEMS.register("pwr_fuel_depleted",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> ROD = ITEMS.register("rod",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> ROD_DUAL = ITEMS.register("rod_dual",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> ROD_QUAD = ITEMS.register("rod_quad",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> PELLET_RTG_DEPLETED = ITEMS.register("pellet_rtg_depleted",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> PELLET_RTG_RADIUM = ITEMS.register("pellet_rtg_radium",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> PELLET_RTG_WEAK = ITEMS.register("pellet_rtg_weak",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> PELLET_RTG = ITEMS.register("pellet_rtg",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> PELLET_RTG_STRONTIUM = ITEMS.register("pellet_rtg_strontium",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> PELLET_RTG_COBALT = ITEMS.register("pellet_rtg_cobalt",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> PELLET_RTG_ACTINIUM = ITEMS.register("pellet_rtg_actinium",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> PELLET_RTG_AMERICIUM = ITEMS.register("pellet_rtg_americium",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> PELLET_RTG_POLONIUM = ITEMS.register("pellet_rtg_polonium",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> PELLET_RTG_GOLD = ITEMS.register("pellet_rtg_gold",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> PELLET_RTG_LEAD = ITEMS.register("pellet_rtg_lead",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> PELLET_RTG_BALEFIRE = ITEMS.register("pellet_rtg_balefire",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> PELLET_COAL = ITEMS.register("pellet_coal",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> CHLORINE_PINWHEEL = ITEMS.register("chlorine_pinwheel",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> BIOMASS = ITEMS.register("biomass",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> BIOMASS_COMPRESSED = ITEMS.register("biomass_compressed",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> PLANT_ITEM = ITEMS.register("plant_item",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> MOLD = ITEMS.register("mold",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> BOLT_SPIKE = ITEMS.register("bolt_spike",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> CASING = ITEMS.register("casing",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> BEDROCK_ORE = ITEMS.register("bedrock_ore_new",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> BEDROCK_ORE_BASE = ITEMS.register("bedrock_ore_base",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> THERMO_UNIT_ENDO = ITEMS.register("thermo_unit_endo",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> THERMO_UNIT_EXO = ITEMS.register("thermo_unit_exo",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> MAGNETRON = ITEMS.register("magnetron",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> PELLET_CHARGED = ITEMS.register("pellet_charged",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> PELLET_CLUSTER = ITEMS.register("pellet_cluster",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> PELLET_GAS = ITEMS.register("pellet_gas",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> COAL_INFERNAL = ITEMS.register("coal_infernal",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> POWDER_CEMENT = ITEMS.register("powder_cement",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> MOTOR = ITEMS.register("motor",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> MOTOR_DESH = ITEMS.register("motor_desh",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> MOTOR_BISMUTH = ITEMS.register("motor_bismuth",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> SAFETY_FUSE = ITEMS.register("safety_fuse",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> RAG = ITEMS.register("rag",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> SOLID_FUEL = ITEMS.register("solid_fuel",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> SOLID_FUEL_PRESTO = ITEMS.register("solid_fuel_presto",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> SOLID_FUEL_PRESTO_TRIPLET = ITEMS.register("solid_fuel_presto_triplet",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> SOLID_FUEL_BF = ITEMS.register("solid_fuel_bf",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> SOLID_FUEL_PRESTO_BF = ITEMS.register("solid_fuel_presto_bf",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> SOLID_FUEL_PRESTO_TRIPLET_BF = ITEMS.register("solid_fuel_presto_triplet_bf",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> ROCKET_FUEL = ITEMS.register("rocket_fuel",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> CIRCUIT = ITEMS.register("circuit",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> DEMON_CORE_OPEN = ITEMS.register("demon_core_open",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> DEMON_CORE_CLOSED = ITEMS.register("demon_core_closed",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> BOTTLE_OPENER = ITEMS.register("bottle_opener",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> BOTTLE_NUKA = ITEMS.register("bottle_nuka",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> BOTTLE_CHERRY = ITEMS.register("bottle_cherry",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> BOTTLE_QUANTUM = ITEMS.register("bottle_quantum",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> BOTTLE_SPARKLE = ITEMS.register("bottle_sparkle",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> BOTTLE_RAD = ITEMS.register("bottle_rad",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> BOTTLE2_KORL = ITEMS.register("bottle2_korl",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> BOTTLE2_FRITZ = ITEMS.register("bottle2_fritz",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> BOTTLE2_KORL_SPECIAL = ITEMS.register("bottle2_korl_special",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> BOTTLE2_FRITZ_SPECIAL = ITEMS.register("bottle2_fritz_special",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> BOTTLE2_SUNSET = ITEMS.register("bottle2_sunset",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> FLASK_INFUSION = ITEMS.register("flask_infusion",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> CHOCOLATE_MILK = ITEMS.register("chocolate_milk",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> COFFEE = ITEMS.register("coffee",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> COFFEE_RADIUM = ITEMS.register("coffee_radium",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> CHOCOLATE = ITEMS.register("chocolate",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> BOMB_WAFFLE = ITEMS.register("bomb_waffle",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> SCHNITZEL_VEGAN = ITEMS.register("schnitzel_vegan",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> COTTON_CANDY = ITEMS.register("cotton_candy",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> APPLE_LEAD = ITEMS.register("apple_lead",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> APPLE_SCHRABIDIUM = ITEMS.register("apple_schrabidium",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> TEM_FLAKES = ITEMS.register("tem_flakes",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> GLOWING_STEW = ITEMS.register("glowing_stew",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> BALEFIRE_SCRAMBLED = ITEMS.register("balefire_scrambled",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> BALEFIRE_AND_HAM = ITEMS.register("balefire_and_ham",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> LEMON = ITEMS.register("lemon",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> DEFINITELYFOOD = ITEMS.register("definitelyfood",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> MED_IPECAC = ITEMS.register("med_ipecac",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> MED_PTSD = ITEMS.register("med_ptsd",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> MED_SCHIZOPHRENIA = ITEMS.register("med_schizophrenia",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> LOOPS = ITEMS.register("loops",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> LOOP_STEW = ITEMS.register("loop_stew",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> FOODITEM = ITEMS.register("fooditem",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> TWINKIE = ITEMS.register("twinkie",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> STATIC_SANDWICH = ITEMS.register("static_sandwich",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> CANTEEN_13 = ITEMS.register("canteen_13",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> CANTEEN_VODKA = ITEMS.register("canteen_vodka",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> CANTEEN_FAB = ITEMS.register("canteen_fab",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> PANCAKE = ITEMS.register("pancake",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> PEAS = ITEMS.register("peas",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> MARSHMALLOW = ITEMS.register("marshmallow",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> CHEESE = ITEMS.register("cheese",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> QUESADILLA = ITEMS.register("cheese_quesadilla",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> MUCHO_MANGO = ITEMS.register("mucho_mango",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> MARSHMALLOW_ROASTED = ITEMS.register("marshmallow_roasted",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> SPONGEBOB_MACARONI = ITEMS.register("spongebob_macaroni",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> COIN_MASKMAN = ITEMS.register("coin_maskman",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> COIN_CREEPER = ITEMS.register("coin_creeper",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> COIN_RADIATION = ITEMS.register("coin_radiation",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> COIN_WORM = ITEMS.register("coin_worm",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> COIN_UFO = ITEMS.register("coin_ufo",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> COIN_SIEGE = ITEMS.register("coin_siege",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> COIN_TOKEN = ITEMS.register("coin_token",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> PUDDING = ITEMS.register("pudding",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> CAN_SMART = ITEMS.register("can_smart",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> CAN_CREATURE = ITEMS.register("can_creature",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> CAN_REDBOMB = ITEMS.register("can_redbomb",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> CAN_MRSUGAR = ITEMS.register("can_mrsugar",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> CAN_OVERCHARGE = ITEMS.register("can_overcharge",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> CAN_LUNA = ITEMS.register("can_luna",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> CAN_BEPIS = ITEMS.register("can_bepis",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> CAN_BREEN = ITEMS.register("can_breen",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> CAN_MUG = ITEMS.register("can_mug",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> DRONE = ITEMS.register("drone",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> TITANIUM_SWORD = ITEMS.register("titanium_sword",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> TITANIUM_PICKAXE = ITEMS.register("titanium_pickaxe",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> TITANIUM_AXE = ITEMS.register("titanium_axe",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> TITANIUM_SHOVEL = ITEMS.register("titanium_shovel",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> TITANIUM_HOE = ITEMS.register("titanium_hoe",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> STEEL_SWORD = ITEMS.register("steel_sword",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> STEEL_PICKAXE = ITEMS.register("steel_pickaxe",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> STEEL_AXE = ITEMS.register("steel_axe",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> STEEL_SHOVEL = ITEMS.register("steel_shovel",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> STEEL_HOE = ITEMS.register("steel_hoe",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> ALLOY_SWORD = ITEMS.register("alloy_sword",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> ALLOY_PICKAXE = ITEMS.register("alloy_pickaxe",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> ALLOY_AXE = ITEMS.register("alloy_axe",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> ALLOY_SHOVEL = ITEMS.register("alloy_shovel",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> ALLOY_HOE = ITEMS.register("alloy_hoe",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> ELEC_SWORD = ITEMS.register("elec_sword",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> ELEC_PICKAXE = ITEMS.register("elec_pickaxe",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> ELEC_AXE = ITEMS.register("elec_axe",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> ELEC_SHOVEL = ITEMS.register("elec_shovel",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> DESH_SWORD = ITEMS.register("desh_sword",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> DESH_PICKAXE = ITEMS.register("desh_pickaxe",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> DESH_AXE = ITEMS.register("desh_axe",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> DESH_SHOVEL = ITEMS.register("desh_shovel",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> DESH_HOE = ITEMS.register("desh_hoe",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> COBALT_SWORD = ITEMS.register("cobalt_sword",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> COBALT_PICKAXE = ITEMS.register("cobalt_pickaxe",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> COBALT_AXE = ITEMS.register("cobalt_axe",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> COBALT_SHOVEL = ITEMS.register("cobalt_shovel",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> COBALT_HOE = ITEMS.register("cobalt_hoe",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> CENTRI_STICK = ITEMS.register("centri_stick",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> SMASHING_HAMMER = ITEMS.register("smashing_hammer",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> COBALT_DECORATED_SWORD = ITEMS.register("cobalt_decorated_sword",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> COBALT_DECORATED_PICKAXE = ITEMS.register("cobalt_decorated_pickaxe",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> COBALT_DECORATED_AXE = ITEMS.register("cobalt_decorated_axe",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> COBALT_DECORATED_SHOVEL = ITEMS.register("cobalt_decorated_shovel",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> COBALT_DECORATED_HOE = ITEMS.register("cobalt_decorated_hoe",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> STARMETAL_SWORD = ITEMS.register("starmetal_sword",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> STARMETAL_PICKAXE = ITEMS.register("starmetal_pickaxe",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> STARMETAL_AXE = ITEMS.register("starmetal_axe",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> STARMETAL_SHOVEL = ITEMS.register("starmetal_shovel",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> STARMETAL_HOE = ITEMS.register("starmetal_hoe",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> CMB_SWORD = ITEMS.register("cmb_sword",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> CMB_PICKAXE = ITEMS.register("cmb_pickaxe",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> CMB_AXE = ITEMS.register("cmb_axe",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> CMB_SHOVEL = ITEMS.register("cmb_shovel",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> CMB_HOE = ITEMS.register("cmb_hoe",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> BISMUTH_AXE = ITEMS.register("bismuth_axe",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> BISMUTH_PICKAXE = ITEMS.register("bismuth_pickaxe",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> VOLCANIC_AXE = ITEMS.register("volcanic_axe",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> VOLCANIC_PICKAXE = ITEMS.register("volcanic_pickaxe",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> CHLOROPHYTE_AXE = ITEMS.register("chlorophyte_axe",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> CHLOROPHYTE_PICKAXE = ITEMS.register("chlorophyte_pickaxe",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> SCHRABIDIUM_HOE = ITEMS.register("schrabidium_hoe",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> SCHRABIDIUM_SWORD = ITEMS.register("schrabidium_sword",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> SCHRABIDIUM_PICKAXE = ITEMS.register("schrabidium_pickaxe",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> SCHRABIDIUM_AXE = ITEMS.register("schrabidium_axe",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> SCHRABIDIUM_SHOVEL = ITEMS.register("schrabidium_shovel",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> CROWBAR = ITEMS.register("crowbar",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> SAW = ITEMS.register("weapon_saw",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> BAT = ITEMS.register("weapon_bat",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> BAT_NAIL = ITEMS.register("weapon_bat_nail",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> GOLF_CLUB = ITEMS.register("weapon_golf_club",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> PIPE_RUSTY = ITEMS.register("weapon_pipe_rusty",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> PIPE_LEAD = ITEMS.register("weapon_pipe_lead",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> REER_GRAAR = ITEMS.register("reer_graar",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> STOPSIGN = ITEMS.register("stopsign",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> SOPSIGN = ITEMS.register("sopsign",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> CHERNOBYLSIGN = ITEMS.register("chernobylsign",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> MASK_OF_INFAMY = ITEMS.register("mask_of_infamy",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> METEORITE_SWORD = ITEMS.register("meteorite_sword",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> METEORITE_SWORD_SEARED = ITEMS.register("meteorite_sword_seared",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> METEORITE_SWORD_REFORGED = ITEMS.register("meteorite_sword_reforged",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> METEORITE_SWORD_HARDENED = ITEMS.register("meteorite_sword_hardened",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> METEORITE_SWORD_ALLOYED = ITEMS.register("meteorite_sword_alloyed",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> METEORITE_SWORD_MACHINED = ITEMS.register("meteorite_sword_machined",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> METEORITE_SWORD_TREATED = ITEMS.register("meteorite_sword_treated",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> METEORITE_SWORD_ETCHED = ITEMS.register("meteorite_sword_etched",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> METEORITE_SWORD_BRED = ITEMS.register("meteorite_sword_bred",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> METEORITE_SWORD_IRRADIATED = ITEMS.register("meteorite_sword_irradiated",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> METEORITE_SWORD_FUSED = ITEMS.register("meteorite_sword_fused",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> METEORITE_SWORD_BALEFUL = ITEMS.register("meteorite_sword_baleful",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> PLATE_PAA = ITEMS.register("plate_paa",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> PLATE_BISMUTH = ITEMS.register("plate_bismuth",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> PLATE_EUPHEMIUM = ITEMS.register("plate_euphemium",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> BLUEPRINTS = ITEMS.register("blueprints",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> BLUEPRINT_FOLDER = ITEMS.register("blueprint_folder",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> BOBMAZON = ITEMS.register("bobmazon",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> BOBMAZON_HIDDEN = ITEMS.register("bobmazon_hidden",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> SIREN_TRACK = ITEMS.register("siren_track",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> FLUID_IDENTIFIER_MULTI = ITEMS.register("fluid_identifier_multi",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> FLUID_DUCT = ITEMS.register("ff_fluid_duct",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> MESE_GAVEL = ITEMS.register("mese_gavel",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> MESE_PICKAXE = ITEMS.register("mese_pickaxe",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> MESE_AXE = ITEMS.register("mese_axe",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> DNT_SWORD = ITEMS.register("dnt_sword",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> DWARVEN_PICKAXE = ITEMS.register("dwarven_pickaxe",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> CRYSTAL_COAL = ITEMS.register("crystal_coal",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> GEM_RAD = ITEMS.register("gem_rad",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> UPGRADE_MUFFLER = ITEMS.register("upgrade_muffler",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> UPGRADE_TEMPLATE = ITEMS.register("upgrade_template",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> PARTS_LEGENDARY = ITEMS.register("parts_legendary",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> GEAR_LARGE = ITEMS.register("gear_large",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> CRT_DISPLAY = ITEMS.register("crt_display",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> CIRCUIT_STAR_PIECE = ITEMS.register("circuit_star_piece",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> CIRCUIT_STAR_COMPONENT = ITEMS.register("circuit_star_component",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> CIRCUIT_STAR = ITEMS.register("circuit_star",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> COIL_MAGNETIZED_TUNGSTEN = ITEMS.register("coil_magnetized_tungsten",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> AMMO_SHELL = ITEMS.register("ammo_shell",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> AMMO_DGK = ITEMS.register("ammo_dgk",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> AMMO_FIREEXT = ITEMS.register("ammo_fireext",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> AMMO_MISC = ITEMS.register("ammo_misc",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> AMMO_ARTY = ITEMS.register("ammo_arty",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> AMMO_HIMARS = ITEMS.register("ammo_himars",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> GUN_B92_AMMO = ITEMS.register("gun_b92_ammo",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> CHARGE_RAILGUN = ITEMS.register("charge_railgun",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> AMMO_CONTAINER = ITEMS.register("ammo_container",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> GRENADE_SHELL = ITEMS.register("grenade_shell",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> GRENADE_FILLING = ITEMS.register("grenade_filling",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> GRENADE_FUZE = ITEMS.register("grenade_fuze",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> GRENADE_EXTRA = ITEMS.register("grenade_extra",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> GRENADE_UNIVERSAL = ITEMS.register("grenade_universal",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> WEAPONIZED_STARBLASTER_CELL = ITEMS.register("weaponized_starblaster_cell",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> TURRET_CHIP = ITEMS.register("turret_chip",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> TURRET_BIOMETRY = ITEMS.register("turret_biometry",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> DESIGNATOR_ARTY_RANGE = ITEMS.register("designator_arty_range",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> BOOK_GUIDE = ITEMS.register("book_guide_book",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> BOOK_LORE = ITEMS.register("book_lore",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> RUNE_BLANK = ITEMS.register("rune_blank",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> RUNE_ISA = ITEMS.register("rune_isa",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> RUNE_DAGAZ = ITEMS.register("rune_dagaz",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> RUNE_HAGALAZ = ITEMS.register("rune_hagalaz",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> RUNE_JERA = ITEMS.register("rune_jera",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> RUNE_THURISAZ = ITEMS.register("rune_thurisaz",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> RBMK_LID = ITEMS.register("rbmk_lid",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> RBMK_LID_GLASS = ITEMS.register("rbmk_lid_glass",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> RBMK_PELLET_LEA = ITEMS.register("rbmk_pellet_lea",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> RBMK_PELLET_MEA = ITEMS.register("rbmk_pellet_mea",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> RBMK_PELLET_HEA241 = ITEMS.register("rbmk_pellet_hea241",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> RBMK_PELLET_HEA242 = ITEMS.register("rbmk_pellet_hea242",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> RBMK_FUEL_UZH = ITEMS.register("rbmk_fuel_uzh",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> RBMK_FUEL_TEST = ITEMS.register("rbmk_fuel_test",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> ICF_PELLET = ITEMS.register("icf_pellet",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> WATZ_PELLET = ITEMS.register("watz_pellet",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> WATZ_PELLET_DEPLETED = ITEMS.register("watz_pellet_depleted",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> PA_COIL = ITEMS.register("pa_coil",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> PARTICLE_DIGAMMA = ITEMS.register("particle_digamma",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> CAPSULE_XEN = ITEMS.register("capsule_xen",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> SINGULARITY = ITEMS.register("singularity",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> SINGULARITY_COUNTER_RESONANT = ITEMS.register("singularity_counter_resonant",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> SINGULARITY_SUPER_HEATED = ITEMS.register("singularity_super_heated",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> BLACK_HOLE = ITEMS.register("black_hole",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> SINGULARITY_SPARK = ITEMS.register("singularity_spark",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> PELLET_ANTIMATTER = ITEMS.register("pellet_antimatter",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> CRYSTAL_XEN = ITEMS.register("crystal_xen",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> CRYSTAL_ENERGY = ITEMS.register("crystal_energy",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> KEY = ITEMS.register("key",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> KEY_RED = ITEMS.register("key_red",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> KEY_RED_CRACKED = ITEMS.register("key_red_cracked",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> KEY_KIT = ITEMS.register("key_kit",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> KEY_FAKE = ITEMS.register("key_fake",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> PIN = ITEMS.register("pin",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> PADLOCK_RUSTY = ITEMS.register("padlock_rusty",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> PADLOCK = ITEMS.register("padlock",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> PADLOCK_REINFORCED = ITEMS.register("padlock_reinforced",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> PADLOCK_UNBREAKABLE = ITEMS.register("padlock_unbreakable",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> MECH_KEY = ITEMS.register("mech_key",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> AMS_CATALYST_IRON = ITEMS.register("ams_catalyst_iron",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> AMS_CATALYST_COPPER = ITEMS.register("ams_catalyst_copper",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> AMS_CATALYST_ALUMINIUM = ITEMS.register("ams_catalyst_aluminium",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> AMS_CATALYST_LITHIUM = ITEMS.register("ams_catalyst_lithium",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> AMS_CATALYST_BERYLLIUM = ITEMS.register("ams_catalyst_beryllium",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> AMS_CATALYST_TUNGSTEN = ITEMS.register("ams_catalyst_tungsten",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> AMS_CATALYST_COBALT = ITEMS.register("ams_catalyst_cobalt",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> AMS_CATALYST_NIOBIUM = ITEMS.register("ams_catalyst_niobium",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> AMS_CATALYST_CERIUM = ITEMS.register("ams_catalyst_cerium",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> AMS_CATALYST_THORIUM = ITEMS.register("ams_catalyst_thorium",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> AMS_CATALYST_STRONTIUM = ITEMS.register("ams_catalyst_strontium",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> AMS_CATALYST_CAESIUM = ITEMS.register("ams_catalyst_caesium",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> AMS_CATALYST_SCHRABIDIUM = ITEMS.register("ams_catalyst_schrabidium",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> AMS_CATALYST_EUPHEMIUM = ITEMS.register("ams_catalyst_euphemium",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> AMS_CATALYST_DINEUTRONIUM = ITEMS.register("ams_catalyst_dineutronium",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> AMS_LENS = ITEMS.register("ams_lens",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> AMS_CORE_SING = ITEMS.register("ams_core_sing",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> AMS_CORE_WORMHOLE = ITEMS.register("ams_core_wormhole",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> AMS_CORE_EYEOFHARMONY = ITEMS.register("ams_core_eyeofharmony",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> AMS_CORE_THINGY = ITEMS.register("ams_core_thingy",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> AMS_MUZZLE = ITEMS.register("ams_muzzle",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> EARLY_EXPLOSIVE_LENSES = ITEMS.register("gadget_explosive8",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> GADGET_WIREING = ITEMS.register("gadget_wireing",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> GADGET_CORE = ITEMS.register("gadget_core",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> BOY_SHIELDING = ITEMS.register("boy_shielding",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> BOY_TARGET = ITEMS.register("boy_target",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> BOY_BULLET = ITEMS.register("boy_bullet",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> BOY_PROPELLANT = ITEMS.register("boy_propellant",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> BOY_IGNITER = ITEMS.register("boy_igniter",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> MAN_CORE = ITEMS.register("man_core",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> EXPLOSIVE_LENSES = ITEMS.register("man_explosive8",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> MAN_IGNITER = ITEMS.register("man_igniter",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> MIKE_CORE = ITEMS.register("mike_core",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> MIKE_COOLING_UNIT = ITEMS.register("mike_cooling_unit",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> TSAR_CORE = ITEMS.register("tsar_core",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> FLEIJA_IGNITER = ITEMS.register("fleija_igniter",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> FLEIJA_PROPELLANT = ITEMS.register("fleija_propellant",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> FLEIJA_CORE = ITEMS.register("fleija_core",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> SOLINIUM_CORE = ITEMS.register("solinium_core",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> SOLINIUM_IGNITER = ITEMS.register("solinium_igniter",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> SOLINIUM_PROPELLANT = ITEMS.register("solinium_propellant",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> N2_CHARGE = ITEMS.register("n2_charge",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> EGG_BALEFIRE_SHARD = ITEMS.register("egg_balefire_shard",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> EGG_BALEFIRE = ITEMS.register("egg_balefire",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> CUSTOM_TNT = ITEMS.register("custom_tnt",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> CUSTOM_NUKE = ITEMS.register("custom_nuke",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> CUSTOM_HYDRO = ITEMS.register("custom_hydro",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> CUSTOM_AMAT = ITEMS.register("custom_amat",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> CUSTOM_DIRTY = ITEMS.register("custom_dirty",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> CUSTOM_SCHRAB = ITEMS.register("custom_schrab",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> CUSTOM_SOL = ITEMS.register("custom_sol",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> CUSTOM_EUPH = ITEMS.register("custom_euph",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> CUSTOM_FALL = ITEMS.register("custom_fall",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> GRENADE_KIT = ITEMS.register("grenade_kit",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> GADGET_KIT = ITEMS.register("gadget_kit",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> BOY_KIT = ITEMS.register("boy_kit",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> MAN_KIT = ITEMS.register("man_kit",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> MIKE_KIT = ITEMS.register("mike_kit",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> TSAR_KIT = ITEMS.register("tsar_kit",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> PROTOTYPE_KIT = ITEMS.register("prototype_kit",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> FLEIJA_KIT = ITEMS.register("fleija_kit",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> SOLINIUM_KIT = ITEMS.register("solinium_kit",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> BALEFIRE_KIT = ITEMS.register("balefire_kit",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> MULTI_KIT = ITEMS.register("multi_kit",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> CUSTOM_KIT = ITEMS.register("custom_kit",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> MISSILE_KIT = ITEMS.register("missile_kit",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> T45_KIT = ITEMS.register("t45_kit",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> HAZMAT_KIT = ITEMS.register("hazmat_kit",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> HAZMAT_RED_KIT = ITEMS.register("hazmat_red_kit",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> HAZMAT_GREY_KIT = ITEMS.register("hazmat_grey_kit",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> NUKE_STARTER_KIT = ITEMS.register("nuke_starter_kit",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> NUKE_ADVANCED_KIT = ITEMS.register("nuke_advanced_kit",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> NUKE_COMMERCIALLY_KIT = ITEMS.register("nuke_commercially_kit",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> KIT_CUSTOM = ITEMS.register("kit_custom",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> TOOLBOX = ITEMS.register("toolbox",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> LOOT_10 = ITEMS.register("loot_10",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> LOOT_15 = ITEMS.register("loot_15",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> LOOT_MISC = ITEMS.register("loot_misc",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> SAT_MAPPER = ITEMS.register("sat_mapper",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> SAT_SCANNER = ITEMS.register("sat_scanner",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> SAT_RADAR = ITEMS.register("sat_radar",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> SAT_LASER = ITEMS.register("sat_laser",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> SAT_FOEQ = ITEMS.register("sat_foeq",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> SAT_RESONATOR = ITEMS.register("sat_resonator",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> SAT_MINER = ITEMS.register("sat_miner",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> SAT_LUNAR_MINER = ITEMS.register("sat_lunar_miner",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> SAT_GERALD = ITEMS.register("sat_gerald",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> SAT_CHIP = ITEMS.register("sat_chip",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> SAT_INTERFACE = ITEMS.register("sat_interface",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> SAT_COORD = ITEMS.register("sat_coord",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> SAT_DESIGNATOR = ITEMS.register("sat_designator",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> SAT_RELAY = ITEMS.register("sat_relay",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> RANGEFINDER = ITEMS.register("rangefinder",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> DESIGNATOR = ITEMS.register("designator",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> DESIGNATOR_RANGE = ITEMS.register("designator_range",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> DESIGNATOR_MANUAL = ITEMS.register("designator_manual",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> LAUNCH_CODE_PIECE = ITEMS.register("launch_code_piece",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> LAUNCH_CODE = ITEMS.register("launch_code",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> LAUNCH_KEY = ITEMS.register("launch_key",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> MISSILE_GENERIC = ITEMS.register("missile_generic",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> MISSILE_STRONG = ITEMS.register("missile_strong",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> MISSILE_BURST = ITEMS.register("missile_burst",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> MISSILE_INCENDIARY = ITEMS.register("missile_incendiary",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> MISSILE_INCENDIARY_STRONG = ITEMS.register("missile_incendiary_strong",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> MISSILE_INFERNO = ITEMS.register("missile_inferno",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> MISSILE_CLUSTER = ITEMS.register("missile_cluster",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> MISSILE_CLUSTER_STRONG = ITEMS.register("missile_cluster_strong",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> MISSILE_RAIN = ITEMS.register("missile_rain",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> MISSILE_BUSTER = ITEMS.register("missile_buster",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> MISSILE_BUSTER_STRONG = ITEMS.register("missile_buster_strong",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> MISSILE_DRILL = ITEMS.register("missile_drill",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> MISSILE_N2 = ITEMS.register("missile_n2",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> MISSILE_NUCLEAR = ITEMS.register("missile_nuclear",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> MISSILE_NUCLEAR_CLUSTER = ITEMS.register("missile_nuclear_cluster",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> MISSILE_VOLCANO = ITEMS.register("missile_volcano",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> MISSILE_ENDO = ITEMS.register("missile_endo",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> MISSILE_EXO = ITEMS.register("missile_exo",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> MISSILE_SHUTTLE = ITEMS.register("missile_shuttle",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> MISSILE_DOOMSDAY = ITEMS.register("missile_doomsday",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> MISSILE_DOOMSDAY_RUSTED = ITEMS.register("missile_doomsday_rusted",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> MISSILE_TAINT = ITEMS.register("missile_taint",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> MISSILE_MICRO = ITEMS.register("missile_micro",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> MISSILE_BHOLE = ITEMS.register("missile_bhole",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> MISSILE_SCHRABIDIUM = ITEMS.register("missile_schrabidium",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> MISSILE_EMP = ITEMS.register("missile_emp",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> MISSILE_EMP_STRONG = ITEMS.register("missile_emp_strong",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> MISSILE_ANTI_BALLISTIC = ITEMS.register("missile_anti_ballistic",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> MISSILE_CARRIER = ITEMS.register("missile_carrier",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> MISSILE_DECOY = ITEMS.register("missile_decoy",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> MISSILE_STEALTH = ITEMS.register("missile_stealth",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> MISSILE_SOYUZ_LANDER = ITEMS.register("missile_soyuz_lander",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> MISSILE_SOYUZ = ITEMS.register("missile_soyuz",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> MIKE_DEUT = ITEMS.register("mike_deut",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> MP_THRUSTER_10_KEROSENE = ITEMS.register("mp_thruster_10_kerosene",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> MP_THRUSTER_10_SOLID = ITEMS.register("mp_thruster_10_solid",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> MP_THRUSTER_10_XENON = ITEMS.register("mp_thruster_10_xenon",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> MP_THRUSTER_15_KEROSENE = ITEMS.register("mp_thruster_15_kerosene",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> MP_THRUSTER_15_KEROSENE_DUAL = ITEMS.register("mp_thruster_15_kerosene_dual",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> MP_THRUSTER_15_KEROSENE_TRIPLE = ITEMS.register("mp_thruster_15_kerosene_triple",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> MP_THRUSTER_15_SOLID = ITEMS.register("mp_thruster_15_solid",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> MP_THRUSTER_15_SOLID_HEXDECUPLE = ITEMS.register("mp_thruster_15_solid_hexdecuple",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> MP_THRUSTER_15_HYDROGEN = ITEMS.register("mp_thruster_15_hydrogen",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> MP_THRUSTER_15_HYDROGEN_DUAL = ITEMS.register("mp_thruster_15_hydrogen_dual",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> MP_THRUSTER_15_BALEFIRE_SHORT = ITEMS.register("mp_thruster_15_balefire_short",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> MP_THRUSTER_15_BALEFIRE = ITEMS.register("mp_thruster_15_balefire",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> MP_THRUSTER_15_BALEFIRE_LARGE = ITEMS.register("mp_thruster_15_balefire_large",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> MP_THRUSTER_15_BALEFIRE_LARGE_RAD = ITEMS.register("mp_thruster_15_balefire_large_rad",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> MP_THRUSTER_20_KEROSENE = ITEMS.register("mp_thruster_20_kerosene",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> MP_THRUSTER_20_KEROSENE_DUAL = ITEMS.register("mp_thruster_20_kerosene_dual",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> MP_THRUSTER_20_KEROSENE_TRIPLE = ITEMS.register("mp_thruster_20_kerosene_triple",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> MP_THRUSTER_20_SOLID = ITEMS.register("mp_thruster_20_solid",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> MP_THRUSTER_20_SOLID_MULTI = ITEMS.register("mp_thruster_20_solid_multi",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> MP_THRUSTER_20_SOLID_MULTIER = ITEMS.register("mp_thruster_20_solid_multier",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> MP_STABILITY_10_FLAT = ITEMS.register("mp_stability_10_flat",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> MP_STABILITY_10_CRUISE = ITEMS.register("mp_stability_10_cruise",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> MP_STABILITY_10_SPACE = ITEMS.register("mp_stability_10_space",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> MP_STABILITY_15_FLAT = ITEMS.register("mp_stability_15_flat",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> MP_STABILITY_15_THIN = ITEMS.register("mp_stability_15_thin",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> MP_STABILITY_15_SOYUZ = ITEMS.register("mp_stability_15_soyuz",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> MP_FUSELAGE_10_KEROSENE = ITEMS.register("mp_fuselage_10_kerosene",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> MP_FUSELAGE_10_SOLID = ITEMS.register("mp_fuselage_10_solid",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> MP_FUSELAGE_10_XENON = ITEMS.register("mp_fuselage_10_xenon",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> MP_FUSELAGE_10_LONG_KEROSENE = ITEMS.register("mp_fuselage_10_long_kerosene",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> MP_FUSELAGE_10_LONG_SOLID = ITEMS.register("mp_fuselage_10_long_solid",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> MP_FUSELAGE_10_15_KEROSENE = ITEMS.register("mp_fuselage_10_15_kerosene",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> MP_FUSELAGE_10_15_SOLID = ITEMS.register("mp_fuselage_10_15_solid",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> MP_FUSELAGE_10_15_HYDROGEN = ITEMS.register("mp_fuselage_10_15_hydrogen",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> MP_FUSELAGE_10_15_BALEFIRE = ITEMS.register("mp_fuselage_10_15_balefire",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> MP_FUSELAGE_15_KEROSENE = ITEMS.register("mp_fuselage_15_kerosene",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> MP_FUSELAGE_15_SOLID = ITEMS.register("mp_fuselage_15_solid",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> MP_FUSELAGE_15_HYDROGEN = ITEMS.register("mp_fuselage_15_hydrogen",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> MP_FUSELAGE_15_BALEFIRE = ITEMS.register("mp_fuselage_15_balefire",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> MP_FUSELAGE_15_20_KEROSENE = ITEMS.register("mp_fuselage_15_20_kerosene",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> MP_FUSELAGE_15_20_SOLID = ITEMS.register("mp_fuselage_15_20_solid",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> MP_WARHEAD_10_HE = ITEMS.register("mp_warhead_10_he",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> MP_WARHEAD_10_INCENDIARY = ITEMS.register("mp_warhead_10_incendiary",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> MP_WARHEAD_10_BUSTER = ITEMS.register("mp_warhead_10_buster",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> MP_WARHEAD_10_NUCLEAR = ITEMS.register("mp_warhead_10_nuclear",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> MP_WARHEAD_10_NUCLEAR_LARGE = ITEMS.register("mp_warhead_10_nuclear_large",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> MP_WARHEAD_10_TAINT = ITEMS.register("mp_warhead_10_taint",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> MP_WARHEAD_10_CLOUD = ITEMS.register("mp_warhead_10_cloud",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> MP_WARHEAD_15_HE = ITEMS.register("mp_warhead_15_he",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> MP_WARHEAD_15_INCENDIARY = ITEMS.register("mp_warhead_15_incendiary",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> MP_WARHEAD_15_NUCLEAR = ITEMS.register("mp_warhead_15_nuclear",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> MP_WARHEAD_15_THERMO = ITEMS.register("mp_warhead_15_thermo",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> MP_WARHEAD_15_MIRV = ITEMS.register("mp_warhead_15_mirv",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> MP_WARHEAD_15_BOXCAR = ITEMS.register("mp_warhead_15_boxcar",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> MP_WARHEAD_15_N2 = ITEMS.register("mp_warhead_15_n2",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> MP_WARHEAD_15_BALEFIRE = ITEMS.register("mp_warhead_15_balefire",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> MP_WARHEAD_15_VOLCANO = ITEMS.register("mp_warhead_15_volcano",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> MP_WARHEAD_15_TURBINE = ITEMS.register("mp_warhead_15_turbine",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> MP_CHIP_1 = ITEMS.register("mp_c_1",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> MP_CHIP_2 = ITEMS.register("mp_c_2",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> MP_CHIP_3 = ITEMS.register("mp_c_3",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> MP_CHIP_4 = ITEMS.register("mp_c_4",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> MP_CHIP_5 = ITEMS.register("mp_c_5",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> MISSILE_SKIN_CAMO = ITEMS.register("missile_skin_camo",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> MISSILE_SKIN_DESERT = ITEMS.register("missile_skin_desert",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> MISSILE_SKIN_FLAMES = ITEMS.register("missile_skin_flames",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> MISSILE_SKIN_MANLY_PINK = ITEMS.register("missile_skin_manly_pink",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> MISSILE_SKIN_ORANGE_INSULATION = ITEMS.register("missile_skin_orange_insulation",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> MISSILE_SKIN_SLEEK = ITEMS.register("missile_skin_sleek",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> MISSILE_SKIN_SOVIET_GLORY = ITEMS.register("missile_skin_soviet_glory",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> MISSILE_SKIN_SOVIET_STANK = ITEMS.register("missile_skin_soviet_stank",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> MISSILE_SKIN_METAL = ITEMS.register("missile_skin_metal",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> MISSILE_CUSTOM = ITEMS.register("missile_custom",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> SLIDING_BLAST_DOOR_SKIN0 = ITEMS.register("sliding_blast_door_skin0",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> SLIDING_BLAST_DOOR_SKIN1 = ITEMS.register("sliding_blast_door_skin1",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> SLIDING_BLAST_DOOR_SKIN2 = ITEMS.register("sliding_blast_door_skin2",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> DOOR_METAL = ITEMS.register("door_metal",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> DOOR_OFFICE = ITEMS.register("door_office",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> DOOR_BUNKER = ITEMS.register("door_bunker",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> DOOR_RED = ITEMS.register("door_red",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> RECORD_LC = ITEMS.register("lc",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> RECORD_SS = ITEMS.register("ss",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> RECORD_VC = ITEMS.register("vc",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> RECORD_GLASS = ITEMS.register("glass",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> FLAME_PONY = ITEMS.register("flame_pony",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> FLAME_CONSPIRACY = ITEMS.register("flame_conspiracy",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> FLAME_POLITICS = ITEMS.register("flame_politics",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> FLAME_OPINION = ITEMS.register("flame_opinion",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> POLAROID = ITEMS.register("polaroid",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> GLITCH = ITEMS.register("glitch",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> BURNT_BARK = ITEMS.register("burnt_bark",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> LETTER = ITEMS.register("letter",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> BOOK_SECRET = ITEMS.register("book_secret",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> CLAY_TABLET = ITEMS.register("clay_tablet",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> BOOK_OF_ = ITEMS.register("book_of_",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> BOOK_LEMEGETON = ITEMS.register("book_lemegeton",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> PAGE_OF_ = ITEMS.register("page_of_",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> CRYSTAL_HORN = ITEMS.register("crystal_horn",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> CRYSTAL_CHARRED = ITEMS.register("crystal_charred",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> WATCH = ITEMS.register("watch",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> APPLE_EUPHEMIUM = ITEMS.register("apple_euphemium",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> WAND = ITEMS.register("wand_k",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> WAND_S = ITEMS.register("wand_s",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> WAND_D = ITEMS.register("wand_d",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> ROD_OF_DISCORD = ITEMS.register("rod_of_discord",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> ANALYZER = ITEMS.register("analyzer",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> DEFUSER = ITEMS.register("defuser",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> DEFUSER_DESH = ITEMS.register("defuser_desh",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> MELTDOWN_TOOL = ITEMS.register("meltdown_tool",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> ENTANGLEMENT_KIT = ITEMS.register("entanglement_kit",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> ACHIEVEMENT_ICON = ITEMS.register("achievement_icon",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> BROKEN_ITEM = ITEMS.register("broken_item",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> UNDEFINED = ITEMS.register("undefined",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> MYSTERYSHOVEL = ITEMS.register("mysteryshovel",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> MEMORY = ITEMS.register("memory",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> CONVEYOR_WAND = ITEMS.register("conveyor_wand",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> PART_GENERIC = ITEMS.register("part_generic",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> ITEM_SECRET = ITEMS.register("item_secret",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> ITEM_EXPENSIVE = ITEMS.register("item_expensive",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> DRAX = ITEMS.register("drax",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> DRAX_MK2 = ITEMS.register("drax_mk2",
        () -> new Item(new Item.Properties()));

public static final DeferredItem<Item> DRAX_MK3 = ITEMS.register("drax_mk3",
        () -> new Item(new Item.Properties()));

// =====================================================
// End of missing items
// Total: 1127 items
// =====================================================
}