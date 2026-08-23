// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.api;

import org.bukkit.World;
import kotlin.jvm.internal.Intrinsics;
import dev.imanity.antixray.api.chunk.IDirectChunkManager;
import org.bukkit.block.Block;
import dev.imanity.antixray.api.chunk.DirectChunk;
import io.fairyproject.libs.packetevents.protocol.world.states.WrappedBlockState;
import org.bukkit.Location;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;
import org.bukkit.entity.Player;
import kotlin.Metadata;

@Metadata(mv = { 1, 9, 0 }, k = 1, xi = 48, d1 = { "\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u001a\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0016J\"\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\t2\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H&J\b\u0010\n\u001a\u00020\u000bH&J\u0012\u0010\f\u001a\u0004\u0018\u00010\r2\u0006\u0010\u000e\u001a\u00020\u000fH&J\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u000e\u001a\u00020\u000fH&J\u0010\u0010\u0012\u001a\u00020\u00032\u0006\u0010\u0013\u001a\u00020\u0014H&J\u0010\u0010\u0015\u001a\u00020\u00032\u0006\u0010\u0016\u001a\u00020\u0017H&J\u0018\u0010\u0015\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0016\u001a\u00020\u0017H\u0016J\u0018\u0010\u0018\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0019\u001a\u00020\u0011H&¨\u0006\u001a" }, d2 = { "Ldev/imanity/antixray/api/AntiXrayApiInstance;", "", "addRaytraceBlock", "", "location", "Lorg/bukkit/Location;", "replacement", "Lio/fairyproject/libs/packetevents/protocol/world/states/WrappedBlockState;", "directChunk", "Ldev/imanity/antixray/api/chunk/DirectChunk;", "getDirectChunkManager", "Ldev/imanity/antixray/api/chunk/IDirectChunkManager;", "getRaytracePlayer", "Ldev/imanity/antixray/api/IRaytracePlayer;", "player", "Lorg/bukkit/entity/Player;", "isBypassed", "", "queueBlockChange", "block", "Lorg/bukkit/block/Block;", "scheduleTask", "runnable", "Ljava/lang/Runnable;", "setBypassed", "value", "api" })
public interface AntiXrayApiInstance
{
    @Nullable
    IRaytracePlayer getRaytracePlayer(@NotNull final Player p0);
    
    boolean isBypassed(@NotNull final Player p0);
    
    void setBypassed(@NotNull final Player p0, final boolean p1);
    
    void addRaytraceBlock(@NotNull final Location p0);
    
    void addRaytraceBlock(@NotNull final Location p0, @Nullable final WrappedBlockState p1);
    
    void addRaytraceBlock(@NotNull final Location p0, @NotNull final DirectChunk p1, @Nullable final WrappedBlockState p2);
    
    void queueBlockChange(@NotNull final Block p0);
    
    @NotNull
    IDirectChunkManager getDirectChunkManager();
    
    void scheduleTask(@NotNull final Runnable p0);
    
    void scheduleTask(@NotNull final Player p0, @NotNull final Runnable p1);
    
    @Metadata(mv = { 1, 9, 0 }, k = 3, xi = 48)
    public static final class DefaultImpls
    {
        public static void addRaytraceBlock(@NotNull final AntiXrayApiInstance antiXrayApiInstance, @NotNull final Location location) {
            Intrinsics.checkNotNullParameter((Object)location, "");
            antiXrayApiInstance.addRaytraceBlock(location, null);
        }
        
        public static void addRaytraceBlock(@NotNull final AntiXrayApiInstance antiXrayApiInstance, @NotNull final Location location, @Nullable final WrappedBlockState wrappedBlockState) {
            Intrinsics.checkNotNullParameter((Object)location, "");
            final IDirectChunkManager directChunkManager = antiXrayApiInstance.getDirectChunkManager();
            final World world = location.getWorld();
            Intrinsics.checkNotNullExpressionValue((Object)world, "");
            antiXrayApiInstance.addRaytraceBlock(location, directChunkManager.get(world).load(location.getBlockX() >> 4, location.getBlockZ() >> 4), wrappedBlockState);
        }
        
        public static void scheduleTask(@NotNull final AntiXrayApiInstance antiXrayApiInstance, @NotNull final Player player, @NotNull final Runnable runnable) {
            Intrinsics.checkNotNullParameter((Object)player, "");
            Intrinsics.checkNotNullParameter((Object)runnable, "");
            antiXrayApiInstance.scheduleTask(runnable);
        }
    }
}
