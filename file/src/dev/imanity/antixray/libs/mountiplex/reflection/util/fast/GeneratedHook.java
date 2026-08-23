// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.mountiplex.reflection.util.fast;

import dev.imanity.antixray.libs.asm.Label;
import java.util.function.Supplier;
import java.lang.reflect.Executable;
import dev.imanity.antixray.libs.asm.MethodVisitor;
import java.util.function.Predicate;
import java.lang.reflect.Modifier;
import java.util.stream.Stream;
import dev.imanity.antixray.libs.mountiplex.reflection.ReflectionUtil;
import java.lang.reflect.Constructor;
import dev.imanity.antixray.libs.mountiplex.reflection.util.asm.MPLType;
import java.util.concurrent.atomic.AtomicInteger;
import dev.imanity.antixray.libs.mountiplex.reflection.util.ExtendedClassWriter;
import java.lang.reflect.Method;
import java.util.function.Function;
import java.util.Collection;

public class GeneratedHook
{
    public static <T> Class<? extends T> generate(final ClassLoader classLoader, final Class<T> clazz, final Collection<Class<?>> collection, final Function<Method, Invoker<?>> function) {
        final ExtendedClassWriter<Object> build = ExtendedClassWriter.builder((Class<? super Object>)clazz).setFlags(1).addInterfaces(collection).setClassLoader(classLoader).build();
        getSuperMethodPrefix(clazz);
        final AtomicInteger atomicInteger = new AtomicInteger(0);
        for (final Constructor constructor : clazz.getDeclaredConstructors()) {
            final String constructorDescriptor = MPLType.getConstructorDescriptor(constructor);
            final MethodVisitor visitMethod = build.visitMethod(constructor.getModifiers(), "<init>", constructorDescriptor, null, MPLType.getInternalNames(constructor.getExceptionTypes()));
            visitMethod.visitCode();
            visitMethod.visitVarInsn(25, 0);
            MPLType.visitVarILoad(visitMethod, 1, (Class<?>[])constructor.getParameterTypes());
            visitMethod.visitMethodInsn(183, MPLType.getInternalName(clazz), "<init>", constructorDescriptor, false);
            visitMethod.visitInsn(177);
            visitMethod.visitMaxs(0, 0);
            visitMethod.visitEnd();
        }
        Stream.concat((Stream<?>)ReflectionUtil.getAllClassesAndInterfaces(clazz), (Stream<?>)collection.stream()).flatMap((Function<? super Object, ? extends Stream<?>>)ReflectionUtil::getDeclaredMethods).filter(method -> {
            method.getModifiers();
            final int n;
            return !Modifier.isPrivate(n) && !Modifier.isStatic(n);
        }).filter((Predicate<? super Object>)ReflectionUtil.createDuplicateMethodFilter()).filter(method2 -> !Modifier.isFinal(method2.getModifiers())).forEachOrdered(method3 -> {
            final Invoker invoker = function2.apply(method3);
            if (invoker != null) {
                implement(extendedClassWriter, method3, invoker, s, atomicInteger2);
            }
            return;
        });
        return (Class<? extends T>)build.generate();
    }
    
    public static <T> Invoker<T> createSuperInvoker(final Class<?> clazz, final Method method) {
        final String string = getSuperMethodPrefix(clazz.getSuperclass()) + MPLType.getName(method);
        Method declaredMethod = method;
        try {
            declaredMethod = clazz.getDeclaredMethod(string, (Class[])method.getParameterTypes());
        }
        catch (final Throwable t) {}
        if (GeneratedInvoker.canCreate(declaredMethod)) {
            return (Invoker<T>)GeneratedInvoker.create(declaredMethod);
        }
        return (Invoker<T>)ReflectionInvoker.create(declaredMethod);
    }
    
    private static String getSuperMethodPrefix(final Class<?> clazz) {
        int i = 0;
        for (Class<?> superclass = clazz; superclass != null; superclass = superclass.getSuperclass()) {
            ++i;
        }
        return "mplsuper" + i + "_";
    }
    
    public static <T> Invoker<T> createLocalField(final Supplier<T> supplier) {
        return new LocalFieldInvoker<T>(supplier);
    }
    
