package com.jackbusters.turtlesdropscute;

import com.jackbusters.turtlesdropscute.glm.EpicLootModifier;
import com.mojang.serialization.Codec;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod(TurtlesDropScute.MOD_ID)
public class TurtlesDropScute {
    public static final String MOD_ID = "turtlesdropscute";
    public static final DeferredRegister<Codec<? extends IGlobalLootModifier>> LOOT_MODIFIERS = DeferredRegister.create(ForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, MOD_ID);
    public static final RegistryObject<Codec<? extends IGlobalLootModifier>> ADD_DROP = LOOT_MODIFIERS.register("add_drop", EpicLootModifier.CODEC);
    public TurtlesDropScute(FMLJavaModLoadingContext modLoadingContext){
        LOOT_MODIFIERS.register(modLoadingContext.getModEventBus());
    }
}
