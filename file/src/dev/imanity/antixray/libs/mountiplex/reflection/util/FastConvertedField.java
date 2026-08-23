// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.mountiplex.reflection.util;

import dev.imanity.antixray.libs.mountiplex.conversion.type.DuplexConverter;

public class FastConvertedField<T> implements IgnoresRemapping
{
    private final FastField<Object> field;
    private final DuplexConverter<Object, T> converter;
    
    public FastConvertedField(final FastField<?> field, final DuplexConverter<?, T> converter) {
        this.field = (FastField<Object>)field;
        this.converter = (DuplexConverter<Object, T>)converter;
    }
    
    public T get(final Object o) {
        return this.converter.convertInput(this.field.get(o));
    }
    
    public void set(final Object o, final T t) {
        this.field.set(o, this.converter.convertOutput(t));
    }
    
    public byte getByte(final Object o) {
        return this.get(o);
    }
    
    public short getShort(final Object o) {
        return this.get(o);
    }
    
    public int getInteger(final Object o) {
        return this.get(o);
    }
    
    public long getLong(final Object o) {
        return this.get(o);
    }
    
    public char getCharacter(final Object o) {
        return this.get(o);
    }
    
    public float getFloat(final Object o) {
        return this.get(o);
    }
    
    public double getDouble(final Object o) {
        return this.get(o);
    }
    
    public void setByte(final Object o, final byte b) {
        this.set(o, b);
    }
    
    public void setShort(final Object o, final short s) {
        this.set(o, s);
    }
    
    public void setInteger(final Object o, final int i) {
        this.set(o, i);
    }
    
    public void setLong(final Object o, final long l) {
        this.set(o, l);
    }
    
    public void setCharacter(final Object o, final char c) {
        this.set(o, c);
    }
    
    public void setFloat(final Object o, final float f) {
        this.set(o, f);
    }
    
    public void setDouble(final Object o, final double d) {
        this.set(o, d);
    }
}
