// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.internal.protocol;

import io.fairyproject.libs.packetevents.protocol.world.chunk.BaseChunk;
import io.fairyproject.libs.packetevents.wrapper.play.server.WrapperPlayServerChunkData;
import io.fairyproject.libs.packetevents.netty.buffer.ByteBufHelper;
import dev.imanity.antixray.a.IlllIIlIIIlllIIIlIIllIlIllIIIlIIIllIlllIIIllIIlIIllIIllIlllIIllIIllIIllIIllIIlllIIlllIllIIllIlllIlIllIIlllIllIllIIlllIIllIIIlllIIIllIIIllIlllIIlllIIlIlIlIlIllIIIlllIIlIllIIllIIlllIIlIIIllIIIllIIlIIlllIlIllIllIIlIIIllIllIIlIIllIll;
import java.util.function.BiConsumer;
import kotlin.jvm.functions.Function2;
import dev.imanity.antixray.a.IlIllIIIllIIIlIIIlIIlIIIlIIlllIIllIIllIlllIlIIlIllIIIlIIlIllIIllIllIIllIIllIIlllIllIllIIlllIIIlIIIllIIlIlIIIllIIIlIIlIllIIlIIllIIIllIIllIlIIlllIIlIIIlIllIIIlIIIllIIlllIIllIIlllIllIIIlllIIIlllIllIllIllIIlIIlllIIIlllIIlllIIIlllIllIIll;
import dev.imanity.antixray.internal.wrapper.WrapperPlayServerChunkDataBulk;
import dev.imanity.antixray.a.IIllIIlIIlIlIIIlllIIlIIlIIlIllIIlIllIIlllIlllIlllIIllIlIIlllIlIlIIlllIIlIllIlllIIlllIlllIlIIIlllIlllIIIllIIllIlllIIllIIlllIlllIIlllIllIIlIlIllIlIlIlIlIIllIlIllIllIlllIIllIIIlllIlllIllIIllIlIllIlIIlIllIIllIlll;
import java.util.UUID;
import dev.imanity.antixray.a.IIllIIlllIIllIlllIIlIlllIIIllIIlllIlIlIIIllIIlllIIIlllIIIlIIllIIIlIIlIIllIlllIIlllIIlllIllIIllIIlllIlIIIllIIlIIlllIIlIIIlIIlllIIllIIlllIIIlIllIlllIIllIIlIIlllIIIllIIlllIIlllIIllIIlIIllIIlllIIlllIIllIllIllIIIllIIIlllIllIIllIIlllIIlIllIlIIllIIl;
import io.fairyproject.libs.packetevents.wrapper.play.server.WrapperPlayServerUnloadChunk;
import dev.imanity.antixray.api.block.RaytraceBlock;
import org.bukkit.World;
import kotlin.Unit;
import dev.imanity.antixray.a.IlIIlllIllIIIllIIllIIIlllIIIlIIIlllIIlIIlllIIIlIllIIllIlIIlIIlIlIIlllIIlIIllIlIlllIIIlIIIlIIIlIIlIlllIIlIIIllIIIllIIIlIlIlllIIlIIlllIIlllIIllIlIllIlllIIIlllIlIlIIllIllIlllIIlIIlIIIllIlllIllIlllIIIlIllIllIllIIllIlllIIlIIIll;
import dev.imanity.antixray.a.IllIlIIlIIlllIllIIllIIIlllIlllIllIIIllIIllIIlllIllIlllIIllIlIIlIlIIllIlIIllIIIlllIIIlIIllIllIIlIIlIIIllIIIllIIlllIIllIIlIIIlIIlIlllIlllIlllIllIIlllIIlIlllIIIlIIIllIlllIIllIllIIllIIlIIIllIIllIlIlIlllIlIIlIIllIllIIllIIIllIl;
import dev.imanity.antixray.a.IllIlllIIlIIIlIIIlIIIlllIlllIIIlIIllIIlIIlIIIllIIlIllIIIllIlllIlIIlllIIlllIllIIIllIllIIlllIIlIlIIlIIlIllIlllIIlllIllIlIllIlIIllIIlIIIllIlIIIlIIIlIIllIIlIlIIIllIIlIllIIlIIlIIllIllIlllIIIlIIIlIIllIIlllIllIllIIllIlIl;
import io.netty.channel.Channel;
import dev.imanity.antixray.a.IIIllIIlIllIIIlllIIlIIlllIIIlllIIlIlIlIIIllIlIllIIIlllIllIIlIllIllIIIllIIllIllIllIllIllIIlIllIIIllIllIIIlIIlllIIllIlllIIIlIllIIlIlIIlllIIIllIlllIllIllIIllIIllIIIllIIIllIIllIllIllIIIllIIlllIIIlIIIllIllIIllIIIllIIIlllIIIllIIllIIllIIIl;
import io.fairyproject.libs.packetevents.wrapper.play.client.WrapperPlayClientPong;
import io.fairyproject.libs.packetevents.wrapper.play.client.WrapperPlayClientWindowConfirmation;
import io.fairyproject.libs.packetevents.protocol.world.chunk.LightData;
import java.util.concurrent.ConcurrentHashMap;
import io.fairyproject.mc.util.BlockPosition;
import io.fairyproject.libs.packetevents.wrapper.PacketWrapper;
import kotlin.jvm.functions.Function1;
import dev.imanity.antixray.api.util.LongHash;
import io.fairyproject.libs.packetevents.wrapper.play.server.WrapperPlayServerUpdateLight;
import dev.imanity.antixray.a.IIllIlllIIIlIIllIIllIlIIllIllIlllIllIIlIIllIlIIIlIIllIIlIIIllIlllIIlIlllIllIIIlllIlIIllIIlIIlIllIIIlIIlIIllIIIlllIIlIIIlllIllIIIlIIlIIllIIllIlIllIllIIllIIlllIIlIIIllIIllIIIllIIlIllIIlIIIlIIlllIlIIlllIIIlllIlIIllIIIllIIlIIl;
import io.fairyproject.libs.packetevents.event.PacketSendEvent;
import io.fairyproject.libs.packetevents.protocol.packettype.PacketTypeCommon;
import io.fairyproject.libs.packetevents.protocol.packettype.PacketType;
import io.fairyproject.libs.packetevents.protocol.ConnectionState;
import io.fairyproject.libs.packetevents.event.PacketReceiveEvent;
import java.util.function.Consumer;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.EventPriority;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerLoginEvent;
import io.fairyproject.container.PreDestroy;
import java.util.Iterator;
import java.util.Collection;
import org.bukkit.Bukkit;
import io.fairyproject.libs.packetevents.event.PacketListenerCommon;
import io.fairyproject.libs.packetevents.PacketEvents;
import io.fairyproject.container.PostInitialize;
import io.fairyproject.log.Log;
import io.fairyproject.data.MetaKey;
import dev.imanity.antixray.AntiXrayKeys;
import dev.imanity.antixray.a.IllIlIIllIIllIIllIIIlllIIIlIllIllIlIIlIIllIlllIIIllIlllIIlIllIIlllIIlllIlllIllIIIlllIllIllIlIIllIIllIIllIllIIIllIIIlIIIllIIlIllIlIIIlIIllIllIIIllIllIIlllIIlIllIIlIIIlIIlIllIllIlIlllIIlllIIlIIlllIllIIllIlIIllIlIIllIIIll;
import dev.imanity.antixray.a.IlIIIlIIlIlllIIlllIIllIIIlllIIIlIllIIllIllIIIlIlllIllIlllIIlIlllIIlIIIlIllIIIlIIlIIIlllIllIlIlIlllIIllIlIlllIIlllIIIllIIllIIlIllIIllIIIlllIIllIIllIIllIIIllIIlIIlIIIllIIllIIlIIlllIIlIllIIlllIIllIllIIlllIllIIllIIIllIIIlllIIIlllIIllIll;
import org.bukkit.entity.Player;
import kotlin.LazyKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.Lazy;
import dev.imanity.antixray.api.tick.InitialTickProcessor;
import org.jetbrains.annotations.NotNull;
import dev.imanity.antixray.a.IIllIIlIIllIIlIIllIIllIIIllIlllIIlIllIIlIllIIIlIIlIIllIllIIIllIIlIIlllIIIlllIIIlIllIIlIIlIlIllIlIIlIIllIlIIIllIIlIIlIlllIIlIIlllIIIlllIlllIlllIIlIlIIlIIlllIIIllIIllIlIIllIIllIIIllIIlIlIIIlIIIllIlIlIIlIlIIIlIIllIIlll;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.Metadata;
import io.fairyproject.bukkit.listener.RegisterAsListener;
import io.fairyproject.container.InjectableComponent;
import org.bukkit.event.Listener;
import io.fairyproject.libs.packetevents.event.PacketListenerAbstract;

@InjectableComponent
@RegisterAsListener
@Metadata(mv = { 1, 9, 0 }, k = 1, xi = 48, d1 = { "\u0000\u009a\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0000\b\u0007\u0018\u00002\u00020\u00012\u00020\u0002B\u0015\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\u0010\u0010\u0015\u001a\u00020\u000b2\u0006\u0010\u0016\u001a\u00020\u0017H\u0002J \u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u00122\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001eH\u0002J\u0010\u0010\u001f\u001a\u00020\u00192\u0006\u0010 \u001a\u00020!H\u0002J\u0010\u0010\"\u001a\u00020\u00192\u0006\u0010 \u001a\u00020!H\u0002J\u0010\u0010#\u001a\u00020\u00192\u0006\u0010 \u001a\u00020$H\u0002J\u0010\u0010%\u001a\u00020\u00192\u0006\u0010 \u001a\u00020!H\u0002JE\u0010&\u001a\u00020\u0019\"\f\b\u0000\u0010'*\u0006\u0012\u0002\b\u00030(2\u0006\u0010 \u001a\u00020!2\f\u0010)\u001a\b\u0012\u0004\u0012\u0002H'0*2\u0017\u0010+\u001a\u0013\u0012\u0004\u0012\u0002H'\u0012\u0004\u0012\u00020-0,¢\u0006\u0002\b.H\u0002J,\u0010/\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u00122\u0006\u0010\u001b\u001a\u00020\u001c2\n\u00100\u001a\u0006\u0012\u0002\b\u00030(2\u0006\u00101\u001a\u00020-H\u0002J\u0010\u00102\u001a\u00020\u00192\u0006\u0010 \u001a\u00020!H\u0002J\u0010\u00103\u001a\u00020\u00192\u0006\u0010 \u001a\u00020$H\u0002J\u0010\u00104\u001a\u00020\u00192\u0006\u0010 \u001a\u00020$H\u0016J\u0010\u00105\u001a\u00020\u00192\u0006\u0010 \u001a\u00020!H\u0016J\u0010\u00106\u001a\u00020\u00192\u0006\u0010 \u001a\u000207H\u0007J\u0010\u00108\u001a\u00020\u00192\u0006\u0010 \u001a\u000209H\u0007J\b\u0010:\u001a\u00020\u0019H\u0007J\b\u0010;\u001a\u00020\u0019H\u0007J*\u0010<\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u00122\u0006\u0010=\u001a\u00020>2\u0006\u0010?\u001a\u00020>2\b\u0010@\u001a\u0004\u0018\u00010AH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\n\u001a\u00020\u000b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\f\u0010\rR\u001a\u0010\u0010\u001a\u0004\u0018\u00010\u0011*\u00020\u00128BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014¨\u0006B" }, d2 = { "Ldev/imanity/antixray/internal/protocol/ChunkPacketListener;", "Lio/fairyproject/libs/packetevents/event/PacketListenerAbstract;", "Lorg/bukkit/event/Listener;", "asyncThreadServer", "Ldev/imanity/antixray/core/infrastructure/AsyncThreadServer;", "initialTickProcessor", "Ldev/imanity/antixray/api/tick/InitialTickProcessor;", "(Ldev/imanity/antixray/core/infrastructure/AsyncThreadServer;Ldev/imanity/antixray/api/tick/InitialTickProcessor;)V", "lastChunkParseWarnNanos", "", "unloadChunkPacket", "", "getUnloadChunkPacket", "()Z", "unloadChunkPacket$delegate", "Lkotlin/Lazy;", "packetQueue", "Ldev/imanity/antixray/core/protocol/PacketQueue;", "Lorg/bukkit/entity/Player;", "getPacketQueue", "(Lorg/bukkit/entity/Player;)Ldev/imanity/antixray/core/protocol/PacketQueue;", "canSendImmediate", "packetType", "Lio/fairyproject/libs/packetevents/protocol/packettype/PacketTypeCommon;", "finishObfuscation", "", "player", "channel", "Lio/netty/channel/Channel;", "chunkPacket", "Ldev/imanity/antixray/core/protocol/ChunkPacket;", "handleChunkBulkData", "event", "Lio/fairyproject/libs/packetevents/event/PacketSendEvent;", "handleChunkData", "handlePong", "Lio/fairyproject/libs/packetevents/event/PacketReceiveEvent;", "handleRespawn", "handleTileEntity", "T", "Lio/fairyproject/libs/packetevents/wrapper/PacketWrapper;", "getPacket", "Lkotlin/Function0;", "getPos", "Lkotlin/Function1;", "Lio/fairyproject/mc/util/BlockPosition;", "Lkotlin/ExtensionFunctionType;", "handleTileEntityPostQueue", "packet", "blockPos", "handleUnloadChunk", "handleWindowConfirmation", "onPacketReceive", "onPacketSend", "onPlayerJoin", "Lorg/bukkit/event/player/PlayerLoginEvent;", "onPlayerQuit", "Lorg/bukkit/event/player/PlayerQuitEvent;", "onPostInitialize", "onPreDestroy", "warnChunkParseFailureThrottled", "peSections", "", "realSections", "ex", "", "plugin" })
@SourceDebugExtension({ "SMAP\nChunkPacketListener.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ChunkPacketListener.kt\ndev/imanity/antixray/internal/protocol/ChunkPacketListener\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n*L\n1#1,449:1\n1855#2,2:450\n1855#2,2:454\n12271#3,2:452\n*S KotlinDebug\n*F\n+ 1 ChunkPacketListener.kt\ndev/imanity/antixray/internal/protocol/ChunkPacketListener\n*L\n87#1:450,2\n77#1:454,2\n415#1:452,2\n*E\n" })
public final class ChunkPacketListener extends PacketListenerAbstract implements Listener
{
    @NotNull
    private final IIllIIlIIllIIlIIllIIllIIIllIlllIIlIllIIlIllIIIlIIlIIllIllIIIllIIlIIlllIIIlllIIIlIllIIlIIlIlIllIlIIlIIllIlIIIllIIlIIlIlllIIlIIlllIIIlllIlllIlllIIlIlIIlIIlllIIIllIIllIlIIllIIllIIIllIIlIlIIIlIIIllIlIlIIlIlIIIlIIllIIlll asyncThreadServer;
    @NotNull
    private final InitialTickProcessor initialTickProcessor;
    @NotNull
    private final Lazy unloadChunkPacket$delegate;
    private volatile long lastChunkParseWarnNanos;
    
