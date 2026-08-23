// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.mountiplex.reflection;

import dev.imanity.antixray.libs.mountiplex.conversion.type.DuplexConverter;

public abstract class SafeDirectField<T> implements FieldAccessor<T>
{
    @Override
    public boolean isValid() {
        return true;
    }
    
    @Override
    public T transfer(final Object o, final Object o2) {
        final T value = this.get(o2);
        this.set(o2, this.get(o));
        return value;
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
