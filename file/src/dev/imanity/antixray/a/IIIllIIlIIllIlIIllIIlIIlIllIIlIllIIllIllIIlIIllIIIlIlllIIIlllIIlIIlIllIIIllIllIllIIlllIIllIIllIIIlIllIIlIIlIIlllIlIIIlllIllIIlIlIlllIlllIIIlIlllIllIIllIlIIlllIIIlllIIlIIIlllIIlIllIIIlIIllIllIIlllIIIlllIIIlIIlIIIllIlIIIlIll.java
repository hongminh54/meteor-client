// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.a;

import kotlin.jvm.internal.Intrinsics;
import io.netty.channel.Channel;
import io.fairyproject.libs.packetevents.protocol.player.User;
import org.jetbrains.annotations.NotNull;
import java.util.concurrent.atomic.AtomicBoolean;

public abstract class IIIllIIlIIllIlIIllIIlIIlIllIIlIllIIllIllIIlIIllIIIlIlllIIIlllIIlIIlIllIIIllIllIllIIlllIIllIIllIIIlIllIIlIIlIIlllIlIIIlllIllIIlIlIlllIlllIIIlIlllIllIIllIlIIlllIIIlllIIlIIIlllIIlIllIIIlIIllIllIIlllIIIlllIIIlIIlIIIllIlIIIlIll implements IllIlIIlIIlllIllIIllIIIlllIlllIllIIIllIIllIIlllIllIlllIIllIlIIlIlIIllIlIIllIIIlllIIIlIIllIllIIlIIlIIIllIIIllIIlllIIllIIlIIIlIIlIlllIlllIlllIllIIlllIIlIlllIIIlIIIllIlllIIllIllIIllIIlIIIllIIllIlIlIlllIlIIlIIllIllIIllIIIllIl
{
    @NotNull
    private final AtomicBoolean ar;
    
    public IIIllIIlIIllIlIIllIIlIIlIllIIlIllIIllIllIIlIIllIIIlIlllIIIlllIIlIIlIllIIIllIllIllIIlllIIllIIllIIIlIllIIlIIlIIlllIlIIIlllIllIIlIlIlllIlllIIIlIlllIllIIllIlIIlllIIIlllIIlIIIlllIIlIllIIIlIIllIllIIlllIIIlllIIIlIIlIIIllIlIIIlIll() {
        this.ar = new AtomicBoolean(false);
    }
    
    @Override
    public void IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(@NotNull final User user, @NotNull final Channel channel) {
        Intrinsics.checkNotNullParameter((Object)user, "");
        Intrinsics.checkNotNullParameter((Object)channel, "");
        if (!this.ar.compareAndSet(false, true)) {
            return;
        }
        this.IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(channel);
    }
    
    public abstract void IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(@NotNull final Channel p0);
    
    @Override
    public boolean IIlllIllIllIIIlllIIlllIlllIIlllIlllIllIlIIIlIIIlIIIlIllIlllIllIlIIIlIlIIIlllIIllIIIlllIIlIIllIIIlIlIIIllIIllIlIIIlllIllIIIllIIllIIIlIIlIlllIIllIlIIIlIIllIIllIlllIIlIllIIllIIIlIIlllIIllIlllIIlIIllIlllIIllIIIlllIIllIllIIlllIIIlllIIIllIIll() {
        return this.ar.get();
    }
    
    @Override
    public boolean IIIllIIllIIllIIlllIIlllIIllIIllIllIIIlIlIlIlIIllIIIlIIllIIlllIIIlIllIllIIlllIlllIIIllIlIIIllIIIlIlIIlIIlllIIlllIIlllIlllIIlllIIIlIlllIIlllIlIIlIIlllIIlllIIIllIlIIIllIllIIllIIlllIIllIIlIIIlIIlIllIllIIllIIIlllIIlllIIllIIIllIIIllIIIlIIIlIIIll() {
        return true;
    }
    
    @Override
    public void IIllIIllIIlllIlIlIIlIIIlllIIllIIIlllIIllIIllIIIlllIlIIIlllIIIlIIlllIIlIlIIIllIIIlllIlIIlIlllIIIllIllIllIIlIIllIIlllIlllIIlllIIIlIllIllIIIllIIIlllIIllIIIlllIIlllIlIlllIIIlIIlIIIllIllIIlllIIllIIIllIIlIIIlllIllIIIlIIllIIIlIllIIllIllIllIllIll() {
        if (!this.ar.compareAndSet(false, true)) {
            return;
        }
        this.IIIlllIIIlIIlllIIllIlllIllIlIIlIIllIIlIIIllIllIllIIlIIlIlIIIlIIIlllIlIIllIlllIllIIlIlIlllIlIllIIllIIIlllIIlllIIIlIlIIIlIIllIllIlIIllIIlIllIIlIIllIIlllIIIlllIlIIllIIIlIllIllIllIIIllIIIllIIllIIllIIlIllIIlIllIIIlllIIIllIIl();
    }
    
    protected void IIIlllIIIlIIlllIIllIlllIllIlIIlIIllIIlIIIllIllIllIIlIIlIlIIIlIIIlllIlIIllIlllIllIIlIlIlllIlIllIIllIIIlllIIlllIIIlIlIIIlIIllIllIlIIllIIlIllIIlIIllIIlllIIIlllIlIIllIIIlIllIllIllIIIllIIIllIIllIIllIIlIllIIlIllIIIlllIIIllIIl() {
    }
}
