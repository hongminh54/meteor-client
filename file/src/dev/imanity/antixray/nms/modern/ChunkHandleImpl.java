// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.nms.modern;

import io.fairyproject.libs.packetevents.protocol.world.states.WrappedBlockState;
import org.bukkit.Material;
import java.util.Map;
import dev.imanity.generated.net.minecraft.core.BlockPosHandle;
import dev.imanity.antixray.api.util.LongHash;
import kotlin.NotImplementedError;
import dev.imanity.antixray.api.nms.StructureHandle;
import java.util.Iterator;
import java.util.ArrayList;
import kotlin.collections.CollectionsKt;
import java.util.Collection;
import dev.imanity.antixray.api.nms.ChunkSectionHandle;
import kotlin.jvm.JvmName;
import kotlin.LazyKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import java.util.concurrent.ConcurrentHashMap;
import org.jetbrains.annotations.Nullable;
import dev.imanity.antixray.api.nms.TileEntityHandle;
import java.util.List;
import kotlin.Lazy;
import org.jetbrains.annotations.NotNull;
import org.bukkit.World;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.Metadata;
import dev.imanity.antixray.api.nms.ChunkHandle;

@Metadata(mv = { 1, 9, 0 }, k = 1, xi = 48, d1 = { "\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0011\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\b\u000b\b\u0007\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u0019\u0010\n\u001a\u0004\u0018\u00010\t2\u0006\u0010\u0003\u001a\u00020\bH\u0017¢\u0006\u0004\b\n\u0010\u000bJ\u0017\u0010\f\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\bH\u0016¢\u0006\u0004\b\f\u0010\rJ)\u0010\u0010\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0003\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\bH\u0017¢\u0006\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0012\u001a\u00020\u00048\u0007X\u0087\u0004¢\u0006\f\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015R\u0014\u0010\u0016\u001a\u00020\b8\u0002X\u0083\u0004¢\u0006\u0006\n\u0004\b\u0016\u0010\u0017R!\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\t0\u00188WX\u0097\u0084\u0002¢\u0006\f\n\u0004\b\u0019\u0010\u001a\u001a\u0004\b\u001b\u0010\u001cR\u001a\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u001f0\u001e8WX\u0096\u0004¢\u0006\u0006\u001a\u0004\b \u0010!R\u001a\u0010$\u001a\b\u0012\u0004\u0012\u00020\u000f0\u001e8WX\u0096\u0004¢\u0006\u0006\u001a\u0004\b#\u0010!R \u0010'\u001a\u000e\u0012\u0004\u0012\u00020&\u0012\u0004\u0012\u00020\u000f0%8\u0002X\u0083\u0004¢\u0006\u0006\n\u0004\b'\u0010(R\u001e\u0010)\u001a\n\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u001e8\u0002@\u0002X\u0083\u000e¢\u0006\u0006\n\u0004\b)\u0010*R\u0016\u0010+\u001a\u00020&8\u0002@\u0002X\u0083\u000e¢\u0006\u0006\n\u0004\b+\u0010,R\u0016\u0010-\u001a\u00020&8\u0002@\u0002X\u0083\u000e¢\u0006\u0006\n\u0004\b-\u0010,R\u001a\u0010.\u001a\u00020\u00028\u0007X\u0087\u0004¢\u0006\f\n\u0004\b.\u0010/\u001a\u0004\b0\u00101" }, d2 = { "Ldev/imanity/antixray/nms/modern/ChunkHandleImpl;", "Ldev/imanity/antixray/api/nms/ChunkHandle;", "Lorg/bukkit/World;", "p0", "Ldev/imanity/generated/net/minecraft/world/level/chunk/ChunkHandle;", "p1", "<init>", "(Lorg/bukkit/World;Ldev/imanity/generated/net/minecraft/world/level/chunk/ChunkHandle;)V", "", "Ldev/imanity/antixray/api/nms/ChunkSectionHandle;", "getSection", "(I)Ldev/imanity/antixray/api/nms/ChunkSectionHandle;", "getSectionY", "(I)I", "p2", "Ldev/imanity/antixray/api/nms/TileEntityHandle;", "getTileEntityAt", "(III)Ldev/imanity/antixray/api/nms/TileEntityHandle;", "instance", "Ldev/imanity/generated/net/minecraft/world/level/chunk/ChunkHandle;", "getInstance", "()Ldev/imanity/generated/net/minecraft/world/level/chunk/ChunkHandle;", "minHeight", "I", "", "sections$delegate", "Lkotlin/Lazy;", "getSections", "()[Ldev/imanity/antixray/api/nms/ChunkSectionHandle;", "sections", "", "Ldev/imanity/antixray/api/nms/StructureHandle;", "getStructures", "()Ljava/util/List;", "structures", "getTileEntities", "tileEntities", "Ljava/util/concurrent/ConcurrentHashMap;", "", "tileEntitiesCache", "Ljava/util/concurrent/ConcurrentHashMap;", "tileEntitiesListCache", "Ljava/util/List;", "tileEntitiesListUpdate", "J", "tileEntitiesUpdate", "world", "Lorg/bukkit/World;", "getWorld", "()Lorg/bukkit/World;" })
@SourceDebugExtension({ "SMAP\nChunkHandleImpl.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ChunkHandleImpl.kt\ndev/imanity/antixray/nms/modern/ChunkHandleImpl\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,95:1\n1549#2:96\n1620#2,3:97\n1#3:100\n*S KotlinDebug\n*F\n+ 1 ChunkHandleImpl.kt\ndev/imanity/antixray/nms/modern/ChunkHandleImpl\n*L\n36#1:96\n36#1:97,3\n*E\n" })
public final class ChunkHandleImpl implements ChunkHandle
{
    @NotNull
    private final World world;
    @NotNull
    private final dev.imanity.generated.net.minecraft.world.level.chunk.ChunkHandle instance;
    @NotNull
    private final Lazy sections$delegate;
    @Nullable
    private volatile List<? extends TileEntityHandle> tileEntitiesListCache;
    private volatile long tileEntitiesListUpdate;
    @NotNull
    private final ConcurrentHashMap<Long, TileEntityHandle> tileEntitiesCache;
    private long tileEntitiesUpdate;
    private final int minHeight;
    
