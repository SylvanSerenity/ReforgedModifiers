package me.sylvanserenity.modifiers.modifier;

import java.util.EnumMap;
import java.util.List;
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
    private static final Map<Tier, List<String>> TIER_DEFAULTS = new EnumMap<>(Map.ofEntries(
        Map.entry(Tier.TIER_1, List.of("#c:ingots/iron", "#c:ingots/gold", "#c:ingots/copper")),
        Map.entry(Tier.TIER_2, List.of("minecraft:trident", "artifacts:plastic_drinking_hat", "artifacts:novelty_drinking_hat")),
        Map.entry(Tier.TIER_3, List.of("#c:gems/diamond", "minecraft:mace")),
        Map.entry(Tier.TIER_4, List.of("#c:ingots/netherite"))
    ));

    /* LOADED CONFIG VARIABLES */
    @Comment("Whether armor pieces count as accessories and can receive accessory modifiers.")
    public boolean armorCountsAsAccessory = false;

    @Comment("""
        Item tiers, used to color modifier names.
        Each entry is a list of item ids (e.g. "minecraft:trident") or tags (e.g. "#c:ingots/iron").
    """)
    public Map<Tier, List<String>> tiers = defaultTiers();

    public static void register() {
        AutoConfig.register(ModifiersConfig.class, JanksonConfigSerializer::new);
    }

    public static boolean armorCountsAsAccessory() {
        return AutoConfig.getConfigHolder(ModifiersConfig.class).getConfig().armorCountsAsAccessory;
    }

    // Merges per-tier: a tier missing from the saved config falls back to its own default,
    // rather than the whole file overriding every tier at once.
    public static Map<Tier, List<String>> tiers() {
        Map<Tier, List<String>> saved = AutoConfig.getConfigHolder(ModifiersConfig.class).getConfig().tiers;
        Map<Tier, List<String>> result = new EnumMap<>(Tier.class);
        for (Tier tier : Tier.values()) {
            result.put(tier, saved.getOrDefault(tier, TIER_DEFAULTS.getOrDefault(tier, List.of())));
        }
        return result;
    }

    private static Map<Tier, List<String>> defaultTiers() {
        Map<Tier, List<String>> result = new EnumMap<>(Tier.class);
        for (Tier tier : Tier.values()) {
            result.put(tier, TIER_DEFAULTS.getOrDefault(tier, List.of()));
        }
        return result;
    }
}
