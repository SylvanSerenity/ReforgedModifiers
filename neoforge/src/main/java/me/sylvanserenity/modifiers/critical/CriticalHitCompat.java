package me.sylvanserenity.modifiers.critical;

import me.sylvanserenity.modifiers.modifier.CriticalHitTracker;
import me.sylvanserenity.modifiers.modifier.ModifierHandler;
import me.sylvanserenity.modifiers.modifier.ModifiersConfig;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.living.LivingKnockBackEvent;
import net.neoforged.neoforge.event.entity.player.CriticalHitEvent;

public class CriticalHitCompat {
    @SubscribeEvent
    public static void onCriticalHit(CriticalHitEvent event) {
        Player player = event.getEntity();
        float chance = ModifierHandler.getCriticalChance(player);
        boolean roll = chance > 0.0F && player.getRandom().nextFloat() < chance;

        boolean critical = ModifiersConfig.disableVanillaCritical() ? roll : (roll || event.isCriticalHit());
        event.setCriticalHit(critical);
        if (critical) {
            event.setDamageMultiplier((float) ModifiersConfig.criticalDamageMultiplier());
            // Correlates with the knockback bonus since knockback is applied in LivingEntity#knockback.
            CriticalHitTracker.markCritical(event.getTarget().getId());
        }
    }

    @SubscribeEvent
    public static void onKnockback(LivingKnockBackEvent event) {
        if (CriticalHitTracker.consumeCritical(event.getEntity().getId())) {
            event.setStrength((float) (event.getStrength() * (1.0 + ModifiersConfig.criticalKnockbackMultiplier())));
        }
    }
}
