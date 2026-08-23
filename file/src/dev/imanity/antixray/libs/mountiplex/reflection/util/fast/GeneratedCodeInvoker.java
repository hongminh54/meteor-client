// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.mountiplex.reflection.util.fast;

import dev.imanity.antixray.libs.asm.MethodVisitor;
import dev.imanity.antixray.libs.mountiplex.reflection.resolver.Resolver;
import java.util.logging.Level;
import dev.imanity.antixray.libs.javassist.CannotCompileException;
import dev.imanity.antixray.libs.mountiplex.reflection.util.asm.javassist.MPLCtNewMethod;
import dev.imanity.antixray.libs.javassist.CtMethod;
import dev.imanity.antixray.libs.mountiplex.reflection.declarations.ParameterDeclaration;
import dev.imanity.antixray.libs.javassist.CtClass;
import dev.imanity.antixray.libs.mountiplex.reflection.declarations.ClassResolver;
import dev.imanity.antixray.libs.mountiplex.reflection.declarations.Requirement;
import dev.imanity.antixray.libs.mountiplex.reflection.util.BoxedType;
import dev.imanity.antixray.libs.mountiplex.reflection.ReflectionUtil;
import dev.imanity.antixray.libs.javassist.ClassPool;
import dev.imanity.antixray.libs.mountiplex.MountiplexUtil;
import dev.imanity.antixray.libs.mountiplex.reflection.resolver.ResolvedClassPool;
import dev.imanity.antixray.libs.mountiplex.reflection.util.ExtendedClassWriter;
import dev.imanity.antixray.libs.mountiplex.reflection.declarations.MethodDeclaration;
import dev.imanity.antixray.libs.mountiplex.reflection.util.asm.MPLType;
import dev.imanity.antixray.libs.mountiplex.reflection.util.IgnoresRemapping;

public abstract class GeneratedCodeInvoker<T> implements IgnoresRemapping, GeneratedExactSignatureInvoker<T>
{
    @Override
    public String getInvokerClassInternalName() {
        return MPLType.getInternalName(this.getClass());
    }
    
    @Override
    public String getInvokerClassTypeDescriptor() {
        return MPLType.getDescriptor(this.getClass());
    }
    
    public static <T> GeneratedCodeInvoker<T> create(final MethodDeclaration methodDeclaration) {
        return generate(ExtendedClassWriter.builder((Class<? super Object>)GeneratedCodeInvoker.class).setFlags(1).setClassLoader(methodDeclaration.getResolver().getClassLoader()).setSingleton(true).build(), methodDeclaration);
    }
    
    public static <T> ExtendedClassWriter.Deferred<GeneratedCodeInvoker<T>> createDefer(final MethodDeclaration methodDeclaration) {
        return ExtendedClassWriter.builder((Class<? super GeneratedCodeInvoker<T>>)GeneratedCodeInvoker.class).setFlags(1).setClassLoader(methodDeclaration.getResolver().getClassLoader()).setSingleton(true).defer(extendedClassWriter2 -> generate((ExtendedClassWriter<GeneratedCodeInvoker<Object>>)extendedClassWriter2, methodDeclaration2));
    }
    
