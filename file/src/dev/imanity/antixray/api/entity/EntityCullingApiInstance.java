// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.api.entity;

import java.util.Iterator;
import java.util.Collection;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import java.util.List;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.bukkit.plugin.java.JavaPlugin;
import kotlin.Metadata;

@Metadata(mv = { 1, 9, 0 }, k = 1, xi = 48, d1 = { "\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u001f\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004H&¢\u0006\u0004\b\u0007\u0010\bJ\u0015\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00040\tH'¢\u0006\u0004\b\n\u0010\u000bJ\u001f\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0003\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\rH\u0017¢\u0006\u0004\b\u000f\u0010\u0010J\u0017\u0010\u0012\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u0011H&¢\u0006\u0004\b\u0012\u0010\u0013J\u0017\u0010\u0014\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u0004H&¢\u0006\u0004\b\u0014\u0010\u0015J\u001f\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0003\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\rH\u0016¢\u0006\u0004\b\u0017\u0010\u0018" }, d2 = { "Ldev/imanity/antixray/api/entity/EntityCullingApiInstance;", "", "Lorg/bukkit/plugin/java/JavaPlugin;", "p0", "Ldev/imanity/antixray/api/entity/EntityCullingStrategy;", "p1", "", "addEntityCullingStrategy", "(Lorg/bukkit/plugin/java/JavaPlugin;Ldev/imanity/antixray/api/entity/EntityCullingStrategy;)V", "", "getEntityCullingStrategies", "()Ljava/util/List;", "Lorg/bukkit/entity/Player;", "Lorg/bukkit/entity/Entity;", "Ldev/imanity/antixray/api/entity/VisibleState;", "isVisible", "(Lorg/bukkit/entity/Player;Lorg/bukkit/entity/Entity;)Ldev/imanity/antixray/api/entity/VisibleState;", "Lorg/bukkit/plugin/Plugin;", "removeEntityCullingStrategies", "(Lorg/bukkit/plugin/Plugin;)V", "removeEntityCullingStrategy", "(Ldev/imanity/antixray/api/entity/EntityCullingStrategy;)V", "", "shouldCull", "(Lorg/bukkit/entity/Player;Lorg/bukkit/entity/Entity;)Z" })
public interface EntityCullingApiInstance
{
    void addEntityCullingStrategy(@NotNull final JavaPlugin p0, @NotNull final EntityCullingStrategy p1);
    
    void removeEntityCullingStrategy(@NotNull final EntityCullingStrategy p0);
    
    void removeEntityCullingStrategies(@NotNull final Plugin p0);
    
    @NotNull
    List<EntityCullingStrategy> getEntityCullingStrategies();
    
    boolean shouldCull(@NotNull final Player p0, @NotNull final Entity p1);
    
    @NotNull
    VisibleState isVisible(@NotNull final Player p0, @NotNull final Entity p1);
    
    @Metadata(mv = { 1, 9, 0 }, k = 3, xi = 48)
    @SourceDebugExtension({ "SMAP\nEntityCullingAPI.kt\nKotlin\n*S Kotlin\n*F\n+ 1 EntityCullingAPI.kt\ndev/imanity/antixray/api/entity/EntityCullingApiInstance$DefaultImpls\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,64:1\n1747#2,3:65\n*S KotlinDebug\n*F\n+ 1 EntityCullingAPI.kt\ndev/imanity/antixray/api/entity/EntityCullingApiInstance$DefaultImpls\n*L\n45#1:65,3\n*E\n" })
    public static final class DefaultImpls
    {
        public static boolean shouldCull(@NotNull final EntityCullingApiInstance entityCullingApiInstance, @NotNull final Player player, @NotNull final Entity entity) {
            Intrinsics.checkNotNullParameter((Object)player, "");
            Intrinsics.checkNotNullParameter((Object)entity, "");
            final List<EntityCullingStrategy> entityCullingStrategies = entityCullingApiInstance.getEntityCullingStrategies();
            if (entityCullingStrategies.isEmpty()) {
                return true;
            }
            final Iterable iterable = entityCullingStrategies;
            boolean b;
            if (iterable instanceof Collection && ((Collection)iterable).isEmpty()) {
                b = false;
            }
            else {
                final Iterator iterator = iterable.iterator();
                while (iterator.hasNext()) {
                    if (((EntityCullingStrategy)iterator.next()).shouldCull(player, entity)) {
                        b = true;
                        return b;
                    }
                }
                b = false;
            }
            return b;
        }
        
        @NotNull
        public static VisibleState isVisible(@NotNull final EntityCullingApiInstance entityCullingApiInstance, @NotNull final Player player, @NotNull final Entity entity) {
            Intrinsics.checkNotNullParameter((Object)player, "");
            Intrinsics.checkNotNullParameter((Object)entity, "");
            final List<EntityCullingStrategy> entityCullingStrategies = entityCullingApiInstance.getEntityCullingStrategies();
            if (entityCullingStrategies.isEmpty()) {
                return VisibleState.INHERIT;
            }
            final Iterator<EntityCullingStrategy> iterator = entityCullingStrategies.iterator();
            while (iterator.hasNext()) {
                final VisibleState visible = iterator.next().isVisible(player, entity);
                if (visible != VisibleState.INHERIT) {
                    return visible;
                }
            }
            return VisibleState.INHERIT;
        }
    }
}
