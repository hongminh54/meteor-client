// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.a;

import kotlin.LazyKt;
import kotlin.jvm.functions.Function0;
import dev.imanity.antixray.nms.Native;
import java.util.Iterator;
import io.fairyproject.mc.scheduler.MCSchedulers;
import io.fairyproject.bukkit.util.CoordXZ;
import io.fairyproject.mc.MCWorld;
import kotlin.jvm.functions.Function1;
import dev.imanity.antixray.hook.asteroid.AsteroidHook;
import org.jetbrains.annotations.Nullable;
import io.fairyproject.data.MetaKey;
import dev.imanity.antixray.AntiXrayKeys;
import io.fairyproject.mc.data.MCMetadata;
import io.fairyproject.data.MetaStorage;
import io.fairyproject.mc.MCPlayer;
import org.bukkit.World;
import kotlin.jvm.internal.Intrinsics;
import org.bukkit.entity.Player;
import io.fairyproject.mc.registry.player.MCPlayerRegistry;
import org.jetbrains.annotations.NotNull;
import kotlin.Lazy;
import kotlin.jvm.internal.SourceDebugExtension;

@SourceDebugExtension({ "SMAP\nPlayerExtension.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PlayerExtension.kt\ndev/imanity/antixray/core/extension/PlayerExtensionKt\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,82:1\n1855#2,2:83\n*S KotlinDebug\n*F\n+ 1 PlayerExtension.kt\ndev/imanity/antixray/core/extension/PlayerExtensionKt\n*L\n72#1:83,2\n*E\n" })
public final class IllIlIIllIIllIIllIIIlllIIIlIllIllIlIIlIIllIlllIIIllIlllIIlIllIIlllIIlllIlllIllIIIlllIllIllIlIIllIIllIIllIllIIIllIIIlIIIllIIlIllIlIIIlIIllIllIIIllIllIIlllIIlIllIIlIIIlIIlIllIllIlIlllIIlllIIlIIlllIllIIllIlIIllIlIIllIIIll
{
    @NotNull
    private static final Lazy IIIllIIllIIIllIIIlllIIlllIIIlllIlIIIlIIlIIIllIlllIIllIIlIIlllIIlIIlIlllIIlIIllIIllIIlIIlIIlIlIIlIIllIIllIllIIIlIllIIllIllIIlIIllIIlllIIlIlllIIIlllIIllIIlIIlIllIlIIIllIIlllIllIIIlllIIllIIlIIIlllIIIllIIIlllIIIlIIlIIIlIIIllIIlIIllIIllIIl;
    @NotNull
    private static final Lazy IIllIIlIIllIIlllIIllIIIlIlllIllIllIIllIllIIlllIllIlIIllIllIIlllIIlIllIlIIIlIIllIIllIIIlIIIlllIIlIIlllIIlIIlIlllIlllIIIlllIIllIlIIIlllIIlllIIIlllIlllIIlIIllIlIIlllIIIllIIlllIIllIIlIllIIIlIlIIlIIlllIIlllIIllIlllIlIIIlllIlllIIIlllIIllIIl;
    
    public static final MCPlayerRegistry IIIllIIlIIllIlIIllIIlIIlIllIIlIllIIllIllIIlIIllIIIlIlllIIIlllIIlIIlIllIIIllIllIllIIlllIIllIIllIIIlIllIIlIIlIIlllIlIIIlllIllIIlIlIlllIlllIIIlIlllIllIIllIlIIlllIIIlllIIlIIIlllIIlIllIIIlIIllIllIIlllIIIlllIIIlIIlIIIllIlIIIlIll() {
        return (MCPlayerRegistry)IllIlIIllIIllIIllIIIlllIIIlIllIllIlIIlIIllIlllIIIllIlllIIlIllIIlllIIlllIlllIllIIIlllIllIllIlIIllIIllIIllIllIIIllIIIlIIIllIIlIllIlIIIlIIllIllIIIllIllIIlllIIlIllIIlIIIlIIlIllIllIlIlllIIlllIIlIIlllIllIIllIlIIllIlIIllIIIll.IIIllIIllIIIllIIIlllIIlllIIIlllIlIIIlIIlIIIllIlllIIllIIlIIlllIIlIIlIlllIIlIIllIIllIIlIIlIIlIlIIlIIllIIllIllIIIlIllIIllIllIIlIIllIIlllIIlIlllIIIlllIIllIIlIIlIllIlIIIllIIlllIllIIIlllIIllIIlIIIlllIIIllIIIlllIIIlIIlIIIlIIIllIIlIIllIIllIIl.getValue();
    }
    
