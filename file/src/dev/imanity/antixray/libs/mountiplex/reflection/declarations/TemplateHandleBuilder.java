// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.mountiplex.reflection.declarations;

import java.lang.reflect.Field;
import java.util.Iterator;
import java.io.Serializable;
import dev.imanity.antixray.libs.asm.MethodVisitor;
import dev.imanity.antixray.libs.mountiplex.reflection.util.fast.Invoker;
import java.util.logging.Level;
import java.lang.reflect.Modifier;
import dev.imanity.antixray.libs.mountiplex.reflection.util.BoxedType;
import dev.imanity.antixray.libs.mountiplex.reflection.util.asm.MPLType;
import dev.imanity.antixray.libs.mountiplex.reflection.resolver.Resolver;
import dev.imanity.antixray.libs.mountiplex.reflection.util.ExtendedClassWriter;
import java.lang.reflect.Constructor;
import dev.imanity.antixray.libs.mountiplex.reflection.util.fast.GeneratedExactSignatureInvoker;
import dev.imanity.antixray.libs.mountiplex.MountiplexUtil;
import dev.imanity.antixray.libs.mountiplex.reflection.util.FastConstructor;

public class TemplateHandleBuilder<H extends Template.Handle>
{
    private final Template.Class<H> templateClass;
    private final Class<H> handleType;
    private Class<? extends H> handleImplType;
    private final FastConstructor<H> handleConstructor;
    
    public TemplateHandleBuilder(final Template.Class<H> templateClass) {
        this.handleConstructor = new FastConstructor<H>();
        this.templateClass = templateClass;
        this.handleType = templateClass.getHandleType();
        this.handleConstructor.initUnavailable("new " + this.handleType.getName() + "()");
    }
    
    public Class<? extends H> getImplType() {
        return this.handleImplType;
    }
    
    public H create(final Object o) {
        return this.handleConstructor.newInstance(o);
    }
    
    private Template.Class<?> getTemplateClass(final Class<?> clazz) {
        try {
            return (Template.Class)clazz.getField("T").get(null);
        }
        catch (final Throwable t) {
            throw MountiplexUtil.uncheckedRethrow(t);
        }
    }
    
    private static boolean isGeneratedInvoker(final Template.TemplateElement<?> templateElement) {
        return templateElement instanceof Template.AbstractMethod && ((Template.AbstractMethod)templateElement).invoker instanceof GeneratedExactSignatureInvoker;
    }
    
