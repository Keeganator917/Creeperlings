package keeganator917.creeperlings;

import keeganator917.creeperlings.entity.CreeperlingModel;
import keeganator917.creeperlings.entity.CreeperlingRenderer;
import keeganator917.creeperlings.entity.ModEntities;
import keeganator917.creeperlings.entity.ModModelLayers;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.ModelLayerRegistry;

public class CreeperlingsClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {

        ModelLayerRegistry.registerModelLayer(ModModelLayers.CREEPERLING, CreeperlingModel::createBodyLayer);

        EntityRendererRegistry.register(ModEntities.CREEPERLING, CreeperlingRenderer::new);

    }
}
