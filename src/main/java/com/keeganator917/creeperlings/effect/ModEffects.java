package com.keeganator917.creeperlings.effect;

import com.keeganator917.creeperlings.Creeperlings;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

public class ModEffects {

    public static RegistryEntry<StatusEffect> SPORED_EFFECT;

    public static void register() {
        SPORED_EFFECT = Registry.registerReference(
                Registries.STATUS_EFFECT, Identifier.of(Creeperlings.MOD_ID, "spored"), new SporeEffect()
        );
    }
}
