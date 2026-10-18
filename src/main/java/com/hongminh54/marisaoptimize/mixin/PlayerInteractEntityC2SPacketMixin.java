/*
 * This file is part of the Marisa Optimize distribution (https://github.com/TheBlueSkyARL/marisa-optimize).
 * Copyright (c) Meteor Development.
 */

package com.hongminh54.marisaoptimize.mixin;

import com.hongminh54.marisaoptimize.mixininterface.IPlayerInteractEntityC2SPacket;
import com.hongminh54.marisaoptimize.systems.modules.Modules;
import com.hongminh54.marisaoptimize.systems.modules.movement.NoSlow;
import com.hongminh54.marisaoptimize.systems.modules.movement.Sneak;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.protocol.game.ServerboundInteractPacket;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import static com.hongminh54.marisaoptimize.MarisaOptimize.mc;

@Mixin(ServerboundInteractPacket.class)
public abstract class PlayerInteractEntityC2SPacketMixin implements IPlayerInteractEntityC2SPacket {
    @Shadow @Final private int entityId;

    @Override
    public InteractType meteor$getType() {
        return InteractType.INTERACT_AT;
    }

    @Override
    public Entity meteor$getEntity() {
        return mc.level == null ? null : mc.level.getEntity(entityId);
    }

    @ModifyVariable(method = "<init>(ILnet/minecraft/world/InteractionHand;Lnet/minecraft/world/phys/Vec3;Z)V", at = @At("HEAD"), ordinal = 0, argsOnly = true)
    private static boolean setSneaking(boolean sneaking) {
        return Modules.get().get(Sneak.class).doPacket() || Modules.get().get(NoSlow.class).airStrict() || sneaking;
    }
}
