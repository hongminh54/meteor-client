// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.mountiplex.reflection;

import java.util.stream.Stream;
import java.util.ArrayList;
import dev.imanity.antixray.libs.mountiplex.reflection.declarations.Declaration;
import dev.imanity.antixray.libs.mountiplex.reflection.util.StringBuffer;
import java.util.function.Predicate;
import java.lang.reflect.Method;
import java.util.Comparator;
import java.util.Collection;
import java.util.LinkedList;
import dev.imanity.antixray.libs.mountiplex.conversion.Converter;
import java.util.Iterator;
import dev.imanity.antixray.libs.mountiplex.reflection.util.asm.MPLType;
import java.util.logging.Level;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.lang.reflect.Field;
import java.util.function.Function;
import java.util.Collections;
import dev.imanity.antixray.libs.mountiplex.reflection.resolver.Resolver;
import dev.imanity.antixray.libs.mountiplex.MountiplexUtil;
import dev.imanity.antixray.libs.mountiplex.reflection.declarations.ClassResolver;
import java.util.Queue;
import dev.imanity.antixray.libs.mountiplex.reflection.declarations.MethodDeclaration;
import dev.imanity.antixray.libs.mountiplex.reflection.declarations.FieldDeclaration;
import dev.imanity.antixray.libs.mountiplex.reflection.util.NullInstantiator;
import dev.imanity.antixray.libs.mountiplex.reflection.util.fast.ClassFieldCopier;
import java.util.List;

public class ClassTemplate<T>
{
    private Class<T> type;
    private List<SafeField<?>> fields;
    private ClassFieldCopier<T> copier;
    private NullInstantiator<T> instantiator;
    private List<FieldDeclaration> typeFields;
    private List<MethodDeclaration> typeMethods;
    private Queue<FieldDeclaration> nextFieldQueue;
    private ClassResolver resolver;
    
    protected ClassTemplate() {
        this.resolver = new ClassResolver();
    }
    
    protected ClassTemplate<T> setClass(final Class<T> clazz) {
        this.type = clazz;
        this.instantiator = NullInstantiator.of((Class<? extends T>)clazz);
        this.fields = null;
        this.typeFields = null;
        (this.resolver = new ClassResolver()).setDeclaredClass(clazz);
        return this;
    }
    
    private ClassTemplate<T> setClassAndLog(final Class<?> class1, final String str, final String str2) {
        if (class1 == null) {
            MountiplexUtil.LOGGER.severe("Failed to find" + ((str.length() == 0) ? " " : (" " + str + " ")) + "Class '" + str2 + "'");
        }
        else {
            this.setClass((Class<T>)class1);
        }
        return this;
    }
    
    protected ClassTemplate<T> setClass(final String s) {
        return this.setClassAndLog(Resolver.loadClass(s, false), "", s);
    }
    
    public ClassTemplate<T> addImport(final String s) {
        this.resolver.addImport(s);
        return this;
    }
    
    public Class<T> getType() {
        return this.type;
    }
    
    public List<SafeField<?>> getFields() {
        if (this.fields == null) {
            if (this.type == null) {
                this.fields = Collections.emptyList();
            }
            else {
                this.fields = Collections.unmodifiableList((List<? extends SafeField<?>>)ReflectionUtil.getAllNonStaticFields(this.type).map((Function<? super Field, ?>)SafeField::new).collect((Collector<? super Object, ?, List<? extends T>>)Collectors.toList()));
            }
        }
        return this.fields;
    }
    
    public SafeField<?> getFieldAt(final int i) {
        final List<SafeField<?>> fields = this.getFields();
        if (i < 0 || i >= fields.size()) {
            throw new IllegalArgumentException("No field exists at index " + i);
        }
        return (SafeField<?>)fields.get(i);
    }
    
    public T newInstance() {
        if (this.type == null) {
            throw new IllegalStateException("Class was not found or is not set");
        }
        try {
            return this.type.newInstance();
        }
        catch (final Throwable thrown) {
            MountiplexUtil.LOGGER.log(Level.SEVERE, "Failed to initialize new instance of " + this.type, thrown);
            return null;
        }
    }
    
