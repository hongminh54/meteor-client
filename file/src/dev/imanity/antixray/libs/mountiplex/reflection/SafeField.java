// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.mountiplex.reflection;

import dev.imanity.antixray.libs.mountiplex.conversion.type.DuplexConverter;
import java.lang.reflect.Modifier;
import dev.imanity.antixray.libs.mountiplex.reflection.util.asm.MPLType;
import dev.imanity.antixray.libs.mountiplex.reflection.util.BoxedType;
import dev.imanity.antixray.libs.mountiplex.reflection.resolver.Resolver;
import java.util.logging.Level;
import dev.imanity.antixray.libs.mountiplex.MountiplexUtil;
import java.lang.reflect.Field;
import dev.imanity.antixray.libs.mountiplex.reflection.util.FastField;

public class SafeField<T> implements FieldAccessor<T>
{
    private final FastField<T> field;
    
    public SafeField(final FastField<T> field) {
        if (field == null) {
            throw new IllegalArgumentException("Backing field can not be null");
        }
        this.field = field;
    }
    
    public SafeField(final Field field) {
        (this.field = new FastField<T>()).init(field);
    }
    
    public SafeField(final String s, final Class<?> clazz) {
        this.field = new FastField<T>();
        if (s == null || s.isEmpty() || !s.contains(".")) {
            MountiplexUtil.LOGGER.log(Level.SEVERE, "Field path contains no class: " + s);
            return;
        }
        try {
            final String lastBefore = MountiplexUtil.getLastBefore(s, ".");
            this.load(Resolver.loadClass(lastBefore, false), s.substring(lastBefore.length() + 1), clazz);
        }
        catch (final Throwable thrown) {
            MountiplexUtil.LOGGER.log(Level.SEVERE, "Failed to load field '" + s + "'", thrown);
        }
    }
    
    public SafeField(final Object o, final String s, final Class<?> clazz) {
        this.field = new FastField<T>();
        this.load((o == null) ? null : o.getClass(), s, clazz);
    }
    
    public SafeField(final Class<?> clazz, final String s, final Class<?> clazz2) {
        this.field = new FastField<T>();
        this.load(clazz, s, clazz2);
    }
    
    private void load(final Class<?> clazz, final String s, final Class<?> obj) {
        if (clazz == null) {
            MountiplexUtil.LOGGER.log(Level.WARNING, "Can not load field '" + s + "' because the class is null!", new Exception());
            return;
        }
        final String resolveFieldName = Resolver.resolveFieldName(clazz, s);
        final String str = s.equals(resolveFieldName) ? s : (s + ":" + resolveFieldName);
        this.field.init(findRaw(clazz, resolveFieldName));
        if (this.field.getField() == null) {
            if (obj == null) {
                this.field.initUnavailable("[???] " + clazz.getSimpleName() + "::" + s + "[???]");
            }
            else {
                this.field.initUnavailable(obj.getSimpleName() + " " + clazz.getSimpleName() + "::" + s + "[???]");
            }
        }
        else {
            if (obj == null) {
                return;
            }
            final Class<T> type = this.field.getType();
            if (type.equals(obj)) {
                return;
            }
            if (BoxedType.tryBoxType(type).equals(BoxedType.tryBoxType(obj))) {
                return;
            }
            MountiplexUtil.LOGGER.log(Level.WARNING, "Field '" + s + "' in class " + MPLType.getName(clazz) + " is of type " + type.getSimpleName() + " while we expect type " + obj.getSimpleName());
            this.field.initUnavailable(obj.getSimpleName() + "[?] " + clazz.getSimpleName() + "::" + s);
        }
        MountiplexUtil.LOGGER.warning("Field '" + str + "' could not be found in class " + MPLType.getName(clazz));
    }
    
    @Override
    public boolean isValid() {
        return this.field.getField() != null;
    }
    
    public boolean isStatic() {
        return this.field.isStatic();
    }
    
    @Override
    public T transfer(final Object o, final Object o2) {
        if (this.field.getField() == null) {
            return null;
        }
        final T value = this.get(o2);
        this.set(o2, this.get(o));
        return value;
    }
    
