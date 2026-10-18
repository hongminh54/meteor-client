/*
 * This file is part of the Marisa Optimize distribution (https://github.com/TheBlueSkyARL/marisa-optimize).
 * Copyright (c) Meteor Development.
 */

package com.hongminh54.marisaoptimize.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.hongminh54.marisaoptimize.systems.modules.Modules;
import com.hongminh54.marisaoptimize.systems.modules.render.Freecam;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.debug.ChunkBorderRenderer;
import net.minecraft.core.SectionPos;
import net.minecraft.util.Mth;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ChunkBorderRenderer.class)
public abstract class ChunkBorderDebugRendererMixin {
    @Shadow
    @Final
    private Minecraft minecraft;

    @ModifyExpressionValue(method = "emitGizmos", at = @At(value = "INVOKE", target = "Lnet/minecraft/core/SectionPos;of(Lnet/minecraft/core/BlockPos;)Lnet/minecraft/core/SectionPos;"))
    private SectionPos render$getChunkPos(SectionPos original) {
        Freecam freecam = Modules.get().get(Freecam.class);
        if (!freecam.isActive()) return original;

        float delta = minecraft.getDeltaTracker().getGameTimeDeltaPartialTick(true);

        return SectionPos.of(
            SectionPos.posToSectionCoord(Mth.floor(freecam.getX(delta))),
            SectionPos.posToSectionCoord(Mth.floor(freecam.getY(delta))),
            SectionPos.posToSectionCoord(Mth.floor(freecam.getZ(delta)))
        );
    }
}
