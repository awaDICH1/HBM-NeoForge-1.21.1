# P3 ModBlocks/ModItems 迁移状态

> 更新于 P5.1b-1（BufPacket payload + LoadedBase/TickingBase 重写 + AudioWrapper + ModSounds.METAL_IMPACT）。生成工具：`tools/gen_registries.py`、`tools/remap.ps1`（规则 73 词边界已修）、`tools/gen_config.py`、`tools/gen_items.ps1`、`tools/scan_network_leftovers.ps1`、`tools/scan_file.ps1`、`tools/scan_dir.ps1`、`tools/skeleton_odm.ps1`、`tools/check_fields.ps1`、`tools/diff_ingot.ps1`、`tools/ingot_probe.ps1`、`tools/gen_ingots.ps1`、`tools/roref_fields.ps1`、`tools/gen_roref.ps1`、`tools/special_fields.ps1`、`tools/gen_special.ps1`、`tools/fix_special2.ps1`、`tools/check_roref_ready.ps1`、`tools/rores_analyze.ps1`、`tools/port_rores.ps1`、`tools/port_rores2.ps1`、`tools/rores_modblocks.ps1`、`tools/gen_block_placeholders.ps1`、`tools/fix_fromone_get.ps1`、`tools/fix_rores_final.ps1`。

## P4.2 OreDictManager 评估（专项批）

**1424 行，非简单"矿辞→Tag 表"**：
- ~100 个 `KEY_*` 常量 + **DictFrame/DictGroup 框架**（形状前缀：`ingotX`/`dustX`/`anyX` + reRegistration 映射）
- 105 次 registerOre 调用 + 静态 import 注册（依赖 **MaterialShapes/Mats**（inventory.material，未迁移））
- `registerGroups()`（材料组，依赖 Mats）、`compensateMojangSpaghettiBullshit()`、`registerHazards()`（hazard ✓ 可保留）
- `onRegisterOre(OreRegisterEvent)`（1.12 事件）→ 删除（1.21.1 外部 mod 经 tag 注册）

**迁移顺序修正（用户 5 批次计划的依赖遗漏）**：
| 用户计划 | 问题 | 修正 |
|---|---|---|
| 批次 1 Fluids | 集群（30+ 文件） | 先做 uninos+fluidmk2 网络 |
| 批次 2 OreDictManager | **依赖材料系统（Mats/MaterialShapes）** | 先做 inventory.material |
| 批次 4 容器 | **容器引用 BlockEntity（P5）** | 仅 TE 无关容器可先做（如 P1 Ashpit 模式） |

**修正后的 P4 顺序**：(1) uninos+fluidmk2 → (2) inventory.material（Mats/NTMMaterial/MaterialShapes）→ (3) OreDictManager → (4) 配方系统 → (5) 容器/GUI（TE 无关子集先行）。

## P4.1 流体子系统（迁移计划）

### ⚠️ 事实修正
`Fluids` **不是枚举**——是带 100+ 静态 `FluidType` 字段的类（1195 行）；`FluidType`（318 行）是 HBM 自研流体类型类（非 NeoForge FluidType）。整个子系统约 30+ 文件、5 个外部依赖集群，需分步迁移。

### 依赖集群与迁移顺序
| 步骤 | 内容 | 阻塞/说明 |
|---|---|---|
| 1 | **uninos + fluidmk2 网络**（FluidNetMK2 / INetworkProvider / UniNodespace） | 自包含度高（P2 评估：只改映射名）；解锁 FluidType.NETWORK_PROVIDER + Fluids 监听器 |
| 2 | **trait 包（14 文件，一个编译单元）** | FluidTrait 静态块引用全部 24 特质；FT_Toxin/FT_Polluting/FT_VentRadiation/FT_Heatable/FT_Coolable 需 P5 桩（ArmorUtil/PollutionHandler/ChunkRadiationManager/Fluids）；FT_Flammable/Rocket/Combustible 的 BobMathUtil → 内联 |
| 3 | **FluidTankNTM**（inventory.fluid.tank） | 被 FluidTrait/FluidType 方法签名引用 |
| 4 | **Fluids + FluidType**（注册主体） | 依赖步骤 1-3 + Gson 配置 + EnumSymbol（P8 桩）+ FluidNTM（P5 桩） |
| 5 | **ModFluids 扩展**（NeoForge Fluid/FluidType 桥接 + 双向映射） | 用户简报的 getFluid/getEnum/getFlowing/getStill 设计在此实现；enableUniversalBucket 删除 |

### 已迁移
| 文件 | 说明 |
|---|---|
| inventory/fluid/FluidStack | 纯数据类（type/fill/pressure），零依赖 ✓ |

### ✅ P4.1 批次1：uninos + energymk2 + fluidmk2 网络（完成，32 文件）

| 包 | 文件 | 状态 |
|---|---|---|
| api/energymk2（9） | IEnergyHandlerMK2 / IEnergyReceiverMK2 / IEnergyProviderMK2 / IEnergyConductorMK2 / IEnergyConnectorMK2 / IEnergyConnectorBlock / IBatteryItem / Nodespace / PowerNetMK2 | ✅ 迁移+审查 |
| api/fluidmk2（1/13） | IFluidRegisterListener | ✅；其余 12 个阻塞延期（见依赖集群表） |
| uninos（8） | UniNodespace / GenNode / NodeNet / INetworkProvider / networkproviders{KlystronNetwork, PlasmaNetwork, RebarNetwork} | ✅ 迁移+审查；PneumaticNetwork 延期 |
| 支撑（3，新增） | util/Tuple（ObjectLongPair，PowerNetMK2 依赖）、interfaces/Spaghetti（注解）、api/tile/ILoadedTile（NodeNet 依赖） | ✅ |

**关键改写**：
- `IEnergyReceiverMK2`：Compat.getTileStandard → `world.getBlockEntity(new BlockPos(x,y,z))`；NetworkRegistry/AuxParticlePacketNT/HbmEffectNT/PacketThreading 导入删除；particleDebug 块 → TODO P8 桩（保留 `red` 变量语义注释）
- `IEnergyProviderMK2`：CapabilityEnergy.ENERGY → `Capabilities.EnergyStorage.BLOCK`（BlockCapability + Direction face）；粒子调试 → TODO P8
- `UniNodespace`：MainRegistry.logger → HBM.LOGGER；`DimensionManager.getWorlds()` → `ServerLifecycleHooks.getCurrentServer().getAllLevels()`（StreamSupport）；`GeneralConfig.enableThreadedNodeSpaceUpdate` 线程化更新保留
- `NodeNet`：`isInvalid()` → `isRemoved()`（BlockEntity）
- `IEnergyConductorMK2`：`Library.POS_X` 等 6 常量 → `ForgeDirection.EAST/WEST/UP/DOWN/SOUTH/NORTH`（Library 仍按 P5 迁移）
- `IBatteryItem`：删除 Library import（仅 javadoc @see 保留，无编译依赖）
- `PowerNetMK2`：泛型链 `NodeNet<IEnergyReceiverMK2, IEnergyProviderMK2, Nodespace.PowerNode, PowerNetMK2>` 完整；weightedShare 精度算法（LongMath/BigInteger）原样保留；update/sendPowerDiode/extractPowerDiode 三通道完整

**1.12 残留扫描**：3 包 × 22 正则模式（fml/NBTTag/EntityPlayer/TileEntity/MathHelper/EnumFacing/isRemote/getTileEntity/getEntityWorld/world.rand/world.provider/getPos()/getWorld()/Minecraft.getMinecraft/setDead/setBlockToAir/getItemDamage/hasTagCompound/getEntityData/EnumHand/ForgeRegistries 等）**零命中** ✅；扫描脚本 `tools/scan_network_leftovers.ps1` 可复用。

**延期清单**（依赖未迁移系统，未复制）：
- uninos/networkproviders/PneumaticNetwork → TileEntityMachineAutocrafter / TileEntityPneumoTube（P5）
- api/fluidmk2：FluidNetMK2、FluidNode、IFillableItem、IFluidConnectorBlockMK2、IFluidConnectorMK2、IFluidPipeMK2、IFluidProviderMK2、IFluidReceiverMK2、IFluidStandardTransceiverMK2 → FluidType（本集群步骤 4）；IFluidStandardSenderMK2、IFluidStandardReceiverMK2、IFluidUserMK2 → FluidTankNTM（步骤 3）

**编译验证 API 清单**（用户侧 `gradlew compileJava` 确认）：
- fastutil `Object2LongOpenHashMap.object2LongEntrySet().fastIterator()`（var 推断）
- `ServerLifecycleHooks.getCurrentServer().getAllLevels()` + `StreamSupport.stream(..., false).toArray(Level[]::new)`
- `Capabilities.EnergyStorage.BLOCK` BlockCapability 查询（`world.getCapability(cap, pos, face)`）
- `Tuple.ObjectLongPair<T>`（getKey/getValue/hashCode/equals）
- NodeNet 泛型自引用 `N extends NodeNet<R, P, L, N>`（Klystron/Plasma/Rebar 三个 provider 实例化）

## ✅ P4.1 批次2：inventory.material 材料系统（完成，4 文件 + 2 支撑扩展）

**⚠️ 事实修正**：包内实际 **4 个文件**（用户简报 3 个，漏 MatDistribution）；依赖方向**反转**——Mats/NTMMaterial 依赖 `OreDictManager.DictFrame`（P4.2），而非 OreDictManager 依赖 material。修正方案（用户确认）：**OreDictManager 骨架提前 + MatDistribution 延期 P4**。

