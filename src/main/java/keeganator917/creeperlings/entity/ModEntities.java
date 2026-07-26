package keeganator917.creeperlings.entity;


import keeganator917.creeperlings.Creeperlings;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

public class ModEntities {

    public static final ResourceKey<EntityType<?>> CREEPERLING_KEY =
            ResourceKey.create(BuiltInRegistries.ENTITY_TYPE.key(), Identifier.fromNamespaceAndPath(Creeperlings.MOD_ID, "creeperling"));

    public static final EntityType<BabyCreeperEntity> CREEPERLING =
            Registry.register(
                    BuiltInRegistries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(Creeperlings.MOD_ID, "creeperling"),
                    EntityType.Builder.of(BabyCreeperEntity::new, MobCategory.MONSTER).sized(0.4f, 1.19f).build(CREEPERLING_KEY)
            );

    public static void registerModEntities() {
        Creeperlings.LOGGER.info("Registering Mod Entities");
    }
}
