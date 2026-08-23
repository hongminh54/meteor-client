// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.api.config;

import java.util.function.Function;
import kotlin.jvm.functions.Function1;
import java.util.NoSuchElementException;
import io.fairyproject.log.Log;
import org.bukkit.World;
import kotlin.text.StringsKt;
import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.Pair;
import kotlin.TuplesKt;
import io.fairyproject.config.yaml.YamlConfiguration;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import io.fairyproject.container.PreInitialize;
import dev.imanity.antixray.api.config.migration.V0Migration;
import dev.imanity.antixray.api.config.migration.Migration;
import kotlin.comparisons.ComparisonsKt;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Collection;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmName;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import kotlin.jvm.internal.Intrinsics;
import io.fairyproject.plugin.Plugin;
import java.util.List;
import java.util.Map;
import java.nio.file.Path;
import org.jetbrains.annotations.NotNull;
import dev.imanity.antixray.api.config.migration.MigrationRegistry;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.Metadata;

@Metadata(mv = { 1, 9, 0 }, k = 1, xi = 48, d1 = { "\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0015\n\u0002\u0010!\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\b\u0002\b'\u0018\u0000*\b\b\u0000\u0010\u0002*\u00020\u00012\u00020\u0003B\u0017\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\b\u0010\tJ\u0015\u0010\f\u001a\u00020\u000b2\u0006\u0010\u0005\u001a\u00020\n¢\u0006\u0004\b\f\u0010\rJ\u001f\u0010\u0010\u001a\u00028\u00002\u0006\u0010\u0005\u001a\u00020\u000e2\u0006\u0010\u0007\u001a\u00020\u000fH'¢\u0006\u0004\b\u0010\u0010\u0011J\u0017\u0010\u0013\u001a\u00028\u00002\u0006\u0010\u0005\u001a\u00020\u0012H'¢\u0006\u0004\b\u0013\u0010\u0014J\u0017\u0010\u0016\u001a\u00028\u00002\u0006\u0010\u0005\u001a\u00020\u0015H\u0007¢\u0006\u0004\b\u0016\u0010\u0017J\u0017\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u0005\u001a\u00028\u0000H\u0007¢\u0006\u0004\b\u0019\u0010\u001aJ\u0015\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00120\u001bH'¢\u0006\u0004\b\u001c\u0010\u001dJ\u0017\u0010\u001e\u001a\u00028\u00002\u0006\u0010\u0005\u001a\u00020\u0015H\u0007¢\u0006\u0004\b\u001e\u0010\u0017J\r\u0010\u001f\u001a\u00020\u000b¢\u0006\u0004\b\u001f\u0010 J\u000f\u0010!\u001a\u00020\u000bH\u0007¢\u0006\u0004\b!\u0010 J\r\u0010\"\u001a\u00020\u000b¢\u0006\u0004\b\"\u0010 R\u0017\u0010$\u001a\b\u0012\u0004\u0012\u00028\u00000\u001b8G¢\u0006\u0006\u001a\u0004\b#\u0010\u001dR\u001e\u0010%\u001a\u0006*\u00020\u000e0\u000e8\u0007X\u0087\u0004¢\u0006\f\n\u0004\b%\u0010&\u001a\u0004\b'\u0010(R\u0017\u0010*\u001a\b\u0012\u0004\u0012\u00028\u00000\u001b8G¢\u0006\u0006\u001a\u0004\b)\u0010\u001dR\u001e\u0010+\u001a\u0006*\u00020\u000e0\u000e8\u0007X\u0087\u0004¢\u0006\f\n\u0004\b+\u0010&\u001a\u0004\b,\u0010(R\u0014\u0010-\u001a\u00020\u00068\u0002X\u0083\u0004¢\u0006\u0006\n\u0004\b-\u0010.R\u0017\u00100\u001a\b\u0012\u0004\u0012\u00028\u00000\u001b8G¢\u0006\u0006\u001a\u0004\b/\u0010\u001dR\u001a\u00102\u001a\b\u0012\u0004\u0012\u00020\n018\u0002X\u0083\u0004¢\u0006\u0006\n\u0004\b2\u00103R \u00105\u001a\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00028\u0000048\u0002X\u0083\u0004¢\u0006\u0006\n\u0004\b5\u00106" }, d2 = { "Ldev/imanity/antixray/api/config/AbstractConfigService;", "Ldev/imanity/antixray/api/config/WorldFilteredConfig;", "T", "", "Lio/fairyproject/plugin/Plugin;", "p0", "Ldev/imanity/antixray/api/config/migration/MigrationRegistry;", "p1", "<init>", "(Lio/fairyproject/plugin/Plugin;Ldev/imanity/antixray/api/config/migration/MigrationRegistry;)V", "Ljava/lang/Runnable;", "", "addReloadCallback", "(Ljava/lang/Runnable;)V", "Ljava/nio/file/Path;", "Lio/fairyproject/config/yaml/YamlConfiguration$YamlProperties;", "createCustomConfig", "(Ljava/nio/file/Path;Lio/fairyproject/config/yaml/YamlConfiguration$YamlProperties;)Ldev/imanity/antixray/api/config/WorldFilteredConfig;", "", "createDefaultConfig", "(Ljava/lang/String;)Ldev/imanity/antixray/api/config/WorldFilteredConfig;", "Lorg/bukkit/World;", "getConfig", "(Lorg/bukkit/World;)Ldev/imanity/antixray/api/config/WorldFilteredConfig;", "Ldev/imanity/antixray/api/config/ConfigContextType;", "getConfigContextType", "(Ldev/imanity/antixray/api/config/WorldFilteredConfig;)Ldev/imanity/antixray/api/config/ConfigContextType;", "", "getDefaultNames", "()Ljava/util/List;", "getOrCreateCustomConfig", "load", "()V", "onPreInitialize", "reload", "getAllConfigs", "allConfigs", "configDirectory", "Ljava/nio/file/Path;", "getConfigDirectory", "()Ljava/nio/file/Path;", "getDefaultConfigs", "defaultConfigs", "directory", "getDirectory", "migrationRegistry", "Ldev/imanity/antixray/api/config/migration/MigrationRegistry;", "getOrderedConfigs", "orderedConfigs", "", "reloadCallbacks", "Ljava/util/List;", "", "worldConfigs", "Ljava/util/Map;" })
@SourceDebugExtension({ "SMAP\nAbstractConfigService.kt\nKotlin\n*S Kotlin\n*F\n+ 1 AbstractConfigService.kt\ndev/imanity/antixray/api/config/AbstractConfigService\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,165:1\n766#2:166\n857#2,2:167\n1045#2:169\n1855#2,2:170\n223#2,2:172\n*S KotlinDebug\n*F\n+ 1 AbstractConfigService.kt\ndev/imanity/antixray/api/config/AbstractConfigService\n*L\n23#1:166\n23#1:167,2\n25#1:169\n88#1:170,2\n121#1:172,2\n*E\n" })
public abstract class AbstractConfigService<T extends WorldFilteredConfig>
{
    @NotNull
    private final MigrationRegistry migrationRegistry;
    private final Path directory;
    private final Path configDirectory;
    @NotNull
    private final Map<String, T> worldConfigs;
    @NotNull
    private final List<Runnable> reloadCallbacks;
    
