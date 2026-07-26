package keeganator917.creeperlings.mixin;

import keeganator917.creeperlings.entity.BabyCreeperEntity;
import net.minecraft.world.entity.ai.goal.SwellGoal;
import net.minecraft.world.entity.monster.Creeper;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(SwellGoal.class)
public class SwellGoalMixin {

    @Shadow
    @Final
    private Creeper creeper;

    @ModifyConstant(method = "canUse", constant = @Constant(doubleValue = 9.0D))
    private double creeperlings$babyCanUseDistance(double original) {
        if (creeper instanceof BabyCreeperEntity) {
            return 2.25D;
        }

        return original;
    }

    @ModifyConstant(method = "tick", constant = @Constant(doubleValue = 49.0D))
    private double creeperlings$babyTickDistance(double original) {
        if (creeper instanceof BabyCreeperEntity) {
            return 12.25D;
        }

        return original;
    }
}