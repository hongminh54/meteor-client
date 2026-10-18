/*
 * This file is part of the Marisa Optimize distribution (https://github.com/TheBlueSkyARL/marisa-optimize).
 * Copyright (c) Meteor Development.
 */
package com.hongminh54.marisaoptimize.utils.network;

import com.google.common.collect.Sets;
import net.minecraft.network.ProtocolInfo;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketFlow;
import net.minecraft.network.protocol.PacketType;
import net.minecraft.network.protocol.configuration.ConfigurationProtocols;
import net.minecraft.network.protocol.game.GameProtocols;
import net.minecraft.network.protocol.handshake.HandshakeProtocols;
import net.minecraft.network.protocol.login.LoginProtocols;
import net.minecraft.network.protocol.status.StatusProtocols;
import net.minecraft.resources.Identifier;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

public class PacketUtils {
    private static final Map<Identifier, PacketType<? extends Packet<?>>> S2C_PACKETS = new HashMap<>();
    private static final Map<Identifier, PacketType<? extends Packet<?>>> C2S_PACKETS = new HashMap<>();

    static {
        Stream.of(StatusProtocols.CLIENTBOUND_TEMPLATE, LoginProtocols.CLIENTBOUND_TEMPLATE,
            ConfigurationProtocols.CLIENTBOUND_TEMPLATE, GameProtocols.CLIENTBOUND_TEMPLATE)
            .map(ProtocolInfo.DetailsProvider::details)
            .forEach(details -> details.listPackets((type, index) -> S2C_PACKETS.put(type.id(), type)));
        Stream.of(HandshakeProtocols.SERVERBOUND_TEMPLATE, StatusProtocols.SERVERBOUND_TEMPLATE,
            LoginProtocols.SERVERBOUND_TEMPLATE, ConfigurationProtocols.SERVERBOUND_TEMPLATE,
            GameProtocols.SERVERBOUND_TEMPLATE)
            .map(ProtocolInfo.DetailsProvider::details)
            .forEach(details -> details.listPackets((type, index) -> C2S_PACKETS.put(type.id(), type)));
    }

    private static final Set<PacketType<? extends Packet<?>>> S2C = Set.copyOf(S2C_PACKETS.values());
    private static final Set<PacketType<? extends Packet<?>>> C2S = Set.copyOf(C2S_PACKETS.values());
    public static final Set<PacketType<? extends Packet<?>>> PACKETS = Sets.union(S2C, C2S);

    public static String getName(PacketType<? extends Packet<?>> type) {
        return (type.flow() == PacketFlow.CLIENTBOUND ? "clientbound/" : "serverbound/") + type.id();
    }

    public static PacketType<? extends Packet<?>> getPacket(String name) {
        if (name.startsWith("clientbound/")) return lookup(S2C_PACKETS, name.substring(12));
        if (name.startsWith("serverbound/")) return lookup(C2S_PACKETS, name.substring(12));
        PacketType<? extends Packet<?>> type = lookup(S2C_PACKETS, name);
        if (type == null) type = lookup(C2S_PACKETS, name);
        return type != null ? type : LegacyPacketNames.ALIASES.get(name);
    }

    private static PacketType<? extends Packet<?>> lookup(Map<Identifier, PacketType<? extends Packet<?>>> packets, String name) {
        Identifier id = Identifier.tryParse(name);
        return id == null ? null : packets.get(id);
    }

    public static Set<PacketType<? extends Packet<?>>> getS2CPackets() {
        return S2C;
    }

    public static Set<PacketType<? extends Packet<?>>> getC2SPackets() {
        return C2S;
    }

    private PacketUtils() {}
}