| 文件 | 行数 | 状态 | 关键处理 |
|---|---|---|---|
| inventory/material/MaterialShapes | 107 | ✅ | `Loader.isModLoaded("gregtech")` → `ModList.get().isLoaded("gregtech")`（唯一 1.12 依赖） |
| inventory/material/NTMMaterial | 123 | ✅ | MainRegistry.logger → HBM.LOGGER；`new ItemStack(item, amount, id)`（1.12 meta 构造器）→ `setDamageValue(id)`（id 恒 < 32767） |
| inventory/material/Mats | 279 | ✅ | `RecipesCommon.ComparableStack` → `com.hbm.util.ComparableStack`；`ItemStackUtil.getOreDictNames` → 空列表桩（TODO P4.2 tag 查询）；`ModItems.scraps/ItemScraps` 分支 → TODO P5 桩。**108 个 MAT_* 材料、23 种 autogen 形状** |
| inventory/OreDictManager（骨架） | 1424→1042 | ✅ | 见下 |
| hazard/HazardRegistry（扩展） | 20→110 | ✅ | 补 7 类型常量（null 桩，TODO P5）+ 形状乘数 11 + 放射乘数 33（CE 原值）；RADIATION 保留 |
| ⏳ 延期 P4 | inventory/material/MatDistribution | 204 | 熔炼配方序列化，绑定 SerializableRecipe（P4 配方系统）+ ItemEnums/BlockEnums + OreDictManager 注册恢复 |

**OreDictManager 骨架化明细**（P4.2 专项批由此缩减为"恢复 registerOres()"）：
- **保留**：~45 个 KEY_* 常量、~100 个 DictFrame 常量 + 6 个 DictGroup 常量、`DictFrame` 类（848-1253，含快捷方法/rad/hot/.../haz 链式）、`DictGroup` 类、`registerGroups()`、`getReflector()`、`addReRegistration()`、`compensateMojangSpaghettiBullshit()`（Blocks/Items 1.21 常量可用）、`arcSmeltable`
- **桩化**：`registerOres()`（~430 行整体注释，TODO P4.2——依赖 ModItems 全量/BlockEnums/ItemEnums/CraftingManager/tag 注册）；`DictFrame.registerStack` 的 `OreDictionary.registerOre` 2 处 → TODO 注释（HazardSystem.register 保留）
- **删除**：`onRegisterOre(OreRegisterEvent)`（1.12 事件系统不存在，外部 mod 兼容改 tag 注册）
- **改写**：`DictFrame.fromOne/fromAll` 6 处 meta 构造器 → `setDamageValue`；import 区清理（删 ModBlocks/ModItems/ItemEnums/BlockEnums/CraftingManager/MainRegistry/OreDictionary/SubscribeEvent，Blocks/Items 改 1.21 路径）
- **HazardRegistry null 桩注意**：`HOT/BLINDING/ASBESTOS/COAL/HYDROACTIVE/EXPLOSIVE/DIGAMMA` 为 null（HazardType* 类未迁移，TODO P5）；registerOres() 桩化期间无调用路径，运行时安全

**材料 × 形状 → 注册名映射**（派生规则，非静态表）：
`MaterialShapes.make(mat)` = `shape.name() + mat.names[0]`（如 `ingotIron`、`dustUranium`）；autogen 物品（P8 系统）仅生成 `setAutogen(...)` 白名单内的形状。当前 **23 形状全集**：BILLET BLOCK BOLT CASTPLATE DENSEWIRE DUST DUSTTINY FRAGMENT GEM GRIP HEAVYBARREL HEAVYRECEIVER INGOT LIGHTBARREL LIGHTRECEIVER MECHANISM NUGGET PIPE PLATE SHELL STOCK WELDEDPLATE WIRE

**1.12 残留扫描**：4 文件 × 20 正则模式，命中全部为 TODO 注释 ✅（`tools/scan_file.ps1` 新增，单文件复用）

**编译验证 API 清单**（用户侧 `gradlew compileJava` 确认）：
- `net.neoforged.fml.ModList.get().isLoaded("gregtech")`
- `new ItemStack(Item, int)` 无 meta 构造器 + `ItemStack.setDamageValue(int)`
- `HazardRegistry` null 类型常量 + `new HazardEntry(IHazardType, double)`（float 自动加宽）
- `OreDictManager` 静态导入自引用（`import static ...DictFrame.fromOne` 于类内）
- `ModList`/`Blocks`/`Items` 1.21 常量（compensate 方法）

## P4 前置：RecipesCommon（配方系统基类，P4 地基）

| 迁移点 | 处理 |
|---|---|
| AStack / ComparableStack | 已提取至 com.hbm.util（HazardSystem 前置），本文件改为 import（**ComparableStack 提取闭环完成**） |
| NbtComparableStack | NBT 匹配；hasTagCompound/getTagCompound → hasTag/getTag；Library.tagContainsOther → 内联（required ⊆ input 语义，P5 Library 迁移后可改回） |
| OreDictStack | OreDictionary.getOres/getOreIDs/getOreName → tag 查询（TODO P4 OreDictManager 键名映射）；extractForJEI 的 proxy.getSubItems → TODO P8 |
| MetaBlock | Block.REGISTRY → BuiltInRegistries.BLOCK.getKey；metaOf(IBlockState)（1.13+ 无 meta）→ metaOf(BlockState, 0) |

> 解锁：P4 配方体系（Anvil/装配机等 100+ 配方类）的堆栈比较基础；HazardSystem 的 ComparableStack 引用闭环。

## ✅ P4.1 批次A：流体集群（完成，~36 文件）

**⚠️ 提示词事实修正**：FluidTankNTM 是 **HBM 自定义类**（非 NeoForge FluidTank）；trait 包**非纯数据**（4 个 P5 桩 + BobMathUtil）；Fluids 的 100+ 字段是 **HBM 自研 FluidType**（非 NeoForge Fluid）；FluidType.Properties 是 NeoForge 概念（A4/A5 混淆）。

### 交付清单
| 组 | 文件 | 状态 |
|---|---|---|
| 零依赖层 | render/misc/EnumSymbol（20 行纯枚举）、util/BobMathUtil（骨架，getShortNumber，P8 扩展） | ✅ |
| trait 包（14） | FluidTrait / FluidTraitSimple（8 简单特质）/ FT_Combustible / FT_Coolable / FT_Corrosive / FT_Flammable / FT_Heatable / FT_Pheromone / FT_Poison / FT_Polluting / FT_PWRModerator / FT_Rocket / FT_Toxin / FT_VentRadiation | ✅（4 个 P5 桩见下） |
| 骨架（3 新增） | handler/pollution/PollutionHandler（PollutionType 枚举 + incrementPollution 桩 + SOOT/HEAVY_METAL/POISON_PER_SECOND 常量）、handler/ArmorUtil（checkForHazmat/damageGasMaskFilter 桩）、util/ArmorRegistry（HazardClass 枚举 + hasAllProtection 桩） | ✅ |
| tank（2） | FluidTankNTM（504→266 行干净版）、IFluidLoadingHandler | ✅ |
| fluid 根 | FluidType（318 行）、Fluids（1195→1103 行，~175 流体 + 特质 + 配置读写 + NBT 序列化） | ✅ |
| fluidmk2（13） | FluidNetMK2 / FluidNode / IFillableItem / IFluidConnectorBlockMK2 / IFluidConnectorMK2 / IFluidPipeMK2 / IFluidProviderMK2 / IFluidReceiverMK2 / IFluidStandardTransceiverMK2 / IFluidStandardSenderMK2 / IFluidStandardReceiverMK2 / IFluidUserMK2（批次 1 延期的 12 个 + IFluidRegisterListener） | ✅ 恢复 |
| 挂载 | HBM.java 构造器 + `Fluids.init()`（ModFluids 注册前）；blocks/fluid/ModFluids 桥接设计文档化 | ✅ |

### 关键改写
- **FluidTankNTM**：删除 1.12 Forge 兼容层（implements IFluidHandler/IFluidTank + 8 个 @Deprecated 方法 + getTankTypeFF——1.21 NeoForge IFluidHandler 签名不同）；删除渲染层 renderTank/renderTankInfo（P8）；onTypeChanged/setType/loadTank 的未迁移依赖（IConnectionAnchors/IItemFluidIdentifier/ModItems）桩化；`IItemHandler` → `net.neoforged.neoforge.items.IItemHandler`；NBT 键名（`s`/`s_max`/`s_type`/`s_p`）与序列化格式**原样保留**（旧存档兼容）
- **FluidType**：`Keyboard.isKeyDown` → `Screen.hasShiftDown()`；`FluidRegistry.getFluid` → `Registries.FLUID.get(ResourceLocation.parse(...))`；**修复 remap.ps1 规则 73 无词边界 bug**（`Side` 前缀破坏 `SideOnly` → `DistOnly`，已加 `\b`，import 手动修回 OnlyIn）
- **Fluids**：删除 1.12 Forge 流体兼容层（initForgeFluidCompat/setupForgeFluidCompat×3/alreadyRegistered/registerForgeFluidCompat——FluidRegistry/FluidNTM/FMLCommonHandler/IResource，由 ModFluids 桥接承担）；`MainRegistry.configHbmDir` → `FMLPaths.CONFIGDIR.get().resolve("hbmConfig").toFile()`；`MainRegistry.logger` → HBM.LOGGER；`PotionEffect` → `MobEffectInstance`、`MobEffects` import 修正；CD_Canister/CD_Gastank（内部类）保留
- **FT_Toxin**（重写）：`Potion` → `MobEffect`、`PotionEffect` → `MobEffectInstance`、`EntityEquipmentSlot` → `EquipmentSlot`、`Potion.REGISTRY` → `BuiltInRegistries.MOB_EFFECT`、`DamageSource` 路径修正 + deserialize 的 `new DamageSource(String)` → null 桩（poison() 兜底 generic，TODO P5）、`StringUtils.ticksToElapsedTime` → `StringUtil.formatTickDuration`
- **FT_VentRadiation**：`ChunkRadiationManager.proxy.incrementRad` → TODO P5 桩（import 删）
- **fluidmk2 恢复**：IFluidReceiverMK2/IFluidStandardSenderMK2 的 `Compat.getTileStandard` → `world.getBlockEntity(...)`、NetworkRegistry/AuxParticlePacketNT/HbmEffectNT/PacketThreading import 删除、particleDebug 块 → TODO P8 桩（与 energymk2 同模式）

