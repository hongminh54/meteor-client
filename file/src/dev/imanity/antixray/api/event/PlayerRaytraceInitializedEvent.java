// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.api.event;

import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import dev.imanity.antixray.api.IRaytracePlayer;
import kotlin.Metadata;
import io.fairyproject.bukkit.listener.events.PlayerCallableEvent;

@Metadata(mv = { 1, 9, 0 }, k = 1, xi = 48, d1 = { "\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007R\u001a\u0010\b\u001a\u00020\u00048\u0007X\u0087\u0004¢\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\n\u0010\u000b" }, d2 = { "Ldev/imanity/antixray/api/event/PlayerRaytraceInitializedEvent;", "Lio/fairyproject/bukkit/listener/events/PlayerCallableEvent;", "Lorg/bukkit/entity/Player;", "p0", "Ldev/imanity/antixray/api/IRaytracePlayer;", "p1", "<init>", "(Lorg/bukkit/entity/Player;Ldev/imanity/antixray/api/IRaytracePlayer;)V", "raytracePlayer", "Ldev/imanity/antixray/api/IRaytracePlayer;", "getRaytracePlayer", "()Ldev/imanity/antixray/api/IRaytracePlayer;" })
public final class PlayerRaytraceInitializedEvent extends PlayerCallableEvent
{
    @NotNull
    private final IRaytracePlayer raytracePlayer;
    
    public PlayerRaytraceInitializedEvent(@NotNull final Player player, @NotNull final IRaytracePlayer raytracePlayer) {
        Intrinsics.checkNotNullParameter((Object)player, "");
        Intrinsics.checkNotNullParameter((Object)raytracePlayer, "");
        super(player);
        this.raytracePlayer = raytracePlayer;
    }
    
    @NotNull
    @JvmName(name = "getRaytracePlayer")
    public final IRaytracePlayer getRaytracePlayer() {
        return this.raytracePlayer;
    }
}
