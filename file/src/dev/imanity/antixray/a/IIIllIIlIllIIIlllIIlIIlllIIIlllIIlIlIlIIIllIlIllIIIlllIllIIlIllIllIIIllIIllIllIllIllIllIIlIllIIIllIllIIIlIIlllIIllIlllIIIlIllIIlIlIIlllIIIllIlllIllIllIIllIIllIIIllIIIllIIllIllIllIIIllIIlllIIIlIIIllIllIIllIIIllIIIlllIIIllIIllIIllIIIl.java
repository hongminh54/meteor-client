// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.a;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import kotlin.collections.CollectionsKt;
import io.fairyproject.bukkit.util.CoordXZ;
import com.google.common.collect.ImmutableSet;
import java.util.Collection;
import java.util.Set;
import dev.imanity.antixray.api.util.LongHash;
import dev.imanity.antixray.nms.Native;
import dev.imanity.antixray.api.DirectionalVector;
import org.bukkit.util.NumberConversions;
import kotlin.jvm.internal.Lambda;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import io.fairyproject.libs.packetevents.protocol.world.states.WrappedBlockState;
import dev.imanity.antixray.api.chunk.DirectChunk;
import dev.imanity.antixray.api.block.RaytraceBlock;
import io.fairyproject.mc.util.BlockPosition;
import dev.imanity.antixray.api.data.IMaterialDataHandler;
import dev.imanity.concurrentutil.SeqLockFactory;
import kotlin.jvm.internal.Intrinsics;
import dev.imanity.antixray.api.chunk.IDirectChunkCache;
import dev.imanity.concurrentutil.SeqLock;
import dev.imanity.antixray.api.data.DirectDataProvider;
import org.bukkit.World;
import org.bukkit.util.Vector;
import org.bukkit.Location;
import java.util.concurrent.atomic.AtomicInteger;
import dev.imanity.antixray.api.Vision;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;
import org.bukkit.entity.Player;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.Metadata;
import dev.imanity.antixray.api.IRaytracePlayer;

@Metadata(mv = { 1, 9, 0 }, k = 1, xi = 48, d1 = { "\u0000\u00d6\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0011\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u0018\u0010M\u001a\u00020N2\u0006\u0010O\u001a\u00020P2\u0006\u0010Q\u001a\u00020RH\u0016J \u0010M\u001a\u00020N2\u0006\u0010O\u001a\u00020P2\u0006\u0010Q\u001a\u00020R2\u0006\u0010S\u001a\u00020TH\u0016J\u0006\u0010U\u001a\u00020VJ\u0006\u0010W\u001a\u00020VJ\b\u0010X\u001a\u00020VH\u0016J\u0018\u0010Y\u001a\u00020E2\u0006\u0010.\u001a\u00020/2\u0006\u0010\u001b\u001a\u00020\u001cH\u0002J\u0010\u0010Z\u001a\u00020V2\u0006\u0010[\u001a\u00020\\H\u0016J\u0018\u0010]\u001a\u00020\u001c2\u0006\u0010^\u001a\u00020/2\u0006\u0010\u001b\u001a\u00020\u001cH\u0002J\u0018\u0010_\u001a\u0004\u0018\u00010`2\u0006\u0010a\u001a\u00020\u001e2\u0006\u0010b\u001a\u00020\u001eJ\u0006\u0010c\u001a\u00020\u001eJ\u000e\u0010d\u001a\b\u0012\u0004\u0012\u00020f0eH\u0016J\f\u0010g\u001a\b\u0012\u0004\u0012\u00020h0eJ\u0012\u0010i\u001a\u0004\u0018\u00010N2\u0006\u0010Q\u001a\u00020RH\u0016J\u0012\u0010j\u001a\u0004\u0018\u00010N2\u0006\u0010Q\u001a\u00020RH\u0016J\u0010\u0010k\u001a\u00020V2\u0006\u0010\b\u001a\u00020\tH\u0002J\u0016\u0010l\u001a\u00020m2\u0006\u0010a\u001a\u00020\u001e2\u0006\u0010b\u001a\u00020\u001eJ\u000e\u0010n\u001a\u00020m2\u0006\u0010o\u001a\u00020\u001eJ\u0010\u0010p\u001a\u00020V2\u0006\u0010q\u001a\u00020`H\u0002J\u0010\u0010r\u001a\u00020V2\u0006\u0010q\u001a\u00020`H\u0002J\u0010\u0010s\u001a\u00020V2\u0006\u0010Q\u001a\u00020RH\u0016J\u0018\u0010t\u001a\u00020\u001c2\u0006\u0010^\u001a\u00020\u001c2\u0006\u0010u\u001a\u00020\u001cH\u0002J\u0016\u0010v\u001a\u00020V2\u0006\u0010a\u001a\u00020\u001e2\u0006\u0010b\u001a\u00020\u001eJ\u0018\u0010w\u001a\u00020V2\u0006\u0010O\u001a\u00020P2\u0006\u0010Q\u001a\u00020RH\u0016J\u0018\u0010x\u001a\u00020V2\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\b\u001a\u00020\tJ\u001e\u0010y\u001a\u00020V2\u0006\u0010a\u001a\u00020\u001e2\u0006\u0010b\u001a\u00020\u001e2\u0006\u0010z\u001a\u00020mJ\u0006\u0010{\u001a\u00020VJ\u0006\u0010|\u001a\u00020VJ\b\u0010}\u001a\u00020VH\u0002R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\u00020\u000eX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0013\u001a\u00020\u0014X\u0080.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u001b\u001a\u0004\u0018\u00010\u001cX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u001d\u001a\u00020\u001eX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R\u000e\u0010#\u001a\u00020$X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010%\u001a\u0004\u0018\u00010&X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010'\u001a\u00020(X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010)\u001a\u00020(X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010*\u001a\u00020$X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010+\u001a\u00020(X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010,\u001a\u00020-X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010.\u001a\u0004\u0018\u00010/X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u00100\u001a\u000201X\u0080.¢\u0006\u000e\n\u0000\u001a\u0004\b2\u00103\"\u0004\b4\u00105R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b6\u00107R\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b8\u00109\"\u0004\b:\u0010;R\u0019\u0010<\u001a\n >*\u0004\u0018\u00010=0=¢\u0006\b\n\u0000\u001a\u0004\b?\u0010@R\u001a\u0010A\u001a\u00020\u001eX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bB\u0010 \"\u0004\bC\u0010\"R\u001a\u0010D\u001a\u00020EX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bF\u0010G\"\u0004\bH\u0010IR\u001a\u0010J\u001a\u00020\u0014X\u0080.¢\u0006\u000e\n\u0000\u001a\u0004\bK\u0010\u0016\"\u0004\bL\u0010\u0018¨\u0006~" }, d2 = { "Ldev/imanity/antixray/core/player/RaytracePlayer;", "Ldev/imanity/antixray/api/IRaytracePlayer;", "player", "Lorg/bukkit/entity/Player;", "raytraceWorld", "Ldev/imanity/antixray/core/RaytraceWorld;", "materialDataHandler", "Ldev/imanity/antixray/core/data/MaterialDataHandler;", "directChunkCache", "Ldev/imanity/antixray/api/chunk/IDirectChunkCache;", "(Lorg/bukkit/entity/Player;Ldev/imanity/antixray/core/RaytraceWorld;Ldev/imanity/antixray/core/data/MaterialDataHandler;Ldev/imanity/antixray/api/chunk/IDirectChunkCache;)V", "asyncContext", "Ldev/imanity/antixray/core/player/RaytracePlayerAsyncContext;", "changeHandler", "Ldev/imanity/antixray/core/player/RaytraceChangeHandler;", "getChangeHandler$plugin", "()Ldev/imanity/antixray/core/player/RaytraceChangeHandler;", "chunks", "Ldev/imanity/antixray/core/player/IRaytraceChunkCollection;", "cullingProvider", "Ldev/imanity/antixray/api/data/DirectDataProvider;", "getCullingProvider$plugin", "()Ldev/imanity/antixray/api/data/DirectDataProvider;", "setCullingProvider$plugin", "(Ldev/imanity/antixray/api/data/DirectDataProvider;)V", "dimensionId", "Ljava/util/concurrent/atomic/AtomicInteger;", "direction", "Lorg/bukkit/util/Vector;", "lastMovedTicks", "", "getLastMovedTicks", "()I", "setLastMovedTicks", "(I)V", "lastPitch", "", "lastWorld", "Lorg/bukkit/World;", "lastX", "", "lastY", "lastYaw", "lastZ", "loadedChunkLock", "", "location", "Lorg/bukkit/Location;", "occlusionCulling", "Ldev/imanity/antixray/core/player/culling/IBlockCulling;", "getOcclusionCulling$plugin", "()Ldev/imanity/antixray/core/player/culling/IBlockCulling;", "setOcclusionCulling$plugin", "(Ldev/imanity/antixray/core/player/culling/IBlockCulling;)V", "getPlayer", "()Lorg/bukkit/entity/Player;", "getRaytraceWorld$plugin", "()Ldev/imanity/antixray/core/RaytraceWorld;", "setRaytraceWorld$plugin", "(Ldev/imanity/antixray/core/RaytraceWorld;)V", "realBlocksLock", "Ldev/imanity/concurrentutil/SeqLock;", "kotlin.jvm.PlatformType", "getRealBlocksLock", "()Ldev/imanity/concurrentutil/SeqLock;", "ticks", "getTicks", "setTicks", "vision", "Ldev/imanity/antixray/api/Vision;", "getVision", "()Ldev/imanity/antixray/api/Vision;", "setVision", "(Ldev/imanity/antixray/api/Vision;)V", "visionProvider", "getVisionProvider$plugin", "setVisionProvider$plugin", "addTraceBlock", "Ldev/imanity/antixray/api/block/RaytraceBlock;", "chunk", "Ldev/imanity/antixray/api/chunk/DirectChunk;", "blockPosition", "Lio/fairyproject/mc/util/BlockPosition;", "replacement", "Lio/fairyproject/libs/packetevents/protocol/world/states/WrappedBlockState;", "changeDimension", "", "cleanup", "clear", "createVision", "execute", "command", "Ljava/lang/Runnable;", "findVision", "origin", "getChunk", "Ldev/imanity/antixray/core/RaytraceChunk;", "chunkX", "chunkZ", "getDimensionId", "getLoadedChunkCoords", "", "Lio/fairyproject/bukkit/util/CoordXZ;", "getLoadedChunks", "", "getTraceBlock", "getTraceBlockUnsafe", "initCulling", "isChunkLoaded", "", "isDimension", "id", "onChunkAdded", "raytraceChunk", "onChunkRemoved", "queueRemoveBlock", "rayTrace", "to", "removeChunk", "removeTraceBlock", "reset", "setChunkState", "loaded", "tickRaytrace", "tickUpdates", "updateVision", "plugin" })
@SourceDebugExtension({ "SMAP\nRaytracePlayer.kt\nKotlin\n*S Kotlin\n*F\n+ 1 RaytracePlayer.kt\ndev/imanity/antixray/core/player/RaytracePlayer\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,383:1\n1549#2:384\n1620#2,3:385\n*S KotlinDebug\n*F\n+ 1 RaytracePlayer.kt\ndev/imanity/antixray/core/player/RaytracePlayer\n*L\n362#1:384\n362#1:385,3\n*E\n" })
public final class IIIllIIlIllIIIlllIIlIIlllIIIlllIIlIlIlIIIllIlIllIIIlllIllIIlIllIllIIIllIIllIllIllIllIllIIlIllIIIllIllIIIlIIlllIIllIlllIIIlIllIIlIlIIlllIIIllIlllIllIllIIllIIllIIIllIIIllIIllIllIllIIIllIIlllIIIlIIIllIllIIllIIIllIIIlllIIIllIIllIIllIIIl implements IRaytracePlayer
{
    @NotNull
    private final Player h;
    @Nullable
    private IlllIIlIIIlllIIIlIIllIlIllIIIlIIIllIlllIIIllIIlIIllIIllIlllIIllIIllIIllIIllIIlllIIlllIllIIllIlllIlIllIIlllIllIllIIlllIIllIIIlllIIIllIIIllIlllIIlllIIlIlIlIlIllIIIlllIIlIllIIllIIlllIIlIIIllIIIllIIlIIlllIlIllIllIIlIIIllIllIIlIIllIll i;
    @NotNull
    private final IIIllIllIIIllIIllIlIllIlllIIlIlIIIllIlIIIlIIIlIlIIIlIIIllIlIllIlIllIIllIIlllIlIIlIIllIlIIIllIIllIIlIllIIlllIlllIllIlIIlllIIlllIIllIlIIlllIIIllIIlIllIIlIIllIIllIIIlIlIIIllIIlllIllIIlllIIlllIIIllIIIlIIIllIlIIIlIIlIIIllIIIll j;
    @NotNull
    private final IIlIlIlIlIllIIIllIIlllIlllIIIllIIIlIIIlllIIIlllIllIIllIIlIIIllIIlllIIIlIIllIIllIIlIIllIIlIIIlIIllIIllIIlIIlIIlIlIIIllIIIllIIlllIIIlIIIllIIllIIllIIIlllIlIIlIIIllIIllIIllIIIlllIIIlllIIIllIIllIIIllIIlllIIIlIIlIIlllIIlllIIlllIllIIllIIlllIIlIlIll k;
    @NotNull
    private volatile Vision IIllIlIlIlIIIllIIllIIllIIlIIllIIlIIlllIIllIIlllIIIllIIllIIIlllIllIIIllIIIllIIlIIlIIlIIllIIlllIlIIlIllIIIlIIlllIIlllIlllIIllIllIIlIIlIlllIIlllIIllIIIllIlIIlIllIIIlIIIlllIIllIlIIlllIllIllIIllIIlIIIllIlllIIIllIlIIlIIlllIIllIlIlll;
    private int l;
    private int n;
    @NotNull
    private final AtomicInteger o;
    @Nullable
    private Location p;
    @Nullable
    private Vector direction;
    @Nullable
    private World q;
    private double r;
    private double s;
    private double t;
    private float u;
    private float v;
    @NotNull
    private final IllIIlIllIlIllIIIllIlIlllIllIllIlIllIIIllIIlllIIIllIllIIllIIllIIllIllIllIIlIIlIIlIIIlllIIIlllIIlIllIllIlllIIlIllIllIIIlIIIllIIlIlIIIlIIIlIIIlllIllIIlllIIIllIIlIllIlIIlIIIllIIIlllIIlIIIlllIIlIlIIIllIIIlIIlIIIlIIIllIllIl w;
    @NotNull
    private final Object A;
    @NotNull
    private final IllIIllIIllIlllIIlIIllIIlIIllIIlllIIIllIlIlllIlllIIllIIllIllIIllIllIIllIIlIIlllIlllIIlllIIIllIIllIIIllIlIIllIllIllIllIIllIIIlIIlllIlllIIIllIIIlIIllIllIIIllIIIllIIlIIIllIIlIIllIllIIlllIlIlIllIllIllIlIIlIllIIlIIllIIllIIIlllIIl B;
    public DirectDataProvider C;
    public DirectDataProvider D;
    public IIllIIIlllIIlllIIllIlIIIlllIIIllIlIIllIIlIllIIlllIIIlIIIlIIIlIIlIIlIIIllIlllIllIIIllIIllIlIlllIllIIllIIlIIlllIlllIIlllIIIlllIIllIlllIlIIllIllIlIIllIIIlIIIlIIIllIIlIIlIIlllIllIlllIlIIllIIIllIIlIIlllIIlIIllIlllIIIlIlIIllIIIlllIIIlIll E;
    private final SeqLock F;
    
