/*
 * This file is part of the Marisa Optimize distribution (https://github.com/TheBlueSkyARL/marisa-optimize).
 * Copyright (c) Meteor Development.
 */

package com.hongminh54.marisaoptimize.mixin;

import com.hongminh54.marisaoptimize.systems.config.Config;
import net.minecraft.client.gui.components.SplashRenderer;
import net.minecraft.client.resources.SplashManager;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;
import java.util.Random;

@Mixin(SplashManager.class)
public abstract class SplashTextResourceSupplierMixin {
    @Unique
    private boolean override = true;
    @Unique
    private static final Random random = new Random();
    @Unique
    private final List<String> meteorSplashes = getMeteorSplashes();

    @Inject(method = "getSplash", at = @At("HEAD"), cancellable = true)
    private void onApply(CallbackInfoReturnable<SplashRenderer> cir) {
        if (Config.get() == null || !Config.get().titleScreenSplashes.get()) return;

        if (override) cir.setReturnValue(new SplashRenderer(Component.literal(meteorSplashes.get(random.nextInt(meteorSplashes.size())))));
        override = !override;
    }

    @Unique
    private static List<String> getMeteorSplashes() {
        return List.of(
                "§4Marisa Optimize!",
                "§6Marisa Optimize!"
        );
    }

}