    public static final boolean IlIIIlIIllIlllIlIIIlIIlllIIlIllIIlIIllIIIlllIIlllIIIlIIIlIIllIlllIIllIllIIIlllIlIllIIIlllIIlllIlIIllIIIllIlIIlIlllIllIIlIIllIllIlIlIIlIIIllIIllIIlIllIIllIIlIIlllIllIIIllIlllIIllIlllIlIIlIllIIlllIIIllIlllIIIllIIlIIllIlllIIll(@NotNull final Player player) {
        Intrinsics.checkNotNullParameter((Object)player, "");
        final World world = player.getWorld();
        Intrinsics.checkNotNullExpressionValue((Object)world, "");
        return IllIlllIIlIIIlIIIlIIIlllIlllIIIlIIllIIlIIlIIIllIIlIllIIIllIlllIlIIlllIIlllIllIIIllIllIIlllIIlIlIIlIIlIllIlllIIlllIllIlIllIlIIllIIlIIIllIlIIIlIIIlIIllIIlIlIIIllIIlIllIIlIIlIIllIllIlllIIIlIIIlIIllIIlllIllIllIIllIlIl.IIlllIlIIllIlIIlIlllIlIlIIIlIIIlllIllIlIIllIlllIlIIllIllIIllIIlllIlllIIllIIllIIlIllIlIIIlIlllIIlIIIllIlllIllIllIllIIllIllIlIIlIllIIllIIIllIllIIlllIIlIllIIlIIIllIIIllIlIIIlIIlllIIIlIIllIIllIlIllIIllIIIllIlllIllIIl(world) && !IllIllIlIIlllIIIlllIIIllIIIlIlIIIlllIIlIIllIIllIllIIIlIIIlllIIllIIIllIIllIIlllIlllIlIllIIlllIIllIIllIlIlllIIIllIIllIlllIIlllIIIlIIllIIIlllIlIIlIIlIllIlllIIllIllIIIllIIllIIIllIIllIIllIIllIIlIIlIIllIIIlIIIllIIlllIIIllIlIlllIllIIlIIIlllIll(player);
    }
    
    @NotNull
    public static final MCPlayer IIlIlllIIllIllIlllIIlIlIllIIlIIllIIllIIIllIlIIlllIIIllIIllIIlllIlllIIllIlllIllIIIlIIllIIllIIlllIIIllIIIlIIllIllIIlIIlIIlllIlIIlIllIlllIIlllIIIlIIIllIIllIIlIIIllIlllIIlIlIlllIIlIIllIIlIllIIlIIlllIIIlllIIlllIIlllIllIIlIllIIIlIIll(@NotNull final Player player) {
        Intrinsics.checkNotNullParameter((Object)player, "");
        final MCPlayer playerByPlatformPlayer = IIIllIIlIIllIlIIllIIlIIlIllIIlIllIIllIllIIlIIllIIIlIlllIIIlllIIlIIlIllIIIllIllIllIIlllIIllIIllIIIlIllIIlIIlIIlllIlIIIlllIllIIlIlIlllIlllIIIlIlllIllIIllIlIIlllIIIlllIIlIIIlllIIlIllIIIlIIllIllIIlllIIIlllIIIlIIlIIIllIlIIIlIll().findPlayerByPlatformPlayer((Object)player);
        Intrinsics.checkNotNullExpressionValue((Object)playerByPlatformPlayer, "");
        return playerByPlatformPlayer;
    }
    
