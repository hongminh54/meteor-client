/*
 * This file is part of the Marisa Optimize distribution (https://github.com/TheBlueSkyARL/marisa-optimize).
 * Copyright (c) Meteor Development.
 */

package com.hongminh54.marisaoptimize.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.hongminh54.marisaoptimize.systems.modules.Modules;
import com.hongminh54.marisaoptimize.systems.modules.render.NoRender;
import com.hongminh54.marisaoptimize.systems.modules.world.Ambience;
import net.minecraft.client.renderer.fog.FogRenderer;
import org.objectweb.asm.Opcodes;
import org.joml.Vector4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(FogRenderer.class)
public abstract class FogRendererMixin {
    @ModifyVariable(method = "updateBuffer(Ljava/nio/ByteBuffer;ILorg/joml/Vector4f;FFFFFF)V", at = @At("HEAD"), argsOnly = true, name = "fogColor")
    private Vector4f modifyFogDistance(Vector4f original) {
        if (Modules.get() == null) return original;

        Ambience ambience = Modules.get().get(Ambience.class);
        if (ambience.isActive() && ambience.customFogColor.get()) {
            return ambience.fogColor.get().getVec4f();
        }

        return original;
    }

    @ModifyExpressionValue(method = "getBuffer", at = @At(value = "FIELD", target = "Lnet/minecraft/client/renderer/fog/FogRenderer;fogEnabled:Z", opcode = Opcodes.GETSTATIC))
    private boolean modifyFogEnabled(boolean original) {
        if (Modules.get() == null) return original;

        return original && !Modules.get().get(NoRender.class).noFog();
    }
}