    private static <T> GeneratedCodeInvoker<T> generate(final ExtendedClassWriter<GeneratedCodeInvoker<T>> extendedClassWriter, final MethodDeclaration methodDeclaration) {
        if (!methodDeclaration.isResolved()) {
            throw new IllegalArgumentException("Declaration not resolved: " + methodDeclaration.toString());
        }
        methodDeclaration.checkTemplateErrors();
        try {
            final ResolvedClassPool create = ResolvedClassPool.create();
            try {
                final int length = methodDeclaration.parameters.parameters.length;
                create.setRemappings(methodDeclaration.getResolver().getRemappings());
                asmAddInvokeMethod(extendedClassWriter, methodDeclaration, true);
                if (length <= 5) {
                    asmAddInvokeMethod(extendedClassWriter, methodDeclaration, false);
                }
                for (final Requirement requirement : methodDeclaration.bodyRequirements) {
                    requirement.declaration.addAsRequirement(extendedClassWriter, requirement, requirement.name);
                }
                final ClassResolver resolver = methodDeclaration.getResolver();
                resolver.getAllImports().forEachOrdered(s -> {
                    if (s.endsWith(".*")) {
                        s.substring(0, s.length() - 2);
                        final String s2;
                        if (!s2.contains("*")) {
                            resolvedClassPool.importPackage(s2);
                        }
                    }
                    else {
                        resolvedClassPool.importPackage(s);
                    }
                    return;
                });
                if (resolver.hasPackage()) {
                    create.importPackage(resolver.getPackage());
                }
                else if (resolver.getDeclaredClass() != null) {
                    final String packagePathFromClassPath = MountiplexUtil.getPackagePathFromClassPath(methodDeclaration.getResolver().getDeclaredClassName());
                    if (!packagePathFromClassPath.isEmpty()) {
                        create.importPackage(packagePathFromClassPath);
                    }
                }
                final CtClass ctClass = extendedClassWriter.getCtClass(create);
                final StringBuilder sb = new StringBuilder();
                sb.setLength();
                sb.append("public ").append(ReflectionUtil.getAccessibleTypeName(methodDeclaration.returnType.type)).append(" ").append(methodDeclaration.name.real()).append("(");
                if (!methodDeclaration.modifiers.isStatic()) {
                    sb.append(ReflectionUtil.getAccessibleTypeName(methodDeclaration.getDeclaringClass())).append(" instance");
                    if (length > 0) {
                        sb.append(',');
                    }
                }
                for (int j = 0; j < length; ++j) {
                    final ParameterDeclaration parameterDeclaration = methodDeclaration.parameters.parameters[j];
                    sb.append(ReflectionUtil.getAccessibleTypeName(parameterDeclaration.type.type)).append(' ').append(parameterDeclaration.name.real());
                    if (j < length - 1) {
                        sb.append(',');
                    }
                }
                sb.append(") {\n");
                sb.append(methodDeclaration.body);
                if (methodDeclaration.returnType.type == Void.TYPE) {
                    sb.append("return;");
                }
                else {
                    sb.append("return ").append(BoxedType.getDefaultValue(methodDeclaration.returnType.type)).append(';');
                }
                sb.append('}');
                ctClass.addMethod(makeMethodAndLog(sb.toString(), ctClass));
                try {
                    final GeneratedCodeInvoker generatedCodeInvoker = extendedClassWriter.generateInstance();
                    if (create != null) {
                        create.close();
                    }
                    return generatedCodeInvoker;
                }
                catch (final VerifyError verifyError) {
                    MountiplexUtil.LOGGER.severe("Failed to verify generated method: " + methodDeclaration.body);
                    throw verifyError;
                }
            }
            catch (final Throwable t) {
                if (create != null) {
                    try {
                        create.close();
                    }
                    catch (final Throwable exception) {
                        t.addSuppressed(exception);
                    }
                }
                throw t;
            }
        }
        catch (final Throwable t2) {
            throw MountiplexUtil.uncheckedRethrow(t2);
        }
    }
    
    private static CtMethod makeMethodAndLog(final String s, final CtClass ctClass) {
        try {
            return MPLCtNewMethod.make(s, ctClass);
        }
        catch (final CannotCompileException ex) {
            MountiplexUtil.LOGGER.severe("Failed to compile method body (" + ex.getReason() + "):");
            MountiplexUtil.LOGGER.severe(s);
            throw MountiplexUtil.uncheckedRethrow(ex.getCause());
        }
        catch (final Throwable thrown) {
            MountiplexUtil.LOGGER.log(Level.SEVERE, "Failed to generate method body:", thrown);
            MountiplexUtil.LOGGER.severe(s);
            throw MountiplexUtil.uncheckedRethrow(thrown);
        }
    }
    
