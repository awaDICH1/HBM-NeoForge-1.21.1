package com.hbm.lib;

import com.hbm.Tags;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.Entity;

/**
 * 迁移自 1.12.2 com.hbm.lib.ModDamageSource。
 *
 * 1.21.1 变更：
 *   - new DamageSource("name") → new DamageSource(Holder<DamageType>)；
 *     1.21 起 damage_type 为数据驱动注册表，代码中只能用 Holder.direct(DamageType) 占位
 *     （死亡信息键 = DamageType.msgId = name，与原 death.attack.<name> 一致 ✓）
 *   - setDamageBypassesArmor/explosion/projectile 等链式方法在 1.21.1 的 DamageSource 上已移除，
 *     对应语义改由数据包 damage_type 的 tag 表达（bypasses_armor / is_explosion / is_projectile ...）
 *     → TODO P-datapack：注册 data/hbm/damage_type/*.json 后恢复全部属性
 *   - setDamageAllowedInCreativeMode → 1.21.1 无独立标志（原 digamma/ams/nitan 语义待数据包）
 *   - EntityDamageSourceIndirect → 用 (Holder, direct, causing) 构造
 *   - DamageSource.getMsgId() 返回 String（原 ResourceLocation）→ 比较直接用字符串
 */
public class ModDamageSource extends DamageSource {

    /** 1.21.1 占位：直接 Holder<DamageType>，msgId 即原伤害名（死亡信息键不变） */
    private static Holder<DamageType> dt(String name) {
        return Holder.direct(new DamageType(name, 0.1F));
    }

    public static DamageSource nuclearBlast = new DamageSource(dt("nuclearBlast"));
    public static DamageSource blast = new DamageSource(dt("blast"));
    public static DamageSource mudPoisoning = new DamageSource(dt("mudPoisoning"));
    public static DamageSource acid = new DamageSource(dt("acid"));
    public static DamageSource euthanizedSelf = new DamageSource(dt("euthanizedSelf"));
    public static DamageSource euthanizedSelf2 = new DamageSource(dt("euthanizedSelf2"));
    public static DamageSource tauBlast = new DamageSource(dt("tauBlast"));
    public static DamageSource digamma = new DamageSource(dt("digamma"));
    public static DamageSource radiation = new DamageSource(dt("radiation"));
    public static DamageSource suicide = new DamageSource(dt("suicide"));
    public static DamageSource rubble = new DamageSource(dt("rubble"));
    public static DamageSource shrapnel = new DamageSource(dt("shrapnel"));
    public static DamageSource blackhole = new DamageSource(dt("blackhole"));
    public static DamageSource turbofan = new DamageSource(dt("blender"));
    public static DamageSource meteorite = new DamageSource(dt("meteorite"));
    public static DamageSource boxcar = new DamageSource(dt("boxcar"));
    public static DamageSource boat = new DamageSource(dt("boat"));
    public static DamageSource building = new DamageSource(dt("building"));
    public static DamageSource taint = new DamageSource(dt("taint"));
    public static DamageSource ams = new DamageSource(dt("ams"));
    public static DamageSource amsCore = new DamageSource(dt("amsCore"));
    public static DamageSource broadcast = new DamageSource(dt("broadcast"));
    public static DamageSource bang = new DamageSource(dt("bang"));
    public static DamageSource pc = new DamageSource(dt("pc"));
    public static DamageSource cloud = new DamageSource(dt("cloud"));
    public static DamageSource lead = new DamageSource(dt("lead"));
    public static DamageSource enervation = new DamageSource(dt("enervation"));
    public static DamageSource electricity = new DamageSource(dt("electricity"));
    public static DamageSource exhaust = new DamageSource(dt("exhaust"));
    public static DamageSource spikes = new DamageSource(dt("spikes"));
    public static DamageSource lunar = new DamageSource(dt("lunar"));
    public static DamageSource slicer = new DamageSource(dt("slicer"));
    public static DamageSource crucible = new DamageSource(dt("crucible"));
    public static DamageSource monoxide = new DamageSource(dt("monoxide"));
    public static DamageSource asbestos = new DamageSource(dt("asbestos"));
    public static DamageSource blacklung = new DamageSource(dt("blacklung"));
    public static DamageSource mku = new DamageSource(dt("mku"));
    public static DamageSource vacuum = new DamageSource(dt("vacuum"));
    public static DamageSource overdose = new DamageSource(dt("overdose"));
    public static DamageSource microwave = new DamageSource(dt("microwave"));
    public static DamageSource nitan = new DamageSource(dt("nitan"));

