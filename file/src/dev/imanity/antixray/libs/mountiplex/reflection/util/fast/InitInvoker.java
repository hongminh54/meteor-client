// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.mountiplex.reflection.util.fast;

import java.lang.reflect.Modifier;
import dev.imanity.antixray.libs.mountiplex.reflection.util.asm.MPLType;
import dev.imanity.antixray.libs.mountiplex.MountiplexUtil;
import java.lang.reflect.Field;
import dev.imanity.antixray.libs.mountiplex.reflection.util.ExtendedClassWriter;
import dev.imanity.antixray.libs.mountiplex.reflection.declarations.ParameterDeclaration;
import java.util.List;
import java.util.ArrayList;
import java.lang.reflect.Executable;
import dev.imanity.antixray.libs.mountiplex.reflection.declarations.MethodDeclaration;
import dev.imanity.antixray.libs.mountiplex.reflection.declarations.ClassResolver;
import java.lang.reflect.Method;
import dev.imanity.antixray.libs.mountiplex.reflection.IgnoredFieldAccessor;
import dev.imanity.antixray.libs.mountiplex.reflection.FieldAccessor;
import dev.imanity.antixray.libs.mountiplex.reflection.util.LazyInitializedObject;

public abstract class InitInvoker<T> implements LazyInitializedObject, Invoker<T>
{
    private static final InitInvoker<?> defaultUnavailableMethod;
    private final FieldAccessor<Invoker<T>> fieldAccessor;
    private final Object fieldInstance;
    
    protected InitInvoker() {
        this.fieldInstance = null;
        this.fieldAccessor = new IgnoredFieldAccessor<Invoker<T>>(this);
    }
    
    public <I> InitInvoker(final I fieldInstance, final FieldAccessor<Invoker<T>> fieldAccessor) {
        this.fieldInstance = fieldInstance;
        this.fieldAccessor = fieldAccessor;
    }
    
    protected abstract Invoker<T> create();
    
    @Override
    public final Invoker<T> initializeInvoker() {
        Invoker<T> create = this.fieldAccessor.get(this.fieldInstance);
        if (create == this) {
            synchronized (this) {
                create = this.fieldAccessor.get(this.fieldInstance);
                if (create == this) {
                    create = this.create();
                    this.fieldAccessor.set(this.fieldInstance, create);
                }
            }
        }
        return create;
    }
    
    @Override
    public final T invoke(final Object o) {
        return this.initializeInvoker().invoke(o);
    }
    
    @Override
    public final T invoke(final Object o, final Object o2) {
        return this.initializeInvoker().invoke(o, o2);
    }
    
    @Override
    public final T invoke(final Object o, final Object o2, final Object o3) {
        return this.initializeInvoker().invoke(o, o2, o3);
    }
    
    @Override
    public final T invoke(final Object o, final Object o2, final Object o3, final Object o4) {
        return this.initializeInvoker().invoke(o, o2, o3, o4);
    }
    
    @Override
    public final T invoke(final Object o, final Object o2, final Object o3, final Object o4, final Object o5) {
        return this.initializeInvoker().invoke(o, o2, o3, o4, o5);
    }
    
    @Override
    public final T invoke(final Object o, final Object o2, final Object o3, final Object o4, final Object o5, final Object o6) {
        return this.initializeInvoker().invoke(o, o2, o3, o4, o5, o6);
    }
    
    @Override
    public final T invokeVA(final Object o, final Object... array) {
        return this.initializeInvoker().invokeVA(o, array);
    }
    
    public static <T> InitInvoker<T> unavailable(final String s, final String s2) {
        return new UnavailableInvoker<T>(s, s2);
    }
    
    public static <T> InitInvoker<T> unavailableMethod() {
        return (InitInvoker<T>)InitInvoker.defaultUnavailableMethod;
    }
    
    public static <T> InitInvoker<T> forMethod(final ClassLoader classLoader, final Object o, final String s, final Method method) {
        final ClassResolver classResolver = new ClassResolver();
        classResolver.setClassLoader(classLoader);
        return (InitInvoker<T>)forMethod(o, s, new MethodDeclaration(classResolver, method));
    }
    
    public static <T> InitInvoker<T> forMethod(final Object o, final String s, final MethodDeclaration methodDeclaration) {
        return forMethod(o, (FieldAccessor<Invoker<T>>)new ReflectionFieldAccessor<T>(o.getClass(), s), methodDeclaration);
    }
    
