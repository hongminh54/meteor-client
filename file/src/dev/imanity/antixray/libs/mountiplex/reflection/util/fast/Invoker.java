// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.mountiplex.reflection.util.fast;

import dev.imanity.antixray.libs.mountiplex.reflection.util.LazyInitializedObject;

public interface Invoker<T> extends LazyInitializedObject
{
    default Invoker<T> initializeInvoker() {
        return this;
    }
    
    default void forceInitialization() {
        this.initializeInvoker();
    }
    
    default T invoke(final Object o) {
        return this.invokeVA(o, new Object[0]);
    }
    
    default T invoke(final Object o, final Object o2) {
        return this.invokeVA(o, o2);
    }
    
    default T invoke(final Object o, final Object o2, final Object o3) {
        return this.invokeVA(o, o2, o3);
    }
    
    default T invoke(final Object o, final Object o2, final Object o3, final Object o4) {
        return this.invokeVA(o, o2, o3, o4);
    }
    
    default T invoke(final Object o, final Object o2, final Object o3, final Object o4, final Object o5) {
        return this.invokeVA(o, o2, o3, o4, o5);
    }
    
    default T invoke(final Object o, final Object o2, final Object o3, final Object o4, final Object o5, final Object o6) {
        return this.invokeVA(o, o2, o3, o4, o5, o6);
    }
    
    T invokeVA(final Object p0, final Object... p1);
}
