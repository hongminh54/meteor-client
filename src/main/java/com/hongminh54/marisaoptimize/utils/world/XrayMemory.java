/*
 * This file is part of the Marisa Optimize distribution (https://github.com/TheBlueSkyARL/marisa-optimize).
 * Copyright (c) Meteor Development.
 */

package com.hongminh54.marisaoptimize.utils.world;

import com.hongminh54.marisaoptimize.mixin.ClientChunkManagerAccessor;
import com.hongminh54.marisaoptimize.mixin.ClientChunkMapAccessor;
import it.unimi.dsi.fastutil.longs.Long2ObjectLinkedOpenHashMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectLinkedOpenHashMap;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.chunk.LevelChunk;

import java.util.function.Predicate;

import static com.hongminh54.marisaoptimize.MarisaOptimize.mc;

/**
 * Stores block states that were revealed by the server through block updates
 * (anti-xray servers only send real data as per-block updates, not in chunk data).
 */
public class XrayMemory {
    private static final int MAX_BLOCKS_PER_CHUNK = 256;
    private static final int MAX_CHUNKS = 512;

    private static final Long2ObjectLinkedOpenHashMap<Object2ObjectLinkedOpenHashMap<Long, BlockState>> chunks = new Long2ObjectLinkedOpenHashMap<>();

    private XrayMemory() {
    }

    public static void capture(BlockPos pos, BlockState state, Predicate<Block> isWhitelisted) {
        if (mc.level == null) return;

        if (state.isAir()) {
            remove(pos);
            return;
        }

        if (!isWhitelisted.test(state.getBlock())) return;

        put(ChunkPos.pack(pos.getX() >> 4, pos.getZ() >> 4), pos.asLong(), state);
    }

    /**
     * Captures all whitelisted blocks of a chunk whose data is trusted
     * (classified as not obfuscated by {@link XrayNoiseFilter}).
     */
    public static int captureChunk(LevelChunk chunk, Predicate<Block> isWhitelisted) {
        if (mc.level == null || chunk == null) return 0;

        long chunkKey = chunk.getPos().pack();
        int captured = 0;
        BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();
        int startX = chunk.getPos().getMinBlockX();
        int startZ = chunk.getPos().getMinBlockZ();
        int bottomY = mc.level.getMinY();
        int topY = bottomY + mc.level.getHeight();

        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                for (int y = bottomY; y < topY; y++) {
                    pos.set(startX + x, y, startZ + z);
                    BlockState state = chunk.getBlockState(pos);
                    if (!state.isAir() && isWhitelisted.test(state.getBlock())) {
                        put(chunkKey, pos.asLong(), state);
                        captured++;
                    }
                }
            }
        }

        return captured;
    }

    private static void put(long chunkKey, long posKey, BlockState state) {
        Object2ObjectLinkedOpenHashMap<Long, BlockState> map = chunks.computeIfAbsent(chunkKey, k -> {
            if (chunks.size() >= MAX_CHUNKS) chunks.removeFirst();
            return new Object2ObjectLinkedOpenHashMap<>();
        });

        if (map.size() >= MAX_BLOCKS_PER_CHUNK) map.removeFirst();
        map.put(posKey, state);
    }

    public static void remove(BlockPos pos) {
        Object2ObjectLinkedOpenHashMap<Long, BlockState> map = chunks.get(ChunkPos.pack(pos.getX() >> 4, pos.getZ() >> 4));
        if (map != null) {
            map.remove(pos.asLong());
            if (map.isEmpty()) chunks.remove(ChunkPos.pack(pos.getX() >> 4, pos.getZ() >> 4));
        }
    }

    public static BlockState get(BlockPos pos) {
        if (mc.level == null) return null;

        Object2ObjectLinkedOpenHashMap<Long, BlockState> map = chunks.get(ChunkPos.pack(pos.getX() >> 4, pos.getZ() >> 4));
        return map == null ? null : map.get(pos.asLong());
    }

    public static boolean isRemembered(BlockPos pos) {
        return get(pos) != null;
    }

    public static boolean chunkHasMemory(BlockPos pos) {
        Object2ObjectLinkedOpenHashMap<Long, BlockState> map = chunks.get(ChunkPos.pack(pos.getX() >> 4, pos.getZ() >> 4));
        return map != null && !map.isEmpty();
    }

    /**
     * Restores remembered blocks that the server re-obfuscated and forgets
     * positions that are now really air. Returns the number of restored blocks.
     */
    public static int applyToChunk(LevelChunk chunk) {
        if (chunk == null || mc.level == null) return 0;

        Object2ObjectLinkedOpenHashMap<Long, BlockState> map = chunks.get(chunk.getPos().pack());
        if (map == null) return 0;

        int applied = 0;

        for (var it = map.object2ObjectEntrySet().fastIterator(); it.hasNext(); ) {
            var entry = it.next();
            BlockPos pos = BlockPos.of(entry.getKey().longValue());
            BlockState remembered = entry.getValue();
            BlockState current = chunk.getBlockState(pos);

            if (current.isAir()) {
                it.remove();
            } else if (current != remembered) {
                chunk.setBlockState(pos, remembered, 0);
                applied++;
            }
        }

        if (map.isEmpty()) chunks.remove(chunk.getPos().pack());

        return applied;
    }

    public static int applyAllLoaded() {
        if (mc.level == null) return 0;

        ClientChunkMapAccessor map = (ClientChunkMapAccessor) (Object) ((ClientChunkManagerAccessor) mc.level.getChunkSource()).meteor$getChunks();
        int applied = 0;

        for (int i = 0; i < map.meteor$getChunks().length(); i++) {
            LevelChunk chunk = map.meteor$getChunks().get(i);
            if (chunk != null) applied += applyToChunk(chunk);
        }

        return applied;
    }

    public static void clearChunk(ChunkPos chunkPos) {
        chunks.remove(chunkPos.pack());
    }

    public static void clearAll() {
        chunks.clear();
    }

    public static int getChunkCount() {
        return chunks.size();
    }

    public static int getBlockCount() {
        int count = 0;
        for (var map : chunks.values()) count += map.size();
        return count;
    }
}