    @NotNull
    public static final MetaStorage IllIllIIlllIIIllIIlllIIlIIlllIIIllIIIlllIIllIIIlIIIlIIIllIIllIlIIIllIlIIlllIIIlllIlIIlIIIlIIlIIlllIlIIlIlllIIllIIllIIllIIIlIIIlIIllIIllIllIIlIIIlllIllIIlIIIllIIllIIlIllIIIlllIIIllIIIllIIllIIlIIIllIIlllIIlllIIIlllIIIllIllIIlIIIlllIIlIIIllIIllIll(@NotNull final Player player) {
        Intrinsics.checkNotNullParameter((Object)player, "");
        final MetaStorage provide = MCMetadata.provide((Object)player);
        Intrinsics.checkNotNullExpressionValue((Object)provide, "");
        return provide;
    }
    
    @Nullable
    public static final IlIIIlIIlIlllIIlllIIllIIIlllIIIlIllIIllIllIIIlIlllIllIlllIIlIlllIIlIIIlIllIIIlIIlIIIlllIllIlIlIlllIIllIlIlllIIlllIIIllIIllIIlIllIIllIIIlllIIllIIllIIllIIIllIIlIIlIIIllIIllIIlIIlllIIlIllIIlllIIllIllIIlllIllIIllIIIllIIIlllIIIlllIIllIll IIllIllIlIlllIIlllIIlllIlIIIllIIlIIllIIllIIlIIIlllIIIlIIlllIIlllIIIlIlIIlIIllIlIIIllIIlIIllIIlIIlIIlllIIIllIlllIIlIIlllIllIllIIlllIlllIIlllIlIIIllIIllIlIlIIIllIlIIIlIIIllIIllIIlIlllIllIllIIIlIIIlIIllIIllIlIIIlIIIlIIllIIIllIIlll(@NotNull final Player player) {
        Intrinsics.checkNotNullParameter((Object)player, "");
        return (IlIIIlIIlIlllIIlllIIllIIIlllIIIlIllIIllIllIIIlIlllIllIlllIIlIlllIIlIIIlIllIIIlIIlIIIlllIllIlIlIlllIIllIlIlllIIlllIIIllIIllIIlIllIIllIIIlllIIllIIllIIllIIIllIIlIIlIIIllIIllIIlIIlllIIlIllIIlllIIllIllIIlllIllIIllIIIllIIIlllIIIlllIIllIll)IllIllIIlllIIIllIIlllIIlIIlllIIIllIIIlllIIllIIIlIIIlIIIllIIllIlIIIllIlIIlllIIIlllIlIIlIIIlIIlIIlllIlIIlIlllIIllIIllIIllIIIlIIIlIIllIIllIllIIlIIIlllIllIIlIIIllIIllIIlIllIIIlllIIIllIIIllIIllIIlIIIllIIlllIIlllIIIlllIIIllIllIIlIIIlllIIlIIIllIIllIll(player).getOrNull((MetaKey)AntiXrayKeys.INSTANCE.getPacketQueueKey());
    }
    
