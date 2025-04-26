package com.jackbusters.turtlesdropscute.glm;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.LootModifier;

import javax.annotation.Nonnull;
import java.util.List;

/**
 * <h1>Global Loot Modifier (GLM)</h1>
 * <p>A loot modifier system built to easily modify any loot table on a whim.</p>
 * <p>Specific loot modifications are data-driven. This is simply the logic behind how the fields in each json are applied.</p>
 * <p>Different Loot Table modifications are created and can be previewed in data/turtlesdropscute/loot_modifiers.
 * All must then be registered with Forge in data/forge/loot_modifiers/global_loot_modifers.json.</p>
 * <p>Loot modifications should <strong>never</strong> be the result of replacing a loot table. Doing so would break other mods' loot.</p>
 */
public class EpicLootModifier extends LootModifier {

    public final Item item; // The item to generate
    public final int amount; // the number of items
    public final LootItemCondition[] conditionsIn;
    public final String lootTable; // the loot table to generate the loot in (i.e. "minecraft:entities/wither").
    public final double chance; // The chance of the loot generating 0 being no chance, 1 being guaranteed.

    protected EpicLootModifier(LootItemCondition[] conditionsIn, Item item, int amount, String lootTable, double chance) {
        super(conditionsIn);
        this.conditionsIn = conditionsIn;
        this.item = item;
        this.amount = amount;
        this.lootTable = lootTable;
        this.chance = chance;
    }

    @Nonnull
    @Override
    protected List<ItemStack> doApply(List<ItemStack> generatedLoot, LootContext context) {
        if (context.getQueriedLootTableId().equals(ResourceLocation.tryParse(lootTable))) {
            ItemStack itemStack = new ItemStack(() -> item, amount);

            if (chance == 1)
                generatedLoot.add(itemStack);
            else {
                double random = Math.random();
                if (random < chance)
                    generatedLoot.add(itemStack);
            }
        }
        return generatedLoot;
    }
}
