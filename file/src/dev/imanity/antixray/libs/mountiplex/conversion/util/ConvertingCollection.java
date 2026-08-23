// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.mountiplex.conversion.util;

import dev.imanity.antixray.libs.mountiplex.MountiplexUtil;
import dev.imanity.antixray.libs.mountiplex.conversion.Converter;
import java.util.Iterator;
import dev.imanity.antixray.libs.mountiplex.conversion.type.DuplexConverter;
import java.util.Collection;

public class ConvertingCollection<T> implements Collection<T>
{
    private final Collection<Object> base;
    protected final DuplexConverter<Object, T> converter;
    
    public ConvertingCollection(final Collection<?> base, final DuplexConverter<?, T> converter) {
        this.base = (Collection<Object>)base;
        this.converter = (DuplexConverter<Object, T>)converter;
    }
    
    public DuplexConverter<Object, T> getElementConverter() {
        return this.converter;
    }
    
    public Collection<Object> getBase() {
        return this.base;
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
    public void clear() {
        this.base.clear();
    }
    
    @Override
    public boolean add(final T t) {
        return this.base.add(this.converter.convertReverse(t));
    }
    
    @Override
    public boolean remove(final Object o) {
        return this.base.remove(this.converter.convertReverse(o));
    }
    
    @Override
    public boolean contains(final Object o) {
        return this.base.contains(this.converter.convertReverse(o));
    }
    
    @Override
    public Iterator<T> iterator() {
        return new ConvertingIterator<T>(this.base.iterator(), this.converter);
    }
    
    @Override
    public boolean containsAll(final Collection<?> collection) {
        return this.base.containsAll(new ConvertingCollection<Object>(collection, this.converter.reverse()));
    }
    
    @Override
    public boolean addAll(final Collection<? extends T> collection) {
        return this.base.addAll(new ConvertingCollection<Object>(collection, this.converter.reverse()));
    }
    
    @Override
    public boolean removeAll(final Collection<?> collection) {
        return this.base.removeAll(new ConvertingCollection<Object>(collection, this.converter.reverse()));
    }
    
    @Override
    public boolean retainAll(final Collection<?> collection) {
        return MountiplexUtil.retainAll(this, collection);
    }
    
    @Override
    public Object[] toArray() {
        return MountiplexUtil.toArray(this);
    }
    
    @Override
    public <K> K[] toArray(final K[] array) {
        return MountiplexUtil.toArray(this, array);
    }
}
