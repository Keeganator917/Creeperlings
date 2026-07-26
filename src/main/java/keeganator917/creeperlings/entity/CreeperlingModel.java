package keeganator917.creeperlings.entity;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.model.monster.creeper.CreeperModel;
import net.minecraft.client.renderer.entity.state.CreeperRenderState;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Mth;

public class CreeperlingModel extends EntityModel<CreeperRenderState> {

    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(Identifier.fromNamespaceAndPath("creeperlings", "creeperling"), "main");
    private final ModelPart creeperling;
    private final ModelPart front_legs;
    private final ModelPart right_leg;
    private final ModelPart left_leg;
    private final ModelPart back_legs;
    private final ModelPart right_leg2;
    private final ModelPart left_leg2;
    private final ModelPart body;
    private final ModelPart head;

    public CreeperlingModel(ModelPart root) {
        super(root);
        this.creeperling = root.getChild("creeperling");
        this.front_legs = this.creeperling.getChild("front_legs");
        this.right_leg = this.front_legs.getChild("right_leg");
        this.left_leg = this.front_legs.getChild("left_leg");
        this.back_legs = this.creeperling.getChild("back_legs");
        this.right_leg2 = this.back_legs.getChild("right_leg2");
        this.left_leg2 = this.back_legs.getChild("left_leg2");
        this.body = this.creeperling.getChild("body");
        this.head = this.creeperling.getChild("head");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition creeperling = partdefinition.addOrReplaceChild("creeperling", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition front_legs = creeperling.addOrReplaceChild("front_legs", CubeListBuilder.create(), PartPose.offset(0.0F, -3.0F, -1.5F));

        PartDefinition right_leg = front_legs.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(18, 12).addBox(-1.5F, 0.0F, -2.0F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.5F, 0.0F, 0.0F));

        PartDefinition left_leg = front_legs.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(18, 17).addBox(-1.5F, 0.0F, -2.0F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(1.5F, 0.0F, 0.0F));

        PartDefinition back_legs = creeperling.addOrReplaceChild("back_legs", CubeListBuilder.create(), PartPose.offset(0.0F, -3.0F, 1.5F));

        PartDefinition right_leg2 = back_legs.addOrReplaceChild("right_leg2", CubeListBuilder.create().texOffs(18, 22).addBox(-1.5F, 0.0F, 0.0F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.5F, 0.0F, 0.0F));

        PartDefinition left_leg2 = back_legs.addOrReplaceChild("left_leg2", CubeListBuilder.create().texOffs(24, 0).addBox(-1.5F, 0.0F, 0.0F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(1.5F, 0.0F, 0.0F));

        PartDefinition body = creeperling.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 12).addBox(-3.0F, -5.0F, -1.5F, 6.0F, 10.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -8.0F, 0.0F));

        PartDefinition head = creeperling.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -6.0F, -3.0F, 6.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -13.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void setupAnim(CreeperRenderState state) {
        super.setupAnim(state);
        this.head.yRot = state.yRot * ((float)Math.PI / 180F);
        this.head.xRot = state.xRot * ((float)Math.PI / 180F);
        float animationSpeed = state.walkAnimationSpeed;
        float animationPos = state.walkAnimationPos;
        this.right_leg.xRot = Mth.cos(animationPos * 0.6662F) * 1.4F * animationSpeed;
        this.left_leg2.xRot = Mth.cos(animationPos * 0.6662F) * 1.4F * animationSpeed;
        this.left_leg.xRot = Mth.cos(animationPos * 0.6662F + (float)Math.PI) * 1.4F * animationSpeed;
        this.right_leg2.xRot = Mth.cos(animationPos * 0.6662F + (float)Math.PI) * 1.4F * animationSpeed;
    }
}
