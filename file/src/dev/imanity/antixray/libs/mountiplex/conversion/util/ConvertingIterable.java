// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.mountiplex.conversion.util;

import java.util.Iterator;
import dev.imanity.antixray.libs.mountiplex.conversion.Converter;

public class ConvertingIterable<T> implements Iterable<T>
{
    private final Iterable<?> base;
    private final Converter<?, T> converter;
    
    public ConvertingIterable(final Iterable<?> base, final Converter<?, T> converter) {
        this.base = base;
        this.converter = converter;
    }
    
    public Converter<?, T> getConverter() {
        return this.converter;
    }
    
    @Override
    public Iterator<T> iterator() {
        return new ConvertingIterator<T>(this.base.iterator(), this.converter);
    }
}
