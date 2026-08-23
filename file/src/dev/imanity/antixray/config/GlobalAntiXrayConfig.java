// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.config;

import kotlin.jvm.internal.DefaultConstructorMarker;
import io.fairyproject.container.PreInitialize;
import dev.imanity.antixray.nms.WrappedBlockStateCache;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import kotlin.jvm.JvmName;
import java.nio.file.Path;
import kotlin.collections.CollectionsKt;
import io.fairyproject.config.yaml.YamlConfiguration;
import kotlin.jvm.internal.Intrinsics;
import dev.imanity.antixray.AntiXrayPlugin;
import io.fairyproject.config.annotation.Comment;
import org.jetbrains.annotations.NotNull;
import kotlin.Metadata;
import io.fairyproject.container.InjectableComponent;
import dev.imanity.antixray.api.config.BasicYamlConfiguration;

@InjectableComponent
@Metadata(mv = { 1, 9, 0 }, k = 1, xi = 48, d1 = { "\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010\u000e\n\u0002\b\u000f\n\u0002\u0010\u0006\n\u0002\b\r\b\u0007\u0018\u0000 62\u00020\u0001:\u00016B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u000f\u0010\u0007\u001a\u00020\u0006H\u0007¢\u0006\u0004\b\u0007\u0010\bR\"\u0010\n\u001a\u00020\t8\u0007@\u0007X\u0087\u000e¢\u0006\u0012\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\"\u0010\u0011\u001a\u00020\u00108\u0007@\u0007X\u0087\u000e¢\u0006\u0012\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\"\u0010\u0017\u001a\u00020\u00108\u0007@\u0007X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0017\u0010\u0012\u001a\u0004\b\u0017\u0010\u0014\"\u0004\b\u0018\u0010\u0016R\"\u0010\u001a\u001a\u00020\u00198\u0007@\u0007X\u0087\u000e¢\u0006\u0012\n\u0004\b\u001a\u0010\u001b\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\"\u0010 \u001a\u00020\t8\u0007@\u0007X\u0087\u000e¢\u0006\u0012\n\u0004\b \u0010\u000b\u001a\u0004\b!\u0010\r\"\u0004\b\"\u0010\u000fR\"\u0010#\u001a\u00020\t8\u0007@\u0007X\u0087\u000e¢\u0006\u0012\n\u0004\b#\u0010\u000b\u001a\u0004\b$\u0010\r\"\u0004\b%\u0010\u000fR\"\u0010&\u001a\u00020\u00108\u0007@\u0007X\u0087\u000e¢\u0006\u0012\n\u0004\b&\u0010\u0012\u001a\u0004\b'\u0010\u0014\"\u0004\b(\u0010\u0016R\"\u0010*\u001a\u00020)8\u0007@\u0007X\u0087\u000e¢\u0006\u0012\n\u0004\b*\u0010+\u001a\u0004\b,\u0010-\"\u0004\b.\u0010/R\"\u00100\u001a\u00020\t8\u0007@\u0007X\u0087\u000e¢\u0006\u0012\n\u0004\b0\u0010\u000b\u001a\u0004\b1\u0010\r\"\u0004\b2\u0010\u000fR\"\u00103\u001a\u00020\u00108\u0007@\u0007X\u0087\u000e¢\u0006\u0012\n\u0004\b3\u0010\u0012\u001a\u0004\b4\u0010\u0014\"\u0004\b5\u0010\u0016" }, d2 = { "Ldev/imanity/antixray/config/GlobalAntiXrayConfig;", "Ldev/imanity/antixray/api/config/BasicYamlConfiguration;", "Ldev/imanity/antixray/AntiXrayPlugin;", "p0", "<init>", "(Ldev/imanity/antixray/AntiXrayPlugin;)V", "", "onPreInitialize", "()V", "", "configVersion", "I", "getConfigVersion", "()I", "setConfigVersion", "(I)V", "", "enableWorldEditTracking", "Z", "getEnableWorldEditTracking", "()Z", "setEnableWorldEditTracking", "(Z)V", "isNewFile", "setNewFile", "", "noPermissionMessage", "Ljava/lang/String;", "getNoPermissionMessage", "()Ljava/lang/String;", "setNoPermissionMessage", "(Ljava/lang/String;)V", "obfuscateThreads", "getObfuscateThreads", "setObfuscateThreads", "schedulerStripes", "getSchedulerStripes", "setSchedulerStripes", "supportThirdPerson", "getSupportThirdPerson", "setSupportThirdPerson", "", "thirdPersonMaxZoom", "D", "getThirdPersonMaxZoom", "()D", "setThirdPersonMaxZoom", "(D)V", "threads", "getThreads", "setThreads", "useOptimizedBlockLookup", "getUseOptimizedBlockLookup", "setUseOptimizedBlockLookup", "Companion" })
public final class GlobalAntiXrayConfig extends BasicYamlConfiguration
{
    @NotNull
    public static final Companion Companion;
    @Comment({ "The version of the configuration.", "Do not change this value." })
    private int configVersion;
    @Comment({ "How many threads should be allocated for raytracing.", "More threads = more players raytrace support, but more CPU usage.", "This setting will be shared across the core and other addons.", "", "WARN: this setting requires a server restart to take effect." })
    private int threads;
    @Comment({ "How many threads should be allocated for the SHARED chunk-obfuscation pool.", "This pool is shared across every world (it is no longer per-world).", "Set to 0 to auto-size from CPU cores (cores - 2, minimum 2), which is recommended.", "On a large server (300+ players) chunk-obfuscation throughput is the binding constraint", "on how fast hidden chunks are delivered, so give this room.", "", "WARN: this setting requires a server restart to take effect." })
    private int obfuscateThreads;
    @Comment({ "Should support third person view for the raytracing?", "", "NOTE: This option will affect every addons, not only the main plugin." })
    private boolean supportThirdPerson;
    @Comment({ "The max zoom distance to simulate for third person view.", "", "NOTE: This option will affect every addons, not only the main plugin." })
    private double thirdPersonMaxZoom;
    @Comment({ "The message to be sent when a player doesn't have permission to execute the command." })
    @NotNull
    private String noPermissionMessage;
    @Comment({ "Should use optimized block lookup?", "This is purely for performance debugging.", "Don't change this unless you know what you're doing.", "", "NOTE: this will only take effect after a server restart." })
    private boolean useOptimizedBlockLookup;
    @Comment({ "Should we track the blocks that are being placed by WorldEdit/FAWE?", "Enabling it will allow us to raytrace the blocks that are being placed by WorldEdit.", "But it could be very performance intensive when large areas are being edited.", "", "NOTE: this will only take effect after a server restart." })
    private boolean enableWorldEditTracking;
    @Comment({ "EXPERIMENTAL: number of scheduler stripes for per-player bookkeeping work", "(trace-block insertion after chunk obfuscation, chunk-unload cleanup, group assembly).", "0 (default) keeps everything on the single AntiXray-Scheduler-Thread.", "A value > 0 spreads different players' work across that many dedicated threads while", "keeping each player's own tasks strictly ordered. Only raise this if a profiler shows", "the AntiXray-Scheduler-Thread saturated on a large server (300+ players).", "", "WARN: this setting requires a server restart to take effect." })
    private int schedulerStripes;
    private transient boolean isNewFile;
    public static final int CONFIG_VERSION = 1;
    
