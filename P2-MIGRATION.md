# P2 基础层迁移状态（util / lib）

> 更新于 P2 第二批（util/lib Tier B 完成）。映射替换工具：`tools/remap.ps1`。

## 已完成（47 个文件，编译预期通过）

### Tier A — 纯 Java 原样复制（27 个）
- `util`（13）：AdjacencyGraph, Calculator, DecodeException, Either, ExponentialMovingAverage,
  GuiUtil, NoteBuilder, ObjectIntPair, ObjectPool, ReferenceIntTuple, SectionKeyHash(fastutil),
  ShadyUtil(guava), SwappedHashSet(commons-lang3)
- `lib`（4）：HbmCollection, ObjectDoubleFunction, ObjObjDoubleConsumer, TriFunction
- `lib.internal`（5）：AbstractUnsafe(sun.misc.Unsafe), InternalUtil, SunUnsafeWrapper,
  TrustedLookupAccessor, package-info
- `lib.maps`（5）：AbstractEntry, LongObjectBiFunction, LongObjectConsumer, LongObjectRefConsumer, RangeUtil

> fastutil / Guava / commons-lang3 / sun.misc.Unsafe 均为 Minecraft/NeoForge 开发环境自带。

### Tier B — 第一批（10 个）
| 文件 | 主要变更 |
|---|---|
| util/FacingUtil | EnumFacing → Direction |
| util/EnumUtil | EnumHand → InteractionHand；EntityEquipmentSlot → EquipmentSlot |
| util/Clock | @SideOnly → @OnlyIn(Dist.CLIENT) |
| util/Vec3dUtil | Vec3d → Vec3；MathHelper → Mth；Vec3i 双参构造器改显式 (int) 截断 |
| util/Function | TextFormatting → ChatFormatting；BobMathUtil.sqrt → Math.sqrt |
| util/WeightedRandomObject | 重构：WeightedRandom.Item 基类 → WeightedRandom.WeightedEntry 接口 |
| util/WeightedRandomGeneric | 同上 |
| lib/ForgeDirection | EnumFacing → Direction；toEnumFacing → toDirection；Rotation 包迁移 |
| lib/DirPos | TileEntity → BlockEntity；getPos() → getBlockPos() |
| util/I18nUtil | FMLCommonHandler → FMLEnvironment.dist；I18n 包迁移；FontRenderer → Font |

### Tier B — 第二批（10 个）
| 文件 | 主要变更 |
|---|---|
| util/ChatBuilder | **重构**：TextComponentString/ITextComponent → MutableComponent/Component.literal/translatable/append/withStyle |
| util/ChunkShapeHelper | ChunkPos 包路径迁移（纯数学，其余原样） |
| util/ChunkSpanAccumulator | TileEntity → BlockEntity |
| util/CompatBlockReplacer | IBlockState → BlockState；getRegistryName → BuiltInRegistries.BLOCK.getKey；meta 系统删除 → defaultBlockState；HARDENED_CLAY → TERRACOTTA |
| util/EnchantmentUtil | **重构**：附魔数据化（stack.enchant(Holder)、ItemEnchantments 组件）；经验字段 experienceProgress/totalExperience/getXpNeededForNextLevel |
| util/FontRendererUtil | FontRenderer → Font；GlStateManager → RenderSystem；@OnlyIn |
| lib/CapabilityContextProvider | 仅 BlockPos 包路径迁移 |
| lib/InventoryHelper | **重构**：ICapabilityProvider → Level.getCapability(Capabilities.ItemHandler.BLOCK)；EntityItem → ItemEntity；motion → setDeltaMovement；splitStack → split |
| lib/ItemStackHandlerWrapper | 仅 import：net.minecraftforge.items → net.neoforged.neoforge.items |
| lib/RecoilHandler | EntityViewRenderEvent.CameraSetup → ViewportEvent.ComputeCameraAngles；Mth；@OnlyIn |

## 已判定"不需要迁移"（4 个）
- `util/UnlistedPropertyBoolean`、`util/UnlistedPropertyInteger`：IUnlistedProperty（Forge 专有）已删除 → 整体删除
- `util/RenderUtil`（669 行）：深度绑定 1.12 GlStateManager 内部状态（alphaState/blendState/textureState/lightState/FogMode），
  NeoForge RenderSystem 是另一套体系 → 删除，P8 渲染时用 RenderSystem 重建精简版（pushGuiBits/popGuiBits 等）
- `util/TrackerUtil`：依赖 1.12 EntityTracker/EntityTrackerEntry/IntHashMap/SPacketEntityTeleport 内部结构，
  1.21.1 追踪体系已重写（ServerEntity）→ 删除，P6 实体同步时用 1.21.1 方案重写（teleportTo + ServerEntity 广播）

## 延期（依赖未迁移，勿复制）
### Tier C / 隐藏依赖
- `util/ColorUtil`：**隐藏依赖** `ItemStackUtil.getOreDictNames`（Tier C，同包无 import 扫描漏掉）+
  客户端方法依赖已删除的 getItemModelMesher/精灵 API → 待 P4（物品）+ P8（渲染）后迁移
- `util/Vec3NT`：继承 `MutableVec3d`（→ UnsafeHolder → HbmCorePlugin 链条）→ 待 P6 实体/物理
- 其余 Tier C（30 个）见第一批记录：依赖 lib.Library、entity、capability、配方、config、api、渲染、core、handler

### lib 深层依赖
- `Library.java`（105KB）、HBMSoundHandler、ModDamageSource、HbmChestContents、NTMBlockContainer、HbmWorld、HbmWorldGen
- `lib.internal.UnsafeHolder`/`MethodHandleHelper`/`InternalUnsafeWrapper`（依赖 com.hbm.core.HbmCorePlugin，P9）
- `lib.maps.ConcurrentAutoTable`/`NonBlockingHashMap*`（依赖 Library + UnsafeHolder）
- `lib.queues.*`（4 个）+ `lib.TLPool`（依赖 Library/UnsafeHolder + jctools 外部库）

## 编译验证
```bat
gradlew compileJava
```
预期通过。若报错优先检查（我无法本地编译）：
- `WeightedRandom.WeightedEntry` 接口名 / `ItemEnchantments.Mutable.removeIf` / `MutableComponent.append` 签名
- `RenderSystem.pushMatrix/translatef/scalef`、`ViewportEvent.ComputeCameraAngles.setPitch`
- `Level.getCapability(Capabilities.ItemHandler.BLOCK, pos, null)` 便捷重载
