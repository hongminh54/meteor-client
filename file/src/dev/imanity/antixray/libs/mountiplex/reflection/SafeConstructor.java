// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.mountiplex.reflection;

import dev.imanity.antixray.libs.mountiplex.conversion.Converter;
import java.util.logging.Level;
import dev.imanity.antixray.libs.mountiplex.MountiplexUtil;
import java.lang.reflect.Constructor;
import dev.imanity.antixray.libs.mountiplex.reflection.util.FastConstructor;

public class SafeConstructor<T>
{
    private final FastConstructor<T> constructor;
    
    public SafeConstructor(final FastConstructor<T> constructor) {
        this.constructor = constructor;
    }
    
    public SafeConstructor(final Constructor<?> constructor) {
        (this.constructor = new FastConstructor<T>()).init(constructor);
    }
    
    public SafeConstructor(final Class<T> clazz, final Class<?>... parameterTypes) {
        this.constructor = new FastConstructor<T>();
        try {
            if (clazz == null) {
                MountiplexUtil.LOGGER.log(Level.WARNING, "Failed to find constructor because type is null");
                this.constructor.initUnavailable("NULL_UNKNOWN_TYPE()");
            }
            else {
                this.constructor.init(clazz.getDeclaredConstructor(parameterTypes));
            }
        }
        catch (final SecurityException thrown) {
            MountiplexUtil.LOGGER.log(Level.WARNING, "Failed to access constructor", thrown);
        }
        catch (final NoSuchMethodException thrown2) {
            MountiplexUtil.LOGGER.log(Level.WARNING, "Failed to find constructor", thrown2);
        }
    }
    
    public boolean isValid() {
        return this.constructor.isAvailable();
    }
    
    public T newInstance(final Object... array) {
        return this.constructor.newInstanceVA(array);
    }
    
    public <K> SafeConstructor<K> translateOutput(final Converter<?, K> converter) {
        return new SafeConstructor<K>(this.constructor) {
            @Override
            public K newInstance(final Object... array) {
                return converter.convert(super.newInstance(array));
            }
        };
    }
    
    public static <T> SafeConstructor<T> create(final Class<T> clazz, final Class<?>... array) {
        return new SafeConstructor<T>(clazz, array);
    }
}
