// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.mountiplex.reflection;

import dev.imanity.antixray.libs.mountiplex.reflection.util.asm.MPLType;
import dev.imanity.antixray.libs.mountiplex.reflection.resolver.Resolver;
import java.util.logging.Level;
import dev.imanity.antixray.libs.mountiplex.MountiplexUtil;
import java.lang.reflect.Method;
import dev.imanity.antixray.libs.mountiplex.reflection.util.FastMethod;

public class SafeMethod<T> implements MethodAccessor<T>
{
    private final FastMethod<T> method;
    
    public SafeMethod(final FastMethod<T> method) {
        if (method == null) {
            throw new IllegalArgumentException("Backing method can not be null");
        }
        this.method = method;
    }
    
    public SafeMethod(final Method method) {
        (this.method = new FastMethod<T>()).init(method);
    }
    
    public SafeMethod(final String s, final Class<?>... array) {
        this.method = new FastMethod<T>();
        if (s == null || s.isEmpty() || !s.contains(".")) {
            MountiplexUtil.LOGGER.log(Level.SEVERE, "Method path contains no class: " + s);
            return;
        }
        try {
            final String lastBefore = MountiplexUtil.getLastBefore(s, ".");
            this.load(Resolver.loadClass(lastBefore, false), s.substring(lastBefore.length() + 1), array);
        }
        catch (final Throwable thrown) {
            MountiplexUtil.LOGGER.log(Level.SEVERE, "Failed to load method '" + s + "'", thrown);
        }
    }
    
    public SafeMethod(final Object o, final String s, final Class<?>... array) {
        this.method = new FastMethod<T>();
        this.load((o == null) ? null : o.getClass(), s, array);
    }
    
    public SafeMethod(final Class<?> clazz, final String s, final Class<?>... array) {
        this.method = new FastMethod<T>();
        this.load(clazz, s, array);
    }
    
    private void load(final Class<?> clazz, final String s, final Class<?>... array) {
        if (clazz == null) {
            MountiplexUtil.LOGGER.log(Level.SEVERE, "Can not load method '" + s + "' because the class is null!", new IllegalStateException("Missing source"));
            return;
        }
        final String resolveMethodName = Resolver.resolveMethodName(clazz, s, array);
        String str = (s.equals(resolveMethodName) ? s : (s + ":" + resolveMethodName)) + "(";
        for (int i = 0; i < array.length; ++i) {
            if (i > 0) {
                str += ", ";
            }
            str += array[i].getSimpleName();
        }
        final String string = str + ")";
        this.method.init(findRaw(clazz, resolveMethodName, array));
        if (!this.method.isAvailable()) {
            final String string2 = "Method '" + string + "' could not be found in class " + MPLType.getName(clazz);
            this.method.initUnavailable(string2);
            MountiplexUtil.LOGGER.warning(string2);
        }
    }
    
    public String getName() {
        return this.method.getName();
    }
    
    public boolean isOverridedIn(final Class<?> clazz) {
        try {
            final Method method = this.method.getMethod();
            return method != null && MPLType.getDeclaredMethod(clazz, MPLType.getName(method), method.getParameterTypes()).getDeclaringClass() != method.getDeclaringClass();
        }
        catch (final Throwable t) {
            return false;
        }
    }
    
    @Override
    public boolean isValid() {
        return this.method.isAvailable();
    }
    
    @Override
    public T invoke(final Object o, final Object... array) {
        return this.method.invokeVA(o, array);
    }
    
    public static boolean contains(final Class<?> clazz, final String s, final Class<?>... array) {
        return findRaw(clazz, Resolver.resolveMethodName(clazz, s, array), array) != null;
    }
    
    private static Method findRaw(final Class<?> clazz, final String name, final Class<?>... parameterTypes) {
        Class<?> superclass = clazz;
        while (superclass != null) {
            try {
                return MPLType.getDeclaredMethod(superclass, name, parameterTypes);
            }
            catch (final NoSuchMethodException ex) {
                superclass = superclass.getSuperclass();
                continue;
            }
            break;
        }
        final Class[] interfaces = clazz.getInterfaces();
        final int length = interfaces.length;
        int i = 0;
        while (i < length) {
            final Class clazz2 = interfaces[i];
            try {
                return clazz2.getDeclaredMethod(name, (Class[])parameterTypes);
            }
            catch (final NoSuchMethodException ex2) {
                ++i;
                continue;
            }
            break;
        }
        return null;
    }
    
    @Override
    public boolean isMethod(final Method obj) {
        final Method method = this.method.getMethod();
        if (method == null) {
            return false;
        }
        if (method.equals(obj)) {
            return true;
        }
        if (!MPLType.getName(method).equals(MPLType.getName(obj))) {
            return false;
        }
        if (!method.getReturnType().equals(obj.getReturnType())) {
            return false;
        }
        final Class<?>[] parameterTypes = method.getParameterTypes();
        final Class<?>[] parameterTypes2 = obj.getParameterTypes();
        if (parameterTypes.length != parameterTypes2.length) {
            return false;
        }
        for (int i = 0; i < parameterTypes.length; ++i) {
            if (!parameterTypes[i].equals(parameterTypes2[i])) {
                return false;
            }
        }
        final Class<?> declaringClass = method.getDeclaringClass();
        final Class<?> declaringClass2 = obj.getDeclaringClass();
        return declaringClass.isAssignableFrom(declaringClass2) || declaringClass2.isAssignableFrom(declaringClass);
    }
}
