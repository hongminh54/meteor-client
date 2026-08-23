/*
 * This file is part of the Marisa Optimize distribution (https://github.com/TheBlueSkyARL/marisa-optimize).
 * Copyright (c) Meteor Development.
 */

package com.hongminh54.marisaoptimize.utils.render;

import net.minecraft.client.render.OutlineVertexConsumerProvider;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;

public class NoopOutlineVertexConsumerProvider extends OutlineVertexConsumerProvider {
    public static final NoopOutlineVertexConsumerProvider INSTANCE = new NoopOutlineVertexConsumerProvider();

    private NoopOutlineVertexConsumerProvider() {
    }

    @Override
    public VertexConsumer getBuffer(RenderLayer layer) {
        return NoopVertexConsumer.INSTANCE;
    }

    @Override
    public void draw() {
    }
}
