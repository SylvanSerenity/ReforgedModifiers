package me.sylvanserenity.modifiers.platform;

import me.sylvanserenity.modifiers.compat.AccessoriesCompat;
import me.sylvanserenity.modifiers.platform.services.IPlatformHelper;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
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
    public void applyAccessoryAttribute(ItemStack stack, ResourceLocation modId, Holder<Attribute> attribute, double amount, AttributeModifier.Operation operation) {
        if (isModLoaded("accessories")) {
            AccessoriesCompat.applyAttribute(stack, modId, attribute, amount, operation);
        }
    }

    @Override
    public void removeAccessoryAttribute(ItemStack stack, ResourceLocation modId, Holder<Attribute> attribute) {
        if (isModLoaded("accessories")) {
            AccessoriesCompat.removeAttribute(stack, modId, attribute);
        }
    }
}
