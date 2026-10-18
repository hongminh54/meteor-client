/*
 * This file is part of the Marisa Optimize distribution (https://github.com/TheBlueSkyARL/marisa-optimize).
 * Copyright (c) Meteor Development.
 */

package com.hongminh54.marisaoptimize.mixininterface;

import net.minecraft.world.entity.Entity;

public interface IPlayerInteractEntityC2SPacket {
    InteractType meteor$getType();

    Entity meteor$getEntity();

    enum InteractType { ATTACK, INTERACT, INTERACT_AT }
}
