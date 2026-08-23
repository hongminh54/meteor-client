// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.a;

import org.bukkit.block.Block;
import io.fairyproject.mc.util.BlockPosition;
import dev.imanity.antixray.api.util.LongHash;
import kotlin.jvm.internal.Intrinsics;
import io.fairyproject.bukkit.util.CoordXZ;
import org.jetbrains.annotations.NotNull;
import org.bukkit.Chunk;

public final class IIlIlIlllIIllIIIlllIIllIIllIIIlIIlIllIIllIllIIlllIIlIIlIIlllIIllIllIlIIlIlllIllIllIIlIIIllIIllIllIllIIllIIlllIllIIlllIlllIIllIIIlIIllIllIlllIllIIlIlllIIIllIIlIlllIIllIIIllIIlIllIIlIllIlllIIlllIIIlIlIlllIllIIllIlIllIll
{
    @NotNull
    public static final CoordXZ IlllIIlIIIlllIIIlIIllIlIllIIIlIIIllIlllIIIllIIlIIllIIllIlllIIllIIllIIllIIllIIlllIIlllIllIIllIlllIlIllIIlllIllIllIIlllIIllIIIlllIIIllIIIllIlllIIlllIIlIlIlIlIllIIIlllIIlIllIIllIIlllIIlIIIllIIIllIIlIIlllIlIllIllIIlIIIllIllIIlIIllIll(@NotNull final Chunk chunk) {
        Intrinsics.checkNotNullParameter((Object)chunk, "");
        return new CoordXZ(chunk.getX(), chunk.getZ());
    }
    
    public static final long IIllIIIllIllIIIlllIIIlllIIllIIlllIlIIIllIllIlIIlIIIlllIIlIIllIIllIlIllIIllIIIllIIlIllIIlIIlIIllIIlIlIIllIllIIlIlIIlIIIllIIlIllIllIIlllIllIIlIlllIIllIlIlllIIlllIIIlIIlIIllIlllIlIIllIIIllIlIlIIlIIllIllIlIllIIIllIIll(@NotNull final Chunk chunk) {
        Intrinsics.checkNotNullParameter((Object)chunk, "");
        return LongHash.INSTANCE.toLong(chunk.getX(), chunk.getZ());
    }
    
    @NotNull
    public static final Block IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(@NotNull final Chunk chunk, @NotNull final BlockPosition blockPosition) {
        Intrinsics.checkNotNullParameter((Object)chunk, "");
        Intrinsics.checkNotNullParameter((Object)blockPosition, "");
        final Block block = chunk.getBlock(blockPosition.getX(), blockPosition.getY(), blockPosition.getZ());
        Intrinsics.checkNotNullExpressionValue((Object)block, "");
        return block;
    }
}