    public ChunkPacketListener(@NotNull final IIllIIlIIllIIlIIllIIllIIIllIlllIIlIllIIlIllIIIlIIlIIllIllIIIllIIlIIlllIIIlllIIIlIllIIlIIlIlIllIlIIlIIllIlIIIllIIlIIlIlllIIlIIlllIIIlllIlllIlllIIlIlIIlIIlllIIIllIIllIlIIllIIllIIIllIIlIlIIIlIIIllIlIlIIlIlIIIlIIllIIlll asyncThreadServer, @NotNull final InitialTickProcessor initialTickProcessor) {
        Intrinsics.checkNotNullParameter((Object)asyncThreadServer, "");
        Intrinsics.checkNotNullParameter((Object)initialTickProcessor, "");
        this.asyncThreadServer = asyncThreadServer;
        this.initialTickProcessor = initialTickProcessor;
        this.unloadChunkPacket$delegate = LazyKt.lazy((Function0)ChunkPacketListener$unloadChunkPacket.ChunkPacketListener$unloadChunkPacket$2.INSTANCE);
    }
    
    private final IlIIIlIIlIlllIIlllIIllIIIlllIIIlIllIIllIllIIIlIlllIllIlllIIlIlllIIlIIIlIllIIIlIIlIIIlllIllIlIlIlllIIllIlIlllIIlllIIIllIIllIIlIllIIllIIIlllIIllIIllIIllIIIllIIlIIlIIIllIIllIIlIIlllIIlIllIIlllIIllIllIIlllIllIIllIIIllIIIlllIIIlllIIllIll getPacketQueue(final Player player) {
        return (IlIIIlIIlIlllIIlllIIllIIIlllIIIlIllIIllIllIIIlIlllIllIlllIIlIlllIIlIIIlIllIIIlIIlIIIlllIllIlIlIlllIIllIlIlllIIlllIIIllIIllIIlIllIIllIIIlllIIllIIllIIllIIIllIIlIIlIIIllIIllIIlIIlllIIlIllIIlllIIllIllIIlllIllIIllIIIllIIIlllIIIlllIIllIll)IllIlIIllIIllIIllIIIlllIIIlIllIllIlIIlIIllIlllIIIllIlllIIlIllIIlllIIlllIlllIllIIIlllIllIllIlIIllIIllIIllIllIIIllIIIlIIIllIIlIllIlIIIlIIllIllIIIllIllIIlllIIlIllIIlIIIlIIlIllIllIlIlllIIlllIIlIIlllIllIIllIlIIllIlIIllIIIll.IllIllIIlllIIIllIIlllIIlIIlllIIIllIIIlllIIllIIIlIIIlIIIllIIllIlIIIllIlIIlllIIIlllIlIIlIIIlIIlIIlllIlIIlIlllIIllIIllIIllIIIlIIIlIIllIIllIllIIlIIIlllIllIIlIIIllIIllIIlIllIIIlllIIIllIIIllIIllIIlIIIllIIlllIIlllIIIlllIIIllIllIIlIIIlllIIlIIIllIIllIll(player).getOrNull((MetaKey)AntiXrayKeys.INSTANCE.getPacketQueueKey());
    }
    
    private final boolean getUnloadChunkPacket() {
        return (boolean)this.unloadChunkPacket$delegate.getValue();
    }
    
    private final void warnChunkParseFailureThrottled(final Player player, final int i, final int j, final Throwable t) {
        final long nanoTime = System.nanoTime();
        if (nanoTime - this.lastChunkParseWarnNanos < 30000000000L) {
            return;
        }
        this.lastChunkParseWarnNanos = nanoTime;
        final String string = "[RaytraceAntiXray] Outbound CHUNK_DATA could not be parsed/obfuscated for player='" + player.getName() + "' (PE sections=" + i + ", world sections=" + j + "); the ORIGINAL un-obfuscated chunk is forwarded as-is by PacketEvents. If PE sections != world sections, PacketEvents has the wrong dimension/world-height for this world (registry/DimensionType not synced) \u2014 the anti-xray cannot run there until that is fixed.";
        if (t != null) {
            Log.warn(string, t, new Object[0]);
        }
        else {
            Log.warn(string, new Object[0]);
        }
    }
    
    @PostInitialize
    public final void onPostInitialize() {
        this.initialTickProcessor.addTask(15, ChunkPacketListener::onPostInitialize$lambda$1);
    }
    
    @PreDestroy
    public final void onPreDestroy() {
        PacketEvents.getAPI().getEventManager().unregisterListener((PacketListenerCommon)this);
        final Collection onlinePlayers = Bukkit.getOnlinePlayers();
        Intrinsics.checkNotNullExpressionValue((Object)onlinePlayers, "");
        for (final Player player : onlinePlayers) {
            Intrinsics.checkNotNull((Object)player);
            IllIlIIllIIllIIllIIIlllIIIlIllIllIlIIlIIllIlllIIIllIlllIIlIllIIlllIIlllIlllIllIIIlllIllIllIlIIllIIllIIllIllIIIllIIIlIIIllIIlIllIlIIIlIIllIllIIIllIllIIlllIIlIllIIlIIIlIIlIllIllIlIlllIIlllIIlIIlllIllIIllIlIIllIlIIllIIIll.IllIllIIlllIIIllIIlllIIlIIlllIIIllIIIlllIIllIIIlIIIlIIIllIIllIlIIIllIlIIlllIIIlllIlIIlIIIlIIlIIlllIlIIlIlllIIllIIllIIllIIIlIIIlIIllIIllIllIIlIIIlllIllIIlIIIllIIllIIlIllIIIlllIIIllIIIllIIllIIlIIIllIIlllIIlllIIIlllIIIllIllIIlIIIlllIIlIIIllIIllIll(player).ifPresent((MetaKey)AntiXrayKeys.INSTANCE.getPacketQueueKey(), ChunkPacketListener::onPreDestroy$lambda$3$lambda$2);
        }
    }
    
    @EventHandler(priority = EventPriority.LOWEST)
    public final void onPlayerJoin(@NotNull final PlayerLoginEvent playerLoginEvent) {
        Intrinsics.checkNotNullParameter((Object)playerLoginEvent, "");
        final Player player = playerLoginEvent.getPlayer();
        Intrinsics.checkNotNullExpressionValue((Object)player, "");
        final Player player2 = player;
        if (IllIlIIllIIllIIllIIIlllIIIlIllIllIlIIlIIllIlllIIIllIlllIIlIllIIlllIIlllIlllIllIIIlllIllIllIlIIllIIllIIllIllIIIllIIIlIIIllIIlIllIlIIIlIIllIllIIIllIllIIlllIIlIllIIlIIIlIIlIllIllIlIlllIIlllIIlIIlllIllIIllIlIIllIlIIllIIIll.IllIllIlIIlllIIIlllIIIllIIIlIlIIIlllIIlIIllIIllIllIIIlIIIlllIIllIIIllIIllIIlllIlllIlIllIIlllIIllIIllIlIlllIIIllIIllIlllIIlllIIIlIIllIIIlllIlIIlIIlIllIlllIIllIllIIIllIIllIIIllIIllIIllIIllIIlIIlIIllIIIlIIIllIIlllIIIllIlIlllIllIIlIIIlllIll(player2)) {
            return;
        }
        IllIlIIllIIllIIllIIIlllIIIlIllIllIlIIlIIllIlllIIIllIlllIIlIllIIlllIIlllIlllIllIIIlllIllIllIlIIllIIllIIllIllIIIllIIIlIIIllIIlIllIlIIIlIIllIllIIIllIllIIlllIIlIllIIlIIIlIIlIllIllIlIlllIIlllIIlIIlllIllIIllIlIIllIlIIllIIIll.IllIllIIlllIIIllIIlllIIlIIlllIIIllIIIlllIIllIIIlIIIlIIIllIIllIlIIIllIlIIlllIIIlllIlIIlIIIlIIlIIlllIlIIlIlllIIllIIllIIllIIIlIIIlIIllIIllIllIIlIIIlllIllIIlIIIllIIllIIlIllIIIlllIIIllIIIllIIllIIlIIIllIIlllIIlllIIIlllIIIllIllIIlIIIlllIIlIIIllIIllIll(player2).put((MetaKey)AntiXrayKeys.INSTANCE.getPacketQueueKey(), (Object)new IlIIIlIIlIlllIIlllIIllIIIlllIIIlIllIIllIllIIIlIlllIllIlllIIlIlllIIlIIIlIllIIIlIIlIIIlllIllIlIlIlllIIllIlIlllIIlllIIIllIIllIIlIllIIllIIIlllIIllIIllIIllIIIllIIlIIlIIIllIIllIIlIIlllIIlIllIIlllIIllIllIIlllIllIIllIIIllIIIlllIIIlllIIllIll(player2));
    }
    
    @EventHandler
    public final void onPlayerQuit(@NotNull final PlayerQuitEvent playerQuitEvent) {
        Intrinsics.checkNotNullParameter((Object)playerQuitEvent, "");
        final Player player = playerQuitEvent.getPlayer();
        Intrinsics.checkNotNullExpressionValue((Object)player, "");
        final Player player2 = player;
        IllIlIIllIIllIIllIIIlllIIIlIllIllIlIIlIIllIlllIIIllIlllIIlIllIIlllIIlllIlllIllIIIlllIllIllIlIIllIIllIIllIllIIIllIIIlIIIllIIlIllIlIIIlIIllIllIIIllIllIIlllIIlIllIIlIIIlIIlIllIllIlIlllIIlllIIlIIlllIllIIllIlIIllIlIIllIIIll.IllIllIIlllIIIllIIlllIIlIIlllIIIllIIIlllIIllIIIlIIIlIIIllIIllIlIIIllIlIIlllIIIlllIlIIlIIIlIIlIIlllIlIIlIlllIIllIIllIIllIIIlIIIlIIllIIllIllIIlIIIlllIllIIlIIIllIIllIIlIllIIIlllIIIllIIIllIIllIIlIIIllIIlllIIlllIIIlllIIIllIllIIlIIIlllIIlIIIllIIllIll(player2).ifPresent((MetaKey)AntiXrayKeys.INSTANCE.getPacketQueueKey(), (Consumer)ChunkPacketListener::onPlayerQuit$lambda$4);
        IllIlIIllIIllIIllIIIlllIIIlIllIllIlIIlIIllIlllIIIllIlllIIlIllIIlllIIlllIlllIllIIIlllIllIllIlIIllIIllIIllIllIIIllIIIlIIIllIIlIllIlIIIlIIllIllIIIllIllIIlllIIlIllIIlIIIlIIlIllIllIlIlllIIlllIIlIIlllIllIIllIlIIllIlIIllIIIll.IllIllIIlllIIIllIIlllIIlIIlllIIIllIIIlllIIllIIIlIIIlIIIllIIllIlIIIllIlIIlllIIIlllIlIIlIIIlIIlIIlllIlIIlIlllIIllIIllIIllIIIlIIIlIIllIIllIllIIlIIIlllIllIIlIIIllIIllIIlIllIIIlllIIIllIIIllIIllIIlIIIllIIlllIIlllIIIlllIIIllIllIIlIIIlllIIlIIIllIIllIll(player2).remove((MetaKey)AntiXrayKeys.INSTANCE.getPacketQueueKey());
    }
    
    public void onPacketReceive(@NotNull final PacketReceiveEvent packetReceiveEvent) {
        Intrinsics.checkNotNullParameter((Object)packetReceiveEvent, "");
        if (packetReceiveEvent.getUser().getEncoderState() != ConnectionState.PLAY) {
            return;
        }
        final PacketTypeCommon packetType = packetReceiveEvent.getPacketType();
        if (packetType == PacketType.Play.Client.WINDOW_CONFIRMATION) {
            this.handleWindowConfirmation(packetReceiveEvent);
        }
        else if (packetType == PacketType.Play.Client.PONG) {
            this.handlePong(packetReceiveEvent);
        }
    }
    
    public void onPacketSend(@NotNull final PacketSendEvent packetSendEvent) {
        Intrinsics.checkNotNullParameter((Object)packetSendEvent, "");
        if (packetSendEvent.getUser().getEncoderState() != ConnectionState.PLAY) {
            return;
        }
        packetSendEvent.getPacketType();
        final PacketTypeCommon packetType = packetSendEvent.getPacketType();
        if (packetType == PacketType.Play.Server.CHUNK_DATA) {
            this.handleChunkData(packetSendEvent);
        }
        else if (packetType == PacketType.Play.Server.MAP_CHUNK_BULK) {
            this.handleChunkBulkData(packetSendEvent);
        }
        else if (packetType == PacketType.Play.Server.RESPAWN) {
            this.handleRespawn(packetSendEvent);
        }
        else if (packetType == PacketType.Play.Server.UNLOAD_CHUNK) {
            this.handleUnloadChunk(packetSendEvent);
        }
        else if (packetType == PacketType.Play.Server.UPDATE_LIGHT) {
            if (!IIllIlllIIIlIIllIIllIlIIllIllIlllIllIIlIIllIlIIIlIIllIIlIIIllIlllIIlIlllIllIIIlllIlIIllIIlIIlIllIIIlIIlIIllIIIlllIIlIIIlllIllIIIlIIlIIllIIllIlIllIllIIllIIlllIIlIIIllIIllIIIllIIlIllIIlIIIlIIlllIlIIlllIIIlllIlIIllIIIllIIlIIl.aW.IllIIlllIllIIIllIIlIIlIIllIIlllIIllIIIlIlIllIllIIllIllIllIIlIIlllIIlIllIlllIIllIlllIlIlllIIlllIIlllIlllIIlIlllIllIllIIIlIlIIllIIllIIllIllIIlllIlIIlllIIlllIIlllIIllIIlllIIlIIlllIlIIllIllIlIllIIIlllIllIIIlllIIllIlllIIlllIlIIll()) {
                return;
            }
            final WrapperPlayServerUpdateLight wrapperPlayServerUpdateLight = new WrapperPlayServerUpdateLight(packetSendEvent);
            final Player player = (Player)packetSendEvent.getPlayer();
            if (player != null) {
                final IlIIIlIIlIlllIIlllIIllIIIlllIIIlIllIIllIllIIIlIlllIllIlllIIlIlllIIlIIIlIllIIIlIIlIIIlllIllIlIlIlllIIllIlIlllIIlllIIIllIIllIIlIllIIllIIIlllIIllIIllIIllIIIllIIlIIlIIIllIIllIIlIIlllIIlIllIIlllIIllIllIIlllIllIIllIIIllIIIlllIIIlllIIllIll packetQueue = this.getPacketQueue(player);
                if (packetQueue != null) {
                    final ConcurrentHashMap<Long, LightData> iiIlIIllIIIlllIIlllIlIIIllIIllIlIIlllIIlllIlllIIlIIllIllIlllIIIlllIlllIlIIIllIIIlllIIIlIIlIlllIlllIIIlllIIlllIIlllIIllIIllIIlllIllIllIlIIIlIllIIllIllIIIlllIIllIIllIIIllIIIlIIIllIlIIlllIIlllIIIlllIllIIIllIIIllIIllIIlllIllIllIIlIIllIIIllIIIlllIIlllIll = packetQueue.IIIlIIllIIIlllIIlllIlIIIllIIllIlIIlllIIlllIlllIIlIIllIllIlllIIIlllIlllIlIIIllIIIlllIIIlIIlIlllIlllIIIlllIIlllIIlllIIllIIllIIlllIllIllIlIIIlIllIIllIllIIIlllIIllIIllIIIllIIIlIIIllIlIIlllIIlllIIIlllIllIIIllIIIllIIllIIlllIllIllIIlIIllIIIllIIIlllIIlllIll();
                    if (iiIlIIllIIIlllIIlllIlIIIllIIllIlIIlllIIlllIlllIIlIIllIllIlllIIIlllIlllIlIIIllIIIlllIIIlIIlIlllIlllIIIlllIIlllIIlllIIllIIllIIlllIllIllIlIIIlIllIIllIllIIIlllIIllIIllIIIllIIIlIIIllIlIIlllIIlllIIIlllIllIIIllIIIllIIllIIlllIllIllIIlIIllIIIllIIIlllIIlllIll != null) {
                        iiIlIIllIIIlllIIlllIlIIIllIIllIlIIlllIIlllIlllIIlIIllIllIlllIIIlllIlllIlIIIllIIIlllIIIlIIlIlllIlllIIIlllIIlllIIlllIIllIIllIIlllIllIllIlIIIlIllIIllIllIIIlllIIllIIllIIIllIIIlIIIllIlIIlllIIlllIIIlllIllIIIllIIIllIIllIIlllIllIllIIlIIllIIIllIIIlllIIlllIll.put(LongHash.INSTANCE.toLong(wrapperPlayServerUpdateLight.getX(), wrapperPlayServerUpdateLight.getZ()), wrapperPlayServerUpdateLight.getLightData());
                        packetSendEvent.setCancelled(true);
                    }
                }
            }
        }
        else if (packetType == PacketType.Play.Server.BLOCK_ENTITY_DATA) {
            this.handleTileEntity(packetSendEvent, (kotlin.jvm.functions.Function0<? extends PacketWrapper>)new ChunkPacketListener$onPacketSend.ChunkPacketListener$onPacketSend$2(packetSendEvent), (kotlin.jvm.functions.Function1<? super PacketWrapper, ? extends BlockPosition>)ChunkPacketListener$onPacketSend.ChunkPacketListener$onPacketSend$3.INSTANCE);
        }
        else if (packetType == PacketType.Play.Server.UPDATE_SIGN) {
            this.handleTileEntity(packetSendEvent, (kotlin.jvm.functions.Function0<? extends PacketWrapper>)new ChunkPacketListener$onPacketSend.ChunkPacketListener$onPacketSend$4(packetSendEvent), (kotlin.jvm.functions.Function1<? super PacketWrapper, ? extends BlockPosition>)ChunkPacketListener$onPacketSend.ChunkPacketListener$onPacketSend$5.INSTANCE);
        }
        else {
            final Player player2 = (Player)packetSendEvent.getPlayer();
            if (player2 == null) {
                return;
            }
            final IlIIIlIIlIlllIIlllIIllIIIlllIIIlIllIIllIllIIIlIlllIllIlllIIlIlllIIlIIIlIllIIIlIIlIIIlllIllIlIlIlllIIllIlIlllIIlllIIIllIIllIIlIllIIllIIIlllIIllIIllIIllIIIllIIlIIlIIIllIIllIIlIIlllIIlIllIIlllIIllIllIIlllIllIIllIIIllIIIlllIIIlllIIllIll packetQueue2 = this.getPacketQueue(player2);
            if (packetQueue2 == null) {
                return;
            }
            packetQueue2.IIIllIIllIIIllIIIlllIIlllIIIlllIlIIIlIIlIIIllIlllIIllIIlIIlllIIlIIlIlllIIlIIllIIllIIlIIlIIlIlIIlIIllIIllIllIIIlIllIIllIllIIlIIllIIlllIIlIlllIIIlllIIllIIlIIlIllIlIIIllIIlllIllIIIlllIIllIIlIIIlllIIIllIIIlllIIIlIIlIIIlIIIllIIlIIllIIllIIl();
        }
    }
    
