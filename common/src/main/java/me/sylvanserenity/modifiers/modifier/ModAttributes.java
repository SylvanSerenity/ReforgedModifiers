package me.sylvanserenity.modifiers.modifier;

import me.sylvanserenity.modifiers.Constants;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;

public class ModAttributes {
    public static final ResourceKey<Attribute> CRITICAL_CHANCE_KEY = ResourceKey.create(
        Registries.ATTRIBUTE, ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "critical_chance")
    );

    public static Holder<Attribute> CRITICAL_CHANCE;

    public static Attribute createCriticalChanceAttribute() {
        return new RangedAttribute("attribute." + Constants.MOD_ID + ".critical_chance", 0.0, 0.0, 100.0).setSyncable(true);
    }
}
