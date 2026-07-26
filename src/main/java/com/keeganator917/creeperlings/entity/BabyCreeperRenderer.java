package com.keeganator917.creeperlings.entity;

import net.minecraft.client.render.entity.CreeperEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.state.CreeperEntityRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class BabyCreeperRenderer extends CreeperEntityRenderer {

    private static final Identifier CREEPER_TEXTURE =
            Identifier.ofVanilla("textures/entity/creeper/creeper.png");

    public BabyCreeperRenderer(EntityRendererFactory.Context context) {
        super(context);
    }

    @Override
    public Identifier getTexture(CreeperEntityRenderState state) {
        return CREEPER_TEXTURE;
    }


    @Override
    protected void scale(CreeperEntityRenderState state, MatrixStack matrices) {
        float s = 0.6f;
        matrices.scale(s, s, s);
        super.scale(state, matrices);
    }
}
