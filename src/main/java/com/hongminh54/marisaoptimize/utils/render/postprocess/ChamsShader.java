/*
 * This file is part of the Marisa Optimize distribution (https://github.com/TheBlueSkyARL/marisa-optimize).
 * Copyright (c) Meteor Development.
 */

package com.hongminh54.marisaoptimize.utils.render.postprocess;

import com.mojang.blaze3d.buffers.Std140Builder;
import com.mojang.blaze3d.buffers.Std140SizeCalculator;
import com.mojang.blaze3d.platform.TextureUtil;
import com.mojang.blaze3d.textures.FilterMode;
import com.mojang.blaze3d.GpuFormat;
import com.hongminh54.marisaoptimize.MarisaOptimize;
import com.hongminh54.marisaoptimize.events.game.ResourcePacksReloadedEvent;
import com.hongminh54.marisaoptimize.renderer.MeshRenderer;
import com.hongminh54.marisaoptimize.renderer.MeteorRenderPipelines;
import com.hongminh54.marisaoptimize.renderer.Texture;
import com.hongminh54.marisaoptimize.systems.modules.Modules;
import com.hongminh54.marisaoptimize.systems.modules.render.Chams;
import com.hongminh54.marisaoptimize.utils.PostInit;
import com.hongminh54.marisaoptimize.utils.render.color.Color;
import meteordevelopment.orbit.EventHandler;
import net.minecraft.client.renderer.DynamicUniformStorage;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.packs.resources.Resource;
import org.lwjgl.stb.STBImage;
import org.lwjgl.system.MemoryStack;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.Optional;

import static com.hongminh54.marisaoptimize.MarisaOptimize.mc;

public class ChamsShader extends EntityShader {
    private static final String[] FILE_FORMATS = { "png", "jpg" };

    private static Texture IMAGE_TEX;
    private static Chams chams;

    public ChamsShader() {
        super(MeteorRenderPipelines.POST_IMAGE);
        MarisaOptimize.EVENT_BUS.subscribe(ChamsShader.class);
    }

    @PostInit
    public static void load() {
        try {
            ByteBuffer data = null;
            for (String fileFormat : FILE_FORMATS) {
                Optional<Resource> optional = mc.getResourceManager().getResource(MarisaOptimize.identifier("textures/chams." + fileFormat));
                if (optional.isEmpty()) {
                    continue;
                }

                try (var input = optional.get().open()) {
                    data = TextureUtil.readResource(input);
                }
                break;
            }
            if (data == null) return;

            data.rewind();

            try (MemoryStack stack = MemoryStack.stackPush()) {
                IntBuffer width = stack.mallocInt(1);
                IntBuffer height = stack.mallocInt(1);
                IntBuffer comp = stack.mallocInt(1);

                STBImage.stbi_set_flip_vertically_on_load(true);
                try {
                    ByteBuffer image = STBImage.stbi_load_from_memory(data, width, height, comp, 4);
                    if (image == null) throw new IOException("Cannot decode chams image: " + STBImage.stbi_failure_reason());

                    try {
                        Texture replacement = new Texture(width.get(0), height.get(0), GpuFormat.RGBA8_UNORM, FilterMode.NEAREST, FilterMode.NEAREST);
                        try {
                            replacement.upload(image);
                        } catch (RuntimeException | Error failure) {
                            replacement.close();
                            throw failure;
                        }
                        Texture previous = IMAGE_TEX;
                        IMAGE_TEX = replacement;
                        if (previous != null) previous.close();
                    } finally {
                        STBImage.stbi_image_free(image);
                    }
                } finally {
                    STBImage.stbi_set_flip_vertically_on_load(false);
                }
            }
        }
        catch (IOException e) {
            MarisaOptimize.LOG.error("Error loading the chams shader", e);
        }
    }

    @EventHandler
    private static void onResourcePacksReloaded(ResourcePacksReloadedEvent event) {
        load();
    }

    @Override
    protected void setupPass(MeshRenderer renderer) {
        Color color = chams.shaderColor.get();

        renderer.uniform("ImageData", UNIFORM_STORAGE.writeUniform(new UniformData(
            color.r / 255f, color.g / 255f, color.b / 255f, color.a / 255f
        )));

        if (chams.isShader() && chams.shader.get() == Chams.Shader.Image && IMAGE_TEX != null) {
            renderer.sampler("u_TextureI", IMAGE_TEX.getTextureView(), IMAGE_TEX.getSampler());
        }
    }

    @Override
    protected boolean shouldDraw() {
        if (chams == null) chams = Modules.get().get(Chams.class);
        return chams.isShader();
    }

    @Override
    public boolean shouldDraw(Entity entity) {
        if (!shouldDraw()) return false;
        return chams.entities.get().contains(entity.getType()) && (entity != mc.player || !chams.ignoreSelfDepth.get());
    }

    // Uniforms

    private static final int UNIFORM_SIZE = new Std140SizeCalculator()
        .putVec4()
        .get();

    private static final DynamicUniformStorage<UniformData> UNIFORM_STORAGE = new DynamicUniformStorage<>("Meteor - Image UBO", UNIFORM_SIZE, 16);

    public static void flipFrame() {
        UNIFORM_STORAGE.endFrame();
    }

    private record UniformData(float r, float g, float b, float a) implements DynamicUniformStorage.DynamicUniform {
        @Override
        public void write(ByteBuffer buffer) {
            Std140Builder.intoBuffer(buffer)
                .putVec4(r, g, b, a);
        }
    }
}
