// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.a;

import io.fairyproject.container.Containers;
import io.fairyproject.log.Log;
import kotlin.collections.CollectionsKt;
import org.bukkit.entity.Player;
import java.util.Collection;
import org.bukkit.Location;
import io.fairyproject.mc.version.MCVersion;
import io.fairyproject.mc.MCServer;
import org.jetbrains.annotations.Nullable;
import java.util.function.Supplier;
import io.fairyproject.data.MetaKey;
import dev.imanity.antixray.AntiXrayKeys;
import io.fairyproject.mc.data.MCMetadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.bukkit.World;

public final class IllIlllIIlIIIlIIIlIIIlllIlllIIIlIIllIIlIIlIIIllIIlIllIIIllIlllIlIIlllIIlllIllIIIllIllIIlllIIlIlIIlIIlIllIlllIIlllIllIlIllIlIIllIIlIIIllIlIIIlIIIlIIllIIlIlIIIllIIlIllIIlIIlIIllIllIlllIIIlIIIlIIllIIlllIllIllIIllIlIl
{
    private static final boolean IIllIIlllIIIllIlllIIllIIlllIIllIIllIIllIllIIllIlIIlIIllIlIllIIIlllIIIlllIlIIlIIlllIIIlIIlllIIllIIllIlIIIlIIlllIIlIIllIllIIlIlIlllIIlIIIllIIlIIIllIllIIIlIIIlllIIllIIIlIIlIIllIllIlIIlllIIllIIllIlIIllIllIIlllIIlIIllIlIIlIllIl;
    
    public static final boolean IIlllIlIIllIlIIlIlllIlIlIIIlIIIlllIllIlIIllIlllIlIIllIllIIllIIlllIlllIIllIIllIIlIllIlIIIlIlllIIlIIIllIlllIllIllIllIIllIllIlIIlIllIIllIIIllIllIIlllIIlIllIIlIIIllIIIllIlIIIlIIlllIIIlIIllIIllIlIllIIllIIIllIlllIllIIl(@NotNull final World world) {
        Intrinsics.checkNotNullParameter((Object)world, "");
        return MCMetadata.provide((Object)world).contains((MetaKey)AntiXrayKeys.INSTANCE.getRaytraceWorldKey());
    }
    
    @NotNull
    public static final IlllIIlIIIlllIIIlIIllIlIllIIIlIIIllIlllIIIllIIlIIllIIllIlllIIllIIllIIllIIllIIlllIIlllIllIIllIlllIlIllIIlllIllIllIIlllIIllIIIlllIIIllIIIllIlllIIlllIIlIlIlIlIllIIIlllIIlIllIIllIIlllIIlIIIllIIIllIIlIIlllIlIllIllIIlIIIllIllIIlIIllIll IllIllIlIIlllIIIlllIIIllIIIlIlIIIlllIIlIIllIIllIllIIIlIIIlllIIllIIIllIIllIIlllIlllIlIllIIlllIIllIIllIlIlllIIIllIIllIlllIIlllIIIlIIllIIIlllIlIIlIIlIllIlllIIllIllIIIllIIllIIIllIIllIIllIIllIIlIIlIIllIIIlIIIllIIlllIIIllIlIlllIllIIlIIIlllIll(@NotNull final World world) {
        Intrinsics.checkNotNullParameter((Object)world, "");
        final Object computeIfAbsent = MCMetadata.provide((Object)world).computeIfAbsent((MetaKey)AntiXrayKeys.INSTANCE.getRaytraceWorldKey(), (Supplier)IllIlllIIlIIIlIIIlIIIlllIlllIIIlIIllIIlIIlIIIllIIlIllIIIllIlllIlIIlllIIlllIllIIIllIllIIlllIIlIlIIlIIlIllIlllIIlllIllIlIllIlIIllIIlIIIllIlIIIlIIIlIIllIIlIlIIIllIIlIllIIlIIlIIllIllIlllIIIlIIIlIIllIIlllIllIllIIllIlIl::IIIllIIlllIlIIIllIllIIIlllIIlllIIlllIlIlIIlIIIllIIllIlllIIlllIIlIlllIIlIIlIIllIIlIIIlllIllIIlllIIlIllIIIllIIlIIlIIlIIllIIlIIlIIIllIIllIIlIIlIIlllIIlllIIIllIlIIllIllIIlllIlIIIlllIIllIIlllIIllIlllIIllIIIllIIllIIllIIIlllIlIIlllIIllIIllIIlll);
        Intrinsics.checkNotNullExpressionValue(computeIfAbsent, "");
        return (IlllIIlIIIlllIIIlIIllIlIllIIIlIIIllIlllIIIllIIlIIllIIllIlllIIllIIllIIllIIllIIlllIIlllIllIIllIlllIlIllIIlllIllIllIIlllIIllIIIlllIIIllIIIllIlllIIlllIIlIlIlIlIllIIIlllIIlIllIIllIIlllIIlIIIllIIIllIIlIIlllIlIllIllIIlIIIllIllIIlIIllIll)computeIfAbsent;
    }
    
