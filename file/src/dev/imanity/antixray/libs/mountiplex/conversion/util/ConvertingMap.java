// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.mountiplex.conversion.util;

import dev.imanity.antixray.libs.mountiplex.conversion.builtin.EntryConverter;
import java.util.Collection;
import java.util.Set;
import java.util.Iterator;
import dev.imanity.antixray.libs.mountiplex.conversion.type.DuplexConverter;
import java.util.Map;

public class ConvertingMap<K, V> implements Map<K, V>
{
    private final Map<Object, Object> base;
    protected final DuplexConverter<Object, K> keyConverter;
    protected final DuplexConverter<Object, V> valueConverter;
    
    public ConvertingMap(final Map<?, ?> base, final DuplexConverter<?, K> keyConverter, final DuplexConverter<?, V> valueConverter) {
        this.base = (Map<Object, Object>)base;
        this.keyConverter = (DuplexConverter<Object, K>)keyConverter;
        this.valueConverter = (DuplexConverter<Object, V>)valueConverter;
    }
    
    @Override
    public int size() {
        return this.base.size();
    }
    
    @Override
    public boolean isEmpty() {
        return this.base.isEmpty();
    }
    
    @Override
    public boolean containsKey(final Object o) {
        return this.base.containsKey(this.keyConverter.convertReverse(o));
    }
    
    @Override
    public boolean containsValue(final Object o) {
        return this.base.containsValue(this.valueConverter.convertReverse(o));
    }
    
    @Override
    public V get(final Object o) {
        return this.valueConverter.convert(this.base.get(this.keyConverter.convertReverse(o)));
    }
    
    @Override
    public V put(final K k, final V v) {
        return this.valueConverter.convert(this.base.put(this.keyConverter.convertReverse(k), this.valueConverter.convertReverse(v)));
    }
    
    @Override
    public V remove(final Object o) {
        return this.valueConverter.convert(this.base.remove(this.keyConverter.convertReverse(o)));
    }
    
    @Override
    public void putAll(final Map<? extends K, ? extends V> map) {
        for (final Entry entry : map.entrySet()) {
            this.put(entry.getKey(), entry.getValue());
        }
    }
    
    @Override
    public void clear() {
        this.base.clear();
    }
    
    @Override
    public Set<K> keySet() {
        return new ConvertingSet<K>(this.base.keySet(), this.keyConverter);
    }
    
    @Override
    public Collection<V> values() {
        return new ConvertingCollection<V>(this.base.values(), this.valueConverter);
    }
    
    @Override
    public Set<Entry<K, V>> entrySet() {
        return new ConvertingSet<Entry<K, V>>(this.base.entrySet(), (DuplexConverter<?, Entry<K, V>>)EntryConverter.create(this.keyConverter, this.valueConverter));
    }
}