### P5/P8 桩清单
- P5：FT_Toxin（ArmorUtil/ArmorRegistry 真实现）、FT_Polluting（incrementPollution 真实现）、FT_VentRadiation（ChunkRadiationManager）、FluidTankNTM（IConnectionAnchors/IItemFluidIdentifier/FluidLoader×4/ModItems）、Fluids 的 ToxinDirectDamage DamageSource 重建
- P8：FluidTankNTM 渲染层、Fluids Forge 兼容层重建为 **ModFluids 桥接**（设计已文档化：RegisterEvent 遍历 metaOrder → NeoForge FluidType + SimpleFluid + 双向映射 + 桶）、BobMathUtil 完整版、粒子调试

**1.12 残留扫描**：fluid 集群 + fluidmk2 + 4 骨架，命中全部为合法 import/TODO 注释 ✅

**编译验证 API 清单**（用户侧 `gradlew compileJava` 确认）：
- `net.neoforged.fml.loading.FMLPaths.CONFIGDIR.get().resolve("hbmConfig").toFile()`
- `net.minecraft.client.gui.screens.Screen.hasShiftDown()`（静态）
- `Registries.FLUID.get(ResourceLocation)`（`Registries.FLUID` 泛型 Holder.Reference 返回）
- `BuiltInRegistries.MOB_EFFECT.get(ResourceLocation)` / `getKey(MobEffect)`
- `MobEffectInstance(MobEffect, int, int, boolean, boolean)` 构造器
- `StringUtil.formatTickDuration(int, boolean)`
- `io.netty.buffer.ByteBuf`（netty，Minecraft 自带依赖）
- `net.neoforged.neoforge.items.IItemHandler`（loadTank/unloadTank/setType 签名）
- HashBiMap.inverse()（renameMapping/fromNameCompat）
- FluidType.NETWORK_PROVIDER → `new FluidNetMK2(this)` 泛型（NodeNet 链）

## ✅ P4.1 批次B：ItemEnums / BlockEnums（完成，4 文件）

**⚠️ 提示词事实修正**：ItemEnums 在 **com.hbm.items**（非 inventory）；两者均为**纯数据枚举**（无 getByName/注册名引用/语言键需求——用户简报的"燃料/能量/机器升级类型"不实，实际是材质/结构/装饰枚举）。

| 文件 | 行数 | 处理 |
|---|---|---|
| items/ItemEnums | 257 | ✅ 纯复制（21 个枚举：EnumCokeType/EnumTarType/EnumAshType/EnumChunkType/EnumCasingType/EnumCircuitType/EnumPartType/ScrapType/EnumDrillType 等，零 MC 依赖） |
| blocks/BlockEnums | 197 | ✅ remap + `EnumBasaltOreType.getDrop()` 的 ASBESTOS/GEM 分支 → null 桩（TODO P3：ModItems.ingot_asbestos/gem_volcanic 未迁移） |
| blocks/IOreType | 13 | ✅ 接口迁移（BlockState 版） |
| blocks/OreEnumUtil | 111→62 | ✅ 骨架：10 个纯数量函数保留；OreEnum 全部 29 值保留（drop/quantity 构造器置 null，TODO P5——依赖 ModItems 9 个缺失字段 + ItemPool/ModBlocks.block_meteor）；3 个 drop 函数桩化 |

**解锁**：OreDictManager.registerOres() 恢复的枚举依赖就绪（BlockEnums.EnumStoneType/EnumBasaltOreType + ItemEnums.\*）；MatDistribution 的 DictFrame.fromOne 枚举参数类型可引用。

## ✅ P4.1 批次C：配方系统骨架（完成，3 文件）

**⚠️ 提示词事实修正**：HBM 配方是**自研 JSON 序列化体系**（SerializableRecipe + 每机器静态配方类），**非原版 `IRecipe/Recipe<T>/RecipeType/RecipeSerializer` 数据包系统**——C2 的 `matches(Container,Level)` 映射与 C3 的 RecipeType/Serializer 注册**不适用**（无需创建 ModRecipeTypes/ModRecipeSerializers）。

| 文件 | 行数 | 处理 |
|---|---|---|
| inventory/recipes/loader/SerializableRecipe | 494 | ✅ 迁移（见下） |
| api/recipe/IRecipeRegisterListener | 17 | ✅ 纯接口复制 |
| util/ItemStackUtil | 313→14 | ✅ 骨架（addNBTFromString 桩，TODO P5 JSON→NBT；完整版依赖 OreDictionary/IItemHandler） |

**SerializableRecipe 关键改写**：
- `Item.REGISTRY.getObject/NameForObject` → `BuiltInRegistries.ITEM.get/getKey`（readAStack/readItemStack/writeAStack 等全部 JSON 工具）
- `new ItemStack(item, size, meta)` → `new ItemStack(item, size)` + `setDamageValue(meta)`；`hasTagCompound/getTagCompound/getItemDamage` → `hasTag/getTag/getDamageValue`
- `MainRegistry.configDir` → `FMLPaths.CONFIGDIR/hbmConfig`；`MainRegistry.logger` → `HBM.LOGGER`
- `ModItems.nothing` → `ModItems.NOTHING.get()`（port 字段大写 + DeferredItem）
- `registerAllHandlers()` 桩化（TODO P4.1 配方批：~40 配方类 + CraftingManager 1572 行 + ModItems 全量）；`GenericRecipes.clearPools()` 调用桩化
- JSON 配方文件格式（`{comment, recipes[]}` + AStack/FluidStack 序列化）**原样保留**（旧配方文件兼容）；FluidStack ✓ / RecipesCommon（AStack/ComparableStack/NbtComparableStack/OreDictStack）✓ / Tuple.Pair ✓

**延期（C2/C3）**：配方类（AssemblyMachineRecipes 1056/SILEXRecipes 822/AnvilRecipes 1180 等 ~60 个）依赖 **CraftingManager（1572 行）→ ModItems/ModBlocks 全量**（P3 物品补全批后）；MatDistribution（204 行）同样解锁。

## ✅ P3.1 物品补全：编译闭环（完成，3 文件 + lang）

**⚠️ 规模修正**：CE ModItems **1838 个字段**（2830 行），port 仅 340 个——**缺失 1498 个**（用户简报"~10 个"仅对当前编译成立）。恢复 registerOres()/CraftingManager/配方类需要**全量**，分 3 层推进。

**本步（P3.1）——当前编译缺失的 2 字段**（BlockEnums.getDrop 依赖）：
| 字段 | 原 CE 类型 | 占位方案 | 状态 |
|---|---|---|---|
| ingot_asbestos | ItemBakedBase（P8） | `ItemBase` 占位，注册名不变 | ✅ |
| gem_volcanic | ItemCustomLore（P8） | `ItemBase` 占位，注册名不变 | ✅ |

- ModItems +2 DeferredItem（INGOT_ASBESTOS/GEM_VOLCANIC，partsTab）
- ModCreativeTabs +2 accept（partsTab 块尾）
- BlockEnums.getDrop **全部恢复**（含既有 9 个字段：SULFUR/FLUORITE/POWDER_MOLYSITE/CAP_* 等——注意 port 字段为大写 + DeferredItem，引用处 `.get()`）
- en_us.json +2 键（`item.hbm.ingot_asbestos`=Asbestos Sheet、`item.hbm.gem_volcanic`=Volcanic Gem，CE 原值）

**P3.2（下一步）**：OreEnumUtil 完整恢复（ModItems 缺失 9 字段：lignite/chunk_ore/gem_rad/trinitite/nugget_zirconium/powder_nitan_mix/oil_tar/ingot_phosphorus/powder_fire）+ registerOres() 恢复所需矿物类字段（~300 个 ItemBase/ItemScraps 型——ItemScraps 需先迁移）+ 恢复 registerOres() 本体。
**P3.3**：CraftingManager（1572 行）+ 配方类全量（最大批，ModItems/ModBlocks 全量后）。

## ✅ P4.2 ingot 批：99 字段补全（完成，3 文件）

**⚠️ 规模修正**：CE 实际 **101 个 ingot_\***（用户简报"105 个"不实；且 INGOT_TIN/INGOT_SILVER/INGOT_CURIUM 等为编造——HBM 无 vanilla 风格锡/银/超铀元素锭）。port 原有 3 个（INGOT_URANIUM/INGOT_ASBESTOS + 1）→ **补 99 个**。

| 项 | 内容 |
|---|---|
| ModItems | +99 DeferredItem（ItemBase 占位；CE 类型 ItemCustomLore ~60 / ItemBakedBase ~36 / ItemFuel 2 / ItemSchraranium 1——P8 按类型替换，注册名不变）→ 共 **441 字段** |
| ModCreativeTabs | +99 accept（partsTab） |
| en_us.json | +99 键（CE lang 原值，如 ingot_actinium=Actinium-227 Ingot）→ 103 ingot 键 |