    @Nullable
    public static final IlllIIlIIIlllIIIlIIllIlIllIIIlIIIllIlllIIIllIIlIIllIIllIlllIIllIIllIIllIIllIIlllIIlllIllIIllIlllIlIllIIlllIllIllIIlllIIllIIIlllIIIllIIIllIlllIIlllIIlIlIlIlIllIIIlllIIlIllIIllIIlllIIlIIIllIIIllIIlIIlllIlIllIllIIlIIIllIllIIlIIllIll IllIIlIIlIIllIlIIIllIIIllIlIlllIlllIlIIllIIllIlIllIlllIIIllIIlIIIlIIllIIlIllIIlIIlIIllIIlIlIllIIlllIlIlIIllIIIlIIlllIllIIIlIIlIIIlllIlllIIllIIlIIllIIlIIllIIIllIIlllIIlllIIIlllIIIlIIllIIlIIlllIlIIIllIIIllIIllIIllIllIIlllIIll(@NotNull final World world) {
        Intrinsics.checkNotNullParameter((Object)world, "");
        return (IlllIIlIIIlllIIIlIIllIlIllIIIlIIIllIlllIIIllIIlIIllIIllIlllIIllIIllIIllIIllIIlllIIlllIllIIllIlllIlIllIIlllIllIllIIlllIIllIIIlllIIIllIIIllIlllIIlllIIlIlIlIlIllIIIlllIIlIllIIllIIlllIIlIIIllIIIllIIlIIlllIlIllIllIIlIIIllIllIIlIIllIll)MCMetadata.provide((Object)world).getOrNull((MetaKey)AntiXrayKeys.INSTANCE.getRaytraceWorldKey());
    }
    
    public static final int IllIIllIlIlIllIIllIIIllIIIllIIIllIIIlIlIlIllIlIIllIIlllIlllIIlIIIllIIlIllIIlllIIllIllIIIlIIlIlIlllIIIlllIIlIIIllIIIllIIIllIlIIlllIIlIlIIIlllIIlIlIIIlllIIlllIIlIllIllIIlIIIllIIllIlllIIllIIllIllIIllIIllIIlIIllIllIIllIIlIIl(@NotNull final World world) {
        Intrinsics.checkNotNullParameter((Object)world, "");
        return MCServer.current().getVersion().isHigherOrEqual(MCVersion.of(17)) ? world.getMinHeight() : 0;
    }
    
    @NotNull
    public static final Collection<Player> IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(@NotNull final World world, @NotNull final Location location, final double n) {
        Intrinsics.checkNotNullParameter((Object)world, "");
        Intrinsics.checkNotNullParameter((Object)location, "");
        Collection collection;
        if (IllIlllIIlIIIlIIIlIIIlllIlllIIIlIIllIIlIIlIIIllIIlIllIIIllIlllIlIIlllIIlllIllIIIllIllIIlllIIlIlIIlIIlIllIlllIIlllIllIlIllIlIIllIIlIIIllIlIIIlIIIlIIllIIlIlIIIllIIlIllIIlIIlIIllIllIlllIIIlIIIlIIllIIlllIllIllIIllIlIl.IIllIIlllIIIllIlllIIllIIlllIIllIIllIIllIllIIllIlIIlIIllIlIllIIIlllIIIlllIlIIlIIlllIIIlIIlllIIllIIllIlIIIlIIlllIIlIIllIllIIlIlIlllIIlIIIllIIlIIIllIllIIIlIIIlllIIllIIIlIIlIIllIllIlIIlllIIllIIllIlIIllIllIIlllIIlIIllIlIIlIllIl) {
            final Collection nearbyPlayers = world.getNearbyPlayers(location, n);
            Intrinsics.checkNotNull((Object)nearbyPlayers);
            collection = nearbyPlayers;
        }
        else {
            final Collection nearbyEntities = world.getNearbyEntities(location, n, n, n);
            Intrinsics.checkNotNullExpressionValue((Object)nearbyEntities, "");
            collection = CollectionsKt.filterIsInstance((Iterable)nearbyEntities, (Class)Player.class);
        }
        return collection;
    }
    
