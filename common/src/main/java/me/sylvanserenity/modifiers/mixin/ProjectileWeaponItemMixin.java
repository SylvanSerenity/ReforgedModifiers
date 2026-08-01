package me.sylvanserenity.modifiers.mixin;

import me.sylvanserenity.modifiers.modifier.ModAttributes;
import me.sylvanserenity.modifiers.modifier.ModifierHandler;
import me.sylvanserenity.modifiers.modifier.ModifiersConfig;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.item.ProjectileWeaponItem;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(ProjectileWeaponItem.class)
public abstract class ProjectileWeaponItemMixin {
    // Not player-gated - any mob with a PROJECTILE_VELOCITY attribute instance (see
    // LivingEntityAttributesMixin) benefits too. The shooter is captured by appending it to the
    // target method's own args (native Mixin support), rather than via MixinExtras' @Local sugar,
    // which doesn't reliably combine with @ModifyVariable in this Mixin version.
    @ModifyVariable(method = "shoot", at = @At("HEAD"), ordinal = 0, argsOnly = true)
    private float modifiers$projectileVelocity(float velocity, ServerLevel level, LivingEntity shooter) {
        AttributeInstance attribute = shooter.getAttribute(ModAttributes.PROJECTILE_VELOCITY);
        return attribute != null ? velocity * (float) attribute.getValue() : velocity;
    }

    @ModifyVariable(method = "createProjectile", at = @At("HEAD"), argsOnly = true)
    private boolean modifiers$criticalHit(boolean vanillaCritical, Level level, LivingEntity shooter) {
        float chance = ModifierHandler.getCriticalChance(shooter);
        boolean roll = chance > 0.0F && shooter.getRandom().nextFloat() < chance;
        return ModifiersConfig.disableVanillaCritical() ? roll : (vanillaCritical || roll);
    }
}
