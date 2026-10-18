/*
 * This file is part of the Marisa Optimize distribution (https://github.com/TheBlueSkyARL/marisa-optimize).
 * Copyright (c) Meteor Development.
 */

package com.hongminh54.marisaoptimize.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import com.llamalad7.mixinextras.sugar.Local;
import com.hongminh54.marisaoptimize.MarisaOptimize;
import com.hongminh54.marisaoptimize.events.entity.player.DoAttackEvent;
import com.hongminh54.marisaoptimize.events.entity.player.DoItemUseEvent;
import com.hongminh54.marisaoptimize.events.entity.player.ItemUseCrosshairTargetEvent;
import com.hongminh54.marisaoptimize.events.game.GameLeftEvent;
import com.hongminh54.marisaoptimize.events.game.ResolutionChangedEvent;
import com.hongminh54.marisaoptimize.events.game.ResourcePacksReloadedEvent;
import com.hongminh54.marisaoptimize.events.world.TickEvent;
import com.hongminh54.marisaoptimize.mixininterface.IMinecraftClient;
import com.hongminh54.marisaoptimize.systems.config.Config;
import com.hongminh54.marisaoptimize.systems.modules.Modules;
import com.hongminh54.marisaoptimize.systems.modules.misc.InventoryTweaks;
import com.hongminh54.marisaoptimize.systems.modules.player.FastUse;
import com.hongminh54.marisaoptimize.systems.modules.player.Multitask;
import com.hongminh54.marisaoptimize.systems.modules.render.ESP;
import com.hongminh54.marisaoptimize.systems.modules.world.HighwayBuilder;
import com.hongminh54.marisaoptimize.utils.Utils;
import com.hongminh54.marisaoptimize.utils.misc.CPSUtils;
import com.hongminh54.marisaoptimize.utils.misc.MeteorStarscript;
import com.hongminh54.marisaoptimize.utils.network.OnlinePlayers;
import net.minecraft.client.Minecraft;
import com.mojang.blaze3d.pipeline.RenderTarget;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.multiplayer.MultiPlayerGameMode;
import net.minecraft.client.Options;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.HitResult;
import net.minecraft.util.profiling.Profiler;
import org.jetbrains.annotations.Nullable;
import org.meteordev.starscript.Script;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.concurrent.CompletableFuture;

@Mixin(value = Minecraft.class, priority = 1001)
public abstract class MinecraftClientMixin implements IMinecraftClient {
    @Unique private boolean doItemUseCalled;
    @Unique private boolean rightClick;
    @Unique private long lastTime;
    @Unique private boolean firstFrame;

    @Shadow public ClientLevel level;
    @Shadow @Final public Options options;

    @Shadow protected abstract void startUseItem();

    @Shadow
    @Nullable
    public MultiPlayerGameMode gameMode;

    @Shadow
    private int rightClickDelay;

    @Shadow
    @Nullable
    public LocalPlayer player;

    @Shadow
    protected abstract void continueAttack(boolean breaking);

    @Inject(method = "<init>", at = @At("TAIL"))
    private void onInit(CallbackInfo info) {
        MarisaOptimize.INSTANCE.onInitializeClient();
        firstFrame = true;
    }

    @Inject(at = @At("HEAD"), method = "tick")
    private void onPreTick(CallbackInfo info) {
        OnlinePlayers.update();

        doItemUseCalled = false;

        Profiler.get().push(MarisaOptimize.MOD_ID + "_pre_update");
        MarisaOptimize.EVENT_BUS.post(TickEvent.Pre.get());
        Profiler.get().pop();

        if (rightClick && !doItemUseCalled && gameMode != null) startUseItem();
        rightClick = false;
    }

    @Inject(at = @At("TAIL"), method = "tick")
    private void onTick(CallbackInfo info) {
        Profiler.get().push(MarisaOptimize.MOD_ID + "_post_update");
        MarisaOptimize.EVENT_BUS.post(TickEvent.Post.get());
        Profiler.get().pop();
    }

    @Inject(method = "startAttack", at = @At("HEAD"), cancellable = true)
    private void onAttack(CallbackInfoReturnable<Boolean> cir) {
        CPSUtils.onAttack();
        if (MarisaOptimize.EVENT_BUS.post(DoAttackEvent.get()).isCancelled()) cir.cancel();
    }

    @Inject(method = "startUseItem", at = @At("HEAD"))
    private void onDoItemUse(CallbackInfo info) {
        doItemUseCalled = true;
    }

    @Inject(method = "disconnect(Lnet/minecraft/client/gui/screens/Screen;ZZ)V", at = @At("HEAD"))
    private void onDisconnect(Screen screen, boolean transferring, boolean stopSound, CallbackInfo info) {
        if (level != null) {
            MarisaOptimize.EVENT_BUS.post(GameLeftEvent.get());
        }
    }

