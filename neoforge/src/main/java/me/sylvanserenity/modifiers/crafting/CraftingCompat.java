package me.sylvanserenity.modifiers.crafting;

import me.sylvanserenity.modifiers.modifier.ModifierHandler;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;

public class CraftingCompat {
    @SubscribeEvent
    public static void onItemCrafted(PlayerEvent.ItemCraftedEvent event) {
        ItemStack stack = event.getCrafting();
        Player player = event.getEntity();
        if (!player.level().isClientSide) {
            ModifierHandler.applyRandom(stack, player.getRandom());
        }
    }
}
