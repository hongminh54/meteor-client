/*
 * This file is part of the Marisa Optimize distribution (https://github.com/TheBlueSkyARL/marisa-optimize).
 * Copyright (c) Meteor Development.
 */

package com.hongminh54.marisaoptimize.mixin.indigo;

import com.hongminh54.marisaoptimize.systems.modules.render.Xray;
import net.fabricmc.fabric.api.client.renderer.v1.mesh.MutableQuadView;
import net.fabricmc.fabric.impl.client.indigo.renderer.render.AltModelBlockRendererImpl;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.core.BlockPos;
import net.minecraft.client.renderer.chunk.ChunkSectionLayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AltModelBlockRendererImpl.class)
public abstract class AbstractTerrainRenderContextMixin {
    @Shadow private BlockState blockState;
    @Shadow private BlockPos pos;

    @Inject(method = "transform", at = @At("RETURN"), cancellable = true)
    private void onBufferQuad(MutableQuadView quad, CallbackInfoReturnable<Boolean> ci) {
        int alpha = Xray.getAlpha(blockState, pos);

        if (alpha == 0) ci.setReturnValue(false);
        else if (alpha != -1) {
            if (alpha > 0 && alpha < 255) quad.chunkLayer(ChunkSectionLayer.TRANSLUCENT);
            for (int i = 0; i < 4; i++) {
                quad.color(i, rewriteQuadAlpha(quad.color(i), alpha));
            }
        }
    }

    @Unique
    private int rewriteQuadAlpha(int color, int alpha) {
        return ((alpha & 0xFF) << 24) | (color & 0x00FFFFFF);
    }
}
