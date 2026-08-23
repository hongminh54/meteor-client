// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.mountiplex.conversion.util;

import dev.imanity.antixray.libs.mountiplex.conversion.type.DuplexConverter;
import java.util.ListIterator;

public class ConvertingListIterator<T> implements ListIterator<T>
{
    private final ListIterator<Object> iter;
    private final DuplexConverter<Object, T> converter;
    
    public ConvertingListIterator(final ListIterator<?> iter, final DuplexConverter<?, T> converter) {
        this.iter = (ListIterator<Object>)iter;
        this.converter = (DuplexConverter<Object, T>)converter;
    }
    
    @Override
    public boolean hasNext() {
        return this.iter.hasNext();
    }
    
    @Override
    public T next() {
        return this.converter.convert(this.iter.next());
    }
    
    @Override
    public boolean hasPrevious() {
        return this.iter.hasPrevious();
    }
    
    @Override
    public T previous() {
        return this.converter.convert(this.iter.previous());
    }
    
    @Override
    public int nextIndex() {
        return this.iter.nextIndex();
    }
    
    @Override
    public int previousIndex() {
        return this.iter.previousIndex();
    }
    
    @Override
    public void remove() {
        this.iter.remove();
    }
    
    @Override
    public void set(final T t) {
        this.iter.set(this.converter.convertReverse(t));
    }
    
    @Override
    public void add(final T t) {
        this.iter.add(this.converter.convertReverse(t));
    }
}
