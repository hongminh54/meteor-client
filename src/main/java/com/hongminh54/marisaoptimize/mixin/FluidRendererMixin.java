/*
 * This file is part of the Marisa Optimize distribution (https://github.com/TheBlueSkyARL/marisa-optimize).
 * Copyright (c) Meteor Development.
 */

package com.hongminh54.marisaoptimize.mixin;

import com.hongminh54.marisaoptimize.systems.modules.Modules;
import com.hongminh54.marisaoptimize.systems.modules.render.Xray;
import com.hongminh54.marisaoptimize.systems.modules.world.Ambience;
import com.hongminh54.marisaoptimize.utils.render.color.Color;
import net.minecraft.world.level.block.state.BlockState;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.block.FluidRenderer;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.tags.FluidTags;
import net.minecraft.core.BlockPos;
import net.minecraft.client.renderer.block.BlockAndTintGetter;
import net.minecraft.util.ARGB;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(FluidRenderer.class)
public abstract class FluidRendererMixin {
    @Unique private final ThreadLocal<Integer> alphas = ThreadLocal.withInitial(() -> -1);
    @Unique private final ThreadLocal<Boolean> ambient = ThreadLocal.withInitial(() -> false);

    @Inject(method = "tesselate", at = @At("HEAD"), cancellable = true)
    private void onRender(BlockAndTintGetter world, BlockPos pos, FluidRenderer.Output output, BlockState blockState, FluidState fluidState, CallbackInfo info) {
        Ambience ambience = Modules.get().get(Ambience.class);
        ambient.set(ambience.isActive() && ambience.customLavaColor.get() && fluidState.is(FluidTags.LAVA));

        // Xray and Wallhack
        int alpha = Xray.getAlpha(fluidState.createLegacyBlock(), pos);

        if (alpha == 0) info.cancel();
        else alphas.set(alpha);
    }

    @Inject(method = "vertex", at = @At("HEAD"), cancellable = true)
    private void onVertex(VertexConsumer vertexConsumer, float x, float y, float z, int packedColor, float u, float v, int light, CallbackInfo info) {
        int alpha = alphas.get();

        if (ambient.get()) {
            Color color = Modules.get().get(Ambience.class).lavaColor.get();
            vertex(vertexConsumer, x, y, z, color.r, color.g, color.b, (alpha != -1 ? alpha : color.a), u, v, light);
            info.cancel();
        }
        else if (alpha != -1) {
            vertex(vertexConsumer, x, y, z, ARGB.red(packedColor), ARGB.green(packedColor), ARGB.blue(packedColor), alpha, u, v, light);
            info.cancel();
        }
    }

    @Unique
    private void vertex(VertexConsumer vertexConsumer, float x, float y, float z, int red, int green, int blue, int alpha, float u, float v, int light) {
        vertexConsumer.addVertex(x, y, z).setColor(red, green, blue, alpha).setUv(u, v).setLight(light).setNormal(0.0f, 1.0f, 0.0f);
    }
}
