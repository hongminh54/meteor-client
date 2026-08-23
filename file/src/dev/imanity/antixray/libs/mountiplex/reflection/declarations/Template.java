// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.mountiplex.reflection.declarations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;
import java.lang.annotation.Repeatable;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Retention;
import dev.imanity.antixray.libs.mountiplex.reflection.util.FastConstructor;
import dev.imanity.antixray.libs.mountiplex.conversion.util.ParamsConverterList;
import dev.imanity.antixray.libs.mountiplex.conversion.type.DisabledConverter;
import dev.imanity.antixray.libs.mountiplex.conversion.type.LazyConverter;
import java.lang.reflect.Method;
import dev.imanity.antixray.libs.mountiplex.reflection.SafeMethod;
import dev.imanity.antixray.libs.mountiplex.reflection.util.FastMethod;
import dev.imanity.antixray.libs.mountiplex.reflection.MethodAccessor;
import dev.imanity.antixray.libs.mountiplex.reflection.IgnoredFieldAccessor;
import dev.imanity.antixray.libs.mountiplex.reflection.TranslatorFieldAccessor;
import dev.imanity.antixray.libs.mountiplex.reflection.SafeField;
import dev.imanity.antixray.libs.mountiplex.reflection.FieldAccessor;
import dev.imanity.antixray.libs.mountiplex.reflection.util.FastField;
import dev.imanity.antixray.libs.mountiplex.reflection.util.BoxedType;
import java.util.Collection;
import dev.imanity.antixray.libs.mountiplex.reflection.util.fast.InitInvoker;
import java.util.ArrayList;
import dev.imanity.antixray.libs.mountiplex.conversion.Converter;
import dev.imanity.antixray.libs.mountiplex.conversion.Conversion;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import dev.imanity.antixray.libs.mountiplex.reflection.util.asm.MPLType;
import java.util.logging.Level;
import dev.imanity.antixray.libs.mountiplex.MountiplexUtil;
import dev.imanity.antixray.libs.mountiplex.reflection.resolver.ClassDeclarationResolver;
import dev.imanity.antixray.libs.mountiplex.reflection.util.fast.ClassFieldCopier;
import dev.imanity.antixray.libs.mountiplex.reflection.util.NullInstantiator;
import dev.imanity.antixray.libs.mountiplex.reflection.util.fast.Invoker;
import dev.imanity.antixray.libs.mountiplex.conversion.type.DuplexConverter;
import dev.imanity.antixray.libs.mountiplex.reflection.util.LazyInitializedObject;
import dev.imanity.antixray.libs.mountiplex.reflection.util.IgnoresRemapping;

public class Template
{
    public static class Class<H extends Handle> implements IgnoresRemapping, LazyInitializedObject
    {
        private boolean valid;
        private boolean optional;
        private String classPath;
        private java.lang.Class<?> classType;
        private java.lang.Class<H> handleType;
        private DuplexConverter<Object, H> handleConverter;
        private TemplateHandleBuilder<H> handleBuilder;
        private Invoker<H> handleBuilderMethod;
        private NullInstantiator<Object> instantiator;
        private TemplateElement<?>[] elements;
        private ClassFieldCopier<Object> fieldCopier;
        private ClassDeclaration classDec;
        
        public static <C extends Class<H>, H extends Handle> C create(final java.lang.Class<C> clazz) {
            return create(clazz, null);
        }
        
        public static <C extends Class<H>, H extends Handle> C create(final java.lang.Class<C> clazz, final ClassDeclarationResolver classDeclarationResolver) {
            try {
                return new TemplateClassBuilder<C, Handle>(clazz, classDeclarationResolver).build();
            }
            catch (final Throwable thrown) {
                MountiplexUtil.LOGGER.log(Level.SEVERE, "Failed to initialize " + MPLType.getName(clazz), thrown);
                return null;
            }
        }
        
        public Class() {
            this.valid = false;
            this.optional = false;
            this.classPath = null;
            this.classType = null;
            this.handleType = null;
            this.handleConverter = null;
            this.handleBuilder = null;
            this.handleBuilderMethod = null;
            this.instantiator = null;
            this.elements = new TemplateElement[0];
            this.fieldCopier = null;
            this.classDec = null;
        }
        
        public final H createHandle(final Object o) {
            return this.createHandle(o, false);
        }
        
        public final H createHandle(final Object o, final boolean b) {
            if (o == null && !b) {
                return null;
            }
            if (this.handleBuilderMethod != null) {
                return this.handleBuilderMethod.invoke(null, o);
            }
            if (this.handleBuilder == null) {
                synchronized (this) {
                    if (this.handleBuilder == null) {
                        final TemplateHandleBuilder handleBuilder = new TemplateHandleBuilder((Class<H>)this);
                        handleBuilder.build();
                        if (this.handleBuilder == null) {
                            this.handleBuilder = handleBuilder;
                        }
                    }
                }
            }
            return this.handleBuilder.create(o);
        }
        
        public final H newHandleNull() {
            return this.createHandle(this.newInstanceNull(), false);
        }
        
        public final Object newInstanceNull() {
            if (this.classType == null) {
                throw new IllegalStateException("Class " + this.classPath + " is not available");
            }
            return this.instantiator.create();
        }
        
        @Override
        public final void forceInitialization() {
            if (this.isAvailable()) {
                boolean b = true;
                for (final TemplateElement<?> templateElement : this.elements) {
                    Label_0106: {
                        if (templateElement.isOptional()) {
                            if (!templateElement.isAvailable()) {
                                break Label_0106;
                            }
                        }
                        try {
                            templateElement.forceInitialization();
                        }
                        catch (final Throwable thrown) {
                            MountiplexUtil.LOGGER.log(Level.SEVERE, "Failed to initialize " + this.classPath + " " + templateElement.getElementName(), thrown);
                            b = false;
                        }
                    }
                }
                if (this.getClass() != this.getSelfClassType()) {
                    for (final java.lang.reflect.Field field : this.getClass().getDeclaredFields()) {
                        Label_0259: {
                            if (Modifier.isStatic(field.getModifiers())) {
                                Object value;
                                try {
                                    field.setAccessible(true);
                                    value = field.get(null);
                                    field.setAccessible(false);
                                }
                                catch (final Throwable t) {
                                    break Label_0259;
                                }
                                if (value instanceof LazyInitializedObject) {
                                    try {
                                        ((LazyInitializedObject)value).forceInitialization();
                                    }
                                    catch (final Throwable thrown2) {
                                        MountiplexUtil.LOGGER.log(Level.SEVERE, "Failed to initialize " + this.classPath + " field " + field.getName(), thrown2);
                                        b = false;
                                    }
                                }
                            }
                        }
                    }
                }
                if (!b) {
                    throw new IllegalStateException("Not all members of template for " + this.classPath + " could be initialized!");
                }
            }
        }
        
