// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.mountiplex.reflection.util.fast;

public final class ConstantReturningInvoker<T> implements Invoker<T>
{
    private final T value;
    
    private ConstantReturningInvoker(final T value) {
        this.value = value;
    }
    
    public T getValue() {
        return this.value;
    }
    
    @Override
    public T invoke(final Object o) {
        return this.value;
    }
    
    @Override
    public T invoke(final Object o, final Object o2) {
        return this.value;
    }
    
    @Override
    public T invoke(final Object o, final Object o2, final Object o3) {
        return this.value;
    }
    
    @Override
    public T invoke(final Object o, final Object o2, final Object o3, final Object o4) {
        return this.value;
    }
    
    @Override
    public T invoke(final Object o, final Object o2, final Object o3, final Object o4, final Object o5) {
        return this.value;
    }
    
    @Override
    public T invoke(final Object o, final Object o2, final Object o3, final Object o4, final Object o5, final Object o6) {
        return this.value;
    }
    
    @Override
    public T invokeVA(final Object o, final Object... array) {
        return this.value;
    }
    
    public static <T> ConstantReturningInvoker<T> of(final T t) {
        return new ConstantReturningInvoker<T>(t);
    }
}
