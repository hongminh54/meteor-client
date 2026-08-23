// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray;

import dev.imanity.antixray.nms.Native;
import io.fairyproject.bukkit.version.OBCVersionDecoder;
import dev.imanity.antixray.nms.modern.ModernNativeProvider;
import java.util.Iterator;
import dev.imanity.antixray.a.IllIlIIllIIllIIllIIIlllIIIlIllIllIlIIlIIllIlllIIIllIlllIIlIllIIlllIIlllIlllIllIIIlllIllIllIlIIllIIllIIllIllIIIllIIIlIIIllIIlIllIlIIIlIIllIllIIIllIllIIlllIIlIllIIlIIIlIIlIllIllIlIlllIIlllIIlIIlllIllIIllIlIIllIlIIllIIIll;
import org.bukkit.entity.Player;
import java.io.Serializable;
import io.fairyproject.util.CC;
import dev.imanity.antixray.api.AntiXrayApiInstance;
import dev.imanity.antixray.api.AntiXrayApiInstanceImpl;
import dev.imanity.antixray.api.AntiXrayAPI;
import io.fairyproject.libs.packetevents.protocol.world.states.WrappedBlockState;
import io.fairyproject.bukkit.FairyBukkitPlatform;
import io.fairyproject.log.Log;
import dev.imanity.antixray.bootstrap.bukkit.BukkitPlugin;
import org.bukkit.Bukkit;
import dev.imanity.antixray.a.IIlIIIlIlIIlIIlllIIlIIlIllIIllIIIllIllIIlIllIlllIIIlllIlIIlIIIlIllIlIIIlIIIllIIIllIllIIIlIlIlIIIllIIIllIllIllIIlIllIIIllIIlIIIllIIIllIllIIIlIIlllIIlIIlllIllIllIIlIIlIIlIllIllIlllIIIllIIlIIIlllIllIIIlllIIlllIIIllIIIlllIIIllIll;
import dev.imanity.antixray.nms.NativeProvider;
import io.fairyproject.FairyLaunch;
import io.fairyproject.plugin.Plugin;

@FairyLaunch
public class AntiXrayPlugin extends Plugin
{
    public static boolean MODERN_BLOCK_DATA;
    public static boolean SDK_IMPLEMENTED;
    private NativeProvider nativeProvider;
    
    public void onInitial() {
        new IIlIIIlIlIIlIIlllIIlIIlIllIIllIIIllIllIIlIllIlllIIIlllIlIIlIIIlIllIlIIIlIIIllIIIllIllIIIlIlIlIIIllIIIllIllIllIIlIllIIIllIIlIIIllIIIllIllIIIlIIlllIIlIIlllIllIllIIlIIlIIlIllIllIlllIIIllIIlIIIlllIllIIIlllIIlllIIIllIIIlllIIIllIll().init();
        if (this.detectNativeProvider()) {
            Bukkit.getPluginManager().disablePlugin((org.bukkit.plugin.Plugin)BukkitPlugin.INSTANCE);
        }
        try {
            Class.forName("org.bukkit.block.data.BlockData");
            AntiXrayPlugin.MODERN_BLOCK_DATA = true;
            Log.info("[raytraced-antixray] Modern BlockData is supported in this server version!", new Object[0]);
        }
        catch (final ClassNotFoundException ex) {
            Log.warn("[raytraced-antixray] Modern BlockData is not supported in this server version!", new Object[0]);
        }
    }
    
    public void onPreEnable() {
        final String version = FairyBukkitPlatform.PLUGIN.getDescription().getVersion();
        if (!version.equals("0.8.7b2-SNAPSHOT")) {
            Log.error("fairy-lib-plugin version mismatch, this could cause issues!", new Object[0]);
            Log.error("excepted version: 0.8.7b2-SNAPSHOT", new Object[0]);
            Log.error("current version: " + version, new Object[0]);
        }
        final String property = System.getProperty("aikars.new.flags");
        if (property == null || !property.equalsIgnoreCase("true")) {
            Log.warn("You are not using Aikar's flags, you may experience some performance & memory allocation issue without it!", new Object[0]);
            Log.warn("Unless you have a properly configured flags, we would recommend you to use it: https://docs.papermc.io/paper/aikars-flags", new Object[0]);
        }
        if (Double.parseDouble(System.getProperty("java.specification.version")) < 17.0) {
            Log.warn("You are using a Java version that is lower than 17, we recommend you to use Java 17 or higher for better performance!", new Object[0]);
        }
    }
    
