package com.hbm.capability;

import com.hbm.capability.HbmLivingCapability.IEntityHbmProps;
import com.hbm.config.RadiationConfig;
import com.hbm.config.ServerConfig;
import com.hbm.lib.ModDamageSource;
import com.hbm.network.ModNetwork;
import com.hbm.packet.toclient.PlayerInformPacketLegacy;
import io.netty.buffer.ByteBuf;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;

import java.util.List;
import java.util.UUID;

/**
 * 迁移自 1.12.2 com.hbm.capability.HbmLivingProps。
 *
 * 变更：
 *   - hasCapability/getCapability(EntityHbmPropsProvider.ENT_HBM_PROPS_CAP, null)
 *     → entity.getCapability(HbmLivingCapability.ENT_HBM_PROPS_CAP)（null → DUMMY）
 *   - getAttributeMap().getAttributeInstance(SharedMonsterAttributes.MAX_HEALTH)
 *     → getAttributes().getInstance(Attributes.MAX_HEALTH)
 *   - applyModifier → addTransientModifier；操作码 2 → AttributeModifier.Operation.MULTIPLY_TOTAL
 *   - attackEntityFrom → hurt；isEntityAlive → isAlive；EntityPlayerMP → ServerPlayer
 *   - NBTTagCompound → CompoundTag
 *
 * ⚠️ TODO 占位（依赖未迁移系统）：
 *   - setDigamma：粒子（HbmEffectNT.Sweat + AuxParticlePacketNT + PacketThreading，P8/P5 网络批）、
 *     成就（AdvancementManager，P5）、onDeath 显式调用（1.21.1 为 protected，hurt 流程已覆盖）
 *   - incrementAsbestos/incrementBlackLung：PlayerInformPacketLegacy 包（P5 网络批）
 */
public class HbmLivingProps {

    public static final UUID digamma_UUID = UUID.fromString("2a3d8aec-5ab9-4218-9b8b-ca812bdf378b");
    /** 1.21.1 AttributeModifier 改用 ResourceLocation 作为 id（原 UUID 构造器已移除） */
    private static final ResourceLocation digamma_ID = ResourceLocation.fromNamespaceAndPath("hbm", "digamma");
    public static final int maxAsbestos = 60 * 60 * 20;
    public static final int maxBlacklung = 2 * 60 * 60 * 20;

    public static IEntityHbmProps getData(LivingEntity entity) {
        IEntityHbmProps props = entity.getCapability(HbmLivingCapability.ENT_HBM_PROPS_CAP);
        return props != null ? props : HbmLivingCapability.DUMMY;
    }

    /// RADIATION ///
    public static double getRadiation(LivingEntity entity) {
        if (!RadiationConfig.enableContamination) return 0;
        return getData(entity).getRads();
    }

    public static void setRadiation(LivingEntity entity, double rad) {
        if (RadiationConfig.enableContamination) getData(entity).setRads(rad);
    }

    public static void incrementRadiation(LivingEntity entity, double rad) {
        if (!RadiationConfig.enableContamination) return;
        double radiation = getRadiation(entity) + rad;

        if (radiation > 25000000) radiation = 25000000;
        if (radiation < 0) radiation = 0;

        setRadiation(entity, radiation);
    }

    // Neutron Radiation
    public static double getNeutron(LivingEntity entity) {
        return getData(entity).getNeutrons();
    }

    public static void setNeutron(LivingEntity entity, double rad) {
        getData(entity).setNeutrons(rad);
    }

    /// RAD ENV ///
    public static double getRadEnv(LivingEntity entity) {
        return getData(entity).getRadsEnv();
    }

    public static void setRadEnv(LivingEntity entity, double rad) {
        getData(entity).setRadsEnv(rad);
    }

    /// RAD BUF ///
    public static double getRadBuf(LivingEntity entity) {
        return getData(entity).getRadBuf();
    }

    public static void setRadBuf(LivingEntity entity, double rad) {
        getData(entity).setRadBuf(rad);
    }

    /// DIGAMMA ///
    public static double getDigamma(LivingEntity entity) {
        return getData(entity).getDigamma();
    }

    public static void setDigamma(LivingEntity entity, double digamma) {

        getData(entity).setDigamma(digamma);

        double healthMod = Math.pow(0.5, digamma) - 1D;

        AttributeInstance attributeinstance = entity.getAttributes().getInstance(Attributes.MAX_HEALTH);

        try {
            attributeinstance.removeModifier(digamma_ID);
        } catch (Exception ex) {
        }

        attributeinstance.addTransientModifier(new AttributeModifier(digamma_ID, healthMod, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));

        if (entity.getHealth() > entity.getMaxHealth()) {
            entity.setHealth(entity.getMaxHealth());
        }

        if ((entity.getMaxHealth() <= 0 || digamma >= 10.0D) && entity.isAlive()) {
            entity.setAbsorptionAmount(0);
            entity.hurt(ModDamageSource.digamma, 5000000F);
            // TODO P8: 原粒子 Sweat（HbmEffectNT + AuxParticlePacketNT + PacketThreading）+ 原显式 onDeath（1.21.1 protected，hurt 流程已触发）
        }

        if (entity instanceof Player) {
            // TODO P5: AdvancementManager.grantAchievement(digammaSee/Feel/Know)
        }
    }

