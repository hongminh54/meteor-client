// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.javassist.util.proxy;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.invoke.MethodHandle;
import dev.imanity.antixray.libs.javassist.bytecode.ClassFile;
import dev.imanity.antixray.libs.javassist.CannotCompileException;
import java.net.URL;

public class DefinePackageHelper
{
    private static final Helper privileged;
    
    public static void definePackage(final String s, final ClassLoader classLoader) {
        try {
            DefinePackageHelper.privileged.definePackage(classLoader, s, null, null, null, null, null, null, null);
        }
        catch (final IllegalArgumentException ex) {}
        catch (final Exception ex2) {
            throw new CannotCompileException(ex2);
        }
    }
    
    private DefinePackageHelper() {
    }
    
    static {
        privileged = ((ClassFile.MAJOR_VERSION >= 53) ? new Java9() : ((ClassFile.MAJOR_VERSION >= 51) ? new Java7() : new JavaOther()));
    }
    
    private abstract static class Helper
    {
        abstract Package definePackage(final ClassLoader p0, final String p1, final String p2, final String p3, final String p4, final String p5, final String p6, final String p7, final URL p8);
    }
    
    private static class Java9 extends Helper
    {
        @Override
        Package definePackage(final ClassLoader classLoader, final String s, final String s2, final String s3, final String s4, final String s5, final String s6, final String s7, final URL url) {
            throw new RuntimeException("define package has been disabled for jigsaw");
        }
    }
    
    private static class Java7 extends Helper
    {
        private final SecurityActions stack;
        private final MethodHandle definePackage;
        
        private Java7() {
            this.stack = SecurityActions.stack;
            this.definePackage = this.getDefinePackageMethodHandle();
        }
        
        private MethodHandle getDefinePackageMethodHandle() {
            if (this.stack.getCallerClass() != this.getClass()) {
                throw new IllegalAccessError("Access denied for caller.");
            }
            try {
                return SecurityActions.getMethodHandle(ClassLoader.class, "definePackage", new Class[] { String.class, String.class, String.class, String.class, String.class, String.class, String.class, URL.class });
            }
            catch (final NoSuchMethodException cause) {
                throw new RuntimeException("cannot initialize", cause);
            }
        }
        
        @Override
        Package definePackage(final ClassLoader classLoader, final String s, final String s2, final String s3, final String s4, final String s5, final String s6, final String s7, final URL url) {
            if (this.stack.getCallerClass() != DefinePackageHelper.class) {
                throw new IllegalAccessError("Access denied for caller.");
            }
            try {
                return (Package)this.definePackage.invokeWithArguments(classLoader, s, s2, s3, s4, s5, s6, s7, url);
            }
            catch (final Throwable t) {
                if (t instanceof IllegalArgumentException) {
                    throw (IllegalArgumentException)t;
                }
                if (t instanceof RuntimeException) {
                    throw (RuntimeException)t;
                }
                return null;
            }
        }
    }
    
    private static class JavaOther extends Helper
    {
        private final SecurityActions stack;
        private final Method definePackage;
        
        private JavaOther() {
            this.stack = SecurityActions.stack;
            this.definePackage = this.getDefinePackageMethod();
        }
        
        private Method getDefinePackageMethod() {
            if (this.stack.getCallerClass() != this.getClass()) {
                throw new IllegalAccessError("Access denied for caller.");
            }
            try {
                return SecurityActions.getDeclaredMethod(ClassLoader.class, "definePackage", new Class[] { String.class, String.class, String.class, String.class, String.class, String.class, String.class, URL.class });
            }
            catch (final NoSuchMethodException cause) {
                throw new RuntimeException("cannot initialize", cause);
            }
        }
        
        @Override
        Package definePackage(final ClassLoader obj, final String s, final String s2, final String s3, final String s4, final String s5, final String s6, final String s7, final URL url) {
            if (this.stack.getCallerClass() != DefinePackageHelper.class) {
                throw new IllegalAccessError("Access denied for caller.");
            }
            try {
                this.definePackage.setAccessible(true);
                return (Package)this.definePackage.invoke(obj, s, s2, s3, s4, s5, s6, s7, url);
            }
            catch (final Throwable t) {
                if (t instanceof InvocationTargetException) {
                    final Throwable targetException = ((InvocationTargetException)t).getTargetException();
                    if (targetException instanceof IllegalArgumentException) {
                        throw (IllegalArgumentException)targetException;
                    }
                }
                if (t instanceof RuntimeException) {
                    throw (RuntimeException)t;
                }
                return null;
            }
        }
    }
}
