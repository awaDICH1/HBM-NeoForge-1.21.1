# 下一阶段方案设计（NEXT_PHASE_PLAN）

> 前提：**编译验证通过后**方可执行（当前 ~215 文件 0 编译，未验证基座禁止堆叠）。

## 方案对比

| | 方案 A：机器 TE 批量（三件套） | 方案 B：P4.2 剩余配方类 | 方案 C：P5 实体批 |
|---|---|---|---|
| 内容 | FluidTank/GasCentrifuge/RBMKConsole/Column/Dials（TE+Container+GUI 逐个） | AnvilRecipes/AssemblyMachineRecipes/SILEXRecipes | Entity 基类/注册/渲染前置 |
| 依赖 | MachineBase ✓/BlockDummyable ✓/Container 链路 ✓/GUI 资源（P8 缺口） | CraftingManager（未迁移——Anvil/Assembly 引用其 addXxx）| 实体渲染（P8）/模型资源 |
| 阻塞 | gui_* 纹理资源缺失（可占位）、openMenu 链路（TODO P4.2） | **CraftingManager 1572 行未迁移**（Anvil/Assembly 直接依赖）；SILEX 引用 ItemFluidIcon ✓ 可做 | 实体模型/动画资源 + 渲染系统（P8） |
| 独立可做性 | 中（每机需 gui 纹理占位 + openMenu） | **低**（除 SILEX 外都依赖 CraftingManager） | 低（P8 资源） |
| 预估 | 5 机 × 4 文件 = 20 文件/批 | 3 类（SILEX 可先行，其余待 CM） | 大批 |

## 推荐顺序（编译通过后）

### 阶段 1：方案 B 子集——SILEXRecipes（独立，零 CraftingManager 依赖）
- SILEXRecipes（822 行）：引用 ItemFluidIcon ✓/Fluids ✓/RecipesCommon ✓——纯 SerializableRecipe 静态集合，**立即可迁移**
- 验证 SerializableRecipe 体系在编译后的实际可用性

### 阶段 2：方案 A——机器三件套逐个推进
- 顺序：GasCentrifuge（用 GasCentrifugeRecipes ✓ 已迁移）→ FluidTank → RBMKConsole（BlockDummyable 多方块验证）
- 每机：TE（MachineBase 子类）→ Container（buf.readBlockPos 模式）→ GUI（占位纹理）→ 注册×3
- 前置：BLOCK_TAB accept + openMenu 恢复（BlockDummyable.standardOpenBehavior → player.openMenu(MenuProvider)）

### 阶段 3：方案 C——实体批（P5.2 尾）
- 需 P8 渲染/模型资源——与 P8 合并或先做纯逻辑实体（数据驱动）

## 依赖图
```
编译通过（闸门）
  ├─ SILEXRecipes（独立）→ 配方体系验证
  ├─ GasCentrifuge 三件套 → 容器链路验证
  ├─ openMenu 恢复 → 全部 GUI 可用
  ├─ CraftingManager（P4 配方批）→ Anvil/Assembly 等 60 配方类
  └─ P8（渲染/资源/粒子）→ 机器 GUI 纹理 + 实体渲染 + 粒子
```

## 工作量预估
- 阶段 1：~2 文件
- 阶段 2：~20 文件
- 阶段 3：视 P8 范围（大批）
