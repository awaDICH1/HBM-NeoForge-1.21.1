package com.hbm.inventory.fluid.tank;

import com.hbm.inventory.fluid.FluidType;
import com.hbm.inventory.fluid.Fluids;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Mth;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.neoforge.items.IItemHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 流体储罐（P4.1 批次A 迁移版）。
 *
 * 迁移自 1.12.2 com.hbm.inventory.fluid.tank.FluidTankNTM（504 行）。
 * 变更：
 *  - 删除 1.12 Forge 兼容层（implements IFluidHandler/IFluidTank + 全部 @Deprecated Forge 方法：
 *    getTankProperties/getFluid/getFluidAmount/getCapacity/getInfo/fill(FluidStack,boolean)/drain×2/getTankTypeFF）——
 *    1.21 NeoForge IFluidHandler 签名不同（getTanks/getFluidInTank/fill(FluidStack,FluidAction)），P8 桥接时重建
 *  - 删除渲染层 renderTank/renderTankInfo（GL11/BufferBuilder/GuiInfoContainer 客户端系统，TODO P8）
 *  - onTypeChanged 的 IConnectionAnchors.notifyAnchors → TODO P5 桩
 *  - setType 的 IItemFluidIdentifier 分支 → TODO P5 桩
 *  - static 块 FluidLoader* 加载器注册 → TODO P4.1（fluidmk2 恢复时随迁）；noDualUnload 的 ModItems 引用 → TODO P5
 *  - IItemHandler → net.neoforged.neoforge.items.IItemHandler
 */
public class FluidTankNTM implements Cloneable {

    public static final List<IFluidLoadingHandler> loadingHandlers = new ArrayList<>();
    public static final Set<Item> noDualUnload = new HashSet<>();

    static {
        // TODO P4.1（fluidmk2 恢复批）: loadingHandlers.add(new FluidLoaderStandard()); //fluid containers
        // TODO P4.1（fluidmk2 恢复批）: loadingHandlers.add(new FluidLoaderFillableItem());
        // TODO P4.1（fluidmk2 恢复批）: loadingHandlers.add(new FluidLoaderInfinite());
        // TODO P4.1（fluidmk2 恢复批）: loadingHandlers.add(new FluidLoaderForge());
        // TODO P5: noDualUnload.add(ModItems.chlorine_pinwheel); 等 4 项（machine 物品未迁移）
    }

    @Deprecated
    public int index = 0;
    @NotNull
    FluidType type;
    int fluid;
    int maxFluid;
    int pressure = 0;

    @Nullable
    private BlockEntity owner;

    public FluidTankNTM withOwner(BlockEntity te) {
        this.owner = te;
        return this;
    }

    private void onTypeChanged() {
        // TODO P5: IConnectionAnchors.notifyAnchors(this.owner) —— 连接锚系统未迁移
    }

    public FluidTankNTM(@NotNull FluidType type, int maxFluid) {
        this.type = type;
        this.maxFluid = maxFluid;
    }

    @Deprecated // indices are no longer needed
    public FluidTankNTM(@NotNull FluidType type, int maxFluid, int index) {
        this.type = type;
        this.maxFluid = maxFluid;
        this.index = index;
    }

    public byte getRedstoneComparatorPower() {
        if(getFill() == 0) return 0;
        double frac = (double) getFill() / (double) getMaxFill() * 15D;
        return (byte) (Mth.clamp((int) frac, 1, 15));
    }

    public FluidTankNTM withPressure(int pressure) {
        if (this.pressure != pressure) this.setFill(0);
        this.pressure = pressure;
        return this;
    }

    @NotNull
    public FluidType getTankType() {
        return type;
    }

    public void setTankType(FluidType type) {
        if (type == null) {
            type = Fluids.NONE;
        }
        if (this.type == type) return;

        this.type = type;
        this.setFill(0);
        onTypeChanged();
    }

    public void resetTank() {
        boolean changed = this.type != Fluids.NONE;
        this.type = Fluids.NONE;
        this.fluid = 0;
        this.pressure = 0;
        if (changed) onTypeChanged();
    }

    /** Changes type and pressure based on a fluid stack, useful for changing tank types based on recipes */
    public FluidTankNTM conform(com.hbm.inventory.fluid.FluidStack stack) {
        this.setTankType(stack.type);
        this.withPressure(stack.pressure);
        return this;
    }

    public int getFill() {
        return fluid;
    }

    public void setFill(int i) {
        fluid = Math.max(0, Math.min(i, maxFluid));
    }

    public int getMaxFill() {
        return maxFluid;
    }

    public int getPressure() {
        return pressure;
    }

    public int changeTankSize(int size) {
        maxFluid = size;
        if (fluid > maxFluid) {
            int dif = fluid - maxFluid;
            fluid = maxFluid;
            return dif;
        }
        return 0;
    }