    public IIIllIIlIllIIIlllIIlIIlllIIIlllIIlIlIlIIIllIlIllIIIlllIllIIlIllIllIIIllIIllIllIllIllIllIIlIllIIIllIllIIIlIIlllIIllIlllIIIlIllIIlIlIIlllIIIllIlllIllIllIIllIIllIIIllIIIllIIllIllIllIIIllIIlllIIIlIIIllIllIIllIIIllIIIlllIIIllIIllIIllIIIl(@NotNull final Player h, @Nullable final IlllIIlIIIlllIIIlIIllIlIllIIIlIIIllIlllIIIllIIlIIllIIllIlllIIllIIllIIllIIllIIlllIIlllIllIIllIlllIlIllIIlllIllIllIIlllIIllIIIlllIIIllIIIllIlllIIlllIIlIlIlIlIllIIIlllIIlIllIIllIIlllIIlIIIllIIIllIIlIIlllIlIllIllIIlIIIllIllIIlIIllIll i, @NotNull final IIIllIllIIIllIIllIlIllIlllIIlIlIIIllIlIIIlIIIlIlIIIlIIIllIlIllIlIllIIllIIlllIlIIlIIllIlIIIllIIllIIlIllIIlllIlllIllIlIIlllIIlllIIllIlIIlllIIIllIIlIllIIlIIllIIllIIIlIlIIIllIIlllIllIIlllIIlllIIIllIIIlIIIllIlIIIlIIlIIIllIIIll j, @NotNull final IDirectChunkCache directChunkCache) {
        Intrinsics.checkNotNullParameter((Object)h, "");
        Intrinsics.checkNotNullParameter((Object)j, "");
        Intrinsics.checkNotNullParameter((Object)directChunkCache, "");
        this.h = h;
        this.i = i;
        this.j = j;
        final String property = System.getProperty("java.specification.version");
        Intrinsics.checkNotNullExpressionValue((Object)property, "");
        this.k = ((Double.parseDouble(property) >= 17.0) ? new IlllIIIlllIIllIIIlllIIllIllIIIllIIllIIlIIlllIIlllIIlllIIlllIIllIlIIlIIlIIIlllIIllIlllIIllIIllIIllIIlIlllIllIIIlIlllIlllIIlllIllIIllIIllIlIIlllIlIIllIlIIIllIIIlllIIllIlllIIllIlIIlIIlllIIIlIIllIIIlllIIlIlIIlllIIIlIIlllIllIlIllIIllIlllIIlll() : ((IIlIlIlIlIllIIIllIIlllIlllIIIllIIIlIIIlllIIIlllIllIIllIIlIIIllIIlllIIIlIIllIIllIIlIIllIIlIIIlIIllIIllIIlIIlIIlIlIIIllIIIllIIlllIIIlIIIllIIllIIllIIIlllIlIIlIIIllIIllIIllIIIlllIIIlllIIIllIIllIIIllIIlllIIIlIIlIIlllIIlllIIlllIllIIllIIlllIIlIlIll)new IIllIlllIlllIllIlIIlllIIlIIIlllIIIlIlIllIlIIllIIIllIlIlllIIIllIIIllIlIllIlllIIlllIIIlllIIIlllIIlllIIIlllIIllIIllIlllIIIlllIIlIIlIIllIIIlIIIllIlIIIlIIIllIIIlllIIllIIllIIllIIlIlIIllIlIIlllIllIIllIIIllIIlllIIlIIIllIIIlIlllIIlllIIllIIIlIllIll()));
        this.o = new AtomicInteger(0);
        this.w = new IllIIlIllIlIllIIIllIlIlllIllIllIlIllIIIllIIlllIIIllIllIIllIIllIIllIllIllIIlIIlIIlIIIlllIIIlllIIlIllIllIlllIIlIllIllIIIlIIIllIIlIlIIIlIIIlIIIlllIllIIlllIIIllIIlIllIlIIlIIIllIIIlllIIlIIIlllIIlIlIIIllIIIlIIlIIIlIIIllIllIl(this.getPlayer(), this);
        this.A = new Object();
        this.B = new IllIIllIIllIlllIIlIIllIIlIIllIIlllIIIllIlIlllIlllIIllIIllIllIIllIllIIllIIlIIlllIlllIIlllIIIllIIllIIIllIlIIllIllIllIllIIllIIIlIIlllIlllIIIllIIIlIIllIllIIIllIIIllIIlIIIllIIlIIllIllIIlllIlIlIllIllIllIlIIlIllIIlIIllIIllIIIlllIIl(this.getPlayer(), this);
        this.F = SeqLockFactory.createWeakSeqLock();
        this.IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(directChunkCache);
        final Location eyeLocation = this.getPlayer().getEyeLocation();
        Intrinsics.checkNotNullExpressionValue((Object)eyeLocation, "");
        final Vector direction = this.getPlayer().getEyeLocation().getDirection();
        Intrinsics.checkNotNullExpressionValue((Object)direction, "");
        this.IIllIllIIllIlIIlIlIIlllIllIIllIIllIlIIllIIIllIIIllIIllIIIlIllIIlIIlllIllIIlIllIllIIlIIlIlIIIlIllIIllIlllIllIIIllIIlIIlllIllIIlIIIlIIllIIllIIlIlIIlIllIIlllIlIIlllIIlIlIIlIIlIlllIIlIIIlIIllIllIlllIIlllIIIlIIllIIIlll(this.IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(eyeLocation, direction));
    }
    
    @NotNull
    @Override
    public Player getPlayer() {
        return this.h;
    }
    
    @Nullable
    public final IlllIIlIIIlllIIIlIIllIlIllIIIlIIIllIlllIIIllIIlIIllIIllIlllIIllIIllIIllIIllIIlllIIlllIllIIllIlllIlIllIIlllIllIllIIlllIIllIIIlllIIIllIIIllIlllIIlllIIlIlIlIlIllIIIlllIIlIllIIllIIlllIIlIIIllIIIllIIlIIlllIlIllIllIIlIIIllIllIIlIIllIll IlIIlllIIlIIllIlIllIIlIIlllIIIllIIIllIllIIlllIIllIIIllIIllIllIIlIIlIIlIllIlllIIllIlllIIllIIllIllIIlllIIIlllIIllIllIlIIlllIIlIIllIIIllIIlIIllIIIlllIlIIllIIllIIIlllIIlIIlIllIlllIlIIlIIllIIlllIIlIlIIlIlllIIllIIlllIIIllIllIIlllIIll() {
        return this.i;
    }
    
