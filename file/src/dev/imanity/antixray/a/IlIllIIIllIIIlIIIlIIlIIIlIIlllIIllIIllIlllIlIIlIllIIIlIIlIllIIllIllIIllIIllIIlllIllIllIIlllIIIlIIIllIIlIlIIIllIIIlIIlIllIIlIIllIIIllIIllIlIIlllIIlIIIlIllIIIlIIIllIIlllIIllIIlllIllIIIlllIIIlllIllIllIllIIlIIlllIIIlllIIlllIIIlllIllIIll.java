// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.a;

import io.fairyproject.libs.packetevents.wrapper.PacketWrapper;
import kotlin.LazyKt;
import dev.imanity.antixray.api.chunk.EmptyTileEntityHolder;
import dev.imanity.antixray.api.chunk.TileEntityHolder;
import kotlin.collections.ArraysKt;
import io.fairyproject.bukkit.util.CoordXZ;
import io.fairyproject.libs.packetevents.protocol.world.chunk.BaseChunk;
import java.util.ArrayList;
import java.util.Collection;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.jvm.internal.Lambda;
import dev.imanity.antixray.api.chunk.ChunkData;
import java.util.List;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.Lazy;
import org.jetbrains.annotations.NotNull;
import dev.imanity.antixray.internal.wrapper.WrapperPlayServerChunkDataBulk;
import kotlin.Metadata;

@Metadata(mv = { 1, 9, 0 }, k = 1, xi = 48, d1 = { "\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\f\u0010\u000e\u001a\u0006\u0012\u0002\b\u00030\u000fH\u0014R!\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b8VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b\f\u0010\r\u001a\u0004\b\n\u0010\u000bR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010" }, d2 = { "Ldev/imanity/antixray/core/protocol/MultiChunkPacket;", "Ldev/imanity/antixray/core/protocol/ChunkPacket;", "worldHeight", "", "packet", "Ldev/imanity/antixray/internal/wrapper/WrapperPlayServerChunkDataBulk;", "(ILdev/imanity/antixray/internal/wrapper/WrapperPlayServerChunkDataBulk;)V", "chunks", "", "Ldev/imanity/antixray/api/chunk/ChunkData;", "getChunks", "()Ljava/util/List;", "chunks$delegate", "Lkotlin/Lazy;", "getChunkPacket", "Lio/fairyproject/libs/packetevents/wrapper/PacketWrapper;", "plugin" })
public final class IlIllIIIllIIIlIIIlIIlIIIlIIlllIIllIIllIlllIlIIlIllIIIlIIlIllIIllIllIIllIIllIIlllIllIllIIlllIIIlIIIllIIlIlIIIllIIIlIIlIllIIlIIllIIIllIIllIlIIlllIIlIIIlIllIIIlIIIllIIlllIIllIIlllIllIIIlllIIIlllIllIllIllIIlIIlllIIIlllIIlllIIIlllIllIIll extends IIllIIlIIlIlIIIlllIIlIIlIIlIllIIlIllIIlllIlllIlllIIllIlIIlllIlIlIIlllIIlIllIlllIIlllIlllIlIIIlllIlllIIIllIIllIlllIIllIIlllIlllIIlllIllIIlIlIllIlIlIlIlIIllIlIllIllIlllIIllIIIlllIlllIllIIllIlIllIlIIlIllIIllIlll
{
    @NotNull
    private final WrapperPlayServerChunkDataBulk aB;
    @NotNull
    private final Lazy aC;
    