    public void build() {
        if (this.templateClass.getType() == null) {
            throw new IllegalStateException("Handle internal type of " + this.handleType + " is null");
        }
        if (this.handleType == Template.Handle.class) {
            this.handleImplType = (Class<? extends H>)FallbackHandle.class;
            try {
                Constructor<? extends H> constructor;
                try {
                    constructor = this.handleImplType.getConstructor(Object.class);
                }
                catch (final Throwable cause) {
                    throw new IllegalStateException("Failed to find generated handle constructor for fallback handle", cause);
                }
                this.handleConstructor.init(constructor);
            }
            catch (final Throwable t) {
                throw MountiplexUtil.uncheckedRethrow(t);
            }
            return;
        }
        final ExtendedClassWriter<Object> build = ExtendedClassWriter.builder((Class<? super Object>)this.handleType).setFlags(1).setAccess(16).setPostfix("$impl").build();
        Class<?> type = this.templateClass.getType();
        final boolean isPublic = Resolver.getMeta(type).isPublic;
        if (!isPublic) {
            type = Object.class;
        }
        final String descriptor = MPLType.getDescriptor(type);
        final String internalName = MPLType.getInternalName(type);
        build.visitField(17, "instance", descriptor, null, null).visitEnd();
        final MethodVisitor visitMethod = build.visitMethod(1, "<init>", "(" + descriptor + ")V", null, null);
        visitMethod.visitCode();
        visitMethod.visitVarInsn(25, 0);
        visitMethod.visitMethodInsn(183, MPLType.getInternalName(this.handleType), "<init>", "()V", false);
        visitMethod.visitVarInsn(25, 0);
        visitMethod.visitVarInsn(25, 1);
        visitMethod.visitFieldInsn(181, build.getInternalName(), "instance", descriptor);
        visitMethod.visitInsn(177);
        visitMethod.visitMaxs(2, 2);
        visitMethod.visitEnd();
        final MethodVisitor visitMethod2 = build.visitMethod(17, "getRaw", "()Ljava/lang/Object;", null, null);
        visitMethod2.visitCode();
        visitMethod2.visitVarInsn(25, 0);
        visitMethod2.visitFieldInsn(180, build.getInternalName(), "instance", descriptor);
        visitMethod2.visitInsn(176);
        visitMethod2.visitMaxs(1, 1);
        visitMethod2.visitEnd();
        Serializable s = this.handleType;
        Template.Class<?> class1 = this.templateClass;
        while (true) {
            final String internalName2 = MPLType.getInternalName((Class<?>)s);
            final Class selfClassType = class1.getSelfClassType();
            final String descriptor2 = MPLType.getDescriptor(selfClassType);
            final String internalName3 = MPLType.getInternalName(selfClassType);
            final Class type2 = class1.getType();
            final ClassDeclaration classDeclaration = class1.getClassDeclaration();
            if (classDeclaration == null) {
                throw new IllegalStateException("Template Handle Class " + class1 + " has no Class Declaration! Not initialized?");
            }
            for (final FieldDeclaration fieldDeclaration : classDeclaration.fields) {
                if (!fieldDeclaration.modifiers.isStatic() && !fieldDeclaration.modifiers.isUnknown() && !fieldDeclaration.modifiers.isOptional()) {
                    if (!fieldDeclaration.isEnum) {
                        final Class<?> type3 = fieldDeclaration.type.exposed().type;
                        final String descriptor3 = MPLType.getDescriptor(type3);
                        final String real = fieldDeclaration.name.real();
                        String internalName4;
                        String descriptor4;
                        try {
                            final Class<?> type4 = selfClassType.getField(real).getType();
                            internalName4 = MPLType.getInternalName(type4);
                            descriptor4 = MPLType.getDescriptor(type4);
                        }
                        catch (final Throwable t2) {
                            throw MountiplexUtil.uncheckedRethrow(t2);
                        }
                        String simpleName = "";
                        String s2 = "Ljava/lang/Object;";
                        for (final Class clazz : BoxedType.getBoxedTypes()) {
                            if (internalName4.endsWith(clazz.getSimpleName())) {
                                simpleName = clazz.getSimpleName();
                                s2 = descriptor3;
                                break;
                            }
                        }
                        final boolean b = isPublic && fieldDeclaration.type.cast == null && fieldDeclaration.field != null && Modifier.isPublic(fieldDeclaration.field.getModifiers());
                        final boolean b2 = b && !Modifier.isFinal(fieldDeclaration.field.getModifiers());
                        final MethodVisitor visitMethod3 = build.visitMethod(17, TemplateGenerator.getGetterName(fieldDeclaration), "()" + descriptor3, null, null);
                        visitMethod3.visitCode();
                        if (b) {
                            visitMethod3.visitVarInsn(25, 0);
                            visitMethod3.visitFieldInsn(180, build.getInternalName(), "instance", descriptor);
                            visitMethod3.visitFieldInsn(180, internalName, fieldDeclaration.getAccessedName(), descriptor3);
                        }
                        else {
                            visitMethod3.visitFieldInsn(178, internalName2, "T", descriptor2);
                            visitMethod3.visitFieldInsn(180, internalName3, real, descriptor4);
                            visitMethod3.visitVarInsn(25, 0);
                            visitMethod3.visitFieldInsn(180, build.getInternalName(), "instance", descriptor);
                            visitMethod3.visitMethodInsn(182, internalName4, "get" + simpleName, "(Ljava/lang/Object;)" + s2, false);
                            if (simpleName.isEmpty()) {
                                ExtendedClassWriter.visitUnboxObjectVariable(visitMethod3, type3);
                            }
                        }
                        visitMethod3.visitInsn(MPLType.getOpcode(type3, 172));
                        visitMethod3.visitMaxs(2, 1);
                        visitMethod3.visitEnd();
                        if (!fieldDeclaration.modifiers.isReadonly()) {
                            final MethodVisitor visitMethod4 = build.visitMethod(17, TemplateGenerator.getSetterName(fieldDeclaration), "(" + descriptor3 + ")V", null, null);
                            visitMethod4.visitCode();
                            if (b2) {
                                visitMethod4.visitVarInsn(25, 0);
                                visitMethod4.visitFieldInsn(180, build.getInternalName(), "instance", descriptor);
                                visitMethod4.visitVarInsn(MPLType.getOpcode(type3, 21), 1);
                                visitMethod4.visitFieldInsn(181, internalName, fieldDeclaration.getAccessedName(), descriptor3);
                            }
                            else {
                                visitMethod4.visitFieldInsn(178, internalName2, "T", descriptor2);
                                visitMethod4.visitFieldInsn(180, internalName3, real, descriptor4);
                                visitMethod4.visitVarInsn(25, 0);
                                visitMethod4.visitFieldInsn(180, build.getInternalName(), "instance", descriptor);
                                visitMethod4.visitVarInsn(MPLType.getOpcode(type3, 21), 1);
                                if (simpleName.isEmpty()) {
                                    MPLType.visitBoxVariable(visitMethod4, type3);
                                }
                                visitMethod4.visitMethodInsn(182, internalName4, "set" + simpleName, "(Ljava/lang/Object;" + s2 + ")V", false);
                            }
                            visitMethod4.visitInsn(177);
                            visitMethod4.visitMaxs(4, 3);
                            visitMethod4.visitEnd();
                        }
                    }
                }
            }
            for (final MethodDeclaration obj : classDeclaration.methods) {
                if (!obj.modifiers.isStatic() && !obj.modifiers.isUnknown()) {
                    if (!obj.modifiers.isOptional()) {
                        if (!obj.isResolved()) {
                            MountiplexUtil.LOGGER.log(Level.WARNING, "Method " + obj + " not implemented in handle for " + classDeclaration.type + " because some types are not resolved");
                        }
                        else {
                            final String real2 = obj.name.real();
                            boolean b3 = !obj.returnType.canDownCast();
                            final Class<?> type5 = obj.returnType.exposed().type;
                            final Class[] array = new Class[obj.parameters.parameters.length];
                            for (int k = 0; k < array.length; ++k) {
                                b3 |= !obj.parameters.parameters[k].type.canUpCast();
                                array[k] = obj.parameters.parameters[k].type.exposed().type;
                            }
                            final String methodDescriptor = MPLType.getMethodDescriptor(type5, array);
                            Template.TemplateElement templateElement;
                            String internalName5;
                            String descriptor5;
                            try {
                                final Field field = selfClassType.getField(real2);
                                templateElement = (Template.TemplateElement)field.get(class1);
                                internalName5 = MPLType.getInternalName(field.getType());
                                descriptor5 = MPLType.getDescriptor(field.getType());
                            }
                            catch (final Throwable t3) {
                                throw MountiplexUtil.uncheckedRethrow(t3);
                            }
                            final boolean b4 = isPublic && obj.method != null && Modifier.isPublic(obj.method.getModifiers()) && !b3;
                            final MethodVisitor visitMethod5 = build.visitMethod(1, real2, methodDescriptor, null, null);
                            visitMethod5.visitCode();
                            if (b4) {
                                visitMethod5.visitVarInsn(25, 0);
                                visitMethod5.visitFieldInsn(180, build.getInternalName(), "instance", descriptor);
                                MPLType.visitVarILoad(visitMethod5, 1, (Class<?>[])array);
                                ExtendedClassWriter.visitInvoke(visitMethod5, type2, obj.method);
                                visitMethod5.visitInsn(MPLType.getOpcode(type5, 172));
                            }
                            else if (isGeneratedInvoker(templateElement)) {
                                final GeneratedExactSignatureInvoker generatedExactSignatureInvoker = (GeneratedExactSignatureInvoker)((Template.AbstractMethod)templateElement).invoker;
                                final String invokerClassInternalName = generatedExactSignatureInvoker.getInvokerClassInternalName();
                                visitMethod5.visitFieldInsn(178, invokerClassInternalName, "INSTANCE", generatedExactSignatureInvoker.getInvokerClassTypeDescriptor());
                                visitMethod5.visitVarInsn(25, 0);
                                visitMethod5.visitFieldInsn(180, build.getInternalName(), "instance", descriptor);
                                MPLType.visitVarILoad(visitMethod5, 1, obj.parameters);
                                visitMethod5.visitMethodInsn(182, invokerClassInternalName, real2, obj.getASMInvokeDescriptor(), false);
                                visitMethod5.visitInsn(MPLType.getOpcode(type5, 172));
                            }
                            else {
                                visitMethod5.visitFieldInsn(178, internalName2, "T", descriptor2);
                                visitMethod5.visitFieldInsn(180, internalName3, real2, descriptor5);
                                final boolean b5 = templateElement instanceof Template.AbstractMethod;
                                if (b5) {
                                    visitMethod5.visitFieldInsn(180, internalName5, "invoker", MPLType.getDescriptor(Invoker.class));
                                }
                                visitMethod5.visitVarInsn(25, 0);
                                visitMethod5.visitFieldInsn(180, build.getInternalName(), "instance", descriptor);
                                if (array.length <= 5) {
                                    final StringBuilder sb = new StringBuilder();
                                    sb.append("(Ljava/lang/Object;");
                                    int visitVarILoadAndBox = 1;
                                    for (int l = 0; l < array.length; ++l) {
                                        visitVarILoadAndBox = MPLType.visitVarILoadAndBox(visitMethod5, visitVarILoadAndBox, array[l]);
                                        sb.append("Ljava/lang/Object;");
                                    }
                                    sb.append(")Ljava/lang/Object;");
                                    if (b5) {
                                        visitMethod5.visitMethodInsn(185, MPLType.getInternalName(Invoker.class), "invoke", sb.toString(), true);
                                    }
                                    else {
                                        visitMethod5.visitMethodInsn(182, internalName5, "invoke", sb.toString(), false);
                                    }
                                }
                                else {
                                    ExtendedClassWriter.visitPushInt(visitMethod5, array.length);
                                    visitMethod5.visitTypeInsn(189, "java/lang/Object");
                                    int visitVarILoadAndBox2 = 1;
                                    for (int n = 0; n < array.length; ++n) {
                                        visitMethod5.visitInsn(89);
                                        ExtendedClassWriter.visitPushInt(visitMethod5, n);
                                        visitVarILoadAndBox2 = MPLType.visitVarILoadAndBox(visitMethod5, visitVarILoadAndBox2, array[n]);
                                        visitMethod5.visitInsn(83);
                                    }
                                    if (b5) {
                                        visitMethod5.visitMethodInsn(185, MPLType.getInternalName(Invoker.class), "invokeVA", "(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;", true);
                                    }
                                    else {
                                        visitMethod5.visitMethodInsn(182, internalName5, "invokeVA", "(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;", false);
                                    }
                                }
                                if (type5.equals(Void.TYPE)) {
                                    visitMethod5.visitInsn(87);
                                    visitMethod5.visitInsn(177);
                                }
                                else if (type5.equals(Object.class)) {
                                    visitMethod5.visitInsn(176);
                                }
                                else {
                                    ExtendedClassWriter.visitUnboxObjectVariable(visitMethod5, type5);
                                    visitMethod5.visitInsn(MPLType.getOpcode(type5, 172));
                                }
                            }
                            visitMethod5.visitMaxs(3, 2);
                            visitMethod5.visitEnd();
                        }
                    }
                }
            }
            s = ((Class<Template.Handle>)s).getSuperclass();
            if (s == Template.Handle.class) {
                this.handleImplType = build.generate();
                try {
                    Constructor<? extends H> constructor2;
                    try {
                        constructor2 = this.handleImplType.getConstructor(type);
                    }
                    catch (final Throwable cause2) {
                        throw new IllegalStateException("Failed to find generated handle constructor of handle for " + MPLType.getName(type), cause2);
                    }
                    this.handleConstructor.init(constructor2);
                }
                catch (final Throwable t4) {
                    throw MountiplexUtil.uncheckedRethrow(t4);
                }
                return;
            }
            class1 = this.getTemplateClass((Class<?>)s);
        }
    }
    
    public static boolean isCreateHandleMethod(final MethodDeclaration methodDeclaration) {
        return methodDeclaration.modifiers.isStatic() && methodDeclaration.name.value().equals("createHandle") && methodDeclaration.parameters.parameters.length == 1 && methodDeclaration.parameters.parameters[0].type.isResolved() && methodDeclaration.parameters.parameters[0].type.type.equals(Object.class) && methodDeclaration.parameters.parameters[0].type.cast == null;
    }
    
    static final class FallbackHandle extends Template.Handle
    {
        private final Object instance;
        
        public FallbackHandle(final Object instance) {
            this.instance = instance;
        }
        
        @Override
        public Object getRaw() {
            return this.instance;
        }
    }
}
