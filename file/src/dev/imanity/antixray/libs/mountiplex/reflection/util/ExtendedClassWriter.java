// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.mountiplex.reflection.util;

import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;
import java.io.Serializable;
import dev.imanity.antixray.libs.mountiplex.reflection.resolver.Resolver;
import java.util.function.Function;
import java.util.Collection;
import dev.imanity.antixray.libs.mountiplex.reflection.util.fast.InitInvoker;
import dev.imanity.antixray.libs.mountiplex.reflection.util.fast.Invoker;
import dev.imanity.antixray.libs.mountiplex.reflection.declarations.MethodDeclaration;
import java.lang.reflect.Modifier;
import java.lang.reflect.Method;
import java.lang.reflect.Constructor;
import dev.imanity.antixray.libs.javassist.CannotCompileException;
import java.security.ProtectionDomain;
import dev.imanity.antixray.libs.javassist.NotFoundException;
import dev.imanity.antixray.libs.mountiplex.reflection.util.asm.javassist.MPLJavac;
import dev.imanity.antixray.libs.javassist.ClassPool;
import dev.imanity.antixray.libs.asm.MethodVisitor;
import java.util.Iterator;
import dev.imanity.antixray.libs.mountiplex.reflection.util.asm.MPLType;
import java.util.Collections;
import java.util.ArrayList;
import dev.imanity.antixray.libs.javassist.CtClass;
import java.util.List;
import dev.imanity.antixray.libs.asm.ClassWriter;

public class ExtendedClassWriter<T> extends ClassWriter
{
    private static final UniqueHash generatedClassCtr;
    private static final UniqueHash generatedStaticFieldCtr;
    private final GeneratedClassName name;
    private final GeneratorClassLoader loader;
    private final Class<?> superType;
    private final Class<?>[] superInterfaces;
    private final List<StaticFieldInit> pendingStaticFields;
    private final boolean singleton;
    private final List<StaticFieldInit> singletonMemberFields;
    private CtClass ctClass;
    private List<JavassistAction> javassistActions;
    
    private ExtendedClassWriter(final GeneratorClassLoader loader, final Builder<T> builder) {
        super(((Builder<Object>)builder).flags);
        this.pendingStaticFields = new ArrayList<StaticFieldInit>();
        this.ctClass = null;
        this.javassistActions = Collections.emptyList();
        this.loader = loader;
        this.name = ((Builder<Object>)builder).exactName;
        this.superType = (((Builder<Object>)builder).superClass.isInterface() ? Object.class : ((Builder<Object>)builder).superClass);
        this.superInterfaces = ((Builder<Object>)builder).interfaces.toArray(new Class[0]);
        this.singleton = ((Builder<Object>)builder).singleton;
        this.singletonMemberFields = (((Builder<Object>)builder).singleton ? new ArrayList<StaticFieldInit>(5) : Collections.emptyList());
        String str = null;
        if (!((Builder<Object>)builder).interfaces.isEmpty()) {
            str = MPLType.getDescriptor(this.superType);
            final Iterator iterator = ((Builder<Object>)builder).interfaces.iterator();
            while (iterator.hasNext()) {
                str += MPLType.getDescriptor((Class<?>)iterator.next());
            }
        }
        final String internalName = MPLType.getInternalName(this.superType);
        String[] array = null;
        if (!((Builder<Object>)builder).interfaces.isEmpty()) {
            array = new String[((Builder<Object>)builder).interfaces.size()];
            for (int i = 0; i < array.length; ++i) {
                array[i] = MPLType.getInternalName((Class<?>)((Builder<Object>)builder).interfaces.get(i));
            }
        }
        this.visit(52, ((Builder<Object>)builder).access, this.name.internalName, str, internalName, array);
        if (this.singleton) {
            this.visitField(25, "INSTANCE", this.getTypeDescriptor(), null, null).visitEnd();
        }
    }
    
    public final String getName() {
        return this.name.name;
    }
    
    public final String getInternalName() {
        return this.name.internalName;
    }
    
