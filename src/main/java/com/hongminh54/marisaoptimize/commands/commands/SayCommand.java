/*
 * This file is part of the Marisa Optimize distribution (https://github.com/TheBlueSkyARL/marisa-optimize).
 * Copyright (c) Meteor Development.
 */

package com.hongminh54.marisaoptimize.commands.commands;

import com.hongminh54.marisaoptimize.commands.Command;
import com.hongminh54.marisaoptimize.mixin.ClientPlayNetworkHandlerAccessor;
import com.hongminh54.marisaoptimize.utils.misc.MeteorStarscript;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.client.multiplayer.ClientSuggestionProvider;
import net.minecraft.util.Crypt;
import net.minecraft.network.chat.LastSeenMessagesTracker;
import net.minecraft.network.chat.SignedMessageBody;
import net.minecraft.network.chat.MessageSignature;
import net.minecraft.network.protocol.game.ServerboundChatPacket;
import org.meteordev.starscript.Script;

import java.time.Instant;

public class SayCommand extends Command {
    public SayCommand() {
        super("say", "Sends messages in chat.");
    }

    @Override
    public void build(LiteralArgumentBuilder<ClientSuggestionProvider> builder) {
        builder.then(argument("message", StringArgumentType.greedyString()).executes(context -> {
            String msg = context.getArgument("message", String.class);
            Script script = MeteorStarscript.compile(msg);

            if (script != null) {
                String message = MeteorStarscript.run(script);

                if (message != null) {
                    Instant instant = Instant.now();
                    long l = Crypt.SaltSupplier.getLong();
                    ClientPacketListener handler = mc.getConnection();
                    LastSeenMessagesTracker.Update lastSeenMessages = ((ClientPlayNetworkHandlerAccessor) handler).meteor$getLastSeenMessagesCollector().generateAndApplyUpdate();
                    MessageSignature messageSignatureData = ((ClientPlayNetworkHandlerAccessor) handler).meteor$getMessagePacker().pack(new SignedMessageBody(message, instant, l, lastSeenMessages.lastSeen()));
                    handler.send(new ServerboundChatPacket(message, instant, l, messageSignatureData, lastSeenMessages.update()));
                }
            }

            return SINGLE_SUCCESS;
        }));
    }
}
