// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.hook.worldedit.v7;

import com.sk89q.worldedit.util.eventbus.Subscribe;
import com.sk89q.worldedit.world.World;
import com.sk89q.worldedit.extent.Extent;
import kotlin.jvm.functions.Function4;
import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.event.extent.EditSessionEvent;
import com.sk89q.worldedit.WorldEdit;
import kotlin.jvm.internal.Intrinsics;
import kotlin.Unit;
import kotlin.jvm.functions.Function5;
import org.jetbrains.annotations.NotNull;
import kotlin.jvm.functions.Function1;
import kotlin.Metadata;

@Metadata(mv = { 1, 9, 0 }, k = 1, xi = 48, d1 = { "\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001BE\u0012\u0012\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003\u0012*\u0010\u0006\u001a&\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\t0\u0007¢\u0006\u0002\u0010\nJ\u0010\u0010\u000b\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\rH\u0007J\u0006\u0010\u000e\u001a\u00020\tR2\u0010\u0006\u001a&\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\t0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f" }, d2 = { "Ldev/imanity/antixray/hook/worldedit/v7/V7WorldEditListener;", "", "verify", "Lkotlin/Function1;", "", "", "callable", "Lkotlin/Function5;", "", "", "(Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function5;)V", "onEditSession", "event", "Lcom/sk89q/worldedit/event/extent/EditSessionEvent;", "register", "worldedit7" })
public final class V7WorldEditListener
{
    @NotNull
    private final Function1<String, Boolean> verify;
    @NotNull
    private final Function5<String, Integer, Integer, Integer, Boolean, Unit> callable;
    
    public V7WorldEditListener(@NotNull final Function1<? super String, Boolean> verify, @NotNull final Function5<? super String, ? super Integer, ? super Integer, ? super Integer, ? super Boolean, Unit> callable) {
        Intrinsics.checkNotNullParameter((Object)verify, "");
        Intrinsics.checkNotNullParameter((Object)callable, "");
        this.verify = (Function1<String, Boolean>)verify;
        this.callable = (Function5<String, Integer, Integer, Integer, Boolean, Unit>)callable;
    }
    
    public final void register() {
        WorldEdit.getInstance().getEventBus().register((Object)this);
    }
    
    @Subscribe
    public final void onEditSession(@NotNull final EditSessionEvent editSessionEvent) {
        Intrinsics.checkNotNullParameter((Object)editSessionEvent, "");
        if (editSessionEvent.getStage() == EditSession.Stage.BEFORE_HISTORY) {
            final World world = editSessionEvent.getWorld();
            final String s = (world != null) ? world.getName() : null;
            if (s == null) {
                return;
            }
            final String s2 = s;
            if (!(boolean)this.verify.invoke((Object)s2)) {
                return;
            }
            final Function4 function4 = (Function4)new V7WorldEditListener$onEditSession.V7WorldEditListener$onEditSession$1(this, s2);
            final Extent extent = editSessionEvent.getExtent();
            Intrinsics.checkNotNullExpressionValue((Object)extent, "");
            editSessionEvent.setExtent((Extent)new V7AntiXrayExtent((Function4<? super Integer, ? super Integer, ? super Integer, ? super Boolean, Unit>)function4, extent));
        }
    }
}
