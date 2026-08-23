// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.mountiplex.reflection;

import java.lang.reflect.Method;

public interface MethodAccessor<T>
{
    boolean isMethod(final Method p0);
    
    boolean isValid();
    
    T invoke(final Object p0, final Object... p1);
}
