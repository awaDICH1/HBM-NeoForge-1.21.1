package com.hbm.tileentity.machine;

import com.hbm.api.fluidmk2.FluidNode;
import com.hbm.api.fluidmk2.IFluidStandardTransceiverMK2;
import com.hbm.blocks.ModBlocks;
import com.hbm.inventory.fluid.FluidType;
import com.hbm.inventory.fluid.Fluids;
import com.hbm.inventory.fluid.tank.FluidTankNTM;
import com.hbm.lib.DirPos;
import com.hbm.lib.Library;
import com.hbm.tileentity.IConnectionAnchors;
import com.hbm.tileentity.IFluidCopiable;
import com.hbm.tileentity.IPersistentNBT;
import com.hbm.tileentity.TileEntityMachineBase;
import com.hbm.uninos.UniNodespace;
import io.netty.buffer.ByteBuf;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import java.util.HashSet;

/**
 * 流体桶（P5.2 精简迁移版）。
 *
 * 迁移自 1.12.2 com.hbm.tileentity.machine.TileEntityBarrel（541 行）。
 * 保留：流体存储（FluidTankNTM）、fluidmk2 网络节点（buffer/常规模式）、红石比较器、
 * 模式系统（0 进出/1 buffer/2 出/3 封存）、网络同步（serialize/deserialize）、流体复制（IFluidCopiable）。
 * 删除/桩（TODO）：
 *  - OpenComputers（li.cil.oc @Optional）/IRORValueProvider/IRORInteractive/IFFtoNTMF/IGUIProvider 接口（P5.2/P8）
 *  - Forge FluidTank（tank）与 convertAndSetFluid 旧存档转换（tankNew 为主，TODO 兼容）
 *  - NTMFluidHandlerWrapper 流体能力（TODO P5.2 capability 批）
 *  - checkFluidInteraction 的 ModBlocks.barrel_antimatter/plastic/corroded（方块未迁移，TODO P3）
 *  - deserialize 的客户端重渲染（TODO P8）
 */
public class TileEntityBarrel extends TileEntityMachineBase implements IPersistentNBT, IFluidCopiable, IFluidStandardTransceiverMK2, IConnectionAnchors {

    public static final short modes = 4;
    private static final int[] slots_top = new int[]{2};
    private static final int[] slots_bottom = new int[]{3, 5};
    private static final int[] slots_side = new int[]{4};
    protected FluidNode node;
    public byte lastRedstone = 0;
    protected FluidType lastType;
    public FluidTankNTM tankNew;
    public short mode = 0;
    private int age = 0;
    private boolean shouldDrop = true;

