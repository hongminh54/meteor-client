/*
 * This file is part of the Marisa Optimize distribution (https://github.com/TheBlueSkyARL/marisa-optimize).
 * Copyright (c) Meteor Development.
 */

package com.hongminh54.marisaoptimize.utils.render;

import com.hongminh54.marisaoptimize.events.render.Render3DEvent;
import com.hongminh54.marisaoptimize.renderer.Renderer3D;
import com.hongminh54.marisaoptimize.renderer.ShapeMode;
import com.hongminh54.marisaoptimize.utils.render.color.Color;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.Model;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.SubmitNodeStorage;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.world.entity.Entity;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;

import static com.hongminh54.marisaoptimize.MarisaOptimize.mc;

public class WireframeEntityRenderer {
    private static final PoseStack matrices = new PoseStack();

    private static Renderer3D renderer;

    private static final InterceptingStorage interceptStorage = new InterceptingStorage();

    private static Color sideColor;
    private static Color lineColor;
    private static ShapeMode shapeMode;

    private static double offsetX;
    private static double offsetY;
    private static double offsetZ;

    private WireframeEntityRenderer() {
    }

    @SuppressWarnings("unchecked")
    public static void render(Render3DEvent event, Entity entity, double scale, Color sideColor, Color lineColor, ShapeMode shapeMode) {
        WireframeEntityRenderer.renderer = event.renderer;
        WireframeEntityRenderer.sideColor = sideColor;
        WireframeEntityRenderer.lineColor = lineColor;
        WireframeEntityRenderer.shapeMode = shapeMode;

        float tickDelta = mc.level.tickRateManager().isFrozen() ? 1 : event.tickDelta;

        offsetX = Mth.lerp(tickDelta, entity.xOld, entity.getX());
        offsetY = Mth.lerp(tickDelta, entity.yOld, entity.getY());
        offsetZ = Mth.lerp(tickDelta, entity.zOld, entity.getZ());

        var renderer = (EntityRenderer<Entity, EntityRenderState>) mc.getEntityRenderDispatcher().getRenderer(entity);
        var state = renderer.createRenderState(entity, tickDelta);

        Vec3 entityOffset = renderer.getRenderOffset(state);
        offsetX += entityOffset.x;
        offsetY += entityOffset.y;
        offsetZ += entityOffset.z;

        matrices.pushPose();
        matrices.scale((float) scale, (float) scale, (float) scale);
        try {
            renderer.submit(state, matrices, interceptStorage, mc.gameRenderer.gameRenderState().levelRenderState.cameraRenderState);
        } finally {
            matrices.popPose();
            interceptStorage.clear();
        }
    }

    private static class InterceptingStorage extends SubmitNodeStorage {
        private final MyVertexConsumer vertexConsumer = new MyVertexConsumer();

        @Override
        public <S> void submitModel(Model<? super S> model, S state, PoseStack poseStack, RenderType renderType, int lightCoords, int overlayCoords, int tintedColor, TextureAtlasSprite sprite, int outlineColor, ModelFeatureRenderer.CrumblingOverlay crumblingOverlay) {
            if (renderType.isOutline()) return;
            vertexConsumer.i = 0;
            model.setupAnim(state);
            model.renderToBuffer(poseStack, vertexConsumer, lightCoords, overlayCoords, tintedColor);
        }

        @Override
        public void submitCustomGeometry(PoseStack poseStack, RenderType renderType, SubmitNodeCollector.CustomGeometryRenderer customGeometryRenderer) {
            if (renderType.isOutline()) return;
            vertexConsumer.i = 0;
            customGeometryRenderer.render(poseStack.last(), vertexConsumer);
        }

        public void clear() {
            submitsPerOrder.clear();
            vertexConsumer.i = 0;
        }
    }

    private static class MyVertexConsumer implements VertexConsumer {
        private final float[] xs = new float[4];
        private final float[] ys = new float[4];
        private final float[] zs = new float[4];

        private int i = 0;

        @Override
        public VertexConsumer addVertex(float x, float y, float z) {
            xs[i] = x;
            ys[i] = y;
            zs[i] = z;

            i++;

            if (i == 4) {
                renderer.side(
                    offsetX + xs[0], offsetY + ys[0], offsetZ + zs[0],
                    offsetX + xs[1], offsetY + ys[1], offsetZ + zs[1],
                    offsetX + xs[2], offsetY + ys[2], offsetZ + zs[2],
                    offsetX + xs[3], offsetY + ys[3], offsetZ + zs[3],
                    sideColor,
                    lineColor,
                    shapeMode
                );

                i = 0;
            }

            return this;
        }

        @Override
        public VertexConsumer setColor(int red, int green, int blue, int alpha) {
            return this;
        }

        @Override
        public VertexConsumer setColor(int argb) {
            return this;
        }

        @Override
        public VertexConsumer setUv(float u, float v) {
            return this;
        }

        @Override
        public VertexConsumer setUv1(int u, int v) {
            return this;
        }

        @Override
        public VertexConsumer setUv2(int u, int v) {
            return this;
        }

        @Override
        public VertexConsumer setNormal(float x, float y, float z) {
            return this;
        }

        @Override
        public VertexConsumer setLineWidth(float width) {
            return this;
        }
    }
}
