// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.a;

import java.util.Map;
import dev.imanity.antixray.nms.Native;
import java.util.concurrent.CompletableFuture;
import org.jetbrains.annotations.Nullable;
import io.fairyproject.mc.scheduler.MCSchedulers;
import dev.imanity.antixray.api.util.LongHash;
import dev.imanity.concurrentutil.SeqLockFactory;
import dev.imanity.antixray.nms.Folia;
import kotlin.jvm.internal.Intrinsics;
import dev.imanity.concurrentutil.SeqLock;
import dev.imanity.antixray.api.chunk.DirectChunk;
import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
import org.jetbrains.annotations.NotNull;
import org.bukkit.World;
import dev.imanity.antixray.api.chunk.IDirectChunkCache;

public final class IlIllIlllIIllIlllIIllIIlllIIIllIIllIIlIIIlllIllIIlIlIIllIIIllIlIlIIllIllIlllIlIIllIIIlllIIllIIIlIIIlIIlIIlIIIlllIllIlllIIIlIIllIIIlIllIIIllIIlIIlIIIlIlIlIIIlllIlllIIlIllIIlllIIllIllIIlllIIIllIlIIllIIllIIlIIlIIllIllIIIllIl implements IDirectChunkCache
{
    @NotNull
    private final World IlIllIlllIIlIIlllIllIllIlIlllIIllIIlIIlIIIlIIlIIIllIIllIIlllIIlIIlllIllIIIllIlllIIllIIlIIllIIlllIlllIIllIIIllIlllIIlIIllIIllIIllIIIlIllIIIlIlIIllIIllIIlllIIlIllIIllIIlllIllIlIlIlllIIIllIlIIlIIllIIIlllIIIllIIllIllIIlIIIllIIl;
    @NotNull
    private final IIllIIIlllIIIllIIIllIIlIIllIIllIIIlllIIlIIIllIIllIIIlIIIlllIIlllIIlllIIllIIlIlllIlllIlIlIlllIllIIIllIIIllIlIlIllIlIllIIlllIllIlIIlIIIlIlIIllIlllIIlIllIIIlIlllIIllIllIIlIIlllIIlllIIIllIIllIlllIIlIlIIIllIIllIIIlIIIllIIllIIllIIIlllIIll IIllIIlllIllIlllIlllIIlllIlIIllIIIlIIIlIIIllIlIllIlIlllIIlIIllIIlIIllIIIlllIIlIlllIlIIllIIlIIllIIlllIIlllIIIllIIlllIIlIIlIIllIllIIIllIllIIlIIlIIllIIlIlllIllIlIIlIlllIlIllIIllIlIlllIIIllIlIIllIIlIlIIIlIIllIllIIlllIIIll;
    @NotNull
    private final Long2ObjectOpenHashMap<DirectChunk> IllIlIllIIIllIllIIllIlIllIllIlIIIllIllIIllIlIllIlIIIllIIllIIllIIIllIIlIlIIIlllIIIlllIllIIIllIIllIIlIlIlIlIIllIlIlIlllIllIIllIIIlIlllIlIlIIlIIllIlllIIIllIlIllIIllIlllIIlllIIllIIlIlIllIlllIlIIlllIIllIIlllIIlll;
    @NotNull
    private final SeqLock IIllIIlllIIllIllIlIIlllIlIllIIlllIlIIlllIlIlllIllIIIllIIIllIlIlllIIlllIIlIIlIllIllIllIIllIIlIIllIIllIIIlllIllIlllIIIlIIIlIIllIllIlIIllIIIlllIIIllIlIlIllIIIllIIllIIlllIIlIIlIIlIlllIIllIIllIlIlIIIlIIllIllIIllIIllIIlllIIl;
    