    public AbstractConfigService(@NotNull final Plugin plugin, @NotNull final MigrationRegistry migrationRegistry) {
        Intrinsics.checkNotNullParameter((Object)plugin, "");
        Intrinsics.checkNotNullParameter((Object)migrationRegistry, "");
        this.migrationRegistry = migrationRegistry;
        this.directory = plugin.getDataFolder();
        this.configDirectory = this.directory.resolve("config");
        this.worldConfigs = new LinkedHashMap<String, T>();
        this.reloadCallbacks = new ArrayList<Runnable>();
    }
    
    @JvmName(name = "getDirectory")
    public final Path getDirectory() {
        return this.directory;
    }
    
    @JvmName(name = "getConfigDirectory")
    public final Path getConfigDirectory() {
        return this.configDirectory;
    }
    
    @NotNull
    @JvmName(name = "getAllConfigs")
    public final List<T> getAllConfigs() {
        return CollectionsKt.toList((Iterable)this.worldConfigs.values());
    }
    
    @NotNull
    @JvmName(name = "getDefaultConfigs")
    public final List<T> getDefaultConfigs() {
        final Iterable iterable = this.worldConfigs.values();
        final Collection collection = new ArrayList();
        for (final Object next : iterable) {
            if (((WorldFilteredConfig)next).getType() == ConfigType.DEFAULT) {
                collection.add(next);
            }
        }
        return CollectionsKt.toList((Iterable)collection);
    }
    
