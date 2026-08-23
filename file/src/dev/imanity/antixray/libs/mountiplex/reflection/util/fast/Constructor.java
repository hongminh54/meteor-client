// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.mountiplex.reflection.util.fast;

public interface Constructor<T>
{
    T newInstance();
    
    T newInstance(final Object p0);
    
    T newInstance(final Object p0, final Object p1);
    
    T newInstance(final Object p0, final Object p1, final Object p2);
    
    T newInstance(final Object p0, final Object p1, final Object p2, final Object p3);
    
    T newInstance(final Object p0, final Object p1, final Object p2, final Object p3, final Object p4);
    
    T newInstanceVA(final Object... p0);
}
