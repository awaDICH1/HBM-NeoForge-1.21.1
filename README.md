# HBM-NeoForge — HBM Nuclear Tech CE → NeoForge 1.21.1 移植工程（P0 骨架）

## 环境要求
- JDK 21（本机：`D:\Program Files\Zulu\zulu-21`，`JAVA_HOME` 已设置）
- 网络可访问 `maven.neoforged.net`（首次构建会下载 NeoForge/Minecraft 依赖）

## 版本
| 组件 | 版本 | 说明 |
|---|---|---|
| ModDevGradle | 2.0.138 | plugins.gradle.org 最新稳定版 |
| NeoForge | 21.1.128 | 1.21.1 官方 MDK 构建号，可升级 |
| Gradle wrapper | 9.2.1 | 复用 CE 仓库的 wrapper |

## 常用命令（在工程根目录执行）
```bat
gradlew runClient     :: 启动开发客户端（P0 验收：进入主菜单）
gradlew runServer     :: 启动开发服务器
gradlew build         :: 构建 jar（产物在 build/libs）
gradlew runData       :: 运行 DataGen（输出到 src/generated/resources）
gradlew --refresh-dependencies
```

## 目录结构（P0）
```
src/main/java/com/hbm/
├── Tags.java                    常量（MODID/MODNAME/VERSION）
├── main/HBM.java                @Mod 主类（IEventBus 构造器 + Dist 分流）
├── main/CommonSetup.java        Mod 总线通用初始化（原 preInit/init 逻辑落点）
├── main/ModEvents.java          Mod 总线订阅（能力/属性/加载完成）
├── main/ModEventHandler.java    游戏总线订阅（原 MinecraftForge.EVENT_BUS）
├── main/client/ClientSetup.java 客户端初始化（原 ClientProxy）
├── blocks/ModBlocks.java        DeferredRegister.Blocks/Items（方块+方块物品）
├── items/ModItems.java          DeferredRegister.Items
├── entity/ModEntities.java      DeferredRegister ENTITY_TYPE
├── tileentity/ModTileEntities.java  DeferredRegister BLOCK_ENTITY_TYPE
├── inventory/ModMenus.java      DeferredRegister MENU
├── creativetabs/ModCreativeTabs.java  DeferredRegister CREATIVE_MODE_TAB（11 个栏）
├── sound/ModSounds.java         DeferredRegister SOUND_EVENT
├── potion/ModMobEffects.java    DeferredRegister MOB_EFFECT
└── blocks/fluid/ModFluids.java  DeferredRegister FLUID + FLUID_TYPE
```

## 里程碑对照
- P0（当前）：本骨架，验收 `gradlew runClient` 进入主菜单。
- P1：垂直切片（沥青方块 + 锭物品 + 基础机器 TE + GUI + 1 个数据包）。
- P2–P10：见移植参考报告 §3。

## 已知待办
- 创造栏图标（P3 用 ModItems 代表物品填充）。
- 网络通道（NetworkChannel）在 P1 引入。
- 原 13,412 个资产文件尚未复制进本工程（P3 起按需分批复制到 src/main/resources/assets/hbm）。