    public final void IIllIIIllIllIIIlllIIIlllIIllIIlllIlIIIllIllIlIIlIIIlllIIlIIllIIllIlIllIIllIIIllIIlIllIIlIIlIIllIIlIlIIllIllIIlIlIIlIIIllIIlIllIllIIlllIllIIlIlllIIllIlIlllIIlllIIIlIIlIIllIlllIlIIllIIIllIlIlIIlIIllIllIlIllIIIllIIll(@Nullable final IlllIIlIIIlllIIIlIIllIlIllIIIlIIIllIlllIIIllIIlIIllIIllIlllIIllIIllIIllIIllIIlllIIlllIllIIllIlllIlIllIIlllIllIllIIlllIIllIIIlllIIIllIIIllIlllIIlllIIlIlIlIlIllIIIlllIIlIllIIllIIlllIIlIIIllIIIllIIlIIlllIlIllIllIIlIIIllIllIIlIIllIll i) {
        this.i = i;
    }
    
    @NotNull
    @Override
    public Vision getVision() {
        return this.IIllIlIlIlIIIllIIllIIllIIlIIllIIlIIlllIIllIIlllIIIllIIllIIIlllIllIIIllIIIllIIlIIlIIlIIllIIlllIlIIlIllIIIlIIlllIIlllIlllIIllIllIIlIIlIlllIIlllIIllIIIllIlIIlIllIIIlIIIlllIIllIlIIlllIllIllIIllIIlIIIllIlllIIIllIlIIlIIlllIIllIlIlll;
    }
    
    public void IIllIllIIllIlIIlIlIIlllIllIIllIIllIlIIllIIIllIIIllIIllIIIlIllIIlIIlllIllIIlIllIllIIlIIlIlIIIlIllIIllIlllIllIIIllIIlIIlllIllIIlIIIlIIllIIllIIlIlIIlIllIIlllIlIIlllIIlIlIIlIIlIlllIIlIIIlIIllIllIlllIIlllIIIlIIllIIIlll(@NotNull final Vision iIllIlIlIlIIIllIIllIIllIIlIIllIIlIIlllIIllIIlllIIIllIIllIIIlllIllIIIllIIIllIIlIIlIIlIIllIIlllIlIIlIllIIIlIIlllIIlllIlllIIllIllIIlIIlIlllIIlllIIllIIIllIlIIlIllIIIlIIIlllIIllIlIIlllIllIllIIllIIlIIIllIlllIIIllIlIIlIIlllIIllIlIlll) {
        Intrinsics.checkNotNullParameter((Object)iIllIlIlIlIIIllIIllIIllIIlIIllIIlIIlllIIllIIlllIIIllIIllIIIlllIllIIIllIIIllIIlIIlIIlIIllIIlllIlIIlIllIIIlIIlllIIlllIlllIIllIllIIlIIlIlllIIlllIIllIIIllIlIIlIllIIIlIIIlllIIllIlIIlllIllIllIIllIIlIIIllIlllIIIllIlIIlIIlllIIllIlIlll, "");
        this.IIllIlIlIlIIIllIIllIIllIIlIIllIIlIIlllIIllIIlllIIIllIIllIIIlllIllIIIllIIIllIIlIIlIIlIIllIIlllIlIIlIllIIIlIIlllIIlllIlllIIllIllIIlIIlIlllIIlllIIllIIIllIlIIlIllIIIlIIIlllIIllIlIIlllIllIllIIllIIlIIIllIlllIIIllIlIIlIIlllIIllIlIlll = iIllIlIlIlIIIllIIllIIllIIlIIllIIlIIlllIIllIIlllIIIllIIllIIIlllIllIIIllIIIllIIlIIlIIlIIllIIlllIlIIlIllIIIlIIlllIIlllIlllIIllIllIIlIIlIlllIIlllIIllIIIllIlIIlIllIIIlIIIlllIIllIlIIlllIllIllIIllIIlIIIllIlllIIIllIlIIlIIlllIIllIlIlll;
    }
    
    @Override
    public int getTicks() {
        return this.l;
    }
    
    @Override
    public void setTicks(final int l) {
        this.l = l;
    }
    
    @Override
    public int getLastMovedTicks() {
        return this.n;
    }
    
    @Override
    public void setLastMovedTicks(final int n) {
        this.n = n;
    }
    
    @NotNull
    public final IllIIlIllIlIllIIIllIlIlllIllIllIlIllIIIllIIlllIIIllIllIIllIIllIIllIllIllIIlIIlIIlIIIlllIIIlllIIlIllIllIlllIIlIllIllIIIlIIIllIIlIlIIIlIIIlIIIlllIllIIlllIIIllIIlIllIlIIlIIIllIIIlllIIlIIIlllIIlIlIIIllIIIlIIlIIIlIIIllIllIl IllIIIlIllIIIlllIIllIIllIIllIIllIIlIIlllIIIlIllIIlllIlllIIlIlIIIllIIlIllIIIlllIIIlIIlIllIIIllIIllIlllIIlllIIlIlllIIllIIlIIllIIlllIlllIIIlIIllIIIlIIIlllIlllIllIlllIllIlllIIIllIIIlllIllIIllIllIllIlllIIllIIlllIIlllIlIlIIlIIIlIIlIIlIl() {
        return this.w;
    }
    
    @NotNull
    public final DirectDataProvider IlIIIlllIIIlIIllIIllIIlIIlIIllIIIlIllIIlllIIIlllIIlIllIIlIIlIllIIlIIllIIllIllIIlIIIllIIllIlIIlllIIIllIlIIllIlllIIIlIIllIIlIllIlllIIllIIlIllIllIlIllIIIllIIllIIIlllIIIlllIlllIlIllIlIlllIIllIllIlllIlllIIlIlIIllIlIIIlIIIlll() {
        final DirectDataProvider c = this.C;
        if (c != null) {
            return c;
        }
        Intrinsics.throwUninitializedPropertyAccessException("");
        return null;
    }
    
    public final void IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(@NotNull final DirectDataProvider c) {
        Intrinsics.checkNotNullParameter((Object)c, "");
        this.C = c;
    }
    
    @NotNull
    public final DirectDataProvider IllIIIlllIIllIIllIIIllIlIIIlIIIllIIIllIIIlIIIlIIIlIllIllIIIllIIIlIIIlllIlIIllIIIlIIllIllIlllIIllIIIllIlIIllIIIllIlIIlIlllIIIllIllIllIllIIlIIlllIIIllIIlllIIllIlIIIlllIllIIlIIlIIlIllIIIllIIlllIIllIIIllIIIllIIlIIllIlIIlIIlIIllIIllIll() {
        final DirectDataProvider d = this.D;
        if (d != null) {
            return d;
        }
        Intrinsics.throwUninitializedPropertyAccessException("");
        return null;
    }
    
    public final void IIllIllIIllIlIIlIlIIlllIllIIllIIllIlIIllIIIllIIIllIIllIIIlIllIIlIIlllIllIIlIllIllIIlIIlIlIIIlIllIIllIlllIllIIIllIIlIIlllIllIIlIIIlIIllIIllIIlIlIIlIllIIlllIlIIlllIIlIlIIlIIlIlllIIlIIIlIIllIllIlllIIlllIIIlIIllIIIlll(@NotNull final DirectDataProvider d) {
        Intrinsics.checkNotNullParameter((Object)d, "");
        this.D = d;
    }
    
    @NotNull
    public final IIllIIIlllIIlllIIllIlIIIlllIIIllIlIIllIIlIllIIlllIIIlIIIlIIIlIIlIIlIIIllIlllIllIIIllIIllIlIlllIllIIllIIlIIlllIlllIIlllIIIlllIIllIlllIlIIllIllIlIIllIIIlIIIlIIIllIIlIIlIIlllIllIlllIlIIllIIIllIIlIIlllIIlIIllIlllIIIlIlIIllIIIlllIIIlIll IIllIIlIIIlllIIIlIlIIllIIllIlIlllIlIIllIlllIIlIlllIIIlIIIlIIlIIllIlllIllIIIllIllIllIIlIIlIllIllIIIlllIIlllIIlllIIllIllIIlIIIlIIllIIlIIlllIIllIIllIllIIIlIllIIIllIlIllIIlllIIllIIllIIllIIIllIlIIIlllIIlIIllIlllIIllIIllIlllIIlIIll() {
        final IIllIIIlllIIlllIIllIlIIIlllIIIllIlIIllIIlIllIIlllIIIlIIIlIIIlIIlIIlIIIllIlllIllIIIllIIllIlIlllIllIIllIIlIIlllIlllIIlllIIIlllIIllIlllIlIIllIllIlIIllIIIlIIIlIIIllIIlIIlIIlllIllIlllIlIIllIIIllIIlIIlllIIlIIllIlllIIIlIlIIllIIIlllIIIlIll e = this.E;
        if (e != null) {
            return e;
        }
        Intrinsics.throwUninitializedPropertyAccessException("");
        return null;
    }
    
    public final void IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(@NotNull final IIllIIIlllIIlllIIllIlIIIlllIIIllIlIIllIIlIllIIlllIIIlIIIlIIIlIIlIIlIIIllIlllIllIIIllIIllIlIlllIllIIllIIlIIlllIlllIIlllIIIlllIIllIlllIlIIllIllIlIIllIIIlIIIlIIIllIIlIIlIIlllIllIlllIlIIllIIIllIIlIIlllIIlIIllIlllIIIlIlIIllIIIlllIIIlIll e) {
        Intrinsics.checkNotNullParameter((Object)e, "");
        this.E = e;
    }
    
    public final SeqLock IlIIlIIIllIlllIllIlIIIllIIIllIllIIlIlllIlllIIlIIlIIIlllIIIllIIlIlllIlllIIllIlllIIllIlIllIlIlIlllIllIllIIlIIllIIlIlllIIlIIIllIIIllIIIlIIllIIllIIIllIlllIllIIllIlllIIlllIIlIIIlIIlllIllIIllIIlIIlIIlIIIlIIIlllIIIllIIlllIIIllIllIIIlll() {
        return this.F;
    }
    
    public final void IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(@Nullable final IlllIIlIIIlllIIIlIIllIlIllIIIlIIIllIlllIIIllIIlIIllIIllIlllIIllIIllIIllIIllIIlllIIlllIllIIllIlllIlIllIIlllIllIllIIlllIIllIIIlllIIIllIIIllIlllIIlllIIlIlIlIlIllIIIlllIIlIllIIllIIlllIIlIIIllIIIllIIlIIlllIlIllIllIIlIIIllIllIIlIIllIll i, @NotNull final IDirectChunkCache directChunkCache) {
        Intrinsics.checkNotNullParameter((Object)directChunkCache, "");
        this.p = null;
        this.direction = null;
        this.w.IlIIllIlIIlIIIlIIIllIllIlIIllIIlIIllIlllIllIlIlllIIllIllIIIllIIllIIIlllIlIIlIlIlllIlIIIllIIIllIllIllIlIIIllIIIlIIIllIIllIIllIlIllIIlllIIllIIlIIIlllIllIIIllIllIIllIllIIIllIIllIllIIlllIIIllIlIlIIIllIIIlllIllIlIIlllIIIlllIl();
        this.k.clear();
        this.B.IIIlIIlIIlIIlIIlIlIIllIIlllIIIllIIIllIIllIIIllIIllIIlllIIIllIIllIIlIllIIlllIIlIIllIIIllIIIlllIlllIllIIlIllIIIlllIIIlIIllIllIIlIIlllIIllIIIllIIllIlIIllIllIIIlIIIllIIllIIllIIIlllIIIllIlIIIlIlIlIIIllIIlIIlllIIlllIIllIIllIllIIlllIIIllIIlllIIlll();
        this.i = i;
        this.IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(directChunkCache);
    }
    
