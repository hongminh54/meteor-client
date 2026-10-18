/*
 * This file is part of the Marisa Optimize distribution (https://github.com/TheBlueSkyARL/marisa-optimize).
 * Copyright (c) Meteor Development.
 */

package com.hongminh54.marisaoptimize.events.game;

import com.hongminh54.marisaoptimize.events.Cancellable;
import net.minecraft.client.CameraType;

public class ChangePerspectiveEvent extends Cancellable {
    private static final ChangePerspectiveEvent INSTANCE = new ChangePerspectiveEvent();

    public CameraType perspective;

    public static ChangePerspectiveEvent get(CameraType perspective) {
        INSTANCE.setCancelled(false);
        INSTANCE.perspective = perspective;
        return INSTANCE;
    }
}
