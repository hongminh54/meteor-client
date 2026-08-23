// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.api.chunk;

import org.jetbrains.annotations.Nullable;
import dev.imanity.antixray.api.extension.BlockPositionExtensionKt;
import org.bukkit.Material;
import io.fairyproject.mc.util.BlockPosition;
import io.fairyproject.libs.packetevents.protocol.world.states.WrappedBlockState;
import dev.imanity.antixray.api.extension.CoordXZExtensionKt;
import kotlin.jvm.internal.Intrinsics;
import io.fairyproject.libs.packetevents.protocol.world.chunk.BaseChunk;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import io.fairyproject.bukkit.util.CoordXZ;
import kotlin.Metadata;

@Metadata(mv = { 1, 9, 0 }, k = 1, xi = 48, d1 = { "\u0000`\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u000e\u0010\u0004\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\t\u0010\u0018\u001a\u00020\u0003H\u00c6\u0003J\u0011\u0010\u0019\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0005H\u00c6\u0003J\t\u0010\u001a\u001a\u00020\bH\u00c6\u0003J/\u0010\u001b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u0010\b\u0002\u0010\u0004\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u00052\b\b\u0002\u0010\u0007\u001a\u00020\bH\u00c6\u0001J\u0013\u0010\u001c\u001a\u00020\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\u000e\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\"J\u001e\u0010\u001f\u001a\u00020 2\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020$2\u0006\u0010&\u001a\u00020$J\u000e\u0010'\u001a\u00020(2\u0006\u0010\u0002\u001a\u00020\u0003J\u0016\u0010'\u001a\u00020(2\u0006\u0010#\u001a\u00020$2\u0006\u0010&\u001a\u00020$J\b\u0010)\u001a\u00020$H\u0016J\u000e\u0010*\u001a\u00020\u001d2\u0006\u0010+\u001a\u00020$J\t\u0010,\u001a\u00020-H\u00d6\u0001J&\u0010.\u001a\u00020/2\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020$2\u0006\u0010&\u001a\u00020$2\u0006\u00100\u001a\u000201R\u0011\u0010\n\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u001a\u0010\r\u001a\u00020\u000eX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\fR\u0019\u0010\u0004\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017¨\u00062" }, d2 = { "Ldev/imanity/antixray/api/chunk/ChunkData;", "", "coords", "Lio/fairyproject/bukkit/util/CoordXZ;", "sections", "", "Lio/fairyproject/libs/packetevents/protocol/world/chunk/BaseChunk;", "tileEntities", "Ldev/imanity/antixray/api/chunk/TileEntityHolder;", "(Lio/fairyproject/bukkit/util/CoordXZ;Ljava/util/List;Ldev/imanity/antixray/api/chunk/TileEntityHolder;)V", "blockCoords", "getBlockCoords", "()Lio/fairyproject/bukkit/util/CoordXZ;", "chunkRegion", "Ldev/imanity/antixray/api/chunk/CachedChunkRegion;", "getChunkRegion", "()Ldev/imanity/antixray/api/chunk/CachedChunkRegion;", "setChunkRegion", "(Ldev/imanity/antixray/api/chunk/CachedChunkRegion;)V", "getCoords", "getSections", "()Ljava/util/List;", "getTileEntities", "()Ldev/imanity/antixray/api/chunk/TileEntityHolder;", "component1", "component2", "component3", "copy", "equals", "", "other", "getBlock", "Lorg/bukkit/Material;", "blockPosition", "Lio/fairyproject/mc/util/BlockPosition;", "x", "", "y", "z", "getChunkAt", "Ldev/imanity/antixray/api/chunk/DirectChunk;", "hashCode", "isSectionEmpty", "index", "toString", "", "write", "", "wrappedBlockState", "Lio/fairyproject/libs/packetevents/protocol/world/states/WrappedBlockState;", "api" })
public final class ChunkData
{
    @NotNull
    private final CoordXZ coords;
    @NotNull
    private final List<BaseChunk> sections;
    @NotNull
    private final TileEntityHolder tileEntities;
    public CachedChunkRegion chunkRegion;
    @NotNull
    private final CoordXZ blockCoords;
    
    public ChunkData(@NotNull final CoordXZ coords, @NotNull final List<? extends BaseChunk> sections, @NotNull final TileEntityHolder tileEntities) {
        Intrinsics.checkNotNullParameter((Object)coords, "");
        Intrinsics.checkNotNullParameter((Object)sections, "");
        Intrinsics.checkNotNullParameter((Object)tileEntities, "");
        this.coords = coords;
        this.sections = (List<BaseChunk>)sections;
        this.tileEntities = tileEntities;
        this.blockCoords = CoordXZExtensionKt.shiftAsBlockCoords(this.coords);
    }
    
