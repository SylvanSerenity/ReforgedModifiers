package me.sylvanserenity.modifiers.modifier;

import java.util.List;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemAttributeModifiers;

// Vanilla's attribute tooltip formatting is hardcoded to the modifier's Operation (ADD_VALUE never
// gets a "%" suffix, only ADD_MULTIPLIED_BASE/TOTAL do), and critical_chance uses ADD_VALUE so its
// amounts sum correctly (fractions of ADD_MULTIPLIED_BASE against a 0 base attribute would always be 0).
// So the vanilla-rendered line is patched in place after the fact, matching it by recomputing the exact
// same Component vanilla would have produced (translatable Components compare structurally).
public class ModifierTooltip {
    public static void rewriteCriticalChanceLines(ItemStack stack, List<Component> tooltip) {
        ItemAttributeModifiers modifiers = stack.getOrDefault(DataComponents.ATTRIBUTE_MODIFIERS, ItemAttributeModifiers.EMPTY);
        for (ItemAttributeModifiers.Entry entry : modifiers.modifiers()) {
            if (entry.attribute().value() != ModAttributes.CRITICAL_CHANCE.value()) continue;

            double amount = entry.modifier().amount();
            if (amount == 0.0) continue;

            boolean positive = amount > 0.0;
            String key = "attribute.modifier." + (positive ? "plus" : "take") + ".0";
            String formatted = ItemAttributeModifiers.ATTRIBUTE_MODIFIER_FORMAT.format(positive ? amount : -amount);
            Component name = Component.translatable(ModAttributes.CRITICAL_CHANCE.value().getDescriptionId());
            Component vanilla = Component.translatable(key, formatted, name).withStyle(ModAttributes.CRITICAL_CHANCE.value().getStyle(positive));
            Component patched = Component.translatable(key, formatted + "%", name).withStyle(ModAttributes.CRITICAL_CHANCE.value().getStyle(positive));

            int index = tooltip.indexOf(vanilla);
            if (index != -1) tooltip.set(index, patched);
        }
    }
}
