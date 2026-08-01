package me.sylvanserenity.modifiers.mixin;

import me.sylvanserenity.modifiers.modifier.CriticalHitTracker;
import me.sylvanserenity.modifiers.modifier.ModifiersConfig;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityKnockbackMixin {
    @ModifyVariable(method = "knockback", at = @At("HEAD"), ordinal = 0)
    private double modifiers$knockback(double strength) {
        LivingEntity self = (LivingEntity) (Object) this;
        if (CriticalHitTracker.consumeCritical(self.getId())) {
            return strength * (1.0 + ModifiersConfig.criticalKnockbackMultiplier());
        }
        return strength;
    }
}
