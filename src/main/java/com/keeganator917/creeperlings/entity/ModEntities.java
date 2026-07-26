package com.keeganator917.creeperlings.entity;

import com.keeganator917.creeperlings.Creeperlings;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;

public class ModEntities {

    public static final RegistryKey<EntityType<?>> CREEPERLING_KEY =
            RegistryKey.of(Registries.ENTITY_TYPE.getKey(), Identifier.of(Creeperlings.MOD_ID, "creeperling"));


    public static final EntityType<BabyCreeperEntity> CREEPERLING =
            Registry.register(Registries.ENTITY_TYPE, CREEPERLING_KEY,
                    EntityType.Builder.<BabyCreeperEntity>create(BabyCreeperEntity::new, SpawnGroup.MONSTER)
                            .dimensions(0.36f, 0.86f).build(CREEPERLING_KEY)
            );


    public static void registerModEntities() {
        Creeperlings.LOGGER.info("Registering Mod Entities for " + Creeperlings.MOD_ID);
    }
}
