// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.api.extension;

import kotlin.LazyKt;
import kotlin.jvm.functions.Function0;
import io.fairyproject.mc.data.MCMetadata;
import io.fairyproject.data.MetaStorage;
import kotlin.jvm.internal.Intrinsics;
import io.fairyproject.mc.MCPlayer;
import org.bukkit.entity.Player;
import kotlin.jvm.JvmName;
import io.fairyproject.mc.registry.player.MCPlayerRegistry;
import org.jetbrains.annotations.NotNull;
import kotlin.Lazy;
import kotlin.Metadata;

@Metadata(mv = { 1, 9, 0 }, k = 2, xi = 48, d1 = { "\u0000$\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u001f\u0010\u0005\u001a\u0006*\u00020\u00000\u00008GX\u0087\u0084\u0002¢\u0006\f\n\u0004\b\u0001\u0010\u0002\u001a\u0004\b\u0003\u0010\u0004\"\u0015\u0010\b\u001a\u00020\u0007*\u00020\u00068G¢\u0006\u0006\u001a\u0004\b\b\u0010\t\"\u0015\u0010\r\u001a\u00020\n*\u00020\u00068G¢\u0006\u0006\u001a\u0004\b\u000b\u0010\f\"\u0015\u0010\u0011\u001a\u00020\u000e*\u00020\u00068G¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010" }, d2 = { "Lio/fairyproject/mc/registry/player/MCPlayerRegistry;", "mcPlayerRegistry$delegate", "Lkotlin/Lazy;", "getMcPlayerRegistry", "()Lio/fairyproject/mc/registry/player/MCPlayerRegistry;", "mcPlayerRegistry", "Lorg/bukkit/entity/Player;", "", "isNPC", "(Lorg/bukkit/entity/Player;)Z", "Lio/fairyproject/mc/MCPlayer;", "getMcPlayer", "(Lorg/bukkit/entity/Player;)Lio/fairyproject/mc/MCPlayer;", "mcPlayer", "Lio/fairyproject/data/MetaStorage;", "getMetadata", "(Lorg/bukkit/entity/Player;)Lio/fairyproject/data/MetaStorage;", "metadata" })
public final class PlayerExtensionKt
{
    @NotNull
    private static final Lazy mcPlayerRegistry$delegate;
    
    @JvmName(name = "getMcPlayerRegistry")
    public static final MCPlayerRegistry getMcPlayerRegistry() {
        return (MCPlayerRegistry)PlayerExtensionKt.mcPlayerRegistry$delegate.getValue();
    }
    
    @NotNull
    @JvmName(name = "getMcPlayer")
    public static final MCPlayer getMcPlayer(@NotNull final Player player) {
        Intrinsics.checkNotNullParameter((Object)player, "");
        final MCPlayer playerByPlatformPlayer = getMcPlayerRegistry().findPlayerByPlatformPlayer((Object)player);
        Intrinsics.checkNotNullExpressionValue((Object)playerByPlatformPlayer, "");
        return playerByPlatformPlayer;
    }
    
    @NotNull
    @JvmName(name = "getMetadata")
    public static final MetaStorage getMetadata(@NotNull final Player player) {
        Intrinsics.checkNotNullParameter((Object)player, "");
        final MetaStorage provide = MCMetadata.provide((Object)player);
        Intrinsics.checkNotNullExpressionValue((Object)provide, "");
        return provide;
    }
    
    @JvmName(name = "isNPC")
    public static final boolean isNPC(@NotNull final Player player) {
        Intrinsics.checkNotNullParameter((Object)player, "");
        return player.hasMetadata("NPC");
    }
    
    static {
        mcPlayerRegistry$delegate = LazyKt.lazy((Function0)PlayerExtensionKt$mcPlayerRegistry.PlayerExtensionKt$mcPlayerRegistry$2.INSTANCE);
    }
}
