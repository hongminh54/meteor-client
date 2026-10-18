/*
 * This file is part of the Marisa Optimize distribution (https://github.com/TheBlueSkyARL/marisa-optimize).
 * Copyright (c) Meteor Development.
 */

package com.hongminh54.marisaoptimize.mixin;

import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import com.hongminh54.marisaoptimize.systems.modules.Modules;
import com.hongminh54.marisaoptimize.systems.modules.render.Chams;
import net.minecraft.client.model.Model;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EndCrystalRenderer;
import net.minecraft.client.renderer.entity.state.EndCrystalRenderState;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.resources.Identifier;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EndCrystalRenderer.class)
public abstract class EndCrystalEntityRendererMixin {
    // Chams

    @Unique
    private Chams chams;

    @Inject(method = "<init>", at = @At("RETURN"))
    private void onInit(CallbackInfo info) {
        chams = Modules.get().get(Chams.class);
    }

    // Chams - Texture

    @Shadow
    @Final
    private static Identifier END_CRYSTAL_LOCATION;

    // Chams - Scale

    @Inject(method = "submit(Lnet/minecraft/client/renderer/entity/state/EndCrystalRenderState;Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/SubmitNodeCollector;Lnet/minecraft/client/renderer/state/level/CameraRenderState;)V", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/vertex/PoseStack;scale(FFF)V"))
    private void render$scale(EndCrystalRenderState endCrystalEntityRenderState, PoseStack matrixStack, SubmitNodeCollector orderedRenderCommandQueue, CameraRenderState arg, CallbackInfo ci) {
        if (!chams.isActive() || !chams.crystals.get()) return;

        float v = chams.crystalsScale.get().floatValue();
        matrixStack.scale(v, v, v);
    }

    // Chams - Color

    @WrapWithCondition(method = "submit(Lnet/minecraft/client/renderer/entity/state/EndCrystalRenderState;Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/SubmitNodeCollector;Lnet/minecraft/client/renderer/state/level/CameraRenderState;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/SubmitNodeCollector;submitModel(Lnet/minecraft/client/model/Model;Ljava/lang/Object;Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/resources/Identifier;IIILnet/minecraft/client/renderer/feature/ModelFeatureRenderer$CrumblingOverlay;)V"))
    private <S> boolean render$color(SubmitNodeCollector instance, Model<? super S> model, S state, PoseStack matrixStack, Identifier texture, int light, int uv, int outlineColor, ModelFeatureRenderer.CrumblingOverlay crumblingOverlayCommand) {
        if (chams.isActive() && chams.crystals.get()) {
            instance.submitModel(
                model,
                state,
                matrixStack,
                RenderTypes.entityTranslucent(!chams.crystalsTexture.get() ? Chams.BLANK : END_CRYSTAL_LOCATION),
                light,
                uv,
                chams.crystalsColor.get().getPacked(),
                null,
                outlineColor,
                null
            );

            return false;
        }

        return true;
    }
}
