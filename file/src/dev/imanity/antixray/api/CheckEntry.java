// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.api;

import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import io.fairyproject.mc.util.BlockPosition;
import org.jetbrains.annotations.NotNull;
import org.bukkit.entity.Player;
import kotlin.Metadata;

@Metadata(mv = { 1, 9, 0 }, k = 1, xi = 48, d1 = { "\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\b\u0002\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007R\u001a\u0010\b\u001a\u00020\u00048\u0007X\u0087\u0004¢\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\n\u0010\u000bR\u001a\u0010\f\u001a\u00020\u00028\u0007X\u0087\u0004¢\u0006\f\n\u0004\b\f\u0010\r\u001a\u0004\b\u000e\u0010\u000f" }, d2 = { "Ldev/imanity/antixray/api/CheckEntry;", "", "Lorg/bukkit/entity/Player;", "p0", "Lio/fairyproject/mc/util/BlockPosition;", "p1", "<init>", "(Lorg/bukkit/entity/Player;Lio/fairyproject/mc/util/BlockPosition;)V", "blockPosition", "Lio/fairyproject/mc/util/BlockPosition;", "getBlockPosition", "()Lio/fairyproject/mc/util/BlockPosition;", "player", "Lorg/bukkit/entity/Player;", "getPlayer", "()Lorg/bukkit/entity/Player;" })
final class CheckEntry
{
    @NotNull
    private final Player player;
    @NotNull
    private final BlockPosition blockPosition;
    
    public CheckEntry(@NotNull final Player player, @NotNull final BlockPosition blockPosition) {
        Intrinsics.checkNotNullParameter((Object)player, "");
        Intrinsics.checkNotNullParameter((Object)blockPosition, "");
        this.player = player;
        this.blockPosition = blockPosition;
    }
    
    @NotNull
    @JvmName(name = "getPlayer")
    public final Player getPlayer() {
        return this.player;
    }
    
    @NotNull
    @JvmName(name = "getBlockPosition")
    public final BlockPosition getBlockPosition() {
        return this.blockPosition;
    }
}
