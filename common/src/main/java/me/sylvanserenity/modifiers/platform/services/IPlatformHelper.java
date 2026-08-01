package me.sylvanserenity.modifiers.platform.services;

import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;

public interface IPlatformHelper {

    /**
     * Gets the name of the current platform
     *
     * @return The name of the current platform.
     */
    String getPlatformName();

    /**
     * Checks if a mod with the given id is loaded.
     *
     * @param modId The mod to check if it is loaded.
     * @return True if the mod is loaded, false otherwise.
     */
    boolean isModLoaded(String modId);

    /**
     * Check if the game is currently in a development environment.
     *
     * @return True if in a development environment, false otherwise.
     */
    boolean isDevelopmentEnvironment();

    /**
     * Gets the name of the environment type as a string.
     *
     * @return The name of the environment type.
     */
    default String getEnvironmentName() {
        return isDevelopmentEnvironment() ? "development" : "production";
    }

    /**
     * Grants an attribute modifier through the platform's native accessory-slot API (e.g. Accessories
     * on Fabric) rather than the vanilla attribute component, for curio/accessory-slot items whose
     * bonus shouldn't rely on vanilla equipment-slot attribute matching. No-op on platforms that don't
     * need this (e.g. NeoForge, where CuriosCompat grants the bonus reactively via the applied
     * modifier's id instead of a persisted attribute write).
     *
     * @param stack     The item stack to grant the modifier on.
     * @param modId     The unique id for this specific attribute entry (attribute + modifier).
     * @param attribute The attribute to modify.
     * @param amount    The modifier's amount.
     * @param operation The modifier's operation.
     */
    default void applyAccessoryAttribute(ItemStack stack, ResourceLocation modId, Holder<Attribute> attribute, double amount, AttributeModifier.Operation operation) {}

    /**
     * Reverses {@link #applyAccessoryAttribute}, removing the previously-granted modifier by its id.
     * No-op on platforms that don't need this (see applyAccessoryAttribute).
     *
     * @param stack     The item stack to remove the modifier from.
     * @param modId     The same id previously passed to applyAccessoryAttribute.
     * @param attribute The attribute that was modified.
     */
    default void removeAccessoryAttribute(ItemStack stack, ResourceLocation modId, Holder<Attribute> attribute) {}
}