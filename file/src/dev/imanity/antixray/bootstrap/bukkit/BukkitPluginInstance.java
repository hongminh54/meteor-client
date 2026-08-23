// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.bootstrap.bukkit;

import io.fairyproject.plugin.PluginAction;
import io.fairyproject.plugin.initializer.PluginClassInitializer;
import dev.imanity.antixray.bootstrap.instance.AbstractPluginInstance;

final class BukkitPluginInstance extends AbstractPluginInstance
{
    public BukkitPluginInstance(final PluginClassInitializer pluginClassInitializer) {
        super(pluginClassInitializer);
    }
    
    @Override
    protected ClassLoader getClassLoader() {
        return ((BukkitPlugin)BukkitPlugin.INSTANCE).getPluginClassLoader();
    }
    
    @Override
    protected PluginAction getPluginAction() {
        return (PluginAction)new BukkitPluginAction((BukkitPlugin)BukkitPlugin.INSTANCE);
    }
}
