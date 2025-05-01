package com.jackbusters.turtlesdropscute;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.registry.RegistryKey;

public class TurtlesDropScute implements ModInitializer {
    private static final RegistryKey<LootTable> TURTLE_LOCATION = EntityType.TURTLE.getLootTableId();
    @Override
    public void onInitialize() {
        LootTableEvents.MODIFY.register((key, table, source) -> {
            if (source.isBuiltin() && TURTLE_LOCATION.equals(key)) {
                LootPool.Builder poolBuilder = LootPool.builder().with(ItemEntry.builder(Items.TURTLE_SCUTE));
                table.pool(poolBuilder);
            }
        });
    }
}
