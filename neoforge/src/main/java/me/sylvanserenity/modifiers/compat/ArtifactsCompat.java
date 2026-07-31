package me.sylvanserenity.modifiers.compat;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import artifacts.component.ability.AttributeModifiers;
import artifacts.registry.ModDataComponents;
import net.minecraft.core.Holder;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.item.ItemStack;

// Artifacts is an optional dependency - only touch its classes when it's confirmed loaded
// (see IPlatformHelper#dedupeArtifactsAttributes callers), or this throws NoClassDefFoundError.
public class ArtifactsCompat {
    // Artifacts stores its own attribute bonuses in its own data component, separate from vanilla's.
    // Some Artifacts items carry multiple entries for the same attribute, which its own tooltip
    // renders as one repeated line per entry - collapse to one per attribute.
    public static void dedupeAttributes(ItemStack stack) {
        AttributeModifiers mods = stack.get(ModDataComponents.ATTRIBUTE_MODIFIERS.get());
        if (mods == null) return;

        Set<Holder<Attribute>> seen = new HashSet<>();
        List<AttributeModifiers.Entry> deduped = mods.entries().stream()
            .filter(entry -> seen.add(entry.attribute()))
            .toList();
        if (deduped.size() != mods.entries().size()) {
            stack.set(ModDataComponents.ATTRIBUTE_MODIFIERS.get(), new AttributeModifiers(deduped));
        }
    }
}
