/*
 * This file is part of the Marisa Optimize distribution (https://github.com/TheBlueSkyARL/marisa-optimize).
 * Copyright (c) Meteor Development.
 */

package com.hongminh54.marisaoptimize.events.entity.player;

import com.hongminh54.marisaoptimize.events.Cancellable;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.InteractionHand;

public class InteractEntityEvent extends Cancellable {
    private static final InteractEntityEvent INSTANCE = new InteractEntityEvent();

    public Entity entity;
    public InteractionHand hand;

    public static InteractEntityEvent get(Entity entity, InteractionHand hand) {
        INSTANCE.setCancelled(false);
        INSTANCE.entity = entity;
        INSTANCE.hand = hand;
        return INSTANCE;
    }
}
