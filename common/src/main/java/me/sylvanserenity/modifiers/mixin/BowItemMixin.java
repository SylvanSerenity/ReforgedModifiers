package me.sylvanserenity.modifiers.mixin;

import me.sylvanserenity.modifiers.modifier.ModAttributes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(BowItem.class)
public abstract class BowItemMixin {
    @ModifyVariable(method = "releaseUsing", at = @At(value = "STORE", ordinal = 0), ordinal = 1)
    private int modifiers$loadSpeed(int elapsedTicks, ItemStack stack, Level level, LivingEntity shooter) {
        AttributeInstance attribute = shooter.getAttribute(ModAttributes.DRAW_SPEED);
        return attribute != null ? Math.round(elapsedTicks * (float) attribute.getValue()) : elapsedTicks;
    }
}
