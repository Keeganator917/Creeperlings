package keeganator917.creeperlings.mixin;

import keeganator917.creeperlings.effect.ModEffects;
import keeganator917.creeperlings.entity.BabyCreeperEntity;
import keeganator917.creeperlings.entity.ModEntities;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gamerules.GameRules;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Creeper.class)
public class CreeperMixin {

    @Unique
    private static final int BABY_SPAWN_COUNT = 5;

    @Inject(method = "explodeCreeper", at = @At("TAIL"))
    private void creeperlings$afterExplode(CallbackInfo ci) {
        Creeper creeper = (Creeper)(Object)this;

        if (!(creeper instanceof BabyCreeperEntity)) {
            spawnSporeEffect();
            spawnBabyCreepers();
        }
    }

    @Redirect(method = "explodeCreeper", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/level/ServerLevel;explode(Lnet/minecraft/world/entity/Entity;DDDFLnet/minecraft/world/level/Level$ExplosionInteraction;)V"))
    private void creeperlings$replaceExplosion(ServerLevel level, Entity entity, double x, double y, double z, float power, Level.ExplosionInteraction interaction) {
        Creeper creeper = (Creeper)(Object)this;

        if (creeper instanceof BabyCreeperEntity) {
            level.explode(entity, x, y, z, power / 3f, Level.ExplosionInteraction.NONE);
        } else {
            level.explode(entity, x, y, z, power, Level.ExplosionInteraction.NONE);
        }
    }

    @Redirect(method = "explodeCreeper", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/monster/Creeper;spawnLingeringCloud()V"))
    private void creeperlings$spawnEffectsCloud(Creeper creeper) {
        if (!(creeper instanceof BabyCreeperEntity)) {
            ((CreeperEntityAccessor)creeper).creeperlings$invokeSpawnLingeringCloud();
        }
    }

    private void spawnSporeEffect() {
        Creeper creep = (Creeper)(Object)this;
        Level level = creep.level();

        level.getEntitiesOfClass(LivingEntity.class, creep.getBoundingBox().inflate(5.0D), entity ->
                entity != creep).forEach(entity ->
                entity.addEffect(
                        new MobEffectInstance(ModEffects.SPORED_EFFECT, 600, 0)
                )
        );

        AreaEffectCloud cloud = new AreaEffectCloud(level, creep.getX(), creep.getY(), creep.getZ());

        cloud.addEffect(new MobEffectInstance(ModEffects.SPORED_EFFECT, 600, 0));

        cloud.setRadius(2.5F);
        cloud.setRadiusOnUse(-0.5F);
        cloud.setDuration(600);
        cloud.setWaitTime(10);
        cloud.setRadiusPerTick(-cloud.getRadius() / cloud.getDuration());

        level.addFreshEntity(cloud);
    }

    private void spawnBabyCreepers() {
        Creeper creep = (Creeper)(Object)this;

        if (!(creep.level() instanceof ServerLevel level)) {
            return;
        }

        if (!level.getGameRules().get(GameRules.SPAWN_MOBS)) {
            return;
        }

        int spawnCount = BABY_SPAWN_COUNT - RandomSource.create().nextIntBetweenInclusive(0, 2);

        for (int i = 0; i < spawnCount; i++) {

            BabyCreeperEntity baby = ModEntities.CREEPERLING.create(level, EntitySpawnReason.MOB_SUMMONED);

            if (baby == null) {
                continue;
            }

            double offsetX = (creep.getRandom().nextDouble() - 0.5D) * 2.0D;
            double offsetZ = (creep.getRandom().nextDouble() - 0.5D) * 2.0D;

            baby.snapTo(
                    new Vec3(creep.getX() + offsetX, creep.getY(), creep.getZ() + offsetZ),
                    creep.getRandom().nextFloat() * 360f, 0f
            );

            level.addFreshEntity(baby);
        }
    }

}
