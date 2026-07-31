package me.sylvanserenity.modifiers.platform;

import me.sylvanserenity.modifiers.compat.ArtifactsCompat;
import me.sylvanserenity.modifiers.platform.services.IPlatformHelper;
import net.minecraft.world.item.ItemStack;
import net.neoforged.fml.ModList;
import net.neoforged.fml.loading.FMLLoader;

public class NeoForgePlatformHelper implements IPlatformHelper {
    @Override
    public String getPlatformName() {
        return "NeoForge";
    }

    @Override
    public boolean isModLoaded(String modId) {
        return ModList.get().isLoaded(modId);
    }

    @Override
    public boolean isDevelopmentEnvironment() {
        return !FMLLoader.isProduction();
    }

    @Override
    public void dedupeArtifactsAttributes(ItemStack stack) {
        ArtifactsCompat.dedupeAttributes(stack);
    }
}