package me.sylvanserenity.modifiers;

import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import me.sylvanserenity.modifiers.command.ModifierCommand;

@Mod(Constants.MOD_ID)
@EventBusSubscriber(modid = Constants.MOD_ID)
public class Modifiers {
    public Modifiers() {
        // Use Forge to bootstrap the Common mod.
        CommonClass.init();
    }

    @SubscribeEvent
    public static void onRegisterCommands(RegisterCommandsEvent e) {
        ModifierCommand.register(e.getDispatcher());
    }
}