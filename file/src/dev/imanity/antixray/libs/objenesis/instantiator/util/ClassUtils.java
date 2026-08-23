// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.objenesis.instantiator.util;

import dev.imanity.antixray.libs.objenesis.ObjenesisException;

public final class ClassUtils
{
    private ClassUtils() {
    }
    
    public static String classNameToInternalClassName(final String s) {
        return s.replace('.', '/');
    }
    
    public static String classNameToResource(final String s) {
        return classNameToInternalClassName(s) + ".class";
    }
    
    public static <T> Class<T> getExistingClass(final ClassLoader loader, final String name) {
        try {
            return (Class<T>)Class.forName(name, true, loader);
        }
        catch (final ClassNotFoundException ex) {
            return null;
        }
    }
    
    public static <T> T newInstance(final Class<T> clazz) {
        try {
            return clazz.newInstance();
        }
        catch (final InstantiationException | IllegalAccessException ex) {
            throw new ObjenesisException((Throwable)ex);
        }
    }
}
