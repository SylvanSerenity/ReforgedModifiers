package me.sylvanserenity.modifiers.compat;

import io.wispforest.accessories.api.AccessoriesAPI;
import io.wispforest.accessories.api.components.AccessoriesDataComponents;
import io.wispforest.accessories.api.components.AccessoryItemAttributeModifiers;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;

public class AccessoriesCompat {
    // Accessories reads attribute bonuses from its own item data component (populated here), not
    // vanilla's.
    // It's unrelated to which vanilla equipment slot (if any) the item occupies, which is what a
    // curio/accessory-slot item's bonus should be granted through instead of the vanilla
    // ATTRIBUTE_MODIFIERS component.
    public static void applyAttribute(ItemStack stack, ResourceLocation modId, Holder<Attribute> attribute, double amount, AttributeModifier.Operation operation) {
        AccessoriesAPI.addAttribute(stack, "any", attribute, modId, amount, operation, true);
    }

    public static void removeAttribute(ItemStack stack, ResourceLocation modId, Holder<Attribute> attribute) {
        stack.update(AccessoriesDataComponents.ATTRIBUTES, AccessoryItemAttributeModifiers.EMPTY, modifiers -> modifiers.withoutModifier(attribute, modId));
    }
}
