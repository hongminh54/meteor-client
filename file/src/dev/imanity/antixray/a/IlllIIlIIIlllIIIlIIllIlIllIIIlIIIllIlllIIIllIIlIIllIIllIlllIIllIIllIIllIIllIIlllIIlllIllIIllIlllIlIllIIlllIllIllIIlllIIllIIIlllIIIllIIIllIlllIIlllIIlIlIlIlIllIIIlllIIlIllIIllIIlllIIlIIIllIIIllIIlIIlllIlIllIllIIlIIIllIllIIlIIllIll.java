// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.a;

import dev.imanity.antixray.api.IRaytracePlayer;
import java.util.ArrayList;
import io.fairyproject.bukkit.util.CoordXZ;
import java.util.function.Function;
import java.util.concurrent.Executor;
import kotlin.jvm.internal.Lambda;
import kotlin.Unit;
import java.util.concurrent.CompletionStage;
import dev.imanity.antixray.api.chunk.CachedChunkRegion;
import kotlin.jvm.functions.Function1;
import java.util.Arrays;
import dev.imanity.antixray.api.chunk.ChunkData;
import io.fairyproject.util.AsyncUtils;
import java.util.concurrent.CompletableFuture;
import io.fairyproject.data.MetaKey;
import dev.imanity.antixray.AntiXrayKeys;
import io.fairyproject.mc.data.MCMetadata;
import org.bukkit.entity.Player;
import org.bukkit.Chunk;
import dev.imanity.antixray.nms.Native;
import java.util.Collection;
import org.jetbrains.annotations.ApiStatus;
import org.bukkit.block.Block;
import dev.imanity.antixray.api.chunk.DirectChunk;
import io.fairyproject.mc.util.BlockPosition;
import io.fairyproject.mc.MCWorld;
import java.util.Iterator;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import java.util.Comparator;
import io.fairyproject.libs.xseries.XMaterial;
import java.util.LinkedHashMap;
import kotlin.collections.MapsKt;
import java.util.Map;
import dev.imanity.antixray.nms.WrappedBlockStateCache;
import kotlin.jvm.internal.Intrinsics;
import io.fairyproject.libs.packetevents.protocol.world.states.WrappedBlockState;
import java.util.concurrent.ExecutorService;
import dev.imanity.antixray.api.obfuscator.ObfuscateStrategy;
import java.util.List;
import dev.imanity.antixray.config.GlobalAntiXrayConfig;
import dev.imanity.antixray.config.AntiXrayConfig;
import dev.imanity.antixray.api.chunk.IDirectChunkCache;
import org.jetbrains.annotations.NotNull;
import org.bukkit.World;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.Metadata;

@Metadata(mv = { 1, 9, 0 }, k = 1, xi = 48, d1 = { "\u0000\u00d6\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u0005\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010\u0015\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u00002\u00020\u0001BK\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r\u0012\u0006\u0010\u000f\u001a\u00020\u0010\u0012\u0006\u0010\u0011\u001a\u00020\u0012¢\u0006\u0002\u0010\u0013J\u0006\u0010E\u001a\u00020\u001dJ\u0006\u0010F\u001a\u00020\u001dJ\u000e\u0010G\u001a\u00020\u00192\u0006\u0010H\u001a\u00020\u001dJ\u000e\u0010I\u001a\u00020\u00152\u0006\u0010J\u001a\u00020KJ\u0016\u0010L\u001a\b\u0012\u0004\u0012\u00020N0M2\u0006\u0010O\u001a\u00020PH\u0002J\u000e\u0010Q\u001a\u00020\u00152\u0006\u0010R\u001a\u00020SJ\u0010\u0010T\u001a\u00020U2\u0006\u0010V\u001a\u00020KH\u0002J\u001a\u0010W\u001a\u0006\u0012\u0002\b\u00030M2\u0006\u0010R\u001a\u00020S2\u0006\u0010X\u001a\u00020YJ\u001c\u0010Z\u001a\u0006\u0012\u0002\b\u00030M2\u0006\u0010R\u001a\u00020S2\u0006\u0010[\u001a\u00020\\H\u0002J\u0018\u0010]\u001a\u00020U2\u0006\u0010^\u001a\u00020_2\b\b\u0002\u0010`\u001a\u00020\u0015J\u000e\u0010a\u001a\u00020U2\u0006\u0010b\u001a\u00020cJ\u000e\u0010d\u001a\u00020U2\u0006\u0010b\u001a\u00020cJ\u0016\u0010e\u001a\u00020U2\u0006\u0010R\u001a\u00020S2\u0006\u0010b\u001a\u00020fJ\u001e\u0010g\u001a\u00020U2\u0006\u0010R\u001a\u00020S2\u0006\u0010h\u001a\u00020\u001d2\u0006\u0010i\u001a\u00020\u001dJ\u0006\u0010j\u001a\u00020UJ\u000e\u0010k\u001a\u00020U2\u0006\u0010J\u001a\u00020KJ\u000e\u0010k\u001a\u00020U2\u0006\u0010^\u001a\u00020_J\u000e\u0010l\u001a\u00020U2\u0006\u0010J\u001a\u00020KJ\u0006\u0010m\u001a\u00020UJ\u0010\u0010n\u001a\u00020U2\u0006\u0010J\u001a\u00020KH\u0007J \u0010n\u001a\u00020U2\u0006\u0010h\u001a\u00020\u001d2\u0006\u0010o\u001a\u00020\u001d2\u0006\u0010i\u001a\u00020\u001dH\u0007R\u0011\u0010\u0014\u001a\u00020\u00158F¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u001a\u001a\u00020\u00158F¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u0017R\u0011\u0010\u001c\u001a\u00020\u001d8F¢\u0006\u0006\u001a\u0004\b\u001e\u0010\u001fR\u000e\u0010 \u001a\u00020!X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010$\u001a\u00020\u00158F¢\u0006\u0006\u001a\u0004\b%\u0010\u0017R\u0011\u0010&\u001a\u00020\u001d8F¢\u0006\u0006\u001a\u0004\b'\u0010\u001fR\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010(\u001a\u00020)8F¢\u0006\u0006\u001a\u0004\b*\u0010+R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010,\u001a\u00020-8F¢\u0006\u0006\u001a\u0004\b.\u0010/R\u0011\u00100\u001a\u0002018F¢\u0006\u0006\u001a\u0004\b2\u00103R\u0011\u00104\u001a\u00020\u001d8F¢\u0006\u0006\u001a\u0004\b5\u0010\u001fR\u0016\u00106\u001a\b\u0012\u0004\u0012\u00020\u001907X\u0082\u0004¢\u0006\u0004\n\u0002\u00108R\u000e\u00109\u001a\u00020:X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010;\u001a\u00020\u00158F¢\u0006\u0006\u001a\u0004\b<\u0010\u0017R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010=\u001a\u00020\u00158F¢\u0006\u0006\u001a\u0004\b>\u0010\u0017R\u0011\u0010?\u001a\u0002018F¢\u0006\u0006\u001a\u0004\b@\u00103R\u0011\u0010A\u001a\u00020\u00158F¢\u0006\u0006\u001a\u0004\bB\u0010\u0017R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\bC\u0010D¨\u0006p" }, d2 = { "Ldev/imanity/antixray/core/RaytraceWorld;", "", "world", "Lorg/bukkit/World;", "chunkCache", "Ldev/imanity/antixray/api/chunk/IDirectChunkCache;", "config", "Ldev/imanity/antixray/config/AntiXrayConfig;", "globalAntiXrayConfig", "Ldev/imanity/antixray/config/GlobalAntiXrayConfig;", "server", "Ldev/imanity/antixray/core/RaytraceServer;", "strategies", "", "Ldev/imanity/antixray/api/obfuscator/ObfuscateStrategy;", "materialDataHandler", "Ldev/imanity/antixray/core/data/MaterialDataHandler;", "obfuscateThreadExecutor", "Ljava/util/concurrent/ExecutorService;", "(Lorg/bukkit/World;Ldev/imanity/antixray/api/chunk/IDirectChunkCache;Ldev/imanity/antixray/config/AntiXrayConfig;Ldev/imanity/antixray/config/GlobalAntiXrayConfig;Ldev/imanity/antixray/core/RaytraceServer;Ljava/util/List;Ldev/imanity/antixray/core/data/MaterialDataHandler;Ljava/util/concurrent/ExecutorService;)V", "aggressiveCheckBlock", "", "getAggressiveCheckBlock", "()Z", "airState", "Lio/fairyproject/libs/packetevents/protocol/world/states/WrappedBlockState;", "blockGrouping", "getBlockGrouping", "blockUpdateRadius", "", "getBlockUpdateRadius", "()I", "blockUpdater", "Ldev/imanity/antixray/core/block/BlockUpdater;", "getConfig", "()Ldev/imanity/antixray/config/AntiXrayConfig;", "hideUnderLava", "getHideUnderLava", "lavaRevealDistance", "getLavaRevealDistance", "mcWorld", "Lio/fairyproject/mc/MCWorld;", "getMcWorld", "()Lio/fairyproject/mc/MCWorld;", "raytraceDelay", "", "getRaytraceDelay", "()J", "raytraceRadius", "", "getRaytraceRadius", "()D", "raytraceRate", "getRaytraceRate", "replacementStatesDesc", "", "[Lio/fairyproject/libs/packetevents/protocol/world/states/WrappedBlockState;", "replacementThresholdsDesc", "", "revealStopRaytracing", "getRevealStopRaytracing", "supportThirdPerson", "getSupportThirdPerson", "thirdPersonMaxZoom", "getThirdPersonMaxZoom", "tracePlacedBlock", "getTracePlacedBlock", "getWorld", "()Lorg/bukkit/World;", "blockUpdateBacklog", "blockUpdateDynamicCap", "getReplacementBlock", "height", "isOnlyExposedViaLava", "blockPosition", "Lio/fairyproject/mc/util/BlockPosition;", "loadNearbyChunks", "Ljava/util/concurrent/CompletableFuture;", "Ldev/imanity/antixray/api/chunk/CachedChunkRegion;", "coords", "Lio/fairyproject/bukkit/util/CoordXZ;", "needsObfuscate", "player", "Lorg/bukkit/entity/Player;", "notifyRemovalNearby", "", "position", "obfuscate", "chunkPacket", "Ldev/imanity/antixray/core/protocol/ChunkPacket;", "obfuscateChunk", "chunkData", "Ldev/imanity/antixray/api/chunk/ChunkData;", "onBlockPlace", "block", "Lorg/bukkit/block/Block;", "clientSeesRealBlock", "onGlobalChunkLoaded", "chunk", "Lorg/bukkit/Chunk;", "onGlobalChunkUnloaded", "onPacketChunkLoaded", "Ldev/imanity/antixray/api/chunk/DirectChunk;", "onPacketChunkUnloaded", "x", "z", "onReload", "queueBlockChange", "queueUpdateBlock", "tick", "updateBlock", "y", "plugin" })
@SourceDebugExtension({ "SMAP\nRaytraceWorld.kt\nKotlin\n*S Kotlin\n*F\n+ 1 RaytraceWorld.kt\ndev/imanity/antixray/core/RaytraceWorld\n+ 2 Maps.kt\nkotlin/collections/MapsKt__MapsKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 4 ArraysJVM.kt\nkotlin/collections/ArraysKt__ArraysJVMKt\n*L\n1#1,304:1\n453#2:305\n403#2:306\n1238#3,4:307\n1054#3:311\n1855#3,2:312\n1747#3,3:314\n1855#3,2:317\n1855#3,2:319\n1855#3,2:321\n37#4,2:323\n*S KotlinDebug\n*F\n+ 1 RaytraceWorld.kt\ndev/imanity/antixray/core/RaytraceWorld\n*L\n83#1:305\n83#1:306\n83#1:307,4\n85#1:311\n96#1:312,2\n176#1:314,3\n199#1:317,2\n203#1:319,2\n207#1:321,2\n297#1:323,2\n*E\n" })
public final class IlllIIlIIIlllIIIlIIllIlIllIIIlIIIllIlllIIIllIIlIIllIIllIlllIIllIIllIIllIIllIIlllIIlllIllIIllIlllIlIllIIlllIllIllIIlllIIllIIIlllIIIllIIIllIlllIIlllIIlIlIlIlIllIIIlllIIlIllIIllIIlllIIlIIIllIIIllIIlIIlllIlIllIllIIlIIIllIllIIlIIllIll
{
    @NotNull
    private final World IllIlllIIllIIlIIlIlIIIllIIllIIlllIIIlIIlIIlllIIlIlIIIlIlIIIlIIllIlllIIIlllIIllIllIllIIlllIlIllIIlIlIIIlIlIlllIIIlIlllIIlIIllIIlIIllIllIIllIlllIIIllIllIlIIIllIIllIIllIIlIIIlIIlIlIIIllIIllIllIlllIIIllIIIllIIIlllIIlIIIlIlll;
    @NotNull
    private final IDirectChunkCache IIllIIlIlIIlllIIllIIllIIIlllIIIlllIllIIllIIlllIlIIllIIllIllIIlIIllIIlIlllIIIlIIlIllIIIllIIIlllIIlIIIlllIIIlllIIlIllIIIllIIlIlIllIlIIllIlIIIlllIIllIIlIIIlIIlIIlIlIlllIlIIIlIIlIIlllIllIllIllIIIlllIIllIIIllIIllIIlllIIlIllIlIIll;
    @NotNull
    private final AntiXrayConfig IIllIIIlllIIIllIIIllIIlIIllIIllIIIlllIIlIIIllIIllIIIlIIIlllIIlllIIlllIIllIIlIlllIlllIlIlIlllIllIIIllIIIllIlIlIllIlIllIIlllIllIlIIlIIIlIlIIllIlllIIlIllIIIlIlllIIllIllIIlIIlllIIlllIIIllIIllIlllIIlIlIIIllIIllIIIlIIIllIIllIIllIIIlllIIll;
    @NotNull
    private final GlobalAntiXrayConfig IIlIIlIllIIllIIllIIIlIIIlllIIIlIIlllIIIlIIllIIlIIlIIllIIIllIlllIIIlllIIlIlIIIllIIlllIIlIlllIIllIIlIIllIIlIllIllIIlIIllIIllIIllIIlllIIlIIllIlIIllIlIIllIlllIllIlllIIIlIIllIIlllIlIllIIlIllIIlIIllIIIllIllIIlIIIlIllIllIlllIll;
    @NotNull
    private final IIllIllIIllIlIIlIlIIlllIllIIllIIllIlIIllIIIllIIIllIIllIIIlIllIIlIIlllIllIIlIllIllIIlIIlIlIIIlIllIIllIlllIllIIIllIIlIIlllIllIIlIIIlIIllIIllIIlIlIIlIllIIlllIlIIlllIIlIlIIlIIlIlllIIlIIIlIIllIllIlllIIlllIIIlIIllIIIlll IIlllIIIllIIllIIllIlIIIlIIIllIIIlIIlllIIIlIlllIIlIIllIlIlIlIlIIIlllIIIlllIIIlllIIIlllIIlIllIIllIlllIllIIlllIlllIlllIIlIIlIIIllIllIIIlllIIllIIlIlllIIllIIIllIIlIIllIIIlIllIIIllIIllIIIlIIllIIlllIIlllIIIllIllIIIllIIllIIllIlIllIllIIIlIIllIIl;
    @NotNull
    private final List<ObfuscateStrategy> IlIllIIlllIIlllIIlllIIIllIIIllIIlllIlIIllIllIIIllIlIIlllIlIIlIIllIllIlIIlIllIIIllIIllIIIllIllIlIIIlllIIIlIIlllIIlIIIlllIIIllIIIllIIlllIIllIIlIIIllIIllIIlllIIllIlIllIIllIIllIllIIIllIIllIIlIIIlIIlllIIIlIIlllIIlIIIllIlIlllIlIllIIlIIIll;
    @NotNull
    private final IIIllIllIIIllIIllIlIllIlllIIlIlIIIllIlIIIlIIIlIlIIIlIIIllIlIllIlIllIIllIIlllIlIIlIIllIlIIIllIIllIIlIllIIlllIlllIllIlIIlllIIlllIIllIlIIlllIIIllIIlIllIIlIIllIIllIIIlIlIIIllIIlllIllIIlllIIlllIIIllIIIlIIIllIlIIIlIIlIIIllIIIll IIlIllIlIIllIllIIlIIIllIIlIIllIIlIllIlIllIllIIlIIlIlllIllIllIlllIIIlIIlIIIlIIlllIIlllIIlIIlIIIllIIlllIllIIIlIllIIlllIlIIlllIIlllIllIIlIIlIllIlllIllIllIIlIllIIIlIIlllIIllIIllIllIIlllIlIIlIllIIllIlIIllIIllIIIlllIll;
    @NotNull
    private final ExecutorService IllIllIIIlIllIllIIllIIIlllIlIllIIlIIlIlIIllIllIIIlllIllIllIIIllIlIlIIIlIIIllIIllIIlIllIIlIIlllIIlIIlllIllIIIllIIllIIIlIllIlllIIllIIIllIlIlIIIlIlIIllIIllIIlllIIIlIIllIllIllIIIlllIIlllIllIIlIIlIIlIIllIIllIIlIIIlllIIlIIlll;
    @NotNull
    private final IllIllIIlllIIIllIIlllIIlIIlllIIIllIIIlllIIllIIIlIIIlIIIllIIllIlIIIllIlIIlllIIIlllIlIIlIIIlIIlIIlllIlIIlIlllIIllIIllIIllIIIlIIIlIIllIIllIllIIlIIIlllIllIIlIIIllIIllIIlIllIIIlllIIIllIIIllIIllIIlIIIllIIlllIIlllIIIlllIIIllIllIIlIIIlllIIlIIIllIIllIll IlllIllIIllIllIIIlllIllIIIlllIIlIIllIIlIllIIlllIIllIIIlIlIlllIIlIlIllIIIllIlllIlllIllIIIllIIIlIIIlIIIlIIIlIIllIllIIIllIllIlIIIlllIIllIIllIIllIIlllIIIlIIIlIIIlIllIIIlllIIlllIlllIIlIIIlIIlllIlIIllIIllIIIllIIlllIlIIIlIllIIlllIIIlllIIllIIIlll;
    @NotNull
    private final int[] IIIllIllIIIllIIllIlIllIlllIIlIlIIIllIlIIIlIIIlIlIIIlIIIllIlIllIlIllIIllIIlllIlIIlIIllIlIIIllIIllIIlIllIIlllIlllIllIlIIlllIIlllIIllIlIIlllIIIllIIlIllIIlIIllIIllIIIlIlIIIllIIlllIllIIlllIIlllIIIllIIIlIIIllIlIIIlIIlIIIllIIIll;
    @NotNull
    private final WrappedBlockState[] IlIIlllIlllIlllIIIlIIlIIlIIllIIIllIlllIIllIIlIIllIllIIllIIIllIIlIlIIllIIllIllIIIlIlIIIllIIlllIllIIlIllIIlIllIlllIIllIlllIllIlllIllIIIllIllIIlIIIlIIlllIIIlIllIllIllIlllIllIIlllIlIIIlllIIlllIllIlIIlIllIIIlllIIIllIIIllIIlIl;
    @NotNull
    private final WrappedBlockState IIllIIlllIllIIllIIIlllIIlllIIllIIllIIlIIllIIllIllIIlllIlllIIlIIllIIllIlllIllIIllIIIlllIllIlllIIlIIlllIllIIIllIlllIIIlIllIIIllIIlIIllIIlIIIllIIIllIIIllIIIllIIIllIlIIllIIIlIIllIIllIllIIlllIIlllIIIllIIlIlIIllIlIIllIIlIIlIIlllIllIIlllIIllIIIl;
    
