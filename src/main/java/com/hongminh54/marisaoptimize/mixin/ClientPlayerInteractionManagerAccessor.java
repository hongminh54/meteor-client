/*
 * This file is part of the Marisa Optimize distribution (https://github.com/TheBlueSkyARL/marisa-optimize).
 * Copyright (c) Meteor Development.
 */

package com.hongminh54.marisaoptimize.mixin;

import net.minecraft.client.multiplayer.MultiPlayerGameMode;
import net.minecraft.core.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(MultiPlayerGameMode.class)
public interface ClientPlayerInteractionManagerAccessor {
    @Accessor("destroyProgress")
    float meteor$getBreakingProgress();

    @Accessor("destroyProgress")
    void meteor$setCurrentBreakingProgress(float progress);

    @Accessor("destroyBlockPos")
    BlockPos meteor$getCurrentBreakingBlockPos();
}
