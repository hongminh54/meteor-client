/*
 * This file is part of the Marisa Optimize distribution (https://github.com/TheBlueSkyARL/marisa-optimize).
 * Copyright (c) Meteor Development.
 */

package com.hongminh54.marisaoptimize.mixininterface;

import net.minecraft.client.gl.Framebuffer;

public interface IMinecraftClient {
    void meteor$rightClick();

    void meteor$setFramebuffer(Framebuffer framebuffer);
}
