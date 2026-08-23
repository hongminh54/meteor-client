// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.hook.slimefun;

import io.fairyproject.container.PostInitialize;
import io.fairyproject.log.Log;
import dev.imanity.antixray.bootstrap.bukkit.BukkitPlugin;
import org.bukkit.plugin.Plugin;
import org.bukkit.event.Listener;
import org.bukkit.Bukkit;
import kotlin.Metadata;
import io.fairyproject.container.InjectableComponent;

@InjectableComponent
@Metadata(mv = { 1, 9, 0 }, k = 1, xi = 48, d1 = { "\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u000f\u0010\u0005\u001a\u00020\u0004H\u0007¢\u0006\u0004\b\u0005\u0010\u0003" }, d2 = { "Ldev/imanity/antixray/hook/slimefun/SlimefunHook;", "", "<init>", "()V", "", "onPostInitialize" })
public final class SlimefunHook
{
    @PostInitialize
    public final void onPostInitialize() {
        final Plugin plugin = Bukkit.getPluginManager().getPlugin("Slimefun");
        if (plugin == null) {
            return;
        }
        final Plugin plugin2 = plugin;
        Bukkit.getPluginManager().registerEvents((Listener)new SlimefunListener(), (Plugin)BukkitPlugin.INSTANCE);
        Log.info("[RaytraceAntiXray] Successfully hooked into Slimefun " + plugin2.getDescription().getVersion(), new Object[0]);
    }
}
