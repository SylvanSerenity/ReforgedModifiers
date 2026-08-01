package me.sylvanserenity.modifiers;

import me.sylvanserenity.modifiers.modifier.ModifierTooltip;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;

public class ModifiersClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ItemTooltipCallback.EVENT.register((stack, context, flag, lines) -> ModifierTooltip.rewriteCriticalChanceLines(stack, lines));
    }
}