    private static void implement(final ExtendedClassWriter<?> extendedClassWriter, final Method method, final Invoker<?> invoker, final String str, final AtomicInteger atomicInteger) {
        final MethodVisitor visitMethod = extendedClassWriter.visitMethod(17, str + MPLType.getName(method), MPLType.getMethodDescriptor(method), null, MPLType.getInternalNames(method.getExceptionTypes()));
        visitMethod.visitCode();
        if (Modifier.isAbstract(method.getModifiers())) {
            visitMethod.visitTypeInsn(187, "java/lang/UnsupportedOperationException");
            visitMethod.visitInsn(89);
            visitMethod.visitLdcInsn("Method is abstract");
            visitMethod.visitMethodInsn(183, "java/lang/UnsupportedOperationException", "<init>", "(Ljava/lang/String;)V", false);
            visitMethod.visitInsn(191);
        }
        else {
            visitMethod.visitVarInsn(25, 0);
            MPLType.visitVarILoad(visitMethod, 1, method.getParameterTypes());
            extendedClassWriter.visitCallSuperMethod(visitMethod, method);
            visitMethod.visitInsn(MPLType.getOpcode(method.getReturnType(), 172));
        }
        visitMethod.visitMaxs(0, 0);
        visitMethod.visitEnd();
        final String string = MPLType.getName(method) + "_" + atomicInteger.incrementAndGet();
        if (invoker instanceof LocalFieldInvoker) {
            final String descriptor = MPLType.getDescriptor(method.getReturnType());
            final String string2 = "supplier_" + string;
            extendedClassWriter.visitStaticField(string2, Supplier.class, ((LocalFieldInvoker)invoker).initialValue);
            final String string3 = "init_" + string;
            extendedClassWriter.visitField(1, string3, "Z", null, null).visitEnd();
            final String string4 = "value_" + string;
            extendedClassWriter.visitField(1, string4, descriptor, null, null).visitEnd();
            final MethodVisitor visitMethod2 = extendedClassWriter.visitMethod(1, MPLType.getName(method), MPLType.getMethodDescriptor(method), null, MPLType.getInternalNames(method.getExceptionTypes()));
            visitMethod2.visitCode();
            visitMethod2.visitVarInsn(25, 0);
            visitMethod2.visitFieldInsn(180, extendedClassWriter.getInternalName(), string3, "Z");
            final Label label = new Label();
            visitMethod2.visitJumpInsn(153, label);
            visitMethod2.visitVarInsn(25, 0);
            visitMethod2.visitFieldInsn(180, extendedClassWriter.getInternalName(), string4, descriptor);
            visitMethod2.visitInsn(MPLType.getOpcode(method.getReturnType(), 172));
            visitMethod2.visitLabel(label);
            visitMethod2.visitFrame(3, 0, null, 0, null);
            visitMethod2.visitFieldInsn(178, extendedClassWriter.getInternalName(), string2, MPLType.getDescriptor(Supplier.class));
            visitMethod2.visitMethodInsn(185, MPLType.getInternalName(Supplier.class), "get", "()Ljava/lang/Object;", true);
            ExtendedClassWriter.visitUnboxObjectVariable(visitMethod2, method.getReturnType());
            int size = 1;
            final Class<?>[] parameterTypes = method.getParameterTypes();
            for (int length = parameterTypes.length, i = 0; i < length; ++i) {
                size = MPLType.getType(parameterTypes[i]).getSize();
            }
            visitMethod2.visitVarInsn(58, size);
            visitMethod2.visitVarInsn(25, 0);
            visitMethod2.visitVarInsn(25, size);
            visitMethod2.visitFieldInsn(181, extendedClassWriter.getInternalName(), string4, descriptor);
            visitMethod2.visitVarInsn(25, 0);
            visitMethod2.visitInsn(4);
            visitMethod2.visitFieldInsn(181, extendedClassWriter.getInternalName(), string3, "Z");
            visitMethod2.visitVarInsn(25, size);
            visitMethod2.visitInsn(176);
            visitMethod2.visitMaxs(0, 0);
            visitMethod2.visitEnd();
            return;
        }
        final String string5 = "invoker_" + string;
        final Class<?>[] parameterTypes2 = method.getParameterTypes();
        extendedClassWriter.visitStaticField(string5, Invoker.class, invoker);
        final MethodVisitor visitMethod3 = extendedClassWriter.visitMethod(1, MPLType.getName(method), MPLType.getMethodDescriptor(method), null, MPLType.getInternalNames(method.getExceptionTypes()));
        visitMethod3.visitCode();
        visitMethod3.visitFieldInsn(178, extendedClassWriter.getInternalName(), string5, MPLType.getDescriptor(Invoker.class));
        visitMethod3.visitVarInsn(25, 0);
        if (parameterTypes2.length <= 5) {
            int visitVarILoadAndBox = 1;
            final Class<?>[] array = parameterTypes2;
            for (int length2 = array.length, j = 0; j < length2; ++j) {
                visitVarILoadAndBox = MPLType.visitVarILoadAndBox(visitMethod3, visitVarILoadAndBox, array[j]);
            }
            final StringBuilder sb = new StringBuilder();
            sb.append('(');
            for (int k = 0; k <= parameterTypes2.length; ++k) {
                sb.append("Ljava/lang/Object;");
            }
            sb.append(")Ljava/lang/Object;");
            visitMethod3.visitMethodInsn(185, MPLType.getInternalName(Invoker.class), "invoke", sb.toString(), true);
        }
        else {
            ExtendedClassWriter.visitPushInt(visitMethod3, parameterTypes2.length);
            visitMethod3.visitTypeInsn(189, "java/lang/Object");
            int visitVarILoadAndBox2 = 1;
            for (int l = 0; l < parameterTypes2.length; ++l) {
                visitMethod3.visitInsn(89);
                ExtendedClassWriter.visitPushInt(visitMethod3, l);
                visitVarILoadAndBox2 = MPLType.visitVarILoadAndBox(visitMethod3, visitVarILoadAndBox2, parameterTypes2[l]);
                visitMethod3.visitInsn(83);
            }
            visitMethod3.visitMethodInsn(185, MPLType.getInternalName(Invoker.class), "invokeVA", "(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;", true);
        }
        if (method.getReturnType() == Void.TYPE) {
            visitMethod3.visitInsn(87);
            visitMethod3.visitInsn(177);
        }
        else {
            ExtendedClassWriter.visitUnboxObjectVariable(visitMethod3, method.getReturnType());
            visitMethod3.visitInsn(MPLType.getOpcode(method.getReturnType(), 172));
        }
        visitMethod3.visitMaxs(0, 0);
        visitMethod3.visitEnd();
    }
    
    private static class LocalFieldInvoker<T> implements Invoker<T>
    {
        private final Supplier<T> initialValue;
        
        public LocalFieldInvoker(final Supplier<T> initialValue) {
            this.initialValue = initialValue;
        }
        
        @Override
        public T invokeVA(final Object o, final Object... array) {
            return this.initialValue.get();
        }
    }
}
