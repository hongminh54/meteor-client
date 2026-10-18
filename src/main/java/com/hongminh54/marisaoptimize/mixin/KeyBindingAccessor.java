/*
 * This file is part of the Marisa Optimize distribution (https://github.com/TheBlueSkyARL/marisa-optimize).
 * Copyright (c) Meteor Development.
 */

package com.hongminh54.marisaoptimize.mixin;

import net.minecraft.client.KeyMapping;
import com.mojang.blaze3d.platform.InputConstants;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.Map;

@Mixin(KeyMapping.class)
public interface KeyBindingAccessor {
    @Accessor("ALL")
    static Map<String, KeyMapping> getKeysById() { return null; }

    @Accessor("key")
    InputConstants.Key meteor$getKey();

    @Accessor("clickCount")
    int meteor$getTimesPressed();

    @Accessor("clickCount")
    void meteor$setTimesPressed(int timesPressed);

    @Invoker("release")
    void meteor$invokeReset();
}
