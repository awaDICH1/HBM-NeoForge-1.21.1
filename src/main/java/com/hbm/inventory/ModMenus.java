package com.hbm.inventory;

import com.hbm.Tags;
import com.hbm.inventory.container.ContainerAshpit;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

/**
 * 菜单（GUI 容器）注册中心。
 * 原 1.12.2：NetworkRegistry.INSTANCE.registerGuiHandler(instance, new GuiHandler()) 按 int id 分发。
 * 1.21.1：MenuType 注册 + MenuProvider + player.openMenu()，GuiHandler 整体删除。
 *
 * 1.21.1 变更（21.1.128）：
 *   - MenuType 构造器为 (MenuSupplier, FeatureFlagSet)，不带 StreamCodec；
 *     modded 菜单带 extraData（如 BlockPos）用 IMenuTypeExtension.create(IContainerFactory)：
 *     客户端经 (int, Inventory, RegistryFriendlyByteBuf) 工厂重建，服务端 openMenu(provider, buf -> ...) 写数据。
 */
public class ModMenus {

    public static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(Registries.MENU, Tags.MODID);

    // ===== 灰烬槽菜单 =====
    // 服务端打开时经 openMenu(provider, buf -> buf.writeBlockPos(pos)) 写 extraData；
    // 客户端用 IContainerFactory.create(int, Inventory, RegistryFriendlyByteBuf) 重建菜单。
    public static final DeferredHolder<MenuType<?>, MenuType<ContainerAshpit>> ASHPIT = MENUS.register("ashpit",
            () -> IMenuTypeExtension.create((windowId, inv, buf) -> new ContainerAshpit(windowId, inv, (RegistryFriendlyByteBuf) buf)));

    // ===== P5.2 Barrel 三件套 =====
    public static final DeferredHolder<MenuType<?>, MenuType<com.hbm.inventory.container.ContainerBarrel>> BARREL = MENUS.register("barrel",
            () -> IMenuTypeExtension.create((windowId, inv, buf) -> new com.hbm.inventory.container.ContainerBarrel(windowId, inv,
                    (com.hbm.tileentity.machine.TileEntityBarrel) inv.player.level().getBlockEntity(buf.readBlockPos()))));

    // ===== P5.2 GasCentrifuge 三件套 =====
    public static final DeferredHolder<MenuType<?>, MenuType<com.hbm.inventory.container.ContainerGasCentrifuge>> GAS_CENT = MENUS.register("gas_cent",
            () -> IMenuTypeExtension.create((windowId, inv, buf) -> new com.hbm.inventory.container.ContainerGasCentrifuge(windowId, inv,
                    (com.hbm.tileentity.machine.TileEntityGasCentrifuge) inv.player.level().getBlockEntity(buf.readBlockPos()))));

    // ===== P5.7 FluidTank 三件套 =====
    public static final DeferredHolder<MenuType<?>, MenuType<com.hbm.inventory.container.ContainerFluidTank>> FLUID_TANK = MENUS.register("fluid_tank",
            () -> IMenuTypeExtension.create((windowId, inv, buf) -> new com.hbm.inventory.container.ContainerFluidTank(windowId, inv,
                    (com.hbm.tileentity.machine.TileEntityFluidTank) inv.player.level().getBlockEntity(buf.readBlockPos()))));

    // ===== P5.7 RBMKConsole 三件套 =====
    public static final DeferredHolder<MenuType<?>, MenuType<com.hbm.inventory.container.ContainerRBMKConsole>> RBMK_CONSOLE = MENUS.register("rbmk_console",
            () -> IMenuTypeExtension.create((windowId, inv, buf) -> new com.hbm.inventory.container.ContainerRBMKConsole(windowId, inv,
                    (com.hbm.tileentity.machine.TileEntityRBMKConsole) inv.player.level().getBlockEntity(buf.readBlockPos()))));

    // ===== P5.8 Compressor 三件套 =====
    public static final DeferredHolder<MenuType<?>, MenuType<com.hbm.inventory.container.ContainerCompressor>> COMPRESSOR = MENUS.register("compressor",
            () -> IMenuTypeExtension.create((windowId, inv, buf) -> new com.hbm.inventory.container.ContainerCompressor(windowId, inv,
                    (com.hbm.tileentity.machine.TileEntityCompressor) inv.player.level().getBlockEntity(buf.readBlockPos()))));

    // ===== P5.10 ChemicalReactor 三件套 =====
    public static final DeferredHolder<MenuType<?>, MenuType<com.hbm.inventory.container.ContainerChemicalReactor>> CHEMICAL_REACTOR = MENUS.register("chemical_reactor",
            () -> IMenuTypeExtension.create((windowId, inv, buf) -> new com.hbm.inventory.container.ContainerChemicalReactor(windowId, inv,
                    (com.hbm.tileentity.machine.TileEntityChemicalReactor) inv.player.level().getBlockEntity(buf.readBlockPos()))));