    public static void incrementDigamma(LivingEntity entity, double digamma) {
        double dRad = getDigamma(entity) + digamma;

        if (dRad > 10) dRad = 10;
        if (dRad < 0) dRad = 0;

        setDigamma(entity, dRad);
    }

    /// ASBESTOS ///
    public static int getAsbestos(LivingEntity entity) {
        return getData(entity).getAsbestos();
    }

    public static void setAsbestos(LivingEntity entity, int asbestos) {
        IEntityHbmProps props = getData(entity);
        props.setAsbestos(asbestos);

        if (asbestos >= HbmLivingCapability.EntityHbmProps.maxAsbestos) {
            props.setAsbestos(0);
            entity.hurt(ModDamageSource.asbestos, 1000);
        }
    }

    public static void incrementAsbestos(LivingEntity entity, int asbestos) {
        setAsbestos(entity, getAsbestos(entity) + asbestos);
        if (entity instanceof ServerPlayer sp) {
            // 原 PacketDispatcher.wrapper.sendTo(new PlayerInformPacketLegacy(...)) → PacketDistributor.sendToPlayer
            ModNetwork.CHANNEL.sendToPlayer(sp,
                    PlayerInformPacketLegacy.component(Component.translatable("info.asbestos").withStyle(ChatFormatting.RED), 10, 3000));
        }
    }

    public static void addCont(LivingEntity entity, ContaminationEffect cont) {
        getData(entity).getContaminationEffectList().add(cont);
    }

    /// BLACK LUNG DISEASE ///
    public static int getBlackLung(LivingEntity entity) {
        return getData(entity).getBlacklung();
    }

    public static void setBlackLung(LivingEntity entity, int blacklung) {
        IEntityHbmProps props = getData(entity);
        props.setBlacklung(blacklung);

        if (blacklung >= HbmLivingCapability.EntityHbmProps.maxBlacklung) {
            props.setBlacklung(0);
            entity.hurt(ModDamageSource.blacklung, 1000);
        }
    }

    public static void incrementBlackLung(LivingEntity entity, int blacklung) {
        setBlackLung(entity, getBlackLung(entity) + blacklung);
        if (entity instanceof ServerPlayer sp) {
            // 原 PacketDispatcher.wrapper.sendTo(new PlayerInformPacketLegacy(...)) → PacketDistributor.sendToPlayer
            ModNetwork.CHANNEL.sendToPlayer(sp,
                    PlayerInformPacketLegacy.component(Component.translatable("info.coaldust").withStyle(ChatFormatting.RED), 10, 3000));
        }
    }

    /// TIME BOMB ///
    public static int getTimer(LivingEntity entity) {
        return getData(entity).getBombTimer();
    }

    public static void setTimer(LivingEntity entity, int bombTimer) {
        getData(entity).setBombTimer(bombTimer);
    }

    /// CONTAGION ///
    public static int getContagion(LivingEntity entity) {
        if (!ServerConfig.ENABLE_MKU.get()) return 0;
        return getData(entity).getContagion();
    }

    public static void setContagion(LivingEntity entity, int contageon) {
        getData(entity).setContagion(contageon);
    }

    public static List<ContaminationEffect> getCont(LivingEntity e) {
        return getData(e).getContaminationEffectList();
    }

    /// OIL ///
    public static int getOil(LivingEntity entity) {
        return getData(entity).getOil();
    }

    public static void setOil(LivingEntity entity, int oil) {
        getData(entity).setOil(oil);
    }

    public static class ContaminationEffect {

        public double maxRad;
        public int maxTime;
        public int time;
        public boolean ignoreArmor;

        public ContaminationEffect(double rad, int time, boolean ignoreArmor) {
            this.maxRad = rad;
            this.maxTime = this.time = time;
            this.ignoreArmor = ignoreArmor;
        }

        public static ContaminationEffect load(CompoundTag nbt, int index) {
            CompoundTag me = nbt.getCompound("cont_" + index);
            double maxRad = me.getDouble("maxRad");
            int maxTime = me.getInt("maxTime");
            int time = me.getInt("time");
            boolean ignoreArmor = me.getBoolean("ignoreArmor");

            ContaminationEffect effect = new ContaminationEffect(maxRad, maxTime, ignoreArmor);
            effect.time = time;
            return effect;
        }

        public double getRad() {
            return maxRad * ((double) time / (double) maxTime);
        }

        public void save(CompoundTag nbt, int index) {
            CompoundTag me = new CompoundTag();
            me.putDouble("maxRad", this.maxRad);
            me.putInt("maxTime", this.maxTime);
            me.putInt("time", this.time);
            me.putBoolean("ignoreArmor", ignoreArmor);
            nbt.put("cont_" + index, me);
        }

        public void writeTo(ByteBuf buf) {
            buf.writeDouble(maxRad);
            buf.writeInt(maxTime);
            buf.writeInt(time);
            buf.writeBoolean(ignoreArmor);
        }

        public static ContaminationEffect readFrom(ByteBuf buf) {
            double maxRad = buf.readDouble();
            int maxTime = buf.readInt();
            int time = buf.readInt();
            boolean ignoreArmor = buf.readBoolean();
            ContaminationEffect effect = new ContaminationEffect(maxRad, maxTime, ignoreArmor);
            effect.time = time;
            return effect;
        }
    }
}
