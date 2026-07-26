package keeganator917.creeperlings.effect;

import keeganator917.creeperlings.Creeperlings;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.effect.MobEffect;

public class ModEffects {

    public static Holder<MobEffect> SPORED_EFFECT;

    public static void register() {
        MobEffect effect = new SporeEffect();
        Registry.register(BuiltInRegistries.MOB_EFFECT, Identifier.fromNamespaceAndPath(Creeperlings.MOD_ID, "spored"), effect);
        SPORED_EFFECT = BuiltInRegistries.MOB_EFFECT.wrapAsHolder(effect);
    }
}
