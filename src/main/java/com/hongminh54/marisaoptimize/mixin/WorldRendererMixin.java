/*
 * This file is part of the Marisa Optimize distribution (https://github.com/TheBlueSkyARL/marisa-optimize).
 * Copyright (c) Meteor Development.
 */

package com.hongminh54.marisaoptimize.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.mojang.blaze3d.pipeline.RenderTarget;
import com.mojang.blaze3d.resource.ResourceHandle;
import com.mojang.blaze3d.vertex.PoseStack;
import com.hongminh54.marisaoptimize.mixininterface.IEntityRenderState;
import com.hongminh54.marisaoptimize.mixininterface.IWorldRenderer;
import com.hongminh54.marisaoptimize.systems.modules.Modules;
import com.hongminh54.marisaoptimize.systems.modules.render.BlockSelection;
import com.hongminh54.marisaoptimize.systems.modules.render.ESP;
import com.hongminh54.marisaoptimize.systems.modules.render.NoRender;
import com.hongminh54.marisaoptimize.utils.OutlineRenderCommandQueue;
import com.hongminh54.marisaoptimize.utils.render.color.Color;
import com.hongminh54.marisaoptimize.utils.render.postprocess.EntityShader;
import com.hongminh54.marisaoptimize.utils.render.postprocess.PostProcessShaders;
import it.unimi.dsi.fastutil.Stack;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.LevelTargetBundle;
import net.minecraft.client.renderer.RenderBuffers;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.feature.FeatureRenderDispatcher;
import net.minecraft.client.renderer.state.level.LevelRenderState;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Function;

import static com.hongminh54.marisaoptimize.MarisaOptimize.mc;

@Mixin(LevelRenderer.class)
public abstract class WorldRendererMixin implements IWorldRenderer {
    @Shadow @Final @Mutable private RenderTarget entityOutlineTarget;
    @Shadow @Final private LevelTargetBundle targets;
    @Shadow @Final private EntityRenderDispatcher entityRenderDispatcher;
    @Shadow @Final private RenderBuffers renderBuffers;
    @Unique private FeatureRenderDispatcher renderDispatcher;
    @Unique private final OutlineRenderCommandQueue outlineRenderCommandQueue = new OutlineRenderCommandQueue();
    @Unique private final Stack<RenderTarget> framebufferStack = new ObjectArrayList<>();
    @Unique private final Stack<ResourceHandle<RenderTarget>> framebufferHandleStack = new ObjectArrayList<>();

    @Inject(method = "checkPoseStack", at = @At("HEAD"), cancellable = true)
    private void onCheckEmpty(PoseStack poses, CallbackInfo ci) {
        ci.cancel();
    }

    @Inject(method = "submitBlockOutline", at = @At("HEAD"), cancellable = true)
    private void onDrawHighlightedBlockOutline(PoseStack poses, SubmitNodeCollector output, LevelRenderState state, CallbackInfo ci) {
        if (Modules.get().isActive(BlockSelection.class)) ci.cancel();
    }

    @ModifyExpressionValue(method = "addSkyPass", at = @At(value = "FIELD", target = "Lnet/minecraft/client/renderer/state/level/CameraEntityRenderState;doesMobEffectBlockSky:Z"))
    private boolean hasBlindnessOrDarkness(boolean original) {
        NoRender noRender = Modules.get().get(NoRender.class);
        return original && !noRender.noBlindness() && !noRender.noDarkness();
    }

    @Inject(method = "render", at = @At("HEAD"))
    private void onRenderHead(CallbackInfo ci) {
        PostProcessShaders.beginRender();
    }

    @Inject(method = "submitEntities", at = @At("TAIL"))
    private void onPushEntityRenders(PoseStack poses, LevelRenderState state, SubmitNodeCollector output, CallbackInfo ci) {
        if (renderDispatcher == null) {
            renderDispatcher = new FeatureRenderDispatcher(renderBuffers, mc.getModelManager(), mc.getAtlasManager(), mc.font, mc.gameRenderer.gameRenderState());
        }
        draw(state, poses, PostProcessShaders.CHAMS, entity -> Color.WHITE);
        draw(state, poses, PostProcessShaders.ENTITY_OUTLINE, entity -> Modules.get().get(ESP.class).getColor(entity));
    }

    @Unique
    private void draw(LevelRenderState worldState, PoseStack poses, EntityShader shader, Function<Entity, Color> colorGetter) {
        var camera = worldState.cameraRenderState.pos;
        boolean empty = true;
        for (var state : worldState.entityRenderStates) {
            Entity entity = ((IEntityRenderState) state).meteor$getEntity();
            if (entity == null || !shader.shouldDraw(entity)) continue;
            Color color = colorGetter.apply(entity);
            if (color == null) continue;
            outlineRenderCommandQueue.setColor(color);
            var renderer = entityRenderDispatcher.getRenderer(state);
            var offset = renderer.getRenderOffset(state);
            poses.pushPose();
            poses.translate(state.x - camera.x + offset.x, state.y - camera.y + offset.y, state.z - camera.z + offset.z);
            renderer.submit(state, poses, outlineRenderCommandQueue, worldState.cameraRenderState);
            poses.popPose();
            empty = false;
        }
        if (empty) return;
        meteor$pushEntityOutlineFramebuffer(shader.framebuffer);
        try {
            try (var frame = renderDispatcher.prepareFrame(outlineRenderCommandQueue)) {
                frame.executeOutline();
            }
        } finally {
            outlineRenderCommandQueue.submitsPerOrder.clear();
            meteor$popEntityOutlineFramebuffer();
        }
    }

    @Inject(method = "lambda$addMainPass$0", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/feature/FeatureRenderDispatcher$PreparedFrame;executeOutline()V", shift = At.Shift.AFTER))
    private void onRender(CallbackInfo ci) {
        PostProcessShaders.submitEntityVertices();
    }

    @Inject(method = "resize", at = @At("HEAD"))
    private void onResized(int width, int height, CallbackInfo ci) {
        PostProcessShaders.onResized(width, height);
    }

    @Override
    public void meteor$pushEntityOutlineFramebuffer(RenderTarget framebuffer) {
        framebufferStack.push(entityOutlineTarget);
        entityOutlineTarget = framebuffer;
        framebufferHandleStack.push(targets.entityOutline);
        targets.entityOutline = () -> framebuffer;
    }

    @Override
    public void meteor$popEntityOutlineFramebuffer() {
        entityOutlineTarget = framebufferStack.pop();
        targets.entityOutline = framebufferHandleStack.pop();
    }
}
