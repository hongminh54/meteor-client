// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.api.config;

import org.bukkit.World;
import org.bukkit.entity.Player;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.Nullable;
import io.fairyproject.util.CC;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import kotlin.Metadata;

@Metadata(mv = { 1, 9, 0 }, k = 1, xi = 48, d1 = { "\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u000f\b\u0086\b\u0018\u0000 %*\b\b\u0000\u0010\u0002*\u00020\u00012\u00020\u0003:\u0001%B\u001f\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00028\u0000¢\u0006\u0004\b\t\u0010\nJ\u0010\u0010\u000b\u001a\u00020\u0004H\u00c7\u0003¢\u0006\u0004\b\u000b\u0010\fJ\u0010\u0010\r\u001a\u00020\u0006H\u00c7\u0003¢\u0006\u0004\b\r\u0010\u000eJ\u0010\u0010\u000f\u001a\u00028\u0000H\u00c7\u0003¢\u0006\u0004\b\u000f\u0010\u0010J4\u0010\u0011\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\b\u001a\u00028\u0000H\u00c7\u0001¢\u0006\u0004\b\u0011\u0010\u0012J\u001a\u0010\u0014\u001a\u00020\u00132\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003H\u00d6\u0003¢\u0006\u0004\b\u0014\u0010\u0015J\u0010\u0010\u0017\u001a\u00020\u0016H\u00d6\u0001¢\u0006\u0004\b\u0017\u0010\u0018J\u0010\u0010\u0019\u001a\u00020\u0006H\u00d7\u0001¢\u0006\u0004\b\u0019\u0010\u000eR\u001a\u0010\u001a\u001a\u00028\u00008\u0007X\u0087\u0004¢\u0006\f\n\u0004\b\u001a\u0010\u001b\u001a\u0004\b\u001c\u0010\u0010R\u001a\u0010\u001d\u001a\u00020\u00068\u0007X\u0087\u0004¢\u0006\f\n\u0004\b\u001d\u0010\u001e\u001a\u0004\b\u001f\u0010\u000eR\u0011\u0010!\u001a\u00020\u00068G¢\u0006\u0006\u001a\u0004\b \u0010\u000eR\u001a\u0010\"\u001a\u00020\u00048\u0007X\u0087\u0004¢\u0006\f\n\u0004\b\"\u0010#\u001a\u0004\b$\u0010\f" }, d2 = { "Ldev/imanity/antixray/api/config/ConfigContext;", "Ldev/imanity/antixray/api/config/WorldFilteredConfig;", "T", "", "Ldev/imanity/antixray/api/config/ConfigContextType;", "p0", "", "p1", "p2", "<init>", "(Ldev/imanity/antixray/api/config/ConfigContextType;Ljava/lang/String;Ldev/imanity/antixray/api/config/WorldFilteredConfig;)V", "component1", "()Ldev/imanity/antixray/api/config/ConfigContextType;", "component2", "()Ljava/lang/String;", "component3", "()Ldev/imanity/antixray/api/config/WorldFilteredConfig;", "copy", "(Ldev/imanity/antixray/api/config/ConfigContextType;Ljava/lang/String;Ldev/imanity/antixray/api/config/WorldFilteredConfig;)Ldev/imanity/antixray/api/config/ConfigContext;", "", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "toString", "config", "Ldev/imanity/antixray/api/config/WorldFilteredConfig;", "getConfig", "name", "Ljava/lang/String;", "getName", "getTitle", "title", "type", "Ldev/imanity/antixray/api/config/ConfigContextType;", "getType", "Companion" })
public final class ConfigContext<T extends WorldFilteredConfig>
{
    @NotNull
    public static final Companion Companion;
    @NotNull
    private final ConfigContextType type;
    @NotNull
    private final String name;
    @NotNull
    private final T config;
    
    public ConfigContext(@NotNull final ConfigContextType type, @NotNull final String name, @NotNull final T config) {
        Intrinsics.checkNotNullParameter((Object)type, "");
        Intrinsics.checkNotNullParameter((Object)name, "");
        Intrinsics.checkNotNullParameter((Object)config, "");
        this.type = type;
        this.name = name;
        this.config = config;
    }
    
