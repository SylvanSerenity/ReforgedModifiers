package me.sylvanserenity.modifiers;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import me.sylvanserenity.modifiers.command.ModifierCommand;
import me.sylvanserenity.modifiers.modifier.ModAttributes;
import me.sylvanserenity.modifiers.modifier.ModifierHandler;

public class Modifiers implements ModInitializer {
    @Override
    public void onInitialize() {
        ModAttributes.CRITICAL_CHANCE = Registry.registerForHolder(
            BuiltInRegistries.ATTRIBUTE, ModAttributes.CRITICAL_CHANCE_KEY, ModAttributes.createCriticalChanceAttribute()
        );
        ModAttributes.PROJECTILE_VELOCITY = Registry.registerForHolder(
            BuiltInRegistries.ATTRIBUTE, ModAttributes.PROJECTILE_VELOCITY_KEY, ModAttributes.createProjectileVelocityAttribute()
        );
        ModAttributes.LOAD_SPEED = Registry.registerForHolder(
            BuiltInRegistries.ATTRIBUTE, ModAttributes.LOAD_SPEED_KEY, ModAttributes.createLoadSpeedAttribute()
        );
        ModAttributes.PROJECTILE_DAMAGE = Registry.registerForHolder(
            BuiltInRegistries.ATTRIBUTE, ModAttributes.PROJECTILE_DAMAGE_KEY, ModAttributes.createProjectileDamageAttribute()
        );
        ModAttributes.KNOCKBACK = Registry.registerForHolder(
            BuiltInRegistries.ATTRIBUTE, ModAttributes.KNOCKBACK_KEY, ModAttributes.createKnockbackAttribute()
        );

        // Use Fabric to bootstrap the Common mod.
        CommonClass.init();

        // Register commands.
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, env) -> ModifierCommand.register(dispatcher));

        FabricDefaultAttributeRegistry.register(EntityType.PLAYER, Player.createAttributes());

        // Gives freshly spawned mobs a chance at a random weapon modifier.
        ServerEntityEvents.ENTITY_LOAD.register((entity, level) -> {
            if (entity instanceof LivingEntity livingEntity) {
                ModifierHandler.applyRandomToEquipment(livingEntity);
            }
        });
    }
}
