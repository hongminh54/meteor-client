/*
 * This file is part of the Marisa Optimize distribution (https://github.com/TheBlueSkyARL/marisa-optimize).
 * Copyright (c) Meteor Development.
 */

package com.hongminh54.marisaoptimize.mixin;

import com.hongminh54.marisaoptimize.mixininterface.IPlayerInteractEntityC2SPacket;
import net.minecraft.network.protocol.game.ServerboundAttackPacket;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import static com.hongminh54.marisaoptimize.MarisaOptimize.mc;

@Mixin(ServerboundAttackPacket.class)
public abstract class ServerboundAttackPacketMixin implements IPlayerInteractEntityC2SPacket {
    @Shadow @Final private int entityId;

    @Override
    public InteractType meteor$getType() {
        return InteractType.ATTACK;
    }

    @Override
    public Entity meteor$getEntity() {
        return mc.level == null ? null : mc.level.getEntity(entityId);
    }
}
