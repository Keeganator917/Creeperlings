package com.keeganator917.creeperlings.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import net.minecraft.world.rule.GameRules;

public class BabyCreeperEntity extends CreeperEntity {

    public BabyCreeperEntity(EntityType<? extends CreeperEntity> entityType, World world) {
        super(entityType, world);
    }

    public static DefaultAttributeContainer.Builder createBabyCreeperAttributes() {
        return CreeperEntity.createCreeperAttributes().add(EntityAttributes.MAX_HEALTH, 10.0).add(EntityAttributes.MOVEMENT_SPEED, 0.35f);
    }

    public float getScaleFactor() {
        return 0.6f;
    }
}