    public TileEntityBarrel(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state, 6, true, false);
        tankNew = new FluidTankNTM(Fluids.NONE, 0).withOwner(this);
    }

    public TileEntityBarrel(BlockEntityType<?> type, BlockPos pos, BlockState state, int cap) {
        super(type, pos, state, 6, true, false);
        tankNew = new FluidTankNTM(Fluids.NONE, cap).withOwner(this);
    }

    /** BlockEntityType.Builder.of 需要的工厂签名：(BlockPos, BlockState) -> T */
    public static TileEntityBarrel create(BlockPos pos, BlockState state) {
        return new TileEntityBarrel(com.hbm.tileentity.ModTileEntities.BARREL.get(), pos, state);
    }

    @Override
    public long getDemand(FluidType type, int pressure) {
        if (this.tilted) return 0;
        if (this.mode == 2 || this.mode == 3) return 0;

        if (tankNew.getPressure() != pressure) return 0;

        return type == tankNew.getTankType() ? tankNew.getMaxFill() - tankNew.getFill() : 0;
    }

    @Override
    public long transferFluid(FluidType type, int pressure, long fluid) {
        long toTransfer = Math.min(getDemand(type, pressure), fluid);
        tankNew.setFill(tankNew.getFill() + (int) toTransfer);
        this.setChanged();
        return fluid - toTransfer;
    }

    // 1.21: BlockEntity 无 tick() 实例方法（由 Block.getTicker 的 BlockEntityTicker lambda 调用，见 ModBlocks.BARREL）
    public void tick() {
        if (this.level == null) return;

        if (!this.level.isClientSide) {
            tankNew.setType(0, 1, inventory);
            tankNew.loadTank(2, 3, inventory);
            tankNew.unloadTank(4, 5, inventory);

            byte comp = tankNew.getRedstoneComparatorPower();
            if (comp != this.lastRedstone) {
                this.setChanged();
                for (DirPos pos : getConPos()) this.updateRedstoneComparatorConnection(pos);
            }
            this.lastRedstone = comp;

            if (mode == 1) {
                if (this.node == null || this.node.expired || tankNew.getTankType() != lastType) {
                    this.node = (FluidNode) UniNodespace.getNode(this.level, this.worldPosition, tankNew.getTankType().getNetworkProvider());
                    if (this.node == null || this.node.expired || tankNew.getTankType() != lastType) {
                        this.node = this.createNode(tankNew.getTankType());
                        UniNodespace.createNode(this.level, this.node);
                        lastType = tankNew.getTankType();
                    }
                }
                if (node != null && node.hasValidNet()) {
                    node.net.addProvider(this);
                    node.net.addReceiver(this);
                }
            } else {
                if (this.node != null) {
                    UniNodespace.destroyNode(this.level, this.worldPosition, tankNew.getTankType().getNetworkProvider());
                    this.node = null;
                }
                if (!this.tilted) for (DirPos pos : getConPos()) {
                    FluidNode dirNode = (FluidNode) UniNodespace.getNode(this.level, pos.getBlockPos(), tankNew.getTankType().getNetworkProvider());
                    if (mode == 2) {
                        tryProvide(tankNew, this.level, pos.getBlockPos(), pos.getDir());
                    } else {
                        if (dirNode != null && dirNode.hasValidNet()) dirNode.net.removeProvider(this);
                    }
                    if (mode == 0) {
                        if (dirNode != null && dirNode.hasValidNet()) dirNode.net.addReceiver(this);
                    } else {
                        if (dirNode != null && dirNode.hasValidNet()) dirNode.net.removeReceiver(this);
                    }
                }
            }

            if (tankNew.getFill() > 0) {
                checkFluidInteraction();
            }

            this.networkPackNT(50);
        }
    }

    protected FluidNode createNode(FluidType type) {
        DirPos[] conPos = getConPos();
        HashSet<BlockPos> posSet = new HashSet<>();
        posSet.add(this.worldPosition);
        for (DirPos pos : conPos) {
            ForgeDirectionDir(pos, posSet);
        }
        return new FluidNode(type.getNetworkProvider(), posSet.toArray(new BlockPos[posSet.size()])).setConnections(conPos);
    }

    private void ForgeDirectionDir(DirPos pos, HashSet<BlockPos> posSet) {
        com.hbm.lib.ForgeDirection dir = pos.getDir();
        posSet.add(new BlockPos(pos.getBlockPos().getX() - dir.offsetX, pos.getBlockPos().getY() - dir.offsetY, pos.getBlockPos().getZ() - dir.offsetZ));
    }

    @Override
    public void serialize(ByteBuf buf) {
        super.serialize(buf);
        buf.writeShort(mode);
        tankNew.serialize(buf);
    }

    @Override
    public void deserialize(ByteBuf buf) {
        super.deserialize(buf);
        mode = buf.readShort();
        tankNew.deserialize(buf);
    }

    public DirPos[] getConPos() {
        return new DirPos[]{
                new DirPos(this.worldPosition.getX() + 1, this.worldPosition.getY(), this.worldPosition.getZ(), Library.POS_X),
                new DirPos(this.worldPosition.getX() - 1, this.worldPosition.getY(), this.worldPosition.getZ(), Library.NEG_X),
                new DirPos(this.worldPosition.getX(), this.worldPosition.getY() + 1, this.worldPosition.getZ(), Library.POS_Y),
                new DirPos(this.worldPosition.getX(), this.worldPosition.getY() - 1, this.worldPosition.getZ(), Library.NEG_Y),
                new DirPos(this.worldPosition.getX(), this.worldPosition.getY(), this.worldPosition.getZ() + 1, Library.POS_Z),
                new DirPos(this.worldPosition.getX(), this.worldPosition.getY(), this.worldPosition.getZ() - 1, Library.NEG_Z)
        };
    }

    public void checkFluidInteraction() {
        Block b = this.getBlockState().getBlock();
        // TODO P3: ModBlocks.barrel_antimatter/barrel_plastic/barrel_corroded 方块未迁移——恢复后实现
        // 反物质爆炸/腐蚀/塑料桶高温损坏逻辑（原 305-326 行）
    }

    @Override
    public boolean shouldDrop() {
        return IPersistentNBT.super.shouldDrop() && shouldDrop;
    }

    @Override
    public String getDefaultName() {
        return "container.barrel";
    }

    @Override
    public void setDestroyedByCreativePlayer() {
        shouldDrop = false;
    }

    @Override
    public boolean isDestroyedByCreativePlayer() {
        return !shouldDrop;
    }

    @Override
    public void writeNBT(CompoundTag nbt) {
        tankNew.writeToNBT(nbt, "tank");
        nbt.putShort("mode", mode);
        nbt.putBoolean("shouldDrop", shouldDrop);
    }

    @Override
    public void readNBT(CompoundTag nbt) {
        tankNew.readFromNBT(nbt, "tank");
        mode = nbt.getShort("mode");
        if (nbt.contains("shouldDrop")) shouldDrop = nbt.getBoolean("shouldDrop");
    }

    @Override
    public int[] getFluidIDToCopy() {
        return new int[]{tankNew.getTankType().getID()};
    }

    @Override
    public FluidTankNTM getTankToPaste() {
        return tankNew;
    }

    // TODO P5.2: 1.12 ForgeCapabilities.FLUID_HANDLER → 1.21 注册侧 BlockCapability（Capabilities.FluidHandler.BLOCK，模式相关 fill/drain 限制）

    @Override
    public FluidTankNTM[] getAllTanks() {
        return new FluidTankNTM[]{tankNew};
    }

    @Override
    public FluidTankNTM[] getSendingTanks() {
        return new FluidTankNTM[]{tankNew};
    }

    @Override
    public FluidTankNTM[] getReceivingTanks() {
        return new FluidTankNTM[]{tankNew};
    }
}
