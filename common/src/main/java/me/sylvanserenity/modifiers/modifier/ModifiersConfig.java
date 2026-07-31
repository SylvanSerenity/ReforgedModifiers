package me.sylvanserenity.modifiers.modifier;

import java.util.EnumMap;
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

    /* LOADED CONFIG VARIABLES */
    @Comment("Whether armor pieces count as accessories and can receive accessory modifiers.")
    public boolean armorCountsAsAccessory = false;

    @Comment("""
    Item tiers used to color modifier names.
    Each entry is a list of item ids (e.g. "minecraft:trident") or tags (e.g. "#c:ingots/iron").
    Entries match items and repair ingredients.
    """)
    public Map<Tier, String[]> tiers = defaultTiers();

    public static void register() {
        AutoConfig.register(ModifiersConfig.class, JanksonConfigSerializer::new);
    }

    public static boolean armorCountsAsAccessory() {
        return AutoConfig.getConfigHolder(ModifiersConfig.class).getConfig().armorCountsAsAccessory;
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
