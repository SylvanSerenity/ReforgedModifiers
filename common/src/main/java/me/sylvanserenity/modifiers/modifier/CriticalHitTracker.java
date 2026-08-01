package me.sylvanserenity.modifiers.modifier;

import java.util.HashSet;
import java.util.Set;

// The knockback bonus needs a way to know whether an attack was critical.
public class CriticalHitTracker {
    private static final Set<Integer> PENDING = new HashSet<>();

    public static void markCritical(int targetEntityId) {
        PENDING.add(targetEntityId);
    }

    public static boolean consumeCritical(int targetEntityId) {
        return PENDING.remove(targetEntityId);
    }
}
