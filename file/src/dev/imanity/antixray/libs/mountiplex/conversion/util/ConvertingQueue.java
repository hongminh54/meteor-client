// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.mountiplex.conversion.util;

import java.util.Collection;
import dev.imanity.antixray.libs.mountiplex.conversion.type.DuplexConverter;
import java.util.Queue;

public class ConvertingQueue<T> extends ConvertingCollection<T> implements Queue<T>
{
    public ConvertingQueue(final Queue<?> queue, final DuplexConverter<?, T> duplexConverter) {
        super(queue, duplexConverter);
    }
    
    @Override
    public Queue<Object> getBase() {
        return (Queue)super.getBase();
    }
    
    @Override
    public boolean offer(final T t) {
        return this.getBase().offer(this.converter.convertReverse(t));
    }
    
    @Override
    public T remove() {
        return this.converter.convert(this.getBase().remove());
    }
    
    @Override
    public T poll() {
        return this.converter.convert(this.getBase().poll());
    }
    
    @Override
    public T element() {
        return this.converter.convert(this.getBase().element());
    }
    
    @Override
    public T peek() {
        return this.converter.convert(this.getBase().peek());
    }
}
