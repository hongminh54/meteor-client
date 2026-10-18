/*
 * This file is part of the Marisa Optimize distribution (https://github.com/TheBlueSkyARL/marisa-optimize).
 * Copyright (c) Meteor Development.
 */

package com.hongminh54.marisaoptimize.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import com.hongminh54.marisaoptimize.systems.modules.Modules;
import com.hongminh54.marisaoptimize.systems.modules.movement.NoSlow;
import net.minecraft.world.level.block.WebBlock;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static com.hongminh54.marisaoptimize.MarisaOptimize.mc;

@Mixin(WebBlock.class)
public abstract class CobwebBlockMixin {
    @Inject(method = "entityInside", at = @At("HEAD"), cancellable = true)
    private void onEntityCollision(CallbackInfo ci, @Local(argsOnly = true) Entity entity) {
        if (entity == mc.player && Modules.get().get(NoSlow.class).cobweb()) ci.cancel();
    }
}
