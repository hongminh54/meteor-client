// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.api.data;

import dev.imanity.antixray.api.util.Vec3d;
import dev.imanity.antixray.api.RaytraceDebugger;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.Nullable;
import dev.imanity.antixray.api.chunk.DirectChunk;
import org.jetbrains.annotations.NotNull;
import dev.imanity.antixray.api.chunk.IDirectChunkCache;
import kotlin.Metadata;

@Metadata(mv = { 1, 9, 0 }, k = 1, xi = 48, d1 = { "\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u000f\u0010\t\u001a\u00020\bH\u0016¢\u0006\u0004\b\t\u0010\nJ'\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0003\u001a\u00020\u000b2\u0006\u0010\u0005\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000bH\u0016¢\u0006\u0004\b\u000e\u0010\u000fJ\u001f\u0010\u0010\u001a\u00020\r2\u0006\u0010\u0003\u001a\u00020\u000b2\u0006\u0010\u0005\u001a\u00020\u000bH\u0016¢\u0006\u0004\b\u0010\u0010\u0011R\u0018\u0010\u0013\u001a\u0004\u0018\u00010\u00128\u0002@\u0002X\u0083\u000e¢\u0006\u0006\n\u0004\b\u0013\u0010\u0014R\u0014\u0010\u0015\u001a\u00020\u00028\u0002X\u0083\u0004¢\u0006\u0006\n\u0004\b\u0015\u0010\u0016R\u0014\u0010\u0017\u001a\u00020\u00048\u0002X\u0083\u0004¢\u0006\u0006\n\u0004\b\u0017\u0010\u0018" }, d2 = { "Ldev/imanity/antixray/api/data/DirectDataProvider;", "Ldev/imanity/antixray/api/data/DataProvider;", "Ldev/imanity/antixray/api/chunk/IDirectChunkCache;", "p0", "Ldev/imanity/antixray/api/data/IMaterialDataHandler;", "p1", "<init>", "(Ldev/imanity/antixray/api/chunk/IDirectChunkCache;Ldev/imanity/antixray/api/data/IMaterialDataHandler;)V", "", "cleanup", "()V", "", "p2", "", "isOpaqueFullCube", "(III)Z", "prepareChunk", "(II)Z", "Ldev/imanity/antixray/api/chunk/DirectChunk;", "chunk", "Ldev/imanity/antixray/api/chunk/DirectChunk;", "chunkCache", "Ldev/imanity/antixray/api/chunk/IDirectChunkCache;", "materialDataHandler", "Ldev/imanity/antixray/api/data/IMaterialDataHandler;" })
public final class DirectDataProvider implements DataProvider
{
    @NotNull
    private final IDirectChunkCache chunkCache;
    @NotNull
    private final IMaterialDataHandler materialDataHandler;
    @Nullable
    private DirectChunk chunk;
    
    public DirectDataProvider(@NotNull final IDirectChunkCache chunkCache, @NotNull final IMaterialDataHandler materialDataHandler) {
        Intrinsics.checkNotNullParameter((Object)chunkCache, "");
        Intrinsics.checkNotNullParameter((Object)materialDataHandler, "");
        this.chunkCache = chunkCache;
        this.materialDataHandler = materialDataHandler;
    }
    
    @Override
    public boolean prepareChunk(final int n, final int n2) {
        if (this.chunk != null) {
            final DirectChunk chunk = this.chunk;
            if (chunk != null && chunk.getX() == n) {
                final DirectChunk chunk2 = this.chunk;
                if (chunk2 != null && chunk2.getZ() == n2) {
                    return true;
                }
            }
        }
        final DirectChunk value = this.chunkCache.get(n, n2);
        boolean b;
        if (value != null) {
            this.chunk = value;
            b = true;
        }
        else {
            b = false;
        }
        return b;
    }
    
    @Override
    public boolean isOpaqueFullCube(final int n, final int n2, final int n3) {
        final DirectChunk chunk = this.chunk;
        if (chunk == null) {
            throw new IllegalStateException("Chunk not prepared. (impossible desync)");
        }
        final DirectChunk directChunk = chunk;
        if (RaytraceDebugger.INSTANCE.isActive() && (n >> 4 != directChunk.getX() || n3 >> 4 != directChunk.getZ())) {
            throw new IllegalStateException("Chunk desync. (impossible desync) x:" + (n >> 4) + "!=" + directChunk.getX() + ", z:" + (n3 >> 4) + "!=" + directChunk.getZ());
        }
        final int blockId = directChunk.getBlockIdAt(n & 0xF, n2, n3 & 0xF);
        switch (this.materialDataHandler.cullingTransparencyCached(blockId)) {
            case 1: {
                return true;
            }
            case 2: {
                return false;
            }
            default: {
                final boolean transparent = this.materialDataHandler.isTransparent(directChunk.getBlockAt(n & 0xF, n2, n3 & 0xF));
                this.materialDataHandler.cacheCullingTransparency(blockId, transparent);
                return !transparent;
            }
        }
    }
    
    @Override
    public void cleanup() {
        this.chunk = null;
    }
    
    @Override
    public void checkingPosition(@Nullable final Vec3d[] array, final int n, @Nullable final Vec3d vec3d) {
        DefaultImpls.checkingPosition(this, array, n, vec3d);
    }
}
