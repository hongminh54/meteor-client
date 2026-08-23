// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.bootstrap.instance;

import io.fairyproject.log.Log;
import io.fairyproject.plugin.PluginManager;
import io.fairyproject.plugin.PluginDescription;
import io.fairyproject.libs.gson.JsonObject;
import io.fairyproject.plugin.PluginAction;
import io.fairyproject.plugin.Plugin;
import java.util.concurrent.CompletableFuture;
import io.fairyproject.plugin.initializer.PluginClassInitializer;

public abstract class AbstractPluginInstance implements PluginInstance
{
    private final PluginClassInitializer pluginClassInitializer;
    protected final CompletableFuture<Plugin> pluginCompletableFuture;
    protected Plugin plugin;
    protected ClassLoader classLoader;
    
    protected abstract ClassLoader getClassLoader();
    
    protected abstract PluginAction getPluginAction();
    
    @Override
    public void init(final JsonObject jsonObject) {
        final PluginDescription pluginDescription = new PluginDescription(jsonObject);
        this.classLoader = this.pluginClassInitializer.initializeClassLoader(pluginDescription.getName(), this.getClassLoader());
        this.plugin = this.pluginClassInitializer.create(pluginDescription.getMainClass(), this.classLoader);
        PluginManager.INSTANCE.onPluginPreLoaded(this.classLoader, pluginDescription, this.getPluginAction(), (CompletableFuture)this.pluginCompletableFuture);
        this.plugin.initializePlugin(pluginDescription, this.getPluginAction(), this.classLoader);
    }
    
    @Override
    public void onLoad() {
        PluginManager.INSTANCE.addPlugin(this.plugin);
        PluginManager.INSTANCE.onPluginInitial(this.plugin);
        this.pluginClassInitializer.onPluginLoad(this.plugin);
        this.plugin.onInitial();
    }
    
    @Override
    public void onEnable() {
        System.out.println("\u001b[36m[Spigotunlocked.com] - COSMO\u001b[0m");
        this.plugin.onPreEnable();
        if (this.plugin.isClosed()) {
            return;
        }
        PluginManager.INSTANCE.onPluginEnable(this.plugin);
        try {
            this.plugin.onPluginEnable();
        }
        catch (final Throwable v1) {
            if (!this.plugin.isClosed()) {
                Log.error(v1);
            }
        }
    }
    
    @Override
    public void onDisable() {
        try {
            this.plugin.onPluginDisable();
        }
        catch (final Throwable t) {
            Log.error(t);
        }
        this.plugin.getCompositeTerminable().closeAndReportException();
        PluginManager.INSTANCE.onPluginDisable(this.plugin);
        PluginManager.INSTANCE.removePlugin(this.plugin);
        this.pluginClassInitializer.onPluginUnload(this.plugin);
    }
    
    public AbstractPluginInstance(final PluginClassInitializer pluginClassInitializer) {
        this.pluginCompletableFuture = new CompletableFuture<Plugin>();
        this.pluginClassInitializer = pluginClassInitializer;
    }
    
    @Override
    public Plugin getPlugin() {
        return this.plugin;
    }
}
