// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.mountiplex.reflection;

import java.lang.reflect.Method;

public abstract class SafeDirectMethod<T> implements MethodAccessor<T>
{
    @Override
    public boolean isValid() {
        return true;
    }
    
    @Override
    public boolean isMethod(final Method method) {
        return false;
    }
}