    public static <T> InitInvoker<T> forMethodLate(final String s, final String s2, final MethodDeclaration methodDeclaration) {
        return forMethod(null, (FieldAccessor<Invoker<T>>)new ReflectionFieldAccessorLate<T>((methodDeclaration == null) ? InitInvoker.class.getClassLoader() : methodDeclaration.getResolver().getClassLoader(), s, s2), methodDeclaration);
    }
    
    public static <T> InitInvoker<T> forMethod(final Object o, final FieldAccessor<Invoker<T>> fieldAccessor, final MethodDeclaration methodDeclaration) {
        if (methodDeclaration == null) {
            return unavailableMethod();
        }
        if (methodDeclaration.body != null) {
            return (InitInvoker<T>)InitGeneratedCodeInvoker.create(o, fieldAccessor, methodDeclaration);
        }
        if (methodDeclaration.method != null) {
            return new InitGeneratedExecutableInvoker<T>(o, (FieldAccessor<Invoker<Object>>)fieldAccessor, methodDeclaration.method);
        }
        if (methodDeclaration.constructor != null) {
            return new InitGeneratedExecutableInvoker<T>(o, (FieldAccessor<Invoker<Object>>)fieldAccessor, methodDeclaration.constructor);
        }
        if (methodDeclaration.isRecordFieldChanger) {
            final String s = methodDeclaration.name.hasAlias() ? methodDeclaration.name.alias() : "change";
            final ArrayList list = new ArrayList(methodDeclaration.parameters.parameters.length);
            final ParameterDeclaration[] parameters = methodDeclaration.parameters.parameters;
            for (int length = parameters.length, i = 0; i < length; ++i) {
                list.add(parameters[i].name.value());
            }
            return new InitGeneratedRecordFieldChangerInvoker<T>(o, (FieldAccessor<Invoker<Object>>)fieldAccessor, methodDeclaration.getDeclaringClass(), s, list);
        }
        return unavailable("method", methodDeclaration.toString());
    }
    
    public static <T> InitInvoker<T> proxy(final Object o, final String s, final Invoker<T> invoker) {
        return proxy(o, new ReflectionFieldAccessor<T>(o.getClass(), s), invoker);
    }
    
    public static <T> InitInvoker<T> proxy(final Object o, final FieldAccessor<Invoker<T>> fieldAccessor, final Invoker<T> invoker) {
        return new ProxyInvoker<T>(o, fieldAccessor, invoker);
    }
    
    static {
        defaultUnavailableMethod = unavailable("method", "!!UNKNOWN!!");
    }
    
    public static final class UnavailableInvoker<T> extends InitInvoker<T>
    {
        private final String type;
        private final String missingInfo;
        
        private UnavailableInvoker(final String type, final String missingInfo) {
            this.type = type;
            this.missingInfo = missingInfo;
        }
        
        public final Invoker<T> create() {
            throw new UnsupportedOperationException(this.type + " " + this.missingInfo + " is not available");
        }
    }
    
    private static final class ProxyInvoker<T> extends InitInvoker<T>
    {
        private final Invoker<T> invoker;
        
        public ProxyInvoker(final Object o, final FieldAccessor<Invoker<T>> fieldAccessor, final Invoker<T> invoker) {
            super(o, fieldAccessor);
            this.invoker = invoker;
        }
        
        public Invoker<T> create() {
            return this.invoker.initializeInvoker();
        }
    }
    
    private static final class InitGeneratedRecordFieldChangerInvoker<T> extends InitInvoker<T>
    {
        private final Class<?> declaringClass;
        private final String nameAlias;
        private final List<String> recordFields;
        
        protected InitGeneratedRecordFieldChangerInvoker(final Object o, final FieldAccessor<Invoker<T>> fieldAccessor, final Class<?> declaringClass, final String nameAlias, final List<String> recordFields) {
            super(o, fieldAccessor);
            this.declaringClass = declaringClass;
            this.nameAlias = nameAlias;
            this.recordFields = recordFields;
        }
        
        @Override
        protected Invoker<T> create() {
            return (Invoker<T>)RecordClassFieldChanger.create(this.declaringClass, this.nameAlias, this.recordFields);
        }
    }
    
    private static final class InitGeneratedExecutableInvoker<T> extends InitInvoker<T>
    {
        private final Executable executable;
        
        protected InitGeneratedExecutableInvoker(final Object o, final FieldAccessor<Invoker<T>> fieldAccessor, final Executable executable) {
            super(o, fieldAccessor);
            this.executable = executable;
        }
        