    public final String getTypeDescriptor() {
        return this.name.typeDescriptor;
    }
    
    public final ClassLoader getClassLoader() {
        return this.loader;
    }
    
    private void closeASM() {
        if (this.singleton || !this.pendingStaticFields.isEmpty()) {
            final MethodVisitor visitMethod = this.visitMethod(8, "<clinit>", "()V", null, null);
            visitMethod.visitCode();
            if (this.singleton) {
                visitMethod.visitTypeInsn(187, this.getInternalName());
                visitMethod.visitInsn(89);
                visitMethod.visitMethodInsn(183, this.getInternalName(), "<init>", "()V", false);
                visitMethod.visitFieldInsn(179, this.getInternalName(), "INSTANCE", this.getTypeDescriptor());
            }
            for (final StaticFieldInit staticFieldInit : this.pendingStaticFields) {
                visitPushInt(visitMethod, staticFieldInit.record);
                visitMethod.visitMethodInsn(184, MPLType.getInternalName(GeneratorArgumentStore.class), "fetch", "(I)Ljava/lang/Object;", false);
                if (staticFieldInit.fieldType != Object.class) {
                    visitMethod.visitTypeInsn(192, MPLType.getInternalName(staticFieldInit.fieldType));
                }
                visitMethod.visitFieldInsn(179, this.getInternalName(), staticFieldInit.fieldName, MPLType.getDescriptor(staticFieldInit.fieldType));
            }
            visitMethod.visitInsn(177);
            visitMethod.visitMaxs(this.singleton ? 2 : 1, 0);
            visitMethod.visitEnd();
        }
        if (this.singleton) {
            final MethodVisitor visitMethod2 = this.visitMethod(2, "<init>", "()V", null, null);
            visitMethod2.visitCode();
            visitMethod2.visitVarInsn(25, 0);
            visitMethod2.visitMethodInsn(183, MPLType.getInternalName(this.superType), "<init>", "()V", false);
            for (final StaticFieldInit staticFieldInit2 : this.singletonMemberFields) {
                visitMethod2.visitVarInsn(25, 0);
                visitPushInt(visitMethod2, staticFieldInit2.record);
                visitMethod2.visitMethodInsn(184, MPLType.getInternalName(GeneratorArgumentStore.class), "fetch", "(I)Ljava/lang/Object;", false);
                if (staticFieldInit2.fieldType != Object.class) {
                    visitMethod2.visitTypeInsn(192, MPLType.getInternalName(staticFieldInit2.fieldType));
                }
                visitMethod2.visitFieldInsn(181, this.getInternalName(), staticFieldInit2.fieldName, MPLType.getDescriptor(staticFieldInit2.fieldType));
            }
            visitMethod2.visitInsn(177);
            visitMethod2.visitMaxs(this.singletonMemberFields.isEmpty() ? 1 : 2, 1);
            visitMethod2.visitEnd();
        }
        this.visitEnd();
    }
    
    public void addJavassist(final JavassistAction javassistAction) {
        if (this.ctClass != null) {
            javassistAction.run(this.ctClass);
        }
        else {
            if (this.javassistActions.isEmpty()) {
                this.javassistActions = new ArrayList<JavassistAction>(3);
            }
            this.javassistActions.add(javassistAction);
        }
    }
    
    public CtClass getCtClass() {
        return this.getCtClass(ClassPool.getDefault());
    }
    
    public CtClass getCtClass(final ClassPool classPool) {
        if (this.ctClass == null) {
            this.closeASM();
            CtClass ctClass;
            try {
                if (this.singleton) {
                    ctClass = MPLJavac.makeNewClass(classPool, this.toByteArray());
                }
                else {
                    ctClass = MPLJavac.makeNewClassWithDefaultConstructor(classPool, this.name.name, this.toByteArray());
                }
            }
            catch (final NotFoundException ex) {
                throw new RuntimeException("Failed to instantiate CtClass " + this.name.name + " from bytecode");
            }
            for (int i = 0; i < this.javassistActions.size(); ++i) {
                this.javassistActions.get(i).run(ctClass);
            }
            this.javassistActions = Collections.emptyList();
            this.ctClass = ctClass;
        }
        return this.ctClass;
    }
    
