// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.api.chunk;

import java.util.Arrays;
import org.jetbrains.annotations.Nullable;
import kotlin.jvm.internal.ArrayIteratorKt;
import java.util.Iterator;
import io.fairyproject.bukkit.util.CoordXZ;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.Metadata;
import kotlin.jvm.internal.markers.KMappedMarker;

@Metadata(mv = { 1, 9, 0 }, k = 1, xi = 48, d1 = { "\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0011\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010(\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0010\b\u0087\b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0015\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u0016\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00020\u0003H\u00c7\u0003¢\u0006\u0004\b\u0007\u0010\bJ\u001f\u0010\u000b\u001a\u00020\t2\u0006\u0010\u0004\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tH\u0002¢\u0006\u0004\b\u000b\u0010\fJ \u0010\r\u001a\u00020\u00002\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00020\u0003H\u00c7\u0001¢\u0006\u0004\b\r\u0010\u000eJ\u001a\u0010\u0011\u001a\u00020\u00102\b\u0010\u0004\u001a\u0004\u0018\u00010\u000fH\u0096\u0002¢\u0006\u0004\b\u0011\u0010\u0012J\u0017\u0010\u0014\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u0013H\u0007¢\u0006\u0004\b\u0014\u0010\u0015J\u001f\u0010\u0014\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tH\u0007¢\u0006\u0004\b\u0014\u0010\u0016J\u000f\u0010\u0017\u001a\u00020\tH\u0016¢\u0006\u0004\b\u0017\u0010\u0018J\u0016\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00020\u0019H\u0097\u0002¢\u0006\u0004\b\u001a\u0010\u001bJ\u0010\u0010\u001d\u001a\u00020\u001cH\u00d7\u0001¢\u0006\u0004\b\u001d\u0010\u001eR \u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00020\u00038\u0007X\u0087\u0004¢\u0006\f\n\u0004\b\u001f\u0010 \u001a\u0004\b!\u0010\bR\u0011\u0010$\u001a\u00020\u00028G¢\u0006\u0006\u001a\u0004\b\"\u0010#R\u0011\u0010&\u001a\u00020\u00028G¢\u0006\u0006\u001a\u0004\b%\u0010#R\u0011\u0010(\u001a\u00020\u00028G¢\u0006\u0006\u001a\u0004\b'\u0010#R\u0011\u0010*\u001a\u00020\u00028G¢\u0006\u0006\u001a\u0004\b)\u0010#R\u0011\u0010,\u001a\u00020\u00028G¢\u0006\u0006\u001a\u0004\b+\u0010#" }, d2 = { "Ldev/imanity/antixray/api/chunk/CachedChunkRegion;", "", "Ldev/imanity/antixray/api/chunk/DirectChunk;", "", "p0", "<init>", "([Ldev/imanity/antixray/api/chunk/DirectChunk;)V", "component1", "()[Ldev/imanity/antixray/api/chunk/DirectChunk;", "", "p1", "coordsToIndex", "(II)I", "copy", "([Ldev/imanity/antixray/api/chunk/DirectChunk;)Ldev/imanity/antixray/api/chunk/CachedChunkRegion;", "", "", "equals", "(Ljava/lang/Object;)Z", "Lio/fairyproject/bukkit/util/CoordXZ;", "getChunkAt", "(Lio/fairyproject/bukkit/util/CoordXZ;)Ldev/imanity/antixray/api/chunk/DirectChunk;", "(II)Ldev/imanity/antixray/api/chunk/DirectChunk;", "hashCode", "()I", "", "iterator", "()Ljava/util/Iterator;", "", "toString", "()Ljava/lang/String;", "chunks", "[Ldev/imanity/antixray/api/chunk/DirectChunk;", "getChunks", "getCurrent", "()Ldev/imanity/antixray/api/chunk/DirectChunk;", "current", "getEast", "east", "getNorth", "north", "getSouth", "south", "getWest", "west" })
@SourceDebugExtension({ "SMAP\nChunkData.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ChunkData.kt\ndev/imanity/antixray/api/chunk/CachedChunkRegion\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,135:1\n1#2:136\n*E\n" })
public final class CachedChunkRegion implements Iterable<DirectChunk>, KMappedMarker
{
    @NotNull
    private final DirectChunk[] chunks;
    
    public CachedChunkRegion(@NotNull final DirectChunk[] chunks) {
        Intrinsics.checkNotNullParameter((Object)chunks, "");
        this.chunks = chunks;
    }
    
    @NotNull
    @JvmName(name = "getChunks")
    public final DirectChunk[] getChunks() {
        return this.chunks;
    }
    
    @NotNull
    @JvmName(name = "getCurrent")
    public final DirectChunk getCurrent() {
        return this.chunks[4];
    }
    
    @NotNull
    @JvmName(name = "getWest")
    public final DirectChunk getWest() {
        return this.chunks[1];
    }
    
    @NotNull
    @JvmName(name = "getEast")
    public final DirectChunk getEast() {
        return this.chunks[7];
    }
    
    @NotNull
    @JvmName(name = "getNorth")
    public final DirectChunk getNorth() {
        return this.chunks[3];
    }
    
    @NotNull
    @JvmName(name = "getSouth")
    public final DirectChunk getSouth() {
        return this.chunks[5];
    }
    
    @NotNull
    public final DirectChunk getChunkAt(@NotNull final CoordXZ coordXZ) {
        Intrinsics.checkNotNullParameter((Object)coordXZ, "");
        return this.chunks[this.coordsToIndex(coordXZ.x, coordXZ.z)];
    }
    
    @NotNull
    public final DirectChunk getChunkAt(final int n, final int n2) {
        return this.chunks[this.coordsToIndex(n, n2)];
    }
    
    private final int coordsToIndex(final int n, final int n2) {
        return n2 + 1 + (n + 1) * 3;
    }
    
    @NotNull
    @Override
    public Iterator<DirectChunk> iterator() {
        return ArrayIteratorKt.iterator((Object[])this.chunks);
    }
    
    @Override
    public boolean equals(@Nullable final Object o) {
        if (this == o) {
            return true;
        }
        if (!Intrinsics.areEqual((Object)this.getClass(), (Object)((o != null) ? o.getClass() : null))) {
            return false;
        }
        Intrinsics.checkNotNull(o);
        final CachedChunkRegion cachedChunkRegion = (CachedChunkRegion)o;
        return Arrays.equals(this.chunks, ((CachedChunkRegion)o).chunks);
    }
    
    @Override
    public int hashCode() {
        return Arrays.hashCode(this.chunks);
    }
    
    @NotNull
    public final DirectChunk[] component1() {
        return this.chunks;
    }
    
    @NotNull
    public final CachedChunkRegion copy(@NotNull final DirectChunk[] array) {
        Intrinsics.checkNotNullParameter((Object)array, "");
        return new CachedChunkRegion(array);
    }
    
    @NotNull
    @Override
    public String toString() {
        return "CachedChunkRegion(chunks=" + Arrays.toString(this.chunks) + ')';
    }
}
