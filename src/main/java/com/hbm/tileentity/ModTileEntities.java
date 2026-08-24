package com.hbm.tileentity;

import com.hbm.Tags;
import com.hbm.blocks.ModBlocks;
import com.hbm.tileentity.machine.TileEntityAshpit;
import com.hbm.tileentity.machine.TileEntityBarrel;
import com.hbm.tileentity.machine.TileEntityCompressor;
import com.hbm.tileentity.machine.TileEntityFluidTank;
import com.hbm.tileentity.machine.TileEntityGasCentrifuge;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

/**
 * 方块实体（TE）注册中心。
 * 原 1.12.2 由 @AutoRegister 注解处理器生成注册；1.21.1 改用本 DeferredRegister。
 */
public class ModTileEntities {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES = DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, Tags.MODID);

    // ===== 灰烬槽（原 1.12.2 @AutoRegister → GameRegistry.registerTileEntity） =====
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<TileEntityAshpit>> ASHPIT =
            BLOCK_ENTITY_TYPES.register("ashpit",
                    () -> BlockEntityType.Builder.of(TileEntityAshpit::create, ModBlocks.ASHPIT.get()).build(null));

    // ===== P5.2 Barrel 三件套 =====
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<TileEntityBarrel>> BARREL =
            BLOCK_ENTITY_TYPES.register("barrel",
                    () -> BlockEntityType.Builder.of(TileEntityBarrel::create, ModBlocks.BARREL.get()).build(null));

    // ===== P5.2 GasCentrifuge 三件套 =====
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<TileEntityGasCentrifuge>> GAS_CENT =
            BLOCK_ENTITY_TYPES.register("gas_cent",
                    () -> BlockEntityType.Builder.of(TileEntityGasCentrifuge::create, ModBlocks.GAS_CENT.get()).build(null));

    // ===== P5.5 Compressor 三件套 =====
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<TileEntityCompressor>> COMPRESSOR_TE =
            BLOCK_ENTITY_TYPES.register("compressor",
                    () -> BlockEntityType.Builder.of(TileEntityCompressor::create, ModBlocks.COMPRESSOR.get()).build(null));

    // ===== P5.7 FluidTank 三件套 =====
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<TileEntityFluidTank>> FLUID_TANK_TE =
            BLOCK_ENTITY_TYPES.register("fluid_tank",
                    () -> BlockEntityType.Builder.of(TileEntityFluidTank::create, ModBlocks.FLUID_TANK.get()).build(null));

    // ===== P5.7 RBMKConsole 三件套 =====
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<com.hbm.tileentity.machine.TileEntityRBMKConsole>> RBMK_CONSOLE_TE =
            BLOCK_ENTITY_TYPES.register("rbmk_console",
                    () -> BlockEntityType.Builder.of(com.hbm.tileentity.machine.TileEntityRBMKConsole::create, ModBlocks.RBMK_CONSOLE.get()).build(null));

    // ===== P5.10 ChemicalReactor 三件套 =====
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<com.hbm.tileentity.machine.TileEntityChemicalReactor>> CHEMICAL_REACTOR_TE =
            BLOCK_ENTITY_TYPES.register("chemical_reactor",
                    () -> BlockEntityType.Builder.of(com.hbm.tileentity.machine.TileEntityChemicalReactor::create, ModBlocks.CHEMICAL_REACTOR.get()).build(null));

    // ===== P5.11 ArcFurnace 三件套 =====
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<com.hbm.tileentity.machine.TileEntityArcFurnace>> ARC_FURNACE_TE =
            BLOCK_ENTITY_TYPES.register("arc_furnace",
                    () -> BlockEntityType.Builder.of(com.hbm.tileentity.machine.TileEntityArcFurnace::create, ModBlocks.ARC_FURNACE.get()).build(null));

    // ===== P5.12 Centrifuge 三件套 =====
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<com.hbm.tileentity.machine.TileEntityCentrifuge>> CENTRIFUGE_TE =
            BLOCK_ENTITY_TYPES.register("centrifuge",
                    () -> BlockEntityType.Builder.of(com.hbm.tileentity.machine.TileEntityCentrifuge::create, ModBlocks.CENTRIFUGE.get()).build(null));

    // ===== P5.13 Crusher 三件套 =====
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<com.hbm.tileentity.machine.TileEntityCrusher>> CRUSHER_TE =
            BLOCK_ENTITY_TYPES.register("crusher",
                    () -> BlockEntityType.Builder.of(com.hbm.tileentity.machine.TileEntityCrusher::create, ModBlocks.CRUSHER.get()).build(null));

    // ===== P5.14 FluidReactor 三件套 =====
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<com.hbm.tileentity.machine.TileEntityFluidReactor>> FLUID_REACTOR_TE =
            BLOCK_ENTITY_TYPES.register("fluid_reactor",
                    () -> BlockEntityType.Builder.of(com.hbm.tileentity.machine.TileEntityFluidReactor::create, ModBlocks.FLUID_REACTOR.get()).build(null));

    // ===== P5.14 Assembler 三件套 =====
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<com.hbm.tileentity.machine.TileEntityAssembler>> ASSEMBLER_TE =
            BLOCK_ENTITY_TYPES.register("assembler",
                    () -> BlockEntityType.Builder.of(com.hbm.tileentity.machine.TileEntityAssembler::create, ModBlocks.ASSEMBLER.get()).build(null));

    // ===== P5.15 RBMKReactor 三件套 =====
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<com.hbm.tileentity.machine.TileEntityRBMKReactor>> RBMK_REACTOR_TE =
            BLOCK_ENTITY_TYPES.register("rbmk_reactor",
                    () -> BlockEntityType.Builder.of(com.hbm.tileentity.machine.TileEntityRBMKReactor::create, ModBlocks.RBMK_REACTOR.get()).build(null));

    // ===== P5.15 HeatExchanger 三件套 =====
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<com.hbm.tileentity.machine.TileEntityHeatExchanger>> HEAT_EXCHANGER_TE =
            BLOCK_ENTITY_TYPES.register("heat_exchanger",
                    () -> BlockEntityType.Builder.of(com.hbm.tileentity.machine.TileEntityHeatExchanger::create, ModBlocks.HEAT_EXCHANGER.get()).build(null));

    // ===== P5.16 ParticleAccelerator 三件套 =====
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<com.hbm.tileentity.machine.TileEntityParticleAccelerator>> PARTICLE_ACCELERATOR_TE =
            BLOCK_ENTITY_TYPES.register("particle_accelerator",
                    () -> BlockEntityType.Builder.of(com.hbm.tileentity.machine.TileEntityParticleAccelerator::create, ModBlocks.PARTICLE_ACCELERATOR.get()).build(null));

    // ===== P5.16 Laser 三件套 =====
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<com.hbm.tileentity.machine.TileEntityLaser>> LASER_TE =
            BLOCK_ENTITY_TYPES.register("laser",
                    () -> BlockEntityType.Builder.of(com.hbm.tileentity.machine.TileEntityLaser::create, ModBlocks.LASER.get()).build(null));

    // ===== P5 迁移模板 =====
    //
    // 1.21.1：
    //   public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<TileEntityXyz>> XYZ =
    //           BLOCK_ENTITY_TYPES.register("te_xyz",
    //                   () -> BlockEntityType.Builder.of(TileEntityXyz::new, ModBlocks.XYZ.get()).build(null));
    //
    // 注意：1.20.5+ 的 readFromNBT/writeToNBT 改为 loadAdditional/saveAdditional，
    //       且签名带 HolderLookup.Provider 参数（NBT 键名保持不变以兼容旧存档）。
}
