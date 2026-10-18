/*
 * This file is part of the Marisa Optimize distribution (https://github.com/TheBlueSkyARL/marisa-optimize).
 * Copyright (c) Meteor Development.
 */

package com.hongminh54.marisaoptimize.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import com.hongminh54.marisaoptimize.systems.modules.Modules;
import com.hongminh54.marisaoptimize.systems.modules.render.ESP;
import com.hongminh54.marisaoptimize.systems.modules.render.NoRender;
import com.hongminh54.marisaoptimize.systems.modules.world.Ambience;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.WeatherEffectRenderer;
import net.minecraft.client.renderer.WorldBorderRenderer;
import net.minecraft.client.renderer.extract.LevelExtractor;
import net.minecraft.client.renderer.state.level.WeatherRenderState;
import net.minecraft.client.renderer.state.level.WorldBorderRenderState;
import net.minecraft.world.level.border.WorldBorder;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static com.hongminh54.marisaoptimize.MarisaOptimize.mc;

@Mixin(LevelExtractor.class)
public abstract class LevelExtractorMixin {
    @WrapWithCondition(method = "extract", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/WeatherEffectRenderer;extractRenderState(Lnet/minecraft/client/multiplayer/ClientLevel;FLnet/minecraft/world/phys/Vec3;Lnet/minecraft/client/renderer/state/level/WeatherRenderState;)V"))
    private boolean shouldRenderPrecipitation(WeatherEffectRenderer renderer, ClientLevel level, float partialTicks, Vec3 pos, WeatherRenderState state) {
        if (Modules.get().get(NoRender.class).noWeather()) {
            state.intensity = 0;
            return false;
        }
        return true;
    }

    @WrapWithCondition(method = "extract", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/WorldBorderRenderer;extract(Lnet/minecraft/world/level/border/WorldBorder;FLnet/minecraft/world/phys/Vec3;DLnet/minecraft/client/renderer/state/level/WorldBorderRenderState;)V"))
    private boolean shouldRenderWorldBorder(WorldBorderRenderer renderer, WorldBorder border, float partialTicks, Vec3 pos, double distance, WorldBorderRenderState state) {
        if (Modules.get().get(NoRender.class).noWorldBorder()) {
            state.alpha = 0;
            return false;
        }
        return true;
    }

    @ModifyExpressionValue(method = "isEntityVisible", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/LevelRenderer;isSectionCompiledAndVisible(Lnet/minecraft/core/BlockPos;)Z"))
    private boolean forceRender(boolean original) {
        return Modules.get().get(ESP.class).forceRender() || original;
    }

    @Inject(method = "extract", at = @At(value = "FIELD", target = "Lnet/minecraft/client/renderer/state/level/LevelRenderState;cloudColor:I", opcode = org.objectweb.asm.Opcodes.PUTFIELD, shift = At.Shift.AFTER))
    private void modifyCloudColor(CallbackInfo ci) {
        Ambience ambience = Modules.get().get(Ambience.class);
        if (ambience.isActive() && ambience.customCloudColor.get()) {
            mc.gameRenderer.gameRenderState().levelRenderState.cloudColor = ambience.cloudColor.get().getPacked();
        }
    }
}
