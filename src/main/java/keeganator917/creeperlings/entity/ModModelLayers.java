package keeganator917.creeperlings.entity;

import keeganator917.creeperlings.Creeperlings;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.Identifier;

public class ModModelLayers {

    public static final ModelLayerLocation CREEPERLING =
            new ModelLayerLocation(Identifier.fromNamespaceAndPath(Creeperlings.MOD_ID, "creeperling"), "main");
}
