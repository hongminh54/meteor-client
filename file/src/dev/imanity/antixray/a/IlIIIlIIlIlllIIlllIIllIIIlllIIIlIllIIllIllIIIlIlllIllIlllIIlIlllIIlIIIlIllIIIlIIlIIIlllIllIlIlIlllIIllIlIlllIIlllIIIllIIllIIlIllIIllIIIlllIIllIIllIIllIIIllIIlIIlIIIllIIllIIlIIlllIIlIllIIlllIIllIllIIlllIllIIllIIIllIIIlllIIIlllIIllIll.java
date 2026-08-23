// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.a;

import kotlin.jvm.internal.DefaultConstructorMarker;
import io.fairyproject.log.Log;
import java.util.Iterator;
import io.netty.buffer.ByteBuf;
import io.netty.channel.EventLoop;
import java.util.concurrent.RejectedExecutionException;
import io.fairyproject.libs.packetevents.manager.protocol.ProtocolManager;
import io.fairyproject.libs.packetevents.wrapper.play.server.WrapperPlayServerPing;
import io.fairyproject.libs.packetevents.wrapper.PacketWrapper;
import io.fairyproject.libs.packetevents.wrapper.play.server.WrapperPlayServerWindowConfirmation;
import io.fairyproject.libs.packetevents.protocol.packettype.PacketType;
import org.jetbrains.annotations.Nullable;
import io.fairyproject.libs.packetevents.PacketEvents;
import io.fairyproject.libs.packetevents.protocol.player.User;
import kotlin.LazyKt;
import dev.imanity.antixray.api.extension.PlayerExtensionKt;
import kotlin.jvm.internal.Lambda;
import io.netty.channel.Channel;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import io.fairyproject.libs.packetevents.protocol.world.chunk.LightData;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import kotlin.Lazy;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import kotlin.Metadata;

@Metadata(mv = { 1, 9, 0 }, k = 1, xi = 48, d1 = { "\u0000|\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\n\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 B2\u00020\u0001:\u0001BB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010/\u001a\u000200J\u000e\u00101\u001a\u0002002\u0006\u00102\u001a\u00020\u0019J\u0006\u00103\u001a\u000200J\u0006\u00104\u001a\u00020\u001dJ\u0006\u00105\u001a\u000200J\u0006\u00106\u001a\u00020\u001dJ\b\u00107\u001a\u000200H\u0002J\u0006\u00108\u001a\u000209J\u0006\u0010:\u001a\u000200J\u0006\u0010;\u001a\u000200J\u0012\u0010<\u001a\u0002002\n\u0010=\u001a\u0006\u0012\u0002\b\u00030>J\u000e\u0010<\u001a\u0002002\u0006\u00102\u001a\u00020\u0019J\u0018\u0010<\u001a\u0002002\u0006\u0010?\u001a\u00020@2\b\b\u0002\u0010A\u001a\u00020\u001dR#\u0010\u0005\u001a\n \u0007*\u0004\u0018\u00010\u00060\u00068BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\b\u0010\tR\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u001d\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00120\u0011¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0014\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00190\u0018X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u001bX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u001c\u001a\u00020\u001dX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!R\u0014\u0010\"\u001a\u00020#X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b$\u0010%R\u001a\u0010&\u001a\u00020\u001dX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b'\u0010\u001f\"\u0004\b(\u0010!R\"\u0010)\u001a\t\u0018\u00010*¢\u0006\u0002\b+8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b.\u0010\u000b\u001a\u0004\b,\u0010-¨\u0006C" }, d2 = { "Ldev/imanity/antixray/core/protocol/PacketQueue;", "", "player", "Lorg/bukkit/entity/Player;", "(Lorg/bukkit/entity/Player;)V", "channel", "Lio/netty/channel/Channel;", "kotlin.jvm.PlatformType", "getChannel", "()Lio/netty/channel/Channel;", "channel$delegate", "Lkotlin/Lazy;", "flushScheduled", "Ljava/util/concurrent/atomic/AtomicBoolean;", "lastWarnNanos", "", "lightCache", "Ljava/util/concurrent/ConcurrentHashMap;", "Lio/fairyproject/libs/packetevents/protocol/world/chunk/LightData;", "getLightCache", "()Ljava/util/concurrent/ConcurrentHashMap;", "getPlayer", "()Lorg/bukkit/entity/Player;", "queue", "Ljava/util/concurrent/ConcurrentLinkedQueue;", "Ldev/imanity/antixray/core/protocol/PacketSendAction;", "queueSize", "Ljava/util/concurrent/atomic/AtomicInteger;", "transactionAcknowledged", "", "getTransactionAcknowledged", "()Z", "setTransactionAcknowledged", "(Z)V", "transactionId", "", "getTransactionId", "()S", "transactionSent", "getTransactionSent", "setTransactionSent", "user", "Lio/fairyproject/libs/packetevents/protocol/player/User;", "Lorg/jetbrains/annotations/Nullable;", "getUser", "()Lcom/github/retrooper/packetevents/protocol/player/User;", "user$delegate", "acknowledgeTransaction", "", "add", "entry", "close", "flush", "forceFlush", "isEmpty", "maybeWarnBacklog", "pendingCount", "", "requestTransaction", "scheduleFlush", "write", "packet", "Lio/fairyproject/libs/packetevents/wrapper/PacketWrapper;", "byteBuf", "Lio/netty/buffer/ByteBuf;", "shouldRelease", "Companion", "plugin" })
public final class IlIIIlIIlIlllIIlllIIllIIIlllIIIlIllIIllIllIIIlIlllIllIlllIIlIlllIIlIIIlIllIIIlIIlIIIlllIllIlIlIlllIIllIlIlllIIlllIIIllIIllIIlIllIIllIIIlllIIllIIllIIllIIIllIIlIIlIIIllIIllIIlIIlllIIlIllIIlllIIllIllIIlllIllIIllIIIllIIIlllIIIlllIIllIll
{
    @NotNull
    public static final IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll aF;
    @NotNull
    private final Player aG;
    private final short aH;
    @NotNull
    private final Lazy aI;
    @NotNull
    private final Lazy aJ;
    @NotNull
    private final ConcurrentLinkedQueue<IllIlIIlIIlllIllIIllIIIlllIlllIllIIIllIIllIIlllIllIlllIIllIlIIlIlIIllIlIIllIIIlllIIIlIIllIllIIlIIlIIIllIIIllIIlllIIllIIlIIIlIIlIlllIlllIlllIllIIlllIIlIlllIIIlIIIllIlllIIllIllIIllIIlIIIllIIllIlIlIlllIlIIlIIllIllIIllIIIllIl> aK;
    @NotNull
    private final ConcurrentHashMap<Long, LightData> aL;
    private boolean aM;
    private boolean aN;
    @NotNull
    private final AtomicInteger aO;
    @NotNull
    private final AtomicBoolean aP;
    private long aQ;
    private static final int aR = 512;
    private static final int aS = 2048;
    private static final long aT = 30000000000L;
    
