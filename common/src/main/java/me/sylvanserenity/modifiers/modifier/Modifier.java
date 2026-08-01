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

    public int tierDelta(Category itemCategory) {
        CategoryData data = itemCategory != null ? categories.get(itemCategory) : null;
        return data != null ? data.tierDelta() : 0;
    }

    public List<Entry> entries(Category itemCategory) {
        CategoryData data = itemCategory != null ? categories.get(itemCategory) : null;
        return data != null ? data.entries() : List.of();
    }
}
