package me.sylvanserenity.modifiers.tooltip;

import java.util.List;
import me.sylvanserenity.modifiers.modifier.ModAttributes;
import me.sylvanserenity.modifiers.modifier.ModifierTooltip;
import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.common.extensions.IAttributeExtension;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;

public class TooltipCompat {
    @SubscribeEvent
    public static void onItemTooltip(ItemTooltipEvent event) {
        // Harmless no-op here: NeoForge doesn't render attribute tooltips in vanilla's shape (see below),
        // so this never finds a match, but it's still needed for other loaders sharing this event path.
        ModifierTooltip.rewriteCriticalChanceLines(event.getItemStack(), event.getToolTip());
        rewriteCriticalChanceLinesNeoForge(event);
    }

    // NeoForge replaces vanilla's private attribute-tooltip formatting with its own
    // (IAttributeExtension#toComponent), using a different translation key ("neoforge.modifier.plus"/"take"
    // instead of "attribute.modifier.plus.0"/"take.0") and nesting the formatted value in its own
    // "neoforge.value.flat" component instead of a plain string arg. ModifierTooltip's reconstruction is
    // shaped for vanilla/Fabric and never matches here, so the line never gets patched. Attribute implements
    // IAttributeExtension on NeoForge, so we can call the exact same method NeoForge used to build the
    // original line - guaranteeing our reconstruction matches it for lookup - then rebuild it with "%"
    // inserted into the nested value component.
    private static void rewriteCriticalChanceLinesNeoForge(ItemTooltipEvent event) {
        Attribute attribute = ModAttributes.CRITICAL_CHANCE.value();
        ItemStack stack = event.getItemStack();
        List<Component> tooltip = event.getToolTip();
        ItemAttributeModifiers modifiers = stack.getOrDefault(DataComponents.ATTRIBUTE_MODIFIERS, ItemAttributeModifiers.EMPTY);

        for (ItemAttributeModifiers.Entry entry : modifiers.modifiers()) {
            if (entry.attribute().value() != attribute) continue;

            AttributeModifier modifier = entry.modifier();
            double value = modifier.amount();
            if (value == 0.0) continue;

            Component original = attribute.toComponent(modifier, event.getFlags());
            int index = tooltip.indexOf(original);
            if (index == -1) continue;

            String key = value > 0 ? "neoforge.modifier.plus" : "neoforge.modifier.take";
            ChatFormatting color = attribute.getStyle(value > 0);
            Component attrDesc = Component.translatable(attribute.getDescriptionId());
            Component valueComp = Component.translatable("neoforge.value.flat", IAttributeExtension.FORMAT.format(value) + "%");
            MutableComponent patched = Component.translatable(key, valueComp, attrDesc)
                .withStyle(color)
                .append(attribute.getDebugInfo(modifier, event.getFlags()));

            tooltip.set(index, patched);
        }
    }
}
