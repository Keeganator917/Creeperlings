package com.keeganator917.creeperlings.effect;

import com.keeganator917.creeperlings.Creeperlings;
import com.keeganator917.creeperlings.entity.ModEntities;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.rule.GameRules;


public class SporeEffect extends StatusEffect {

    public static final float SPAWN_CHANCE = 0.30f;

    public SporeEffect() {
        super(StatusEffectCategory.HARMFUL, 0x7EC84A);
    }


    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }

    public static void onSporedEntityDamaged(LivingEntity entity) {
        if (!(entity.getEntityWorld() instanceof ServerWorld serverWorld)) return;
        if (!serverWorld.getGameRules().getValue(GameRules.DO_MOB_SPAWNING)) return;
        if (entity.getEntityWorld().isClient()) return;

        if (entity.getRandom().nextFloat() < SPAWN_CHANCE) {
            var baby = ModEntities.CREEPERLING.create(serverWorld, SpawnReason.MOB_SUMMONED);
            if (baby == null) return;

            double offsetX = (entity.getRandom().nextDouble() - 0.5) * 1.5;
            double offsetZ = (entity.getRandom().nextDouble() - 0.5) * 1.5;
            baby.refreshPositionAndAngles(entity.getX() + offsetX, entity.getY(), entity.getZ() + offsetZ, entity.getRandom().nextFloat() * 360f, 0f);
            serverWorld.spawnEntity(baby);
        }
    }
}
