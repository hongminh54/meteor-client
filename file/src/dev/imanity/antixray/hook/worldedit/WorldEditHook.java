// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.hook.worldedit;

import io.fairyproject.container.PostInitialize;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.Plugin;
import dev.imanity.antixray.hook.worldedit.v6.V6WorldEditListener;
import dev.imanity.antixray.hook.worldedit.v7.V7WorldEditListener;
import kotlin.text.StringsKt;
import kotlin.Unit;
import dev.imanity.antixray.hook.worldedit.fawe.FaweWorldEditListener;
import kotlin.jvm.functions.Function5;
import kotlin.jvm.functions.Function1;
import io.fairyproject.log.Log;
import org.bukkit.Bukkit;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import dev.imanity.antixray.config.GlobalAntiXrayConfig;
import kotlin.Metadata;
import io.fairyproject.container.InjectableComponent;

@InjectableComponent
@Metadata(mv = { 1, 9, 0 }, k = 1, xi = 48, d1 = { "\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u000f\u0010\u0007\u001a\u00020\u0006H\u0007¢\u0006\u0004\b\u0007\u0010\bR\u0014\u0010\t\u001a\u00020\u00028\u0002X\u0083\u0004¢\u0006\u0006\n\u0004\b\t\u0010\n" }, d2 = { "Ldev/imanity/antixray/hook/worldedit/WorldEditHook;", "", "Ldev/imanity/antixray/config/GlobalAntiXrayConfig;", "p0", "<init>", "(Ldev/imanity/antixray/config/GlobalAntiXrayConfig;)V", "", "onPostInitialize", "()V", "config", "Ldev/imanity/antixray/config/GlobalAntiXrayConfig;" })
public final class WorldEditHook
{
    @NotNull
    private final GlobalAntiXrayConfig config;
    
    public WorldEditHook(@NotNull final GlobalAntiXrayConfig config) {
        Intrinsics.checkNotNullParameter((Object)config, "");
        this.config = config;
    }
    
    @PostInitialize
    public final void onPostInitialize() {
        final Plugin plugin = Bukkit.getPluginManager().getPlugin("WorldEdit");
        final Plugin plugin2 = Bukkit.getPluginManager().getPlugin("FastAsyncWorldEdit");
        if (plugin != null || plugin2 != null) {
            if (!this.config.getEnableWorldEditTracking()) {
                Log.info("[RaytraceAntiXray] WorldEdit / FAWE hook is currently disabled.", new Object[0]);
                return;
            }
            final Plugin plugin3 = plugin2;
            String version = null;
            Label_0109: {
                if (plugin3 != null) {
                    final PluginDescriptionFile description = plugin3.getDescription();
                    if (description != null && (version = description.getVersion()) != null) {
                        break Label_0109;
                    }
                }
                final Plugin plugin4 = plugin;
                String version2 = null;
                Label_0094: {
                    if (plugin4 != null) {
                        final PluginDescriptionFile description2 = plugin4.getDescription();
                        if (description2 != null) {
                            version = (version2 = description2.getVersion());
                            break Label_0094;
                        }
                    }
                    version = (version2 = null);
                }
                if (version2 == null) {
                    throw new IllegalStateException("WorldEdit not found");
                }
            }
            final String s = version;
            final Function1 function1 = (Function1)WorldEditHook$onPostInitialize$verify.WorldEditHook$onPostInitialize$verify$1.INSTANCE;
            final Function5 function2 = (Function5)WorldEditHook$onPostInitialize$callable.WorldEditHook$onPostInitialize$callable$1.INSTANCE;
            boolean b;
            try {
                Class.forName("com.fastasyncworldedit.core.configuration.Settings").getMethod("settings", (Class<?>[])new Class[0]);
                b = true;
            }
            catch (final ClassNotFoundException ex) {
                b = false;
            }
            catch (final NoSuchMethodException ex2) {
                b = false;
            }
            final boolean b2 = b;
            if (plugin2 != null && b2) {
                new FaweWorldEditListener((Function1<? super String, Boolean>)function1, (Function5<? super String, ? super Integer, ? super Integer, ? super Integer, ? super Boolean, Unit>)function2).register();
            }
            else if (StringsKt.startsWith$default(s, "7", false, 2, (Object)null)) {
                new V7WorldEditListener((Function1<? super String, Boolean>)function1, (Function5<? super String, ? super Integer, ? super Integer, ? super Integer, ? super Boolean, Unit>)function2).register();
            }
            else {
                new V6WorldEditListener((Function1<? super String, Boolean>)function1, (Function5<? super String, ? super Integer, ? super Integer, ? super Integer, ? super Boolean, Unit>)function2).register();
            }
            if (plugin2 != null) {
                Log.info("[RaytraceAntiXray] Successfully hooked into FastAsyncWorldEdit " + s, new Object[0]);
            }
            else {
                Log.info("[RaytraceAntiXray] Successfully hooked into WorldEdit " + s, new Object[0]);
            }
        }
    }
}
