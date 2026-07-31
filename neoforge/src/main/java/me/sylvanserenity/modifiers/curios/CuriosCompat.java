package me.sylvanserenity.modifiers.curios;

import me.sylvanserenity.modifiers.modifier.Modifier;
import me.sylvanserenity.modifiers.modifier.ModifierHandler;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.neoforged.bus.api.SubscribeEvent;
import top.theillusivec4.curios.api.event.CurioAttributeModifierEvent;

// Only ever touched when Curios is confirmed loaded - see Modifiers' guarded registration.
// Referencing this class at all (even just Class.class) would throw NoClassDefFoundError without Curios present.
public class CuriosCompat {
    @SubscribeEvent
    public static void onAttributeModifiers(CurioAttributeModifierEvent event) {
        Modifier mod = ModifierHandler.getAppliedModifier(event.getItemStack());
        if (mod == null) return;

        for (Modifier.Entry entry : mod.entries(Modifier.Category.ACCESSORY)) {
            ResourceLocation modId = mod.id().withSuffix("/" + entry.attr().unwrapKey().orElseThrow().location().getPath());
            event.addModifier(entry.attr(), new AttributeModifier(modId, entry.amount(), entry.op()));
        }
    }
}
