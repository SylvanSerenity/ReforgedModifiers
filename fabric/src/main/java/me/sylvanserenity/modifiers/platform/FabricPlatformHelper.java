package me.sylvanserenity.modifiers.platform;

import me.sylvanserenity.modifiers.compat.ArtifactsCompat;
import me.sylvanserenity.modifiers.platform.services.IPlatformHelper;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.world.item.ItemStack;

public class FabricPlatformHelper implements IPlatformHelper {
    @Override
    public String getPlatformName() {
        return "Fabric";
    }

    @Override
    public boolean isModLoaded(String modId) {
        return FabricLoader.getInstance().isModLoaded(modId);
    }

    @Override
    public boolean isDevelopmentEnvironment() {
        return FabricLoader.getInstance().isDevelopmentEnvironment();
    }

    @Override
    public void dedupeArtifactsAttributes(ItemStack stack) {
        ArtifactsCompat.dedupeAttributes(stack);
    }
}
