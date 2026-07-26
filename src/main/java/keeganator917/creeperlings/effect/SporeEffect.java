package keeganator917.creeperlings.effect;


import keeganator917.creeperlings.entity.BabyCreeperEntity;
import keeganator917.creeperlings.entity.ModEntities;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.gamerules.GameRules;
import net.minecraft.world.phys.Vec3;

public class SporeEffect extends MobEffect {

    public static final float SPAWN_CHANCE = 0.30f;

    public SporeEffect() {
        super(MobEffectCategory.HARMFUL, 0x7EC84A);
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return true;
    }

    public static void onSporedEntityDamaged(LivingEntity entity) {
        if (!(entity.level() instanceof ServerLevel serverLevel)) return;

        if (!serverLevel.getGameRules().get(GameRules.SPAWN_MOBS)) return;

        if (entity.getRandom().nextFloat() < SPAWN_CHANCE) {
            BabyCreeperEntity baby = ModEntities.CREEPERLING.create(serverLevel, EntitySpawnReason.MOB_SUMMONED);

            if (baby == null) return;

            double offsetX = (entity.getRandom().nextDouble() - 0.5) * 1.5;
            double offsetZ = (entity.getRandom().nextDouble() - 0.5) * 1.5;

            baby.moveOrInterpolateTo(
                    new Vec3(entity.getX() + offsetX, entity.getY(), entity.getZ() + offsetZ),
                    entity.getRandom().nextFloat() * 360f, 0f
            );

            serverLevel.addFreshEntity(baby);
        }
    }
}
