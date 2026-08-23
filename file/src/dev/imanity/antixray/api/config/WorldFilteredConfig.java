// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.api.config;

import kotlin.collections.MapsKt;
import kotlin.TuplesKt;
import kotlin.collections.SetsKt;
import kotlin.Pair;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.Result$Companion;
import kotlin.ResultKt;
import kotlin.text.Regex;
import kotlin.Result;
import java.util.Iterator;
import kotlin.text.StringsKt;
import kotlin.jvm.JvmName;
import java.util.ArrayList;
import kotlin.jvm.internal.Intrinsics;
import io.fairyproject.config.yaml.YamlConfiguration;
import java.nio.file.Path;
import java.util.Set;
import org.bukkit.World;
import java.util.Map;
import io.fairyproject.config.annotation.Comment;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.Metadata;

@Metadata(mv = { 1, 9, 0 }, k = 1, xi = 48, d1 = { "\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010!\n\u0002\b\u0007\b\u0017\u0018\u0000 $2\u00020\u0001:\u0001$B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u001f\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0003\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\tH\u0002¢\u0006\u0004\b\u000b\u0010\fJ\u0015\u0010\r\u001a\u00020\n2\u0006\u0010\u0003\u001a\u00020\t¢\u0006\u0004\b\r\u0010\u000eJ\r\u0010\u000f\u001a\u00020\n¢\u0006\u0004\b\u000f\u0010\u0010J\u001f\u0010\u0011\u001a\u00020\n2\u0006\u0010\u0003\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\bH\u0002¢\u0006\u0004\b\u0011\u0010\u0012J\u0015\u0010\u0014\u001a\u00020\n2\u0006\u0010\u0003\u001a\u00020\u0013¢\u0006\u0004\b\u0014\u0010\u0015R\"\u0010\u0017\u001a\u00020\u00168\u0007@\u0007X\u0087\u000e¢\u0006\u0012\n\u0004\b\u0017\u0010\u0018\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR(\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\b0\u001d8\u0007@\u0007X\u0087\u000e¢\u0006\u0012\n\u0004\b\u001e\u0010\u001f\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#" }, d2 = { "Ldev/imanity/antixray/api/config/WorldFilteredConfig;", "Ldev/imanity/antixray/api/config/BasicYamlConfiguration;", "Ljava/nio/file/Path;", "p0", "Lio/fairyproject/config/yaml/YamlConfiguration$YamlProperties;", "p1", "<init>", "(Ljava/nio/file/Path;Lio/fairyproject/config/yaml/YamlConfiguration$YamlProperties;)V", "", "Lorg/bukkit/World$Environment;", "", "isEnvironmentAlias", "(Ljava/lang/String;Lorg/bukkit/World$Environment;)Z", "isEnvironmentFiltered", "(Lorg/bukkit/World$Environment;)Z", "isSingleNameFilter", "()Z", "isWildcardMatch", "(Ljava/lang/String;Ljava/lang/String;)Z", "Lorg/bukkit/World;", "isWorldFiltered", "(Lorg/bukkit/World;)Z", "Ldev/imanity/antixray/api/config/ConfigType;", "type", "Ldev/imanity/antixray/api/config/ConfigType;", "getType", "()Ldev/imanity/antixray/api/config/ConfigType;", "setType", "(Ldev/imanity/antixray/api/config/ConfigType;)V", "", "worldFilters", "Ljava/util/List;", "getWorldFilters", "()Ljava/util/List;", "setWorldFilters", "(Ljava/util/List;)V", "Companion" })
@SourceDebugExtension({ "SMAP\nWorldFilteredConfig.kt\nKotlin\n*S Kotlin\n*F\n+ 1 WorldFilteredConfig.kt\ndev/imanity/antixray/api/config/WorldFilteredConfig\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,101:1\n1#2:102\n*E\n" })
public class WorldFilteredConfig extends BasicYamlConfiguration
{
    @NotNull
    public static final Companion Companion;
    @Comment({ "The world filters to be used by the anti-xray.", "When any of the world filters match, the configuration will be used.", "Check documentation for syntaxes: https://docs.imanity.dev/antixray/configurations#world-filter" })
    @NotNull
    private List<String> worldFilters;
    @Comment({ "The type of the config.", "Config system will first search for the config with CUSTOM type.", "If not found, it will search for the config with DEFAULT type." })
    @NotNull
    private ConfigType type;
    @NotNull
    private static final Map<World.Environment, Set<String>> ENVIRONMENT_ALIASES;
    
    public WorldFilteredConfig(@NotNull final Path path, @NotNull final YamlConfiguration.YamlProperties yamlProperties) {
        Intrinsics.checkNotNullParameter((Object)path, "");
        Intrinsics.checkNotNullParameter((Object)yamlProperties, "");
        super(path, yamlProperties);
        this.worldFilters = new ArrayList<String>();
        this.type = ConfigType.DEFAULT;
    }
    
    @NotNull
    @JvmName(name = "getWorldFilters")
    public final List<String> getWorldFilters() {
        return this.worldFilters;
    }
    
    @JvmName(name = "setWorldFilters")
    public final void setWorldFilters(@NotNull final List<String> worldFilters) {
        Intrinsics.checkNotNullParameter((Object)worldFilters, "");
        this.worldFilters = worldFilters;
    }
    
    @NotNull
    @JvmName(name = "getType")
    public final ConfigType getType() {
        return this.type;
    }
    
    @JvmName(name = "setType")
    public final void setType(@NotNull final ConfigType type) {
        Intrinsics.checkNotNullParameter((Object)type, "");
        this.type = type;
    }
    
