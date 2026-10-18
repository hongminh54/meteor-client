/*
 * This file is part of the Marisa Optimize distribution (https://github.com/TheBlueSkyARL/marisa-optimize).
 * Copyright (c) Meteor Development.
 */

package com.hongminh54.marisaoptimize.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.hongminh54.marisaoptimize.systems.modules.Modules;
import com.hongminh54.marisaoptimize.systems.modules.render.NoRender;
import net.minecraft.client.renderer.state.MapRenderState;
import net.minecraft.client.renderer.MapRenderer;
import net.minecraft.client.renderer.SubmitNodeCollector;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.world.level.saveddata.maps.MapDecoration;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(MapRenderer.class)
public abstract class MapRendererMixin {
    @ModifyExpressionValue(method = "render", at = @At(value = "FIELD", target = "Lnet/minecraft/client/renderer/state/MapRenderState;decorations:Ljava/util/List;", opcode = Opcodes.GETFIELD))
    private List<MapDecoration> getIconsProxy(List<MapDecoration> original) {
        return (Modules.get().get(NoRender.class).noMapMarkers()) ? List.of() : original;
    }

    @Inject(method = "render", at = @At("HEAD"), cancellable = true)
    private void onDraw(MapRenderState state, PoseStack matrices, SubmitNodeCollector queue, boolean skipRenderingDecorations, int light, CallbackInfo ci) {
        if (Modules.get().get(NoRender.class).noMapContents()) ci.cancel();
    }
}
