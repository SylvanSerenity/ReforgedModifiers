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

@Mixin(Item.class)
public class ItemMixin {
    @Inject(method = "onCraftedBy", at = @At("TAIL"))
    private void modifiers$onCraftedBy(ItemStack stack, Level level, Player player, CallbackInfo ci) {
        if (!level.isClientSide) {
            ModifierHandler.applyRandom(stack, player.getRandom());
        }
    }
}
