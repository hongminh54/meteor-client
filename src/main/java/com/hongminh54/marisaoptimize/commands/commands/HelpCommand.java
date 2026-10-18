/*
 * This file is part of the Marisa Optimize distribution (https://github.com/TheBlueSkyARL/marisa-optimize).
 * Copyright (c) Meteor Development.
 */

package com.hongminh54.marisaoptimize.commands.commands;

import com.hongminh54.marisaoptimize.commands.Command;
import com.hongminh54.marisaoptimize.commands.Commands;
import com.hongminh54.marisaoptimize.commands.arguments.CommandArgumentType;
import com.hongminh54.marisaoptimize.utils.player.ChatUtils;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.tree.CommandNode;
import net.minecraft.client.multiplayer.ClientSuggestionProvider;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Component;
import net.minecraft.ChatFormatting;

import java.util.Map;

public class HelpCommand extends Command {
    public HelpCommand() {
        super("help", "Shows you what a command does.");
    }

    @Override
    public void build(LiteralArgumentBuilder<ClientSuggestionProvider> builder) {
        builder.then(argument("command", CommandArgumentType.create()).executes(context -> {
            showHelp(CommandArgumentType.get(context));
            return SINGLE_SUCCESS;
        }));

        builder.executes(context -> {
            showHelp(this);
            return SINGLE_SUCCESS;
        });
    }

    private void showHelp(Command cmd) {
        MutableComponent msg = Component.literal("");
        msg.append(Component.literal("Help for ").withStyle(ChatFormatting.GRAY).append(Component.literal(cmd.getName()).withStyle(ChatFormatting.YELLOW)));
        msg.append(Component.literal("\n ")).append(Component.literal("Description: ").withStyle(ChatFormatting.GRAY).append(Component.literal(cmd.getDescription()).withStyle(ChatFormatting.WHITE)));

        if (!cmd.getAliases().isEmpty()) {
            msg.append(Component.literal("\n ")).append(Component.literal("Aliases: ").withStyle(ChatFormatting.GRAY));
            msg.append(Component.literal(String.join(", ", cmd.getAliases())).withStyle(ChatFormatting.AQUA));
        }

        msg.append(getUsageText(cmd));
        ChatUtils.sendMsg(msg);
    }

    private MutableComponent getUsageText(Command cmd) {
        ClientSuggestionProvider source = mc.getConnection().getSuggestionsProvider();
        CommandNode<ClientSuggestionProvider> root = Commands.DISPATCHER.getRoot();
        CommandNode<ClientSuggestionProvider> node = root.getChild(cmd.getName());

        MutableComponent usagesText = Component.literal("");

        if (node != null) {
            Map<CommandNode<ClientSuggestionProvider>, String> usages = Commands.DISPATCHER.getSmartUsage(node, source);

            for (String usage : usages.values()) {
                usagesText.append(Component.literal("\n " + cmd + " ").withStyle(ChatFormatting.GREEN)).append(Component.literal(usage).withStyle(ChatFormatting.GREEN));
            }
        }

        if (usagesText.getString().isEmpty()) {
            usagesText.append(Component.literal("\n " + cmd).withStyle(ChatFormatting.GREEN));
        }

        return Component.literal("\n Usage:").withStyle(ChatFormatting.GRAY).append(usagesText);
    }
}