    public IlllIIlIIIlllIIIlIIllIlIllIIIlIIIllIlllIIIllIIlIIllIIllIlllIIllIIllIIllIIllIIlllIIlllIllIIllIlllIlIllIIlllIllIllIIlllIIllIIIlllIIIllIIIllIlllIIlllIIlIlIlIlIllIIIlllIIlIllIIllIIlllIIlIIIllIIIllIIlIIlllIlIllIllIIlIIIllIllIIlIIllIll(@NotNull final World illIlllIIllIIlIIlIlIIIllIIllIIlllIIIlIIlIIlllIIlIlIIIlIlIIIlIIllIlllIIIlllIIllIllIllIIlllIlIllIIlIlIIIlIlIlllIIIlIlllIIlIIllIIlIIllIllIIllIlllIIIllIllIlIIIllIIllIIllIIlIIIlIIlIlIIIllIIllIllIlllIIIllIIIllIIIlllIIlIIIlIlll, @NotNull final IDirectChunkCache iIllIIlIlIIlllIIllIIllIIIlllIIIlllIllIIllIIlllIlIIllIIllIllIIlIIllIIlIlllIIIlIIlIllIIIllIIIlllIIlIIIlllIIIlllIIlIllIIIllIIlIlIllIlIIllIlIIIlllIIllIIlIIIlIIlIIlIlIlllIlIIIlIIlIIlllIllIllIllIIIlllIIllIIIllIIllIIlllIIlIllIlIIll, @NotNull final AntiXrayConfig iIllIIIlllIIIllIIIllIIlIIllIIllIIIlllIIlIIIllIIllIIIlIIIlllIIlllIIlllIIllIIlIlllIlllIlIlIlllIllIIIllIIIllIlIlIllIlIllIIlllIllIlIIlIIIlIlIIllIlllIIlIllIIIlIlllIIllIllIIlIIlllIIlllIIIllIIllIlllIIlIlIIIllIIllIIIlIIIllIIllIIllIIIlllIIll, @NotNull final GlobalAntiXrayConfig iIlIIlIllIIllIIllIIIlIIIlllIIIlIIlllIIIlIIllIIlIIlIIllIIIllIlllIIIlllIIlIlIIIllIIlllIIlIlllIIllIIlIIllIIlIllIllIIlIIllIIllIIllIIlllIIlIIllIlIIllIlIIllIlllIllIlllIIIlIIllIIlllIlIllIIlIllIIlIIllIIIllIllIIlIIIlIllIllIlllIll, @NotNull final IIllIllIIllIlIIlIlIIlllIllIIllIIllIlIIllIIIllIIIllIIllIIIlIllIIlIIlllIllIIlIllIllIIlIIlIlIIIlIllIIllIlllIllIIIllIIlIIlllIllIIlIIIlIIllIIllIIlIlIIlIllIIlllIlIIlllIIlIlIIlIIlIlllIIlIIIlIIllIllIlllIIlllIIIlIIllIIIlll iIlllIIIllIIllIIllIlIIIlIIIllIIIlIIlllIIIlIlllIIlIIllIlIlIlIlIIIlllIIIlllIIIlllIIIlllIIlIllIIllIlllIllIIlllIlllIlllIIlIIlIIIllIllIIIlllIIllIIlIlllIIllIIIllIIlIIllIIIlIllIIIllIIllIIIlIIllIIlllIIlllIIIllIllIIIllIIllIIllIlIllIllIIIlIIllIIl, @NotNull final List<? extends ObfuscateStrategy> ilIllIIlllIIlllIIlllIIIllIIIllIIlllIlIIllIllIIIllIlIIlllIlIIlIIllIllIlIIlIllIIIllIIllIIIllIllIlIIIlllIIIlIIlllIIlIIIlllIIIllIIIllIIlllIIllIIlIIIllIIllIIlllIIllIlIllIIllIIllIllIIIllIIllIIlIIIlIIlllIIIlIIlllIIlIIIllIlIlllIlIllIIlIIIll, @NotNull final IIIllIllIIIllIIllIlIllIlllIIlIlIIIllIlIIIlIIIlIlIIIlIIIllIlIllIlIllIIllIIlllIlIIlIIllIlIIIllIIllIIlIllIIlllIlllIllIlIIlllIIlllIIllIlIIlllIIIllIIlIllIIlIIllIIllIIIlIlIIIllIIlllIllIIlllIIlllIIIllIIIlIIIllIlIIIlIIlIIIllIIIll iIlIllIlIIllIllIIlIIIllIIlIIllIIlIllIlIllIllIIlIIlIlllIllIllIlllIIIlIIlIIIlIIlllIIlllIIlIIlIIIllIIlllIllIIIlIllIIlllIlIIlllIIlllIllIIlIIlIllIlllIllIllIIlIllIIIlIIlllIIllIIllIllIIlllIlIIlIllIIllIlIIllIIllIIIlllIll, @NotNull final ExecutorService illIllIIIlIllIllIIllIIIlllIlIllIIlIIlIlIIllIllIIIlllIllIllIIIllIlIlIIIlIIIllIIllIIlIllIIlIIlllIIlIIlllIllIIIllIIllIIIlIllIlllIIllIIIllIlIlIIIlIlIIllIIllIIlllIIIlIIllIllIllIIIlllIIlllIllIIlIIlIIlIIllIIllIIlIIIlllIIlIIlll) {
        Intrinsics.checkNotNullParameter((Object)illIlllIIllIIlIIlIlIIIllIIllIIlllIIIlIIlIIlllIIlIlIIIlIlIIIlIIllIlllIIIlllIIllIllIllIIlllIlIllIIlIlIIIlIlIlllIIIlIlllIIlIIllIIlIIllIllIIllIlllIIIllIllIlIIIllIIllIIllIIlIIIlIIlIlIIIllIIllIllIlllIIIllIIIllIIIlllIIlIIIlIlll, "");
        Intrinsics.checkNotNullParameter((Object)iIllIIlIlIIlllIIllIIllIIIlllIIIlllIllIIllIIlllIlIIllIIllIllIIlIIllIIlIlllIIIlIIlIllIIIllIIIlllIIlIIIlllIIIlllIIlIllIIIllIIlIlIllIlIIllIlIIIlllIIllIIlIIIlIIlIIlIlIlllIlIIIlIIlIIlllIllIllIllIIIlllIIllIIIllIIllIIlllIIlIllIlIIll, "");
        Intrinsics.checkNotNullParameter((Object)iIllIIIlllIIIllIIIllIIlIIllIIllIIIlllIIlIIIllIIllIIIlIIIlllIIlllIIlllIIllIIlIlllIlllIlIlIlllIllIIIllIIIllIlIlIllIlIllIIlllIllIlIIlIIIlIlIIllIlllIIlIllIIIlIlllIIllIllIIlIIlllIIlllIIIllIIllIlllIIlIlIIIllIIllIIIlIIIllIIllIIllIIIlllIIll, "");
        Intrinsics.checkNotNullParameter((Object)iIlIIlIllIIllIIllIIIlIIIlllIIIlIIlllIIIlIIllIIlIIlIIllIIIllIlllIIIlllIIlIlIIIllIIlllIIlIlllIIllIIlIIllIIlIllIllIIlIIllIIllIIllIIlllIIlIIllIlIIllIlIIllIlllIllIlllIIIlIIllIIlllIlIllIIlIllIIlIIllIIIllIllIIlIIIlIllIllIlllIll, "");
        Intrinsics.checkNotNullParameter((Object)iIlllIIIllIIllIIllIlIIIlIIIllIIIlIIlllIIIlIlllIIlIIllIlIlIlIlIIIlllIIIlllIIIlllIIIlllIIlIllIIllIlllIllIIlllIlllIlllIIlIIlIIIllIllIIIlllIIllIIlIlllIIllIIIllIIlIIllIIIlIllIIIllIIllIIIlIIllIIlllIIlllIIIllIllIIIllIIllIIllIlIllIllIIIlIIllIIl, "");
        Intrinsics.checkNotNullParameter((Object)ilIllIIlllIIlllIIlllIIIllIIIllIIlllIlIIllIllIIIllIlIIlllIlIIlIIllIllIlIIlIllIIIllIIllIIIllIllIlIIIlllIIIlIIlllIIlIIIlllIIIllIIIllIIlllIIllIIlIIIllIIllIIlllIIllIlIllIIllIIllIllIIIllIIllIIlIIIlIIlllIIIlIIlllIIlIIIllIlIlllIlIllIIlIIIll, "");
        Intrinsics.checkNotNullParameter((Object)iIlIllIlIIllIllIIlIIIllIIlIIllIIlIllIlIllIllIIlIIlIlllIllIllIlllIIIlIIlIIIlIIlllIIlllIIlIIlIIIllIIlllIllIIIlIllIIlllIlIIlllIIlllIllIIlIIlIllIlllIllIllIIlIllIIIlIIlllIIllIIllIllIIlllIlIIlIllIIllIlIIllIIllIIIlllIll, "");
        Intrinsics.checkNotNullParameter((Object)illIllIIIlIllIllIIllIIIlllIlIllIIlIIlIlIIllIllIIIlllIllIllIIIllIlIlIIIlIIIllIIllIIlIllIIlIIlllIIlIIlllIllIIIllIIllIIIlIllIlllIIllIIIllIlIlIIIlIlIIllIIllIIlllIIIlIIllIllIllIIIlllIIlllIllIIlIIlIIlIIllIIllIIlIIIlllIIlIIlll, "");
        this.IllIlllIIllIIlIIlIlIIIllIIllIIlllIIIlIIlIIlllIIlIlIIIlIlIIIlIIllIlllIIIlllIIllIllIllIIlllIlIllIIlIlIIIlIlIlllIIIlIlllIIlIIllIIlIIllIllIIllIlllIIIllIllIlIIIllIIllIIllIIlIIIlIIlIlIIIllIIllIllIlllIIIllIIIllIIIlllIIlIIIlIlll = illIlllIIllIIlIIlIlIIIllIIllIIlllIIIlIIlIIlllIIlIlIIIlIlIIIlIIllIlllIIIlllIIllIllIllIIlllIlIllIIlIlIIIlIlIlllIIIlIlllIIlIIllIIlIIllIllIIllIlllIIIllIllIlIIIllIIllIIllIIlIIIlIIlIlIIIllIIllIllIlllIIIllIIIllIIIlllIIlIIIlIlll;
        this.IIllIIlIlIIlllIIllIIllIIIlllIIIlllIllIIllIIlllIlIIllIIllIllIIlIIllIIlIlllIIIlIIlIllIIIllIIIlllIIlIIIlllIIIlllIIlIllIIIllIIlIlIllIlIIllIlIIIlllIIllIIlIIIlIIlIIlIlIlllIlIIIlIIlIIlllIllIllIllIIIlllIIllIIIllIIllIIlllIIlIllIlIIll = iIllIIlIlIIlllIIllIIllIIIlllIIIlllIllIIllIIlllIlIIllIIllIllIIlIIllIIlIlllIIIlIIlIllIIIllIIIlllIIlIIIlllIIIlllIIlIllIIIllIIlIlIllIlIIllIlIIIlllIIllIIlIIIlIIlIIlIlIlllIlIIIlIIlIIlllIllIllIllIIIlllIIllIIIllIIllIIlllIIlIllIlIIll;
        this.IIllIIIlllIIIllIIIllIIlIIllIIllIIIlllIIlIIIllIIllIIIlIIIlllIIlllIIlllIIllIIlIlllIlllIlIlIlllIllIIIllIIIllIlIlIllIlIllIIlllIllIlIIlIIIlIlIIllIlllIIlIllIIIlIlllIIllIllIIlIIlllIIlllIIIllIIllIlllIIlIlIIIllIIllIIIlIIIllIIllIIllIIIlllIIll = iIllIIIlllIIIllIIIllIIlIIllIIllIIIlllIIlIIIllIIllIIIlIIIlllIIlllIIlllIIllIIlIlllIlllIlIlIlllIllIIIllIIIllIlIlIllIlIllIIlllIllIlIIlIIIlIlIIllIlllIIlIllIIIlIlllIIllIllIIlIIlllIIlllIIIllIIllIlllIIlIlIIIllIIllIIIlIIIllIIllIIllIIIlllIIll;
        this.IIlIIlIllIIllIIllIIIlIIIlllIIIlIIlllIIIlIIllIIlIIlIIllIIIllIlllIIIlllIIlIlIIIllIIlllIIlIlllIIllIIlIIllIIlIllIllIIlIIllIIllIIllIIlllIIlIIllIlIIllIlIIllIlllIllIlllIIIlIIllIIlllIlIllIIlIllIIlIIllIIIllIllIIlIIIlIllIllIlllIll = iIlIIlIllIIllIIllIIIlIIIlllIIIlIIlllIIIlIIllIIlIIlIIllIIIllIlllIIIlllIIlIlIIIllIIlllIIlIlllIIllIIlIIllIIlIllIllIIlIIllIIllIIllIIlllIIlIIllIlIIllIlIIllIlllIllIlllIIIlIIllIIlllIlIllIIlIllIIlIIllIIIllIllIIlIIIlIllIllIlllIll;
        this.IIlllIIIllIIllIIllIlIIIlIIIllIIIlIIlllIIIlIlllIIlIIllIlIlIlIlIIIlllIIIlllIIIlllIIIlllIIlIllIIllIlllIllIIlllIlllIlllIIlIIlIIIllIllIIIlllIIllIIlIlllIIllIIIllIIlIIllIIIlIllIIIllIIllIIIlIIllIIlllIIlllIIIllIllIIIllIIllIIllIlIllIllIIIlIIllIIl = iIlllIIIllIIllIIllIlIIIlIIIllIIIlIIlllIIIlIlllIIlIIllIlIlIlIlIIIlllIIIlllIIIlllIIIlllIIlIllIIllIlllIllIIlllIlllIlllIIlIIlIIIllIllIIIlllIIllIIlIlllIIllIIIllIIlIIllIIIlIllIIIllIIllIIIlIIllIIlllIIlllIIIllIllIIIllIIllIIllIlIllIllIIIlIIllIIl;
        this.IlIllIIlllIIlllIIlllIIIllIIIllIIlllIlIIllIllIIIllIlIIlllIlIIlIIllIllIlIIlIllIIIllIIllIIIllIllIlIIIlllIIIlIIlllIIlIIIlllIIIllIIIllIIlllIIllIIlIIIllIIllIIlllIIllIlIllIIllIIllIllIIIllIIllIIlIIIlIIlllIIIlIIlllIIlIIIllIlIlllIlIllIIlIIIll = (List<ObfuscateStrategy>)ilIllIIlllIIlllIIlllIIIllIIIllIIlllIlIIllIllIIIllIlIIlllIlIIlIIllIllIlIIlIllIIIllIIllIIIllIllIlIIIlllIIIlIIlllIIlIIIlllIIIllIIIllIIlllIIllIIlIIIllIIllIIlllIIllIlIllIIllIIllIllIIIllIIllIIlIIIlIIlllIIIlIIlllIIlIIIllIlIlllIlIllIIlIIIll;
        this.IIlIllIlIIllIllIIlIIIllIIlIIllIIlIllIlIllIllIIlIIlIlllIllIllIlllIIIlIIlIIIlIIlllIIlllIIlIIlIIIllIIlllIllIIIlIllIIlllIlIIlllIIlllIllIIlIIlIllIlllIllIllIIlIllIIIlIIlllIIllIIllIllIIlllIlIIlIllIIllIlIIllIIllIIIlllIll = iIlIllIlIIllIllIIlIIIllIIlIIllIIlIllIlIllIllIIlIIlIlllIllIllIlllIIIlIIlIIIlIIlllIIlllIIlIIlIIIllIIlllIllIIIlIllIIlllIlIIlllIIlllIllIIlIIlIllIlllIllIllIIlIllIIIlIIlllIIllIIllIllIIlllIlIIlIllIIllIlIIllIIllIIIlllIll;
        this.IllIllIIIlIllIllIIllIIIlllIlIllIIlIIlIlIIllIllIIIlllIllIllIIIllIlIlIIIlIIIllIIllIIlIllIIlIIlllIIlIIlllIllIIIllIIllIIIlIllIlllIIllIIIllIlIlIIIlIlIIllIIllIIlllIIIlIIllIllIllIIIlllIIlllIllIIlIIlIIlIIllIIllIIlIIIlllIIlIIlll = illIllIIIlIllIllIIllIIIlllIlIllIIlIIlIlIIllIllIIIlllIllIllIIIllIlIlIIIlIIIllIIllIIlIllIIlIIlllIIlIIlllIllIIIllIIllIIIlIllIlllIIllIIIllIlIlIIIlIlIIllIIllIIlllIIIlIIllIllIllIIIlllIIlllIllIIlIIlIIlIIllIIllIIlIIIlllIIlIIlll;
        this.IlllIllIIllIllIIIlllIllIIIlllIIlIIllIIlIllIIlllIIllIIIlIlIlllIIlIlIllIIIllIlllIlllIllIIIllIIIlIIIlIIIlIIIlIIllIllIIIllIllIlIIIlllIIllIIllIIllIIlllIIIlIIIlIIIlIllIIIlllIIlllIlllIIlIIIlIIlllIlIIllIIllIIIllIIlllIlIIIlIllIIlllIIIlllIIllIIIlll = IllIllIIlllIIIllIIlllIIlIIlllIIIllIIIlllIIllIIIlIIIlIIIllIIllIlIIIllIlIIlllIIIlllIlIIlIIIlIIlIIlllIlIIlIlllIIllIIllIIllIIIlIIIlIIllIIllIllIIlIIIlllIllIIlIIIllIIllIIlIllIIIlllIIIllIIIllIIllIIlIIIllIIlllIIlllIIIlllIIIllIllIIlIIIlllIIlIIIllIIllIll.IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll.IlllIIlIIIlllIIIlIIllIlIllIIIlIIIllIlllIIIllIIlIIllIIllIlllIIllIIllIIllIIllIIlllIIlllIllIIllIlllIlIllIIlllIllIllIIlllIIllIIIlllIIIllIIIllIlllIIlllIIlIlIlIlIllIIIlllIIlIllIIllIIlllIIlIIIllIIIllIIlIIlllIlIllIllIIlIIIllIllIIlIIllIll(this);
        this.IIllIIlllIllIIllIIIlllIIlllIIllIIllIIlIIllIIllIllIIlllIlllIIlIIllIIllIlllIllIIllIIIlllIllIlllIIlIIlllIllIIIllIlllIIIlIllIIIllIIlIIllIIlIIIllIIIllIIIllIIIllIIIllIlIIllIIIlIIllIIllIllIIlllIIlllIIIllIIlIlIIllIlIIllIIlIIlIIlllIllIIlllIIllIIIl = WrappedBlockStateCache.INSTANCE.get(0);
        final Map<Integer, XMaterial> replacementBlocks;
        final Map map = new LinkedHashMap(MapsKt.mapCapacity((replacementBlocks = this.IIllIIIlllIIIllIIIllIIlIIllIIllIIIlllIIlIIIllIIllIIIlIIIlllIIlllIIlllIIllIIlIlllIlllIlIlIlllIllIIIllIIIllIlIlIllIlIllIIlllIllIlIIlIIIlIlIIllIlllIIlIllIIIlIlllIIllIllIIlIIlllIIlllIIIllIIllIlllIIlIlIIIllIIllIIIlIIIllIIllIIllIIIlllIIll.getReplacementBlocks()).size()));
        for (final Object next : replacementBlocks.entrySet()) {
            map.put(((Map.Entry<Object, V>)next).getKey(), this.IIlIllIlIIllIllIIlIIIllIIlIIllIIlIllIlIllIllIIlIIlIlllIllIllIlllIIIlIIlIIIlIIlllIIlllIIlIIlIIIllIIlllIllIIIlIllIIlllIlIIlllIIlllIllIIlIIlIllIlllIllIllIIlIllIIIlIIlllIIllIIllIllIIlllIlIIlIllIIllIlIIllIIllIIIlllIll.decode(((Map.Entry<Object, XMaterial>)next).getValue()));
        }
        final List sortedWith = CollectionsKt.sortedWith((Iterable)map.entrySet(), (Comparator)new Comparator() {
            @Override
            public final int compare(final T t, final T t2) {
                return ComparisonsKt.compareValues((Comparable)((Map.Entry)t2).getKey(), (Comparable)((Map.Entry)t).getKey());
            }
        });
        int i = 0;
        final int size = sortedWith.size();
        final int[] iiIllIllIIIllIIllIlIllIlllIIlIlIIIllIlIIIlIIIlIlIIIlIIIllIlIllIlIllIIllIIlllIlIIlIIllIlIIIllIIllIIlIllIIlllIlllIllIlIIlllIIlllIIllIlIIlllIIIllIIlIllIIlIIllIIllIIIlIlIIIllIIlllIllIIlllIIlllIIIllIIIlIIIllIlIIIlIIlIIIllIIIll = new int[size];
        while (i < size) {
            final int n = i;
            iiIllIllIIIllIIllIlIllIlllIIlIlIIIllIlIIIlIIIlIlIIIlIIIllIlIllIlIllIIllIIlllIlIIlIIllIlIIIllIIllIIlIllIIlllIlllIllIlIIlllIIlllIIllIlIIlllIIIllIIlIllIIlIIllIIllIIIlIlIIIllIIlllIllIIlllIIlllIIIllIIIlIIIllIlIIIlIIlIIIllIIIll[n] = ((Map.Entry<Number, V>)sortedWith.get(n)).getKey().intValue();
            ++i;
        }
        this.IIIllIllIIIllIIllIlIllIlllIIlIlIIIllIlIIIlIIIlIlIIIlIIIllIlIllIlIllIIllIIlllIlIIlIIllIlIIIllIIllIIlIllIIlllIlllIllIlIIlllIIlllIIllIlIIlllIIIllIIlIllIIlIIllIIllIIIlIlIIIllIIlllIllIIlllIIlllIIIllIIIlIIIllIlIIIlIIlIIIllIIIll = iiIllIllIIIllIIllIlIllIlllIIlIlIIIllIlIIIlIIIlIlIIIlIIIllIlIllIlIllIIllIIlllIlIIlIIllIlIIIllIIllIIlIllIIlllIlllIllIlIIlllIIlllIIllIlIIlllIIIllIIlIllIIlIIllIIllIIIlIlIIIllIIlllIllIIlllIIlllIIIllIIIlIIIllIlIIIlIIlIIIllIIIll;
        int j = 0;
        final int size2 = sortedWith.size();
        final WrappedBlockState[] ilIIlllIlllIlllIIIlIIlIIlIIllIIIllIlllIIllIIlIIllIllIIllIIIllIIlIlIIllIIllIllIIIlIlIIIllIIlllIllIIlIllIIlIllIlllIIllIlllIllIlllIllIIIllIllIIlIIIlIIlllIIIlIllIllIllIlllIllIIlllIlIIIlllIIlllIllIlIIlIllIIIlllIIIllIIIllIIlIl = new WrappedBlockState[size2];
        while (j < size2) {
            final int n2 = j;
            ilIIlllIlllIlllIIIlIIlIIlIIllIIIllIlllIIllIIlIIllIllIIllIIIllIIlIlIIllIIllIllIIIlIlIIIllIIlllIllIIlIllIIlIllIlllIIllIlllIllIlllIllIIIllIllIIlIIIlIIlllIIIlIllIllIllIlllIllIIlllIlIIIlllIIlllIllIlIIlIllIIIlllIIIllIIIllIIlIl[n2] = ((Map.Entry<K, WrappedBlockState>)sortedWith.get(n2)).getValue();
            ++j;
        }
        this.IlIIlllIlllIlllIIIlIIlIIlIIllIIIllIlllIIllIIlIIllIllIIllIIIllIIlIlIIllIIllIllIIIlIlIIIllIIlllIllIIlIllIIlIllIlllIIllIlllIllIlllIllIIIllIllIIlIIIlIIlllIIIlIllIllIllIlllIllIIlllIlIIIlllIIlllIllIlIIlIllIIIlllIIIllIIIllIIlIl = ilIIlllIlllIlllIIIlIIlIIlIIllIIIllIlllIIllIIlIIllIllIIllIIIllIIlIlIIllIIllIllIIIlIlIIIllIIlllIllIIlIllIIlIllIlllIIllIlllIllIlllIllIIIllIllIIlIIIlIIlllIIIlIllIllIllIlllIllIIlllIlIIIlllIIlllIllIlIIlIllIIIlllIIIllIIIllIIlIl;
    }
    