    public IlIllIlllIIllIlllIIllIIlllIIIllIIllIIlIIIlllIllIIlIlIIllIIIllIlIlIIllIllIlllIlIIllIIIlllIIllIIIlIIIlIIlIIlIIIlllIllIlllIIIlIIllIIIlIllIIIllIIlIIlIIIlIlIlIIIlllIlllIIlIllIIlllIIllIllIIlllIIIllIlIIllIIllIIlIIlIIllIllIIIllIl(@NotNull final World ilIllIlllIIlIIlllIllIllIlIlllIIllIIlIIlIIIlIIlIIIllIIllIIlllIIlIIlllIllIIIllIlllIIllIIlIIllIIlllIlllIIllIIIllIlllIIlIIllIIllIIllIIIlIllIIIlIlIIllIIllIIlllIIlIllIIllIIlllIllIlIlIlllIIIllIlIIlIIllIIIlllIIIllIIllIllIIlIIIllIIl, @NotNull final IIllIIIlllIIIllIIIllIIlIIllIIllIIIlllIIlIIIllIIllIIIlIIIlllIIlllIIlllIIllIIlIlllIlllIlIlIlllIllIIIllIIIllIlIlIllIlIllIIlllIllIlIIlIIIlIlIIllIlllIIlIllIIIlIlllIIllIllIIlIIlllIIlllIIIllIIllIlllIIlIlIIIllIIllIIIlIIIllIIllIIllIIIlllIIll iIllIIlllIllIlllIlllIIlllIlIIllIIIlIIIlIIIllIlIllIlIlllIIlIIllIIlIIllIIIlllIIlIlllIlIIllIIlIIllIIlllIIlllIIIllIIlllIIlIIlIIllIllIIIllIllIIlIIlIIllIIlIlllIllIlIIlIlllIlIllIIllIlIlllIIIllIlIIllIIlIlIIIlIIllIllIIlllIIIll) {
        Intrinsics.checkNotNullParameter((Object)ilIllIlllIIlIIlllIllIllIlIlllIIllIIlIIlIIIlIIlIIIllIIllIIlllIIlIIlllIllIIIllIlllIIllIIlIIllIIlllIlllIIllIIIllIlllIIlIIllIIllIIllIIIlIllIIIlIlIIllIIllIIlllIIlIllIIllIIlllIllIlIlIlllIIIllIlIIlIIllIIIlllIIIllIIllIllIIlIIIllIIl, "");
        Intrinsics.checkNotNullParameter((Object)iIllIIlllIllIlllIlllIIlllIlIIllIIIlIIIlIIIllIlIllIlIlllIIlIIllIIlIIllIIIlllIIlIlllIlIIllIIlIIllIIlllIIlllIIIllIIlllIIlIIlIIllIllIIIllIllIIlIIlIIllIIlIlllIllIlIIlIlllIlIllIIllIlIlllIIIllIlIIllIIlIlIIIlIIllIllIIlllIIIll, "");
        this.IlIllIlllIIlIIlllIllIllIlIlllIIllIIlIIlIIIlIIlIIIllIIllIIlllIIlIIlllIllIIIllIlllIIllIIlIIllIIlllIlllIIllIIIllIlllIIlIIllIIllIIllIIIlIllIIIlIlIIllIIllIIlllIIlIllIIllIIlllIllIlIlIlllIIIllIlIIlIIllIIIlllIIIllIIllIllIIlIIIllIIl = ilIllIlllIIlIIlllIllIllIlIlllIIllIIlIIlIIIlIIlIIIllIIllIIlllIIlIIlllIllIIIllIlllIIllIIlIIllIIlllIlllIIllIIIllIlllIIlIIllIIllIIllIIIlIllIIIlIlIIllIIllIIlllIIlIllIIllIIlllIllIlIlIlllIIIllIlIIlIIllIIIlllIIIllIIllIllIIlIIIllIIl;
        this.IIllIIlllIllIlllIlllIIlllIlIIllIIIlIIIlIIIllIlIllIlIlllIIlIIllIIlIIllIIIlllIIlIlllIlIIllIIlIIllIIlllIIlllIIIllIIlllIIlIIlIIllIllIIIllIllIIlIIlIIllIIlIlllIllIlIIlIlllIlIllIIllIlIlllIIIllIlIIllIIlIlIIIlIIllIllIIlllIIIll = iIllIIlllIllIlllIlllIIlllIlIIllIIIlIIIlIIIllIlIllIlIlllIIlIIllIIlIIllIIIlllIIlIlllIlIIllIIlIIllIIlllIIlllIIIllIIlllIIlIIlIIllIllIIIllIllIIlIIlIIllIIlIlllIllIlIIlIlllIlIllIIllIlIlllIIIllIlIIllIIlIlIIIlIIllIllIIlllIIIll;
        this.IllIlIllIIIllIllIIllIlIllIllIlIIIllIllIIllIlIllIlIIIllIIllIIllIIIllIIlIlIIIlllIIIlllIllIIIllIIllIIlIlIlIlIIllIlIlIlllIllIIllIIIlIlllIlIlIIlIIllIlllIIIllIlIllIIllIlllIIlllIIllIIlIlIllIlllIlIIlllIIllIIlllIIlll = (Long2ObjectOpenHashMap<DirectChunk>)new Long2ObjectOpenHashMap(8192);
        SeqLock iIllIIlllIIllIllIlIIlllIlIllIIlllIlIIlllIlIlllIllIIIllIIIllIlIlllIIlllIIlIIlIllIllIllIIllIIlIIllIIllIIIlllIllIlllIIIlIIIlIIllIllIlIIllIIIlllIIIllIlIlIllIIIllIIllIIlllIIlIIlIIlIlllIIllIIllIlIlIIIlIIllIllIIllIIllIIlllIIl;
        if (Folia.INSTANCE.getIS_FOLIA()) {
            Intrinsics.checkNotNullExpressionValue((Object)(iIllIIlllIIllIllIlIIlllIlIllIIlllIlIIlllIlIlllIllIIIllIIIllIlIlllIIlllIIlIIlIllIllIllIIllIIlIIllIIllIIIlllIllIlllIIIlIIIlIIllIllIlIIllIIIlllIIIllIlIlIllIIIllIIllIIlllIIlIIlIIlIlllIIllIIllIlIlIIIlIIllIllIIllIIllIIlllIIl = SeqLockFactory.createVoliatileSeqLock()), "");
        }
        else {
            Intrinsics.checkNotNullExpressionValue((Object)(iIllIIlllIIllIllIlIIlllIlIllIIlllIlIIlllIlIlllIllIIIllIIIllIlIlllIIlllIIlIIlIllIllIllIIllIIlIIllIIllIIIlllIllIlllIIIlIIIlIIllIllIlIIllIIIlllIIIllIlIlIllIIIllIIllIIlllIIlIIlIIlIlllIIllIIllIlIlIIIlIIllIllIIllIIllIIlllIIl = SeqLockFactory.createWeakSeqLock()), "");
        }
        this.IIllIIlllIIllIllIlIIlllIlIllIIlllIlIIlllIlIlllIllIIIllIIIllIlIlllIIlllIIlIIlIllIllIllIIllIIlIIllIIllIIIlllIllIlllIIIlIIIlIIllIllIlIIllIIIlllIIIllIlIlIllIIIllIIllIIlllIIlIIlIIlIlllIIllIIllIlIlIIIlIIllIllIIllIIllIIlllIIl = iIllIIlllIIllIllIlIIlllIlIllIIlllIlIIlllIlIlllIllIIIllIIIllIlIlllIIlllIIlIIlIllIllIllIIllIIlIIllIIllIIIlllIllIlllIIIlIIIlIIllIllIlIIllIIIlllIIIllIlIlIllIIIllIIllIIlllIIlIIlIIlIlllIIllIIllIlIlIIIlIIllIllIIllIIllIIlllIIl;
    }
    