        final void init(final TemplateClassBuilder<?, H> templateClassBuilder) {
            this.handleType = templateClassBuilder.handleType;
            this.classPath = templateClassBuilder.instanceClassPath;
            this.classType = templateClassBuilder.instanceType;
            this.classDec = templateClassBuilder.classDec;
            this.optional = templateClassBuilder.isOptional;
            this.valid = (this.classType != null && this.classDec != null);
            this.instantiator = NullInstantiator.of(this.classType);
            if (this.valid) {
                this.classDec.checkTemplateErrors();
            }
            if (this.classType != null && this.handleType != null) {
                Conversion.registerConverter(this.handleConverter = (DuplexConverter<Object, H>)new DuplexHandleConverter((Class<Handle>)this));
            }
            if (this.valid) {
                this.classDec.getResolver().runBootstrap();
            }
            boolean b = true;
            final ArrayList list = new ArrayList();
            for (final java.lang.reflect.Field field : this.getClass().getFields()) {
                final String name = field.getName();
                try {
                    final Object value = field.get(this);
                    if (value instanceof TemplateElement) {
                        final TemplateElement e = (TemplateElement)value;
                        e.initElementName(this.classPath + "." + name);
                        list.add(e);
                        if (field.getAnnotation(Optional.class) != null) {
                            e.setOptional();
                        }
                        if (field.getAnnotation(Readonly.class) != null) {
                            e.setReadonly();
                        }
                        if (this.valid) {
                            final Declaration init = e.init(this, this.classDec, name);
                            if (init != null) {
                                if (init instanceof MethodDeclaration && TemplateHandleBuilder.isCreateHandleMethod((MethodDeclaration)init)) {
                                    this.handleBuilderMethod = (Invoker<H>)InitInvoker.proxy(this, "handleBuilderMethod", ((StaticMethod)e).invoker);
                                }
                            }
                            else if (!e._optional) {
                                b = false;
                            }
                        }
                        else {
                            e.initNoClass();
                        }
                    }
                }
                catch (final Throwable thrown) {
                    MountiplexUtil.LOGGER.log(Level.SEVERE, "Failed to initialize template field '" + name + "' in " + this.classType.getName(), thrown);
                    b = false;
                }
            }
            this.valid &= b;
            this.elements = MountiplexUtil.toArray(list, TemplateElement.class);
        }
        
        public final boolean isAvailable() {
            return this.classType != null;
        }
        
        public final boolean isValid() {
            return this.valid;
        }
        
        public final boolean isOptional() {
            return this.optional;
        }
        
        public java.lang.Class<?> getType() {
            return this.classType;
        }
        
        public java.lang.Class<H> getHandleType() {
            return this.handleType;
        }
        
        protected java.lang.Class<?> getSelfClassType() {
            return this.getClass();
        }
        
        public ClassDeclaration getClassDeclaration() {
            return this.classDec;
        }
        
        public DuplexConverter<Object, H> getHandleConverter() {
            return this.handleConverter;
        }
        
        public boolean isType(final Object o) {
            return o != null && this.classType != null && this.classType.equals(o.getClass());
        }
        
        public boolean isType(final java.lang.Class<?> obj) {
            return this.classType != null && this.classType.equals(obj);
        }
        
        public boolean isHandleType(final Handle handle) {
            return handle != null && this.isType(handle.getRaw());
        }
        
        public boolean isAssignableFrom(final Object o) {
            return o != null && this.classType != null && this.classType.isAssignableFrom(o.getClass());
        }
        
        public boolean isAssignableFrom(final java.lang.Class<?> clazz) {
            return clazz != null && this.classType != null && this.classType.isAssignableFrom(clazz);
        }
        
        public void copyHandle(final H h, final H h2) {
            if (h == null || h2 == null) {
                return;
            }
            this.copy(h.getRaw(), h2.getRaw());
        }
        
        public void copy(final Object o, final Object o2) {
            if (this.fieldCopier == null) {
                this.fieldCopier = ClassFieldCopier.of(this.classType);
            }
            this.fieldCopier.copy(o, o2);
        }
    }
    
    public abstract static class Handle implements IgnoresRemapping
    {
        public final boolean isInstanceOf(final Class<?> class1) {
            return this.isInstanceOf(class1.getType());
        }
        
        public final boolean isInstanceOf(final java.lang.Class<?> clazz) {
            return clazz != null && clazz.isAssignableFrom(this.getRaw().getClass());
        }
        
        public abstract Object getRaw();
        
        public static final Object getRaw(final Handle handle) {
            return (handle == null) ? null : handle.getRaw();
        }
        
        public <T extends Handle> T cast(final Class<T> class1) {
            if (this.isInstanceOf(class1)) {
                return class1.createHandle(this.getRaw(), false);
            }
            throw new ClassCastException("Failed to cast handle of type " + MPLType.getName(this.getRaw().getClass()) + " to " + MPLType.getName(class1.getType()));
        }
        
        public <T extends Handle> T tryCast(final Class<T> class1) {
            if (this.isInstanceOf(class1)) {
                return class1.createHandle(this.getRaw(), false);
            }
            return null;
        }
        
        public static Handle createHandle(final Object o) {
            if (o == null) {
                return null;
            }
            return new Handle() {
                @Override
                public Object getRaw() {
                    return o;
                }
            };
        }
        
        @Override
        public final int hashCode() {
            return this.getRaw().hashCode();
        }
        
        @Override
        public final boolean equals(final Object o) {
            if (this.getRaw() == null) {
                return o == null || (o instanceof Handle && ((Handle)o).getRaw() == null);
            }
            return this == o || (o instanceof Handle && ((Handle)o).getRaw().equals(this.getRaw()));
        }
        
        @Override
        public final String toString() {
            return this.getRaw().toString();
        }
    }
    
    public static final class DuplexHandleConverter<H extends Handle> extends DuplexConverter<Object, H>
    {
        public final Class<H> handleClass;
        
        public DuplexHandleConverter(final Class<H> handleClass) {
            super(TypeDeclaration.fromClass(handleClass.getType()), TypeDeclaration.fromClass(handleClass.getHandleType()), null);
            this.handleClass = handleClass;
            this.reverse = (DuplexConverter<B, A>)new DuplexHandleConverterReverse((DuplexHandleConverter<Handle>)this);
        }
        
        @Override
        public H convertInput(final Object o) {
            return this.handleClass.createHandle(o, false);
        }
        
        @Override
        public Object convertOutput(final Handle handle) {
            return handle.getRaw();
        }
    }
    
    public static final class DuplexHandleConverterReverse<H extends Handle> extends DuplexConverter<H, Object>
    {
        public final Class<H> handleClass;
        
        public DuplexHandleConverterReverse(final DuplexHandleConverter<H> duplexHandleConverter) {
            super(duplexHandleConverter.output, duplexHandleConverter.input, duplexHandleConverter);
            this.handleClass = duplexHandleConverter.handleClass;
        }
        
