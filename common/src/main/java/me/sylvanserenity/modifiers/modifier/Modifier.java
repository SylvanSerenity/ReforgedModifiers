package me.sylvanserenity.modifiers.modifier;

import java.util.List;
import java.util.Map;
import java.util.Set;

import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;

public record Modifier(ResourceLocation id, Map<Category, CategoryData> categories) {
    public enum Category { MELEE, RANGED, TOOL, ACCESSORY }

    public record CategoryData(int tierDelta, List<Entry> entries) {}

    public record Entry(Holder<Attribute> attr, double amount, AttributeModifier.Operation op, EquipmentSlotGroup slot) {}

    public int tierDelta(Set<Category> itemCategories) {
        CategoryData data = resolve(itemCategories);
        return data != null ? data.tierDelta() : 0;
    }

    public List<Entry> entries(Set<Category> itemCategories) {
        CategoryData data = resolve(itemCategories);
        return data != null ? data.entries() : List.of();
    }

    // Picks a single category's data for the item: the most specific category present (e.g. MELEE
    // over TOOL) if this modifier defines one, otherwise falls back to TOOL - but only if the item
    // is itself TOOL-eligible (weapons/tools), never for a pure ACCESSORY item. A melee weapon can
    // roll either a MELEE-only or a TOOL-only modifier, but never both combined into one application.
    private CategoryData resolve(Set<Category> itemCategories) {
        for (Category category : itemCategories) {
            if (category == Category.TOOL) continue;
            CategoryData data = categories.get(category);
            if (data != null) return data;
        }
        return itemCategories.contains(Category.TOOL) ? categories.get(Category.TOOL) : null;
    }
}