    @Nullable
    public static final IIIllIIlIllIIIlllIIlIIlllIIIlllIIlIlIlIIIllIlIllIIIlllIllIIlIllIllIIIllIIllIllIllIllIllIIlIllIIIllIllIIIlIIlllIIllIlllIIIlIllIIlIlIIlllIIIllIlllIllIllIIllIIllIIIllIIIllIIllIllIllIIIllIIlllIIIlIIIllIllIIllIIIllIIIlllIIIllIIllIIllIIIl IIlllIlIIllIlIIlIlllIlIlIIIlIIIlllIllIlIIllIlllIlIIllIllIIllIIlllIlllIIllIIllIIlIllIlIIIlIlllIIlIIIllIlllIllIllIllIIllIllIlIIlIllIIllIIIllIllIIlllIIlIllIIlIIIllIIIllIlIIIlIIlllIIIlIIllIIllIlIllIIllIIIllIlllIllIIl(@NotNull final Player player) {
        Intrinsics.checkNotNullParameter((Object)player, "");
        return (IIIllIIlIllIIIlllIIlIIlllIIIlllIIlIlIlIIIllIlIllIIIlllIllIIlIllIllIIIllIIllIllIllIllIllIIlIllIIIllIllIIIlIIlllIIllIlllIIIlIllIIlIlIIlllIIIllIlllIllIllIIllIIllIIIllIIIllIIllIllIllIIIllIIlllIIIlIIIllIllIIllIIIllIIIlllIIIllIIllIIllIIIl)IllIllIIlllIIIllIIlllIIlIIlllIIIllIIIlllIIllIIIlIIIlIIIllIIllIlIIIllIlIIlllIIIlllIlIIlIIIlIIlIIlllIlIIlIlllIIllIIllIIllIIIlIIIlIIllIIllIllIIlIIIlllIllIIlIIIllIIllIIlIllIIIlllIIIllIIIllIIllIIlIIIllIIlllIIlllIIIlllIIIllIllIIlIIIlllIIlIIIllIIllIll(player).getOrNull((MetaKey)AntiXrayKeys.INSTANCE.getRaytrace());
    }
    
    public static final boolean IllIllIlIIlllIIIlllIIIllIIIlIlIIIlllIIlIIllIIllIllIIIlIIIlllIIllIIIllIIllIIlllIlllIlIllIIlllIIllIIllIlIlllIIIllIIllIlllIIlllIIIlIIllIIIlllIlIIlIIlIllIlllIIllIllIIIllIIllIIIllIIllIIllIIllIIlIIlIIllIIIlIIIllIIlllIIIllIlIlllIllIIlIIIlllIll(@NotNull final Player player) {
        Intrinsics.checkNotNullParameter((Object)player, "");
        return player.hasMetadata("NPC") || player.hasMetadata("fake") || AsteroidHook.INSTANCE.isBot(player);
    }
    
    private static final Function1<Player, Integer> IIIlIIIllIIllIIllIIllIIIllIIIlIIllIIllIIllIlIIlllIIllIIIlIIlIlllIIllIIlllIIllIIllIlllIIlIIIlIIllIIllIIllIIlllIIllIlllIIlIIlIIIlIIIllIlIlllIIIllIlllIIlllIllIIIlIllIIlIIlllIllIIllIIIlIllIIlllIlIIllIllIlIIlIllIIllIIllIllIllIIlllIIlll() {
        return (Function1<Player, Integer>)IllIlIIllIIllIIllIIIlllIIIlIllIllIlIIlIIllIlllIIIllIlllIIlIllIIlllIIlllIlllIllIIIlllIllIllIlIIllIIllIIllIllIIIllIIIlIIIllIIlIllIlIIIlIIllIllIIIllIllIIlllIIlIllIIlIIIlIIlIllIllIlIlllIIlllIIlIIlllIllIIllIlIIllIlIIllIIIll.IIllIIlIIllIIlllIIllIIIlIlllIllIllIIllIllIIlllIllIlIIllIllIIlllIIlIllIlIIIlIIllIIllIIIlIIIlllIIlIIlllIIlIIlIlllIlllIIIlllIIllIlIIIlllIIlllIIIlllIlllIIlIIllIlIIlllIIIllIIlllIIllIIlIllIIIlIlIIlIIlllIIlllIIllIlllIlIIIlllIlllIIIlllIIllIIl.getValue();
    }
    