    private final boolean canSendImmediate(final PacketTypeCommon packetTypeCommon) {
        return packetTypeCommon == PacketType.Play.Server.KEEP_ALIVE || packetTypeCommon == PacketType.Play.Server.CHAT_MESSAGE || packetTypeCommon == PacketType.Play.Server.SYSTEM_CHAT_MESSAGE || packetTypeCommon == PacketType.Play.Server.TAB_COMPLETE || packetTypeCommon == PacketType.Play.Server.TITLE || packetTypeCommon == PacketType.Play.Server.SET_TITLE_TEXT || packetTypeCommon == PacketType.Play.Server.SET_TITLE_SUBTITLE || packetTypeCommon == PacketType.Play.Server.SET_TITLE_TIMES || packetTypeCommon == PacketType.Play.Server.ACTION_BAR || packetTypeCommon == PacketType.Play.Server.CLEAR_TITLES || packetTypeCommon == PacketType.Play.Server.SOUND_EFFECT || packetTypeCommon == PacketType.Play.Server.NAMED_SOUND_EFFECT || packetTypeCommon == PacketType.Play.Server.ENTITY_SOUND_EFFECT || packetTypeCommon == PacketType.Play.Server.STOP_SOUND || packetTypeCommon == PacketType.Play.Server.PARTICLE || packetTypeCommon == PacketType.Play.Server.BOSS_BAR;
    }
    
    private final void handleWindowConfirmation(final PacketReceiveEvent packetReceiveEvent) {
        final Player player = (Player)packetReceiveEvent.getPlayer();
        if (player == null) {
            return;
        }
        final IlIIIlIIlIlllIIlllIIllIIIlllIIIlIllIIllIllIIIlIlllIllIlllIIlIlllIIlIIIlIllIIIlIIlIIIlllIllIlIlIlllIIllIlIlllIIlllIIIllIIllIIlIllIIllIIIlllIIllIIllIIllIIIllIIlIIlIIIllIIllIIlIIlllIIlIllIIlllIIllIllIIlllIllIIllIIIllIIIlllIIIlllIIllIll packetQueue = this.getPacketQueue(player);
        if (packetQueue == null) {
            return;
        }
        final IlIIIlIIlIlllIIlllIIllIIIlllIIIlIllIIllIllIIIlIlllIllIlllIIlIlllIIlIIIlIllIIIlIIlIIIlllIllIlIlIlllIIllIlIlllIIlllIIIllIIllIIlIllIIllIIIlllIIllIIllIIllIIIllIIlIIlIIIllIIllIIlIIlllIIlIllIIlllIIllIllIIlllIllIIllIIIllIIIlllIIIlllIIllIll ilIIIlIIlIlllIIlllIIllIIIlllIIIlIllIIllIllIIIlIlllIllIlllIIlIlllIIlIIIlIllIIIlIIlIIIlllIllIlIlIlllIIllIlIlllIIlllIIIllIIllIIlIllIIllIIIlllIIllIIllIIllIIIllIIlIIlIIIllIIllIIlIIlllIIlIllIIlllIIllIllIIlllIllIIllIIIllIIIlllIIIlllIIllIll = packetQueue;
        final WrapperPlayClientWindowConfirmation wrapperPlayClientWindowConfirmation = new WrapperPlayClientWindowConfirmation(packetReceiveEvent);
        if (wrapperPlayClientWindowConfirmation.getWindowId() == 0 && wrapperPlayClientWindowConfirmation.getActionId() == ilIIIlIIlIlllIIlllIIllIIIlllIIIlIllIIllIllIIIlIlllIllIlllIIlIlllIIlIIIlIllIIIlIIlIIIlllIllIlIlIlllIIllIlIlllIIlllIIIllIIllIIlIllIIllIIIlllIIllIIllIIllIIIllIIlIIlIIIllIIllIIlIIlllIIlIllIIlllIIllIllIIlllIllIIllIIIllIIIlllIIIlllIIllIll.IIIllIIllIIllIIIlIlIlllIIllIIllIIlIIlllIIIllIllIllIIlIllIIllIIIllIIIlllIlIIIlllIllIllIIIlIIlIIllIIIllIIlllIllIIllIIIllIIIllIlIllIlllIlIIIlllIllIIIlllIIIlIIlllIIllIIlllIIlllIIIllIllIIlllIIllIIllIllIlllIIllIIllIIllIIlllIIllIllIllIllIllIIl()) {
            ilIIIlIIlIlllIIlllIIllIIIlllIIIlIllIIllIllIIIlIlllIllIlllIIlIlllIIlIIIlIllIIIlIIlIIIlllIllIlIlIlllIIllIlIlllIIlllIIIllIIllIIlIllIIllIIIlllIIllIIllIIllIIIllIIlIIlIIIllIIllIIlIIlllIIlIllIIlllIIllIllIIlllIllIIllIIIllIIIlllIIIlllIIllIll.IIlIIlllIIlllIIllIIIlllIllIIIlIllIIllIllIIlIIlllIIlllIlIIIlllIIllIIllIIlllIllIIIllIlIIIllIlllIlIlllIllIIIlIlllIlllIIIllIIIlIllIIlllIIllIIllIllIIlllIIlllIIlIIlllIIIllIIlIIIlIllIlIlllIlllIIIlIIllIlIlllIIlIlllIlIIlllIIlllIIIlllIllIIlIll();
        }
    }
    
    private final void handlePong(final PacketReceiveEvent packetReceiveEvent) {
        final Player player = (Player)packetReceiveEvent.getPlayer();
        if (player == null) {
            return;
        }
        final IlIIIlIIlIlllIIlllIIllIIIlllIIIlIllIIllIllIIIlIlllIllIlllIIlIlllIIlIIIlIllIIIlIIlIIIlllIllIlIlIlllIIllIlIlllIIlllIIIllIIllIIlIllIIllIIIlllIIllIIllIIllIIIllIIlIIlIIIllIIllIIlIIlllIIlIllIIlllIIllIllIIlllIllIIllIIIllIIIlllIIIlllIIllIll packetQueue = this.getPacketQueue(player);
        if (packetQueue == null) {
            return;
        }
        final IlIIIlIIlIlllIIlllIIllIIIlllIIIlIllIIllIllIIIlIlllIllIlllIIlIlllIIlIIIlIllIIIlIIlIIIlllIllIlIlIlllIIllIlIlllIIlllIIIllIIllIIlIllIIllIIIlllIIllIIllIIllIIIllIIlIIlIIIllIIllIIlIIlllIIlIllIIlllIIllIllIIlllIllIIllIIIllIIIlllIIIlllIIllIll ilIIIlIIlIlllIIlllIIllIIIlllIIIlIllIIllIllIIIlIlllIllIlllIIlIlllIIlIIIlIllIIIlIIlIIIlllIllIlIlIlllIIllIlIlllIIlllIIIllIIllIIlIllIIllIIIlllIIllIIllIIllIIIllIIlIIlIIIllIIllIIlIIlllIIlIllIIlllIIllIllIIlllIllIIllIIIllIIIlllIIIlllIIllIll = packetQueue;
        if (new WrapperPlayClientPong(packetReceiveEvent).getId() == ilIIIlIIlIlllIIlllIIllIIIlllIIIlIllIIllIllIIIlIlllIllIlllIIlIlllIIlIIIlIllIIIlIIlIIIlllIllIlIlIlllIIllIlIlllIIlllIIIllIIllIIlIllIIllIIIlllIIllIIllIIllIIIllIIlIIlIIIllIIllIIlIIlllIIlIllIIlllIIllIllIIlllIllIIllIIIllIIIlllIIIlllIIllIll.IIIllIIllIIllIIIlIlIlllIIllIIllIIlIIlllIIIllIllIllIIlIllIIllIIIllIIIlllIlIIIlllIllIllIIIlIIlIIllIIIllIIlllIllIIllIIIllIIIllIlIllIlllIlIIIlllIllIIIlllIIIlIIlllIIllIIlllIIlllIIIllIllIIlllIIllIIllIllIlllIIllIIllIIllIIlllIIllIllIllIllIllIIl()) {
            ilIIIlIIlIlllIIlllIIllIIIlllIIIlIllIIllIllIIIlIlllIllIlllIIlIlllIIlIIIlIllIIIlIIlIIIlllIllIlIlIlllIIllIlIlllIIlllIIIllIIllIIlIllIIllIIIlllIIllIIllIIllIIIllIIlIIlIIIllIIllIIlIIlllIIlIllIIlllIIllIllIIlllIllIIllIIIllIIIlllIIIlllIIllIll.IIlIIlllIIlllIIllIIIlllIllIIIlIllIIllIllIIlIIlllIIlllIlIIIlllIIllIIllIIlllIllIIIllIlIIIllIlllIlIlllIllIIIlIlllIlllIIIllIIIlIllIIlllIIllIIllIllIIlllIIlllIIlIIlllIIIllIIlIIIlIllIlIlllIlllIIIlIIllIlIlllIIlIlllIlIIlllIIlllIIIlllIllIIlIll();
        }
    }
    
    private final void handleRespawn(final PacketSendEvent packetSendEvent) {
        final Player player = (Player)packetSendEvent.getPlayer();
        if (player == null) {
            return;
        }
        final Player player2 = player;
        final IlIIIlIIlIlllIIlllIIllIIIlllIIIlIllIIllIllIIIlIlllIllIlllIIlIlllIIlIIIlIllIIIlIIlIIIlllIllIlIlIlllIIllIlIlllIIlllIIIllIIllIIlIllIIllIIIlllIIllIIllIIllIIIllIIlIIlIIIllIIllIIlIIlllIIlIllIIlllIIllIllIIlllIllIIllIIIllIIIlllIIIlllIIllIll packetQueue = this.getPacketQueue(player2);
        if (packetQueue != null) {
            final ConcurrentHashMap<Long, LightData> iiIlIIllIIIlllIIlllIlIIIllIIllIlIIlllIIlllIlllIIlIIllIllIlllIIIlllIlllIlIIIllIIIlllIIIlIIlIlllIlllIIIlllIIlllIIlllIIllIIllIIlllIllIllIlIIIlIllIIllIllIIIlllIIllIIllIIIllIIIlIIIllIlIIlllIIlllIIIlllIllIIIllIIIllIIllIIlllIllIllIIlIIllIIIllIIIlllIIlllIll = packetQueue.IIIlIIllIIIlllIIlllIlIIIllIIllIlIIlllIIlllIlllIIlIIllIllIlllIIIlllIlllIlIIIllIIIlllIIIlIIlIlllIlllIIIlllIIlllIIlllIIllIIllIIlllIllIllIlIIIlIllIIllIllIIIlllIIllIIllIIIllIIIlIIIllIlIIlllIIlllIIIlllIllIIIllIIIllIIllIIlllIllIllIIlIIllIIIllIIIlllIIlllIll();
            if (iiIlIIllIIIlllIIlllIlIIIllIIllIlIIlllIIlllIlllIIlIIllIllIlllIIIlllIlllIlIIIllIIIlllIIIlIIlIlllIlllIIIlllIIlllIIlllIIllIIllIIlllIllIllIlIIIlIllIIllIllIIIlllIIllIIllIIIllIIIlIIIllIlIIlllIIlllIIIlllIllIIIllIIIllIIllIIlllIllIllIIlIIllIIIllIIIlllIIlllIll != null) {
                iiIlIIllIIIlllIIlllIlIIIllIIllIlIIlllIIlllIlllIIlIIllIllIlllIIIlllIlllIlIIIllIIIlllIIIlIIlIlllIlllIIIlllIIlllIIlllIIllIIllIIlllIllIllIlIIIlIllIIllIllIIIlllIIllIIllIIIllIIIlIIIllIlIIlllIIlllIIIlllIllIIIllIIIllIIllIIlllIllIllIIlIIllIIIllIIIlllIIlllIll.clear();
            }
        }
        final IIIllIIlIllIIIlllIIlIIlllIIIlllIIlIlIlIIIllIlIllIIIlllIllIIlIllIllIIIllIIllIllIllIllIllIIlIllIIIllIllIIIlIIlllIIllIlllIIIlIllIIlIlIIlllIIIllIlllIllIllIIllIIllIIIllIIIllIIllIllIllIIIllIIlllIIIlIIIllIllIIllIIIllIIIlllIIIllIIllIIllIIIl iIlllIlIIllIlIIlIlllIlIlIIIlIIIlllIllIlIIllIlllIlIIllIllIIllIIlllIlllIIllIIllIIlIllIlIIIlIlllIIlIIIllIlllIllIllIllIIllIllIlIIlIllIIllIIIllIllIIlllIIlIllIIlIIIllIIIllIlIIIlIIlllIIIlIIllIIllIlIllIIllIIIllIlllIllIIl = IllIlIIllIIllIIllIIIlllIIIlIllIllIlIIlIIllIlllIIIllIlllIIlIllIIlllIIlllIlllIllIIIlllIllIllIlIIllIIllIIllIllIIIllIIIlIIIllIIlIllIlIIIlIIllIllIIIllIllIIlllIIlIllIIlIIIlIIlIllIllIlIlllIIlllIIlIIlllIllIIllIlIIllIlIIllIIIll.IIlllIlIIllIlIIlIlllIlIlIIIlIIIlllIllIlIIllIlllIlIIllIllIIllIIlllIlllIIllIIllIIlIllIlIIIlIlllIIlIIIllIlllIllIllIllIIllIllIlIIlIllIIllIIIllIllIIlllIIlIllIIlIIIllIIIllIlIIIlIIlllIIIlIIllIIllIlIllIIllIIIllIlllIllIIl(player2);
        if (iIlllIlIIllIlIIlIlllIlIlIIIlIIIlllIllIlIIllIlllIlIIllIllIIllIIlllIlllIIllIIllIIlIllIlIIIlIlllIIlIIIllIlllIllIllIllIIllIllIlIIlIllIIllIIIllIllIIlllIIlIllIIlIIIllIIIllIlIIIlIIlllIIIlIIllIIllIlIllIIllIIIllIlllIllIIl != null) {
            iIlllIlIIllIlIIlIlllIlIlIIIlIIIlllIllIlIIllIlllIlIIllIllIIllIIlllIlllIIllIIllIIlIllIlIIIlIlllIIlIIIllIlllIllIllIllIIllIllIlIIlIllIIllIIIllIllIIlllIIlIllIIlIIIllIIIllIlIIIlIIlllIIIlIIllIIllIlIllIIllIIIllIlllIllIIl.IIIllIIllIIIlIllIIllIIIlIIllIlIIlIIlIIIlIIIllIlllIlIIllIIIllIIllIIIlllIIllIIIllIllIllIllIIllIIllIIlIIIlllIIIlllIllIlIIlllIIIllIIIllIIlIIlllIIlIllIllIlIIlllIIllIIlIIIllIIlIllIllIIIlIIlIIlllIlIllIlIllIIlllIIllIIlIIIlllIIlllIIIllIIl();
        }
    }
    
