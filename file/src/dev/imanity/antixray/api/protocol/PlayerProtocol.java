// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.api.protocol;

import io.netty.buffer.ByteBuf;
import io.fairyproject.libs.packetevents.PacketEvents;
import io.fairyproject.libs.packetevents.wrapper.PacketWrapper;
import kotlin.jvm.internal.Intrinsics;
import java.util.concurrent.atomic.AtomicInteger;
import org.jetbrains.annotations.NotNull;
import io.netty.channel.Channel;
import kotlin.Metadata;

@Metadata(mv = { 1, 9, 0 }, k = 1, xi = 48, d1 = { "\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\t\u001a\u00020\nJ\u0012\u0010\u000b\u001a\u00020\n2\n\u0010\f\u001a\u0006\u0012\u0002\b\u00030\rJ\u0012\u0010\u000e\u001a\u00020\n2\n\u0010\f\u001a\u0006\u0012\u0002\b\u00030\rJ\u0012\u0010\u000f\u001a\u00020\n2\n\u0010\f\u001a\u0006\u0012\u0002\b\u00030\rJ\u000e\u0010\u000f\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\u0011R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012" }, d2 = { "Ldev/imanity/antixray/api/protocol/PlayerProtocol;", "", "channel", "Lio/netty/channel/Channel;", "(Lio/netty/channel/Channel;)V", "flushPacketCount", "", "packetCount", "Ljava/util/concurrent/atomic/AtomicInteger;", "flush", "", "sendPacket", "packet", "Lio/fairyproject/libs/packetevents/wrapper/PacketWrapper;", "write", "writeSilently", "byteBuf", "Lio/netty/buffer/ByteBuf;", "api" })
public final class PlayerProtocol
{
    @NotNull
    private final Channel channel;
    @NotNull
    private final AtomicInteger packetCount;
    private int flushPacketCount;
    
    public PlayerProtocol(@NotNull final Channel channel) {
        Intrinsics.checkNotNullParameter((Object)channel, "");
        this.channel = channel;
        this.packetCount = new AtomicInteger(0);
    }
    
    public final void sendPacket(@NotNull final PacketWrapper<?> packetWrapper) {
        Intrinsics.checkNotNullParameter((Object)packetWrapper, "");
        PacketEvents.getAPI().getProtocolManager().sendPacketSilently((Object)this.channel, (PacketWrapper)packetWrapper);
    }
    
    public final void write(@NotNull final PacketWrapper<?> packetWrapper) {
        Intrinsics.checkNotNullParameter((Object)packetWrapper, "");
        this.packetCount.incrementAndGet();
        PacketEvents.getAPI().getProtocolManager().writePacket((Object)this.channel, (PacketWrapper)packetWrapper);
    }
    
    public final void writeSilently(@NotNull final PacketWrapper<?> packetWrapper) {
        Intrinsics.checkNotNullParameter((Object)packetWrapper, "");
        this.packetCount.incrementAndGet();
        PacketEvents.getAPI().getProtocolManager().writePacketSilently((Object)this.channel, (PacketWrapper)packetWrapper);
    }
    
    public final void writeSilently(@NotNull final ByteBuf byteBuf) {
        Intrinsics.checkNotNullParameter((Object)byteBuf, "");
        this.packetCount.incrementAndGet();
        PacketEvents.getAPI().getProtocolManager().writePacketSilently((Object)this.channel, (Object)byteBuf);
    }
    
    public final void flush() {
        final int value = this.packetCount.get();
        if (this.flushPacketCount != value) {
            this.flushPacketCount = value;
            this.channel.flush();
        }
    }
}