    @NotNull
    public final CoordXZ getCoords() {
        return this.coords;
    }
    
    @NotNull
    public final List<BaseChunk> getSections() {
        return this.sections;
    }
    
    @NotNull
    public final TileEntityHolder getTileEntities() {
        return this.tileEntities;
    }
    
    @NotNull
    public final CachedChunkRegion getChunkRegion() {
        final CachedChunkRegion chunkRegion = this.chunkRegion;
        if (chunkRegion != null) {
            return chunkRegion;
        }
        Intrinsics.throwUninitializedPropertyAccessException("");
        return null;
    }
    
    public final void setChunkRegion(@NotNull final CachedChunkRegion chunkRegion) {
        Intrinsics.checkNotNullParameter((Object)chunkRegion, "");
        this.chunkRegion = chunkRegion;
    }
    
    @NotNull
    public final CoordXZ getBlockCoords() {
        return this.blockCoords;
    }
    
    public final boolean isSectionEmpty(final int n) {
        final BaseChunk baseChunk = this.sections.get(n);
        return baseChunk == null || baseChunk.isEmpty();
    }
    
    public final void write(final int n, final int n2, final int n3, @NotNull final WrappedBlockState wrappedBlockState) {
        Intrinsics.checkNotNullParameter((Object)wrappedBlockState, "");
        final int n4 = n & 0xF;
        final int n5 = n2 & 0xF;
        final int n6 = n3 & 0xF;
        final BaseChunk baseChunk = this.sections.get(this.getChunkRegion().getCurrent().getChunkHandle().getSectionY(n2));
        if (baseChunk == null) {
            return;
        }
        baseChunk.set(n4, n5, n6, wrappedBlockState);
    }
    
    @NotNull
    public final Material getBlock(@NotNull final BlockPosition blockPosition) {
        Intrinsics.checkNotNullParameter((Object)blockPosition, "");
        return this.getChunkAt(BlockPositionExtensionKt.getChunkCoords(blockPosition)).getBlockAt(blockPosition.getX(), blockPosition.getY(), blockPosition.getZ());
    }
    
    @NotNull
    public final Material getBlock(final int n, final int n2, final int n3) {
        return this.getChunkAt(new CoordXZ(n >> 4, n3 >> 4)).getBlockAt(n & 0xF, n2, n3 & 0xF);
    }
    
    @NotNull
    public final DirectChunk getChunkAt(@NotNull final CoordXZ coordXZ) {
        Intrinsics.checkNotNullParameter((Object)coordXZ, "");
        return this.getChunkRegion().getChunkAt(CoordXZExtensionKt.minus(coordXZ, this.coords));
    }
    
    @NotNull
    public final DirectChunk getChunkAt(final int n, final int n2) {
        return this.getChunkRegion().getChunkAt(n - this.coords.x, n2 - this.coords.z);
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
        final ChunkData chunkData = (ChunkData)o;
        return Intrinsics.areEqual((Object)this.coords, (Object)((ChunkData)o).coords) && Intrinsics.areEqual((Object)this.sections, (Object)((ChunkData)o).sections) && Intrinsics.areEqual((Object)this.getChunkRegion(), (Object)((ChunkData)o).getChunkRegion()) && Intrinsics.areEqual((Object)this.blockCoords, (Object)((ChunkData)o).blockCoords);
    }
    
    @Override
    public int hashCode() {
        return 31 * (31 * (31 * this.coords.hashCode() + this.sections.hashCode()) + this.getChunkRegion().hashCode()) + this.blockCoords.hashCode();
    }
    
    @NotNull
    public final CoordXZ component1() {
        return this.coords;
    }
    
    @NotNull
    public final List<BaseChunk> component2() {
        return this.sections;
    }
    
    @NotNull
    public final TileEntityHolder component3() {
        return this.tileEntities;
    }
    
    @NotNull
    public final ChunkData copy(@NotNull final CoordXZ coordXZ, @NotNull final List<? extends BaseChunk> list, @NotNull final TileEntityHolder tileEntityHolder) {
        Intrinsics.checkNotNullParameter((Object)coordXZ, "");
        Intrinsics.checkNotNullParameter((Object)list, "");
        Intrinsics.checkNotNullParameter((Object)tileEntityHolder, "");
        return new ChunkData(coordXZ, list, tileEntityHolder);
    }
    
    @NotNull
    @Override
    public String toString() {
        return "ChunkData(coords=" + this.coords + ", sections=" + this.sections + ", tileEntities=" + this.tileEntities + ')';
    }
}
