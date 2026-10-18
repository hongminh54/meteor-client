/*
 * This file is part of the Marisa Optimize distribution (https://github.com/TheBlueSkyARL/marisa-optimize).
 * Copyright (c) Meteor Development.
 */

package com.hongminh54.marisaoptimize.events.world;

import net.minecraft.world.level.chunk.LevelChunk;

/**
 * @implNote Shouldn't be put in a {@link com.hongminh54.marisaoptimize.utils.misc.Pool} to avoid a race-condition, or in a {@link ThreadLocal} as it is shared between threads.
 * @author Crosby
 */
public record ChunkDataEvent(LevelChunk chunk) {}