    public static final String s_bullet = "revolverBullet";
    public static final String s_emplacer = "chopperBullet";
    public static final String s_tau = "tau";
    public static final String s_combineball = "cmb";
    public static final String s_zomg_prefix = "subAtomic";
    public static final String s_euthanized = "euthanized";
    public static final String s_emp = "electrified";
    public static final String s_flamethrower = "flamethrower";
    public static final String s_immolator = "plasma";
    public static final String s_cryolator = "ice";
    public static final String s_laser = "laser";
    public static final String s_boil = "boil";
    public static final String s_acid = "acidPlayer";

    public ModDamageSource(ResourceLocation msgId) {
        super(Holder.direct(new DamageType(msgId == null ? "generic" : msgId.getPath(), 0.1F)));
    }

    /**
     * 1.21.1 移除 IndirectEntityDamageSource；间接伤害用 (Holder, direct, causing) 构造。
     * TODO P-datapack：注册自定义 damage_type（data/hbm/damage_type/*.json）后改回 id 对应 Holder。
     */
    private static DamageSource indirect(String name, Entity direct, Entity causing) {
        return new DamageSource(Holder.direct(new DamageType(name, 0.1F)), direct, causing);
    }

    // ===== 间接伤害工厂（原 EntityDamageSourceIndirect；实体类型 P6 恢复） =====

    public static DamageSource causeBulletDamage(Entity base, Entity ent) {
        return indirect(s_bullet, base, ent);
    }

    public static DamageSource causeBulletGibDamage(Entity base, Entity ent) {   // 原 EntityBulletBase
        return indirect("gunGib", base, ent);
    }

    public static DamageSource causeDisplacementDamage(Entity ent, Entity hit) {
        return indirect("chopperBullet", ent, hit);
    }

    public static DamageSource causeTauDamage(Entity base, Entity hit) {
        return indirect("tau", base, hit);
    }

    public static DamageSource causeCombineDamage(Entity base, Entity hit) {
        return indirect("cmb", base, hit);
    }

    public static DamageSource causeSubatomicDamage(Entity base, Entity hit) {   // 原 EntityRainbow
        return indirect(s_zomg_prefix + (base.getRandom().nextInt(5) + 1), base, hit);
    }

    public static DamageSource causeSubatomicDamage2(Entity base, Entity hit) {
        return indirect("subAtomic2", base, hit);
    }

    public static DamageSource causeSubatomicDamage3(Entity base, Entity hit) {
        return indirect("subAtomic3", base, hit);
    }

    public static DamageSource causeSubatomicDamage4(Entity base, Entity hit) {
        return indirect("subAtomic4", base, hit);
    }

    public static DamageSource causeSubatomicDamage5(Entity base, Entity hit) {
        return indirect("subAtomic5", base, hit);
    }

    public static DamageSource euthanized(Entity base, Entity hit) {
        return indirect("euthanized", base, hit);
    }

    public static DamageSource causeDischargeDamage(Entity base, Entity hit) {   // 原 EntityDischarge
        return indirect("electrified", base, hit);
    }

    public static DamageSource causeFireDamage(Entity base, Entity hit) {        // 原 EntityFire
        return indirect("flamethrower", base, hit);
    }

    public static DamageSource causePlasmaDamage(Entity base, Entity hit) {      // 原 EntityPlasmaBeam
        return indirect("plasma", base, hit);
    }

    public static DamageSource causeIceDamage(Entity base, Entity hit) {         // 原 EntityLN2
        return indirect("ice", base, hit);
    }

    public static DamageSource causeLaserDamage(Entity base, Entity hit) {       // 原 EntityLaserBeam / EntityMinerBeam 两个重载
        // 原两个重载（EntityLaserBeam / EntityMinerBeam）在 Entity 占位下签名相同，合并为一个；
        // P6 实体类型恢复后拆回两个重载
        return indirect("laser", base, hit);
    }

    public static boolean getIsTau(DamageSource source) {
        if (source.getDirectEntity() != null) {
            return source.getMsgId().equals("tau");
        }
        return false;
    }

    public static boolean getIsSubatomic(DamageSource source) {
        if (source.getDirectEntity() != null) {
            String s = source.getMsgId();
            return s.equals("subAtomic") || s.equals("subAtomic2") || s.equals("subAtomic3") || s.equals("subAtomic4") || s.equals("subAtomic5");
        }
        return false;
    }
}
