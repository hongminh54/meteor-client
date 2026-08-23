// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.mountiplex.reflection.util.fast;

import dev.imanity.antixray.libs.mountiplex.reflection.resolver.Resolver;
import dev.imanity.antixray.libs.mountiplex.reflection.util.BoxedType;
import dev.imanity.antixray.libs.mountiplex.reflection.util.asm.MPLType;
import java.lang.reflect.Modifier;
import java.lang.reflect.Field;

public class ReflectionAccessor<T> implements Copier, Reader<T>, Writer<T>
{
    private final Field f;
    
    protected ReflectionAccessor(final Field f) {
        this.f = f;
    }
    
    private RuntimeException checkInstance(final Object o) {
        if (Modifier.isStatic(this.f.getModifiers())) {
            if (o != null) {
                return new IllegalArgumentException("Instance should be null for static fields, but was " + MPLType.getName(o.getClass()) + " instead");
            }
        }
        else {
            if (o == null) {
                return new IllegalArgumentException("Instance can not be null for member fields declared in " + MPLType.getName(this.f.getDeclaringClass()));
            }
            if (!this.f.getDeclaringClass().isAssignableFrom(o.getClass())) {
                return new IllegalArgumentException("Instance of type " + MPLType.getName(o.getClass()) + " does not contain the field declared in " + MPLType.getName(this.f.getDeclaringClass()));
            }
        }
        return null;
    }
    
    private RuntimeException f(final Object o, final Throwable cause) {
        final RuntimeException checkInstance = this.checkInstance(o);
        if (checkInstance != null) {
            return checkInstance;
        }
        if (cause instanceof RuntimeException) {
            return (RuntimeException)cause;
        }
        return new RuntimeException("Failed to get field", cause);
    }
    
    private RuntimeException f(final Object o, final Object o2, final Throwable cause) {
        final RuntimeException checkInstance = this.checkInstance(o);
        if (checkInstance != null) {
            return checkInstance;
        }
        final Class<?> type = this.f.getType();
        if (type.isPrimitive()) {
            if (o2 == null) {
                return new IllegalArgumentException("Field primitive type " + type.getName() + " can not be assigned null");
            }
            final Class<?> unboxedType = BoxedType.getUnboxedType(o2.getClass());
            if (unboxedType == null || !type.isAssignableFrom(unboxedType)) {
                return new IllegalArgumentException("value type " + MPLType.getName(o2.getClass()) + " can not be assigned to primitive field type " + type.getName());
            }
        }
        else if (o2 != null && !type.isAssignableFrom(o2.getClass())) {
            return new IllegalArgumentException("value type " + MPLType.getName(o2.getClass()) + " can not be assigned to field type " + MPLType.getName(type));
        }
        if (cause instanceof RuntimeException) {
            return (RuntimeException)cause;
        }
        return new RuntimeException("Failed to set field", cause);
    }
    
    @Override
    public T get(final Object obj) {
        try {
            return (T)this.f.get(obj);
        }
        catch (final Throwable t) {
            throw this.f(obj, t);
        }
    }
    
    @Override
    public double getDouble(final Object obj) {
        try {
            return this.f.getDouble(obj);
        }
        catch (final Throwable t) {
            throw this.f(obj, t);
        }
    }
    
    @Override
    public float getFloat(final Object obj) {
        try {
            return this.f.getFloat(obj);
        }
        catch (final Throwable t) {
            throw this.f(obj, t);
        }
    }
    
    @Override
    public byte getByte(final Object obj) {
        try {
            return this.f.getByte(obj);
        }
        catch (final Throwable t) {
            throw this.f(obj, t);
        }
    }
    
    @Override
    public short getShort(final Object obj) {
        try {
            return this.f.getShort(obj);
        }
        catch (final Throwable t) {
            throw this.f(obj, t);
        }
    }
    
    @Override
    public int getInteger(final Object obj) {
        try {
            return this.f.getInt(obj);
        }
        catch (final Throwable t) {
            throw this.f(obj, t);
        }
    }
    
    @Override
    public long getLong(final Object obj) {
        try {
            return this.f.getLong(obj);
        }
        catch (final Throwable t) {
            throw this.f(obj, t);
        }
    }
    
    @Override
    public char getCharacter(final Object obj) {
        try {
            return this.f.getChar(obj);
        }
        catch (final Throwable t) {
            throw this.f(obj, t);
        }
    }
    
    @Override
    public boolean getBoolean(final Object obj) {
        try {
            return this.f.getBoolean(obj);
        }
        catch (final Throwable t) {
            throw this.f(obj, t);
        }
    }
    
    @Override
    public Field getReadField() {
        return this.f;
    }
    
    @Override
    public void checkCanRead() {
    }
    
    @Override
    public void set(final Object obj, final T value) {
        try {
            this.f.set(obj, value);
        }
        catch (final Throwable t) {
            throw this.f(obj, value, t);
        }
    }
    
    @Override
    public void setDouble(final Object obj, final double n) {
        try {
            this.f.setDouble(obj, n);
        }
        catch (final Throwable t) {
            throw this.f(obj, n, t);
        }
    }
    
    @Override
    public void setFloat(final Object obj, final float n) {
        try {
            this.f.setFloat(obj, n);
        }
        catch (final Throwable t) {
            throw this.f(obj, n, t);
        }
    }
    
    @Override
    public void setByte(final Object obj, final byte b) {
        try {
            this.f.setByte(obj, b);
        }
        catch (final Throwable t) {
            throw this.f(obj, b, t);
        }
    }
    
    @Override
    public void setShort(final Object obj, final short n) {
        try {
            this.f.setShort(obj, n);
        }
        catch (final Throwable t) {
            throw this.f(obj, n, t);
        }
    }
    
    @Override
    public void setInteger(final Object obj, final int n) {
        try {
            this.f.setInt(obj, n);
        }
        catch (final Throwable t) {
            throw this.f(obj, n, t);
        }
    }
    
    @Override
    public void setLong(final Object obj, final long n) {
        try {
            this.f.setLong(obj, n);
        }
        catch (final Throwable t) {
            throw this.f(obj, n, t);
        }
    }
    
    @Override
    public void setCharacter(final Object obj, final char c) {
        try {
            this.f.setChar(obj, c);
        }
        catch (final Throwable t) {
            throw this.f(obj, c, t);
        }
    }
    
    @Override
    public void setBoolean(final Object obj, final boolean b) {
        try {
            this.f.setBoolean(obj, b);
        }
        catch (final Throwable t) {
            throw this.f(obj, b, t);
        }
    }
    
    @Override
    public Field getWriteField() {
        return this.f;
    }
    
    @Override
    public void checkCanWrite() {
    }
    
    @Override
    public void copy(final Object o, final Object o2) {
        this.set(o2, this.get(o));
    }
    
    @Override
    public Field getCopyField() {
        return this.f;
    }
    
    @Override
    public void checkCanCopy() {
    }
    
    public static <T> ReflectionAccessor<T> create(final Field field) {
        final int modifiers = field.getModifiers();
        if (Resolver.isPublic(field) || (Modifier.isFinal(modifiers) && Modifier.isStatic(modifiers))) {
            return (ReflectionAccessor<T>)GeneratedAccessor.create(field);
        }
        return new ReflectionAccessor<T>(field);
    }
}
