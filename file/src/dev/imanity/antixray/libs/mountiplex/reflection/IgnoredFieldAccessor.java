// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.mountiplex.reflection;

import dev.imanity.antixray.libs.mountiplex.conversion.type.DuplexConverter;

public class IgnoredFieldAccessor<T> implements FieldAccessor<T>
{
    private final T defaultValue;
    
    public IgnoredFieldAccessor(final T defaultValue) {
        this.defaultValue = defaultValue;
    }
    
    @Override
    public boolean isValid() {
        return false;
    }
    
    @Override
    public T get(final Object o) {
        return this.defaultValue;
    }
    
    @Override
    public boolean set(final Object o, final T t) {
        return false;
    }
    
    @Override
    public T transfer(final Object o, final Object o2) {
        return this.defaultValue;
    }
    
    @Override
    public <K> TranslatorFieldAccessor<K> translate(final DuplexConverter<?, K> duplexConverter) {
        return new TranslatorFieldAccessor<K>(this, duplexConverter);
    }
    
    @Override
    public FieldAccessor<T> ignoreInvalid(final T t) {
        return new IgnoredFieldAccessor(t);
    }
}