    public IlIIIlIIlIlllIIlllIIllIIIlllIIIlIllIIllIllIIIlIlllIllIlllIIlIlllIIlIIIlIllIIIlIIlIIIlllIllIlIlIlllIIllIlIlllIIlllIIIllIIllIIlIllIIllIIIlllIIllIIllIIllIIIllIIlIIlIIIllIIllIIlIIlllIIlIllIIlllIIllIllIIlllIllIIllIIIllIIIlllIIIlllIIllIll(@NotNull final Player ag) {
        Intrinsics.checkNotNullParameter((Object)ag, "");
        this.aG = ag;
        this.aH = -32768;
        this.aI = LazyKt.lazy((Function0)new Function0<Channel>() {
            final /* synthetic */ IlIIIlIIlIlllIIlllIIllIIIlllIIIlIllIIllIllIIIlIlllIllIlllIIlIlllIIlIIIlIllIIIlIIlIIIlllIllIlIlIlllIIllIlIlllIIlllIIIllIIllIIlIllIIllIIIlllIIllIIllIIllIIIllIIlIIlIIIllIIllIIlIIlllIIlIllIIlllIIllIllIIlllIllIIllIIIllIIIlllIIIlllIIllIll aU;
            
            public final Channel IIlIllIllIllIIIllIIlIlllIlIllIIlIIIlIIlIIllIlIIllIIIllIIlIIIlIllIIIlIllIIllIIlllIIlIIllIIIlllIIllIIllIIIlllIIIllIIllIlIIlIIllIIIlllIlllIIllIIllIIIllIlllIIlllIIlllIIIllIIllIIllIIllIIllIlIlIllIIllIlIIllIlIIllIIlIIlIIllIllIIll() {
                return PlayerExtensionKt.getMcPlayer(this.aU.IIllIllIIlllIIIlIIllIIllIIllIIlIllIIllIIllIIIllIIIllIIllIllIllIIIlllIIlIIIllIIllIIIllIIIllIIllIIIlIIIlIIlIllIIllIIllIlllIlllIllIIllIlllIIlllIIllIIllIIIlllIIlllIIIlllIIlllIIlIIIlIIllIlIIlIlIlIIlIllIlIIIlllIllIIlllIlllIlIIIllIIIlIIlIIl()).getChannel();
            }
        });
        this.aJ = LazyKt.lazy((Function0)new Function0<User>() {
            @Nullable
            public final User IIllIIIllIIIllIIllIIlllIlIlIIllIIlllIIIllIIlllIIllIIllIIlllIllIIlllIIllIIIlIlllIIIllIIllIIIlllIIlIllIllIlIIIlllIIIlIlIIllIIIlIllIIIlllIIllIIlllIllIIlllIllIIlllIllIllIIllIIlIllIIlllIIIllIIllIIIlIllIllIIIllIIlllIllIIIllIIIllIllIllIIIlIIllIIlll() {
                return PacketEvents.getAPI().getProtocolManager().getUser((Object)IlIIIlIIlIlllIIlllIIllIIIlllIIIlIllIIllIllIIIlIlllIllIlllIIlIlllIIlIIIlIllIIIlIIlIIIlllIllIlIlIlllIIllIlIlllIIlllIIIllIIllIIlIllIIllIIIlllIIllIIllIIllIIIllIIlIIlIIIllIIllIIlIIlllIIlIllIIlllIIllIllIIlllIllIIllIIIllIIIlllIIIlllIIllIll.this.IlIIlIlIlllIIlIlllIllIllIIlIlIIIlllIIIllIllIlllIllIIIllIIIllIlIIIllIllIIllIlllIIIllIlIIlIllIlIlIIllIIlIIIllIlllIIllIIlIIllIIlIIIllIIlIIIllIllIIllIllIIlllIlIIlllIIlIIlllIIIlIIIlIIIlllIIIlIIlllIIllIlIIlllIIlIIIllIllIIlIIlll());
            }
        });
        this.aK = new ConcurrentLinkedQueue<IllIlIIlIIlllIllIIllIIIlllIlllIllIIIllIIllIIlllIllIlllIIllIlIIlIlIIllIlIIllIIIlllIIIlIIllIllIIlIIlIIIllIIIllIIlllIIllIIlIIIlIIlIlllIlllIlllIllIIlllIIlIlllIIIlIIIllIlllIIllIllIIllIIlIIIllIIllIlIlIlllIlIIlIIllIllIIllIIIllIl>();
        this.aL = new ConcurrentHashMap<Long, LightData>(0);
        this.aO = new AtomicInteger(0);
        this.aP = new AtomicBoolean(false);
    }
    
    @NotNull
    public final Player IIllIllIIlllIIIlIIllIIllIIllIIlIllIIllIIllIIIllIIIllIIllIllIllIIIlllIIlIIIllIIllIIIllIIIllIIllIIIlIIIlIIlIllIIllIIllIlllIlllIllIIllIlllIIlllIIllIIllIIIlllIIlllIIIlllIIlllIIlIIIlIIllIlIIlIlIlIIlIllIlIIIlllIllIIlllIlllIlIIIllIIIlIIlIIl() {
        return this.aG;
    }
    
    public final short IIIllIIllIIllIIIlIlIlllIIllIIllIIlIIlllIIIllIllIllIIlIllIIllIIIllIIIlllIlIIIlllIllIllIIIlIIlIIllIIIllIIlllIllIIllIIIllIIIllIlIllIlllIlIIIlllIllIIIlllIIIlIIlllIIllIIlllIIlllIIIllIllIIlllIIllIIllIllIlllIIllIIllIIllIIlllIIllIllIllIllIllIIl() {
        return this.aH;
    }
    
    private final Channel IlIIlIlIlllIIlIlllIllIllIIlIlIIIlllIIIllIllIlllIllIIIllIIIllIlIIIllIllIIllIlllIIIllIlIIlIllIlIlIIllIIlIIIllIlllIIllIIlIIllIIlIIIllIIlIIIllIllIIllIllIIlllIlIIlllIIlIIlllIIIlIIIlIIIlllIIIlIIlllIIllIlIIlllIIlIIIllIllIIlIIlll() {
        return (Channel)this.aI.getValue();
    }
    
    private final User IIllIIlIIIllIIIlIllIIllIIllIIIlllIlIIllIIllIIllIIlllIIlllIllIIllIIlllIllIllIllIIlIIllIIIllIlllIllIlIlllIIllIIlllIlIIllIIllIIllIIllIlIIlIIlllIIIlllIlllIIlllIIlIIlIlIIlIIllIIIllIIllIllIIllIIlIIIllIIllIIIlIIlIIllIIIlIllIlIIllIIll() {
        return (User)this.aJ.getValue();
    }
    
    @NotNull
    public final ConcurrentHashMap<Long, LightData> IIIlIIllIIIlllIIlllIlIIIllIIllIlIIlllIIlllIlllIIlIIllIllIlllIIIlllIlllIlIIIllIIIlllIIIlIIlIlllIlllIIIlllIIlllIIlllIIllIIllIIlllIllIllIlIIIlIllIIllIllIIIlllIIllIIllIIIllIIIlIIIllIlIIlllIIlllIIIlllIllIIIllIIIllIIllIIlllIllIllIIlIIllIIIllIIIlllIIlllIll() {
        return this.aL;
    }
    
    public final boolean IIlIIIlIIllIIIlIIllIIIllIlllIIllIIIlllIllIIllIIIllIlIllIIllIIllIIIllIIllIIlllIIIlllIIIlIllIllIIlIIlllIIIlllIlllIIlIIIlIIlllIIIlIIIlllIIllIIlIIlIIIlllIIlIIllIlllIIllIlIlIIllIIlIIlIIlIIllIIlllIIIllIlIIIllIIIllIIIllIIIlIIlIIllIIIlIlllIllIIIlll() {
        return this.aM;
    }
    
    public final void IlllIIlIIIlllIIIlIIllIlIllIIIlIIIllIlllIIIllIIlIIllIIllIlllIIllIIllIIllIIllIIlllIIlllIllIIllIlllIlIllIIlllIllIllIIlllIIllIIIlllIIIllIIIllIlllIIlllIIlIlIlIlIllIIIlllIIlIllIIllIIlllIIlIIIllIIIllIIlIIlllIlIllIllIIlIIIllIllIIlIIllIll(final boolean am) {
        this.aM = am;
    }
    
    public final boolean IllIIIlllIlIlllIlIlIIlllIlllIIlIIlllIlIlIIllIIlllIllIIlIllIIlIlIllIllIlllIIllIIlIlllIIIllIIllIllIIllIlIIlIIllIIlIIllIlllIIlIIlIIlIIlIIlllIIlIIlllIIIlIIllIIIlllIllIllIIlllIIIllIIIllIIlllIllIlIIIllIIIlllIIlIIIlllIIlllIlIIll() {
        return this.aN;
    }
    
