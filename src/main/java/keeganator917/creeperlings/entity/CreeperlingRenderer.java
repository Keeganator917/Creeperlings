package keeganator917.creeperlings.entity;

import keeganator917.creeperlings.Creeperlings;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.CreeperRenderState;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Mth;

public class CreeperlingRenderer extends MobRenderer<BabyCreeperEntity, CreeperRenderState, CreeperlingModel> {

    private static final Identifier TEXTURE = Identifier.fromNamespaceAndPath(Creeperlings.MOD_ID, "textures/entity/creeperling.png");

    public CreeperlingRenderer(EntityRendererProvider.Context context) {
        super(context, new CreeperlingModel(context.bakeLayer(CreeperlingModel.LAYER_LOCATION)), 0.25F);
    }

    @Override
    public CreeperRenderState createRenderState() {
        return new CreeperRenderState();
    }

    @Override
    public Identifier getTextureLocation(CreeperRenderState state) {
        return TEXTURE;
    }

    @Override
    protected float getWhiteOverlayProgress(final CreeperRenderState state) {
        float step = state.swelling;
        return (int)(step * 10.0F) % 2 == 0 ? 0.0F : Mth.clamp(step, 0.5F, 1.0F);
    }

    @Override
    public void extractRenderState(final BabyCreeperEntity entity, final CreeperRenderState state, final float partialTicks) {
        super.extractRenderState(entity, state, partialTicks);
        state.swelling = entity.getSwelling(partialTicks);
        state.isPowered = entity.isPowered();
    }
}