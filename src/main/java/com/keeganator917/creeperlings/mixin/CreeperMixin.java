package com.keeganator917.creeperlings.mixin;

import com.keeganator917.creeperlings.effect.ModEffects;
import com.keeganator917.creeperlings.entity.BabyCreeperEntity;
import com.keeganator917.creeperlings.entity.ModEntities;
import net.minecraft.entity.AreaEffectCloudEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.rule.GameRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CreeperEntity.class)
public class CreeperMixin {

    @Unique
    private static final int BABY_SPAWN_COUNT = 5;

    @Inject(method = "explode", at = @At("TAIL"))
    private void creeperlings$afterExplode(CallbackInfo ci) {
        CreeperEntity creeper = (CreeperEntity)(Object)this;

        if (!(creeper instanceof BabyCreeperEntity)) {
            spawnSporeEffect();
            spawnBabyCreepers();
        }
    }

    @Redirect(method = "explode", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/world/ServerWorld;createExplosion(Lnet/minecraft/entity/Entity;DDDFLnet/minecraft/world/World$ExplosionSourceType;)V"))
    private void creeperlings$replaceExplosion(ServerWorld world, Entity entity, double x, double y, double z, float power, World.ExplosionSourceType sourceType) {
        CreeperEntity creeper = (CreeperEntity)(Object)this;

        if (creeper instanceof BabyCreeperEntity) {
            world.createExplosion(entity, x, y, z, power / 3, World.ExplosionSourceType.NONE);
        } else {
            world.createExplosion(entity, x, y, z, power, World.ExplosionSourceType.NONE);
        }

    }

    @Redirect(method = "explode", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/mob/CreeperEntity;spawnEffectsCloud()V"))
    private void creeperlings$spawnEffectsCloud(CreeperEntity creeper) {
        if (!(creeper instanceof BabyCreeperEntity)) {
            ((CreeperEntityAccessor) creeper).creeperlings$invokeSpawnEffectsCloud();
        }
    }

    @Unique
    private void spawnSporeEffect() {
        CreeperEntity creep = (CreeperEntity) (Object) this;
        World world = creep.getEntityWorld();

        world.getEntitiesByClass(LivingEntity.class, creep.getBoundingBox().expand(5.0f), e ->
                e != creep).forEach(entity -> {
                    entity.addStatusEffect(new StatusEffectInstance(ModEffects.SPORED_EFFECT, 600, 0));
                });

        AreaEffectCloudEntity areaEffectCloud = new AreaEffectCloudEntity(creep.getEntityWorld(), creep.getX(), creep.getY(), creep.getZ());

        areaEffectCloud.addEffect(new StatusEffectInstance(ModEffects.SPORED_EFFECT, 600, 0));
        areaEffectCloud.setRadius(2.5F);
        areaEffectCloud.setRadiusOnUse(-0.5F);
        areaEffectCloud.setDuration(600);
        areaEffectCloud.setWaitTime(10);
        areaEffectCloud.setRadiusGrowth(-areaEffectCloud.getRadius() / areaEffectCloud.getDuration());
        world.spawnEntity(areaEffectCloud);
    }

    @Unique
    private void spawnBabyCreepers() {
        CreeperEntity creep = (CreeperEntity) (Object) this;
        if (!(creep.getEntityWorld() instanceof ServerWorld serverWorld)) return;
        if (!serverWorld.getGameRules().getValue(GameRules.DO_MOB_SPAWNING)) return;

        int spawnCount = BABY_SPAWN_COUNT - Random.create().nextBetween(0, 2);

        for (int i = 0; i < spawnCount; i++) {
            BabyCreeperEntity baby = ModEntities.CREEPERLING.create(serverWorld, SpawnReason.MOB_SUMMONED);
            if (baby == null) continue;

            double offsetX = (creep.getRandom().nextDouble() - 0.5) * 2.0;
            double offsetZ = (creep.getRandom().nextDouble() - 0.5) * 2.0;
            baby.refreshPositionAndAngles(creep.getX() + offsetX, creep.getY(), creep.getZ() + offsetZ, creep.getRandom().nextFloat() * 360f, 0f);
            serverWorld.spawnEntity(baby);
        }
    }

}
