/*
 * This file is part of the Marisa Optimize distribution (https://github.com/TheBlueSkyARL/marisa-optimize).
 * Copyright (c) Meteor Development.
 */

package com.hongminh54.marisaoptimize.utils.render;

import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.rendertype.RenderType;

public interface IVertexConsumerProvider {
    VertexConsumer getBuffer(RenderType layer);
    void setOffset(int offsetX, int offsetY, int offsetZ);
}
