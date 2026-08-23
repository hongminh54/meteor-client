// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.a;

import java.util.function.Function;
import kotlin.jvm.functions.Function1;
import dev.imanity.antixray.nms.Native;
import dev.imanity.antixray.api.nms.ChunkHandle;
import java.util.concurrent.CompletableFuture;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.bukkit.World;

public final class IIlIllIlIIllIllIIlIIIllIIlIIllIIlIllIlIllIllIIlIIlIlllIllIllIlllIIIlIIlIIIlIIlllIIlllIIlIIlIIIllIIlllIllIIIlIllIIlllIlIIlllIIlllIllIIlIIlIllIlllIllIllIIlIllIIIlIIlllIIllIIllIllIIlllIlIIlIllIIllIlIIllIIllIIIlllIll implements IIlllIIIllIIllIIllIlIIIlIIIllIIIlIIlllIIIlIlllIIlIIllIlIlIlIlIIIlllIIIlllIIIlllIIIlllIIlIllIIllIlllIllIIlllIlllIlllIIlIIlIIIllIllIIIlllIIllIIlIlllIIllIIIllIIlIIllIIIlIllIIIllIIllIIIlIIllIIlllIIlllIIIllIllIIIllIIllIIllIlIllIllIIIlIIllIIl
{
    @NotNull
    private final World IIllIIlIIlIIllIIIlIIIlIIllIlIIllIIIllIIlIIllIllIIIlllIIlllIIIllIIllIIIlllIllIllIIlIlllIllIllIIIlllIIlIIlIIIlllIIIllIlllIIlllIIllIIlIlllIIlIlIlIIlIIIlllIIlllIIIlllIIllIlllIIlllIIlllIIllIIlIlllIIllIlllIIIlIIlllIIIlllIIlllIllIlllIIIlllIIlIIIlIIIll;
    
    public IIlIllIlIIllIllIIlIIIllIIlIIllIIlIllIlIllIllIIlIIlIlllIllIllIlllIIIlIIlIIIlIIlllIIlllIIlIIlIIIllIIlllIllIIIlIllIIlllIlIIlllIIlllIllIIlIIlIllIlllIllIllIIlIllIIIlIIlllIIllIIllIllIIlllIlIIlIllIIllIlIIllIIllIIIlllIll(@NotNull final World iIllIIlIIlIIllIIIlIIIlIIllIlIIllIIIllIIlIIllIllIIIlllIIlllIIIllIIllIIIlllIllIllIIlIlllIllIllIIIlllIIlIIlIIIlllIIIllIlllIIlllIIllIIlIlllIIlIlIlIIlIIIlllIIlllIIIlllIIllIlllIIlllIIlllIIllIIlIlllIIllIlllIIIlIIlllIIIlllIIlllIllIlllIIIlllIIlIIIlIIIll) {
        Intrinsics.checkNotNullParameter((Object)iIllIIlIIlIIllIIIlIIIlIIllIlIIllIIIllIIlIIllIllIIIlllIIlllIIIllIIllIIIlllIllIllIIlIlllIllIllIIIlllIIlIIlIIIlllIIIllIlllIIlllIIllIIlIlllIIlIlIlIIlIIIlllIIlllIIIlllIIllIlllIIlllIIlllIIllIIlIlllIIllIlllIIIlIIlllIIIlllIIlllIllIlllIIIlllIIlIIIlIIIll, "");
        this.IIllIIlIIlIIllIIIlIIIlIIllIlIIllIIIllIIlIIllIllIIIlllIIlllIIIllIIllIIIlllIllIllIIlIlllIllIllIIIlllIIlIIlIIIlllIIIllIlllIIlllIIllIIlIlllIIlIlIlIIlIIIlllIIlllIIIlllIIllIlllIIlllIIlllIIllIIlIlllIIllIlllIIIlIIlllIIIlllIIlllIllIlllIIIlllIIlIIIlIIIll = iIllIIlIIlIIllIIIlIIIlIIllIlIIllIIIllIIlIIllIllIIIlllIIlllIIIllIIllIIIlllIllIllIIlIlllIllIllIIIlllIIlIIlIIIlllIIIllIlllIIlllIIllIIlIlllIIlIlIlIIlIIIlllIIlllIIIlllIIllIlllIIlllIIlllIIllIIlIlllIIllIlllIIIlIIlllIIIlllIIlllIllIlllIIIlllIIlIIIlIIIll;
    }
    
    @NotNull
    @Override
    public CompletableFuture<ChunkHandle> IIllIIIllIllIIIlllIIIlllIIllIIlllIlIIIllIllIlIIlIIIlllIIlIIllIIllIlIllIIllIIIllIIlIllIIlIIlIIllIIlIlIIllIllIIlIlIIlIIIllIIlIllIllIIlllIllIIlIlllIIllIlIlllIIlllIIIlIIlIIllIlllIlIIllIIIllIlIlIIlIIllIllIlIllIIIllIIll(final int n, final int n2) {
        try {
            final CompletableFuture<Object> thenApply = (CompletableFuture<Object>)Native.INSTANCE.getChunkAtAsync(this.IIllIIlIIlIIllIIIlIIIlIIllIlIIllIIIllIIlIIllIllIIIlllIIlllIIIllIIllIIIlllIllIllIIlIlllIllIllIIIlllIIlIIlIIIlllIIIllIlllIIlllIIllIIlIlllIIlIlIlIIlIIIlllIIlllIIIlllIIllIlllIIlllIIlllIIllIIlIlllIIllIlllIIIlIIlllIIIlllIIlllIllIlllIIIlllIIlIIIlIIIll, n, n2).thenApply((Function<? super ChunkHandle, ? extends ChunkHandle>)IIlIllIlIIllIllIIlIIIllIIlIIllIIlIllIlIllIllIIlIIlIlllIllIllIlllIIIlIIlIIIlIIlllIIlllIIlIIlIIIllIIlllIllIIIlIllIIlllIlIIlllIIlllIllIIlIIlIllIlllIllIllIIlIllIIIlIIlllIIllIIllIllIIlllIlIIlIllIIllIlIIllIIllIIIlllIll::IIllIIIllIllIIIlllIIIlllIIllIIlllIlIIIllIllIlIIlIIIlllIIlIIllIIllIlIllIIllIIIllIIlIllIIlIIlIIllIIlIlIIllIllIIlIlIIlIIIllIIlIllIllIIlllIllIIlIlllIIllIlIlllIIlllIIIlIIlIIllIlllIlIIllIIIllIlIlIIlIIllIllIlIllIIIllIIll);
            Intrinsics.checkNotNullExpressionValue((Object)thenApply, "");
            return (CompletableFuture<ChunkHandle>)thenApply;
        }
        catch (final Throwable t) {
            t.printStackTrace();
            throw t;
        }
    }
    
    private static final ChunkHandle IIllIIIllIllIIIlllIIIlllIIllIIlllIlIIIllIllIlIIlIIIlllIIlIIllIIllIlIllIIllIIIllIIlIllIIlIIlIIllIIlIlIIllIllIIlIlIIlIIIllIIlIllIllIIlllIllIIlIlllIIllIlIlllIIlllIIIlIIlIIllIlllIlIIllIIIllIlIlIIlIIllIllIlIllIIIllIIll(final Function1 function1, final Object o) {
        Intrinsics.checkNotNullParameter((Object)function1, "");
        return (ChunkHandle)function1.invoke(o);
    }
}
