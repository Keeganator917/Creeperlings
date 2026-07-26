package com.keeganator917.creeperlings.item;

import com.keeganator917.creeperlings.Creeperlings;
import com.keeganator917.creeperlings.entity.ModEntities;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

import java.util.function.Function;

public class ModItems {
    public static final Item CREEPERLING_SPAWN_EGG = registerItem("creeperling_spawn_egg", settings ->
            new SpawnEggItem(settings.spawnEgg(ModEntities.CREEPERLING)));

    private static Item registerItem(String name, Function<Item.Settings, Item> function) {
        return Registry.register(Registries.ITEM, Identifier.of(Creeperlings.MOD_ID, name),
                function.apply(new Item.Settings().registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(Creeperlings.MOD_ID, name)))));
    }

    public static void register() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.SPAWN_EGGS).register(entries -> {
            entries.add(ModItems.CREEPERLING_SPAWN_EGG);
        });
    }
}
