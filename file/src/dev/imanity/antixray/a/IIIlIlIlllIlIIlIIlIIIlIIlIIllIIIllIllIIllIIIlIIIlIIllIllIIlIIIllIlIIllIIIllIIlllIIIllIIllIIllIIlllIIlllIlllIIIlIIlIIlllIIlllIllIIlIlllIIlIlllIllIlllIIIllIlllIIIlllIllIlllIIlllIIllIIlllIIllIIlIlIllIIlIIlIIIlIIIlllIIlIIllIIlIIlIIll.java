// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.a;

import org.jetbrains.annotations.NotNull;
import io.fairyproject.container.InjectableComponent;
import org.bukkit.plugin.java.JavaPlugin;
import kotlin.jvm.internal.Intrinsics;
import dev.imanity.antixray.bootstrap.bukkit.BukkitPlugin;
import org.bukkit.plugin.Plugin;
import io.fairyproject.container.configuration.Configuration;

@Configuration
public final class IIIlIlIlllIlIIlIIlIIIlIIlIIllIIIllIllIIllIIIlIIIlIIllIllIIlIIIllIlIIllIIIllIIlllIIIllIIllIIllIIlllIIlllIlllIIIlIIlIIlllIIlllIllIIlIlllIIlIlllIllIlllIIIllIlllIIIlllIllIlllIIlllIIllIIlllIIllIIlIlIllIIlIIlIIIlIIIlllIIlIIllIIlIIlIIll
{
    @InjectableComponent
    @NotNull
    public final Plugin IlllIIIlIlIlllIIIlIIlIIlIIIlIIlIIlIIllIlllIlIIlllIIllIIlllIlllIlllIIlllIIllIlllIIllIlIllIIlIIIlIIIlllIIlllIIIllIIllIIlllIlllIllIIllIIIlllIIlIIllIllIIlIIllIIllIIlllIIIlllIIlIIllIIIlllIlIIllIllIIlllIIlIIlIllIIllIIlllIllIllIllIlIl() {
        final JavaPlugin instance = BukkitPlugin.INSTANCE;
        Intrinsics.checkNotNullExpressionValue((Object)instance, "");
        return (Plugin)instance;
    }
}
