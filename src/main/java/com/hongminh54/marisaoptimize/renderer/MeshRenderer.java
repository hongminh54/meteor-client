/*
 * This file is part of the Marisa Optimize distribution (https://github.com/TheBlueSkyARL/marisa-optimize).
 * Copyright (c) Meteor Development.
 */

package com.hongminh54.marisaoptimize.renderer;

import com.mojang.blaze3d.buffers.GpuBuffer;
import com.mojang.blaze3d.buffers.GpuBufferSlice;
import com.mojang.blaze3d.pipeline.RenderPipeline;
import com.mojang.blaze3d.systems.RenderPass;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.textures.GpuTextureView;
import com.mojang.blaze3d.IndexType;
import com.mojang.blaze3d.systems.CommandEncoder;
import com.mojang.blaze3d.vertex.PoseStack;
import com.hongminh54.marisaoptimize.utils.Utils;
import com.hongminh54.marisaoptimize.utils.render.RenderUtils;
import com.hongminh54.marisaoptimize.utils.render.color.Color;
import com.mojang.blaze3d.pipeline.RenderTarget;
import com.mojang.blaze3d.textures.GpuSampler;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix4f;

import java.util.HashMap;
import java.util.OptionalDouble;
import java.util.Optional;
import org.joml.Vector4f;
import org.joml.Vector4fc;

import static com.hongminh54.marisaoptimize.MarisaOptimize.mc;

public class MeshRenderer {
    private static final MeshRenderer INSTANCE = new MeshRenderer();

    private static boolean taken;

    private GpuTextureView colorAttachment;
    private GpuTextureView depthAttachment;
    private Color clearColor;
    private RenderPipeline pipeline;
    private @Nullable MeshBuilder mesh;
    private @Nullable GpuBufferSlice vertexBuffer;
    private @Nullable GpuBufferSlice indexBuffer;
    private Matrix4f matrix;
    private final HashMap<String, GpuBufferSlice> uniforms = new HashMap<>();
    private final HashMap<String, TextureViewAndSampler> samplers = new HashMap<>();

    private record TextureViewAndSampler(GpuTextureView view, GpuSampler sampler) {}

    private MeshRenderer() {}

    public static MeshRenderer begin() {
        if (taken)
            throw new IllegalStateException("Previous instance of MeshRenderer was not ended");

        taken = true;
        return INSTANCE;
    }

    public MeshRenderer attachments(GpuTextureView color, GpuTextureView depth) {
        colorAttachment = color;
        depthAttachment = depth;
        return this;
    }

    public MeshRenderer attachments(RenderTarget framebuffer) {
        colorAttachment = framebuffer.getColorTextureView();
        depthAttachment = framebuffer.getDepthTextureView();
        return this;
    }

    public MeshRenderer clearColor(Color color) {
        clearColor = color;
        return this;
    }

    public MeshRenderer pipeline(RenderPipeline pipeline) {
        this.pipeline = pipeline;
        return this;
    }

    public MeshRenderer mesh(GpuBuffer vertices, GpuBuffer indices) {
        return mesh(vertices.slice(), indices.slice());
    }

    public MeshRenderer mesh(GpuBufferSlice vertices, GpuBufferSlice indices) {
        this.vertexBuffer = vertices;
        this.indexBuffer = indices;
        return this;
    }

    public MeshRenderer mesh(MeshBuilder mesh) {
        this.mesh = mesh;
        return this;
    }

    public MeshRenderer mesh(MeshBuilder mesh, Matrix4f matrix) {
        this.mesh = mesh;
        return this.transform(matrix);
    }

    public MeshRenderer mesh(MeshBuilder mesh, PoseStack matrices) {
        this.mesh = mesh;
        return this.transform(matrices);
    }

    public MeshRenderer transform(Matrix4f matrix) {
        this.matrix = matrix;
        return this;
    }

    public MeshRenderer transform(PoseStack matrices) {
        this.matrix = matrices.last().pose();
        return this;
    }

