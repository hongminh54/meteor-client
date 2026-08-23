// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.javassist.util.proxy;

import java.lang.reflect.AccessibleObject;
import java.lang.invoke.MethodHandle;
import java.util.List;
import java.lang.reflect.Method;
import dev.imanity.antixray.libs.javassist.bytecode.ClassFile;
import java.lang.invoke.MethodHandles;
import dev.imanity.antixray.libs.javassist.CannotCompileException;
import java.security.ProtectionDomain;

public class DefineClassHelper
{
    private static final Helper privileged;
    
    public static Class<?> toClass(final String s, final Class<?> clazz, final ClassLoader classLoader, final ProtectionDomain protectionDomain, final byte[] array) {
        try {
            return DefineClassHelper.privileged.defineClass(s, array, 0, array.length, clazz, classLoader, protectionDomain);
        }
        catch (final RuntimeException ex) {
            throw ex;
        }
        catch (final CannotCompileException ex2) {
            throw ex2;
        }
        catch (final ClassFormatError classFormatError) {
            final Throwable cause = classFormatError.getCause();
            throw new CannotCompileException((cause == null) ? classFormatError : cause);
        }
        catch (final Exception ex3) {
            throw new CannotCompileException(ex3);
        }
    }
    
    public static Class<?> toClass(final Class<?> targetClass, final byte[] bytes) {
        try {
            DefineClassHelper.class.getModule().addReads(targetClass.getModule());
            return MethodHandles.privateLookupIn(targetClass, MethodHandles.lookup()).defineClass(bytes);
        }
        catch (final IllegalAccessException | IllegalArgumentException ex) {
            throw new CannotCompileException(((Throwable)ex).getMessage() + ": " + targetClass.getName() + " has no permission to define the class");
        }
    }
    
    public static Class<?> toClass(final MethodHandles.Lookup lookup, final byte[] bytes) {
        try {
            return lookup.defineClass(bytes);
        }
        catch (final IllegalAccessException | IllegalArgumentException ex) {
            throw new CannotCompileException(((Throwable)ex).getMessage());
        }
    }
    
    static Class<?> toPublicClass(final String s, final byte[] bytes) {
        try {
            return MethodHandles.lookup().dropLookupMode(2).defineClass(bytes);
        }
        catch (final Throwable t) {
            throw new CannotCompileException(t);
        }
    }
    
    private DefineClassHelper() {
    }
    
    static {
        privileged = ((ClassFile.MAJOR_VERSION > 54) ? new Java11() : ((ClassFile.MAJOR_VERSION >= 53) ? new Java9() : ((ClassFile.MAJOR_VERSION >= 51) ? new Java7() : new JavaOther())));
    }
    
    private abstract static class Helper
    {
        abstract Class<?> defineClass(final String p0, final byte[] p1, final int p2, final int p3, final Class<?> p4, final ClassLoader p5, final ProtectionDomain p6);
    }
    
    private static class Java11 extends JavaOther
    {
        @Override
        Class<?> defineClass(final String s, final byte[] array, final int n, final int n2, final Class<?> clazz, final ClassLoader classLoader, final ProtectionDomain protectionDomain) {
            if (clazz != null) {
                return DefineClassHelper.toClass(clazz, array);
            }
            return super.defineClass(s, array, n, n2, clazz, classLoader, protectionDomain);
        }
    }
    
    private static class Java9 extends Helper
    {
        private final Object stack;
        private final Method getCallerClass;
        private final ReferencedUnsafe sunMiscUnsafe;
        
        Java9() {
            this.sunMiscUnsafe = this.getReferencedUnsafe();
            Class<?> forName = null;
            try {
                forName = Class.forName("java.lang.StackWalker");
            }
            catch (final ClassNotFoundException ex) {}
            if (forName != null) {
                try {
                    final Class<?> forName2 = Class.forName("java.lang.StackWalker$Option");
                    this.stack = forName.getMethod("getInstance", forName2).invoke(null, forName2.getEnumConstants()[0]);
                    this.getCallerClass = forName.getMethod("getCallerClass", (Class<?>[])new Class[0]);
                    return;
                }
                catch (final Throwable cause) {
                    throw new RuntimeException("cannot initialize", cause);
                }
            }
            this.stack = null;
            this.getCallerClass = null;
        }
        
        private final ReferencedUnsafe getReferencedUnsafe() {
            try {
                if (DefineClassHelper.privileged != null && this.getCallerClass.invoke(this.stack, new Object[0]) != this.getClass()) {
                    throw new IllegalAccessError("Access denied for caller.");
                }
            }
            catch (final Exception cause) {
                throw new RuntimeException("cannot initialize", cause);
            }
            try {
                final SecurityActions.TheUnsafe sunMiscUnsafeAnonymously = SecurityActions.getSunMiscUnsafeAnonymously();
                final List list = sunMiscUnsafeAnonymously.methods.get("defineClass");
                if (null == list) {
                    return null;
                }
                return new ReferencedUnsafe(sunMiscUnsafeAnonymously, MethodHandles.lookup().unreflect((Method)list.get(0)));
            }
            catch (final Throwable cause2) {
                throw new RuntimeException("cannot initialize", cause2);
            }
        }
        
