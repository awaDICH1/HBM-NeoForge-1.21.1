package com.hbm.main;

import com.hbm.Tags;
import com.hbm.blocks.ModBlocks;
import com.hbm.blocks.fluid.ModFluids;
import com.hbm.config.HBMConfig;
import com.hbm.creativetabs.ModCreativeTabs;
import com.hbm.entity.ModEntities;
import com.hbm.inventory.ModMenus;
import com.hbm.items.ModItems;
import com.hbm.main.client.ClientSetup;
import com.hbm.network.ModNetwork;
import com.hbm.potion.ModMobEffects;
import com.hbm.sound.ModSounds;
import com.hbm.tileentity.ModTileEntities;
import com.mojang.logging.LogUtils;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.neoforge.common.NeoForge;
import org.slf4j.Logger;

/**
 * NTM 1.21.1 NeoForge 主类。
 *
 * 迁移自 1.12.2 的 com.hbm.main.MainRegistry：
 *   @Mod + @SidedProxy + @EventHandler 生命周期 → @Mod + IEventBus 构造器 + FMLEnvironment.dist 分流
 *   MinecraftForge.EVENT_BUS.register(...) → NeoForge.EVENT_BUS.register(...)
 */
@Mod(Tags.MODID)
public class HBM {

    public static HBM instance;
    public static final Logger LOGGER = LogUtils.getLogger();

    public HBM(IEventBus modBus, ModContainer modContainer) {
        instance = this;

        // ===== 配置（原 MainRegistry.reloadConfig 的 Configuration → ModConfigSpec） =====
        // 生成文件：config/hbm/hbm.toml
        modContainer.registerConfig(ModConfig.Type.COMMON, HBMConfig.SPEC, "hbm/hbm.toml");
        modBus.addListener(HBMConfig::onLoad);

        // ===== 注册表挂载（原 preInit 中 ForgeRegistries.*.register / AutoRegistry 的替代） =====
        // 流体数据层先行（原 MainRegistry.preInit 的 Fluids.init()）——纯静态数据，不触碰注册表；
        // NeoForge Fluid/FluidType 注册（ModFluids 桥接）在 RegisterEvent 期读取 Fluids.metaOrder
        com.hbm.inventory.fluid.Fluids.init();
        // P4.2: 矿辞注册（原 MainRegistry.preInit 的 OreDictManager.registerOres()）已迁至
        // CommonSetup.commonSetup()（FMLCommonSetupEvent + enqueueWork）：registerOres() 内部对
        // DeferredBlock/DeferredItem 调用 .get()，构造器期注册表尚未绑定会抛 "unbound value" NPE。
        ModBlocks.BLOCKS.register(modBus);
        ModBlocks.ITEMS.register(modBus);
        ModItems.ITEMS.register(modBus);
        ModFluids.FLUIDS.register(modBus);
        ModFluids.FLUID_TYPES.register(modBus);
        ModEntities.ENTITY_TYPES.register(modBus);
        ModTileEntities.BLOCK_ENTITY_TYPES.register(modBus);
        ModMenus.MENUS.register(modBus);
        ModCreativeTabs.TABS.register(modBus);
        ModSounds.SOUNDS.register(modBus);
        ModMobEffects.MOB_EFFECTS.register(modBus);
        com.hbm.particle.ModParticleTypes.PARTICLE_TYPES.register(modBus);

        // ===== 网络通道（原 PacketDispatcher.registerPackets；NetworkChannel 类加载时自注册） =====
        ModNetwork.init();

        // ===== Mod 总线生命周期（原 MainRegistry 的 @EventHandler 方法） =====
        // CommonSetup / ModEvents 已通过 @EventBusSubscriber(Bus.MOD) 自动挂载，此处不再 addListener，
        // 避免双重注册。原逻辑对应：
        //   CommonSetup.commonSetup        ← 原 preInit/init 的注册类初始化
        //   ModEvents.registerCapabilities ← 原 CapabilityManager.INSTANCE.register(...)
        //   ModEvents.entityAttributes     ← 原 SharedMonsterAttributes 相关
        //   ModEvents.loadComplete         ← 原 MainRegistry.loadComplete(FMLLoadCompleteEvent)

        // ===== 游戏总线（原 MinecraftForge.EVENT_BUS.register(new ModEventHandler())） =====
        NeoForge.EVENT_BUS.register(new ModEventHandler());

        // ===== 客户端分流（原 @SidedProxy；NeoForge 已弃用 DistExecutor，官方推荐 FMLEnvironment.dist 判断） =====
        if (FMLEnvironment.dist.isClient()) {
            modBus.addListener(ClientSetup::clientSetup);   // 原 ClientProxy.preInit/init/postInit
            modBus.addListener(ClientSetup::registerScreens);   // 菜单屏幕绑定（RegisterMenuScreensEvent）
        }
        LOGGER.info("{} v{} loaded on {}", Tags.MODNAME, Tags.VERSION, FMLEnvironment.dist);
    }
}
