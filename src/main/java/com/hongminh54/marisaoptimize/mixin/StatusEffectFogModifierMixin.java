/*
 * This file is part of the Marisa Optimize distribution (https://github.com/TheBlueSkyARL/marisa-optimize).
 * Copyright (c) Meteor Development.
 */

package com.hongminh54.marisaoptimize.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.hongminh54.marisaoptimize.systems.modules.Modules;
import com.hongminh54.marisaoptimize.systems.modules.render.NoRender;
import net.minecraft.client.renderer.fog.environment.MobEffectFogEnvironment;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.core.Holder;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(MobEffectFogEnvironment.class)
public abstract class StatusEffectFogModifierMixin {
    @Shadow
    public abstract Holder<MobEffect> getMobEffect();

    @ModifyReturnValue(method = "isApplicable", at = @At("RETURN"))
    private boolean modifyShouldApply(boolean original) {
        NoRender noRender = Modules.get().get(NoRender.class);
        if (getMobEffect() == MobEffects.BLINDNESS) return original && !noRender.noBlindness();
        if (getMobEffect() == MobEffects.DARKNESS) return original && !noRender.noDarkness();
        return original;
    }
}