    public GlobalAntiXrayConfig(@NotNull final AntiXrayPlugin antiXrayPlugin) {
        Intrinsics.checkNotNullParameter((Object)antiXrayPlugin, "");
        final Path resolve = antiXrayPlugin.getDataFolder().resolve("config.yml");
        Intrinsics.checkNotNullExpressionValue((Object)resolve, "");
        final YamlConfiguration.YamlProperties build = YamlConfiguration.YamlProperties.builder().setPrependedComments(CollectionsKt.listOf((Object[])new String[] { "This is the global configuration for AntiXray.", "You can configure the global settings here." })).build();
        Intrinsics.checkNotNullExpressionValue((Object)build, "");
        super(resolve, build);
        this.threads = 4;
        this.supportThirdPerson = true;
        this.thirdPersonMaxZoom = 4.2;
        this.noPermissionMessage = "&cYou don't have permission to do this.";
        this.useOptimizedBlockLookup = true;
        this.enableWorldEditTracking = true;
    }
    
    @JvmName(name = "getConfigVersion")
    public final int getConfigVersion() {
        return this.configVersion;
    }
    
    @JvmName(name = "setConfigVersion")
    public final void setConfigVersion(final int configVersion) {
        this.configVersion = configVersion;
    }
    
    @JvmName(name = "getThreads")
    public final int getThreads() {
        return this.threads;
    }
    
