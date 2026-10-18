/*
 * This file is part of the Marisa Optimize distribution (https://github.com/TheBlueSkyARL/marisa-optimize).
 * Copyright (c) Meteor Development.
 */

package com.hongminh54.marisaoptimize.events.packets;

import net.minecraft.network.protocol.game.ClientboundContainerSetContentPacket;

public class InventoryEvent {
    private static final InventoryEvent INSTANCE = new InventoryEvent();

    public ClientboundContainerSetContentPacket packet;

    public static InventoryEvent get(ClientboundContainerSetContentPacket packet) {
        INSTANCE.packet = packet;
        return INSTANCE;
    }
}
