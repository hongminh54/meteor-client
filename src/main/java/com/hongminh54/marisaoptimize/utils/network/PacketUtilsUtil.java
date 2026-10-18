/*
 * This file is part of the Marisa Optimize distribution (https://github.com/TheBlueSkyARL/marisa-optimize).
 * Copyright (c) Meteor Development.
 */
package com.hongminh54.marisaoptimize.utils.network;

/** Prints the live protocol registry; packet source generation is no longer necessary. */
public final class PacketUtilsUtil {
    private PacketUtilsUtil() {}

    public static void main(String[] args) {
        init();
    }

    public static void init() {
        PacketUtils.PACKETS.stream().map(PacketUtils::getName).sorted().forEach(System.out::println);
    }
}
