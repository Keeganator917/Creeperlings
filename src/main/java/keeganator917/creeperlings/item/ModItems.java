package keeganator917.creeperlings.item;


import keeganator917.creeperlings.Creeperlings;
import keeganator917.creeperlings.entity.ModEntities;
import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;

import java.util.function.Function;

public class ModItems {
    public static final Item CREEPERLING_SPAWN_EGG = registerItem("creeperling_spawn_egg", properties ->
            new SpawnEggItem(properties.spawnEgg(ModEntities.CREEPERLING)));

    private static Item registerItem(String name, Function<Item.Properties, Item> function) {
        return Registry.register(BuiltInRegistries.ITEM, Identifier.fromNamespaceAndPath(Creeperlings.MOD_ID, name),
                function.apply(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(Creeperlings.MOD_ID, name)))));
    }

    public static void registerModItems() {
        Creeperlings.LOGGER.info("Registering Mod Items for " + Creeperlings.MOD_ID);

        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.SPAWN_EGGS).register(output -> {
            output.accept(CREEPERLING_SPAWN_EGG);
        });
    }
}