    @NotNull
    public final World IllIllIlIIlllIIIlllIIIllIIIlIlIIIlllIIlIIllIIllIllIIIlIIIlllIIllIIIllIIllIIlllIlllIlIllIIlllIIllIIllIlIlllIIIllIIllIlllIIlllIIIlIIllIIIlllIlIIlIIlIllIlllIIllIllIIIllIIllIIIllIIllIIllIIllIIlIIlIIllIIIlIIIllIIlllIIIllIlIlllIllIIlIIIlllIll() {
        return this.IllIlllIIllIIlIIlIlIIIllIIllIIlllIIIlIIlIIlllIIlIlIIIlIlIIIlIIllIlllIIIlllIIllIllIllIIlllIlIllIIlIlIIIlIlIlllIIIlIlllIIlIIllIIlIIllIllIIllIlllIIIllIllIlIIIllIIllIIllIIlIIIlIIlIlIIIllIIllIllIlllIIIllIIIllIIIlllIIlIIIlIlll;
    }
    
    @NotNull
    public final AntiXrayConfig IllIIlIIlIIllIlIIIllIIIllIlIlllIlllIlIIllIIllIlIllIlllIIIllIIlIIIlIIllIIlIllIIlIIlIIllIIlIlIllIIlllIlIlIIllIIIlIIlllIllIIIlIIlIIIlllIlllIIllIIlIIllIIlIIllIIIllIIlllIIlllIIIlllIIIlIIllIIlIIlllIlIIIllIIIllIIllIIllIllIIlllIIll() {
        return this.IIllIIIlllIIIllIIIllIIlIIllIIllIIIlllIIlIIIllIIllIIIlIIIlllIIlllIIlllIIllIIlIlllIlllIlIlIlllIllIIIllIIIllIlIlIllIlIllIIlllIllIlIIlIIIlIlIIllIlllIIlIllIIIlIlllIIllIllIIlIIlllIIlllIIIllIIllIlllIIlIlIIIllIIllIIIlIIIllIIllIIllIIIlllIIll;
    }
    