        @Override
        public Object convertInput(final H h) {
            return h.getRaw();
        }
        
        @Override
        public H convertOutput(final Object o) {
            return this.handleClass.createHandle(o, false);
        }
    }
    
    public abstract static class TemplateElement<T extends Declaration> implements IgnoresRemapping, LazyInitializedObject
    {
        private boolean _optional;
        private boolean _readonly;
        protected boolean _hasClass;
        private String _elementName;
        
        public TemplateElement() {
            this._optional = false;
            this._readonly = false;
            this._hasClass = true;
            this._elementName = "!!UNKNOWN!!";
        }
        
        protected void initNoClass() {
            this._hasClass = false;
        }
        
        protected abstract T init(final Class<?> p0, final ClassDeclaration p1, final String p2);
        
        protected void initElementName(final String elementName) {
            this._elementName = elementName;
        }
        
        public final String getElementName() {
            return this._elementName;
        }
        
        public abstract boolean isAvailable();
        
        protected void failNotFound() {
        }
        
        protected final <V> V failGetSafe(final RuntimeException thrown, final V v) {
            MountiplexUtil.LOGGER.log(Level.SEVERE, "Failed to get static field value", thrown);
            return v;
        }
        
        protected final void failInvalidArgs(final java.lang.Class<?>[] array, final Object[] array2) {
            if (array.length != array2.length) {
                throw new IllegalArgumentException("Invalid number of argument specified! Expected " + array.length + " arguments, but got " + array2.length);
            }
            for (int i = 0; i < array.length; ++i) {
                if (array[i].isPrimitive() && array2[i] == null) {
                    throw new IllegalArgumentException("Null can not be assigned to primitive " + array[i].getName() + " argument [" + i + "]");
                }
                if (array2[i] != null && !BoxedType.tryBoxType(array[i]).isAssignableFrom(array2[i].getClass())) {
                    throw new IllegalArgumentException("Value of type " + MPLType.getName(array2[i].getClass()) + " can not be assigned to " + MPLType.getName(array[i]) + " argument [" + i + "]");
                }
            }
        }
        
        protected final void initFail(final String msg) {
            if (!this.isOptional()) {
                MountiplexUtil.LOGGER.warning(msg);
            }
        }
        
        protected void setOptional() {
            this._optional = true;
        }
        
        protected void setReadonly() {
            this._readonly = true;
        }
        
        public boolean isOptional() {
            return this._optional;
        }
        
        public boolean isReadonly() {
            return this._readonly;
        }
    }
    
    public static class AbstractField<T> extends TemplateElement<FieldDeclaration>
    {
        protected final FastField<T> field;
        
        public AbstractField() {
            this.field = new FastField<T>();
        }
        
        @Override
        protected void failNotFound() {
            if (!this.field.isAvailable()) {
                throw new RuntimeException("Field " + this.getElementName() + " not found");
            }
        }
        
        @Override
        protected FieldDeclaration init(final Class<?> class1, final ClassDeclaration classDeclaration, final String str) {
            if (classDeclaration == null) {
                throw new IllegalArgumentException("ClassDeclaration is null");
            }
            final FieldDeclaration[] fields = classDeclaration.fields;
            final int length = fields.length;
            int i = 0;
            while (i < length) {
                final FieldDeclaration obj = fields[i];
                if (obj.field != null && obj.name.real().equals(str)) {
                    if (!obj.isResolved()) {
                        this.initFail("Field '" + str + "' has an unresolved declaration: " + obj);
                        return null;
                    }
                    this.field.init(obj.field);
                    return obj;
                }
                else {
                    ++i;
                }
            }
            this.initFail("Field '" + str + "' not found in template for " + classDeclaration.type.typePath);
            return null;
        }
        
        @Override
        public void forceInitialization() {
            this.field.forceInitialization();
        }
        
        @Override
        protected void initElementName(final String s) {
            super.initElementName(s);
            this.field.initUnavailable(s);
        }
        
        @Override
        public boolean isAvailable() {
            return this.field.getField() != null;
        }
        
        public FieldAccessor<T> toFieldAccessor() {
            if (this.isAvailable()) {
                return new SafeField<T>(this.field);
            }
            return new FieldAccessor<T>() {
                @Override
                public boolean isValid() {
                    return false;
                }
                
                private UnsupportedOperationException fail() {
                    return new UnsupportedOperationException("Field " + AbstractField.this.field.getDescription() + " is not available");
                }
                
                @Override
                public T get(final Object o) {
                    throw this.fail();
                }
                
                @Override
                public boolean set(final Object o, final T t) {
                    throw this.fail();
                }
                
                @Override
                public T transfer(final Object o, final Object o2) {
                    throw this.fail();
                }
                
                @Override
                public <K> TranslatorFieldAccessor<K> translate(final DuplexConverter<?, K> duplexConverter) {
                    return new TranslatorFieldAccessor<K>(this, duplexConverter);
                }
                
                @Override
                public FieldAccessor<T> ignoreInvalid(final T t) {
                    return new IgnoredFieldAccessor<T>(t);
                }
            };
        }
    }
    
    public static class AbstractMethod<T> extends TemplateElement<MethodDeclaration>
    {
        private MethodDeclaration method;
        public Invoker<T> invoker;
        
        public AbstractMethod() {
            this.method = null;
            this.invoker = (Invoker<T>)InitInvoker.unavailableMethod();
        }
        
        @Override
        protected final void failNotFound() {
            if (this.method == null) {
                throw new RuntimeException("Method " + this.getElementName() + " not found");
            }
        }
        
        @Override
        protected MethodDeclaration init(final Class<?> class1, final ClassDeclaration classDeclaration, final String str) {
            if (classDeclaration == null) {
                throw new IllegalArgumentException("ClassDeclaration is null");
            }
            final MethodDeclaration[] methods = classDeclaration.methods;
            final int length = methods.length;
            int i = 0;
            while (i < length) {
                final MethodDeclaration methodDeclaration = methods[i];
                if ((methodDeclaration.constructor != null || methodDeclaration.method != null || methodDeclaration.body != null) && methodDeclaration.name.real().equals(str)) {
                    if (!methodDeclaration.isResolved()) {
                        this.initFail("Method '" + str + "' has an unresolved declaration: " + methodDeclaration);
                        return null;
                    }
                    this.method = methodDeclaration;
                    this.invoker = (Invoker<T>)InitInvoker.forMethod(this, "invoker", methodDeclaration);
                    return methodDeclaration;
                }
                else {
                    ++i;
                }
            }
            this.initFail("Method '" + str + "' not found in template for " + classDeclaration.type.typePath);
            return null;
        }
        
        @Override
        public void forceInitialization() {
            this.invoker.forceInitialization();
            this.failNotFound();
        }
        
        @Override
        protected void initElementName(final String s) {
            super.initElementName(s);
            this.method = null;
            this.invoker = (Invoker<T>)InitInvoker.unavailable("method", s);
        }
        
