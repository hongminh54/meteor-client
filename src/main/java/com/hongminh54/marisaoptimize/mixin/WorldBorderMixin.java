/*
 * This file is part of the Marisa Optimize distribution (https://github.com/TheBlueSkyARL/marisa-optimize).
 * Copyright (c) Meteor Development.
 */

package com.hongminh54.marisaoptimize.mixin;

import com.hongminh54.marisaoptimize.systems.modules.Modules;
import com.hongminh54.marisaoptimize.systems.modules.world.Collisions;
import net.minecraft.world.level.border.WorldBorder;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(WorldBorder.class)
public abstract class WorldBorderMixin {
    @Inject(method = "isInsideCloseToBorder", at = @At("HEAD"), cancellable = true)
    private void canCollide(CallbackInfoReturnable<Boolean> info) {
        if (Modules.get().get(Collisions.class).ignoreBorder()) info.setReturnValue(false);
    }

    @Inject(method = "isWithinBounds(Lnet/minecraft/core/BlockPos;)Z", at = @At("HEAD"), cancellable = true)
    private void contains(CallbackInfoReturnable<Boolean> info) {
        if (Modules.get().get(Collisions.class).ignoreBorder()) info.setReturnValue(true);
    }
}
