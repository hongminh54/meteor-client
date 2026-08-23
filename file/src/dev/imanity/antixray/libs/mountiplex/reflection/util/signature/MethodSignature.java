// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.mountiplex.reflection.util.signature;

import java.util.Arrays;
import dev.imanity.antixray.libs.mountiplex.reflection.util.asm.MPLType;
import java.lang.reflect.Method;

public final class MethodSignature
{
    private final String name;
    private final Class<?>[] parameterTypes;
    
    public MethodSignature(final String name, final Class<?>[] parameterTypes) {
        this.name = name;
        this.parameterTypes = parameterTypes;
    }
    
    public MethodSignature(final Method method) {
        this.name = MPLType.getName(method);
        this.parameterTypes = method.getParameterTypes();
    }
    
    public String getName() {
        return this.name;
    }
    
    public Class<?>[] getParameterTypes() {
        return this.parameterTypes;
    }
    
    @Override
    public int hashCode() {
        return this.name.hashCode();
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o instanceof MethodSignature) {
            final MethodSignature methodSignature = (MethodSignature)o;
            return this.name.equals(methodSignature.name) && Arrays.equals(this.parameterTypes, methodSignature.parameterTypes);
        }
        return false;
    }
}
