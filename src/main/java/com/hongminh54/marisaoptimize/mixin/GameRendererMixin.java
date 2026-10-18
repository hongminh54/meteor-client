/*
 * This file is part of the Marisa Optimize distribution (https://github.com/TheBlueSkyARL/marisa-optimize).
 * Copyright (c) Meteor Development.
 */
package com.hongminh54.marisaoptimize.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.hongminh54.marisaoptimize.MarisaOptimize;
import com.hongminh54.marisaoptimize.MixinPlugin;
import com.hongminh54.marisaoptimize.events.render.Render3DEvent;
import com.hongminh54.marisaoptimize.events.render.RenderAfterWorldEvent;
import com.hongminh54.marisaoptimize.renderer.MeteorRenderPipelines;
import com.hongminh54.marisaoptimize.renderer.Renderer3D;
import com.hongminh54.marisaoptimize.systems.modules.Modules;
import com.hongminh54.marisaoptimize.systems.modules.render.Freecam;
import com.hongminh54.marisaoptimize.systems.modules.render.NoRender;
import com.hongminh54.marisaoptimize.systems.modules.render.Zoom;
import com.hongminh54.marisaoptimize.utils.Utils;
import com.hongminh54.marisaoptimize.utils.render.CustomBannerGuiElementRenderer;
import com.hongminh54.marisaoptimize.utils.render.NametagUtils;
import com.hongminh54.marisaoptimize.utils.render.RenderUtils;
import net.minecraft.client.Camera;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.render.pip.PictureInPictureRenderer;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.state.GameRenderState;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.util.profiling.Profiler;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.joml.Matrix4f;
import org.joml.Matrix4fc;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.List;

@Mixin(GameRenderer.class)
public abstract class GameRendererMixin {
    @Shadow @Final private Minecraft minecraft;
    @Shadow @Final private Camera mainCamera;
    @Shadow @Final private GameRenderState gameRenderState;
    @Shadow protected abstract void bobView(CameraRenderState cameraState, PoseStack poseStack);
    @Shadow protected abstract void bobHurt(CameraRenderState cameraState, PoseStack poseStack);

    @Unique private Renderer3D renderer;
    @Unique private Renderer3D depthRenderer;
    @Unique private final PoseStack matrices = new PoseStack();

    @ModifyArg(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/render/GuiRenderer;<init>(Lnet/minecraft/client/renderer/state/gui/GuiRenderState;Lnet/minecraft/client/renderer/feature/FeatureRenderDispatcher;Ljava/util/List;)V"))
    private List<PictureInPictureRenderer<?>> meteor$addSpecialRenderers(List<PictureInPictureRenderer<?>> list) {
        List<PictureInPictureRenderer<?>> result = new ArrayList<>(list);
        result.add(new CustomBannerGuiElementRenderer(minecraft.getAtlasManager()));
        return result;
    }

    @Inject(method = "renderLevel", at = @At(value = "INVOKE_STRING", target = "Lnet/minecraft/util/profiling/ProfilerFiller;popPush(Ljava/lang/String;)V", args = "ldc=hand"))
    private void onRenderLevel(DeltaTracker deltaTracker, CallbackInfo ci,
                              @Local(name = "projectionMatrix") Matrix4f projectionMatrix,
                              @Local(name = "modelViewMatrix") Matrix4fc modelViewMatrix,
                              @Local(name = "worldPartialTicks") float worldPartialTicks,
                              @Local(name = "bobStack") PoseStack bobStack) {
        if (!Utils.canUpdate()) return;
        Profiler.get().push(MarisaOptimize.MOD_ID + "_render");
        try {
            if (renderer == null) renderer = new Renderer3D(MeteorRenderPipelines.WORLD_COLORED_LINES, MeteorRenderPipelines.WORLD_COLORED);
            if (depthRenderer == null) depthRenderer = new Renderer3D(MeteorRenderPipelines.WORLD_COLORED_LINES_DEPTH, MeteorRenderPipelines.WORLD_COLORED_DEPTH);
            Render3DEvent event = Render3DEvent.get(bobStack, renderer, depthRenderer, worldPartialTicks,
                mainCamera.position().x, mainCamera.position().y, mainCamera.position().z);

            RenderSystem.getModelViewStack().pushMatrix();
            try {
                RenderSystem.getModelViewStack().mul(modelViewMatrix);
                Matrix4f inverseBob;
                matrices.pushPose();
                try {
                    bobHurt(gameRenderState.levelRenderState.cameraRenderState, matrices);
                    if (minecraft.options.bobView().get()) bobView(gameRenderState.levelRenderState.cameraRenderState, matrices);
                    inverseBob = new Matrix4f(matrices.last().pose()).invert();
                } finally {
                    matrices.popPose();
                }
                RenderSystem.getModelViewStack().mul(inverseBob);
                Matrix4fc correctedPosition = MixinPlugin.isIrisPresent && RenderUtils.isShaderPackInUse()
                    ? new Matrix4f(modelViewMatrix).mul(inverseBob) : modelViewMatrix;
                RenderUtils.updateScreenCenter(projectionMatrix, correctedPosition);
                NametagUtils.onRender(modelViewMatrix);

                renderer.begin();
                depthRenderer.begin();
                MarisaOptimize.EVENT_BUS.post(event);
                renderer.render(bobStack);
                depthRenderer.render(bobStack);
            } finally {
                RenderSystem.getModelViewStack().popMatrix();
            }
        } finally {
            Profiler.get().pop();
        }
    }

    @Inject(method = "renderLevel", at = @At("TAIL"))
    private void onRenderLevelTail(CallbackInfo ci) {
        MarisaOptimize.EVENT_BUS.post(RenderAfterWorldEvent.get());
    }

    @Inject(method = "displayItemActivation", at = @At("HEAD"), cancellable = true)
    private void onDisplayItemActivation(ItemStack itemStack, CallbackInfo ci) {
        if (itemStack.getItem() == Items.TOTEM_OF_UNDYING && Modules.get().get(NoRender.class).noTotemAnimation()) ci.cancel();
    }

    @ModifyExpressionValue(method = "renderLevel", at = @At(value = "INVOKE", target = "Ljava/lang/Math;max(FF)F", ordinal = 0))
    private float applyCameraTransformationsMathHelperLerpProxy(float original) {
        return Modules.get().get(NoRender.class).noNausea() ? 0 : original;
    }

    @Inject(method = "renderItemInHand", at = @At("HEAD"), cancellable = true)
    private void renderItemInHand(CameraRenderState cameraState, float deltaPartialTick, Matrix4fc modelViewMatrix, CallbackInfo ci) {
        if (!Modules.get().get(Freecam.class).renderHands() || !Modules.get().get(Zoom.class).renderHands()) ci.cancel();
    }
}
