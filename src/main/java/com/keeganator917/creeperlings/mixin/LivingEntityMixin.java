package com.keeganator917.creeperlings.mixin;

import com.keeganator917.creeperlings.Creeperlings;
import com.keeganator917.creeperlings.effect.ModEffects;
import com.keeganator917.creeperlings.effect.SporeEffect;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {

    @Inject(method = "damage", at = @At("TAIL"))
    private void onDamage(ServerWorld world, DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        if (!cir.getReturnValue()) return;

        LivingEntity self = (LivingEntity) (Object) this;
        if (self.getEntityWorld().isClient()) return;

        if (self.hasStatusEffect(ModEffects.SPORED_EFFECT)) {
            SporeEffect.onSporedEntityDamaged(self);
        }
    }
}
