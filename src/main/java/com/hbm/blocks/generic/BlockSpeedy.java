package com.hbm.blocks.generic;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

import java.util.List;

/**
 * 迁移自 1.12.2 com.hbm.blocks.generic.BlockSpeedy。
 *
 * 原实现：
 *   - extends BlockBakeBase implements IStepTickReceiver, ITooltipProvider
 *   - onPlayerStep(Level, x, y, z, Player)：客户端将 motionX/motionZ 乘以 speed
 *   - addInformation：蓝色提示 "+X% speed"
 *
 * 1.21.1 变更：
 *   - 踩踏回调：IStepTickReceiver 事件 → 原版 Block#stepOn(Level, BlockPos, BlockState, Entity)
 *   - 移动：player.motionX/Z → player.getDeltaMovement()/setDeltaMovement()
 *   - tooltip：addInformation → appendHoverText；ChatFormatting.BLUE → ChatFormatting.BLUE
 *   - 材质：new BlockSpeedy(Material.ROCK, "asphalt", 1.5) → new BlockSpeedy(BlockBehaviour.Properties.of().strength(...), 1.5D)
 */
public class BlockSpeedy extends Block {

    private final double speed;

    public BlockSpeedy(BlockBehaviour.Properties properties, double speed) {
        super(properties);
        this.speed = speed;
    }

    @Override
    public void stepOn(Level level, BlockPos pos, BlockState state, Entity entity) {
        // 原 onPlayerStep：仅客户端生效，避免服务端修正造成抖动
        if (!level.isClientSide || !(entity instanceof Player player)) {
            return;
        }
        if (player.zza != 0 || player.xxa != 0) {
            Vec3 motion = player.getDeltaMovement();
            player.setDeltaMovement(motion.x * speed, motion.y, motion.z * speed);
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
        tooltip.add(Component.literal("Increases speed by " + (int) ((speed - 1) * 100) + "%")
                .withStyle(ChatFormatting.BLUE));
    }
}