    public final void IIllIIIllIllIIIlllIIIlllIIllIIlllIlIIIllIllIlIIlIIIlllIIlIIllIIllIlIllIIllIIIllIIlIllIIlIIlIIllIIlIlIIllIllIIlIlIIlIIIllIIlIllIllIIlllIllIIlIlllIIllIlIlllIIlllIIIlIIlIIllIlllIlIIllIIIllIlIlIIlIIllIllIlIllIIIllIIll(final boolean an) {
        this.aN = an;
    }
    
    public final void IIIllIIIlllIIlllIIlllIIIllIIllIIllIIlIIllIlIllIIllIIllIlllIIllIlIlIIllIIIlIIIllIIIlIIlllIIllIllIIlllIIlIIlllIllIIllIIIlIIllIIlIIIlIIllIIlllIllIllIllIlllIIllIlIIllIlIIIllIlllIllIIlllIIlIlIlllIllIIlllIIllIIIllIIllIIlllIllIIlllIIllIIl() {
        this.aM = true;
        this.aN = false;
        if (PacketType.Play.Server.PING.getId(PacketEvents.getAPI().getServerManager().getVersion().toClientVersion()) == -1) {
            final WrapperPlayServerWindowConfirmation wrapperPlayServerWindowConfirmation = new WrapperPlayServerWindowConfirmation(0, this.aH, false);
            final ProtocolManager protocolManager = PacketEvents.getAPI().getProtocolManager();
            final User iIllIIlIIIllIIIlIllIIllIIllIIIlllIlIIllIIllIIllIIlllIIlllIllIIllIIlllIllIllIllIIlIIllIIIllIlllIllIlIlllIIllIIlllIlIIllIIllIIllIIllIlIIlIIlllIIIlllIlllIIlllIIlIIlIlIIlIIllIIIllIIllIllIIllIIlIIIllIIllIIIlIIlIIllIIIlIllIlIIllIIll = this.IIllIIlIIIllIIIlIllIIllIIllIIIlllIlIIllIIllIIllIIlllIIlllIllIIllIIlllIllIllIllIIlIIllIIIllIlllIllIlIlllIIllIIlllIlIIllIIllIIllIIllIlIIlIIlllIIIlllIlllIIlllIIlIIlIlIIlIIllIIIllIIllIllIIllIIlIIIllIIllIIIlIIlIIllIIIlIllIlIIllIIll();
            protocolManager.sendPacket((iIllIIlIIIllIIIlIllIIllIIllIIIlllIlIIllIIllIIllIIlllIIlllIllIIllIIlllIllIllIllIIlIIllIIIllIlllIllIlIlllIIllIIlllIlIIllIIllIIllIIllIlIIlIIlllIIIlllIlllIIlllIIlIIlIlIIlIIllIIIllIIllIllIIllIIlIIIllIIllIIIlIIlIIllIIIlIllIlIIllIIll != null) ? iIllIIlIIIllIIIlIllIIllIIllIIIlllIlIIllIIllIIllIIlllIIlllIllIIllIIlllIllIllIllIIlIIllIIIllIlllIllIlIlllIIllIIlllIlIIllIIllIIllIIllIlIIlIIlllIIIlllIlllIIlllIIlIIlIlIIlIIllIIIllIIllIllIIllIIlIIIllIIllIIIlIIlIIllIIIlIllIlIIllIIll.getChannel() : null, (PacketWrapper)wrapperPlayServerWindowConfirmation);
        }
        else {
            final WrapperPlayServerPing wrapperPlayServerPing = new WrapperPlayServerPing((int)this.aH);
            final ProtocolManager protocolManager2 = PacketEvents.getAPI().getProtocolManager();
            final User iIllIIlIIIllIIIlIllIIllIIllIIIlllIlIIllIIllIIllIIlllIIlllIllIIllIIlllIllIllIllIIlIIllIIIllIlllIllIlIlllIIllIIlllIlIIllIIllIIllIIllIlIIlIIlllIIIlllIlllIIlllIIlIIlIlIIlIIllIIIllIIllIllIIllIIlIIIllIIllIIIlIIlIIllIIIlIllIlIIllIIll2 = this.IIllIIlIIIllIIIlIllIIllIIllIIIlllIlIIllIIllIIllIIlllIIlllIllIIllIIlllIllIllIllIIlIIllIIIllIlllIllIlIlllIIllIIlllIlIIllIIllIIllIIllIlIIlIIlllIIIlllIlllIIlllIIlIIlIlIIlIIllIIIllIIllIllIIllIIlIIIllIIllIIIlIIlIIllIIIlIllIlIIllIIll();
            protocolManager2.sendPacket((iIllIIlIIIllIIIlIllIIllIIllIIIlllIlIIllIIllIIllIIlllIIlllIllIIllIIlllIllIllIllIIlIIllIIIllIlllIllIlIlllIIllIIlllIlIIllIIllIIllIIllIlIIlIIlllIIIlllIlllIIlllIIlIIlIlIIlIIllIIIllIIllIllIIllIIlIIIllIIllIIIlIIlIIllIIIlIllIlIIllIIll2 != null) ? iIllIIlIIIllIIIlIllIIllIIllIIIlllIlIIllIIllIIllIIlllIIlllIllIIllIIlllIllIllIllIIlIIllIIIllIlllIllIlIlllIIllIIlllIlIIllIIllIIllIIllIlIIlIIlllIIIlllIlllIIlllIIlIIlIlIIlIIllIIIllIIllIllIIllIIlIIIllIIllIIIlIIlIIllIIIlIllIlIIllIIll2.getChannel() : null, (PacketWrapper)wrapperPlayServerPing);
        }
    }
    
    public final void IIlIIlllIIlllIIllIIIlllIllIIIlIllIIllIllIIlIIlllIIlllIlIIIlllIIllIIllIIlllIllIIIllIlIIIllIlllIlIlllIllIIIlIlllIlllIIIllIIIlIllIIlllIIllIIllIllIIlllIIlllIIlIIlllIIIllIIlIIIlIllIlIlllIlllIIIlIIllIlIlllIIlIlllIlIIlllIIlllIIIlllIllIIlIll() {
        this.aN = true;
    }
    
    public final void IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(@NotNull final IllIlIIlIIlllIllIIllIIIlllIlllIllIIIllIIllIIlllIllIlllIIllIlIIlIlIIllIlIIllIIIlllIIIlIIllIllIIlIIlIIIllIIIllIIlllIIllIIlIIIlIIlIlllIlllIlllIllIIlllIIlIlllIIIlIIIllIlllIIllIllIIllIIlIIIllIIllIlIlIlllIlIIlIIllIllIIllIIIllIl e) {
        Intrinsics.checkNotNullParameter((Object)e, "");
        this.aK.add(e);
        this.aO.incrementAndGet();
        this.IIIllIIllIIlIIlIIIlIllIIllIIlllIIIllIIllIlIIllIIlIIlIIllIIIllIIIlIIlllIIllIIllIIlIIIlIIllIIIlllIIlllIIIlIIIlIIlllIIlllIllIIlllIIIlIIlIIlIIIllIllIIIlllIIIlIIlIIlIlllIllIlllIllIIlIIlllIlllIlllIllIIlIlllIIIllIlllIIllIllIIllIIIllIIIlIIlllIll();
        this.IIIllIIllIIIllIIIlllIIlllIIIlllIlIIIlIIlIIIllIlllIIllIIlIIlllIIlIIlIlllIIlIIllIIllIIlIIlIIlIlIIlIIllIIllIllIIIlIllIIllIllIIlIIllIIlllIIlIlllIIIlllIIllIIlIIlIllIlIIIllIIlllIllIIIlllIIllIIlIIIlllIIIllIIIlllIIIlIIlIIIlIIIllIIlIIllIIllIIl();
    }
    
