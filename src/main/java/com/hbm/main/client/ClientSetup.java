package com.hbm.main.client;

import com.hbm.inventory.ModMenus;
import com.hbm.inventory.gui.GUIAshpit;
import com.hbm.inventory.gui.GUIBarrel;
import com.hbm.inventory.gui.GUIGasCentrifuge;
import com.hbm.inventory.gui.MachineGUIs;
import com.hbm.main.HBM;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;

/**
 * 客户端初始化（替代 1.12.2 的 com.hbm.main.ClientProxy 中 preInit/init/postInit）。
 * 仅当 FMLEnvironment.dist == CLIENT 时由 HBM 构造器注册，专用服务器不会加载本类。
 */
public class ClientSetup {

    public static void clientSetup(final FMLClientSetupEvent event) {
        HBM.LOGGER.info("HBM client setup");
    }

    public static void registerScreens(final RegisterMenuScreensEvent event) {
        event.register(ModMenus.ASHPIT.get(), GUIAshpit::new);
        event.register(ModMenus.BARREL.get(), GUIBarrel::new);
        event.register(ModMenus.GAS_CENT.get(), GUIGasCentrifuge::new);

        event.register(ModMenus.COMPRESSOR.get(), MachineGUIs.Compressor::new);
        event.register(ModMenus.FLUID_TANK.get(), MachineGUIs.FluidTank::new);
        event.register(ModMenus.RBMK_CONSOLE.get(), MachineGUIs.RBMKConsole::new);
        event.register(ModMenus.CHEMICAL_REACTOR.get(), MachineGUIs.ChemicalReactor::new);
        event.register(ModMenus.ARC_FURNACE.get(), MachineGUIs.ArcFurnace::new);
        event.register(ModMenus.CENTRIFUGE.get(), MachineGUIs.Centrifuge::new);
        event.register(ModMenus.CRUSHER.get(), MachineGUIs.Crusher::new);
        event.register(ModMenus.FLUID_REACTOR.get(), MachineGUIs.FluidReactor::new);
        event.register(ModMenus.ASSEMBLER.get(), MachineGUIs.Assembler::new);
        event.register(ModMenus.RBMK_REACTOR.get(), MachineGUIs.RBMKReactor::new);
        event.register(ModMenus.HEAT_EXCHANGER.get(), MachineGUIs.HeatExchanger::new);
        event.register(ModMenus.PARTICLE_ACCELERATOR.get(), MachineGUIs.ParticleAccelerator::new);
        event.register(ModMenus.LASER.get(), MachineGUIs.Laser::new);
    }
}