    @NotNull
    @JvmName(name = "getOrderedConfigs")
    public final List<T> getOrderedConfigs() {
        return CollectionsKt.toList((Iterable)CollectionsKt.sortedWith((Iterable)this.worldConfigs.values(), (Comparator)new Comparator() {
            @Override
            public final int compare(final T t, final T t2) {
                return ComparisonsKt.compareValues((Comparable)((WorldFilteredConfig)t).getType().ordinal(), (Comparable)((WorldFilteredConfig)t2).getType().ordinal());
            }
        }));
    }
    
    @PreInitialize
    public final void onPreInitialize() {
        this.migrationRegistry.add(new V0Migration(this));
    }
    
    public final void load() {
        if (!Files.exists(this.directory, new LinkOption[0])) {
            Files.createDirectories(this.directory, (FileAttribute<?>[])new FileAttribute[0]);
        }
        if (!Files.exists(this.configDirectory, new LinkOption[0])) {
            Files.createDirectories(this.configDirectory, (FileAttribute<?>[])new FileAttribute[0]);
            final Path resolve = this.configDirectory.resolve("sample.yml");
            Intrinsics.checkNotNullExpressionValue((Object)resolve, "");
            final YamlConfiguration.YamlProperties build = YamlConfiguration.YamlProperties.builder().setPrependedComments(CollectionsKt.listOf((Object[])new String[] { "This is a sample config,", "You can copy this file and paste it into the custom directory,", "Rename it and change the world filters to your needs.", " " })).build();
            Intrinsics.checkNotNullExpressionValue((Object)build, "");
            final WorldFilteredConfig customConfig = this.createCustomConfig(resolve, build);
            customConfig.getWorldFilters().add("sample");
            final Map<String, T> worldConfigs = this.worldConfigs;
            final Pair to = TuplesKt.to((Object)"sample", (Object)customConfig);
            worldConfigs.put((String)to.getFirst(), (T)to.getSecond());
            customConfig.loadAndSave();
            for (final String s : this.getDefaultNames()) {
                final WorldFilteredConfig defaultConfig = this.createDefaultConfig(s);
                defaultConfig.loadOrSave();
                final Map<String, T> worldConfigs2 = this.worldConfigs;
                final Pair to2 = TuplesKt.to((Object)s, (Object)defaultConfig);
                worldConfigs2.put((String)to2.getFirst(), (T)to2.getSecond());
            }
        }
        else {
            this.reload();
        }
    }
    
    public final void reload() {
        final Set set = new LinkedHashSet();
        for (final Path path : Files.list(this.configDirectory)) {
            final String string = path.getFileName().toString();
            if (!StringsKt.endsWith$default(string, ".yml", false, 2, (Object)null)) {
                continue;
            }
            final String replace$default = StringsKt.replace$default(string, ".yml", "", false, 4, (Object)null);
            WorldFilteredConfig worldFilteredConfig;
            if ((worldFilteredConfig = this.worldConfigs.get(replace$default)) == null) {
                if (this.getDefaultNames().contains(replace$default)) {
                    worldFilteredConfig = this.createDefaultConfig(replace$default);
                }
                else {
                    Intrinsics.checkNotNull((Object)path);
                    final Path path2 = path;
                    final YamlConfiguration.YamlProperties default1 = YamlConfiguration.YamlProperties.DEFAULT;
                    Intrinsics.checkNotNullExpressionValue((Object)default1, "");
                    worldFilteredConfig = this.createCustomConfig(path2, default1);
                }
            }
            final WorldFilteredConfig worldFilteredConfig2 = worldFilteredConfig;
            worldFilteredConfig2.loadOrSave();
            this.worldConfigs.put(replace$default, (T)worldFilteredConfig2);
            set.add(replace$default);
        }
        for (final String s : this.worldConfigs.keySet()) {
            if (!set.contains(s)) {
                this.worldConfigs.remove(s);
            }
        }
        final Iterator iterator3 = this.reloadCallbacks.iterator();
        while (iterator3.hasNext()) {
            ((Runnable)iterator3.next()).run();
        }
    }
    