    @Inject(method = "startUseItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;isItemEnabled(Lnet/minecraft/world/flag/FeatureFlagSet;)Z"))
    private void onDoItemUseHand(CallbackInfo ci, @Local ItemStack itemStack) {
        FastUse fastUse = Modules.get().get(FastUse.class);
        if (fastUse.isActive()) {
            rightClickDelay = fastUse.getItemUseCooldown(itemStack);
        }
    }

    @Inject(method = "startUseItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/InteractionHand;values()[Lnet/minecraft/world/InteractionHand;"), cancellable = true)
    private void onDoItemUseBeforeHands(CallbackInfo ci) {
        if (MarisaOptimize.EVENT_BUS.post(DoItemUseEvent.get()).isCancelled()) ci.cancel();
    }

    @ModifyExpressionValue(method = "startUseItem", at = @At(value = "FIELD", target = "Lnet/minecraft/client/Minecraft;hitResult:Lnet/minecraft/world/phys/HitResult;", ordinal = 1))
    private HitResult doItemUseMinecraftClientCrosshairTargetProxy(HitResult original) {
        return MarisaOptimize.EVENT_BUS.post(ItemUseCrosshairTargetEvent.get(original)).target;
    }

    @ModifyReturnValue(method = "reloadResourcePacks(ZLnet/minecraft/client/GameLoadCookie;)Ljava/util/concurrent/CompletableFuture;", at = @At("RETURN"))
    private CompletableFuture<Void> onReloadResourcesNewCompletableFuture(CompletableFuture<Void> original) {
        return original.thenRun(() -> MarisaOptimize.EVENT_BUS.post(ResourcePacksReloadedEvent.get()));
    }

    @ModifyArg(method = "updateTitle", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/platform/Window;setTitle(Ljava/lang/String;)V"))
    private String setTitle(String original) {
        if (Config.get() == null || !Config.get().customWindowTitle.get()) return original;

        String customTitle = Config.get().customWindowTitleText.get();
        Script script = MeteorStarscript.compile(customTitle);

        if (script != null) {
            String title = MeteorStarscript.run(script);
            if (title != null) customTitle = title;
        }

        return customTitle;
    }

    // Have to add this condition if we want to draw back a bow using packets, without it getting cancelled by vanilla code
    @WrapWithCondition(method = "handleKeybinds", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/multiplayer/MultiPlayerGameMode;releaseUsingItem(Lnet/minecraft/world/entity/player/Player;)V"))
    private boolean wrapStopUsing(MultiPlayerGameMode instance, Player player) {
        return HB$stopUsingItem();
    }

    @Unique
    private boolean HB$stopUsingItem() {
        HighwayBuilder b = Modules.get().get(HighwayBuilder.class);
        return !b.isActive() || !b.drawingBow;
    }

    @Inject(method = "resizeGui", at = @At("TAIL"))
    private void onResolutionChanged(CallbackInfo info) {
        MarisaOptimize.EVENT_BUS.post(ResolutionChangedEvent.get());
    }

    // Time delta

    @Inject(method = "runTick", at = @At("HEAD"))
    private void onRender(CallbackInfo info) {
        long time = System.currentTimeMillis();

        if (firstFrame) {
            lastTime = time;
            firstFrame = false;
        }

        Utils.frameTime = (time - lastTime) / 1000.0;
        lastTime = time;
    }

    // Multitask

    @ModifyExpressionValue(method = "startUseItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/multiplayer/MultiPlayerGameMode;isDestroying()Z"))
    private boolean doItemUseModifyIsBreakingBlock(boolean original) {
        return !Modules.get().isActive(Multitask.class) && original;
    }

    @ModifyExpressionValue(method = "continueAttack", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/player/LocalPlayer;isUsingItem()Z"))
    private boolean handleBlockBreakingModifyIsUsingItem(boolean original) {
        return !Modules.get().isActive(Multitask.class) && original;
    }

    @ModifyExpressionValue(method = "handleKeybinds", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/player/LocalPlayer;isUsingItem()Z", ordinal = 0))
    private boolean handleInputEventsModifyIsUsingItem(boolean original) {
        return !Modules.get().get(Multitask.class).attackingEntities() && original;
    }

    @Inject(method = "handleKeybinds", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/player/LocalPlayer;isUsingItem()Z", ordinal = 0, shift = At.Shift.BEFORE))
    private void handleInputEventsInjectStopUsingItem(CallbackInfo info) {
        if (Modules.get().get(Multitask.class).attackingEntities() && player.isUsingItem()) {
            if (!options.keyUse.isDown() && HB$stopUsingItem()) gameMode.releaseUsingItem(player);
            //noinspection StatementWithEmptyBody
            while (options.keyUse.consumeClick());
        }
    }

    // Glow esp

    @ModifyReturnValue(method = "shouldEntityAppearGlowing", at = @At("RETURN"))
    private boolean hasOutlineModifyIsOutline(boolean original, Entity entity) {
        ESP esp = Modules.get().get(ESP.class);
        if (esp == null) return original;
        if (!esp.isGlow() || esp.shouldSkip(entity)) return original;

        return esp.getColor(entity) != null || original;
    }


    // faster inputs

    @Unique
    private boolean isBreaking = false;

    @WrapWithCondition(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/Minecraft;handleKeybinds()V"))
    private boolean wrapHandleInputEvents(Minecraft instance) {
        return !Modules.get().get(InventoryTweaks.class).frameInput();
    }

    @WrapWithCondition(method = "handleKeybinds", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/Minecraft;continueAttack(Z)V"))
    private boolean wrapHandleBlockBreaking(Minecraft instance, boolean breaking) {
        isBreaking = breaking;
        return !Modules.get().get(InventoryTweaks.class).frameInput();
    }

    @Inject(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/Minecraft;handleKeybinds()V", shift = At.Shift.AFTER))
    private void afterHandleInputEvents(CallbackInfo ci) {
        if (!Modules.get().get(InventoryTweaks.class).frameInput()) return;

        continueAttack(isBreaking);
        isBreaking = false;
    }

    // Interface

    @Override
    public void meteor$rightClick() {
        rightClick = true;
    }

}
