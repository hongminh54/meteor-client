/*
 * This file is part of the Marisa Optimize distribution (https://github.com/TheBlueSkyARL/marisa-optimize).
 * Copyright (c) Meteor Development.
 */

package com.hongminh54.marisaoptimize.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.hongminh54.marisaoptimize.MarisaOptimize;
import com.hongminh54.marisaoptimize.events.render.GetFovEvent;
import com.hongminh54.marisaoptimize.mixininterface.ICamera;
import com.hongminh54.marisaoptimize.systems.modules.Modules;
import com.hongminh54.marisaoptimize.systems.modules.render.CameraTweaks;
import com.hongminh54.marisaoptimize.systems.modules.render.FreeLook;
import com.hongminh54.marisaoptimize.systems.modules.render.Freecam;
import com.hongminh54.marisaoptimize.systems.modules.render.NoRender;
import com.hongminh54.marisaoptimize.systems.modules.world.HighwayBuilder;
import net.minecraft.world.level.material.FogType;
import net.minecraft.client.Camera;
import net.minecraft.util.Mth;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(Camera.class)
public abstract class CameraMixin implements ICamera {
    @Shadow private boolean detached;

    @Shadow private float yRot;
    @Shadow private float xRot;

    @Shadow protected abstract void setRotation(float yaw, float pitch);

    @Inject(method = "getFluidInCamera", at = @At("HEAD"), cancellable = true)
    private void getSubmergedFluidState(CallbackInfoReturnable<FogType> ci) {
        if (Modules.get().get(NoRender.class).noLiquidOverlay()) ci.setReturnValue(FogType.NONE);
    }

    @ModifyVariable(method = "getMaxZoom", at = @At("HEAD"), ordinal = 0, argsOnly = true)
    private float modifyClipToSpace(float d) {
        if (Modules.get().get(Freecam.class).isActive()) return 0;

        CameraTweaks cameraTweaks = Modules.get().get(CameraTweaks.class);
        return cameraTweaks.isActive() ? (float) cameraTweaks.distance : d;
    }

    @Inject(method = "getMaxZoom", at = @At("HEAD"), cancellable = true)
    private void onClipToSpace(float desiredCameraDistance, CallbackInfoReturnable<Float> info) {
        if (Modules.get().get(CameraTweaks.class).clip()) {
            info.setReturnValue(desiredCameraDistance);
        }
    }

    @Inject(method = "alignWithEntity", at = @At("TAIL"))
    private void onUpdateTail(float tickProgress, CallbackInfo ci) {
        if (Modules.get().isActive(Freecam.class)) {
            this.detached = true;
        }
    }

    @ModifyArgs(method = "alignWithEntity", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/Camera;setPosition(DDD)V"))
    private void onUpdateSetPosArgs(Args args, @Local(argsOnly = true) float tickDelta) {
        Freecam freecam = Modules.get().get(Freecam.class);

        if (freecam.isActive()) {
            args.set(0, freecam.getX(tickDelta));
            args.set(1, freecam.getY(tickDelta));
            args.set(2, freecam.getZ(tickDelta));
        }
    }

    @ModifyArgs(method = "alignWithEntity", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/Camera;setRotation(FF)V"))
    private void onUpdateSetRotationArgs(Args args, @Local(argsOnly = true) float tickDelta) {
        Freecam freecam = Modules.get().get(Freecam.class);
        FreeLook freeLook = Modules.get().get(FreeLook.class);

        if (freecam.isActive()) {
            args.set(0, (float) freecam.getYaw(tickDelta));
            args.set(1, (float) freecam.getPitch(tickDelta));
        }
        else if (Modules.get().isActive(HighwayBuilder.class)) {
            args.set(0, yRot);
            args.set(1, xRot);
        }
        else if (freeLook.isActive()) {
            args.set(0, freeLook.cameraYaw);
            args.set(1, freeLook.cameraPitch);
        }
    }

    @Override
    public void meteor$setRot(double yaw, double pitch) {
        setRotation((float) yaw, (float) Mth.clamp(pitch, -90, 90));
    }

    @ModifyExpressionValue(method = "extractRenderState", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/player/LocalPlayer;isSpectator()Z"))
    private boolean meteor$disableSmartCullingInFreecam(boolean original) {
        return original || Modules.get().isActive(Freecam.class);
    }

    @ModifyReturnValue(method = "calculateFov", at = @At("RETURN"))
    private float meteor$modifyFov(float original) {
        return MarisaOptimize.EVENT_BUS.post(GetFovEvent.get(original)).fov;
    }
}
