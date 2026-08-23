// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.mountiplex.conversion.util;

import java.util.ListIterator;
import java.util.Collection;
import dev.imanity.antixray.libs.mountiplex.conversion.type.DuplexConverter;
import java.util.List;

public class ConvertingList<T> extends ConvertingCollection<T> implements List<T>
{
    public ConvertingList(final List<?> list, final DuplexConverter<?, T> duplexConverter) {
        super(list, duplexConverter);
    }
    
    @Override
    public List<Object> getBase() {
        return (List)super.getBase();
    }
    
    @Override
    public boolean addAll(final int n, final Collection<? extends T> collection) {
        return this.getBase().addAll(n, new ConvertingCollection<Object>(collection, this.converter.reverse()));
    }
    
    @Override
    public T get(final int n) {
        return this.converter.convert(this.getBase().get(n));
    }
    
    @Override
    public T set(final int n, final T t) {
        return this.converter.convert(this.getBase().set(n, this.converter.convertReverse(t)));
    }
    
    @Override
    public void add(final int n, final T t) {
        this.getBase().add(n, this.converter.convert(t));
    }
    
    @Override
    public T remove(final int n) {
        return this.converter.convert(this.getBase().remove(n));
    }
    
    @Override
    public int indexOf(final Object o) {
        return this.getBase().indexOf(this.converter.convertReverse(o));
    }
    
    @Override
    public int lastIndexOf(final Object o) {
        return this.getBase().lastIndexOf(this.converter.convertReverse(o));
    }
    
    @Override
    public ListIterator<T> listIterator() {
        return new ConvertingListIterator<T>(this.getBase().listIterator(), this.converter);
    }
    
    @Override
    public ListIterator<T> listIterator(final int n) {
        return new ConvertingListIterator<T>(this.getBase().listIterator(n), this.converter);
    }
    
    @Override
    public List<T> subList(final int n, final int n2) {
        return new ConvertingList(this.getBase().subList(n, n2), (DuplexConverter<?, Object>)this.converter);
    }
}
