package com.hbm.datagen;

import com.hbm.Tags;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.List;
import java.util.Set;

@EventBusSubscriber(modid = Tags.MODID, bus = EventBusSubscriber.Bus.MOD)
public class HBMDataGenerators {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        var packOutput = generator.getPackOutput();
        var registries = event.getLookupProvider();

        generator.addProvider(event.includeServer(), new HBMRecipeProvider(packOutput, registries));

        generator.addProvider(event.includeServer(),
                new LootTableProvider(packOutput, Set.of(),
                        List.of(new LootTableProvider.SubProviderEntry(
                                HBMEntityLootProvider::new, LootContextParamSets.ENTITY)),
                        registries));
    }
}
