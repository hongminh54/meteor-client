/*
 * This file is part of the Marisa Optimize distribution (https://github.com/TheBlueSkyARL/marisa-optimize).
 * Copyright (c) Meteor Development.
 */

package com.hongminh54.marisaoptimize.events.packets;

import net.minecraft.network.protocol.game.ClientboundSoundPacket;

public class PlaySoundPacketEvent {

    private static final PlaySoundPacketEvent INSTANCE = new PlaySoundPacketEvent();

    public ClientboundSoundPacket packet;

    public static PlaySoundPacketEvent get(ClientboundSoundPacket packet) {
        INSTANCE.packet = packet;
        return INSTANCE;
    }
}