    private static boolean mustCastType(final Class<?> clazz) {
        return clazz != null && clazz != Object.class && Resolver.isPublic(clazz);
    }
    
    private static <T> void asmAddInvokeMethod(final ExtendedClassWriter<GeneratedCodeInvoker<T>> extendedClassWriter, final MethodDeclaration methodDeclaration, final boolean b) {
        final int length = methodDeclaration.parameters.parameters.length;
        final int n = b ? 3 : (2 + length);
        final Class<?> clazz = methodDeclaration.modifiers.isStatic() ? null : methodDeclaration.getDeclaringClass();
        final Class<?> type = methodDeclaration.returnType.type;
        MethodVisitor methodVisitor;
        if (b) {
            methodVisitor = extendedClassWriter.visitMethod(145, "invokeVA", "(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;", null, null);
            methodVisitor.visitCode();
            GeneratedInvoker.visitInvokeVAArgCountCheck(methodVisitor, length);
        }
        else {
            final StringBuilder sb = new StringBuilder();
            sb.append("(Ljava/lang/Object;");
            for (int i = 0; i < length; ++i) {
                sb.append("Ljava/lang/Object;");
            }
            sb.append(")Ljava/lang/Object;");
            methodVisitor = extendedClassWriter.visitMethod(17, "invoke", sb.toString(), null, null);
        }
        int j = 0;
        int visitVarIStore = n;
        while (j < length) {
            final ParameterDeclaration parameterDeclaration = methodDeclaration.parameters.parameters[j];
            if (mustCastType(parameterDeclaration.type.type)) {
                if (b) {
                    methodVisitor.visitVarInsn(25, 2);
                    ExtendedClassWriter.visitPushInt(methodVisitor, j);
                    methodVisitor.visitInsn(50);
                }
                else {
                    methodVisitor.visitVarInsn(25, 2 + j);
                }
                ExtendedClassWriter.visitUnboxObjectVariable(methodVisitor, parameterDeclaration.type.type);
                visitVarIStore = MPLType.visitVarIStore(methodVisitor, visitVarIStore, parameterDeclaration.type.type);
            }
            ++j;
        }
        final StringBuilder sb2 = new StringBuilder();
        methodVisitor.visitVarInsn(25, 0);
        sb2.append('(');
        if (clazz != null) {
            methodVisitor.visitVarInsn(25, 1);
            if (mustCastType(clazz)) {
                ExtendedClassWriter.visitUnboxObjectVariable(methodVisitor, clazz);
                sb2.append(MPLType.getDescriptor(clazz));
            }
            else {
                sb2.append("Ljava/lang/Object;");
            }
        }
        int k = 0;
        int visitVarILoad = n;
        while (k < length) {
            final ParameterDeclaration parameterDeclaration2 = methodDeclaration.parameters.parameters[k];
            if (mustCastType(parameterDeclaration2.type.type)) {
                sb2.append(MPLType.getDescriptor(parameterDeclaration2.type.type));
                visitVarILoad = MPLType.visitVarILoad(methodVisitor, visitVarILoad, parameterDeclaration2.type.type);
            }
            else if (b) {
                sb2.append("Ljava/lang/Object;");
                methodVisitor.visitVarInsn(25, 2);
                ExtendedClassWriter.visitPushInt(methodVisitor, k);
                methodVisitor.visitInsn(50);
            }
            else {
                sb2.append("Ljava/lang/Object;");
                methodVisitor.visitVarInsn(25, 2 + k);
            }
            ++k;
        }
        sb2.append(')');
        if (mustCastType(type)) {
            sb2.append(MPLType.getDescriptor(type));
        }
        else {
            sb2.append("Ljava/lang/Object;");
        }
        methodVisitor.visitMethodInsn(182, extendedClassWriter.getInternalName(), methodDeclaration.name.real(), sb2.toString(), false);
        MPLType.visitBoxVariable(methodVisitor, type);
        methodVisitor.visitInsn(176);
        methodVisitor.visitMaxs(0, 0);
        methodVisitor.visitEnd();
    }
}
