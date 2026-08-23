// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.bootstrap.bukkit;

import java.nio.file.Path;
import org.bukkit.plugin.Plugin;
import org.bukkit.Bukkit;
import io.fairyproject.plugin.PluginAction;

public class BukkitPluginAction implements PluginAction
{
    private final BukkitPlugin bukkitPlugin;
    
    public BukkitPluginAction(final BukkitPlugin bukkitPlugin) {
        this.bukkitPlugin = bukkitPlugin;
    }
    
    public void close() {
        Bukkit.getPluginManager().disablePlugin((Plugin)this.bukkitPlugin);
    }
    
    public boolean isClosed() {
        return !this.bukkitPlugin.isEnabled();
    }
    
    public Path getDataFolder() {
        return this.bukkitPlugin.getDataFolder().toPath();
    }
}
