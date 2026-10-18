/*
 * This file is part of the Marisa Optimize distribution (https://github.com/TheBlueSkyARL/marisa-optimize).
 * Copyright (c) Meteor Development.
 */

package com.hongminh54.marisaoptimize.events.packets;

import net.minecraft.network.protocol.game.ClientboundContainerSetSlotPacket;

public class ContainerSlotUpdateEvent {
    private static final ContainerSlotUpdateEvent INSTANCE = new ContainerSlotUpdateEvent();

    public ClientboundContainerSetSlotPacket packet;

    public static ContainerSlotUpdateEvent get(ClientboundContainerSetSlotPacket packet) {
        INSTANCE.packet = packet;
        return INSTANCE;
    }
}
