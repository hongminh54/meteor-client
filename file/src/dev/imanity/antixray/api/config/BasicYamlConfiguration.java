// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.api.config;

import io.fairyproject.log.Log;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import java.nio.file.Path;
import kotlin.Metadata;
import io.fairyproject.config.yaml.YamlConfiguration;

@Metadata(mv = { 1, 9, 0 }, k = 1, xi = 48, d1 = { "\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\b\u0016\u0018\u00002\u00020\u0001B\u0011\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\u0004\u0010\bJ\r\u0010\n\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bR\u001a\u0010\f\u001a\u00020\u00028\u0007X\u0087\u0004¢\u0006\f\n\u0004\b\f\u0010\r\u001a\u0004\b\u000e\u0010\u000f" }, d2 = { "Ldev/imanity/antixray/api/config/BasicYamlConfiguration;", "Lio/fairyproject/config/yaml/YamlConfiguration;", "Ljava/nio/file/Path;", "p0", "<init>", "(Ljava/nio/file/Path;)V", "Lio/fairyproject/config/yaml/YamlConfiguration$YamlProperties;", "p1", "(Ljava/nio/file/Path;Lio/fairyproject/config/yaml/YamlConfiguration$YamlProperties;)V", "", "loadOrSave", "()V", "path", "Ljava/nio/file/Path;", "getPath", "()Ljava/nio/file/Path;" })
public class BasicYamlConfiguration extends YamlConfiguration
{
    @NotNull
    private final Path path;
    
    public BasicYamlConfiguration(@NotNull final Path path, @NotNull final YamlConfiguration.YamlProperties yamlProperties) {
        Intrinsics.checkNotNullParameter((Object)path, "");
        Intrinsics.checkNotNullParameter((Object)yamlProperties, "");
        super(path, yamlProperties);
        this.path = path;
    }
    
    @NotNull
    @JvmName(name = "getPath")
    public final Path getPath() {
        return this.path;
    }
    
    public BasicYamlConfiguration(@NotNull final Path path) {
        Intrinsics.checkNotNullParameter((Object)path, "");
        final YamlConfiguration.YamlProperties default1 = YamlConfiguration.YamlProperties.DEFAULT;
        Intrinsics.checkNotNullExpressionValue((Object)default1, "");
        this(path, default1);
    }
    
    public final void loadOrSave() {
        try {
            this.loadAndSave();
        }
        catch (final Throwable t) {
            Log.error("Failed to load or save config at " + this.path, t, new Object[0]);
        }
    }
}
