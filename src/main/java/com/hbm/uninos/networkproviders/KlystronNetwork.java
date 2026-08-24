package com.hbm.uninos.networkproviders;

import com.hbm.uninos.GenNode;
import com.hbm.uninos.INetworkProvider;
import com.hbm.uninos.NodeNet;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.core.BlockPos;

public class KlystronNetwork extends NodeNet<BlockEntity, BlockEntity, KlystronNetwork.KlystronNode, KlystronNetwork> {

    public static INetworkProvider<KlystronNetwork> THE_PROVIDER = KlystronNetwork::new;

    @Override
    public void update() { }

    public static class KlystronNode extends GenNode<KlystronNetwork> {

        public KlystronNode(INetworkProvider<KlystronNetwork> provider, BlockPos... positions) {
            super(provider, positions);
        }
    }
}