    // ===== P5.11 ArcFurnace 三件套 =====
    public static final DeferredHolder<MenuType<?>, MenuType<com.hbm.inventory.container.ContainerArcFurnace>> ARC_FURNACE = MENUS.register("arc_furnace",
            () -> IMenuTypeExtension.create((windowId, inv, buf) -> new com.hbm.inventory.container.ContainerArcFurnace(windowId, inv,
                    (com.hbm.tileentity.machine.TileEntityArcFurnace) inv.player.level().getBlockEntity(buf.readBlockPos()))));

    // ===== P5.12 Centrifuge 三件套 =====
    public static final DeferredHolder<MenuType<?>, MenuType<com.hbm.inventory.container.ContainerCentrifuge>> CENTRIFUGE = MENUS.register("centrifuge",
            () -> IMenuTypeExtension.create((windowId, inv, buf) -> new com.hbm.inventory.container.ContainerCentrifuge(windowId, inv,
                    (com.hbm.tileentity.machine.TileEntityCentrifuge) inv.player.level().getBlockEntity(buf.readBlockPos()))));

    // ===== P5.13 Crusher 三件套 =====
    public static final DeferredHolder<MenuType<?>, MenuType<com.hbm.inventory.container.ContainerCrusher>> CRUSHER = MENUS.register("crusher",
            () -> IMenuTypeExtension.create((windowId, inv, buf) -> new com.hbm.inventory.container.ContainerCrusher(windowId, inv,
                    (com.hbm.tileentity.machine.TileEntityCrusher) inv.player.level().getBlockEntity(buf.readBlockPos()))));

    // ===== P5.14 FluidReactor 三件套 =====
    public static final DeferredHolder<MenuType<?>, MenuType<com.hbm.inventory.container.ContainerFluidReactor>> FLUID_REACTOR = MENUS.register("fluid_reactor",
            () -> IMenuTypeExtension.create((windowId, inv, buf) -> new com.hbm.inventory.container.ContainerFluidReactor(windowId, inv,
                    (com.hbm.tileentity.machine.TileEntityFluidReactor) inv.player.level().getBlockEntity(buf.readBlockPos()))));

    // ===== P5.14 Assembler 三件套 =====
    public static final DeferredHolder<MenuType<?>, MenuType<com.hbm.inventory.container.ContainerAssembler>> ASSEMBLER = MENUS.register("assembler",
            () -> IMenuTypeExtension.create((windowId, inv, buf) -> new com.hbm.inventory.container.ContainerAssembler(windowId, inv,
                    (com.hbm.tileentity.machine.TileEntityAssembler) inv.player.level().getBlockEntity(buf.readBlockPos()))));

    // ===== P5.15 RBMKReactor 三件套 =====
    public static final DeferredHolder<MenuType<?>, MenuType<com.hbm.inventory.container.ContainerRBMKReactor>> RBMK_REACTOR = MENUS.register("rbmk_reactor",
            () -> IMenuTypeExtension.create((windowId, inv, buf) -> new com.hbm.inventory.container.ContainerRBMKReactor(windowId, inv,
                    (com.hbm.tileentity.machine.TileEntityRBMKReactor) inv.player.level().getBlockEntity(buf.readBlockPos()))));

    // ===== P5.15 HeatExchanger 三件套 =====
    public static final DeferredHolder<MenuType<?>, MenuType<com.hbm.inventory.container.ContainerHeatExchanger>> HEAT_EXCHANGER = MENUS.register("heat_exchanger",
            () -> IMenuTypeExtension.create((windowId, inv, buf) -> new com.hbm.inventory.container.ContainerHeatExchanger(windowId, inv,
                    (com.hbm.tileentity.machine.TileEntityHeatExchanger) inv.player.level().getBlockEntity(buf.readBlockPos()))));

    // ===== P5.16 ParticleAccelerator 三件套 =====
    public static final DeferredHolder<MenuType<?>, MenuType<com.hbm.inventory.container.ContainerParticleAccelerator>> PARTICLE_ACCELERATOR = MENUS.register("particle_accelerator",
            () -> IMenuTypeExtension.create((windowId, inv, buf) -> new com.hbm.inventory.container.ContainerParticleAccelerator(windowId, inv,
                    (com.hbm.tileentity.machine.TileEntityParticleAccelerator) inv.player.level().getBlockEntity(buf.readBlockPos()))));

    // ===== P5.16 Laser 三件套 =====
    public static final DeferredHolder<MenuType<?>, MenuType<com.hbm.inventory.container.ContainerLaser>> LASER = MENUS.register("laser",
            () -> IMenuTypeExtension.create((windowId, inv, buf) -> new com.hbm.inventory.container.ContainerLaser(windowId, inv,
                    (com.hbm.tileentity.machine.TileEntityLaser) inv.player.level().getBlockEntity(buf.readBlockPos()))));

    // ===== P5 迁移模板 =====
    //
    //   public static final DeferredHolder<MenuType<?>, MenuType<ContainerXyz>> XYZ =
    //           MENUS.register("xyz", () -> new MenuType<>(ContainerXyz::new, FeatureFlags.VANILLA_SET));
    //
    // 客户端屏幕绑定（ClientSetup 中）：
    //   MenuScreens.register(ModMenus.XYZ.get(), ScreenXyz::new);
}
