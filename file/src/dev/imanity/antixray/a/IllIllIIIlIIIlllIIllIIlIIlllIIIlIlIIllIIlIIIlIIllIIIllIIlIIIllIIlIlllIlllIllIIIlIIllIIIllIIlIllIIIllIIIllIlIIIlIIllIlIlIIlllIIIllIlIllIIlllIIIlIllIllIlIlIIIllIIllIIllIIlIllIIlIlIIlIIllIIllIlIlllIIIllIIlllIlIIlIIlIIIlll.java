// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.a;

import io.fairyproject.util.CC;
import java.util.Collection;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import kotlin.Metadata;

@Metadata(mv = { 1, 9, 0 }, k = 1, xi = 48, d1 = { "\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0015\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0007¢\u0006\u0004\b\u0006\u0010\u0007J\u001d\u0010\f\u001a\u00020\u000b2\u0006\u0010\b\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\t¢\u0006\u0004\b\f\u0010\r" }, d2 = { "Ldev/imanity/antixray/a/IllIllIIIlIIIlllIIllIIlIIlllIIIlIlIIllIIlIIIlIIllIIIllIIlIIIllIIlIlllIlllIllIIIlIIllIIIllIIlIllIIIllIIIllIlIIIlIIllIlIlIIlllIIIllIlIllIIlllIIIlIllIllIlIlIIIllIIllIIllIIlIllIIlIlIIlIIllIIllIlIlllIIIllIIlllIlIIlIIlIIIlll;", "", "<init>", "()V", "", "Lorg/bukkit/entity/Player;", "IllIllIlllIIlIIlllIllIIlIIlllIIlIIlllIIllIIlIlIIllIIIllIIllIIllIIIlIIlIlIllIIIllIlllIllIIIlIIIllIIIllIIlIIlIIlIIllIIllIllIIlIIIllIIlIllIIlIIlIIllIlIIIllIllIIIllIIllIIIllIllIIlllIIllIIllIllIIlllIIllIIlllIlIIlIIIllIIlIIlIIl", "()Ljava/util/List;", "p0", "", "p1", "", "IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll", "(Lorg/bukkit/entity/Player;Ljava/lang/String;)V" })
public final class IllIllIIIlIIIlllIIllIIlIIlllIIIlIlIIllIIlIIIlIIllIIIllIIlIIIllIIlIlllIlllIllIIIlIIllIIIllIIlIllIIIllIIIllIlIIIlIIllIlIlIIlllIIIllIlIllIIlllIIIlIllIllIlIlIIIllIIllIIllIIlIllIIlIlIIlIIllIIllIlIlllIIIllIIlllIlIIlIIlIIIlll
{
    @NotNull
    public static final IllIllIIIlIIIlllIIllIIlIIlllIIIlIlIIllIIlIIIlIIllIIIllIIlIIIllIIlIlllIlllIllIIIlIIllIIIllIIlIllIIIllIIIllIlIIIlIIllIlIlIIlllIIIllIlIllIIlllIIIlIllIllIlIlIIIllIIllIIllIIlIllIIlIlIIlIIllIIllIlIlllIIIllIIlllIlIIlIIlIIIlll INSTANCE;
    
    private IllIllIIIlIIIlllIIllIIlIIlllIIIlIlIIllIIlIIIlIIllIIIllIIlIIIllIIlIlllIlllIllIIIlIIllIIIllIIlIllIIIllIIIllIlIIIlIIllIlIlIIlllIIIllIlIllIIlllIIIlIllIllIlIlIIIllIIllIIllIIlIllIIlIlIIlIIllIIllIlIlllIIIllIIlllIlIIlIIlIIIlll() {
    }
    
    @NotNull
    public final List<Player> IllIllIlllIIlIIlllIllIIlIIlllIIlIIlllIIllIIlIlIIllIIIllIIllIIllIIIlIIlIlIllIIIllIlllIllIIIlIIIllIIIllIIlIIlIIlIIllIIllIllIIlIIIllIIlIllIIlIIlIIllIlIIIllIllIIIllIIllIIIllIllIIlllIIllIIllIllIIlllIIllIIlllIlIIlIIIllIIlIIlIIl() {
        final Collection onlinePlayers = Bukkit.getOnlinePlayers();
        Intrinsics.checkNotNullExpressionValue((Object)onlinePlayers, "");
        return CollectionsKt.toList((Iterable)onlinePlayers);
    }
    
    public final void IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(@NotNull final Player player, @NotNull final String s) {
        Intrinsics.checkNotNullParameter((Object)player, "");
        Intrinsics.checkNotNullParameter((Object)s, "");
        player.sendMessage(CC.translate(s));
    }
    
    static {
        INSTANCE = new IllIllIIIlIIIlllIIllIIlIIlllIIIlIlIIllIIlIIIlIIllIIIllIIlIIIllIIlIlllIlllIllIIIlIIllIIIllIIlIllIIIllIIIllIlIIIlIIllIlIlIIlllIIIllIlIllIIlllIIIlIllIllIlIlIIIllIIllIIllIIlIllIIlIlIIlIIllIIllIlIlllIIIllIIlllIlIIlIIlIIIlll();
    }
}