    private final <T extends PacketWrapper<?>> void handleTileEntity(final PacketSendEvent packetSendEvent, final Function0<? extends T> function0, final Function1<? super T, ? extends BlockPosition> function2) {
        final Player player = (Player)packetSendEvent.getPlayer();
        if (player == null) {
            return;
        }
        final Player player2 = player;
        final Object channel = packetSendEvent.getChannel();
        Intrinsics.checkNotNull(channel);
        final Channel channel2 = (Channel)channel;
        final IIIllIIlIllIIIlllIIlIIlllIIIlllIIlIlIlIIIllIlIllIIIlllIllIIlIllIllIIIllIIllIllIllIllIllIIlIllIIIllIllIIIlIIlllIIllIlllIIIlIllIIlIlIIlllIIIllIlllIllIllIIllIIllIIIllIIIllIIllIllIllIIIllIIlllIIIlIIIllIllIIllIIIllIIIlllIIIllIIllIIllIIIl iIlllIlIIllIlIIlIlllIlIlIIIlIIIlllIllIlIIllIlllIlIIllIllIIllIIlllIlllIIllIIllIIlIllIlIIIlIlllIIlIIIllIlllIllIllIllIIllIllIlIIlIllIIllIIIllIllIIlllIIlIllIIlIIIllIIIllIlIIIlIIlllIIIlIIllIIllIlIllIIllIIIllIlllIllIIl = IllIlIIllIIllIIllIIIlllIIIlIllIllIlIIlIIllIlllIIIllIlllIIlIllIIlllIIlllIlllIllIIIlllIllIllIlIIllIIllIIllIllIIIllIIIlIIIllIIlIllIlIIIlIIllIllIIIllIllIIlllIIlIllIIlIIIlIIlIllIllIlIlllIIlllIIlIIlllIllIIllIlIIllIlIIllIIIll.IIlllIlIIllIlIIlIlllIlIlIIIlIIIlllIllIlIIllIlllIlIIllIllIIllIIlllIlllIIllIIllIIlIllIlIIIlIlllIIlIIIllIlllIllIllIllIIllIllIlIIlIllIIllIIIllIllIIlllIIlIllIIlIIIllIIIllIlIIIlIIlllIIIlIIllIIllIlIllIIllIIIllIlllIllIIl(player2);
        if (iIlllIlIIllIlIIlIlllIlIlIIIlIIIlllIllIlIIllIlllIlIIllIllIIllIIlllIlllIIllIIllIIlIllIlIIIlIlllIIlIIIllIlllIllIllIllIIllIllIlIIlIllIIllIIIllIllIIlllIIlIllIIlIIIllIIIllIlIIIlIIlllIIIlIIllIIllIlIllIIllIIIllIlllIllIIl == null) {
            return;
        }
        final IIIllIIlIllIIIlllIIlIIlllIIIlllIIlIlIlIIIllIlIllIIIlllIllIIlIllIllIIIllIIllIllIllIllIllIIlIllIIIllIllIIIlIIlllIIllIlllIIIlIllIIlIlIIlllIIIllIlllIllIllIIllIIllIIIllIIIllIIllIllIllIIIllIIlllIIIlIIIllIllIIllIIIllIIIlllIIIllIIllIIllIIIl iiIllIIlIllIIIlllIIlIIlllIIIlllIIlIlIlIIIllIlIllIIIlllIllIIlIllIllIIIllIIllIllIllIllIllIIlIllIIIllIllIIIlIIlllIIllIlllIIIlIllIIlIlIIlllIIIllIlllIllIllIIllIIllIIIllIIIllIIllIllIllIIIllIIlllIIIlIIIllIllIIllIIIllIIIlllIIIllIIllIIllIIIl = iIlllIlIIllIlIIlIlllIlIlIIIlIIIlllIllIlIIllIlllIlIIllIllIIllIIlllIlllIIllIIllIIlIllIlIIIlIlllIIlIIIllIlllIllIllIllIIllIllIlIIlIllIIllIIIllIllIIlllIIlIllIIlIIIllIIIllIlIIIlIIlllIIIlIIllIIllIlIllIIllIIIllIlllIllIIl;
        final World world = player2.getWorld();
        Intrinsics.checkNotNullExpressionValue((Object)world, "");
        if (!IllIlllIIlIIIlIIIlIIIlllIlllIIIlIIllIIlIIlIIIllIIlIllIIIllIlllIlIIlllIIlllIllIIIllIllIIlllIIlIlIIlIIlIllIlllIIlllIllIlIllIlIIllIIlIIIllIlIIIlIIIlIIllIIlIlIIIllIIlIllIIlIIlIIllIllIlllIIIlIIIlIIllIIlllIllIllIIllIlIl.IIlllIlIIllIlIIlIlllIlIlIIIlIIIlllIllIlIIllIlllIlIIllIllIIllIIlllIlllIIllIIllIIlIllIlIIIlIlllIIlIIIllIlllIllIllIllIIllIllIlIIlIllIIllIIIllIllIIlllIIlIllIIlIIIllIIIllIlIIIlIIlllIIIlIIllIIllIlIllIIllIIIllIlllIllIIl(world)) {
            return;
        }
        final PacketWrapper packetWrapper = (PacketWrapper)function0.invoke();
        final IlIIIlIIlIlllIIlllIIllIIIlllIIIlIllIIllIllIIIlIlllIllIlllIIlIlllIIlIIIlIllIIIlIIlIIIlllIllIlIlIlllIIllIlIlllIIlllIIIllIIllIIlIllIIllIIIlllIIllIIllIIllIIIllIIlIIlIIIllIIllIIlIIlllIIlIllIIlllIIllIllIIlllIllIIllIIIllIIIlllIIIlllIIllIll packetQueue = this.getPacketQueue(player2);
        if (packetQueue != null) {
            final IlIIIlIIlIlllIIlllIIllIIIlllIIIlIllIIllIllIIIlIlllIllIlllIIlIlllIIlIIIlIllIIIlIIlIIIlllIllIlIlIlllIIllIlIlllIIlllIIIllIIllIIlIllIIllIIIlllIIllIIllIIllIIIllIIlIIlIIIllIIllIIlIIlllIIlIllIIlllIIllIllIIlllIllIIllIIIllIIIlllIIIlllIIllIll ilIIIlIIlIlllIIlllIIllIIIlllIIIlIllIIllIllIIIlIlllIllIlllIIlIlllIIlIIIlIllIIIlIIlIIIlllIllIlIlIlllIIllIlIlllIIlllIIIllIIllIIlIllIIllIIIlllIIllIIllIIllIIIllIIlIIlIIIllIIllIIlIIlllIIlIllIIlllIIllIllIIlllIllIIllIIIllIIIlllIIIlllIIllIll = packetQueue;
            if (!ilIIIlIIlIlllIIlllIIllIIIlllIIIlIllIIllIllIIIlIlllIllIlllIIlIlllIIlIIIlIllIIIlIIlIIIlllIllIlIlIlllIIllIlIlllIIlllIIIllIIllIIlIllIIllIIIlllIIllIIllIIllIIIllIIlIIlIIIllIIllIIlIIlllIIlIllIIlllIIllIllIIlllIllIIllIIIllIIIlllIIIlllIIllIll.IIIllIllIlIIIlIllIIlIllIIlIlllIlIIIllIIlIIllIIIllIIIllIIlIIlIIlllIIIlllIIlllIllIIIlIlllIlIllIIlllIIlllIIlIllIIllIIIlllIIIlIIIllIIlllIIIllIllIIIlllIIllIIIllIIlIIIllIllIIllIIllIIlllIIlllIIllIIllIIIlIIIlIlIIlllIlIIIlIIIlllIIllIllIIIlllIIllIl()) {
                ilIIIlIIlIlllIIlllIIllIIIlllIIIlIllIIllIllIIIlIlllIllIlllIIlIlllIIlIIIlIllIIIlIIlIIIlllIllIlIlIlllIIllIlIlllIIlllIIIllIIllIIlIllIIllIIIlllIIllIIllIIllIIIllIIlIIlIIIllIIllIIlIIlllIIlIllIIlllIIllIllIIlllIllIIllIIIllIIIlllIIIlllIIllIll.IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(new IlIIlllIllIIIllIIllIIIlllIIIlIIIlllIIlIIlllIIIlIllIIllIlIIlIIlIlIIlllIIlIIllIlIlllIIIlIIIlIIIlIIlIlllIIlIIIllIIIllIIIlIlIlllIIlIIlllIIlllIIllIlIllIlllIIIlllIlIlIIllIllIlllIIlIIlIIIllIlllIllIlllIIIlIllIllIllIIllIlllIIlIIIll((Function0<Unit>)new ChunkPacketListener$handleTileEntity$1.ChunkPacketListener$handleTileEntity$1$1(this, player2, channel2, packetWrapper, (Function1)function2)));
                packetSendEvent.setCancelled(true);
                return;
            }
        }
        final RaytraceBlock traceBlockUnsafe = iiIllIIlIllIIIlllIIlIIlllIIIlllIIlIlIlIIIllIlIllIIIlllIllIIlIllIllIIIllIIllIllIllIllIllIIlIllIIIllIllIIIlIIlllIIllIlllIIIlIllIIlIlIIlllIIIllIlllIllIllIIllIIllIIIllIIIllIIllIllIllIIIllIIlllIIIlIIIllIllIIllIIIllIIIlllIIIllIIllIIllIIIl.getTraceBlockUnsafe((BlockPosition)function2.invoke((Object)packetWrapper));
        if (traceBlockUnsafe != null && !traceBlockUnsafe.getVisible()) {
            packetSendEvent.setCancelled(true);
        }
    }
    
    private final void handleTileEntityPostQueue(final Player player, final Channel channel, final PacketWrapper<?> packetWrapper, final BlockPosition blockPosition) {
        final IIIllIIlIllIIIlllIIlIIlllIIIlllIIlIlIlIIIllIlIllIIIlllIllIIlIllIllIIIllIIllIllIllIllIllIIlIllIIIllIllIIIlIIlllIIllIlllIIIlIllIIlIlIIlllIIIllIlllIllIllIIllIIllIIIllIIIllIIllIllIllIIIllIIlllIIIlIIIllIllIIllIIIllIIIlllIIIllIIllIIllIIIl iIlllIlIIllIlIIlIlllIlIlIIIlIIIlllIllIlIIllIlllIlIIllIllIIllIIlllIlllIIllIIllIIlIllIlIIIlIlllIIlIIIllIlllIllIllIllIIllIllIlIIlIllIIllIIIllIllIIlllIIlIllIIlIIIllIIIllIlIIIlIIlllIIIlIIllIIllIlIllIIllIIIllIlllIllIIl = IllIlIIllIIllIIllIIIlllIIIlIllIllIlIIlIIllIlllIIIllIlllIIlIllIIlllIIlllIlllIllIIIlllIllIllIlIIllIIllIIllIllIIIllIIIlIIIllIIlIllIlIIIlIIllIllIIIllIllIIlllIIlIllIIlIIIlIIlIllIllIlIlllIIlllIIlIIlllIllIIllIlIIllIlIIllIIIll.IIlllIlIIllIlIIlIlllIlIlIIIlIIIlllIllIlIIllIlllIlIIllIllIIllIIlllIlllIIllIIllIIlIllIlIIIlIlllIIlIIIllIlllIllIllIllIIllIllIlIIlIllIIllIIIllIllIIlllIIlIllIIlIIIllIIIllIlIIIlIIlllIIIlIIllIIllIlIllIIllIIIllIlllIllIIl(player);
        if (iIlllIlIIllIlIIlIlllIlIlIIIlIIIlllIllIlIIllIlllIlIIllIllIIllIIlllIlllIIllIIllIIlIllIlIIIlIlllIIlIIIllIlllIllIllIllIIllIllIlIIlIllIIllIIIllIllIIlllIIlIllIIlIIIllIIIllIlIIIlIIlllIIIlIIllIIllIlIllIIllIIIllIlllIllIIl != null) {
            final RaytraceBlock traceBlockUnsafe = iIlllIlIIllIlIIlIlllIlIlIIIlIIIlllIllIlIIllIlllIlIIllIllIIllIIlllIlllIIllIIllIIlIllIlIIIlIlllIIlIIIllIlllIllIllIllIIllIllIlIIlIllIIllIIIllIllIIlllIIlIllIIlIIIllIIIllIlIIIlIIlllIIIlIIllIIllIlIllIIllIIIllIlllIllIIl.getTraceBlockUnsafe(blockPosition);
            if (traceBlockUnsafe == null || traceBlockUnsafe.getVisible()) {
                PacketEvents.getAPI().getProtocolManager().writePacketsSilently((Object)channel, new Object[] { packetWrapper });
            }
        }
    }
    