    public final void IIllIllIIllIlIIlIlIIlllIllIIllIIllIlIIllIIIllIIIllIIllIIIlIllIIlIIlllIllIIlIllIllIIlIIlIlIIIlIllIIllIlllIllIIIllIIlIIlllIllIIlIIIlIIllIIllIIlIlIIlIllIIlllIlIIlllIIlIlIIlIIlIlllIIlIIIlIIllIllIlllIIlllIIIlIIllIIIlll(@NotNull final IllIlIIlIIlllIllIIllIIIlllIlllIllIIIllIIllIIlllIllIlllIIllIlIIlIlIIllIlIIllIIIlllIIIlIIllIllIIlIIlIIIllIIIllIIlllIIllIIlIIIlIIlIlllIlllIlllIllIIlllIIlIlllIIIlIIIllIlllIIllIllIIllIIlIIIllIIllIlIlIlllIlIIlIIllIllIIllIIIllIl e) {
        Intrinsics.checkNotNullParameter((Object)e, "");
        this.aK.add(e);
        this.aO.incrementAndGet();
        this.IIIllIIllIIlIIlIIIlIllIIllIIlllIIIllIIllIlIIllIIlIIlIIllIIIllIIIlIIlllIIllIIllIIlIIIlIIllIIIlllIIlllIIIlIIIlIIlllIIlllIllIIlllIIIlIIlIIlIIIllIllIIIlllIIIlIIlIIlIlllIllIlllIllIIlIIlllIlllIlllIllIIlIlllIIIllIlllIIllIllIIllIIIllIIIlIIlllIll();
    }
    
    public final int IIllIIlIllIIIllIlllIIIllIlllIIIllIlIIlllIIllIlllIIllIllIIlllIIllIIlllIllIIIllIlIllIllIIIllIIIllIIlllIlIlllIIlIIIllIlllIlIIIlllIIIlIlllIllIIlIIllIIlllIIllIIlIllIIIllIIllIIIlllIIlIIllIllIIllIIllIllIIllIlIIllIIlllIIllIlIIllIIIlllIIlIll() {
        return this.aO.get();
    }
    
    public final void IlIIlllIllIIlIIlIIlllIIllIllIlIIllIIlIlllIllIIllIIlllIIIlIIlllIIlllIIlllIIlIllIllIIIllIIlIIlIlllIIllIIIlIIllIIllIIIllIlllIllIIIlIIlllIIIllIIlIIllIIIlllIIIllIllIlIIIllIIllIlIIlIlllIIllIIlIlllIIIllIIllIIlIIIlIIllIIlIIlllIIlllIIlIl() {
        if (!this.aP.compareAndSet(false, true)) {
            return;
        }
        EventLoop eventLoop;
        try {
            eventLoop = this.IlIIlIlIlllIIlIlllIllIllIIlIlIIIlllIIIllIllIlllIllIIIllIIIllIlIIIllIllIIllIlllIIIllIlIIlIllIlIlIIllIIlIIIllIlllIIllIIlIIllIIlIIIllIIlIIIllIllIIllIllIIlllIlIIlllIIlIIlllIIIlIIIlIIIlllIIIlIIlllIIllIlIIlllIIlIIIllIllIIlIIlll().eventLoop();
        }
        catch (final Throwable t) {
            this.aP.set(false);
            return;
        }
        final EventLoop eventLoop2 = eventLoop;
        try {
            eventLoop2.execute(IlIIIlIIlIlllIIlllIIllIIIlllIIIlIllIIllIllIIIlIlllIllIlllIIlIlllIIlIIIlIllIIIlIIlIIIlllIllIlIlIlllIIllIlIlllIIlllIIIllIIllIIlIllIIllIIIlllIIllIIllIIllIIIllIIlIIlIIIllIIllIIlIIlllIIlIllIIlllIIllIllIIlllIllIIllIIIllIIIlllIIIlllIIllIll::IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll);
        }
        catch (final RejectedExecutionException ex) {
            this.aP.set(false);
        }
    }
    
    public final void IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(@NotNull final PacketWrapper<?> packetWrapper) {
        Intrinsics.checkNotNullParameter((Object)packetWrapper, "");
        this.IIllIllIIllIlIIlIlIIlllIllIIllIIllIlIIllIIIllIIIllIIllIIIlIllIIlIIlllIllIIlIllIllIIlIIlIlIIIlIllIIllIlllIllIIIllIIlIIlllIllIIlIIIlIIllIIllIIlIlIIlIllIIlllIlIIlllIIlIlIIlIIlIlllIIlIIIlIIllIllIlllIIlllIIIlIIllIIIlll(new IIllIIlIIlllIIIllIlllIIlllIIlIlIIlIlllIIlllIIIlIIlIIlIIlIIlllIIllIIlIIllIIlllIllIllIIlllIllIllIIlIIlllIlIIlIIllIIlIlIIlllIIllIIlllIIllIIllIIlIIlIIlIIlllIIIlllIIIllIIIllIlIlIlllIIlIIIllIIIlIllIlllIllIllIIIlllIlllIIllIIIlIIIlIIll(packetWrapper));
    }
    
    public final void IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(@NotNull final ByteBuf byteBuf, final boolean b) {
        Intrinsics.checkNotNullParameter((Object)byteBuf, "");
        this.IIllIllIIllIlIIlIlIIlllIllIIllIIllIlIIllIIIllIIIllIIllIIIlIllIIlIIlllIllIIlIllIllIIlIIlIlIIIlIllIIllIlllIllIIIllIIlIIlllIllIIlIIIlIIllIIllIIlIlIIlIllIIlllIlIIlllIIlIlIIlIIlIlllIIlIIIlIIllIllIlllIIlllIIIlIIllIIIlll(new IIIlIIIllIIllIIllIIllIIIllIIIlIIllIIllIIllIlIIlllIIllIIIlIIlIlllIIllIIlllIIllIIllIlllIIlIIIlIIllIIllIIllIIlllIIllIlllIIlIIlIIIlIIIllIlIlllIIIllIlllIIlllIllIIIlIllIIlIIlllIllIIllIIIlIllIIlllIlIIllIllIlIIlIllIIllIIllIllIllIIlllIIlll(byteBuf, b));
    }
    
    public final boolean IIIllIllIlIIIlIllIIlIllIIlIlllIlIIIllIIlIIllIIIllIIIllIIlIIlIIlllIIIlllIIlllIllIIIlIlllIlIllIIlllIIlllIIlIllIIllIIIlllIIIlIIIllIIlllIIIllIllIIIlllIIllIIIllIIlIIIllIllIIllIIllIIlllIIlllIIllIIllIIIlIIIlIlIIlllIlIIIlIIIlllIIllIllIIIlllIIllIl() {
        return this.aK.isEmpty();
    }
    
