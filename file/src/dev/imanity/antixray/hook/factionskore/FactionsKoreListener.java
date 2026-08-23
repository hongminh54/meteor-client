// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.hook.factionskore;

import org.bukkit.Location;
import java.util.Iterator;
import java.util.Set;
import dev.imanity.antixray.api.extension.BlockPositionExtensionKt;
import com.golfing8.kore.object.Tool;
import dev.imanity.antixray.a.IlllIIlIIIlllIIIlIIllIlIllIIIlIIIllIlllIIIllIIlIIllIIllIlllIIllIIllIIllIIllIIlllIIlllIllIIllIlllIlIllIIlllIllIllIIlllIIllIIIlllIIIllIIIllIlllIIlllIIlIlIlIlIllIIIlllIIlIllIIllIIlllIIlIIIllIIIllIIlIIlllIlIllIllIIlIIIllIllIIlIIllIll;
import org.bukkit.event.EventPriority;
import org.bukkit.event.EventHandler;
import org.bukkit.World;
import dev.imanity.antixray.a.IllIlllIIlIIIlIIIlIIIlllIlllIIIlIIllIIlIIlIIIllIIlIllIIIllIlllIlIIlllIIlllIllIIIllIllIIlllIIlIlIIlIIlIllIlllIIlllIllIlIllIlIIllIIlIIIllIlIIIlIIIlIIllIIlIlIIIllIIlIllIIlIIlIIllIllIlllIIIlIIIlIIllIIlllIllIllIIllIlIl;
import com.golfing8.kore.event.TrenchToolBreakEvent;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import dev.imanity.antixray.a.IIllIllIIllIlIIlIlIIlllIllIIllIIllIlIIllIIIllIIIllIIllIIIlIllIIlIIlllIllIIlIllIllIIlIIlIlIIIlIllIIllIlllIllIIIllIIlIIlllIllIIlIIIlIIllIIllIIlIlIIlIllIIlllIlIIlllIIlIlIIlIIlIlllIIlIIIlIIllIllIlllIIlllIIIlIIllIIIlll;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.Metadata;
import org.bukkit.event.Listener;

@Metadata(mv = { 1, 9, 0 }, k = 1, xi = 48, d1 = { "\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0017\u0010\b\u001a\u00020\u00072\u0006\u0010\u0003\u001a\u00020\u0006H\u0007¢\u0006\u0004\b\b\u0010\tR\u0014\u0010\n\u001a\u00020\u00028\u0002X\u0083\u0004¢\u0006\u0006\n\u0004\b\n\u0010\u000b" }, d2 = { "Ldev/imanity/antixray/hook/factionskore/FactionsKoreListener;", "Lorg/bukkit/event/Listener;", "Ldev/imanity/antixray/a/IIllIllIIllIlIIlIlIIlllIllIIllIIllIlIIllIIIllIIIllIIllIIIlIllIIlIIlllIllIIlIllIllIIlIIlIlIIIlIllIIllIlllIllIIIllIIlIIlllIllIIlIIIlIIllIIllIIlIlIIlIllIIlllIlIIlllIIlIlIIlIIlIlllIIlIIIlIIllIllIlllIIlllIIIlIIllIIIlll;", "p0", "<init>", "(Ldev/imanity/antixray/a/IIllIllIIllIlIIlIlIIlllIllIIllIIllIlIIllIIIllIIIllIIllIIIlIllIIlIIlllIllIIlIllIllIIlIIlIlIIIlIllIIllIlllIllIIIllIIlIIlllIllIIlIIIlIIllIIllIIlIlIIlIllIIlllIlIIlllIIlIlIIlIIlIlllIIlIIIlIIllIllIlllIIlllIIIlIIllIIIlll;)V", "Lcom/golfing8/kore/event/TrenchToolBreakEvent;", "", "onTrenchToolBreak", "(Lcom/golfing8/kore/event/TrenchToolBreakEvent;)V", "raytraceServer", "Ldev/imanity/antixray/a/IIllIllIIllIlIIlIlIIlllIllIIllIIllIlIIllIIIllIIIllIIllIIIlIllIIlIIlllIllIIlIllIllIIlIIlIlIIIlIllIIllIlllIllIIIllIIlIIlllIllIIlIIIlIIllIIllIIlIlIIlIllIIlllIlIIlllIIlIlIIlIIlIlllIIlIIIlIIllIllIlllIIlllIIIlIIllIIIlll;" })
@SourceDebugExtension({ "SMAP\nFactionsKoreListener.kt\nKotlin\n*S Kotlin\n*F\n+ 1 FactionsKoreListener.kt\ndev/imanity/antixray/hook/factionskore/FactionsKoreListener\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,29:1\n1855#2,2:30\n*S KotlinDebug\n*F\n+ 1 FactionsKoreListener.kt\ndev/imanity/antixray/hook/factionskore/FactionsKoreListener\n*L\n21#1:30,2\n*E\n" })
public final class FactionsKoreListener implements Listener
{
    @NotNull
    private final IIllIllIIllIlIIlIlIIlllIllIIllIIllIlIIllIIIllIIIllIIllIIIlIllIIlIIlllIllIIlIllIllIIlIIlIlIIIlIllIIllIlllIllIIIllIIlIIlllIllIIlIIIlIIllIIllIIlIlIIlIllIIlllIlIIlllIIlIlIIlIIlIlllIIlIIIlIIllIllIlllIIlllIIIlIIllIIIlll raytraceServer;
    