    @NotNull
    public final MCWorld IllIIllIlIlIllIIllIIIllIIIllIIIllIIIlIlIlIllIlIIllIIlllIlllIIlIIIllIIlIllIIlllIIllIllIIIlIIlIlIlllIIIlllIIlIIIllIIIllIIIllIlIIlllIIlIlIIIlllIIlIlIIIlllIIlllIIlIllIllIIlIIIllIIllIlllIIllIIllIllIIllIIllIIlIIllIllIIllIIlIIl() {
        final MCWorld from = MCWorld.from((Object)this.IllIlllIIllIIlIIlIlIIIllIIllIIlllIIIlIIlIIlllIIlIlIIIlIlIIIlIIllIlllIIIlllIIllIllIllIIlllIlIllIIlIlIIIlIlIlllIIIlIlllIIlIIllIIlIIllIllIIllIlllIIIllIllIlIIIllIIllIIllIIlIIIlIIlIlIIIllIIllIllIlllIIIllIIIllIIIlllIIlIIIlIlll);
        Intrinsics.checkNotNullExpressionValue((Object)from, "");
        return from;
    }
    
    public final int IIIllIIlllIlIIIllIllIIIlllIIlllIIlllIlIlIIlIIIllIIllIlllIIlllIIlIlllIIlIIlIIllIIlIIIlllIllIIlllIIlIllIIIllIIlIIlIIlIIllIIlIIlIIIllIIllIIlIIlIIlllIIlllIIIllIlIIllIllIIlllIlIIIlllIIllIIlllIIllIlllIIllIIIllIIllIIllIIIlllIlIIlllIIllIIllIIlll() {
        return this.IIllIIIlllIIIllIIIllIIlIIllIIllIIIlllIIlIIIllIIllIIIlIIIlllIIlllIIlllIIllIIlIlllIlllIlIlIlllIllIIIllIIIllIlIlIllIlIllIIlllIllIlIIlIIIlIlIIllIlllIIlIllIIIlIlllIIllIllIIlIIlllIIlllIIIllIIllIlllIIlIlIIIllIIllIIIlIIIllIIllIIllIIIlllIIll.getUpdateRadius();
    }
    
    public final double IllIIlIIllIllIIlllIlllIlIIllIlIllIIllIlllIIllIIIlIIIlllIIllIIllIlIIIlIIllIIlIIIlIllIIllIIlIIllIIIlIIlllIIllIIIlIIlIlIIIlIllIIlIIIllIIlIlIIIlIIIllIIlllIIllIIlllIIlIIIllIIllIIlllIllIllIIlIIllIIlllIIIlllIllIllIIlIIlllIlllIIIlIIIll() {
        return this.IIllIIIlllIIIllIIIllIIlIIllIIllIIIlllIIlIIIllIIllIIIlIIIlllIIlllIIlllIIllIIlIlllIlllIlIlIlllIllIIIllIIIllIlIlIllIlIllIIlllIllIlIIlIIIlIlIIllIlllIIlIllIIIlIlllIIllIllIIlIIlllIIlllIIIllIIllIlllIIlIlIIIllIIllIIIlIIIllIIllIIllIIIlllIIll.getMaxRaytraceDistance();
    }
    
    public final long IllIllIlIIIllIIlIIllIIlllIIlllIIllIlllIIlIIllIIlllIlIllIIIllIllIIlllIlllIIlIIlIllIIllIlllIlllIIlIIlIIlIIlIIlllIllIIlIllIIIlllIIllIIIlIIIlllIlIIIlIlIlIIllIIIlIIllIlIIlIlIllIIlllIIllIIIlllIIllIIlIllIllIIllIllIIlllIIlIll() {
        return this.IIllIIIlllIIIllIIIllIIlIIllIIllIIIlllIIlIIIllIIllIIIlIIIlllIIlllIIlllIIllIIlIlllIlllIlIlIlllIllIIIllIIIllIlIlIllIlIllIIlllIllIlIIlIIIlIlIIllIlllIIlIllIIIlIlllIIllIllIIlIIlllIIlllIIIllIIllIlllIIlIlIIIllIIllIIIlIIIllIIllIIllIIIlllIIll.getRaytraceDelay();
    }
    
    public final int IllIllIIIlllIllIIlllIlIllIIllIllIIlIIIlIIlllIIllIlllIIlIlIlIIlIIIllIIlIIIllIIIllIIlIIlIIIllIIlIllIIlllIlIIlllIllIIIllIIllIIlllIIIllIIllIIIllIIIllIIIlllIIlIlIIIlIlllIIIlIIllIlIIlllIlIIllIllIllIlllIIllIIIllIIIllIIlIIlIIIlIIllIll() {
        return this.IIllIIIlllIIIllIIIllIIlIIllIIllIIIlllIIlIIIllIIllIIIlIIIlllIIlllIIlllIIllIIlIlllIlllIlIlIlllIllIIIllIIIllIlIlIllIlIllIIlllIllIlIIlIIIlIlIIllIlllIIlIllIIIlIlllIIllIllIIlIIlllIIlllIIIllIIllIlllIIlIlIIIllIIllIIIlIIIllIIllIIllIIIlllIIll.getRaytraceRate();
    }
    
    public final boolean IIlllIIllIlllIlllIIlllIIIlIllIIIlllIIlIIIlIlllIlllIIllIIIlIIllIIlIIlIllIIIllIIlllIIIllIIIllIIlllIIllIIllIIllIlllIlllIIIllIllIlIllIIlllIlllIIIlllIlllIlllIllIIllIIIllIIllIlllIIllIIllIIlllIIlIIlllIIIlIlIllIIlIIllIIIlIIlIIlIIIlllIIIllIIIllIIlIl() {
        return this.IIllIIIlllIIIllIIIllIIlIIllIIllIIIlllIIlIIIllIIllIIIlIIIlllIIlllIIlllIIllIIlIlllIlllIlIlIlllIllIIIllIIIllIlIlIllIlIllIIlllIllIlIIlIIIlIlIIllIlllIIlIllIIIlIlllIIllIllIIlIIlllIIlllIIIllIIllIlllIIlIlIIIllIIllIIIlIIIllIIllIIllIIIlllIIll.getTracePlacedBlock();
    }
    
    public final boolean IllIllIllIlllIIlllIIlllIIlIIlllIIlIIllIIllIIllIIIlIllIIllIlllIlIIllIIlllIIlIIIlIIlIIllIlllIIlIIIlIIIlIllIlIIllIIllIIlllIIllIIlIlllIlllIIIlIIIlllIIllIIIlllIIllIllIIlllIIllIIllIIllIlIIllIllIIlllIIllIIlIIllIlIIIlllIIlIllIIlllIllIIl() {
        return this.IIllIIIlllIIIllIIIllIIlIIllIIllIIIlllIIlIIIllIIllIIIlIIIlllIIlllIIlllIIllIIlIlllIlllIlIlIlllIllIIIllIIIllIlIlIllIlIllIIlllIllIlIIlIIIlIlIIllIlllIIlIllIIIlIlllIIllIllIIlIIlllIIlllIIIllIIllIlllIIlIlIIIllIIllIIIlIIIllIIllIIllIIIlllIIll.getRevealStopRaytracing();
    }
    
    public final boolean IlIllIlllIIllIlllIIllIIlllIIIllIIllIIlIIIlllIllIIlIlIIllIIIllIlIlIIllIllIlllIlIIllIIIlllIIllIIIlIIIlIIlIIlIIIlllIllIlllIIIlIIllIIIlIllIIIllIIlIIlIIIlIlIlIIIlllIlllIIlIllIIlllIIllIllIIlllIIIllIlIIllIIllIIlIIlIIllIllIIIllIl() {
        return this.IIlIIlIllIIllIIllIIIlIIIlllIIIlIIlllIIIlIIllIIlIIlIIllIIIllIlllIIIlllIIlIlIIIllIIlllIIlIlllIIllIIlIIllIIlIllIllIIlIIllIIllIIllIIlllIIlIIllIlIIllIlIIllIlllIllIlllIIIlIIllIIlllIlIllIIlIllIIlIIllIIIllIllIIlIIIlIllIllIlllIll.getSupportThirdPerson();
    }
    
    public final double IIIlIIIllIIIllIlllIIllIlllIIlIIIllIIIlIIIllIIlllIIlllIIIlIlIllIlIIllIIlIllIIIlIlIIlllIIllIIIllIIllIIIlIIllIlIIllIIIllIlIIlllIIllIIIlIIlIIIllIlIIlllIIIllIllIlllIllIlIllIIIllIIlIllIIlIIlllIIllIlllIllIIlIllIIlIIllIlIllIIllIIl() {
        return this.IIlIIlIllIIllIIllIIIlIIIlllIIIlIIlllIIIlIIllIIlIIlIIllIIIllIlllIIIlllIIlIlIIIllIIlllIIlIlllIIllIIlIIllIIlIllIllIIlIIllIIllIIllIIlllIIlIIllIlIIllIlIIllIlllIllIlllIIIlIIllIIlllIlIllIIlIllIIlIIllIIIllIllIIlIIIlIllIllIlllIll.getThirdPersonMaxZoom();
    }
    
    public final boolean IllIlllIIllIIlIIlIlIIIllIIllIIlllIIIlIIlIIlllIIlIlIIIlIlIIIlIIllIlllIIIlllIIllIllIllIIlllIlIllIIlIlIIIlIlIlllIIIlIlllIIlIIllIIlIIllIllIIllIlllIIIllIllIlIIIllIIllIIllIIlIIIlIIlIlIIIllIIllIllIlllIIIllIIIllIIIlllIIlIIIlIlll() {
        return this.IIllIIIlllIIIllIIIllIIlIIllIIllIIIlllIIlIIIllIIllIIIlIIIlllIIlllIIlllIIllIIlIlllIlllIlIlIlllIllIIIllIIIllIlIlIllIlIllIIlllIllIlIIlIIIlIlIIllIlllIIlIllIIIlIlllIIllIllIIlIIlllIIlllIIIllIIllIlllIIlIlIIIllIIllIIIlIIIllIIllIIllIIIlllIIll.getAggressiveCheckBlock();
    }
    
    public final boolean IIllIIlIlIIlllIIllIIllIIIlllIIIlllIllIIllIIlllIlIIllIIllIllIIlIIllIIlIlllIIIlIIlIllIIIllIIIlllIIlIIIlllIIIlllIIlIllIIIllIIlIlIllIlIIllIlIIIlllIIllIIlIIIlIIlIIlIlIlllIlIIIlIIlIIlllIllIllIllIIIlllIIllIIIllIIllIIlllIIlIllIlIIll() {
        return this.IIllIIIlllIIIllIIIllIIlIIllIIllIIIlllIIlIIIllIIllIIIlIIIlllIIlllIIlllIIllIIlIlllIlllIlIlIlllIllIIIllIIIllIlIlIllIlIllIIlllIllIlIIlIIIlIlIIllIlllIIlIllIIIlIlllIIllIllIIlIIlllIIlllIIIllIIllIlllIIlIlIIIllIIllIIIlIIIllIIllIIllIIIlllIIll.getBlockGrouping();
    }
    
    public final boolean IIllIIIlllIIIllIIIllIIlIIllIIllIIIlllIIlIIIllIIllIIIlIIIlllIIlllIIlllIIllIIlIlllIlllIlIlIlllIllIIIllIIIllIlIlIllIlIllIIlllIllIlIIlIIIlIlIIllIlllIIlIllIIIlIlllIIllIllIIlIIlllIIlllIIIllIIllIlllIIlIlIIIllIIllIIIlIIIllIIllIIllIIIlllIIll() {
        return this.IIllIIIlllIIIllIIIllIIlIIllIIllIIIlllIIlIIIllIIllIIIlIIIlllIIlllIIlllIIllIIlIlllIlllIlIlIlllIllIIIllIIIllIlIlIllIlIllIIlllIllIlIIlIIIlIlIIllIlllIIlIllIIIlIlllIIllIllIIlIIlllIIlllIIIllIIllIlllIIlIlIIIllIIllIIIlIIIllIIllIIllIIIlllIIll.getHideUnderLava();
    }
    
    public final int IIlIIlIllIIllIIllIIIlIIIlllIIIlIIlllIIIlIIllIIlIIlIIllIIIllIlllIIIlllIIlIlIIIllIIlllIIlIlllIIllIIlIIllIIlIllIllIIlIIllIIllIIllIIlllIIlIIllIlIIllIlIIllIlllIllIlllIIIlIIllIIlllIlIllIIlIllIIlIIllIIIllIllIIlIIIlIllIllIlllIll() {
        return this.IIllIIIlllIIIllIIIllIIlIIllIIllIIIlllIIlIIIllIIllIIIlIIIlllIIlllIIlllIIllIIlIlllIlllIlIlIlllIllIIIllIIIllIlIlIllIlIllIIlllIllIlIIlIIIlIlIIllIlllIIlIllIIIlIlllIIllIllIIlIIlllIIlllIIIllIIllIlllIIlIlIIIllIIllIIIlIIIllIIllIIllIIIlllIIll.getLavaRevealDistance();
    }
    
    public final void IIlllIIIllIIllIIllIlIIIlIIIllIIIlIIlllIIIlIlllIIlIIllIlIlIlIlIIIlllIIIlllIIIlllIIIlllIIlIllIIllIlllIllIIlllIlllIlllIIlIIlIIIllIllIIIlllIIllIIlIlllIIllIIIllIIlIIllIIIlIllIIIllIIllIIIlIIllIIlllIIlllIIIllIllIIIllIIllIIllIlIllIllIIIlIIllIIl() {
        this.IIllIIlIlIIlllIIllIIllIIIlllIIIlllIllIIllIIlllIlIIllIIllIllIIlIIllIIlIlllIIIlIIlIllIIIllIIIlllIIlIIIlllIIIlllIIlIllIIIllIIlIlIllIlIIllIlIIIlllIIllIIlIIIlIIlIIlIlIlllIlIIIlIIlIIlllIllIllIllIIIlllIIllIIIllIIllIIlllIIlIllIlIIll.tick();
        this.IlllIllIIllIllIIIlllIllIIIlllIIlIIllIIlIllIIlllIIllIIIlIlIlllIIlIlIllIIIllIlllIlllIllIIIllIIIlIIIlIIIlIIIlIIllIllIIIllIllIlIIIlllIIllIIllIIllIIlllIIIlIIIlIIIlIllIIIlllIIlllIlllIIlIIIlIIlllIlIIllIIllIIIllIIlllIlIIIlIllIIlllIIIlllIIllIIIlll.IIIllIIlIIIlIIllIIIlllIIIlIllIllIIllIIlllIlIllIIllIIIlIIlllIIIlllIIlIllIIIllIllIlIlIIIllIllIIllIllIIllIIIlllIlIIllIllIIllIlllIIIllIIlllIlIIIllIIlIIllIlIlllIllIIlllIIllIlllIlIllIIIllIllIIlIIlIIIllIlllIIIlIIllIIllIIlllIlIIIllIlll();
    }
    
