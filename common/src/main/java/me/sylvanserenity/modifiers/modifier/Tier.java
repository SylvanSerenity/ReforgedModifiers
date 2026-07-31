package me.sylvanserenity.modifiers.modifier;

import net.minecraft.network.chat.TextColor;

public enum Tier {
    WORTHLESS   (TextColor.fromRgb(0x545454)), // Dark gray
    BAD         (TextColor.fromRgb(0x747474)), // Gray
    DEFAULT     (TextColor.fromRgb(0xFDFDFD)), // White
    TIER_1      (TextColor.fromRgb(0x9292F9)), // Blue
    TIER_2      (TextColor.fromRgb(0x92F992)), // Green
    TIER_3      (TextColor.fromRgb(0xE9B689)), // Orange
    TIER_4      (TextColor.fromRgb(0xFD9494)), // Light red
    TIER_5      (TextColor.fromRgb(0xF992F9)), // Pink
    TIER_6      (TextColor.fromRgb(0xBF92E9)), // Light purple
    TIER_7      (TextColor.fromRgb(0x89E909)), // Lime
    TIER_8      (TextColor.fromRgb(0xE9E909)), // Yellow
    TIER_9      (TextColor.fromRgb(0x038AB1)), // Cyan
    TIER_10     (TextColor.fromRgb(0xD52756)), // Red
    TIER_11     (TextColor.fromRgb(0xA52AEA)); // Purple

    public final TextColor color;
    Tier(TextColor color) { this.color = color; }
}
