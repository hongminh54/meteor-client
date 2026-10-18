/*
 * This file is part of the Marisa Optimize distribution (https://github.com/TheBlueSkyARL/marisa-optimize).
 * Copyright (c) Meteor Development.
 */

package com.hongminh54.marisaoptimize.utils.render.postprocess;

import com.mojang.blaze3d.pipeline.RenderPipeline;
import com.hongminh54.marisaoptimize.mixininterface.IWorldRenderer;
import net.minecraft.world.entity.Entity;

import static com.hongminh54.marisaoptimize.MarisaOptimize.mc;

public abstract class EntityShader extends PostProcessShader {
    protected EntityShader(RenderPipeline pipeline) {
        super(pipeline);
    }

    public abstract boolean shouldDraw(Entity entity);

    @Override
    protected void preDraw() {
        ((IWorldRenderer) mc.levelRenderer).meteor$pushEntityOutlineFramebuffer(framebuffer);
    }

    @Override
    protected void postDraw() {
        ((IWorldRenderer) mc.levelRenderer).meteor$popEntityOutlineFramebuffer();
    }

    public void submitVertices() {
        // Entity outlines are executed by FeatureRenderDispatcher during submission.
    }
}
