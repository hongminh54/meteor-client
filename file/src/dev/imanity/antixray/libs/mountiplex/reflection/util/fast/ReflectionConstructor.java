// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.mountiplex.reflection.util.fast;

import dev.imanity.antixray.libs.mountiplex.reflection.resolver.Resolver;
import java.lang.reflect.InvocationTargetException;
import dev.imanity.antixray.libs.mountiplex.reflection.UnhandledInvokerCheckedException;
import dev.imanity.antixray.libs.mountiplex.reflection.util.asm.MPLType;
import dev.imanity.antixray.libs.mountiplex.reflection.util.BoxedType;

public class ReflectionConstructor<T> implements Constructor<T>
{
    private static final Object[] NO_ARGS;
    private final java.lang.reflect.Constructor<Object> c;
    
    protected ReflectionConstructor(final java.lang.reflect.Constructor<?> c) {
        this.c = (java.lang.reflect.Constructor<Object>)c;
    }
    
    private RuntimeException f(final Object[] array, final Throwable cause) {
        final Class<?>[] parameterTypes = this.c.getParameterTypes();
        if (parameterTypes.length != array.length) {
            return new InvalidArgumentCountException("constructor", array.length, parameterTypes.length);
        }
        for (int i = 0; i < parameterTypes.length; ++i) {
            if (parameterTypes[i].isPrimitive()) {
                if (array[i] == null) {
                    return new IllegalArgumentException("Illegal null value used for primitive " + parameterTypes[i].getSimpleName() + " method parameter #" + i);
                }
                final Class<?> boxedType = BoxedType.getBoxedType(parameterTypes[i]);
                if (boxedType != null && !boxedType.isAssignableFrom(array[i].getClass())) {
                    return new IllegalArgumentException("Value of type " + MPLType.getName(array[i].getClass()) + " can not be assigned to primitive " + parameterTypes[i].getSimpleName() + " method parameter #" + i);
                }
            }
            else if (array[i] != null && !parameterTypes[i].isAssignableFrom(array[i].getClass())) {
                return new IllegalArgumentException("Value of type " + MPLType.getName(array[i].getClass()) + " can not be assigned to " + MPLType.getName(parameterTypes[i]) + " method parameter #" + i);
            }
        }
        if (cause instanceof RuntimeException) {
            return (RuntimeException)cause;
        }
        return new RuntimeException("Failed to invoke method", cause);
    }
    
    @Override
    public T newInstanceVA(final Object... initargs) {
        try {
            return (T)this.c.newInstance(initargs);
        }
        catch (final InvocationTargetException ex) {
            final Throwable cause = ex.getCause();
            if (cause instanceof RuntimeException) {
                throw (RuntimeException)cause;
            }
            throw new UnhandledInvokerCheckedException(cause);
        }
        catch (final Throwable t) {
            throw this.f(initargs, t);
        }
    }
    
    @Override
    public T newInstance() {
        return this.newInstanceVA(ReflectionConstructor.NO_ARGS);
    }
    
    @Override
    public T newInstance(final Object o) {
        return this.newInstanceVA(o);
    }
    
    @Override
    public T newInstance(final Object o, final Object o2) {
        return this.newInstanceVA(o, o2);
    }
    
    @Override
    public T newInstance(final Object o, final Object o2, final Object o3) {
        return this.newInstanceVA(o, o2, o3);
    }
    
    @Override
    public T newInstance(final Object o, final Object o2, final Object o3, final Object o4) {
        return this.newInstanceVA(o, o2, o3, o4);
    }
    
    @Override
    public T newInstance(final Object o, final Object o2, final Object o3, final Object o4, final Object o5) {
        return this.newInstanceVA(o, o2, o3, o4, o5);
    }
    
    public static <T> Constructor<T> create(final java.lang.reflect.Constructor<?> constructor) {
        if (constructor.getParameterTypes().length <= 5 && Resolver.isPublic(constructor)) {
            return (Constructor<T>)GeneratedConstructor.create(constructor);
        }
        return new ReflectionConstructor<T>(constructor);
    }
    
    static {
        NO_ARGS = new Object[0];
    }
}
