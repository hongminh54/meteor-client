// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.a;

import org.bukkit.event.EventHandler;
import dev.imanity.antixray.nms.Native;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import io.fairyproject.container.InjectableComponent;
import io.fairyproject.bukkit.listener.RegisterAsListener;
import org.bukkit.event.Listener;

@RegisterAsListener
@InjectableComponent
public final class IlllIlIIIlllIllIIIlllIIlIlllIlIIlIlIllIIllIIIlllIIllIIIlIIlIlIIIlllIIIlIIlIIlIlIIIlllIIlIIlllIIllIIllIlllIIllIllIIllIlIIlIlIIllIIllIIIllIIllIIlllIIlIIIlllIlIIlllIIIlIIIllIIIlIIlIIllIIllIIlIIIlIIllIIIllIIIllIlllIIIllIIllIIIlIIllIIIll implements Listener
{
    @EventHandler
    public final void IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(@NotNull final AsyncPlayerPreLoginEvent asyncPlayerPreLoginEvent) {
        Intrinsics.checkNotNullParameter((Object)asyncPlayerPreLoginEvent, "");
        Native.INSTANCE.resetEmptyTicks();
    }
}
