/*
 * This file is part of the Marisa Optimize distribution (https://github.com/TheBlueSkyARL/marisa-optimize).
 * Copyright (c) Meteor Development.
 */

package com.hongminh54.marisaoptimize.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import com.llamalad7.mixinextras.sugar.Local;
import com.mojang.authlib.GameProfile;
import com.hongminh54.marisaoptimize.MarisaOptimize;
import com.hongminh54.marisaoptimize.events.entity.DropItemsEvent;
import com.hongminh54.marisaoptimize.events.entity.player.PlayerTickMovementEvent;
import com.hongminh54.marisaoptimize.events.entity.player.SendMovementPacketsEvent;
import com.hongminh54.marisaoptimize.systems.modules.Modules;
import com.hongminh54.marisaoptimize.systems.modules.player.LiquidInteract;
import com.hongminh54.marisaoptimize.systems.modules.player.NoMiningTrace;
import com.hongminh54.marisaoptimize.systems.modules.player.Portals;
import com.hongminh54.marisaoptimize.utils.entity.fakeplayer.FakePlayerEntity;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.player.ClientInput;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.PlayerRideableJumping;
import net.minecraft.world.entity.player.Input;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import com.hongminh54.marisaoptimize.systems.modules.movement.*;

@Mixin(LocalPlayer.class)
public abstract class ClientPlayerEntityMixin extends AbstractClientPlayer {
    @Shadow
    public ClientInput input;

    public ClientPlayerEntityMixin(ClientLevel world, GameProfile profile) {
        super(world, profile);
    }

    @Inject(method = "drop", at = @At("HEAD"), cancellable = true)
    private void onDropSelectedItem(boolean dropEntireStack, CallbackInfoReturnable<Boolean> info) {
        if (MarisaOptimize.EVENT_BUS.post(DropItemsEvent.get(getMainHandItem())).isCancelled()) info.setReturnValue(false);
    }

