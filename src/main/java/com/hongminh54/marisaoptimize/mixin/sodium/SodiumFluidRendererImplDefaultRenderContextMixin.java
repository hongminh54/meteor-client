/*
 * This file is part of the Marisa Optimize distribution (https://github.com/TheBlueSkyARL/marisa-optimize).
 * Copyright (c) Meteor Development.
 */

package com.hongminh54.marisaoptimize.mixin.sodium;

import com.hongminh54.marisaoptimize.systems.modules.Modules;
import com.hongminh54.marisaoptimize.systems.modules.world.Ambience;
import com.hongminh54.marisaoptimize.utils.render.color.Color;
import net.caffeinemc.mods.sodium.api.util.ColorABGR;
import net.caffeinemc.mods.sodium.client.model.color.ColorProvider;
import net.caffeinemc.mods.sodium.client.model.quad.ModelQuadView;
import net.caffeinemc.mods.sodium.client.world.LevelSlice;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.tags.FluidTags;
import net.minecraft.core.BlockPos;
import net.minecraft.client.color.block.BlockTintSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Arrays;

@Mixin(targets = "net.caffeinemc.mods.sodium.fabric.render.FluidRendererImpl$DefaultRenderContext", remap = false)
public abstract class SodiumFluidRendererImplDefaultRenderContextMixin {
    @Unique
    private Ambience ambience;

    @Inject(method = "<init>", at = @At("TAIL"))
    private void onInit(CallbackInfo info) {
        ambience = Modules.get().get(Ambience.class);
    }

    @Inject(method = "getColorProvider", at = @At("HEAD"), cancellable = true)
    private void onGetColorProvider(Fluid fluid, BlockTintSource tintSource, CallbackInfoReturnable<ColorProvider<FluidState>> info) {
        if (ambience.isActive() && ambience.customLavaColor.get() && fluid.defaultFluidState().is(FluidTags.LAVA)) {
            info.setReturnValue(this::lavaColorProvider);
        }
    }

    @Unique
    private void lavaColorProvider(LevelSlice slice, BlockPos pos, BlockPos.MutableBlockPos scratchPos, FluidState state, ModelQuadView quad, int[] output, boolean smooth) {
        Color c = ambience.lavaColor.get();
        Arrays.fill(output, ColorABGR.pack(c.r, c.g, c.b, c.a));
    }
}