    public final void IlIllIIlllIIlllIIlllIIIllIIIllIIlllIlIIllIllIIIllIlIIlllIlIIlIIllIllIlIIlIllIIIllIIllIIIllIllIlIIIlllIIIlIIlllIIlIIIlllIIIllIIIllIIlllIIllIIlIIIllIIllIIlllIIllIlIllIIllIIllIllIIIllIIllIIlIIIlIIlllIIIlIIlllIIlIIIllIlIlllIlIllIIlIIIll() {
        final Iterator iterator = this.IlIllIIlllIIlllIIlllIIIllIIIllIIlllIlIIllIllIIIllIlIIlllIlIIlIIllIllIlIIlIllIIIllIIllIIIllIllIlIIIlllIIIlIIlllIIlIIIlllIIIllIIIllIIlllIIllIIlIIIllIIllIIlllIIllIlIllIIllIIllIllIIIllIIllIIlIIIlIIlllIIIlIIlllIIlIIIllIlIlllIlIllIIlIIIll.iterator();
        while (iterator.hasNext()) {
            ((ObfuscateStrategy)iterator.next()).onReload();
        }
    }
    
    @NotNull
    public final WrappedBlockState IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(final int n) {
        final int[] iiIllIllIIIllIIllIlIllIlllIIlIlIIIllIlIIIlIIIlIlIIIlIIIllIlIllIlIllIIllIIlllIlIIlIIllIlIIIllIIllIIlIllIIlllIlllIllIlIIlllIIlllIIllIlIIlllIIIllIIlIllIIlIIllIIllIIIlIlIIIllIIlllIllIIlllIIlllIIIllIIIlIIIllIlIIIlIIlIIIllIIIll = this.IIIllIllIIIllIIllIlIllIlllIIlIlIIIllIlIIIlIIIlIlIIIlIIIllIlIllIlIllIIllIIlllIlIIlIIllIlIIIllIIllIIlIllIIlllIlllIllIlIIlllIIlllIIllIlIIlllIIIllIIlIllIIlIIllIIllIIIlIlIIIllIIlllIllIIlllIIlllIIIllIIIlIIIllIlIIIlIIlIIIllIIIll;
        for (int i = 0; i < iiIllIllIIIllIIllIlIllIlllIIlIlIIIllIlIIIlIIIlIlIIIlIIIllIlIllIlIllIIllIIlllIlIIlIIllIlIIIllIIllIIlIllIIlllIlllIllIlIIlllIIlllIIllIlIIlllIIIllIIlIllIIlIIllIIllIIIlIlIIIllIIlllIllIIlllIIlllIIIllIIIlIIIllIlIIIlIIlIIIllIIIll.length; ++i) {
            if (n > iiIllIllIIIllIIllIlIllIlllIIlIlIIIllIlIIIlIIIlIlIIIlIIIllIlIllIlIllIIllIIlllIlIIlIIllIlIIIllIIllIIlIllIIlllIlllIllIlIIlllIIlllIIllIlIIlllIIIllIIlIllIIlIIllIIllIIIlIlIIIllIIlllIllIIlllIIlllIIIllIIIlIIIllIlIIIlIIlIIIllIIIll[i]) {
                return this.IlIIlllIlllIlllIIIlIIlIIlIIllIIIllIlllIIllIIlIIllIllIIllIIIllIIlIlIIllIIllIllIIIlIlIIIllIIlllIllIIlIllIIlIllIlllIIllIlllIllIlllIllIIIllIllIIlIIIlIIlllIIIlIllIllIllIlllIllIIlllIlIIIlllIIlllIllIlIIlIllIIIlllIIIllIIIllIIlIl[i];
            }
        }
        return this.IIllIIlllIllIIllIIIlllIIlllIIllIIllIIlIIllIIllIllIIlllIlllIIlIIllIIllIlllIllIIllIIIlllIllIlllIIlIIlllIllIIIllIlllIIIlIllIIIllIIlIIllIIlIIIllIIIllIIIllIIIllIIIllIlIIllIIIlIIllIIllIllIIlllIIlllIIIllIIlIlIIllIlIIllIIlIIlIIlllIllIIlllIIllIIIl;
    }
    
    public final boolean IIllIIIllIllIIIlllIIIlllIIllIIlllIlIIIllIllIlIIlIIIlllIIlIIllIIllIlIllIIllIIIllIIlIllIIlIIlIIllIIlIlIIllIllIIlIlIIlIIIllIIlIllIllIIlllIllIIlIlllIIllIlIlllIIlllIIIlIIlIIllIlllIlIIllIIIllIlIlIIlIIllIllIlIllIIIllIIll(@NotNull final BlockPosition blockPosition) {
        Intrinsics.checkNotNullParameter((Object)blockPosition, "");
        final int x = blockPosition.getX();
        final int y = blockPosition.getY();
        final int z = blockPosition.getZ();
        boolean b = false;
        final int n = x >> 4;
        final int n2 = z >> 4;
        final DirectChunk value = this.IIllIIlIlIIlllIIllIIllIIIlllIIIlllIllIIllIIlllIlIIllIIllIllIIlIIllIIlIlllIIIlIIlIllIIIllIIIlllIIlIIIlllIIIlllIIlIllIIIllIIlIlIllIlIIllIlIIIlllIIllIIlIIIlIIlIIlIlIlllIlIIIlIIlIIlllIllIllIllIIIlllIIllIIIllIIllIIlllIIlIllIlIIll.get(n, n2);
        if (value == null) {
            return false;
        }
        final DirectChunk directChunk = value;
        final int n3 = x - 1;
        DirectChunk value2;
        if (n3 >> 4 == n) {
            value2 = directChunk;
        }
        else if ((value2 = this.IIllIIlIlIIlllIIllIIllIIIlllIIIlllIllIIllIIlllIlIIllIIllIllIIlIIllIIlIlllIIIlIIlIllIIIllIIIlllIIlIIIlllIIIlllIIlIllIIIllIIlIlIllIlIIllIlIIIlllIIllIIlIIIlIIlIIlIlIlllIlIIIlIIlIIlllIllIllIllIIIlllIIllIIIllIIllIIlllIIlIllIlIIll.get(n3 >> 4, n2)) == null) {
            return false;
        }
        final WrappedBlockState blockData = value2.getBlockDataAt(n3 & 0xF, y, z & 0xF);
        if (this.IIlIllIlIIllIllIIlIIIllIIlIIllIIlIllIlIllIllIIlIIlIlllIllIllIlllIIIlIIlIIIlIIlllIIlllIIlIIlIIIllIIlllIllIIIlIllIIlllIlIIlllIIlllIllIIlIIlIllIlllIllIllIIlIllIIIlIIlllIIllIIllIllIIlllIlIIlIllIIllIlIIllIIllIIIlllIll.isLava(blockData)) {
            b = true;
        }
        else if (this.IIlIllIlIIllIllIIlIIIllIIlIIllIIlIllIlIllIllIIlIIlIlllIllIllIlllIIIlIIlIIIlIIlllIIlllIIlIIlIIIllIIlllIllIIIlIllIIlllIlIIlllIIlllIllIIlIIlIllIlllIllIllIIlIllIIIlIIlllIIllIIllIllIIlllIlIIlIllIIllIlIIllIIllIIIlllIll.isTransparent(blockData)) {
            return false;
        }
        final int n4 = x + 1;
        DirectChunk value3;
        if (n4 >> 4 == n) {
            value3 = directChunk;
        }
        else if ((value3 = this.IIllIIlIlIIlllIIllIIllIIIlllIIIlllIllIIllIIlllIlIIllIIllIllIIlIIllIIlIlllIIIlIIlIllIIIllIIIlllIIlIIIlllIIIlllIIlIllIIIllIIlIlIllIlIIllIlIIIlllIIllIIlIIIlIIlIIlIlIlllIlIIIlIIlIIlllIllIllIllIIIlllIIllIIIllIIllIIlllIIlIllIlIIll.get(n4 >> 4, n2)) == null) {
            return false;
        }
        final WrappedBlockState blockData2 = value3.getBlockDataAt(n4 & 0xF, y, z & 0xF);
        if (this.IIlIllIlIIllIllIIlIIIllIIlIIllIIlIllIlIllIllIIlIIlIlllIllIllIlllIIIlIIlIIIlIIlllIIlllIIlIIlIIIllIIlllIllIIIlIllIIlllIlIIlllIIlllIllIIlIIlIllIlllIllIllIIlIllIIIlIIlllIIllIIllIllIIlllIlIIlIllIIllIlIIllIIllIIIlllIll.isLava(blockData2)) {
            b = true;
        }
        else if (this.IIlIllIlIIllIllIIlIIIllIIlIIllIIlIllIlIllIllIIlIIlIlllIllIllIlllIIIlIIlIIIlIIlllIIlllIIlIIlIIIllIIlllIllIIIlIllIIlllIlIIlllIIlllIllIIlIIlIllIlllIllIllIIlIllIIIlIIlllIIllIIllIllIIlllIlIIlIllIIllIlIIllIIllIIIlllIll.isTransparent(blockData2)) {
            return false;
        }
        final int n5 = z - 1;
        DirectChunk value4;
        if (n5 >> 4 == n2) {
            value4 = directChunk;
        }
        else if ((value4 = this.IIllIIlIlIIlllIIllIIllIIIlllIIIlllIllIIllIIlllIlIIllIIllIllIIlIIllIIlIlllIIIlIIlIllIIIllIIIlllIIlIIIlllIIIlllIIlIllIIIllIIlIlIllIlIIllIlIIIlllIIllIIlIIIlIIlIIlIlIlllIlIIIlIIlIIlllIllIllIllIIIlllIIllIIIllIIllIIlllIIlIllIlIIll.get(n, n5 >> 4)) == null) {
            return false;
        }
        final WrappedBlockState blockData3 = value4.getBlockDataAt(x & 0xF, y, n5 & 0xF);
        if (this.IIlIllIlIIllIllIIlIIIllIIlIIllIIlIllIlIllIllIIlIIlIlllIllIllIlllIIIlIIlIIIlIIlllIIlllIIlIIlIIIllIIlllIllIIIlIllIIlllIlIIlllIIlllIllIIlIIlIllIlllIllIllIIlIllIIIlIIlllIIllIIllIllIIlllIlIIlIllIIllIlIIllIIllIIIlllIll.isLava(blockData3)) {
            b = true;
        }
        else if (this.IIlIllIlIIllIllIIlIIIllIIlIIllIIlIllIlIllIllIIlIIlIlllIllIllIlllIIIlIIlIIIlIIlllIIlllIIlIIlIIIllIIlllIllIIIlIllIIlllIlIIlllIIlllIllIIlIIlIllIlllIllIllIIlIllIIIlIIlllIIllIIllIllIIlllIlIIlIllIIllIlIIllIIllIIIlllIll.isTransparent(blockData3)) {
            return false;
        }
        final int n6 = z + 1;
        DirectChunk value5;
        if (n6 >> 4 == n2) {
            value5 = directChunk;
        }
        else if ((value5 = this.IIllIIlIlIIlllIIllIIllIIIlllIIIlllIllIIllIIlllIlIIllIIllIllIIlIIllIIlIlllIIIlIIlIllIIIllIIIlllIIlIIIlllIIIlllIIlIllIIIllIIlIlIllIlIIllIlIIIlllIIllIIlIIIlIIlIIlIlIlllIlIIIlIIlIIlllIllIllIllIIIlllIIllIIIllIIllIIlllIIlIllIlIIll.get(n, n6 >> 4)) == null) {
            return false;
        }
        final WrappedBlockState blockData4 = value5.getBlockDataAt(x & 0xF, y, n6 & 0xF);
        if (this.IIlIllIlIIllIllIIlIIIllIIlIIllIIlIllIlIllIllIIlIIlIlllIllIllIlllIIIlIIlIIIlIIlllIIlllIIlIIlIIIllIIlllIllIIIlIllIIlllIlIIlllIIlllIllIIlIIlIllIlllIllIllIIlIllIIIlIIlllIIllIIllIllIIlllIlIIlIllIIllIlIIllIIllIIIlllIll.isLava(blockData4)) {
            b = true;
        }
        else if (this.IIlIllIlIIllIllIIlIIIllIIlIIllIIlIllIlIllIllIIlIIlIlllIllIllIlllIIIlIIlIIIlIIlllIIlllIIlIIlIIIllIIlllIllIIIlIllIIlllIlIIlllIIlllIllIIlIIlIllIlllIllIllIIlIllIIIlIIlllIIllIIllIllIIlllIlIIlIllIIllIlIIllIIllIIIlllIll.isTransparent(blockData4)) {
            return false;
        }
        final WrappedBlockState blockData5 = directChunk.getBlockDataAt(x & 0xF, y + 1, z & 0xF);
        if (this.IIlIllIlIIllIllIIlIIIllIIlIIllIIlIllIlIllIllIIlIIlIlllIllIllIlllIIIlIIlIIIlIIlllIIlllIIlIIlIIIllIIlllIllIIIlIllIIlllIlIIlllIIlllIllIIlIIlIllIlllIllIllIIlIllIIIlIIlllIIllIIllIllIIlllIlIIlIllIIllIlIIllIIllIIIlllIll.isLava(blockData5)) {
            b = true;
        }
        else if (this.IIlIllIlIIllIllIIlIIIllIIlIIllIIlIllIlIllIllIIlIIlIlllIllIllIlllIIIlIIlIIIlIIlllIIlllIIlIIlIIIllIIlllIllIIIlIllIIlllIlIIlllIIlllIllIIlIIlIllIlllIllIllIIlIllIIIlIIlllIIllIIllIllIIlllIlIIlIllIIllIlIIllIIllIIIlllIll.isTransparent(blockData5)) {
            return false;
        }
        final WrappedBlockState blockData6 = directChunk.getBlockDataAt(x & 0xF, y - 1, z & 0xF);
        if (this.IIlIllIlIIllIllIIlIIIllIIlIIllIIlIllIlIllIllIIlIIlIlllIllIllIlllIIIlIIlIIIlIIlllIIlllIIlIIlIIIllIIlllIllIIIlIllIIlllIlIIlllIIlllIllIIlIIlIllIlllIllIllIIlIllIIIlIIlllIIllIIllIllIIlllIlIIlIllIIllIlIIllIIllIIIlllIll.isLava(blockData6)) {
            b = true;
        }
        else if (this.IIlIllIlIIllIllIIlIIIllIIlIIllIIlIllIlIllIllIIlIIlIlllIllIllIlllIIIlIIlIIIlIIlllIIlllIIlIIlIIIllIIlllIllIIIlIllIIlllIlIIlllIIlllIllIIlIIlIllIlllIllIllIIlIllIIIlIIlllIIllIIllIllIIlllIlIIlIllIIllIlIIllIIllIIIlllIll.isTransparent(blockData6)) {
            return false;
        }
        return b;
    }
    
