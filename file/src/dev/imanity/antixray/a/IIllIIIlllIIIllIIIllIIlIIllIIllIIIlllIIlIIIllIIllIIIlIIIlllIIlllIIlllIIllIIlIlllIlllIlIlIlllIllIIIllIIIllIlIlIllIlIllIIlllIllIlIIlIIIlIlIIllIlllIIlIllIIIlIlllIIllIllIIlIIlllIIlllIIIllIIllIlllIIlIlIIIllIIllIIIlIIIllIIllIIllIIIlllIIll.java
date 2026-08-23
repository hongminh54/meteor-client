// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.a;

import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.Metadata;
import kotlin.jvm.internal.Lambda;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicBoolean;
import dev.imanity.antixray.api.nms.ChunkHandle;
import dev.imanity.antixray.api.chunk.DirectChunk;
import java.util.concurrent.CompletableFuture;
import io.fairyproject.mc.scheduler.MCSchedulers;
import kotlin.jvm.internal.Intrinsics;
import java.util.concurrent.locks.ReentrantLock;
import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
import io.fairyproject.mc.scheduler.MCScheduler;
import dev.imanity.antixray.config.AntiXrayConfig;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class IIllIIIlllIIIllIIIllIIlIIllIIllIIIlllIIlIIIllIIllIIIlIIIlllIIlllIIlllIIllIIlIlllIlllIlIlIlllIllIIIllIIIllIlIlIllIlIllIIlllIllIlIIlIIIlIlIIllIlllIIlIllIIIlIlllIIllIllIIlIIlllIIlllIIIllIIllIlllIIlIlIIIllIIllIIIlIIIllIIllIIllIIIlllIIll
{
    @Nullable
    private final IIllIIlIlIIlllIIllIIllIIIlllIIIlllIllIIllIIlllIlIIllIIllIllIIlIIllIIlIlllIIIlIIlIllIIIllIIIlllIIlIIIlllIIIlllIIlIllIIIllIIlIlIllIlIIllIlIIIlllIIllIIlIIIlIIlIIlIlIlllIlIIIlIIlIIlllIllIllIllIIIlllIIllIIIllIIllIIlllIIlIllIlIIll IlllIIIlIlIlllIIIlIIlIIlIIIlIIlIIlIIllIlllIlIIlllIIllIIlllIlllIlllIIlllIIllIlllIIllIlIllIIlIIIlIIIlllIIlllIIIllIIllIIlllIlllIllIIllIIIlllIIlIIllIllIIlIIllIIllIIlllIIIlllIIlIIllIIIlllIlIIllIllIIlllIIlIIlIllIIllIIlllIllIllIllIlIl;
    @NotNull
    private final IIlllIIIllIIllIIllIlIIIlIIIllIIIlIIlllIIIlIlllIIlIIllIlIlIlIlIIIlllIIIlllIIIlllIIIlllIIlIllIIllIlllIllIIlllIlllIlllIIlIIlIIIllIllIIIlllIIllIIlIlllIIllIIIllIIlIIllIIIlIllIIIllIIllIIIlIIllIIlllIIlllIIIllIllIIIllIIllIIllIlIllIllIIIlIIllIIl IIlllIIlIlllIIIlllIIIlIIIlllIlIIIllIIIllIIlIIIllIlIIllIIIllIIIllIIlIIllIIIlIlllIIllIIllIllIIllIIlllIlIIlIlllIllIllIllIIlllIIlllIIIllIlllIIlllIlIIlIIllIllIIllIIllIlIllIIlllIIIlllIIllIlIIIllIllIlIlIIllIllIlllIIIlllIIllIIlIIlllIIllIIl;
    @NotNull
    private final AntiXrayConfig IIllIIllIIlIIllIIllIIlllIIlllIllIIIllIIIlllIIlIlIIlIIllIlIIllIllIIlIIllIllIlIIllIllIlllIIIlIIIllIIllIIlIllIIIllIlIIllIIllIIllIlIIlllIIlIIIlllIIIlllIIllIIlllIIIlllIIIllIIIllIIllIIllIIllIlllIlIIIlllIIlIIIlIlllIIllIIllIIllIIIlIIllIIlllIlll;
    private final MCScheduler IllIllIIIlIIIlllIIllIIlIIlllIIIlIlIIllIIlIIIlIIllIIIllIIlIIIllIIlIlllIlllIllIIIlIIllIIIllIIlIllIIIllIIIllIlIIIlIIllIlIlIIlllIIIllIlIllIIlllIIIlIllIllIlIlIIIllIIllIIllIIlIllIIlIlIIlIIllIIllIlIlllIIIllIIlllIlIIlIIlIIIlll;
    @NotNull
    private final IIllIIlllIlIIIllIIIllIIIlllIIllIIlllIllIIIllIllIIIllIlIllIlIlIIllIIllIllIIlIIlllIIlIlIlIllIllIIlIlllIIllIIllIIlIlllIIIllIIIllIIlIIIlllIIIlllIIllIIllIllIIIlllIIlIIllIlIIlllIIlIIIllIlllIIlIllIlIlIIllIIlIIllIIllIllIIIllIIIllIIlll<IIllIllIIllIlIIlIlIIlllIllIIllIIllIlIIllIIIllIIIllIIllIIIlIllIIlIIlllIllIIlIllIllIIlIIlIlIIIlIllIIllIlllIllIIIllIIlIIlllIllIIlIIIlIIllIIllIIlIlIIlIllIIlllIlIIlllIIlIlIIlIIlIlllIIlIIIlIIllIllIlllIIlllIIIlIIllIIIlll> IIIlllIlIIlllIIlllIlIlIlIlllIIIllIIIllIIlIlIIIllIlIlIIllIIllIIlllIIllIlllIIIllIIIlIIIlIIllIIllIIIlIllIIllIIIllIIlllIllIIIllIIllIIIllIlIIIllIIIlllIllIlIlIIIlllIIlllIIllIIlllIIllIlIIlllIIIlllIIllIIlIIllIlIllIIIllIlllIIlllIlIlIIllIlll;
    @NotNull
    private final Long2ObjectOpenHashMap<IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll> IIllIIIlIllIIlIIlIllIIlllIIlIIlIIlIIllIllIIlIIIlIIllIIlIIlllIlIIIllIllIIIlllIIlllIllIIIlIlllIIIllIIlIlllIIllIIIlllIIllIlllIllIIllIIIllIIIlIIlIIIlllIllIIIllIIlllIllIIIllIIIllIIlIIlllIIIlIIIlIIllIIlllIlIIIlllIIllIIIlIIIllIlIIIllIIlllIIlllIlll;
    @NotNull
    private final ReentrantLock IIllIIlllIlIIIllIIIllIIIlllIIllIIlllIllIIIllIllIIIllIlIllIlIlIIllIIllIllIIlIIlllIIlIlIlIllIllIIlIlllIIllIIllIIlIlllIIIllIIIllIIlIIIlllIIIlllIIllIIllIllIIIlllIIlIIllIlIIlllIIlIIIllIlllIIlIllIlIlIIllIIlIIllIIllIllIIIllIIIllIIlll;
    
    public IIllIIIlllIIIllIIIllIIlIIllIIllIIIlllIIlIIIllIIllIIIlIIIlllIIlllIIlllIIllIIlIlllIlllIlIlIlllIllIIIllIIIllIlIlIllIlIllIIlllIllIlIIlIIIlIlIIllIlllIIlIllIIIlIlllIIllIllIIlIIlllIIlllIIIllIIllIlllIIlIlIIIllIIllIIIlIIIllIIllIIllIIIlllIIll(@Nullable final IIllIIlIlIIlllIIllIIllIIIlllIIIlllIllIIllIIlllIlIIllIIllIllIIlIIllIIlIlllIIIlIIlIllIIIllIIIlllIIlIIIlllIIIlllIIlIllIIIllIIlIlIllIlIIllIlIIIlllIIllIIlIIIlIIlIIlIlIlllIlIIIlIIlIIlllIllIllIllIIIlllIIllIIIllIIllIIlllIIlIllIlIIll illlIIIlIlIlllIIIlIIlIIlIIIlIIlIIlIIllIlllIlIIlllIIllIIlllIlllIlllIIlllIIllIlllIIllIlIllIIlIIIlIIIlllIIlllIIIllIIllIIlllIlllIllIIllIIIlllIIlIIllIllIIlIIllIIllIIlllIIIlllIIlIIllIIIlllIlIIllIllIIlllIIlIIlIllIIllIIlllIllIllIllIlIl, @NotNull final IIlllIIIllIIllIIllIlIIIlIIIllIIIlIIlllIIIlIlllIIlIIllIlIlIlIlIIIlllIIIlllIIIlllIIIlllIIlIllIIllIlllIllIIlllIlllIlllIIlIIlIIIllIllIIIlllIIllIIlIlllIIllIIIllIIlIIllIIIlIllIIIllIIllIIIlIIllIIlllIIlllIIIllIllIIIllIIllIIllIlIllIllIIIlIIllIIl iIlllIIlIlllIIIlllIIIlIIIlllIlIIIllIIIllIIlIIIllIlIIllIIIllIIIllIIlIIllIIIlIlllIIllIIllIllIIllIIlllIlIIlIlllIllIllIllIIlllIIlllIIIllIlllIIlllIlIIlIIllIllIIllIIllIlIllIIlllIIIlllIIllIlIIIllIllIlIlIIllIllIlllIIIlllIIllIIlIIlllIIllIIl, @NotNull final AntiXrayConfig iIllIIllIIlIIllIIllIIlllIIlllIllIIIllIIIlllIIlIlIIlIIllIlIIllIllIIlIIllIllIlIIllIllIlllIIIlIIIllIIllIIlIllIIIllIlIIllIIllIIllIlIIlllIIlIIIlllIIIlllIIllIIlllIIIlllIIIllIIIllIIllIIllIIllIlllIlIIIlllIIlIIIlIlllIIllIIllIIllIIIlIIllIIlllIlll) {
        Intrinsics.checkNotNullParameter((Object)iIlllIIlIlllIIIlllIIIlIIIlllIlIIIllIIIllIIlIIIllIlIIllIIIllIIIllIIlIIllIIIlIlllIIllIIllIllIIllIIlllIlIIlIlllIllIllIllIIlllIIlllIIIllIlllIIlllIlIIlIIllIllIIllIIllIlIllIIlllIIIlllIIllIlIIIllIllIlIlIIllIllIlllIIIlllIIllIIlIIlllIIllIIl, "");
        Intrinsics.checkNotNullParameter((Object)iIllIIllIIlIIllIIllIIlllIIlllIllIIIllIIIlllIIlIlIIlIIllIlIIllIllIIlIIllIllIlIIllIllIlllIIIlIIIllIIllIIlIllIIIllIlIIllIIllIIllIlIIlllIIlIIIlllIIIlllIIllIIlllIIIlllIIIllIIIllIIllIIllIIllIlllIlIIIlllIIlIIIlIlllIIllIIllIIllIIIlIIllIIlllIlll, "");
        this.IlllIIIlIlIlllIIIlIIlIIlIIIlIIlIIlIIllIlllIlIIlllIIllIIlllIlllIlllIIlllIIllIlllIIllIlIllIIlIIIlIIIlllIIlllIIIllIIllIIlllIlllIllIIllIIIlllIIlIIllIllIIlIIllIIllIIlllIIIlllIIlIIllIIIlllIlIIllIllIIlllIIlIIlIllIIllIIlllIllIllIllIlIl = illlIIIlIlIlllIIIlIIlIIlIIIlIIlIIlIIllIlllIlIIlllIIllIIlllIlllIlllIIlllIIllIlllIIllIlIllIIlIIIlIIIlllIIlllIIIllIIllIIlllIlllIllIIllIIIlllIIlIIllIllIIlIIllIIllIIlllIIIlllIIlIIllIIIlllIlIIllIllIIlllIIlIIlIllIIllIIlllIllIllIllIlIl;
        this.IIlllIIlIlllIIIlllIIIlIIIlllIlIIIllIIIllIIlIIIllIlIIllIIIllIIIllIIlIIllIIIlIlllIIllIIllIllIIllIIlllIlIIlIlllIllIllIllIIlllIIlllIIIllIlllIIlllIlIIlIIllIllIIllIIllIlIllIIlllIIIlllIIllIlIIIllIllIlIlIIllIllIlllIIIlllIIllIIlIIlllIIllIIl = iIlllIIlIlllIIIlllIIIlIIIlllIlIIIllIIIllIIlIIIllIlIIllIIIllIIIllIIlIIllIIIlIlllIIllIIllIllIIllIIlllIlIIlIlllIllIllIllIIlllIIlllIIIllIlllIIlllIlIIlIIllIllIIllIIllIlIllIIlllIIIlllIIllIlIIIllIllIlIlIIllIllIlllIIIlllIIllIIlIIlllIIllIIl;
        this.IIllIIllIIlIIllIIllIIlllIIlllIllIIIllIIIlllIIlIlIIlIIllIlIIllIllIIlIIllIllIlIIllIllIlllIIIlIIIllIIllIIlIllIIIllIlIIllIIllIIllIlIIlllIIlIIIlllIIIlllIIllIIlllIIIlllIIIllIIIllIIllIIllIIllIlllIlIIIlllIIlIIIlIlllIIllIIllIIllIIIlIIllIIlllIlll = iIllIIllIIlIIllIIllIIlllIIlllIllIIIllIIIlllIIlIlIIlIIllIlIIllIllIIlIIllIllIlIIllIllIlllIIIlIIIllIIllIIlIllIIIllIlIIllIIllIIllIlIIlllIIlIIIlllIIIlllIIllIIlllIIIlllIIIllIIIllIIllIIllIIllIlllIlIIIlllIIlIIIlIlllIIllIIllIIllIIIlIIllIIlllIlll;
        this.IllIllIIIlIIIlllIIllIIlIIlllIIIlIlIIllIIlIIIlIIllIIIllIIlIIIllIIlIlllIlllIllIIIlIIllIIIllIIlIllIIIllIIIllIlIIIlIIllIlIlIIlllIIIllIlIllIIlllIIIlIllIllIlIlIIIllIIllIIllIIlIllIIlIlIIlIIllIIllIlIlllIIIllIIlllIlIIlIIlIIIlll = MCSchedulers.getAsyncScheduler();
        this.IIIlllIlIIlllIIlllIlIlIlIlllIIIllIIIllIIlIlIIIllIlIlIIllIIllIIlllIIllIlllIIIllIIIlIIIlIIllIIllIIIlIllIIllIIIllIIlllIllIIIllIIllIIIllIlIIIllIIIlllIllIlIlIIIlllIIlllIIllIIlllIIllIlIIlllIIIlllIIllIIlIIllIlIllIIIllIlllIIlllIlIlIIllIlll = new IIllIIlllIlIIIllIIIllIIIlllIIllIIlllIllIIIllIllIIIllIlIllIlIlIIllIIllIllIIlIIlllIIlIlIlIllIllIIlIlllIIllIIllIIlIlllIIIllIIIllIIlIIIlllIIIlllIIllIIllIllIIIlllIIlIIllIlIIlllIIlIIIllIlllIIlIllIlIlIIllIIlIIllIIllIllIIIllIIIllIIlll<IIllIllIIllIlIIlIlIIlllIllIIllIIllIlIIllIIIllIIIllIIllIIIlIllIIlIIlllIllIIlIllIllIIlIIlIlIIIlIllIIllIlllIllIIIllIIlIIlllIllIIlIIIlIIllIIllIIlIlIIlIllIIlllIlIIlllIIlIlIIlIIlIlllIIlIIIlIIllIllIlllIIlllIIIlIIllIIIlll>();
        this.IIllIIIlIllIIlIIlIllIIlllIIlIIlIIlIIllIllIIlIIIlIIllIIlIIlllIlIIIllIllIIIlllIIlllIllIIIlIlllIIIllIIlIlllIIllIIIlllIIllIlllIllIIllIIIllIIIlIIlIIIlllIllIIIllIIlllIllIIIllIIIllIIlIIlllIIIlIIIlIIllIIlllIlIIIlllIIllIIIlIIIllIlIIIllIIlllIIlllIlll = (Long2ObjectOpenHashMap<IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll>)new Long2ObjectOpenHashMap();
        this.IIllIIlllIlIIIllIIIllIIIlllIIllIIlllIllIIIllIllIIIllIlIllIlIlIIllIIllIllIIlIIlllIIlIlIlIllIllIIlIlllIIllIIllIIlIlllIIIllIIIllIIlIIIlllIIIlllIIllIIllIllIIIlllIIlIIllIlIIlllIIlIIIllIlllIIlIllIlIlIIllIIlIIllIIllIllIIIllIIIllIIlll = new ReentrantLock();
    }
    
    @NotNull
    public final CompletableFuture<DirectChunk> IlllIIlIIIlllIIIlIIllIlIllIIIlIIIllIlllIIIllIIlIIllIIllIlllIIllIIllIIllIIllIIlllIIlllIllIIllIlllIlIllIIlllIllIllIIlllIIllIIIlllIIIllIIIllIlllIIlllIIlIlIlIlIllIIIlllIIlIllIIllIIlllIIlIIIllIIIllIIlIIlllIlIllIllIIlIIIllIllIIlIIllIll(final int p0, final int p1, @NotNull final IIllIIlllIlIIIllIIIllIIIlllIIllIIlllIllIIIllIllIIIllIlIllIlIlIIllIIllIllIIlIIlllIIlIlIlIllIllIIlIlllIIllIIllIIlIlllIIIllIIIllIIlIIIlllIIIlllIIllIIllIllIIIlllIIlIIllIlIIlllIIlIIIllIlllIIlIllIlIlIIllIIlIIllIIllIllIIIllIIIllIIlll.IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll p2) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: ldc             ""
        //     3: invokestatic    kotlin/jvm/internal/Intrinsics.checkNotNullParameter:(Ljava/lang/Object;Ljava/lang/String;)V
        //     6: getstatic       dev/imanity/antixray/api/util/LongHash.INSTANCE:Ldev/imanity/antixray/api/util/LongHash;
        //     9: iload_1        
        //    10: iload_2        
        //    11: invokevirtual   dev/imanity/antixray/api/util/LongHash.toLong:(II)J
        //    14: lstore          4
        //    16: aload_0        
        //    17: getfield        dev/imanity/antixray/a/IIllIIIlllIIIllIIIllIIlIIllIIllIIIlllIIlIIIllIIllIIIlIIIlllIIlllIIlllIIllIIlIlllIlllIlIlIlllIllIIIllIIIllIlIlIllIlIllIIlllIllIlIIlIIIlIlIIllIlllIIlIllIIIlIlllIIllIllIIlIIlllIIlllIIIllIIllIlllIIlIlIIIllIIllIIIlIIIllIIllIIllIIIlllIIll.IIllIIlllIlIIIllIIIllIIIlllIIllIIlllIllIIIllIllIIIllIlIllIlIlIIllIIllIllIIlIIlllIIlIlIlIllIllIIlIlllIIllIIllIIlIlllIIIllIIIllIIlIIIlllIIIlllIIllIIllIllIIIlllIIlIIllIlIIlllIIlIIIllIlllIIlIllIlIlIIllIIlIIllIIllIllIIIllIIIllIIlll:Ljava/util/concurrent/locks/ReentrantLock;
        //    20: checkcast       Ljava/util/concurrent/locks/Lock;
        //    23: astore          6
        //    25: aload           6
        //    27: invokeinterface java/util/concurrent/locks/Lock.lock:()V
        //    32: nop            
        //    33: iconst_0       
        //    34: istore          7
        //    36: aload_0        
        //    37: getfield        dev/imanity/antixray/a/IIllIIIlllIIIllIIIllIIlIIllIIllIIIlllIIlIIIllIIllIIIlIIIlllIIlllIIlllIIllIIlIlllIlllIlIlIlllIllIIIllIIIllIlIlIllIlIllIIlllIllIlIIlIIIlIlIIllIlllIIlIllIIIlIlllIIllIllIIlIIlllIIlllIIIllIIllIlllIIlIlIIIllIIllIIIlIIIllIIllIIllIIIlllIIll.IIllIIIlIllIIlIIlIllIIlllIIlIIlIIlIIllIllIIlIIIlIIllIIlIIlllIlIIIllIllIIIlllIIlllIllIIIlIlllIIIllIIlIlllIIllIIIlllIIllIlllIllIIllIIIllIIIlIIlIIIlllIllIIIllIIlllIllIIIllIIIllIIlIIlllIIIlIIIlIIllIIlllIlIIIlllIIllIIIlIIIllIlIIIllIIlllIIlllIlll:Lit/unimi/dsi/fastutil/longs/Long2ObjectOpenHashMap;
        //    40: lload           4
        //    42: invokevirtual   it/unimi/dsi/fastutil/longs/Long2ObjectOpenHashMap.get:(J)Ljava/lang/Object;
        //    45: checkcast       Ldev/imanity/antixray/a/IIllIIIlllIIIllIIIllIIlIIllIIllIIIlllIIlIIIllIIllIIIlIIIlllIIlllIIlllIIllIIlIlllIlllIlIlIlllIllIIIllIIIllIlIlIllIlIllIIlllIllIlIIlIIIlIlIIllIlllIIlIllIIIlIlllIIllIllIIlIIlllIIlllIIIllIIllIlllIIlIlIIIllIIllIIIlIIIllIIllIIllIIIlllIIll$IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll;
        //    48: astore          8
        //    50: aload           8
        //    52: dup            
        //    53: ifnull          84
        //    56: invokestatic    kotlin/jvm/internal/Intrinsics.checkNotNull:(Ljava/lang/Object;)V
        //    59: aload           8
        //    61: astore          9
        //    63: iconst_0       
        //    64: istore          10
        //    66: aload           9
        //    68: aload_3        
        //    69: invokeinterface dev/imanity/antixray/a/IIllIIIlllIIIllIIIllIIlIIllIIllIIIlllIIlIIIllIIllIIIlIIIlllIIlllIIlllIIllIIlIlllIlllIlIlIlllIllIIIllIIIllIlIlIllIlIllIIlllIllIlIIlIIIlIlIIllIlllIIlIllIIIlIlllIIllIllIIlIIlllIIlllIIIllIIllIlllIIlIlIIIllIIllIIIlIIIllIIllIIllIIIlllIIll$IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll.IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll:(Ldev/imanity/antixray/a/IIllIIlllIlIIIllIIIllIIIlllIIllIIlllIllIIIllIllIIIllIlIllIlIlIIllIIllIllIIlIIlllIIlIlIlIllIllIIlIlllIIllIIllIIlIlllIIIllIIIllIIlIIIlllIIIlllIIllIIllIllIIIlllIIlIIllIlIIlllIIlIIIllIlllIIlIllIlIlIIllIIlIIllIIllIllIIIllIIIllIIlll$IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll;)V
        //    74: aload           9
        //    76: invokeinterface dev/imanity/antixray/a/IIllIIIlllIIIllIIIllIIlIIllIIllIIIlllIIlIIIllIIllIIIlIIIlllIIlllIIlllIIllIIlIlllIlllIlIlIlllIllIIIllIIIllIlIlIllIlIllIIlllIllIlIIlIIIlIlIIllIlllIIlIllIIIlIlllIIllIllIIlIIlllIIlllIIIllIIllIlllIIlIlIIIllIIllIIIlIIIllIIllIIllIIIlllIIll$IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll.IIlIlIIIllIllIIlllIIlIIlllIIlllIIlllIIlllIIIlllIlllIIllIllIlIllIlIIlIIlIlIlllIllIIIllIlIllIIlIIllIIllIIlIIIllIIllIIllIllIIllIIIlllIlIllIlllIllIIlllIIIlIIllIIlIIllIIlIIIllIllIIIllIIIllIIllIlIIIlllIIIlllIllIllIlIllIlIIllIIIll:()Ljava/util/concurrent/CompletableFuture;
        //    81: goto            150
        //    84: pop            
        //    85: new             Ljava/util/concurrent/CompletableFuture;
        //    88: dup            
        //    89: invokespecial   java/util/concurrent/CompletableFuture.<init>:()V
        //    92: astore          8
        //    94: aload_0        
        //    95: getfield        dev/imanity/antixray/a/IIllIIIlllIIIllIIIllIIlIIllIIllIIIlllIIlIIIllIIllIIIlIIIlllIIlllIIlllIIllIIlIlllIlllIlIlIlllIllIIIllIIIllIlIlIllIlIllIIlllIllIlIIlIIIlIlIIllIlllIIlIllIIIlIlllIIllIllIIlIIlllIIlllIIIllIIllIlllIIlIlIIIllIIllIIIlIIIllIIllIIllIIIlllIIll.IlllIIIlIlIlllIIIlIIlIIlIIIlIIlIIlIIllIlllIlIIlllIIllIIlllIlllIlllIIlllIIllIlllIIllIlIllIIlIIIlIIIlllIIlllIIIllIIllIIlllIlllIllIIllIIIlllIIlIIllIllIIlIIllIIllIIlllIIIlllIIlIIllIIIlllIlIIllIllIIlllIIlIIlIllIIllIIlllIllIllIllIlIl:Ldev/imanity/antixray/a/IIllIIlIlIIlllIIllIIllIIIlllIIIlllIllIIllIIlllIlIIllIIllIllIIlIIllIIlIlllIIIlIIlIllIIIllIIIlllIIlIIIlllIIIlllIIlIllIIIllIIlIlIllIlIIllIlIIIlllIIllIIlIIIlIIlIIlIlIlllIlIIIlIIlIIlllIllIllIllIIIlllIIllIIIllIIllIIlllIIlIllIlIIll;
        //    98: ifnull          113
        //   101: aload_0        
        //   102: iload_1        
        //   103: iload_2        
        //   104: aload           8
        //   106: aload_3        
        //   107: invokespecial   dev/imanity/antixray/a/IIllIIIlllIIIllIIIllIIlIIllIIllIIIlllIIlIIIllIIllIIIlIIIlllIIlllIIlllIIllIIlIlllIlllIlIlIlllIllIIIllIIIllIlIlIllIlIllIIlllIllIlIIlIIIlIlIIllIlllIIlIllIIIlIlllIIllIllIIlIIlllIIlllIIIllIIllIlllIIlIlIIIllIIllIIIlIIIllIIllIIllIIIlllIIll.IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll:(IILjava/util/concurrent/CompletableFuture;Ldev/imanity/antixray/a/IIllIIlllIlIIIllIIIllIIIlllIIllIIlllIllIIIllIllIIIllIlIllIlIlIIllIIllIllIIlIIlllIIlIlIlIllIllIIlIlllIIllIIllIIlIlllIIIllIIIllIIlIIIlllIIIlllIIllIIllIllIIIlllIIlIIllIlIIlllIIlIIIllIlllIIlIllIlIlIIllIIlIIllIIllIllIIIllIIIllIIlll$IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll;)Ldev/imanity/antixray/a/IIllIIIlllIIIllIIIllIIlIIllIIllIIIlllIIlIIIllIIllIIIlIIIlllIIlllIIlllIIllIIlIlllIlllIlIlIlllIllIIIllIIIllIlIlIllIlIllIIlllIllIlIIlIIIlIlIIllIlllIIlIllIIIlIlllIIllIllIIlIIlllIIlllIIIllIIllIlllIIlIlIIIllIIllIIIlIIIllIIllIIllIIIlllIIll$IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll;
        //   110: goto            122
        //   113: aload_0        
        //   114: iload_1        
        //   115: iload_2        
        //   116: aload           8
        //   118: aload_3        
        //   119: invokespecial   dev/imanity/antixray/a/IIllIIIlllIIIllIIIllIIlIIllIIllIIIlllIIlIIIllIIllIIIlIIIlllIIlllIIlllIIllIIlIlllIlllIlIlIlllIllIIIllIIIllIlIlIllIlIllIIlllIllIlIIlIIIlIlIIllIlllIIlIllIIIlIlllIIllIllIIlIIlllIIlllIIIllIIllIlllIIlIlIIIllIIllIIIlIIIllIIllIIllIIIlllIIll.IIllIllIIllIlIIlIlIIlllIllIIllIIllIlIIllIIIllIIIllIIllIIIlIllIIlIIlllIllIIlIllIllIIlIIlIlIIIlIllIIllIlllIllIIIllIIlIIlllIllIIlIIIlIIllIIllIIlIlIIlIllIIlllIlIIlllIIlIlIIlIIlIlllIIlIIIlIIllIllIlllIIlllIIIlIIllIIIlll:(IILjava/util/concurrent/CompletableFuture;Ldev/imanity/antixray/a/IIllIIlllIlIIIllIIIllIIIlllIIllIIlllIllIIIllIllIIIllIlIllIlIlIIllIIllIllIIlIIlllIIlIlIlIllIllIIlIlllIIllIIllIIlIlllIIIllIIIllIIlIIIlllIIIlllIIllIIllIllIIIlllIIlIIllIlIIlllIIlIIIllIlllIIlIllIlIlIIllIIlIIllIIllIllIIIllIIIllIIlll$IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll;)Ldev/imanity/antixray/a/IIllIIIlllIIIllIIIllIIlIIllIIllIIIlllIIlIIIllIIllIIIlIIIlllIIlllIIlllIIllIIlIlllIlllIlIlIlllIllIIIllIIIllIlIlIllIlIllIIlllIllIlIIlIIIlIlIIllIlllIIlIllIIIlIlllIIllIllIIlIIlllIIlllIIIllIIllIlllIIlIlIIIllIIllIIIlIIIllIIllIIllIIIlllIIll$IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll;
        //   122: astore          11
        //   124: lload           4
        //   126: invokestatic    java/lang/Long.valueOf:(J)Ljava/lang/Long;
        //   129: astore          10
        //   131: aload_0        
        //   132: getfield        dev/imanity/antixray/a/IIllIIIlllIIIllIIIllIIlIIllIIllIIIlllIIlIIIllIIllIIIlIIIlllIIlllIIlllIIllIIlIlllIlllIlIlIlllIllIIIllIIIllIlIlIllIlIllIIlllIllIlIIlIIIlIlIIllIlllIIlIllIIIlIlllIIllIllIIlIIlllIIlllIIIllIIllIlllIIlIlIIIllIIllIIIlIIIllIIllIIllIIIlllIIll.IIllIIIlIllIIlIIlIllIIlllIIlIIlIIlIIllIllIIlIIIlIIllIIlIIlllIlIIIllIllIIIlllIIlllIllIIIlIlllIIIllIIlIlllIIllIIIlllIIllIlllIllIIllIIIllIIIlIIlIIIlllIllIIIllIIlllIllIIIllIIIllIIlIIlllIIIlIIIlIIllIIlllIlIIIlllIIllIIIlIIIllIlIIIllIIlllIIlllIlll:Lit/unimi/dsi/fastutil/longs/Long2ObjectOpenHashMap;
        //   135: checkcast       Ljava/util/Map;
        //   138: aload           10
        //   140: aload           11
        //   142: invokeinterface java/util/Map.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   147: pop            
        //   148: aload           8
        //   150: astore          7
        //   152: aload           6
        //   154: invokeinterface java/util/concurrent/locks/Lock.unlock:()V
        //   159: aload           7
        //   161: goto            176
        //   164: astore          7
        //   166: aload           6
        //   168: invokeinterface java/util/concurrent/locks/Lock.unlock:()V
        //   173: aload           7
        //   175: athrow         
        //   176: areturn        
        //    Signature:
        //  (IILdev/imanity/antixray/a/IIllIIlllIlIIIllIIIllIIIlllIIllIIlllIllIIIllIllIIIllIlIllIlIlIIllIIllIllIIlIIlllIIlIlIlIllIllIIlIlllIIllIIllIIlIlllIIIllIIIllIIlIIIlllIIIlllIIllIIllIllIIIlllIIlIIllIlIIlllIIlIIIllIlllIIlIllIlIlIIllIIlIIllIIllIllIIIllIIIllIIlll$IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll;)Ljava/util/concurrent/CompletableFuture<Ldev/imanity/antixray/api/chunk/DirectChunk;>;
        //    StackMapTable: 00 06 FF 00 54 00 06 07 00 02 01 01 07 00 10 04 07 00 5A 00 01 07 00 07 FD 00 1C 00 07 00 6E FF 00 08 00 08 07 00 02 00 00 00 04 07 00 5A 00 07 00 6E 00 01 07 00 07 FF 00 1B 00 07 00 00 00 00 00 00 07 00 5A 00 01 07 00 6E 4D 07 00 85 FF 00 0B 00 00 00 01 07 00 6E
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  32     152    164    176    Any
        //  164    166    164    176    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index -1 out of bounds for length 0
        //     at java.base/jdk.internal.util.Preconditions.outOfBounds(Preconditions.java:100)
        //     at java.base/jdk.internal.util.Preconditions.outOfBoundsCheckIndex(Preconditions.java:106)
        //     at java.base/jdk.internal.util.Preconditions.checkIndex(Preconditions.java:302)
        //     at java.base/java.util.Objects.checkIndex(Objects.java:385)
        //     at java.base/java.util.ArrayList.remove(ArrayList.java:551)
        //     at com.strobel.assembler.ir.StackMappingVisitor.pop(StackMappingVisitor.java:267)
        //     at com.strobel.assembler.ir.StackMappingVisitor$InstructionAnalyzer.execute(StackMappingVisitor.java:599)
        //     at com.strobel.assembler.ir.StackMappingVisitor$InstructionAnalyzer.visit(StackMappingVisitor.java:398)
        //     at com.strobel.decompiler.ast.AstBuilder.performStackAnalysis(AstBuilder.java:2086)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:108)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:203)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:93)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:868)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:761)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:638)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:605)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:195)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:162)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:137)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:333)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:254)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:144)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    private final IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(final int n, final int n2, final CompletableFuture<DirectChunk> completableFuture, final IIllIIlllIlIIIllIIIllIIIlllIIllIIlllIllIIIllIllIIIllIlIllIlIlIIllIIllIllIIlIIlllIIlIlIlIllIllIIlIlllIIllIIllIIlIlllIIIllIIIllIIlIIIlllIIIlllIIllIIllIllIIIlllIIlIIllIlIIlllIIlIIIllIlllIIlIllIlIlIIllIIlIIllIIllIllIIIllIIIllIIlll.IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll illIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll) {
        final IlllIIlIIIlllIIIlIIllIlIllIIIlIIIllIlllIIIllIIlIIllIIllIlllIIllIIllIIllIIllIIlllIIlllIllIIllIlllIlIllIIlllIllIllIIlllIIllIIIlllIIIllIIIllIlllIIlllIIlIlIlIlIllIIIlllIIlIllIIllIIlllIIlIIIllIIIllIIlIIlllIlIllIllIIlIIIllIllIIlIIllIll illlIIlIIIlllIIIlIIllIlIllIIIlIIIllIlllIIIllIIlIIllIIllIlllIIllIIllIIllIIllIIlllIIlllIllIIllIlllIlIllIIlllIllIllIIlllIIllIIIlllIIIllIIIllIlllIIlllIIlIlIlIlIllIIIlllIIlIllIIllIIlllIIlIIIllIIIllIIlIIlllIlIllIllIIlIIIllIllIIlIIllIll = new IlllIIlIIIlllIIIlIIllIlIllIIIlIIIllIlllIIIllIIlIIllIIllIlllIIllIIllIIllIIllIIlllIIlllIllIIllIlllIlIllIIlllIllIllIIlllIIllIIIlllIIIllIIIllIlllIIlllIIlIlIlIlIllIIIlllIIlIllIIllIIlllIIlIIIllIIIllIIlIIlllIlIllIllIIlIIIllIllIIlIIllIll(completableFuture, illIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll);
        this.IllIllIIIlIIIlllIIllIIlIIlllIIIlIlIIllIIlIIIlIIllIIIllIIlIIIllIIlIlllIlllIllIIIlIIllIIIllIIlIllIIIllIIIllIlIIIlIIllIlIlIIlllIIIllIlIllIIlllIIIlIllIllIlIlIIIllIIllIIllIIlIllIIlIlIIlIIllIIllIlIlllIIIllIIlllIlIIlIIlIIIlll.schedule(IIllIIIlllIIIllIIIllIIlIIllIIllIIIlllIIlIIIllIIllIIIlIIIlllIIlllIIlllIIllIIlIlllIlllIlIlIlllIllIIIllIIIllIlIlIllIlIllIIlllIllIlIIlIIIlIlIIllIlllIIlIllIIIlIlllIIllIllIIlIIlllIIlllIIIllIIllIlllIIlIlIIIllIIllIIIlIIIllIIllIIllIIIlllIIll::IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll);
        return illlIIlIIIlllIIIlIIllIlIllIIIlIIIllIlllIIIllIIlIIllIIllIlllIIllIIllIIllIIllIIlllIIlllIllIIllIlllIlIllIIlllIllIllIIlllIIllIIIlllIIIllIIIllIlllIIlllIIlIlIlIlIllIIIlllIIlIllIIllIIlllIIlIIIllIIIllIIlIIlllIlIllIllIIlIIIllIllIIlIIllIll;
    }
    
    private final IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll IIllIllIIllIlIIlIlIIlllIllIIllIIllIlIIllIIIllIIIllIIllIIIlIllIIlIIlllIllIIlIllIllIIlIIlIlIIIlIllIIllIlllIllIIIllIIlIIlllIllIIlIIIlIIllIIllIIlIlIIlIllIIlllIlIIlllIIlIlIIlIIlIlllIIlIIIlIIllIllIlllIIlllIIIlIIllIIIlll(final int n, final int n2, final CompletableFuture<DirectChunk> completableFuture, final IIllIIlllIlIIIllIIIllIIIlllIIllIIlllIllIIIllIllIIIllIlIllIlIlIIllIIllIllIIlIIlllIIlIlIlIllIllIIlIlllIIllIIllIIlIlllIIIllIIIllIIlIIIlllIIIlllIIllIIllIllIIIlllIIlIIllIlIIlllIIlIIIllIlllIIlIllIlIlIIllIIlIIllIIllIllIIIllIIIllIIlll.IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll illIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll) {
        final IIllIllIIllIlIIlIlIIlllIllIIllIIllIlIIllIIIllIIIllIIllIIIlIllIIlIIlllIllIIlIllIllIIlIIlIlIIIlIllIIllIlllIllIIIllIIlIIlllIllIIlIIIlIIllIIllIIlIlIIlIllIIlllIlIIlllIIlIlIIlIIlIlllIIlIIIlIIllIllIlllIIlllIIIlIIllIIIlll illIllIIllIlIIlIlIIlllIllIIllIIllIlIIllIIIllIIIllIIllIIIlIllIIlIIlllIllIIlIllIllIIlIIlIlIIIlIllIIllIlllIllIIIllIIlIIlllIllIIlIIIlIIllIIllIIlIlIIlIllIIlllIlIIlllIIlIlIIlIIlIlllIIlIIIlIIllIllIlllIIlllIIIlIIllIIIlll = new IIllIllIIllIlIIlIlIIlllIllIIllIIllIlIIllIIIllIIIllIIllIIIlIllIIlIIlllIllIIlIllIllIIlIIlIlIIIlIllIIllIlllIllIIIllIIlIIlllIllIIlIIIlIIllIIllIIlIlIIlIllIIlllIlIIlllIIlIlIIlIIlIlllIIlIIIlIIllIllIlllIIlllIIIlIIllIIIlll(n, n2, completableFuture, illIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll);
        illIllIIllIlIIlIlIIlllIllIIllIIllIlIIllIIIllIIIllIIllIIIlIllIIlIIlllIllIIlIllIllIIlIIlIlIIIlIllIIllIlllIllIIIllIIlIIlllIllIIlIIIlIIllIIllIIlIlIIlIllIIlllIlIIlllIIlIlIIlIIlIlllIIlIIIlIIllIllIlllIIlllIIIlIIllIIIlll.IllIllIIIlllIIIlIllIIlllIIlIllIIIlIIlllIIIllIIIlIIIllIIIllIlIIIllIIlIIllIIlIIIllIIllIlllIlllIllIIIlIllIIIlIIllIIllIIllIIlIIllIIllIIllIlIllIIlllIIIllIIlllIIllIlIllIlllIIIlIIllIIlllIIlIllIllIIlIIIllIIllIllIIIllIlIllIIIlIIIlIIlIlll();
        return illIllIIllIlIIlIlIIlllIllIIllIIllIlIIllIIIllIIIllIIllIIIlIllIIlIIlllIllIIlIllIllIIlIIlIlIIIlIllIIllIlllIllIIIllIIlIIlllIllIIlIIIlIIllIIllIIlIlIIlIllIIlllIlIIlllIIlIlIIlIIlIlllIIlIIIlIIllIllIlllIIlllIIIlIIllIIIlll;
    }
    
    public final boolean IlllIIlIIIlllIIIlIIllIlIllIIIlIIIllIlllIIIllIIlIIllIIllIlllIIllIIllIIllIIllIIlllIIlllIllIIllIlllIlIllIIlllIllIllIIlllIIllIIIlllIIIllIIIllIlllIIlllIIlIlIlIlIllIIIlllIIlIllIIllIIlllIIlIIIllIIIllIIlIIlllIlIllIllIIlIIIllIllIIlIIllIll(final int p0, final int p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     3: iload_1        
        //     4: iload_2        
        //     5: invokevirtual   dev/imanity/antixray/api/util/LongHash.toLong:(II)J
        //     8: lstore_3       
        //     9: aload_0        
        //    10: getfield        dev/imanity/antixray/a/IIllIIIlllIIIllIIIllIIlIIllIIllIIIlllIIlIIIllIIllIIIlIIIlllIIlllIIlllIIllIIlIlllIlllIlIlIlllIllIIIllIIIllIlIlIllIlIllIIlllIllIlIIlIIIlIlIIllIlllIIlIllIIIlIlllIIllIllIIlIIlllIIlllIIIllIIllIlllIIlIlIIIllIIllIIIlIIIllIIllIIllIIIlllIIll.IIllIIlllIlIIIllIIIllIIIlllIIllIIlllIllIIIllIllIIIllIlIllIlIlIIllIIllIllIIlIIlllIIlIlIlIllIllIIlIlllIIllIIllIIlIlllIIIllIIIllIIlIIIlllIIIlllIIllIIllIllIIIlllIIlIIllIlIIlllIIlIIIllIlllIIlIllIlIlIIllIIlIIllIIllIllIIIllIIIllIIlll:Ljava/util/concurrent/locks/ReentrantLock;
        //    13: checkcast       Ljava/util/concurrent/locks/Lock;
        //    16: astore          5
        //    18: aload           5
        //    20: invokeinterface java/util/concurrent/locks/Lock.lock:()V
        //    25: nop            
        //    26: iconst_0       
        //    27: istore          6
        //    29: aload_0        
        //    30: getfield        dev/imanity/antixray/a/IIllIIIlllIIIllIIIllIIlIIllIIllIIIlllIIlIIIllIIllIIIlIIIlllIIlllIIlllIIllIIlIlllIlllIlIlIlllIllIIIllIIIllIlIlIllIlIllIIlllIllIlIIlIIIlIlIIllIlllIIlIllIIIlIlllIIllIllIIlIIlllIIlllIIIllIIllIlllIIlIlIIIllIIllIIIlIIIllIIllIIllIIIlllIIll.IIllIIIlIllIIlIIlIllIIlllIIlIIlIIlIIllIllIIlIIIlIIllIIlIIlllIlIIIllIllIIIlllIIlllIllIIIlIlllIIIllIIlIlllIIllIIIlllIIllIlllIllIIllIIIllIIIlIIlIIIlllIllIIIllIIlllIllIIIllIIIllIIlIIlllIIIlIIIlIIllIIlllIlIIIlllIIllIIIlIIIllIlIIIllIIlllIIlllIlll:Lit/unimi/dsi/fastutil/longs/Long2ObjectOpenHashMap;
        //    33: lload_3        
        //    34: invokevirtual   it/unimi/dsi/fastutil/longs/Long2ObjectOpenHashMap.remove:(J)Ljava/lang/Object;
        //    37: checkcast       Ldev/imanity/antixray/a/IIllIIIlllIIIllIIIllIIlIIllIIllIIIlllIIlIIIllIIllIIIlIIIlllIIlllIIlllIIllIIlIlllIlllIlIlIlllIllIIIllIIIllIlIlIllIlIllIIlllIllIlIIlIIIlIlIIllIlllIIlIllIIIlIlllIIllIllIIlIIlllIIlllIIIllIIllIlllIIlIlIIIllIIllIIIlIIIllIIllIIllIIIlllIIll$IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll;
        //    40: astore          7
        //    42: aload           7
        //    44: dup            
        //    45: ifnonnull       62
        //    48: pop            
        //    49: iconst_0       
        //    50: istore          8
        //    52: aload           5
        //    54: invokeinterface java/util/concurrent/locks/Lock.unlock:()V
        //    59: iload           8
        //    61: ireturn        
        //    62: invokestatic    kotlin/jvm/internal/Intrinsics.checkNotNull:(Ljava/lang/Object;)V
        //    65: iconst_1       
        //    66: istore          6
        //    68: aload           5
        //    70: invokeinterface java/util/concurrent/locks/Lock.unlock:()V
        //    75: iload           6
        //    77: goto            92
        //    80: astore          6
        //    82: aload           5
        //    84: invokeinterface java/util/concurrent/locks/Lock.unlock:()V
        //    89: aload           6
        //    91: athrow         
        //    92: ireturn        
        //    StackMapTable: 00 03 FF 00 3E 00 06 00 00 00 00 00 07 00 5A 00 01 07 00 07 51 07 00 85 FF 00 0B 00 00 00 01 01
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  25     52     80     92     Any
        //  62     68     80     92     Any
        //  80     82     80     92     Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index -1 out of bounds for length 0
        //     at java.base/jdk.internal.util.Preconditions.outOfBounds(Preconditions.java:100)
        //     at java.base/jdk.internal.util.Preconditions.outOfBoundsCheckIndex(Preconditions.java:106)
        //     at java.base/jdk.internal.util.Preconditions.checkIndex(Preconditions.java:302)
        //     at java.base/java.util.Objects.checkIndex(Objects.java:385)
        //     at java.base/java.util.ArrayList.remove(ArrayList.java:551)
        //     at com.strobel.assembler.ir.StackMappingVisitor.pop(StackMappingVisitor.java:267)
        //     at com.strobel.assembler.ir.StackMappingVisitor$InstructionAnalyzer.execute(StackMappingVisitor.java:581)
        //     at com.strobel.assembler.ir.StackMappingVisitor$InstructionAnalyzer.visit(StackMappingVisitor.java:398)
        //     at com.strobel.decompiler.ast.AstBuilder.performStackAnalysis(AstBuilder.java:2086)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:108)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:203)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:93)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:868)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:761)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:638)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:605)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:195)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:162)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:137)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:333)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:254)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:144)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public final void IIIlIlIIllIIlIIllIllIlllIlIIlllIllIIIllIIlIIllIlllIlIIlIIllIllIIIlIIllIllIIllIlIlIIllIIlllIIllIllIIlIllIIllIlllIIIlllIIllIIlllIIlllIIlIlllIlllIlllIIIllIllIIllIllIlIlllIIllIIlllIIlIIllIIllIIlIlllIIlIIIllIIllIIllIIlIllIll() {
        for (int i = 0; i < this.IIllIIllIIlIIllIIllIIlllIIlllIllIIIllIIIlllIIlIlIIlIIllIlIIllIllIIlIIllIllIlIIllIllIlllIIIlIIIllIIllIIlIllIIIllIlIIllIIllIIllIlIIlllIIlIIIlllIIIlllIIllIIlllIIIlllIIIllIIIllIIllIIllIIllIlllIlIIIlllIIlIIIlIlllIIllIIllIIllIIIlIIllIIlllIlll.getMaxChunkLoadPerTick(); ++i) {
            final Runnable illlIlllIIlIlllIIlIIlIllIIlIIIlllIIllIIlIIIlllIIllIlIIlIIlIIlIlIIlIIlllIlIIlIIlIlIlllIIllIIlIIllIIlIIlIIIlllIIIlllIIlIIllIllIIlllIlIIIllIIllIIlllIIIlllIIlIlllIlIIlIIlIllIIIlIIllIIIllIIIlllIlllIIIlIIIlIIlllIIIlIllIIlllIIlIIll = this.IIIlllIlIIlllIIlllIlIlIlIlllIIIllIIIllIIlIlIIIllIlIlIIllIIllIIlllIIllIlllIIIllIIIlIIIlIIllIIllIIIlIllIIllIIIllIIlllIllIIIllIIllIIIllIlIIIllIIIlllIllIlIlIIIlllIIlllIIllIIlllIIllIlIIlllIIIlllIIllIIlIIllIlIllIIIllIlllIIlllIlIlIIllIlll.IlllIlllIIlIlllIIlIIlIllIIlIIIlllIIllIIlIIIlllIIllIlIIlIIlIIlIlIIlIIlllIlIIlIIlIlIlllIIllIIlIIllIIlIIlIIIlllIIIlllIIlIIllIllIIlllIlIIIllIIllIIlllIIIlllIIlIlllIlIIlIIlIllIIIlIIllIIIllIIIlllIlllIIIlIIIlIIlllIIIlIllIIlllIIlIIll();
            if (illlIlllIIlIlllIIlIIlIllIIlIIIlllIIllIIlIIIlllIIllIlIIlIIlIIlIlIIlIIlllIlIIlIIlIlIlllIIllIIlIIllIIlIIlIIIlllIIIlllIIlIIllIllIIlllIlIIIllIIllIIlllIIIlllIIlIlllIlIIlIIlIllIIIlIIllIIIllIIIlllIlllIIIlIIIlIIlllIIIlIllIIlllIIlIIll == null) {
                return;
            }
            illlIlllIIlIlllIIlIIlIllIIlIIIlllIIllIIlIIIlllIIllIlIIlIIlIIlIlIIlIIlllIlIIlIIlIlIlllIIllIIlIIllIIlIIlIIIlllIIIlllIIlIIllIllIIlllIlIIIllIIllIIlllIIIlllIIlIlllIlIIlIIlIllIIIlIIllIIIllIIIlllIlllIIIlIIIlIIlllIIIlIllIIlllIIlIIll.run();
        }
    }
    
    private static final void IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(final IIllIIIlllIIIllIIIllIIlIIllIIllIIIlllIIlIIIllIIllIIIlIIIlllIIlllIIlllIIllIIlIlllIlllIlIlIlllIllIIIllIIIllIlIlIllIlIllIIlllIllIlIIlIIIlIlIIllIlllIIlIllIIIlIlllIIllIllIIlIIlllIIlllIIIllIIllIlllIIlIlIIIllIIllIIIlIIIllIIllIIllIIIlllIIll illIIIlllIIIllIIIllIIlIIllIIllIIIlllIIlIIIllIIllIIIlIIIlllIIlllIIlllIIllIIlIlllIlllIlIlIlllIllIIIllIIIllIlIlIllIlIllIIlllIllIlIIlIIIlIlIIllIlllIIlIllIIIlIlllIIllIllIIlIIlllIIlllIIIllIIllIlllIIlIlIIIllIIllIIIlIIIllIIllIIllIIIlllIIll, final int n, final int n2, final CompletableFuture completableFuture, final IIllIIlllIlIIIllIIIllIIIlllIIllIIlllIllIIIllIllIIIllIlIllIlIlIIllIIllIllIIlIIlllIIlIlIlIllIllIIlIlllIIllIIllIIlIlllIIIllIIIllIIlIIIlllIIIlllIIllIIllIllIIIlllIIlIIllIlIIlllIIlIIIllIlllIIlIllIlIlIIllIIlIIllIIllIllIIIllIIIllIIlll.IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll illIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll, final IlllIIlIIIlllIIIlIIllIlIllIIIlIIIllIlllIIIllIIlIIllIIllIlllIIllIIllIIllIIllIIlllIIlllIllIIllIlllIlIllIIlllIllIllIIlllIIllIIIlllIIIllIIIllIlllIIlllIIlIlIlIlIllIIIlllIIlIllIIllIIlllIIlIIIllIIIllIIlIIlllIlIllIllIIlIIIllIllIIlIIllIll illlIIlIIIlllIIIlIIllIlIllIIIlIIIllIlllIIIllIIlIIllIIllIlllIIllIIllIIllIIllIIlllIIlllIllIIllIlllIlIllIIlllIllIllIIlllIIllIIIlllIIIllIIIllIlllIIlllIIlIlIlIlIllIIIlllIIlIllIIllIIlllIIlIIIllIIIllIIlIIlllIlIllIllIIlIIIllIllIIlIIllIll) {
        Intrinsics.checkNotNullParameter((Object)illIIIlllIIIllIIIllIIlIIllIIllIIIlllIIlIIIllIIllIIIlIIIlllIIlllIIlllIIllIIlIlllIlllIlIlIlllIllIIIllIIIllIlIlIllIlIllIIlllIllIlIIlIIIlIlIIllIlllIIlIllIIIlIlllIIllIllIIlIIlllIIlllIIIllIIllIlllIIlIlIIIllIIllIIIlIIIllIIllIIllIIIlllIIll, "");
        Intrinsics.checkNotNullParameter((Object)completableFuture, "");
        Intrinsics.checkNotNullParameter((Object)illIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll, "");
        Intrinsics.checkNotNullParameter((Object)illlIIlIIIlllIIIlIIllIlIllIIIlIIIllIlllIIIllIIlIIllIIllIlllIIllIIllIIllIIllIIlllIIlllIllIIllIlllIlIllIIlllIllIllIIlllIIllIIIlllIIIllIIIllIlllIIlllIIlIlIlIlIllIIIlllIIlIllIIllIIlllIIlIIIllIIIllIIlIIlllIlIllIllIIlIIIllIllIIlIIllIll, "");
        final IIllIIlIlIIlllIIllIIllIIIlllIIIlllIllIIllIIlllIlIIllIIllIllIIlIIllIIlIlllIIIlIIlIllIIIllIIIlllIIlIIIlllIIIlllIIlIllIIIllIIlIlIllIlIIllIlIIIlllIIllIIlIIIlIIlIIlIlIlllIlIIIlIIlIIlllIllIllIllIIIlllIIllIIIllIIllIIlllIIlIllIlIIll illlIIIlIlIlllIIIlIIlIIlIIIlIIlIIlIIllIlllIlIIlllIIllIIlllIlllIlllIIlllIIllIlllIIllIlIllIIlIIIlIIIlllIIlllIIIllIIllIIlllIlllIllIIllIIIlllIIlIIllIllIIlIIllIIllIIlllIIIlllIIlIIllIIIlllIlIIllIllIIlllIIlIIlIllIIllIIlllIllIllIllIlIl = illIIIlllIIIllIIIllIIlIIllIIllIIIlllIIlIIIllIIllIIIlIIIlllIIlllIIlllIIllIIlIlllIlllIlIlIlllIllIIIllIIIllIlIlIllIlIllIIlllIllIlIIlIIIlIlIIllIlllIIlIllIIIlIlllIIllIllIIlIIlllIIlllIIIllIIllIlllIIlIlIIIllIIllIIIlIIIllIIllIIllIIIlllIIll.IlllIIIlIlIlllIIIlIIlIIlIIIlIIlIIlIIllIlllIlIIlllIIllIIlllIlllIlllIIlllIIllIlllIIllIlIllIIlIIIlIIIlllIIlllIIIllIIllIIlllIlllIllIIllIIIlllIIlIIllIllIIlIIllIIllIIlllIIIlllIIlIIllIIIlllIlIIllIllIIlllIIlIIlIllIIllIIlllIllIllIllIlIl;
        Intrinsics.checkNotNull((Object)illlIIIlIlIlllIIIlIIlIIlIIIlIIlIIlIIllIlllIlIIlllIIllIIlllIlllIlllIIlllIIllIlllIIllIlIllIIlIIIlIIIlllIIlllIIIllIIllIIlllIlllIllIIllIIIlllIIlIIllIllIIlIIllIIllIIlllIIIlllIIlIIllIIIlllIlIIllIllIIlllIIlIIlIllIIllIIlllIllIllIllIlIl);
        final ChunkHandle iIllIllIIllIlIIlIlIIlllIllIIllIIllIlIIllIIIllIIIllIIllIIIlIllIIlIIlllIllIIlIllIllIIlIIlIlIIIlIllIIllIlllIllIIIllIIlIIlllIllIIlIIIlIIllIIllIIlIlIIlIllIIlllIlIIlllIIlIlIIlIIlIlllIIlIIIlIIllIllIlllIIlllIIIlIIllIIIlll = illlIIIlIlIlllIIIlIIlIIlIIIlIIlIIlIIllIlllIlIIlllIIllIIlllIlllIlllIIlllIIllIlllIIllIlIllIIlIIIlIIIlllIIlllIIIllIIllIIlllIlllIllIIllIIIlllIIlIIllIllIIlIIllIIllIIlllIIIlllIIlIIllIIIlllIlIIllIllIIlllIIlIIlIllIIllIIlllIllIllIllIlIl.IIllIllIIllIlIIlIlIIlllIllIIllIIllIlIIllIIIllIIIllIIllIIIlIllIIlIIlllIllIIlIllIllIIlIIlIlIIIlIllIIllIlllIllIIIllIIlIIlllIllIIlIIIlIIllIIllIIlIlIIlIllIIlllIlIIlllIIlIlIIlIIlIlllIIlIIIlIIllIllIlllIIlllIIIlIIllIIIlll(n, n2);
        if (iIllIllIIllIlIIlIlIIlllIllIIllIIllIlIIllIIIllIIIllIIllIIIlIllIIlIIlllIllIIlIllIllIIlIIlIlIIIlIllIIllIlllIllIIIllIIlIIlllIllIIlIIIlIIllIIllIIlIlIIlIllIIlllIlIIlllIIlIlIIlIIlIlllIIlIIIlIIllIllIlllIIlllIIIlIIllIIIlll == null) {
            illlIIlIIIlllIIIlIIllIlIllIIIlIIIllIlllIIIllIIlIIllIIllIlllIIllIIllIIllIIllIIlllIIlllIllIIllIlllIlIllIIlllIllIllIIlllIIllIIIlllIIIllIIIllIlllIIlllIIlIlIlIlIllIIIlllIIlIllIIllIIlllIIlIIIllIIIllIIlIIlllIlIllIllIIlIIIllIllIIlIIllIll.IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(illIIIlllIIIllIIIllIIlIIllIIllIIIlllIIlIIIllIIllIIIlIIIlllIIlllIIlllIIllIIlIlllIlllIlIlIlllIllIIIllIIIllIlIlIllIlIllIIlllIllIlIIlIIIlIlIIllIlllIIlIllIIIlIlllIIllIllIIlIIlllIIlllIIIllIIllIlllIIlIlIIIllIIllIIIlIIIllIIllIIllIIIlllIIll.IIllIllIIllIlIIlIlIIlllIllIIllIIllIlIIllIIIllIIIllIIllIIIlIllIIlIIlllIllIIlIllIllIIlIIlIlIIIlIllIIllIlllIllIIIllIIlIIlllIllIIlIIIlIIllIIllIIlIlIIlIllIIlllIlIIlllIIlIlIIlIIlIlllIIlIIIlIIllIllIlllIIlllIIIlIIllIIIlll(n, n2, completableFuture, illIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll));
            return;
        }
        completableFuture.complete(new DirectChunk(n, n2, iIllIllIIllIlIIlIlIIlllIllIIllIIllIlIIllIIIllIIIllIIllIIIlIllIIlIIlllIllIIlIllIllIIlIIlIlIIIlIllIIllIlllIllIIIllIIlIIlllIllIIlIIIlIIllIIllIIlIlIIlIllIIlllIlIIlllIIlIlIIlIIlIlllIIlIIIlIIllIllIlllIIlllIIIlIIllIIIlll));
        illIIIlllIIIllIIIllIIlIIllIIllIIIlllIIlIIIllIIllIIIlIIIlllIIlllIIlllIIllIIlIlllIlllIlIlIlllIllIIIllIIIllIlIlIllIlIllIIlllIllIlIIlIIIlIlIIllIlllIIlIllIIIlIlllIIllIllIIlIIlllIIlllIIIllIIllIlllIIlIlIIIllIIllIIIlIIIllIIllIIllIIIlllIIll.IlllIIlIIIlllIIIlIIllIlIllIIIlIIIllIlllIIIllIIlIIllIIllIlllIIllIIllIIllIIllIIlllIIlllIllIIllIlllIlIllIIlllIllIllIIlllIIllIIIlllIIIllIIIllIlllIIlllIIlIlIlIlIllIIIlllIIlIllIIllIIlllIIlIIIllIIIllIIlIIlllIlIllIllIIlIIIllIllIIlIIllIll(n, n2);
    }
    
    public static final /* synthetic */ IIlllIIIllIIllIIllIlIIIlIIIllIIIlIIlllIIIlIlllIIlIIllIlIlIlIlIIIlllIIIlllIIIlllIIIlllIIlIllIIllIlllIllIIlllIlllIlllIIlIIlIIIllIllIIIlllIIllIIlIlllIIllIIIllIIlIIllIIIlIllIIIllIIllIIIlIIllIIlllIIlllIIIllIllIIIllIIllIIllIlIllIllIIIlIIllIIl IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(final IIllIIIlllIIIllIIIllIIlIIllIIllIIIlllIIlIIIllIIllIIIlIIIlllIIlllIIlllIIllIIlIlllIlllIlIlIlllIllIIIllIIIllIlIlIllIlIllIIlllIllIlIIlIIIlIlIIllIlllIIlIllIIIlIlllIIllIllIIlIIlllIIlllIIIllIIllIlllIIlIlIIIllIIllIIIlIIIllIIllIIllIIIlllIIll illIIIlllIIIllIIIllIIlIIllIIllIIIlllIIlIIIllIIllIIIlIIIlllIIlllIIlllIIllIIlIlllIlllIlIlIlllIllIIIllIIIllIlIlIllIlIllIIlllIllIlIIlIIIlIlIIllIlllIIlIllIIIlIlllIIllIllIIlIIlllIIlllIIIllIIllIlllIIlIlIIIllIIllIIIlIIIllIIllIIllIIIlllIIll) {
        return illIIIlllIIIllIIIllIIlIIllIIllIIIlllIIlIIIllIIllIIIlIIIlllIIlllIIlllIIllIIlIlllIlllIlIlIlllIllIIIllIIIllIlIlIllIlIllIIlllIllIlIIlIIIlIlIIllIlllIIlIllIIIlIlllIIllIllIIlIIlllIIlllIIIllIIllIlllIIlIlIIIllIIllIIIlIIIllIIllIIllIIIlllIIll.IIlllIIlIlllIIIlllIIIlIIIlllIlIIIllIIIllIIlIIIllIlIIllIIIllIIIllIIlIIllIIIlIlllIIllIIllIllIIllIIlllIlIIlIlllIllIllIllIIlllIIlllIIIllIlllIIlllIlIIlIIllIllIIllIIllIlIllIIlllIIIlllIIllIlIIIllIllIlIlIIllIllIlllIIIlllIIllIIlIIlllIIllIIl;
    }
    
    public static final /* synthetic */ IIllIIlllIlIIIllIIIllIIIlllIIllIIlllIllIIIllIllIIIllIlIllIlIlIIllIIllIllIIlIIlllIIlIlIlIllIllIIlIlllIIllIIllIIlIlllIIIllIIIllIIlIIIlllIIIlllIIllIIllIllIIIlllIIlIIllIlIIlllIIlIIIllIlllIIlIllIlIlIIllIIlIIllIIllIllIIIllIIIllIIlll IIllIllIIllIlIIlIlIIlllIllIIllIIllIlIIllIIIllIIIllIIllIIIlIllIIlIIlllIllIIlIllIllIIlIIlIlIIIlIllIIllIlllIllIIIllIIlIIlllIllIIlIIIlIIllIIllIIlIlIIlIllIIlllIlIIlllIIlIlIIlIIlIlllIIlIIIlIIllIllIlllIIlllIIIlIIllIIIlll(final IIllIIIlllIIIllIIIllIIlIIllIIllIIIlllIIlIIIllIIllIIIlIIIlllIIlllIIlllIIllIIlIlllIlllIlIlIlllIllIIIllIIIllIlIlIllIlIllIIlllIllIlIIlIIIlIlIIllIlllIIlIllIIIlIlllIIllIllIIlIIlllIIlllIIIllIIllIlllIIlIlIIIllIIllIIIlIIIllIIllIIllIIIlllIIll illIIIlllIIIllIIIllIIlIIllIIllIIIlllIIlIIIllIIllIIIlIIIlllIIlllIIlllIIllIIlIlllIlllIlIlIlllIllIIIllIIIllIlIlIllIlIllIIlllIllIlIIlIIIlIlIIllIlllIIlIllIIIlIlllIIllIllIIlIIlllIIlllIIIllIIllIlllIIlIlIIIllIIllIIIlIIIllIIllIIllIIIlllIIll) {
        return illIIIlllIIIllIIIllIIlIIllIIllIIIlllIIlIIIllIIllIIIlIIIlllIIlllIIlllIIllIIlIlllIlllIlIlIlllIllIIIllIIIllIlIlIllIlIllIIlllIllIlIIlIIIlIlIIllIlllIIlIllIIIlIlllIIllIllIIlIIlllIIlllIIIllIIllIlllIIlIlIIIllIIllIIIlIIIllIIllIIllIIIlllIIll.IIIlllIlIIlllIIlllIlIlIlIlllIIIllIIIllIIlIlIIIllIlIlIIllIIllIIlllIIllIlllIIIllIIIlIIIlIIllIIllIIIlIllIIllIIIllIIlllIllIIIllIIllIIIllIlIIIllIIIlllIllIlIlIIIlllIIlllIIllIIlllIIllIlIIlllIIIlllIIllIIlIIllIlIllIIIllIlllIIlllIlIlIIllIlll;
    }
    
    public final class IlllIIlIIIlllIIIlIIllIlIllIIIlIIIllIlllIIIllIIlIIllIIllIlllIIllIIllIIllIIllIIlllIIlllIllIIllIlllIlIllIIlllIllIllIIlllIIllIIIlllIIIllIIIllIlllIIlllIIlIlIlIlIllIIIlllIIlIllIIllIIlllIIlIIIllIIIllIIlIIlllIlIllIllIIlIIIllIllIIlIIllIll implements IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll
    {
        @NotNull
        private final CompletableFuture<DirectChunk> IlIIIlIIIllIIIlllIIllIllIIllIIlIIlIIllIIlIIllIIllIllIIlIIlIIllIIlllIIllIIlIIlIIlIIlIIllIIIlllIIIlIIllIIIllIIIlIIlIIllIlIIIllIIllIlIIIllIIIllIIllIIIllIlIIIlIIIllIlllIIlllIlIlIIlllIIllIIlllIlIIlIIIllIIllIIllIllIlIllIlIllIll;
        @NotNull
        private IIllIIlllIlIIIllIIIllIIIlllIIllIIlllIllIIIllIllIIIllIlIllIlIlIIllIIllIllIIlIIlllIIlIlIlIllIllIIlIlllIIllIIllIIlIlllIIIllIIIllIIlIIIlllIIIlllIIllIIllIllIIIlllIIlIIllIlIIlllIIlIIIllIlllIIlIllIlIlIIllIIlIIllIIllIllIIIllIIIllIIlll.IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll IIlIlIIlllIIlllIIllIllIIllIIlllIllIIIlIIllIIIllIllIlIIlllIIlIIIlIlIIIlIIIllIIlllIllIIllIIllIlllIllIIIlllIllIIlllIIlllIlllIIllIllIIIllIIllIlIllIIlIlIIlIlllIllIllIIlllIIllIIlIIlIlIIlIIllIIlIIIllIlIIIllIllIIIlllIllIIIlllIIllIll;
        @Nullable
        private IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll IIlllIIIllIIIlIIllIIlIIIlIIllIllIIllIIllIlllIIlllIIlllIIlIIlIIIllIlIlllIIllIIIlllIIlllIlIIllIllIIlllIIlIIIlllIIlllIllIllIIllIIlIIlllIIllIIlIIIlIlllIIlIIllIIlllIIIlIIlllIIlllIllIIIlIIIllIIllIllIIIllIllIIlIIIlIllIlIllIIIllIllIIIllIIlIIl;
        
        public IlllIIlIIIlllIIIlIIllIlIllIIIlIIIllIlllIIIllIIlIIllIIllIlllIIllIIllIIllIIllIIlllIIlllIllIIllIlllIlIllIIlllIllIllIIlllIIllIIIlllIIIllIIIllIlllIIlllIIlIlIlIlIllIIIlllIIlIllIIllIIlllIIlIIIllIIIllIIlIIlllIlIllIllIIlIIIllIllIIlIIllIll(@NotNull final CompletableFuture<DirectChunk> ilIIIlIIIllIIIlllIIllIllIIllIIlIIlIIllIIlIIllIIllIllIIlIIlIIllIIlllIIllIIlIIlIIlIIlIIllIIIlllIIIlIIllIIIllIIIlIIlIIllIlIIIllIIllIlIIIllIIIllIIllIIIllIlIIIlIIIllIlllIIlllIlIlIIlllIIllIIlllIlIIlIIIllIIllIIllIllIlIllIlIllIll, final IIllIIlllIlIIIllIIIllIIIlllIIllIIlllIllIIIllIllIIIllIlIllIlIlIIllIIllIllIIlIIlllIIlIlIlIllIllIIlIlllIIllIIllIIlIlllIIIllIIIllIIlIIIlllIIIlllIIllIIllIllIIIlllIIlIIllIlIIlllIIlIIIllIlllIIlIllIlIlIIllIIlIIllIIllIllIIIllIIIllIIlll.IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll iIlIlIIlllIIlllIIllIllIIllIIlllIllIIIlIIllIIIllIllIlIIlllIIlIIIlIlIIIlIIIllIIlllIllIIllIIllIlllIllIIIlllIllIIlllIIlllIlllIIllIllIIIllIIllIlIllIIlIlIIlIlllIllIllIIlllIIllIIlIIlIlIIlIIllIIlIIIllIlIIIllIllIIIlllIllIIIlllIIllIll) {
            Intrinsics.checkNotNullParameter((Object)ilIIIlIIIllIIIlllIIllIllIIllIIlIIlIIllIIlIIllIIllIllIIlIIlIIllIIlllIIllIIlIIlIIlIIlIIllIIIlllIIIlIIllIIIllIIIlIIlIIllIlIIIllIIllIlIIIllIIIllIIllIIIllIlIIIlIIIllIlllIIlllIlIlIIlllIIllIIlllIlIIlIIIllIIllIIllIllIlIllIlIllIll, "");
            Intrinsics.checkNotNullParameter((Object)iIlIlIIlllIIlllIIllIllIIllIIlllIllIIIlIIllIIIllIllIlIIlllIIlIIIlIlIIIlIIIllIIlllIllIIllIIllIlllIllIIIlllIllIIlllIIlllIlllIIllIllIIIllIIllIlIllIIlIlIIlIlllIllIllIIlllIIllIIlIIlIlIIlIIllIIlIIIllIlIIIllIllIIIlllIllIIIlllIIllIll, "");
            this.IlIIIlIIIllIIIlllIIllIllIIllIIlIIlIIllIIlIIllIIllIllIIlIIlIIllIIlllIIllIIlIIlIIlIIlIIllIIIlllIIIlIIllIIIllIIIlIIlIIllIlIIIllIIllIlIIIllIIIllIIllIIIllIlIIIlIIIllIlllIIlllIlIlIIlllIIllIIlllIlIIlIIIllIIllIIllIllIlIllIlIllIll = ilIIIlIIIllIIIlllIIllIllIIllIIlIIlIIllIIlIIllIIllIllIIlIIlIIllIIlllIIllIIlIIlIIlIIlIIllIIIlllIIIlIIllIIIllIIIlIIlIIllIlIIIllIIllIlIIIllIIIllIIllIIIllIlIIIlIIIllIlllIIlllIlIlIIlllIIllIIlllIlIIlIIIllIIllIIllIllIlIllIlIllIll;
            this.IIlIlIIlllIIlllIIllIllIIllIIlllIllIIIlIIllIIIllIllIlIIlllIIlIIIlIlIIIlIIIllIIlllIllIIllIIllIlllIllIIIlllIllIIlllIIlllIlllIIllIllIIIllIIllIlIllIIlIlIIlIlllIllIllIIlllIIllIIlIIlIlIIlIIllIIlIIIllIlIIIllIllIIIlllIllIIIlllIIllIll = iIlIlIIlllIIlllIIllIllIIllIIlllIllIIIlIIllIIIllIllIlIIlllIIlIIIlIlIIIlIIIllIIlllIllIIllIIllIlllIllIIIlllIllIIlllIIlllIlllIIllIllIIIllIIllIlIllIIlIlIIlIlllIllIllIIlllIIllIIlIIlIlIIlIIllIIlIIIllIlIIIllIllIIIlllIllIIIlllIIllIll;
        }
        
        @NotNull
        @Override
        public CompletableFuture<DirectChunk> IIlIlIIIllIllIIlllIIlIIlllIIlllIIlllIIlllIIIlllIlllIIllIllIlIllIlIIlIIlIlIlllIllIIIllIlIllIIlIIllIIllIIlIIIllIIllIIllIllIIllIIIlllIlIllIlllIllIIlllIIIlIIllIIlIIllIIlIIIllIllIIIllIIIllIIllIlIIIlllIIIlllIllIllIlIllIlIIllIIIll() {
            return this.IlIIIlIIIllIIIlllIIllIllIIllIIlIIlIIllIIlIIllIIllIllIIlIIlIIllIIlllIIllIIlIIlIIlIIlIIllIIIlllIIIlIIllIIIllIIIlIIlIIllIlIIIllIIllIlIIIllIIIllIIllIIIllIlIIIlIIIllIlllIIlllIlIlIIlllIIllIIlllIlIIlIIIllIIllIIllIllIlIllIlIllIll;
        }
        
        @NotNull
        public final IIllIIlllIlIIIllIIIllIIIlllIIllIIlllIllIIIllIllIIIllIlIllIlIlIIllIIllIllIIlIIlllIIlIlIlIllIllIIlIlllIIllIIllIIlIlllIIIllIIIllIIlIIIlllIIIlllIIllIIllIllIIIlllIIlIIllIlIIlllIIlIIIllIlllIIlIllIlIlIIllIIlIIllIIllIllIIIllIIIllIIlll.IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll IIllIIllIIllIlIllIIlIlIlIlIllIIlllIllIIllIIIllIIIlllIIIllIIIlllIIlllIIlIIllIIllIIIlIIlllIIllIlIlIlIIIlIIIllIIlIllIIIllIllIIlllIlIlllIIIllIlllIIIlIIIlIIllIllIlllIIIllIIIllIIllIIllIIIllIIlIlIIllIIIlIIllIIlIlIlIlIlIIllIlll() {
            return this.IIlIlIIlllIIlllIIllIllIIllIIlllIllIIIlIIllIIIllIllIlIIlllIIlIIIlIlIIIlIIIllIIlllIllIIllIIllIlllIllIIIlllIllIIlllIIlllIlllIIllIllIIIllIIllIlIllIIlIlIIlIlllIllIllIIlllIIllIIlIIlIlIIlIIllIIlIIIllIlIIIllIllIIIlllIllIIIlllIIllIll;
        }
        
        public final void IIllIllIIllIlIIlIlIIlllIllIIllIIllIlIIllIIIllIIIllIIllIIIlIllIIlIIlllIllIIlIllIllIIlIIlIlIIIlIllIIllIlllIllIIIllIIlIIlllIllIIlIIIlIIllIIllIIlIlIIlIllIIlllIlIIlllIIlIlIIlIIlIlllIIlIIIlIIllIllIlllIIlllIIIlIIllIIIlll(@NotNull final IIllIIlllIlIIIllIIIllIIIlllIIllIIlllIllIIIllIllIIIllIlIllIlIlIIllIIllIllIIlIIlllIIlIlIlIllIllIIlIlllIIllIIllIIlIlllIIIllIIIllIIlIIIlllIIIlllIIllIIllIllIIIlllIIlIIllIlIIlllIIlIIIllIlllIIlIllIlIlIIllIIlIIllIIllIllIIIllIIIllIIlll.IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll iIlIlIIlllIIlllIIllIllIIllIIlllIllIIIlIIllIIIllIllIlIIlllIIlIIIlIlIIIlIIIllIIlllIllIIllIIllIlllIllIIIlllIllIIlllIIlllIlllIIllIllIIIllIIllIlIllIIlIlIIlIlllIllIllIIlllIIllIIlIIlIlIIlIIllIIlIIIllIlIIIllIllIIIlllIllIIIlllIIllIll) {
            Intrinsics.checkNotNullParameter((Object)iIlIlIIlllIIlllIIllIllIIllIIlllIllIIIlIIllIIIllIllIlIIlllIIlIIIlIlIIIlIIIllIIlllIllIIllIIllIlllIllIIIlllIllIIlllIIlllIlllIIllIllIIIllIIllIlIllIIlIlIIlIlllIllIllIIlllIIllIIlIIlIlIIlIIllIIlIIIllIlIIIllIllIIIlllIllIIIlllIIllIll, "");
            this.IIlIlIIlllIIlllIIllIllIIllIIlllIllIIIlIIllIIIllIllIlIIlllIIlIIIlIlIIIlIIIllIIlllIllIIllIIllIlllIllIIIlllIllIIlllIIlllIlllIIllIllIIIllIIllIlIllIIlIlIIlIlllIllIllIIlllIIllIIlIIlIlIIlIIllIIlIIIllIlIIIllIllIIIlllIllIIIlllIIllIll = iIlIlIIlllIIlllIIllIllIIllIIlllIllIIIlIIllIIIllIllIlIIlllIIlIIIlIlIIIlIIIllIIlllIllIIllIIllIlllIllIIIlllIllIIlllIIlllIlllIIllIllIIIllIIllIlIllIIlIlIIlIlllIllIllIIlllIIllIIlIIlIlIIlIIllIIlIIIllIlIIIllIllIIIlllIllIIIlllIIllIll;
        }
        
        @Nullable
        public final IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll IIIlllIlIIIlllIIllIIllIlllIIIllIIllIIIlllIllIlllIIllIIllIllIlIIlIIllIllIlIllIIlllIIlllIIlllIIllIllIlIllIIllIIlIIIlIIlIlIIIllIllIlllIllIIlIIllIlIllIIllIlllIllIIlllIIlIIlllIlllIIIlIIlIlllIIIlllIIlllIIllIIIlIlllIIlIIllIIllIIIllIIIlll() {
            return this.IIlllIIIllIIIlIIllIIlIIIlIIllIllIIllIIllIlllIIlllIIlllIIlIIlIIIllIlIlllIIllIIIlllIIlllIlIIllIllIIlllIIlIIIlllIIlllIllIllIIllIIlIIlllIIllIIlIIIlIlllIIlIIllIIlllIIIlIIlllIIlllIllIIIlIIIllIIllIllIIIllIllIIlIIIlIllIlIllIIIllIllIIIllIIlIIl;
        }
        
        public final void IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(@Nullable final IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll iIlllIIIllIIIlIIllIIlIIIlIIllIllIIllIIllIlllIIlllIIlllIIlIIlIIIllIlIlllIIllIIIlllIIlllIlIIllIllIIlllIIlIIIlllIIlllIllIllIIllIIlIIlllIIllIIlIIIlIlllIIlIIllIIlllIIIlIIlllIIlllIllIIIlIIIllIIllIllIIIllIllIIlIIIlIllIlIllIIIllIllIIIllIIlIIl) {
            this.IIlllIIIllIIIlIIllIIlIIIlIIllIllIIllIIllIlllIIlllIIlllIIlIIlIIIllIlIlllIIllIIIlllIIlllIlIIllIllIIlllIIlIIIlllIIlllIllIllIIllIIlIIlllIIllIIlIIIlIlllIIlIIllIIlllIIIlIIlllIIlllIllIIIlIIIllIIllIllIIIllIllIIlIIIlIllIlIllIIIllIllIIIllIIlIIl = iIlllIIIllIIIlIIllIIlIIIlIIllIllIIllIIllIlllIIlllIIlllIIlIIlIIIllIlIlllIIllIIIlllIIlllIlIIllIllIIlllIIlIIIlllIIlllIllIllIIllIIlIIlllIIllIIlIIIlIlllIIlIIllIIlllIIIlIIlllIIlllIllIIIlIIIllIIllIllIIIllIllIIlIIIlIllIlIllIIIllIllIIIllIIlIIl;
        }
        
        @Override
        public void IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(@NotNull final IIllIIlllIlIIIllIIIllIIIlllIIllIIlllIllIIIllIllIIIllIlIllIlIlIIllIIllIllIIlIIlllIIlIlIlIllIllIIlIlllIIllIIllIIlIlllIIIllIIIllIIlIIIlllIIIlllIIllIIllIllIIIlllIIlIIllIlIIlllIIlIIIllIlllIIlIllIlIlIIllIIlIIllIIllIllIIIllIIIllIIlll.IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll iIlIlIIlllIIlllIIllIllIIllIIlllIllIIIlIIllIIIllIllIlIIlllIIlIIIlIlIIIlIIIllIIlllIllIIllIIllIlllIllIIIlllIllIIlllIIlllIlllIIllIllIIIllIIllIlIllIIlIlIIlIlllIllIllIIlllIIllIIlIIlIlIIlIIllIIlIIIllIlIIIllIllIIIlllIllIIIlllIIllIll) {
            Intrinsics.checkNotNullParameter((Object)iIlIlIIlllIIlllIIllIllIIllIIlllIllIIIlIIllIIIllIllIlIIlllIIlIIIlIlIIIlIIIllIIlllIllIIllIIllIlllIllIIIlllIllIIlllIIlllIlllIIllIllIIIllIIllIlIllIIlIlIIlIlllIllIllIIlllIIllIIlIIlIlIIlIIllIIlIIIllIlIIIllIllIIIlllIllIIIlllIIllIll, "");
            if (this.IIlIlIIlllIIlllIIllIllIIllIIlllIllIIIlIIllIIIllIllIlIIlllIIlIIIlIlIIIlIIIllIIlllIllIIllIIllIlllIllIIIlllIllIIlllIIlllIlllIIllIllIIIllIIllIlIllIIlIlIIlIlllIllIllIIlllIIllIIlIIlIlIIlIIllIIlIIIllIlIIIllIllIIIlllIllIIIlllIIllIll.ordinal() < iIlIlIIlllIIlllIIllIllIIllIIlllIllIIIlIIllIIIllIllIlIIlllIIlIIIlIlIIIlIIIllIIlllIllIIllIIllIlllIllIIIlllIllIIlllIIlllIlllIIllIllIIIllIIllIlIllIIlIlIIlIlllIllIllIIlllIIllIIlIIlIlIIlIIllIIlIIIllIlIIIllIllIIIlllIllIIIlllIIllIll.ordinal()) {
                this.IIlIlIIlllIIlllIIllIllIIllIIlllIllIIIlIIllIIIllIllIlIIlllIIlIIIlIlIIIlIIIllIIlllIllIIllIIllIlllIllIIIlllIllIIlllIIlllIlllIIllIllIIIllIIllIlIllIIlIlIIlIlllIllIllIIlllIIllIIlIIlIlIIlIIllIIlIIIllIlIIIllIllIIIlllIllIIIlllIIllIll = iIlIlIIlllIIlllIIllIllIIllIIlllIllIIIlIIllIIIllIllIlIIlllIIlIIIlIlIIIlIIIllIIlllIllIIllIIllIlllIllIIIlllIllIIlllIIlllIlllIIllIllIIIllIIllIlIllIIlIlIIlIlllIllIllIIlllIIllIIlIIlIlIIlIIllIIlIIIllIlIIIllIllIIIlllIllIIIlllIIllIll;
            }
            final IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll iIlllIIIllIIIlIIllIIlIIIlIIllIllIIllIIllIlllIIlllIIlllIIlIIlIIIllIlIlllIIllIIIlllIIlllIlIIllIllIIlllIIlIIIlllIIlllIllIllIIllIIlIIlllIIllIIlIIIlIlllIIlIIllIIlllIIIlIIlllIIlllIllIIIlIIIllIIllIllIIIllIllIIlIIIlIllIlIllIIIllIllIIIllIIlIIl = this.IIlllIIIllIIIlIIllIIlIIIlIIllIllIIllIIllIlllIIlllIIlllIIlIIlIIIllIlIlllIIllIIIlllIIlllIlIIllIllIIlllIIlIIIlllIIlllIllIllIIllIIlIIlllIIllIIlIIIlIlllIIlIIllIIlllIIIlIIlllIIlllIllIIIlIIIllIIllIllIIIllIllIIlIIIlIllIlIllIIIllIllIIIllIIlIIl;
            if (iIlllIIIllIIIlIIllIIlIIIlIIllIllIIllIIllIlllIIlllIIlllIIlIIlIIIllIlIlllIIllIIIlllIIlllIlIIllIllIIlllIIlIIIlllIIlllIllIllIIllIIlIIlllIIllIIlIIIlIlllIIlIIllIIlllIIIlIIlllIIlllIllIIIlIIIllIIllIllIIIllIllIIlIIIlIllIlIllIIIllIllIIIllIIlIIl != null) {
                iIlllIIIllIIIlIIllIIlIIIlIIllIllIIllIIllIlllIIlllIIlllIIlIIlIIIllIlIlllIIllIIIlllIIlllIlIIllIllIIlllIIlIIIlllIIlllIllIllIIllIIlIIlllIIllIIlIIIlIlllIIlIIllIIlllIIIlIIlllIIlllIllIIIlIIIllIIllIllIIIllIllIIlIIIlIllIlIllIIIllIllIIIllIIlIIl.IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(iIlIlIIlllIIlllIIllIllIIllIIlllIllIIIlIIllIIIllIllIlIIlllIIlIIIlIlIIIlIIIllIIlllIllIIllIIllIlllIllIIIlllIllIIlllIIlllIlllIIllIllIIIllIIllIlIllIIlIlIIlIlllIllIllIIlllIIllIIlIIlIlIIlIIllIIlIIIllIlIIIllIllIIIlllIllIIIlllIIllIll);
            }
        }
    }
    
    public final class IIllIllIIllIlIIlIlIIlllIllIIllIIllIlIIllIIIllIIIllIIllIIIlIllIIlIIlllIllIIlIllIllIIlIIlIlIIIlIllIIllIlllIllIIIllIIlIIlllIllIIlIIIlIIllIIllIIlIlIIlIllIIlllIlIIlllIIlIlIIlIIlIlllIIlIIIlIIllIllIlllIIlllIIIlIIllIIIlll implements IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll, Runnable
    {
        private final int IIIllIlllIIlllIIIllIIllIIllIlIllIIIllIIIllIIlIIIlIIlIlllIlIIllIIIlllIlIIIllIIIlIIllIIlIllIIIlllIIllIllIIlIIIlllIlllIIlIIlIIlIIIlIIlIlllIIllIllIIlIIllIIlIIllIlIllIIlIIIlIIllIIllIlIllIlIIlllIIIllIIIlllIIIlIlIIlIIIllIIIlllIlIIll;
        private final int IIIlllIIIlIIIlIIlIIlIIllIIllIlIIIlIlIlIIIllIIIlIIlllIllIllIIIllIIlIIIlllIIlIlIlIIlIIllIIIllIIlIIIlllIIlIIlIllIIIllIIIllIlIllIIllIIIlllIIIlIIIlIlIIIlIlllIIlllIlIIllIIIlIIlIlIllIlIIlIIlIIllIlIllIIIllIIlllIIllIIllIIllIIlll;
        @NotNull
        private final CompletableFuture<DirectChunk> IlIIllIllIIlllIIlIlllIIlllIllIllIIIlIIlllIIlllIIllIlIIlIIllIIllIIlIIlllIlllIIllIlllIIllIllIIIlIIIlIIIlllIIlIIllIIlllIIlIlIllIllIIIlIIllIIIllIIlllIIlllIllIlllIIllIIllIIIllIIIlllIIIlIlllIlIIllIIIlIIllIlIlIIllIllIIllIIIlIllIIlIll;
        @NotNull
        private final AtomicBoolean IIllIIlllIIlIIlIIllIlllIllIIIllIIllIlllIIllIlIIllIllIIIlIIllIllIlllIIlIIlIllIIllIIIlIIlllIIIlIIIlIIlllIllIlIIlIIIllIllIIllIIIllIlIIIlllIIlllIllIlIIllIIllIIIllIllIllIIIlIIIllIIIlllIIlllIIllIIlIlllIIllIlllIllIIllIIlIIllIIlllIIlIIIll;
        @NotNull
        private final AtomicInteger IlIIlllIIIllIlllIlllIIlllIIIllIIIlllIIIllIIlIIlIllIIllIlIIllIIlIlllIlIIIlllIllIIlIlIIIlllIIlIlIlIlllIIIllIlIllIlllIIlIIlIlIlIIllIIIlllIIlIlIIlllIIlIIIlllIIIlIllIlIllIIllIIIlIIIllIIllIlllIIlIIllIllIIllIIIllIIlllIIlIllIIl;
        @NotNull
        private final AtomicInteger IlllIlllIIlllIIIllIIlllIllIIlIIlIIlIllIlllIllIlIIIlllIIIlIllIIllIIIllIllIIlIIIlIIlllIllIlIIllIIllIIlllIIIllIIllIIIlllIIIllIIllIIIlllIllIIIlllIIllIIIlIIllIlIllIIllIIlllIIllIIIllIIIlIIllIIlllIIIlIlllIIIllIIllIIIllIIllIIlIllIIlIlIIlIllIIlll;
        final /* synthetic */ IIllIIIlllIIIllIIIllIIlIIllIIllIIIlllIIlIIIllIIllIIIlIIIlllIIlllIIlllIIllIIlIlllIlllIlIlIlllIllIIIllIIIllIlIlIllIlIllIIlllIllIlIIlIIIlIlIIllIlllIIlIllIIIlIlllIIllIllIIlIIlllIIlllIIIllIIllIlllIIlIlIIIllIIllIIIlIIIllIIllIIllIIIlllIIll IIlllIIlIlIIlIIlllIIlIlllIIIlllIlIIlIllIIllIlllIIIllIlIlIIlllIIllIllIIlIIIllIIllIIllIIIlllIIlllIllIIIlIIllIIllIIlllIIllIIIlIIlllIIllIIIlIIIllIIIlIIIlllIllIIIlllIIIlllIlIlIIIllIIlllIIlllIIlIIllIIlIllIIllIlllIIlllIlllIlIIIllIIlIlllIllIIIll;
        
        public IIllIllIIllIlIIlIlIIlllIllIIllIIllIlIIllIIIllIIIllIIllIIIlIllIIlIIlllIllIIlIllIllIIlIIlIlIIIlIllIIllIlllIllIIIllIIlIIlllIllIIlIIIlIIllIIllIIlIlIIlIllIIlllIlIIlllIIlIlIIlIIlIlllIIlIIIlIIllIllIlllIIlllIIIlIIllIIIlll(final int iiIllIlllIIlllIIIllIIllIIllIlIllIIIllIIIllIIlIIIlIIlIlllIlIIllIIIlllIlIIIllIIIlIIllIIlIllIIIlllIIllIllIIlIIIlllIlllIIlIIlIIlIIIlIIlIlllIIllIllIIlIIllIIlIIllIlIllIIlIIIlIIllIIllIlIllIlIIlllIIIllIIIlllIIIlIlIIlIIIllIIIlllIlIIll, @NotNull final int iiIlllIIIlIIIlIIlIIlIIllIIllIlIIIlIlIlIIIllIIIlIIlllIllIllIIIllIIlIIIlllIIlIlIlIIlIIllIIIllIIlIIIlllIIlIIlIllIIIllIIIllIlIllIIllIIIlllIIIlIIIlIlIIIlIlllIIlllIlIIllIIIlIIlIlIllIlIIlIIlIIllIlIllIIIllIIlllIIllIIllIIllIIlll, @NotNull final CompletableFuture<DirectChunk> ilIIllIllIIlllIIlIlllIIlllIllIllIIIlIIlllIIlllIIllIlIIlIIllIIllIIlIIlllIlllIIllIlllIIllIllIIIlIIIlIIIlllIIlIIllIIlllIIlIlIllIllIIIlIIllIIIllIIlllIIlllIllIlllIIllIIllIIIllIIIlllIIIlIlllIlIIllIIIlIIllIlIlIIllIllIIllIIIlIllIIlIll, final IIllIIlllIlIIIllIIIllIIIlllIIllIIlllIllIIIllIllIIIllIlIllIlIlIIllIIllIllIIlIIlllIIlIlIlIllIllIIlIlllIIllIIllIIlIlllIIIllIIIllIIlIIIlllIIIlllIIllIIllIllIIIlllIIlIIllIlIIlllIIlIIIllIlllIIlIllIlIlIIllIIlIIllIIllIllIIIllIIIllIIlll.IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll illIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll) {
            Intrinsics.checkNotNullParameter((Object)ilIIllIllIIlllIIlIlllIIlllIllIllIIIlIIlllIIlllIIllIlIIlIIllIIllIIlIIlllIlllIIllIlllIIllIllIIIlIIIlIIIlllIIlIIllIIlllIIlIlIllIllIIIlIIllIIIllIIlllIIlllIllIlllIIllIIllIIIllIIIlllIIIlIlllIlIIllIIIlIIllIlIlIIllIllIIllIIIlIllIIlIll, "");
            Intrinsics.checkNotNullParameter((Object)illIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll, "");
            this.IIIllIlllIIlllIIIllIIllIIllIlIllIIIllIIIllIIlIIIlIIlIlllIlIIllIIIlllIlIIIllIIIlIIllIIlIllIIIlllIIllIllIIlIIIlllIlllIIlIIlIIlIIIlIIlIlllIIllIllIIlIIllIIlIIllIlIllIIlIIIlIIllIIllIlIllIlIIlllIIIllIIIlllIIIlIlIIlIIIllIIIlllIlIIll = iiIllIlllIIlllIIIllIIllIIllIlIllIIIllIIIllIIlIIIlIIlIlllIlIIllIIIlllIlIIIllIIIlIIllIIlIllIIIlllIIllIllIIlIIIlllIlllIIlIIlIIlIIIlIIlIlllIIllIllIIlIIllIIlIIllIlIllIIlIIIlIIllIIllIlIllIlIIlllIIIllIIIlllIIIlIlIIlIIIllIIIlllIlIIll;
            this.IIIlllIIIlIIIlIIlIIlIIllIIllIlIIIlIlIlIIIllIIIlIIlllIllIllIIIllIIlIIIlllIIlIlIlIIlIIllIIIllIIlIIIlllIIlIIlIllIIIllIIIllIlIllIIllIIIlllIIIlIIIlIlIIIlIlllIIlllIlIIllIIIlIIlIlIllIlIIlIIlIIllIlIllIIIllIIlllIIllIIllIIllIIlll = iiIlllIIIlIIIlIIlIIlIIllIIllIlIIIlIlIlIIIllIIIlIIlllIllIllIIIllIIlIIIlllIIlIlIlIIlIIllIIIllIIlIIIlllIIlIIlIllIIIllIIIllIlIllIIllIIIlllIIIlIIIlIlIIIlIlllIIlllIlIIllIIIlIIlIlIllIlIIlIIlIIllIlIllIIIllIIlllIIllIIllIIllIIlll;
            this.IlIIllIllIIlllIIlIlllIIlllIllIllIIIlIIlllIIlllIIllIlIIlIIllIIllIIlIIlllIlllIIllIlllIIllIllIIIlIIIlIIIlllIIlIIllIIlllIIlIlIllIllIIIlIIllIIIllIIlllIIlllIllIlllIIllIIllIIIllIIIlllIIIlIlllIlIIllIIIlIIllIlIlIIllIllIIllIIIlIllIIlIll = ilIIllIllIIlllIIlIlllIIlllIllIllIIIlIIlllIIlllIIllIlIIlIIllIIllIIlIIlllIlllIIllIlllIIllIllIIIlIIIlIIIlllIIlIIllIIlllIIlIlIllIllIIIlIIllIIIllIIlllIIlllIllIlllIIllIIllIIIllIIIlllIIIlIlllIlIIllIIIlIIllIlIlIIllIllIIllIIIlIllIIlIll;
            this.IIllIIlllIIlIIlIIllIlllIllIIIllIIllIlllIIllIlIIllIllIIIlIIllIllIlllIIlIIlIllIIllIIIlIIlllIIIlIIIlIIlllIllIlIIlIIIllIllIIllIIIllIlIIIlllIIlllIllIlIIllIIllIIIllIllIllIIIlIIIllIIIlllIIlllIIllIIlIlllIIllIlllIllIIllIIlIIllIIlllIIlIIIll = new AtomicBoolean(false);
            this.IlIIlllIIIllIlllIlllIIlllIIIllIIIlllIIIllIIlIIlIllIIllIlIIllIIlIlllIlIIIlllIllIIlIlIIIlllIIlIlIlIlllIIIllIlIllIlllIIlIIlIlIlIIllIIIlllIIlIlIIlllIIlIIIlllIIIlIllIlIllIIllIIIlIIIllIIllIlllIIlIIllIllIIllIIIllIIlllIIlIllIIl = new AtomicInteger(-1);
            this.IlllIlllIIlllIIIllIIlllIllIIlIIlIIlIllIlllIllIlIIIlllIIIlIllIIllIIIllIllIIlIIIlIIlllIllIlIIllIIllIIlllIIIllIIllIIIlllIIIllIIllIIIlllIllIIIlllIIllIIIlIIllIlIllIIllIIlllIIllIIIllIIIlIIllIIlllIIIlIlllIIIllIIllIIIllIIllIIlIllIIlIlIIlIllIIlll = new AtomicInteger(illIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll.ordinal());
        }
        
        public final int IlllIIIllIIIlIlIIlIIIlllIIIllIIIlllIlIlllIIllIlIIlllIIIllIIlIIIlIIlIIIllIllIIIlIlllIllIIllIIlIIllIIlllIIllIIIllIIllIllIllIIllIllIIllIlllIIllIIllIllIIllIIllIlllIIllIIllIlIlllIIIllIIllIIIllIlIllIllIIIlIIIllIllIlllIllIllIIllIllIlll() {
            return this.IIIllIlllIIlllIIIllIIllIIllIlIllIIIllIIIllIIlIIIlIIlIlllIlIIllIIIlllIlIIIllIIIlIIllIIlIllIIIlllIIllIllIIlIIIlllIlllIIlIIlIIlIIIlIIlIlllIIllIllIIlIIllIIlIIllIlIllIIlIIIlIIllIIllIlIllIlIIlllIIIllIIIlllIIIlIlIIlIIIllIIIlllIlIIll;
        }
        
        public final int IIlIlIIllIIlllIIllIIlIllIIIlIIllIIIlllIlllIllIIIllIIIllIlIIllIIlllIIllIIIlllIIlIIIlIIIlllIIllIIlllIIlllIIIllIIlIIIlIIllIlIllIllIIlIIllIIllIllIllIIllIlllIIIllIIlIIlllIlIIlllIIlllIIlllIllIIlIIlIlIIlIllIIlIIIllIIlllIIllIlIIlIIIlllIIlll() {
            return this.IIIlllIIIlIIIlIIlIIlIIllIIllIlIIIlIlIlIIIllIIIlIIlllIllIllIIIllIIlIIIlllIIlIlIlIIlIIllIIIllIIlIIIlllIIlIIlIllIIIllIIIllIlIllIIllIIIlllIIIlIIIlIlIIIlIlllIIlllIlIIllIIIlIIlIlIllIlIIlIIlIIllIlIllIIIllIIlllIIllIIllIIllIIlll;
        }
        
        @NotNull
        @Override
        public CompletableFuture<DirectChunk> IIlIlIIIllIllIIlllIIlIIlllIIlllIIlllIIlllIIIlllIlllIIllIllIlIllIlIIlIIlIlIlllIllIIIllIlIllIIlIIllIIllIIlIIIllIIllIIllIllIIllIIIlllIlIllIlllIllIIlllIIIlIIllIIlIIllIIlIIIllIllIIIllIIIllIIllIlIIIlllIIIlllIllIllIlIllIlIIllIIIll() {
            return this.IlIIllIllIIlllIIlIlllIIlllIllIllIIIlIIlllIIlllIIllIlIIlIIllIIllIIlIIlllIlllIIllIlllIIllIllIIIlIIIlIIIlllIIlIIllIIlllIIlIlIllIllIIIlIIllIIIllIIlllIIlllIllIlllIIllIIllIIIllIIIlllIIIlIlllIlIIllIIIlIIllIlIlIIllIllIIllIIIlIllIIlIll;
        }
        
        public final boolean IIllIIlIllIlIlIlIIlIIllIIIlllIllIIlllIlIlIIllIIllIIlIllIlllIIlIIIllIlIIlIllIIlIllIIlIlIlIIlllIIllIIIlIIIllIIlIIIlllIllIllIlllIIlIIlllIllIIIllIIlllIIlIIIllIIllIIIllIIllIIlIIllIlIIllIlllIIllIllIIlllIlIIIllIIIllIIlllIIll() {
            return this.IIllIIlllIIlIIlIIllIlllIllIIIllIIllIlllIIllIlIIllIllIIIlIIllIllIlllIIlIIlIllIIllIIIlIIlllIIIlIIIlIIlllIllIlIIlIIIllIllIIllIIIllIlIIIlllIIlllIllIlIIllIIllIIIllIllIllIIIlIIIllIIIlllIIlllIIllIIlIlllIIllIlllIllIIllIIlIIllIIlllIIlIIIll.compareAndSet(false, true);
        }
        
        public final boolean IlllIIlIIIlllIIIlIIllIlIllIIIlIIIllIlllIIIllIIlIIllIIllIlllIIllIIllIIllIIllIIlllIIlllIllIIllIlllIlIllIIlllIllIllIIlllIIllIIIlllIIIllIIIllIlllIIlllIIlIlIlIlIllIIIlllIIlIllIIllIIlllIIlIIIllIIIllIIlIIlllIlIllIllIIlIIIllIllIIlIIllIll(final int n) {
            return this.IlllIlllIIlllIIIllIIlllIllIIlIIlIIlIllIlllIllIlIIIlllIIIlIllIIllIIIllIllIIlIIIlIIlllIllIlIIllIIllIIlllIIIllIIllIIIlllIIIllIIllIIIlllIllIIIlllIIllIIIlIIllIlIllIIllIIlllIIllIIIllIIIlIIllIIlllIIIlIlllIIIllIIllIIIllIIllIIlIllIIlIlIIlIllIIlll.get() == n;
        }
        
        @Override
        public void run() {
            if (!this.IIllIIlllIIlIIlIIllIlllIllIIIllIIllIlllIIllIlIIllIllIIIlIIllIllIlllIIlIIlIllIIllIIIlIIlllIIIlIIIlIIlllIllIlIIlIIIllIllIIllIIIllIlIIIlllIIlllIllIlIIllIIllIIIllIllIllIIIlIIIllIIIlllIIlllIIllIIlIlllIIllIlllIllIIllIIlIIllIIlllIIlIIIll.compareAndSet(false, true)) {
                return;
            }
            IIllIIIlllIIIllIIIllIIlIIllIIllIIIlllIIlIIIllIIllIIIlIIIlllIIlllIIlllIIllIIlIlllIlllIlIlIlllIllIIIllIIIllIlIlIllIlIllIIlllIllIlIIlIIIlIlIIllIlllIIlIllIIIlIlllIIllIllIIlIIlllIIlllIIIllIIllIlllIIlIlIIIllIIllIIIlIIIllIIllIIllIIIlllIIll.IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(IIllIIIlllIIIllIIIllIIlIIllIIllIIIlllIIlIIIllIIllIIIlIIIlllIIlllIIlllIIllIIlIlllIlllIlIlIlllIllIIIllIIIllIlIlIllIlIllIIlllIllIlIIlIIIlIlIIllIlllIIlIllIIIlIlllIIllIllIIlIIlllIIlllIIIllIIllIlllIIlIlIIIllIIllIIIlIIIllIIllIIllIIIlllIIll.this).IIllIIIllIllIIIlllIIIlllIIllIIlllIlIIIllIllIlIIlIIIlllIIlIIllIIllIlIllIIllIIIllIIlIllIIlIIlIIllIIlIlIIllIllIIlIlIIlIIIllIIlIllIllIIlllIllIIlIlllIIllIlIlllIIlllIIIlIIlIIllIlllIlIIllIIIllIlIlIIlIIllIllIlIllIIIllIIll(this.IIIllIlllIIlllIIIllIIllIIllIlIllIIIllIIIllIIlIIIlIIlIlllIlIIllIIIlllIlIIIllIIIlIIllIIlIllIIIlllIIllIllIIlIIIlllIlllIIlIIlIIlIIIlIIlIlllIIllIllIIlIIllIIlIIllIlIllIIlIIIlIIllIIllIlIllIlIIlllIIIllIIIlllIIIlIlIIlIIIllIIIlllIlIIll, this.IIIlllIIIlIIIlIIlIIlIIllIIllIlIIIlIlIlIIIllIIIlIIlllIllIllIIIllIIlIIIlllIIlIlIlIIlIIllIIIllIIlIIIlllIIlIIlIllIIIllIIIllIlIllIIllIIIlllIIIlIIIlIlIIIlIlllIIlllIlIIllIIIlIIlIlIllIlIIlIIlIIllIlIllIIIllIIlllIIllIIllIIllIIlll).whenComplete(IIllIllIIllIlIIlIlIIlllIllIIllIIllIlIIllIIIllIIIllIIllIIIlIllIIlIIlllIllIIlIllIllIIlIIlIlIIIlIllIIllIlllIllIIIllIIlIIlllIllIIlIIIlIIllIIllIIlIlIIlIllIIlllIlIIlllIIlIlIIlIIlIlllIIlIIIlIIllIllIlllIIlllIIIlIIllIIIlll::IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll);
        }
        
        public final void IllIllIIIlllIIIlIllIIlllIIlIllIIIlIIlllIIIllIIIlIIIllIIIllIlIIIllIIlIIllIIlIIIllIIllIlllIlllIllIIIlIllIIIlIIllIIllIIllIIlIIllIIllIIllIlIllIIlllIIIllIIlllIIllIlIllIlllIIIlIIllIIlllIIlIllIllIIlIIIllIIllIllIIIllIlIllIIIlIIIlIIlIlll() {
            int value;
            int value2;
            do {
                value = this.IlIIlllIIIllIlllIlllIIlllIIIllIIIlllIIIllIIlIIlIllIIllIlIIllIIlIlllIlIIIlllIllIIlIlIIIlllIIlIlIlIlllIIIllIlIllIlllIIlIIlIlIlIIllIIIlllIIlIlIIlllIIlIIIlllIIIlIllIlIllIIllIIIlIIIllIIllIlllIIlIIllIllIIllIIIllIIlllIIlIllIIl.get();
                value2 = this.IlllIlllIIlllIIIllIIlllIllIIlIIlIIlIllIlllIllIlIIIlllIIIlIllIIllIIIllIllIIlIIIlIIlllIllIlIIllIIllIIlllIIIllIIllIIIlllIIIllIIllIIIlllIllIIIlllIIllIIIlIIllIlIllIIllIIlllIIllIIIllIIIlIIllIIlllIIIlIlllIIIllIIllIIIllIIllIIlIllIIlIlIIlIllIIlll.get();
                if (value == value2) {
                    return;
                }
            } while (!this.IlIIlllIIIllIlllIlllIIlllIIIllIIIlllIIIllIIlIIlIllIIllIlIIllIIlIlllIlIIIlllIllIIlIlIIIlllIIlIlIlIlllIIIllIlIllIlllIIlIIlIlIlIIllIIIlllIIlIlIIlllIIlIIIlllIIIlIllIlIllIIllIIIlIIIllIIllIlllIIlIIllIllIIllIIIllIIlllIIlIllIIl.compareAndSet(value, value2));
            IIllIIIlllIIIllIIIllIIlIIllIIllIIIlllIIlIIIllIIllIIIlIIIlllIIlllIIlllIIllIIlIlllIlllIlIlIlllIllIIIllIIIllIlIlIllIlIllIIlllIllIlIIlIIIlIlIIllIlllIIlIllIIIlIlllIIllIllIIlIIlllIIlllIIIllIIllIlllIIlIlIIIllIIllIIIlIIIllIIllIIllIIIlllIIll.IIllIllIIllIlIIlIlIIlllIllIIllIIllIlIIllIIIllIIIllIIllIIIlIllIIlIIlllIllIIlIllIllIIlIIlIlIIIlIllIIllIlllIllIIIllIIlIIlllIllIIlIIIlIIllIIllIIlIlIIlIllIIlllIlIIlllIIlIlIIlIIlIlllIIlIIIlIIllIllIlllIIlllIIIlIIllIIIlll(IIllIIIlllIIIllIIIllIIlIIllIIllIIIlllIIlIIIllIIllIIIlIIIlllIIlllIIlllIIllIIlIlllIlllIlIlIlllIllIIIllIIIllIlIlIllIlIllIIlllIllIlIIlIIIlIlIIllIlllIIlIllIIIlIlllIIllIllIIlIIlllIIlllIIIllIIllIlllIIlIlIIIllIIllIIIlIIIllIIllIIllIIIlllIIll.this).IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(this, value2);
        }
        
        @Override
        public void IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(@NotNull final IIllIIlllIlIIIllIIIllIIIlllIIllIIlllIllIIIllIllIIIllIlIllIlIlIIllIIllIllIIlIIlllIIlIlIlIllIllIIlIlllIIllIIllIIlIlllIIIllIIIllIIlIIIlllIIIlllIIllIIllIllIIIlllIIlIIllIlIIlllIIlIIIllIlllIIlIllIlIlIIllIIlIIllIIllIllIIIllIIIllIIlll.IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll illIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll) {
            Intrinsics.checkNotNullParameter((Object)illIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll, "");
            int value;
            int ordinal;
            do {
                value = this.IlllIlllIIlllIIIllIIlllIllIIlIIlIIlIllIlllIllIlIIIlllIIIlIllIIllIIIllIllIIlIIIlIIlllIllIlIIllIIllIIlllIIIllIIllIIIlllIIIllIIllIIIlllIllIIIlllIIllIIIlIIllIlIllIIllIIlllIIllIIIllIIIlIIllIIlllIIIlIlllIIIllIIllIIIllIIllIIlIllIIlIlIIlIllIIlll.get();
                ordinal = illIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll.ordinal();
            } while (value < ordinal && !this.IlllIlllIIlllIIIllIIlllIllIIlIIlIIlIllIlllIllIlIIIlllIIIlIllIIllIIIllIllIIlIIIlIIlllIllIlIIllIIllIIlllIIIllIIllIIIlllIIIllIIllIIIlllIllIIIlllIIllIIIlIIllIlIllIIllIIlllIIllIIIllIIIlIIllIIlllIIIlIlllIIIllIIllIIIllIIllIIlIllIIlIlIIlIllIIlll.compareAndSet(value, ordinal));
            if (this.IlIIlllIIIllIlllIlllIIlllIIIllIIIlllIIIllIIlIIlIllIIllIlIIllIIlIlllIlIIIlllIllIIlIlIIIlllIIlIlIlIlllIIIllIlIllIlllIIlIIlIlIlIIllIIIlllIIlIlIIlllIIlIIIlllIIIlIllIlIllIIllIIIlIIIllIIllIlllIIlIIllIllIIllIIIllIIlllIIlIllIIl.get() == -1 || this.IIllIIlllIIlIIlIIllIlllIllIIIllIIllIlllIIllIlIIllIllIIIlIIllIllIlllIIlIIlIllIIllIIIlIIlllIIIlIIIlIIlllIllIlIIlIIIllIllIIllIIIllIlIIIlllIIlllIllIlIIllIIllIIIllIllIllIIIlIIIllIIIlllIIlllIIllIIlIlllIIllIlllIllIIllIIlIIllIIlllIIlIIIll.get()) {
                return;
            }
            this.IllIllIIIlllIIIlIllIIlllIIlIllIIIlIIlllIIIllIIIlIIIllIIIllIlIIIllIIlIIllIIlIIIllIIllIlllIlllIllIIIlIllIIIlIIllIIllIIllIIlIIllIIllIIllIlIllIIlllIIIllIIlllIIllIlIllIlllIIIlIIllIIlllIIlIllIllIIlIIIllIIllIllIIIllIlIllIIIlIIIlIIlIlll();
        }
        
        private static final void IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(final Function2 function2, final Object o, final Object o2) {
            Intrinsics.checkNotNullParameter((Object)function2, "");
            function2.invoke(o, o2);
        }
    }
    
    public interface IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll
    {
        @NotNull
        CompletableFuture<DirectChunk> IIlIlIIIllIllIIlllIIlIIlllIIlllIIlllIIlllIIIlllIlllIIllIllIlIllIlIIlIIlIlIlllIllIIIllIlIllIIlIIllIIllIIlIIIllIIllIIllIllIIllIIIlllIlIllIlllIllIIlllIIIlIIllIIlIIllIIlIIIllIllIIIllIIIllIIllIlIIIlllIIIlllIllIllIlIllIlIIllIIIll();
        
        void IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(@NotNull final IIllIIlllIlIIIllIIIllIIIlllIIllIIlllIllIIIllIllIIIllIlIllIlIlIIllIIllIllIIlIIlllIIlIlIlIllIllIIlIlllIIllIIllIIlIlllIIIllIIIllIIlIIIlllIIIlllIIllIIllIllIIIlllIIlIIllIlIIlllIIlIIIllIlllIIlIllIlIlIIllIIlIIllIIllIllIIIllIIIllIIlll.IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll p0);
    }
}
