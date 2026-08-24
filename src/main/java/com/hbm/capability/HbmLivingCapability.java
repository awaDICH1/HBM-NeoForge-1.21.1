package com.hbm.capability;

import com.hbm.Tags;
import com.hbm.capability.HbmLivingProps.ContaminationEffect;
import com.hbm.config.ServerConfig;
import io.netty.buffer.ByteBuf;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.neoforged.neoforge.capabilities.EntityCapability;

import java.util.ArrayList;
import java.util.List;

/**
 * 迁移自 1.12.2 com.hbm.capability.HbmLivingCapability。
 *
 * 1.12 Forge 能力系统（Capability/IStorage/@CapabilityInject/ICapabilitySerializable）
 * → 1.21.1 NeoForge EntityCapability：
 *   - ENT_HBM_PROPS_CAP = EntityCapability.createVoid(...)
 *   - 原 EntityHbmPropsProvider（@CapabilityInject + ICapabilitySerializable）删除，
 *     提供器改为注册时的 lambda（见 ModEvents.registerCapabilities：
 *     event.registerEntity(ENT_HBM_PROPS_CAP, LivingEntity.class, (entity, ctx) -> new EntityHbmProps())）
 *   - 原 EntityHbmPropsStorage 删除：NBT 读写由接口的 saveNBTData/loadNBTData 承担（键名不变，旧存档兼容）
 *   - DUMMY 提升为 HbmLivingCapability.DUMMY（原 EntityHbmPropsProvider.DUMMY）
 *   - MathHelper → Mth；NBTTagCompound → CompoundTag
 *   - ByteBuf 序列化保留（P5 网络批接入实体同步）
 */
public class HbmLivingCapability {

    public interface IEntityHbmProps {

        double getRads();

        void setRads(double rads);

        void increaseRads(double rads);

        void decreaseRads(double rads);

        double getNeutrons();

        void setNeutrons(double rads);

        double getRadsEnv();

        void setRadsEnv(double rads);

        double getRadBuf();

        void setRadBuf(double buf);

        double getDigamma();

        void setDigamma(double dig);

        void increaseDigamma(double dig);

        void decreaseDigamma(double dig);

        int getAsbestos();

        void setAsbestos(int asbestos);

        int getBlacklung();

        void setBlacklung(int blacklung);

        int getBombTimer();

        void setBombTimer(int bombTimer);

        int getContagion();

        void setContagion(int cont);

        int getOil();

        void setOil(int time);

        int getPhosphorus();

        void setPhosphorus(int time);

        int getFire();

        void setFire(int time);

        int getBalefire();

        void setBalefire(int time);

        int getGrenadeDeployment();

        void setGrenadeDeployment(int ticks);

        List<HbmLivingProps.ContaminationEffect> getContaminationEffectList();

        void saveNBTData(CompoundTag tag);

        void loadNBTData(CompoundTag tag);

        /**
         * Scalar reads tolerate cross-thread staleness (next tick re-syncs); the contamination
         * list does not, so the caller must supply a thread-local snapshot taken on whichever
         * thread owns the live list (main thread on the server).
         */
        default void serialize(ByteBuf buf, ContaminationEffect[] contaminationSnapshot) {
            buf.writeByte(1);
            buf.writeDouble(getRads());
            buf.writeDouble(getNeutrons());
            buf.writeDouble(getRadsEnv());
            buf.writeDouble(getRadBuf());
            buf.writeDouble(getDigamma());
            buf.writeInt(getAsbestos());
            buf.writeInt(getBlacklung());
            buf.writeInt(getBombTimer());
            buf.writeInt(getContagion());
            buf.writeInt(getOil());
            buf.writeInt(getPhosphorus());
            buf.writeInt(getFire());
            buf.writeInt(getBalefire());
            buf.writeInt(contaminationSnapshot.length);
            for (ContaminationEffect e : contaminationSnapshot) e.writeTo(buf);
        }