    public IlIllIIIllIIIlIIIlIIlIIIlIIlllIIllIIllIlllIlIIlIllIIIlIIlIllIIllIllIIllIIllIIlllIllIllIIlllIIIlIIIllIIlIlIIIllIIIlIIlIllIIlIIllIIIllIIllIlIIlllIIlIIIlIllIIIlIIIllIIlllIIllIIlllIllIIIlllIIIlllIllIllIllIIlIIlllIIIlllIIlllIIIlllIllIIll(final int n, @NotNull final WrapperPlayServerChunkDataBulk ab) {
        Intrinsics.checkNotNullParameter((Object)ab, "");
        super(n);
        this.aB = ab;
        this.aC = LazyKt.lazy((Function0)new Function0<List<? extends ChunkData>>() {
            final /* synthetic */ IlIllIIIllIIIlIIIlIIlIIIlIIlllIIllIIllIlllIlIIlIllIIIlIIlIllIIllIllIIllIIllIIlllIllIllIIlllIIIlIIIllIIlIlIIIllIIIlIIlIllIIlIIllIIIllIIllIlIIlllIIlIIIlIllIIIlIIIllIIlllIIllIIlllIllIIIlllIIIlllIllIllIllIIlIIlllIIIlllIIlllIIIlllIllIIll aD;
            
            @NotNull
            public final List<ChunkData> IIIllIllIlllIIllIlIlIIllIIIllIIIllIIIlIIIlllIIlIIIlIlllIllIIIlllIIIllIllIIllIIIlllIIlllIlIllIIllIIIlIIlIIlIlIIIllIlllIIIlIIIlIIIlIIIllIIlllIlllIllIIlIIllIIIlllIlIIlIIIlIIllIllIlIIllIIIllIllIlIIlIIlllIIIllIIIlIIlIlIIlllIllIllIll() {
                final BaseChunk[][] chunks = IlIllIIIllIIIlIIIlIIlIIIlIIlllIIllIIllIlllIlIIlIllIIIlIIlIllIIllIllIIllIIllIIlllIllIllIIlllIIIlIIIllIIlIlIIIllIIIlIIlIllIIlIIllIIIllIIllIlIIlllIIlIIIlIllIIIlIIIllIIlllIIllIIlllIllIIIlllIIIlllIllIllIllIIlIIlllIIIlllIIlllIIIlllIllIIll.IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(this.aD).getChunks();
                Intrinsics.checkNotNullExpressionValue((Object)chunks, "");
                final Object[] array = chunks;
                final IlIllIIIllIIIlIIIlIIlIIIlIIlllIIllIIllIlllIlIIlIllIIIlIIlIllIIllIllIIllIIllIIlllIllIllIIlllIIIlIIIllIIlIlIIIllIIIlIIlIllIIlIIllIIIllIIllIlIIlllIIlIIIlIllIIIlIIIllIIlllIIllIIlllIllIIIlllIIIlllIllIllIllIIlIIlllIIIlllIIlllIIIlllIllIIll ad = this.aD;
                final Object[] array2 = array;
                final Collection collection = new ArrayList(array.length);
                int n = 0;
                for (int i = 0; i < array2.length; ++i) {
                    final Object o = array2[i];
                    final Collection collection2 = collection;
                    final int n2 = n++;
                    final BaseChunk[] array3 = (BaseChunk[])o;
                    final int n3 = n2;
                    final Collection collection3 = collection2;
                    final CoordXZ coordXZ = new CoordXZ(IlIllIIIllIIIlIIIlIIlIIIlIIlllIIllIIllIlllIlIIlIllIIIlIIlIllIIllIllIIllIIllIIlllIllIllIIlllIIIlIIIllIIlIlIIIllIIIlIIlIllIIlIIllIIIllIIllIlIIlllIIlIIIlIllIIIlIIIllIIlllIIllIIlllIllIIIlllIIIlllIllIllIllIIlIIlllIIIlllIIlllIIIlllIllIIll.IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(ad).getX()[n3], IlIllIIIllIIIlIIIlIIlIIIlIIlllIIllIIllIlllIlIIlIllIIIlIIlIllIIllIllIIllIIllIIlllIllIllIIlllIIIlIIIllIIlIlIIIllIIIlIIlIllIIlIIllIIIllIIllIlIIlllIIlIIIlIllIIIlIIIllIIlllIIllIIlllIllIIIlllIIIlllIllIllIllIIlIIlllIIIlllIIlllIIIlllIllIIll.IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(ad).getZ()[n3]);
                    Intrinsics.checkNotNull((Object)array3);
                    collection3.add(new ChunkData(coordXZ, ArraysKt.asList((Object[])array3), new EmptyTileEntityHolder()));
                }
                return (List<ChunkData>)collection;
            }
        });
    }
    
    @NotNull
    @Override
    public List<ChunkData> IllIIlllIIIlIIllIIlIIllIIlIIlllIIIllIllIIlllIIlIIllIIllIIIllIlllIlIlIlllIlIIllIIIlllIllIlIIlllIIlllIIllIIIlIIIllIIlIIIlIllIIIllIIllIIllIIlIIIlIIlIIllIllIlIlIIlllIIlIIlIllIllIIllIIlIIIlIIlllIllIIIlllIlllIIlllIllIIlIIIlllIIllIl() {
        return (List)this.aC.getValue();
    }
    
    @NotNull
    @Override
    protected PacketWrapper<?> IIllIIIlllIIllIlllIllIlIllIlllIIIlIIllIIIlIIlIIIlIIlllIIllIlllIIlIIllIIlIIllIIlIllIllIIllIllIlIlIIlIIIllIIllIlIIlIlllIIlllIIIlllIIlIIIllIIIlIIlIIlIIllIIIlllIIIlIIIlllIIlIllIIlIIIllIllIIllIIllIlIlIIIlllIlllIIlIIlllIlIllIIl() {
        return new WrapperPlayServerChunkDataBulk(this.aB);
    }
    
    public static final /* synthetic */ WrapperPlayServerChunkDataBulk IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(final IlIllIIIllIIIlIIIlIIlIIIlIIlllIIllIIllIlllIlIIlIllIIIlIIlIllIIllIllIIllIIllIIlllIllIllIIlllIIIlIIIllIIlIlIIIllIIIlIIlIllIIlIIllIIIllIIllIlIIlllIIlIIIlIllIIIlIIIllIIlllIIllIIlllIllIIIlllIIIlllIllIllIllIIlIIlllIIIlllIIlllIIIlllIllIIll ilIllIIIllIIIlIIIlIIlIIIlIIlllIIllIIllIlllIlIIlIllIIIlIIlIllIIllIllIIllIIllIIlllIllIllIIlllIIIlIIIllIIlIlIIIllIIIlIIlIllIIlIIllIIIllIIllIlIIlllIIlIIIlIllIIIlIIIllIIlllIIllIIlllIllIIIlllIIIlllIllIllIllIIlIIlllIIIlllIIlllIIIlllIllIIll) {
        return ilIllIIIllIIIlIIIlIIlIIIlIIlllIIllIIllIlllIlIIlIllIIIlIIlIllIIllIllIIllIIllIIlllIllIllIIlllIIIlIIIllIIlIlIIIllIIIlIIlIllIIlIIllIIIllIIllIlIIlllIIlIIIlIllIIIlIIIllIIlllIIllIIlllIllIIIlllIIIlllIllIllIllIIlIIlllIIIlllIIlllIIIlllIllIIll.aB;
    }
}
