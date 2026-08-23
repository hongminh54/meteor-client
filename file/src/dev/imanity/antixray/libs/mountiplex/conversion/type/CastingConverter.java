// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.mountiplex.conversion.type;

import dev.imanity.antixray.libs.mountiplex.reflection.declarations.TypeDeclaration;
import dev.imanity.antixray.libs.mountiplex.conversion.Converter;

public final class CastingConverter<T> extends Converter<Object, T>
{
    public CastingConverter(final Class<?> clazz, final Class<?> clazz2) {
        super(clazz, clazz2);
    }
    
    public CastingConverter(final TypeDeclaration typeDeclaration, final TypeDeclaration typeDeclaration2) {
        super(typeDeclaration, typeDeclaration2);
    }
    
    @Override
    public T convertInput(final Object o) {
        if (this.output.isAssignableFrom(o)) {
            return (T)o;
        }
        return null;
    }
}
