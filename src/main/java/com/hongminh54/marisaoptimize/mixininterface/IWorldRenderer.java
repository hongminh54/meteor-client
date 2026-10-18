/*
 * This file is part of the Marisa Optimize distribution (https://github.com/TheBlueSkyARL/marisa-optimize).
 * Copyright (c) Meteor Development.
 */

package com.hongminh54.marisaoptimize.mixininterface;

import com.mojang.blaze3d.pipeline.RenderTarget;

public interface IWorldRenderer {
    void meteor$pushEntityOutlineFramebuffer(RenderTarget framebuffer);

    void meteor$popEntityOutlineFramebuffer();
}
