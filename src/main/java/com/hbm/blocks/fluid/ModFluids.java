package com.hbm.blocks.fluid;

import com.hbm.Tags;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

/**
 * 流体注册中心（P4.1 批次A 骨架版）。
 * 迁移自 1.12.2 com.hbm.blocks.fluid.ModFluids + net.minecraftforge.fluids.FluidRegistry。
 *
 * 1.21.1 变更：
 *   - 流体拆为两个注册表：Fluid（Registries.FLUID，逻辑）与 FluidType（NeoForgeRegistries.FLUID_TYPES，渲染/物理属性）；
 *   - FluidRegistry.enableUniversalBucket() 删除，桶改由 FluidType + BucketItem 属性实现；
 *   - HBM 自研流体数据表 com.hbm.inventory.fluid.Fluids（P4.1 批次A 已迁移，1103 行，~175 流体 + 特质 + 配置读写 + NBT 序列化）保留；
 *   - 1.12 的 Forge 流体兼容层（FluidRegistry.registerFluid/FluidNTM）已从 Fluids 中删除，由本类桥接承担。
 *
 * TODO P8（渲染/容器批）——桥接设计：
 *   - HBM 构造器：Fluids.init() 先行（纯数据层，不触碰注册表）；
 *   - RegisterEvent（Registries.FLUID / NeoForgeRegistries.FLUID_TYPES）回调中遍历 Fluids.metaOrder：
 *     - 每个非 ffBan 的 HBM FluidType 注册 NeoForge FluidType（Properties.create().descriptionId(...).density(...).temperature(...)）；
 *     - 注册 SimpleFluid(ffType, Fluid.Properties.create().density().viscosity().temperature())，注册名 = fluid.getName().toLowerCase(Locale.US)；
 *     - 流体方块（LiquidBlock）与流动/静止双条目随 P5 方块批；
 *   - 双向映射：HBM FluidType.getFF()（Registries.FLUID.get，已实现）↔ 反向查表（MetaOrder 遍历时建 HashMap<Fluid, FluidType>）；
 *   - 桶/BucketItem 由 FluidType 属性 + P8 物品批实现。
 */
public class ModFluids {

    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(Registries.FLUID, Tags.MODID);
    public static final DeferredRegister<FluidType> FLUID_TYPES = DeferredRegister.create(NeoForgeRegistries.FLUID_TYPES, Tags.MODID);

    // ===== P8 迁移模板 =====
    //
    // 原 1.12.2：Fluid toxic = new Fluid("toxic_fluid", ResourceLocation.parse("hbm:blocks/..."), ...).setBlock(toxic_block);
    //
    // 1.21.1：
    //   FLUID_TYPES.register("toxic_fluid", () -> new FluidType(FluidType.Properties.create()
    //           .descriptionId("block.hbm.toxic_fluid").density(1500).viscosity(2000).temperature(300)));
    //   FLUIDS.register("toxic_fluid", () -> new SimpleFluid(ModFluids.TOXIC_TYPE.get(), Fluid.Properties.create().density(1500).viscosity(2000)));
    //   // 流体方块：LiquidBlock；流动/静止用 FlowingFluid 时需两个条目。
}