    @NotNull
    @JvmName(name = "getType")
    public final ConfigContextType getType() {
        return this.type;
    }
    
    @NotNull
    @JvmName(name = "getName")
    public final String getName() {
        return this.name;
    }
    
    @NotNull
    @JvmName(name = "getConfig")
    public final T getConfig() {
        return this.config;
    }
    
    @NotNull
    @JvmName(name = "getTitle")
    public final String getTitle() {
        final String translate = CC.translate(switch (WhenMappings.$EnumSwitchMapping$0[this.type.ordinal()]) {
            case 1 -> "&e(Overworld)";
            case 2 -> "&c(Nether)";
            case 3 -> "&5(End)";
            case 4 -> "&6(Default)";
            case 5 -> new StringBuilder().append("&b[&1").append(this.name).append("&b]").toString();
            default -> throw new NoWhenBranchMatchedException();
        });
        Intrinsics.checkNotNullExpressionValue((Object)translate, "");
        return translate;
    }
    
    @NotNull
    public final ConfigContextType component1() {
        return this.type;
    }
    
    @NotNull
    public final String component2() {
        return this.name;
    }
    
    @NotNull
    public final T component3() {
        return this.config;
    }
    
    @NotNull
    public final ConfigContext<T> copy(@NotNull final ConfigContextType configContextType, @NotNull final String s, @NotNull final T t) {
        Intrinsics.checkNotNullParameter((Object)configContextType, "");
        Intrinsics.checkNotNullParameter((Object)s, "");
        Intrinsics.checkNotNullParameter((Object)t, "");
        return new ConfigContext<T>(configContextType, s, t);
    }
    
    @NotNull
    @Override
    public String toString() {
        return "ConfigContext(type=" + this.type + ", name=" + this.name + ", config=" + this.config + ')';
    }
    
    @Override
    public int hashCode() {
        return (this.type.hashCode() * 31 + this.name.hashCode()) * 31 + this.config.hashCode();
    }
    
    @Override
    public boolean equals(@Nullable final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ConfigContext)) {
            return false;
        }
        final ConfigContext configContext = (ConfigContext)o;
        return this.type == configContext.type && Intrinsics.areEqual((Object)this.name, (Object)configContext.name) && Intrinsics.areEqual((Object)this.config, (Object)configContext.config);
    }
    
    static {
        Companion = new Companion(null);
    }
    
    @Metadata(mv = { 1, 9, 0 }, k = 1, xi = 48, d1 = { "\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J5\u0010\u000b\u001a\b\u0012\u0004\u0012\u00028\u00010\n\"\b\b\u0001\u0010\u0005*\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00062\f\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00010\bH\u0007¢\u0006\u0004\b\u000b\u0010\f" }, d2 = { "Ldev/imanity/antixray/api/config/ConfigContext$Companion;", "", "<init>", "()V", "Ldev/imanity/antixray/api/config/WorldFilteredConfig;", "T", "Lorg/bukkit/entity/Player;", "p0", "Ldev/imanity/antixray/api/config/AbstractConfigService;", "p1", "Ldev/imanity/antixray/api/config/ConfigContext;", "from", "(Lorg/bukkit/entity/Player;Ldev/imanity/antixray/api/config/AbstractConfigService;)Ldev/imanity/antixray/api/config/ConfigContext;" })
    public static final class Companion
    {
        private Companion() {
        }
        
        @NotNull
        public final <T extends WorldFilteredConfig> ConfigContext<T> from(@NotNull final Player player, @NotNull final AbstractConfigService<T> abstractConfigService) {
            Intrinsics.checkNotNullParameter((Object)player, "");
            Intrinsics.checkNotNullParameter((Object)abstractConfigService, "");
            final World world = player.getWorld();
            Intrinsics.checkNotNullExpressionValue((Object)world, "");
            final WorldFilteredConfig config = abstractConfigService.getConfig(world);
            final ConfigContextType configContextType = abstractConfigService.getConfigContextType((T)config);
            final String name = player.getWorld().getName();
            Intrinsics.checkNotNullExpressionValue((Object)name, "");
            return new ConfigContext<T>(configContextType, name, config);
        }
    }
}