    public final void IlIIIlIIllIlllIlIIIlIIlllIIlIllIIlIIllIIIlllIIlllIIIlIIIlIIllIlllIIllIllIIIlllIlIllIIIlllIIlllIlIIllIIIllIlIIlIlllIllIIlIIllIllIlIlIIlIIIllIIllIIlIllIIllIIlIIlllIllIIIllIlllIIllIlllIlIIlIllIIlllIIIllIlllIIIllIIlIIllIlllIIll(@NotNull final BlockPosition blockPosition) {
        Intrinsics.checkNotNullParameter((Object)blockPosition, "");
        this.IlllIllIIllIllIIIlllIllIIIlllIIlIIllIIlIllIIlllIIllIIIlIlIlllIIlIlIllIIIllIlllIlllIllIIIllIIIlIIIlIIIlIIIlIIllIllIIIllIllIlIIIlllIIllIIllIIllIIlllIIIlIIIlIIIlIllIIIlllIIlllIlllIIlIIIlIIlllIlIIllIIllIIIllIIlllIlIIIlIllIIlllIIIlllIIllIIIlll.IIlllIlIIllIlIIlIlllIlIlIIIlIIIlllIllIlIIllIlllIlIIllIllIIllIIlllIlllIIllIIllIIlIllIlIIIlIlllIIlIIIllIlllIllIllIllIIllIllIlIIlIllIIllIIIllIllIIlllIIlIllIIlIIIllIIIllIlIIIlIIlllIIIlIIllIIllIlIllIIllIIIllIlllIllIIl(blockPosition);
    }
    
    public final int IIlIllIlIIllIllIIlIIIllIIlIIllIIlIllIlIllIllIIlIIlIlllIllIllIlllIIIlIIlIIIlIIlllIIlllIIlIIlIIIllIIlllIllIIIlIllIIlllIlIIlllIIlllIllIIlIIlIllIlllIllIllIIlIllIIIlIIlllIIllIIllIllIIlllIlIIlIllIIllIlIIllIIllIIIlllIll() {
        return this.IlllIllIIllIllIIIlllIllIIIlllIIlIIllIIlIllIIlllIIllIIIlIlIlllIIlIlIllIIIllIlllIlllIllIIIllIIIlIIIlIIIlIIIlIIllIllIIIllIllIlIIIlllIIllIIllIIllIIlllIIIlIIIlIIIlIllIIIlllIIlllIlllIIlIIIlIIlllIlIIllIIllIIIllIIlllIlIIIlIllIIlllIIIlllIIllIIIlll.IllIlIIllIIllIIllIIIlllIIIlIllIllIlIIlIIllIlllIIIllIlllIIlIllIIlllIIlllIlllIllIIIlllIllIllIlIIllIIllIIllIllIIIllIIIlIIIllIIlIllIlIIIlIIllIllIIIllIllIIlllIIlIllIIlIIIlIIlIllIllIlIlllIIlllIIlIIlllIllIIllIlIIllIlIIllIIIll();
    }
    
    public final int IllIllIIIlIllIllIIllIIIlllIlIllIIlIIlIlIIllIllIIIlllIllIllIIIllIlIlIIIlIIIllIIllIIlIllIIlIIlllIIlIIlllIllIIIllIIllIIIlIllIlllIIllIIIllIlIlIIIlIlIIllIIllIIlllIIIlIIllIllIllIIIlllIIlllIllIIlIIlIIlIIllIIllIIlIIIlllIIlIIlll() {
        return this.IlllIllIIllIllIIIlllIllIIIlllIIlIIllIIlIllIIlllIIllIIIlIlIlllIIlIlIllIIIllIlllIlllIllIIIllIIIlIIIlIIIlIIIlIIllIllIIIllIllIlIIIlllIIllIIllIIllIIlllIIIlIIIlIIIlIllIIIlllIIlllIlllIIlIIIlIIlllIlIIllIIllIIIllIIlllIlIIIlIllIIlllIIIlllIIllIIIlll.IllIlllIIlIIIlIIIlIIIlllIlllIIIlIIllIIlIIlIIIllIIlIllIIIllIlllIlIIlllIIlllIllIIIllIllIIlllIIlIlIIlIIlIllIlllIIlllIllIlIllIlIIllIIlIIIllIlIIIlIIIlIIllIIlIlIIIllIIlIllIIlIIlIIllIllIlllIIIlIIIlIIllIIlllIllIllIIllIlIl();
    }
    
    public final void IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(@NotNull final Block block) {
        Intrinsics.checkNotNullParameter((Object)block, "");
        this.IIlIlllIIllIllIlllIIlIlIllIIlIIllIIllIIIllIlIIlllIIIllIIllIIlllIlllIIllIlllIllIIIlIIllIIllIIlllIIIllIIIlIIllIllIIlIIlIIlllIlIIlIllIlllIIlllIIIlIIIllIIllIIlIIIllIlllIIlIlIlllIIlIIllIIlIllIIlIIlllIIIlllIIlllIIlllIllIIlIllIIIlIIll(IlIllIIlIlIIllIIllIlllIIIllIIlllIIllIIlIIlllIIlIIlllIIIllIIlIlllIIllIIlIIlllIlIIIllIIIllIIlIIllIIlllIIIllIllIIlIllIIlllIlllIIlIlIllIIIlllIIIlllIIllIIIlIlIllIIlIIllIIlllIIIllIIlIIIllIIIlllIlIIlllIllIIlIIlIlllIIIlIIllIIllIlIIlllIll.IIllIllIIllIlIIlIlIIlllIllIIllIIllIlIIllIIIllIIIllIIllIIIlIllIIlIIlllIllIIlIllIllIIlIIlIlIIIlIllIIllIlllIllIIIllIIlIIlllIllIIlIIIlIIllIIllIIlIlIIlIllIIlllIlIIlllIIlIlIIlIIlIlllIIlIIIlIIllIllIlllIIlllIIIlIIllIIIlll(block));
    }
    
    public final void IIlIlllIIllIllIlllIIlIlIllIIlIIllIIllIIIllIlIIlllIIIllIIllIIlllIlllIIllIlllIllIIIlIIllIIllIIlllIIIllIIIlIIllIllIIlIIlIIlllIlIIlIllIlllIIlllIIIlIIIllIIllIIlIIIllIlllIIlIlIlllIIlIIllIIlIllIIlIIlllIIIlllIIlllIIlllIllIIlIllIIIlIIll(@NotNull final BlockPosition blockPosition) {
        Intrinsics.checkNotNullParameter((Object)blockPosition, "");
        this.IIllIllIlIlllIIlllIIlllIlIIIllIIlIIllIIllIIlIIIlllIIIlIIlllIIlllIIIlIlIIlIIllIlIIIllIIlIIllIIlIIlIIlllIIIllIlllIIlIIlllIllIllIIlllIlllIIlllIlIIIllIIllIlIlIIIllIlIIIlIIIllIIllIIlIlllIllIllIIIlIIIlIIllIIllIlIIIlIIIlIIllIIIllIIlll(blockPosition);
        this.IlllIllIIllIllIIIlllIllIIIlllIIlIIllIIlIllIIlllIIllIIIlIlIlllIIlIlIllIIIllIlllIlllIllIIIllIIIlIIIlIIIlIIIlIIllIllIIIllIllIlIIIlllIIllIIllIIllIIlllIIIlIIIlIIIlIllIIIlllIIlllIlllIIlIIIlIIlllIlIIllIIllIIIllIIlllIlIIIlIllIIlllIIIlllIIllIIIlll.IIlllIlIIllIlIIlIlllIlIlIIIlIIIlllIllIlIIllIlllIlIIllIllIIllIIlllIlllIIllIIllIIlIllIlIIIlIlllIIlIIIllIlllIllIllIllIIllIllIlIIlIllIIllIIIllIllIIlllIIlIllIIlIIIllIIIllIlIIIlIIlllIIIlIIllIIllIlIllIIllIIIllIlllIllIIl(blockPosition);
    }
    
    @ApiStatus.Internal
    public final void IllIllIIlllIIIllIIlllIIlIIlllIIIllIIIlllIIllIIIlIIIlIIIllIIllIlIIIllIlIIlllIIIlllIlIIlIIIlIIlIIlllIlIIlIlllIIllIIllIIllIIIlIIIlIIllIIllIllIIlIIIlllIllIIlIIIllIIllIIlIllIIIlllIIIllIIIllIIllIIlIIIllIIlllIIlllIIIlllIIIllIllIIlIIIlllIIlIIIllIIllIll(@NotNull final BlockPosition blockPosition) {
        Intrinsics.checkNotNullParameter((Object)blockPosition, "");
        this.IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(blockPosition.getX(), blockPosition.getY(), blockPosition.getZ());
    }
    
    @ApiStatus.Internal
    public final void IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(final int n, final int n2, final int n3) {
        final Iterable iterable = this.IlIllIIlllIIlllIIlllIIIllIIIllIIlllIlIIllIllIIIllIlIIlllIlIIlIIllIllIlIIlIllIIIllIIllIIIllIllIlIIIlllIIIlIIlllIIlIIIlllIIIllIIIllIIlllIIllIIlIIIllIIllIIlllIIllIlIllIIllIIllIllIIIllIIllIIlIIIlIIlllIIIlIIlllIIlIIIllIlIlllIlIllIIlIIIll;
        boolean b = false;
        Label_0093: {
            if (iterable instanceof Collection && ((Collection)iterable).isEmpty()) {
                b = false;
            }
            else {
                final Iterator iterator = iterable.iterator();
                while (iterator.hasNext()) {
                    if (((ObfuscateStrategy)iterator.next()).shouldNotifyBlockUpdate(n, n2, n3)) {
                        b = true;
                        break Label_0093;
                    }
                }
                b = false;
            }
        }
        if (b) {
            Native.INSTANCE.updateBlock(this.IllIlllIIllIIlIIlIlIIIllIIllIIlllIIIlIIlIIlllIIlIlIIIlIlIIIlIIllIlllIIIlllIIllIllIllIIlllIlIllIIlIlIIIlIlIlllIIIlIlllIIlIIllIIlIIllIllIIllIlllIIIllIllIlIIIllIIllIIllIIlIIIlIIlIlIIIllIIllIllIlllIIIllIIIllIIIlllIIlIIIlIlll, n, n2, n3);
        }
    }
    
    private final void IIllIllIlIlllIIlllIIlllIlIIIllIIlIIllIIllIIlIIIlllIIIlIIlllIIlllIIIlIlIIlIIllIlIIIllIIlIIllIIlIIlIIlllIIIllIlllIIlIIlllIllIllIIlllIlllIIlllIlIIIllIIllIlIlIIIllIlIIIlIIIllIIllIIlIlllIllIllIIIlIIIlIIllIIllIlIIIlIIIlIIllIIIllIIlll(final BlockPosition blockPosition) {
        if (!IllIlllIIlIIIlIIIlIIIlllIlllIIIlIIllIIlIIlIIIllIIlIllIIIllIlllIlIIlllIIlllIllIIIllIllIIlllIIlIlIIlIIlIllIlllIIlllIllIlIllIlIIllIIlIIIllIlIIIlIIIlIIllIIlIlIIIllIIlIllIIlIIlIIllIllIlllIIIlIIIlIIllIIlllIllIllIIllIlIl.IIlllIlIIllIlIIlIlllIlIlIIIlIIIlllIllIlIIllIlllIlIIllIllIIllIIlllIlllIIllIIllIIlIllIlIIIlIlllIIlIIIllIlllIllIllIllIIllIllIlIIlIllIIllIIIllIllIIlllIIlIllIIlIIIllIIIllIlIIIlIIlllIIIlIIllIIllIlIllIIllIIIllIlllIllIIl(this.IllIlllIIllIIlIIlIlIIIllIIllIIlllIIIlIIlIIlllIIlIlIIIlIlIIIlIIllIlllIIIlllIIllIllIllIIlllIlIllIIlIlIIIlIlIlllIIIlIlllIIlIIllIIlIIllIllIIllIlllIIIllIllIlIIIllIIllIIllIIlIIIlIIlIlIIIllIIllIllIlllIIIllIIIllIIIlllIIlIIIlIlll)) {
            return;
        }
        this.IIlllIIIllIIllIIllIlIIIlIIIllIIIlIIlllIIIlIlllIIlIIllIlIlIlIlIIIlllIIIlllIIIlllIIIlllIIlIllIIllIlllIllIIlllIlllIlllIIlIIlIIIllIllIIIlllIIllIIlIlllIIllIIIllIIlIIllIIIlIllIIIllIIllIIIlIIllIIlllIIlllIIIllIllIIIllIIllIIllIlIllIllIIIlIIllIIl.schedule(IlllIIlIIIlllIIIlIIllIlIllIIIlIIIllIlllIIIllIIlIIllIIllIlllIIllIIllIIllIIllIIlllIIlllIllIIllIlllIlIllIIlllIllIllIIlllIIllIIIlllIIIllIIIllIlllIIlllIIlIlIlIlIllIIIlllIIlIllIIllIIlllIIlIIIllIIIllIIlIIlllIlIllIllIIlIIIllIllIIlIIllIll::IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll);
    }
    
    public final void IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(@NotNull final Block block, final boolean b) {
        Intrinsics.checkNotNullParameter((Object)block, "");
        final Iterator iterator = this.IlIllIIlllIIlllIIlllIIIllIIIllIIlllIlIIllIllIIIllIlIIlllIlIIlIIllIllIlIIlIllIIIllIIllIIIllIllIlIIIlllIIIlIIlllIIlIIIlllIIIllIIIllIIlllIIllIIlIIIllIIllIIlllIIllIlIllIIllIIllIllIIIllIIllIIlIIIlIIlllIIIlIIlllIIlIIIllIlIlllIlIllIIlIIIll.iterator();
        while (iterator.hasNext()) {
            ((ObfuscateStrategy)iterator.next()).onBlockPlace(block, b);
        }
    }
    
    public final void IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(@NotNull final Chunk chunk) {
        Intrinsics.checkNotNullParameter((Object)chunk, "");
        final Iterator iterator = this.IlIllIIlllIIlllIIlllIIIllIIIllIIlllIlIIllIllIIIllIlIIlllIlIIlIIllIllIlIIlIllIIIllIIllIIIllIllIlIIIlllIIIlIIlllIIlIIIlllIIIllIIIllIIlllIIllIIlIIIllIIllIIlllIIllIlIllIIllIIllIllIIIllIIllIIlIIIlIIlllIIIlIIlllIIlIIIllIlIlllIlIllIIlIIIll.iterator();
        while (iterator.hasNext()) {
            ((ObfuscateStrategy)iterator.next()).onGlobalChunkLoaded(chunk);
        }
    }
    
