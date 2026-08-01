package me.sylvanserenity.modifiers.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import me.sylvanserenity.modifiers.modifier.ModAttributes;
import me.sylvanserenity.modifiers.modifier.ModifiersConfig;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.projectile.AbstractArrow;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(AbstractArrow.class)
public abstract class AbstractArrowMixin {
    @ModifyArg(method = "onHitEntity", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/Mth;clamp(DDD)D"), index = 0)
    private double modifiers$projectileDamage(double damage) {
        AbstractArrow self = (AbstractArrow) (Object) this;
        if (!(self.getOwner() instanceof LivingEntity shooter)) return damage;

        AttributeInstance attribute = shooter.getAttribute(ModAttributes.PROJECTILE_DAMAGE);
        return attribute != null ? damage * attribute.getValue() : damage;
    }

    @Redirect(method = "onHitEntity", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/RandomSource;nextInt(I)I"))
    private int modifiers$criticalDamage(RandomSource random, int bound, @Local(ordinal = 0) int baseDamage) {
        return Math.round(baseDamage * ((float) ModifiersConfig.criticalDamageMultiplier() - 1.0F));
    }

    @ModifyVariable(method = "doKnockback", at = @At("STORE"), ordinal = 0)
    private double modifiers$knockback(double strength) {
        AbstractArrow self = (AbstractArrow) (Object) this;
        if (!(self.getOwner() instanceof LivingEntity shooter)) return strength;

        AttributeInstance attribute = shooter.getAttribute(ModAttributes.KNOCKBACK);
        return attribute != null ? strength * attribute.getValue() : strength;
    }
}
