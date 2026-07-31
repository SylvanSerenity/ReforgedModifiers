package me.sylvanserenity.modifiers.modifier;

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
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ProjectileWeaponItem;
import net.minecraft.world.item.TieredItem;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.crafting.Ingredient;

public class ModifierHandler {
    public static final int BASE_TIER = Tier.DEFAULT.ordinal();

    public static void apply(ItemStack stack, Modifier mod) {
        Modifier.Category category = getItemCategory(stack);

        // Start from the item's own base attributes, deduping any repeats.
        ItemAttributeModifiers base = stack.getItem().components().getOrDefault(DataComponents.ATTRIBUTE_MODIFIERS, ItemAttributeModifiers.EMPTY);
        ItemAttributeModifiers mods = ItemAttributeModifiers.EMPTY;
        Set<Holder<Attribute>> seen = new HashSet<>();
        for (ItemAttributeModifiers.Entry entry : base.modifiers()) {
            if (seen.add(entry.attribute())) {
                mods = mods.withModifierAdded(entry.attribute(), entry.modifier(), entry.slot());
            }
        }

        // Append the modifier's own entries.
        for (Modifier.Entry e : mod.entries(category)) {
            ResourceLocation modId = mod.id().withSuffix("/" + e.attr().unwrapKey().orElseThrow().location().getPath());
            mods = mods.withModifierAdded(e.attr(), new AttributeModifier(modId, e.amount(), e.op()), e.slot());
        }
        stack.set(DataComponents.ATTRIBUTE_MODIFIERS, mods);

        // Dedupe Artifacts' own attribute component too, if Artifacts is loaded on this platform.
        if (Services.PLATFORM.isModLoaded("artifacts")) {
            Services.PLATFORM.dedupeArtifactsAttributes(stack);
        }

        // Set item name.
        Tier tier = getItemTier(stack, mod.tierDelta(category));
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

    /// Whether mod is applicable to item.
    public static boolean isApplicable(ItemStack stack, Modifier mod) {
        return !mod.entries(getItemCategory(stack)).isEmpty();
    }

    // Returns null for items that aren't a valid target for any modifier.
    private static Modifier.Category getItemCategory(ItemStack stack) {
        if (stack.getItem() instanceof ArmorItem) {
            return ModifiersConfig.armorCountsAsAccessory() ? Modifier.Category.ACCESSORY : null;
        }
        if (isCurioSlotItem(stack)) return Modifier.Category.ACCESSORY;
        if (stack.getItem() instanceof ProjectileWeaponItem) return Modifier.Category.RANGED;
        if (stack.getItem() instanceof DiggerItem) return Modifier.Category.TOOL;
        if (hasAttackAttributes(stack)) return Modifier.Category.MELEE;
        return null;
    }

    private static boolean isCurioSlotItem(ItemStack stack) {
        // NOTE: Curios defines slot eligibility via item tags (e.g. "curios:head").
        return BuiltInRegistries.ITEM.getTagNames()
            .filter(tag -> tag.location().getNamespace().equals("curios"))
            .anyMatch(stack::is);
    }

    private static boolean hasAttackAttributes(ItemStack stack) {
        ItemAttributeModifiers modifiers = stack.getItem().components().getOrDefault(DataComponents.ATTRIBUTE_MODIFIERS, ItemAttributeModifiers.EMPTY);
        for (ItemAttributeModifiers.Entry entry : modifiers.modifiers()) {
            if (entry.attribute().value() == Attributes.ATTACK_DAMAGE.value() || entry.attribute().value() == Attributes.ATTACK_SPEED.value()) return true;
        }

        return false;
    }

    // Recovers which registered Modifier (if any) is currently baked into the stack's attribute modifiers.
    public static Modifier getAppliedModifier(ItemStack stack) {
        ItemAttributeModifiers mods = stack.getOrDefault(DataComponents.ATTRIBUTE_MODIFIERS, ItemAttributeModifiers.EMPTY);
        for (ItemAttributeModifiers.Entry entry : mods.modifiers()) {
            ResourceLocation id = entry.modifier().id();
            ResourceLocation modId = ResourceLocation.fromNamespaceAndPath(id.getNamespace(), id.getPath().split("/")[0]);
            Modifier mod = Modifiers.MODIFIERS.get(modId);
            if (mod != null) return mod;
        }
        return null;
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
        // Match item ID.
        Item target = BuiltInRegistries.ITEM.get(ResourceLocation.parse(id));
        if (repairIngredient != null) {
            for (ItemStack candidate : repairIngredient.getItems()) {
                if (candidate.is(target)) return true;
            }
        }
        if (stack.is(target)) return true;

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

        return false;
    }
}
