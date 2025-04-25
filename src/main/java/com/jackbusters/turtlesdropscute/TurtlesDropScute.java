package com.jackbusters.turtlesdropscute;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.util.Identifier;

public class TurtlesDropScute implements ModInitializer {
    private static final Identifier TURTLE_LOCATION = EntityType.TURTLE.getLootTableId();
    @Override
    public void onInitialize() {
        LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {
            if (source.isBuiltin() && TURTLE_LOCATION.equals(id)) {
                LootPool.Builder poolBuilder = LootPool.builder().with(ItemEntry.builder(Items.SCUTE));
                tableBuilder.pool(poolBuilder);
            }
        });
    }
}
