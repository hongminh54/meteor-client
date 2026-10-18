/*
 * This file is part of the Marisa Optimize distribution (https://github.com/TheBlueSkyARL/marisa-optimize).
 * Copyright (c) Meteor Development.
 */

package com.hongminh54.marisaoptimize.mixin;

import net.minecraft.client.ResourceLoadStateTracker;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(ResourceLoadStateTracker.class)
public interface ResourceReloadLoggerAccessor {
    @Accessor("reloadState")
    ResourceLoadStateTracker.ReloadState meteor$getReloadState();
}
