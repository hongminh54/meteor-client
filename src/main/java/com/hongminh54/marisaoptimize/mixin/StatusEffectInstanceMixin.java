/*
 * This file is part of the Marisa Optimize distribution (https://github.com/TheBlueSkyARL/marisa-optimize).
 * Copyright (c) Meteor Development.
 */

package com.hongminh54.marisaoptimize.mixin;

import com.hongminh54.marisaoptimize.systems.modules.Modules;
import com.hongminh54.marisaoptimize.systems.modules.player.PotionSaver;
import com.hongminh54.marisaoptimize.utils.Utils;
import net.minecraft.world.effect.MobEffectInstance;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MobEffectInstance.class)
public abstract class StatusEffectInstanceMixin {
    @Inject(method = "tickDownDuration", at = @At("HEAD"), cancellable = true)
    private void tick(CallbackInfo info) {
        if (!Utils.canUpdate()) return;

        if (Modules.get().get(PotionSaver.class).shouldFreeze(((MobEffectInstance) (Object) this).getEffect().value())) {
            info.cancel();
        }
    }
}
