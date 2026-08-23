// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.bootstrap.bukkit;

import io.fairyproject.bukkit.FairyBukkitPlatform;
import io.fairyproject.FairyPlatform;
import dev.imanity.antixray.bootstrap.type.PlatformType;
import org.bukkit.Bukkit;
import org.jetbrains.annotations.Nullable;
import dev.imanity.antixray.bootstrap.platform.AbstractPlatformBootstrap;

class BukkitPlatformBootstrap extends AbstractPlatformBootstrap
{
    @Override
    protected void onFailure(@Nullable final Throwable t) {
        if (t != null) {
            t.printStackTrace();
        }
        Bukkit.shutdown();
    }
    
    @Override
    protected PlatformType getPlatformType() {
        return PlatformType.BUKKIT;
    }
    
    @Override
    protected FairyPlatform createPlatform() {
        return (FairyPlatform)new FairyBukkitPlatform(BukkitPlugin.INSTANCE.getDataFolder());
    }
}