    private final void handleUnloadChunk(final PacketSendEvent packetSendEvent) {
        final Player player = (Player)packetSendEvent.getPlayer();
        if (player == null) {
            return;
        }
        final Player player2 = player;
        final IIIllIIlIllIIIlllIIlIIlllIIIlllIIlIlIlIIIllIlIllIIIlllIllIIlIllIllIIIllIIllIllIllIllIllIIlIllIIIllIllIIIlIIlllIIllIlllIIIlIllIIlIlIIlllIIIllIlllIllIllIIllIIllIIIllIIIllIIllIllIllIIIllIIlllIIIlIIIllIllIIllIIIllIIIlllIIIllIIllIIllIIIl iIlllIlIIllIlIIlIlllIlIlIIIlIIIlllIllIlIIllIlllIlIIllIllIIllIIlllIlllIIllIIllIIlIllIlIIIlIlllIIlIIIllIlllIllIllIllIIllIllIlIIlIllIIllIIIllIllIIlllIIlIllIIlIIIllIIIllIlIIIlIIlllIIIlIIllIIllIlIllIIllIIIllIlllIllIIl = IllIlIIllIIllIIllIIIlllIIIlIllIllIlIIlIIllIlllIIIllIlllIIlIllIIlllIIlllIlllIllIIIlllIllIllIlIIllIIllIIllIllIIIllIIIlIIIllIIlIllIlIIIlIIllIllIIIllIllIIlllIIlIllIIlIIIlIIlIllIllIlIlllIIlllIIlIIlllIllIIllIlIIllIlIIllIIIll.IIlllIlIIllIlIIlIlllIlIlIIIlIIIlllIllIlIIllIlllIlIIllIllIIllIIlllIlllIIllIIllIIlIllIlIIIlIlllIIlIIIllIlllIllIllIllIIllIllIlIIlIllIIllIIIllIllIIlllIIlIllIIlIIIllIIIllIlIIIlIIlllIIIlIIllIIllIlIllIIllIIIllIlllIllIIl(player2);
        if (iIlllIlIIllIlIIlIlllIlIlIIIlIIIlllIllIlIIllIlllIlIIllIllIIllIIlllIlllIIllIIllIIlIllIlIIIlIlllIIlIIIllIlllIllIllIllIIllIllIlIIlIllIIllIIIllIllIIlllIIlIllIIlIIIllIIIllIlIIIlIIlllIIIlIIllIIllIlIllIIllIIIllIlllIllIIl == null) {
            return;
        }
        final IIIllIIlIllIIIlllIIlIIlllIIIlllIIlIlIlIIIllIlIllIIIlllIllIIlIllIllIIIllIIllIllIllIllIllIIlIllIIIllIllIIIlIIlllIIllIlllIIIlIllIIlIlIIlllIIIllIlllIllIllIIllIIllIIIllIIIllIIllIllIllIIIllIIlllIIIlIIIllIllIIllIIIllIIIlllIIIllIIllIIllIIIl iiIllIIlIllIIIlllIIlIIlllIIIlllIIlIlIlIIIllIlIllIIIlllIllIIlIllIllIIIllIIllIllIllIllIllIIlIllIIIllIllIIIlIIlllIIllIlllIIIlIllIIlIlIIlllIIIllIlllIllIllIIllIIllIIIllIIIllIIllIllIllIIIllIIlllIIIlIIIllIllIIllIIIllIIIlllIIIllIIllIIllIIIl = iIlllIlIIllIlIIlIlllIlIlIIIlIIIlllIllIlIIllIlllIlIIllIllIIllIIlllIlllIIllIIllIIlIllIlIIIlIlllIIlIIIllIlllIllIllIllIIllIllIlIIlIllIIllIIIllIllIIlllIIlIllIIlIIIllIIIllIlIIIlIIlllIIIlIIllIIllIlIllIIllIIIllIlllIllIIl;
        final int illIIlIIllIIlIlllIlIlIlllIIIlIIlllIIIlIllIIIlIllIIIllIIlIIIllIIIllIIIlllIlllIllIllIllIIIlIIIllIlIlllIIllIlllIIIllIlIIlIIlIlIlIIllIllIlllIIllIlIIlIIIlllIIllIIIlIIIllIIIlllIIIllIlllIIlIlIIlIIllIIIlllIllIlIIIlIlIIlIIIlllIIIlll = iiIllIIlIllIIIlllIIlIIlllIIIlllIIlIlIlIIIllIlIllIIIlllIllIIlIllIllIIIllIIllIllIllIllIllIIlIllIIIllIllIIIlIIlllIIllIlllIIIlIllIIlIlIIlllIIIllIlllIllIllIIllIIllIIIllIIIllIIllIllIllIIIllIIlllIIIlIIIllIllIIllIIIllIIIlllIIIllIIllIIllIIIl.IllIIlIIllIIlIlllIlIlIlllIIIlIIlllIIIlIllIIIlIllIIIllIIlIIIllIIIllIIIlllIlllIllIllIllIIIlIIIllIlIlllIIllIlllIIIllIlIIlIIlIlIlIIllIllIlllIIllIlIIlIIIlllIIllIIIlIIIllIIIlllIIIllIlllIIlIlIIlIIllIIIlllIllIlIIIlIlIIlIIIlllIIIlll();
        final WrapperPlayServerUnloadChunk wrapperPlayServerUnloadChunk = new WrapperPlayServerUnloadChunk(packetSendEvent);
        final IIIllIIlIllIIIlllIIlIIlllIIIlllIIlIlIlIIIllIlIllIIIlllIllIIlIllIllIIIllIIllIllIllIllIllIIlIllIIIllIllIIIlIIlllIIllIlllIIIlIllIIlIlIIlllIIIllIlllIllIllIIllIIllIIIllIIIllIIllIllIllIIIllIIlllIIIlIIIllIllIIllIIIllIIIlllIIIllIIllIIllIIIl iIlllIlIIllIlIIlIlllIlIlIIIlIIIlllIllIlIIllIlllIlIIllIllIIllIIlllIlllIIllIIllIIlIllIlIIIlIlllIIlIIIllIlllIllIllIllIIllIllIlIIlIllIIllIIIllIllIIlllIIlIllIIlIIIllIIIllIlIIIlIIlllIIIlIIllIIllIlIllIIllIIIllIlllIllIIl2 = IllIlIIllIIllIIllIIIlllIIIlIllIllIlIIlIIllIlllIIIllIlllIIlIllIIlllIIlllIlllIllIIIlllIllIllIlIIllIIllIIllIllIIIllIIIlIIIllIIlIllIlIIIlIIllIllIIIllIllIIlllIIlIllIIlIIIlIIlIllIllIlIlllIIlllIIlIIlllIllIIllIlIIllIlIIllIIIll.IIlllIlIIllIlIIlIlllIlIlIIIlIIIlllIllIlIIllIlllIlIIllIllIIllIIlllIlllIIllIIllIIlIllIlIIIlIlllIIlIIIllIlllIllIllIllIIllIllIlIIlIllIIllIIIllIllIIlllIIlIllIIlIIIllIIIllIlIIIlIIlllIIIlIIllIIllIlIllIIllIIIllIlllIllIIl(player2);
        if (iIlllIlIIllIlIIlIlllIlIlIIIlIIIlllIllIlIIllIlllIlIIllIllIIllIIlllIlllIIllIIllIIlIllIlIIIlIlllIIlIIIllIlllIllIllIllIIllIllIlIIlIllIIllIIIllIllIIlllIIlIllIIlIIIllIIIllIlIIIlIIlllIIIlIIllIIllIlIllIIllIIIllIlllIllIIl2 != null) {
            iIlllIlIIllIlIIlIlllIlIlIIIlIIIlllIllIlIIllIlllIlIIllIllIIllIIlllIlllIIllIIllIIlIllIlIIIlIlllIIlIIIllIlllIllIllIllIIllIllIlIIlIllIIllIIIllIllIIlllIIlIllIIlIIIllIIIllIlIIIlIIlllIIIlIIllIIllIlIllIIllIIIllIlllIllIIl2.IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(wrapperPlayServerUnloadChunk.getChunkX(), wrapperPlayServerUnloadChunk.getChunkZ(), false);
        }
        final World world = player2.getWorld();
        Intrinsics.checkNotNullExpressionValue((Object)world, "");
        if (!IllIlllIIlIIIlIIIlIIIlllIlllIIIlIIllIIlIIlIIIllIIlIllIIIllIlllIlIIlllIIlllIllIIIllIllIIlllIIlIlIIlIIlIllIlllIIlllIllIlIllIlIIllIIlIIIllIlIIIlIIIlIIllIIlIlIIIllIIlIllIIlIIlIIllIllIlllIIIlIIIlIIllIIlllIllIllIIllIlIl.IIlllIlIIllIlIIlIlllIlIlIIIlIIIlllIllIlIIllIlllIlIIllIllIIllIIlllIlllIIllIIllIIlIllIlIIIlIlllIIlIIIllIlllIllIllIllIIllIllIlIIlIllIIllIIIllIllIIlllIIlIllIIlIIIllIIIllIlIIIlIIlllIIIlIIllIIllIlIllIIllIIIllIlllIllIIl(world)) {
            return;
        }
        final IlIIIlIIlIlllIIlllIIllIIIlllIIIlIllIIllIllIIIlIlllIllIlllIIlIlllIIlIIIlIllIIIlIIlIIIlllIllIlIlIlllIIllIlIlllIIlllIIIllIIllIIlIllIIllIIIlllIIllIIllIIllIIIllIIlIIlIIIllIIllIIlIIlllIIlIllIIlllIIllIllIIlllIllIIllIIIllIIIlllIIIlllIIllIll packetQueue = this.getPacketQueue(player2);
        if (packetQueue != null) {
            final IlIIIlIIlIlllIIlllIIllIIIlllIIIlIllIIllIllIIIlIlllIllIlllIIlIlllIIlIIIlIllIIIlIIlIIIlllIllIlIlIlllIIllIlIlllIIlllIIIllIIllIIlIllIIllIIIlllIIllIIllIIllIIIllIIlIIlIIIllIIllIIlIIlllIIlIllIIlllIIllIllIIlllIllIIllIIIllIIIlllIIIlllIIllIll ilIIIlIIlIlllIIlllIIllIIIlllIIIlIllIIllIllIIIlIlllIllIlllIIlIlllIIlIIIlIllIIIlIIlIIIlllIllIlIlIlllIIllIlIlllIIlllIIIllIIllIIlIllIIllIIIlllIIllIIllIIllIIIllIIlIIlIIIllIIllIIlIIlllIIlIllIIlllIIllIllIIlllIllIIllIIIllIIIlllIIIlllIIllIll = packetQueue;
            if (!ilIIIlIIlIlllIIlllIIllIIIlllIIIlIllIIllIllIIIlIlllIllIlllIIlIlllIIlIIIlIllIIIlIIlIIIlllIllIlIlIlllIIllIlIlllIIlllIIIllIIllIIlIllIIllIIIlllIIllIIllIIllIIIllIIlIIlIIIllIIllIIlIIlllIIlIllIIlllIIllIllIIlllIllIIllIIIllIIIlllIIIlllIIllIll.IIIllIllIlIIIlIllIIlIllIIlIlllIlIIIllIIlIIllIIIllIIIllIIlIIlIIlllIIIlllIIlllIllIIIlIlllIlIllIIlllIIlllIIlIllIIllIIIlllIIIlIIIllIIlllIIIllIllIIIlllIIllIIIllIIlIIIllIllIIllIIllIIlllIIlllIIllIIllIIIlIIIlIlIIlllIlIIIlIIIlllIIllIllIIIlllIIllIl()) {
                ilIIIlIIlIlllIIlllIIllIIIlllIIIlIllIIllIllIIIlIlllIllIlllIIlIlllIIlIIIlIllIIIlIIlIIIlllIllIlIlIlllIIllIlIlllIIlllIIIllIIllIIlIllIIllIIIlllIIllIIllIIllIIIllIIlIIlIIIllIIllIIlIIlllIIlIllIIlllIIllIllIIlllIllIIllIIIllIIIlllIIIlllIIllIll.IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(new IIllIIlllIIllIlllIIlIlllIIIllIIlllIlIlIIIllIIlllIIIlllIIIlIIllIIIlIIlIIllIlllIIlllIIlllIllIIllIIlllIlIIIllIIlIIlllIIlIIIlIIlllIIllIIlllIIIlIllIlllIIllIIlIIlllIIIllIIlllIIlllIIllIIlIIllIIlllIIlllIIllIllIllIIIllIIIlllIllIIllIIlllIIlIllIlIIllIIl((PacketWrapper<?>)wrapperPlayServerUnloadChunk));
                packetSendEvent.setCancelled(true);
                return;
            }
        }
        final IIllIIlIIllIIlIIllIIllIIIllIlllIIlIllIIlIllIIIlIIlIIllIllIIIllIIlIIlllIIIlllIIIlIllIIlIIlIlIllIlIIlIIllIlIIIllIIlIIlIlllIIlIIlllIIIlllIlllIlllIIlIlIIlIIlllIIIllIIllIlIIllIIllIIIllIIlIlIIIlIIIllIlIlIIlIlIIIlIIllIIlll asyncThreadServer = this.asyncThreadServer;
        final UUID uniqueId = player2.getUniqueId();
        Intrinsics.checkNotNullExpressionValue((Object)uniqueId, "");
        asyncThreadServer.IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(uniqueId, ChunkPacketListener::handleUnloadChunk$lambda$10);
    }
    
    private final void finishObfuscation(final Player player, final Channel channel, final IIllIIlIIlIlIIIlllIIlIIlIIlIllIIlIllIIlllIlllIlllIIllIlIIlllIlIlIIlllIIlIllIlllIIlllIlllIlIIIlllIlllIIIllIIllIlllIIllIIlllIlllIIlllIllIIlIlIllIlIlIlIlIIllIlIllIllIlllIIllIIIlllIlllIllIIllIlIllIlIIlIllIIllIlll illIIlIIlIlIIIlllIIlIIlIIlIllIIlIllIIlllIlllIlllIIllIlIIlllIlIlIIlllIIlIllIlllIIlllIlllIlIIIlllIlllIIIllIIllIlllIIllIIlllIlllIIlllIllIIlIlIllIlIlIlIlIIllIlIllIllIlllIIllIIIlllIlllIllIIllIlIllIlIIlIllIIllIlll) {
        try {
            illIIlIIlIlIIIlllIIlIIlIIlIllIIlIllIIlllIlllIlllIIllIlIIlllIlIlIIlllIIlIllIlllIIlllIlllIlIIIlllIlllIIIllIIllIlllIIllIIlllIlllIIlllIllIIlIlIllIlIlIlIlIIllIlIllIllIlllIIllIIIlllIlllIllIIllIlIllIlIIlIllIIllIlll.IIllIllIIllIlIIlIlIIlllIllIIllIIllIlIIllIIIllIIIllIIllIIIlIllIIlIIlllIllIIlIllIllIIlIIlIlIIIlIllIIllIlllIllIIIllIIlIIlllIllIIlIIIlIIllIIllIIlIlIIlIllIIlllIlIIlllIIlIlIIlIIlIlllIIlIIIlIIllIllIlllIIlllIIIlIIllIIIlll(channel);
        }
        catch (final Throwable t) {
            Log.error("Failed to prepare chunk packet for player " + player.getName(), t, new Object[0]);
        }
        illIIlIIlIlIIIlllIIlIIlIIlIllIIlIllIIlllIlllIlllIIllIlIIlllIlIlIIlllIIlIllIlllIIlllIlllIlIIIlllIlllIIIllIIllIlllIIllIIlllIlllIIlllIllIIlIlIllIlIlIlIlIIllIlIllIllIlllIIllIIIlllIlllIllIIllIlIllIlIIlIllIIllIlll.IIIllIllIIlllIIllIlIllIlIIlIIlIIIlIlIIllIlIlllIllIIllIIllIIlIIIlIIllIIllIlIIllIIllIIIllIIIlIIlllIIIllIlllIlIIllIIlIIIlIlIlIIlIIlIlIllIIlIlllIllIIllIIIllIlIIIllIIlIIllIllIIIlIIllIIIlllIIIllIIlIllIIlllIIllIIlllIIlllIIl();
        final IlIIIlIIlIlllIIlllIIllIIIlllIIIlIllIIllIllIIIlIlllIllIlllIIlIlllIIlIIIlIllIIIlIIlIIIlllIllIlIlIlllIIllIlIlllIIlllIIIllIIllIIlIllIIllIIIlllIIllIIllIIllIIIllIIlIIlIIIllIIllIIlIIlllIIlIllIIlllIIllIllIIlllIllIIllIIIllIIIlllIIIlllIIllIll packetQueue = this.getPacketQueue(player);
        if (packetQueue != null) {
            packetQueue.IlIIlllIllIIlIIlIIlllIIllIllIlIIllIIlIlllIllIIllIIlllIIIlIIlllIIlllIIlllIIlIllIllIIIllIIlIIlIlllIIllIIIlIIllIIllIIIllIlllIllIIIlIIlllIIIllIIlIIllIIIlllIIIllIllIlIIIllIIllIlIIlIlllIIllIIlIlllIIIllIIllIIlIIIlIIllIIlIIlllIIlllIIlIl();
        }
    }
    