    public void onPluginEnable() {
        System.out.println("\u001b[36m[Spigotunlocked.com] - COSMO\u001b[0m");
        this.nativeProvider.init();
        WrappedBlockState.getByGlobalId(0);
        AntiXrayAPI.INSTANCE.setInstance(new AntiXrayApiInstanceImpl());
        new Metrics((org.bukkit.plugin.Plugin)BukkitPlugin.INSTANCE, 21493);
        try {
            Class.forName("dev.imanity.antixray.sdk.AntiXrayAdapter");
            final Serializable v1 = Class.forName("dev.imanity.antixray.hook.MainAntiXrayAdapter");
            final Object v2 = ((Class<Object>)v1).getDeclaredConstructor((Class<?>[])new Class[0]).newInstance(new Object[0]);
            ((Class)v1).getDeclaredMethod("initialize", (Class<?>[])new Class[0]).invoke(v2, new Object[0]);
            Log.info("[raytraced-antixray] AntiXraySDK Adapter has been set to MainAntiXrayAdapter!", new Object[0]);
            AntiXrayPlugin.SDK_IMPLEMENTED = true;
        }
        catch (final ClassNotFoundException v1) {
            Log.info("[raytraced-antixray] AntiXraySDK Adapter has not been set!", new Object[0]);
        }
        catch (final Exception v1) {
            Log.error("[raytraced-antixray] Failed to set AntiXraySDK Adapter!", (Throwable)v1, new Object[0]);
        }
        Bukkit.getConsoleSender().sendMessage(CC.AQUA + "RaytraceAntiXray has been enabled with bukkit version " + Bukkit.getVersion() + " & plugin version " + BukkitPlugin.INSTANCE.getDescription().getVersion() + "," + (AntiXrayPlugin.SDK_IMPLEMENTED ? (CC.GREEN + " with AntiXraySDK support.") : (CC.YELLOW + " without AntiXraySDK support.")));
    }
    
    public void onPluginDisable() {
        final Iterator iterator = Bukkit.getOnlinePlayers().iterator();
        while (iterator.hasNext()) {
            IllIlIIllIIllIIllIIIlllIIIlIllIllIlIIlIIllIlllIIIllIlllIIlIllIIlllIIlllIlllIllIIIlllIllIllIlIIllIIllIIllIllIIIllIIIlIIIllIIlIllIlIIIlIIllIllIIIllIllIIlllIIlIllIIlIIIlIIlIllIllIlIlllIIlllIIlIIlllIllIIllIlIIllIlIIllIIIll.IllIIllIlIlIllIIllIIIllIIIllIIIllIIIlIlIlIllIlIIllIIlllIlllIIlIIIllIIlIllIIlllIIllIllIIIlIIlIlIlllIIIlllIIlIIIllIIIllIIIllIlIIlllIIlIlIIIlllIIlIlIIIlllIIlllIIlIllIllIIlIIIllIIllIlllIIllIIllIllIIllIIllIIlIIllIllIIllIIlIIl((Player)iterator.next());
        }
    }
    
    private boolean detectNativeProvider() {
        this.nativeProvider = new ModernNativeProvider();
        if (!this.nativeProvider.validate()) {
            final String decode = OBCVersionDecoder.create().decode((Class)Bukkit.getServer().getClass());
            Class<?> forName;
            try {
                forName = Class.forName("dev.imanity.antixray.nms." + decode + ".NativeProviderImpl");
            }
            catch (final ClassNotFoundException ex) {
                Log.error("Failed to find native provider for version " + decode + ".", new Object[0]);
                return true;
            }
            try {
                this.nativeProvider = (NativeProvider)forName.getDeclaredConstructor((Class<?>[])new Class[0]).newInstance(new Object[0]);
            }
            catch (final Exception ex2) {
                Log.error("Failed to create native provider for version " + decode + ".", new Object[0]);
                return true;
            }
            if (!this.nativeProvider.validate()) {
                Log.error("Failed to validate native library. (" + decode + ")", new Object[0]);
                return true;
            }
        }
        Native.INSTANCE.setNativeProvider(this.nativeProvider);
        return false;
    }
    
    static {
        AntiXrayPlugin.MODERN_BLOCK_DATA = false;
        AntiXrayPlugin.SDK_IMPLEMENTED = false;
    }
}