    private final void IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(final IDirectChunkCache directChunkCache) {
        this.IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(new DirectDataProvider(directChunkCache, this.j));
        this.IIllIllIIllIlIIlIlIIlllIllIIllIIllIlIIllIIIllIIIllIIllIIIlIllIIlIIlllIllIIlIllIllIIlIIlIlIIIlIllIIllIlllIllIIIllIIlIIlllIllIIlIIIlIIllIIllIIlIlIIlIllIIlllIlIIlllIIlIlIIlIIlIlllIIlIIIlIIllIllIlllIIlllIIIlIIllIIIlll(new DirectDataProvider(directChunkCache, this.j));
        final IlllIIlIIIlllIIIlIIllIlIllIIIlIIIllIlllIIIllIIlIIllIIllIlllIIllIIllIIllIIllIIlllIIlllIllIIllIlllIlIllIIlllIllIllIIlllIIllIIIlllIIIllIIIllIlllIIlllIIlIlIlIlIllIIIlllIIlIllIIllIIlllIIlIIIllIIIllIIlIIlllIlIllIllIIlIIIllIllIIlIIllIll i = this.i;
        Intrinsics.checkNotNull((Object)i);
        this.IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(new IIIllIllIllIllIIllIIlIIllIIIllIlIIIlIIlIlllIlIllIIlllIIIlllIIIllIIlIIllIlllIlIIIllIlllIIllIIlllIIIllIIIlllIIllIlllIIIlIllIIIllIIlllIIlIllIllIllIlIIlllIIllIIlIIIlIIlIllIlIIIllIIIlllIllIlllIlIIlIIIlIIllIIlllIIllIIllIIIllIllIlllIIll(i));
        this.IIllIIlIIIlllIIIlIlIIllIIllIlIlllIlIIllIlllIIlIlllIIIlIIIlIIlIIllIlllIllIIIllIllIllIIlIIlIllIllIIIlllIIlllIIlllIIllIllIIlIIIlIIllIIlIIlllIIllIIllIllIIIlIllIIIllIlIllIIlllIIllIIllIIllIIIllIlIIIlllIIlIIllIlllIIllIIllIlllIIlIIll().IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(this.getPlayer(), this.IlIIIlllIIIlIIllIIllIIlIIlIIllIIIlIllIIlllIIIlllIIlIllIIlIIlIllIIlIIllIIllIllIIlIIIllIIllIlIIlllIIIllIlIIllIlllIIIlIIllIIlIllIlllIIllIIlIllIllIlIllIIIllIIllIIIlllIIIlllIlllIlIllIlIlllIIllIllIlllIlllIIlIlIIllIlIIIlIIIlll());
    }
    
    public final void IIlllIIIllIIIlIIlIIlIIllIIIllIIllIlllIlllIlllIIlllIIlllIIlllIlIIlIlIIIlIlIIIlIIIlIlllIIIlIIllIIIllIIlIIIllIllIIlllIIllIIlllIIllIIllIllIlllIIllIIllIllIIIlIIlllIIllIIllIIlIIllIlIIllIIllIlIIIllIIlllIIlIlIIllIIllIIlllIllIlIlIIlllIll() {
        this.w.IlIIllIlIIlIIIlIIIllIllIlIIllIIlIIllIlllIllIlIlllIIllIllIIIllIIllIIIlllIlIIlIlIlllIlIIIllIIIllIllIllIlIIIllIIIlIIIllIIllIIllIlIllIIlllIIllIIlIIIlllIllIIIllIllIIllIllIIIllIIllIllIIlllIIIllIlIlIIIllIIIlllIllIlIIlllIIIlllIl();
        this.k.clear();
        this.B.IIIlIIlIIlIIlIIlIlIIllIIlllIIIllIIIllIIllIIIllIIllIIlllIIIllIIllIIlIllIIlllIIlIIllIIIllIIIlllIlllIllIIlIllIIIlllIIIlIIllIllIIlIIlllIIllIIIllIIllIlIIllIllIIIlIIIllIIllIIllIIIlllIIIllIlIIIlIlIlIIIllIIlIIlllIIlllIIllIIllIllIIlllIIIllIIlllIIlll();
    }
    
    @Override
    public void queueRemoveBlock(@NotNull final BlockPosition blockPosition) {
        Intrinsics.checkNotNullParameter((Object)blockPosition, "");
        final IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll ilIIIlIIllIlllIlIIIlIIlllIIlIllIIlIIllIIIlllIIlllIIIlIIIlIIllIlllIIllIllIIIlllIlIllIIIlllIIlllIlIIllIIIllIlIIlIlllIllIIlIIllIllIlIlIIlIIIllIIllIIlIllIIllIIlIIlllIllIIIllIlllIIllIlllIlIIlIllIIlllIIIllIlllIIIllIIlIIllIlllIIll = this.k.IlIIIlIIllIlllIlIIIlIIlllIIlIllIIlIIllIIIlllIIlllIIIlIIIlIIllIlllIIllIllIIIlllIlIllIIIlllIIlllIlIIllIIIllIlIIlIlllIllIIlIIllIllIlIlIIlIIIllIIllIIlIllIIllIIlIIlllIllIIIllIlllIIllIlllIlIIlIllIIlllIIIllIlllIIIllIIlIIllIlllIIll(blockPosition.getX() >> 4, blockPosition.getZ() >> 4);
        if (ilIIIlIIllIlllIlIIIlIIlllIIlIllIIlIIllIIIlllIIlllIIIlIIIlIIllIlllIIllIllIIIlllIlIllIIIlllIIlllIlIIllIIIllIlIIlIlllIllIIlIIllIllIlIlIIlIIIllIIllIIlIllIIllIIlIIlllIllIIIllIlllIIllIlllIlIIlIllIIlllIIIllIlllIIIllIIlIIllIlllIIll == null) {
            return;
        }
        final RaytraceBlock iIllIllIIllIlIIlIlIIlllIllIIllIIllIlIIllIIIllIIIllIIllIIIlIllIIlIIlllIllIIlIllIllIIlIIlIlIIIlIllIIllIlllIllIIIllIIlIIlllIllIIlIIIlIIllIIllIIlIlIIlIllIIlllIlIIlllIIlIlIIlIIlIlllIIlIIIlIIllIllIlllIIlllIIIlIIllIIIlll = ilIIIlIIllIlllIlIIIlIIlllIIlIllIIlIIllIIIlllIIlllIIIlIIIlIIllIlllIIllIllIIIlllIlIllIIIlllIIlllIlIIllIIIllIlIIlIlllIllIIlIIllIllIlIlIIlIIIllIIllIIlIllIIllIIlIIlllIllIIIllIlllIIllIlllIlIIlIllIIlllIIIllIlllIIIllIIlIIllIlllIIll.IIllIllIIllIlIIlIlIIlllIllIIllIIllIlIIllIIIllIIIllIIllIIIlIllIIlIIlllIllIIlIllIllIIlIIlIlIIIlIllIIllIlllIllIIIllIIlIIlllIllIIlIIIlIIllIIllIIlIlIIlIllIIlllIlIIlllIIlIlIIlIIlIlllIIlIIIlIIllIllIlllIIlllIIIlIIllIIIlll(blockPosition);
        if (iIllIllIIllIlIIlIlIIlllIllIIllIIllIlIIllIIIllIIIllIIllIIIlIllIIlIIlllIllIIlIllIllIIlIIlIlIIIlIllIIllIlllIllIIIllIIlIIlllIllIIlIIIlIIllIIllIIlIlIIlIllIIlllIlIIlllIIlIlIIlIIlIlllIIlIIIlIIllIllIlllIIlllIIIlIIllIIIlll == null) {
            return;
        }
        iIllIllIIllIlIIlIlIIlllIllIIllIIllIlIIllIIIllIIIllIIllIIIlIllIIlIIlllIllIIlIllIllIIlIIlIlIIIlIllIIllIlllIllIIIllIIlIIlllIllIIlIIIlIIllIIllIIlIlIIlIllIIlllIlIIlllIIlIlIIlIIlIlllIIlIIIlIIllIllIlllIIlllIIIlIIllIIIlll.setPendingRemoval(true);
    }
    
    @NotNull
    @Override
    public RaytraceBlock addTraceBlock(@NotNull final DirectChunk directChunk, @NotNull final BlockPosition blockPosition) {
        Intrinsics.checkNotNullParameter((Object)directChunk, "");
        Intrinsics.checkNotNullParameter((Object)blockPosition, "");
        final IlllIIlIIIlllIIIlIIllIlIllIIIlIIIllIlllIIIllIIlIIllIIllIlllIIllIIllIIllIIllIIlllIIlllIllIIllIlllIlIllIIlllIllIllIIlllIIllIIIlllIIIllIIIllIlllIIlllIIlIlIlIlIllIIIlllIIlIllIIllIIlllIIlIIIllIIIllIIlIIlllIlIllIllIIlIIIllIllIIlIIllIll i = this.i;
        Intrinsics.checkNotNull((Object)i);
        return this.addTraceBlock(directChunk, blockPosition, i.IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(blockPosition.getY()));
    }
    
    @NotNull
    @Override
    public RaytraceBlock addTraceBlock(@NotNull final DirectChunk directChunk, @NotNull final BlockPosition blockPosition, @NotNull final WrappedBlockState wrappedBlockState) {
        Intrinsics.checkNotNullParameter((Object)directChunk, "");
        Intrinsics.checkNotNullParameter((Object)blockPosition, "");
        Intrinsics.checkNotNullParameter((Object)wrappedBlockState, "");
        final int x = directChunk.getX();
        final int z = directChunk.getZ();
        final IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll iIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll = this.k.IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(x, z, IIIllIIlIllIIIlllIIlIIlllIIIlllIIlIlIlIIIllIlIllIIIlllIllIIlIllIllIIIllIIllIllIllIllIllIIlIllIIIllIllIIIlIIlllIIllIlllIIIlIllIIlIlIIlllIIIllIlllIllIllIIllIIllIIIllIIIllIIllIllIllIIIllIIlllIIIlIIIllIllIIllIIIllIIIlllIIIllIIllIIllIIIl::IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll);
        try {
            return iIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll.IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(directChunk, blockPosition, directChunk.getBlockDataAt(blockPosition.getX(), blockPosition.getY(), blockPosition.getZ()), wrappedBlockState);
        }
        finally {
            this.B.IIlIIlIIllIlllIIlIlIllIIlIIllIIllIIIlIIlllIllIlIllIlIllIIllIIlIIllIIlIlIIIlllIIIlIIllIlIIllIIllIllIIllIllIlIlllIlIllIIIlllIIIlIIIlllIllIIIlIIIllIlllIIllIIlllIllIIlIIllIIlIIlIIIlIIllIIlIIllIIlIIlllIIlIIlllIIIllIIllIIl();
        }
    }
    