    public final boolean IIIllIIllIIIllIIIlllIIlllIIIlllIlIIIlIIlIIIllIlllIIllIIlIIlllIIlIIlIlllIIlIIllIIllIIlIIlIIlIlIIlIIllIIllIllIIIlIllIIllIllIIlIIllIIlllIIlIlllIIIlllIIllIIlIIlIllIlIIIllIIlllIllIIIlllIIllIIlIIIlllIIIllIIIlllIIIlIIlIIIlIIIllIIlIIllIIllIIl() {
        if (this.aK.peek() == null) {
            return true;
        }
        final Iterator<IllIlIIlIIlllIllIIllIIIlllIlllIllIIIllIIllIIlllIllIlllIIllIlIIlIlIIllIlIIllIIIlllIIIlIIllIllIIlIIlIIIllIIIllIIlllIIllIIlIIIlIIlIlllIlllIlllIllIIlllIIlIlllIIIlIIIllIlllIIllIllIIllIIlIIIllIIllIlIlIlllIlIIlIIllIllIIllIIIllIl> iterator = this.aK.iterator();
        Intrinsics.checkNotNullExpressionValue((Object)iterator, "");
        final Iterator<IllIlIIlIIlllIllIIllIIIlllIlllIllIIIllIIllIIlllIllIlllIIllIlIIlIlIIllIlIIllIIIlllIIIlIIllIllIIlIIlIIIllIIIllIIlllIIllIIlIIIlIIlIlllIlllIlllIllIIlllIIlIlllIIIlIIIllIlllIIllIllIIllIIlIIIllIIllIlIlIlllIlIIlIIllIllIIllIIIllIl> iterator2 = iterator;
        boolean b = false;
        while (iterator2.hasNext()) {
            final IllIlIIlIIlllIllIIllIIIlllIlllIllIIIllIIllIIlllIllIlllIIllIlIIlIlIIllIlIIllIIIlllIIIlIIllIllIIlIIlIIIllIIIllIIlllIIllIIlIIIlIIlIlllIlllIlllIllIIlllIIlIlllIIIlIIIllIlllIIllIllIIllIIlIIIllIIllIlIlIlllIlIIlIIllIllIIllIIIllIl illIlIIlIIlllIllIIllIIIlllIlllIllIIIllIIllIIlllIllIlllIIllIlIIlIlIIllIlIIllIIIlllIIIlIIllIllIIlIIlIIIllIIIllIIlllIIllIIlIIIlIIlIlllIlllIlllIllIIlllIIlIlllIIIlIIIllIlllIIllIllIIllIIlIIIllIIllIlIlIlllIlIIlIIllIllIIllIIIllIl = iterator2.next();
            if (illIlIIlIIlllIllIIllIIIlllIlllIllIIIllIIllIIlllIllIlllIIllIlIIlIlIIllIlIIllIIIlllIIIlIIllIllIIlIIlIIIllIIIllIIlllIIllIIlIIIlIIlIlllIlllIlllIllIIlllIIlIlllIIIlIIIllIlllIIllIllIIllIIlIIIllIIllIlIlIlllIlIIlIIllIllIIllIIIllIl.IIlllIllIllIIIlllIIlllIlllIIlllIlllIllIlIIIlIIIlIIIlIllIlllIllIlIIIlIlIIIlllIIllIIIlllIIlIIllIIIlIlIIIllIIllIlIIIlllIllIIIllIIllIIIlIIlIlllIIllIlIIIlIIllIIllIlllIIlIllIIllIIIlIIlllIIllIlllIIlIIllIlllIIllIIIlllIIllIllIIlllIIIlllIIIllIIll()) {
                iterator2.remove();
                this.aO.decrementAndGet();
            }
            else {
                if (!illIlIIlIIlllIllIIllIIIlllIlllIllIIIllIIllIIlllIllIlllIIllIlIIlIlIIllIlIIllIIIlllIIIlIIllIllIIlIIlIIIllIIIllIIlllIIllIIlIIIlIIlIlllIlllIlllIllIIlllIIlIlllIIIlIIIllIlllIIllIllIIllIIlIIIllIIllIlIlIlllIlIIlIIllIllIIllIIIllIl.IIIllIIllIIllIIlllIIlllIIllIIllIllIIIlIlIlIlIIllIIIlIIllIIlllIIIlIllIllIIlllIlllIIIllIlIIIllIIIlIlIIlIIlllIIlllIIlllIlllIIlllIIIlIlllIIlllIlIIlIIlllIIlllIIIllIlIIIllIllIIllIIlllIIllIIlIIIlIIlIllIllIIllIIIlllIIlllIIllIIIllIIIllIIIlIIIlIIIll()) {
                    return false;
                }
                iterator2.remove();
                this.aO.decrementAndGet();
                final User iIllIIlIIIllIIIlIllIIllIIllIIIlllIlIIllIIllIIllIIlllIIlllIllIIllIIlllIllIllIllIIlIIllIIIllIlllIllIlIlllIIllIIlllIlIIllIIllIIllIIllIlIIlIIlllIIIlllIlllIIlllIIlIIlIlIIlIIllIIIllIIllIllIIllIIlIIIllIIllIIIlIIlIIllIIIlIllIlIIllIIll = this.IIllIIlIIIllIIIlIllIIllIIllIIIlllIlIIllIIllIIllIIlllIIlllIllIIllIIlllIllIllIllIIlIIllIIIllIlllIllIlIlllIIllIIlllIlIIllIIllIIllIIllIlIIlIIlllIIIlllIlllIIlllIIlIIlIlIIlIIllIIIllIIllIllIIllIIlIIIllIIllIIIlIIlIIllIIIlIllIlIIllIIll();
                if (iIllIIlIIIllIIIlIllIIllIIllIIIlllIlIIllIIllIIllIIlllIIlllIllIIllIIlllIllIllIllIIlIIllIIIllIlllIllIlIlllIIllIIlllIlIIllIIllIIllIIllIlIIlIIlllIIIlllIlllIIlllIIlIIlIlIIlIIllIIIllIIllIllIIllIIlIIIllIIllIIIlIIlIIllIIIlIllIlIIllIIll != null) {
                    final User user = iIllIIlIIIllIIIlIllIIllIIllIIIlllIlIIllIIllIIllIIlllIIlllIllIIllIIlllIllIllIllIIlIIllIIIllIlllIllIlIlllIIllIIlllIlIIllIIllIIllIIllIlIIlIIlllIIIlllIlllIIlllIIlIIlIlIIlIIllIIIllIIllIllIIllIIlIIIllIIllIIIlIIlIIllIIIlIllIlIIllIIll;
                    final IllIlIIlIIlllIllIIllIIIlllIlllIllIIIllIIllIIlllIllIlllIIllIlIIlIlIIllIlIIllIIIlllIIIlIIllIllIIlIIlIIIllIIIllIIlllIIllIIlIIIlIIlIlllIlllIlllIllIIlllIIlIlllIIIlIIIllIlllIIllIllIIllIIlIIIllIIllIlIlIlllIlIIlIIllIllIIllIIIllIl illIlIIlIIlllIllIIllIIIlllIlllIllIIIllIIllIIlllIllIlllIIllIlIIlIlIIllIlIIllIIIlllIIIlIIllIllIIlIIlIIIllIIIllIIlllIIllIIlIIIlIIlIlllIlllIlllIllIIlllIIlIlllIIIlIIIllIlllIIllIllIIllIIlIIIllIIllIlIlIlllIlIIlIIllIllIIllIIIllIl2 = illIlIIlIIlllIllIIllIIIlllIlllIllIIIllIIllIIlllIllIlllIIllIlIIlIlIIllIlIIllIIIlllIIIlIIllIllIIlIIlIIIllIIIllIIlllIIllIIlIIIlIIlIlllIlllIlllIllIIlllIIlIlllIIIlIIIllIlllIIllIllIIllIIlIIIllIIllIlIlIlllIlIIlIIllIllIIllIIIllIl;
                    final User user2 = user;
                    final Channel ilIIlIlIlllIIlIlllIllIllIIlIlIIIlllIIIllIllIlllIllIIIllIIIllIlIIIllIllIIllIlllIIIllIlIIlIllIlIlIIllIIlIIIllIlllIIllIIlIIllIIlIIIllIIlIIIllIllIIllIllIIlllIlIIlllIIlIIlllIIIlIIIlIIIlllIIIlIIlllIIllIlIIlllIIlIIIllIllIIlIIlll = this.IlIIlIlIlllIIlIlllIllIllIIlIlIIIlllIIIllIllIlllIllIIIllIIIllIlIIIllIllIIllIlllIIIllIlIIlIllIlIlIIllIIlIIIllIlllIIllIIlIIllIIlIIIllIIlIIIllIllIIllIllIIlllIlIIlllIIlIIlllIIIlIIIlIIIlllIIIlIIlllIIllIlIIlllIIlIIIllIllIIlIIlll();
                    Intrinsics.checkNotNullExpressionValue((Object)ilIIlIlIlllIIlIlllIllIllIIlIlIIIlllIIIllIllIlllIllIIIllIIIllIlIIIllIllIIllIlllIIIllIlIIlIllIlIlIIllIIlIIIllIlllIIllIIlIIllIIlIIIllIIlIIIllIllIIllIllIIlllIlIIlllIIlIIlllIIIlIIIlIIIlllIIIlIIlllIIllIlIIlllIIlIIIllIllIIlIIlll, "");
                    illIlIIlIIlllIllIIllIIIlllIlllIllIIIllIIllIIlllIllIlllIIllIlIIlIlIIllIlIIllIIIlllIIIlIIllIllIIlIIlIIIllIIIllIIlllIIllIIlIIIlIIlIlllIlllIlllIllIIlllIIlIlllIIIlIIIllIlllIIllIllIIllIIlIIIllIIllIlIlIlllIlIIlIIllIllIIllIIIllIl2.IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(user2, ilIIlIlIlllIIlIlllIllIllIIlIlIIIlllIIIllIllIlllIllIIIllIIIllIlIIIllIllIIllIlllIIIllIlIIlIllIlIlIIllIIlIIIllIlllIIllIIlIIllIIlIIIllIIlIIIllIllIIllIllIIlllIlIIlllIIlIIlllIIIlIIIlIIIlllIIIlIIlllIIllIlIIlllIIlIIIllIllIIlIIlll);
                }
                b = true;
            }
        }
        if (b) {
            this.IlIIlIlIlllIIlIlllIllIllIIlIlIIIlllIIIllIllIlllIllIIIllIIIllIlIIIllIllIIllIlllIIIllIlIIlIllIlIlIIllIIlIIIllIlllIIllIIlIIllIIlIIIllIIlIIIllIllIIllIllIIlllIlIIlllIIlIIlllIIIlIIIlIIIlllIIIlIIlllIIllIlIIlllIIlIIIllIllIIlIIlll().flush();
        }
        return true;
    }
    