生成工具：`tools/gen_ingots.ps1`（CE 提取→差集→三文件插入）、`tools/ingot_probe.ps1`（tab/lang 探针）、`tools/diff_ingot.ps1`。
**核对**：ModItems 102 注册 / tabs 102 accept / en_us 103 键 + JSON 有效 ✓。
**剩余**：CE 1838 - 441 = **~1397 字段待 P3.2/3.3**（powder_/ore_/nugget_/billet_/crystal_/plate_ 等系列）。

## ✅ P3.2 矿物类字段批：registerOres 依赖集（完成，157 字段）

**精确范围（非按前缀猜）**：提取 CE `registerOres()` 方法体（~430 行）引用的全部 ModItems 字段 = **350 个**；port 按注册名差集缺失 = **157 个**（用户推测 61/54/45/100+ 分系列不实）。

| 项 | 内容 |
|---|---|
| ModItems | +157 DeferredItem（ItemBase 占位；nugget_/billet_/crystal_/powder_/plate_/fragment_/ore_/gem_ 及 LITHIUM/BALL_RESIN 等非系列字段）→ **620 字段** |
| ModCreativeTabs | +157 accept（partsTab） |
| en_us.json | +157 键（CE lang 原值） |
| 工具 | `tools/roref_fields.ps1`（registerOres 引用字段提取）、`tools/gen_roref.ps1`（157 字段三文件插入） |

**registerOres() 恢复前置还剩**：~11 个非标准声明字段（字段名≠注册名/特殊构造器）+ **ItemScraps 迁移**（198 行：数据部分保留、1.12 客户端渲染 ModelBakeEvent/IModel/ModelLoader 桩化 P8）+ tag 注册映射（OreDictionary.registerOre → 1.21 tag）。

## ✅ P3.2 步骤①：ItemScraps/ItemAutogen 骨架（完成，2 新文件 + 2 恢复）

| 文件 | 处理 |
|---|---|
| items/special/ItemAutogen（新，214→64 行） | 骨架：构造器（INSTANCES 注册）+ aot/oun/getTexturePath + getDescriptionId(ItemStack) 名称覆写；**删除全部 1.12 客户端渲染**（registerModels/bakeModel/registerSprite/registerColorHandlers/ownsModelLocation/getSubItems/getItemStackDisplayName + IModelRegister/IClaimedModelLocation 接口 + ModelBakeEvent/IModel/ModelLoader/TextureAtlasSprite/IItemColor 等，TODO P8） |
| items/machine/ItemScraps（新，198→57 行） | 骨架：数据核心 getMats/create 保留（Mats ✓）；`setTagCompound` → `DataComponents.CUSTOM_DATA` + `CustomData.of`；`stack.getMetadata` → `getDamageValue`；渲染方法全删（P8） |
| ModItems.SCRAPS | `new ItemBase(...)` 占位 → `new ItemScraps("scraps")`（+import） |
| Mats.getMaterialsFromItem | **scraps 分支恢复**（批次 2 桩 → 实代码：`ModItems.SCRAPS.get()` + `ItemScraps.getMats`，+2 import） |

**解锁**：Mats ↔ ItemScraps 材料闭环恢复；registerOres() 恢复的 `mat.make(ModItems.bedrock_ore_fragment)` 型依赖（ItemAutogen 基类）就绪。

## ✅ P3.2 步骤②：特殊字段（完成，+11 实增，registerOres 字段 100% 就绪）

**⚠️ 规模修正**：特殊字段实际 **30 个**（非 11）：ItemAutogen 型 17（可直接用骨架，但其中 **18 个 port 已有 ItemBase 占位**——保留原占位，不重复）、ItemEnumMulti 型 4、ItemLemon 型 4、ItemTooling 型 4。**真实新增 11 个**（briquette/coke/oil_tar/glyphid_meat/glyphid_meat_grilled/ingot_semtex/nugget/hand_drill/hand_drill_desh/screwdriver/screwdriver_desh，ItemBase 占位，CE 类型注释标注）。

- **查重教训**：首轮 gen_special 未查 port 已存在 → 18 个重复声明（编译错误）→ `tools/fix_special2.ps1` 回滚去重。**最终 ModItems 631 注册、0 重复** ✓
- tabs：partsTab +26 / consumableTab +3（glyphid_meat×2/nugget 按 CE 归属）；en_us +29 键（JSON 有效）
- **registerOres 字段就绪度验证**（`tools/check_roref_ready.ps1`）：**350 引用字段 0 缺失** ✓

**步骤③ registerOres() 恢复前置**（下一步）：字段 ✓ / ItemScraps ✓ / Mats ✓ / BlockEnums+ItemEnums ✓；剩余：字段引用 CE 小写→port 大写映射（脚本）、`OreDictionary.registerOre/getOres` → 1.21 tag 桩、`CraftingManager.add9To1ForODM`（761-766，P4 前置）桩化、`Mats.MAT_SCHRABIDIUM.make(plate_cast)` ✓、`EnumTarType/EnumAshType` ✓。

## ✅ P3.2 步骤③：registerOres() 恢复（完成，OreDictManager 1046→1406 行）

**恢复方式**：CE registerOres() 方法体（361 行）全量移植 + 脚本变换（`tools/port_rores2.ps1` + `fix_fromone_get.ps1` + `fix_rores_final.ps1`）：
- **字段映射**：ModItems 350 引用 → 大写（`POWDER_COAL_TINY` 等，`(?!\s*\()` 排除 DictFrame 方法名）；`ModItems.xxx` → `ModItems.XXX.get()`；`Mats.make(x)` → `.get()`；fromOne/fromAll 参数 `.get()`（Item/Block 重载）；ModBlocks 123 引用 → 大写+`.get()`（`ORE_GNEISS_IRON.get()`）
- **新增 ModBlocks 121 占位方块**（通用 `new Block(BlockBehaviour.Properties.of())` + `registerSimpleBlockItem` + BLOCK_TAB 121 accept，P3 方块批替换真类）——这是 registerOres 恢复的**硬前置**（原缺 121/123）
- **桩化（保留原文注释）**：`OreDictionary.registerOre` ~100 行 → `// TODO P4.2 tag:`（KEY_* 语义保留）；CraftingManager autogen 循环块（759-764）→ `// TODO P4:`；`OreDictionary.getOres`/`if(mat.autogen...)`/`for(fuelCoke)`/`concrete_colored` 行 → 注释
- **保留**：DictFrame 链式调用（COAL.gem 等）、Mats.make、HazardRegistry 乘数、fromOne/fromAll（枚举 ✓）、`MaterialShapes.registerCompatShapes()`、`compensateMojangSpaghettiBullshit()`、getReflector
- **import**：+ModBlocks/ModItems 普通 + static（含 7 个枚举静态导入）；冲突审计（on-demand static import 同名懒解析，NUGGET 等未引用 ✓）
- **遗留清理**：`.get()` 链压缩（ModItems.bedrock_ore_fragment 7 层 → 1 层）；registerOres 现**可编译**（注册逻辑 P4.2 tag 系统恢复后取消注释即完整）

**registerOres 引用就绪度**：ModItems 350/350 ✓、ModBlocks 123/123（占位）✓ → **P4.2 tag 系统 = 最后一步**（取消注释 + `OreDictionary.registerOre` → 1.21 `ItemTags`/自定义 tag + `setData`）。

## ✅ P5.1a 方块实体基类层（完成，14 文件）

| 文件 | 处理 |
|---|---|
| TileEntityInventoryBase（重写） | 1.21 BlockEntity：构造器 `(BlockEntityType, BlockPos, BlockState, int)`；`saveAdditional/loadAdditional(HolderLookup)`；`level/worldPosition`；1.12 Forge Capability → **NeoForge BlockCapability**（`getCapability(BlockCapability, Direction)` + `Capabilities.ItemStack.ITEM`）；NBT 键 "inventory" 保留 |
| IPersistentNBT（重写） | IWorldNameable→Nameable；asItem+setDamageValue；setTag→`DataComponents.CUSTOM_DATA`+CustomData；spawnAsEntity→popResource；hasAnalogOutputSignal/updateNeighbourForOutputSignal；instabuild；CompatExternal.getCoreFromPos→TODO P5.1b 桩 |
| 接口 ×8 | IConnectionAnchors/IOverpressurable/IRadarCommandReceiver/IBufPacketReceiver/IConfigurableMachine ✓ 零改；IFluidCopiable/IMetalCopiable（ICopiable ✓ 迁移 + BobMathUtil 补 intCollectionToArray×2/collectionToIntArray/shuffleIntArray）；IUpgradeInfoProvider（getDescriptionId + ItemMachineUpgrade 骨架含 UpgradeType 枚举） |
| 支撑 | ICopiable（Either ✓）、api/tile/IWorldRenameable（1.21 Nameable 版） |
| **延期删除**（P5.1b/2） | TileEntityTickingBase（LoadedBase）、TileEntityProxyConductor/Dyn/Energy/Inventory（ProxyBase→BlockDummyable）、TileEntityData（方块绑定）、IRepairable（BlockDummyable/ItemBlowtorch/RenderGameOverlayEvent） |