        @Override
        public boolean isAvailable() {
            return this.method != null;
        }
        
        public <R> MethodAccessor<R> toMethodAccessor() {
            final FastMethod fastMethod = new FastMethod();
            fastMethod.init(this.method, this.invoker);
            return new SafeMethod<R>(fastMethod);
        }
        
        public java.lang.reflect.Method toJavaMethod() {
            return (this.method == null) ? null : this.method.method;
        }
    }
    
    public static class AbstractFieldConverter<F extends AbstractField<?>, T> extends TemplateElement<FieldDeclaration>
    {
        public final F raw;
        protected LazyConverter<?, T> converter;
        protected LazyConverter<T, ?> reverse;
        
        protected AbstractFieldConverter(final F raw) {
            this.converter = LazyConverter.uninitialized();
            this.reverse = LazyConverter.uninitialized();
            this.raw = raw;
        }
        
        @Override
        public void forceInitialization() {
            this.raw.forceInitialization();
            if (!this.converter.isAvailable()) {
                throw new UnsupportedOperationException("Field get() converter for " + this.getElementName() + " was not found");
            }
            if (!this.reverse.isAvailable()) {
                throw new UnsupportedOperationException("Field set() converter for " + this.getElementName() + " was not found");
            }
        }
        
        @Override
        protected void initElementName(final String s) {
            super.initElementName(s);
            this.raw.initElementName(s);
        }
        
        private void initFailConverter(final NameDeclaration nameDeclaration, final TypeDeclaration typeDeclaration, final TypeDeclaration typeDeclaration2) {
            this.initFail("Converter for field " + nameDeclaration.toString() + " not found: " + typeDeclaration.toString(true) + " -> " + typeDeclaration2.toString(true));
        }
        
        @Override
        protected FieldDeclaration init(final Class<?> class1, final ClassDeclaration classDeclaration, final String str) {
            if (classDeclaration == null) {
                throw new IllegalArgumentException("ClassDeclaration is null");
            }
            final FieldDeclaration init = this.raw.init(class1, classDeclaration, str);
            if (init != null) {
                if (this.isReadonly()) {
                    this.converter = (LazyConverter<?, T>)LazyConverter.create(init.type, init.type.cast);
                    this.reverse = LazyConverter.of((Converter<T, ?>)new DisabledConverter<T, Object>(init.type.cast, init.type, "Field " + str + " is readonly"));
                }
                else {
                    this.converter = (LazyConverter<?, T>)LazyConverter.create(init.type, init.type.cast);
                    this.reverse = (LazyConverter<T, ?>)LazyConverter.create(init.type.cast, init.type);
                }
                final NameDeclaration name = init.name;
                this.converter.whenFailing((typeDeclaration3, typeDeclaration4) -> this.initFailConverter(nameDeclaration, typeDeclaration3, typeDeclaration4));
                this.reverse.whenFailing((typeDeclaration7, typeDeclaration8) -> this.initFailConverter(nameDeclaration2, typeDeclaration7, typeDeclaration8));
            }
            return init;
        }
        
        @Override
        public boolean isAvailable() {
            return this.raw.isAvailable() && this.converter.isAvailable() && this.reverse.isAvailable();
        }
        
        @Override
        protected void setOptional() {
            super.setOptional();
            this.raw.setOptional();
        }
        
        public TranslatorFieldAccessor<T> toFieldAccessor() {
            Converter<A, B> converter;
            if (this.isAvailable()) {
                converter = (Converter<A, B>)DuplexConverter.pair(this.converter, this.reverse);
            }
            else {
                converter = (Converter<A, B>)DuplexConverter.createNull(TypeDeclaration.fromClass(Object.class));
            }
            return new TranslatorFieldAccessor<T>(this.raw.toFieldAccessor(), (DuplexConverter<?, Object>)converter);
        }
        
        public F raw() {
            return this.raw;
        }
    }
    
    public abstract static class AbstractParamsConverter<R extends TemplateElement<?>, T, D extends Declaration> extends TemplateElement<D>
    {
        public final R raw;
        protected ParamsConverterList.Supplier<T> converters;
        protected int argCount;
        
        protected AbstractParamsConverter(final R raw) {
            this.converters = ParamsConverterList.Supplier.uninitialized();
            this.argCount = -1;
            this.raw = raw;
        }
        
        @Override
        public void forceInitialization() {
            this.raw.forceInitialization();
            this.converters.get();
        }
        
        @Override
        protected void initElementName(final String s) {
            super.initElementName(s);
            this.raw.initElementName(s);
        }
        
        protected final void initConverters(final String s, final TypeDeclaration typeDeclaration, final ParameterListDeclaration parameterListDeclaration) {
            this.argCount = parameterListDeclaration.parameters.length;
            this.converters = ParamsConverterList.Supplier.lazy(() -> {
                final ParamsConverterList list = new ParamsConverterList(s2, typeDeclaration2, parameterListDeclaration2);
                if (list.valid) {
                    this.converters = ParamsConverterList.Supplier.of((ParamsConverterList<T>)list);
                }
                else {
                    this.argCount = -1;
                    this.converters = ParamsConverterList.Supplier.unsupported("Method converters for " + this.getElementName() + " could not be initialized");
                }
                return this.converters;
            });
        }
        
        @Override
        protected void setOptional() {
            super.setOptional();
            this.raw.setOptional();
        }
        
        @Override
        public boolean isAvailable() {
            return this.raw.isAvailable();
        }
        
        protected final ParamsConverterList<T> prepare(final int i) {
            if (this.argCount != i) {
                if (!this.converters.available()) {
                    this.raw.failNotFound();
                    this.converters.get();
                }
                throw new IllegalArgumentException("Invalid number of arguments (" + this.argCount + " expected, but got " + i + ")");
            }
            return this.converters.get();
        }
        
        public R raw() {
            return this.raw;
        }
    }
    
    public static class AbstractMethodConverter<M extends AbstractMethod<?>, T> extends AbstractParamsConverter<M, T, MethodDeclaration>
    {
        protected AbstractMethodConverter(final M m) {
            super(m);
        }
        
        @Override
        protected MethodDeclaration init(final Class<?> class1, final ClassDeclaration classDeclaration, final String s) {
            if (classDeclaration == null) {
                throw new IllegalArgumentException("ClassDeclaration is null");
            }
            final MethodDeclaration init = ((AbstractMethod)this.raw).init(class1, classDeclaration, s);
            if (init != null) {
                this.initConverters("method " + init.name.toString(), init.returnType, init.parameters);
            }
            return init;
        }
    }
    
    public static final class Constructor<T> extends TemplateElement<ConstructorDeclaration>
    {
        protected final FastConstructor<T> constructor;
        
        public Constructor() {
            this.constructor = new FastConstructor<T>();
        }
        
        public T newInstanceVA(final Object... array) {
            return this.constructor.newInstanceVA(array);
        }
        
