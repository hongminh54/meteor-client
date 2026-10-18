/*
 * This file is part of the Marisa Optimize distribution (https://github.com/TheBlueSkyARL/marisa-optimize).
 * Copyright (c) Meteor Development.
 */

package com.hongminh54.marisaoptimize.mixininterface;

import com.mojang.authlib.GameProfile;
import net.minecraft.client.multiplayer.chat.GuiMessageTag;
import net.minecraft.network.chat.MessageSignature;
import net.minecraft.network.chat.Component;

public interface IMessageHandler {
    /** Only valid inside of {@link net.minecraft.client.gui.components.ChatComponent#addMessage(Component, MessageSignature, GuiMessageTag)} call */
    GameProfile meteor$getSender();
}
