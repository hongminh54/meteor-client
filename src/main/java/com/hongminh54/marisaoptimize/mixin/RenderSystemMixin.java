/*
 * This file is part of the Marisa Optimize distribution (https://github.com/TheBlueSkyARL/marisa-optimize).
 * Copyright (c) Meteor Development.
 */

package com.hongminh54.marisaoptimize.mixin;

import net.minecraft.client.Minecraft;
import com.hongminh54.marisaoptimize.renderer.MeshUniforms;
import com.hongminh54.marisaoptimize.systems.modules.Modules;
import com.hongminh54.marisaoptimize.systems.modules.misc.InventoryTweaks;
import com.hongminh54.marisaoptimize.utils.render.postprocess.ChamsShader;
import com.hongminh54.marisaoptimize.utils.render.postprocess.OutlineUniforms;
import com.hongminh54.marisaoptimize.utils.render.postprocess.PostProcessShader;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static com.hongminh54.marisaoptimize.MarisaOptimize.mc;

@Mixin(Minecraft.class)
public abstract class RenderSystemMixin {
    @Inject(method = "renderFrame", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/LevelRenderer;endFrame()V", shift = At.Shift.AFTER))
    private void meteor$flipFrame(boolean advanceGameTime, CallbackInfo info) {
        MeshUniforms.flipFrame();
        PostProcessShader.flipFrame();
        ChamsShader.flipFrame();
        OutlineUniforms.flipFrame();

        if (Modules.get() == null || mc.player == null) return;
        if (Modules.get().get(InventoryTweaks.class).frameInput()) ((MinecraftClientAccessor) mc).meteor$handleInputEvents();
    }
}
