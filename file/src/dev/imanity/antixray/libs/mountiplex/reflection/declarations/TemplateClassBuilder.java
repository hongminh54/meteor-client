// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.mountiplex.reflection.declarations;

import java.util.Iterator;
import java.lang.reflect.Method;
import dev.imanity.antixray.libs.asm.MethodVisitor;
import dev.imanity.antixray.libs.mountiplex.reflection.util.fast.Invoker;
import java.util.Collection;
import dev.imanity.antixray.libs.mountiplex.reflection.util.asm.MPLType;
import dev.imanity.antixray.libs.mountiplex.reflection.util.ExtendedClassWriter;
import java.lang.reflect.Modifier;
import java.util.logging.Level;
import dev.imanity.antixray.libs.mountiplex.MountiplexUtil;
import dev.imanity.antixray.libs.mountiplex.reflection.resolver.Resolver;
import java.util.Collections;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.function.Function;
import java.util.stream.Stream;
import dev.imanity.antixray.libs.mountiplex.reflection.ReflectionUtil;
import java.util.List;
import dev.imanity.antixray.libs.mountiplex.reflection.resolver.ClassDeclarationResolver;

public class TemplateClassBuilder<C extends Template.Class<H>, H extends Template.Handle>
{
    public final ClassDeclarationResolver classDeclarationResolver;
    public final Class<C> classType;
    public final Class<H> handleType;
    public final Class<?> instanceType;
    public final String instanceClassPath;
    public final String classPackage;
    public final List<String> classImports;
    public final boolean isOptional;
    public final ClassDeclaration classDec;
    
    public TemplateClassBuilder(final Class<C> classType, final ClassDeclarationResolver classDeclarationResolver) {
        this.classDeclarationResolver = classDeclarationResolver;
        this.classType = classType;
        final Class<?> declaringClass = this.classType.getDeclaringClass();
        if (declaringClass != null && Template.Handle.class.isAssignableFrom(declaringClass)) {
            this.handleType = (Class<H>)declaringClass;
        }
        else {
            this.handleType = (Class<H>)Template.Handle.class;
        }
        this.instanceClassPath = ReflectionUtil.recurseFindAnnotationValue(classType, Template.InstanceType.class, Template.InstanceType::value, "java.lang.Object");
        this.isOptional = ReflectionUtil.recurseFindAnnotationValue((Class<?>)classType, Template.Optional.class, p0 -> Boolean.TRUE, Boolean.FALSE);
        this.classPackage = ReflectionUtil.recurseFindAnnotationValue(classType, Template.Package.class, Template.Package::value, null);
        Collections.reverse(this.classImports = ReflectionUtil.getAllDeclaringClasses(classType).flatMap(clazz -> Stream.of((Object[])clazz.getAnnotationsByType(Template.Import.class))).map((Function<? super Object, ?>)Template.Import::value).collect((Collector<? super Object, ?, List<String>>)Collectors.toList()));
        this.instanceType = Resolver.loadClass(this.instanceClassPath, false);
        if (this.instanceType == null && !this.isOptional) {
            MountiplexUtil.LOGGER.log(Level.SEVERE, "Class " + this.instanceClassPath + " not found; Template '" + declaringClass.getSimpleName() + " not initialized.");
        }
        if (this.instanceType != null && this.instanceType != Object.class && classDeclarationResolver != null) {
            this.classDec = classDeclarationResolver.resolveClassDeclaration(this.instanceClassPath, this.instanceType);
        }
        else {
            this.classDec = null;
        }
    }
    
    public C build() {
        if (Modifier.isAbstract(this.classType.getModifiers())) {
            return this.buildExtend();
        }
        return this.buildDefault();
    }
    
    private C buildDefault() {
        try {
            final Template.Class<H> class1 = this.classType.newInstance();
            class1.init(this);
            return (C)class1;
        }
        catch (final Throwable t) {
            throw MountiplexUtil.uncheckedRethrow(t);
        }
    }
    