    @NotNull
    public final T getConfig(@NotNull final World world) {
        Intrinsics.checkNotNullParameter((Object)world, "");
        BasicYamlConfiguration basicYamlConfiguration = null;
        int i = 0;
        for (final WorldFilteredConfig worldFilteredConfig : this.worldConfigs.values()) {
            if (worldFilteredConfig.isWorldFiltered(world)) {
                switch (WhenMappings.$EnumSwitchMapping$0[worldFilteredConfig.getType().ordinal()]) {
                    case 1: {
                        return (T)worldFilteredConfig;
                    }
                    case 2: {
                        ++i;
                        if (basicYamlConfiguration == null || worldFilteredConfig.getPath().toString().compareTo(basicYamlConfiguration.getPath().toString()) < 0) {
                            basicYamlConfiguration = worldFilteredConfig;
                            continue;
                        }
                        continue;
                    }
                }
            }
        }
        if (i > 1) {
            final StringBuilder append = new StringBuilder().append("Found ").append(i).append(" DEFAULT configs matching world ").append(world.getName()).append("; using ");
            final BasicYamlConfiguration basicYamlConfiguration2 = basicYamlConfiguration;
            Intrinsics.checkNotNull((Object)basicYamlConfiguration2);
            Log.warn(append.append(basicYamlConfiguration2.getPath()).append(" (deterministic). Give each world exactly one matching config \u2014 use a CUSTOM-type config or distinct worldFilters \u2014 to remove this ambiguity.").toString(), new Object[0]);
        }
        BasicYamlConfiguration basicYamlConfiguration3;
        if ((basicYamlConfiguration3 = basicYamlConfiguration) == null) {
            for (final Object next : this.worldConfigs.values()) {
                if (((T)next).getType() == ConfigType.DEFAULT) {
                    final WorldFilteredConfig worldFilteredConfig2 = (T)next;
                    Log.error("No config found for world " + world.getName() + ", fallback to default config " + worldFilteredConfig2.getPath(), new Object[0]);
                    basicYamlConfiguration3 = worldFilteredConfig2;
                    return (T)basicYamlConfiguration3;
                }
            }
            throw new NoSuchElementException("Collection contains no element matching the predicate.");
        }
        return (T)basicYamlConfiguration3;
    }
    
    @NotNull
    public final T getOrCreateCustomConfig(@NotNull final World world) {
        Intrinsics.checkNotNullParameter((Object)world, "");
        final WorldFilteredConfig computeIfAbsent = this.worldConfigs.computeIfAbsent(world.getName(), (Function<? super String, ? extends T>)AbstractConfigService::getOrCreateCustomConfig$lambda$5);
        Intrinsics.checkNotNullExpressionValue((Object)computeIfAbsent, "");
        return (T)computeIfAbsent;
    }
    
    @NotNull
    public final ConfigContextType getConfigContextType(@NotNull final T t) {
        Intrinsics.checkNotNullParameter((Object)t, "");
        if (t.getType() != ConfigType.DEFAULT) {
            return ConfigContextType.CUSTOM;
        }
        if (t.isEnvironmentFiltered(World.Environment.NORMAL)) {
            return ConfigContextType.OVERWORLD;
        }
        if (t.isEnvironmentFiltered(World.Environment.NETHER)) {
            return ConfigContextType.NETHER;
        }
        if (t.isEnvironmentFiltered(World.Environment.THE_END)) {
            return ConfigContextType.END;
        }
        return ConfigContextType.DEFAULT;
    }
    
    public final void addReloadCallback(@NotNull final Runnable runnable) {
        Intrinsics.checkNotNullParameter((Object)runnable, "");
        this.reloadCallbacks.add(runnable);
    }
    
    @NotNull
    public abstract T createCustomConfig(@NotNull final Path p0, @NotNull final YamlConfiguration.YamlProperties p1);
    
    @NotNull
    public abstract List<String> getDefaultNames();
    
    @NotNull
    public abstract T createDefaultConfig(@NotNull final String p0);
    
    private static final WorldFilteredConfig getOrCreateCustomConfig$lambda$5(final Function1 function1, final Object o) {
        Intrinsics.checkNotNullParameter((Object)function1, "");
        return (WorldFilteredConfig)function1.invoke(o);
    }
}
