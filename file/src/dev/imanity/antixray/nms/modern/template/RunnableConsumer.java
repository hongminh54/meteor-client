// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.nms.modern.template;

import dev.imanity.generated.net.minecraft.server.level.ChunkProviderServerHandle;
import io.fairyproject.log.Log;
import java.util.Optional;
import java.util.function.Consumer;

public final class RunnableConsumer<T> implements Consumer<T>
{
    private static final Class<?> DATAFIXER_EITHER;
    private final Runnable runnable;
    private T value;
    
    private RunnableConsumer(final Runnable runnable) {
        this.value = null;
        this.runnable = runnable;
    }
    
    public T getValue() {
        return this.value;
    }
    
    @Override
    public void accept(final T t) {
        this.value = (T)unpack(t);
        this.runnable.run();
    }
    
    public static Object unpack(final Object obj) {
        if (RunnableConsumer.DATAFIXER_EITHER != null && RunnableConsumer.DATAFIXER_EITHER.isAssignableFrom(obj.getClass())) {
            try {
                final Optional optional = (Optional)RunnableConsumer.DATAFIXER_EITHER.getMethod("left", (Class<?>[])new Class[0]).invoke(obj, new Object[0]);
                if (optional.isPresent()) {
                    return optional.get();
                }
            }
            catch (final Throwable t) {
                Log.error("Unhandled error unpacking DF either", t, new Object[0]);
            }
            return null;
        }
        return ChunkProviderServerHandle.unpackGetChunkAsyncResult(obj);
    }
    
    public static <T> RunnableConsumer<T> create(final Runnable runnable) {
        return new RunnableConsumer<T>(runnable);
    }
    
    static {
        Class<?> forName;
        try {
            forName = Class.forName("com.mojang.datafixers.util.Either");
        }
        catch (final ClassNotFoundException ex) {
            forName = null;
        }
        DATAFIXER_EITHER = forName;
    }
}
