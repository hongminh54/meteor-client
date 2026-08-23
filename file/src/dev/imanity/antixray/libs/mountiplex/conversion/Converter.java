// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.mountiplex.conversion;

import dev.imanity.antixray.libs.mountiplex.reflection.util.BoxedType;
import dev.imanity.antixray.libs.mountiplex.reflection.declarations.TypeDeclaration;
import java.util.function.Function;

public abstract class Converter<I, O> implements Function<Object, O>
{
    public final TypeDeclaration input;
    public final TypeDeclaration output;
    
    public Converter(final Class<?> clazz, final Class<?> clazz2) {
        this(TypeDeclaration.fromClass(clazz), TypeDeclaration.fromClass(clazz2));
    }
    
    public Converter(final TypeDeclaration input, final TypeDeclaration output) {
        this.input = input;
        this.output = output;
    }
    
    public abstract O convertInput(final I p0);
    
    public final O convert(final Object o) {
        O o2 = null;
        if (o != null) {
            Class<?> clazz = this.input.type;
            if (clazz.isPrimitive()) {
                clazz = BoxedType.getBoxedType(clazz);
            }
            if (clazz.isAssignableFrom(o.getClass())) {
                o2 = this.convertInput(o);
            }
        }
        else if (this.acceptsNullInput()) {
            o2 = this.convertInput(null);
        }
        if (o2 == null && this.output.isPrimitive) {
            o2 = BoxedType.getDefaultValue(this.output.type);
        }
        return o2;
    }
    
    @Override
    public final O apply(final Object o) {
        O o2 = null;
        if (o != null) {
            Class<?> clazz = this.input.type;
            if (clazz.isPrimitive()) {
                clazz = BoxedType.getBoxedType(clazz);
            }
            if (clazz.isAssignableFrom(o.getClass())) {
                o2 = this.convertInput(o);
            }
        }
        else if (this.acceptsNullInput()) {
            o2 = this.convertInput(null);
        }
        if (o2 == null && this.output.isPrimitive) {
            o2 = BoxedType.getDefaultValue(this.output.type);
        }
        return o2;
    }
    
    public final O convert(final Object o, final O o2) {
        O o3 = null;
        if (o != null) {
            Class<?> clazz = this.input.type;
            if (clazz.isPrimitive()) {
                clazz = BoxedType.getBoxedType(clazz);
            }
            if (clazz.isAssignableFrom(o.getClass())) {
                o3 = this.convertInput(o);
            }
        }
        else if (this.acceptsNullInput()) {
            o3 = this.convertInput(null);
        }
        if (o3 == null) {
            o3 = o2;
            if (o3 == null && this.output.isPrimitive) {
                o3 = BoxedType.getDefaultValue(this.output.type);
            }
        }
        return o3;
    }
    
    public boolean isLazy() {
        return false;
    }
    
    public int getCost() {
        return this.isLazy() ? 100 : 1;
    }
    
    public boolean acceptsNullInput() {
        return false;
    }
    
    @Override
    public String toString() {
        return this.getClass().getName() + "[cost=" + this.getCost() + ", " + this.input.toString() + " -> " + this.output.toString() + "]";
    }
}
