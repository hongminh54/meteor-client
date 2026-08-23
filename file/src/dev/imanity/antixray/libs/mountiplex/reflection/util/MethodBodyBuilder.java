// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.mountiplex.reflection.util;

import dev.imanity.antixray.libs.mountiplex.reflection.resolver.Resolver;
import dev.imanity.antixray.libs.mountiplex.reflection.ReflectionUtil;

public final class MethodBodyBuilder
{
    public final StringBuilder builder;
    
    public MethodBodyBuilder() {
        this.builder = new StringBuilder();
    }
    
    public MethodBodyBuilder appendEnd() {
        this.builder.append(";\n");
        return this;
    }
    
    public MethodBodyBuilder append(final String str) {
        this.builder.append(str);
        return this;
    }
    
    public MethodBodyBuilder appendFieldName(final String str, final String str2) {
        this.builder.append(str).append(str2);
        return this;
    }
    
    public MethodBodyBuilder append(final char c) {
        this.builder.append(c);
        return this;
    }
    
    public MethodBodyBuilder append(final int i) {
        this.builder.append(i);
        return this;
    }
    
    public MethodBodyBuilder appendBoxPrimitive(final Class<?> clazz, final String str, final String str2) {
        this.builder.append(BoxedType.getBoxedType(clazz).getSimpleName());
        this.builder.append(".valueOf(").append(str).append(str2).append(')');
        return this;
    }
    
    public MethodBodyBuilder appendUnboxPrimitive(final Class<?> clazz) {
        this.builder.append('.').append(clazz.getSimpleName());
        this.builder.append("Value()");
        return this;
    }
    
    public MethodBodyBuilder appendTypeName(final Class<?> clazz) {
        return this.append(ReflectionUtil.getTypeName(clazz));
    }
    
    public MethodBodyBuilder appendTypeCast(final Class<?> clazz) {
        this.builder.append('(').append(ReflectionUtil.getTypeName(clazz)).append(')');
        return this;
    }
    
    public MethodBodyBuilder appendBoxPrimitive(final Class<?> clazz, final String str) {
        this.builder.append(BoxedType.getBoxedType(clazz).getSimpleName());
        this.builder.append(".valueOf(").append(str).append(')');
        return this;
    }
    
    public MethodBodyBuilder appendAccessibleTypeName(final Class<?> clazz) {
        return this.append(ReflectionUtil.getAccessibleTypeName(clazz));
    }
    
    public MethodBodyBuilder appendAccessibleTypeCast(final Class<?> clazz) {
        if (clazz != Object.class && Resolver.isPublic(clazz)) {
            final String typeName = ReflectionUtil.getTypeName(clazz);
            if (Resolver.resolveClassPath(typeName).equals(typeName)) {
                this.builder.append('(').append(typeName).append(')');
            }
            else {
                this.builder.append('(');
                this.builder.append("MPL_NOREMAP$").append(typeName);
                this.builder.append(')');
            }
        }
        return this;
    }
    
    @Override
    public String toString() {
        return this.builder.toString();
    }
    
    @Override
    public int hashCode() {
        return this.builder.hashCode();
    }
    
    @Override
    public boolean equals(final Object o) {
        return o == this || (o instanceof MethodBodyBuilder && this.builder.equals(((MethodBodyBuilder)o).builder));
    }
}
