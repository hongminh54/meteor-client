// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.api.chunk;

import org.jetbrains.annotations.NotNull;
import java.util.concurrent.CompletableFuture;
import org.jetbrains.annotations.Nullable;
import kotlin.Metadata;

@Metadata(mv = { 1, 9, 0 }, k = 1, xi = 48, d1 = { "\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\bf\u0018\u00002\u00020\u0001J\u0017\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H&¢\u0006\u0004\b\u0005\u0010\u0006J!\u0010\t\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0003\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007H'¢\u0006\u0004\b\t\u0010\nJ-\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00020\r2\u0006\u0010\u0003\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u000bH'¢\u0006\u0004\b\u000e\u0010\u000fJ\u001f\u0010\u0010\u001a\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007H'¢\u0006\u0004\b\u0010\u0010\nJ\u0019\u0010\u0011\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0003\u001a\u00020\u0002H'¢\u0006\u0004\b\u0011\u0010\u0012J\u000f\u0010\u0013\u001a\u00020\u0004H&¢\u0006\u0004\b\u0013\u0010\u0014J\u000f\u0010\u0015\u001a\u00020\u0004H&¢\u0006\u0004\b\u0015\u0010\u0014J!\u0010\u0016\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0003\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007H'¢\u0006\u0004\b\u0016\u0010\n" }, d2 = { "Ldev/imanity/antixray/api/chunk/IDirectChunkCache;", "", "Ldev/imanity/antixray/api/chunk/DirectChunk;", "p0", "", "add", "(Ldev/imanity/antixray/api/chunk/DirectChunk;)V", "", "p1", "get", "(II)Ldev/imanity/antixray/api/chunk/DirectChunk;", "", "p2", "Ljava/util/concurrent/CompletableFuture;", "getOrLoadAsync", "(IIZ)Ljava/util/concurrent/CompletableFuture;", "load", "remove", "(Ldev/imanity/antixray/api/chunk/DirectChunk;)Ldev/imanity/antixray/api/chunk/DirectChunk;", "reset", "()V", "tick", "unload" })
public interface IDirectChunkCache
{
    void tick();
    
    @Nullable
    DirectChunk get(final int p0, final int p1);
    
    @NotNull
    CompletableFuture<DirectChunk> getOrLoadAsync(final int p0, final int p1, final boolean p2);
    
    @NotNull
    DirectChunk load(final int p0, final int p1);
    
    @Nullable
    DirectChunk unload(final int p0, final int p1);
    
    void add(@NotNull final DirectChunk p0);
    
    @Nullable
    DirectChunk remove(@NotNull final DirectChunk p0);
    
    void reset();
}
