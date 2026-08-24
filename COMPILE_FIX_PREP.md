# 编译错误预判清单（COMPILE_FIX_PREP）

> 基于 ~215 文件改动的静态审查。**⚠️ 用户离线预判清单中多数为误判**（见下方"已核实正确的 API"）；本清单给出**真实风险点**与修复预案。

## 已核实正确的 API（用户预判纠正）
| 用户担心 | 实际 | 依据 |
|---|---|---|
| `IMenuTypeExtension` 21.1.128 不存在 | ✅ **存在** | ModMenus.ASHPIT 已用 `IMenuTypeExtension.create`（P1 垂直切片编译期验证过） |
| `getUpdateTag(Provider)` 参数不匹配 | ✅ **1.21 正确签名** | `BlockEntity.getUpdateTag(HolderLookup.Provider)` + `handleUpdateTag(CompoundTag, Provider)` |
| `SoundEvent.createVariableRangeEvent` | ✅ **正确 API** | 1.21 SoundEvent 标准构造 |
| `stackOf`/`ItemLike` 签名 | ✅ 正确 | `net.minecraft.world.level.ItemLike`（Item/Block/DeferredItem 均实现）+ `new ItemStack(ItemLike)` |
| `getShape` 返回 VoxelShape | ✅ 正确 | `Shapes.create(AABB)` 1.17+ 存在 |
| `BlockCapability` 泛型 | ✅ 正确 | `getCapability(BlockCapability<T,D>, D)` 1.21 BlockEntity 标准覆写 |

## 真实风险点（按概率排序）+ 修复预案

### R1：`Level.neighborChanged(BlockPos, Block, BlockPos)` 签名（3 文件用）
- 文件：BlockDummyable（neighborChanged 覆写）、MachineBase.updateRedstoneComparatorConnection、LoadedBase（IConnectionAnchors 调用）
- 风险：1.21 覆写签名 `neighborChanged(BlockState, Level, BlockPos, Block, BlockPos, boolean)` ✓ 已写对；`level.neighborChanged(pos, block, fromPos)` 调用签名 ✓
- **预案**：若报"cannot find symbol"→ 检查是否缺 `boolean isMoving` 参数（覆写）或调用端参数顺序

### R2：`Level.updateNeighbourForOutputSignal(BlockPos, Block)` 存在性
- 文件：MachineBase/LoadedBase
- 风险：1.21 方法名 `updateNeighbourForOutputSignal` ✓（英式拼写）
- **预案**：若不存在 → `level.getBlockState(pos).getAnalogOutputSignal(...)` 手写或 `updateNeighbors`

### R3：`BlockState.isSolid()` 无参形式
- 文件：LoadedBase.checkTilt
- 风险：1.21 `isSolid()` 无参 ✓（1.20.5+）；若报"requires args"→ 用 `state.isSolid()` 或 `state.isSolidRender()`
- **预案**：`state.isSolidRender(level, pos)`（带参版）

### R4：`Inventory.selected` / `getInventory().add/setItem`
- 文件：BlockDummyable.setPlacedBy
- 风险：1.21 `Inventory.selected`（int 字段）✓、`add(ItemStack)` ✓、`setItem(int, ItemStack)` ✓
- **预案**：若报错 → `player.getInventory().items.set(slot, stack)`

### R5：`ModBlocks.BARREL.getTicker` 泛型 lambda
- 文件：ModBlocks
- 风险：`BlockEntityTicker<T>` lambda 返回类型——`(lvl,pos,st,te) -> ((TileEntityBarrel)te).tick()`（void 返回 ✓）
- **预案**：若报"incompatible types"→ 改 `(lvl,pos,st,te) -> { ((TileEntityBarrel)te).tick(); }`

### R6：`buf.readBlockPos()` 在 IMenuTypeExtension 工厂
- 文件：ModMenus.BARREL
- 风险：工厂 buf 是 `RegistryFriendlyByteBuf`（extends FriendlyByteBuf）→ `readBlockPos()` ✓
- **预案**：若报错 → `BlockPos.of(buf.readLong())`

### R7：声音批 379 DeferredHolder 静态初始化顺序
- 文件：ModSounds
- 风险：`DeferredHolder.get()` 在注册前调用 → 返回 null（不崩）；HBMSoundHandler 静态字段 `ModSounds.X.get()` 在类加载（注册前）→ null
- **预案**：若 NPE → HBMSoundHandler 改懒加载（方法内 get()）

### R8：`ItemStackHandler.serializeNBT/deserializeNBT(Provider)` 签名
- 文件：MachineBase/InventoryBase
- 风险：1.21 neoforge `serializeNBT(HolderLookup.Provider)` / `deserializeNBT(Provider, CompoundTag)` ✓ 已写对
- **预案**：若报错 → `inventory.serializeNBT(registries)` 检查 import

## 修复流程（用户贴错误后）
1. 提取 `文件:行号: error:` 每行
2. 对照本清单 R1-R8 分类
3. 逐条修 + 记录到 P3-MIGRATION.md
