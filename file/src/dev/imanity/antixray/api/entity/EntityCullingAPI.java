// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.api.entity;

import java.util.List;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import org.bukkit.plugin.java.JavaPlugin;
import kotlin.jvm.JvmName;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;
import kotlin.Metadata;

@Metadata(mv = { 1, 9, 0 }, k = 1, xi = 48, d1 = { "\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001d\u0010\t\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\t\u0010\nJ\u0015\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00060\u000bH\u0007¢\u0006\u0004\b\f\u0010\rJ\u0015\u0010\u000e\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u000e\u0010\u000fR$\u0010\u0011\u001a\u0004\u0018\u00010\u00108\u0007@\u0007X\u0087\u000e¢\u0006\u0012\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R$\u0010\u0018\u001a\u0004\u0018\u00010\u00178\u0007@\u0007X\u0087\u000e¢\u0006\u0012\n\u0004\b\u0018\u0010\u0019\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001d" }, d2 = { "Ldev/imanity/antixray/api/entity/EntityCullingAPI;", "", "<init>", "()V", "Lorg/bukkit/plugin/java/JavaPlugin;", "p0", "Ldev/imanity/antixray/api/entity/EntityCullingStrategy;", "p1", "", "addEntityCullingStrategy", "(Lorg/bukkit/plugin/java/JavaPlugin;Ldev/imanity/antixray/api/entity/EntityCullingStrategy;)V", "", "getEntityCullingStrategies", "()Ljava/util/List;", "removeEntityCullingStrategy", "(Ldev/imanity/antixray/api/entity/EntityCullingStrategy;)V", "Ldev/imanity/antixray/api/entity/EntityCullingGuiProvider;", "guiProvider", "Ldev/imanity/antixray/api/entity/EntityCullingGuiProvider;", "getGuiProvider", "()Ldev/imanity/antixray/api/entity/EntityCullingGuiProvider;", "setGuiProvider", "(Ldev/imanity/antixray/api/entity/EntityCullingGuiProvider;)V", "Ldev/imanity/antixray/api/entity/EntityCullingApiInstance;", "instance", "Ldev/imanity/antixray/api/entity/EntityCullingApiInstance;", "getInstance", "()Ldev/imanity/antixray/api/entity/EntityCullingApiInstance;", "setInstance", "(Ldev/imanity/antixray/api/entity/EntityCullingApiInstance;)V" })
public final class EntityCullingAPI
{
    @NotNull
    public static final EntityCullingAPI INSTANCE;
    @Nullable
    private static EntityCullingApiInstance instance;
    @Nullable
    private static EntityCullingGuiProvider guiProvider;
    
    private EntityCullingAPI() {
    }
    
    @Nullable
    @JvmName(name = "getInstance")
    public final EntityCullingApiInstance getInstance() {
        return EntityCullingAPI.instance;
    }
    
    @JvmName(name = "setInstance")
    public final void setInstance(@Nullable final EntityCullingApiInstance instance) {
        EntityCullingAPI.instance = instance;
    }
    
    @Nullable
    @JvmName(name = "getGuiProvider")
    public final EntityCullingGuiProvider getGuiProvider() {
        return EntityCullingAPI.guiProvider;
    }
    
    @JvmName(name = "setGuiProvider")
    public final void setGuiProvider(@Nullable final EntityCullingGuiProvider guiProvider) {
        EntityCullingAPI.guiProvider = guiProvider;
    }
    
    public final void addEntityCullingStrategy(@NotNull final JavaPlugin javaPlugin, @NotNull final EntityCullingStrategy entityCullingStrategy) {
        Intrinsics.checkNotNullParameter((Object)javaPlugin, "");
        Intrinsics.checkNotNullParameter((Object)entityCullingStrategy, "");
        final EntityCullingApiInstance instance = EntityCullingAPI.instance;
        Unit instance2;
        if (instance != null) {
            instance.addEntityCullingStrategy(javaPlugin, entityCullingStrategy);
            instance2 = Unit.INSTANCE;
        }
        else {
            instance2 = null;
        }
        if (instance2 == null) {
            throw new IllegalStateException("EntityCullingAPI instance is not initialized (Do you have EntityCulling addon installed?)");
        }
    }
    
    public final void removeEntityCullingStrategy(@NotNull final EntityCullingStrategy entityCullingStrategy) {
        Intrinsics.checkNotNullParameter((Object)entityCullingStrategy, "");
        final EntityCullingApiInstance instance = EntityCullingAPI.instance;
        Unit instance2;
        if (instance != null) {
            instance.removeEntityCullingStrategy(entityCullingStrategy);
            instance2 = Unit.INSTANCE;
        }
        else {
            instance2 = null;
        }
        if (instance2 == null) {
            throw new IllegalStateException("EntityCullingAPI instance is not initialized (Do you have EntityCulling addon installed?)");
        }
    }
    
    @NotNull
    public final List<EntityCullingStrategy> getEntityCullingStrategies() {
        final EntityCullingApiInstance instance = EntityCullingAPI.instance;
        if (instance != null) {
            final List<EntityCullingStrategy> entityCullingStrategies = instance.getEntityCullingStrategies();
            if (entityCullingStrategies != null) {
                return entityCullingStrategies;
            }
        }
        throw new IllegalStateException("EntityCullingAPI instance is not initialized (Do you have EntityCulling addon installed?)");
    }
    
    static {
        INSTANCE = new EntityCullingAPI();
    }
}