    private final void handleChunkBulkData(final PacketSendEvent packetSendEvent) {
        final Player player = (Player)packetSendEvent.getPlayer();
        if (player == null) {
            return;
        }
        final Player player2 = player;
        final Object channel = packetSendEvent.getChannel();
        Intrinsics.checkNotNull(channel);
        final Channel channel2 = (Channel)channel;
        final World world = player2.getWorld();
        Intrinsics.checkNotNullExpressionValue((Object)world, "");
        if (!IllIlllIIlIIIlIIIlIIIlllIlllIIIlIIllIIlIIlIIIllIIlIllIIIllIlllIlIIlllIIlllIllIIIllIllIIlllIIlIlIIlIIlIllIlllIIlllIllIlIllIlIIllIIlIIIllIlIIIlIIIlIIllIIlIlIIIllIIlIllIIlIIlIIllIllIlllIIIlIIIlIIllIIlllIllIllIIllIlIl.IIlllIlIIllIlIIlIlllIlIlIIIlIIIlllIllIlIIllIlllIlIIllIllIIllIIlllIlllIIllIIllIIlIllIlIIIlIlllIIlIIIllIlllIllIllIllIIllIllIlIIlIllIIllIIIllIllIIlllIIlIllIIlIIIllIIIllIlIIIlIIlllIIIlIIllIIllIlIllIIllIIIllIlllIllIIl(world)) {
            return;
        }
        WrapperPlayServerChunkDataBulk wrapperPlayServerChunkDataBulk;
        try {
            wrapperPlayServerChunkDataBulk = new WrapperPlayServerChunkDataBulk(packetSendEvent);
        }
        catch (final Throwable t) {
            this.warnChunkParseFailureThrottled(player2, packetSendEvent.getUser().getTotalWorldHeight() >> 4, player2.getWorld().getMaxHeight() - player2.getWorld().getMinHeight() >> 4, t);
            return;
        }
        final WrapperPlayServerChunkDataBulk wrapperPlayServerChunkDataBulk2 = wrapperPlayServerChunkDataBulk;
        final IIIllIIlIllIIIlllIIlIIlllIIIlllIIlIlIlIIIllIlIllIIIlllIllIIlIllIllIIIllIIllIllIllIllIllIIlIllIIIllIllIIIlIIlllIIllIlllIIIlIllIIlIlIIlllIIIllIlllIllIllIIllIIllIIIllIIIllIIllIllIllIIIllIIlllIIIlIIIllIllIIllIIIllIIIlllIIIllIIllIIllIIIl iIlllIlIIllIlIIlIlllIlIlIIIlIIIlllIllIlIIllIlllIlIIllIllIIllIIlllIlllIIllIIllIIlIllIlIIIlIlllIIlIIIllIlllIllIllIllIIllIllIlIIlIllIIllIIIllIllIIlllIIlIllIIlIIIllIIIllIlIIIlIIlllIIIlIIllIIllIlIllIIllIIIllIlllIllIIl = IllIlIIllIIllIIllIIIlllIIIlIllIllIlIIlIIllIlllIIIllIlllIIlIllIIlllIIlllIlllIllIIIlllIllIllIlIIllIIllIIllIllIIIllIIIlIIIllIIlIllIlIIIlIIllIllIIIllIllIIlllIIlIllIIlIIIlIIlIllIllIlIlllIIlllIIlIIlllIllIIllIlIIllIlIIllIIIll.IIlllIlIIllIlIIlIlllIlIlIIIlIIIlllIllIlIIllIlllIlIIllIllIIllIIlllIlllIIllIIllIIlIllIlIIIlIlllIIlIIIllIlllIllIllIllIIllIllIlIIlIllIIllIIIllIllIIlllIIlIllIIlIIIllIIIllIlIIIlIIlllIIIlIIllIIllIlIllIIllIIIllIlllIllIIl(player2);
        if (iIlllIlIIllIlIIlIlllIlIlIIIlIIIlllIllIlIIllIlllIlIIllIllIIllIIlllIlllIIllIIllIIlIllIlIIIlIlllIIlIIIllIlllIllIllIllIIllIllIlIIlIllIIllIIIllIllIIlllIIlIllIIlIIIllIIIllIlIIIlIIlllIIIlIIllIIllIlIllIIllIIIllIlllIllIIl == null) {
            return;
        }
        final IIIllIIlIllIIIlllIIlIIlllIIIlllIIlIlIlIIIllIlIllIIIlllIllIIlIllIllIIIllIIllIllIllIllIllIIlIllIIIllIllIIIlIIlllIIllIlllIIIlIllIIlIlIIlllIIIllIlllIllIllIIllIIllIIIllIIIllIIllIllIllIIIllIIlllIIIlIIIllIllIIllIIIllIIIlllIIIllIIllIIllIIIl iiIllIIlIllIIIlllIIlIIlllIIIlllIIlIlIlIIIllIlIllIIIlllIllIIlIllIllIIIllIIllIllIllIllIllIIlIllIIIllIllIIIlIIlllIIllIlllIIIlIllIIlIlIIlllIIIllIlllIllIllIIllIIllIIIllIIIllIIllIllIllIIIllIIlllIIIlIIIllIllIIllIIIllIIIlllIIIllIIllIIllIIIl = iIlllIlIIllIlIIlIlllIlIlIIIlIIIlllIllIlIIllIlllIlIIllIllIIllIIlllIlllIIllIIllIIlIllIlIIIlIlllIIlIIIllIlllIllIllIllIIllIllIlIIlIllIIllIIIllIllIIlllIIlIllIIlIIIllIIIllIlIIIlIIlllIIIlIIllIIllIlIllIIllIIIllIlllIllIIl;
        final World world2 = player2.getWorld();
        Intrinsics.checkNotNullExpressionValue((Object)world2, "");
        final IlllIIlIIIlllIIIlIIllIlIllIIIlIIIllIlllIIIllIIlIIllIIllIlllIIllIIllIIllIIllIIlllIIlllIllIIllIlllIlIllIIlllIllIllIIlllIIllIIIlllIIIllIIIllIlllIIlllIIlIlIlIlIllIIIlllIIlIllIIllIIlllIIlIIIllIIIllIIlIIlllIlIllIllIIlIIIllIllIIlIIllIll illIllIlIIlllIIIlllIIIllIIIlIlIIIlllIIlIIllIIllIllIIIlIIIlllIIllIIIllIIllIIlllIlllIlIllIIlllIIllIIllIlIlllIIIllIIllIlllIIlllIIIlIIllIIIlllIlIIlIIlIllIlllIIllIllIIIllIIllIIIllIIllIIllIIllIIlIIlIIllIIIlIIIllIIlllIIIllIlIlllIllIIlIIIlllIll = IllIlllIIlIIIlIIIlIIIlllIlllIIIlIIllIIlIIlIIIllIIlIllIIIllIlllIlIIlllIIlllIllIIIllIllIIlllIIlIlIIlIIlIllIlllIIlllIllIlIllIlIIllIIlIIIllIlIIIlIIIlIIllIIlIlIIIllIIlIllIIlIIlIIllIllIlllIIIlIIIlIIllIIlllIllIllIIllIlIl.IllIllIlIIlllIIIlllIIIllIIIlIlIIIlllIIlIIllIIllIllIIIlIIIlllIIllIIIllIIllIIlllIlllIlIllIIlllIIllIIllIlIlllIIIllIIllIlllIIlllIIIlIIllIIIlllIlIIlIIlIllIlllIIllIllIIIllIIllIIIllIIllIIllIIllIIlIIlIIllIIIlIIIllIIlllIIIllIlIlllIllIIlIIIlllIll(world2);
        final IIIllIIlIllIIIlllIIlIIlllIIIlllIIlIlIlIIIllIlIllIIIlllIllIIlIllIllIIIllIIllIllIllIllIllIIlIllIIIllIllIIIlIIlllIIllIlllIIIlIllIIlIlIIlllIIIllIlllIllIllIIllIIllIIIllIIIllIIllIllIllIIIllIIlllIIIlIIIllIllIIllIIIllIIIlllIIIllIIllIIllIIIl iiIllIIlIllIIIlllIIlIIlllIIIlllIIlIlIlIIIllIlIllIIIlllIllIIlIllIllIIIllIIllIllIllIllIllIIlIllIIIllIllIIIlIIlllIIllIlllIIIlIllIIlIlIIlllIIIllIlllIllIllIIllIIllIIIllIIIllIIllIllIllIIIllIIlllIIIlIIIllIllIIllIIIllIIIlllIIIllIIllIIllIIIl2 = iiIllIIlIllIIIlllIIlIIlllIIIlllIIlIlIlIIIllIlIllIIIlllIllIIlIllIllIIIllIIllIllIllIllIllIIlIllIIIllIllIIIlIIlllIIllIlllIIIlIllIIlIlIIlllIIIllIlllIllIllIIllIIllIIIllIIIllIIllIllIllIIIllIIlllIIIlIIIllIllIIllIIIllIIIlllIIIllIIllIIllIIIl;
        for (int i = 0; i < wrapperPlayServerChunkDataBulk2.getX().length; ++i) {
            iiIllIIlIllIIIlllIIlIIlllIIIlllIIlIlIlIIIllIlIllIIIlllIllIIlIllIllIIIllIIllIllIllIllIllIIlIllIIIllIllIIIlIIlllIIllIlllIIIlIllIIlIlIIlllIIIllIlllIllIllIIllIIllIIIllIIIllIIllIllIllIIIllIIlllIIIlIIIllIllIIllIIIllIIIlllIIIllIIllIIllIIIl2.IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(wrapperPlayServerChunkDataBulk2.getX()[i], wrapperPlayServerChunkDataBulk2.getZ()[i], true);
        }
        if (!illIllIlIIlllIIIlllIIIllIIIlIlIIIlllIIlIIllIIllIllIIIlIIIlllIIllIIIllIIllIIlllIlllIlIllIIlllIIllIIllIlIlllIIIllIIllIlllIIlllIIIlIIllIIIlllIlIIlIIlIllIlllIIllIllIIIllIIllIIIllIIllIIllIIllIIlIIlIIllIIIlIIIllIIlllIIIllIlIlllIllIIlIIIlllIll.IIllIIIllIllIIIlllIIIlllIIllIIlllIlIIIllIllIlIIlIIIlllIIlIIllIIllIlIllIIllIIIllIIlIllIIlIIlIIllIIlIlIIllIllIIlIlIIlIIIllIIlIllIllIIlllIllIIlIlllIIllIlIlllIIlllIIIlIIlIIllIlllIlIIllIIIllIlIlIIlIIllIllIlIllIIIllIIll(player2)) {
            return;
        }
        final IlIllIIIllIIIlIIIlIIlIIIlIIlllIIllIIllIlllIlIIlIllIIIlIIlIllIIllIllIIllIIllIIlllIllIllIIlllIIIlIIIllIIlIlIIIllIIIlIIlIllIIlIIllIIIllIIllIlIIlllIIlIIIlIllIIIlIIIllIIlllIIllIIlllIllIIIlllIIIlllIllIllIllIIlIIlllIIIlllIIlllIIIlllIllIIll ilIllIIIllIIIlIIIlIIlIIIlIIlllIIllIIllIlllIlIIlIllIIIlIIlIllIIllIllIIllIIllIIlllIllIllIIlllIIIlIIIllIIlIlIIIllIIIlIIlIllIIlIIllIIIllIIllIlIIlllIIlIIIlIllIIIlIIIllIIlllIIllIIlllIllIIIlllIIIlllIllIllIllIIlIIlllIIIlllIIlllIIIlllIllIIll = new IlIllIIIllIIIlIIIlIIlIIIlIIlllIIllIIllIlllIlIIlIllIIIlIIlIllIIllIllIIllIIllIIlllIllIllIIlllIIIlIIIllIIlIlIIIllIIIlIIlIllIIlIIllIIIllIIllIlIIlllIIlIIIlIllIIIlIIIllIIlllIIllIIlllIllIIIlllIIIlllIllIllIllIIlIIlllIIIlllIIlllIIIlllIllIIll(packetSendEvent.getUser().getTotalWorldHeight() >> 4, wrapperPlayServerChunkDataBulk2);
        packetSendEvent.setCancelled(true);
        final IlIIIlIIlIlllIIlllIIllIIIlllIIIlIllIIllIllIIIlIlllIllIlllIIlIlllIIlIIIlIllIIIlIIlIIIlllIllIlIlIlllIIllIlIlllIIlllIIIllIIllIIlIllIIllIIIlllIIllIIllIIllIIIllIIlIIlIIIllIIllIIlIIlllIIlIllIIlllIIllIllIIlllIllIIllIIIllIIIlllIIIlllIIllIll packetQueue = this.getPacketQueue(player2);
        if (packetQueue != null) {
            packetQueue.IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(ilIllIIIllIIIlIIIlIIlIIIlIIlllIIllIIllIlllIlIIlIllIIIlIIlIllIIllIllIIllIIllIIlllIllIllIIlllIIIlIIIllIIlIlIIIllIIIlIIlIllIIlIIllIIIllIIllIlIIlllIIlIIIlIllIIIlIIIllIIlllIIllIIlllIllIIIlllIIIlllIllIllIllIIlIIlllIIIlllIIlllIIIlllIllIIll);
        }
        illIllIlIIlllIIIlllIIIllIIIlIlIIIlllIIlIIllIIllIllIIIlIIIlllIIllIIIllIIllIIlllIlllIlIllIIlllIIllIIllIlIlllIIIllIIllIlllIIlllIIIlIIllIIIlllIlIIlIIlIllIlllIIllIllIIIllIIllIIIllIIllIIllIIllIIlIIlIIllIIIlIIIllIIlllIIIllIlIlllIllIIlIIIlllIll.IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(player2, ilIllIIIllIIIlIIIlIIlIIIlIIlllIIllIIllIlllIlIIlIllIIIlIIlIllIIllIllIIllIIllIIlllIllIllIIlllIIIlIIIllIIlIlIIIllIIIlIIlIllIIlIIllIIIllIIllIlIIlllIIlIIIlIllIIIlIIIllIIlllIIllIIlllIllIIIlllIIIlllIllIllIllIIlIIlllIIIlllIIlllIIIlllIllIIll).whenComplete((BiConsumer<?, ? super Throwable>)ChunkPacketListener::handleChunkBulkData$lambda$12);
    }
    