    @Override
    public void removeTraceBlock(@NotNull final DirectChunk directChunk, @NotNull final BlockPosition blockPosition) {
        Intrinsics.checkNotNullParameter((Object)directChunk, "");
        Intrinsics.checkNotNullParameter((Object)blockPosition, "");
        final int x = directChunk.getX();
        final int z = directChunk.getZ();
        final IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll ilIIIlIIllIlllIlIIIlIIlllIIlIllIIlIIllIIIlllIIlllIIIlIIIlIIllIlllIIllIllIIIlllIlIllIIIlllIIlllIlIIllIIIllIlIIlIlllIllIIlIIllIllIlIlIIlIIIllIIllIIlIllIIllIIlIIlllIllIIIllIlllIIllIlllIlIIlIllIIlllIIIllIlllIIIllIIlIIllIlllIIll = this.k.IlIIIlIIllIlllIlIIIlIIlllIIlIllIIlIIllIIIlllIIlllIIIlIIIlIIllIlllIIllIllIIIlllIlIllIIIlllIIlllIlIIllIIIllIlIIlIlllIllIIlIIllIllIlIlIIlIIIllIIllIIlIllIIllIIlIIlllIllIIIllIlllIIllIlllIlIIlIllIIlllIIIllIlllIIIllIIlIIllIlllIIll(x, z);
        if (ilIIIlIIllIlllIlIIIlIIlllIIlIllIIlIIllIIIlllIIlllIIIlIIIlIIllIlllIIllIllIIIlllIlIllIIIlllIIlllIlIIllIIIllIlIIlIlllIllIIlIIllIllIlIlIIlIIIllIIllIIlIllIIllIIlIIlllIllIIIllIlllIIllIlllIlIIlIllIIlllIIIllIlllIIIllIIlIIllIlllIIll == null) {
            return;
        }
        final IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll illIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll = ilIIIlIIllIlllIlIIIlIIlllIIlIllIIlIIllIIIlllIIlllIIIlIIIlIIllIlllIIllIllIIIlllIlIllIIIlllIIlllIlIIllIIIllIlIIlIlllIllIIlIIllIllIlIlIIlIIIllIIllIIlIllIIllIIlIIlllIllIIIllIlllIIllIlllIlIIlIllIIlllIIIllIlllIIIllIIlIIllIlllIIll;
        illIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll.IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(blockPosition);
        if (illIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll.isEmpty()) {
            this.IIllIllIlIlllIIlllIIlllIlIIIllIIlIIllIIllIIlIIIlllIIIlIIlllIIlllIIIlIlIIlIIllIlIIIllIIlIIllIIlIIlIIlllIIIllIlllIIlIIlllIllIllIIlllIlllIIlllIlIIIllIIllIlIlIIIllIlIIIlIIIllIIllIIlIlllIllIllIIIlIIIlIIllIIllIlIIIlIIIlIIllIIIllIIlll(x, z);
        }
    }
    
    @Nullable
    @Override
    public RaytraceBlock getTraceBlock(@NotNull final BlockPosition blockPosition) {
        Intrinsics.checkNotNullParameter((Object)blockPosition, "");
        final IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll ilIIIlIIllIlllIlIIIlIIlllIIlIllIIlIIllIIIlllIIlllIIIlIIIlIIllIlllIIllIllIIIlllIlIllIIIlllIIlllIlIIllIIIllIlIIlIlllIllIIlIIllIllIlIlIIlIIIllIIllIIlIllIIllIIlIIlllIllIIIllIlllIIllIlllIlIIlIllIIlllIIIllIlllIIIllIIlIIllIlllIIll = this.k.IlIIIlIIllIlllIlIIIlIIlllIIlIllIIlIIllIIIlllIIlllIIIlIIIlIIllIlllIIllIllIIIlllIlIllIIIlllIIlllIlIIllIIIllIlIIlIlllIllIIlIIllIllIlIlIIlIIIllIIllIIlIllIIllIIlIIlllIllIIIllIlllIIllIlllIlIIlIllIIlllIIIllIlllIIIllIIlIIllIlllIIll(blockPosition.getX() >> 4, blockPosition.getZ() >> 4);
        if (ilIIIlIIllIlllIlIIIlIIlllIIlIllIIlIIllIIIlllIIlllIIIlIIIlIIllIlllIIllIllIIIlllIlIllIIIlllIIlllIlIIllIIIllIlIIlIlllIllIIlIIllIllIlIlIIlIIIllIIllIIlIllIIllIIlIIlllIllIIIllIlllIIllIlllIlIIlIllIIlllIIIllIlllIIIllIIlIIllIlllIIll == null) {
            return null;
        }
        return ilIIIlIIllIlllIlIIIlIIlllIIlIllIIlIIllIIIlllIIlllIIIlIIIlIIllIlllIIllIllIIIlllIlIllIIIlllIIlllIlIIllIIIllIlIIlIlllIllIIlIIllIllIlIlIIlIIIllIIllIIlIllIIllIIlIIlllIllIIIllIlllIIllIlllIlIIlIllIIlllIIIllIlllIIIllIIlIIllIlllIIll.IIllIllIIllIlIIlIlIIlllIllIIllIIllIlIIllIIIllIIIllIIllIIIlIllIIlIIlllIllIIlIllIllIIlIIlIlIIIlIllIIllIlllIllIIIllIIlIIlllIllIIlIIIlIIllIIllIIlIlIIlIllIIlllIlIIlllIIlIlIIlIIlIlllIIlIIIlIIllIllIlllIIlllIIIlIIllIIIlll(blockPosition);
    }
    
    @Nullable
    @Override
    public RaytraceBlock getTraceBlockUnsafe(@NotNull final BlockPosition blockPosition) {
        Intrinsics.checkNotNullParameter((Object)blockPosition, "");
        final IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll ilIIIlIIllIlllIlIIIlIIlllIIlIllIIlIIllIIIlllIIlllIIIlIIIlIIllIlllIIllIllIIIlllIlIllIIIlllIIlllIlIIllIIIllIlIIlIlllIllIIlIIllIllIlIlIIlIIIllIIllIIlIllIIllIIlIIlllIllIIIllIlllIIllIlllIlIIlIllIIlllIIIllIlllIIIllIIlIIllIlllIIll = this.k.IlIIIlIIllIlllIlIIIlIIlllIIlIllIIlIIllIIIlllIIlllIIIlIIIlIIllIlllIIllIllIIIlllIlIllIIIlllIIlllIlIIllIIIllIlIIlIlllIllIIlIIllIllIlIlIIlIIIllIIllIIlIllIIllIIlIIlllIllIIIllIlllIIllIlllIlIIlIllIIlllIIIllIlllIIIllIIlIIllIlllIIll(blockPosition.getX() >> 4, blockPosition.getZ() >> 4);
        if (ilIIIlIIllIlllIlIIIlIIlllIIlIllIIlIIllIIIlllIIlllIIIlIIIlIIllIlllIIllIllIIIlllIlIllIIIlllIIlllIlIIllIIIllIlIIlIlllIllIIlIIllIllIlIlIIlIIIllIIllIIlIllIIllIIlIIlllIllIIIllIlllIIllIlllIlIIlIllIIlllIIIllIlllIIIllIIlIIllIlllIIll == null) {
            return null;
        }
        return ilIIIlIIllIlllIlIIIlIIlllIIlIllIIlIIllIIIlllIIlllIIIlIIIlIIllIlllIIllIllIIIlllIlIllIIIlllIIlllIlIIllIIIllIlIIlIlllIllIIlIIllIllIlIlIIlIIIllIIllIIlIllIIllIIlIIlllIllIIIllIlllIIllIlllIlIIlIllIIlllIIIllIlllIIIllIIlIIllIlllIIll.IlllIIlIIIlllIIIlIIllIlIllIIIlIIIllIlllIIIllIIlIIllIIllIlllIIllIIllIIllIIllIIlllIIlllIllIIllIlllIlIllIIlllIllIllIIlllIIllIIIlllIIIllIIIllIlllIIlllIIlIlIlIlIllIIIlllIIlIllIIllIIlllIIlIIIllIIIllIIlIIlllIlIllIllIIlIIIllIllIIlIIllIll(blockPosition);
    }
    
    public final void IllIlIIlllIIlIIlIIlIlIIIlllIIIllIlIIllIIlllIIIlIIllIIIllIIIllIIlIIlllIllIllIIllIlllIIIllIIllIIllIlllIlllIllIIlIIlIIllIIllIIIllIIIlIIIlIIllIIIllIIlIlllIllIIlllIIIllIIllIlIllIIIlllIllIIlIlIllIlIIIlIIIlIIlIIIlIIIlIllIIllIIIlllIl() {
        if (this.i == null) {
            return;
        }
        final Location p = this.p;
        Location add;
        if (p != null) {
            final Location location = p;
            this.getPlayer().getLocation(location);
            add = location.add(0.0, this.getPlayer().getEyeHeight(), 0.0);
        }
        else {
            add = null;
        }
        if (add == null) {
            final IIIllIIlIllIIIlllIIlIIlllIIIlllIIlIlIlIIIllIlIllIIIlllIllIIlIllIllIIIllIIllIllIllIllIllIIlIllIIIllIllIIIlIIlllIIllIlllIIIlIllIIlIlIIlllIIIllIlllIllIllIIllIIllIIIllIIIllIIllIllIllIIIllIIlllIIIlIIIllIllIIllIIIllIIIlllIIIllIIllIIllIIIl iiIllIIlIllIIIlllIIlIIlllIIIlllIIlIlIlIIIllIlIllIIIlllIllIIlIllIllIIIllIIllIllIllIllIllIIlIllIIIllIllIIIlIIlllIIllIlllIIIlIllIIlIlIIlllIIIllIlllIllIllIIllIIllIIIllIIIllIIllIllIllIIIllIIlllIIIlIIIllIllIIllIIIllIIIlllIIIllIIllIIllIIIl = this;
            iiIllIIlIllIIIlllIIlIIlllIIIlllIIlIlIlIIIllIlIllIIIlllIllIIlIllIllIIIllIIllIllIllIllIllIIlIllIIIllIllIIIlIIlllIIllIlllIIIlIllIIlIlIIlllIIIllIlllIllIllIIllIIllIIIllIIIllIIllIllIllIIIllIIlllIIIlIIIllIllIIllIIIllIIIlllIIIllIIllIIllIIIl.p = iiIllIIlIllIIIlllIIlIIlllIIIlllIIlIlIlIIIllIlIllIIIlllIllIIlIllIllIIIllIIllIllIllIllIllIIlIllIIIllIllIIIlIIlllIIllIlllIIIlIllIIlIlIIlllIIIllIlllIllIllIIllIIllIIIllIIIllIIllIllIllIIIllIIlllIIIlIIIllIllIIllIIIllIIIlllIIIllIIllIIllIIIl.getPlayer().getEyeLocation();
        }
        this.w.IlIIIllIIlllIIlIIIlIIIlIIlIIllIIllIIlllIIlllIIlllIllIIIlIIIlllIIllIIIlllIIlIIlllIIllIIIlIIIlIlIllIlllIIlIIIllIlIIllIIIllIlIIlIIIlIllIIlIIllIIllIllIllIIIlllIlIIllIlIIlIIlllIIIllIIllIIllIIlIlIIIllIIlIlIIllIIllIIlllIIllIIlIIlllIl();
        this.setTicks(this.getTicks() + 1);
        this.IIllIllIIIllIIlllIllIIllIlllIIIllIllIIIlllIllIIllIllIlIIlllIIlIIllIllIIllIIIlIIllIIllIllIlllIIIlllIIllIlIIllIIIllIlIllIIIllIlIIIlIIIlIIlllIlIIllIllIIlllIIIlIIIllIIlIIIlllIIlIIllIIllIIIlIIlIlIIIllIllIIllIIlIllIlIlllIIllIIIlIl();
    }
    
