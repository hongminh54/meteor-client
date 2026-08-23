// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.api;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import org.bukkit.entity.Player;
import io.fairyproject.mc.util.BlockPosition;
import java.util.concurrent.ConcurrentHashMap;
import org.jetbrains.annotations.NotNull;
import kotlin.Metadata;

@Metadata(mv = { 1, 9, 0 }, k = 1, xi = 48, d1 = { "\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001d\u0010\t\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\t\u0010\nJA\u0010\u0010\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000b2\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\b0\u000e¢\u0006\u0004\b\u0010\u0010\u0011J\r\u0010\u0013\u001a\u00020\u0012¢\u0006\u0004\b\u0013\u0010\u0014J\u0015\u0010\u0015\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0015\u0010\u0016R \u0010\u0019\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00180\u00178\u0002X\u0083\u0004¢\u0006\u0006\n\u0004\b\u0019\u0010\u001a" }, d2 = { "Ldev/imanity/antixray/api/RaytraceDebugger;", "", "<init>", "()V", "Lorg/bukkit/entity/Player;", "p0", "Lio/fairyproject/mc/util/BlockPosition;", "p1", "", "addCheck", "(Lorg/bukkit/entity/Player;Lio/fairyproject/mc/util/BlockPosition;)V", "", "p2", "p3", "Lkotlin/Function1;", "p4", "debug", "(Lorg/bukkit/entity/Player;IIILkotlin/jvm/functions/Function1;)V", "", "isActive", "()Z", "removeCheck", "(Lio/fairyproject/mc/util/BlockPosition;)V", "Ljava/util/concurrent/ConcurrentHashMap;", "Ldev/imanity/antixray/api/CheckEntry;", "checks", "Ljava/util/concurrent/ConcurrentHashMap;" })
public final class RaytraceDebugger
{
    @NotNull
    public static final RaytraceDebugger INSTANCE;
    @NotNull
    private static final ConcurrentHashMap<BlockPosition, CheckEntry> checks;
    
    private RaytraceDebugger() {
    }
    
    public final void addCheck(@NotNull final Player player, @NotNull final BlockPosition blockPosition) {
        Intrinsics.checkNotNullParameter((Object)player, "");
        Intrinsics.checkNotNullParameter((Object)blockPosition, "");
        RaytraceDebugger.checks.put(blockPosition, new CheckEntry(player, blockPosition));
    }
    
    public final void removeCheck(@NotNull final BlockPosition key) {
        Intrinsics.checkNotNullParameter((Object)key, "");
        RaytraceDebugger.checks.remove(key);
    }
    
    public final boolean isActive() {
        return !RaytraceDebugger.checks.isEmpty();
    }
    
    public final void debug(@NotNull final Player player, final int n, final int n2, final int n3, @NotNull final Function1<? super Player, Unit> function1) {
        Intrinsics.checkNotNullParameter((Object)player, "");
        Intrinsics.checkNotNullParameter((Object)function1, "");
        if (RaytraceDebugger.checks.isEmpty()) {
            return;
        }
        final CheckEntry checkEntry = RaytraceDebugger.checks.get(new BlockPosition(n, n2, n3));
        if (checkEntry != null && Intrinsics.areEqual((Object)checkEntry.getPlayer(), (Object)player)) {
            function1.invoke((Object)checkEntry.getPlayer());
        }
    }
    
    static {
        INSTANCE = new RaytraceDebugger();
        checks = new ConcurrentHashMap<BlockPosition, CheckEntry>();
    }
}
