package me.sylvanserenity.modifiers.platform.services;

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
     * Dedupes Artifacts' own attribute-modifier data component on the given stack, if Artifacts
     * is present on this platform. No-op on platforms without an Artifacts build to compile against.
     *
     * @param stack The stack to clean up.
     */
    default void dedupeArtifactsAttributes(ItemStack stack) {
    }
}