    public Class<T> generate() {
        try {
            if (this.ctClass == null && !this.javassistActions.isEmpty()) {
                this.getCtClass();
            }
            if (this.ctClass == null) {
                this.closeASM();
                return (Class<T>)this.loader.createClassFromBytecode(this.name.name, this.toByteArray(), null);
            }
            return (Class<T>)this.ctClass.toClass(this.loader, null);
        }
        catch (final CannotCompileException cause) {
            throw new RuntimeException("Failed to compile class " + this.name.name, cause);
        }
        catch (final NotFoundException cause2) {
            throw new RuntimeException("Compiling failed because a class was not found", cause2);
        }
    }
    
    public Constructor<T> generateConstructor(final Class<?>... parameterTypes) {
        if (this.singleton) {
            throw new UnsupportedOperationException("Cannot create new instances of a singleton class");
        }
        final Class<T> generate = this.generate();
        try {
            return generate.getConstructor(parameterTypes);
        }
        catch (final Throwable cause) {
            throw new RuntimeException("Failed to generate class", cause);
        }
    }
    
    public T generateInstance() {
        if (this.singleton) {
            final Class<T> generate = this.generate();
            try {
                return (T)MPLType.getDeclaredField(generate, "INSTANCE").get(null);
            }
            catch (final VerifyError cause) {
                throw new RuntimeException("Failed to load generated class " + this.name.name, cause);
            }
            catch (final Throwable cause2) {
                throw new IllegalStateException("INSTANCE not found in singleton. This should not happen!", cause2);
            }
        }
        return this.generateInstance(new Class[0], new Object[0]);
    }
    
    public T generateInstance(final Class<?>[] array, final Object[] initargs) {
        final Constructor<T> generateConstructor = this.generateConstructor(array);
        try {
            return generateConstructor.newInstance(initargs);
        }
        catch (final Throwable cause) {
            throw new RuntimeException("Failed to generate class", cause);
        }
    }
    
    public T generateInstanceNull() {
        return NullInstantiator.of(this.generate()).create();
    }
    
    public static String getNextPostfix() {
        return "$mplgen" + ExtendedClassWriter.generatedClassCtr.nextHex();
    }
    
    public static String getAvailableClassName(final String str) {
        String string = str;
        int i = 1;
        while (true) {
            try {
                MPLType.getClassByName(string);
                string = str + "_" + i;
            }
            catch (final ClassNotFoundException ex) {
                return string;
            }
            ++i;
        }
    }
    
    public static void visitPushChar(final MethodVisitor methodVisitor, final char c) {
        visitPushInt(methodVisitor, c);
    }
    
    public static void visitPushBoolean(final MethodVisitor methodVisitor, final boolean b) {
        methodVisitor.visitInsn(b ? 4 : 3);
    }
    
    public static void visitPushFloat(final MethodVisitor methodVisitor, final float f) {
        if (f == 0.0f) {
            methodVisitor.visitInsn(11);
        }
        else if (f == 1.0f) {
            methodVisitor.visitInsn(12);
        }
        else if (f == 2.0f) {
            methodVisitor.visitInsn(13);
        }
        else {
            methodVisitor.visitLdcInsn(f);
        }
    }
    
    public static void visitPushDouble(final MethodVisitor methodVisitor, final double d) {
        if (d == 0.0) {
            methodVisitor.visitInsn(14);
        }
        else if (d == 1.0) {
            methodVisitor.visitInsn(15);
        }
        else {
            methodVisitor.visitLdcInsn(d);
        }
    }
    
