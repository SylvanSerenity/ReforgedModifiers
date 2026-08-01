package me.sylvanserenity.modifiers.modifier;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import me.sylvanserenity.modifiers.platform.Services;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ProjectileWeaponItem;
import net.minecraft.world.item.TieredItem;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.crafting.Ingredient;

public class ModifierHandler {
    public static final int BASE_TIER = Tier.DEFAULT.ordinal();

    // Custom NBT key tracking which modifier is applied.
    private static final String MODIFIER_ID_KEY = "ReforgedModifiersModifier";

    public static void apply(ItemStack stack, Modifier mod) {
        Set<Modifier.Category> categories = getItemCategories(stack);
        boolean useNativeAccessoryApi = isAccessory(stack);

        // Add the modifier's own entry first, so it's listed above the item's existing attributes.
        ItemAttributeModifiers mods = ItemAttributeModifiers.EMPTY;
        Set<Holder<Attribute>> seenFromModifier = new HashSet<>();
        for (Modifier.Entry e : mod.entries(categories)) {
            ResourceLocation modId = mod.id().withSuffix("/" + e.attr().unwrapKey().orElseThrow().location().getPath());
            if (useNativeAccessoryApi) {
                Services.PLATFORM.applyAccessoryAttribute(stack, modId, e.attr(), e.amount(), e.op());
            } else if (seenFromModifier.add(e.attr())) {
                mods = mods.withModifierAdded(e.attr(), new AttributeModifier(modId, e.amount(), e.op()), e.slot());
            }
        }

        // Add stack's existing attributes (excluding a previous roll of this modifier).
        ItemAttributeModifiers existing = stack.getOrDefault(DataComponents.ATTRIBUTE_MODIFIERS, ItemAttributeModifiers.EMPTY);
        Set<Holder<Attribute>> seenFromExisting = new HashSet<>();
        for (ItemAttributeModifiers.Entry entry : existing.modifiers()) {
            if (isOwnModifierEntry(entry)) continue;

            if (seenFromExisting.add(entry.attribute())) {
                mods = mods.withModifierAdded(entry.attribute(), entry.modifier(), entry.slot());
            }
        }
        stack.set(DataComponents.ATTRIBUTE_MODIFIERS, mods);

        // Record which modifier is applied.
        CustomData.update(DataComponents.CUSTOM_DATA, stack, tag -> tag.putString(MODIFIER_ID_KEY, mod.id().toString()));

        // Set item name.
        Tier tier = getItemTier(stack, mod.tierDelta(categories));
        Component modName = Component
            .translatable("modifier." + mod.id().getNamespace() + "." + mod.id().getPath())
            .withStyle(
                Style.EMPTY
                    .withColor(tier.color)
                    .withItalic(false)
            );
        Component itemName = Component.translatable(stack.getItem().getDescriptionId()).withStyle(Style.EMPTY.withItalic(false));
        stack.set(DataComponents.CUSTOM_NAME, modName.copy().append(" ").append(itemName));
    }

    // Strips this modifier's attributes, lore, tracking data, and custom name from the stack.
    public static void remove(ItemStack stack) {
        Modifier mod = getAppliedModifier(stack);
        if (mod == null) return;

        if (isAccessory(stack)) {
            Set<Modifier.Category> categories = getItemCategories(stack);
            for (Modifier.Entry e : mod.entries(categories)) {
                ResourceLocation modId = mod.id().withSuffix("/" + e.attr().unwrapKey().orElseThrow().location().getPath());
                Services.PLATFORM.removeAccessoryAttribute(stack, modId, e.attr());
            }
        }

        ItemAttributeModifiers existing = stack.getOrDefault(DataComponents.ATTRIBUTE_MODIFIERS, ItemAttributeModifiers.EMPTY);
        ItemAttributeModifiers mods = ItemAttributeModifiers.EMPTY;
        for (ItemAttributeModifiers.Entry entry : existing.modifiers()) {
            if (isOwnModifierEntry(entry)) continue;
            mods = mods.withModifierAdded(entry.attribute(), entry.modifier(), entry.slot());
        }
        stack.set(DataComponents.ATTRIBUTE_MODIFIERS, mods);

        CustomData.update(DataComponents.CUSTOM_DATA, stack, tag -> tag.remove(MODIFIER_ID_KEY));
        stack.remove(DataComponents.CUSTOM_NAME);
    }

    /// Whether mod is applicable to item.
    public static boolean isApplicable(ItemStack stack, Modifier mod) {
        return !mod.entries(getItemCategories(stack)).isEmpty();
    }

    // Returns an empty set for items that aren't a valid target for any modifier.
    private static Set<Modifier.Category> getItemCategories(ItemStack stack) {
        if (stack.getItem() instanceof ArmorItem) {
            return ModifiersConfig.armorCountsAsAccessory()
                ? EnumSet.of(Modifier.Category.ACCESSORY)
                : EnumSet.noneOf(Modifier.Category.class);
        }
        if (isAccessory(stack)) return EnumSet.of(Modifier.Category.ACCESSORY);

        // TOOL is a catch-all every weapon/tool item gets if not already categorized.
        Set<Modifier.Category> categories = EnumSet.noneOf(Modifier.Category.class);
        boolean isWeaponOrTool = false;
        if (stack.getItem() instanceof ProjectileWeaponItem) {
            categories.add(Modifier.Category.RANGED);
            isWeaponOrTool = true;
        }
        if (isMeleeWeapon(stack)) {
            categories.add(Modifier.Category.MELEE);
            isWeaponOrTool = true;
        }
        if (stack.getItem() instanceof DiggerItem) {
            isWeaponOrTool = true;
        }
        if (isWeaponOrTool) categories.add(Modifier.Category.TOOL);
        return categories;
    }

