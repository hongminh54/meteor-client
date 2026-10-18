/*
 * This file is part of the Marisa Optimize distribution (https://github.com/TheBlueSkyARL/marisa-optimize).
 * Copyright (c) Meteor Development.
 */

package com.hongminh54.marisaoptimize.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.hongminh54.marisaoptimize.systems.modules.Modules;
import com.hongminh54.marisaoptimize.systems.modules.render.Xray;
import com.mojang.blaze3d.vertex.QuadInstance;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.client.renderer.block.BlockQuadOutput;
import net.minecraft.client.renderer.block.ModelBlockRenderer;
import net.minecraft.client.renderer.block.dispatch.BlockStateModelPart;
import net.minecraft.client.renderer.block.BlockAndTintGetter;
import net.minecraft.client.renderer.chunk.ChunkSectionLayer;
import net.minecraft.client.resources.model.geometry.BakedQuad;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.ARGB;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(ModelBlockRenderer.class)
public abstract class BlockModelRendererMixin {
    @Shadow @Final private QuadInstance quadInstance;

    @Unique
    private final ThreadLocal<Integer> alphas = ThreadLocal.withInitial(() -> -1);

    @Inject(method = {"tesselateAmbientOcclusion", "tesselateFlat"}, at = @At("HEAD"), cancellable = true)
    private void onRenderSmooth(BlockQuadOutput output, float x, float y, float z, List<BlockStateModelPart> parts, BlockAndTintGetter world, BlockState state, BlockPos pos, CallbackInfo ci) {
        int alpha = Xray.getAlpha(state, pos);

        if (alpha == 0) ci.cancel();
        else alphas.set(alpha);
    }

    @Inject(method = "putQuadWithTint", at = @At("HEAD"))
    private void modifyXrayAlpha(BlockQuadOutput output, float x, float y, float z, BlockAndTintGetter world, BlockState state, BlockPos pos, BakedQuad quad, CallbackInfo ci) {
        int alpha = alphas.get();
        if (alpha != -1) quadInstance.multiplyColor(ARGB.color(alpha, 255, 255, 255));
    }

    @ModifyArg(method = "putQuadWithTint", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/block/BlockQuadOutput;put(FFFLnet/minecraft/client/resources/model/geometry/BakedQuad;Lcom/mojang/blaze3d/vertex/QuadInstance;)V"), index = 3)
    private BakedQuad modifyXrayLayer(BakedQuad quad) {
        int alpha = alphas.get();
        if (alpha <= 0 || alpha >= 255) return quad;
        BakedQuad.MaterialInfo info = quad.materialInfo();
        if (info.layer() == ChunkSectionLayer.TRANSLUCENT) return quad;
        BakedQuad.MaterialInfo translucent = new BakedQuad.MaterialInfo(info.sprite(), ChunkSectionLayer.TRANSLUCENT,
            info.itemRenderType(), info.tintIndex(), info.shade(), info.lightEmission());
        return new BakedQuad(quad.position0(), quad.position1(), quad.position2(), quad.position3(),
            quad.packedUV0(), quad.packedUV1(), quad.packedUV2(), quad.packedUV3(), quad.direction(), translucent);
    }

    @ModifyReturnValue(method = "shouldRenderFace", at = @At("RETURN"))
    private static boolean modifyShouldDrawFace(boolean original, BlockAndTintGetter world, BlockState state, Direction side, BlockPos pos) {
        Xray xray = Modules.get().get(Xray.class);

        if (xray.isActive()) {
            return xray.modifyDrawSide(state, world, pos.relative(side.getOpposite()), side, original); // thanks mojang
        }

        return original;
    }
}
