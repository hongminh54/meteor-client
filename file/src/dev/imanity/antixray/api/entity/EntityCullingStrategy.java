// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.api.entity;

import kotlin.jvm.internal.Intrinsics;
import org.bukkit.entity.Entity;
import org.jetbrains.annotations.NotNull;
import org.bukkit.entity.Player;
import kotlin.Metadata;

@Metadata(mv = { 1, 9, 0 }, k = 1, xi = 48, d1 = { "\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u001f\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004H\u0017¢\u0006\u0004\b\u0007\u0010\bJ\u001f\u0010\n\u001a\u00020\t2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004H&¢\u0006\u0004\b\n\u0010\u000b" }, d2 = { "Ldev/imanity/antixray/api/entity/EntityCullingStrategy;", "", "Lorg/bukkit/entity/Player;", "p0", "Lorg/bukkit/entity/Entity;", "p1", "Ldev/imanity/antixray/api/entity/VisibleState;", "isVisible", "(Lorg/bukkit/entity/Player;Lorg/bukkit/entity/Entity;)Ldev/imanity/antixray/api/entity/VisibleState;", "", "shouldCull", "(Lorg/bukkit/entity/Player;Lorg/bukkit/entity/Entity;)Z" })
public interface EntityCullingStrategy
{
    boolean shouldCull(@NotNull final Player p0, @NotNull final Entity p1);
    
    @NotNull
    VisibleState isVisible(@NotNull final Player p0, @NotNull final Entity p1);
    
    @Metadata(mv = { 1, 9, 0 }, k = 3, xi = 48)
    public static final class DefaultImpls
    {
        @NotNull
        public static VisibleState isVisible(@NotNull final EntityCullingStrategy entityCullingStrategy, @NotNull final Player player, @NotNull final Entity entity) {
            Intrinsics.checkNotNullParameter((Object)player, "");
            Intrinsics.checkNotNullParameter((Object)entity, "");
            return VisibleState.INHERIT;
        }
    }
}