    public void visitPush(final MethodVisitor methodVisitor, final Class<?> clazz, Object defaultValue) {
        if (clazz == Void.TYPE) {
            return;
        }
        if (clazz.isPrimitive() && defaultValue == null) {
            defaultValue = BoxedType.getDefaultValue(clazz);
        }
        if (defaultValue == null) {
            methodVisitor.visitInsn(1);
        }
        else if (clazz == Byte.TYPE) {
            visitPushByte(methodVisitor, (byte)defaultValue);
        }
        else if (clazz == Short.TYPE) {
            visitPushShort(methodVisitor, (short)defaultValue);
        }
        else if (clazz == Integer.TYPE) {
            visitPushInt(methodVisitor, (int)defaultValue);
        }
        else if (clazz == Long.TYPE) {
            visitPushLong(methodVisitor, (long)defaultValue);
        }
        else if (clazz == Float.TYPE) {
            visitPushFloat(methodVisitor, (float)defaultValue);
        }
        else if (clazz == Double.TYPE) {
            visitPushDouble(methodVisitor, (double)defaultValue);
        }
        else if (clazz == Character.TYPE) {
            visitPushChar(methodVisitor, (char)defaultValue);
        }
        else if (clazz == Boolean.TYPE) {
            visitPushBoolean(methodVisitor, (boolean)defaultValue);
        }
        else if (clazz == String.class) {
            methodVisitor.visitLdcInsn(defaultValue);
        }
        else {
            final String string = "mplgen_cinit_field_" + ExtendedClassWriter.generatedStaticFieldCtr.nextHex();
            this.visitStaticField(string, clazz, defaultValue);
            methodVisitor.visitFieldInsn(178, this.name.internalName, string, MPLType.getDescriptor(clazz));
        }
    }
    
    public static void visitPushByte(final MethodVisitor methodVisitor, final byte b) {
        visitPushInt(methodVisitor, b);
    }
    
    public static void visitPushShort(final MethodVisitor methodVisitor, final short n) {
        visitPushInt(methodVisitor, n);
    }
    
    public static void visitPushInt(final MethodVisitor methodVisitor, final int i) {
        if (i >= 0 && i <= 5) {
            methodVisitor.visitInsn(3 + i);
        }
        else if (i >= -128 && i <= 127) {
            methodVisitor.visitIntInsn(16, i);
        }
        else if (i >= -32768 && i <= 32767) {
            methodVisitor.visitIntInsn(17, i);
        }
        else {
            methodVisitor.visitLdcInsn(i);
        }
    }
    
    public static void visitPushLong(final MethodVisitor methodVisitor, final long l) {
        if (l >= 0L && l <= 5L) {
            methodVisitor.visitInsn(9 + (int)l);
        }
        else {
            methodVisitor.visitLdcInsn(l);
        }
    }
    
    public static void visitUnboxObjectVariable(final MethodVisitor methodVisitor, final Class<?> obj) {
        if (obj.isPrimitive()) {
            final Class<?> boxedType = BoxedType.getBoxedType(obj);
            if (boxedType != null) {
                methodVisitor.visitTypeInsn(192, MPLType.getInternalName(boxedType));
                MPLType.visitUnboxVariable(methodVisitor, obj);
            }
        }
        else if (obj.isArray()) {
            methodVisitor.visitTypeInsn(192, MPLType.getDescriptor(obj));
        }
        else if (!Object.class.equals(obj)) {
            methodVisitor.visitTypeInsn(192, MPLType.getInternalName(obj));
        }
    }
    
    public static void visitInvoke(final MethodVisitor methodVisitor, final Class<?> clazz, final Method method) {
        final String internalName = MPLType.getInternalName(clazz);
        final boolean interface1 = clazz.isInterface();
        if (Modifier.isStatic(method.getModifiers())) {
            methodVisitor.visitMethodInsn(184, internalName, MPLType.getName(method), MPLType.getMethodDescriptor(method), interface1);
        }
        else if (clazz.isInterface()) {
            methodVisitor.visitMethodInsn(185, internalName, MPLType.getName(method), MPLType.getMethodDescriptor(method), interface1);
        }
        else {
            methodVisitor.visitMethodInsn(182, internalName, MPLType.getName(method), MPLType.getMethodDescriptor(method), interface1);
        }
    }
    
