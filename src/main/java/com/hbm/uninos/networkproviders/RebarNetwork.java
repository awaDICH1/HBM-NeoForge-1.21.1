package com.hbm.uninos.networkproviders;

import com.hbm.lib.DirPos;
import com.hbm.uninos.GenNode;
import com.hbm.uninos.INetworkProvider;
import com.hbm.uninos.NodeNet;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.core.BlockPos;

public class RebarNetwork extends NodeNet<BlockEntity, BlockEntity, RebarNetwork.RebarNode, RebarNetwork> {

    public static final INetworkProvider<RebarNetwork> THE_PROVIDER = RebarNetwork::new;

    @Override
    public void update() { }

    public static class RebarNode extends GenNode<RebarNetwork> {

        public RebarNode(INetworkProvider<RebarNetwork> provider, BlockPos... positions) {
            super(provider, positions);
        }

        @Override
        public RebarNode setConnections(DirPos... connections) {
            return (RebarNode) super.setConnections(connections);
        }
    }
}