    public final boolean isSingleNameFilter() {
        return this.worldFilters.size() == 1 && !StringsKt.contains$default((CharSequence)this.worldFilters.get(0), (CharSequence)"*", false, 2, (Object)null) && !StringsKt.contains$default((CharSequence)this.worldFilters.get(0), (CharSequence)"~", false, 2, (Object)null) && !StringsKt.contains$default((CharSequence)this.worldFilters.get(0), (CharSequence)"^", false, 2, (Object)null) && !StringsKt.contains$default((CharSequence)this.worldFilters.get(0), (CharSequence)"$", false, 2, (Object)null);
    }
    
    public final boolean isEnvironmentFiltered(@NotNull final World.Environment environment) {
        Intrinsics.checkNotNullParameter((Object)environment, "");
        final String lowerCase = environment.name().toLowerCase();
        Intrinsics.checkNotNullExpressionValue((Object)lowerCase, "");
        final String str = lowerCase;
        for (final String s : this.worldFilters) {
            if (Intrinsics.areEqual((Object)s, (Object)("environment:" + str)) || this.isEnvironmentAlias(s, environment)) {
                return true;
            }
        }
        return false;
    }
    
    public final boolean isWorldFiltered(@NotNull final World world) {
        Intrinsics.checkNotNullParameter((Object)world, "");
        final String name = world.getName();
        Intrinsics.checkNotNullExpressionValue((Object)name, "");
        final String s = name;
        final String lowerCase = world.getEnvironment().name().toLowerCase();
        Intrinsics.checkNotNullExpressionValue((Object)lowerCase, "");
        final String str = lowerCase;
        for (final String s2 : this.worldFilters) {
            if (Intrinsics.areEqual((Object)s2, (Object)"*")) {
                return true;
            }
            if (Intrinsics.areEqual((Object)s2, (Object)s)) {
                return true;
            }
            if (Intrinsics.areEqual((Object)s2, (Object)("environment:" + str))) {
                return true;
            }
            final String s3 = s2;
            final World.Environment environment = world.getEnvironment();
            Intrinsics.checkNotNullExpressionValue((Object)environment, "");
            if (this.isEnvironmentAlias(s3, environment)) {
                return true;
            }
            if (this.isWildcardMatch(s2, s)) {
                return true;
            }
            Object o;
            try {
                final Result$Companion companion = Result.Companion;
                final WorldFilteredConfig worldFilteredConfig = this;
                o = Result.constructor-impl((Object)new Regex(s2).matches((CharSequence)s));
            }
            catch (final Throwable t) {
                final Result$Companion companion2 = Result.Companion;
                o = Result.constructor-impl(ResultKt.createFailure(t));
            }
            final Object o2 = o;
            final Boolean value = false;
            if (Result.isFailure-impl(o2) ? value : o2) {
                return true;
            }
        }
        return false;
    }
    
    private final boolean isEnvironmentAlias(final String s, final World.Environment environment) {
        final Set set = WorldFilteredConfig.ENVIRONMENT_ALIASES.get(environment);
        return set != null && set.contains(s);
    }
    
    private final boolean isWildcardMatch(final String s, final String s2) {
        final int indexOf$default = StringsKt.indexOf$default((CharSequence)s, '*', 0, false, 6, (Object)null);
        if (indexOf$default == -1) {
            return false;
        }
        if (indexOf$default == 0) {
            final String substring = s.substring(1);
            Intrinsics.checkNotNullExpressionValue((Object)substring, "");
            return StringsKt.endsWith$default(s2, substring, false, 2, (Object)null);
        }
        if (indexOf$default == s.length() - 1) {
            final String substring2 = s.substring(0, s.length() - 1);
            Intrinsics.checkNotNullExpressionValue((Object)substring2, "");
            return StringsKt.startsWith$default(s2, substring2, false, 2, (Object)null);
        }
        final String substring3 = s.substring(0, indexOf$default);
        Intrinsics.checkNotNullExpressionValue((Object)substring3, "");
        if (StringsKt.startsWith$default(s2, substring3, false, 2, (Object)null)) {
            final String substring4 = s.substring(indexOf$default + 1);
            Intrinsics.checkNotNullExpressionValue((Object)substring4, "");
            if (StringsKt.endsWith$default(s2, substring4, false, 2, (Object)null)) {
                return true;
            }
        }
        return false;
    }
    
    static {
        Companion = new Companion(null);
        ENVIRONMENT_ALIASES = MapsKt.mapOf(new Pair[] { TuplesKt.to((Object)World.Environment.NORMAL, (Object)SetsKt.setOf((Object[])new String[] { "normal", "overworld" })), TuplesKt.to((Object)World.Environment.NETHER, (Object)SetsKt.setOf((Object[])new String[] { "nether", "the_nether" })), TuplesKt.to((Object)World.Environment.THE_END, (Object)SetsKt.setOf((Object[])new String[] { "the_end", "end", "theend" })) });
    }
    
    @Metadata(mv = { 1, 9, 0 }, k = 1, xi = 48, d1 = { "\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R&\u0010\b\u001a\u0014\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00060\u00048\u0002X\u0083\u0004¢\u0006\u0006\n\u0004\b\b\u0010\t" }, d2 = { "Ldev/imanity/antixray/api/config/WorldFilteredConfig$Companion;", "", "<init>", "()V", "", "Lorg/bukkit/World$Environment;", "", "", "ENVIRONMENT_ALIASES", "Ljava/util/Map;" })
    public static final class Companion
    {
        private Companion() {
        }
    }
}
