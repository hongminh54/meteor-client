// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.api.tile;

import org.jetbrains.annotations.NotNull;
import org.bukkit.entity.Player;
import kotlin.Metadata;

@Metadata(mv = { 1, 9, 0 }, k = 1, xi = 48, d1 = { "\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u001f\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u0001H&¢\u0006\u0004\b\u0006\u0010\u0007" }, d2 = { "Ldev/imanity/antixray/api/tile/TileCullingGuiProvider;", "", "Lorg/bukkit/entity/Player;", "p0", "p1", "", "open", "(Lorg/bukkit/entity/Player;Ljava/lang/Object;)V" })
public interface TileCullingGuiProvider
{
    void open(@NotNull final Player p0, @NotNull final Object p1);
}