    public static final int IllIIlIIlIIllIlIIIllIIIllIlIlllIlllIlIIllIIllIlIllIlllIIIllIIlIIIlIIllIIlIllIIlIIlIIllIIlIlIllIIlllIlIlIIllIIIlIIlllIllIIIlIIlIIIlllIlllIIllIIlIIllIIlIIllIIIllIIlllIIlllIIIlllIIIlIIllIIlIIlllIlIIIllIIIllIIllIIllIllIIlllIIll(@NotNull final Player player) {
        Intrinsics.checkNotNullParameter((Object)player, "");
        return ((Number)IIIlIIIllIIllIIllIIllIIIllIIIlIIllIIllIIllIlIIlllIIllIIIlIIlIlllIIllIIlllIIllIIllIlllIIlIIIlIIllIIllIIllIIlllIIllIlllIIlIIlIIIlIIIllIlIlllIIIllIlllIIlllIllIIIlIllIIlIIlllIllIIllIIIlIllIIlllIlIIllIllIlIIlIllIIllIIllIllIllIIlllIIlll().invoke((Object)player)).intValue();
    }
    
    public static final void IllIIllIlIlIllIIllIIIllIIIllIIIllIIIlIlIlIllIlIIllIIlllIlllIIlIIIllIIlIllIIlllIIllIllIIIlIIlIlIlllIIIlllIIlIIIllIIIllIIIllIlIIlllIIlIlIIIlllIIlIlIIIlllIIlllIIlIllIllIIlIIIllIIllIlllIIllIIllIllIIllIIllIIlIIllIllIIllIIlIIl(@NotNull final Player player) {
        Intrinsics.checkNotNullParameter((Object)player, "");
        final MCWorld from = MCWorld.from((Object)player.getWorld());
        final long n = 1L;
        final double n2 = 3.0;
        int n3 = 1;
        final IIIllIIlIllIIIlllIIlIIlllIIIlllIIlIlIlIIIllIlIllIIIlllIllIIlIllIllIIIllIIllIllIllIllIllIIlIllIIIllIllIIIlIIlllIIllIlllIIIlIllIIlIlIIlllIIIllIlllIllIllIIllIIllIIIllIIIllIIllIllIllIIIllIIlllIIIlIIIllIllIIllIIIllIIIlllIIIllIIllIIllIIIl iIlllIlIIllIlIIlIlllIlIlIIIlIIIlllIllIlIIllIlllIlIIllIllIIllIIlllIlllIIllIIllIIlIllIlIIIlIlllIIlIIIllIlllIllIllIllIIllIllIlIIlIllIIllIIIllIllIIlllIIlIllIIlIIIllIIIllIlIIIlIIlllIIIlIIllIIllIlIllIIllIIIllIlllIllIIl = IIlllIlIIllIlIIlIlllIlIlIIIlIIIlllIllIlIIllIlllIlIIllIllIIllIIlllIlllIIllIIllIIlIllIlIIIlIlllIIlIIIllIlllIllIllIllIIllIllIlIIlIllIIllIIIllIllIIlllIIlIllIIlIIIllIIIllIlIIIlIIlllIIIlIIllIIllIlIllIIllIIIllIlllIllIIl(player);
        if (iIlllIlIIllIlIIlIlllIlIlIIIlIIIlllIllIlIIllIlllIlIIllIllIIllIIlllIlllIIllIIllIIlIllIlIIIlIlllIIlIIIllIlllIllIllIllIIllIllIlIIlIllIIllIIIllIllIIlllIIlIllIIlIIIllIIIllIlIIIlIIlllIIIlIIllIIllIlIllIIllIIIllIlllIllIIl != null) {
            for (final CoordXZ coordXZ : iIlllIlIIllIlIIlIlllIlIlIIIlIIIlllIllIlIIllIlllIlIIllIllIIllIIlllIlllIIllIIllIIlIllIlIIIlIlllIIlIIIllIlllIllIllIllIIllIllIlIIlIllIIllIIIllIllIIlllIIlIllIIlIIIllIIIllIlIIIlIIlllIIIlIIllIIllIlIllIIllIIIllIlllIllIIl.getLoadedChunkCoords()) {
                MCSchedulers.getChunkScheduler(from, coordXZ.x, coordXZ.z).schedule(IllIlIIllIIllIIllIIIlllIIIlIllIllIlIIlIIllIlllIIIllIlllIIlIllIIlllIIlllIlllIllIIIlllIllIllIlIIllIIllIIllIllIIIllIIIlIIIllIIlIllIlIIIlIIllIllIIIllIllIIlllIIlIllIIlIIIlIIlIllIllIlIlllIIlllIIlIIlllIllIIllIlIIllIlIIllIIIll::IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll, Math.max(1L, n * (long)Math.floor(n3 / n2)));
                ++n3;
            }
        }
    }
    