    private final void handleChunkData(final PacketSendEvent packetSendEvent) {
        final Player player = (Player)packetSendEvent.getPlayer();
        if (player == null) {
            return;
        }
        final Player player2 = player;
        final World world = player2.getWorld();
        Intrinsics.checkNotNullExpressionValue((Object)world, "");
        if (!IllIlllIIlIIIlIIIlIIIlllIlllIIIlIIllIIlIIlIIIllIIlIllIIIllIlllIlIIlllIIlllIllIIIllIllIIlllIIlIlIIlIIlIllIlllIIlllIllIlIllIlIIllIIlIIIllIlIIIlIIIlIIllIIlIlIIIllIIlIllIIlIIlIIllIllIlllIIIlIIIlIIllIIlllIllIllIIllIlIl.IIlllIlIIllIlIIlIlllIlIlIIIlIIIlllIllIlIIllIlllIlIIllIllIIllIIlllIlllIIllIIllIIlIllIlIIIlIlllIIlIIIllIlllIllIllIllIIllIllIlIIlIllIIllIIIllIllIIlllIIlIllIIlIIIllIIIllIlIIIlIIlllIIIlIIllIIllIlIllIIllIIIllIlllIllIIl(world)) {
            return;
        }
        final Object channel = packetSendEvent.getChannel();
        Intrinsics.checkNotNull(channel);
        final Channel channel2 = (Channel)channel;
        final IIIllIIlIllIIIlllIIlIIlllIIIlllIIlIlIlIIIllIlIllIIIlllIllIIlIllIllIIIllIIllIllIllIllIllIIlIllIIIllIllIIIlIIlllIIllIlllIIIlIllIIlIlIIlllIIIllIlllIllIllIIllIIllIIIllIIIllIIllIllIllIIIllIIlllIIIlIIIllIllIIllIIIllIIIlllIIIllIIllIIllIIIl iIlllIlIIllIlIIlIlllIlIlIIIlIIIlllIllIlIIllIlllIlIIllIllIIllIIlllIlllIIllIIllIIlIllIlIIIlIlllIIlIIIllIlllIllIllIllIIllIllIlIIlIllIIllIIIllIllIIlllIIlIllIIlIIIllIIIllIlIIIlIIlllIIIlIIllIIllIlIllIIllIIIllIlllIllIIl = IllIlIIllIIllIIllIIIlllIIIlIllIllIlIIlIIllIlllIIIllIlllIIlIllIIlllIIlllIlllIllIIIlllIllIllIlIIllIIllIIllIllIIIllIIIlIIIllIIlIllIlIIIlIIllIllIIIllIllIIlllIIlIllIIlIIIlIIlIllIllIlIlllIIlllIIlIIlllIllIIllIlIIllIlIIllIIIll.IIlllIlIIllIlIIlIlllIlIlIIIlIIIlllIllIlIIllIlllIlIIllIllIIllIIlllIlllIIllIIllIIlIllIlIIIlIlllIIlIIIllIlllIllIllIllIIllIllIlIIlIllIIllIIIllIllIIlllIIlIllIIlIIIllIIIllIlIIIlIIlllIIIlIIllIIllIlIllIIllIIIllIlllIllIIl(player2);
        if (iIlllIlIIllIlIIlIlllIlIlIIIlIIIlllIllIlIIllIlllIlIIllIllIIllIIlllIlllIIllIIllIIlIllIlIIIlIlllIIlIIIllIlllIllIllIllIIllIllIlIIlIllIIllIIIllIllIIlllIIlIllIIlIIIllIIIllIlIIIlIIlllIIIlIIllIIllIlIllIIllIIIllIlllIllIIl == null) {
            return;
        }
        final IIIllIIlIllIIIlllIIlIIlllIIIlllIIlIlIlIIIllIlIllIIIlllIllIIlIllIllIIIllIIllIllIllIllIllIIlIllIIIllIllIIIlIIlllIIllIlllIIIlIllIIlIlIIlllIIIllIlllIllIllIIllIIllIIIllIIIllIIllIllIllIIIllIIlllIIIlIIIllIllIIllIIIllIIIlllIIIllIIllIIllIIIl iiIllIIlIllIIIlllIIlIIlllIIIlllIIlIlIlIIIllIlIllIIIlllIllIIlIllIllIIIllIIllIllIllIllIllIIlIllIIIllIllIIIlIIlllIIllIlllIIIlIllIIlIlIIlllIIIllIlllIllIllIIllIIllIIIllIIIllIIllIllIllIIIllIIlllIIIlIIIllIllIIllIIIllIIIlllIIIllIIllIIllIIIl = iIlllIlIIllIlIIlIlllIlIlIIIlIIIlllIllIlIIllIlllIlIIllIllIIllIIlllIlllIIllIIllIIlIllIlIIIlIlllIIlIIIllIlllIllIllIllIIllIllIlIIlIllIIllIIIllIllIIlllIIlIllIIlIIIllIIIllIlIIIlIIlllIIIlIIllIIllIlIllIIllIIIllIlllIllIIl;
        final int n = packetSendEvent.getUser().getTotalWorldHeight() >> 4;
        final int n2 = player2.getWorld().getMaxHeight() - player2.getWorld().getMinHeight() >> 4;
        if (n != n2 || n < 1 || n > 64) {
            this.warnChunkParseFailureThrottled(player2, n, n2, null);
            return;
        }
        final World world2 = player2.getWorld();
        Intrinsics.checkNotNullExpressionValue((Object)world2, "");
        final IlllIIlIIIlllIIIlIIllIlIllIIIlIIIllIlllIIIllIIlIIllIIllIlllIIllIIllIIllIIllIIlllIIlllIllIIllIlllIlIllIIlllIllIllIIlllIIllIIIlllIIIllIIIllIlllIIlllIIlIlIlIlIllIIIlllIIlIllIIllIIlllIIlIIIllIIIllIIlIIlllIlIllIllIIlIIIllIllIIlIIllIll illIllIlIIlllIIIlllIIIllIIIlIlIIIlllIIlIIllIIllIllIIIlIIIlllIIllIIIllIIllIIlllIlllIlIllIIlllIIllIIllIlIlllIIIllIIllIlllIIlllIIIlIIllIIIlllIlIIlIIlIllIlllIIllIllIIIllIIllIIIllIIllIIllIIllIIlIIlIIllIIIlIIIllIIlllIIIllIlIlllIllIIlIIIlllIll = IllIlllIIlIIIlIIIlIIIlllIlllIIIlIIllIIlIIlIIIllIIlIllIIIllIlllIlIIlllIIlllIllIIIllIllIIlllIIlIlIIlIIlIllIlllIIlllIllIlIllIlIIllIIlIIIllIlIIIlIIIlIIllIIlIlIIIllIIlIllIIlIIlIIllIllIlllIIIlIIIlIIllIIlllIllIllIIllIlIl.IllIllIlIIlllIIIlllIIIllIIIlIlIIIlllIIlIIllIIllIllIIIlIIIlllIIllIIIllIIllIIlllIlllIlIllIIlllIIllIIllIlIlllIIIllIIllIlllIIlllIIIlIIllIIIlllIlIIlIIlIllIlllIIllIllIIIllIIllIIIllIIllIIllIIllIIlIIlIIllIIIlIIIllIIlllIIIllIlIlllIllIIlIIIlllIll(world2);
        if (this.getUnloadChunkPacket() && !illIllIlIIlllIIIlllIIIllIIIlIlIIIlllIIlIIllIIllIllIIIlIIIlllIIllIIIllIIllIIlllIlllIlIllIIlllIIllIIllIlIlllIIIllIIllIlllIIlllIIIlIIllIIIlllIlIIlIIlIllIlllIIllIllIIIllIIllIIIllIIllIIllIIllIIlIIlIIllIIIlIIIllIIlllIIIllIlIlllIllIIlIIIlllIll.IIllIIIllIllIIIlllIIIlllIIllIIlllIlIIIllIllIlIIlIIIlllIIlIIllIIllIlIllIIllIIIllIIlIllIIlIIlIIllIIlIlIIllIllIIlIlIIlIIIllIIlIllIllIIlllIllIIlIlllIIllIlIlllIIlllIIIlIIlIIllIlllIlIIllIIIllIlIlIIlIIllIllIlIllIIIllIIll(player2)) {
            final Object byteBuf = packetSendEvent.getByteBuf();
            ByteBufHelper.markReaderIndex(byteBuf);
            final int int1 = ByteBufHelper.readInt(byteBuf);
            final int int2 = ByteBufHelper.readInt(byteBuf);
            ByteBufHelper.resetReaderIndex(byteBuf);
            iiIllIIlIllIIIlllIIlIIlllIIIlllIIlIlIlIIIllIlIllIIIlllIllIIlIllIllIIIllIIllIllIllIllIllIIlIllIIIllIllIIIlIIlllIIllIlllIIIlIllIIlIlIIlllIIIllIlllIllIllIIllIIllIIIllIIIllIIllIllIllIIIllIIlllIIIlIIIllIllIIllIIIllIIIlllIIIllIIllIIllIIIl.IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(int1, int2, true);
            return;
        }
        WrapperPlayServerChunkData wrapperPlayServerChunkData;
        try {
            wrapperPlayServerChunkData = new WrapperPlayServerChunkData(packetSendEvent);
        }
        catch (final Throwable t) {
            this.warnChunkParseFailureThrottled(player2, n, n2, t);
            return;
        }
        final WrapperPlayServerChunkData wrapperPlayServerChunkData2 = wrapperPlayServerChunkData;
        boolean b2 = false;
        Label_0348: {
            Label_0347: {
                if (!this.getUnloadChunkPacket() && wrapperPlayServerChunkData2.getColumn().isFullChunk()) {
                    final BaseChunk[] chunks = wrapperPlayServerChunkData2.getColumn().getChunks();
                    Intrinsics.checkNotNullExpressionValue((Object)chunks, "");
                    final Object[] array = chunks;
                    int i = 0;
                    while (true) {
                        while (i < array.length) {
                            final BaseChunk baseChunk = (BaseChunk)array[i];
                            if (baseChunk != null && !baseChunk.isEmpty()) {
                                final boolean b = false;
                                if (b) {
                                    b2 = true;
                                    break Label_0348;
                                }
                                break Label_0347;
                            }
                            else {
                                ++i;
                            }
                        }
                        final boolean b = true;
                        continue;
                    }
                }
            }
            b2 = false;
        }
        final boolean b3 = b2;
        iiIllIIlIllIIIlllIIlIIlllIIIlllIIlIlIlIIIllIlIllIIIlllIllIIlIllIllIIIllIIllIllIllIllIllIIlIllIIIllIllIIIlIIlllIIllIlllIIIlIllIIlIlIIlllIIIllIlllIllIllIIllIIllIIIllIIIllIIllIllIllIIIllIIlllIIIlIIIllIllIIllIIIllIIIlllIIIllIIllIIllIIIl.IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(wrapperPlayServerChunkData2.getColumn().getX(), wrapperPlayServerChunkData2.getColumn().getZ(), !b3);
        if (!illIllIlIIlllIIIlllIIIllIIIlIlIIIlllIIlIIllIIllIllIIIlIIIlllIIllIIIllIIllIIlllIlllIlIllIIlllIIllIIllIlIlllIIIllIIllIlllIIlllIIIlIIllIIIlllIlIIlIIlIllIlllIIllIllIIIllIIllIIIllIIllIIllIIllIIlIIlIIllIIIlIIIllIIlllIIIllIlIlllIllIIlIIIlllIll.IIllIIIllIllIIIlllIIIlllIIllIIlllIlIIIllIllIlIIlIIIlllIIlIIllIIllIlIllIIllIIIllIIlIllIIlIIlIIllIIlIlIIllIllIIlIlIIlIIIllIIlIllIllIIlllIllIIlIlllIIllIlIlllIIlllIIIlIIlIIllIlllIlIIllIIIllIlIlIIlIIllIllIlIllIIIllIIll(player2)) {
            return;
        }
        final int x = wrapperPlayServerChunkData2.getColumn().getX();
        final int z = wrapperPlayServerChunkData2.getColumn().getZ();
        packetSendEvent.setCancelled(true);
        final int n3 = packetSendEvent.getUser().getTotalWorldHeight() >> 4;
        final IlIIIlIIlIlllIIlllIIllIIIlllIIIlIllIIllIllIIIlIlllIllIlllIIlIlllIIlIIIlIllIIIlIIlIIIlllIllIlIlIlllIIllIlIlllIIlllIIIllIIllIIlIllIIllIIIlllIIllIIllIIllIIIllIIlIIlIIIllIIllIIlIIlllIIlIllIIlllIIllIllIIlllIllIIllIIIllIIIlllIIIlllIIllIll packetQueue = this.getPacketQueue(player2);
        LightData lightData = null;
        Label_0471: {
            if (packetQueue != null) {
                final ConcurrentHashMap<Long, LightData> iiIlIIllIIIlllIIlllIlIIIllIIllIlIIlllIIlllIlllIIlIIllIllIlllIIIlllIlllIlIIIllIIIlllIIIlIIlIlllIlllIIIlllIIlllIIlllIIllIIllIIlllIllIllIlIIIlIllIIllIllIIIlllIIllIIllIIIllIIIlIIIllIlIIlllIIlllIIIlllIllIIIllIIIllIIllIIlllIllIllIIlIIllIIIllIIIlllIIlllIll = packetQueue.IIIlIIllIIIlllIIlllIlIIIllIIllIlIIlllIIlllIlllIIlIIllIllIlllIIIlllIlllIlIIIllIIIlllIIIlIIlIlllIlllIIIlllIIlllIIlllIIllIIllIIlllIllIllIlIIIlIllIIllIllIIIlllIIllIIllIIIllIIIlIIIllIlIIlllIIlllIIIlllIllIIIllIIIllIIllIIlllIllIllIIlIIllIIIllIIIlllIIlllIll();
                if (iiIlIIllIIIlllIIlllIlIIIllIIllIlIIlllIIlllIlllIIlIIllIllIlllIIIlllIlllIlIIIllIIIlllIIIlIIlIlllIlllIIIlllIIlllIIlllIIllIIllIIlllIllIllIlIIIlIllIIllIllIIIlllIIllIIllIIIllIIIlIIIllIlIIlllIIlllIIIlllIllIIIllIIIllIIllIIlllIllIllIIlIIllIIIllIIIlllIIlllIll != null) {
                    lightData = iiIlIIllIIIlllIIlllIlIIIllIIllIlIIlllIIlllIlllIIlIIllIllIlllIIIlllIlllIlIIIllIIIlllIIIlIIlIlllIlllIIIlllIIlllIIlllIIllIIllIIlllIllIllIlIIIlIllIIllIllIIIlllIIllIIllIIIllIIIlIIIllIlIIlllIIlllIIIlllIllIIIllIIIllIIllIIlllIllIllIIlIIllIIIllIIIlllIIlllIll.remove(LongHash.INSTANCE.toLong(x, z));
                    break Label_0471;
                }
            }
            lightData = null;
        }
        final IIllIlllIIIlIIllIIllIlIIllIllIlllIllIIlIIllIlIIIlIIllIIlIIIllIlllIIlIlllIllIIIlllIlIIllIIlIIlIllIIIlIIlIIllIIIlllIIlIIIlllIllIIIlIIlIIllIIllIlIllIllIIllIIlllIIlIIIllIIllIIIllIIlIllIIlIIIlIIlllIlIIlllIIIlllIlIIllIIIllIIlIIl illIlllIIIlIIllIIllIlIIllIllIlllIllIIlIIllIlIIIlIIllIIlIIIllIlllIIlIlllIllIIIlllIlIIllIIlIIlIllIIIlIIlIIllIIIlllIIlIIIlllIllIIIlIIlIIllIIllIlIllIllIIllIIlllIIlIIIllIIllIIIllIIlIllIIlIIIlIIlllIlIIlllIIIlllIlIIllIIIllIIlIIl = new IIllIlllIIIlIIllIIllIlIIllIllIlllIllIIlIIllIlIIIlIIllIIlIIIllIlllIIlIlllIllIIIlllIlIIllIIlIIlIllIIIlIIlIIllIIIlllIIlIIIlllIllIIIlIIlIIllIIllIlIllIllIIllIIlllIIlIIIllIIllIIIllIIlIllIIlIIIlIIlllIlIIlllIIIlllIlIIllIIIllIIlIIl(n3, wrapperPlayServerChunkData2, lightData);
        final IlIIIlIIlIlllIIlllIIllIIIlllIIIlIllIIllIllIIIlIlllIllIlllIIlIlllIIlIIIlIllIIIlIIlIIIlllIllIlIlIlllIIllIlIlllIIlllIIIllIIllIIlIllIIllIIIlllIIllIIllIIllIIIllIIlIIlIIIllIIllIIlIIlllIIlIllIIlllIIllIllIIlllIllIIllIIIllIIIlllIIIlllIIllIll packetQueue2 = this.getPacketQueue(player2);
        if (packetQueue2 != null) {
            packetQueue2.IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(illIlllIIIlIIllIIllIlIIllIllIlllIllIIlIIllIlIIIlIIllIIlIIIllIlllIIlIlllIllIIIlllIlIIllIIlIIlIllIIIlIIlIIllIIIlllIIlIIIlllIllIIIlIIlIIllIIllIlIllIllIIllIIlllIIlIIIllIIllIIIllIIlIllIIlIIIlIIlllIlIIlllIIIlllIlIIllIIIllIIlIIl);
        }
        if (b3) {
            illIllIlIIlllIIIlllIIIllIIIlIlIIIlllIIlIIllIIllIllIIIlIIIlllIIllIIIllIIllIIlllIlllIlIllIIlllIIllIIllIlIlllIIIllIIllIlllIIlllIIIlIIllIIIlllIlIIlIIlIllIlllIIllIllIIIllIIllIIIllIIllIIllIIllIIlIIlIIllIIIlIIIllIIlllIIIllIlIlllIllIIlIIIlllIll.IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(player2, x, z);
            this.finishObfuscation(player2, channel2, illIlllIIIlIIllIIllIlIIllIllIlllIllIIlIIllIlIIIlIIllIIlIIIllIlllIIlIlllIllIIIlllIlIIllIIlIIlIllIIIlIIlIIllIIIlllIIlIIIlllIllIIIlIIlIIllIIllIlIllIllIIllIIlllIIlIIIllIIllIIIllIIlIllIIlIIIlIIlllIlIIlllIIIlllIlIIllIIIllIIlIIl);
            return;
        }
        illIllIlIIlllIIIlllIIIllIIIlIlIIIlllIIlIIllIIllIllIIIlIIIlllIIllIIIllIIllIIlllIlllIlIllIIlllIIllIIllIlIlllIIIllIIllIlllIIlllIIIlIIllIIIlllIlIIlIIlIllIlllIIllIllIIIllIIllIIIllIIllIIllIIllIIlIIlIIllIIIlIIIllIIlllIIIllIlIlllIllIIlIIIlllIll.IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(player2, illIlllIIIlIIllIIllIlIIllIllIlllIllIIlIIllIlIIIlIIllIIlIIIllIlllIIlIlllIllIIIlllIlIIllIIlIIlIllIIIlIIlIIllIIIlllIIlIIIlllIllIIIlIIlIIllIIllIlIllIllIIllIIlllIIlIIIllIIllIIIllIIlIllIIlIIIlIIlllIlIIlllIIIlllIlIIllIIIllIIlIIl).whenComplete((BiConsumer<?, ? super Throwable>)ChunkPacketListener::handleChunkData$lambda$14);
    }
    
