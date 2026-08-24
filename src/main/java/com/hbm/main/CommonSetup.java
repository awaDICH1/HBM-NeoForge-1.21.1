package com.hbm.main;

import com.hbm.Tags;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;

/**
 * 通用初始化（Mod 总线）。
 * 迁移自 1.12.2 MainRegistry.preInit()/init() 中不依赖注册表的部分。
 */
@EventBusSubscriber(modid = Tags.MODID, bus = EventBusSubscriber.Bus.MOD)
public class CommonSetup {

    @SubscribeEvent
    public static void commonSetup(final FMLCommonSetupEvent event) {
        // 原 MainRegistry.preInit()：
        //   HBMSoundHandler.init(); MaterialRegistry.init(); Fluids.init();
        //   OreDictManager.registerGroups(); MinecraftForge.EVENT_BUS.register(new OreDictManager());
        // 原 MainRegistry.init()：
        //   ModItems.init(); ModBlocks.init(); HbmWorld.mainRegistry(); ...
        // 需要主线程执行的任务包进 enqueueWork：
        event.enqueueWork(() -> {
            // 矿辞表填充：内部对 DeferredBlock/DeferredItem 调用 .get()，须在注册表绑定后（common setup）执行。
            // 原 MainRegistry.preInit 的 OreDictManager.registerOres()；从 HBM 构造器迁出以避免 "unbound value" NPE。
            com.hbm.inventory.OreDictManager.registerOres();
            com.hbm.inventory.recipes.SILEXRecipes.register();
            com.hbm.inventory.recipes.GasCentrifugeRecipes.register();
            com.hbm.inventory.recipes.RBMKFuelRecipes.registerRecipes();
            com.hbm.inventory.recipes.CompressorRecipes.register();
            com.hbm.inventory.recipes.CrusherRecipes.register();
            com.hbm.inventory.recipes.ArcFurnaceRecipes.register();
            com.hbm.inventory.recipes.AssemblyMachineRecipes.register();
            com.hbm.inventory.recipes.AnvilRecipes.register();
            HBM.LOGGER.info("HBM common setup: 矿辞 + 全配方系统注册完成");
        });
    }
}