**P5.1a 编译验证 API**：`BlockCapability<T,D>` 覆写、`ItemStackHandler.serializeNBT/deserializeNBT(Provider)`、`Block.popResource`、`Nameable` 接口、`player.getAbilities().instabuild`。
**P5.1b 剩余**：BlockDummyable 重写式迁移（945）+ TileEntityLoadedBase（BufPacket/HBMSoundHandler 桩）+ TileEntityProxyBase + TileEntityMachineBase 补全（IWorldRenameable ✓ 就绪）。

## ✅ P5.1b-1：BufPacket 迁移（完成）

- `packet/toclient/BufPacket`：1.12 SimpleNetworkWrapper + PrecompiledPacket → **CustomPacketPayload + StreamCodec**（`ByteBufCodecs.VAR_INT×3 + byteArray`；客户端 handle 经 `IBufPacketReceiver.deserialize(Unpooled.wrappedBuffer(data))`）
- `ModNetwork`：`registrar.playToClient(BufPacket.TYPE, ...)` 注册
- **P5.1b-2 前置**：TileEntityLoadedBase（290）的 BufPacket 依赖 ✓ 就绪；剩余 HBMSoundHandler（789 行桩）+ AudioWrapper（30 行桩）+ BlockDummyable（945 重写）+ MultiblockHandlerXR（173，与 BlockDummyable 循环依赖同批）+ TileEntityProxyBase

## ✅ P5.1b-1：同步基类层（完成，LoadedBase/TickingBase/AudioWrapper/ModSounds）

| 文件 | 处理 |
|---|---|
| packet/toclient/BufPacket | payload 化（上一批）✓ |
| sound/AudioWrapper | ✅ 纯桩迁移（Entity 路径 remap） |
| sound/ModSounds | +METAL_IMPACT 注册（"block.metalImpact"，CE 原注册名） |
| tileentity/TileEntityLoadedBase（重写 290→~250） | 1.21 BlockEntity：`(type,pos,state)` 构造器；loadAdditional/saveAdditional；**getUpdateTag(Provider)/handleUpdateTag(tag,Provider)**（hbmSync 载荷）；getUpdatePacket/onDataPacket 删除（1.21 自动）；markChanged→`setBlocksDirty`；networkPackNT/MK2 → `PacketThreading.createAllAroundThreadedPacket(ServerLevel,...)` + BufPacket(payload)；checkTilt 材质系统（getMaterial/isNormalCube/isSideSolid）→ `isSolid()` 简化 + TODO P8；metalImpact → ModSounds；SoundCategory→SoundSource；fnv1a64 去重 TODO P8 |
| tileentity/TileEntityTickingBase（恢复） | LoadedBase 基类（ITickable 删）；getGaugeScaled 用 **FluidTankNTM**（getFill/getMaxFill） |

**P5.1b-2 剩余（BlockDummyable 重写专项）**：BlockDummyable（945，1.12 API 已量化）+ MultiblockHandlerXR（173）+ TileEntityProxyBase（71）+ TileEntityMachineBase 补全 + HBMSoundHandler 其余（789 行 TODO P8 声音批）。

## ✅ P5.1b-1 声音批：HBMSoundHandler 迁移（完成，379 声音）

- **sound/ModSounds**：+378 注册（CE HBMSoundHandler 379 项批量，DeferredHolder，注册名与 CE 一致；`block.metalImpact` 已注册跳过）→ **379 注册 0 重复**
- **lib/HBMSoundHandler**：门面化（379 字段 = ModSounds.XXX.get()，CE 字段名保留——未来机器 TE 引用兼容）；播放方法（Minecraft.getSoundManager/SoundInstance）与 GunConfiguration 依赖 → TODO P8
- 工具：`tools/gen_sounds.ps1`（CE 提取 → ModSounds + 门面一键生成）

## ✅ P5.1b-2：BlockDummyable 重写 + 多方块体系（完成，6 文件）

| 文件 | 处理 |
|---|---|
| blocks/BlockDummyable（945→~400） | **重写**：BlockContainer→Block+EntityBlock（abstract newBlockEntity）；Material 构造器→Properties；PropertyInteger→IntegerProperty+createBlockStateDefinition；getStateFromMeta/getMetaFromState 删除；onBlockPlacedBy→setPlacedBy；breakBlock→onRemove；tick(ServerLevel,RandomSource)；setBlockToAir→removeBlock；withProperty→setValue；getDefaultState→defaultBlockState；instabuild/getInventory()/asItem()；openGui→TODO P4.2；**多方块核心保留**（findCore* 序列化搜索/isOrphan/cascadeOrphans 迭代孤儿级联/makeExtra/removeExtra/checkRequirement/fillSpace/getAABBRotationOffset）；渲染层 ~200 行（drawPlacementHighlight/bakeModel 等）→ TODO P8；碰撞覆写组（isOpaqueCube/isNormalCube/shouldSideBeRendered/canCreatureSpawn/getBoundingBox/addCollisionBoxToList/getBlockFaceShape）→ getShape/getCollisionShape |
| handler/MultiblockHandlerXR（173） | set→setPos；getStateFromMeta→defaultBlockState().setValue(BlockDummyable.META, meta)；setBlockToAir→removeBlock；checkForPlayerEyePositions→TODO P5 桩 |
| tileentity/TileEntityProxyBase（71） | Compat 删→getBlockEntity；loadAdditional/saveAdditional；level/worldPosition；MachineDiFurnace 分支→TODO P5.2 |
| TileEntityProxyConductor/Energy/Inventory（恢复） | ProxyBase 基类；Energy/Inventory 能力 → **NeoForge BlockCapability 转发**（Capabilities.EnergyStorage.BLOCK/ItemStack.ITEM → 核心 TE） |

**编译验证 API**：`setPlacedBy`、`onRemove(state,level,pos,newState,isMoving)`、`tick(BlockState,ServerLevel,BlockPos,RandomSource)`、`createBlockStateDefinition`、`IntegerProperty`、`getShape/getCollisionShape(VoxelShape)`、`Shapes.create(AABB)`、`removeBlock(pos,false)`、`BlockPos.of(long)`、`MutableBlockPos.set(x,y,z)`、`BlockCapability` 能力转发、`getInventory().selected/add/setItem`
**剩余**：子任务 4（MachineBase 补全 + 首批机器 TE——Barrel/FluidTank 等）、ProxyCombo（512，P5.2）、渲染层 P8。

## ✅ P5.1b-2 子任务4：MachineBase 补全（完成，2 文件）

- **TileEntityMachineBase**（75→~300 行补全）：P1 简化版 → CE 完整版——extends **TileEntityLoadedBase** + **IWorldRenameable**（Nameable 版）；CheckedInventory 内部类 + getCheckedInventory（容器/GUI 用）；countMufflers/getVolume（muffler 方块 → TODO P3 桩）；updateRedstoneComparatorConnection（1.21 updateNeighbourForOutputSignal）；IBufPacketReceiver serialize/deserialize（muffled）；构造器族 `(type,pos,state,scount[,slotlimit][,fluid,energy])`；**NeoForge BlockCapability**（Capabilities.ItemStack.ITEM 完整实现：accessorPos 条件访问 + isItemValid/canInsert/canExtract；能量/流体包装 TODO P5.2）
- **TileEntityAshpit** 适配：`super(type,pos,state,5,64)`（新 MachineBase 构造器，CE `super(5)` 对应）
- **首批机器 TE（Barrel 541/FluidTank 722 等）**：P5.2 独立批（每个 500+ 行大工程）

## ✅ 配方类支线：RBMK + GasCentrifuge（完成，5 文件 + 33 字段）

⚠️ **用户自创版已拒绝**（extends SerializableRecipe 抽象类 + readNBT/writeNBT 不存在 + 编造 RBMKFuelRecipe/thermalFlux/uranium_hexafluoride 结构——无法编译）。**按 CE 原版**执行：

| 项 | 内容 |
|---|---|
| items/machine/ItemRBMKRod（骨架） | 字段 pellet/yield/fullName 等 + 静态桩（setCoreHeat/setHullHeat/setPoison/setYield/getEnrichment/getPoisonLevel，TODO P5.2 完整 RBMK） |
| items/machine/ItemRBMKPellet（骨架） | pellet 依赖类 |
| items/machine/ItemFluidIcon（骨架） | make(FluidStack)/(FluidType,int)/(FluidType,int,int) 桩（GasCentrifugeRecipes 用） |
| ModItems | **+33 字段**（31 个 `DeferredItem<ItemRBMKRod>` rbmk_fuel_*（注册名 CE 一致，hep239→"rbmk_fuel_hep"）+ nugget_uranium_fuel/nuclear_waste_tiny ItemBase）→ **664 字段、0 重复** |
| RBMKFuelRecipes | CE 版（78 行）适配：31 个 `addRod(ModItems.RBMK_FUEL_*.get())` |
| GasCentrifugeRecipes | CE 版（101 行）适配：`ModItems.xxx` → `ModItems.XXX.get()`；NOTHING.get()；ItemFluidIcon.make ✓ |

**验证**：ModItems 0 重复注册、两配方类 1.12 残留 CLEAN ✓
**工具**：`tools/gen_rbmk.ps1`（CE 字段提取 → ModItems + 两配方类一键适配）

## ✅ P4.2 收尾：自研矿辞注册表（registerOres 完整恢复，完成）

⚠️ **架构修正**：1.21 **无运行时 tag 注册**（数据包驱动、加载后不可变）——`OreDictionary.registerOre` 的等价物为 **HBM 自研 ore 表**（`OreDictManager.oreDict: HashMap<String, List<ItemStack>>`）。

