// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.a;

import io.fairyproject.libs.packetevents.PacketEvents;
import io.netty.channel.Channel;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import io.fairyproject.libs.packetevents.wrapper.PacketWrapper;

public final class IIllIIlllIIllIlllIIlIlllIIIllIIlllIlIlIIIllIIlllIIIlllIIIlIIllIIIlIIlIIllIlllIIlllIIlllIllIIllIIlllIlIIIllIIlIIlllIIlIIIlIIlllIIllIIlllIIIlIllIlllIIllIIlIIlllIIIllIIlllIIlllIIllIIlIIllIIlllIIlllIIllIllIllIIIllIIIlllIllIIllIIlllIIlIllIlIIllIIl extends IIIllIIlIIllIlIIllIIlIIlIllIIlIllIIllIllIIlIIllIIIlIlllIIIlllIIlIIlIllIIIllIllIllIIlllIIllIIllIIIlIllIIlIIlIIlllIlIIIlllIllIIlIlIlllIlllIIIlIlllIllIIllIlIIlllIIIlllIIlIIIlllIIlIllIIIlIIllIllIIlllIIIlllIIIlIIlIIIllIlIIIlIll
{
    @NotNull
    private final PacketWrapper<?> be;
    
    public IIllIIlllIIllIlllIIlIlllIIIllIIlllIlIlIIIllIIlllIIIlllIIIlIIllIIIlIIlIIllIlllIIlllIIlllIllIIllIIlllIlIIIllIIlIIlllIIlIIIlIIlllIIllIIlllIIIlIllIlllIIllIIlIIlllIIIllIIlllIIlllIIllIIlIIllIIlllIIlllIIllIllIllIIIllIIIlllIllIIllIIlllIIlIllIlIIllIIl(@NotNull final PacketWrapper<?> be) {
        Intrinsics.checkNotNullParameter((Object)be, "");
        this.be = be;
    }
    
    @Override
    public void IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(@NotNull final Channel channel) {
        Intrinsics.checkNotNullParameter((Object)channel, "");
        PacketEvents.getAPI().getProtocolManager().writePacketsSilently((Object)channel, new Object[] { this.be });
    }
}
