// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.mountiplex.reflection.util.asm;

import dev.imanity.antixray.libs.asm.MethodVisitor;
import dev.imanity.antixray.libs.asm.Type;
import dev.imanity.antixray.libs.asm.ClassWriter;
import dev.imanity.antixray.libs.mountiplex.reflection.util.UniqueHash;
import java.security.ProtectionDomain;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import dev.imanity.antixray.libs.mountiplex.MountiplexUtil;
import java.lang.invoke.MethodHandles;
import dev.imanity.antixray.libs.mountiplex.reflection.util.GeneratorClassLoader;

public class MPLGeneratorClassLoaderBuilder
{
    public static Class<? extends GeneratorClassLoader> buildImplementation() {
        final Method methodHandlesAPIDefineClassMethod = getMethodHandlesAPIDefineClassMethod();
        if (methodHandlesAPIDefineClassMethod != null) {
            final ImplementationSource buildSource = buildSource(MPLGeneratorClassLoaderBuilder.class, "GeneratorClassLoaderImpl");
            final MethodHandles.Lookup lookup = MethodHandles.lookup();
            try {
                return (Class)methodHandlesAPIDefineClassMethod.invoke(lookup, buildSource.byteCode);
            }
            catch (final Throwable t) {
                throw MountiplexUtil.uncheckedRethrow(t);
            }
        }
        final ImplementationSource buildSource2 = buildSource(GeneratorClassLoader.class, "impl");
        final ClassLoader classLoader = GeneratorClassLoader.class.getClassLoader();
        final Method classLoaderAPIDefineClassMethod = getClassLoaderAPIDefineClassMethod();
        final Object o = null;
        try {
            return (Class)classLoaderAPIDefineClassMethod.invoke(classLoader, buildSource2.className, buildSource2.byteCode, 0, buildSource2.byteCode.length, o);
        }
        catch (final Throwable t2) {
            throw MountiplexUtil.uncheckedRethrow(t2);
        }
    }
    
    private static Method getMethodHandlesAPIDefineClassMethod() {
        try {
            Class.forName("java.lang.invoke.MethodHandles$Lookup");
        }
        catch (final ClassNotFoundException ex) {
            return null;
        }
        Method method;
        try {
            method = Class.class.getMethod(String.join("", "get", "Method"), String.class, Class[].class);
        }
        catch (final Throwable t) {
            throw MountiplexUtil.uncheckedRethrow(t);
        }
        try {
            return (Method)method.invoke(MethodHandles.Lookup.class, String.join("", "define", "Class"), new Class[] { byte[].class });
        }
        catch (final InvocationTargetException ex2) {
            if (ex2.getCause() instanceof NoSuchMethodException) {
                return null;
            }
            throw MountiplexUtil.uncheckedRethrow(ex2.getCause());
        }
        catch (final Throwable t2) {
            throw MountiplexUtil.uncheckedRethrow(t2);
        }
    }
    
    private static Method getClassLoaderAPIDefineClassMethod() {
        Method method;
        try {
            method = Class.class.getMethod(String.join("", "get", "Declared", "Method"), String.class, Class[].class);
        }
        catch (final Throwable t) {
            throw MountiplexUtil.uncheckedRethrow(t);
        }
        Method method2;
        try {
            method2 = (Method)method.invoke(ClassLoader.class, String.join("", "define", "Class"), new Class[] { String.class, byte[].class, Integer.TYPE, Integer.TYPE, ProtectionDomain.class });
        }
        catch (final Throwable t2) {
            throw MountiplexUtil.uncheckedRethrow(t2);
        }
        try {
            method2.setAccessible(true);
        }
        catch (final Throwable cause) {
            MountiplexUtil.LOGGER.severe("Failed to make ClassLoader.defineClass() accessible. Try adding one of these JVM flags:\n  - JDK9 to JDK14: --illegal-access=permit\n  - JDK15 and beyond: --add-opens java.base/java.lang=ALL-UNNAMED");
            throw new UnsupportedOperationException("Failed to make ClassLoader.defineClass() accessible", cause);
        }
        return method2;
    }
    
    private static ImplementationSource buildSource(final Class<?> clazz, final String str) {
        String string = str;
        final UniqueHash uniqueHash = new UniqueHash();
        while (true) {
            try {
                Class.forName(clazz.getName() + "$" + string);
            }
            catch (final ClassNotFoundException ex) {
                break;
            }
            string = str + uniqueHash.nextHex();
        }
        final ClassWriter classWriter = new ClassWriter(0);
        final String internalName = Type.getInternalName(clazz);
        final String internalName2 = Type.getInternalName(GeneratorClassLoader.class);
        classWriter.visit(52, 33, internalName + "$" + string, null, internalName2, null);
        final MethodVisitor visitMethod = classWriter.visitMethod(1, "<init>", "(Ljava/lang/ClassLoader;)V", null, null);
        visitMethod.visitCode();
        visitMethod.visitVarInsn(25, 0);
        visitMethod.visitVarInsn(25, 1);
        visitMethod.visitMethodInsn(183, internalName2, "<init>", "(Ljava/lang/ClassLoader;)V", false);
        visitMethod.visitInsn(177);
        visitMethod.visitMaxs(2, 2);
        visitMethod.visitEnd();
        final MethodVisitor visitMethod2 = classWriter.visitMethod(4, "defineClassFromBytecode", "(Ljava/lang/String;[BLjava/security/ProtectionDomain;)Ljava/lang/Class;", "(Ljava/lang/String;[BLjava/security/ProtectionDomain;)Ljava/lang/Class<*>;", null);
        visitMethod2.visitCode();
        visitMethod2.visitVarInsn(25, 0);
        visitMethod2.visitVarInsn(25, 1);
        visitMethod2.visitVarInsn(25, 2);
        visitMethod2.visitInsn(3);
        visitMethod2.visitVarInsn(25, 2);
        visitMethod2.visitInsn(190);
        visitMethod2.visitVarInsn(25, 3);
        visitMethod2.visitMethodInsn(183, internalName2, "defineClass", "(Ljava/lang/String;[BIILjava/security/ProtectionDomain;)Ljava/lang/Class;", false);
        visitMethod2.visitInsn(176);
        visitMethod2.visitMaxs(6, 4);
        visitMethod2.visitEnd();
        classWriter.visitEnd();
        return new ImplementationSource(clazz.getName() + "$" + string, classWriter.toByteArray());
    }
    
    private static class ImplementationSource
    {
        public final String className;
        public final byte[] byteCode;
        
        public ImplementationSource(final String className, final byte[] byteCode) {
            this.className = className;
            this.byteCode = byteCode;
        }
    }
}