    public final void IIllIIlIIllIIlllIIllIIIlIlllIllIllIIllIllIIlllIllIlIIllIllIIlllIIlIllIlIIIlIIllIIllIIIlIIIlllIIlIIlllIIlIIlIlllIlllIIIlllIIllIlIIIlllIIlllIIIlllIlllIIlIIllIlIIlllIIIllIIlllIIllIIlIllIIIlIlIIlIIlllIIlllIIllIlllIlIIIlllIlllIIIlllIIllIIl() {
        while (true) {
            final IllIlIIlIIlllIllIIllIIIlllIlllIllIIIllIIllIIlllIllIlllIIllIlIIlIlIIllIlIIllIIIlllIIIlIIllIllIIlIIlIIIllIIIllIIlllIIllIIlIIIlIIlIlllIlllIlllIllIIlllIIlIlllIIIlIIIllIlllIIllIllIIllIIlIIIllIIllIlIlIlllIlIIlIIllIllIIllIIIllIl illIlIIlIIlllIllIIllIIIlllIlllIllIIIllIIllIIlllIllIlllIIllIlIIlIlIIllIlIIllIIIlllIIIlIIllIllIIlIIlIIIllIIIllIIlllIIllIIlIIIlIIlIlllIlllIlllIllIIlllIIlIlllIIIlIIIllIlllIIllIllIIllIIlIIIllIIllIlIlIlllIlIIlIIllIllIIllIIIllIl = this.aK.poll();
            if (illIlIIlIIlllIllIIllIIIlllIlllIllIIIllIIllIIlllIllIlllIIllIlIIlIlIIllIlIIllIIIlllIIIlIIllIllIIlIIlIIIllIIIllIIlllIIllIIlIIIlIIlIlllIlllIlllIllIIlllIIlIlllIIIlIIIllIlllIIllIllIIllIIlIIIllIIllIlIlIlllIlIIlIIllIllIIllIIIllIl == null) {
                break;
            }
            final IllIlIIlIIlllIllIIllIIIlllIlllIllIIIllIIllIIlllIllIlllIIllIlIIlIlIIllIlIIllIIIlllIIIlIIllIllIIlIIlIIIllIIIllIIlllIIllIIlIIIlIIlIlllIlllIlllIllIIlllIIlIlllIIIlIIIllIlllIIllIllIIllIIlIIIllIIllIlIlIlllIlIIlIIllIllIIllIIIllIl illIlIIlIIlllIllIIllIIIlllIlllIllIIIllIIllIIlllIllIlllIIllIlIIlIlIIllIlIIllIIIlllIIIlIIllIllIIlIIlIIIllIIIllIIlllIIllIIlIIIlIIlIlllIlllIlllIllIIlllIIlIlllIIIlIIIllIlllIIllIllIIllIIlIIIllIIllIlIlIlllIlIIlIIllIllIIllIIIllIl2 = illIlIIlIIlllIllIIllIIIlllIlllIllIIIllIIllIIlllIllIlllIIllIlIIlIlIIllIlIIllIIIlllIIIlIIllIllIIlIIlIIIllIIIllIIlllIIllIIlIIIlIIlIlllIlllIlllIllIIlllIIlIlllIIIlIIIllIlllIIllIllIIllIIlIIIllIIllIlIlIlllIlIIlIIllIllIIllIIIllIl;
            this.aO.decrementAndGet();
            try {
                illIlIIlIIlllIllIIllIIIlllIlllIllIIIllIIllIIlllIllIlllIIllIlIIlIlIIllIlIIllIIIlllIIIlIIllIllIIlIIlIIIllIIIllIIlllIIllIIlIIIlIIlIlllIlllIlllIllIIlllIIlIlllIIIlIIIllIlllIIllIllIIllIIlIIIllIIllIlIlIlllIlIIlIIllIllIIllIIIllIl2.IIllIIllIIlllIlIlIIlIIIlllIIllIIIlllIIllIIllIIIlllIlIIIlllIIIlIIlllIIlIlIIIllIIIlllIlIIlIlllIIIllIllIllIIlIIllIIlllIlllIIlllIIIlIllIllIIIllIIIlllIIllIIIlllIIlllIlIlllIIIlIIlIIIllIllIIlllIIllIIIllIIlIIIlllIllIIIlIIllIIIlIllIIllIllIllIllIll();
            }
            catch (final Throwable t) {
                Log.error("Failed to release queued packet for player " + this.aG.getName(), t, new Object[0]);
            }
        }
    }
    
