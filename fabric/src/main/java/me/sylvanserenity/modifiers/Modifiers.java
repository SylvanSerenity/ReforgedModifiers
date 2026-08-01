package me.sylvanserenity.modifiers;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import me.sylvanserenity.modifiers.command.ModifierCommand;
import me.sylvanserenity.modifiers.modifier.ModAttributes;

public class Modifiers implements ModInitializer {
    @Override
    public void onInitialize() {
        ModAttributes.CRITICAL_CHANCE = Registry.registerForHolder(
            BuiltInRegistries.ATTRIBUTE, ModAttributes.CRITICAL_CHANCE_KEY, ModAttributes.createCriticalChanceAttribute()
        );

        // Use Fabric to bootstrap the Common mod.
        CommonClass.init();

        // Register commands.
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, env) -> ModifierCommand.register(dispatcher));

        // Attach the critical chance attribute to players.
        FabricDefaultAttributeRegistry.register(EntityType.PLAYER, Player.createAttributes().add(ModAttributes.CRITICAL_CHANCE, 0.0));
    }
}
