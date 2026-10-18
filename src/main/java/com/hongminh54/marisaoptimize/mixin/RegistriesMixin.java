/*
 * This file is part of the Marisa Optimize distribution (https://github.com/TheBlueSkyARL/marisa-optimize).
 * Copyright (c) Meteor Development.
 */

package com.hongminh54.marisaoptimize.mixin;

import net.minecraft.core.registries.BuiltInRegistries;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.function.Supplier;

@Mixin(BuiltInRegistries.class)
public abstract class RegistriesMixin {
    @Redirect(method = "internalRegister", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/Bootstrap;checkBootstrapCalled(Ljava/util/function/Supplier;)V"))
    private static void ignoreBootstrap(Supplier<String> callerGetter) {
        // nothing
    }
}