    // Melee weapons include axes and anything non-DiggerItem with attack attributes.
    private static boolean isMeleeWeapon(ItemStack stack) {
        if (stack.getItem() instanceof AxeItem) return true;
        return hasAttackAttributes(stack) && !(stack.getItem() instanceof DiggerItem);
    }

    private static boolean isAccessory(ItemStack stack) {
        // Curios exposes slots like "curios:head", Accessories exposes slots like "accessories:head".
        return BuiltInRegistries.ITEM.getTagNames()
            .filter(tag -> tag.location().getNamespace().equals("curios") || tag.location().getNamespace().equals("accessories"))
            .anyMatch(stack::is);
    }

    private static boolean hasAttackAttributes(ItemStack stack) {
        ItemAttributeModifiers modifiers = stack.getItem().components().getOrDefault(DataComponents.ATTRIBUTE_MODIFIERS, ItemAttributeModifiers.EMPTY);
        for (ItemAttributeModifiers.Entry entry : modifiers.modifiers()) {
            if (entry.attribute().value() == Attributes.ATTACK_DAMAGE.value() || entry.attribute().value() == Attributes.ATTACK_SPEED.value()) return true;
        }

        return false;
    }

    // Recovers which registered Modifier (if any) is currently applied to the stack. Read from
    // CUSTOM_DATA rather than scanning attribute entries, since true accessory-slot items don't carry
    // their bonus in the vanilla attribute component at all (see apply()).
    public static Modifier getAppliedModifier(ItemStack stack) {
        CustomData data = stack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY);
        if (!data.contains(MODIFIER_ID_KEY)) return null;
        ResourceLocation id = ResourceLocation.tryParse(data.copyTag().getString(MODIFIER_ID_KEY));
        return id != null ? Modifiers.MODIFIERS.get(id) : null;
    }

    // Whether entry was added by a previous ModifierHandler.apply() call (identifiable by its
    // AttributeModifier id belonging to a currently-registered Modifier), as opposed to the item's
    // own base attributes, an enchantment, or some other mod's attribute modifier.
    private static boolean isOwnModifierEntry(ItemAttributeModifiers.Entry entry) {
        return recoverModifier(entry) != null;
    }

    private static Modifier recoverModifier(ItemAttributeModifiers.Entry entry) {
        ResourceLocation id = entry.modifier().id();
        ResourceLocation modId = ResourceLocation.fromNamespaceAndPath(id.getNamespace(), id.getPath().split("/")[0]);
        return Modifiers.MODIFIERS.get(modId);
    }

    private static Tier getItemTier(ItemStack stack, int tierDelta) {
        // Find tier after tierDelta is applied.
        int tier = getBaseTier(stack).ordinal() + tierDelta;

        // Return the final tier.
        return Tier.values()[Math.clamp(tier, 0, Tier.values().length - 1)];
    }

    private static Tier getBaseTier(ItemStack stack) {
        Item item = stack.getItem();

        // Find item's repair ingredient, if it has one (tools/armor only).
        Ingredient repairIngredient = null;
        if (item instanceof TieredItem tiered) {
            repairIngredient = tiered.getTier().getRepairIngredient();
        } else if (item instanceof ArmorItem armor) {
            repairIngredient = armor.getMaterial().value().repairIngredient().get();
        }

        // Search from the highest tier down.
        Map<Tier, String[]> tiers = ModifiersConfig.tiers();
        Tier[] values = Tier.values();
        for (int i = values.length - 1; i >= 0; i--) {
            Tier tier = values[i];
            for (String id : tiers.get(tier)) {
                if (matches(stack, repairIngredient, id)) return tier;
            }
        }
        return Tier.DEFAULT;
    }

    // Matches a config entry against the stack's repair ingredient or item type.
    // Entries starting with "#" are tag references; otherwise item ID.
    private static boolean matches(ItemStack stack, Ingredient repairIngredient, String id) {
        // Match tags.
        if (id.startsWith("#")) {
            TagKey<Item> tag = TagKey.create(Registries.ITEM, ResourceLocation.parse(id.substring(1)));
            if (repairIngredient != null) {
                for (ItemStack candidate : repairIngredient.getItems()) {
                    if (candidate.is(tag)) return true;
                }
            }
            return stack.is(tag);
        }

        // Match item ID.
        Item target = BuiltInRegistries.ITEM.get(ResourceLocation.parse(id));
        if (repairIngredient != null) {
            for (ItemStack candidate : repairIngredient.getItems()) {
                if (candidate.is(target)) return true;
            }
        }
        return stack.is(target);
    }
}
