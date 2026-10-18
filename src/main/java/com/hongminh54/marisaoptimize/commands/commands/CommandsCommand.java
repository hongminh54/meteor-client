/*
 * This file is part of the Marisa Optimize distribution (https://github.com/TheBlueSkyARL/marisa-optimize).
 * Copyright (c) Meteor Development.
 */

package com.hongminh54.marisaoptimize.commands.commands;

import com.hongminh54.marisaoptimize.commands.Command;
import com.hongminh54.marisaoptimize.commands.Commands;
import com.hongminh54.marisaoptimize.systems.config.Config;
import com.hongminh54.marisaoptimize.utils.Utils;
import com.hongminh54.marisaoptimize.utils.player.ChatUtils;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.minecraft.client.multiplayer.ClientSuggestionProvider;
import net.minecraft.network.chat.ClickEvent;
import net.minecraft.network.chat.HoverEvent;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Component;
import net.minecraft.ChatFormatting;

public class CommandsCommand extends Command {
    public CommandsCommand() {
        super("commands", "List of all commands.", "help");
    }

    @Override
    public void build(LiteralArgumentBuilder<ClientSuggestionProvider> builder) {
        builder.executes(context -> {
            ChatUtils.info("--- Commands ((highlight)%d(default)) ---", Commands.COMMANDS.size());

            MutableComponent commands = Component.literal("");
            Commands.COMMANDS.forEach(command -> commands.append(getCommandText(command)));
            ChatUtils.sendMsg(commands);

            return SINGLE_SUCCESS;
        });
    }

    private MutableComponent getCommandText(Command command) {
        // Hover tooltip
        MutableComponent tooltip = Component.literal("");

        tooltip.append(Component.literal(Utils.nameToTitle(command.getName())).withStyle(ChatFormatting.BLUE, ChatFormatting.BOLD)).append("\n");

        MutableComponent aliases = Component.literal(Config.get().prefix.get() + command.getName());
        if (!command.getAliases().isEmpty()) {
            aliases.append(", ");
            for (String alias : command.getAliases()) {
                if (alias.isEmpty()) continue;
                aliases.append(Config.get().prefix.get() + alias);
                if (!alias.equals(command.getAliases().getLast())) aliases.append(", ");
            }
        }
        tooltip.append(aliases.withStyle(ChatFormatting.GRAY)).append("\n\n");

        tooltip.append(Component.literal(command.getDescription()).withStyle(ChatFormatting.WHITE));

        // Text
        MutableComponent text = Component.literal(Utils.nameToTitle(command.getName()));
        if (command != Commands.COMMANDS.getLast())
            text.append(Component.literal(", ").withStyle(ChatFormatting.GRAY));
        text.setStyle(text
            .getStyle()
            .withHoverEvent(new HoverEvent.ShowText(tooltip))
            .withClickEvent(new ClickEvent.SuggestCommand(Config.get().prefix.get() + command.getName()))
        );

        return text;
    }

}
