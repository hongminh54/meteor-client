/*
 * This file is part of the Marisa Optimize distribution (https://github.com/TheBlueSkyARL/marisa-optimize).
 * Copyright (c) Meteor Development.
 */

package com.hongminh54.marisaoptimize.mixin.indigo;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.hongminh54.marisaoptimize.systems.modules.Modules;
import com.hongminh54.marisaoptimize.systems.modules.render.Xray;
import net.fabricmc.fabric.impl.client.indigo.renderer.render.AltModelBlockRendererImpl;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.client.renderer.block.BlockAndTintGetter;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(AltModelBlockRendererImpl.class)
public abstract class BlockRenderInfoMixin {
    @Shadow
    private BlockState blockState;

    @Shadow
    private BlockAndTintGetter level;

    @Shadow
    private BlockPos pos;

    @ModifyReturnValue(method = "shouldCullFace", at = @At("RETURN"))
    private boolean modifyShouldDrawSide(boolean original, Direction side) {
        if (side == null) return original;
        Xray xray = Modules.get().get(Xray.class);

        if (xray.isActive()) {
            return !xray.modifyDrawSide(blockState, level, pos, side, !original);
        }

        return original;
    }
}
