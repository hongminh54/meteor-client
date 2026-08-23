// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.objenesis.strategy;

import java.lang.reflect.InvocationTargetException;
import dev.imanity.antixray.libs.objenesis.ObjenesisException;
import dev.imanity.antixray.libs.objenesis.instantiator.ObjectInstantiator;
import java.lang.reflect.Constructor;

public class SingleInstantiatorStrategy implements InstantiatorStrategy
{
    private final Constructor<?> constructor;
    
    public <T extends ObjectInstantiator<?>> SingleInstantiatorStrategy(final Class<T> clazz) {
        try {
            this.constructor = clazz.getConstructor(Class.class);
        }
        catch (final NoSuchMethodException ex) {
            throw new ObjenesisException(ex);
        }
    }
    
    @Override
    public <T> ObjectInstantiator<T> newInstantiatorOf(final Class<T> clazz) {
        try {
            return (ObjectInstantiator<T>)this.constructor.newInstance(clazz);
        }
        catch (final InstantiationException | IllegalAccessException | InvocationTargetException ex) {
            throw new ObjenesisException((Throwable)ex);
        }
    }
}