    @Override
    public void tick() {
        this.IIllIIlllIllIlllIlllIIlllIlIIllIIIlIIIlIIIllIlIllIlIlllIIlIIllIIlIIllIIIlllIIlIlllIlIIllIIlIIllIIlllIIlllIIIllIIlllIIlIIlIIllIllIIIllIllIIlIIlIIllIIlIlllIllIlIIlIlllIlIllIIllIlIlllIIIllIlIIllIIlIlIIIlIIllIllIIlllIIIll.IIIlIlIIllIIlIIllIllIlllIlIIlllIllIIIllIIlIIllIlllIlIIlIIllIllIIIlIIllIllIIllIlIlIIllIIlllIIllIllIIlIllIIllIlllIIIlllIIllIIlllIIlllIIlIlllIlllIlllIIIllIllIIllIllIlIlllIIllIIlllIIlIIllIIllIIlIlllIIlIIIllIIllIIllIIlIllIll();
    }
    
    @Nullable
    @Override
    public DirectChunk get(final int n, final int n2) {
        final long long1 = LongHash.INSTANCE.toLong(n, n2);
        if (!Folia.INSTANCE.getIS_FOLIA() && MCSchedulers.getGlobalScheduler().isCurrentThread()) {
            return (DirectChunk)this.IllIlIllIIIllIllIIllIlIllIllIlIIIllIllIIllIlIllIlIIIllIIllIIllIIIllIIlIlIIIlllIIIlllIllIIIllIIllIIlIlIlIlIIllIlIlIlllIllIIllIIIlIlllIlIlIIlIIllIlllIIIllIlIllIIllIlllIIlllIIllIIlIlIllIlllIlIIlllIIllIIlllIIlll.get(long1);
        }
        DirectChunk directChunk = null;
        long acquireRead;
        do {
            acquireRead = this.IIllIIlllIIllIllIlIIlllIlIllIIlllIlIIlllIlIlllIllIIIllIIIllIlIlllIIlllIIlIIlIllIllIllIIllIIlIIllIIllIIIlllIllIlllIIIlIIIlIIllIllIlIIllIIIlllIIIllIlIlIllIIIllIIllIIlllIIlIIlIIlIlllIIllIIllIlIlIIIlIIllIllIIllIIllIIlllIIl.acquireRead();
            try {
                directChunk = (DirectChunk)this.IllIlIllIIIllIllIIllIlIllIllIlIIIllIllIIllIlIllIlIIIllIIllIIllIIIllIIlIlIIIlllIIIlllIllIIIllIIllIIlIlIlIlIIllIlIlIlllIllIIllIIIlIlllIlIlIIlIIllIlllIIIllIlIllIIllIlllIIlllIIllIIlIlIllIlllIlIIlllIIllIIlllIIlll.get(long1);
            }
            catch (final Throwable t) {
                if (t instanceof ThreadDeath) {
                    throw t;
                }
            }
        } while (!this.IIllIIlllIIllIllIlIIlllIlIllIIlllIlIIlllIlIlllIllIIIllIIIllIlIlllIIlllIIlIIlIllIllIllIIllIIlIIllIIllIIIlllIllIlllIIIlIIIlIIllIllIlIIllIIIlllIIIllIlIlIllIIIllIIllIIlllIIlIIlIIlIlllIIllIIllIlIlIIIlIIllIllIIllIIllIIlllIIl.tryReleaseRead(acquireRead));
        return directChunk;
    }
    
