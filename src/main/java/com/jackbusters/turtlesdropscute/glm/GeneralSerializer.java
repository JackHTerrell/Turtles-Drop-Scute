package com.jackbusters.turtlesdropscute.glm;

import com.google.gson.JsonObject;
import net.minecraft.item.Item;
import net.minecraft.loot.conditions.ILootCondition;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.registries.ForgeRegistries;

public class GeneralSerializer extends GlobalLootModifierSerializer<EpicLootModifier> {
    @Override
    public EpicLootModifier read(ResourceLocation resourceLocation, JsonObject jsonObject, ILootCondition[] iLootConditions) {
        Item item = ForgeRegistries.ITEMS.getValue(new ResourceLocation((JSONUtils.getAsString(jsonObject, "item"))));
        int amount = JSONUtils.getAsInt(jsonObject, "amount");
        String lootTable = JSONUtils.getAsString(jsonObject, "loot_table");
        double chance = JSONUtils.getAsInt(jsonObject, "chance");
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