    public T newInstanceNull() {
        if (this.type == null) {
            throw new IllegalStateException("Class was not found or is not set");
        }
        return this.instantiator.create();
    }
    
    public T cloneInstance(final Object o) {
        final T instanceNull = this.newInstanceNull();
        this.transfer(o, instanceNull);
        return instanceNull;
    }
    
    public boolean isInstance(final Object o) {
        return this.type.isInstance(o);
    }
    
    public boolean isAssignableFrom(final Class<?> clazz) {
        return this.type.isAssignableFrom(clazz);
    }
    
    public boolean isType(final Object o) {
        return o != null && this.isType(o.getClass());
    }
    
    public boolean isType(final Class<?> obj) {
        return obj != null && this.type.equals(obj);
    }
    
    public T cast(final Object obj) {
        return this.type.cast(obj);
    }
    
    public T tryCast(final Object obj) {
        if (this.isInstance(obj)) {
            return this.type.cast(obj);
        }
        return null;
    }
    
    public void transfer(final Object o, final Object o2) {
        if (this.copier == null) {
            this.copier = ClassFieldCopier.of(this.type);
        }
        this.copier.copy((T)o, (T)o2);
    }
    
    public boolean isValid() {
        return this.type != null;
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(500);
        sb.append("Class path: ").append(MPLType.getName(this.getType())).append('\n');
        sb.append("Fields (").append(this.getFields().size()).append("):");
        final Iterator<SafeField<?>> iterator = this.getFields().iterator();
        while (iterator.hasNext()) {
            sb.append("\n    ").append(iterator.next().toString());
        }
        return sb.toString();
    }
    
    public SafeConstructor<T> getConstructor(final Class<?>... array) {
        return new SafeConstructor<T>(this.getType(), array);
    }
    
    public <K> SafeField<K> getField(final String s, final Class<?> clazz) {
        return new SafeField<K>(this.getType(), s, clazz);
    }
    
    public <K> SafeField<K> getField(final String s) {
        return new SafeField<K>(this.getType(), s, null);
    }
    
    public <K> SafeMethod<K> getMethod(final String s, final Class<?>... array) {
        return new SafeMethod<K>(this.getType(), s, array);
    }
    
    public <K> K getStaticFieldValue(final String s, final Class<K> clazz) {
        return SafeField.get(this.getType(), s, clazz);
    }
    
    public <K> K getStaticFieldValue(final String s, final Converter<?, K> converter) {
        return converter.convert(this.getStaticFieldValue(s, (Class<Object>)null));
    }
    
    public <K> void setStaticFieldValue(final String s, final K k) {
        SafeField.setStatic(this.getType(), s, k);
    }
    
    public static ClassTemplate<?> create(final String class1) {
        return new ClassTemplate<Object>().setClass(class1);
    }
    
    public static <T> ClassTemplate<T> create(final T t) {
        return new ClassTemplate<T>().setClass((Class<T>)t.getClass());
    }
    
    public static <T> ClassTemplate<T> create(final Class<T> class1) {
        return new ClassTemplate<T>().setClass(class1);
    }
    
    private void logFieldWarning(final String str, final String str2) {
        MountiplexUtil.LOGGER.warning("Field '" + str + "' in class " + ((this.type == null) ? "null" : MPLType.getName(this.type)) + " " + str2);
    }
    
    private void logMethodWarning(final String str, final String str2) {
        MountiplexUtil.LOGGER.warning("Method '" + str + "' in class " + ((this.type == null) ? "null" : MPLType.getName(this.type)) + " " + str2);
    }
    
    public Class<?> resolveClass(final String s) {
        return this.resolveClass(s, true);
    }
    
    public Class<?> resolveClass(final String str, final boolean b) {
        final Class<?> resolveClass = this.resolver.resolveClass(str);
        if (resolveClass == null && b) {
            MountiplexUtil.LOGGER.warning("Could not find type: " + str);
        }
        return resolveClass;
    }
    