    @NotNull
    @Override
    public CompletableFuture<DirectChunk> getOrLoadAsync(final int n, final int n2, final boolean b) {
        return this.IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(n, n2, b ? IIllIIlllIlIIIllIIIllIIIlllIIllIIlllIllIIIllIllIIIllIlIllIlIlIIllIIllIllIIlIIlllIIlIlIlIllIllIIlIlllIIllIIllIIlIlllIIIllIIIllIIlIIIlllIIIlllIIllIIllIllIIIlllIIlIIllIlIIlllIIlIIIllIlllIIlIllIlIlIIllIIlIIllIIllIllIIIllIIIllIIlll.IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll.dc : IIllIIlllIlIIIllIIIllIIIlllIIllIIlllIllIIIllIllIIIllIlIllIlIlIIllIIllIllIIlIIlllIIlIlIlIllIllIIlIlllIIllIIllIIlIlllIIIllIIIllIIlIIIlllIIIlllIIllIIllIllIIIlllIIlIIllIlIIlllIIlIIIllIlllIIlIllIlIlIIllIIlIIllIIllIllIIIllIIIllIIlll.IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll.db);
    }
    
    @NotNull
    public final CompletableFuture<DirectChunk> IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(final int n, final int n2, @NotNull final IIllIIlllIlIIIllIIIllIIIlllIIllIIlllIllIIIllIllIIIllIlIllIlIlIIllIIllIllIIlIIlllIIlIlIlIllIllIIlIlllIIllIIllIIlIlllIIIllIIIllIIlIIIlllIIIlllIIllIIllIllIIIlllIIlIIllIlIIlllIIlIIIllIlllIIlIllIlIlIIllIIlIIllIIllIllIIIllIIIllIIlll.IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll illIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll) {
        Intrinsics.checkNotNullParameter((Object)illIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll, "");
        final DirectChunk value = this.get(n, n2);
        if (value != null) {
            final CompletableFuture<DirectChunk> completedFuture = CompletableFuture.completedFuture(value);
            Intrinsics.checkNotNullExpressionValue((Object)completedFuture, "");
            return completedFuture;
        }
        return this.IIllIIlllIllIlllIlllIIlllIlIIllIIIlIIIlIIIllIlIllIlIlllIIlIIllIIlIIllIIIlllIIlIlllIlIIllIIlIIllIIlllIIlllIIIllIIlllIIlIIlIIllIllIIIllIllIIlIIlIIllIIlIlllIllIlIIlIlllIlIllIIllIlIlllIIIllIlIIllIIlIlIIIlIIllIllIIlllIIIll.IlllIIlIIIlllIIIlIIllIlIllIIIlIIIllIlllIIIllIIlIIllIIllIlllIIllIIllIIllIIllIIlllIIlllIllIIllIlllIlIllIIlllIllIllIIlllIIllIIIlllIIIllIIIllIlllIIlllIIlIlIlIlIllIIIlllIIlIllIIllIIlllIIlIIIllIIIllIIlIIlllIlIllIllIIlIIIllIllIIlIIllIll(n, n2, illIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll);
    }
    
