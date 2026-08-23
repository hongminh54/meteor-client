// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.api;

import org.jetbrains.annotations.Nullable;
import io.fairyproject.bukkit.util.CoordXZ;
import java.util.Set;
import io.fairyproject.libs.packetevents.protocol.world.states.WrappedBlockState;
import dev.imanity.antixray.api.block.RaytraceBlock;
import io.fairyproject.mc.util.BlockPosition;
import dev.imanity.antixray.api.chunk.DirectChunk;
import org.jetbrains.annotations.NotNull;
import org.bukkit.entity.Player;
import kotlin.Metadata;
import java.util.concurrent.Executor;

@Metadata(mv = { 1, 9, 0 }, k = 1, xi = 48, d1 = { "\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0005\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H&J \u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001aH&J\b\u0010\u001b\u001a\u00020\u001cH&J\u000e\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001f0\u001eH&J\u0012\u0010 \u001a\u0004\u0018\u00010\u00142\u0006\u0010\u0017\u001a\u00020\u0018H&J\u0012\u0010!\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u0017\u001a\u00020\u0018H&J\u0010\u0010\"\u001a\u00020\u001c2\u0006\u0010\u0017\u001a\u00020\u0018H&J\u0018\u0010#\u001a\u00020\u001c2\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H&R\u0018\u0010\u0002\u001a\u00020\u0003X¦\u000e¢\u0006\f\u001a\u0004\b\u0004\u0010\u0005\"\u0004\b\u0006\u0010\u0007R\u0012\u0010\b\u001a\u00020\tX¦\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0018\u0010\f\u001a\u00020\u0003X¦\u000e¢\u0006\f\u001a\u0004\b\r\u0010\u0005\"\u0004\b\u000e\u0010\u0007R\u0012\u0010\u000f\u001a\u00020\u0010X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012¨\u0006$" }, d2 = { "Ldev/imanity/antixray/api/IRaytracePlayer;", "Ljava/util/concurrent/Executor;", "lastMovedTicks", "", "getLastMovedTicks", "()I", "setLastMovedTicks", "(I)V", "player", "Lorg/bukkit/entity/Player;", "getPlayer", "()Lorg/bukkit/entity/Player;", "ticks", "getTicks", "setTicks", "vision", "Ldev/imanity/antixray/api/Vision;", "getVision", "()Ldev/imanity/antixray/api/Vision;", "addTraceBlock", "Ldev/imanity/antixray/api/block/RaytraceBlock;", "chunk", "Ldev/imanity/antixray/api/chunk/DirectChunk;", "blockPosition", "Lio/fairyproject/mc/util/BlockPosition;", "replacement", "Lio/fairyproject/libs/packetevents/protocol/world/states/WrappedBlockState;", "clear", "", "getLoadedChunkCoords", "", "Lio/fairyproject/bukkit/util/CoordXZ;", "getTraceBlock", "getTraceBlockUnsafe", "queueRemoveBlock", "removeTraceBlock", "api" })
public interface IRaytracePlayer extends Executor
{
    @NotNull
    Player getPlayer();
    
    @NotNull
    Vision getVision();
    
    int getTicks();
    
    void setTicks(final int p0);
    
    int getLastMovedTicks();
    
    void setLastMovedTicks(final int p0);
    
    @NotNull
    RaytraceBlock addTraceBlock(@NotNull final DirectChunk p0, @NotNull final BlockPosition p1);
    
    @NotNull
    RaytraceBlock addTraceBlock(@NotNull final DirectChunk p0, @NotNull final BlockPosition p1, @NotNull final WrappedBlockState p2);
    
    @NotNull
    Set<CoordXZ> getLoadedChunkCoords();
    
    @Nullable
    RaytraceBlock getTraceBlock(@NotNull final BlockPosition p0);
    
    @Nullable
    RaytraceBlock getTraceBlockUnsafe(@NotNull final BlockPosition p0);
    
    void queueRemoveBlock(@NotNull final BlockPosition p0);
    
    void removeTraceBlock(@NotNull final DirectChunk p0, @NotNull final BlockPosition p1);
    
    void clear();
}
