// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.hook.factionskore;

import io.fairyproject.container.PostInitialize;
import org.bukkit.plugin.PluginDescriptionFile;
import io.fairyproject.log.Log;
import dev.imanity.antixray.bootstrap.bukkit.BukkitPlugin;
import org.bukkit.plugin.Plugin;
import org.bukkit.event.Listener;
import org.bukkit.Bukkit;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import dev.imanity.antixray.a.IIllIllIIllIlIIlIlIIlllIllIIllIIllIlIIllIIIllIIIllIIllIIIlIllIIlIIlllIllIIlIllIllIIlIIlIlIIIlIllIIllIlllIllIIIllIIlIIlllIllIIlIIIlIIllIIllIIlIlIIlIllIIlllIlIIlllIIlIlIIlIIlIlllIIlIIIlIIllIllIlllIIlllIIIlIIllIIIlll;
import kotlin.Metadata;
import io.fairyproject.container.InjectableComponent;

@InjectableComponent
@Metadata(mv = { 1, 9, 0 }, k = 1, xi = 48, d1 = { "\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u000f\u0010\u0007\u001a\u00020\u0006H\u0007¢\u0006\u0004\b\u0007\u0010\bR\u0014\u0010\t\u001a\u00020\u00028\u0002X\u0083\u0004¢\u0006\u0006\n\u0004\b\t\u0010\n" }, d2 = { "Ldev/imanity/antixray/hook/factionskore/FactionsKoreHook;", "", "Ldev/imanity/antixray/a/IIllIllIIllIlIIlIlIIlllIllIIllIIllIlIIllIIIllIIIllIIllIIIlIllIIlIIlllIllIIlIllIllIIlIIlIlIIIlIllIIllIlllIllIIIllIIlIIlllIllIIlIIIlIIllIIllIIlIlIIlIllIIlllIlIIlllIIlIlIIlIIlIlllIIlIIIlIIllIllIlllIIlllIIIlIIllIIIlll;", "p0", "<init>", "(Ldev/imanity/antixray/a/IIllIllIIllIlIIlIlIIlllIllIIllIIllIlIIllIIIllIIIllIIllIIIlIllIIlIIlllIllIIlIllIllIIlIIlIlIIIlIllIIllIlllIllIIIllIIlIIlllIllIIlIIIlIIllIIllIIlIlIIlIllIIlllIlIIlllIIlIlIIlIIlIlllIIlIIIlIIllIllIlllIIlllIIIlIIllIIIlll;)V", "", "onPostInitialize", "()V", "raytraceServer", "Ldev/imanity/antixray/a/IIllIllIIllIlIIlIlIIlllIllIIllIIllIlIIllIIIllIIIllIIllIIIlIllIIlIIlllIllIIlIllIllIIlIIlIlIIIlIllIIllIlllIllIIIllIIlIIlllIllIIlIIIlIIllIIllIIlIlIIlIllIIlllIlIIlllIIlIlIIlIIlIlllIIlIIIlIIllIllIlllIIlllIIIlIIllIIIlll;" })
public final class FactionsKoreHook
{
    @NotNull
    private final IIllIllIIllIlIIlIlIIlllIllIIllIIllIlIIllIIIllIIIllIIllIIIlIllIIlIIlllIllIIlIllIllIIlIIlIlIIIlIllIIllIlllIllIIIllIIlIIlllIllIIlIIIlIIllIIllIIlIlIIlIllIIlllIlIIlllIIlIlIIlIIlIlllIIlIIIlIIllIllIlllIIlllIIIlIIllIIIlll raytraceServer;
    
    public FactionsKoreHook(@NotNull final IIllIllIIllIlIIlIlIIlllIllIIllIIllIlIIllIIIllIIIllIIllIIIlIllIIlIIlllIllIIlIllIllIIlIIlIlIIIlIllIIllIlllIllIIIllIIlIIlllIllIIlIIIlIIllIIllIIlIlIIlIllIIlllIlIIlllIIlIlIIlIIlIlllIIlIIIlIIllIllIlllIIlllIIIlIIllIIIlll raytraceServer) {
        Intrinsics.checkNotNullParameter((Object)raytraceServer, "");
        this.raytraceServer = raytraceServer;
    }
    
    @PostInitialize
    public final void onPostInitialize() {
        if (!Bukkit.getPluginManager().isPluginEnabled("FactionsKore")) {
            return;
        }
        Bukkit.getPluginManager().registerEvents((Listener)new FactionsKoreListener(this.raytraceServer), (Plugin)BukkitPlugin.INSTANCE);
        final StringBuilder append = new StringBuilder().append("[RaytraceAntiXray] Successfully hooked into FactionsKore ");
        final Plugin plugin = Bukkit.getPluginManager().getPlugin("FactionsKore");
        String version = null;
        Label_0085: {
            if (plugin != null) {
                final PluginDescriptionFile description = plugin.getDescription();
                if (description != null) {
                    version = description.getVersion();
                    break Label_0085;
                }
            }
            version = null;
        }
        Log.info(append.append(version).toString(), new Object[0]);
    }
}