    public final void IIIlIIllIIIlIlIIlllIIlIlllIIIllIlllIlIIIllIllIlIIIlllIllIllIIIllIllIIllIIlIIllIIllIIlllIIIlllIIllIIllIIlllIIIlIIllIIlIIllIIlllIIIllIIlIllIllIllIIIllIlIIlllIIIllIIIllIIIlllIIIllIlllIlIIllIIlIlllIllIIllIIllIlllIIlIIIllIlllIlllIllIllIIl() {
        final Iterator<IllIlIIlIIlllIllIIllIIIlllIlllIllIIIllIIllIIlllIllIlllIIllIlIIlIlIIllIlIIllIIIlllIIIlIIllIllIIlIIlIIIllIIIllIIlllIIllIIlIIIlIIlIlllIlllIlllIllIIlllIIlIlllIIIlIIIllIlllIIllIllIIllIIlIIIllIIllIlIlIlllIlIIlIIllIllIIllIIIllIl> iterator = this.aK.iterator();
        Intrinsics.checkNotNullExpressionValue((Object)iterator, "");
        final Iterator<IllIlIIlIIlllIllIIllIIIlllIlllIllIIIllIIllIIlllIllIlllIIllIlIIlIlIIllIlIIllIIIlllIIIlIIllIllIIlIIlIIIllIIIllIIlllIIllIIlIIIlIIlIlllIlllIlllIllIIlllIIlIlllIIIlIIIllIlllIIllIllIIllIIlIIIllIIllIlIlIlllIlIIlIIllIllIIllIIIllIl> iterator2 = iterator;
        while (iterator2.hasNext()) {
            final IllIlIIlIIlllIllIIllIIIlllIlllIllIIIllIIllIIlllIllIlllIIllIlIIlIlIIllIlIIllIIIlllIIIlIIllIllIIlIIlIIIllIIIllIIlllIIllIIlIIIlIIlIlllIlllIlllIllIIlllIIlIlllIIIlIIIllIlllIIllIllIIllIIlIIIllIIllIlIlIlllIlIIlIIllIllIIllIIIllIl illIlIIlIIlllIllIIllIIIlllIlllIllIIIllIIllIIlllIllIlllIIllIlIIlIlIIllIlIIllIIIlllIIIlIIllIllIIlIIlIIIllIIIllIIlllIIllIIlIIIlIIlIlllIlllIlllIllIIlllIIlIlllIIIlIIIllIlllIIllIllIIllIIlIIIllIIllIlIlIlllIlIIlIIllIllIIllIIIllIl = iterator2.next();
            if (illIlIIlIIlllIllIIllIIIlllIlllIllIIIllIIllIIlllIllIlllIIllIlIIlIlIIllIlIIllIIIlllIIIlIIllIllIIlIIlIIIllIIIllIIlllIIllIIlIIIlIIlIlllIlllIlllIllIIlllIIlIlllIIIlIIIllIlllIIllIllIIllIIlIIIllIIllIlIlIlllIlIIlIIllIllIIllIIIllIl.IIlllIllIllIIIlllIIlllIlllIIlllIlllIllIlIIIlIIIlIIIlIllIlllIllIlIIIlIlIIIlllIIllIIIlllIIlIIllIIIlIlIIIllIIllIlIIIlllIllIIIllIIllIIIlIIlIlllIIllIlIIIlIIllIIllIlllIIlIllIIllIIIlIIlllIIllIlllIIlIIllIlllIIllIIIlllIIllIllIIlllIIIlllIIIllIIll()) {
                iterator2.remove();
                this.aO.decrementAndGet();
            }
            else {
                iterator2.remove();
                this.aO.decrementAndGet();
                final User iIllIIlIIIllIIIlIllIIllIIllIIIlllIlIIllIIllIIllIIlllIIlllIllIIllIIlllIllIllIllIIlIIllIIIllIlllIllIlIlllIIllIIlllIlIIllIIllIIllIIllIlIIlIIlllIIIlllIlllIIlllIIlIIlIlIIlIIllIIIllIIllIllIIllIIlIIIllIIllIIIlIIlIIllIIIlIllIlIIllIIll = this.IIllIIlIIIllIIIlIllIIllIIllIIIlllIlIIllIIllIIllIIlllIIlllIllIIllIIlllIllIllIllIIlIIllIIIllIlllIllIlIlllIIllIIlllIlIIllIIllIIllIIllIlIIlIIlllIIIlllIlllIIlllIIlIIlIlIIlIIllIIIllIIllIllIIllIIlIIIllIIllIIIlIIlIIllIIIlIllIlIIllIIll();
                if (iIllIIlIIIllIIIlIllIIllIIllIIIlllIlIIllIIllIIllIIlllIIlllIllIIllIIlllIllIllIllIIlIIllIIIllIlllIllIlIlllIIllIIlllIlIIllIIllIIllIIllIlIIlIIlllIIIlllIlllIIlllIIlIIlIlIIlIIllIIIllIIllIllIIllIIlIIIllIIllIIIlIIlIIllIIIlIllIlIIllIIll == null) {
                    continue;
                }
                final User user = iIllIIlIIIllIIIlIllIIllIIllIIIlllIlIIllIIllIIllIIlllIIlllIllIIllIIlllIllIllIllIIlIIllIIIllIlllIllIlIlllIIllIIlllIlIIllIIllIIllIIllIlIIlIIlllIIIlllIlllIIlllIIlIIlIlIIlIIllIIIllIIllIllIIllIIlIIIllIIllIIIlIIlIIllIIIlIllIlIIllIIll;
                final IllIlIIlIIlllIllIIllIIIlllIlllIllIIIllIIllIIlllIllIlllIIllIlIIlIlIIllIlIIllIIIlllIIIlIIllIllIIlIIlIIIllIIIllIIlllIIllIIlIIIlIIlIlllIlllIlllIllIIlllIIlIlllIIIlIIIllIlllIIllIllIIllIIlIIIllIIllIlIlIlllIlIIlIIllIllIIllIIIllIl illIlIIlIIlllIllIIllIIIlllIlllIllIIIllIIllIIlllIllIlllIIllIlIIlIlIIllIlIIllIIIlllIIIlIIllIllIIlIIlIIIllIIIllIIlllIIllIIlIIIlIIlIlllIlllIlllIllIIlllIIlIlllIIIlIIIllIlllIIllIllIIllIIlIIIllIIllIlIlIlllIlIIlIIllIllIIllIIIllIl2 = illIlIIlIIlllIllIIllIIIlllIlllIllIIIllIIllIIlllIllIlllIIllIlIIlIlIIllIlIIllIIIlllIIIlIIllIllIIlIIlIIIllIIIllIIlllIIllIIlIIIlIIlIlllIlllIlllIllIIlllIIlIlllIIIlIIIllIlllIIllIllIIllIIlIIIllIIllIlIlIlllIlIIlIIllIllIIllIIIllIl;
                final User user2 = user;
                final Channel ilIIlIlIlllIIlIlllIllIllIIlIlIIIlllIIIllIllIlllIllIIIllIIIllIlIIIllIllIIllIlllIIIllIlIIlIllIlIlIIllIIlIIIllIlllIIllIIlIIllIIlIIIllIIlIIIllIllIIllIllIIlllIlIIlllIIlIIlllIIIlIIIlIIIlllIIIlIIlllIIllIlIIlllIIlIIIllIllIIlIIlll = this.IlIIlIlIlllIIlIlllIllIllIIlIlIIIlllIIIllIllIlllIllIIIllIIIllIlIIIllIllIIllIlllIIIllIlIIlIllIlIlIIllIIlIIIllIlllIIllIIlIIllIIlIIIllIIlIIIllIllIIllIllIIlllIlIIlllIIlIIlllIIIlIIIlIIIlllIIIlIIlllIIllIlIIlllIIlIIIllIllIIlIIlll();
                Intrinsics.checkNotNullExpressionValue((Object)ilIIlIlIlllIIlIlllIllIllIIlIlIIIlllIIIllIllIlllIllIIIllIIIllIlIIIllIllIIllIlllIIIllIlIIlIllIlIlIIllIIlIIIllIlllIIllIIlIIllIIlIIIllIIlIIIllIllIIllIllIIlllIlIIlllIIlIIlllIIIlIIIlIIIlllIIIlIIlllIIllIlIIlllIIlIIIllIllIIlIIlll, "");
                illIlIIlIIlllIllIIllIIIlllIlllIllIIIllIIllIIlllIllIlllIIllIlIIlIlIIllIlIIllIIIlllIIIlIIllIllIIlIIlIIIllIIIllIIlllIIllIIlIIIlIIlIlllIlllIlllIllIIlllIIlIlllIIIlIIIllIlllIIllIllIIllIIlIIIllIIllIlIlIlllIlIIlIIllIllIIllIIIllIl2.IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(user2, ilIIlIlIlllIIlIlllIllIllIIlIlIIIlllIIIllIllIlllIllIIIllIIIllIlIIIllIllIIllIlllIIIllIlIIlIllIlIlIIllIIlIIIllIlllIIllIIlIIllIIlIIIllIIlIIIllIllIIllIllIIlllIlIIlllIIlIIlllIIIlIIIlIIIlllIIIlIIlllIIllIlIIlllIIlIIIllIllIIlIIlll);
            }
        }
        this.IlIIlIlIlllIIlIlllIllIllIIlIlIIIlllIIIllIllIlllIllIIIllIIIllIlIIIllIllIIllIlllIIIllIlIIlIllIlIlIIllIIlIIIllIlllIIllIIlIIllIIlIIIllIIlIIIllIllIIllIllIIlllIlIIlllIIlIIlllIIIlIIIlIIIlllIIIlIIlllIIllIlIIlllIIlIIIllIllIIlIIlll().flush();
    }
    
