// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.config;

import kotlin.jvm.JvmName;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import io.fairyproject.config.annotation.NestedComment;
import io.fairyproject.config.annotation.ElementType;
import io.fairyproject.config.annotation.Comment;
import java.util.List;
import kotlin.Metadata;
import io.fairyproject.config.annotation.ConfigurationElement;

@ConfigurationElement
@Metadata(mv = { 1, 9, 0 }, k = 1, xi = 48, d1 = { "\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\b\b\u0007\u0018\u00002\u00020\u0001B\u0017\u0012\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002¢\u0006\u0004\b\u0005\u0010\u0006R(\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00030\u00028\u0007@\u0007X\u0087\u000e¢\u0006\u0012\n\u0004\b\u0007\u0010\b\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\u0006" }, d2 = { "Ldev/imanity/antixray/config/FakeConfig;", "", "", "Ldev/imanity/antixray/config/MinableFeature;", "p0", "<init>", "(Ljava/util/List;)V", "ores", "Ljava/util/List;", "getOres", "()Ljava/util/List;", "setOres" })
public final class FakeConfig
{
    @Comment({ "The fake ores to be generated within the world.", "'block' is the block shown to players, 'replace' is the real block it is generated within.", "Set 'block' to AIR to carve illusory caves/holes that mislead cave-finder & ESP cheats." })
    @ElementType(MinableFeature.class)
    @NestedComment
    @NotNull
    private List<MinableFeature> ores;
    
    public FakeConfig(@NotNull final List<MinableFeature> ores) {
        Intrinsics.checkNotNullParameter((Object)ores, "");
        this.ores = ores;
    }
    
    @NotNull
    @JvmName(name = "getOres")
    public final List<MinableFeature> getOres() {
        return this.ores;
    }
    
    @JvmName(name = "setOres")
    public final void setOres(@NotNull final List<MinableFeature> ores) {
        Intrinsics.checkNotNullParameter((Object)ores, "");
        this.ores = ores;
    }
    
    public FakeConfig() {
        this(null, 1, null);
    }
}