        public T newInstance() {
            return this.constructor.newInstance();
        }
        
        public T newInstance(final Object o) {
            return this.constructor.newInstance(o);
        }
        
        public T newInstance(final Object o, final Object o2) {
            return this.constructor.newInstance(o, o2);
        }
        
        public T newInstance(final Object o, final Object o2, final Object o3) {
            return this.constructor.newInstance(o, o2, o3);
        }
        
        public T newInstance(final Object o, final Object o2, final Object o3, final Object o4) {
            return this.constructor.newInstance(o, o2, o3, o4);
        }
        
        public T newInstance(final Object o, final Object o2, final Object o3, final Object o4, final Object o5) {
            return this.constructor.newInstance(o, o2, o3, o4, o5);
        }
        
        @Override
        public void forceInitialization() {
            this.constructor.forceInitialization();
        }
        
        @Override
        public boolean isAvailable() {
            return this.constructor.isAvailable();
        }
        
        @Override
        protected final void failNotFound() {
            if (!this.constructor.isAvailable()) {
                throw new RuntimeException("Constructor not found");
            }
        }
        
        protected final void failInvalidArgs(final Object[] array) {
            this.failInvalidArgs(this.constructor.getConstructor().getParameterTypes(), array);
        }
        
        @Override
        protected ConstructorDeclaration init(final Class<?> class1, final ClassDeclaration classDeclaration, final String str) {
            if (classDeclaration == null) {
                throw new IllegalArgumentException("ClassDeclaration is null");
            }
            for (final ConstructorDeclaration constructorDeclaration : classDeclaration.constructors) {
                if (constructorDeclaration.constructor != null && constructorDeclaration.getName().equals(str)) {
                    try {
                        this.constructor.init(constructorDeclaration);
                        return constructorDeclaration;
                    }
                    catch (final Throwable t) {
                        MountiplexUtil.LOGGER.warning("Method '" + str + "' in template for " + classDeclaration.type.typePath + " not accessible");
                        return null;
                    }
                }
            }
            this.initFail("Constructor '" + str + "' not found in template for " + classDeclaration.type.typePath);
            return null;
        }
        
        public static final class Converted<T> extends AbstractParamsConverter<Constructor<Object>, T, ConstructorDeclaration>
        {
            public Converted() {
                super(new Constructor());
            }
            
            @Override
            protected ConstructorDeclaration init(final Class<?> class1, final ClassDeclaration classDeclaration, final String s) {
                if (classDeclaration == null) {
                    throw new IllegalArgumentException("ClassDeclaration is null");
                }
                final ConstructorDeclaration init = ((Constructor)this.raw).init(class1, classDeclaration, s);
                if (init != null) {
                    this.initConverters("constructor " + init.parameters.toString(), init.type, init.parameters);
                }
                return init;
            }
            
            public final T newInstanceVA(final Object... array) {
                final ParamsConverterList<T> prepare = this.prepare(array.length);
                return prepare.convertResult(((Constructor)this.raw).newInstanceVA(prepare.convertArgs(array)));
            }
            
            public final T newInstance() {
                return this.prepare(0).convertResult(((Constructor)this.raw).newInstance());
            }
            
            public final T newInstance(final Object o) {
                final ParamsConverterList<T> prepare = this.prepare(1);
                if (prepare.args == null) {
                    return prepare.convertResult(((Constructor)this.raw).newInstance(o));
                }
                return prepare.convertResult(((Constructor)this.raw).newInstance(prepare.arg0.apply(o)));
            }
            
            public final T newInstance(final Object o, final Object o2) {
                final ParamsConverterList<T> prepare = this.prepare(2);
                if (prepare.args == null) {
                    return prepare.convertResult(((Constructor)this.raw).newInstance(o, o2));
                }
                return prepare.convertResult(((Constructor)this.raw).newInstance(prepare.arg0.apply(o), prepare.arg1.apply(o2)));
            }
            
            public final T newInstance(final Object o, final Object o2, final Object o3) {
                final ParamsConverterList<T> prepare = this.prepare(3);
                if (prepare.args == null) {
                    return prepare.convertResult(((Constructor)this.raw).newInstance(o, o2, o3));
                }
                return prepare.convertResult(((Constructor)this.raw).newInstance(prepare.arg0.apply(o), prepare.arg1.apply(o2), prepare.arg2.apply(o3)));
            }
            
            public final T newInstance(final Object o, final Object o2, final Object o3, final Object o4) {
                final ParamsConverterList<T> prepare = this.prepare(4);
                if (prepare.args == null) {
                    return prepare.convertResult(((Constructor)this.raw).newInstance(o, o2, o3, o4));
                }
                return prepare.convertResult(((Constructor)this.raw).newInstance(prepare.arg0.apply(o), prepare.arg1.apply(o2), prepare.arg2.apply(o3), prepare.arg3.apply(o4)));
            }
            
            public final T newInstance(final Object o, final Object o2, final Object o3, final Object o4, final Object o5) {
                final ParamsConverterList<T> prepare = this.prepare(5);
                if (prepare.args == null) {
                    return prepare.convertResult(((Constructor)this.raw).newInstance(o, o2, o3, o4, o5));
                }
                return prepare.convertResult(((Constructor)this.raw).newInstance(prepare.arg0.apply(o), prepare.arg1.apply(o2), prepare.arg2.apply(o3), prepare.arg3.apply(o4), prepare.arg4.apply(o5)));
            }
        }
    }
    
    public static final class StaticMethod<T> extends AbstractMethod<T>
    {
        public T invokeVA(final Object... array) {
            return this.invoker.invokeVA(null, array);
        }
        
        public T invoke() {
            return this.invoker.invoke(null);
        }
        
        public T invoke(final Object o) {
            return this.invoker.invoke(null, o);
        }
        
        public T invoke(final Object o, final Object o2) {
            return this.invoker.invoke(null, o, o2);
        }
        
        public T invoke(final Object o, final Object o2, final Object o3) {
            return this.invoker.invoke(null, o, o2, o3);
        }
        
        public T invoke(final Object o, final Object o2, final Object o3, final Object o4) {
            return this.invoker.invoke(null, o, o2, o3, o4);
        }
        
        public T invoke(final Object o, final Object o2, final Object o3, final Object o4, final Object o5) {
            return this.invoker.invoke(null, o, o2, o3, o4, o5);
        }
        
        public static final class Converted<T> extends AbstractMethodConverter<StaticMethod<Object>, T>
        {
            public Converted() {
                super(new StaticMethod());
            }
            
            public final T invokeVA(final Object... array) {
                final ParamsConverterList<T> prepare = this.prepare(array.length);
                return (T)prepare.convertResult(((StaticMethod)this.raw).invoker.invokeVA(null, prepare.convertArgs(array)));
            }
            
            public final T invoke() {
                return (T)this.prepare(0).convertResult(((StaticMethod)this.raw).invoker.invoke(null));
            }
            