    @JvmName(name = "setThreads")
    public final void setThreads(final int threads) {
        this.threads = threads;
    }
    
    @JvmName(name = "getObfuscateThreads")
    public final int getObfuscateThreads() {
        return this.obfuscateThreads;
    }
    
    @JvmName(name = "setObfuscateThreads")
    public final void setObfuscateThreads(final int obfuscateThreads) {
        this.obfuscateThreads = obfuscateThreads;
    }
    
    @JvmName(name = "getSupportThirdPerson")
    public final boolean getSupportThirdPerson() {
        return this.supportThirdPerson;
    }
    
    @JvmName(name = "setSupportThirdPerson")
    public final void setSupportThirdPerson(final boolean supportThirdPerson) {
        this.supportThirdPerson = supportThirdPerson;
    }
    
    @JvmName(name = "getThirdPersonMaxZoom")
    public final double getThirdPersonMaxZoom() {
        return this.thirdPersonMaxZoom;
    }
    
    @JvmName(name = "setThirdPersonMaxZoom")
    public final void setThirdPersonMaxZoom(final double thirdPersonMaxZoom) {
        this.thirdPersonMaxZoom = thirdPersonMaxZoom;
    }
    
    @NotNull
    @JvmName(name = "getNoPermissionMessage")
    public final String getNoPermissionMessage() {
        return this.noPermissionMessage;
    }
    
    @JvmName(name = "setNoPermissionMessage")
    public final void setNoPermissionMessage(@NotNull final String noPermissionMessage) {
        Intrinsics.checkNotNullParameter((Object)noPermissionMessage, "");
        this.noPermissionMessage = noPermissionMessage;
    }
    
    @JvmName(name = "getUseOptimizedBlockLookup")
    public final boolean getUseOptimizedBlockLookup() {
        return this.useOptimizedBlockLookup;
    }
    
    @JvmName(name = "setUseOptimizedBlockLookup")
    public final void setUseOptimizedBlockLookup(final boolean useOptimizedBlockLookup) {
        this.useOptimizedBlockLookup = useOptimizedBlockLookup;
    }
    
    @JvmName(name = "getEnableWorldEditTracking")
    public final boolean getEnableWorldEditTracking() {
        return this.enableWorldEditTracking;
    }
    
    @JvmName(name = "setEnableWorldEditTracking")
    public final void setEnableWorldEditTracking(final boolean enableWorldEditTracking) {
        this.enableWorldEditTracking = enableWorldEditTracking;
    }
    
    @JvmName(name = "getSchedulerStripes")
    public final int getSchedulerStripes() {
        return this.schedulerStripes;
    }
    
    @JvmName(name = "setSchedulerStripes")
    public final void setSchedulerStripes(final int schedulerStripes) {
        this.schedulerStripes = schedulerStripes;
    }
    
    @JvmName(name = "isNewFile")
    public final boolean isNewFile() {
        return this.isNewFile;
    }
    
    @JvmName(name = "setNewFile")
    public final void setNewFile(final boolean isNewFile) {
        this.isNewFile = isNewFile;
    }
    
    @PreInitialize
    public final void onPreInitialize() {
        this.isNewFile = !Files.exists(this.getPath(), new LinkOption[0]);
        this.loadOrSave();
        WrappedBlockStateCache.INSTANCE.init(this.useOptimizedBlockLookup);
    }
    
    static {
        Companion = new Companion(null);
    }
    
    @Metadata(mv = { 1, 9, 0 }, k = 1, xi = 48, d1 = { "\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0014\u0010\u0005\u001a\u00020\u00048\u0006X\u0087T¢\u0006\u0006\n\u0004\b\u0005\u0010\u0006" }, d2 = { "Ldev/imanity/antixray/config/GlobalAntiXrayConfig$Companion;", "", "<init>", "()V", "", "CONFIG_VERSION", "I" })
    public static final class Companion
    {
        private Companion() {
        }
    }
}
