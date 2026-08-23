// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.api.event;

import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.bukkit.entity.Player;
import kotlin.Metadata;
import io.fairyproject.bukkit.listener.events.PlayerCallableEvent;

@Metadata(mv = { 1, 9, 0 }, k = 1, xi = 48, d1 = { "\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007R\u0017\u0010\b\u001a\u00020\u00048\u0007¢\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\n\u0010\u000b" }, d2 = { "Ldev/imanity/antixray/api/event/PlayerBypassChangedEvent;", "Lio/fairyproject/bukkit/listener/events/PlayerCallableEvent;", "Lorg/bukkit/entity/Player;", "p0", "", "p1", "<init>", "(Lorg/bukkit/entity/Player;Z)V", "bypassed", "Z", "getBypassed", "()Z" })
public final class PlayerBypassChangedEvent extends PlayerCallableEvent
{
    private final boolean bypassed;
    
    public PlayerBypassChangedEvent(@NotNull final Player player, final boolean bypassed) {
        Intrinsics.checkNotNullParameter((Object)player, "");
        super(player);
        this.bypassed = bypassed;
    }
    
    @JvmName(name = "getBypassed")
    public final boolean getBypassed() {
        return this.bypassed;
    }
}
