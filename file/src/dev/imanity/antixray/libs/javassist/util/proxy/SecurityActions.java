// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.javassist.util.proxy;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import dev.imanity.antixray.libs.javassist.bytecode.ClassFile;
import java.lang.reflect.Field;
import java.lang.reflect.AccessibleObject;
import java.security.PrivilegedActionException;
import java.lang.invoke.MethodHandles;
import java.security.PrivilegedExceptionAction;
import java.lang.invoke.MethodHandle;
import java.lang.reflect.Constructor;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.lang.reflect.Method;

class SecurityActions extends SecurityManager
{
    public static final SecurityActions stack;
    
    public Class<?> getCallerClass() {
        return this.getClassContext()[2];
    }
    
    static Method[] getDeclaredMethods(final Class<?> clazz) {
        if (System.getSecurityManager() == null) {
            return clazz.getDeclaredMethods();
        }
        return AccessController.doPrivileged((PrivilegedAction<Method[]>)new PrivilegedAction<Method[]>() {
            @Override
            public Method[] run() {
                return clazz.getDeclaredMethods();
            }
        });
    }
    
    static Constructor<?>[] getDeclaredConstructors(final Class<?> clazz) {
        if (System.getSecurityManager() == null) {
            return clazz.getDeclaredConstructors();
        }
        return AccessController.doPrivileged((PrivilegedAction<Constructor[]>)new PrivilegedAction<Constructor<?>[]>() {
            @Override
            public Constructor<?>[] run() {
                return clazz.getDeclaredConstructors();
            }
        });
    }
    
    static MethodHandle getMethodHandle(final Class<?> clazz, final String s, final Class<?>[] array) {
        try {
            return AccessController.doPrivileged((PrivilegedExceptionAction<MethodHandle>)new PrivilegedExceptionAction<MethodHandle>() {
                @Override
                public MethodHandle run() {
                    final Method declaredMethod = clazz.getDeclaredMethod(s, (Class[])array);
                    declaredMethod.setAccessible(true);
                    final MethodHandle unreflect = MethodHandles.lookup().unreflect(declaredMethod);
                    declaredMethod.setAccessible(false);
                    return unreflect;
                }
            });
        }
        catch (final PrivilegedActionException ex) {
            if (ex.getCause() instanceof NoSuchMethodException) {
                throw (NoSuchMethodException)ex.getCause();
            }
            throw new RuntimeException(ex.getCause());
        }
    }
    
    static Method getDeclaredMethod(final Class<?> clazz, final String name, final Class<?>[] parameterTypes) {
        if (System.getSecurityManager() == null) {
            return clazz.getDeclaredMethod(name, (Class[])parameterTypes);
        }
        try {
            return AccessController.doPrivileged((PrivilegedExceptionAction<Method>)new PrivilegedExceptionAction<Method>() {
                @Override
                public Method run() {
                    return clazz.getDeclaredMethod(name, (Class[])parameterTypes);
                }
            });
        }
        catch (final PrivilegedActionException ex) {
            if (ex.getCause() instanceof NoSuchMethodException) {
                throw (NoSuchMethodException)ex.getCause();
            }
            throw new RuntimeException(ex.getCause());
        }
    }
    
    static Constructor<?> getDeclaredConstructor(final Class<?> clazz, final Class<?>[] parameterTypes) {
        if (System.getSecurityManager() == null) {
            return clazz.getDeclaredConstructor(parameterTypes);
        }
        try {
            return AccessController.doPrivileged((PrivilegedExceptionAction<Constructor<?>>)new PrivilegedExceptionAction<Constructor<?>>() {
                @Override
                public Constructor<?> run() {
                    return clazz.getDeclaredConstructor((Class<?>[])parameterTypes);
                }
            });
        }
        catch (final PrivilegedActionException ex) {
            if (ex.getCause() instanceof NoSuchMethodException) {
                throw (NoSuchMethodException)ex.getCause();
            }
            throw new RuntimeException(ex.getCause());
        }
    }
    
