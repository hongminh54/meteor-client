// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.hook.itemsadder;

import io.fairyproject.container.PreInitialize;
import dev.imanity.antixray.bootstrap.bukkit.BukkitPlugin;
import org.bukkit.plugin.Plugin;
import org.bukkit.event.Listener;
import dev.imanity.antixray.api.tick.DelayInitialTick;
import kotlin.jvm.internal.Intrinsics;
import org.bukkit.Server;
import org.jetbrains.annotations.NotNull;
import dev.imanity.antixray.api.tick.InitialTickProcessor;
import kotlin.Metadata;
import io.fairyproject.container.InjectableComponent;

@InjectableComponent
@Metadata(mv = { 1, 9, 0 }, k = 1, xi = 48, d1 = { "\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0006\b\u0007\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u000f\u0010\t\u001a\u00020\bH\u0007¢\u0006\u0004\b\t\u0010\nR\u0014\u0010\u000b\u001a\u00020\u00028\u0002X\u0083\u0004¢\u0006\u0006\n\u0004\b\u000b\u0010\fR\u0014\u0010\r\u001a\u00020\u00048\u0002X\u0083\u0004¢\u0006\u0006\n\u0004\b\r\u0010\u000e" }, d2 = { "Ldev/imanity/antixray/hook/itemsadder/ItemsAdderComponent;", "", "Ldev/imanity/antixray/api/tick/InitialTickProcessor;", "p0", "Lorg/bukkit/Server;", "p1", "<init>", "(Ldev/imanity/antixray/api/tick/InitialTickProcessor;Lorg/bukkit/Server;)V", "", "onPreInitialize", "()V", "initialTickProcessor", "Ldev/imanity/antixray/api/tick/InitialTickProcessor;", "server", "Lorg/bukkit/Server;" })
public final class ItemsAdderComponent
{
    @NotNull
    private final InitialTickProcessor initialTickProcessor;
    @NotNull
    private final Server server;
    
    public ItemsAdderComponent(@NotNull final InitialTickProcessor initialTickProcessor, @NotNull final Server server) {
        Intrinsics.checkNotNullParameter((Object)initialTickProcessor, "");
        Intrinsics.checkNotNullParameter((Object)server, "");
        this.initialTickProcessor = initialTickProcessor;
        this.server = server;
    }
    
    @PreInitialize
    public final void onPreInitialize() {
        if (ItemsAdderHook.INSTANCE.isHooked()) {
            final ItemsAdderDelayInitialTick itemsAdderDelayInitialTick = new ItemsAdderDelayInitialTick(this.initialTickProcessor);
            this.initialTickProcessor.addDelay(itemsAdderDelayInitialTick);
            this.server.getPluginManager().registerEvents((Listener)itemsAdderDelayInitialTick, (Plugin)BukkitPlugin.INSTANCE);
        }
    }
}
