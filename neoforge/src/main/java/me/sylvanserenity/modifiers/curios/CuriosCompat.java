package me.sylvanserenity.modifiers.curios;

import me.sylvanserenity.modifiers.modifier.Modifier;
import me.sylvanserenity.modifiers.modifier.ModifierHandler;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import top.theillusivec4.curios.api.event.CurioAttributeModifierEvent;

public class CuriosCompat {
    @SubscribeEvent
    public static void onAttributeModifiers(CurioAttributeModifierEvent event) {
        ItemStack stack = event.getItemStack();
        Modifier mod = ModifierHandler.getAppliedModifier(stack);
        if (mod == null) return;

        // Reactive, so its ids must match what apply()/remove() already stored on this stack.
        // Otherwise two stacks bearing the same modifier would collide under the same
        // AttributeModifier id and only one would ever contribute.
        for (Modifier.Entry entry : mod.entries(Modifier.Category.ACCESSORY)) {
            ResourceLocation modId = ModifierHandler.buildAttributeModifierId(stack, mod, entry.attr());
            event.addModifier(entry.attr(), new AttributeModifier(modId, entry.amount(), entry.op()));
        }
    }
}