        default void deserialize(ByteBuf buf) {
            if (buf.readByte() != 1) return;
            setRads(buf.readDouble());
            setNeutrons(buf.readDouble());
            setRadsEnv(buf.readDouble());
            setRadBuf(buf.readDouble());
            setDigamma(buf.readDouble());
            setAsbestos(buf.readInt());
            setBlacklung(buf.readInt());
            setBombTimer(buf.readInt());
            setContagion(buf.readInt());
            setOil(buf.readInt());
            setPhosphorus(buf.readInt());
            setFire(buf.readInt());
            setBalefire(buf.readInt());
            List<ContaminationEffect> effects = getContaminationEffectList();
            effects.clear();
            int size = buf.readInt();
            for (int i = 0; i < size; i++) effects.add(ContaminationEffect.readFrom(buf));
        }
    }

    /** 原 EntityHbmPropsProvider.ENT_HBM_PROPS_CAP（@CapabilityInject） */
    public static final EntityCapability<IEntityHbmProps, Void> ENT_HBM_PROPS_CAP =
            EntityCapability.createVoid(ResourceLocation.fromNamespaceAndPath(Tags.MODID, "entity_hbm_props"), IEntityHbmProps.class);

    /** 原 EntityHbmPropsProvider.DUMMY：无能力时的空实现 */
    public static final IEntityHbmProps DUMMY = new IEntityHbmProps() {

        @Override public double getRads() { return 0D; }
        @Override public void setRads(double rads) { }
        @Override public double getNeutrons() { return 0D; }
        @Override public void setNeutrons(double rads) { }
        @Override public double getRadsEnv() { return 0D; }
        @Override public void setRadsEnv(double rads) { }
        @Override public double getRadBuf() { return 0D; }
        @Override public void setRadBuf(double buf) { }
        @Override public double getDigamma() { return 0D; }
        @Override public void setDigamma(double dig) { }
        @Override public void increaseRads(double rads) { }
        @Override public void decreaseRads(double rads) { }
        @Override public void increaseDigamma(double dig) { }
        @Override public void decreaseDigamma(double dig) { }
        @Override public int getAsbestos() { return 0; }
        @Override public void setAsbestos(int asbestos) { }
        @Override public int getBlacklung() { return 0; }
        @Override public void setBlacklung(int blacklung) { }
        @Override public int getBombTimer() { return 0; }
        @Override public void setBombTimer(int bombTimer) { }
        @Override public int getContagion() { return 0; }
        @Override public void setContagion(int cont) { }
        @Override public int getOil() { return 0; }
        @Override public void setOil(int time) { }
        @Override public int getPhosphorus() { return 0; }
        @Override public void setPhosphorus(int phos) { }
        @Override public int getFire() { return 0; }
        @Override public void setFire(int time) { }
        @Override public int getBalefire() { return 0; }
        @Override public void setBalefire(int time) { }
        @Override public int getGrenadeDeployment() { return 0; }
        @Override public void setGrenadeDeployment(int ticks) { }
        @Override public List<ContaminationEffect> getContaminationEffectList() { return new ArrayList<>(0); }
        @Override public void saveNBTData(CompoundTag tag) { }
        @Override public void loadNBTData(CompoundTag tag) { }
    };

    public static class EntityHbmProps implements IEntityHbmProps {

        public static final int maxAsbestos = 60 * 60 * 20;
        public static final int maxBlacklung = 60 * 60 * 20;

        private double rads = 0D;
        private double neutrons = 0D;
        private double envRads = 0D;
        private double radBuf = 0D;
        private double digamma = 0D;
        private int asbestos = 0;
        private int blacklung;
        private int bombTimer;
        private int contagion;
        private int oil;
        public int phosphorus;
        public int fire;
        public int balefire;
        private int grenadeDeployment;
        private final List<HbmLivingProps.ContaminationEffect> contamination = new ArrayList<>();

        @Override public double getRads() { return rads; }

        @Override public void setRads(double rads) { this.rads = Mth.clamp(rads, 0D, 2500D); }

        @Override public double getNeutrons() { return neutrons; }

        @Override public void setNeutrons(double neutrons) { this.neutrons = Math.max(neutrons, 0D); }

        @Override public void increaseRads(double rads) { this.rads = Mth.clamp(this.rads + rads, 0D, 2500D); }

        @Override public void decreaseRads(double rads) { this.rads = Mth.clamp(this.rads - rads, 0D, 2500D); }

        @Override public double getRadsEnv() { return envRads; }

        @Override public void setRadsEnv(double rads) { envRads = rads; }

        @Override public double getRadBuf() { return radBuf; }

        @Override public void setRadBuf(double buf) { radBuf = buf; }