    private static final IlllIIlIIIlllIIIlIIllIlIllIIIlIIIllIlllIIIllIIlIIllIIllIlllIIllIIllIIllIIllIIlllIIlllIllIIllIlllIlIllIIlllIllIllIIlllIIllIIIlllIIIllIIIllIlllIIlllIIlIlIlIlIllIIIlllIIlIllIIllIIlllIIlIIIllIIIllIIlIIlllIlIllIllIIlIIIllIllIIlIIllIll IIIllIIlllIlIIIllIllIIIlllIIlllIIlllIlIlIIlIIIllIIllIlllIIlllIIlIlllIIlIIlIIllIIlIIIlllIllIIlllIIlIllIIIllIIlIIlIIlIIllIIlIIlIIIllIIllIIlIIlIIlllIIlllIIIllIlIIllIllIIlllIlIIIlllIIllIIlllIIllIlllIIllIIIllIIllIIllIIIlllIlIIlllIIllIIllIIlll(final World world) {
        Intrinsics.checkNotNullParameter((Object)world, "");
        Log.warn("Creating RaytraceWorld for " + world.getName() + " without initialization.", new Object[0]);
        return ((IIllIIIllIllIIIlllIIIlllIIllIIlllIlIIIllIllIlIIlIIIlllIIlIIllIIllIlIllIIllIIIllIIlIllIIlIIlIIllIIlIlIIllIllIIlIlIIlIIIllIIlIllIllIIlllIllIIlIlllIIllIlIlllIIlllIIIlIIlIIllIlllIlIIllIIIllIlIlIIlIIllIllIlIllIIIllIIll)Containers.get((Class)IIllIIIllIllIIIlllIIIlllIIllIIlllIlIIIllIllIlIIlIIIlllIIlIIllIIllIlIllIIllIIIllIIlIllIIlIIlIIllIIlIlIIllIllIIlIlIIlIIIllIIlIllIllIIlllIllIIlIlllIIllIlIlllIIlllIIIlIIlIIllIlllIlIIllIIIllIlIlIIlIIllIllIlIllIIIllIIll.class)).IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(world);
    }
    
    static {
        boolean iIllIIlllIIIllIlllIIllIIlllIIllIIllIIllIllIIllIlIIlIIllIlIllIIIlllIIIlllIlIIlIIlllIIIlIIlllIIllIIllIlIIIlIIlllIIlIIllIllIIlIlIlllIIlIIIllIIlIIIllIllIIIlIIIlllIIllIIIlIIlIIllIllIlIIlllIIllIIllIlIIllIllIIlllIIlIIllIlIIlIllIl;
        try {
            World.class.getDeclaredMethod("getNearbyPlayers", Location.class, Double.TYPE);
            iIllIIlllIIIllIlllIIllIIlllIIllIIllIIllIllIIllIlIIlIIllIlIllIIIlllIIIlllIlIIlIIlllIIIlIIlllIIllIIllIlIIIlIIlllIIlIIllIllIIlIlIlllIIlIIIllIIlIIIllIllIIIlIIIlllIIllIIIlIIlIIllIllIlIIlllIIllIIllIlIIllIllIIlllIIlIIllIlIIlIllIl = true;
        }
        catch (final NoSuchMethodException ex) {
            iIllIIlllIIIllIlllIIllIIlllIIllIIllIIllIllIIllIlIIlIIllIlIllIIIlllIIIlllIlIIlIIlllIIIlIIlllIIllIIllIlIIIlIIlllIIlIIllIllIIlIlIlllIIlIIIllIIlIIIllIllIIIlIIIlllIIllIIIlIIlIIllIllIlIIlllIIllIIllIlIIllIllIIlllIIlIIllIlIIlIllIl = false;
        }
        IIllIIlllIIIllIlllIIllIIlllIIllIIllIIllIllIIllIlIIlIIllIlIllIIIlllIIIlllIlIIlIIlllIIIlIIlllIIllIIllIlIIIlIIlllIIlIIllIllIIlIlIlllIIlIIIllIIlIIIllIllIIIlIIIlllIIllIIIlIIlIIllIllIlIIlllIIllIIllIlIIllIllIIlllIIlIIllIlIIlIllIl = iIllIIlllIIIllIlllIIllIIlllIIllIIllIIllIllIIllIlIIlIIllIlIllIIIlllIIIlllIlIIlIIlllIIIlIIlllIIllIIllIlIIIlIIlllIIlIIllIllIIlIlIlllIIlIIIllIIlIIIllIllIIIlIIIlllIIllIIIlIIlIIllIllIlIIlllIIllIIllIlIIllIllIIlllIIlIIllIlIIlIllIl;
    }
}