    public String resolveClassName(final Class<?> clazz) {
        return this.resolver.resolveName(clazz);
    }
    
    private void loadFields(final boolean b) {
        if (this.typeFields == null) {
            this.typeFields = new LinkedList<FieldDeclaration>();
            if (this.type != null) {
                final Field[] declaredFields = this.type.getDeclaredFields();
                for (int length = declaredFields.length, i = 0; i < length; ++i) {
                    this.typeFields.add(new FieldDeclaration(this.resolver, declaredFields[i]));
                }
            }
        }
        if (b || this.nextFieldQueue == null) {
            this.nextFieldQueue = new LinkedList<FieldDeclaration>(this.typeFields);
        }
    }
    
    private Comparator<Method> createMethodComparator() {
        return new Comparator<Method>() {
            String paramsStr(final Method method) {
                final Class<?>[] parameterTypes = method.getParameterTypes();
                String s = "";
                for (int i = 0; i < parameterTypes.length; ++i) {
                    if (i > 0) {
                        s += ",";
                    }
                    s += ClassTemplate.this.resolveClassName(parameterTypes[i]);
                }
                return s;
            }
            
            @Override
            public int compare(final Method method, final Method method2) {
                final String paramsStr = this.paramsStr(method);
                final String paramsStr2 = this.paramsStr(method2);
                if (!paramsStr.equals(paramsStr2)) {
                    return paramsStr.compareTo(paramsStr2);
                }
                final String resolveClassName = ClassTemplate.this.resolveClassName(method.getReturnType());
                final String resolveClassName2 = ClassTemplate.this.resolveClassName(method2.getReturnType());
                if (resolveClassName.equals(resolveClassName2)) {
                    return MPLType.getName(method).compareTo(MPLType.getName(method2));
                }
                return resolveClassName.compareTo(resolveClassName2);
            }
        };
    }
    
    private void loadMethods() {
        if (this.typeMethods == null) {
            this.typeMethods = ReflectionUtil.getAllClassesAndInterfaces(this.getType()).flatMap(clazz -> ReflectionUtil.getDeclaredMethods(clazz).sorted(this.createMethodComparator())).filter((Predicate<? super Object>)ReflectionUtil.createDuplicateMethodFilter()).map(method -> new MethodDeclaration(this.resolver, method)).collect((Collector<? super Object, ?, List<MethodDeclaration>>)Collectors.toList());
        }
    }
    
    public <F> FieldAccessor<F> nextField(final String s) {
        if (this.type == null) {
            this.logFieldWarning(s, "can not be found because class to find it in is null");
            return (FieldAccessor<F>)SafeField.createNull(s);
        }
        final FieldDeclaration fieldDeclaration = new FieldDeclaration(this.resolver, StringBuffer.of(s));
        if (!fieldDeclaration.isValid()) {
            this.logFieldWarning(s, "could not be parsed");
            return (FieldAccessor<F>)SafeField.createNull(s);
        }
        if (!fieldDeclaration.isResolved()) {
            this.logFieldWarning(fieldDeclaration.toString(), "has some unresolved types");
            return (FieldAccessor<F>)SafeField.createNull(s);
        }
        final FieldDeclaration resolveName = fieldDeclaration.resolveName();
        this.loadFields(true);
        for (final FieldDeclaration fieldDeclaration2 : this.nextFieldQueue) {
            if (resolveName.match(fieldDeclaration2)) {
                while (this.nextFieldQueue.remove() != fieldDeclaration2) {}
                return new SafeField<F>(fieldDeclaration2.field);
            }
        }
        final ArrayList list = new ArrayList();
        for (final FieldDeclaration fieldDeclaration3 : this.typeFields) {
            if (resolveName.matchSignature(fieldDeclaration3)) {
                list.add(fieldDeclaration3);
            }
        }
        if (list.size() == 0) {
            for (final FieldDeclaration fieldDeclaration4 : this.typeFields) {
                if (resolveName.name.match(fieldDeclaration4.name)) {
                    list.add(fieldDeclaration4);
                }
            }
        }
        if (list.size() == 0) {
            this.logFieldWarning(s, "not found; no alternatives available. (Removed?)");
        }
        else {
            this.logFieldWarning(s, "not found; there are " + list.size() + " close matches:");
            final Iterator iterator4 = list.iterator();
            while (iterator4.hasNext()) {
                MountiplexUtil.LOGGER.warning("  - " + ((FieldDeclaration)iterator4.next()).toString());
            }
        }
        return (FieldAccessor<F>)SafeField.createNull(s);
    }
    
