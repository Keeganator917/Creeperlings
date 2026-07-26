package keeganator917.creeperlings.mixin;

import keeganator917.creeperlings.effect.ModEffects;
import keeganator917.creeperlings.effect.SporeEffect;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {

    @Inject(method = "hurtServer", at = @At("TAIL"))
    private void onDamage(ServerLevel level, DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {

        if (!cir.getReturnValue()) {
            return;
        }

        LivingEntity self = (LivingEntity)(Object)this;

        if (self.hasEffect(ModEffects.SPORED_EFFECT)) {
            SporeEffect.onSporedEntityDamaged(self);
        }
    }
}
