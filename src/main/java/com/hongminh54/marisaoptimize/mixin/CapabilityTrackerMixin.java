/*
 * This file is part of the Marisa Optimize distribution (https://github.com/TheBlueSkyARL/marisa-optimize).
 * Copyright (c) Meteor Development.
 */

package com.hongminh54.marisaoptimize.mixin;

import com.hongminh54.marisaoptimize.mixininterface.ICapabilityTracker;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(targets = "com.mojang.blaze3d.opengl.GlStateManager$BooleanState")
public abstract class CapabilityTrackerMixin implements ICapabilityTracker {
    @Shadow
    private boolean enabled;

    @Shadow
    public abstract void setEnabled(boolean state);

    @Override
    public boolean meteor$get() {
        return enabled;
    }

    @Override
    public void meteor$set(boolean state) {
        setEnabled(state);
    }
}