    public void skipFieldSignature(final String s) {
        this.nextFieldSignature(s);
    }
    
    public void skipField(final String s) {
        this.nextField(s);
    }
    
    public <F> FieldAccessor<F> nextFieldSignature(final String s) {
        this.loadFields(false);
        if (this.nextFieldQueue.isEmpty()) {
            this.logFieldWarning(s, "could not be found (no more fields)");
            return (FieldAccessor<F>)SafeField.createNull(s);
        }
        final FieldDeclaration fieldDeclaration = new FieldDeclaration(this.resolver, StringBuffer.of(s));
        if (!fieldDeclaration.isValid()) {
            this.logFieldWarning(s, "could not be parsed");
            return (FieldAccessor<F>)SafeField.createNull(s);
        }
        if (!fieldDeclaration.isResolved()) {
            this.logFieldWarning(fieldDeclaration.toString(), "has some unresolved types");
            return (FieldAccessor<F>)SafeField.createNull(s);
        }
        final FieldDeclaration resolveName = fieldDeclaration.resolveName();
        FieldDeclaration fieldDeclaration2 = this.nextFieldQueue.peek();
        if (resolveName.match(fieldDeclaration2)) {
            this.nextFieldQueue.remove();
        }
        else {
            fieldDeclaration2 = null;
            final ArrayList list = new ArrayList();
            while (!this.nextFieldQueue.isEmpty()) {
                final FieldDeclaration fieldDeclaration3 = this.nextFieldQueue.remove();
                if (resolveName.matchSignature(fieldDeclaration3)) {
                    fieldDeclaration2 = fieldDeclaration3;
                    break;
                }
                list.add(fieldDeclaration3);
            }
            if (fieldDeclaration2 == null) {
                this.logFieldWarning(s, "could not be found (no more fields)");
                return (FieldAccessor<F>)SafeField.createNull(s);
            }
            if (list.size() > 0) {
                this.logFieldWarning(s, "skipped " + list.size() + " fields during lookup:");
                final Iterator iterator = list.iterator();
                while (iterator.hasNext()) {
                    MountiplexUtil.LOGGER.warning("  - " + ((FieldDeclaration)iterator.next()).toString());
                }
            }
        }
        if (!resolveName.match(fieldDeclaration2)) {
            this.logFieldWarning(s, "has an incorrect name. New name: " + fieldDeclaration2.name.toString());
        }
        return new SafeField<F>(fieldDeclaration2.field);
    }
    
    public <F> F selectStaticValue(final String s) {
        final FieldAccessor<Object> selectField = this.selectField(s);
        return (F)(selectField.isValid() ? selectField.get(null) : null);
    }
    
