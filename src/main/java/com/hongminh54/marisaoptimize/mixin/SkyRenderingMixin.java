/*
 * This file is part of the Marisa Optimize distribution (https://github.com/TheBlueSkyARL/marisa-optimize).
 * Copyright (c) Meteor Development.
 */

package com.hongminh54.marisaoptimize.mixin;

import com.hongminh54.marisaoptimize.systems.modules.Modules;
import com.hongminh54.marisaoptimize.systems.modules.world.Ambience;
import net.minecraft.client.Camera;
import net.minecraft.client.renderer.SkyRenderer;
import net.minecraft.client.renderer.DynamicUniforms;
import net.minecraft.client.renderer.state.level.SkyRenderState;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.world.level.dimension.DimensionType;
import org.joml.Matrix4f;
import com.mojang.blaze3d.buffers.GpuBufferSlice;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SkyRenderer.class)
public class SkyRenderingMixin {
    @Inject(method = "extractRenderState", at = @At("TAIL"))
    private void updateRenderState(ClientLevel world, float tickProgress, Camera camera, SkyRenderState state, CallbackInfo ci) {
        Ambience ambience = Modules.get().get(Ambience.class);
        if (!ambience.isActive()) return;

        if (ambience.endSky.get()) state.skybox = DimensionType.Skybox.END;
        if (ambience.customSkyColor.get()) state.skyColor = ambience.skyColor().getPacked();
    }

    @WrapOperation(method = "renderEndSky", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/DynamicUniforms;writeTransform(Lorg/joml/Matrix4f;)Lcom/mojang/blaze3d/buffers/GpuBufferSlice;"))
    private GpuBufferSlice modifyEndSkyColor(DynamicUniforms instance, Matrix4f modelView, Operation<GpuBufferSlice> original) {
        Ambience ambience = Modules.get().get(Ambience.class);

        if (ambience.isActive() && ambience.endSky.get() && ambience.customSkyColor.get()) return instance.writeTransform(modelView, ambience.skyColor().getVec4f());
        else return original.call(instance, modelView);
    }
}