    @NotNull
    @Override
    public DirectChunk load(final int n, final int n2) {
        DirectChunk value;
        if ((value = this.get(n, n2)) == null) {
            final DirectChunk directChunk = new DirectChunk(n, n2, Native.INSTANCE.getChunkAt(this.IlIllIlllIIlIIlllIllIllIlIlllIIllIIlIIlIIIlIIlIIIllIIllIIlllIIlIIlllIllIIIllIlllIIllIIlIIllIIlllIlllIIllIIIllIlllIIlIIllIIllIIllIIIlIllIIIlIlIIllIIllIIlllIIlIllIIllIIlllIllIlIlIlllIIIllIlIIlIIllIIIlllIIIllIIllIllIIlIIIllIIl, n, n2));
            this.add(directChunk);
            value = directChunk;
        }
        return value;
    }
    
    @Nullable
    @Override
    public DirectChunk unload(final int n, final int n2) {
        DirectChunk directChunk = null;
        this.IIllIIlllIIllIllIlIIlllIlIllIIlllIlIIlllIlIlllIllIIIllIIIllIlIlllIIlllIIlIIlIllIllIllIIllIIlIIllIIllIIIlllIllIlllIIIlIIIlIIllIllIlIIllIIIlllIIIllIlIlIllIIIllIIllIIlllIIlIIlIIlIlllIIllIIllIlIlIIIlIIllIllIIllIIllIIlllIIl.acquireWrite();
        try {
            directChunk = (DirectChunk)this.IllIlIllIIIllIllIIllIlIllIllIlIIIllIllIIllIlIllIlIIIllIIllIIllIIIllIIlIlIIIlllIIIlllIllIIIllIIllIIlIlIlIlIIllIlIlIlllIllIIllIIIlIlllIlIlIIlIIllIlllIIIllIlIllIIllIlllIIlllIIllIIlIlIllIlllIlIIlllIIllIIlllIIlll.remove(LongHash.INSTANCE.toLong(n, n2));
        }
        finally {
            this.IIllIIlllIIllIllIlIIlllIlIllIIlllIlIIlllIlIlllIllIIIllIIIllIlIlllIIlllIIlIIlIllIllIllIIllIIlIIllIIllIIIlllIllIlllIIIlIIIlIIllIllIlIIllIIIlllIIIllIlIlIllIIIllIIllIIlllIIlIIlIIlIlllIIllIIllIlIlIIIlIIllIllIIllIIllIIlllIIl.releaseWrite();
        }
        return directChunk;
    }
    
