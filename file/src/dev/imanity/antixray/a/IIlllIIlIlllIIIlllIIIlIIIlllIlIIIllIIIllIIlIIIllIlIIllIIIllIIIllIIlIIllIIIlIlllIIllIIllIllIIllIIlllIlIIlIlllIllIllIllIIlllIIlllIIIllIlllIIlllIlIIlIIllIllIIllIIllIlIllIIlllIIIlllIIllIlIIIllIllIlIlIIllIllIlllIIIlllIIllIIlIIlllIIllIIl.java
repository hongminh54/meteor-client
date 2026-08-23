// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.a;

import dev.imanity.antixray.nms.WrappedBlockStateCache;
import io.fairyproject.libs.packetevents.protocol.world.chunk.impl.v1_7.Chunk_v1_7;
import io.fairyproject.libs.packetevents.protocol.world.chunk.impl.v1_8.Chunk_v1_8;
import io.fairyproject.libs.packetevents.protocol.world.chunk.impl.v1_16.Chunk_v1_9;
import io.fairyproject.libs.packetevents.protocol.world.chunk.impl.v_1_18.Chunk_v1_18;
import kotlin.jvm.internal.Intrinsics;
import io.fairyproject.libs.packetevents.protocol.world.states.WrappedBlockState;
import org.jetbrains.annotations.NotNull;
import io.fairyproject.libs.packetevents.protocol.world.chunk.BaseChunk;

public final class IIlllIIlIlllIIIlllIIIlIIIlllIlIIIllIIIllIIlIIIllIlIIllIIIllIIIllIIlIIllIIIlIlllIIllIIllIllIIllIIlllIlIIlIlllIllIllIllIIlllIIlllIIIllIlllIIlllIlIIlIIllIllIIllIIllIlIllIIlllIIIlllIIllIlIIIllIllIlIlIIllIllIlllIIIlllIIllIIlIIlllIIllIIl
{
    @NotNull
    public static final WrappedBlockState IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(@NotNull final BaseChunk baseChunk, final int n, final int n2, final int n3) {
        Intrinsics.checkNotNullParameter((Object)baseChunk, "");
        int value;
        if (baseChunk instanceof Chunk_v1_18) {
            value = ((Chunk_v1_18)baseChunk).getChunkData().get(n, n2, n3);
        }
        else {
            if (baseChunk instanceof Chunk_v1_9) {
                final WrappedBlockState value2 = baseChunk.get(n, n2, n3);
                Intrinsics.checkNotNullExpressionValue((Object)value2, "");
                return value2;
            }
            if (baseChunk instanceof Chunk_v1_8) {
                final int value3 = ((Chunk_v1_8)baseChunk).getBlocks().get(n, n2, n3);
                value = ((value3 >> 4 == 51) ? 0 : value3);
            }
            else {
                if (!(baseChunk instanceof Chunk_v1_7)) {
                    throw new UnsupportedOperationException("Unsupported chunk version " + baseChunk.getClass().getName());
                }
                value = (((Chunk_v1_7)baseChunk).getBlocks().get(n, n2, n3) | ((Chunk_v1_7)baseChunk).getExtendedBlocks().get(n, n2, n3) << 12);
            }
        }
        return WrappedBlockStateCache.INSTANCE.get(value);
    }
    
    public static final int IllIIllIlIlIllIIllIIIllIIIllIIIllIIIlIlIlIllIlIIllIIlllIlllIIlIIIllIIlIllIIlllIIllIllIIIlIIlIlIlllIIIlllIIlIIIllIIIllIIIllIlIIlllIIlIlIIIlllIIlIlIIIlllIIlllIIlIllIllIIlIIIllIIllIlllIIllIIllIllIIllIIllIIlIIllIllIIllIIlIIl(final int n, final int n2) {
        return n + n2 * 16;
    }
    
    public static final boolean IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(@NotNull final boolean[] array, final int n, final int n2) {
        Intrinsics.checkNotNullParameter((Object)array, "");
        return array[IllIIllIlIlIllIIllIIIllIIIllIIIllIIIlIlIlIllIlIIllIIlllIlllIIlIIIllIIlIllIIlllIIllIllIIIlIIlIlIlllIIIlllIIlIIIllIIIllIIIllIlIIlllIIlIlIIIlllIIlIlIIIlllIIlllIIlIllIllIIlIIIllIIllIlllIIllIIllIllIIllIIllIIlIIllIllIIllIIlIIl(n, n2)];
    }
}
