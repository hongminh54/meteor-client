/*
 * This file is part of the Marisa Optimize distribution (https://github.com/TheBlueSkyARL/marisa-optimize).
 * Copyright (c) Meteor Development.
 */

package com.hongminh54.marisaoptimize.mixin;

import com.hongminh54.marisaoptimize.mixininterface.IText;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.locale.Language;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(MutableComponent.class)
public abstract class MutableTextMixin implements IText {
    @Shadow
    private @Nullable Language decomposedWith;

    @Override
    public void meteor$invalidateCache() {
        this.decomposedWith = null;
    }
}
