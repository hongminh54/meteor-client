// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.mountiplex.reflection.util.fast;

import dev.imanity.antixray.libs.asm.MethodVisitor;
import dev.imanity.antixray.libs.asm.Label;
import dev.imanity.antixray.libs.mountiplex.reflection.util.asm.MPLType;
import dev.imanity.antixray.libs.mountiplex.reflection.util.ExtendedClassWriter;

public abstract class GeneratedConstructor implements Constructor<Object>
{
    private final java.lang.reflect.Constructor<Object> c;
    
    public GeneratedConstructor(final java.lang.reflect.Constructor<?> c) {
        this.c = (java.lang.reflect.Constructor<Object>)c;
    }
    
    @Override
    public Object newInstance() {
        throw this.failArgs(0);
    }
    
    @Override
    public Object newInstance(final Object o) {
        throw this.failArgs(1);
    }
    
    @Override
    public Object newInstance(final Object o, final Object o2) {
        throw this.failArgs(2);
    }
    
    @Override
    public Object newInstance(final Object o, final Object o2, final Object o3) {
        throw this.failArgs(3);
    }
    
    @Override
    public Object newInstance(final Object o, final Object o2, final Object o3, final Object o4) {
        throw this.failArgs(4);
    }
    
    @Override
    public Object newInstance(final Object o, final Object o2, final Object o3, final Object o4, final Object o5) {
        throw this.failArgs(5);
    }
    
    protected final InvalidArgumentCountException failArgs(final int n) {
        return new InvalidArgumentCountException("constructor", n, this.c.getParameterTypes().length);
    }
    
    public static GeneratedConstructor create(final java.lang.reflect.Constructor<?> constructor) {
        final ExtendedClassWriter<Object> build = ExtendedClassWriter.builder((Class<? super Object>)GeneratedConstructor.class).setFlags(1).setAccess(16).build();
        final Class declaringClass = constructor.getDeclaringClass();
        final String internalName = MPLType.getInternalName(declaringClass);
        final Class[] parameterTypes = constructor.getParameterTypes();
        if (parameterTypes.length > 5) {
            throw new IllegalArgumentException("Constructor has too many parameters to be optimizable");
        }
        final MethodVisitor visitMethod = build.visitMethod(1, "<init>", "(Ljava/lang/reflect/Constructor;)V", "(Ljava/lang/reflect/Constructor<*>;)V", null);
        visitMethod.visitCode();
        visitMethod.visitVarInsn(25, 0);
        visitMethod.visitVarInsn(25, 1);
        visitMethod.visitMethodInsn(183, MPLType.getInternalName(GeneratedConstructor.class), "<init>", "(Ljava/lang/reflect/Constructor;)V", false);
        visitMethod.visitInsn(177);
        visitMethod.visitMaxs(2, 2);
        visitMethod.visitEnd();
        final String str = "Ljava/lang/Object;";
        final StringBuilder sb = new StringBuilder(str.length() * (parameterTypes.length + 1));
        sb.append('(');
        for (int i = 0; i < parameterTypes.length; ++i) {
            sb.append(str);
        }
        sb.append(")Ljava/lang/Object;");
        final String string = sb.toString();
        final MethodVisitor visitMethod2 = build.visitMethod(129, "newInstanceVA", "([Ljava/lang/Object;)Ljava/lang/Object;", null, null);
        visitMethod2.visitCode();
        visitMethod2.visitVarInsn(25, 1);
        visitMethod2.visitInsn(190);
        final Label label = new Label();
        if (parameterTypes.length > 0) {
            visitMethod2.visitInsn(3 + parameterTypes.length);
            visitMethod2.visitJumpInsn(159, label);
        }
        else {
            visitMethod2.visitJumpInsn(153, label);
        }
        visitMethod2.visitVarInsn(25, 0);
        visitMethod2.visitVarInsn(25, 1);
        visitMethod2.visitInsn(190);
        visitMethod2.visitMethodInsn(182, MPLType.getInternalName(GeneratedConstructor.class), "failArgs", "(I)" + MPLType.getDescriptor(InvalidArgumentCountException.class), false);
        visitMethod2.visitInsn(191);
        visitMethod2.visitLabel(label);
        visitMethod2.visitFrame(3, 0, null, 0, null);
        visitMethod2.visitTypeInsn(187, internalName);
        visitMethod2.visitInsn(89);
        for (int j = 0; j < parameterTypes.length; ++j) {
            visitMethod2.visitVarInsn(25, 1);
            visitMethod2.visitInsn(3 + j);
            visitMethod2.visitInsn(50);
            ExtendedClassWriter.visitUnboxObjectVariable(visitMethod2, parameterTypes[j]);
        }
        ExtendedClassWriter.visitInit(visitMethod2, declaringClass, constructor);
        visitMethod2.visitInsn(176);
        visitMethod2.visitMaxs(5, 2);
        visitMethod2.visitEnd();
        final MethodVisitor visitMethod3 = build.visitMethod(1, "newInstance", string, null, null);
        visitMethod3.visitCode();
        visitMethod3.visitTypeInsn(187, internalName);
        visitMethod3.visitInsn(89);
        for (int k = 0; k < parameterTypes.length; ++k) {
            visitMethod3.visitVarInsn(25, 1 + k);
            ExtendedClassWriter.visitUnboxObjectVariable(visitMethod3, parameterTypes[k]);
        }
        ExtendedClassWriter.visitInit(visitMethod3, declaringClass, constructor);
        visitMethod3.visitInsn(176);
        visitMethod3.visitMaxs(3, 2);
        visitMethod3.visitEnd();
        return (GeneratedConstructor)build.generateInstance(new Class[] { java.lang.reflect.Constructor.class }, new Object[] { constructor });
    }
}
