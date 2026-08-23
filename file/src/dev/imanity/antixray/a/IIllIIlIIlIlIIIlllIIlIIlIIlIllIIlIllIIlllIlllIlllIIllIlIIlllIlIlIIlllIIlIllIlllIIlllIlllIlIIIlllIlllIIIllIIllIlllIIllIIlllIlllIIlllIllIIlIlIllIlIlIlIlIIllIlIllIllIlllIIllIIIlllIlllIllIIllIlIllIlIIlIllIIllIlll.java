// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.a;

import io.fairyproject.libs.packetevents.netty.buffer.ByteBufHelper;
import java.util.Arrays;
import io.fairyproject.log.Log;
import io.fairyproject.libs.packetevents.protocol.player.User;
import io.fairyproject.libs.packetevents.PacketEvents;
import kotlin.jvm.internal.Intrinsics;
import io.netty.channel.Channel;
import dev.imanity.antixray.api.chunk.ChunkData;
import java.util.List;
import org.jetbrains.annotations.Nullable;
import io.fairyproject.libs.packetevents.wrapper.PacketWrapper;
import org.jetbrains.annotations.NotNull;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.Metadata;

@Metadata(mv = { 1, 9, 0 }, k = 1, xi = 48, d1 = { "\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b&\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\f\u0010\u0015\u001a\u0006\u0012\u0002\b\u00030\rH$J\b\u0010\u0016\u001a\u00020\u0017H\u0016J\b\u0010\u0018\u001a\u00020\u0017H\u0016J\u0018\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001eH\u0014J\u000e\u0010\u001f\u001a\u00020\u001a2\u0006\u0010\u001d\u001a\u00020\u001eJ\b\u0010 \u001a\u00020\u001aH\u0002J\b\u0010!\u001a\u00020\u001aH\u0016J\u0018\u0010\"\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001eH\u0016J\u0006\u0010#\u001a\u00020\u001aR\u0018\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X¦\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\b\u0012\u0002\b\u0003\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0018\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0011R\u000e\u0010\u0012\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014¨\u0006$" }, d2 = { "Ldev/imanity/antixray/core/protocol/ChunkPacket;", "Ldev/imanity/antixray/core/protocol/PacketSendAction;", "worldHeight", "", "(I)V", "chunks", "", "Ldev/imanity/antixray/api/chunk/ChunkData;", "getChunks", "()Ljava/util/List;", "consumed", "Ljava/util/concurrent/atomic/AtomicBoolean;", "prepared", "Lio/fairyproject/libs/packetevents/wrapper/PacketWrapper;", "preparedBuffers", "", "", "[Ljava/lang/Object;", "ready", "getWorldHeight", "()I", "getChunkPacket", "isConsumed", "", "isReady", "preSend", "", "user", "Lio/fairyproject/libs/packetevents/protocol/player/User;", "channel", "Lio/netty/channel/Channel;", "prepare", "releaseBuffers", "releaseResources", "send", "setReady", "plugin" })
@SourceDebugExtension({ "SMAP\nChunkPacket.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ChunkPacket.kt\ndev/imanity/antixray/core/protocol/ChunkPacket\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,106:1\n1#2:107\n*E\n" })
public abstract class IIllIIlIIlIlIIIlllIIlIIlIIlIllIIlIllIIlllIlllIlllIIllIlIIlllIlIlIIlllIIlIllIlllIIlllIlllIlIIIlllIlllIIIllIIllIlllIIllIIlllIlllIIlllIllIIlIlIllIlIlIlIlIIllIlIllIllIlllIIllIIIlllIlllIllIIllIlIllIlIIlIllIIllIlll implements IllIlIIlIIlllIllIIllIIIlllIlllIllIIIllIIllIIlllIllIlllIIllIlIIlIlIIllIlIIllIIIlllIIIlIIllIllIIlIIlIIIllIIIllIIlllIIllIIlIIIlIIlIlllIlllIlllIllIIlllIIlIlllIIIlIIIllIlllIIllIllIIllIIlIIIllIIllIlIlIlllIlIIlIIllIllIIllIIIllIl
{
    private final int au;
    @NotNull
    private final AtomicBoolean aw;
    @NotNull
    private final AtomicBoolean ax;
    @Nullable
    private volatile PacketWrapper<?> ay;
    @Nullable
    private volatile Object[] az;
    
