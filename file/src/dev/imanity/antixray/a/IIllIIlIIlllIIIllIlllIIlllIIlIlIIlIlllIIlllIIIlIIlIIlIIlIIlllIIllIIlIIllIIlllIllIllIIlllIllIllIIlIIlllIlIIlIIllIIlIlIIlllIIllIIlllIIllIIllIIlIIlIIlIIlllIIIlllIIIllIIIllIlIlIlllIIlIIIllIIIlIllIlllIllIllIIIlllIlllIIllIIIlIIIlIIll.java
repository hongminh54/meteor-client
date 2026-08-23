// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.a;

import io.fairyproject.libs.packetevents.PacketEvents;
import io.netty.channel.Channel;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import io.fairyproject.libs.packetevents.wrapper.PacketWrapper;

public final class IIllIIlIIlllIIIllIlllIIlllIIlIlIIlIlllIIlllIIIlIIlIIlIIlIIlllIIllIIlIIllIIlllIllIllIIlllIllIllIIlIIlllIlIIlIIllIIlIlIIlllIIllIIlllIIllIIllIIlIIlIIlIIlllIIIlllIIIllIIIllIlIlIlllIIlIIIllIIIlIllIlllIllIllIIIlllIlllIIllIIIlIIIlIIll extends IIIllIIlIIllIlIIllIIlIIlIllIIlIllIIllIllIIlIIllIIIlIlllIIIlllIIlIIlIllIIIllIllIllIIlllIIllIIllIIIlIllIIlIIlIIlllIlIIIlllIllIIlIlIlllIlllIIIlIlllIllIIllIlIIlllIIIlllIIlIIIlllIIlIllIIIlIIllIllIIlllIIIlllIIIlIIlIIIllIlIIIlIll
{
    @NotNull
    private final PacketWrapper<?> aE;
    
    public IIllIIlIIlllIIIllIlllIIlllIIlIlIIlIlllIIlllIIIlIIlIIlIIlIIlllIIllIIlIIllIIlllIllIllIIlllIllIllIIlIIlllIlIIlIIllIIlIlIIlllIIllIIlllIIllIIllIIlIIlIIlIIlllIIIlllIIIllIIIllIlIlIlllIIlIIIllIIIlIllIlllIllIllIIIlllIlllIIllIIIlIIIlIIll(@NotNull final PacketWrapper<?> ae) {
        Intrinsics.checkNotNullParameter((Object)ae, "");
        this.aE = ae;
    }
    
    @Override
    public void IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(@NotNull final Channel channel) {
        Intrinsics.checkNotNullParameter((Object)channel, "");
        PacketEvents.getAPI().getProtocolManager().writePacketSilently((Object)channel, (PacketWrapper)this.aE);
    }
}
