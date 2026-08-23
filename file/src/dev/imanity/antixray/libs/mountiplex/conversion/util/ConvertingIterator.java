// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.mountiplex.conversion.util;

import dev.imanity.antixray.libs.mountiplex.conversion.Converter;
import java.util.Iterator;

public class ConvertingIterator<T> implements Iterator<T>
{
    private final Iterator<?> iter;
    private final Converter<Object, T> converter;
    
    public ConvertingIterator(final Iterator<?> iter, final Converter<?, T> converter) {
        this.iter = iter;
        this.converter = (Converter<Object, T>)converter;
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
    public void remove() {
        this.iter.remove();
    }
}
