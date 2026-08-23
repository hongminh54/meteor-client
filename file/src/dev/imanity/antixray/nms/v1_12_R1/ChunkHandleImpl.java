// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.nms.v1_12_R1;

import io.fairyproject.libs.packetevents.protocol.world.states.WrappedBlockState;
import org.bukkit.Material;
import org.jetbrains.annotations.Nullable;
import kotlin.NotImplementedError;
import dev.imanity.antixray.api.nms.StructureHandle;
import java.util.Iterator;
import net.minecraft.server.v1_12_R1.TileEntity;
import java.util.ArrayList;
import kotlin.collections.CollectionsKt;
import java.util.Collection;
import dev.imanity.antixray.api.nms.TileEntityHandle;
import java.util.List;
import dev.imanity.antixray.api.nms.ChunkSectionHandle;
import kotlin.LazyKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.Lazy;
import org.jetbrains.annotations.NotNull;
import net.minecraft.server.v1_12_R1.Chunk;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.Metadata;
import dev.imanity.antixray.api.nms.ChunkHandle;

@Metadata(mv = { 1, 9, 0 }, k = 1, xi = 48, d1 = { "\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0012\u0010\u0014\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u0015\u001a\u00020\u0016H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R!\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00068VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\b\u0010\tR\u001a\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00120\r8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0010¨\u0006\u0017" }, d2 = { "Ldev/imanity/antixray/nms/v1_12_R1/ChunkHandleImpl;", "Ldev/imanity/antixray/api/nms/ChunkHandle;", "chunk", "Lnet/minecraft/server/v1_12_R1/Chunk;", "(Lnet/minecraft/server/v1_12_R1/Chunk;)V", "sections", "", "Ldev/imanity/antixray/api/nms/ChunkSectionHandle;", "getSections", "()[Ldev/imanity/antixray/api/nms/ChunkSectionHandle;", "sections$delegate", "Lkotlin/Lazy;", "structures", "", "Ldev/imanity/antixray/api/nms/StructureHandle;", "getStructures", "()Ljava/util/List;", "tileEntities", "Ldev/imanity/antixray/api/nms/TileEntityHandle;", "getTileEntities", "getSection", "blockY", "", "v1_12_1" })
@SourceDebugExtension({ "SMAP\nChunkHandleImpl.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ChunkHandleImpl.kt\ndev/imanity/antixray/nms/v1_12_R1/ChunkHandleImpl\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,28:1\n1549#2:29\n1620#2,3:30\n*S KotlinDebug\n*F\n+ 1 ChunkHandleImpl.kt\ndev/imanity/antixray/nms/v1_12_R1/ChunkHandleImpl\n*L\n18#1:29\n18#1:30,3\n*E\n" })
public final class ChunkHandleImpl implements ChunkHandle
{
    @NotNull
    private final Chunk chunk;
    @NotNull
    private final Lazy sections$delegate;
    
    public ChunkHandleImpl(@NotNull final Chunk chunk) {
        Intrinsics.checkNotNullParameter((Object)chunk, "");
        this.chunk = chunk;
        this.sections$delegate = LazyKt.lazy((Function0)new ChunkHandleImpl$sections.ChunkHandleImpl$sections$2(this));
    }
    
    @NotNull
    @Override
    public ChunkSectionHandle[] getSections() {
        return (ChunkSectionHandle[])this.sections$delegate.getValue();
    }
    
    @NotNull
    @Override
    public List<TileEntityHandle> getTileEntities() {
        final Iterable iterable;
        final Collection collection = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable = this.chunk.tileEntities.values(), 10));
        for (final Object next : iterable) {
            final Collection collection2 = collection;
            final TileEntity tileEntity = (TileEntity)next;
            final Collection collection3 = collection2;
            Intrinsics.checkNotNull((Object)tileEntity);
            collection3.add(new TileEntityHandleImpl(tileEntity));
        }
        return (List<TileEntityHandle>)collection;
    }
    
    @NotNull
    @Override
    public List<StructureHandle> getStructures() {
        throw new NotImplementedError("An operation is not implemented: " + "Not yet implemented");
    }
    
    @Nullable
    @Override
    public ChunkSectionHandle getSection(final int n) {
        if (n >= 256) {
            return null;
        }
        return DefaultImpls.getSection(this, n);
    }
    
    @NotNull
    @Override
    public Material getBlockAt(final int n, final int n2, final int n3) {
        return DefaultImpls.getBlockAt(this, n, n2, n3);
    }
    
    @NotNull
    @Override
    public WrappedBlockState getBlockDataAt(final int n, final int n2, final int n3) {
        return DefaultImpls.getBlockDataAt(this, n, n2, n3);
    }
    
    @Override
    public int getBlockIdAt(final int n, final int n2, final int n3) {
        return DefaultImpls.getBlockIdAt(this, n, n2, n3);
    }
    
    @Override
    public int getSectionY(final int n) {
        return DefaultImpls.getSectionY(this, n);
    }
    
    @Nullable
    @Override
    public TileEntityHandle getTileEntityAt(final int n, final int n2, final int n3) {
        return DefaultImpls.getTileEntityAt(this, n, n2, n3);
    }
}
