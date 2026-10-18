/*
 * This file is part of the Marisa Optimize distribution (https://github.com/TheBlueSkyARL/marisa-optimize).
 * Copyright (c) Meteor Development.
 */

package com.hongminh54.marisaoptimize.mixin;

import com.hongminh54.marisaoptimize.systems.modules.Modules;
import com.hongminh54.marisaoptimize.systems.modules.render.Xray;
import com.hongminh54.marisaoptimize.systems.modules.world.Ambience;
import net.minecraft.client.renderer.block.FluidRenderer;
import net.minecraft.client.renderer.chunk.ChunkSectionLayer;
import net.minecraft.world.level.material.FluidState;
import com.llamalad7.mixinextras.sugar.Local;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(FluidRenderer.class)
public class BlockRenderLayersMixin {
    // Block quad layers are handled by BlockModelRendererMixin in 26.2.
    @ModifyArg(method = "tesselate", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/block/FluidRenderer$Output;getBuilder(Lnet/minecraft/client/renderer/chunk/ChunkSectionLayer;)Lcom/mojang/blaze3d/vertex/VertexConsumer;"), index = 0)
    private ChunkSectionLayer onGetFluidLayer(ChunkSectionLayer layer, @Local(argsOnly = true) FluidState state) {
        if (Modules.get() == null) return layer;

        int alpha = Xray.getAlpha(state.createLegacyBlock(), null);
        if (alpha > 0 && alpha < 255) {
            return ChunkSectionLayer.TRANSLUCENT;
        }

        else {
            Ambience ambience = Modules.get().get(Ambience.class);
            int a = ambience.lavaColor.get().a;
            if (ambience.isActive() && ambience.customLavaColor.get() && a > 0 && a < 255) {
                return ChunkSectionLayer.TRANSLUCENT;
            }
        }
        return layer;
    }
}
