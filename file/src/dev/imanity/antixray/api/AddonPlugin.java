// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.api;

import io.fairyproject.log.Log;
import org.bukkit.Bukkit;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.bukkit.plugin.java.JavaPlugin;
import kotlin.Metadata;
import io.fairyproject.plugin.Plugin;

@Metadata(mv = { 1, 9, 0 }, k = 1, xi = 48, d1 = { "\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\b\u0016\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u000f\u0010\u0007\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\u0007\u0010\bR\u0014\u0010\t\u001a\u00020\u00028\u0002X\u0083\u0004¢\u0006\u0006\n\u0004\b\t\u0010\n" }, d2 = { "Ldev/imanity/antixray/api/AddonPlugin;", "Lio/fairyproject/plugin/Plugin;", "Lorg/bukkit/plugin/java/JavaPlugin;", "p0", "<init>", "(Lorg/bukkit/plugin/java/JavaPlugin;)V", "", "onPreEnable", "()V", "plugin", "Lorg/bukkit/plugin/java/JavaPlugin;" })
public class AddonPlugin extends Plugin
{
    @NotNull
    private final JavaPlugin plugin;
    
    public AddonPlugin(@NotNull final JavaPlugin plugin) {
        Intrinsics.checkNotNullParameter((Object)plugin, "");
        this.plugin = plugin;
    }
    
    public void onPreEnable() {
        final org.bukkit.plugin.Plugin plugin = Bukkit.getPluginManager().getPlugin("raytraced-antixray");
        if (plugin == null) {
            return;
        }
        final org.bukkit.plugin.Plugin plugin2 = plugin;
        if (!Intrinsics.areEqual((Object)plugin2.getDescription().getVersion(), (Object)this.plugin.getDescription().getVersion())) {
            Log.warn("The version of the AntiXray Addon is different from the main plugin. This may cause issues!!", new Object[0]);
            Log.warn("-> Main Plugin Version: " + plugin2.getDescription().getVersion(), new Object[0]);
            Log.warn("-> Addon Plugin Version: " + this.plugin.getDescription().getVersion(), new Object[0]);
        }
    }
}