    private static final void IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(final Player player, final CoordXZ coordXZ) {
        Intrinsics.checkNotNullParameter((Object)player, "");
        Intrinsics.checkNotNullParameter((Object)coordXZ, "");
        if (!player.getWorld().isChunkLoaded(coordXZ.x, coordXZ.z)) {
            return;
        }
        Native.INSTANCE.resendChunk(player, coordXZ.x, coordXZ.z);
    }
    
    static {
        IIIllIIllIIIllIIIlllIIlllIIIlllIlIIIlIIlIIIllIlllIIllIIlIIlllIIlIIlIlllIIlIIllIIllIIlIIlIIlIlIIlIIllIIllIllIIIlIllIIllIllIIlIIllIIlllIIlIlllIIIlllIIllIIlIIlIllIlIIIllIIlllIllIIIlllIIllIIlIIIlllIIIllIIIlllIIIlIIlIIIlIIIllIIlIIllIIllIIl = LazyKt.lazy((Function0)IllIlIIllIIllIIllIIIlllIIIlIllIllIlIIlIIllIlllIIIllIlllIIlIllIIlllIIlllIlllIllIIIlllIllIllIlIIllIIllIIllIllIIIllIIIlIIIllIIlIllIlIIIlIIllIllIIIllIllIIlllIIlIllIIlIIIlIIlIllIllIlIlllIIlllIIlIIlllIllIIllIlIIllIlIIllIIIll$1.IIIlIIllIIIlIlIIlllIIlIlllIIIllIlllIlIIIllIllIlIIIlllIllIllIIIllIllIIllIIlIIllIIllIIlllIIIlllIIllIIllIIlllIIIlIIllIIlIIllIIlllIIIllIIlIllIllIllIIIllIlIIlllIIIllIIIllIIIlllIIIllIlllIlIIllIIlIlllIllIIllIIllIlllIIlIIIllIlllIlllIllIllIIl);
        IIllIIlIIllIIlllIIllIIIlIlllIllIllIIllIllIIlllIllIlIIllIllIIlllIIlIllIlIIIlIIllIIllIIIlIIIlllIIlIIlllIIlIIlIlllIlllIIIlllIIllIlIIIlllIIlllIIIlllIlllIIlIIllIlIIlllIIIllIIlllIIllIIlIllIIIlIlIIlIIlllIIlllIIllIlllIlIIIlllIlllIIIlllIIllIIl = LazyKt.lazy((Function0)IllIlIIllIIllIIllIIIlllIIIlIllIllIlIIlIIllIlllIIIllIlllIIlIllIIlllIIlllIlllIllIIIlllIllIllIlIIllIIllIIllIllIIIllIIIlIIIllIIlIllIlIIIlIIllIllIIIllIllIIlllIIlIllIIlIIIlIIlIllIllIlIlllIIlllIIlIIlllIllIIllIlIIllIlIIllIIIll$2.IIIllIIllIIlIIlIIIlIllIIllIIlllIIIllIIllIlIIllIIlIIlIIllIIIllIIIlIIlllIIllIIllIIlIIIlIIllIIIlllIIlllIIIlIIIlIIlllIIlllIllIIlllIIIlIIlIIlIIIllIllIIIlllIIIlIIlIIlIlllIllIlllIllIIlIIlllIlllIlllIllIIlIlllIIIllIlllIIllIllIIllIIIllIIIlIIlllIll);
    }
}
