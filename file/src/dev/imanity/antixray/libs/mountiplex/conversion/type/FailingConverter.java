// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.mountiplex.conversion.type;

import dev.imanity.antixray.libs.mountiplex.reflection.declarations.TypeDeclaration;
import dev.imanity.antixray.libs.mountiplex.conversion.Converter;

public final class FailingConverter<I, O> extends Converter<I, O>
{
    private final String message;
    private static final FailingConverter UNINITIALIZED;
    
    private FailingConverter(final TypeDeclaration typeDeclaration, final TypeDeclaration typeDeclaration2, final String message) {
        super(typeDeclaration, typeDeclaration2);
        this.message = message;
    }
    
    @Override
    public O convertInput(final I n) {
        throw new UnsupportedOperationException(this.message);
    }
    
    public String getMessage() {
        return this.message;
    }
    
    public static <I, O> FailingConverter<I, O> create(final TypeDeclaration typeDeclaration, final TypeDeclaration typeDeclaration2, final String s) {
        return new FailingConverter<I, O>(typeDeclaration, typeDeclaration2, s);
    }
    
    public static <I, O> FailingConverter<I, O> uninitialized() {
        return FailingConverter.UNINITIALIZED;
    }
    
    static {
        UNINITIALIZED = new FailingConverter(TypeDeclaration.OBJECT, TypeDeclaration.OBJECT, "Converter has not been initialized");
    }
}
