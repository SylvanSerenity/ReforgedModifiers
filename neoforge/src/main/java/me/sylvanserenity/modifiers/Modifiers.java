package me.sylvanserenity.modifiers;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.RegisterCommandsEvent;
import me.sylvanserenity.modifiers.command.ModifierCommand;
import me.sylvanserenity.modifiers.crafting.CraftingCompat;
import me.sylvanserenity.modifiers.curios.CuriosCompat;
import me.sylvanserenity.modifiers.platform.Services;

@Mod(Constants.MOD_ID)
public class Modifiers {
    public Modifiers(IEventBus eventBus) {
        // Use NeoForge to bootstrap the Common mod.
        CommonClass.init();
        NeoForge.EVENT_BUS.addListener(this::onRegisterCommands);
        NeoForge.EVENT_BUS.register(CraftingCompat.class);

        // Curios compatibility.
        if (Services.PLATFORM.isModLoaded("curios")) {
            NeoForge.EVENT_BUS.register(CuriosCompat.class);
        }
    }

    private void onRegisterCommands(RegisterCommandsEvent e) {
        ModifierCommand.register(e.getDispatcher());
    }
}