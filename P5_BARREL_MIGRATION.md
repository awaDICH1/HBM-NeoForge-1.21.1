# P5.2 Barrel 三件套迁移方案

> 更新：**三件套已完成**（实际落地，非用户贴的 1.16 API 错误版）。

## ✅ 完成：Barrel 完整三件套

| 文件 | 状态 |
|---|---|
| `tileentity/machine/TileEntityBarrel.java` | ✅ 精简版（541→~230）+ `create(pos,state)` 工厂；FluidTankNTM/fluidmk2 节点/模式/红石/网络/IPersistentNBT/IFluidCopiable/IFluidStandardTransceiverMK2/IConnectionAnchors |
| `inventory/container/ContainerBarrel.java` | ✅ AbstractContainerMenu：6 槽 SlotItemHandler（62+i*18, 17）+ 玩家背包（86/144）；quickMoveStack；stillValid（isUseableByPlayer）；MenuType 经 buf.readBlockPos 重建 |
| `inventory/gui/GUIBarrel.java` | ✅ AbstractContainerScreen + GuiGraphics；纹理暂用 gui_ashpit.png 占位（TODO P8 换 gui_barrel.png）；流体信息 renderTankInfoText 文本呈现 |
| `ModMenus.BARREL` | ✅ IMenuTypeExtension.create + buf.readBlockPos |
| `ModTileEntities.BARREL` | ✅ Builder.of(TileEntityBarrel::create, ModBlocks.BARREL.get()) |
| `ModBlocks.BARREL/BARREL_ITEM` | ✅ EntityBlock 匿名方块（strength 2.0/10.0, METAL）+ registerSimpleBlockItem |
| `ClientSetup` | ✅ event.register(ModMenus.BARREL.get(), GUIBarrel::new) |

⚠️ **用户贴的代码已拒绝**：1.16 Forge API（Capability/LazyOptional/ForgeCapabilities/net.minecraftforge.fluids）+ 不存在的类（com.hbm.util.DirPos/RefStrings）+ 签名错（loadAdditional 缺 Provider/FluidTankNTM(int,boolean)）+ 会覆盖已完成 Barrel（super 0 槽）。

## 剩余（P5.2 后续）
- gui_barrel.png 纹理资源（P8）
- BLOCK_TAB accept（Barrel 挂创造栏）
- NTMFluidHandlerWrapper 流体能力（Capabilities.FluidHandler.BLOCK）
- barrel_* 变体方块（iron/steel/plastic/corroded/antimatter，P3 方块批）
- openMenu 恢复（BlockDummyable.standardOpenBehavior TODO）

## ✅ 已完成：TileEntityBarrel（541→~230 行精简版）

`src/main/java/com/hbm/tileentity/machine/TileEntityBarrel.java`

**保留**：
- 流体存储（FluidTankNTM：tankNew）+ 构造器 (type,pos,state[,cap])
- **fluidmk2 网络节点**：mode==1 buffer 模式（createNode/UniNodespace 节点 + addProvider/Receiver）；常规模式（邻居节点 remove/定向 tryProvide）
- 模式系统（0 进出/1 buffer/2 出/3 封存——getDemand/transferFluid 分支）
- 红石比较器（getRedstoneComparatorPower + updateRedstoneComparatorConnection）
- 网络同步（IBufPacketReceiver serialize/deserialize：mode + tankNew）
- 流体复制（IFluidCopiable：getFluidIDToCopy/getTankToPaste）
- 传送接口（IFluidStandardTransceiverMK2：getSendingTanks/getReceivingTanks）
- IPersistentNBT（writeNBT/readNBT：tank/mode/shouldDrop；shouldDrop/创意破坏）
- 连接锚（IConnectionAnchors.getConPos——6 向 DirPos）

**删除/桩（TODO）**：
- OpenComputers（li.cil.oc @Optional）/IRORValueProvider/IRORInteractive/IFFtoNTMF/IGUIProvider 接口（P5.2 容器批/P8）
- Forge FluidTank（tank）与 convertAndSetFluid 旧存档转换（tankNew 为主）
- NTMFluidHandlerWrapper 流体能力（Capabilities.FluidHandler.BLOCK → TODO P5.2 capability 批）
- checkFluidInteraction 的 barrel_antimatter/plastic/corroded 方块（TODO P3 方块批）
- deserialize 客户端重渲染（TODO P8）

## ⏳ 待完成：Container + GUI + 注册（P5.2 容器批）

### ContainerBarrel（参考 ContainerAshpit 先例）
- extends AbstractContainerMenu；构造器 (int id, Inventory player, TileEntityBarrel barrel)
- 槽：2-5 槽流体容器交互（slots_top/bottom/side）+ 玩家物品栏
- 用 `MachineBase.getCheckedInventory()`（CheckedInventory——isItemValidForSlot 校验）
- quickMoveStack 槽位转移

### GUIBarrel（参考 GUIAshpit 先例）
- extends AbstractContainerScreen<ContainerBarrel>；GuiGraphics 绘制
- 纹理 `hbm:textures/gui/gui_barrel.png`（CE 资源复制到 port assets）
- 模式按钮（4 模式）、流体槽渲染（FluidTankNTM.renderTankInfoText → P8 渲染或文本占位）

### 注册
- `ModMenus`：+BARREL MenuType（StreamCodec 需可序列化槽数据——Barrel 用 `MenuType` 带 data（BlockPos））
- `ModTileEntities`：+BARREL BlockEntityType（Builder.of(TileEntityBarrel::new, ModBlocks.BARREL.get())）
- `ModBlocks`：+BARREL 方块（extends BlockDummyable 或 BlockBase + EntityBlock）+ BlockItem + barrel_* 变体（iron/steel/plastic/corroded/antimatter——CE 5 变体，P3 方块批）
- 方块 use → standardOpenBehavior（openMenu 需 MenuProvider——P4.2 容器批的 openMenu 桩恢复）

## 依赖前置（Container/GUI 批）
- ModMenus 的 StreamCodec MenuType 模式（ASHPIT 先例 ✓）
- AbstractContainerMenu/AbstractContainerScreen（Ashpit ✓ 已用）
- GUI 纹理资源（assets/hbm/textures/gui/——P8 或复制 CE）
- openMenu 恢复（BlockDummyable.standardOpenBehavior 的 TODO P4.2）