    static void setAccessible(final AccessibleObject accessibleObject, final boolean accessible) {
        if (System.getSecurityManager() == null) {
            accessibleObject.setAccessible(accessible);
        }
        else {
            AccessController.doPrivileged((PrivilegedAction<Object>)new PrivilegedAction<Void>() {
                @Override
                public Void run() {
                    accessibleObject.setAccessible(accessible);
                    return null;
                }
            });
        }
    }
    
    static void set(final Field field, final Object obj, final Object value) {
        if (System.getSecurityManager() == null) {
            field.set(obj, value);
        }
        else {
            try {
                AccessController.doPrivileged((PrivilegedExceptionAction<Object>)new PrivilegedExceptionAction<Void>() {
                    @Override
                    public Void run() {
                        field.set(obj, value);
                        return null;
                    }
                });
            }
            catch (final PrivilegedActionException ex) {
                if (ex.getCause() instanceof NoSuchMethodException) {
                    throw (IllegalAccessException)ex.getCause();
                }
                throw new RuntimeException(ex.getCause());
            }
        }
    }
    
    static TheUnsafe getSunMiscUnsafeAnonymously() {
        try {
            return AccessController.doPrivileged((PrivilegedExceptionAction<TheUnsafe>)new PrivilegedExceptionAction<TheUnsafe>() {
                @Override
                public TheUnsafe run() {
                    final Class<?> forName = Class.forName("sun.misc.Unsafe");
                    final Field declaredField = forName.getDeclaredField("theUnsafe");
                    declaredField.setAccessible(true);
                    final TheUnsafe theUnsafe = new TheUnsafe(forName, declaredField.get(null));
                    declaredField.setAccessible(false);
                    SecurityActions.disableWarning(theUnsafe);
                    return theUnsafe;
                }
            });
        }
        catch (final PrivilegedActionException ex) {
            if (ex.getCause() instanceof ClassNotFoundException) {
                throw (ClassNotFoundException)ex.getCause();
            }
            if (ex.getCause() instanceof NoSuchFieldException) {
                throw new ClassNotFoundException("No such instance.", ex.getCause());
            }
            if (ex.getCause() instanceof IllegalAccessException || ex.getCause() instanceof SecurityException) {
                throw new ClassNotFoundException("Security denied access.", ex.getCause());
            }
            throw new RuntimeException(ex.getCause());
        }
    }
    
    static void disableWarning(final TheUnsafe theUnsafe) {
        try {
            if (ClassFile.MAJOR_VERSION < 53) {
                return;
            }
            final Class<?> forName = Class.forName("jdk.internal.module.IllegalAccessLogger");
            theUnsafe.call("putObjectVolatile", forName, theUnsafe.call("staticFieldOffset", forName.getDeclaredField("logger")), null);
        }
        catch (final Exception ex) {}
    }
    
    static {
        stack = new SecurityActions();
    }
    
    class TheUnsafe
    {
        final Class<?> unsafe;
        final Object theUnsafe;
        final Map<String, List<Method>> methods;
        
        TheUnsafe(final Class<?> unsafe, final Object theUnsafe) {
            this.methods = new HashMap<String, List<Method>>();
            this.unsafe = unsafe;
            this.theUnsafe = theUnsafe;
            for (final Method o : this.unsafe.getDeclaredMethods()) {
                if (!this.methods.containsKey(o.getName())) {
                    this.methods.put(o.getName(), Collections.singletonList(o));
                }
                else {
                    if (this.methods.get(o.getName()).size() == 1) {
                        this.methods.put(o.getName(), new ArrayList<Method>(this.methods.get(o.getName())));
                    }
                    this.methods.get(o.getName()).add(o);
                }
            }
        }
        
        private Method getM(final String s, final Object[] array) {
            return this.methods.get(s).get(0);
        }
        
        public Object call(final String s, final Object... args) {
            try {
                return this.getM(s, args).invoke(this.theUnsafe, args);
            }
            catch (final Throwable t) {
                t.printStackTrace();
                return null;
            }
        }
    }
}
