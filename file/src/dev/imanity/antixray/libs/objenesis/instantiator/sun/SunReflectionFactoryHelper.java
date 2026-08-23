// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.objenesis.instantiator.sun;

import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;
import dev.imanity.antixray.libs.objenesis.ObjenesisException;
import java.lang.reflect.Constructor;

class SunReflectionFactoryHelper
{
    public static <T> Constructor<T> newConstructorForSerialization(final Class<T> clazz, final Constructor<?> constructor) {
        final Class<?> reflectionFactoryClass = getReflectionFactoryClass();
        final Object reflectionFactory = createReflectionFactory(reflectionFactoryClass);
        final Method newConstructorForSerializationMethod = getNewConstructorForSerializationMethod(reflectionFactoryClass);
        try {
            return (Constructor)newConstructorForSerializationMethod.invoke(reflectionFactory, clazz, constructor);
        }
        catch (final IllegalArgumentException | IllegalAccessException | InvocationTargetException ex) {
            throw new ObjenesisException((Throwable)ex);
        }
    }
    
    private static Class<?> getReflectionFactoryClass() {
        try {
            return Class.forName("sun.reflect.ReflectionFactory");
        }
        catch (final ClassNotFoundException ex) {
            throw new ObjenesisException(ex);
        }
    }
    
    private static Object createReflectionFactory(final Class<?> clazz) {
        try {
            return clazz.getDeclaredMethod("getReflectionFactory", (Class<?>[])new Class[0]).invoke(null, new Object[0]);
        }
        catch (final NoSuchMethodException | IllegalAccessException | InvocationTargetException | IllegalArgumentException ex) {
            throw new ObjenesisException((Throwable)ex);
        }
    }
    
    private static Method getNewConstructorForSerializationMethod(final Class<?> clazz) {
        try {
            return clazz.getDeclaredMethod("newConstructorForSerialization", Class.class, Constructor.class);
        }
        catch (final NoSuchMethodException ex) {
            throw new ObjenesisException(ex);
        }
    }
}
