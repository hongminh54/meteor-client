/*
 * This file is part of the Marisa Optimize distribution (https://github.com/TheBlueSkyARL/marisa-optimize).
 * Copyright (c) Meteor Development.
 */

package com.hongminh54.marisaoptimize.mixin;

import com.hongminh54.marisaoptimize.MarisaOptimize;
import com.hongminh54.marisaoptimize.events.meteor.CharTypedEvent;
import com.hongminh54.marisaoptimize.events.meteor.KeyEvent;
import com.hongminh54.marisaoptimize.gui.GuiKeyEvents;
import com.hongminh54.marisaoptimize.gui.WidgetScreen;
import com.hongminh54.marisaoptimize.utils.Utils;
import com.hongminh54.marisaoptimize.utils.misc.input.Input;
import com.hongminh54.marisaoptimize.utils.misc.input.KeyAction;
import net.minecraft.client.KeyboardHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.input.CharacterEvent;
import org.lwjgl.glfw.GLFW;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(KeyboardHandler.class)
public abstract class KeyboardMixin {
    @Shadow @Final private Minecraft minecraft;

    @Inject(method = "keyPress", at = @At("HEAD"), cancellable = true)
    public void onKey(long window, int action, net.minecraft.client.input.KeyEvent input, CallbackInfo ci) {
        int modifiers = input.modifiers();
        if (input.key() != GLFW.GLFW_KEY_UNKNOWN) {
            // on Linux/X11 the modifier is not active when the key is pressed and still active when the key is released
            // https://github.com/glfw/glfw/issues/1630
            if (action == GLFW.GLFW_PRESS) {
                modifiers |= Input.getModifier(input.key());
            } else if (action == GLFW.GLFW_RELEASE) {
                modifiers &= ~Input.getModifier(input.key());
            }

            if (minecraft.gui.screen() instanceof WidgetScreen screen && action == GLFW.GLFW_REPEAT) {
                screen.keyRepeated(new net.minecraft.client.input.KeyEvent(input.key(), input.scancode(), modifiers));
            }

            if (GuiKeyEvents.canUseKeys) {
                Input.setKeyState(input.key(), action != GLFW.GLFW_RELEASE);
                if (MarisaOptimize.EVENT_BUS.post(KeyEvent.get(new net.minecraft.client.input.KeyEvent(input.key(), input.scancode(), modifiers), KeyAction.get(action))).isCancelled()) ci.cancel();
            }
        }
    }

    @Inject(method = "charTyped", at = @At("HEAD"), cancellable = true)
    private void onChar(long window, CharacterEvent input, CallbackInfo ci) {
        if (Utils.canUpdate() && !minecraft.isPaused() && (minecraft.gui.screen() == null || minecraft.gui.screen() instanceof WidgetScreen)) {
            if (MarisaOptimize.EVENT_BUS.post(CharTypedEvent.get((char) input.codepoint())).isCancelled()) ci.cancel();
        }
    }
}
