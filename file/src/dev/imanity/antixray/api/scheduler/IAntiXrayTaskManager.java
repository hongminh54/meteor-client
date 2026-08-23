// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.api.scheduler;

import org.jetbrains.annotations.NotNull;
import org.bukkit.entity.Player;
import kotlin.Metadata;

@Metadata(mv = { 1, 9, 0 }, k = 1, xi = 48, d1 = { "\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0017\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H'¢\u0006\u0004\b\u0005\u0010\u0006J\u0017\u0010\b\u001a\u00020\u00072\u0006\u0010\u0003\u001a\u00020\u0002H&¢\u0006\u0004\b\b\u0010\tJ\u0017\u0010\n\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H'¢\u0006\u0004\b\n\u0010\u0006J\u0017\u0010\f\u001a\u00020\u000b2\u0006\u0010\u0003\u001a\u00020\u0002H&¢\u0006\u0004\b\f\u0010\r" }, d2 = { "Ldev/imanity/antixray/api/scheduler/IAntiXrayTaskManager;", "", "Lorg/bukkit/entity/Player;", "p0", "Ldev/imanity/antixray/api/scheduler/IAntiXrayTask;", "get", "(Lorg/bukkit/entity/Player;)Ldev/imanity/antixray/api/scheduler/IAntiXrayTask;", "", "isPresent", "(Lorg/bukkit/entity/Player;)Z", "start", "", "stop", "(Lorg/bukkit/entity/Player;)V" })
public interface IAntiXrayTaskManager
{
    boolean isPresent(@NotNull final Player p0);
    
    @NotNull
    IAntiXrayTask get(@NotNull final Player p0);
    
    @NotNull
    IAntiXrayTask start(@NotNull final Player p0);
    
    void stop(@NotNull final Player p0);
}