    public final void IllIIllIIllIIllIllIIlIllIIIlllIIIllIIIlllIllIlIIlllIllIllIIIlIIllIIlIIIllIIlIlIIlllIlIIlIIlllIlllIIIlIIllIIllIIlIIIlIllIllIIlllIIIlllIIllIIlIIIlllIIIlIllIllIIIllIIlIIllIIllIIlIIlllIIllIllIIllIlIIlllIllIIlllIIlIIIllIIllIIlIIIllIll() {
        if (this.i == null) {
            return;
        }
        this.B.IllIIllIlllIlllIllIIlllIIllIIlIIlllIIlIIlIIIllIlllIllIIIllIllIIlllIIlIIlIIlIIlllIIIlIlIIlllIlllIllIIllIIlllIIIlllIIlllIIlIllIlllIlllIIllIIIllIIllIIlIIIllIIIlllIlllIIlIIlIlllIIIllIIIllIIIlllIlIIIlllIIllIIllIIlllIIIlIIlIllIIIlIlIIllIlIll();
    }
    
    @Override
    public void clear() {
        this.k.forEach((Function1<? super IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll, Unit>)new Function1<IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll, Unit>() {
            public final void IlIIIlIIllIlllIlIIIlIIlllIIlIllIIlIIllIIIlllIIlllIIIlIIIlIIllIlllIIllIllIIIlllIlIllIIIlllIIlllIlIIllIIIllIlIIlIlllIllIIlIIllIllIlIlIIlIIIllIIllIIlIllIIllIIlIIlllIllIIIllIlllIIllIlllIlIIlIllIIlllIIIllIlllIIIllIIlIIllIlllIIll(@NotNull final IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll illIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll) {
                Intrinsics.checkNotNullParameter((Object)illIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll, "");
                IIIllIIlIllIIIlllIIlIIlllIIIlllIIlIlIlIIIllIlIllIIIlllIllIIlIllIllIIIllIIllIllIllIllIllIIlIllIIIllIllIIIlIIlllIIllIlllIIIlIllIIlIlIIlllIIIllIlllIllIllIIllIIllIIIllIIIllIIllIllIllIIIllIIlllIIIlIIIllIllIIllIIIllIIIlllIIIllIIllIIllIIIl.this.IIllIIIllIllIIIlllIIIlllIIllIIlllIlIIIllIllIlIIlIIIlllIIlIIllIIllIlIllIIllIIIllIIlIllIIlIIlIIllIIlIlIIllIllIIlIlIIlIIIllIIlIllIllIIlllIllIIlIlllIIllIlIlllIIlllIIIlIIlIIllIlllIlIIllIIIllIlIlIIlIIllIllIlIllIIIllIIll(illIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll);
            }
        });
        this.k.clear();
        this.w.IlIIllIlIIlIIIlIIIllIllIlIIllIIlIIllIlllIllIlIlllIIllIllIIIllIIllIIIlllIlIIlIlIlllIlIIIllIIIllIllIllIlIIIllIIIlIIIllIIllIIllIlIllIIlllIIllIIlIIIlllIllIIIllIllIIllIllIIIllIIllIllIIlllIIIllIlIlIIIllIIIlllIllIlIIlllIIIlllIl();
        this.B.IIIlIIlIIlIIlIIlIlIIllIIlllIIIllIIIllIIllIIIllIIllIIlllIIIllIIllIIlIllIIlllIIlIIllIIIllIIIlllIlllIllIIlIllIIIlllIIIlIIllIllIIlIIlllIIllIIIllIIllIlIIllIllIIIlIIIllIIllIIllIIIlllIIIllIlIIIlIlIlIIIllIIlIIlllIIlllIIllIIllIllIIlllIIIllIIlllIIlll();
        this.p = null;
    }
    
    @Override
    public void execute(@NotNull final Runnable e) {
        Intrinsics.checkNotNullParameter((Object)e, "");
        this.B.IIlIllIIlIIlllIlIlllIIIllIlIIlIIllIIllIlIIIlIllIIlllIIllIlllIllIIllIlIIllIIIlllIllIllIlIlllIIIlllIIIlIIIlIllIlllIIlIIlIIllIIlIIIllIllIIlIIlllIllIIlIllIIlIIllIlIIIlllIIIlIIlIlIlllIIIllIIlIlIllIIIlIIllIlllIIIllIIllIll().add(e);
    }
    
    @Nullable
    public final IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll IllIllIIlllIIIllIIlllIIlIIlllIIIllIIIlllIIllIIIlIIIlIIIllIIllIlIIIllIlIIlllIIIlllIlIIlIIIlIIlIIlllIlIIlIlllIIllIIllIIllIIIlIIIlIIllIIllIllIIlIIIlllIllIIlIIIllIIllIIlIllIIIlllIIIllIIIllIIllIIlIIIllIIlllIIlllIIIlllIIIllIllIIlIIIlllIIlIIIllIIllIll(final int n, final int n2) {
        return this.k.IlIIIlIIllIlllIlIIIlIIlllIIlIllIIlIIllIIIlllIIlllIIIlIIIlIIllIlllIIllIllIIIlllIlIllIIIlllIIlllIlIIllIIIllIlIIlIlllIllIIlIIllIllIlIlIIlIIIllIIllIIlIllIIllIIlIIlllIllIIIllIlllIIllIlllIlIIlIllIIlllIIIllIlllIIIllIIlIIllIlllIIll(n, n2);
    }
    
    public final void IIllIllIlIlllIIlllIIlllIlIIIllIIlIIllIIllIIlIIIlllIIIlIIlllIIlllIIIlIlIIlIIllIlIIIllIIlIIllIIlIIlIIlllIIIllIlllIIlIIlllIllIllIIlllIlllIIlllIlIIIllIIllIlIlIIIllIlIIIlIIIllIIllIIlIlllIllIllIIIlIIIlIIllIIllIlIIIlIIIlIIllIIIllIIlll(final int n, final int n2) {
        final IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll iIlIlllIIllIllIlllIIlIlIllIIlIIllIIllIIIllIlIIlllIIIllIIllIIlllIlllIIllIlllIllIIIlIIllIIllIIlllIIIllIIIlIIllIllIIlIIlIIlllIlIIlIllIlllIIlllIIIlIIIllIIllIIlIIIllIlllIIlIlIlllIIlIIllIIlIllIIlIIlllIIIlllIIlllIIlllIllIIlIllIIIlIIll = this.k.IIlIlllIIllIllIlllIIlIlIllIIlIIllIIllIIIllIlIIlllIIIllIIllIIlllIlllIIllIlllIllIIIlIIllIIllIIlllIIIllIIIlIIllIllIIlIIlIIlllIlIIlIllIlllIIlllIIIlIIIllIIllIIlIIIllIlllIIlIlIlllIIlIIllIIlIllIIlIIlllIIIlllIIlllIIlllIllIIlIllIIIlIIll(n, n2);
        if (iIlIlllIIllIllIlllIIlIlIllIIlIIllIIllIIIllIlIIlllIIIllIIllIIlllIlllIIllIlllIllIIIlIIllIIllIIlllIIIllIIIlIIllIllIIlIIlIIlllIlIIlIllIlllIIlllIIIlIIIllIIllIIlIIIllIlllIIlIlIlllIIlIIllIIlIllIIlIIlllIIIlllIIlllIIlllIllIIlIllIIIlIIll != null) {
            this.IIllIIIllIllIIIlllIIIlllIIllIIlllIlIIIllIllIlIIlIIIlllIIlIIllIIllIlIllIIllIIIllIIlIllIIlIIlIIllIIlIlIIllIllIIlIlIIlIIIllIIlIllIllIIlllIllIIlIlllIIllIlIlllIIlllIIIlIIlIIllIlllIlIIllIIIllIlIlIIlIIllIllIlIllIIIllIIll(iIlIlllIIllIllIlllIIlIlIllIIlIIllIIllIIIllIlIIlllIIIllIIllIIlllIlllIIllIlllIllIIIlIIllIIllIIlllIIIllIIIlIIllIllIIlIIlIIlllIlIIlIllIlllIIlllIIIlIIIllIIllIIlIIIllIlllIIlIlIlllIIlIIllIIlIllIIlIIlllIIIlllIIlllIIlllIllIIlIllIIIlIIll);
        }
    }
    
    private final void IIllIllIIIllIIlllIllIIllIlllIIIllIllIIIlllIllIIllIllIlIIlllIIlIIllIllIIllIIIlIIllIIllIllIlllIIIlllIIllIlIIllIIIllIlIllIIIllIlIIIlIIIlIIlllIlIIllIllIIlllIIIlIIIllIIlIIIlllIIlIIllIIllIIIlIIlIlIIIllIllIIllIIlIllIlIlllIIllIIIlIl() {
        final Location p = this.p;
        if (p == null) {
            return;
        }
        final Location location = p;
        final double n = NumberConversions.square(location.getX() - this.r) + NumberConversions.square(location.getY() - this.s) + NumberConversions.square(location.getZ() - this.t);
        if (this.q == location.getWorld() && n < 0.01 && this.u == location.getYaw() && this.v == location.getPitch()) {
            return;
        }
        this.q = location.getWorld();
        this.r = location.getX();
        this.s = location.getY();
        this.t = location.getZ();
        this.u = location.getYaw();
        this.v = location.getPitch();
        final Vector direction = location.getDirection();
        Intrinsics.checkNotNullExpressionValue((Object)direction, "");
        final Vector direction2 = direction;
        this.direction = direction2;
        this.IIllIllIIllIlIIlIlIIlllIllIIllIIllIlIIllIIIllIIIllIIllIIIlIllIIlIIlllIllIIlIllIllIIlIIlIlIIIlIllIIllIlllIllIIIllIIlIIlllIllIIlIIIlIIllIIllIIlIlIIlIllIIlllIlIIlllIIlIlIIlIIlIlllIIlIIIlIIllIllIlllIIlllIIIlIIllIIIlll(this.IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(location, direction2));
        this.setLastMovedTicks(this.getTicks());
    }
    