            public final T invoke(final Object o) {
                final ParamsConverterList<T> prepare = this.prepare(1);
                if (prepare.args == null) {
                    return (T)prepare.convertResult(((StaticMethod)this.raw).invoker.invoke(null, o));
                }
                ((StaticMethod)this.raw).invoker.initializeInvoker();
                return (T)prepare.convertResult(((StaticMethod)this.raw).invoker.invoke(null, prepare.arg0.apply(o)));
            }
            
            public final T invoke(final Object o, final Object o2) {
                final ParamsConverterList<T> prepare = this.prepare(2);
                if (prepare.args == null) {
                    return (T)prepare.convertResult(((StaticMethod)this.raw).invoker.invoke(null, o, o2));
                }
                return (T)prepare.convertResult(((StaticMethod)this.raw).invoker.invoke(null, prepare.arg0.apply(o), prepare.arg1.apply(o2)));
            }
            
            public final T invoke(final Object o, final Object o2, final Object o3) {
                final ParamsConverterList<T> prepare = this.prepare(3);
                if (prepare.args == null) {
                    return (T)prepare.convertResult(((StaticMethod)this.raw).invoker.invoke(null, o, o2, o3));
                }
                return (T)prepare.convertResult(((StaticMethod)this.raw).invoker.invoke(null, prepare.arg0.apply(o), prepare.arg1.apply(o2), prepare.arg2.apply(o3)));
            }
            
            public final T invoke(final Object o, final Object o2, final Object o3, final Object o4) {
                final ParamsConverterList<T> prepare = this.prepare(4);
                if (prepare.args == null) {
                    return (T)prepare.convertResult(((StaticMethod)this.raw).invoker.invoke(null, o, o2, o3, o4));
                }
                return (T)prepare.convertResult(((StaticMethod)this.raw).invoker.invoke(null, prepare.arg0.apply(o), prepare.arg1.apply(o2), prepare.arg2.apply(o3), prepare.arg3.apply(o4)));
            }
            
            public final T invoke(final Object o, final Object o2, final Object o3, final Object o4, final Object o5) {
                final ParamsConverterList<T> prepare = this.prepare(5);
                if (prepare.args == null) {
                    return (T)prepare.convertResult(((StaticMethod)this.raw).invoker.invoke(null, o, o2, o3, o4, o5));
                }
                return (T)prepare.convertResult(((StaticMethod)this.raw).invoker.invoke(null, prepare.arg0.apply(o), prepare.arg1.apply(o2), prepare.arg2.apply(o3), prepare.arg3.apply(o4), prepare.arg4.apply(o5)));
            }
            
            @Override
            public final StaticMethod<Object> raw() {
                return (StaticMethod)this.raw;
            }
        }
    }
    
    public static final class Method<T> extends AbstractMethod<T>
    {
        public T invokeVA(final Object o, final Object... array) {
            return this.invoker.invokeVA(o, array);
        }
        
        public T invoke(final Object o) {
            return this.invoker.invoke(o);
        }
        
        public T invoke(final Object o, final Object o2) {
            return this.invoker.invoke(o, o2);
        }
        
        public T invoke(final Object o, final Object o2, final Object o3) {
            return this.invoker.invoke(o, o2, o3);
        }
        
        public T invoke(final Object o, final Object o2, final Object o3, final Object o4) {
            return this.invoker.invoke(o, o2, o3, o4);
        }
        
        public T invoke(final Object o, final Object o2, final Object o3, final Object o4, final Object o5) {
            return this.invoker.invoke(o, o2, o3, o4, o5);
        }
        
        public T invoke(final Object o, final Object o2, final Object o3, final Object o4, final Object o5, final Object o6) {
            return this.invoker.invoke(o, o2, o3, o4, o5, o6);
        }
        
        public static final class Converted<T> extends AbstractMethodConverter<Method<Object>, T>
        {
            public Converted() {
                super(new Method());
            }
            
            public final T invokeVA(final Object o, final Object... array) {
                final ParamsConverterList<T> prepare = this.prepare(array.length);
                return (T)prepare.convertResult(((Method)this.raw).invoker.invokeVA(o, prepare.convertArgs(array)));
            }
            
            public T invoke(final Object o) {
                return (T)this.prepare(0).convertResult(((Method)this.raw).invoker.invoke(o));
            }
            
            public T invoke(final Object o, final Object o2) {
                final ParamsConverterList<T> prepare = this.prepare(1);
                if (prepare.args == null) {
                    return (T)prepare.convertResult(((Method)this.raw).invoker.invoke(o, o2));
                }
                return (T)prepare.convertResult(((Method)this.raw).invoker.invoke(o, prepare.arg0.apply(o2)));
            }
            
            public T invoke(final Object o, final Object o2, final Object o3) {
                final ParamsConverterList<T> prepare = this.prepare(2);
                if (prepare.args == null) {
                    return (T)prepare.convertResult(((Method)this.raw).invoker.invoke(o, o2, o3));
                }
                return (T)prepare.convertResult(((Method)this.raw).invoker.invoke(o, prepare.arg0.apply(o2), prepare.arg1.apply(o3)));
            }
            
            public T invoke(final Object o, final Object o2, final Object o3, final Object o4) {
                final ParamsConverterList<T> prepare = this.prepare(3);
                if (prepare.args == null) {
                    return (T)prepare.convertResult(((Method)this.raw).invoker.invoke(o, o2, o3, o4));
                }
                return (T)prepare.convertResult(((Method)this.raw).invoker.invoke(o, prepare.arg0.apply(o2), prepare.arg1.apply(o3), prepare.arg2.apply(o4)));
            }
            
            public T invoke(final Object o, final Object o2, final Object o3, final Object o4, final Object o5) {
                final ParamsConverterList<T> prepare = this.prepare(4);
                if (prepare.args == null) {
                    return (T)prepare.convertResult(((Method)this.raw).invoker.invoke(o, o2, o3, o4, o5));
                }
                return (T)prepare.convertResult(((Method)this.raw).invoker.invoke(o, prepare.arg0.apply(o2), prepare.arg1.apply(o3), prepare.arg2.apply(o4), prepare.arg3.apply(o5)));
            }
            
            public T invoke(final Object o, final Object o2, final Object o3, final Object o4, final Object o5, final Object o6) {
                final ParamsConverterList<T> prepare = this.prepare(5);
                if (prepare.args == null) {
                    return (T)prepare.convertResult(((Method)this.raw).invoker.invoke(o, o2, o3, o4, o5, o6));
                }
                return (T)prepare.convertResult(((Method)this.raw).invoker.invoke(o, prepare.arg0.apply(o2), prepare.arg1.apply(o3), prepare.arg2.apply(o4), prepare.arg3.apply(o5), prepare.arg4.apply(o6)));
            }
            
            @Override
            public final Method<Object> raw() {
                return (Method)this.raw;
            }
        }
    }
    