| 项 | 内容 |
|---|---|
| OreDictManager | +`oreDict` 表 + `registerOre(String, Object...)`（ItemStack/ItemLike，含去重）+ `getOres(String)` + `stackOf(ItemLike, meta)`（1.12 3 参 ItemStack 等价） |
| registerOres() | **121 行注释取消恢复**（`// TODO P4.2 tag/P3/P4:` 前缀删除）→ **101 个 registerOre 调用 + 41 个 stackOf**；3 参 `new ItemStack`（WILDCARD/i/mat.id）→ `stackOf`；`.get()` 型正则补丁（fix_3arg）；CONCRETE_COLORED_EXT 双重 .get() 修复 |
| DictFrame.registerStack | 2 处 TODO → `registerOre(tag + mat, stack)` 恢复 |
| RecipesCommon.OreDictStack.toStacks | TODO → `OreDictManager.getOres(name)` 接入（同包 ✓） |
| HBM.java | +`OreDictManager.registerOres()` 挂载（Fluids.init() 后、DeferredRegister 前） |

**registerOres() 现为完整运行态**（DictFrame 链式调用 + ore 表填充）；仅 CraftingManager 块（getOres 搭配 add9To1ForODM）保留 TODO P4（CraftingManager 未迁移）。
**工具**：`tools/uncomment_rores.ps1`（注释取消 + 转换）、`tools/fix_3arg.ps1`（.get() 型 3 参补丁）。

## ✅ P5.2 Barrel 三件套（7 文件落地 + getTicker 修复）

- **TileEntityBarrel**：精简版（541→~230）+ create 工厂；FluidTankNTM/fluidmk2 节点/模式/红石/网络/IPersistentNBT/IFluidCopiable/IFluidStandardTransceiverMK2（getSendingTanks/getReceivingTanks/getAllTanks 全实现）/IConnectionAnchors
- **ContainerBarrel**：AbstractContainerMenu 6 槽（SlotItemHandler 62+i*18,17）+ 玩家背包（86/144）；quickMoveStack；MenuType 经 `IMenuTypeExtension.create` + `buf.readBlockPos()` 重建
- **GUIBarrel**：AbstractContainerScreen + GuiGraphics；纹理 gui_ashpit.png 占位（TODO P8 换 gui_barrel.png）；流体信息 renderTankInfoText 文本呈现
- **注册**：ModMenus.BARREL / ModTileEntities.BARREL / ModBlocks.BARREL（**EntityBlock 匿名 + getTicker**——否则 Barrel.tick() 永不调用）+ ClientSetup
- **静态审查**（人工 API 签名核对）：registerOres/BlockDummyable/MachineBase/Barrel 接口实现全覆盖，无确定性编译错误；GUI 打开链路（standardOpenBehavior 的 openMenu）仍 TODO P4.2；BLOCK_TAB accept TODO
- **编译闸门**：累计 ~215 文件真实改动、0 次实际编译——**最高风险点**，需用户跑 `gradlew compileJava` 贴结果

## ✅ P5.2 SILEX 前置骨架（完成，3 文件；SILEX 主体独立批）

- **items/machine/ItemFELCrystal** ✅ 迁移（EnumWavelengths 枚举 NULL/IR/VISIBLE/UV/GAMMA/DRX 保留；构造器 (EnumWavelengths, Properties)；appendHoverText 1.21；addInformation/ALL_ITEMS/registry 删）
- **items/special/ItemWasteLong/Short** ✅ 骨架（ItemBase(Properties) 构造器；WasteClass 子类型/客户端/放射性 TODO P5.2）
- ⚠️ **用户贴的 SILEXRecipes 已拒绝**（extends SerializableRecipe 抽象类 + readNBT/writeNBT 不存在 + 编造 SILEXRecipe 结构——无法编译）
- **SILEXRecipes 主体**（CE 822 行已复制+remap 在工程）：适配清单 → 独立批（新上下文）：① `net.minecraft.init.Blocks/Items` → 1.21；② `net.minecraftforge.oredict.OreDictionary` → 自研 getOres；③ `RecipesCommon.ComparableStack` → com.hbm.util；④ 117 个 ModItems 字段核对补缺（vs 当前 664）；⑤ ItemStack 3 参→setDamageValue；⑥ static import OreDictManager.*（DictFrame ✓）
- **编译闸门**：SILEX 主体依赖 ModItems 当前字段可用性——需编译验证或独立批核对

## P3 物品批：第二批（54 个 EffectItem 占位物品）

| 文件 | 说明 |
|---|---|
| items/EffectItem.java | 12 行占位类（不可获得、排除 JEI/创造栏）；(String)→(Item.Properties) |
| items/ModItems.java | +54 个 `new EffectItem(...)`（全部 setCreativeTab(null) → 不挂任何 tab、无 lang）；共 **340 声明**（1+285+54） |
| tools/gen_items.ps1 | 扩展：同时匹配 `new (ItemBase|EffectItem)`；生成 339 条 |

## P3 物品批：第一批（285 个 ItemBase 物品）

### 规模修正
`ModItems.java` 实际有 **1838 个 Item 字段**（非简报的 441——那是包文件数）：
ItemCustomLore 334（P8）/ ItemBase 285（本批）/ ItemBakedBase 79（P8）/ ItemMissile 63 / 其他 ~1070。

### 交付
| 文件 | 说明 |
|---|---|
| tools/gen_items.ps1 | ItemBase 字段 → DeferredItem 生成器（setCreativeTab→tab 记录、setMaxStackSize→stacksTo、setMaxDamage→durability、setContainerItem→craftRemainder、setFull3D→TODO；⚠️ 需 UTF-8 环境执行，Windows PowerShell 5.1 按 GBK 读无 BOM 脚本会乱码） |
| items/ModItems.java | +285 个 ItemBase 声明（注册名与原 CE 一致；`craftRemainder` 引用 PARTICLE_EMPTY/ROD_ZIRNOX_EMPTY 均在本批内）；共 286 声明 |
| creativetabs/ModCreativeTabs | 6 个 tab 挂载 283 个物品（parts 222/control 39/consumable 14/template 4/weapon 2/nuke 2）；null tab 2 个不挂 |
| lang/en_us.json | +286 个 `item.hbm.*` 键（取自 CE en_us.lang），共 325 键 |

### 校验
286 声明无重复字段/注册名；302 个 tab accept 无重复；en_us.json JSON 合法。
⚠️ 编译验证点：Item.Properties().craftRemainder(DeferredItem.get())、stacksTo/durability。

## ComparableStack 提取（P5 HazardSystem 第一前置）

| 文件 | 说明 |
|---|---|
| util/AStack | 原 RecipesCommon.AStack（抽象父类，ComparableStack/OreDictStack/NbtComparableStack 共用）；isApplicable(ItemStack) 的 NbtComparableStack 分支、OreDictStack 分支、extractForCyclingDisplay 的 ModItems.nothing → TODO P4/P3 |
| util/ComparableStack | 原 RecipesCommon.ComparableStack（**9 构造器 + 12 方法全量**）；Item.REGISTRY→BuiltInRegistries.ITEM；metadata→getDamageValue/setDamageValue；getItemFromBlock→block.asItem()；OreDictionary.WILDCARD(-1)→常量 WILDCARD；getDictKeys（矿辞）→TODO P4 tag 映射；ModItems.nothing→Items.AIR（P3 物品批后恢复）；equals/hashCode/compareTo/matchesRecipe 逻辑原样 |

> 原 RecipesCommon.java（P4 迁移时处理）：删除嵌套 AStack/ComparableStack（移至 com.hbm.util），
> NbtComparableStack/OreDictStack 改为继承 com.hbm.util.AStack 并更新 import。
> 引用更新：HazardSystem（com.hbm.inventory.RecipesCommon.ComparableStack → com.hbm.util.ComparableStack）。

## HazardSystem 前置：危险物运行时基础（7 个文件，hazard 包）

| 文件 | 说明 |
|---|---|
| hazard/type/IHazardType | 接口；EntityLivingBase/EntityItem/EntityPlayer → LivingEntity/ItemEntity/Player；@SideOnly→@OnlyIn；hazardRate 静态字段读 RadiationConfig ✓ |
| hazard/modifier/IHazardModifier | 纯接口 + evalAllModifiers 静态方法 |
| hazard/modifier/HazardModifierFuelRadiation | 纯逻辑（getDurabilityForDisplay 签名不变） |
| hazard/HazardData | 数据模型；addEntry 的 HazardRegistry.CONTAMINATING 判定 TODO（HazardTypeContaminating 未迁） |
| hazard/HazardEntry | 数据模型（Cloneable）；LivingEntity |
| hazard/transformer/IHazardTransformer | 纯接口（含 ItemStack import） |
| hazard/type/HazardTypeRadiation | onUpdate 的 reacher（HazardHelper/ModItems）与 contaminate（ContaminationUtil）TODO；BobMathUtil.sqrt → Math.sqrt；roundFloat 内联；**getNewValue/getSuffix 完整迁移**（ContaminationUtil.addNeutronRadInfo 依赖） |

