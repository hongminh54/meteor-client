// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.mountiplex.reflection.util.fast;

import java.lang.reflect.InvocationTargetException;
import dev.imanity.antixray.libs.mountiplex.reflection.UnhandledInvokerCheckedException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import dev.imanity.antixray.libs.mountiplex.reflection.util.BoxedType;
import dev.imanity.antixray.libs.mountiplex.reflection.util.asm.MPLType;
import java.lang.reflect.Modifier;
import java.lang.reflect.Executable;

public abstract class ReflectionInvoker<T> implements Invoker<T>
{
    private static final Object[] NO_ARGS;
    
    private static RuntimeException checkInstance(final Executable executable, final Object o) {
        if (Modifier.isStatic(executable.getModifiers())) {
            if (o != null) {
                return new IllegalArgumentException("Instance should be null for static fields, but was " + MPLType.getName(o.getClass()) + " instead");
            }
        }
        else {
            if (o == null) {
                return new IllegalArgumentException("Instance can not be null for member fields declared in " + MPLType.getName(executable.getDeclaringClass()));
            }
            if (!executable.getDeclaringClass().isAssignableFrom(o.getClass())) {
                return new IllegalArgumentException("Instance of type " + MPLType.getName(o.getClass()) + " does not contain the field declared in " + MPLType.getName(executable.getDeclaringClass()));
            }
        }
        return null;
    }
    
    protected static RuntimeException f(final Executable executable, final Object o, final Object[] array, final Throwable cause) {
        final RuntimeException checkInstance = checkInstance(executable, o);
        if (checkInstance != null) {
            return checkInstance;
        }
        final Class<?>[] parameterTypes = executable.getParameterTypes();
        if (parameterTypes.length != array.length) {
            return new InvalidArgumentCountException("method", array.length, parameterTypes.length);
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
    public T invoke(final Object o) {
        return this.invokeVA(o, ReflectionInvoker.NO_ARGS);
    }
    
    @Override
    public T invoke(final Object o, final Object o2) {
        return this.invokeVA(o, o2);
    }
    
    @Override
    public T invoke(final Object o, final Object o2, final Object o3) {
        return this.invokeVA(o, o2, o3);
    }
    
    @Override
    public T invoke(final Object o, final Object o2, final Object o3, final Object o4) {
        return this.invokeVA(o, o2, o3, o4);
    }
    
    @Override
    public T invoke(final Object o, final Object o2, final Object o3, final Object o4, final Object o5) {
        return this.invokeVA(o, o2, o3, o4, o5);
    }
    
    @Override
    public T invoke(final Object o, final Object o2, final Object o3, final Object o4, final Object o5, final Object o6) {
        return this.invokeVA(o, o2, o3, o4, o5, o6);
    }
    
    public static <T> Invoker<T> create(final Executable executable) {
        executable.setAccessible(true);
        if (executable instanceof Method) {
            return new ReflectionMethodInvoker<T>((Method)executable);
        }
        if (executable instanceof Constructor) {
            return new ReflectionConstructorInvoker<T>((Constructor<?>)executable);
        }
        throw new IllegalArgumentException("Not a method or constructor");
    }
    
    static {
        NO_ARGS = new Object[0];
    }
    
    private static class ReflectionMethodInvoker<T> extends ReflectionInvoker<T>
    {
        protected final Method m;
        
        public ReflectionMethodInvoker(final Method m) {
            this.m = m;
        }
        
        @Override
        public T invokeVA(final Object obj, final Object... args) {
            try {
                return (T)this.m.invoke(obj, args);
            }
            catch (final InvocationTargetException ex) {
                final Throwable cause = ex.getCause();
                if (cause instanceof RuntimeException) {
                    throw (RuntimeException)cause;
                }
                throw new UnhandledInvokerCheckedException(cause);
            }
            catch (final Throwable t) {
                throw ReflectionInvoker.f(this.m, obj, args, t);
            }
        }
    }
    
    private static class ReflectionConstructorInvoker<T> extends ReflectionInvoker<T>
    {
        protected final Constructor<T> c;
        
        public ReflectionConstructorInvoker(final Constructor<?> c) {
            this.c = (Constructor<T>)c;
        }
        
        @Override
        public T invokeVA(final Object o, final Object... initargs) {
            try {
                return this.c.newInstance(initargs);
            }
            catch (final InvocationTargetException ex) {
                final Throwable cause = ex.getCause();
                if (cause instanceof RuntimeException) {
                    throw (RuntimeException)cause;
                }
                throw new UnhandledInvokerCheckedException(cause);
            }
            catch (final Throwable t) {
                throw ReflectionInvoker.f(this.c, o, initargs, t);
            }
        }
    }
}