        @Override
        protected Invoker<T> create() {
            if (GeneratedInvoker.canCreate(this.executable)) {
                return (Invoker<T>)GeneratedInvoker.create(this.executable);
            }
            return ReflectionInvoker.create(this.executable);
        }
    }
    
    public static class InitGeneratedCodeInvoker extends InitInvoker<Object> implements GeneratedExactSignatureInvoker<Object>
    {
        private final ExtendedClassWriter.Deferred<? extends GeneratedCodeInvoker<Object>> invoker;
        
        private InitGeneratedCodeInvoker(final Object o, final FieldAccessor<Invoker<Object>> fieldAccessor, final ExtendedClassWriter.Deferred<? extends GeneratedCodeInvoker<Object>> invoker) {
            super(o, fieldAccessor);
            this.invoker = invoker;
        }
        
        @Override
        public String getInvokerClassInternalName() {
            return this.invoker.getInternalName();
        }
        
        @Override
        public String getInvokerClassTypeDescriptor() {
            return this.invoker.getTypeDescriptor();
        }
        
        @Override
        protected Invoker<Object> create() {
            return (Invoker)this.invoker.generate();
        }
        
        public static <T> InitGeneratedCodeInvoker create(final Object o, final FieldAccessor<Invoker<T>> fieldAccessor, final MethodDeclaration methodDeclaration) {
            return new InitGeneratedCodeInvoker(o, (FieldAccessor<Invoker<Object>>)fieldAccessor, GeneratedCodeInvoker.createDefer(methodDeclaration));
        }
    }
    
    private static final class ReflectionFieldAccessorLate<T> extends ReflectionFieldAccessorBase<T>
    {
        private final ClassLoader classLoader;
        private final String declaringClassName;
        private final String fieldName;
        private Field field;
        
        public ReflectionFieldAccessorLate(final ClassLoader classLoader, final String declaringClassName, final String fieldName) {
            this.field = null;
            this.classLoader = classLoader;
            this.declaringClassName = declaringClassName;
            this.fieldName = fieldName;
        }
        
        @Override
        public Field getField() {
            if (this.field == null) {
                try {
                    this.field = ReflectionFieldAccessorBase.findField(this.classLoader.loadClass(this.declaringClassName), this.fieldName);
                }
                catch (final Throwable t) {
                    throw MountiplexUtil.uncheckedRethrow(t);
                }
            }
            return this.field;
        }
    }
    
    private static final class ReflectionFieldAccessor<T> extends ReflectionFieldAccessorBase<T>
    {
        private final Field field;
        
        public ReflectionFieldAccessor(final Class<?> clazz, final String s) {
            try {
                this.field = ReflectionFieldAccessorBase.findField(clazz, s);
            }
            catch (final Throwable t) {
                throw MountiplexUtil.uncheckedRethrow(t);
            }
        }
        
        @Override
        public Field getField() {
            return this.field;
        }
    }
    
    private abstract static class ReflectionFieldAccessorBase<T> implements FieldAccessor<Invoker<T>>
    {
        public abstract Field getField();
        
        protected static Field findField(final Class<?> clazz, final String s) {
            try {
                return MPLType.getDeclaredField(clazz, s);
            }
            catch (final NoSuchFieldException ex) {
                final Class<?> superclass = clazz.getSuperclass();
                if (superclass == null) {
                    throw ex;
                }
                return findField(superclass, s);
            }
        }
        
        @Override
        public synchronized Invoker<T> get(final Object obj) {
            try {
                final Field field = this.getField();
                final boolean accessible = field.isAccessible();
                Object value;
                try {
                    field.setAccessible(true);
                    value = field.get(obj);
                }
                finally {
                    field.setAccessible(accessible);
                }
                return (Invoker<T>)value;
            }
            catch (final Throwable t) {
                throw MountiplexUtil.uncheckedRethrow(t);
            }
        }
        
        @Override
        public synchronized boolean set(final Object o, final Invoker<T> value) {
            try {
                final Field field = this.getField();
                final int modifiers = field.getModifiers();
                if (Modifier.isStatic(modifiers) && Modifier.isFinal(modifiers)) {
                    field.get(o);
                    GeneratedAccessor.GeneratedStaticFinalAccessor.setUninitializedField(field, value);
                }
                else {
                    final boolean accessible = field.isAccessible();
                    try {
                        field.setAccessible(true);
                        field.set(o, value);
                    }
                    finally {
                        field.setAccessible(accessible);
                    }
                }
                return true;
            }
            catch (final Throwable t) {
                throw MountiplexUtil.uncheckedRethrow(t);
            }
        }
    }
}