    @ModifyExpressionValue(method = "handlePortalTransitionEffect", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/Gui;screen()Lnet/minecraft/client/gui/screens/Screen;", ordinal = 0))
    private Screen modifyNauseaCurrentScreen(Screen original) {
        if (Modules.get().isActive(Portals.class)) return null;
        return original;
    }

    @ModifyExpressionValue(method = "modifyInput", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/player/LocalPlayer;isUsingItem()Z"))
    private boolean redirectUsingItem(boolean isUsingItem) {
        if (Modules.get().get(NoSlow.class).items()) return false;
        return isUsingItem;
    }

    @Inject(method = "isShiftKeyDown", at = @At("HEAD"), cancellable = true)
    private void onIsSneaking(CallbackInfoReturnable<Boolean> info) {
        if (Modules.get().get(Scaffold.class).scaffolding()) info.setReturnValue(false);
        if (Modules.get().get(Flight.class).noSneak()) info.setReturnValue(false);
    }

    @Inject(method = "isMovingSlowly", at = @At("HEAD"), cancellable = true)
    private void onShouldSlowDown(CallbackInfoReturnable<Boolean> info) {
        if (Modules.get().get(NoSlow.class).sneaking()) {
            info.setReturnValue(isVisuallyCrawling());
        }
    }

    @Inject(method = "moveTowardsClosestSpace", at = @At("HEAD"), cancellable = true)
    private void onPushOutOfBlocks(double x, double d, CallbackInfo info) {
        Velocity velocity = Modules.get().get(Velocity.class);
        if (velocity.isActive() && velocity.blocks.get()) {
            info.cancel();
        }
    }

    @ModifyExpressionValue(method = "tick", at = @At(value = "FIELD", target = "Lnet/minecraft/client/player/ClientInput;keyPresses:Lnet/minecraft/world/entity/player/Input;", opcode = Opcodes.GETFIELD))
    private Input isSneaking(Input original) {
        if (Modules.get().get(Sneak.class).doPacket() || Modules.get().get(NoSlow.class).airStrict()) {
            return new Input(
                original.forward(),
                original.backward(),
                original.left(),
                original.right(),
                original.jump(),
                true,
                original.sprint()
            );
        }
        return original;
    }

    @Inject(method = "aiStep", at = @At("HEAD"))
    private void preTickMovement(CallbackInfo ci) {
        MarisaOptimize.EVENT_BUS.post(PlayerTickMovementEvent.get());
    }

    @ModifyReturnValue(method = "getJumpRidingScale", at = @At("RETURN"))
    private float modifyMountJumpStrength(float original) {
        if (Modules.get().get(EntityControl.class).maxJump()) return 1f;
        return original;
    }

    @Inject(method = "jumpableVehicle", at = @At("RETURN"), cancellable = true)
    private void changeJumpingMount(CallbackInfoReturnable<PlayerRideableJumping> info) {
        if (Modules.get().get(EntityControl.class).cancelJump()) info.setReturnValue(null);
    }

    @ModifyReturnValue(method = "pick", at = @At("RETURN"))
    private static HitResult onUpdateTargetedEntity(HitResult original, @Local(name = "blockHitResult") HitResult hitResult) {
        if (original instanceof EntityHitResult ehr) {
            if (Modules.get().get(NoMiningTrace.class).canWork(ehr.getEntity()) && hitResult.getType() == HitResult.Type.BLOCK) {
                return hitResult;
            }
            else if (ehr.getEntity() instanceof FakePlayerEntity fakePlayer && fakePlayer.noHit) {
                return hitResult;
            }
        }

        return original;
    }

    @ModifyExpressionValue(method = "pick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/Entity;pick(DFZ)Lnet/minecraft/world/phys/HitResult;"))
    private static HitResult modifyRaycastResult(HitResult original, @Local(argsOnly = true, name = "cameraEntity") Entity entity, @Local(argsOnly = true, name = "partialTicks") float tickProgress, @Local(name = "maxDistance") double maxDistance) {
        if (!Modules.get().isActive(LiquidInteract.class)) return original;
        if (original.getType() != HitResult.Type.MISS) return original;

        return entity.pick(maxDistance, tickProgress, true);
    }

    // Sprint

    @ModifyExpressionValue(method = "canStartSprinting", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/player/ClientInput;hasForwardImpulse()Z"))
    private boolean modifyIsWalking(boolean original) {
        if (!Modules.get().get(Sprint.class).rageSprint()) return original;

        float forwards = Math.abs(zza);
        float sideways = Math.abs(xxa);

        return (isUnderWater() ? (forwards > 1.0E-5F || sideways > 1.0E-5F) : (forwards > 0.8 || sideways > 0.8));
    }

    @ModifyExpressionValue(method = "aiStep", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/player/ClientInput;hasForwardImpulse()Z"))
    private boolean modifyMovement(boolean original) {
        if (!Modules.get().get(Sprint.class).rageSprint()) return original;

        return Math.abs(xxa) > 1.0E-5F || Math.abs(zza) > 1.0E-5F;
    }

    @WrapWithCondition(method = "aiStep", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/player/LocalPlayer;setSprinting(Z)V", ordinal = 3))
    private boolean wrapSetSprinting(LocalPlayer instance, boolean b) {
        Sprint s = Modules.get().get(Sprint.class);

        return !s.rageSprint() || s.unsprintInWater() && isInWater();
    }

    // Rotations

    @Inject(method = "sendPosition", at = @At("HEAD"))
    private void onSendMovementPacketsHead(CallbackInfo info) {
        MarisaOptimize.EVENT_BUS.post(SendMovementPacketsEvent.Pre.get());
    }

    @Inject(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/multiplayer/ClientPacketListener;send(Lnet/minecraft/network/protocol/Packet;)V", ordinal = 1))
    private void onTickHasVehicleBeforeSendPackets(CallbackInfo info) {
        MarisaOptimize.EVENT_BUS.post(SendMovementPacketsEvent.Pre.get());
    }

    @Inject(method = "sendPosition", at = @At("TAIL"))
    private void onSendMovementPacketsTail(CallbackInfo info) {
        MarisaOptimize.EVENT_BUS.post(SendMovementPacketsEvent.Post.get());
    }

    @Inject(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/multiplayer/ClientPacketListener;send(Lnet/minecraft/network/protocol/Packet;)V", ordinal = 1, shift = At.Shift.AFTER))
    private void onTickHasVehicleAfterSendPackets(CallbackInfo info) {
        MarisaOptimize.EVENT_BUS.post(SendMovementPacketsEvent.Post.get());
    }
}
