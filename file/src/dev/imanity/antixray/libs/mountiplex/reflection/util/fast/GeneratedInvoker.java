// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.mountiplex.reflection.util.fast;

import dev.imanity.antixray.libs.asm.Label;
import dev.imanity.antixray.libs.asm.MethodVisitor;
import java.lang.reflect.Modifier;
import dev.imanity.antixray.libs.mountiplex.reflection.util.asm.MPLType;
import dev.imanity.antixray.libs.mountiplex.reflection.util.ExtendedClassWriter;
import java.lang.reflect.Constructor;
import dev.imanity.antixray.libs.mountiplex.reflection.resolver.Resolver;
import java.lang.reflect.Method;
import java.lang.reflect.Executable;

public abstract class GeneratedInvoker<T> implements Invoker<T>
{
    public static boolean canCreate(final Executable executable) {
        if (executable.getParameterTypes().length > 5) {
            return false;
        }
        if (executable instanceof Method) {
            return Resolver.isPublic((Method)executable);
        }
        return executable instanceof Constructor && Resolver.isPublic((Constructor<?>)executable);
    }
    
    public static <T> GeneratedInvoker<T> create(final Executable executable) {
        final Class<?> declaringClass = executable.getDeclaringClass();
        final ExtendedClassWriter<Object> build = ExtendedClassWriter.builder((Class<? super Object>)GeneratedInvoker.class).setClassLoader(declaringClass.getClassLoader()).setFlags(1).setAccess(16).build();
        final String internalName = MPLType.getInternalName(declaringClass);
        final Class<?>[] parameterTypes = executable.getParameterTypes();
        if (parameterTypes.length > 5) {
            throw new IllegalArgumentException("Method has too many parameters to be optimizable");
        }
        Class<?> clazz;
        boolean static1;
        if (executable instanceof Constructor) {
            clazz = executable.getDeclaringClass();
            static1 = true;
        }
        else {
            if (!(executable instanceof Method)) {
                throw new IllegalArgumentException("Not a constructor or method");
            }
            clazz = ((Method)executable).getReturnType();
            static1 = Modifier.isStatic(executable.getModifiers());
        }
        final MethodVisitor visitMethod = build.visitMethod(1, "<init>", "()V", null, null);
        visitMethod.visitCode();
        visitMethod.visitVarInsn(25, 0);
        visitMethod.visitMethodInsn(183, MPLType.getInternalName(GeneratedInvoker.class), "<init>", "()V", false);
        visitMethod.visitInsn(177);
        visitMethod.visitMaxs(2, 2);
        visitMethod.visitEnd();
        final String buildInvokeDescriptor = buildInvokeDescriptor(parameterTypes.length);
        final MethodVisitor visitMethod2 = build.visitMethod(145, "invokeVA", "(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;", null, null);
        visitMethod2.visitCode();
        visitInvokeVAArgCountCheck(visitMethod2, parameterTypes.length);
        if (executable instanceof Constructor) {
            visitMethod2.visitTypeInsn(187, MPLType.getInternalName(declaringClass));
            visitMethod2.visitInsn(89);
        }
        if (!static1) {
            visitMethod2.visitVarInsn(25, 1);
            visitMethod2.visitTypeInsn(192, internalName);
        }
        for (int i = 0; i < parameterTypes.length; ++i) {
            visitMethod2.visitVarInsn(25, 2);
            ExtendedClassWriter.visitPushInt(visitMethod2, i);
            visitMethod2.visitInsn(50);
            ExtendedClassWriter.visitUnboxObjectVariable(visitMethod2, parameterTypes[i]);
        }
        if (executable instanceof Method) {
            ExtendedClassWriter.visitInvoke(visitMethod2, declaringClass, (Method)executable);
        }
        else if (executable instanceof Constructor) {
            visitMethod2.visitMethodInsn(183, MPLType.getInternalName(declaringClass), "<init>", MPLType.getConstructorDescriptor((Constructor<?>)executable), false);
        }
        MPLType.visitBoxVariable(visitMethod2, clazz);
        visitMethod2.visitInsn(176);
        visitMethod2.visitMaxs(0, 0);
        visitMethod2.visitEnd();
        final MethodVisitor visitMethod3 = build.visitMethod(1, "invoke", buildInvokeDescriptor, null, null);
        visitMethod3.visitCode();
        if (executable instanceof Constructor) {
            visitMethod3.visitTypeInsn(187, MPLType.getInternalName(declaringClass));
            visitMethod3.visitInsn(89);
        }
        if (!static1) {
            visitMethod3.visitVarInsn(25, 1);
            visitMethod3.visitTypeInsn(192, internalName);
        }
        for (int j = 0; j < parameterTypes.length; ++j) {
            visitMethod3.visitVarInsn(25, 2 + j);
            ExtendedClassWriter.visitUnboxObjectVariable(visitMethod3, parameterTypes[j]);
        }
        if (executable instanceof Method) {
            ExtendedClassWriter.visitInvoke(visitMethod3, declaringClass, (Method)executable);
        }
        else if (executable instanceof Constructor) {
            visitMethod3.visitMethodInsn(183, MPLType.getInternalName(declaringClass), "<init>", MPLType.getConstructorDescriptor((Constructor<?>)executable), false);
        }
        MPLType.visitBoxVariable(visitMethod3, clazz);
        visitMethod3.visitInsn(176);
        visitMethod3.visitMaxs(0, 0);
        visitMethod3.visitEnd();
        return build.generateInstance();
    }
    
    public static String buildInvokeDescriptor(final int n) {
        final String s = "Ljava/lang/Object;";
        final StringBuilder sb = new StringBuilder(s.length() * (n + 1) + 2);
        sb.append('(');
        for (int i = 0; i <= n; ++i) {
            sb.append(s);
        }
        sb.append(')');
        sb.append(s);
        return sb.toString();
    }
    
    public static void visitInvokeVAArgCountCheck(final MethodVisitor methodVisitor, final int n) {
        methodVisitor.visitVarInsn(25, 2);
        methodVisitor.visitInsn(190);
        final Label label = new Label();
        if (n > 0) {
            ExtendedClassWriter.visitPushInt(methodVisitor, n);
            methodVisitor.visitJumpInsn(159, label);
        }
        else {
            methodVisitor.visitJumpInsn(153, label);
        }
        final String internalName = MPLType.getInternalName(InvalidArgumentCountException.class);
        methodVisitor.visitTypeInsn(187, internalName);
        methodVisitor.visitInsn(89);
        methodVisitor.visitLdcInsn("method");
        methodVisitor.visitVarInsn(25, 2);
        methodVisitor.visitInsn(190);
        ExtendedClassWriter.visitPushInt(methodVisitor, n);
        methodVisitor.visitMethodInsn(183, internalName, "<init>", "(Ljava/lang/String;II)V", false);
        methodVisitor.visitInsn(191);
        methodVisitor.visitLabel(label);
        methodVisitor.visitFrame(3, 0, null, 0, null);
    }
}
