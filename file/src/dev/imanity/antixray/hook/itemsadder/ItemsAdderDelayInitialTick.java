// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.hook.itemsadder;

import org.bukkit.event.EventHandler;
import io.fairyproject.log.Log;
import dev.lone.itemsadder.api.Events.ItemsAdderLoadDataEvent;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import dev.imanity.antixray.api.tick.InitialTickProcessor;
import kotlin.Metadata;
import org.bukkit.event.Listener;
import dev.imanity.antixray.api.tick.DelayInitialTick;

@Metadata(mv = { 1, 9, 0 }, k = 1, xi = 48, d1 = { "\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u00012\u00020\u0002B\u000f\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u000f\u0010\b\u001a\u00020\u0007H\u0016¢\u0006\u0004\b\b\u0010\tJ\u0017\u0010\f\u001a\u00020\u000b2\u0006\u0010\u0004\u001a\u00020\nH\u0007¢\u0006\u0004\b\f\u0010\rR\u0014\u0010\u000e\u001a\u00020\u00038\u0002X\u0083\u0004¢\u0006\u0006\n\u0004\b\u000e\u0010\u000fR\u0016\u0010\u0010\u001a\u00020\u00078\u0002@\u0002X\u0083\u000e¢\u0006\u0006\n\u0004\b\u0010\u0010\u0011" }, d2 = { "Ldev/imanity/antixray/hook/itemsadder/ItemsAdderDelayInitialTick;", "Ldev/imanity/antixray/api/tick/DelayInitialTick;", "Lorg/bukkit/event/Listener;", "Ldev/imanity/antixray/api/tick/InitialTickProcessor;", "p0", "<init>", "(Ldev/imanity/antixray/api/tick/InitialTickProcessor;)V", "", "canPass", "()Z", "Ldev/lone/itemsadder/api/Events/ItemsAdderLoadDataEvent;", "", "onItemsAdderLoadData", "(Ldev/lone/itemsadder/api/Events/ItemsAdderLoadDataEvent;)V", "initialTickProcessor", "Ldev/imanity/antixray/api/tick/InitialTickProcessor;", "loaded", "Z" })
public final class ItemsAdderDelayInitialTick implements DelayInitialTick, Listener
{
    @NotNull
    private final InitialTickProcessor initialTickProcessor;
    private boolean loaded;
    
    public ItemsAdderDelayInitialTick(@NotNull final InitialTickProcessor initialTickProcessor) {
        Intrinsics.checkNotNullParameter((Object)initialTickProcessor, "");
        this.initialTickProcessor = initialTickProcessor;
    }
    
    @Override
    public boolean canPass() {
        return this.loaded;
    }
    
    @EventHandler
    public final void onItemsAdderLoadData(@NotNull final ItemsAdderLoadDataEvent itemsAdderLoadDataEvent) {
        Intrinsics.checkNotNullParameter((Object)itemsAdderLoadDataEvent, "");
        if (this.loaded) {
            return;
        }
        this.loaded = true;
        if (this.initialTickProcessor.isWaitingForInitTick()) {
            Log.info("[RaytraceAntiXray] ItemsAdder loaded, processing initial tick...", new Object[0]);
            this.initialTickProcessor.process();
        }
    }
}
