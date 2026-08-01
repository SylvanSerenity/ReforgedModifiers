package me.sylvanserenity.modifiers;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.RegisterCommandsEvent;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import me.sylvanserenity.modifiers.command.ModifierCommand;
import me.sylvanserenity.modifiers.critical.CriticalHitCompat;
import me.sylvanserenity.modifiers.curios.CuriosCompat;
import me.sylvanserenity.modifiers.modifier.ModAttributes;
import me.sylvanserenity.modifiers.modifier.ModifierHandler;
import me.sylvanserenity.modifiers.platform.Services;
import me.sylvanserenity.modifiers.tooltip.TooltipCompat;

@Mod(Constants.MOD_ID)
public class Modifiers {
    // NeoForge freezes Registries.ATTRIBUTE before mod constructors run, so registration has to go
    // through this rather than the plain Registry.register other loader-agnostic content could use.
    private static final DeferredRegister<Attribute> ATTRIBUTES = DeferredRegister.create(Registries.ATTRIBUTE, Constants.MOD_ID);
    private static final DeferredHolder<Attribute, Attribute> CRITICAL_CHANCE =
        ATTRIBUTES.register("critical_chance", ModAttributes::createCriticalChanceAttribute);
    private static final DeferredHolder<Attribute, Attribute> PROJECTILE_VELOCITY =
        ATTRIBUTES.register("projectile_velocity", ModAttributes::createProjectileVelocityAttribute);
    private static final DeferredHolder<Attribute, Attribute> DRAW_SPEED =
        ATTRIBUTES.register("draw_speed", ModAttributes::createLoadSpeedAttribute);
    private static final DeferredHolder<Attribute, Attribute> PROJECTILE_DAMAGE =
        ATTRIBUTES.register("projectile_damage", ModAttributes::createProjectileDamageAttribute);
    private static final DeferredHolder<Attribute, Attribute> KNOCKBACK =
        ATTRIBUTES.register("knockback", ModAttributes::createKnockbackAttribute);

    public Modifiers(IEventBus eventBus) {
        ModAttributes.CRITICAL_CHANCE = CRITICAL_CHANCE;
        ModAttributes.PROJECTILE_VELOCITY = PROJECTILE_VELOCITY;
        ModAttributes.DRAW_SPEED = DRAW_SPEED;
        ModAttributes.PROJECTILE_DAMAGE = PROJECTILE_DAMAGE;
        ModAttributes.KNOCKBACK = KNOCKBACK;
        ATTRIBUTES.register(eventBus);

        // Use NeoForge to bootstrap the Common mod.
        CommonClass.init();
        NeoForge.EVENT_BUS.addListener(this::onRegisterCommands);
        NeoForge.EVENT_BUS.addListener(this::onEntityJoinLevel);
        NeoForge.EVENT_BUS.register(CriticalHitCompat.class);
        NeoForge.EVENT_BUS.register(TooltipCompat.class);

        // Curios compatibility.
        if (Services.PLATFORM.isModLoaded("curios")) {
            NeoForge.EVENT_BUS.register(CuriosCompat.class);
        }
    }

    private void onRegisterCommands(RegisterCommandsEvent e) {
        ModifierCommand.register(e.getDispatcher());
    }

    // Gives freshly spawned mobs a chance at a random weapon modifier, same as loot/crafting. Skips
    // chunk-reloaded entities directly (loadedFromDisk), and ModifierHandler's own idempotency guard
    // covers any other repeat-join case.
    private void onEntityJoinLevel(EntityJoinLevelEvent e) {
        if (!e.loadedFromDisk() && e.getEntity() instanceof LivingEntity entity) {
            ModifierHandler.applyRandomToEquipment(entity);
        }
    }
}