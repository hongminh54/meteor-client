/*
 * This file is part of the Marisa Optimize distribution (https://github.com/TheBlueSkyARL/marisa-optimize).
 * Copyright (c) Meteor Development.
 */

package com.hongminh54.marisaoptimize.utils;

import com.mojang.blaze3d.vertex.PoseStack;
import com.hongminh54.marisaoptimize.utils.render.color.Color;
import net.minecraft.client.gui.Font;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.SubmitNodeCollection;
import net.minecraft.client.renderer.SubmitNodeStorage;
import net.minecraft.client.renderer.block.MovingBlockRenderState;
import net.minecraft.client.renderer.block.dispatch.BlockStateModelPart;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.item.ItemStackRenderState;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.renderer.state.level.QuadParticleRenderState;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.geometry.BakedQuad;
import net.minecraft.network.chat.Component;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.phys.Vec3;
import org.joml.Quaternionf;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;

public class OutlineRenderCommandQueue extends SubmitNodeStorage {
    private int color;
    private int[] tints;

    public void setColor(Color color) {
        this.color = color.getPacked();
    }

    @Override
    public SubmitNodeCollection order(int i) {
        return submitsPerOrder.computeIfAbsent(i, order -> new OutlineBatchingRenderCommandQueue());
    }

    private class OutlineBatchingRenderCommandQueue extends SubmitNodeCollection {
        @Override
        public void submitShadow(PoseStack poses, float radius, List<EntityRenderState.ShadowPiece> pieces) {}

        @Override
        public void submitNameTag(PoseStack poses, @Nullable Vec3 attachment, int offset, Component name, boolean seeThrough, int light, CameraRenderState camera) {}

        @Override
        public void submitText(PoseStack poses, float x, float y, FormattedCharSequence text, boolean shadow, Font.DisplayMode mode, int light, int color, int background, int outline) {}

        @Override
        public void submitFlame(PoseStack poses, EntityRenderState state, Quaternionf rotation) {}

        @Override
        public void submitLeash(PoseStack poses, EntityRenderState.LeashState leash) {}

        @Override
        public <S> void submitModel(Model<? super S> model, S state, PoseStack poses, RenderType layer, int light, int overlay, int tintedColor, @Nullable TextureAtlasSprite sprite, int outlineColor, ModelFeatureRenderer.@Nullable CrumblingOverlay crumblingOverlay) {
            super.submitModel(model, state, poses, layer, light, overlay, color, sprite, color, crumblingOverlay);
        }

        @Override
        public void submitModelPart(ModelPart part, PoseStack poses, RenderType layer, int light, int overlay, @Nullable TextureAtlasSprite sprite, int tintedColor, ModelFeatureRenderer.@Nullable CrumblingOverlay crumblingOverlay, int outlineColor) {
            super.submitModelPart(part, poses, layer, light, overlay, sprite, color, crumblingOverlay, color);
        }

        @Override
        public void submitMovingBlock(PoseStack poses, MovingBlockRenderState state, int outlineColor) {}

        @Override
        public void submitBlockModel(PoseStack poses, RenderType layer, List<BlockStateModelPart> parts, int[] tintLayers, int light, int overlay, int outlineColor) {
            Arrays.fill(tintLayers, color);
            super.submitBlockModel(poses, layer, parts, tintLayers, light, overlay, color);
        }

        @Override
        public void submitBreakingBlockModel(PoseStack poses, List<BlockStateModelPart> parts, int progress) {}

        @Override
        public void submitItem(PoseStack poses, ItemDisplayContext context, int light, int overlay, int outlineColor, int[] tintLayers, List<BakedQuad> quads, ItemStackRenderState.FoilType foil) {
            if (tints == null || tints[0] != color) tints = new int[] { color, color, color, color };
            super.submitItem(poses, context, light, overlay, color, tints, quads, foil);
        }

        @Override
        public void submitCustomGeometry(PoseStack poses, RenderType layer, CustomGeometryRenderer renderer) {}

        @Override
        public void submitQuadParticleGroup(QuadParticleRenderState state) {}
    }
}
