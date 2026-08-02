package me.sylvanserenity.modifiers.modifier;

import java.util.EnumMap;
import java.util.LinkedHashMap;
import java.util.Map;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;
import me.sylvanserenity.modifiers.Constants;

@Config(name = Constants.MOD_ID)
public class ModifiersConfig implements ConfigData {
    // Each value is either an item id (e.g. "minecraft:trident") or a tag (e.g. "#c:ingots/iron").
    private static final Map<Tier, String[]> TIER_DEFAULTS = new EnumMap<>(Map.ofEntries(
        Map.entry(Tier.TIER_1, new String[] {
            "#c:ingots/iron",
            "#c:ingots/gold",
            "#c:ingots/copper",
            "#c:tools/bow",
            "#c:tools/crossbow",
            "#artifacts:artifacts",
        }),
        Map.entry(Tier.TIER_2, new String[] {
            "minecraft:trident",
            "artifacts:vampiric_glove",
            "artifacts:chorus_totum",
            "artifacts:lucky_scarf",
            "artifacts:scarf_of_invisibility",
            "artifacts:fire_gauntlet",
            "artifacts:power_glove",
            "artifacts:panic_necklace",
            "artifacts:shock_pendant",
            "artifacts:flame_pendant",
            "artifacts:feral_claws",
            "artifacts:running_shoes",
        }),
        Map.entry(Tier.TIER_3, new String[] {
            "#c:gems/diamond",
            "minecraft:mace",
            "artifacts:cross_necklace",
            "artifacts:withered_bracelet",
            "artifacts:warp_drive",
        }),
        Map.entry(Tier.TIER_4, new String[] {
            "#c:ingots/netherite",
        })
    ));

    // Percentage points (0-100), matching ModAttributes.CRITICAL_CHANCE's scale.
    private static final Map<String, Double> CRITICAL_CHANCE_DEFAULTS = Map.ofEntries(
        Map.entry("minecraft:trident", 6.0),
        Map.entry("minecraft:mace", 10.0)
    );

    /* LOADED CONFIG VARIABLES */
    @Comment("Whether armor pieces count as accessories and can receive accessory modifiers.")
    public boolean armorCountsAsAccessory = false;

    @Comment("Whether vanilla's jump-attack critical hit is disabled in favor of the critical strike chance attribute.")
    public boolean disableVanillaCritical = true;

    @Comment("Damage multiplier applied on a critical hit. Default 2.0 (double damage).")
    public double criticalDamageMultiplier = 2.0;

    @Comment("Extra knockback applied on a critical hit. Default 0.4 (+40% knockback).")
    public double criticalKnockbackMultiplier = 0.4;

    @Comment("Whether an axe counts as a weapon, allowing melee modifiers but disabling mining speed bonuses.")
    public boolean axeIsWeapon = false;

    @Comment("""
    Knockback strength of a +100% KNOCKBACK attribute.
    Added on top of Punch's knockback.
    Default 0.5; vanilla Punch I contributes 1.0 for reference.
    """)
    public double baseRangedKnockback = 0.5;

    @Comment("""
    Item tiers used to color modifier names.
    Each entry is a list of item ids (e.g. "minecraft:trident") or tags (e.g. "#c:ingots/iron").
    Entries match items and repair ingredients.
    """)
    public Map<Tier, String[]> tiers = defaultTiers();

    @Comment("Base critical strike chance for weapons not listed in criticalChance below, in percentage points. Default 8.0 (8%).")
    public double defaultCriticalChance = 8.0;

    @Comment("""
    Base critical strike chance by weapon, in percentage points (8.0 = 8%), overriding defaultCriticalChance.
    Each key is an item id (e.g. "minecraft:trident") or a tag (e.g. "#minecraft:swords").
    """)
    public Map<String, Double> criticalChance = new LinkedHashMap<>(CRITICAL_CHANCE_DEFAULTS);

    public static void register() {
        AutoConfig.register(ModifiersConfig.class, JanksonConfigSerializer::new);
    }

    /* CONFIG GETTERS */
    public static boolean armorCountsAsAccessory() {
        return AutoConfig.getConfigHolder(ModifiersConfig.class).getConfig().armorCountsAsAccessory;
    }

    public static boolean disableVanillaCritical() {
        return AutoConfig.getConfigHolder(ModifiersConfig.class).getConfig().disableVanillaCritical;
    }

    public static double criticalDamageMultiplier() {
        return AutoConfig.getConfigHolder(ModifiersConfig.class).getConfig().criticalDamageMultiplier;
    }

    public static double criticalKnockbackMultiplier() {
        return AutoConfig.getConfigHolder(ModifiersConfig.class).getConfig().criticalKnockbackMultiplier;
    }

    public static boolean axeIsWeapon() {
        return AutoConfig.getConfigHolder(ModifiersConfig.class).getConfig().axeIsWeapon;
    }

    public static double baseRangedKnockback() {
        return AutoConfig.getConfigHolder(ModifiersConfig.class).getConfig().baseRangedKnockback;
    }

    public static double defaultCriticalChance() {
        return AutoConfig.getConfigHolder(ModifiersConfig.class).getConfig().defaultCriticalChance;
    }

    public static Map<String, Double> criticalChance() {
        return AutoConfig.getConfigHolder(ModifiersConfig.class).getConfig().criticalChance;
    }

    // Merges per-tier: a tier missing from the saved config falls back to its own default,
    // rather than the whole file overriding every tier at once.
    public static Map<Tier, String[]> tiers() {
        Map<Tier, String[]> saved = AutoConfig.getConfigHolder(ModifiersConfig.class).getConfig().tiers;
        Map<Tier, String[]> result = new EnumMap<>(Tier.class);
        for (Tier tier : Tier.values()) {
            result.put(tier, saved.getOrDefault(tier, TIER_DEFAULTS.getOrDefault(tier, new String[0])));
        }
        return result;
    }

    private static Map<Tier, String[]> defaultTiers() {
        Map<Tier, String[]> result = new EnumMap<>(Tier.class);
        for (Tier tier : Tier.values()) {
            result.put(tier, TIER_DEFAULTS.getOrDefault(tier, new String[0]));
        }
        return result;
    }
}
