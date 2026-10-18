/*
 * This file is part of the Marisa Optimize distribution (https://github.com/TheBlueSkyARL/marisa-optimize).
 * Copyright (c) Meteor Development.
 */

package com.hongminh54.marisaoptimize.events.entity;

import net.minecraft.world.entity.Entity;

public class EntityAddedEvent {
    private static final EntityAddedEvent INSTANCE = new EntityAddedEvent();

    public Entity entity;

    public static EntityAddedEvent get(Entity entity) {
        INSTANCE.entity = entity;
        return INSTANCE;
    }
}