    private final void IIIllIIllIIlIIlIIIlIllIIllIIlllIIIllIIllIlIIllIIlIIlIIllIIIllIIIlIIlllIIllIIllIIlIIIlIIllIIIlllIIlllIIIlIIIlIIlllIIlllIllIIlllIIIlIIlIIlIIIllIllIIIlllIIIlIIlIIlIlllIllIlllIllIIlIIlllIlllIlllIllIIlIlllIIIllIlllIIllIllIIllIIIllIIIlIIlllIll() {
        final int value = this.aO.get();
        if (value < 512) {
            return;
        }
        final long nanoTime = System.nanoTime();
        if (nanoTime - this.aQ < 30000000000L) {
            return;
        }
        this.aQ = nanoTime;
        Log.warn("[RaytraceAntiXray] PacketQueue backlog [" + ((value >= 2048) ? "CRITICAL" : "ELEVATED") + "] player='" + this.aG.getName() + "' pending=" + value + ". Chunk obfuscation is not keeping up with chunk streaming, queued chunk packets are being held (head-of-line) and heap is growing. Raise 'obfuscateThreads' in config.yml (0 = auto from cores), lower 'maxRaytraceDistance', or raise 'raytraceRate'. If this persists, the player's chunk delivery is stalling.", new Object[0]);
    }
    
    private static final void IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(final IlIIIlIIlIlllIIlllIIllIIIlllIIIlIllIIllIllIIIlIlllIllIlllIIlIlllIIlIIIlIllIIIlIIlIIIlllIllIlIlIlllIIllIlIlllIIlllIIIllIIllIIlIllIIllIIIlllIIllIIllIIllIIIllIIlIIlIIIllIIllIIlIIlllIIlIllIIlllIIllIllIIlllIllIIllIIIllIIIlllIIIlllIIllIll ilIIIlIIlIlllIIlllIIllIIIlllIIIlIllIIllIllIIIlIlllIllIlllIIlIlllIIlIIIlIllIIIlIIlIIIlllIllIlIlIlllIIllIlIlllIIlllIIIllIIllIIlIllIIllIIIlllIIllIIllIIllIIIllIIlIIlIIIllIIllIIlIIlllIIlIllIIlllIIllIllIIlllIllIIllIIIllIIIlllIIIlllIIllIll) {
        Intrinsics.checkNotNullParameter((Object)ilIIIlIIlIlllIIlllIIllIIIlllIIIlIllIIllIllIIIlIlllIllIlllIIlIlllIIlIIIlIllIIIlIIlIIIlllIllIlIlIlllIIllIlIlllIIlllIIIllIIllIIlIllIIllIIIlllIIllIIllIIllIIIllIIlIIlIIIllIIllIIlIIlllIIlIllIIlllIIllIllIIlllIllIIllIIIllIIIlllIIIlllIIllIll, "");
        ilIIIlIIlIlllIIlllIIllIIIlllIIIlIllIIllIllIIIlIlllIllIlllIIlIlllIIlIIIlIllIIIlIIlIIIlllIllIlIlIlllIIllIlIlllIIlllIIIllIIllIIlIllIIllIIIlllIIllIIllIIllIIIllIIlIIlIIIllIIllIIlIIlllIIlIllIIlllIIllIllIIlllIllIIllIIIllIIIlllIIIlllIIllIll.aP.set(false);
        try {
            ilIIIlIIlIlllIIlllIIllIIIlllIIIlIllIIllIllIIIlIlllIllIlllIIlIlllIIlIIIlIllIIIlIIlIIIlllIllIlIlIlllIIllIlIlllIIlllIIIllIIllIIlIllIIllIIIlllIIllIIllIIllIIIllIIlIIlIIIllIIllIIlIIlllIIlIllIIlllIIllIllIIlllIllIIllIIIllIIIlllIIIlllIIllIll.IIIllIIllIIIllIIIlllIIlllIIIlllIlIIIlIIlIIIllIlllIIllIIlIIlllIIlIIlIlllIIlIIllIIllIIlIIlIIlIlIIlIIllIIllIllIIIlIllIIllIllIIlIIllIIlllIIlIlllIIIlllIIllIIlIIlIllIlIIIllIIlllIllIIIlllIIllIIlIIIlllIIIllIIIlllIIIlIIlIIIlIIIllIIlIIllIIllIIl();
        }
        catch (final Throwable t) {
            Log.error("Failed to flush packet queue for player " + ilIIIlIIlIlllIIlllIIllIIIlllIIIlIllIIllIllIIIlIlllIllIlllIIlIlllIIlIIIlIllIIIlIIlIIIlllIllIlIlIlllIIllIlIlllIIlllIIIllIIllIIlIllIIllIIIlllIIllIIllIIllIIIllIIlIIlIIIllIIllIIlIIlllIIlIllIIlllIIllIllIIlllIllIIllIIIllIIIlllIIIlllIIllIll.aG.getName(), t, new Object[0]);
        }
    }
    
    static {
        aF = new IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(null);
    }
    
    @Metadata(mv = { 1, 9, 0 }, k = 1, xi = 48, d1 = { "\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0014\u0010\u0007\u001a\u00020\u00048\u0002X\u0083T¢\u0006\u0006\n\u0004\b\u0005\u0010\u0006R\u0014\u0010\u000b\u001a\u00020\b8\u0002X\u0083T¢\u0006\u0006\n\u0004\b\t\u0010\nR\u0014\u0010\r\u001a\u00020\u00048\u0002X\u0083T¢\u0006\u0006\n\u0004\b\f\u0010\u0006" }, d2 = { "Ldev/imanity/antixray/a/IlIIIlIIlIlllIIlllIIllIIIlllIIIlIllIIllIllIIIlIlllIllIlllIIlIlllIIlIIIlIllIIIlIIlIIIlllIllIlIlIlllIIllIlIlllIIlllIIIllIIllIIlIllIIllIIIlllIIllIIllIIllIIIllIIlIIlIIIllIIllIIlIIlllIIlIllIIlllIIllIllIIlllIllIIllIIIllIIIlllIIIlllIIllIll$IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll;", "", "<init>", "()V", "", "aS", "I", "IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll", "", "aT", "J", "IIllIllIIllIlIIlIlIIlllIllIIllIIllIlIIllIIIllIIIllIIllIIIlIllIIlIIlllIllIIlIllIllIIlIIlIlIIIlIllIIllIlllIllIIIllIIlIIlllIllIIlIIIlIIllIIllIIlIlIIlIllIIlllIlIIlllIIlIlIIlIIlIlllIIlIIIlIIllIllIlllIIlllIIIlIIllIIIlll", "aR", "IlllIIlIIIlllIIIlIIllIlIllIIIlIIIllIlllIIIllIIlIIllIIllIlllIIllIIllIIllIIllIIlllIIlllIllIIllIlllIlIllIIlllIllIllIIlllIIllIIIlllIIIllIIIllIlllIIlllIIlIlIlIlIllIIIlllIIlIllIIllIIlllIIlIIIllIIIllIIlIIlllIlIllIllIIlIIIllIllIIlIIllIll" })
    public static final class IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll
    {
        private IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll() {
        }
    }
}
