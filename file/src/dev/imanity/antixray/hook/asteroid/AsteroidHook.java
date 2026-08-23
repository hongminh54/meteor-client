// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.hook.asteroid;

import kotlin.LazyKt;
import kotlin.jvm.functions.Function0;
import me.serbob.asteroidapi.registries.FakePlayerRegistry;
import kotlin.jvm.internal.Intrinsics;
import org.bukkit.entity.Player;
import org.bukkit.Bukkit;
import kotlin.jvm.JvmName;
import kotlin.Lazy;
import org.jetbrains.annotations.NotNull;
import kotlin.Metadata;

@Metadata(mv = { 1, 9, 0 }, k = 1, xi = 48, d1 = { "\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0015\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0007\u0010\bJ\r\u0010\t\u001a\u00020\u0006¢\u0006\u0004\b\t\u0010\nR\u001b\u0010\u000e\u001a\u00020\u00068CX\u0083\u0084\u0002¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\n" }, d2 = { "Ldev/imanity/antixray/hook/asteroid/AsteroidHook;", "", "<init>", "()V", "Lorg/bukkit/entity/Player;", "p0", "", "isBot", "(Lorg/bukkit/entity/Player;)Z", "isHooked", "()Z", "asteroidHooked$delegate", "Lkotlin/Lazy;", "getAsteroidHooked", "asteroidHooked" })
public final class AsteroidHook
{
    @NotNull
    public static final AsteroidHook INSTANCE;
    @NotNull
    private static final Lazy asteroidHooked$delegate;
    
    private AsteroidHook() {
    }
    
    @JvmName(name = "getAsteroidHooked")
    private final boolean getAsteroidHooked() {
        return (boolean)AsteroidHook.asteroidHooked$delegate.getValue();
    }
    
    public final boolean isHooked() {
        return Bukkit.getPluginManager().isPluginEnabled("Asteroid") || Bukkit.getPluginManager().isPluginEnabled("Asteroid Spoofer");
    }
    
    public final boolean isBot(@NotNull final Player player) {
        Intrinsics.checkNotNullParameter((Object)player, "");
        return this.getAsteroidHooked() && FakePlayerRegistry.INSTANCE.isAFakePlayer(player.getUniqueId());
    }
    
    static {
        INSTANCE = new AsteroidHook();
        asteroidHooked$delegate = LazyKt.lazy((Function0)AsteroidHook$asteroidHooked.AsteroidHook$asteroidHooked$2.INSTANCE);
    }
}
