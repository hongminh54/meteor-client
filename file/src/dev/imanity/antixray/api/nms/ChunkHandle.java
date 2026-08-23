// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.api.nms;

import java.util.Iterator;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import io.fairyproject.libs.packetevents.protocol.world.states.WrappedBlockState;
import org.bukkit.Material;
import org.jetbrains.annotations.Nullable;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import kotlin.Metadata;

@Metadata(mv = { 1, 9, 0 }, k = 1, xi = 48, d1 = { "\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\bf\u0018\u00002\u00020\u0001J \u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0014\u001a\u00020\u0012H\u0016J \u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0014\u001a\u00020\u0012H\u0016J \u0010\u0017\u001a\u00020\u00122\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0014\u001a\u00020\u0012H\u0016J\u0012\u0010\u0018\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0019\u001a\u00020\u0012H\u0016J\u0010\u0010\u001a\u001a\u00020\u00122\u0006\u0010\u0019\u001a\u00020\u0012H\u0016J\"\u0010\u001b\u001a\u0004\u0018\u00010\r2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0014\u001a\u00020\u0012H\u0016R\u0018\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u0018\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX¦\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0018\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\bX¦\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000b¨\u0006\u001c" }, d2 = { "Ldev/imanity/antixray/api/nms/ChunkHandle;", "", "sections", "", "Ldev/imanity/antixray/api/nms/ChunkSectionHandle;", "getSections", "()[Ldev/imanity/antixray/api/nms/ChunkSectionHandle;", "structures", "", "Ldev/imanity/antixray/api/nms/StructureHandle;", "getStructures", "()Ljava/util/List;", "tileEntities", "Ldev/imanity/antixray/api/nms/TileEntityHandle;", "getTileEntities", "getBlockAt", "Lorg/bukkit/Material;", "x", "", "y", "z", "getBlockDataAt", "Lio/fairyproject/libs/packetevents/protocol/world/states/WrappedBlockState;", "getBlockIdAt", "getSection", "blockY", "getSectionY", "getTileEntityAt", "api" })
public interface ChunkHandle
{
    @NotNull
    ChunkSectionHandle[] getSections();
    
    @NotNull
    List<TileEntityHandle> getTileEntities();
    
    @NotNull
    List<StructureHandle> getStructures();
    
    @Nullable
    ChunkSectionHandle getSection(final int p0);
    
    int getSectionY(final int p0);
    
    @NotNull
    Material getBlockAt(final int p0, final int p1, final int p2);
    
    @NotNull
    WrappedBlockState getBlockDataAt(final int p0, final int p1, final int p2);
    
    int getBlockIdAt(final int p0, final int p1, final int p2);
    
    @Nullable
    TileEntityHandle getTileEntityAt(final int p0, final int p1, final int p2);
    
    @Metadata(mv = { 1, 9, 0 }, k = 3, xi = 48)
    @SourceDebugExtension({ "SMAP\nChunkHandle.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ChunkHandle.kt\ndev/imanity/antixray/api/nms/ChunkHandle$DefaultImpls\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,41:1\n288#2,2:42\n*S KotlinDebug\n*F\n+ 1 ChunkHandle.kt\ndev/imanity/antixray/api/nms/ChunkHandle$DefaultImpls\n*L\n38#1:42,2\n*E\n" })
    public static final class DefaultImpls
    {
        @Nullable
        public static ChunkSectionHandle getSection(@NotNull final ChunkHandle chunkHandle, final int n) {
            if (n < 0) {
                return null;
            }
            return chunkHandle.getSections()[chunkHandle.getSectionY(n)];
        }
        
        public static int getSectionY(@NotNull final ChunkHandle chunkHandle, final int n) {
            return n >> 4;
        }
        
        @NotNull
        public static Material getBlockAt(@NotNull final ChunkHandle chunkHandle, final int n, final int n2, final int n3) {
            final ChunkSectionHandle section = chunkHandle.getSection(n2);
            if (section == null) {
                return Material.AIR;
            }
            return section.getBlockType(n & 0xF, n2 & 0xF, n3 & 0xF);
        }
        
        @NotNull
        public static WrappedBlockState getBlockDataAt(@NotNull final ChunkHandle chunkHandle, final int n, final int n2, final int n3) {
            final ChunkSectionHandle section = chunkHandle.getSection(n2);
            if (section == null) {
                final WrappedBlockState byGlobalId = WrappedBlockState.getByGlobalId(0);
                Intrinsics.checkNotNullExpressionValue((Object)byGlobalId, "");
                return byGlobalId;
            }
            return section.getBlockData(n & 0xF, n2 & 0xF, n3 & 0xF);
        }
        
        public static int getBlockIdAt(@NotNull final ChunkHandle chunkHandle, final int n, final int n2, final int n3) {
            final ChunkSectionHandle section = chunkHandle.getSection(n2);
            if (section == null) {
                return 0;
            }
            return section.getBlockId(n & 0xF, n2 & 0xF, n3 & 0xF);
        }
        
        @Nullable
        public static TileEntityHandle getTileEntityAt(@NotNull final ChunkHandle chunkHandle, final int n, final int n2, final int n3) {
            for (final Object next : chunkHandle.getTileEntities()) {
                final TileEntityHandle tileEntityHandle = (TileEntityHandle)next;
                if (tileEntityHandle.getX() == n && tileEntityHandle.getY() == n2 && tileEntityHandle.getZ() == n3) {
                    return (TileEntityHandle)next;
                }
            }
            return null;
        }
    }
}