    public MeshRenderer fullscreen() {
        return this.mesh(FullScreenRenderer.vbo, FullScreenRenderer.ibo);
    }

    public MeshRenderer uniform(String name, GpuBufferSlice slice) {
        uniforms.put(name, slice);
        return this;
    }

    public MeshRenderer sampler(String name, GpuTextureView view, GpuSampler sampler) {
        if (name != null && view != null && sampler != null) {
            samplers.put(name, new TextureViewAndSampler(view, sampler));
        }

        return this;
    }

    public void end() {
        try {
            draw();
        } finally {
            reset();
        }
    }

    private void draw() {
        if (mesh != null && mesh.isBuilding()) {
            mesh.end();
        }

        int indexCount = mesh != null ? mesh.getIndicesCount()
            : (indexBuffer != null ? Math.toIntExact(indexBuffer.length() / Integer.BYTES) : -1);

        if (indexCount > 0) {

            boolean transform = Utils.rendering3D || matrix != null;
            if (transform) {
                RenderSystem.getModelViewStack().pushMatrix();
            }

            try {
                if (matrix != null) {
                    RenderSystem.getModelViewStack().mul(matrix);
                }

                if (Utils.rendering3D) {
                    applyCameraPos();
                }

                CommandEncoder encoder = RenderSystem.getDevice().createCommandEncoder();
                GpuBufferSlice vertexBuffer = mesh != null ? mesh.uploadVertexBuffer(encoder) : java.util.Objects.requireNonNull(this.vertexBuffer);
                GpuBufferSlice indexBuffer = mesh != null ? mesh.uploadIndexBuffer(encoder) : java.util.Objects.requireNonNull(this.indexBuffer);
                if (indexBuffer.offset() % Integer.BYTES != 0) {
                    throw new IllegalArgumentException("Index buffer offset must be aligned to four bytes.");
                }
                int firstIndex = Math.toIntExact(indexBuffer.offset() / Integer.BYTES);
                Optional<Vector4fc> clearColor = this.clearColor != null ?
                    Optional.of(new Vector4f(this.clearColor.r / 255f, this.clearColor.g / 255f, this.clearColor.b / 255f, this.clearColor.a / 255f)) :
                    Optional.empty();

                GpuBufferSlice meshData = MeshUniforms.write(RenderUtils.projection, RenderSystem.getModelViewStack());

                try (RenderPass pass = (depthAttachment != null && pipeline.wantsDepthTexture()) ?
                    encoder.createRenderPass(() -> "Marisa MeshRenderer", colorAttachment, clearColor, depthAttachment, OptionalDouble.empty()) :
                    encoder.createRenderPass(() -> "Marisa MeshRenderer", colorAttachment, clearColor)) {

                    pass.setPipeline(pipeline);
                    pass.setUniform("MeshData", meshData);

                    for (var entry : uniforms.entrySet()) {
                        pass.setUniform(entry.getKey(), entry.getValue());
                    }

                    for (var entry : samplers.entrySet()) {
                        pass.bindTexture(entry.getKey(), entry.getValue().view(), entry.getValue().sampler());
                    }

                    pass.setVertexBuffer(0, vertexBuffer);
                    pass.setIndexBuffer(indexBuffer.buffer(), IndexType.INT);
                    pass.drawIndexed(indexCount, 1, firstIndex, 0, 0);
                }

            } finally {
                if (transform) RenderSystem.getModelViewStack().popMatrix();
            }
        }
    }

    private void reset() {
        colorAttachment = null;
        depthAttachment = null;
        clearColor = null;
        pipeline = null;
        mesh = null;
        vertexBuffer = null;
        indexBuffer = null;
        matrix = null;
        uniforms.clear();
        samplers.clear();

        taken = false;
    }

    private static void applyCameraPos() {
        Vec3 cameraPos = mc.gameRenderer.mainCamera().position();
        RenderSystem.getModelViewStack().translate(0, (float) -cameraPos.y, 0);
    }
}
