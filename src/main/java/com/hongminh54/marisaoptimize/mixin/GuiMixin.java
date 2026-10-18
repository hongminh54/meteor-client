/*
 * This file is part of the Marisa Optimize distribution (https://github.com/TheBlueSkyARL/marisa-optimize).
 * Copyright (c) Meteor Development.
 */

package com.hongminh54.marisaoptimize.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.hongminh54.marisaoptimize.MarisaOptimize;
import com.hongminh54.marisaoptimize.events.game.OpenScreenEvent;
import com.hongminh54.marisaoptimize.gui.WidgetScreen;
import com.hongminh54.marisaoptimize.systems.modules.Modules;
import com.hongminh54.marisaoptimize.systems.modules.movement.GUIMove;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.Options;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.screens.Screen;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Gui.class)
public abstract class GuiMixin {
    @Shadow @Final private Minecraft minecraft;

    @Inject(method = "setScreen", at = @At("HEAD"), cancellable = true)
    private void onSetScreen(Screen screen, CallbackInfo ci) {
        if (screen instanceof WidgetScreen) {
            screen.mouseMoved(minecraft.mouseHandler.getScaledXPos(minecraft.getWindow()), minecraft.mouseHandler.getScaledYPos(minecraft.getWindow()));
        }
        OpenScreenEvent event = MarisaOptimize.EVENT_BUS.post(OpenScreenEvent.get(screen));
        if (event.isCancelled()) ci.cancel();
    }

    @WrapOperation(method = "setScreen", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/KeyMapping;releaseAll()V"))
    private void onReleaseAll(Operation<Void> original) {
        Modules modules = Modules.get();
        if (modules == null) {
            original.call();
            return;
        }
        GUIMove guimove = modules.get(GUIMove.class);
        if (guimove == null || !guimove.isActive() || guimove.skip()) {
            original.call();
            return;
        }
        Options options = minecraft.options;
        for (KeyMapping kb : KeyBindingAccessor.getKeysById().values()) {
            if (kb == options.keyUp || kb == options.keyLeft || kb == options.keyRight || kb == options.keyDown) continue;
            if (guimove.sneak.get() && kb == options.keyShift) continue;
            if (guimove.sprint.get() && kb == options.keySprint) continue;
            if (guimove.jump.get() && kb == options.keyJump) continue;
            ((KeyBindingAccessor) kb).meteor$invokeReset();
        }
    }
}