    //Fills tank from canisters
    public boolean loadTank(int in, int out, @NotNull IItemHandler slots) {

        if (slots.getStackInSlot(in).isEmpty()) return false;

        // TODO P5: isInfiniteBarrel 检查（ModItems.fluid_barrel_infinite 未迁移）
        boolean isInfiniteBarrel = false;

        if (!isInfiniteBarrel && pressure != 0) return false;

        int prev = this.getFill();

        for (IFluidLoadingHandler handler : loadingHandlers) {
            if (handler.emptyItem(slots, in, out, this)) {
                break;
            }
        }

        return this.getFill() > prev;
    }

    //Fills canisters from tank
    public boolean unloadTank(int in, int out, @NotNull IItemHandler slots) {

        if (slots.getStackInSlot(in).isEmpty()) return false;

        int prev = this.getFill();

        for (IFluidLoadingHandler handler : loadingHandlers) {
            if (handler.fillItem(slots, in, out, this)) {
                break;
            }
        }

        return this.getFill() < prev;
    }

    public boolean setType(int in, @NotNull IItemHandler slots) {
        return setType(in, in, slots);
    }

    /**
     * Changes the tank type and returns true if successful
     *
     * @param in
     * @param out
     * @param slots
     * @return
     */
    public boolean setType(int in, int out, @NotNull IItemHandler slots) {

        // TODO P5: IItemFluidIdentifier 分支（items.machine 未迁移）——流体识别器物品换罐类型
        /*
        if (!slots.getStackInSlot(in).isEmpty() && slots.getStackInSlot(in).getItem() instanceof IItemFluidIdentifier id) {
            if (in == out) {
                FluidType newType = id.getType(null, 0, 0, 0, slots.getStackInSlot(in));
                if (type != newType) {
                    type = newType;
                    fluid = 0;
                    onTypeChanged();
                    return true;
                }
            } else if (slots.getStackInSlot(out).isEmpty()) {
                FluidType newType = id.getType(null, 0, 0, 0, slots.getStackInSlot(in));
                if (type != newType) {
                    type = newType;
                    slots.insertItem(out, slots.getStackInSlot(in).copy(), false);
                    slots.getStackInSlot(in).shrink(1);
                    fluid = 0;
                    onTypeChanged();
                    return true;
                }
            }
        }
        */

        return false;
    }

    //Called by TE to save fillstate
    public void writeToNBT(CompoundTag nbt, String s) {
        nbt.putInt(s, fluid);
        nbt.putInt(s + "_max", maxFluid);
        Fluids.writeType(nbt, s + "_type", type); //stored by name, IDs shift when fluids are added/removed
        nbt.putShort(s + "_p", (short) pressure);
    }

    //Called by TE to load fillstate
    public void readFromNBT(@NotNull CompoundTag nbt, String s) {
        fluid = nbt.getInt(s);
        int max = nbt.getInt(s + "_max");
        if (max > 0) maxFluid = max;

        fluid = Mth.clamp(fluid, 0, max);

        type = Fluids.readType(nbt, s + "_type"); //name-based, with legacy numeric-ID fallback

        this.pressure = nbt.getShort(s + "_p");
    }

    public void serialize(@NotNull io.netty.buffer.ByteBuf buf) {
        buf.writeInt(fluid);
        buf.writeInt(maxFluid);
        buf.writeInt(type.getID());
        buf.writeShort((short) pressure);
    }

    public void deserialize(@NotNull io.netty.buffer.ByteBuf buf) {
        fluid = buf.readInt();
        maxFluid = buf.readInt();
        type = Fluids.fromID(buf.readInt());
        pressure = buf.readShort();
    }

    /** 显示辅助（P8 渲染迁移前暂以文本呈现） */
    public void renderTankInfoText(List<String> list) {
        list.add(this.type.getConditionalName());
        list.add(fluid + "/" + maxFluid + "mB");
        if (this.pressure != 0) {
            list.add(ChatFormatting.RED + "Pressure: " + this.pressure + " PU");
        }
        type.addInfo(list);
    }

    // TODO P8: renderTank/renderTankInfo（GL11/BufferBuilder/Tessellator/GUIElements 客户端渲染）——已删除，渲染迁移时重建

    public int fill(@Nullable FluidType incomingType, int amount, boolean doFill) {
        if (incomingType == null || incomingType == Fluids.NONE || amount <= 0) return 0;
        if (this.type == Fluids.NONE) {
            int toTransfer = Math.min(getMaxFill(), amount);
            if (doFill) {
                this.type = incomingType;
                this.setFill(toTransfer);
                onTypeChanged();
            }
            return toTransfer;
        } else {
            if (!this.type.equals(incomingType)) return 0;
            int toTransfer = Math.min(getMaxFill() - getFill(), amount);
            if (doFill && toTransfer > 0) setFill(getFill() + toTransfer);
            return toTransfer;
        }
    }

    @Override
    public FluidTankNTM clone() {
        try {
            return (FluidTankNTM) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
