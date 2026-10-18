/*
 * This file is part of the Marisa Optimize distribution (https://github.com/TheBlueSkyARL/marisa-optimize).
 * Copyright (c) Meteor Development.
 */

package com.hongminh54.marisaoptimize.commands.commands;

import com.hongminh54.marisaoptimize.commands.Command;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.minecraft.client.multiplayer.ClientSuggestionProvider;
import net.minecraft.network.protocol.common.ClientboundDisconnectPacket;
import net.minecraft.network.chat.Component;
import net.minecraft.ChatFormatting;

public class DisconnectCommand extends Command {
    public DisconnectCommand() {
        super("disconnect", "Disconnect from the server", "dc");
    }

    @Override
    public void build(LiteralArgumentBuilder<ClientSuggestionProvider> builder) {
        builder.executes(context -> {
            mc.player.connection.handleDisconnect(new ClientboundDisconnectPacket(Component.literal("%s[%sDisconnectCommand%s] Disconnected by user.".formatted(ChatFormatting.GRAY, ChatFormatting.BLUE, ChatFormatting.GRAY))));
            return SINGLE_SUCCESS;
        });

        builder.then(argument("reason", StringArgumentType.greedyString()).executes(context -> {
            mc.player.connection.handleDisconnect(new ClientboundDisconnectPacket(Component.literal(StringArgumentType.getString(context, "reason"))));
            return SINGLE_SUCCESS;
        }));
    }
}
