/*
 * This file is part of the Marisa Optimize distribution (https://github.com/TheBlueSkyARL/marisa-optimize).
 * Copyright (c) Meteor Development.
 */

package com.hongminh54.marisaoptimize.mixin.sodium;

import com.mojang.blaze3d.vertex.VertexFormat;
import com.mojang.blaze3d.vertex.VertexFormatElement;
import com.hongminh54.marisaoptimize.utils.render.MeshBuilderVertexConsumerProvider;
import net.caffeinemc.mods.sodium.api.vertex.buffer.VertexBufferWriter;
import com.mojang.blaze3d.vertex.VertexConsumer;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(value = MeshBuilderVertexConsumerProvider.MeshBuilderVertexConsumer.class, remap = false)
public abstract class MeshVertexConsumerMixin implements VertexConsumer, VertexBufferWriter {
    @Override
    public void push(MemoryStack stack, long ptr, int count, VertexFormat format) {
        VertexFormatElement position = format.getElement("Position");
        if (position == null) return;
        int positionOffset = position.offset();

        for (int i = 0; i < count; i++) {
            long positionPtr = ptr + (long) format.getVertexSize() * i + positionOffset;

            float x = MemoryUtil.memGetFloat(positionPtr);
            float y = MemoryUtil.memGetFloat(positionPtr + 4);
            float z = MemoryUtil.memGetFloat(positionPtr + 8);

            addVertex(x, y, z);
        }
    }
}
