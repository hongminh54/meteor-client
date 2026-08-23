// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.api.event;

import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.bukkit.entity.Player;
import kotlin.Metadata;
import io.fairyproject.bukkit.listener.events.PlayerCallableEvent;

@Metadata(mv = { 1, 9, 0 }, k = 1, xi = 48, d1 = { "\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005" }, d2 = { "Ldev/imanity/antixray/api/event/PlayerRaytraceClosedEvent;", "Lio/fairyproject/bukkit/listener/events/PlayerCallableEvent;", "Lorg/bukkit/entity/Player;", "p0", "<init>", "(Lorg/bukkit/entity/Player;)V" })
public final class PlayerRaytraceClosedEvent extends PlayerCallableEvent
{
    public PlayerRaytraceClosedEvent(@NotNull final Player player) {
        Intrinsics.checkNotNullParameter((Object)player, "");
        super(player);
    }
}