    public IIllIIlIIlIlIIIlllIIlIIlIIlIllIIlIllIIlllIlllIlllIIllIlIIlllIlIlIIlllIIlIllIlllIIlllIlllIlIIIlllIlllIIIllIIllIlllIIllIIlllIlllIIlllIllIIlIlIllIlIlIlIlIIllIlIllIllIlllIIllIIIlllIlllIllIIllIlIllIlIIlIllIIllIlll(final int au) {
        this.au = au;
        this.aw = new AtomicBoolean(false);
        this.ax = new AtomicBoolean(false);
    }
    
    public final int IIlllIllIIllIlllIlIIlIIllIllIIllIIIlIIlllIllIlllIlIIllIIIllIIIllIIlIIlIlllIllIIIllIIllIIIllIIlIIlIlllIIlIIlIIIlIIlllIlIIllIIllIIlIllIlIIIlIIlllIllIIIlIIIlIIIlIllIIllIIIlIllIIlIIllIIllIllIIIlIlIIllIlllIIIlllIIIlllIllIIlIll() {
        return this.au;
    }
    
    @NotNull
    public abstract List<ChunkData> IllIIlllIIIlIIllIIlIIllIIlIIlllIIIllIllIIlllIIlIIllIIllIIIllIlllIlIlIlllIlIIllIIIlllIllIlIIlllIIlllIIllIIIlIIIllIIlIIIlIllIIIllIIllIIllIIlIIIlIIlIIllIllIlIlIIlllIIlIIlIllIllIIllIIlIIIlIIlllIllIIIlllIlllIIlllIllIIlIIIlllIIllIl();
    
    public final void IIllIllIIllIlIIlIlIIlllIllIIllIIllIlIIllIIIllIIIllIIllIIIlIllIIlIIlllIllIIlIllIllIIlIIlIlIIIlIllIIllIlllIllIIIllIIlIIlllIllIIlIIIlIIllIIllIIlIlIIlIllIIlllIlIIlllIIlIlIIlIIlIlllIIlIIIlIIllIllIlllIIlllIIIlIIllIIIlll(@NotNull final Channel channel) {
        Intrinsics.checkNotNullParameter((Object)channel, "");
        if (this.az != null) {
            return;
        }
        PacketWrapper<?> ay;
        if ((ay = this.ay) == null) {
            final PacketWrapper<?> iIllIIIlllIIllIlllIllIlIllIlllIIIlIIllIIIlIIlIIIlIIlllIIllIlllIIlIIllIIlIIllIIlIllIllIIllIllIlIlIIlIIIllIIllIlIIlIlllIIlllIIIlllIIlIIIllIIIlIIlIIlIIllIIIlllIIIlIIIlllIIlIllIIlIIIllIllIIllIIllIlIlIIIlllIlllIIlIIlllIlIllIIl = this.IIllIIIlllIIllIlllIllIlIllIlllIIIlIIllIIIlIIlIIIlIIlllIIllIlllIIlIIllIIlIIllIIlIllIllIIllIllIlIlIIlIIIllIIllIlIIlIlllIIlllIIIlllIIlIIIllIIIlIIlIIlIIllIIIlllIIIlIIIlllIIlIllIIlIIIllIllIIllIIllIlIlIIIlllIlllIIlIIlllIlIllIIl();
            this.ay = iIllIIIlllIIllIlllIllIlIllIlllIIIlIIllIIIlIIlIIIlIIlllIIllIlllIIlIIllIIlIIllIIlIllIllIIllIllIlIlIIlIIIllIIllIlIIlIlllIIlllIIIlllIIlIIIllIIIlIIlIIlIIllIIIlllIIIlIIIlllIIlIllIIlIIIllIllIIllIIllIlIlIIIlllIlllIIlIIlllIlIllIIl;
            ay = iIllIIIlllIIllIlllIllIlIllIlllIIIlIIllIIIlIIlIIIlIIlllIIllIlllIIlIIllIIlIIllIIlIllIllIIllIllIlIlIIlIIIllIIllIlIIlIlllIIlllIIIlllIIlIIIllIIIlIIlIIlIIllIIIlllIIIlIIIlllIIlIllIIlIIIllIllIIllIIllIlIlIIIlllIlllIIlIIlllIlIllIIl;
        }
        this.az = PacketEvents.getAPI().getProtocolManager().transformWrappers((PacketWrapper)ay, (Object)channel, true);
    }
    
