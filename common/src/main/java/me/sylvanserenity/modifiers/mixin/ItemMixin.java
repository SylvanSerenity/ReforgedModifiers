package me.sylvanserenity.modifiers.mixin;

import me.sylvanserenity.modifiers.modifier.ModifierHandler;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

// Hooks the vanilla per-item crafting callback directly rather than relying on a loader-specific
// event (e.g. NeoForge's PlayerEvent.ItemCraftedEvent doesn't reliably fire for the shift-click
// quick-move bulk-craft path) - onCraftedBy is called by ResultSlot#checkTakeAchievements for every
// way of taking a crafted result on both loaders, and no vanilla Item subclass overrides it, so a
// single injection here covers every item and every crafting interaction uniformly.
@Mixin(Item.class)
public class ItemMixin {
    @Inject(method = "onCraftedBy", at = @At("TAIL"))
    private void modifiers$onCraftedBy(ItemStack stack, Level level, Player player, CallbackInfo ci) {
        if (!level.isClientSide) {
            ModifierHandler.applyRandom(stack, player.getRandom());
        }
    }
}
