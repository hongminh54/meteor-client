/*
 * This file is part of the Marisa Optimize distribution (https://github.com/TheBlueSkyARL/marisa-optimize).
 * Copyright (c) Meteor Development.
 */
package com.hongminh54.marisaoptimize.utils.render;

import net.minecraft.client.gui.render.GuiRenderer;
import net.minecraft.client.gui.render.pip.PictureInPictureRenderer;
import net.minecraft.client.renderer.feature.FeatureRenderDispatcher;
import net.minecraft.client.renderer.state.gui.GuiRenderState;

import java.util.List;

/** Separate GUI state and renderer for the Marisa overlay pass. */
public class MarisaMcGuiRenderer extends GuiRenderer {
    public MarisaMcGuiRenderer(GuiRenderState state, FeatureRenderDispatcher dispatcher, List<PictureInPictureRenderer<?>> renderers) {
        super(state, dispatcher, renderers);
    }
}