    @Override
    public void IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(@NotNull final User user, @NotNull final Channel channel) {
        Intrinsics.checkNotNullParameter((Object)user, "");
        Intrinsics.checkNotNullParameter((Object)channel, "");
        if (!this.aw.compareAndSet(false, true)) {
            return;
        }
        if (user.getTotalWorldHeight() >> 4 != this.au) {
            Log.debug("Different world height " + (user.getTotalWorldHeight() >> 4) + '/' + this.au + ", ignoring the chunk packet", new Object[0]);
            this.IIllIIlllIIllIlIIIlllIIlIIllIIIlIIllIIlllIIIllIIIllIlIllIIIlIIllIlllIllIIllIIllIIlllIlllIIlIIlIIlIlllIIIlIlIIlIlIIlllIIlIIIllIlIlllIIIlllIIlllIIllIIlIIllIllIlIlllIIllIIIlIlIlIlIIlllIIllIIllIIlllIIllIIllIIlllIIIlllIIIlIIlllIlIlll();
            return;
        }
        this.IIllIllIIllIlIIlIlIIlllIllIIllIIllIlIIllIIIllIIIllIIllIIIlIllIIlIIlllIllIIlIllIllIIlIIlIlIIIlIllIIllIlllIllIIIllIIlIIlllIllIIlIIIlIIllIIllIIlIlIIlIllIIlllIlIIlllIIlIlIIlIIlIlllIIlIIIlIIllIllIlllIIlllIIIlIIllIIIlll(user, channel);
        final Object[] az = this.az;
        if (az != null) {
            this.az = null;
            PacketEvents.getAPI().getProtocolManager().writePacketsSilently((Object)channel, Arrays.copyOf(az, az.length));
            return;
        }
        PacketWrapper<?> packetWrapper;
        if ((packetWrapper = this.ay) == null) {
            packetWrapper = this.IIllIIIlllIIllIlllIllIlIllIlllIIIlIIllIIIlIIlIIIlIIlllIIllIlllIIlIIllIIlIIllIIlIllIllIIllIllIlIlIIlIIIllIIllIlIIlIlllIIlllIIIlllIIlIIIllIIIlIIlIIlIIllIIIlllIIIlIIIlllIIlIllIIlIIIllIllIIllIIllIlIlIIIlllIlllIIlIIlllIlIllIIl();
        }
        PacketEvents.getAPI().getProtocolManager().writePacketSilently((Object)channel, (PacketWrapper)packetWrapper);
    }
    
    protected void IIllIllIIllIlIIlIlIIlllIllIIllIIllIlIIllIIIllIIIllIIllIIIlIllIIlIIlllIllIIlIllIllIIlIIlIlIIIlIllIIllIlllIllIIIllIIlIIlllIllIIlIIIlIIllIIllIIlIlIIlIllIIlllIlIIlllIIlIlIIlIIlIlllIIlIIIlIIllIllIlllIIlllIIIlIIllIIIlll(@NotNull final User user, @NotNull final Channel channel) {
        Intrinsics.checkNotNullParameter((Object)user, "");
        Intrinsics.checkNotNullParameter((Object)channel, "");
    }
    