        @Override
        Class<?> defineClass(final String s, final byte[] array, final int n, final int n2, final Class<?> clazz, final ClassLoader classLoader, final ProtectionDomain protectionDomain) {
            try {
                if (this.getCallerClass.invoke(this.stack, new Object[0]) != DefineClassHelper.class) {
                    throw new IllegalAccessError("Access denied for caller.");
                }
            }
            catch (final Exception cause) {
                throw new RuntimeException("cannot initialize", cause);
            }
            return this.sunMiscUnsafe.defineClass(s, array, n, n2, classLoader, protectionDomain);
        }
        
        final class ReferencedUnsafe
        {
            private final SecurityActions.TheUnsafe sunMiscUnsafeTheUnsafe;
            private final MethodHandle defineClass;
            
            ReferencedUnsafe(final SecurityActions.TheUnsafe sunMiscUnsafeTheUnsafe, final MethodHandle defineClass) {
                this.sunMiscUnsafeTheUnsafe = sunMiscUnsafeTheUnsafe;
                this.defineClass = defineClass;
            }
            
            Class<?> defineClass(final String s, final byte[] array, final int i, final int j, final ClassLoader classLoader, final ProtectionDomain protectionDomain) {
                try {
                    if (Java9.this.getCallerClass.invoke(Java9.this.stack, new Object[0]) != Java9.class) {
                        throw new IllegalAccessError("Access denied for caller.");
                    }
                }
                catch (final Exception cause) {
                    throw new RuntimeException("cannot initialize", cause);
                }
                try {
                    return (Class)this.defineClass.invokeWithArguments(this.sunMiscUnsafeTheUnsafe.theUnsafe, s, array, i, j, classLoader, protectionDomain);
                }
                catch (final Throwable t) {
                    if (t instanceof RuntimeException) {
                        throw (RuntimeException)t;
                    }
                    if (t instanceof ClassFormatError) {
                        throw (ClassFormatError)t;
                    }
                    throw new ClassFormatError(t.getMessage());
                }
            }
        }
    }
    
    private static class Java7 extends Helper
    {
        private final SecurityActions stack;
        private final MethodHandle defineClass;
        
        private Java7() {
            this.stack = SecurityActions.stack;
            this.defineClass = this.getDefineClassMethodHandle();
        }
        
        private final MethodHandle getDefineClassMethodHandle() {
            if (DefineClassHelper.privileged != null && this.stack.getCallerClass() != this.getClass()) {
                throw new IllegalAccessError("Access denied for caller.");
            }
            try {
                return SecurityActions.getMethodHandle(ClassLoader.class, "defineClass", new Class[] { String.class, byte[].class, Integer.TYPE, Integer.TYPE, ProtectionDomain.class });
            }
            catch (final NoSuchMethodException cause) {
                throw new RuntimeException("cannot initialize", cause);
            }
        }
        
        @Override
        Class<?> defineClass(final String s, final byte[] array, final int i, final int j, final Class<?> clazz, final ClassLoader classLoader, final ProtectionDomain protectionDomain) {
            if (this.stack.getCallerClass() != DefineClassHelper.class) {
                throw new IllegalAccessError("Access denied for caller.");
            }
            try {
                return (Class)this.defineClass.invokeWithArguments(classLoader, s, array, i, j, protectionDomain);
            }
            catch (final Throwable t) {
                if (t instanceof RuntimeException) {
                    throw (RuntimeException)t;
                }
                if (t instanceof ClassFormatError) {
                    throw (ClassFormatError)t;
                }
                throw new ClassFormatError(t.getMessage());
            }
        }
    }
    
    private static class JavaOther extends Helper
    {
        private final Method defineClass;
        private final SecurityActions stack;
        
        private JavaOther() {
            this.defineClass = this.getDefineClassMethod();
            this.stack = SecurityActions.stack;
        }
        
        private final Method getDefineClassMethod() {
            if (DefineClassHelper.privileged != null && this.stack.getCallerClass() != this.getClass()) {
                throw new IllegalAccessError("Access denied for caller.");
            }
            try {
                return SecurityActions.getDeclaredMethod(ClassLoader.class, "defineClass", new Class[] { String.class, byte[].class, Integer.TYPE, Integer.TYPE, ProtectionDomain.class });
            }
            catch (final NoSuchMethodException cause) {
                throw new RuntimeException("cannot initialize", cause);
            }
        }
        
        @Override
        Class<?> defineClass(final String s, final byte[] array, final int i, final int j, final Class<?> clazz, final ClassLoader obj, final ProtectionDomain protectionDomain) {
            final Class<?> callerClass = this.stack.getCallerClass();
            if (callerClass != DefineClassHelper.class && callerClass != this.getClass()) {
                throw new IllegalAccessError("Access denied for caller.");
            }
            try {
                SecurityActions.setAccessible(this.defineClass, true);
                return (Class)this.defineClass.invoke(obj, s, array, i, j, protectionDomain);
            }
            catch (final Throwable t) {
                if (t instanceof ClassFormatError) {
                    throw (ClassFormatError)t;
                }
                if (t instanceof RuntimeException) {
                    throw (RuntimeException)t;
                }
                throw new CannotCompileException(t);
            }
        }
    }
}