    public static class EnumConstant<T extends Enum<?>> extends TemplateElement<FieldDeclaration>
    {
        protected T constant;
        private String constantName;
        
        public EnumConstant() {
            this.constantName = null;
        }
        
        public final T getSafe() {
            if (!this._hasClass) {
                return null;
            }
            try {
                return this.get();
            }
            catch (final RuntimeException ex) {
                return this.failGetSafe(ex, (T)null);
            }
        }
        
        public final T get() {
            if (this.constant != null) {
                return this.constant;
            }
            if (this.constantName == null) {
                throw new UnsupportedOperationException("Enumeration constant not initialized");
            }
            throw new UnsupportedOperationException("Enumeration constant " + this.constantName + " is not available");
        }
        
        @Override
        protected FieldDeclaration init(final Class<?> class1, final ClassDeclaration classDeclaration, final String str) {
            if (classDeclaration == null) {
                throw new IllegalArgumentException("ClassDeclaration is null");
            }
            this.constantName = str;
            final FieldDeclaration[] fields = classDeclaration.fields;
            final int length = fields.length;
            int i = 0;
            while (i < length) {
                final FieldDeclaration fieldDeclaration = fields[i];
                if (fieldDeclaration.isEnum && fieldDeclaration.name.real().equals(str)) {
                    if (classDeclaration.type.type == null) {
                        this.initFail("Enumeration constant " + str + " in class " + classDeclaration.type + " not initialized: class not found");
                        return null;
                    }
                    for (final Object o : classDeclaration.type.type.getEnumConstants()) {
                        if (((T)o).name().equals(fieldDeclaration.name.value())) {
                            this.constant = (T)o;
                            return fieldDeclaration;
                        }
                    }
                    this.initFail("Enumeration constant " + str + " missing in class " + classDeclaration.type);
                    return null;
                }
                else {
                    ++i;
                }
            }
            this.initFail("Failed to find enumeration field " + str + " in class " + classDeclaration.type);
            return null;
        }
        
        @Override
        public void forceInitialization() {
            this.get();
        }
        
        @Override
        public boolean isAvailable() {
            return this.constant != null;
        }
        
        public static final class Converted<T> extends TemplateElement<FieldDeclaration>
        {
            public final EnumConstant<Enum<?>> raw;
            protected DuplexConverter<?, T> converter;
            
            public Converted() {
                this.raw = new EnumConstant<Enum<?>>();
                this.converter = null;
            }
            
            public final T get() {
                final Enum<?> value = this.raw.get();
                try {
                    return (T)this.converter.convert(value);
                }
                catch (final RuntimeException ex) {
                    this.failNoConverter();
                    throw ex;
                }
            }
            
            public final T getSafe() {
                if (!this._hasClass) {
                    return null;
                }
                try {
                    return this.get();
                }
                catch (final RuntimeException ex) {
                    return this.failGetSafe(ex, (T)null);
                }
            }
            
            protected final void failNoConverter() {
                if (this.converter == null) {
                    throw new UnsupportedOperationException("Enum constant converter was not found");
                }
            }
            
            @Override
            public void forceInitialization() {
                this.raw.forceInitialization();
                this.failNoConverter();
            }
            
            @Override
            protected void initElementName(final String s) {
                super.initElementName(s);
                this.raw.initElementName(s);
            }
            
            @Override
            protected void initNoClass() {
                super.initNoClass();
                this.raw.initNoClass();
            }
            
            @Override
            protected FieldDeclaration init(final Class<?> class1, final ClassDeclaration classDeclaration, final String s) {
                if (classDeclaration == null) {
                    throw new IllegalArgumentException("ClassDeclaration is null");
                }
                final FieldDeclaration init = this.raw.init(class1, classDeclaration, s);
                if (init != null) {
                    final DuplexConverter<Object, ?> handleConverter = class1.getHandleConverter();
                    if (init.type.equals(handleConverter.input) && init.type.cast.equals(handleConverter.output)) {
                        this.converter = (DuplexConverter<?, T>)handleConverter;
                    }
                    else {
                        this.converter = (DuplexConverter<?, T>)Conversion.findDuplex(init.type, init.type.cast);
                        if (this.converter == null) {
                            MountiplexUtil.LOGGER.warning("Converter for enum constant " + init.name.toString() + " not found: " + init.type.toString());
                        }
                    }
                }
                return init;
            }
            
            @Override
            protected void setOptional() {
                super.setOptional();
                this.raw.setOptional();
            }
            
            @Override
            public boolean isAvailable() {
                return this.raw.isAvailable();
            }
        }
    }
    
    public static class StaticField<T> extends AbstractField<T>
    {
        public final T getSafe() {
            if (!this._hasClass) {
                return null;
            }
            try {
                return this.get();
            }
            catch (final RuntimeException ex) {
                return this.failGetSafe(ex, (T)null);
            }
        }
        
        public final T get() {
            return this.field.get(null);
        }
        
        public final void set(final T t) {
            this.field.set(null, t);
        }
        
        public static final class Converted<T> extends AbstractFieldConverter<StaticField<Object>, T>
        {
            public Converted() {
                super(new StaticField());
            }
            
            public final T getSafe() {
                if (!this._hasClass) {
                    return null;
                }
                try {
                    return this.get();
                }
                catch (final RuntimeException ex) {
                    return this.raw.failGetSafe(ex, (T)null);
                }
            }
            
            public final T get() {
                return (T)this.converter.converter.convert(((StaticField)this.raw).get());
            }
            
            public final void set(final T t) {
                ((StaticField)this.raw).set(this.reverse.converter.convert(t));
            }
            
            @Override
            public final StaticField<Object> raw() {
                return (StaticField)this.raw;
            }
        }
        
        public static final class Double extends StaticField<java.lang.Double>
        {
            public final double getDoubleSafe() {
                if (!this._hasClass) {
                    return 0.0;
                }
                try {
                    return this.getDouble();
                }
                catch (final RuntimeException ex) {
                    return this.failGetSafe(ex, 0.0);
                }
            }
            
            public final double getDouble() {
                return this.field.getDouble(null);
            }
            
            public final void setDouble(final double n) {
                this.field.setDouble(null, n);
            }
        }
        
        public static final class Float extends StaticField<java.lang.Float>
        {
            public final float getFloatSafe() {
                if (!this._hasClass) {
                    return 0.0f;
                }
                try {
                    return this.getFloat();
                }
                catch (final RuntimeException ex) {
                    return this.failGetSafe(ex, 0.0f);
                }
            }
            
            public final float getFloat() {
                return this.field.getFloat(null);
            }
            
            public final void setFloat(final float n) {
                this.field.setFloat(null, n);
            }
        }
        
        public static final class Byte extends StaticField<java.lang.Byte>
        {
            public final byte getByteSafe() {
                if (!this._hasClass) {
                    return 0;
                }
                try {
                    return this.getByte();
                }
                catch (final RuntimeException ex) {
                    return this.failGetSafe(ex, (java.lang.Byte)0);
                }
            }
            
