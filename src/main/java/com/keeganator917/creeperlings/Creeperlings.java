package com.keeganator917.creeperlings;

import com.keeganator917.creeperlings.effect.ModEffects;
import com.keeganator917.creeperlings.effect.SporeEffect;
import com.keeganator917.creeperlings.entity.BabyCreeperEntity;
import com.keeganator917.creeperlings.entity.ModEntities;
import com.keeganator917.creeperlings.item.ModItems;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.item.Item;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Creeperlings implements ModInitializer {
	public static final String MOD_ID = "creeperlings";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItems.register();
		ModEntities.registerModEntities();
		ModEffects.register();

		LOGGER.info("Registering Creeperlings!");

		FabricDefaultAttributeRegistry.register(ModEntities.CREEPERLING, BabyCreeperEntity.createBabyCreeperAttributes());
	}
}