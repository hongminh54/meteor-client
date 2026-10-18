/*
 * This file is part of the Marisa Optimize distribution (https://github.com/TheBlueSkyARL/marisa-optimize).
 * Copyright (c) Meteor Development.
 */

package com.hongminh54.marisaoptimize.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import com.hongminh54.marisaoptimize.MarisaOptimize;
import com.hongminh54.marisaoptimize.events.world.PlaySoundEvent;
import com.hongminh54.marisaoptimize.systems.modules.Modules;
import com.hongminh54.marisaoptimize.systems.modules.misc.SoundBlocker;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.client.sounds.SoundEngine;
import net.minecraft.client.resources.sounds.TickableSoundInstance;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SoundEngine.class)
public abstract class SoundSystemMixin {
    @Shadow
    public abstract void stop(SoundInstance sound);

    @Inject(method = "play(Lnet/minecraft/client/resources/sounds/SoundInstance;)Lnet/minecraft/client/sounds/SoundEngine$PlayResult;", at = @At("HEAD"), cancellable = true)
    private void onPlay(SoundInstance soundInstance, CallbackInfoReturnable<SoundEngine.PlayResult> cir) {
        PlaySoundEvent event = MarisaOptimize.EVENT_BUS.post(PlaySoundEvent.get(soundInstance));

        if (event.isCancelled()) cir.setReturnValue(SoundEngine.PlayResult.NOT_STARTED);
    }

    @Inject(method = "tickInGameSound()V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/resources/sounds/TickableSoundInstance;tick()V", ordinal = 0))
    private void onTick(CallbackInfo ci, @Local(name = "instance") TickableSoundInstance tickableSoundInstance) {
        if (Modules.get().get(SoundBlocker.class).shouldBlock(tickableSoundInstance)) stop(tickableSoundInstance);
    }
}
