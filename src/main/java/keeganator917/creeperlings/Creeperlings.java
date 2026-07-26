package keeganator917.creeperlings;

import keeganator917.creeperlings.effect.ModEffects;
import keeganator917.creeperlings.entity.BabyCreeperEntity;
import keeganator917.creeperlings.entity.ModEntities;
import keeganator917.creeperlings.item.ModItems;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Creeperlings implements ModInitializer {
	public static final String MOD_ID = "creeperlings";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItems.registerModItems();
		ModEntities.registerModEntities();
		ModEffects.register();

		LOGGER.info("Registering Creeperlings");

		FabricDefaultAttributeRegistry.register(ModEntities.CREEPERLING, BabyCreeperEntity.createBabyCreeperAttributes());
	}
}