package com.hbm.datagen;

import com.hbm.entity.ModEntities;
import com.hbm.items.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemKilledByPlayerCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.function.BiConsumer;

public class HBMEntityLootProvider implements LootTableSubProvider {

    public HBMEntityLootProvider(HolderLookup.Provider registries) {
    }

    @Override
    public void generate(BiConsumer<ResourceKey<LootTable>, LootTable.Builder> consumer) {

        addNuclearCreeper(consumer);
        addGoldCreeper(consumer);
        addVolatileCreeper(consumer);
        addTaintCrab(consumer);
        addTeslaCrab(consumer);
        addDuck(consumer);
        addPigeon(consumer);
        addGlowingOne(consumer);

        addEmpty(consumer, ModEntities.C_RE_EP_ER_PH_OS_GE_NE.get());
        addEmpty(consumer, ModEntities.C_YB_ER_CR_AB.get());
        addEmpty(consumer, ModEntities.D_UM_MY.get());
        addEmpty(consumer, ModEntities.F_BI.get());
        addEmpty(consumer, ModEntities.F_BI_DR_ON_E.get());
        addEmpty(consumer, ModEntities.H_UN_TE_RC_HO_PP_ER.get());
        addEmpty(consumer, ModEntities.M_AS_KM_AN.get());
        addEmpty(consumer, ModEntities.P_AR_AS_IT_EM_AG_GO_T.get());
        addEmpty(consumer, ModEntities.Q_UA_CK_OS.get());
        addEmpty(consumer, ModEntities.R_AD_BE_AS_T.get());
        addEmpty(consumer, ModEntities.U_FO.get());
        addEmpty(consumer, ModEntities.U_ND_EA_DS_OL_DI_ER.get());
        addEmpty(consumer, ModEntities.B_OT_PR_IM_EB_OD_Y.get());
        addEmpty(consumer, ModEntities.B_OT_PR_IM_EH_EA_D.get());
        addEmpty(consumer, ModEntities.G_LY_PH_ID.get());
        addEmpty(consumer, ModEntities.G_LY_PH_ID_BE_HE_MO_TH.get());
        addEmpty(consumer, ModEntities.G_LY_PH_ID_BL_AS_TE_R.get());
        addEmpty(consumer, ModEntities.G_LY_PH_ID_BO_MB_AR_DI_ER.get());
        addEmpty(consumer, ModEntities.G_LY_PH_ID_BR_AW_LE_R.get());
        addEmpty(consumer, ModEntities.G_LY_PH_ID_BR_EN_DA.get());
        addEmpty(consumer, ModEntities.G_LY_PH_ID_DI_GG_ER.get());
        addEmpty(consumer, ModEntities.G_LY_PH_ID_NU_CL_EA_R.get());
        addEmpty(consumer, ModEntities.G_LY_PH_ID_SC_OU_T.get());
    }

    private static void addNuclearCreeper(BiConsumer<ResourceKey<LootTable>, LootTable.Builder> consumer) {
        LootTable.Builder table = LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(Items.TNT))
                        .when(LootItemKilledByPlayerCondition.killedByPlayer()))
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(ModItems.COIN_CREEPER.get()))
                        .when(LootItemRandomChanceCondition.randomChance(0.33F))
                        .when(LootItemKilledByPlayerCondition.killedByPlayer()));
        consumer.accept(lootKey(ModEntities.C_RE_EP_ER_NU_CL_EA_R.get()), table);
    }

    private static void addGoldCreeper(BiConsumer<ResourceKey<LootTable>, LootTable.Builder> consumer) {
        LootTable.Builder table = LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(Items.GUNPOWDER)))
                .withPool(LootPool.lootPool().setRolls(UniformGenerator.between(5, 10))
                        .add(LootItem.lootTableItem(ModItems.CRYSTAL_GOLD.get()))
                        .when(LootItemKilledByPlayerCondition.killedByPlayer()));
        consumer.accept(lootKey(ModEntities.C_RE_EP_ER_GO_LD.get()), table);
    }

    private static void addVolatileCreeper(BiConsumer<ResourceKey<LootTable>, LootTable.Builder> consumer) {
        LootTable.Builder table = LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(UniformGenerator.between(2, 4))
                        .add(LootItem.lootTableItem(ModItems.SULFUR.get())))
                .withPool(LootPool.lootPool().setRolls(UniformGenerator.between(1, 2))
                        .add(LootItem.lootTableItem(ModItems.STICK_TNT.get())));
        consumer.accept(lootKey(ModEntities.C_RE_EP_ER_VO_LA_TI_LE.get()), table);
    }

    private static void addTaintCrab(BiConsumer<ResourceKey<LootTable>, LootTable.Builder> consumer) {
        LootTable.Builder table = LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(ModItems.COIL_COPPER.get())))
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(ModItems.COIL_MAGNETIZED_TUNGSTEN.get()))
                        .when(LootItemRandomChanceCondition.randomChance(0.005F)));
        consumer.accept(lootKey(ModEntities.T_AI_NT_CR_AB.get()), table);
    }

    private static void addTeslaCrab(BiConsumer<ResourceKey<LootTable>, LootTable.Builder> consumer) {
        LootTable.Builder table = LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(ModItems.COIL_COPPER.get()))
                        .when(LootItemRandomChanceCondition.randomChance(0.005F)));
        consumer.accept(lootKey(ModEntities.T_ES_LA_CR_AB.get()), table);
    }

    private static void addDuck(BiConsumer<ResourceKey<LootTable>, LootTable.Builder> consumer) {
        LootTable.Builder table = LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(UniformGenerator.between(0, 2))
                        .add(LootItem.lootTableItem(Items.FEATHER)))
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(Items.CHICKEN)));
        consumer.accept(lootKey(ModEntities.D_UC_K.get()), table);
    }

    private static void addPigeon(BiConsumer<ResourceKey<LootTable>, LootTable.Builder> consumer) {
        LootTable.Builder table = LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(UniformGenerator.between(0, 2))
                        .add(LootItem.lootTableItem(Items.FEATHER)))
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(Items.CHICKEN)));
        consumer.accept(lootKey(ModEntities.P_IG_EO_N.get()), table);
    }

    private static void addGlowingOne(BiConsumer<ResourceKey<LootTable>, LootTable.Builder> consumer) {
        LootTable.Builder table = LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(Items.ROTTEN_FLESH)))
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(ModItems.CAP_RAD.get())));
        consumer.accept(lootKey(ModEntities.G_LO_WI_NG_ON_E.get()), table);
    }

    private static ResourceKey<LootTable> lootKey(EntityType<?> entity) {
        ResourceLocation entityId = EntityType.getKey(entity);
        ResourceLocation lootTableId = ResourceLocation.fromNamespaceAndPath(entityId.getNamespace(), "entities/" + entityId.getPath());
        return ResourceKey.create(Registries.LOOT_TABLE, lootTableId);
    }

    private static void addEmpty(BiConsumer<ResourceKey<LootTable>, LootTable.Builder> consumer, EntityType<?> entity) {
        consumer.accept(lootKey(entity), LootTable.lootTable());
    }
}
