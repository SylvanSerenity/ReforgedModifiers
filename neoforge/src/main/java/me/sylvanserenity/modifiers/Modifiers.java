package me.sylvanserenity.modifiers;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.RegisterCommandsEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeModificationEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import me.sylvanserenity.modifiers.command.ModifierCommand;
import me.sylvanserenity.modifiers.critical.CriticalHitCompat;
import me.sylvanserenity.modifiers.curios.CuriosCompat;
import me.sylvanserenity.modifiers.modifier.ModAttributes;
import me.sylvanserenity.modifiers.platform.Services;
import me.sylvanserenity.modifiers.tooltip.TooltipCompat;

@Mod(Constants.MOD_ID)
public class Modifiers {
    // NeoForge freezes Registries.ATTRIBUTE before mod constructors run, so registration has to go
    // through this rather than the plain Registry.register other loader-agnostic content could use.
    private static final DeferredRegister<Attribute> ATTRIBUTES = DeferredRegister.create(Registries.ATTRIBUTE, Constants.MOD_ID);
    private static final DeferredHolder<Attribute, Attribute> CRITICAL_CHANCE =
        ATTRIBUTES.register("critical_chance", ModAttributes::createCriticalChanceAttribute);

    public Modifiers(IEventBus eventBus) {
        ModAttributes.CRITICAL_CHANCE = CRITICAL_CHANCE;
        ATTRIBUTES.register(eventBus);

        // Use NeoForge to bootstrap the Common mod.
        CommonClass.init();
        NeoForge.EVENT_BUS.addListener(this::onRegisterCommands);
        NeoForge.EVENT_BUS.register(CriticalHitCompat.class);
        NeoForge.EVENT_BUS.register(TooltipCompat.class);
        eventBus.addListener(this::onAttributeModification);

        // Curios compatibility.
        if (Services.PLATFORM.isModLoaded("curios")) {
            NeoForge.EVENT_BUS.register(CuriosCompat.class);
        }
    }

    private void onRegisterCommands(RegisterCommandsEvent e) {
        ModifierCommand.register(e.getDispatcher());
    }

    private void onAttributeModification(EntityAttributeModificationEvent e) {
        e.add(EntityType.PLAYER, ModAttributes.CRITICAL_CHANCE);
    }
}