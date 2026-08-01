package me.sylvanserenity.modifiers.mixin;

import me.sylvanserenity.modifiers.modifier.ModAttributes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(CrossbowItem.class)
public abstract class CrossbowItemMixin {
    @Inject(method = "getChargeDuration", at = @At("RETURN"), cancellable = true)
    private static void modifiers$loadSpeed(ItemStack stack, LivingEntity shooter, CallbackInfoReturnable<Integer> cir) {
        AttributeInstance attribute = shooter.getAttribute(ModAttributes.DRAW_SPEED);
        if (attribute == null) return;

        cir.setReturnValue(Math.max(1, Math.round(cir.getReturnValue() / (float) attribute.getValue())));
    }
}