    public final void IIllIllIIllIlIIlIlIIlllIllIIllIIllIlIIllIIIllIIIllIIllIIIlIllIIlIIlllIllIIlIllIllIIlIIlIlIIIlIllIIllIlllIllIIIllIIlIIlllIllIIlIIIlIIllIIllIIlIlIIlIllIIlllIlIIlllIIlIlIIlIIlIlllIIlIIIlIIllIllIlllIIlllIIIlIIllIIIlll(@NotNull final Chunk chunk) {
        Intrinsics.checkNotNullParameter((Object)chunk, "");
        final Iterator iterator = this.IlIllIIlllIIlllIIlllIIIllIIIllIIlllIlIIllIllIIIllIlIIlllIlIIlIIllIllIlIIlIllIIIllIIllIIIllIllIlIIIlllIIIlIIlllIIlIIIlllIIIllIIIllIIlllIIllIIlIIIllIIllIIlllIIllIlIllIIllIIllIllIIIllIIllIIlIIIlIIlllIIIlIIlllIIlIIIllIlIlllIlIllIIlIIIll.iterator();
        while (iterator.hasNext()) {
            ((ObfuscateStrategy)iterator.next()).onGlobalChunkUnloaded(chunk);
        }
    }
    
    public final void IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(@NotNull final Player player, @NotNull final DirectChunk directChunk) {
        Intrinsics.checkNotNullParameter((Object)player, "");
        Intrinsics.checkNotNullParameter((Object)directChunk, "");
    }
    
    public final void IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(@NotNull final Player player, final int n, final int n2) {
        Intrinsics.checkNotNullParameter((Object)player, "");
        this.IIlllIIIllIIllIIllIlIIIlIIIllIIIlIIlllIIIlIlllIIlIIllIlIlIlIlIIIlllIIIlllIIIlllIIIlllIIlIllIIllIlllIllIIlllIlllIlllIIlIIlIIIllIllIIIlllIIllIIlIlllIIllIIIllIIlIIllIIIlIllIIIllIIllIIIlIIllIIlllIIlllIIIllIllIIIllIIllIIllIlIllIllIIIlIIllIIl.IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(player, IlllIIlIIIlllIIIlIIllIlIllIIIlIIIllIlllIIIllIIlIIllIIllIlllIIllIIllIIllIIllIIlllIIlllIllIIllIlllIlIllIIlllIllIllIIlllIIllIIIlllIIIllIIIllIlllIIlllIIlIlIlIlIllIIIlllIIlIllIIllIIlllIIlIIIllIIIllIIlIIlllIlIllIllIIlIIIllIllIIlIIllIll::IIllIllIIllIlIIlIlIIlllIllIIllIIllIlIIllIIIllIIIllIIllIIIlIllIIlIIlllIllIIlIllIllIIlIIlIlIIIlIllIIllIlllIllIIIllIIlIIlllIllIIlIIIlIIllIIllIIlIlIIlIllIIlllIlIIlllIIlIlIIlIIlIlllIIlIIIlIIllIllIlllIIlllIIIlIIllIIIlll);
    }
    
    public final boolean IIllIIIllIllIIIlllIIIlllIIllIIlllIlIIIllIllIlIIlIIIlllIIlIIllIIllIlIllIIllIIIllIIlIllIIlIIlIIllIIlIlIIllIllIIlIlIIlIIIllIIlIllIllIIlllIllIIlIlllIIllIlIlllIIlllIIIlIIlIIllIlllIlIIllIIIllIlIlIIlIIllIllIlIllIIIllIIll(@NotNull final Player player) {
        Intrinsics.checkNotNullParameter((Object)player, "");
        if (!this.IIllIIIlllIIIllIIIllIIlIIllIIllIIIlllIIlIIIllIIllIIIlIIIlllIIlllIIlllIIllIIlIlllIlllIlIlIlllIllIIIllIIIllIlIlIllIlIllIIlllIllIlIIlIIIlIlIIllIlllIIlIllIIIlIlllIIllIllIIlIIlllIIlllIIIllIIllIlllIIlIlIIIllIIllIIIlIIIllIIllIIllIIIlllIIll.getEnabled()) {
            return false;
        }
        final Object orDefault = MCMetadata.provide((Object)player).getOrDefault((MetaKey)AntiXrayKeys.INSTANCE.getAntiXrayBypass(), (Object)false);
        Intrinsics.checkNotNullExpressionValue(orDefault, "");
        return !(boolean)orDefault;
    }
    
    @NotNull
    public final CompletableFuture<?> IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(@NotNull final Player player, @NotNull final IIllIIlIIlIlIIIlllIIlIIlIIlIllIIlIllIIlllIlllIlllIIllIlIIlllIlIlIIlllIIlIllIlllIIlllIlllIlIIIlllIlllIIIllIIllIlllIIllIIlllIlllIIlllIllIIlIlIllIlIlIlIlIIllIlIllIllIlllIIllIIIlllIlllIllIIllIlIllIlIIlIllIIllIlll illIIlIIlIlIIIlllIIlIIlIIlIllIIlIllIIlllIlllIlllIIllIlIIlllIlIlIIlllIIlIllIlllIIlllIlllIlIIIlllIlllIIIllIIllIlllIIllIIlllIlllIIlllIllIIlIlIllIlIlIlIlIIllIlIllIllIlllIIllIIIlllIlllIllIIllIlIllIlIIlIllIIllIlll) {
        Intrinsics.checkNotNullParameter((Object)player, "");
        Intrinsics.checkNotNullParameter((Object)illIIlIIlIlIIIlllIIlIIlIIlIllIIlIllIIlllIlllIlllIIllIlIIlllIlIlIIlllIIlIllIlllIIlllIlllIlIIIlllIlllIIIllIIllIlllIIllIIlllIlllIIlllIllIIlIlIllIlIlIlIlIIllIlIllIllIlllIIllIIIlllIlllIllIIllIlIllIlIIlIllIIllIlll, "");
        if (!this.IIllIIIlllIIIllIIIllIIlIIllIIllIIIlllIIlIIIllIIllIIIlIIIlllIIlllIIlllIIllIIlIlllIlllIlIlIlllIllIIIllIIIllIlIlIllIlIllIIlllIllIlIIlIIIlIlIIllIlllIIlIllIIIlIlllIIllIllIIlIIlllIIlllIIIllIIllIlllIIlIlIIIllIIllIIIlIIIllIIllIIllIIIlllIIll.getEnabled()) {
            final CompletableFuture empty = AsyncUtils.EMPTY;
            Intrinsics.checkNotNullExpressionValue((Object)empty, "");
            return empty;
        }
        final Object orDefault = MCMetadata.provide((Object)player).getOrDefault((MetaKey)AntiXrayKeys.INSTANCE.getAntiXrayBypass(), (Object)false);
        Intrinsics.checkNotNullExpressionValue(orDefault, "");
        if (orDefault) {
            final CompletableFuture empty2 = AsyncUtils.EMPTY;
            Intrinsics.checkNotNullExpressionValue((Object)empty2, "");
            return empty2;
        }
        if (illIIlIIlIlIIIlllIIlIIlIIlIllIIlIllIIlllIlllIlllIIllIlIIlllIlIlIIlllIIlIllIlllIIlllIlllIlIIIlllIlllIIIllIIllIlllIIllIIlllIlllIIlllIllIIlIlIllIlIlIlIlIIllIlIllIllIlllIIllIIIlllIlllIllIIllIlIllIlIIlIllIIllIlll.IllIIlllIIIlIIllIIlIIllIIlIIlllIIIllIllIIlllIIlIIllIIllIIIllIlllIlIlIlllIlIIllIIIlllIllIlIIlllIIlllIIllIIIlIIIllIIlIIIlIllIIIllIIllIIllIIlIIIlIIlIIllIllIlIlIIlllIIlIIlIllIllIIllIIlIIIlIIlllIllIIIlllIlllIIlllIllIIlIIIlllIIllIl().size() == 1) {
            return this.IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(player, illIIlIIlIlIIIlllIIlIIlIIlIllIIlIllIIlllIlllIlllIIllIlIIlllIlIlIIlllIIlIllIlllIIlllIlllIlIIIlllIlllIIIllIIllIlllIIllIIlllIlllIIlllIllIIlIlIllIlIlIlIlIIllIlIllIllIlllIIllIIIlllIlllIllIIllIlIllIlIIlIllIIllIlll.IllIIlllIIIlIIllIIlIIllIIlIIlllIIIllIllIIlllIIlIIllIIllIIIllIlllIlIlIlllIlIIllIIIlllIllIlIIlllIIlllIIllIIIlIIIllIIlIIIlIllIIIllIIllIIllIIlIIIlIIlIIllIllIlIlIIlllIIlIIlIllIllIIllIIlIIIlIIlllIllIIIlllIlllIIlllIllIIlIIIlllIIllIl().get(0));
        }
        int i = 0;
        final int size = illIIlIIlIlIIIlllIIlIIlIIlIllIIlIllIIlllIlllIlllIIllIlIIlllIlIlIIlllIIlIllIlllIIlllIlllIlIIIlllIlllIIIllIIllIlllIIllIIlllIlllIIlllIllIIlIlIllIlIlIlIlIIllIlIllIllIlllIIllIIIlllIlllIllIIllIlIllIlIIlIllIIllIlll.IllIIlllIIIlIIllIIlIIllIIlIIlllIIIllIllIIlllIIlIIllIIllIIIllIlllIlIlIlllIlIIllIIIlllIllIlIIlllIIlllIIllIIIlIIIllIIlIIIlIllIIIllIIllIIllIIlIIIlIIlIIllIllIlIlIIlllIIlIIlIllIllIIllIIlIIIlIIlllIllIIIlllIlllIIlllIllIIlIIIlllIIllIl().size();
        final CompletableFuture[] array = new CompletableFuture[size];
        while (i < size) {
            final int n = i;
            array[n] = this.IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(player, illIIlIIlIlIIIlllIIlIIlIIlIllIIlIllIIlllIlllIlllIIllIlIIlllIlIlIIlllIIlIllIlllIIlllIlllIlIIIlllIlllIIIllIIllIlllIIllIIlllIlllIIlllIllIIlIlIllIlIlIlIlIIllIlIllIllIlllIIllIIIlllIlllIllIIllIlIllIlIIlIllIIllIlll.IllIIlllIIIlIIllIIlIIllIIlIIlllIIIllIllIIlllIIlIIllIIllIIIllIlllIlIlIlllIlIIllIIIlllIllIlIIlllIIlllIIllIIIlIIIllIIlIIIlIllIIIllIIllIIllIIlIIIlIIlIIllIllIlIlIIlllIIlIIlIllIllIIllIIlIIIlIIlllIllIIIlllIlllIIlllIllIIlIIIlllIIllIl().get(n));
            ++i;
        }
        final CompletableFuture[] original = array;
        final CompletableFuture<Void> all = CompletableFuture.allOf((CompletableFuture<?>[])Arrays.copyOf(original, original.length));
        Intrinsics.checkNotNullExpressionValue((Object)all, "");
        return all;
    }
    
    private final CompletableFuture<?> IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(final Player player, final ChunkData chunkData) {
        final CompletableFuture<Object> thenCompose = this.IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(chunkData.getCoords()).thenCompose((Function<? super CachedChunkRegion, ? extends CompletionStage<Object>>)IlllIIlIIIlllIIIlIIllIlIllIIIlIIIllIlllIIIllIIlIIllIIllIlllIIllIIllIIllIIllIIlllIIlllIllIIllIlllIlIllIIlllIllIllIIlllIIllIIIlllIIIllIIIllIlllIIlllIIlIlIlIlIllIIIlllIIlIllIIllIIlllIIlIIIllIIIllIIlIIlllIlIllIllIIlIIIllIllIIlIIllIll::IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll);
        Intrinsics.checkNotNullExpressionValue((Object)thenCompose, "");
        return thenCompose;
    }
    
    private final CompletableFuture<CachedChunkRegion> IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(final CoordXZ coordXZ) {
        final DirectChunk[] array = new DirectChunk[9];
        int i = 0;
        int n = 0;
        for (int j = -1; j < 2; ++j) {
            for (int k = -1; k < 2; ++k) {
                final DirectChunk value = this.IIllIIlIlIIlllIIllIIllIIIlllIIIlllIllIIllIIlllIlIIllIIllIllIIlIIllIIlIlllIIIlIIlIllIIIllIIIlllIIlIIIlllIIIlllIIlIllIIIllIIlIlIllIlIIllIlIIIlllIIllIIlIIIlIIlIIlIlIlllIlIIIlIIlIIlllIllIllIllIIIlllIIllIIIllIIllIIlllIIlIllIlIIll.get(coordXZ.x + j, coordXZ.z + k);
                if (value == null) {
                    i |= 1 << n;
                }
                array[n] = value;
                ++n;
            }
        }
        if (i == 0) {
            final CompletableFuture<CachedChunkRegion> completedFuture = CompletableFuture.completedFuture(new CachedChunkRegion(array));
            Intrinsics.checkNotNullExpressionValue((Object)completedFuture, "");
            return completedFuture;
        }
        final ArrayList list = new ArrayList<CompletableFuture<Void>>(Integer.bitCount(i));
        int n2 = 0;
        for (int l = -1; l < 2; ++l) {
            for (int n3 = -1; n3 < 2; ++n3) {
                if ((i & 1 << n2) != 0x0) {
                    ((Collection<CompletableFuture<Void>>)list).add(this.IIllIIlIlIIlllIIllIIllIIIlllIIIlllIllIIllIIlllIlIIllIIllIllIIlIIllIIlIlllIIIlIIlIllIIIllIIIlllIIlIIIlllIIIlllIIlIllIIIllIIlIlIllIlIIllIlIIIlllIIllIIlIIIlIIlIIlIlIlllIlIIIlIIlIIlllIllIllIllIIIlllIIllIIIllIIllIIlllIIlIllIlIIll.getOrLoadAsync(coordXZ.x + l, coordXZ.z + n3, l == 0 && n3 == 0).thenAccept(IlllIIlIIIlllIIIlIIllIlIllIIIlIIIllIlllIIIllIIlIIllIIllIlllIIllIIllIIllIIllIIlllIIlllIllIIllIlllIlIllIIlllIllIllIIlllIIllIIIlllIIIllIIIllIlllIIlllIIlIlIlIlIllIIIlllIIlIllIIllIIlllIIlIIIllIIIllIIlIIlllIlIllIllIIlIIIllIllIIlIIllIll::IIllIllIIllIlIIlIlIIlllIllIIllIIllIlIIllIIIllIIIllIIllIIIlIllIIlIIlllIllIIlIllIllIIlIIlIlIIIlIllIIllIlllIllIIIllIIlIIlllIllIIlIIIlIIllIIllIIlIlIIlIllIIlllIlIIlllIIlIlIIlIIlIlllIIlIIIlIIllIllIlllIIlllIIIlIIllIIIlll));
                }
                ++n2;
            }
        }
        final CompletableFuture[] original = ((Collection<CompletableFuture<Void>>)list).toArray(new CompletableFuture[0]);
        final CompletableFuture<Object> thenApply = (CompletableFuture<Object>)CompletableFuture.allOf((CompletableFuture<?>[])Arrays.copyOf(original, original.length)).thenApply((Function<? super Void, ? extends CachedChunkRegion>)IlllIIlIIIlllIIIlIIllIlIllIIIlIIIllIlllIIIllIIlIIllIIllIlllIIllIIllIIllIIllIIlllIIlllIllIIllIlllIlIllIIlllIllIllIIlllIIllIIIlllIIIllIIIllIlllIIlllIIlIlIlIlIllIIIlllIIlIllIIllIIlllIIlIIIllIIIllIIlIIlllIlIllIllIIlIIIllIllIIlIIllIll::IlllIIlIIIlllIIIlIIllIlIllIIIlIIIllIlllIIIllIIlIIllIIllIlllIIllIIllIIllIIllIIlllIIlllIllIIllIlllIlIllIIlllIllIllIIlllIIllIIIlllIIIllIIIllIlllIIlllIIlIlIlIlIllIIIlllIIlIllIIllIIlllIIlIIIllIIIllIIlIIlllIlIllIllIIlIIIllIllIIlIIllIll);
        Intrinsics.checkNotNullExpressionValue((Object)thenApply, "");
        return (CompletableFuture<CachedChunkRegion>)thenApply;
    }
    
