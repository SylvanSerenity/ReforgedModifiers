package me.sylvanserenity.modifiers.modifier;

import java.util.HashMap;
import java.util.Map;

public class KnockbackTracker {
    private static final Map<Integer, Float> PENDING = new HashMap<>();

    public static void markKnockback(int targetEntityId, float multiplier) {
        PENDING.put(targetEntityId, multiplier);
    }

    public static float consumeKnockback(int targetEntityId) {
        Float multiplier = PENDING.remove(targetEntityId);
        return multiplier != null ? multiplier : 1.0F;
    }
}
