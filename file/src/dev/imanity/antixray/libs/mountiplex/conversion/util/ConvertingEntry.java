// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.mountiplex.conversion.util;

import dev.imanity.antixray.libs.mountiplex.conversion.type.DuplexConverter;
import java.util.Map;

public class ConvertingEntry<K, V> implements Map.Entry<K, V>
{
    private final Map.Entry<Object, Object> base;
    private final DuplexConverter<Object, K> keyConverter;
    private final DuplexConverter<Object, V> valueConverter;
    
    public ConvertingEntry(final Map.Entry<?, ?> base, final DuplexConverter<?, K> keyConverter, final DuplexConverter<?, V> valueConverter) {
        this.base = (Map.Entry<Object, Object>)base;
        this.keyConverter = (DuplexConverter<Object, K>)keyConverter;
        this.valueConverter = (DuplexConverter<Object, V>)valueConverter;
    }
    
    @Override
    public K getKey() {
        return this.keyConverter.convert(this.base.getKey());
    }
    
    @Override
    public V getValue() {
        return this.valueConverter.convert(this.base.getValue());
    }
    
    @Override
    public V setValue(final V v) {
        return this.valueConverter.convert(this.base.setValue(this.valueConverter.convertReverse(v)));
    }
}
