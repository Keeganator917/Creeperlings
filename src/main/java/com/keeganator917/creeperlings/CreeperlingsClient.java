package com.keeganator917.creeperlings;

import com.keeganator917.creeperlings.entity.BabyCreeperRenderer;
import com.keeganator917.creeperlings.entity.ModEntities;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

public class CreeperlingsClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(ModEntities.CREEPERLING, BabyCreeperRenderer::new);

    }
}