### HazardSystem（989 行）✅ 已迁移（806 行，12 处 TODO P 标记）
| 迁移点 | 处理 |
|---|---|
| RecipesCommon.ComparableStack | → com.hbm.util.ComparableStack（已提取 ✓） |
| ItemStackUtil.comparableStackFrom（6 处） | 内联 `new ComparableStack(stack).makeSingular()` |
| ForgeRegistries.ITEMS / IForgeRegistry | → BuiltInRegistries.ITEM（containsKey/get） |
| Tuple → Pair | commons-lang3 Pair（locationRateRegisterList） |
| 容器 API | inventoryContainer→containerMenu、inventorySlots→slots、detectAndSendChanges→broadcastChanges、slot.inventory→slot.container、player.inventory→getInventory() |
| FMLCommonHandler | → ServerLifecycleHooks.getCurrentServer() |
| OreDictionary（2 处） | → `stack.getTags()`（TagKey → location().toString()）；oreMap 键名映射待 P4 OreDictManager |
| NBT | NBTTagCompound→CompoundTag、hasKey→contains、getKeySet→keySet、getTag→get、Constants.NBT.TAG_COMPOUND→Tag.TAG_COMPOUND |
| NTM_NEUTRON_NBT_KEY | 本地常量（原静态导入 ContaminationUtil，P5 恢复） |
| 线程化管线（PlayerHazardData + CompletableFuture + 5 个 record） | 逻辑整体平移 |

**剩余 TODO（12 处）**：ContaminationUtil.getNeutronRads/contaminate/getNoNeutronPlayerRads/neutronActivateInventory（6 处，P5）；OreDictionary tag 映射（2 处，P4 OreDictManager）；HazardRegistry 其余类型字段（P3 物品批 + P5 类型批）。

### HazardRegistry（669 行）延期：ModItems/Fluids/MaterialShapes（P3 物品/P4 流体）——骨架版已建（RADIATION 实例），完整注册表 P3 物品批后填充
### 其余类型/修饰器/转换器（20 个）：HazardTypeAsbestos/Coal/Toxic/Cold（ArmorUtil/ArmorRegistry P5）、
### HazardTypeContaminating（实体/爆炸 P6）、HazardTypeUnstable（实体 P6）、transformers（ModItems/Fluids P3/P4）

## P5 网络批：能力相关包 + 线程化发包语义

| 文件 | 说明 |
|---|---|
| packet/toclient/PlayerInformPacketLegacy | IMessage/ByteBufUtils/ITextComponent → CustomPacketPayload + StreamCodec（ComponentSerialization.TRUSTED_STREAM_CODEC）；原 4 构造器 → 4 静态工厂；客户端显示：原 displayTooltipLegacy（P8 提示条）→ 临时 action bar |
| handler/threading/PacketThreading | **简化重写**：原 1.12 专属管线（ThreadedPacket 预编译 ByteBuf + 直写通道 + UnsafeHolder/jctools）→ 跨线程发包 = MinecraftServer.execute 入队主线程；保留原 API 形状（createSendTo*ThreadedPacket），参数改 CustomPacketPayload + 目标（TargetPoint → 坐标，维度 int → ServerLevel） |
| network/ModNetwork | +1 条消息（PlayerInformPacketLegacy，id=1），共 2 条 |
| capability/HbmLivingProps | 恢复 incrementAsbestos/incrementBlackLung 发包（原 PacketDispatcher.wrapper.sendTo → ModNetwork + PacketDistributor.PLAYER）；setDigamma 仍带桩（需 HbmEffectNT，P8）+ AdvancementManager（P5） |
| packet/toclient/AuxParticlePacketNT | **延期**：effect 字段类型为 HbmEffectNT（P8 粒子枚举），与 setDigamma 的粒子发送一起等 P8 |

> ⚠️ 需验证（21.1.128）：ComponentSerialization.TRUSTED_STREAM_CODEC、PacketDistributor.NEAR/TargetPoint/
> DIMENSION/ALL.noArg、ServerLifecycleHooks.getCurrentServer。

## P5 前置：能力系统核心（com.hbm.capability + JSON 配置）

| 文件 | 说明 |
|---|---|
| config/RunningConfig | JSON 运行配置基类；MainRegistry.configHbmDir → FMLPaths.CONFIGDIR/resolve("hbmConfig") |
| config/ServerConfig | hbmServer.json（13 个 ConfigWrapper）；解锁 HbmLivingCapability/HbmLivingProps(ENABLE_MKU)、HbmPotion.taint(TAINT_TRAILS) |
| capability/HbmLivingCapability | **能力系统重写**：Forge Capability/IStorage/@CapabilityInject/ICapabilitySerializable → NeoForge EntityCapability（createVoid + registerEntity(LivingEntity.class) lambda 提供）；DUMMY 提升为顶层静态；NBT 键名不变（fmt v1 旧存档兼容）；ByteBuf 序列化保留（P5 网络批接入） |
| capability/HbmLivingProps | 属性访问器；getAttributeMap→getAttributes、applyModifier→addTransientModifier、攻击→hurt；setDigamma 粒子(P8)/成就(P5) 带桩、incrementAsbestos/BlackLung 发包(P5 网络批) 带桩 |
| main/ModEvents | registerCapabilities 接入：`event.registerEntity(ENT_HBM_PROPS_CAP, LivingEntity.class, (e, ctx) -> new EntityHbmProps())` |

> ⚠️ 需验证（21.1.128）：EntityCapability.createVoid、RegisterCapabilitiesEvent.registerEntity 的
> (EntityCapability<T,Void>, Class<? extends Entity>, provider) 重载、Entity.getCapability(EntityCapability)。
>
> ContaminationUtil 仍被阻塞（本轮未迁）：实体 P6 ×10（EntityNukeTorex 等）、HazardSystem/ArmorUtil/
> ChunkRadiationManager（P5）、ModItems（P3 物品批）、Library（P5）、自定义 Vec3（P8）。

## P5 前置：ModDamageSource（com.hbm.lib，P5 前置）

| 文件 | 说明 |
|---|---|
| lib/ModDamageSource | 41 个静态伤害源 + 13 个常量 + 12 个间接伤害工厂；`new DamageSource("name")` → `(ResourceLocation.fromNamespaceAndPath("hbm", name))`（死亡键 death.attack.<path> 不变）；set* 链 → bypassArmor/bypassInvuln/explosion/projectile/fire；EntityDamageSourceIndirect → IndirectEntityDamageSource（damageType → getMsgId().getPath()）；setDamageAllowedInCreativeMode 无独立标志（digamma/ams/nitan 已 bypassInvuln 等价）；EntityBulletBase 等 8 个未迁移实体类型暂以 Entity 占位（P6 恢复，含 causeLaserDamage 双重载合并） |

> ⚠️ 需验证：DamageSource 实例链方法（explosion()/bypassArmor()/bypassInvuln()/projectile()/fire()）
> 在 21.1.128 存在（vanilla DamageSources.arrow 内部使用 .projectile()）；备用 `DamageSource.builder(RL).bypassArmor().build()`。
> 解锁：HbmPotion 的 bang/lead 分支、BlockHazard、ExplosionLarge 等 20+ 类引用。

## P5 前置：potion 包（com.hbm.potion 2 个类 + 注册）

| 文件 | 说明 |
|---|---|
| HbmPotion | Potion → MobEffect（MobEffectCategory）；performEffect → applyEffectTick、isReady → shouldApplyEffectTickThisTick；PotionEffect → MobEffectInstance；telekinesis/potionsickness/phosphorus 分支完整迁移，taint/radiation/radaway/bang/lead 分支 TODO（P5/P6/P7/P8 依赖）；getIcon → 单图标纹理路径 |
| HbmDetox | Potion.getPotionFromResourceLocation → BuiltInRegistries.MOB_EFFECT.get(ResourceLocation.parse)；依赖 PotionConfig.potionBlacklist ✓ |
| ModMobEffects | 12 个效果注册（注册名 hbm:taint/hbm:radiation/...）；**注册名变更**：原 "potion.hbm_xxx" → 短名（效果不落盘，无兼容问题）；`/effect give @p hbm:radiation 60 0` 可用 |
| config/VersatileConfig | 依赖 HbmPotion 解锁 → **config 包 cfg 型 13/13 全部完成** |

### 图标说明
1.12 的 potions.png 自定义图集（256x256，内容区域布局非标准、图标粘连）→ 1.21.1 需要 textures/mob_effect/<name>.png 单图标。
已生成 12 个透明占位图（避免紫黑缺失纹理），精确裁剪 P8 渲染批次处理。

## P5 前置：config 包（cfg 型全部完成）

### 迁移模式（Configuration → ModConfigSpec）
- `CommonConfig`：类别常量保留（`01_general`~`18_weapons`/`528`/`LESS BULLSHIT MODE`）；
  `createConfigXxx(config, cat, key, comment, def)` → `configXxx(builder, cat, key, comment, def)`（内部 push/define/pop，键名一致）
- 每配置类：`build(builder)` 定义键值 + `load()` 拷回静态字段（**全项目引用点零改动**）
- `HBMConfig`：单一 COMMON spec（`config/hbm/hbm.toml`），`HBM` 构造器注入 `ModContainer` 注册 +
  `modBus.addListener(HBMConfig::onLoad)`
- ⚠️ .cfg → .toml 格式变更，旧 hbm.cfg 不能自动迁移；键名一致可对照手动重设

