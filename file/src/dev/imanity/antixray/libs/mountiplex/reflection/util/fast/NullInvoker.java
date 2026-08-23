// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.mountiplex.reflection.util.fast;

import dev.imanity.antixray.libs.mountiplex.reflection.util.BoxedType;

public class NullInvoker<T> implements Invoker<T>
{
    private final T nullObject;
    
    public NullInvoker() {
        this.nullObject = null;
    }
    
    public NullInvoker(final T nullObject) {
        this.nullObject = nullObject;
    }
    
    public NullInvoker(final Class<T> clazz) {
        this.nullObject = BoxedType.getDefaultValue(clazz);
    }
    
    @Override
    public T invoke(final Object o) {
        return this.nullObject;
    }
    
    @Override
    public T invoke(final Object o, final Object o2) {
        return this.nullObject;
    }
    
    @Override
    public T invoke(final Object o, final Object o2, final Object o3) {
        return this.nullObject;
    }
    
    @Override
    public T invoke(final Object o, final Object o2, final Object o3, final Object o4) {
        return this.nullObject;
    }
    
    @Override
    public T invoke(final Object o, final Object o2, final Object o3, final Object o4, final Object o5) {
        return this.nullObject;
    }
    
    @Override
    public T invoke(final Object o, final Object o2, final Object o3, final Object o4, final Object o5, final Object o6) {
        return this.nullObject;
    }
    
    @Override
    public T invokeVA(final Object o, final Object... array) {
        return this.nullObject;
    }
}
