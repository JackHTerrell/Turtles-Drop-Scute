package com.jackbusters.turtlesdropscute;

import com.jackbusters.turtlesdropscute.glm.GeneralSerializer;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod(TurtlesDropScute.MOD_ID)
public class TurtlesDropScute {
    public static final String MOD_ID = "turtlesdropscute";
    public static final DeferredRegister<GlobalLootModifierSerializer<?>> LOOT_MODIFIERS = DeferredRegister.create(ForgeRegistries.LOOT_MODIFIER_SERIALIZERS, MOD_ID);
    public static final RegistryObject<GlobalLootModifierSerializer<?>> ADD_DROP = LOOT_MODIFIERS.register("add_drop", GeneralSerializer::new);
    public TurtlesDropScute(){
        LOOT_MODIFIERS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}
