// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.mountiplex.conversion.type;

import dev.imanity.antixray.libs.mountiplex.reflection.declarations.TypeDeclaration;
import dev.imanity.antixray.libs.mountiplex.conversion.Converter;

public class DisabledConverter<I, O> extends Converter<I, O>
{
    private final String _message;
    
    public DisabledConverter(final Class<?> clazz, final Class<?> clazz2, final String message) {
        super(clazz, clazz2);
        this._message = message;
    }
    
    public DisabledConverter(final TypeDeclaration typeDeclaration, final TypeDeclaration typeDeclaration2, final String message) {
        super(typeDeclaration, typeDeclaration2);
        this._message = message;
    }
    
    @Override
    public final O convertInput(final I n) {
        throw new UnsupportedOperationException(this._message);
    }
    
    @Override
    public final boolean acceptsNullInput() {
        return true;
    }
}
