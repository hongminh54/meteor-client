// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.mountiplex.reflection;

import dev.imanity.antixray.libs.mountiplex.conversion.Converter;
import dev.imanity.antixray.libs.mountiplex.conversion.type.DuplexConverter;

public class TranslatorFieldAccessor<T> implements FieldAccessor<T>
{
    private final FieldAccessor<Object> base;
    private final DuplexConverter<Object, T> converterPair;
    
    public TranslatorFieldAccessor(final FieldAccessor<?> fieldAccessor, final Converter<?, ?> converter, final Converter<?, T> converter2) {
        this(fieldAccessor, DuplexConverter.pair(converter2, converter));
    }
    
    public TranslatorFieldAccessor(final FieldAccessor<?> base, final DuplexConverter<?, T> converterPair) {
        if (base == null) {
            throw new IllegalArgumentException("Can not construct using a null base");
        }
        if (converterPair == null) {
            throw new IllegalArgumentException("Can not construct using a null converter pair");
        }
        this.base = (FieldAccessor<Object>)base;
        this.converterPair = (DuplexConverter<Object, T>)converterPair;
    }
    
    @Override
    public boolean isValid() {
        return this.base.isValid();
    }
    
    public Object getInternal(final Object o) {
        return this.base.get(o);
    }
    
    public boolean setInternal(final Object o, final Object o2) {
        return this.base.set(o, o2);
    }
    
    @Override
    public T get(final Object o) {
        return this.converterPair.convert(this.getInternal(o));
    }
    
    @Override
    public boolean set(final Object o, final T t) {
        return this.setInternal(o, this.converterPair.convertReverse(t));
    }
    
    @Override
    public T transfer(final Object o, final Object o2) {
        return this.converterPair.convert(this.base.transfer(o, o2));
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
}