    public static void visitInit(final MethodVisitor methodVisitor, final Class<?> clazz, final Constructor<?> constructor) {
        methodVisitor.visitMethodInsn(183, MPLType.getInternalName(clazz), "<init>", MPLType.getConstructorDescriptor(constructor), false);
    }
    
    public void visitMethodUnsupported(final Method method, final String s) {
        final MethodVisitor visitMethod = this.visitMethod(1, MPLType.getName(method), MPLType.getMethodDescriptor(method), null, null);
        visitMethod.visitCode();
        visitMethod.visitTypeInsn(187, "java/lang/UnsupportedOperationException");
        visitMethod.visitInsn(89);
        visitMethod.visitLdcInsn(s);
        visitMethod.visitMethodInsn(183, "java/lang/UnsupportedOperationException", "<init>", "(Ljava/lang/String;)V", false);
        visitMethod.visitInsn(191);
        visitMethod.visitMaxs(3, 1 + method.getParameterCount());
        visitMethod.visitEnd();
    }
    
    public void visitMethodReturnConstant(final Method obj, final Object o) {
        final Class<?> returnType = obj.getReturnType();
        if (returnType != Void.TYPE) {
            if (returnType.isPrimitive()) {
                if (o == null) {
                    throw new IllegalArgumentException("Cannot return 'null' from method " + obj);
                }
                final Class<?> unboxedType = BoxedType.getUnboxedType(o.getClass());
                if (unboxedType != returnType) {
                    throw new IllegalArgumentException("Cannot return type " + unboxedType.getName() + " from method " + obj);
                }
            }
            else if (o != null && !returnType.isAssignableFrom(o.getClass())) {
                throw new IllegalArgumentException("Cannot return type " + o.getClass().getName() + " from method " + obj);
            }
        }
        int n = 1;
        final Class<?>[] parameterTypes = obj.getParameterTypes();
        for (int length = parameterTypes.length, i = 0; i < length; ++i) {
            n += MPLType.getType(parameterTypes[i]).getSize();
        }
        final MethodVisitor visitMethod = this.visitMethod(1, MPLType.getName(obj), MPLType.getMethodDescriptor(obj), null, null);
        visitMethod.visitCode();
        this.visitPush(visitMethod, returnType, o);
        visitMethod.visitInsn(MPLType.getOpcode(returnType, 172));
        visitMethod.visitMaxs(MPLType.getType(returnType).getSize(), n);
        visitMethod.visitEnd();
    }
    
    public void visitStaticInvokerField(final String s, final MethodDeclaration methodDeclaration) {
        this.visitStaticField(s, Invoker.class, InitInvoker.forMethodLate(this.getName(), s, methodDeclaration));
    }
    
    public void visitStaticField(final String s, final Class<?> clazz, final Object o) {
        final StaticFieldInit staticFieldInit = new StaticFieldInit(s, clazz, o);
        this.pendingStaticFields.add(staticFieldInit);
        this.visitField(25, s, MPLType.getDescriptor(staticFieldInit.fieldType), null, null).visitEnd();
    }
    
    public void visitSingletonField(final String s, final Class<?> clazz, final Object o) {
        if (this.singletonMemberFields == null) {
            throw new IllegalStateException("The Class being generated isn't a singleton. Missing setSingleton(true) in Builder?");
        }
        final StaticFieldInit staticFieldInit = new StaticFieldInit(s, clazz, o);
        this.singletonMemberFields.add(staticFieldInit);
        this.visitField(18, s, MPLType.getDescriptor(staticFieldInit.fieldType), null, null).visitEnd();
    }
    