            public final byte getByte() {
                return this.field.getByte(null);
            }
            
            public final void setByte(final byte b) {
                this.field.setByte(null, b);
            }
        }
        
        public static final class Short extends StaticField<java.lang.Short>
        {
            public final short getShortSafe() {
                if (!this._hasClass) {
                    return 0;
                }
                try {
                    return this.getShort();
                }
                catch (final RuntimeException ex) {
                    return this.failGetSafe(ex, (java.lang.Short)0);
                }
            }
            
            public final short getShort() {
                return this.field.getShort(null);
            }
            
            public final void setShort(final short n) {
                this.field.setShort(null, n);
            }
        }
        
        public static final class Integer extends StaticField<java.lang.Integer>
        {
            public final int getIntegerSafe() {
                if (!this._hasClass) {
                    return 0;
                }
                try {
                    return this.getInteger();
                }
                catch (final RuntimeException ex) {
                    return this.failGetSafe(ex, 0);
                }
            }
            
            public final int getInteger() {
                return this.field.getInteger(null);
            }
            
            public final void setInteger(final int n) {
                this.field.setInteger(null, n);
            }
        }
        
        public static final class Long extends StaticField<java.lang.Long>
        {
            public final long getLongSafe() {
                if (!this._hasClass) {
                    return 0L;
                }
                try {
                    return this.getLong();
                }
                catch (final RuntimeException ex) {
                    return this.failGetSafe(ex, 0L);
                }
            }
            
            public final long getLong() {
                return this.field.getLong(null);
            }
            
            public final void setLong(final long n) {
                this.field.setLong(null, n);
            }
        }
        
        public static final class Character extends StaticField<java.lang.Character>
        {
            public final char getCharacterSafe() {
                if (!this._hasClass) {
                    return '\0';
                }
                try {
                    return this.getCharacter();
                }
                catch (final RuntimeException ex) {
                    return this.failGetSafe(ex, '\0');
                }
            }
            
            public final char getCharacter() {
                return this.field.getCharacter(null);
            }
            
            public final void setCharacter(final char c) {
                this.field.setCharacter(null, c);
            }
        }
        
        public static final class Boolean extends StaticField<java.lang.Boolean>
        {
            public final boolean getBooleanSafe() {
                if (!this._hasClass) {
                    return false;
                }
                try {
                    return this.getBoolean();
                }
                catch (final RuntimeException ex) {
                    return this.failGetSafe(ex, false);
                }
            }
            
            public final boolean getBoolean() {
                return this.field.getBoolean(null);
            }
            
            public final void setBoolean(final boolean b) {
                this.field.setBoolean(null, b);
            }
        }
    }
    
    public static class Field<T> extends AbstractField<T>
    {
        public final T get(final Object o) {
            return this.field.get(o);
        }
        
        public final void set(final Object o, final T t) {
            this.field.set(o, t);
        }
        
        public void copy(final Object o, final Object o2) {
            this.field.copy(o, o2);
        }
        
        public static final class Converted<T> extends AbstractFieldConverter<Field<Object>, T>
        {
            public Converted() {
                super(new Field());
            }
            
            public final T get(final Object o) {
                return (T)this.converter.converter.convert(((Field)this.raw).get(o));
            }
            
            public final void set(final Object o, final T t) {
                ((Field)this.raw).set(o, this.reverse.converter.convert(t));
            }
            
            public final void copy(final Object o, final Object o2) {
                ((Field)this.raw).copy(o, o2);
            }
            
            @Override
            public final Field<Object> raw() {
                return (Field)this.raw;
            }
        }
        
        public static final class Double extends Field<java.lang.Double>
        {
            public final double getDouble(final Object o) {
                return this.field.getDouble(o);
            }
            
            public final void setDouble(final Object o, final double n) {
                this.field.setDouble(o, n);
            }
        }
        
        public static final class Float extends Field<java.lang.Float>
        {
            public final float getFloat(final Object o) {
                return this.field.getFloat(o);
            }
            
            public final void setFloat(final Object o, final float n) {
                this.field.setFloat(o, n);
            }
        }
        
        public static final class Byte extends Field<java.lang.Byte>
        {
            public final byte getByte(final Object o) {
                return this.field.getByte(o);
            }
            
            public final void setByte(final Object o, final byte b) {
                this.field.setByte(o, b);
            }
        }
        
        public static final class Short extends Field<java.lang.Short>
        {
            public final short getShort(final Object o) {
                return this.field.getShort(o);
            }
            
            public final void setShort(final Object o, final short n) {
                this.field.setShort(o, n);
            }
        }
        
        public static final class Integer extends Field<java.lang.Integer>
        {
            public final int getInteger(final Object o) {
                return this.field.getInteger(o);
            }
            
            public final void setInteger(final Object o, final int n) {
                this.field.setInteger(o, n);
            }
        }
        
        public static final class Long extends Field<java.lang.Long>
        {
            public final long getLong(final Object o) {
                return this.field.getLong(o);
            }
            
            public final void setLong(final Object o, final long n) {
                this.field.setLong(o, n);
            }
        }
        
        public static final class Character extends Field<java.lang.Character>
        {
            public final char getCharacter(final Object o) {
                return this.field.getCharacter(o);
            }
            
            public final void setCharacter(final Object o, final char c) {
                this.field.setCharacter(o, c);
            }
        }
        
        public static final class Boolean extends Field<java.lang.Boolean>
        {
            public final boolean getBoolean(final Object o) {
                return this.field.getBoolean(o);
            }
            
            public final void setBoolean(final Object o, final boolean b) {
                this.field.setBoolean(o, b);
            }
        }
    }
    
    @Retention(RetentionPolicy.RUNTIME)
    public @interface ImportList {
        Import[] value();
    }
    
    @Retention(RetentionPolicy.RUNTIME)
    @Repeatable(ImportList.class)
    public @interface Import {
        String value();
    }
    
    @Retention(RetentionPolicy.RUNTIME)
    public @interface Package {
        String value();
    }
    
    @Retention(RetentionPolicy.RUNTIME)
    public @interface Readonly {
    }
    
    @Retention(RetentionPolicy.RUNTIME)
    public @interface Optional {
    }
    
    @Retention(RetentionPolicy.RUNTIME)
    @Target({ ElementType.TYPE })
    public @interface RequirementsList {
        Require[] value();
    }
    
    @Retention(RetentionPolicy.RUNTIME)
    @Target({ ElementType.TYPE })
    @Repeatable(RequirementsList.class)
    public @interface Require {
        String declaring() default "";
        
        String value();
    }
    
    @Retention(RetentionPolicy.RUNTIME)
    public @interface Generated {
        String value() default "";
    }
    
    @Retention(RetentionPolicy.RUNTIME)
    public @interface InstanceType {
        String value();
    }
}