    public <F> FieldAccessor<F> selectField(final String s) {
        final FieldDeclaration fieldDeclaration = new FieldDeclaration(this.resolver, StringBuffer.of(s));
        if (!fieldDeclaration.isValid()) {
            this.logFieldWarning(s, "could not be parsed");
            return (FieldAccessor<F>)SafeField.createNull(s);
        }
        if (!fieldDeclaration.isResolved()) {
            this.logFieldWarning(fieldDeclaration.toString(), "has some unresolved types");
            return (FieldAccessor<F>)SafeField.createNull(s);
        }
        final FieldDeclaration resolveName = fieldDeclaration.resolveName();
        this.loadFields(false);
        for (final FieldDeclaration fieldDeclaration2 : this.typeFields) {
            if (resolveName.match(fieldDeclaration2)) {
                return new SafeField<F>(fieldDeclaration2.field);
            }
        }
        final ArrayList list = new ArrayList();
        for (final FieldDeclaration fieldDeclaration3 : this.typeFields) {
            if (resolveName.matchSignature(fieldDeclaration3)) {
                list.add(fieldDeclaration3);
            }
        }
        if (list.size() == 0) {
            for (final FieldDeclaration fieldDeclaration4 : this.typeFields) {
                if (fieldDeclaration4.modifiers.isStatic() != resolveName.modifiers.isStatic()) {
                    continue;
                }
                if (!resolveName.name.match(fieldDeclaration4.name) || !resolveName.type.match(fieldDeclaration4.type)) {
                    continue;
                }
                list.add(fieldDeclaration4);
            }
        }
        if (list.size() == 0) {
            for (final FieldDeclaration fieldDeclaration5 : this.typeFields) {
                if (fieldDeclaration5.modifiers.isStatic() != resolveName.modifiers.isStatic()) {
                    continue;
                }
                if (!resolveName.type.match(fieldDeclaration5.type)) {
                    continue;
                }
                list.add(fieldDeclaration5);
            }
        }
        if (list.size() == 0) {
            this.logFieldWarning(s, "not found; no alternatives available. (Removed?)");
        }
        else {
            this.logFieldWarning(s, "not found; there are " + list.size() + " close matches:");
            final Iterator iterator5 = list.iterator();
            while (iterator5.hasNext()) {
                MountiplexUtil.LOGGER.warning("  - " + ((FieldDeclaration)iterator5.next()).toString());
            }
        }
        return (FieldAccessor<F>)SafeField.createNull(s);
    }
    
    public <M> MethodAccessor<M> selectMethod(final String s) {
        return new SafeMethod<M>(this.selectRawMethod(s, true));
    }
    
    public Method selectRawMethod(final String s, final boolean b) {
        final MethodDeclaration methodDeclaration = new MethodDeclaration(this.resolver, s);
        if (!methodDeclaration.isValid()) {
            if (b) {
                this.logMethodWarning(s, "could not be parsed");
            }
            return null;
        }
        if (!methodDeclaration.isResolved()) {
            if (b) {
                this.logMethodWarning(methodDeclaration.toString(), "has some unresolved types");
            }
            return null;
        }
        return this.selectRawMethod(methodDeclaration, b);
    }
    
    public Method selectRawMethod(MethodDeclaration resolveName, final boolean b) {
        resolveName = resolveName.resolveName();
        if (this.type == null) {
            if (b) {
                this.logMethodWarning(resolveName.toString(), "can not be loaded because this class is null");
            }
            return null;
        }
        this.loadMethods();
        for (final MethodDeclaration obj : this.typeMethods) {
            if (resolveName.match(obj) && obj.method != null) {
                try {
                    obj.method.setAccessible(true);
                    return obj.method;
                }
                catch (final SecurityException thrown) {
                    MountiplexUtil.LOGGER.log(Level.SEVERE, "Security exception trying to access method " + obj, thrown);
                }
            }
        }
        final ArrayList list = new ArrayList();
        for (final MethodDeclaration methodDeclaration : this.typeMethods) {
            if (resolveName.matchSignature(methodDeclaration)) {
                list.add(methodDeclaration);
            }
        }
        if (b) {
            if (list.size() == 0) {
                this.logMethodWarning(resolveName.toString(), "not found; no alternatives available. (Removed?)");
            }
            else {
                this.logMethodWarning(resolveName.toString(), "not found; there are " + list.size() + " close matches:");
                final Iterator iterator3 = list.iterator();
                while (iterator3.hasNext()) {
                    MountiplexUtil.LOGGER.warning("  - " + ((MethodDeclaration)iterator3.next()).toString());
                }
            }
        }
        return null;
    }
}
