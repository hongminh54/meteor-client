// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.mountiplex.reflection.util;

import dev.imanity.antixray.libs.mountiplex.MountiplexUtil;
import dev.imanity.antixray.libs.mountiplex.reflection.util.asm.MPLType;
import dev.imanity.antixray.libs.objenesis.ObjenesisHelper;
import dev.imanity.antixray.libs.objenesis.instantiator.ObjectInstantiator;

public class NullInstantiator<T>
{
    private final Class<?> type;
    private ObjectInstantiator<?> instantiator;
    
    private NullInstantiator(final Class<? extends T> type) {
        this.instantiator = null;
        this.type = type;
    }
    
    public T create() {
        if (this.instantiator == null) {
            synchronized (this) {
                if (this.instantiator == null) {
                    if (this.type == null) {
                        throw new IllegalStateException("Class is unavailable");
                    }
                    this.instantiator = ObjenesisHelper.getInstantiatorOf(this.type);
                    if (this.instantiator == null) {
                        throw new IllegalStateException("Class of type " + MPLType.getName(this.type) + " could not be instantiated");
                    }
                }
            }
        }
        try {
            return (T)this.instantiator.newInstance();
        }
        catch (final Throwable t) {
            throw MountiplexUtil.uncheckedRethrow(t);
        }
    }
    
    public static <T> NullInstantiator<T> of(final Class<? extends T> clazz) {
        return new NullInstantiator<T>(clazz);
    }
}
