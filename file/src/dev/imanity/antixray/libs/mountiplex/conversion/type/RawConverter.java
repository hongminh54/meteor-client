// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.mountiplex.conversion.type;

import dev.imanity.antixray.libs.mountiplex.reflection.declarations.TypeDeclaration;
import dev.imanity.antixray.libs.mountiplex.conversion.Converter;

public abstract class RawConverter extends Converter<Object, Object>
{
    public RawConverter(final Class<?> clazz, final Class<?> clazz2) {
        super(TypeDeclaration.fromClass(clazz), TypeDeclaration.fromClass(clazz2));
    }
    
    public RawConverter(final TypeDeclaration typeDeclaration, final TypeDeclaration typeDeclaration2) {
        super(typeDeclaration, typeDeclaration2);
    }
    
    @Override
    public abstract Object convertInput(final Object p0);
}