    public void visitCallSuperMethod(final MethodVisitor methodVisitor, final Method obj) {
        final Class<?> declaringClass = obj.getDeclaringClass();
        Class<?> superType;
        if (declaringClass.isAssignableFrom(this.superType)) {
            superType = this.superType;
        }
        else {
            superType = null;
            for (final Class<?> clazz : this.superInterfaces) {
                if (declaringClass.isAssignableFrom(clazz)) {
                    superType = clazz;
                    break;
                }
            }
            if (superType == null) {
                throw new IllegalArgumentException("Method " + obj + " is not a superclass method");
            }
        }
        methodVisitor.visitMethodInsn(183, MPLType.getInternalName(superType), MPLType.getName(obj), MPLType.getMethodDescriptor(obj), false);
    }
    
    public static <T> Builder<T> builder(final Class<? super T> clazz) {
        return new Builder<T>((Class)clazz);
    }
    
    static {
        generatedClassCtr = new UniqueHash();
        generatedStaticFieldCtr = new UniqueHash();
    }
    
    public static final class Builder<T>
    {
        private final Class<T> superClass;
        private List<Class<?>> interfaces;
        private int flags;
        private int access;
        private boolean singleton;
        private String postfix;
        private GeneratedClassName exactName;
        private ClassLoader classLoader;
        
        private Builder(final Class<T> superClass) {
            this.interfaces = new ArrayList<Class<?>>(0);
            this.flags = 0;
            this.access = 9;
            this.singleton = false;
            this.postfix = null;
            this.exactName = null;
            this.classLoader = null;
            this.superClass = superClass;
            if (superClass.isInterface()) {
                this.interfaces.add(superClass);
            }
        }
        
        public Builder<T> addInterface(final Class<?> clazz) {
            this.interfaces.add(clazz);
            return this;
        }
        
        public Builder<T> addInterfaces(final Collection<Class<?>> collection) {
            this.interfaces.addAll(collection);
            return this;
        }
        
        public Builder<T> setFlags(final int n) {
            this.flags |= n;
            return this;
        }
        
        public Builder<T> setAccess(final int n) {
            this.access |= n;
            return this;
        }
        
        public Builder<T> setPostfix(final String postfix) {
            this.postfix = postfix;
            return this;
        }
        
        public Builder<T> setSingleton(final boolean singleton) {
            this.singleton = singleton;
            return this;
        }
        
        public Builder<T> setExactName(final String s) {
            this.exactName = new GeneratedClassName(s);
            return this;
        }
        
        public Builder<T> setClassLoader(final ClassLoader classLoader) {
            this.classLoader = classLoader;
            return this;
        }
        
        public <TO> ExtendedClassWriter<TO> build() {
            final GeneratorClassLoader initClassLoader = this.initClassLoader();
            this.computeExactName(initClassLoader);
            return new ExtendedClassWriter<TO>(initClassLoader, this, null);
        }
        
        public Deferred<T> defer(final Function<ExtendedClassWriter<T>, T> function) {
            final GeneratorClassLoader initClassLoader = this.initClassLoader();
            this.computeExactName(initClassLoader);
            return new Deferred<T>(initClassLoader, this, (Function)function);
        }
        
        private GeneratorClassLoader initClassLoader() {
            if (this.classLoader == null) {
                return GeneratorClassLoader.get(this.superClass.getClassLoader());
            }
            if (this.classLoader instanceof GeneratorClassLoader) {
                return (GeneratorClassLoader)this.classLoader;
            }
            return GeneratorClassLoader.get(this.classLoader);
        }
        
        private void computeExactName(final GeneratorClassLoader generatorClassLoader) {
            if (this.exactName != null) {
                return;
            }
            String string = (this.postfix != null) ? this.postfix : ("$mpldefgen" + ExtendedClassWriter.generatedClassCtr.nextHex());
            Serializable superClass = this.superClass;
            String str = MPLType.getName((Class<?>)superClass);
            if (str.startsWith("java.") || str.startsWith("sun.")) {
                superClass = ExtendedClassWriter.class;
                str = MPLType.getName((Class<?>)superClass);
            }
            final String str2 = string;
            int i = 1;
            while (true) {
                final String string2 = str + string;
                boolean b = false;
                try {
                    generatorClassLoader.loadClass(string2);
                    b = true;
                }
                catch (final ClassNotFoundException ex) {}
                try {
                    Resolver.getClassByExactName(string2);
                    b = true;
                }
                catch (final ClassNotFoundException ex2) {}
                if (!b) {
                    break;
                }
                string = str2 + "_" + i;
                ++i;
            }
            this.exactName = new GeneratedClassName((Class<?>)superClass, string);
        }
    }
    