### 已完成（手写 9 个 + 基础设施 2 个 + IDoor 接口）
| 文件 | 说明 |
|---|---|
| CommonConfig | 类别常量 + configBool/Int/Double/String/StringList/IntList + toIntArray + setDef/setDefZero/parseStructureFlag |
| HBMConfig | 中央 SPEC + onLoad 监听器（已挂载 9 个配置类） |
| WorldConfig | 02_ores / 17_biomes / 05_meteors 全部键值 |
| RadiationConfig | 13_radiation / 06_explosions / 14_hazard / 16_pollution；**修复原 CE bug**（railgunDamage 被 railgunBuffer/railgunUse 连续覆盖） |
| BombConfig | 03_nukes / 06_explosions（35 个 int） |
| MobConfig | 12_mobs（含 8 个 int 列表 → configIntList + toIntArray；rampant 联动保留在 load） |
| ToolConfig | 11_tools（保留原键名重复写法） |
| WeaponConfig | 07_missile_machines / 10_dangerous_drops |
| PotionConfig | 08_potion_effects（HashSet → string list + load 解析；potionSickness 字符串判定） |
| MachineConfig | 09_machines（doorConf → string list + IDoor.Mode 解析） |
| StructureConfig | 15_structures（flag 解析 + setDef + min>max 回退，logger → HBM.LOGGER） |
| GeneralConfig | 01_general / 528 / LBSM 全量（90+ 键值）；trueExp() 预留 P4（PrecAssRecipes）；GL 3.3 检查预留 P8（GLCompat，advancedRenderingSupported 暂为 true）；528↔LBSM 联动保留；whitelist 字符串列表解析（ResourceLocation.parse） |
| interfaces/IDoor | getRegistryName → BuiltInRegistries.BLOCK.getKey；markDirty → setChanged（MachineConfig 依赖） |

### 待迁移（cfg 型剩余 1 个）
VersatileConfig（依赖 HbmPotion，P5）——cfg 型配置类至此 13/14 完成
（GeneralConfig 已迁移；CompatibilityConfig 已迁移：76 个 HashMap + 5 个 HashSet + 6 个布尔，
 键名/类别与原 .cfg 一致；isWarDim(World) 重载删除——原 world.provider.getDimension() 已移除，
 维度改 ResourceKey，P7 重构；isWarDim(int) 保留）

### JSON 型（10 个，另行处理）
FalloutConfigJSON(1095行)、CustomMachineConfigJSON、MachineDynConfig、ItemPoolConfigJSON、ClientConfig、
BedrockOreJsonConfig、RunningConfig、CassetteJsonConfig、ServerConfig、JsonConfig —— 保留自研 JSON 加载，
只换 Minecraft 类型；依赖 recipes/items/MainRegistry → 随 P3 物品/P4 配方批迁移

## P3 第二批（blocks.generic 梯子/活板门）

### 已迁移（2 个类 + 13 个注册）
| 注册名 | 类 | 主要迁移点 |
|---|---|---|
| ladder_sturdy/iron/gold/aluminium/copper/titanium/lead/cobalt/steel/tungsten/red/red_top（12） | BlockNTMLadder | BlockLadder→LadderBlock；canPlaceBlockOnSide→canSurvive；getBoundingBox→getShape（red_top 为 4px 平台）；getStateForPlacement(BlockPlaceContext)；`ModBlocks.ladder_red_top` → `ModBlocks.LADDER_RED_TOP.get()`（引用自解，12 个梯子同批注册） |
| trapdoor_steel | BlockNTMTrapdoor | BlockTrapDoor→TrapDoorBlock；**简化**：自定义 LADDER 属性/getActualState/bakeModel/StateMapper（1.12 烘焙管线）全部删除 → blockstate json 数据驱动（32 变体）；onBlockActivated→useWithoutItem；getBoundingBox→getShape(VoxelShape)；isLadder 逻辑保留 |

### 延期（依赖未迁移系统，勿复制）
| 类 | 阻塞依赖 | 解锁阶段 |
|---|---|---|
| BlockHazard | HazardSystem、ChunkRadiationManager、ContaminationUtil、HbmPotion、HbmEffectNT、ModBlocks.block_meteor_molten 等 | P5（危险物/辐射系统）+ P8（粒子） |
| BlockMeta（含 MetaBlockItem） | BlockBakeFrame、IDynamicModels、ModelBakeEvent、StateMapperBase、NTMClientRegistry（整个 1.12 自定义烘焙模型管线） | P8（渲染架构：1.21.1 改为 blockstate json 数据驱动，Java 烘焙代码作废） |
| BlockModDoor | HBMSoundHandler（lib Tier C）、ModItems.door_metal/office/red/bunker（物品未迁）、INBTBlockTransformable（P7） | P3 物品批 + P7 + lib.HBMSoundHandler |
| BlockNTMTrapdoor 原烘焙部分 | 同上 | P8 |

## 已完成

### P3-1 基类
| 文件 | 状态 | 说明 |
|---|---|---|
| `blocks/BlockBase.java` | ✅ | 构造器 `(Material, String)` → `(BlockBehaviour.Properties)`；setRegistryName/setTranslationKey/setCreativeTab/setHarvestLevel/ALL_BLOCKS 删除；爆破抗性 tooltip 保留（meteor_battery 特殊提示待 ModBlocks 完整迁移） |
| `items/ItemBase.java` | ✅ | 构造器 `(String)` → `(Item.Properties)`；同上删除项 |
| `blocks/BlockDummyable.java`（945 行） | ⏸️ 延期 | 深度绑定 1.12 blockstate/metadata 多块系统；其 TE 侧（TileEntityDummy）与方块侧必须一起迁。P5 机器体系迁移时做专项：用 blockstate 属性（dummy 类型/朝向）编码占位块，替代原 meta 编码。 |
| `items/special/ItemCustomLore.java` | ⏸️ 延期 | 依赖 `ItemBakedBase`（P8 烘焙物品模型系统），且引用 `ModItems.undefined` 等未迁移字段 |
| `blocks/ITransformableBlock` | ❌ 不存在 | CE 源码中无此类，忽略 |

### P3-2 批量脚本
- `tools/gen_registries.py`（Python）：解析 CE `ModBlocks.java`/`ModItems.java` 的 `public static final Block|Item` 声明，
  提取注册名（构造参数最后一个字符串字面量）、Material、setter 链（Hardness/Resistance/SoundType/LightLevel/LightOpacity/CreativeTab），
  生成 DeferredBlock/DeferredItem 脚手架代码 + 按创造栏分组的 tabs.txt。
  ⚠️ 本机无 Python，需在用户环境运行：`python tools/gen_registries.py <CE ModBlocks.java> --items <CE ModItems.java>`
  ⚠️ 生成的代码是"脚手架"：Material→Properties 默认硬度、特殊构造参数需按类人工核对（输出含 TODO 标注）

### P3-3 第一批方块（8 个，注册名与原 CE 完全一致）
| 注册名 | 类 | 原 1.12.2 | 创造栏 | 主要迁移点 |
|---|---|---|---|---|
| brick_red | BlockRedBrick | Material.ROCK + setResistance(10000) | 无 | PropertyInteger→IntegerProperty；BlockStateContainer→StateDefinition；getStateFromMeta 删除；getDrops；getStateForPlacement(BlockPlaceContext) |
| oil_pipe / drill_pipe | BlockNoDrop | Material.IRON 5/10 | 无 | getItemDropped→getDrops |
| block_lithium | BlockHydroreactive | Material.IRON 5/10 | blockTab | Material.WATER→FluidTags.WATER；newExplosion→level.explode(...ExplosionInteraction.TNT)；neighborChanged 签名；animateTick |
| brick_concrete_marked | BlockWriting | Material.ROCK 15/160 | blockTab | onBlockActivated→useWithoutItem；sendMessage→displayClientMessage；Style→withStyle |
| pole_top | DecoPoleTop | Material.IRON 5/15 | blockTab | 1.13 删除的渲染方法（getBlockFaceShape/isOpaqueCube/canRenderInLayer...）→ noOcclusion() + 模型 json render_type |
| ntm_dirt | BlockNTMDirt | 0.5 硬度 GROUND | 无 | BlockDirt→DirtBlock；getSubBlocks/getLocalizedName 删除；掉落原版泥土 |
| pink_log | BlockPinkLog | 0.5 硬度 WOOD | 无 | BlockLog→RotatedPillarBlock；meta 轴向→blockstate json |

### P3-4 创造栏
- `ModCreativeTabs.BLOCK_TAB.displayItems` 已加入：asphalt（P1）、block_lithium、brick_concrete_marked、pole_top
- 其余方块无创造栏（原 setCreativeTab(null) 或未设置）→ 不加入任何 tab
- 完整批量分组由 gen_registries.py 输出 `tools/generated/tabs.txt` 后按 tab 填入

### 资产文件
- 修复了 **P1 纹理路径 bug**：CE 用 `textures/blocks/`（复数），1.21.1 模型 json 约定 `textures/block/`（单数）——
  P1 的 asphalt/machine_amgen/ingot_uranium 纹理已复制到正确目录
- 新增 8 个方块的 blockstate/model/item json（31 个 json 总数）；pole_top 用 cube 占位（原 OBJ 模型 P8 迁移）；
  ntm_dirt 复用原版泥土纹理；brick_red 含 7 个 meta 变体；pink_log 含 axis 变体

## 待办
- **BlockDummyable 专项**（P5 机器体系）：`ladder_red_top` 等依赖未注册字段的方块（BlockNTMLadder）随 ModBlocks 扩批迁移
- **gen_registries.py 全量运行**：在用户环境执行后，按 TODO 逐个核对 Material→Properties
- 物品批量（ModItems 441 个）→ 用 gen_registries.py 生成后分批核对（ItemCustomLore 等特殊类 P4/P8）
- 语言键：新方块需在 en_us.json 添加 `block.hbm.<name>`；`block.hbm.pink_log.desc` 等 desc 键
- 方块掉落表（部分方块有特殊掉落，如 ntm_dirt 掉原版泥土已处理）

## 编译验证
```bat
gradlew compileJava
```
预期通过（若报错，优先检查：`getDrops` 签名、`Level.explode` 签名、`RotatedPillarBlock`/`DirtBlock` 构造器）。
