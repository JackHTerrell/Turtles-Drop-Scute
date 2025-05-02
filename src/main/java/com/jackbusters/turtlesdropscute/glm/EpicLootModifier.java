package com.jackbusters.turtlesdropscute.glm;

import com.google.common.base.Suppliers;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.common.loot.LootModifier;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nonnull;
import java.util.function.Supplier;

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

    public static final Supplier<Codec<EpicLootModifier>> CODEC = Suppliers.memoize(() ->
            RecordCodecBuilder.create(instance -> codecStart(instance).and(ForgeRegistries.ITEMS.getCodec()
                            .fieldOf("item").forGetter(a -> a.item)).and(Codec.INT
                            .fieldOf("amount").forGetter(d -> d.amount)).and(Codec.STRING
                            .fieldOf("loot_table").forGetter(e -> e.lootTable)).and(Codec.DOUBLE
                            .fieldOf("chance").forGetter(f -> f.chance))
                    .apply(instance, EpicLootModifier::new)));

    public static final Supplier<MapCodec<EpicLootModifier>> MAP_CODEC = Suppliers.memoize(() -> MapCodec.assumeMapUnsafe(CODEC.get())); // Convert Codec to MapCodec so that it is usable.

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
    protected ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
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

    @Override
    public MapCodec<? extends IGlobalLootModifier> codec() {
        return MAP_CODEC.get();
    }
}
