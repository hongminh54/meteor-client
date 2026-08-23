// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.objenesis.instantiator.basic;

import dev.imanity.antixray.libs.objenesis.ObjenesisException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Constructor;
import dev.imanity.antixray.libs.objenesis.instantiator.ObjectInstantiator;

public abstract class DelegatingToExoticInstantiator<T> implements ObjectInstantiator<T>
{
    private final ObjectInstantiator<T> wrapped;
    
    protected DelegatingToExoticInstantiator(final String s, final Class<T> clazz) {
        this.wrapped = this.instantiator(s, clazz, this.instantiatorConstructor(s, this.instantiatorClass(s)));
    }
    
    private ObjectInstantiator<T> instantiator(final String str, final Class<T> clazz, final Constructor<ObjectInstantiator<T>> constructor) {
        try {
            return constructor.newInstance(clazz);
        }
        catch (final InstantiationException | IllegalAccessException | InvocationTargetException cause) {
            throw new RuntimeException("Failed to call constructor of " + str, (Throwable)cause);
        }
    }
    
    private Class<ObjectInstantiator<T>> instantiatorClass(final String className) {
        try {
            return (Class<ObjectInstantiator<T>>)Class.forName(className);
        }
        catch (final ClassNotFoundException ex) {
            throw new ObjenesisException(this.getClass().getSimpleName() + " now requires objenesis-exotic to be in the classpath", ex);
        }
    }
    
    private Constructor<ObjectInstantiator<T>> instantiatorConstructor(final String str, final Class<ObjectInstantiator<T>> clazz) {
        try {
            return clazz.getConstructor(Class.class);
        }
        catch (final NoSuchMethodException ex) {
            throw new ObjenesisException("Try to find constructor taking a Class<T> in parameter on " + str + " but can't find it", ex);
        }
    }
    
    @Override
    public T newInstance() {
        return this.wrapped.newInstance();
    }
}
