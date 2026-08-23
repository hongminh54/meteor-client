// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.mountiplex.conversion.type;

import dev.imanity.antixray.libs.mountiplex.reflection.declarations.TypeDeclaration;
import dev.imanity.antixray.libs.mountiplex.conversion.Converter;

public abstract class InputConverter<T> extends Converter<Object, T>
{
    public InputConverter(final TypeDeclaration typeDeclaration, final TypeDeclaration typeDeclaration2) {
        super(typeDeclaration, typeDeclaration2);
    }
    
    public InputConverter(final TypeDeclaration typeDeclaration) {
        super(TypeDeclaration.OBJECT, typeDeclaration);
    }
    
    public Converter<?, T> getNullConverter() {
        return null;
    }
    
    @Override
    public boolean acceptsNullInput() {
        return this.getNullConverter() != null;
    }
    
    public abstract Converter<?, T> getConverter(final TypeDeclaration p0);
    
    public final <I> Converter<I, T> getConverter(final Class<I> clazz) {
        return (Converter<I, T>)this.getConverter(TypeDeclaration.fromClass(clazz));
    }
    
    public boolean canConvert(final TypeDeclaration typeDeclaration) {
        return this.getConverter(typeDeclaration) != null;
    }
    
    public boolean canConvert(final Class<?> clazz) {
        return this.getConverter(clazz) != null;
    }
    
    @Override
    public final T convertInput(final Object o) {
        Converter<?, T> converter;
        if (o == null) {
            converter = this.getNullConverter();
        }
        else {
            if (this.output.isAssignableFrom(o)) {
                return (T)o;
            }
            converter = this.getConverter(TypeDeclaration.fromClass(o.getClass()));
        }
        if (converter != null) {
            return converter.convertInput(o);
        }
        return null;
    }
}