    public FactionsKoreListener(@NotNull final IIllIllIIllIlIIlIlIIlllIllIIllIIllIlIIllIIIllIIIllIIllIIIlIllIIlIIlllIllIIlIllIllIIlIIlIlIIIlIllIIllIlllIllIIIllIIlIIlllIllIIlIIIlIIllIIllIIlIlIIlIllIIlllIlIIlllIIlIlIIlIIlIlllIIlIIIlIIllIllIlllIIlllIIIlIIllIIIlll raytraceServer) {
        Intrinsics.checkNotNullParameter((Object)raytraceServer, "");
        this.raytraceServer = raytraceServer;
    }
    
    @EventHandler(priority = EventPriority.MONITOR)
    public final void onTrenchToolBreak(@NotNull final TrenchToolBreakEvent trenchToolBreakEvent) {
        Intrinsics.checkNotNullParameter((Object)trenchToolBreakEvent, "");
        final World world = trenchToolBreakEvent.getPlayer().getWorld();
        Intrinsics.checkNotNullExpressionValue((Object)world, "");
        this.raytraceServer.schedule(FactionsKoreListener::onTrenchToolBreak$lambda$1);
    }
    
    private static final void onTrenchToolBreak$lambda$1(final TrenchToolBreakEvent trenchToolBreakEvent, final IlllIIlIIIlllIIIlIIllIlIllIIIlIIIllIlllIIIllIIlIIllIIllIlllIIllIIllIIllIIllIIlllIIlllIllIIllIlllIlIllIIlllIllIllIIlllIIllIIIlllIIIllIIIllIlllIIlllIIlIlIlIlIllIIIlllIIlIllIIllIIlllIIlIIIllIIIllIIlIIlllIlIllIllIIlIIIllIllIIlIIllIll illlIIlIIIlllIIIlIIllIlIllIIIlIIIllIlllIIIllIIlIIllIIllIlllIIllIIllIIllIIllIIlllIIlllIllIIllIlllIlIllIIlllIllIllIIlllIIllIIIlllIIIllIIIllIlllIIlllIIlIlIlIlIllIIIlllIIlIllIIllIIlllIIlIIIllIIIllIIlIIlllIlIllIllIIlIIIllIllIIlIIllIll) {
        Intrinsics.checkNotNullParameter((Object)trenchToolBreakEvent, "");
        Intrinsics.checkNotNullParameter((Object)illlIIlIIIlllIIIlIIllIlIllIIIlIIIllIlllIIIllIIlIIllIIllIlllIIllIIllIIllIIllIIlllIIlllIllIIllIlllIlIllIIlllIllIllIIlllIIllIIIlllIIIllIIIllIlllIIlllIIlIlIlIlIllIIIlllIIlIllIIllIIlllIIlIIIllIIIllIIlIIlllIlIllIllIIlIIIllIllIIlIIllIll, "");
        final Set blocksBroken = trenchToolBreakEvent.getBlocksBroken();
        Intrinsics.checkNotNullExpressionValue((Object)blocksBroken, "");
        final Iterator iterator = blocksBroken.iterator();
        while (iterator.hasNext()) {
            final Location location = ((Tool.ThreadSafeBlock)iterator.next()).getLocation();
            Intrinsics.checkNotNull((Object)location);
            illlIIlIIIlllIIIlIIllIlIllIIIlIIIllIlllIIIllIIlIIllIIllIlllIIllIIllIIllIIllIIlllIIlllIllIIllIlllIlIllIIlllIllIllIIlllIIllIIIlllIIIllIIIllIlllIIlllIIlIlIlIlIllIIIlllIIlIllIIllIIlllIIlIIIllIIIllIIlIIlllIlIllIllIIlIIIllIllIIlIIllIll.IIlIlllIIllIllIlllIIlIlIllIIlIIllIIllIIIllIlIIlllIIIllIIllIIlllIlllIIllIlllIllIIIlIIllIIllIIlllIIIllIIIlIIllIllIIlIIlIIlllIlIIlIllIlllIIlllIIIlIIIllIIllIIlIIIllIlllIIlIlIlllIIlIIllIIlIllIIlIIlllIIIlllIIlllIIlllIllIIlIllIIIlIIll(BlockPositionExtensionKt.getBlockPosition(location));
        }
    }
}
