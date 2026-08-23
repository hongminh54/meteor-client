// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.a;

import io.netty.channel.Channel;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;

public final class IlIIlllIllIIIllIIllIIIlllIIIlIIIlllIIlIIlllIIIlIllIIllIlIIlIIlIlIIlllIIlIIllIlIlllIIIlIIIlIIIlIIlIlllIIlIIIllIIIllIIIlIlIlllIIlIIlllIIlllIIllIlIllIlllIIIlllIlIlIIllIllIlllIIlIIlIIIllIlllIllIlllIIIlIllIllIllIIllIlllIIlIIIll extends IIIllIIlIIllIlIIllIIlIIlIllIIlIllIIllIllIIlIIllIIIlIlllIIIlllIIlIIlIllIIIllIllIllIIlllIIllIIllIIIlIllIIlIIlIIlllIlIIIlllIllIIlIlIlllIlllIIIlIlllIllIIllIlIIlllIIIlllIIlIIIlllIIlIllIIIlIIllIllIIlllIIIlllIIIlIIlIIIllIlIIIlIll
{
    @NotNull
    private final Function0<Unit> bd;
    
    public IlIIlllIllIIIllIIllIIIlllIIIlIIIlllIIlIIlllIIIlIllIIllIlIIlIIlIlIIlllIIlIIllIlIlllIIIlIIIlIIIlIIlIlllIIlIIIllIIIllIIIlIlIlllIIlIIlllIIlllIIllIlIllIlllIIIlllIlIlIIllIllIlllIIlIIlIIIllIlllIllIlllIIIlIllIllIllIIllIlllIIlIIIll(@NotNull final Function0<Unit> bd) {
        Intrinsics.checkNotNullParameter((Object)bd, "");
        this.bd = bd;
    }
    
    @Override
    public void IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(@NotNull final Channel channel) {
        Intrinsics.checkNotNullParameter((Object)channel, "");
        this.bd.invoke();
    }
}