    public static final class Deferred<T> implements LazyInitializedObject
    {
        private static final Map<String, Deferred<?>> pending;
        private final GeneratorClassLoader classLoader;
        private final Builder<T> builder;
        private final GeneratedClassName name;
        private final Function<ExtendedClassWriter<T>, T> callback;
        private T generated;
        private RuntimeException generateError;
        
        private Deferred(final GeneratorClassLoader classLoader, final Builder<T> builder, final Function<ExtendedClassWriter<T>, T> callback) {
            this.classLoader = classLoader;
            this.builder = builder;
            this.name = ((Builder<Object>)builder).exactName;
            this.callback = callback;
            this.generated = null;
            this.generateError = null;
            synchronized (Deferred.pending) {
                Deferred.pending.put(this.name.name, this);
            }
        }
        
        static Class<?> load(final String str) {
            final Deferred deferred = Deferred.pending.get(str);
            if (deferred == null) {
                return null;
            }
            try {
                return deferred.generate().getClass();
            }
            catch (final Throwable ex) {
                throw new ClassNotFoundException("Failed to generate class " + str, ex);
            }
        }
        
        public String getName() {
            return this.name.name;
        }
        
        public String getInternalName() {
            return this.name.internalName;
        }
        
        public String getTypeDescriptor() {
            return this.name.typeDescriptor;
        }
        
        public synchronized T generate() {
            final T generated = this.generated;
            if (generated != null) {
                return generated;
            }
            final RuntimeException generateError = this.generateError;
            if (generateError != null) {
                throw generateError;
            }
            try {
                final T apply = this.callback.apply(new ExtendedClassWriter<T>(this.classLoader, this.builder, null));
                if (apply == null) {
                    throw new IllegalStateException("Deferred callback " + this.callback.getClass().getName() + " returned null");
                }
                this.generated = apply;
                this.generateError = null;
                Deferred.pending.remove(this.name.name);
                return apply;
            }
            catch (final RuntimeException generateError2) {
                this.generated = null;
                throw this.generateError = generateError2;
            }
        }
        
        @Override
        public void forceInitialization() {
            this.generate();
        }
        
        static {
            pending = new ConcurrentHashMap<String, Deferred<?>>();
        }
    }
    
    private static final class GeneratedClassName
    {
        public final String name;
        public final String internalName;
        public final String typeDescriptor;
        
        public GeneratedClassName(final String name) {
            this.name = name;
            this.internalName = name.replace('.', '/');
            this.typeDescriptor = "L" + this.internalName + ";";
        }
        
        public GeneratedClassName(final Class<?> clazz, final String s) {
            this.name = MPLType.getName(clazz) + s;
            this.internalName = MPLType.getInternalName(clazz) + s;
            this.typeDescriptor = computeNameDescriptor(clazz, s);
        }
        
        private static String computeNameDescriptor(final Class<?> clazz, final String str) {
            final String descriptor = MPLType.getDescriptor(clazz);
            return descriptor.substring(0, descriptor.length() - 1) + str + ";";
        }
    }
    
    private static class StaticFieldInit
    {
        public final String fieldName;
        public final Class<?> fieldType;
        public final int record;
        
        public StaticFieldInit(final String fieldName, final Class<?> fieldType, final Object o) {
            this.fieldName = fieldName;
            this.fieldType = fieldType;
            this.record = GeneratorArgumentStore.store(o);
        }
    }
    
    @FunctionalInterface
    public interface JavassistAction
    {
        void run(final CtClass p0) throws NotFoundException, CannotCompileException;
    }
}