    public ChunkHandleImpl(@NotNull final World world, @NotNull final dev.imanity.generated.net.minecraft.world.level.chunk.ChunkHandle instance) {
        Intrinsics.checkNotNullParameter((Object)world, "");
        Intrinsics.checkNotNullParameter((Object)instance, "");
        this.world = world;
        this.instance = instance;
        this.sections$delegate = LazyKt.lazy((Function0)new ChunkHandleImpl$sections.ChunkHandleImpl$sections$2(this));
        this.tileEntitiesCache = new ConcurrentHashMap<Long, TileEntityHandle>();
        this.minHeight = this.world.getMinHeight();
    }
    
    @NotNull
    @JvmName(name = "getWorld")
    public final World getWorld() {
        return this.world;
    }
    
    @NotNull
    @JvmName(name = "getInstance")
    public final dev.imanity.generated.net.minecraft.world.level.chunk.ChunkHandle getInstance() {
        return this.instance;
    }
    
    @NotNull
    @JvmName(name = "getSections")
    @Override
    public ChunkSectionHandle[] getSections() {
        return (ChunkSectionHandle[])this.sections$delegate.getValue();
    }
    
    @NotNull
    @JvmName(name = "getTileEntities")
    @Override
    public List<TileEntityHandle> getTileEntities() {
        final long currentTimeMillis = System.currentTimeMillis();
        List<? extends TileEntityHandle> tileEntitiesListCache = this.tileEntitiesListCache;
        if (tileEntitiesListCache == null || currentTimeMillis - this.tileEntitiesListUpdate > 1000L) {
            final Collection<?> rawTileEntities = this.instance.getRawTileEntities();
            Intrinsics.checkNotNullExpressionValue((Object)rawTileEntities, "");
            final Iterable iterable;
            final Collection collection = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable = rawTileEntities, 10));
            for (final Object next : iterable) {
                final Collection collection2 = collection;
                final Object o = next;
                final Collection collection3 = collection2;
                final dev.imanity.generated.net.minecraft.world.level.block.entity.TileEntityHandle handle = dev.imanity.generated.net.minecraft.world.level.block.entity.TileEntityHandle.createHandle(o);
                Intrinsics.checkNotNullExpressionValue((Object)handle, "");
                collection3.add(new TileEntityHandleImpl(handle));
            }
            tileEntitiesListCache = (List<? extends TileEntityHandle>)collection;
            this.tileEntitiesListCache = tileEntitiesListCache;
            this.tileEntitiesListUpdate = currentTimeMillis;
        }
        return (List<TileEntityHandle>)tileEntitiesListCache;
    }
    
    @NotNull
    @JvmName(name = "getStructures")
    @Override
    public List<StructureHandle> getStructures() {
        throw new NotImplementedError("An operation is not implemented: " + "Not yet implemented");
    }
    
    @Nullable
    @Override
    public ChunkSectionHandle getSection(final int n) {
        final int sectionY = this.getSectionY(n);
        if (sectionY < 0) {
            return null;
        }
        if (sectionY >= this.getSections().length) {
            return null;
        }
        return this.getSections()[sectionY];
    }
    
    @Override
    public int getSectionY(final int n) {
        return n - this.minHeight >> 4;
    }
    
    @Nullable
    @Override
    public TileEntityHandle getTileEntityAt(final int n, final int n2, final int n3) {
        final long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - this.tileEntitiesUpdate > 1000L) {
            this.tileEntitiesCache.clear();
            this.tileEntitiesUpdate = currentTimeMillis;
        }
        final long long1 = LongHash.INSTANCE.toLong(n, n2, n3);
        final TileEntityHandle tileEntityHandle = this.tileEntitiesCache.get(long1);
        if (tileEntityHandle != null) {
            return tileEntityHandle;
        }
        final Object tileEntity = this.instance.getTileEntity(BlockPosHandle.createNew(n, n2, n3));
        TileEntityHandleImpl tileEntityHandleImpl2;
        if (tileEntity != null) {
            final dev.imanity.generated.net.minecraft.world.level.block.entity.TileEntityHandle handle = dev.imanity.generated.net.minecraft.world.level.block.entity.TileEntityHandle.createHandle(tileEntity);
            Intrinsics.checkNotNullExpressionValue((Object)handle, "");
            final TileEntityHandleImpl tileEntityHandleImpl = new TileEntityHandleImpl(handle);
            this.tileEntitiesCache.put(long1, tileEntityHandleImpl);
            tileEntityHandleImpl2 = tileEntityHandleImpl;
        }
        else {
            tileEntityHandleImpl2 = null;
        }
        return tileEntityHandleImpl2;
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
}
