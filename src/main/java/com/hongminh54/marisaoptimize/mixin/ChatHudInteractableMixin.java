/*
 * This file is part of the Marisa Optimize distribution (https://github.com/TheBlueSkyARL/marisa-optimize).
 * Copyright (c) Meteor Development.
 */

package com.hongminh54.marisaoptimize.mixin;

import com.llamalad7.mixinextras.injector.ModifyReceiver;
import com.hongminh54.marisaoptimize.systems.modules.Modules;
import com.hongminh54.marisaoptimize.systems.modules.misc.BetterChat;
import net.minecraft.client.gui.TextAlignment;
import net.minecraft.client.gui.ActiveTextCollector;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.util.ARGB;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(targets = "net.minecraft.client.gui.components.ChatComponent$DrawingFocusedGraphicsAccess", remap = false)
public class ChatHudInteractableMixin {
    @Unique
    private static BetterChat betterChat;

    @Shadow
    @Final
    private GuiGraphicsExtractor graphics;

    @ModifyReceiver(method = "handleMessage", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/ActiveTextCollector;accept(Lnet/minecraft/client/gui/TextAlignment;IILnet/minecraft/client/gui/ActiveTextCollector$Parameters;Lnet/minecraft/util/FormattedCharSequence;)V"))
    private ActiveTextCollector onRender_beforeDrawTextWithShadow(ActiveTextCollector instance, TextAlignment alignment, int x, int y, ActiveTextCollector.Parameters transformation, FormattedCharSequence orderedText) {
        getBetterChat().beforeDrawMessage(graphics, y, ARGB.white(transformation.opacity()));
        return instance;
    }

    @ModifyArg(method = "handleMessage", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/ActiveTextCollector;accept(Lnet/minecraft/client/gui/TextAlignment;IILnet/minecraft/client/gui/ActiveTextCollector$Parameters;Lnet/minecraft/util/FormattedCharSequence;)V"), index = 1)
    private int modifyX(int x) {
        return getBetterChat().modifyChatWidth(x);
    }

    @Inject(method = "handleMessage", at = @At("TAIL"))
    private void onRender_afterDrawTextWithShadow(int y, float f, FormattedCharSequence text, CallbackInfoReturnable<Boolean> cir) {
        getBetterChat().afterDrawMessage();
    }

    @Unique
    private static BetterChat getBetterChat() {
        if (betterChat == null) {
            betterChat = Modules.get().get(BetterChat.class);
        }

        return betterChat;
    }
}
