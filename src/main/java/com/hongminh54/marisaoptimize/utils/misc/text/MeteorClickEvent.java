/*
 * This file is part of the Marisa Optimize distribution (https://github.com/TheBlueSkyARL/marisa-optimize).
 * Copyright (c) Meteor Development.
 */

package com.hongminh54.marisaoptimize.utils.misc.text;

import net.minecraft.network.chat.ClickEvent;

/**
 * This class does nothing except ensure that {@link ClickEvent}'s containing Marisa Optimize commands can only be executed if they come from the client.
 * The screen click handler must only dispatch this client-owned event type.
 */
public class MeteorClickEvent implements ClickEvent {
    public final String value;

    public MeteorClickEvent(String value) {
        this.value = value;
    }

    @Override
    public Action action() {
        return Action.RUN_COMMAND;
    }
}