    @Override
    public void add(@NotNull final DirectChunk directChunk) {
        Intrinsics.checkNotNullParameter((Object)directChunk, "");
        this.IIllIIlllIIllIllIlIIlllIlIllIIlllIlIIlllIlIlllIllIIIllIIIllIlIlllIIlllIIlIIlIllIllIllIIllIIlIIllIIllIIIlllIllIlllIIIlIIIlIIllIllIlIIllIIIlllIIIllIlIlIllIIIllIIllIIlllIIlIIlIIlIlllIIllIIllIlIlIIIlIIllIllIIllIIllIIlllIIl.acquireWrite();
        try {
            ((Map)this.IllIlIllIIIllIllIIllIlIllIllIlIIIllIllIIllIlIllIlIIIllIIllIIllIIIllIIlIlIIIlllIIIlllIllIIIllIIllIIlIlIlIlIIllIlIlIlllIllIIllIIIlIlllIlIlIIlIIllIlllIIIllIlIllIIllIlllIIlllIIllIIlIlIllIlllIlIIlllIIllIIlllIIlll).put(directChunk.getChunkKey(), directChunk);
        }
        finally {
            this.IIllIIlllIIllIllIlIIlllIlIllIIlllIlIIlllIlIlllIllIIIllIIIllIlIlllIIlllIIlIIlIllIllIllIIllIIlIIllIIllIIIlllIllIlllIIIlIIIlIIllIllIlIIllIIIlllIIIllIlIlIllIIIllIIllIIlllIIlIIlIIlIlllIIllIIllIlIlIIIlIIllIllIIllIIllIIlllIIl.releaseWrite();
        }
    }
    
    @Nullable
    @Override
    public DirectChunk remove(@NotNull final DirectChunk directChunk) {
        Intrinsics.checkNotNullParameter((Object)directChunk, "");
        DirectChunk directChunk2 = null;
        this.IIllIIlllIIllIllIlIIlllIlIllIIlllIlIIlllIlIlllIllIIIllIIIllIlIlllIIlllIIlIIlIllIllIllIIllIIlIIllIIllIIIlllIllIlllIIIlIIIlIIllIllIlIIllIIIlllIIIllIlIlIllIIIllIIllIIlllIIlIIlIIlIlllIIllIIllIlIlIIIlIIllIllIIllIIllIIlllIIl.acquireWrite();
        try {
            directChunk2 = (DirectChunk)this.IllIlIllIIIllIllIIllIlIllIllIlIIIllIllIIllIlIllIlIIIllIIllIIllIIIllIIlIlIIIlllIIIlllIllIIIllIIllIIlIlIlIlIIllIlIlIlllIllIIllIIIlIlllIlIlIIlIIllIlllIIIllIlIllIIllIlllIIlllIIllIIlIlIllIlllIlIIlllIIllIIlllIIlll.remove(directChunk.getChunkKey());
        }
        finally {
            this.IIllIIlllIIllIllIlIIlllIlIllIIlllIlIIlllIlIlllIllIIIllIIIllIlIlllIIlllIIlIIlIllIllIllIIllIIlIIllIIllIIIlllIllIlllIIIlIIIlIIllIllIlIIllIIIlllIIIllIlIlIllIIIllIIllIIlllIIlIIlIIlIlllIIllIIllIlIlIIIlIIllIllIIllIIllIIlllIIl.releaseWrite();
        }
        return directChunk2;
    }
    
    @Override
    public void reset() {
        this.IIllIIlllIIllIllIlIIlllIlIllIIlllIlIIlllIlIlllIllIIIllIIIllIlIlllIIlllIIlIIlIllIllIllIIllIIlIIllIIllIIIlllIllIlllIIIlIIIlIIllIllIlIIllIIIlllIIIllIlIlIllIIIllIIllIIlllIIlIIlIIlIlllIIllIIllIlIlIIIlIIllIllIIllIIllIIlllIIl.acquireWrite();
        try {
            this.IllIlIllIIIllIllIIllIlIllIllIlIIIllIllIIllIlIllIlIIIllIIllIIllIIIllIIlIlIIIlllIIIlllIllIIIllIIllIIlIlIlIlIIllIlIlIlllIllIIllIIIlIlllIlIlIIlIIllIlllIIIllIlIllIIllIlllIIlllIIllIIlIlIllIlllIlIIlllIIllIIlllIIlll.clear();
        }
        finally {
            this.IIllIIlllIIllIllIlIIlllIlIllIIlllIlIIlllIlIlllIllIIIllIIIllIlIlllIIlllIIlIIlIllIllIllIIllIIlIIllIIllIIIlllIllIlllIIIlIIIlIIllIllIlIIllIIIlllIIIllIlIlIllIIIllIIllIIlllIIlIIlIIlIlllIIllIIllIlIlIIIlIIllIllIIllIIllIIlllIIl.releaseWrite();
        }
    }
}
