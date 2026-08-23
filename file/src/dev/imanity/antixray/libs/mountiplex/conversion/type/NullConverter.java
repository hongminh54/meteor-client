// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.mountiplex.conversion.type;

import dev.imanity.antixray.libs.mountiplex.reflection.declarations.TypeDeclaration;

public final class NullConverter extends RawConverter
{
    public NullConverter(final Class<?> clazz, final Class<?> clazz2) {
        super(clazz, clazz2);
    }
    
    public NullConverter(final TypeDeclaration typeDeclaration, final TypeDeclaration typeDeclaration2) {
        super(typeDeclaration, typeDeclaration2);
    }
    
    @Override
    public final Object convertInput(final Object o) {
        return o;
    }
    
    @Override
    public int getCost() {
        return 0;
    }
}
