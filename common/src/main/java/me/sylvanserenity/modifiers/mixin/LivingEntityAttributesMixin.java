package me.sylvanserenity.modifiers.mixin;

import me.sylvanserenity.modifiers.modifier.ModAttributes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityAttributesMixin {
    @Inject(method = "createLivingAttributes", at = @At("RETURN"))
    private static void modifiers$addAttributes(CallbackInfoReturnable<AttributeSupplier.Builder> cir) {
        AttributeSupplier.Builder builder = cir.getReturnValue();
        if (ModAttributes.CRITICAL_CHANCE != null) builder.add(ModAttributes.CRITICAL_CHANCE);
        if (ModAttributes.PROJECTILE_VELOCITY != null) builder.add(ModAttributes.PROJECTILE_VELOCITY);
        if (ModAttributes.DRAW_SPEED != null) builder.add(ModAttributes.DRAW_SPEED);
        if (ModAttributes.PROJECTILE_DAMAGE != null) builder.add(ModAttributes.PROJECTILE_DAMAGE);
        if (ModAttributes.KNOCKBACK != null) builder.add(ModAttributes.KNOCKBACK);
    }
}