    @Override
    public T get(final Object o) {
        try {
            return this.field.get(o);
        }
        catch (final RuntimeException thrown) {
            MountiplexUtil.LOGGER.log(Level.SEVERE, "Failed to get field " + this.field.getDescription(), thrown);
            return null;
        }
    }
    
    @Override
    public boolean set(final Object o, final T t) {
        try {
            this.field.set(o, t);
            return true;
        }
        catch (final RuntimeException thrown) {
            MountiplexUtil.LOGGER.log(Level.SEVERE, "Failed to set field " + this.field.getDescription(), thrown);
            return false;
        }
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(20);
        final Field field = this.field.getField();
        if (field == null) {
            return "null";
        }
        final int modifiers = field.getModifiers();
        if (Modifier.isPublic(modifiers)) {
            sb.append("public ");
        }
        else if (Modifier.isPrivate(modifiers)) {
            sb.append("private ");
        }
        else if (Modifier.isProtected(modifiers)) {
            sb.append("protected ");
        }
        if (Modifier.isStatic(modifiers)) {
            sb.append("static ");
        }
        return sb.append(MPLType.getName(field.getType())).append(" ").append(MPLType.getName(field)).toString();
    }
    
    public String getName() {
        return this.field.getName();
    }
    
    public Class<?> getType() {
        return this.field.getType();
    }
    
    public FastField<T> getFastField() {
        return this.field;
    }
    
    @Override
    public <K> TranslatorFieldAccessor<K> translate(final DuplexConverter<?, K> duplexConverter) {
        return new TranslatorFieldAccessor<K>(this, duplexConverter);
    }
    
    @Override
    public FieldAccessor<T> ignoreInvalid(final T t) {
        if (this.isValid()) {
            return this;
        }
        return new IgnoredFieldAccessor<T>(t);
    }
    
    public static <T> void set(final Object o, final String s, final T t) {
        if (t == null) {
            new SafeField<T>(o, s, null).set(o, t);
        }
        else {
            new SafeField<T>(o, s, t.getClass()).set(o, t);
        }
    }
    
    public static <T> void setStatic(final Class<?> clazz, final String s, final T t) {
        if (t == null) {
            new SafeField<T>(clazz, s, null).set(null, t);
        }
        else {
            new SafeField<T>(clazz, s, t.getClass()).set(null, t);
        }
    }
    
    public static <T> T get(final Object o, final String s, final Class<T> clazz) {
        return new SafeField<T>(o, s, clazz).get(o);
    }
    
    public static <T> T get(final Class<?> clazz, final String s, final Class<T> clazz2) {
        return new SafeField<T>(clazz, s, clazz2).get(null);
    }
    
    public static <T> SafeField<T> create(final Class<?> clazz, final String s, final Class<T> clazz2) {
        return new SafeField<T>(clazz, s, clazz2);
    }
    
    public static <T> TranslatorFieldAccessor<T> create(final Class<?> clazz, final String s, final DuplexConverter<?, T> duplexConverter) {
        return create(clazz, s, duplexConverter.output.type).translate(duplexConverter);
    }
    
    public static boolean contains(final Class<?> clazz, final String s, final Class<?> clazz2) {
        final Field raw = findRaw(clazz, Resolver.resolveFieldName(clazz, s));
        return raw != null && (clazz2 == null || clazz2.equals(raw.getType()));
    }
    
    private static Field findRaw(final Class<?> clazz, final String s) {
        Class<?> superclass = clazz;
        while (superclass != null) {
            try {
                return MPLType.getDeclaredField(superclass, s);
            }
            catch (final NoSuchFieldException ex) {
                superclass = superclass.getSuperclass();
                continue;
            }
            break;
        }
        return null;
    }
    
    public static <T> SafeField<T> createNull(final String s) {
        final FastField fastField = new FastField();
        fastField.initUnavailable(s);
        return new SafeField<T>(fastField);
    }
    
    @Deprecated
    public static <T> SafeField<T> createNull() {
        return new SafeField<T>((Field)null);
    }
}
