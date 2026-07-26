package com.keeganator917.creeperlings.mixin;

import net.minecraft.entity.mob.CreeperEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(CreeperEntity.class)
public interface CreeperEntityAccessor {

    @Invoker("spawnEffectsCloud")
    void creeperlings$invokeSpawnEffectsCloud();
}