    private final Vision IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(final Location location, final Vector vector) {
        final IlllIIlIIIlllIIIlIIllIlIllIIIlIIIllIlllIIIllIIlIIllIIllIlllIIllIIllIIllIIllIIlllIIlllIllIIllIlllIlIllIIlllIllIllIIlllIIllIIIlllIIIllIIIllIlllIIlllIIlIlIlIlIllIIIlllIIlIllIIllIIlllIIlIIIllIIIllIIlIIlllIlIllIllIIlIIIllIllIIlIIllIll i = this.i;
        if (i != null && !i.IlIllIlllIIllIlllIIllIIlllIIIllIIllIIlIIIlllIllIIlIlIIllIIIllIlIlIIllIllIlllIlIIllIIIlllIIllIIIlIIIlIIlIIlIIIlllIllIlllIIIlIIllIIIlIllIIIllIIlIIlIIIlIlIlIIIlllIlllIIlIllIIlllIIllIllIIlllIIIllIlIIllIIllIIlIIlIIllIllIIIllIl()) {
            final Player player = this.getPlayer();
            final World world = location.getWorld();
            Intrinsics.checkNotNullExpressionValue((Object)world, "");
            final Vector vector2 = location.toVector();
            Intrinsics.checkNotNullExpressionValue((Object)vector2, "");
            return new Vision(player, world, new DirectionalVector(vector2, vector), null, null);
        }
        final Vector multiply = vector.clone().multiply(-1.0);
        Intrinsics.checkNotNullExpressionValue((Object)multiply, "");
        final Vector direction = multiply;
        if (Native.INSTANCE.isZoomSupported()) {
            final Player player2 = this.getPlayer();
            final World world2 = location.getWorld();
            Intrinsics.checkNotNullExpressionValue((Object)world2, "");
            final Vector vector3 = location.toVector();
            Intrinsics.checkNotNullExpressionValue((Object)vector3, "");
            final DirectionalVector directionalVector = new DirectionalVector(vector3, vector);
            final Native instance = Native.INSTANCE;
            final Location setDirection = location.clone().setDirection(direction);
            Intrinsics.checkNotNullExpressionValue((Object)setDirection, "");
            final IlllIIlIIIlllIIIlIIllIlIllIIIlIIIllIlllIIIllIIlIIllIIllIlllIIllIIllIIllIIllIIlllIIlllIllIIllIlllIlIllIIlllIllIllIIlllIIllIIIlllIIIllIIIllIlllIIlllIIlIlIlIlIllIIIlllIIlIllIIllIIlllIIlIIIllIIIllIIlIIlllIlIllIllIIlIIIllIllIIlIIllIll j = this.i;
            Intrinsics.checkNotNull((Object)j);
            final DirectionalVector directionalVector2 = new DirectionalVector(instance.zoom(setDirection, j.IIIlIIIllIIIllIlllIIllIlllIIlIIIllIIIlIIIllIIlllIIlllIIIlIlIllIlIIllIIlIllIIIlIlIIlllIIllIIIllIIllIIIlIIllIlIIllIIIllIlIIlllIIllIIIlIIlIIIllIlIIlllIIIllIllIlllIllIlIllIIIllIIlIllIIlIIlllIIllIlllIllIIlIllIIlIIllIlIllIIllIIl()), direction);
            final Native instance2 = Native.INSTANCE;
            final IlllIIlIIIlllIIIlIIllIlIllIIIlIIIllIlllIIIllIIlIIllIIllIlllIIllIIllIIllIIllIIlllIIlllIllIIllIlllIlIllIIlllIllIllIIlllIIllIIIlllIIIllIIIllIlllIIlllIIlIlIlIlIllIIIlllIIlIllIIllIIlllIIlIIIllIIIllIIlIIlllIlIllIllIIlIIIllIllIIlIIllIll k = this.i;
            Intrinsics.checkNotNull((Object)k);
            return new Vision(player2, world2, directionalVector, directionalVector2, new DirectionalVector(instance2.zoom(location, k.IIIlIIIllIIIllIlllIIllIlllIIlIIIllIIIlIIIllIIlllIIlllIIIlIlIllIlIIllIIlIllIIIlIlIIlllIIllIIIllIIllIIIlIIllIlIIllIIIllIlIIlllIIllIIIlIIlIIIllIlIIlllIIIllIllIlllIllIlIllIIIllIIlIllIIlIIlllIIllIlllIllIIlIllIIlIIllIlIllIIllIIl()), vector));
        }
        final Player player3 = this.getPlayer();
        final World world3 = location.getWorld();
        Intrinsics.checkNotNullExpressionValue((Object)world3, "");
        final Vector vector4 = location.toVector();
        Intrinsics.checkNotNullExpressionValue((Object)vector4, "");
        final DirectionalVector directionalVector3 = new DirectionalVector(vector4, vector);
        final DirectionalVector directionalVector4 = new DirectionalVector(this.IIllIllIIllIlIIlIlIIlllIllIIllIIllIlIIllIIIllIIIllIIllIIIlIllIIlIIlllIllIIlIllIllIIlIIlIlIIIlIllIIllIlllIllIIIllIIlIIlllIllIIlIIIlIIllIIllIIlIlIIlIllIIlllIlIIlllIIlIlIIlIIlIlllIIlIIIlIIllIllIlllIIlllIIIlIIllIIIlll(location, direction), direction);
        final Vector clone = vector.clone();
        Intrinsics.checkNotNullExpressionValue((Object)clone, "");
        return new Vision(player3, world3, directionalVector3, directionalVector4, new DirectionalVector(this.IIllIllIIllIlIIlIlIIlllIllIIllIIllIlIIllIIIllIIIllIIllIIIlIllIIlIIlllIllIIlIllIllIIlIIlIlIIIlIllIIllIlllIllIIIllIIlIIlllIllIIlIIIlIIllIIllIIlIlIIlIllIIlllIlIIlllIIlIlIIlIIlIlllIIlIIIlIIllIllIlllIIlllIIIlIIllIIIlll(location, clone), vector));
    }
    
    private final Vector IIllIllIIllIlIIlIlIIlllIllIIllIIllIlIIllIIIllIIIllIIllIIIlIllIIlIIlllIllIIlIllIllIIlIIlIlIIIlIllIIllIlllIllIIIllIIlIIlllIllIIlIIIlIIllIIllIIlIlIIlIllIIlllIlIIlllIIlIlIIlIIlIlllIIlIIIlIIllIllIlllIIlllIIIlIIllIIIlll(final Location location, final Vector vector) {
        final Vector vector2 = location.toVector();
        Intrinsics.checkNotNullExpressionValue((Object)vector2, "");
        final Vector vector3 = vector2;
        final double x = vector3.getX();
        final double y = vector3.getY();
        final double z = vector3.getZ();
        final double x2 = vector.getX();
        final double y2 = vector.getY();
        final double z2 = vector.getZ();
        final IlllIIlIIIlllIIIlIIllIlIllIIIlIIIllIlllIIIllIIlIIllIIllIlllIIllIIllIIllIIllIIlllIIlllIllIIllIlllIlIllIIlllIllIllIIlllIIllIIIlllIIIllIIIllIlllIIlllIIlIlIlIlIllIIIlllIIlIllIIllIIlllIIlIIIllIIIllIIlIIlllIlIllIllIIlIIIllIllIIlIIllIll i = this.i;
        double n2;
        final double n = n2 = ((i != null) ? i.IIIlIIIllIIIllIlllIIllIlllIIlIIIllIIIlIIIllIIlllIIlllIIIlIlIllIlIIllIIlIllIIIlIlIIlllIIllIIIllIIllIIIlIIllIlIIllIIIllIlIIlllIIllIIIlIIlIIIllIlIIlllIIIllIllIlllIllIlIllIIIllIIlIllIIlIIlllIIllIlllIllIIlIllIIlIIllIlIllIIllIIl() : 4.0);
        for (int j = 0; j < 8; ++j) {
            final float n3 = (float)((j & 0x1) * 2 - 1);
            final float n4 = (float)((j >> 1 & 0x1) * 2 - 1);
            final float n5 = (float)((j >> 2 & 0x1) * 2 - 1);
            final float n6 = n3 * 0.1f;
            final float n7 = n4 * 0.1f;
            final float n8 = n5 * 0.1f;
            final Vector add = vector3.clone().add(new Vector(n6, n7, n8));
            Intrinsics.checkNotNullExpressionValue((Object)add, "");
            final double distance = this.IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(add, new Vector(x - x2 * n + n6, y - y2 * n + n7, z - z2 * n + n8)).distance(vector3);
            if (distance < n2) {
                n2 = distance;
            }
        }
        final Vector subtract = location.toVector().subtract(vector.multiply(n2));
        Intrinsics.checkNotNullExpressionValue((Object)subtract, "");
        return subtract;
    }
    
    private final Vector IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(final Vector vector, final Vector vector2) {
        final Vector clone = vector.clone();
        Intrinsics.checkNotNullExpressionValue((Object)clone, "");
        final Vector vector3 = clone;
        final double distance = vector2.distance(vector);
        final Vector multiply = vector2.subtract(vector).normalize().multiply(0.1);
        Intrinsics.checkNotNullExpressionValue((Object)multiply, "");
        final Vector vector4 = multiply;
        double n = 0.0;
        int n2 = Integer.MIN_VALUE;
        int n3 = Integer.MIN_VALUE;
        int n4 = Integer.MIN_VALUE;
        while (n < distance) {
            n += 0.1;
            final double n5 = vector3.getX() + vector4.getX();
            final double n6 = vector3.getY() + vector4.getY();
            final double n7 = vector3.getZ() + vector4.getZ();
            final int n8 = (int)Math.floor(n5);
            final int n9 = (int)Math.floor(n6);
            final int n10 = (int)Math.floor(n7);
            if (!this.IllIIIlllIIllIIllIIIllIlIIIlIIIllIIIllIIIlIIIlIIIlIllIllIIIllIIIlIIIlllIlIIllIIIlIIllIllIlllIIllIIIllIlIIllIIIllIlIIlIlllIIIllIllIllIllIIlIIlllIIIllIIlllIIllIlIIIlllIllIIlIIlIIlIllIIIllIIlllIIllIIIllIIIllIIlIIllIlIIlIIlIIllIIllIll().prepareChunk(n8 >> 4, n10 >> 4)) {
                break;
            }
            if ((n2 != n8 || n3 != n9 || n4 != n10) && this.IllIIIlllIIllIIllIIIllIlIIIlIIIllIIIllIIIlIIIlIIIlIllIllIIIllIIIlIIIlllIlIIllIIIlIIllIllIlllIIllIIIllIlIIllIIIllIlIIlIlllIIIllIllIllIllIIlIIlllIIIllIIlllIIllIlIIIlllIllIIlIIlIIlIllIIIllIIlllIIllIIIllIIIllIIlIIllIlIIlIIlIIllIIllIll().isOpaqueFullCube(n8, n9, n10)) {
                break;
            }
            vector3.setX(n5);
            vector3.setY(n6);
            vector3.setZ(n7);
            n2 = n8;
            n3 = n9;
            n4 = n10;
        }
        this.IllIIIlllIIllIIllIIIllIlIIIlIIIllIIIllIIIlIIIlIIIlIllIllIIIllIIIlIIIlllIlIIllIIIlIIllIllIlllIIllIIIllIlIIllIIIllIlIIlIlllIIIllIllIllIllIIlIIlllIIIllIIlllIIllIlIIIlllIllIIlIIlIIlIllIIIllIIlllIIllIIIllIIIllIIlIIllIlIIlIIlIIllIIllIll().cleanup();
        return vector3;
    }
    
