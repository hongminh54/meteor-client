// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.mountiplex.conversion.type;

import dev.imanity.antixray.libs.mountiplex.conversion.Converter;
import dev.imanity.antixray.libs.mountiplex.reflection.declarations.TypeDeclaration;

public abstract class InputOutputConverter<I, O> extends InputConverter<O>
{
    public InputOutputConverter(final TypeDeclaration typeDeclaration, final TypeDeclaration typeDeclaration2) {
        super(typeDeclaration, typeDeclaration2);
    }
    
    public abstract O convert(final I p0, final TypeDeclaration p1, final TypeDeclaration p2);
    
    @Override
    public Converter<?, O> getConverter(final TypeDeclaration typeDeclaration) {
        if (typeDeclaration.isInstanceOf(this.output)) {
            return (Converter<?, O>)new NullConverter(typeDeclaration, this.output);
        }
        return new ElementConverter(typeDeclaration, this.output);
    }
    
    private final class ElementConverter extends Converter<I, O>
    {
        public ElementConverter(final TypeDeclaration typeDeclaration, final TypeDeclaration typeDeclaration2) {
            super(typeDeclaration, typeDeclaration2);
        }
        
        @Override
        public O convertInput(final I n) {
            return InputOutputConverter.this.convert(n, this.input, this.output);
        }
    }
}
