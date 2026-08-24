package com.hbm.tileentity.machine;

import com.hbm.inventory.container.ContainerGasCentrifuge;
import com.hbm.tileentity.ModTileEntities;
import com.hbm.tileentity.TileEntityMachineBase;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

/**
 * GasCentrifuge（P5.2 三件套模板生成）。
 * 基于 TileEntityMachineBase；具体机器逻辑按 CE 适配。
 */
public class TileEntityGasCentrifuge extends TileEntityMachineBase {

    public TileEntityGasCentrifuge(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state, 4, true, false);
    }

    public static TileEntityGasCentrifuge create(BlockPos pos, BlockState state) {
        return new TileEntityGasCentrifuge(ModTileEntities.GAS_CENT.get(), pos, state);
    }

    public void tick() {
        if (this.level == null || this.level.isClientSide) return;
        // TODO: GasCentrifuge processing logic (fluid input → item output via GasCentrifugeRecipes)
    }

    @Override
    public String getDefaultName() {
        return "container.gas_cent";
    }

    public MenuProvider getMenuProvider() {
        return new SimpleMenuProvider((id, inv, player) -> new ContainerGasCentrifuge(id, inv, this), Component.literal("Gas Centrifuge"));
    }
}
