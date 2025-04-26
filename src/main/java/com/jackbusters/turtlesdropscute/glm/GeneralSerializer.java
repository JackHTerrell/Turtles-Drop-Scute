package com.jackbusters.turtlesdropscute.glm;

import com.google.gson.JsonObject;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.registries.ForgeRegistries;

public class GeneralSerializer extends GlobalLootModifierSerializer<EpicLootModifier> {
    @Override
    public EpicLootModifier read(ResourceLocation location, JsonObject jsonObject, LootItemCondition[] iLootConditions) {
        Item item = GsonHelper.getAsItem(jsonObject, "item");
        int amount = GsonHelper.getAsInt(jsonObject, "amount");
        String lootTable = GsonHelper.getAsString(jsonObject, "loot_table");
        double chance = GsonHelper.getAsInt(jsonObject, "chance");
        // Deserialize other properties
        return new EpicLootModifier(iLootConditions, item, amount, lootTable, chance);
    }

    @Override
    public JsonObject write(EpicLootModifier epicLootModifier) {
        // Create json object with conditions in modifier
        JsonObject res = this.makeConditions(epicLootModifier.conditionsIn);
        res.addProperty("amount", epicLootModifier.amount);
        res.addProperty("loot_table", epicLootModifier.lootTable);
        res.addProperty("chance", epicLootModifier.chance);
        res.addProperty("item", ForgeRegistries.ITEMS.getKey(epicLootModifier.item).toString());
        // Add other properties in modifier
        return res;
    }
}
