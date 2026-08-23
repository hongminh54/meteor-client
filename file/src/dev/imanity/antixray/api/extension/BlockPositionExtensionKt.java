// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.api.extension;

import org.bukkit.Location;
import io.fairyproject.mc.util.Position;
import io.fairyproject.bukkit.util.CoordXZ;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import io.fairyproject.mc.util.BlockPosition;
import kotlin.Metadata;

@Metadata(mv = { 1, 9, 0 }, k = 2, xi = 48, d1 = { "\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0017\u001a,\u0010\u0005\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u0001H\u0087\u0002¢\u0006\u0004\b\u0005\u0010\u0006\u001a\u001b\u0010\t\u001a\u00020\b*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0007H\u0007¢\u0006\u0004\b\t\u0010\n\"\u0015\u0010\u000e\u001a\u00020\u0000*\u00020\u000b8G¢\u0006\u0006\u001a\u0004\b\f\u0010\r\"\u0015\u0010\u0012\u001a\u00020\u000f*\u00020\u00008G¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011\"\u0015\u0010\u0015\u001a\u00020\u0001*\u00020\u00008G¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014\"\u0015\u0010\u0017\u001a\u00020\u0001*\u00020\u00008G¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0014\"\u0015\u0010\u001a\u001a\u00020\u0000*\u00020\u00008G¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0019\"\u0015\u0010\u001c\u001a\u00020\u0000*\u00020\u00008G¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u0019\"\u0015\u0010\u001e\u001a\u00020\u0000*\u00020\u00008G¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u0019\"\u0015\u0010 \u001a\u00020\u0000*\u00020\u00008G¢\u0006\u0006\u001a\u0004\b\u001f\u0010\u0019\"\u0015\u0010\"\u001a\u00020\u0000*\u00020\u00008G¢\u0006\u0006\u001a\u0004\b!\u0010\u0019\"\u0015\u0010$\u001a\u00020\u0000*\u00020\u00008G¢\u0006\u0006\u001a\u0004\b#\u0010\u0019\"\u0015\u0010&\u001a\u00020\u0000*\u00020\u00008G¢\u0006\u0006\u001a\u0004\b%\u0010\u0019" }, d2 = { "Lio/fairyproject/mc/util/BlockPosition;", "", "p0", "p1", "p2", "get", "(Lio/fairyproject/mc/util/BlockPosition;III)Lio/fairyproject/mc/util/BlockPosition;", "", "Lio/fairyproject/mc/util/Position;", "toPosition", "(Lio/fairyproject/mc/util/BlockPosition;Ljava/lang/String;)Lio/fairyproject/mc/util/Position;", "Lorg/bukkit/Location;", "getBlockPosition", "(Lorg/bukkit/Location;)Lio/fairyproject/mc/util/BlockPosition;", "blockPosition", "Lio/fairyproject/bukkit/util/CoordXZ;", "getChunkCoords", "(Lio/fairyproject/mc/util/BlockPosition;)Lio/fairyproject/bukkit/util/CoordXZ;", "chunkCoords", "getChunkX", "(Lio/fairyproject/mc/util/BlockPosition;)I", "chunkX", "getChunkZ", "chunkZ", "getDown", "(Lio/fairyproject/mc/util/BlockPosition;)Lio/fairyproject/mc/util/BlockPosition;", "down", "getEast", "east", "getInChunkCoords", "inChunkCoords", "getNorth", "north", "getSouth", "south", "getUp", "up", "getWest", "west" })
public final class BlockPositionExtensionKt
{
    @NotNull
    public static final BlockPosition get(@NotNull final BlockPosition blockPosition, final int n, final int n2, final int n3) {
        Intrinsics.checkNotNullParameter((Object)blockPosition, "");
        return new BlockPosition(blockPosition.getX() + n, blockPosition.getY() + n2, blockPosition.getZ() + n3);
    }
    
    @JvmName(name = "getChunkX")
    public static final int getChunkX(@NotNull final BlockPosition blockPosition) {
        Intrinsics.checkNotNullParameter((Object)blockPosition, "");
        return blockPosition.getX() >> 4;
    }
    
    @JvmName(name = "getChunkZ")
    public static final int getChunkZ(@NotNull final BlockPosition blockPosition) {
        Intrinsics.checkNotNullParameter((Object)blockPosition, "");
        return blockPosition.getZ() >> 4;
    }
    
    @NotNull
    @JvmName(name = "getChunkCoords")
    public static final CoordXZ getChunkCoords(@NotNull final BlockPosition blockPosition) {
        Intrinsics.checkNotNullParameter((Object)blockPosition, "");
        return new CoordXZ(blockPosition.getX() >> 4, blockPosition.getZ() >> 4);
    }
    
    @NotNull
    @JvmName(name = "getInChunkCoords")
    public static final BlockPosition getInChunkCoords(@NotNull final BlockPosition blockPosition) {
        Intrinsics.checkNotNullParameter((Object)blockPosition, "");
        return new BlockPosition(blockPosition.getX() & 0xF, blockPosition.getY(), blockPosition.getZ() & 0xF);
    }
    
    @NotNull
    @JvmName(name = "getWest")
    public static final BlockPosition getWest(@NotNull final BlockPosition blockPosition) {
        Intrinsics.checkNotNullParameter((Object)blockPosition, "");
        return get(blockPosition, -1, 0, 0);
    }
    
    @NotNull
    @JvmName(name = "getEast")
    public static final BlockPosition getEast(@NotNull final BlockPosition blockPosition) {
        Intrinsics.checkNotNullParameter((Object)blockPosition, "");
        return get(blockPosition, 1, 0, 0);
    }
    
    @NotNull
    @JvmName(name = "getNorth")
    public static final BlockPosition getNorth(@NotNull final BlockPosition blockPosition) {
        Intrinsics.checkNotNullParameter((Object)blockPosition, "");
        return get(blockPosition, 0, 0, -1);
    }
    
    @NotNull
    @JvmName(name = "getSouth")
    public static final BlockPosition getSouth(@NotNull final BlockPosition blockPosition) {
        Intrinsics.checkNotNullParameter((Object)blockPosition, "");
        return get(blockPosition, 0, 0, 1);
    }
    
    @NotNull
    @JvmName(name = "getUp")
    public static final BlockPosition getUp(@NotNull final BlockPosition blockPosition) {
        Intrinsics.checkNotNullParameter((Object)blockPosition, "");
        return get(blockPosition, 0, 1, 0);
    }
    
    @NotNull
    @JvmName(name = "getDown")
    public static final BlockPosition getDown(@NotNull final BlockPosition blockPosition) {
        Intrinsics.checkNotNullParameter((Object)blockPosition, "");
        return get(blockPosition, 0, -1, 0);
    }
    
    @NotNull
    public static final Position toPosition(@NotNull final BlockPosition blockPosition, @NotNull final String s) {
        Intrinsics.checkNotNullParameter((Object)blockPosition, "");
        Intrinsics.checkNotNullParameter((Object)s, "");
        return new Position(s, (double)blockPosition.getX(), (double)blockPosition.getY(), (double)blockPosition.getZ());
    }
    
    @NotNull
    @JvmName(name = "getBlockPosition")
    public static final BlockPosition getBlockPosition(@NotNull final Location location) {
        Intrinsics.checkNotNullParameter((Object)location, "");
        return new BlockPosition(location.getBlockX(), location.getBlockY(), location.getBlockZ());
    }
}
