/*
 * This file is part of the Marisa Optimize distribution (https://github.com/TheBlueSkyARL/marisa-optimize).
 * Copyright (c) Meteor Development.
 */
package com.hongminh54.marisaoptimize.mixin;

import com.mojang.blaze3d.systems.RenderSystem;
import com.hongminh54.marisaoptimize.MarisaOptimize;
import com.hongminh54.marisaoptimize.events.render.Render2DEvent;
import com.hongminh54.marisaoptimize.gui.WidgetScreen;
import com.hongminh54.marisaoptimize.systems.hud.screens.HudEditorScreen;
import com.hongminh54.marisaoptimize.utils.Utils;
import com.hongminh54.marisaoptimize.utils.render.MarisaMcGuiRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.render.GuiRenderer;
import net.minecraft.client.gui.render.pip.PictureInPictureRenderer;
import net.minecraft.client.renderer.feature.FeatureRenderDispatcher;
import net.minecraft.client.renderer.state.gui.GuiRenderState;
import net.minecraft.util.profiling.Profiler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(GuiRenderer.class)
public abstract class GuiRendererMixin {
    @Unique private GuiRenderState meteor$renderState;
    @Unique private MarisaMcGuiRenderer meteor$guiRenderer;

    @Inject(method = "<init>", at = @At("RETURN"))
    private void meteor$init(GuiRenderState state, FeatureRenderDispatcher dispatcher, List<PictureInPictureRenderer<?>> renderers, CallbackInfo ci) {
        if ((Object) this instanceof MarisaMcGuiRenderer) return;
        meteor$renderState = new GuiRenderState();
        meteor$guiRenderer = new MarisaMcGuiRenderer(meteor$renderState, dispatcher, renderers);
    }

    @Inject(method = "render", at = @At("HEAD"))
    private void meteor$beforeGui(CallbackInfo ci) {
        if ((Object) this instanceof MarisaMcGuiRenderer) return;
        Minecraft mc = Minecraft.getInstance();
        if (mc.gui.screen() != null && !(mc.gui.screen() instanceof WidgetScreen)) meteor$render2D(mc);
    }

    @Inject(method = "render", at = @At("TAIL"))
    private void meteor$afterGui(CallbackInfo ci) {
        if ((Object) this instanceof MarisaMcGuiRenderer) return;
        Minecraft mc = Minecraft.getInstance();
        try {
            RenderSystem.getDevice().createCommandEncoder().clearDepthTexture(mc.gameRenderer.mainRenderTarget().getDepthTexture(), 1.0);
            if (mc.gui.screen() == null || mc.gui.screen() instanceof WidgetScreen) meteor$render2D(mc);
        } finally {
            meteor$guiRenderer.endFrame();
        }
    }

    @Unique
    private void meteor$render2D(Minecraft mc) {
        int mouseX = (int) mc.mouseHandler.getScaledXPos(mc.getWindow());
        int mouseY = (int) mc.mouseHandler.getScaledYPos(mc.getWindow());
        if (Utils.canUpdate() || HudEditorScreen.isOpen()) {
            Profiler.get().push(MarisaOptimize.MOD_ID + "_render_2d");
            try {
                Utils.unscaledProjection();
                GuiGraphicsExtractor graphics = new GuiGraphicsExtractor(mc, meteor$renderState, mouseX, mouseY);
                float tickDelta = mc.getDeltaTracker().getGameTimeDeltaPartialTick(true);
                MarisaOptimize.EVENT_BUS.post(Render2DEvent.get(graphics, graphics.guiWidth(), graphics.guiHeight(), tickDelta));
                meteor$guiRenderer.render();
            } finally {
                try {
                    Utils.scaledProjection();
                } finally {
                    Profiler.get().pop();
                }
            }
        }
        if (mc.gui.screen() instanceof WidgetScreen screen) {
            GuiGraphicsExtractor graphics = new GuiGraphicsExtractor(mc, meteor$renderState, mouseX, mouseY);
            screen.renderCustom(graphics, mouseX, mouseY, mc.getDeltaTracker().getGameTimeDeltaTicks());
            meteor$guiRenderer.render();
        }
    }
}
