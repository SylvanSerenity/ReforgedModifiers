package me.sylvanserenity.modifiers.command;

import java.util.List;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;

import me.sylvanserenity.modifiers.modifier.Modifier;
import me.sylvanserenity.modifiers.modifier.ModifierHandler;
import me.sylvanserenity.modifiers.modifier.Modifiers;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.SharedSuggestionProvider;
import net.minecraft.commands.arguments.ResourceLocationArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;

public class ModifierCommand {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(
            Commands.literal("modifier")
            .requires(src -> src.hasPermission(2))
            .then(
                Commands.literal("set").then(
                    Commands.argument("id", ResourceLocationArgument.id())
                    .suggests((ctx, builder) -> SharedSuggestionProvider.suggestResource(Modifiers.MODIFIERS.keySet(), builder))
                    .executes(ctx -> {
                        ServerPlayer player = ctx.getSource().getPlayerOrException();
                        ItemStack stack = player.getMainHandItem();
                        Modifier mod = Modifiers.MODIFIERS.get(ResourceLocationArgument.getId(ctx, "id"));
                        if (mod == null) throw new SimpleCommandExceptionType(Component.literal("Unknown modifier")).create();
                        if (!ModifierHandler.isApplicable(stack, mod)) {
                            throw new SimpleCommandExceptionType(Component.literal("This modifier cannot be applied to this item")).create();
                        }
                        ModifierHandler.apply(stack, mod);
                        return 1;
                    })
                )
            )
            .then(
                Commands.literal("random")
                .executes(ctx -> {
                    ServerPlayer player = ctx.getSource().getPlayerOrException();
                    ItemStack stack = player.getMainHandItem();
                    List<Modifier> pool = Modifiers.MODIFIERS.values().stream()
                        .filter(mod -> ModifierHandler.isApplicable(stack, mod))
                        .toList();
                    if (pool.isEmpty()) throw new SimpleCommandExceptionType(Component.literal("No modifiers apply to this item")).create();
                    Modifier mod = pool.get(player.getRandom().nextInt(pool.size()));
                    ModifierHandler.apply(stack, mod);
                    return 1;
                })
            )
        );
    }
}
