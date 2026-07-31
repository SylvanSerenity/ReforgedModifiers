package me.sylvanserenity.modifiers.modifier;

import java.util.List;
import java.util.Map;

import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;

public record Modifier(ResourceLocation id, Map<Category, CategoryData> categories) {
    public enum Category { MELEE, RANGED, TOOL, ACCESSORY }

    public record CategoryData(int tierDelta, List<Entry> entries) {}

    public record Entry(Holder<Attribute> attr, double amount, AttributeModifier.Operation op, EquipmentSlotGroup slot) {}

    public int tierDelta(Category category) {
        if (category == null) return 0;
        CategoryData data = categories.get(category);
        if (data != null) return data.tierDelta();
        CategoryData fallback = categories.get(Category.TOOL);
        return fallback != null ? fallback.tierDelta() : 0;
    }

    // category is null for items that don't belong to any modifier category (e.g. an invalid item).
    public List<Entry> entries(Category category) {
        if (category == null) return List.of();
        CategoryData data = categories.get(category);
        return data != null ? data.entries() : List.of();
    }
}