    private static final void onPostInitialize$lambda$1(final ChunkPacketListener chunkPacketListener) {
        Intrinsics.checkNotNullParameter((Object)chunkPacketListener, "");
        PacketEvents.getAPI().getEventManager().registerListener((PacketListenerCommon)chunkPacketListener);
        final Collection onlinePlayers = Bukkit.getOnlinePlayers();
        Intrinsics.checkNotNullExpressionValue((Object)onlinePlayers, "");
        for (final Player player : onlinePlayers) {
            Intrinsics.checkNotNull((Object)player);
            IllIlIIllIIllIIllIIIlllIIIlIllIllIlIIlIIllIlllIIIllIlllIIlIllIIlllIIlllIlllIllIIIlllIllIllIlIIllIIllIIllIllIIIllIIIlIIIllIIlIllIlIIIlIIllIllIIIllIllIIlllIIlIllIIlIIIlIIlIllIllIlIlllIIlllIIlIIlllIllIIllIlIIllIlIIllIIIll.IllIllIIlllIIIllIIlllIIlIIlllIIIllIIIlllIIllIIIlIIIlIIIllIIllIlIIIllIlIIlllIIIlllIlIIlIIIlIIlIIlllIlIIlIlllIIllIIllIIllIIIlIIIlIIllIIllIllIIlIIIlllIllIIlIIIllIIllIIlIllIIIlllIIIllIIIllIIllIIlIIIllIIlllIIlllIIIlllIIIllIllIIlIIIlllIIlIIIllIIllIll(player).put((MetaKey)AntiXrayKeys.INSTANCE.getPacketQueueKey(), (Object)new IlIIIlIIlIlllIIlllIIllIIIlllIIIlIllIIllIllIIIlIlllIllIlllIIlIlllIIlIIIlIllIIIlIIlIIIlllIllIlIlIlllIIllIlIlllIIlllIIIllIIllIIlIllIIllIIIlllIIllIIllIIllIIIllIIlIIlIIIllIIllIIlIIlllIIlIllIIlllIIllIllIIlllIllIIllIIIllIIIlllIIIlllIIllIll(player));
        }
    }
    
    private static final void onPreDestroy$lambda$3$lambda$2(final Player player, final IlIIIlIIlIlllIIlllIIllIIIlllIIIlIllIIllIllIIIlIlllIllIlllIIlIlllIIlIIIlIllIIIlIIlIIIlllIllIlIlIlllIIllIlIlllIIlllIIIllIIllIIlIllIIllIIIlllIIllIIllIIllIIIllIIlIIlIIIllIIllIIlIIlllIIlIllIIlllIIllIllIIlllIllIIllIIIllIIIlllIIIlllIIllIll ilIIIlIIlIlllIIlllIIllIIIlllIIIlIllIIllIllIIIlIlllIllIlllIIlIlllIIlIIIlIllIIIlIIlIIIlllIllIlIlIlllIIllIlIlllIIlllIIIllIIllIIlIllIIllIIIlllIIllIIllIIllIIIllIIlIIlIIIllIIllIIlIIlllIIlIllIIlllIIllIllIIlllIllIIllIIIllIIIlllIIIlllIIllIll) {
        ilIIIlIIlIlllIIlllIIllIIIlllIIIlIllIIllIllIIIlIlllIllIlllIIlIlllIIlIIIlIllIIIlIIlIIIlllIllIlIlIlllIIllIlIlllIIlllIIIllIIllIIlIllIIllIIIlllIIllIIllIIllIIIllIIlIIlIIIllIIllIIlIIlllIIlIllIIlllIIllIllIIlllIllIIllIIIllIIIlllIIIlllIIllIll.IIIlIIllIIIlIlIIlllIIlIlllIIIllIlllIlIIIllIllIlIIIlllIllIllIIIllIllIIllIIlIIllIIllIIlllIIIlllIIllIIllIIlllIIIlIIllIIlIIllIIlllIIIllIIlIllIllIllIIIllIlIIlllIIIllIIIllIIIlllIIIllIlllIlIIllIIlIlllIllIIllIIllIlllIIlIIIllIlllIlllIllIllIIl();
        Intrinsics.checkNotNull((Object)player);
        IllIlIIllIIllIIllIIIlllIIIlIllIllIlIIlIIllIlllIIIllIlllIIlIllIIlllIIlllIlllIllIIIlllIllIllIlIIllIIllIIllIllIIIllIIIlIIIllIIlIllIlIIIlIIllIllIIIllIllIIlllIIlIllIIlIIIlIIlIllIllIlIlllIIlllIIlIIlllIllIIllIlIIllIlIIllIIIll.IllIllIIlllIIIllIIlllIIlIIlllIIIllIIIlllIIllIIIlIIIlIIIllIIllIlIIIllIlIIlllIIIlllIlIIlIIIlIIlIIlllIlIIlIlllIIllIIllIIllIIIlIIIlIIllIIllIllIIlIIIlllIllIIlIIIllIIllIIlIllIIIlllIIIllIIIllIIllIIlIIIllIIlllIIlllIIIlllIIIllIllIIlIIIlllIIlIIIllIIllIll(player).remove((MetaKey)AntiXrayKeys.INSTANCE.getPacketQueueKey());
    }
    
    private static final void onPlayerQuit$lambda$4(final IlIIIlIIlIlllIIlllIIllIIIlllIIIlIllIIllIllIIIlIlllIllIlllIIlIlllIIlIIIlIllIIIlIIlIIIlllIllIlIlIlllIIllIlIlllIIlllIIIllIIllIIlIllIIllIIIlllIIllIIllIIllIIIllIIlIIlIIIllIIllIIlIIlllIIlIllIIlllIIllIllIIlllIllIIllIIIllIIIlllIIIlllIIllIll ilIIIlIIlIlllIIlllIIllIIIlllIIIlIllIIllIllIIIlIlllIllIlllIIlIlllIIlIIIlIllIIIlIIlIIIlllIllIlIlIlllIIllIlIlllIIlllIIIllIIllIIlIllIIllIIIlllIIllIIllIIllIIIllIIlIIlIIIllIIllIIlIIlllIIlIllIIlllIIllIllIIlllIllIIllIIIllIIIlllIIIlllIIllIll) {
        ilIIIlIIlIlllIIlllIIllIIIlllIIIlIllIIllIllIIIlIlllIllIlllIIlIlllIIlIIIlIllIIIlIIlIIIlllIllIlIlIlllIIllIlIlllIIlllIIIllIIllIIlIllIIllIIIlllIIllIIllIIllIIIllIIlIIlIIIllIIllIIlIIlllIIlIllIIlllIIllIllIIlllIllIIllIIIllIIIlllIIIlllIIllIll.IIllIIlIIllIIlllIIllIIIlIlllIllIllIIllIllIIlllIllIlIIllIllIIlllIIlIllIlIIIlIIllIIllIIIlIIIlllIIlIIlllIIlIIlIlllIlllIIIlllIIllIlIIIlllIIlllIIIlllIlllIIlIIllIlIIlllIIIllIIlllIIllIIlIllIIIlIlIIlIIlllIIlllIIllIlllIlIIIlllIlllIIIlllIIllIIl();
    }
    
    private static final void handleUnloadChunk$lambda$10(final WrapperPlayServerUnloadChunk wrapperPlayServerUnloadChunk, final IIIllIIlIllIIIlllIIlIIlllIIIlllIIlIlIlIIIllIlIllIIIlllIllIIlIllIllIIIllIIllIllIllIllIllIIlIllIIIllIllIIIlIIlllIIllIlllIIIlIllIIlIlIIlllIIIllIlllIllIllIIllIIllIIIllIIIllIIllIllIllIIIllIIlllIIIlIIIllIllIIllIIIllIIIlllIIIllIIllIIllIIIl iiIllIIlIllIIIlllIIlIIlllIIIlllIIlIlIlIIIllIlIllIIIlllIllIIlIllIllIIIllIIllIllIllIllIllIIlIllIIIllIllIIIlIIlllIIllIlllIIIlIllIIlIlIIlllIIIllIlllIllIllIIllIIllIIIllIIIllIIllIllIllIIIllIIlllIIIlIIIllIllIIllIIIllIIIlllIIIllIIllIIllIIIl, final int n) {
        Intrinsics.checkNotNullParameter((Object)wrapperPlayServerUnloadChunk, "");
        Intrinsics.checkNotNullParameter((Object)iiIllIIlIllIIIlllIIlIIlllIIIlllIIlIlIlIIIllIlIllIIIlllIllIIlIllIllIIIllIIllIllIllIllIllIIlIllIIIllIllIIIlIIlllIIllIlllIIIlIllIIlIlIIlllIIIllIlllIllIllIIllIIllIIIllIIIllIIllIllIllIIIllIIlllIIIlIIIllIllIIllIIIllIIIlllIIIllIIllIIllIIIl, "");
        final int chunkX = wrapperPlayServerUnloadChunk.getChunkX();
        final int chunkZ = wrapperPlayServerUnloadChunk.getChunkZ();
        if (iiIllIIlIllIIIlllIIlIIlllIIIlllIIlIlIlIIIllIlIllIIIlllIllIIlIllIllIIIllIIllIllIllIllIllIIlIllIIIllIllIIIlIIlllIIllIlllIIIlIllIIlIlIIlllIIIllIlllIllIllIIllIIllIIIllIIIllIIllIllIllIIIllIIlllIIIlIIIllIllIIllIIIllIIIlllIIIllIIllIIllIIIl.IllIIlIIllIIlIlllIlIlIlllIIIlIIlllIIIlIllIIIlIllIIIllIIlIIIllIIIllIIIlllIlllIllIllIllIIIlIIIllIlIlllIIllIlllIIIllIlIIlIIlIlIlIIllIllIlllIIllIlIIlIIIlllIIllIIIlIIIllIIIlllIIIllIlllIIlIlIIlIIllIIIlllIllIlIIIlIlIIlIIIlllIIIlll() != n) {
            return;
        }
        iiIllIIlIllIIIlllIIlIIlllIIIlllIIlIlIlIIIllIlIllIIIlllIllIIlIllIllIIIllIIllIllIllIllIllIIlIllIIIllIllIIIlIIlllIIllIlllIIIlIllIIlIlIIlllIIIllIlllIllIllIIllIIllIIIllIIIllIIllIllIllIIIllIIlllIIIlIIIllIllIIllIIIllIIIlllIIIllIIllIIllIIIl.IIllIllIlIlllIIlllIIlllIlIIIllIIlIIllIIllIIlIIIlllIIIlIIlllIIlllIIIlIlIIlIIllIlIIIllIIlIIllIIlIIlIIlllIIIllIlllIIlIIlllIllIllIIlllIlllIIlllIlIIIllIIllIlIlIIIllIlIIIlIIIllIIllIIlIlllIllIllIIIlIIIlIIllIIllIlIIIlIIIlIIllIIIllIIlll(chunkX, chunkZ);
    }
    
    private static final void handleChunkBulkData$lambda$12(final Function2 function2, final Object o, final Object o2) {
        Intrinsics.checkNotNullParameter((Object)function2, "");
        function2.invoke(o, o2);
    }
    
    private static final void handleChunkData$lambda$14(final Function2 function2, final Object o, final Object o2) {
        Intrinsics.checkNotNullParameter((Object)function2, "");
        function2.invoke(o, o2);
    }
}
