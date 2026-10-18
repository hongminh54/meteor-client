/*
 * This file is part of the Marisa Optimize distribution (https://github.com/TheBlueSkyARL/marisa-optimize).
 * Copyright (c) Meteor Development.
 */

package com.hongminh54.marisaoptimize.utils.world;

import com.hongminh54.marisaoptimize.mixin.ClientChunkManagerAccessor;
import com.hongminh54.marisaoptimize.mixin.ClientChunkMapAccessor;
import net.minecraft.world.level.chunk.ChunkAccess;

import java.util.Iterator;

import static com.hongminh54.marisaoptimize.MarisaOptimize.mc;

public class ChunkIterator implements Iterator<ChunkAccess> {
    private final ClientChunkMapAccessor map = (ClientChunkMapAccessor) (Object) ((ClientChunkManagerAccessor) mc.level.getChunkSource()).meteor$getChunks();
    private final boolean onlyWithLoadedNeighbours;

    private int i = 0;
    private ChunkAccess chunk;

    public ChunkIterator(boolean onlyWithLoadedNeighbours) {
        this.onlyWithLoadedNeighbours = onlyWithLoadedNeighbours;

        getNext();
    }

    private ChunkAccess getNext() {
        ChunkAccess prev = chunk;
        chunk = null;

        while (i < map.meteor$getChunks().length()) {
            chunk = map.meteor$getChunks().get(i++);
            if (chunk != null && (!onlyWithLoadedNeighbours || isInRadius(chunk))) break;
        }

        return prev;
    }

    private boolean isInRadius(ChunkAccess chunk) {
        int x = chunk.getPos().x();
        int z = chunk.getPos().z();

        return mc.level.getChunkSource().hasChunk(x + 1, z) && mc.level.getChunkSource().hasChunk(x - 1, z) && mc.level.getChunkSource().hasChunk(x, z + 1) && mc.level.getChunkSource().hasChunk(x, z - 1);
    }

    @Override
    public boolean hasNext() {
        return chunk != null;
    }

    @Override
    public ChunkAccess next() {
        return getNext();
    }
}