    private final void IlllIIlIIIlllIIIlIIllIlIllIIIlIIIllIlllIIIllIIlIIllIIllIlllIIllIIllIIllIIllIIlllIIlllIllIIllIlllIlIllIIlllIllIllIIlllIIllIIIlllIIIllIIIllIlllIIlllIIlIlIlIlIllIIIlllIIlIllIIllIIlllIIlIIIllIIIllIIlIIlllIlIllIllIIlIIIllIllIIlIIllIll(final IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll illIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll) {
        illIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll.IIllIIIllIllIIIlllIIIlllIIllIIlllIlIIIllIllIlIIlIIIlllIIlIIllIIllIlIllIIllIIIllIIlIllIIlIIlIIllIIlIlIIllIllIIlIlIIlIIIllIIlIllIllIIlllIllIIlIlllIIllIlIlllIIlllIIIlIIlIIllIlllIlIIllIIIllIlIlIIlIIllIllIlIllIIIllIIll().getView().addViewer(this);
    }
    
    private final void IIllIIIllIllIIIlllIIIlllIIllIIlllIlIIIllIllIlIIlIIIlllIIlIIllIIllIlIllIIllIIIllIIlIllIIlIIlIIllIIlIlIIllIllIIlIlIIlIIIllIIlIllIllIIlllIllIIlIlllIIllIlIlllIIlllIIIlIIlIIllIlllIlIIllIIIllIlIlIIlIIllIllIlIllIIIllIIll(final IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll illIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll) {
        illIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll.IIllIIIllIllIIIlllIIIlllIIllIIlllIlIIIllIllIlIIlIIIlllIIlIIllIIllIlIllIIllIIIllIIlIllIIlIIlIIllIIlIlIIllIllIIlIlIIlIIIllIIlIllIllIIlllIllIIlIlllIIllIlIlllIIlllIIIlIIlIIllIlllIlIIllIIIllIlIlIIlIIllIllIlIllIIIllIIll().getView().removeViewer(this);
    }
    
    public final void IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(final int n, final int n2, final boolean b) {
        synchronized (this.A) {
            final boolean b2 = b ? this.B.IIllIIlllIIllIIIlllIlIIlIlllIIllIIllIIlIIIlllIIlllIllIIlIIlIlIIlIIIllIIlllIIIllIIlllIIllIIlIlllIlllIIIlIllIlIIlIIIllIIIllIIllIllIIllIllIIIllIIllIIIlllIIlIIlllIlllIIlIIlIIlllIIllIllIIlllIllIIIlIllIIlllIIIllIIIlllIIlIIlIIIlIllIllIllIl().add(LongHash.INSTANCE.toLong(n, n2)) : this.B.IIllIIlllIIllIIIlllIlIIlIlllIIllIIllIIlIIIlllIIlllIllIIlIIlIlIIlIIIllIIlllIIIllIIlllIIllIIlIlllIlllIIIlIllIlIIlIIIllIIIllIIllIllIIllIllIIIllIIllIIIlllIIlIIlllIlllIIlIIlIIlllIIllIllIIlllIllIIIlIllIIlllIIIllIIIlllIIlIIlIIIlIllIllIllIl().remove(LongHash.INSTANCE.toLong(n, n2));
        }
    }
    
    @NotNull
    public final Set<Long> IIllIIllIllIIllIIIlllIIllIIllIIlIlllIlllIlllIIIllIIIllIIlIIlIIlllIIlIIIlIIIlllIllIIllIIllIIllIIlIIlllIlIIIllIIlIlIllIIlIIIllIllIllIIllIIIlIIIllIIllIIlIIIlllIIIlIlIIIlIllIIIlIIllIIlllIIIllIIIlllIIIllIlIIlllIlIIIlllIIlllIIllIIIllIIlllIIllIll() {
        synchronized (this.A) {
            final ImmutableSet copy = ImmutableSet.copyOf((Collection)this.B.IIllIIlllIIllIIIlllIlIIlIlllIIllIIllIIlIIIlllIIlllIllIIlIIlIlIIlIIIllIIlllIIIllIIlllIIllIIlIlllIlllIIIlIllIlIIlIIIllIIIllIIllIllIIllIllIIIllIIllIIIlllIIlIIlllIlllIIlIIlIIlllIIllIllIIlllIllIIIlIllIIlllIIIllIIIlllIIlIIlIIIlIllIllIllIl());
            Intrinsics.checkNotNullExpressionValue((Object)copy, "");
            return (Set<Long>)copy;
        }
    }
    
    @NotNull
    @Override
    public Set<CoordXZ> getLoadedChunkCoords() {
        final Iterable iterable;
        final Collection collection = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable = this.IIllIIllIllIIllIIIlllIIllIIllIIlIlllIlllIlllIIIllIIIllIIlIIlIIlllIIlIIIlIIIlllIllIIllIIllIIllIIlIIlllIlIIIllIIlIlIllIIlIIIllIllIllIIllIIIlIIIllIIllIIlIIIlllIIIlIlIIIlIllIIIlIIllIIlllIIIllIIIlllIIIllIlIIlllIlIIIlllIIlllIIllIIIllIIlllIIllIll(), 10));
        for (final Object next : iterable) {
            final Collection collection2 = collection;
            final long longValue = ((Number)next).longValue();
            collection2.add(new CoordXZ(LongHash.INSTANCE.msw(longValue), LongHash.INSTANCE.lsw(longValue)));
        }
        return CollectionsKt.toSet((Iterable)collection);
    }
    
    public final boolean IIlllIlIIllIlIIlIlllIlIlIIIlIIIlllIllIlIIllIlllIlIIllIllIIllIIlllIlllIIllIIllIIlIllIlIIIlIlllIIlIIIllIlllIllIllIllIIllIllIlIIlIllIIllIIIllIllIIlllIIlIllIIlIIIllIIIllIlIIIlIIlllIIIlIIllIIllIlIllIIllIIIllIlllIllIIl(final int n, final int n2) {
        synchronized (this.A) {
            return this.B.IIllIIlllIIllIIIlllIlIIlIlllIIllIIllIIlIIIlllIIlllIllIIlIIlIlIIlIIIllIIlllIIIllIIlllIIllIIlIlllIlllIIIlIllIlIIlIIIllIIIllIIllIllIIllIllIIIllIIllIIIlllIIlIIlllIlllIIlIIlIIlllIIllIllIIlllIllIIIlIllIIlllIIIllIIIlllIIlIIlIIIlIllIllIllIl().contains(LongHash.INSTANCE.toLong(n, n2));
        }
    }
    
    public final void IIIllIIllIIIlIllIIllIIIlIIllIlIIlIIlIIIlIIIllIlllIlIIllIIIllIIllIIIlllIIllIIIllIllIllIllIIllIIllIIlIIIlllIIIlllIllIlIIlllIIIllIIIllIIlIIlllIIlIllIllIlIIlllIIllIIlIIIllIIlIllIllIIIlIIlIIlllIlIllIlIllIIlllIIllIIlIIIlllIIlllIIIllIIl() {
        this.o.getAndIncrement();
    }
    
    public final int IllIIlIIllIIlIlllIlIlIlllIIIlIIlllIIIlIllIIIlIllIIIllIIlIIIllIIIllIIIlllIlllIllIllIllIIIlIIIllIlIlllIIllIlllIIIllIlIIlIIlIlIlIIllIllIlllIIllIlIIlIIIlllIIllIIIlIIIllIIIlllIIIllIlllIIlIlIIlIIllIIIlllIllIlIIIlIlIIlIIIlllIIIlll() {
        return this.o.get();
    }
    
    public final boolean IIllIllIlIlllIIlllIIlllIlIIIllIIlIIllIIllIIlIIIlllIIIlIIlllIIlllIIIlIlIIlIIllIlIIIllIIlIIllIIlIIlIIlllIIIllIlllIIlIIlllIllIllIIlllIlllIIlllIlIIIllIIllIlIlIIIllIlIIIlIIIllIIllIIlIlllIllIllIIIlIIIlIIllIIllIlIIIlIIIlIIllIIIllIIlll(final int n) {
        return this.o.get() == n;
    }
    
    private static final IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(final IIIllIIlIllIIIlllIIlIIlllIIIlllIIlIlIlIIIllIlIllIIIlllIllIIlIllIllIIIllIIllIllIllIllIllIIlIllIIIllIllIIIlIIlllIIllIlllIIIlIllIIlIlIIlllIIIllIlllIllIllIIllIIllIIIllIIIllIIllIllIllIIIllIIlllIIIlIIIllIllIIllIIIllIIIlllIIIllIIllIIllIIIl iiIllIIlIllIIIlllIIlIIlllIIIlllIIlIlIlIIIllIlIllIIIlllIllIIlIllIllIIIllIIllIllIllIllIllIIlIllIIIllIllIIIlIIlllIIllIlllIIIlIllIIlIlIIlllIIIllIlllIllIllIIllIIllIIIllIIIllIIllIllIllIIIllIIlllIIIlIIIllIllIIllIIIllIIIlllIIIllIIllIIllIIIl, final int n, final int n2, final DirectChunk directChunk, final Long n3) {
        Intrinsics.checkNotNullParameter((Object)iiIllIIlIllIIIlllIIlIIlllIIIlllIIlIlIlIIIllIlIllIIIlllIllIIlIllIllIIIllIIllIllIllIllIllIIlIllIIIllIllIIIlIIlllIIllIlllIIIlIllIIlIlIIlllIIIllIlllIllIllIIllIIllIIIllIIIllIIllIllIllIIIllIIlllIIIlIIIllIllIIllIIIllIIIlllIIIllIIllIIllIIIl, "");
        Intrinsics.checkNotNullParameter((Object)directChunk, "");
        Intrinsics.checkNotNullParameter((Object)n3, "");
        final IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll illIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll = new IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(iiIllIIlIllIIIlllIIlIIlllIIIlllIIlIlIlIIIllIlIllIIIlllIllIIlIllIllIIIllIIllIllIllIllIllIIlIllIIIllIllIIIlIIlllIIllIlllIIIlIllIIlIlIIlllIIIllIlllIllIllIIllIIllIIIllIIIllIIllIllIllIIIllIIlllIIIlIIIllIllIIllIIIllIIIlllIIIllIIllIIllIIIl, n, n2, directChunk);
        iiIllIIlIllIIIlllIIlIIlllIIIlllIIlIlIlIIIllIlIllIIIlllIllIIlIllIllIIIllIIllIllIllIllIllIIlIllIIIllIllIIIlIIlllIIllIlllIIIlIllIIlIlIIlllIIIllIlllIllIllIIllIIllIIIllIIIllIIllIllIllIIIllIIlllIIIlIIIllIllIIllIIIllIIIlllIIIllIIllIIllIIIl.IlllIIlIIIlllIIIlIIllIlIllIIIlIIIllIlllIIIllIIlIIllIIllIlllIIllIIllIIllIIllIIlllIIlllIllIIllIlllIlIllIIlllIllIllIIlllIIllIIIlllIIIllIIIllIlllIIlllIIlIlIlIlIllIIIlllIIlIllIIllIIlllIIlIIIllIIIllIIlIIlllIlIllIllIIlIIIllIllIIlIIllIll(illIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll);
        return illIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll;
    }
}
