/*
 * This file is part of the Marisa Optimize distribution (https://github.com/TheBlueSkyARL/marisa-optimize).
 * Copyright (c) Meteor Development.
 */

package com.hongminh54.marisaoptimize.events.entity.player;

import com.hongminh54.marisaoptimize.events.Cancellable;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.phys.BlockHitResult;

public class InteractBlockEvent extends Cancellable {
    private static final InteractBlockEvent INSTANCE = new InteractBlockEvent();

    public InteractionHand hand;
    public BlockHitResult result;

    public static InteractBlockEvent get(InteractionHand hand, BlockHitResult result) {
        INSTANCE.setCancelled(false);
        INSTANCE.hand = hand;
        INSTANCE.result = result;
        return INSTANCE;
    }
}
