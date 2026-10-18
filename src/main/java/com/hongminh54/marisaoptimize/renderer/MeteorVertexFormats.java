/*
 * This file is part of the Marisa Optimize distribution (https://github.com/TheBlueSkyARL/marisa-optimize).
 * Copyright (c) Meteor Development.
 */

package com.hongminh54.marisaoptimize.renderer;

import com.mojang.blaze3d.vertex.VertexFormat;
import com.mojang.blaze3d.GpuFormat;

public abstract class MeteorVertexFormats {
    public static final VertexFormat POS2 = VertexFormat.builder(0)
        .addAttribute("Position", GpuFormat.RG32_FLOAT)
        .build();

    public static final VertexFormat POS2_COLOR = VertexFormat.builder(0)
        .addAttribute("Position", GpuFormat.RG32_FLOAT)
        .addAttribute("Color", GpuFormat.RGBA8_UNORM)
        .build();

    public static final VertexFormat POS2_TEXTURE_COLOR = VertexFormat.builder(0)
        .addAttribute("Position", GpuFormat.RG32_FLOAT)
        .addAttribute("Texture", GpuFormat.RG32_FLOAT)
        .addAttribute("Color", GpuFormat.RGBA8_UNORM)
        .build();

    private MeteorVertexFormats() {}
}
