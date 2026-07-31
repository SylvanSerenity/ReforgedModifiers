package me.sylvanserenity.modifiers;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import me.sylvanserenity.modifiers.command.ModifierCommand;

public class Modifiers implements ModInitializer {
    @Override
    public void onInitialize() {
        // Use Fabric to bootstrap the Common mod.
        CommonClass.init();

        // Register commands.
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, env) -> ModifierCommand.register(dispatcher));
    }
}