    private C buildExtend() {
        final ExtendedClassWriter<Object> build = ExtendedClassWriter.builder((Class<? super Object>)this.classType).setFlags(1).setAccess(16).setClassLoader(this.classType.getClassLoader()).setPostfix("$impl").build();
        final MethodVisitor visitMethod = build.visitMethod(1, "<init>", "()V", null, null);
        visitMethod.visitCode();
        visitMethod.visitVarInsn(25, 0);
        visitMethod.visitMethodInsn(183, MPLType.getInternalName(this.classType), "<init>", "()V", false);
        visitMethod.visitInsn(177);
        visitMethod.visitMaxs(1, 1);
        visitMethod.visitEnd();
        final MethodVisitor visitMethod2 = build.visitMethod(1, "getSelfClassType", "()Ljava/lang/Class;", "()Ljava/lang/Class<*>;", null);
        visitMethod2.visitCode();
        visitMethod2.visitVarInsn(25, 0);
        visitMethod2.visitMethodInsn(182, "java/lang/Object", "getClass", "()Ljava/lang/Class;", false);
        visitMethod2.visitMethodInsn(182, "java/lang/Class", "getSuperclass", "()Ljava/lang/Class;", false);
        visitMethod2.visitInsn(176);
        visitMethod2.visitMaxs(1, 1);
        visitMethod2.visitEnd();
        int n = 1;
        ClassResolver classResolver = null;
        for (final Method method : this.classType.getDeclaredMethods()) {
            final Template.Generated generated = method.getAnnotation(Template.Generated.class);
            if (generated != null) {
                if (classResolver == null) {
                    classResolver = new ClassResolver();
                    classResolver.setClassLoader(build.getClassLoader());
                    classResolver.setDeclaredClass(this.instanceType, this.instanceClassPath);
                    if (this.classDeclarationResolver != null) {
                        classResolver.setAllVariables(this.classDeclarationResolver);
                    }
                    else {
                        classResolver.setAllVariables(Resolver.resolveClassVariables(this.instanceClassPath, this.instanceType));
                    }
                    if (this.classPackage != null) {
                        classResolver.setPackage(this.classPackage, false);
                    }
                    classResolver.addImports(this.classImports);
                    final StringBuilder sb = new StringBuilder();
                    for (final Template.Require require : this.classType.getAnnotationsByType(Template.Require.class)) {
                        final String trim = SourceDeclaration.preprocess(require.value(), classResolver).trim();
                        if (!trim.isEmpty()) {
                            if (!require.declaring().isEmpty()) {
                                sb.append("#require ");
                                sb.append(require.declaring()).append(' ');
                            }
                            else if (!trim.startsWith("#")) {
                                sb.append("#require ");
                            }
                            sb.append(trim).append('\n');
                        }
                    }
                    if (sb.length() > 0) {
                        sb.insert(0, "class DummyClassPleaseIgnore {\n");
                        sb.append("}");
                        final Iterator<Requirement> iterator = new ClassDeclaration(classResolver, sb.toString()).getResolver().getRequirements().iterator();
                        while (iterator.hasNext()) {
                            classResolver.storeRequirement(iterator.next());
                        }
                    }
                }
                final String preprocess = SourceDeclaration.preprocess(generated.value(), classResolver);
                final Declaration declaration = Declaration.parseDeclaration(classResolver, preprocess);
                if (declaration == null || !declaration.isValid()) {
                    MountiplexUtil.LOGGER.warning("Declaration for method " + MPLType.getName(method) + " could not be parsed: " + preprocess);
                    build.visitMethodUnsupported(method, "Declaration for this generated method could not be parsed");
                }
                else if (!declaration.isResolved()) {
                    MountiplexUtil.LOGGER.warning("Declaration for method " + MPLType.getName(method) + " could not be resolved: " + declaration);
                    build.visitMethodUnsupported(method, "Declaration for this generated method could not be resolved (missing types)");
                }
                else {
                    final Declaration discover = declaration.discover();
                    if (discover == null) {
                        build.visitMethodUnsupported(method, "Failed to find: " + declaration);
                    }
                    else if (discover instanceof FieldDeclaration) {
                        build.visitMethodUnsupported(method, "Field getters/setters not implemented yet");
                    }
                    else if (discover instanceof MethodDeclaration) {
                        final MethodDeclaration methodDeclaration = (MethodDeclaration)discover;
                        if (!methodDeclaration.modifiers.isStatic() && methodDeclaration.constructor == null) {
                            build.visitMethodUnsupported(method, "Local methods cannot be called statically");
                        }
                        else if (methodDeclaration.body == null && methodDeclaration.method == null && methodDeclaration.constructor == null) {
                            build.visitMethodUnsupported(method, "Static method '" + methodDeclaration.name.toString() + "' was not found");
                        }
                        else {
                            boolean b = false;
                            if (methodDeclaration.returnType.cast != null && !methodDeclaration.returnType.cast.isAssignableFrom(methodDeclaration.returnType) && !methodDeclaration.returnType.isAssignableFrom(methodDeclaration.returnType.cast)) {
                                b = true;
                            }
                            else {
                                for (final ParameterDeclaration parameterDeclaration : methodDeclaration.parameters.parameters) {
                                    if (parameterDeclaration.type.cast != null && !parameterDeclaration.type.isAssignableFrom(parameterDeclaration.type.cast) && !parameterDeclaration.type.cast.isAssignableFrom(parameterDeclaration.type)) {
                                        b = true;
                                        break;
                                    }
                                }
                            }
                            if (b) {
                                build.visitMethodUnsupported(method, "Conversion of parameters/return type is not supported yet");
                            }
                            else {
                                final boolean b2 = (methodDeclaration.method != null && Modifier.isPublic(methodDeclaration.method.getModifiers())) || (methodDeclaration.constructor != null && Modifier.isPublic(methodDeclaration.constructor.getModifiers()));
                                if (methodDeclaration.body == null && b2 && Resolver.isPublic(this.instanceType)) {
                                    final MethodVisitor visitMethod3 = build.visitMethod(1, MPLType.getName(method), MPLType.getMethodDescriptor(method), null, null);
                                    visitMethod3.visitCode();
                                    if (methodDeclaration.constructor != null) {
                                        visitMethod3.visitTypeInsn(187, MPLType.getInternalName(methodDeclaration.constructor.getDeclaringClass()));
                                        visitMethod3.visitInsn(89);
                                    }
                                    int visitVarILoad = 1;
                                    for (final ParameterDeclaration parameterDeclaration2 : methodDeclaration.parameters.parameters) {
                                        visitVarILoad = MPLType.visitVarILoad(visitMethod3, visitVarILoad, parameterDeclaration2.type.exposed().type);
                                        if (parameterDeclaration2.type.cast != null) {
                                            ExtendedClassWriter.visitUnboxObjectVariable(visitMethod3, parameterDeclaration2.type.type);
                                        }
                                    }
                                    if (methodDeclaration.constructor != null) {
                                        visitMethod3.visitMethodInsn(183, MPLType.getInternalName(this.instanceType), "<init>", MPLType.getInternalMethodDescriptor(methodDeclaration), false);
                                    }
                                    else {
                                        visitMethod3.visitMethodInsn(184, MPLType.getInternalName(this.instanceType), methodDeclaration.name.value(), MPLType.getInternalMethodDescriptor(methodDeclaration), false);
                                    }
                                    if (methodDeclaration.returnType.cast != null) {
                                        ExtendedClassWriter.visitUnboxObjectVariable(visitMethod3, methodDeclaration.returnType.cast.type);
                                    }
                                    visitMethod3.visitInsn(MPLType.getOpcode(method.getReturnType(), 172));
                                    if (methodDeclaration.constructor != null) {
                                        visitMethod3.visitMaxs(visitVarILoad + 1, visitVarILoad);
                                    }
                                    else {
                                        visitMethod3.visitMaxs(visitVarILoad, visitVarILoad);
                                    }
                                    visitMethod3.visitEnd();
                                }
                                else {
                                    String s = methodDeclaration.name.real() + "_invoker_" + n++;
                                    if (s.startsWith("<init>_")) {
                                        s = "initializer_" + s.substring(7);
                                    }
                                    build.visitStaticInvokerField(s, methodDeclaration);
                                    final MethodVisitor visitMethod4 = build.visitMethod(1, MPLType.getName(method), MPLType.getMethodDescriptor(method), null, null);
                                    visitMethod4.visitCode();
                                    visitMethod4.visitFieldInsn(178, build.getInternalName(), s, MPLType.getDescriptor(Invoker.class));
                                    visitMethod4.visitInsn(1);
                                    if (methodDeclaration.parameters.parameters.length > 5) {
                                        ExtendedClassWriter.visitPushInt(visitMethod4, methodDeclaration.parameters.parameters.length);
                                        visitMethod4.visitTypeInsn(189, "java/lang/Object");
                                        int visitVarILoadAndBox = 1;
                                        for (final Class<?> clazz : method.getParameterTypes()) {
                                            visitMethod4.visitInsn(89);
                                            ExtendedClassWriter.visitPushInt(visitMethod4, visitVarILoadAndBox - 1);
                                            visitVarILoadAndBox = MPLType.visitVarILoadAndBox(visitMethod4, visitVarILoadAndBox, clazz);
                                            visitMethod4.visitInsn(83);
                                        }
                                        visitMethod4.visitMethodInsn(185, MPLType.getInternalName(Invoker.class), "invokeVA", "(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;", true);
                                    }
                                    else {
                                        int visitVarILoadAndBox2 = 1;
                                        final Class<?>[] parameterTypes2 = method.getParameterTypes();
                                        for (int length6 = parameterTypes2.length, n3 = 0; n3 < length6; ++n3) {
                                            visitVarILoadAndBox2 = MPLType.visitVarILoadAndBox(visitMethod4, visitVarILoadAndBox2, parameterTypes2[n3]);
                                        }
                                        final StringBuilder sb2 = new StringBuilder();
                                        sb2.append('(');
                                        for (int n4 = 0; n4 <= methodDeclaration.parameters.parameters.length; ++n4) {
                                            sb2.append("Ljava/lang/Object;");
                                        }
                                        sb2.append(")Ljava/lang/Object;");
                                        visitMethod4.visitMethodInsn(185, MPLType.getInternalName(Invoker.class), "invoke", sb2.toString(), true);
                                    }
                                    if (method.getReturnType() == Void.TYPE) {
                                        visitMethod4.visitInsn(87);
                                        visitMethod4.visitInsn(177);
                                    }
                                    else {
                                        ExtendedClassWriter.visitUnboxObjectVariable(visitMethod4, method.getReturnType());
                                        visitMethod4.visitInsn(MPLType.getOpcode(method.getReturnType(), 172));
                                    }
                                    visitMethod4.visitMaxs(0, 0);
                                    visitMethod4.visitEnd();
                                }
                            }
                        }
                    }
                    else {
                        build.visitMethodUnsupported(method, "Not supported yet");
                    }
                }
            }
        }
        final Template.Class<H> class1 = build.generateInstance(new Class[0], new Object[0]);
        class1.init(this);
        return (C)class1;
    }
}