    private static final void IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(final IRaytracePlayer raytracePlayer, final BlockPosition blockPosition) {
        Intrinsics.checkNotNullParameter((Object)raytracePlayer, "");
        Intrinsics.checkNotNullParameter((Object)blockPosition, "");
        raytracePlayer.queueRemoveBlock(blockPosition);
    }
    
    private static final void IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(final IlllIIlIIIlllIIIlIIllIlIllIIIlIIIllIlllIIIllIIlIIllIIllIlllIIllIIllIIllIIllIIlllIIlllIllIIllIlllIlIllIIlllIllIllIIlllIIllIIIlllIIIllIIIllIlllIIlllIIlIlIlIlIllIIIlllIIlIllIIllIIlllIIlIIIllIIIllIIlIIlllIlIllIllIIlIIIllIllIIlIIllIll illlIIlIIIlllIIIlIIllIlIllIIIlIIIllIlllIIIllIIlIIllIIllIlllIIllIIllIIllIIllIIlllIIlllIllIIllIlllIlIllIIlllIllIllIIlllIIllIIIlllIIIllIIIllIlllIIlllIIlIlIlIlIllIIIlllIIlIllIIllIIlllIIlIIIllIIIllIIlIIlllIlIllIllIIlIIIllIllIIlIIllIll, final BlockPosition blockPosition) {
        Intrinsics.checkNotNullParameter((Object)illlIIlIIIlllIIIlIIllIlIllIIIlIIIllIlllIIIllIIlIIllIIllIlllIIllIIllIIllIIllIIlllIIlllIllIIllIlllIlIllIIlllIllIllIIlllIIllIIIlllIIIllIIIllIlllIIlllIIlIlIlIlIllIIIlllIIlIllIIllIIlllIIlIIIllIIIllIIlIIlllIlIllIllIIlIIIllIllIIlIIllIll, "");
        Intrinsics.checkNotNullParameter((Object)blockPosition, "");
        final DirectChunk value = illlIIlIIIlllIIIlIIllIlIllIIIlIIIllIlllIIIllIIlIIllIIllIlllIIllIIllIIllIIllIIlllIIlllIllIIllIlllIlIllIIlllIllIllIIlllIIllIIIlllIIIllIIIllIlllIIlllIIlIlIlIlIllIIIlllIIlIllIIllIIlllIIlIIIllIIIllIIlIIlllIlIllIllIIlIIIllIllIIlIIllIll.IIllIIlIlIIlllIIllIIllIIIlllIIIlllIllIIllIIlllIlIIllIIllIllIIlIIllIIlIlllIIIlIIlIllIIIllIIIlllIIlIIIlllIIIlllIIlIllIIIllIIlIlIllIlIIllIlIIIlllIIllIIlIIIlIIlIIlIlIlllIlIIIlIIlIIlllIllIllIllIIIlllIIllIIIllIIllIIlllIIlIllIlIIll.get(blockPosition.getX() >> 4, blockPosition.getZ() >> 4);
        if (value != null) {
            for (final IRaytracePlayer raytracePlayer : value.getView().getViewers()) {
                illlIIlIIIlllIIIlIIllIlIllIIIlIIIllIlllIIIllIIlIIllIIllIlllIIllIIllIIllIIllIIlllIIlllIllIIllIlllIlIllIIlllIllIllIIlllIIllIIIlllIIIllIIIllIlllIIlllIIlIlIlIlIllIIIlllIIlIllIIllIIlllIIlIIIllIIIllIIlIIlllIlIllIllIIlIIIllIllIIlIIllIll.IIlllIIIllIIllIIllIlIIIlIIIllIIIlIIlllIIIlIlllIIlIIllIlIlIlIlIIIlllIIIlllIIIlllIIIlllIIlIllIIllIlllIllIIlllIlllIlllIIlIIlIIIllIllIIIlllIIllIIlIlllIIllIIIllIIlIIllIIIlIllIIIllIIllIIIlIIllIIlllIIlllIIIllIllIIIllIIllIIllIlIllIllIIIlIIllIIl.IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(raytracePlayer.getPlayer(), IlllIIlIIIlllIIIlIIllIlIllIIIlIIIllIlllIIIllIIlIIllIIllIlllIIllIIllIIllIIllIIlllIIlllIllIIllIlllIlIllIIlllIllIllIIlllIIllIIIlllIIIllIIIllIlllIIlllIIlIlIlIlIllIIIlllIIlIllIIllIIlllIIlIIIllIIIllIIlIIlllIlIllIllIIlIIIllIllIIlIIllIll::IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll);
            }
        }
    }
    
    private static final void IIllIllIIllIlIIlIlIIlllIllIIllIIllIlIIllIIIllIIIllIIllIIIlIllIIlIIlllIllIIlIllIllIIlIIlIlIIIlIllIIllIlllIllIIIllIIlIIlllIllIIlIIIlIIllIIllIIlIlIIlIllIIlllIlIIlllIIlIlIIlIIlIlllIIlIIIlIIllIllIlllIIlllIIIlIIllIIIlll(final Player player, final int n, final int n2) {
        Intrinsics.checkNotNullParameter((Object)player, "");
        final IIIllIIlIllIIIlllIIlIIlllIIIlllIIlIlIlIIIllIlIllIIIlllIllIIlIllIllIIIllIIllIllIllIllIllIIlIllIIIllIllIIIlIIlllIIllIlllIIIlIllIIlIlIIlllIIIllIlllIllIllIIllIIllIIIllIIIllIIllIllIllIIIllIIlllIIIlIIIllIllIIllIIIllIIIlllIIIllIIllIIllIIIl iIlllIlIIllIlIIlIlllIlIlIIIlIIIlllIllIlIIllIlllIlIIllIllIIllIIlllIlllIIllIIllIIlIllIlIIIlIlllIIlIIIllIlllIllIllIllIIllIllIlIIlIllIIllIIIllIllIIlllIIlIllIIlIIIllIIIllIlIIIlIIlllIIIlIIllIIllIlIllIIllIIIllIlllIllIIl = IllIlIIllIIllIIllIIIlllIIIlIllIllIlIIlIIllIlllIIIllIlllIIlIllIIlllIIlllIlllIllIIIlllIllIllIlIIllIIllIIllIllIIIllIIIlIIIllIIlIllIlIIIlIIllIllIIIllIllIIlllIIlIllIIlIIIlIIlIllIllIlIlllIIlllIIlIIlllIllIIllIlIIllIlIIllIIIll.IIlllIlIIllIlIIlIlllIlIlIIIlIIIlllIllIlIIllIlllIlIIllIllIIllIIlllIlllIIllIIllIIlIllIlIIIlIlllIIlIIIllIlllIllIllIllIIllIllIlIIlIllIIllIIIllIllIIlllIIlIllIIlIIIllIIIllIlIIIlIIlllIIIlIIllIIllIlIllIIllIIIllIlllIllIIl(player);
        if (iIlllIlIIllIlIIlIlllIlIlIIIlIIIlllIllIlIIllIlllIlIIllIllIIllIIlllIlllIIllIIllIIlIllIlIIIlIlllIIlIIIllIlllIllIllIllIIllIllIlIIlIllIIllIIIllIllIIlllIIlIllIIlIIIllIIIllIlIIIlIIlllIIIlIIllIIllIlIllIIllIIIllIlllIllIIl == null) {
            return;
        }
        iIlllIlIIllIlIIlIlllIlIlIIIlIIIlllIllIlIIllIlllIlIIllIllIIllIIlllIlllIIllIIllIIlIllIlIIIlIlllIIlIIIllIlllIllIllIllIIllIllIlIIlIllIIllIIIllIllIIlllIIlIllIIlIIIllIIIllIlIIIlIIlllIIIlIIllIIllIlIllIIllIIIllIlllIllIIl.IIllIllIlIlllIIlllIIlllIlIIIllIIlIIllIIllIIlIIIlllIIIlIIlllIIlllIIIlIlIIlIIllIlIIIllIIlIIllIIlIIlIIlllIIIllIlllIIlIIlllIllIllIIlllIlllIIlllIlIIIllIIllIlIlIIIllIlIIIlIIIllIIllIIlIlllIllIllIIIlIIIlIIllIIllIlIIIlIIIlIIllIIIllIIlll(n, n2);
    }
    
    private static final CompletionStage IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(final Function1 function1, final Object o) {
        Intrinsics.checkNotNullParameter((Object)function1, "");
        return (CompletionStage)function1.invoke(o);
    }
    
    private static final void IIllIllIIllIlIIlIlIIlllIllIIllIIllIlIIllIIIllIIIllIIllIIIlIllIIlIIlllIllIIlIllIllIIlIIlIlIIIlIllIIllIlllIllIIIllIIlIIlllIllIIlIIIlIIllIIllIIlIlIIlIllIIlllIlIIlllIIlIlIIlIIlIlllIIlIIIlIIllIllIlllIIlllIIIlIIllIIIlll(final Function1 function1, final Object o) {
        Intrinsics.checkNotNullParameter((Object)function1, "");
        function1.invoke(o);
    }
    
    private static final CachedChunkRegion IlllIIlIIIlllIIIlIIllIlIllIIIlIIIllIlllIIIllIIlIIllIIllIlllIIllIIllIIllIIllIIlllIIlllIllIIllIlllIlIllIIlllIllIllIIlllIIllIIIlllIIIllIIIllIlllIIlllIIlIlIlIlIllIIIlllIIlIllIIllIIlllIIlIIIllIIIllIIlIIlllIlIllIllIIlIIIllIllIIlIIllIll(final Function1 function1, final Object o) {
        Intrinsics.checkNotNullParameter((Object)function1, "");
        return (CachedChunkRegion)function1.invoke(o);
    }
    
    public static final /* synthetic */ ExecutorService IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(final IlllIIlIIIlllIIIlIIllIlIllIIIlIIIllIlllIIIllIIlIIllIIllIlllIIllIIllIIllIIllIIlllIIlllIllIIllIlllIlIllIIlllIllIllIIlllIIllIIIlllIIIllIIIllIlllIIlllIIlIlIlIlIllIIIlllIIlIllIIllIIlllIIlIIIllIIIllIIlIIlllIlIllIllIIlIIIllIllIIlIIllIll illlIIlIIIlllIIIlIIllIlIllIIIlIIIllIlllIIIllIIlIIllIIllIlllIIllIIllIIllIIllIIlllIIlllIllIIllIlllIlIllIIlllIllIllIIlllIIllIIIlllIIIllIIIllIlllIIlllIIlIlIlIlIllIIIlllIIlIllIIllIIlllIIlIIIllIIIllIIlIIlllIlIllIllIIlIIIllIllIIlIIllIll) {
        return illlIIlIIIlllIIIlIIllIlIllIIIlIIIllIlllIIIllIIlIIllIIllIlllIIllIIllIIllIIllIIlllIIlllIllIIllIlllIlIllIIlllIllIllIIlllIIllIIIlllIIIllIIIllIlllIIlllIIlIlIlIlIllIIIlllIIlIllIIllIIlllIIlIIIllIIIllIIlIIlllIlIllIllIIlIIIllIllIIlIIllIll.IllIllIIIlIllIllIIllIIIlllIlIllIIlIIlIlIIllIllIIIlllIllIllIIIllIlIlIIIlIIIllIIllIIlIllIIlIIlllIIlIIlllIllIIIllIIllIIIlIllIlllIIllIIIllIlIlIIIlIlIIllIIllIIlllIIIlIIllIllIllIIIlllIIlllIllIIlIIlIIlIIllIIllIIlIIIlllIIlIIlll;
    }
    
    public static final /* synthetic */ List IIllIllIIllIlIIlIlIIlllIllIIllIIllIlIIllIIIllIIIllIIllIIIlIllIIlIIlllIllIIlIllIllIIlIIlIlIIIlIllIIllIlllIllIIIllIIlIIlllIllIIlIIIlIIllIIllIIlIlIIlIllIIlllIlIIlllIIlIlIIlIIlIlllIIlIIIlIIllIllIlllIIlllIIIlIIllIIIlll(final IlllIIlIIIlllIIIlIIllIlIllIIIlIIIllIlllIIIllIIlIIllIIllIlllIIllIIllIIllIIllIIlllIIlllIllIIllIlllIlIllIIlllIllIllIIlllIIllIIIlllIIIllIIIllIlllIIlllIIlIlIlIlIllIIIlllIIlIllIIllIIlllIIlIIIllIIIllIIlIIlllIlIllIllIIlIIIllIllIIlIIllIll illlIIlIIIlllIIIlIIllIlIllIIIlIIIllIlllIIIllIIlIIllIIllIlllIIllIIllIIllIIllIIlllIIlllIllIIllIlllIlIllIIlllIllIllIIlllIIllIIIlllIIIllIIIllIlllIIlllIIlIlIlIlIllIIIlllIIlIllIIllIIlllIIlIIIllIIIllIIlIIlllIlIllIllIIlIIIllIllIIlIIllIll) {
        return illlIIlIIIlllIIIlIIllIlIllIIIlIIIllIlllIIIllIIlIIllIIllIlllIIllIIllIIllIIllIIlllIIlllIllIIllIlllIlIllIIlllIllIllIIlllIIllIIIlllIIIllIIIllIlllIIlllIIlIlIlIlIllIIIlllIIlIllIIllIIlllIIlIIIllIIIllIIlIIlllIlIllIllIIlIIIllIllIIlIIllIll.IlIllIIlllIIlllIIlllIIIllIIIllIIlllIlIIllIllIIIllIlIIlllIlIIlIIllIllIlIIlIllIIIllIIllIIIllIllIlIIIlllIIIlIIlllIIlIIIlllIIIllIIIllIIlllIIllIIlIIIllIIllIIlllIIllIlIllIIllIIllIllIIIllIIllIIlIIIlIIlllIIIlIIlllIIlIIIllIlIlllIlIllIIlIIIll;
    }
}
