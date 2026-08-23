// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.a;

import io.fairyproject.libs.packetevents.netty.buffer.ByteBufHelper;
import io.fairyproject.libs.packetevents.PacketEvents;
import io.netty.channel.Channel;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import io.netty.buffer.ByteBuf;

public final class IIIlIIIllIIllIIllIIllIIIllIIIlIIllIIllIIllIlIIlllIIllIIIlIIlIlllIIllIIlllIIllIIllIlllIIlIIIlIIllIIllIIllIIlllIIllIlllIIlIIlIIIlIIIllIlIlllIIIllIlllIIlllIllIIIlIllIIlIIlllIllIIllIIIlIllIIlllIlIIllIllIlIIlIllIIllIIllIllIllIIlllIIlll extends IIIllIIlIIllIlIIllIIlIIlIllIIlIllIIllIllIIlIIllIIIlIlllIIIlllIIlIIlIllIIIllIllIllIIlllIIllIIllIIIlIllIIlIIlIIlllIlIIIlllIllIIlIlIlllIlllIIIlIlllIllIIllIlIIlllIIIlllIIlIIIlllIIlIllIIIlIIllIllIIlllIIIlllIIIlIIlIIIllIlIIIlIll
{
    @NotNull
    private final ByteBuf as;
    private final boolean at;
    
    public IIIlIIIllIIllIIllIIllIIIllIIIlIIllIIllIIllIlIIlllIIllIIIlIIlIlllIIllIIlllIIllIIllIlllIIlIIIlIIllIIllIIllIIlllIIllIlllIIlIIlIIIlIIIllIlIlllIIIllIlllIIlllIllIIIlIllIIlIIlllIllIIllIIIlIllIIlllIlIIllIllIlIIlIllIIllIIllIllIllIIlllIIlll(@NotNull final ByteBuf as, final boolean at) {
        Intrinsics.checkNotNullParameter((Object)as, "");
        this.as = as;
        this.at = at;
    }
    
    @Override
    public void IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(@NotNull final Channel channel) {
        Intrinsics.checkNotNullParameter((Object)channel, "");
        PacketEvents.getAPI().getProtocolManager().writePacketsSilently((Object)channel, new Object[] { this.as });
        if (this.at) {
            ByteBufHelper.release((Object)this.as);
        }
    }
    
    @Override
    protected void IIIlllIIIlIIlllIIllIlllIllIlIIlIIllIIlIIIllIllIllIIlIIlIlIIIlIIIlllIlIIllIlllIllIIlIlIlllIlIllIIllIIIlllIIlllIIIlIlIIIlIIllIllIlIIllIIlIllIIlIIllIIlllIIIlllIlIIllIIIlIllIllIllIIIllIIIllIIllIIllIIlIllIIlIllIIIlllIIIllIIl() {
        if (this.at) {
            ByteBufHelper.release((Object)this.as);
        }
    }
}
