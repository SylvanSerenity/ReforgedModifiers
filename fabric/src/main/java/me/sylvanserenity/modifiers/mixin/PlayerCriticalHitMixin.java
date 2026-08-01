package me.sylvanserenity.modifiers.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import me.sylvanserenity.modifiers.modifier.CriticalHitTracker;
import me.sylvanserenity.modifiers.modifier.KnockbackTracker;
import me.sylvanserenity.modifiers.modifier.ModAttributes;
import me.sylvanserenity.modifiers.modifier.ModifierHandler;
import me.sylvanserenity.modifiers.modifier.ModifiersConfig;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Player.class)
public abstract class PlayerCriticalHitMixin {
    // Stashed unconditionally (not just on crit) so LivingEntityKnockbackMixin can apply it regardless
    // of whether this particular hit also happens to be a critical.
    @Inject(method = "attack", at = @At("HEAD"))
    private void modifiers$knockback(Entity target, CallbackInfo ci) {
        Player player = (Player) (Object) this;
        KnockbackTracker.markKnockback(target.getId(), (float) player.getAttributeValue(ModAttributes.KNOCKBACK));
    }

    @ModifyVariable(method = "attack", at = @At("STORE"), index = 9)
    private boolean modifiers$criticalHit(boolean vanillaCritical, @Local(argsOnly = true) Entity target) {
        Player player = (Player) (Object) this;
        float chance = ModifierHandler.getCriticalChance(player);
        boolean roll = chance > 0.0F && player.getRandom().nextFloat() < chance;

        boolean critical = ModifiersConfig.disableVanillaCritical() ? roll : (vanillaCritical || roll);
        // Correlates with LivingEntityKnockbackMixin's bonus, since knockback is applied later from a
        // separate method with no direct access to this local.
        if (critical) CriticalHitTracker.markCritical(target.getId());
        return critical;
    }

    @ModifyConstant(method = "attack", constant = @Constant(floatValue = 1.5F))
    private float modifiers$criticalDamageMultiplier(float original) {
        return (float) ModifiersConfig.criticalDamageMultiplier();
    }
}