        @Override public double getDigamma() { return digamma; }

        @Override public void setDigamma(double dig) { digamma = dig; }

        @Override public void increaseDigamma(double dig) { this.digamma = Mth.clamp(this.digamma + dig, 0D, 1000D); }

        @Override public void decreaseDigamma(double dig) { this.digamma = Mth.clamp(this.digamma - dig, 0D, 1000D); }

        @Override public int getAsbestos() { return asbestos; }

        @Override public void setAsbestos(int asbestos) { this.asbestos = asbestos; }

        @Override public int getBlacklung() { return blacklung; }

        @Override public void setBlacklung(int blacklung) { this.blacklung = blacklung; }

        @Override public int getBombTimer() { return bombTimer; }

        @Override public void setBombTimer(int bombTimer) { this.bombTimer = bombTimer; }

        @Override public int getContagion() {
            if (!ServerConfig.ENABLE_MKU.get()) return 0;
            return contagion;
        }

        @Override public void setContagion(int cont) { contagion = cont; }

        @Override public int getOil() { return oil; }

        @Override public void setOil(int time) { this.oil = time; }

        @Override public int getPhosphorus() { return phosphorus; }

        @Override public void setPhosphorus(int phosphorus) { this.phosphorus = phosphorus; }

        @Override public int getFire() { return fire; }

        @Override public void setFire(int time) { this.fire = time; }

        @Override public int getBalefire() { return balefire; }

        @Override public void setBalefire(int time) { this.balefire = time; }

        @Override public int getGrenadeDeployment() { return grenadeDeployment; }

        @Override public void setGrenadeDeployment(int ticks) { this.grenadeDeployment = ticks; }

        @Override public List<HbmLivingProps.ContaminationEffect> getContaminationEffectList() { return contamination; }

        @Override
        public void saveNBTData(CompoundTag tag) {
            // Versioned payload (v1): doubles —— 键名与原版一致，旧存档兼容
            tag.putString("fmt", "v1");
            tag.putDouble("rads", this.rads);
            tag.putDouble("neutrons", this.neutrons);
            tag.putDouble("envRads", this.envRads);
            tag.putDouble("radBuf", this.radBuf);
            tag.putDouble("digamma", this.digamma);
            tag.putInt("asbestos", getAsbestos());
            tag.putInt("blacklung", blacklung);
            tag.putInt("bombtimer", bombTimer);
            if (ServerConfig.ENABLE_MKU.get()) tag.putInt("contagion", contagion);
            tag.putInt("oil", getOil());
            tag.putInt("fire", getFire());
            tag.putInt("phosphorus", getPhosphorus());
            tag.putInt("balefire", getBalefire());
            tag.putInt("conteffectsize", contamination.size());
            for (int i = 0; i < contamination.size(); i++) {
                contamination.get(i).save(tag, i);
            }
        }

        @Override
        public void loadNBTData(CompoundTag tag) {
            final boolean isV1 = tag.contains("fmt") && "v1".equals(tag.getString("fmt"));
            if (isV1) {
                this.rads = tag.getDouble("rads");
                this.neutrons = tag.getDouble("neutrons");
                this.envRads = tag.getDouble("envRads");
                this.radBuf = tag.getDouble("radBuf");
                this.digamma = tag.getDouble("digamma");
            } else {
                // Legacy payload (floats)
                this.rads = tag.getFloat("rads");
                this.neutrons = tag.getFloat("neutrons");
                this.envRads = tag.getFloat("envRads");
                this.radBuf = tag.getFloat("radBuf");
                this.digamma = tag.getFloat("digamma");
            }
            setAsbestos(tag.getInt("asbestos"));
            setBlacklung(tag.getInt("blacklung"));
            setBombTimer(tag.getInt("bombtimer"));
            if (ServerConfig.ENABLE_MKU.get()) setContagion(tag.getInt("contagion"));
            setOil(tag.getInt("oil"));
            setFire(tag.getInt("fire"));
            setPhosphorus(tag.getInt("phosphorus"));
            setBalefire(tag.getInt("balefire"));
            contamination.clear();
            for (int i = 0; i < tag.getInt("conteffectsize"); i++) {
                contamination.add(HbmLivingProps.ContaminationEffect.load(tag, i));
            }
        }
    }
}
