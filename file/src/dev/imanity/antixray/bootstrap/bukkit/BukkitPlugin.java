// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.bootstrap.bukkit;

import java.net.URLConnection;
import java.io.IOException;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.bukkit.plugin.Plugin;
import dev.imanity.antixray.bootstrap.PluginClassInitializerFinder;
import org.bukkit.Bukkit;
import java.io.File;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPluginLoader;
import dev.imanity.antixray.bootstrap.platform.PlatformBootstrap;
import dev.imanity.antixray.bootstrap.PluginFileReader;
import dev.imanity.antixray.bootstrap.instance.PluginInstance;
import org.bukkit.plugin.PluginManager;
import dev.imanity.antixray.bootstrap.internal.FairyInternalIdentityMeta;
import org.bukkit.plugin.java.JavaPlugin;

@FairyInternalIdentityMeta
public final class BukkitPlugin extends JavaPlugin
{
    public static JavaPlugin INSTANCE;
    private final PluginManager pluginManager;
    private final PluginInstance instance;
    private final PluginFileReader pluginFileReader;
    private final PlatformBootstrap bootstrap;
    private boolean loaded;
    
    public BukkitPlugin(final JavaPluginLoader javaPluginLoader, final PluginDescriptionFile pluginDescriptionFile, final File file, final File file2, final PluginManager pluginManager, final PluginInstance instance, final PluginFileReader pluginFileReader, final PlatformBootstrap bootstrap) {
        super(javaPluginLoader, pluginDescriptionFile, file, file2);
        this.pluginManager = pluginManager;
        this.instance = instance;
        this.pluginFileReader = pluginFileReader;
        this.bootstrap = bootstrap;
    }
    
    public BukkitPlugin(final PluginManager pluginManager, final PluginInstance instance, final PluginFileReader pluginFileReader, final PlatformBootstrap bootstrap) {
        this.pluginManager = pluginManager;
        this.instance = instance;
        this.pluginFileReader = pluginFileReader;
        this.bootstrap = bootstrap;
    }
    
    public BukkitPlugin() {
        this(Bukkit.getPluginManager(), new BukkitPluginInstance(PluginClassInitializerFinder.find()), new PluginFileReader(), new BukkitPlatformBootstrap());
    }
    
    public void onLoad() {
        if (this.loaded) {
            return;
        }
        BukkitPlugin.INSTANCE = this;
        if (!this.bootstrap.preload()) {
            this.getLogger().warning("Failed to boot fairy! check stacktrace for the reason of failure!");
            this.pluginManager.disablePlugin((Plugin)this);
            return;
        }
        this.instance.init(this.pluginFileReader.read(this.getClass()));
        this.bootstrap.load(this.instance.getPlugin());
        this.instance.onLoad();
        this.loaded = true;
    }
    
    public void onEnable() {
        System.out.println("\u001b[36m[Spigotunlocked.com] - COSMO\u001b[0m");
        loadConfig0();
        if (!this.loaded) {
            throw new IllegalStateException("Plugin not loaded yet!");
        }
        this.bootstrap.enable();
        this.instance.onEnable();
    }
    
    public void onDisable() {
        if (!this.loaded) {
            return;
        }
        this.instance.onDisable();
        this.bootstrap.disable();
    }
    
    public ClassLoader getPluginClassLoader() {
        return this.getClassLoader();
    }
    
    void setLoaded(final boolean loaded) {
        this.loaded = loaded;
    }
}
