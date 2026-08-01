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
    public static final ResourceKey<Attribute> PROJECTILE_VELOCITY_KEY = ResourceKey.create(
        Registries.ATTRIBUTE, ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "projectile_velocity")
    );
    public static final ResourceKey<Attribute> LOAD_SPEED_KEY = ResourceKey.create(
        Registries.ATTRIBUTE, ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "load_speed")
    );
    public static final ResourceKey<Attribute> PROJECTILE_DAMAGE_KEY = ResourceKey.create(
        Registries.ATTRIBUTE, ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "projectile_damage")
    );
    public static final ResourceKey<Attribute> KNOCKBACK_KEY = ResourceKey.create(
        Registries.ATTRIBUTE, ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "knockback")
    );

    public static Holder<Attribute> CRITICAL_CHANCE;
    public static Holder<Attribute> PROJECTILE_VELOCITY;
    public static Holder<Attribute> LOAD_SPEED;
    public static Holder<Attribute> PROJECTILE_DAMAGE;
    public static Holder<Attribute> KNOCKBACK;

    public static Attribute createCriticalChanceAttribute() {
        return new RangedAttribute("attribute." + Constants.MOD_ID + ".critical_chance", 0.0, 0.0, 100.0).setSyncable(true);
    }

    public static Attribute createProjectileVelocityAttribute() {
        return new RangedAttribute("attribute." + Constants.MOD_ID + ".projectile_velocity", 1.0, 0.0, 1024.0).setSyncable(true);
    }

    public static Attribute createLoadSpeedAttribute() {
        return new RangedAttribute("attribute." + Constants.MOD_ID + ".load_speed", 1.0, 0.0, 1024.0).setSyncable(true);
    }

    public static Attribute createProjectileDamageAttribute() {
        return new RangedAttribute("attribute." + Constants.MOD_ID + ".projectile_damage", 1.0, 0.0, 1024.0).setSyncable(true);
    }

    public static Attribute createKnockbackAttribute() {
        return new RangedAttribute("attribute." + Constants.MOD_ID + ".knockback", 1.0, 0.0, 1024.0).setSyncable(true);
    }
}
