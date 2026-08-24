package com.hbm.blocks;

import com.hbm.items.ModItems;
import net.minecraft.world.item.Item;

import javax.annotation.Nullable;

import static com.hbm.blocks.OreEnumUtil.OreEnum;

public class BlockEnums {

	public enum EnumStoneType {
		SULFUR,
		ASBESTOS,
		HEMATITE,
		MALACHITE,
		LIMESTONE,
		BAUXITE;

		public static final EnumStoneType[] VALUES = values();
	}

	public enum EnumMeteorType {
		IRON,
		COPPER,
		ALUMINIUM,
		RAREEARTH,
		COBALT;

		public static final EnumMeteorType[] VALUES = values();
	}

	public enum EnumStalagmiteType {
		SULFUR,
		ASBESTOS;

		public static final EnumStalagmiteType[] VALUES = values();
	}

	public enum EnumCMMaterials {
		STEEL,
		ALLOY,
		DESH,
		TCALLOY;

		public static final EnumCMMaterials[] VALUES = values();
	}

	public enum EnumCMEngines {
		STANDARD,
		DESH,
		BISMUTH;

		public static final EnumCMEngines[] VALUES = values();
	}

	public enum EnumCMCircuit {
		ALUMINIUM,
		COPPER,
		RED_COPPER,
		GOLD,
		SCHRABIDIUM;

		public static final EnumCMCircuit[] VALUES = values();
	}

	/** DECO / STRUCTURE ENUMS */
	//i apologize in advance

	public enum TileType {
		LARGE,
		SMALL;

		public static final TileType[] VALUES = values();
	}

	public enum LightstoneType {
		UNREFINED,
		TILE,
		BRICKS,
		BRICKS_CHISELED,
		CHISELED;

		public static final LightstoneType[] VALUES = values();
	}

	public enum DecoComputerEnum {
		IBM_300PL;

		public static final DecoComputerEnum[] VALUES = values();
	}

	public enum DecoCabinetEnum {
		GREEN,
		STEEL;

		public static final DecoCabinetEnum[] VALUES = values();
	}

    public enum DecoCRTEnum {
        CLEAN,
        BROKEN,
        BLINKING,
        BSOD;

		public static final DecoCRTEnum[] VALUES = values();
    }

    public enum DecoToasterEnum {
        IRON,
        STEEL,
        WOOD;

		public static final DecoToasterEnum[] VALUES = values();
    }

	public enum OreType {
		EMERALD ("emerald",OreEnum.EMERALD),
		DIAMOND ("diamond", OreEnum.DIAMOND),
		RADGEM ("radgem",OreEnum.RAD_GEM),
		//URANIUM_SCORCEHD ("uranium_scorched", null),
		URANIUM ("uranium", null),
		SCHRABIDIUM ("schrabidium", null);

		public static final OreType[] VALUES = values();

		public final String overlayTexture;
		public final OreEnum oreEnum;

		public String getName(){
			return overlayTexture;
		}

		OreType(String overlayTexture, @Nullable OreEnum oreEnum) {
			this.overlayTexture = overlayTexture;
			this.oreEnum = oreEnum;

		}
	}


	public enum EnumBasaltOreType {
		SULFUR,
		FLUORITE,
		ASBESTOS,
		GEM,
		MOLYSITE;

		public static final EnumBasaltOreType[] VALUES = values();

		public Item getDrop() {
			return switch (this) {
                 case SULFUR -> ModItems.SULFUR.get();
                 case FLUORITE -> ModItems.FLUORITE.get();
                 case ASBESTOS -> ModItems.INGOT_ASBESTOS.get();
                 case GEM -> ModItems.GEM_VOLCANIC.get();
                 case MOLYSITE -> ModItems.POWDER_MOLYSITE.get();
			};
		}

		public int getDropCount(int rand){
			return rand + 1;
		}
    }

	public enum EnumBlockCapType {
		NUKA,
		QUANTUM,
		RAD,
		SPARKLE,
		KORL,
		FRITZ;

		public static final EnumBlockCapType[] VALUES = values();

		public Item getDrop() {
			return switch (this) {
                 case NUKA -> ModItems.CAP_NUKA.get();
                 case QUANTUM -> ModItems.CAP_QUANTUM.get();
                 case RAD -> ModItems.CAP_RAD.get();
                 case SPARKLE -> ModItems.CAP_SPARKLE.get();
                 case KORL -> ModItems.CAP_KORL.get();
                 case FRITZ -> ModItems.CAP_FRITZ.get();
			};
		}

		public int getDropCount(){
			return 128;
		}
	}

    public enum LightType {
        INCANDESCENT,
        FLUORESCENT,
        HALOGEN
    }
}
