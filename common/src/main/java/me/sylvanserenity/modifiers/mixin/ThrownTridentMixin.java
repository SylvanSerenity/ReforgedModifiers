package me.sylvanserenity.modifiers.mixin;

import me.sylvanserenity.modifiers.modifier.ModAttributes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.projectile.ThrownTrident;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(ThrownTrident.class)
public abstract class ThrownTridentMixin {
    // Targets the entity.hurt() call that consumes the damage value rather than the local's own STORE.
    @ModifyArg(method = "onHitEntity", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/Entity;hurt(Lnet/minecraft/world/damagesource/DamageSource;F)Z"), index = 1)
    private float modifiers$projectileDamage(float damage) {
        ThrownTrident self = (ThrownTrident) (Object) this;
        if (!(self.getOwner() instanceof LivingEntity shooter)) return damage;

        AttributeInstance attribute = shooter.getAttribute(ModAttributes.PROJECTILE_DAMAGE);
        return attribute != null ? damage * (float) attribute.getValue() : damage;
    }
}
