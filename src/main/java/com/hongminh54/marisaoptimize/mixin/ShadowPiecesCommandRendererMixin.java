/*
 * This file is part of the Marisa Optimize distribution (https://github.com/TheBlueSkyARL/marisa-optimize).
 * Copyright (c) Meteor Development.
 */

package com.hongminh54.marisaoptimize.mixin;

import net.minecraft.client.renderer.feature.FeatureFrameContext;
import net.minecraft.client.renderer.feature.ShadowFeatureRenderer;
import java.util.List;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ShadowFeatureRenderer.class)
public abstract class ShadowPiecesCommandRendererMixin {
    @Inject(method = "buildGroup", at = @At("HEAD"), cancellable = true)
    private void meteor$onRender(FeatureFrameContext context, List<ShadowFeatureRenderer.Submit> shadows, CallbackInfo info) {
        if (shadows.isEmpty()) {
            info.cancel();
        }
    }
}