    @Override
    public boolean IIlllIllIllIIIlllIIlllIlllIIlllIlllIllIlIIIlIIIlIIIlIllIlllIllIlIIIlIlIIIlllIIllIIIlllIIlIIllIIIlIlIIIllIIllIlIIIlllIllIIIllIIllIIIlIIlIlllIIllIlIIIlIIllIIllIlllIIlIllIIllIIIlIIlllIIllIlllIIlIIllIlllIIllIIIlllIIllIllIIlllIIIlllIIIllIIll() {
        return this.aw.get();
    }
    
    @Override
    public boolean IIIllIIllIIllIIlllIIlllIIllIIllIllIIIlIlIlIlIIllIIIlIIllIIlllIIIlIllIllIIlllIlllIIIllIlIIIllIIIlIlIIlIIlllIIlllIIlllIlllIIlllIIIlIlllIIlllIlIIlIIlllIIlllIIIllIlIIIllIllIIllIIlllIIllIIlIIIlIIlIllIllIIllIIIlllIIlllIIllIIIllIIIllIIIlIIIlIIIll() {
        return this.ax.get();
    }
    
    public final void IIIllIllIIlllIIllIlIllIlIIlIIlIIIlIlIIllIlIlllIllIIllIIllIIlIIIlIIllIIllIlIIllIIllIIIllIIIlIIlllIIIllIlllIlIIllIIlIIIlIlIlIIlIIlIlIllIIlIlllIllIIllIIIllIlIIIllIIlIIllIllIIIlIIllIIIlllIIIllIIlIllIIlllIIllIIlllIIlllIIl() {
        this.ax.set(true);
    }
    
    @Override
    public void IIllIIllIIlllIlIlIIlIIIlllIIllIIIlllIIllIIllIIIlllIlIIIlllIIIlIIlllIIlIlIIIllIIIlllIlIIlIlllIIIllIllIllIIlIIllIIlllIlllIIlllIIIlIllIllIIIllIIIlllIIllIIIlllIIlllIlIlllIIIlIIlIIIllIllIIlllIIllIIIllIIlIIIlllIllIIIlIIllIIIlIllIIllIllIllIllIll() {
        if (!this.aw.compareAndSet(false, true)) {
            return;
        }
        this.IIllIIlllIIllIlIIIlllIIlIIllIIIlIIllIIlllIIIllIIIllIlIllIIIlIIllIlllIllIIllIIllIIlllIlllIIlIIlIIlIlllIIIlIlIIlIlIIlllIIlIIIllIlIlllIIIlllIIlllIIllIIlIIllIllIlIlllIIllIIIlIlIlIlIIlllIIllIIllIIlllIIllIIllIIlllIIIlllIIIlIIlllIlIlll();
    }
    
    private final void IIllIIlllIIllIlIIIlllIIlIIllIIIlIIllIIlllIIIllIIIllIlIllIIIlIIllIlllIllIIllIIllIIlllIlllIIlIIlIIlIlllIIIlIlIIlIlIIlllIIlIIIllIlIlllIIIlllIIlllIIllIIlIIllIllIlIlllIIllIIIlIlIlIlIIlllIIllIIllIIlllIIllIIllIIlllIIIlllIIIlIIlllIlIlll() {
        final Object[] az = this.az;
        if (az == null) {
            return;
        }
        final Object[] array = az;
        this.az = null;
        for (int i = 0; i < array.length; ++i) {
            final Object o = array[i];
            try {
                ByteBufHelper.release(o);
            }
            catch (final Throwable t) {}
        }
    }
    
    @NotNull
    protected abstract PacketWrapper<?> IIllIIIlllIIllIlllIllIlIllIlllIIIlIIllIIIlIIlIIIlIIlllIIllIlllIIlIIllIIlIIllIIlIllIllIIllIllIlIlIIlIIIllIIllIlIIlIlllIIlllIIIlllIIlIIIllIIIlIIlIIlIIllIIIlllIIIlIIIlllIIlIllIIlIIIllIllIIllIIllIlIlIIIlllIlllIIlIIlllIlIllIIl();
}
