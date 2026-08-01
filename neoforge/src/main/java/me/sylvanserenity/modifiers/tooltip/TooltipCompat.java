package me.sylvanserenity.modifiers.tooltip;

import me.sylvanserenity.modifiers.modifier.ModifierTooltip;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;

public class TooltipCompat {
    @SubscribeEvent
    public static void onItemTooltip(ItemTooltipEvent event) {
        ModifierTooltip.rewriteCriticalChanceLines(event.getItemStack(), event.getToolTip());
    }
}
