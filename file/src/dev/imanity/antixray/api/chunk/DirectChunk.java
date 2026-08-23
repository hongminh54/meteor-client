// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.api.chunk;

import io.fairyproject.libs.packetevents.protocol.world.states.WrappedBlockState;
import org.bukkit.Material;
import org.jetbrains.annotations.Nullable;
import dev.imanity.antixray.api.nms.ChunkSectionHandle;
import dev.imanity.antixray.api.util.LongHash;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import dev.imanity.antixray.api.nms.ChunkHandle;
import kotlin.Metadata;

@Metadata(mv = { 1, 9, 0 }, k = 1, xi = 48, d1 = { "\u0000T\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\t\u0010\u001a\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001b\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001c\u001a\u00020\u0006H\u00c6\u0003J'\u0010\u001d\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006H\u00c6\u0001J\u0013\u0010\u001e\u001a\u00020\u001f2\b\u0010 \u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\u001e\u0010!\u001a\u00020\"2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010#\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0003J\u001e\u0010$\u001a\u00020%2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010#\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0003J\u001e\u0010&\u001a\u00020\u00032\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010#\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0003J\u0010\u0010'\u001a\u0004\u0018\u00010\u00102\u0006\u0010(\u001a\u00020\u0003J\u000e\u0010)\u001a\u00020\u00032\u0006\u0010*\u001a\u00020\u0003J\t\u0010+\u001a\u00020\u0003H\u00d6\u0001J\t\u0010,\u001a\u00020-H\u00d6\u0001R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u001a\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0013\u001a\u00020\u0014¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0018¨\u0006." }, d2 = { "Ldev/imanity/antixray/api/chunk/DirectChunk;", "", "x", "", "z", "chunkHandle", "Ldev/imanity/antixray/api/nms/ChunkHandle;", "(IILdev/imanity/antixray/api/nms/ChunkHandle;)V", "getChunkHandle", "()Ldev/imanity/antixray/api/nms/ChunkHandle;", "chunkKey", "", "getChunkKey", "()J", "sections", "", "Ldev/imanity/antixray/api/nms/ChunkSectionHandle;", "getSections", "()[Ldev/imanity/antixray/api/nms/ChunkSectionHandle;", "view", "Ldev/imanity/antixray/api/chunk/ChunkView;", "getView", "()Ldev/imanity/antixray/api/chunk/ChunkView;", "getX", "()I", "getZ", "component1", "component2", "component3", "copy", "equals", "", "other", "getBlockAt", "Lorg/bukkit/Material;", "y", "getBlockDataAt", "Lio/fairyproject/libs/packetevents/protocol/world/states/WrappedBlockState;", "getBlockIdAt", "getChunkSection", "index", "getSectionY", "blockY", "hashCode", "toString", "", "api" })
public final class DirectChunk
{
    private final int x;
    private final int z;
    @NotNull
    private final ChunkHandle chunkHandle;
    private final long chunkKey;
    @NotNull
    private final ChunkView view;
    
    public DirectChunk(final int x, final int z, @NotNull final ChunkHandle chunkHandle) {
        Intrinsics.checkNotNullParameter((Object)chunkHandle, "");
        this.x = x;
        this.z = z;
        this.chunkHandle = chunkHandle;
        this.chunkKey = LongHash.INSTANCE.toLong(this.x, this.z);
        this.view = new ChunkView();
    }
    
    public final int getX() {
        return this.x;
    }
    
    public final int getZ() {
        return this.z;
    }
    
    @NotNull
    public final ChunkHandle getChunkHandle() {
        return this.chunkHandle;
    }
    
    public final long getChunkKey() {
        return this.chunkKey;
    }
    
    @NotNull
    public final ChunkView getView() {
        return this.view;
    }
    
    private final ChunkSectionHandle[] getSections() {
        return this.chunkHandle.getSections();
    }
    
    public final int getSectionY(final int n) {
        return this.chunkHandle.getSectionY(n);
    }
    
    @Nullable
    public final ChunkSectionHandle getChunkSection(final int n) {
        final ChunkSectionHandle chunkSectionHandle = this.getSections()[n];
        if (chunkSectionHandle.getEmpty()) {
            return null;
        }
        return chunkSectionHandle;
    }
    
    @NotNull
    public final Material getBlockAt(final int n, final int n2, final int n3) {
        return this.chunkHandle.getBlockAt(n, n2, n3);
    }
    
    @NotNull
    public final WrappedBlockState getBlockDataAt(final int n, final int n2, final int n3) {
        return this.chunkHandle.getBlockDataAt(n, n2, n3);
    }
    
    public final int getBlockIdAt(final int n, final int n2, final int n3) {
        return this.chunkHandle.getBlockIdAt(n, n2, n3);
    }
    
    public final int component1() {
        return this.x;
    }
    
    public final int component2() {
        return this.z;
    }
    
    @NotNull
    public final ChunkHandle component3() {
        return this.chunkHandle;
    }
    
    @NotNull
    public final DirectChunk copy(final int n, final int n2, @NotNull final ChunkHandle chunkHandle) {
        Intrinsics.checkNotNullParameter((Object)chunkHandle, "");
        return new DirectChunk(n, n2, chunkHandle);
    }
    
    @NotNull
    @Override
    public String toString() {
        return "DirectChunk(x=" + this.x + ", z=" + this.z + ", chunkHandle=" + this.chunkHandle + ')';
    }
    
    @Override
    public int hashCode() {
        return (Integer.hashCode(this.x) * 31 + Integer.hashCode(this.z)) * 31 + this.chunkHandle.hashCode();
    }
    
    @Override
    public boolean equals(@Nullable final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DirectChunk)) {
            return false;
        }
        final DirectChunk directChunk = (DirectChunk)o;
        return this.x == directChunk.x && this.z == directChunk.z && Intrinsics.areEqual((Object)this.chunkHandle, (Object)directChunk.chunkHandle);
    }
}
