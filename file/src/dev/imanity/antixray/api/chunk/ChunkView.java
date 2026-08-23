// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.api.chunk;

import java.util.Set;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import dev.imanity.antixray.api.IRaytracePlayer;
import java.util.concurrent.ConcurrentHashMap;
import kotlin.Metadata;

@Metadata(mv = { 1, 9, 0 }, k = 1, xi = 48, d1 = { "\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0015\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0007\u0010\bJ\u0015\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00040\tH\u0007¢\u0006\u0004\b\n\u0010\u000bJ\u0015\u0010\f\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\f\u0010\bR@\u0010\u000f\u001a.\u0012\b\u0012\u0006*\u00020\u00040\u0004\u0012\b\u0012\u0006*\u00020\u000e0\u000e*\u0016\u0012\b\u0012\u0006*\u00020\u00040\u0004\u0012\b\u0012\u0006*\u00020\u000e0\u000e0\r0\r8\u0002X\u0083\u0004¢\u0006\u0006\n\u0004\b\u000f\u0010\u0010" }, d2 = { "Ldev/imanity/antixray/api/chunk/ChunkView;", "", "<init>", "()V", "Ldev/imanity/antixray/api/IRaytracePlayer;", "p0", "", "addViewer", "(Ldev/imanity/antixray/api/IRaytracePlayer;)V", "", "getViewers", "()Ljava/util/Set;", "removeViewer", "Ljava/util/concurrent/ConcurrentHashMap$KeySetView;", "", "viewers", "Ljava/util/concurrent/ConcurrentHashMap$KeySetView;" })
public final class ChunkView
{
    private final ConcurrentHashMap.KeySetView<IRaytracePlayer, Boolean> viewers;
    
    public ChunkView() {
        this.viewers = ConcurrentHashMap.newKeySet();
    }
    
    public final void addViewer(@NotNull final IRaytracePlayer e) {
        Intrinsics.checkNotNullParameter((Object)e, "");
        this.viewers.add(e);
    }
    
    public final void removeViewer(@NotNull final IRaytracePlayer o) {
        Intrinsics.checkNotNullParameter((Object)o, "");
        this.viewers.remove(o);
    }
    
    @NotNull
    public final Set<IRaytracePlayer> getViewers() {
        final ConcurrentHashMap.KeySetView<IRaytracePlayer, Boolean> viewers = this.viewers;
        Intrinsics.checkNotNullExpressionValue((Object)viewers, "");
        return viewers;
    }
}
