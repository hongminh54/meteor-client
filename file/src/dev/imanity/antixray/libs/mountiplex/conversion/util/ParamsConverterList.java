// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.mountiplex.conversion.util;

import java.util.function.Supplier;
import dev.imanity.antixray.libs.mountiplex.reflection.declarations.ParameterDeclaration;
import dev.imanity.antixray.libs.mountiplex.conversion.type.NullConverter;
import dev.imanity.antixray.libs.mountiplex.MountiplexUtil;
import dev.imanity.antixray.libs.mountiplex.conversion.Conversion;
import dev.imanity.antixray.libs.mountiplex.reflection.declarations.ParameterListDeclaration;
import dev.imanity.antixray.libs.mountiplex.reflection.declarations.TypeDeclaration;
import java.util.function.Function;

public class ParamsConverterList<T>
{
    public Function<Object, T> result;
    public Function<Object, ?>[] args;
    public Function<Object, ?> arg0;
    public Function<Object, ?> arg1;
    public Function<Object, ?> arg2;
    public Function<Object, ?> arg3;
    public Function<Object, ?> arg4;
    public boolean valid;
    private static final Supplier UNINITIALIZED_SUPPLIER;
    
    public ParamsConverterList(final String s, final TypeDeclaration typeDeclaration, final ParameterListDeclaration parameterListDeclaration) {
        this.result = null;
        this.args = null;
        this.arg0 = null;
        this.arg1 = null;
        this.arg2 = null;
        this.arg3 = null;
        this.arg4 = null;
        this.valid = true;
        this.result = null;
        this.args = null;
        this.arg0 = null;
        this.arg1 = null;
        this.arg2 = null;
        this.arg3 = null;
        this.arg4 = null;
        if (typeDeclaration.cast != null) {
            this.result = (Function<Object, T>)Conversion.find(typeDeclaration, typeDeclaration.cast);
            if (this.result == null) {
                this.valid = false;
                MountiplexUtil.LOGGER.warning("Converter for " + s + " return type not found: " + typeDeclaration.toString());
            }
            else if (this.result instanceof NullConverter) {
                this.result = null;
            }
        }
        final ParameterDeclaration[] parameters = parameterListDeclaration.parameters;
        this.args = new Function[parameters.length];
        boolean b = false;
        for (int i = 0; i < parameters.length; ++i) {
            if (parameters[i].type.cast != null) {
                this.args[i] = Conversion.find(parameters[i].type.cast, parameters[i].type);
                if (this.args[i] == null) {
                    this.valid = false;
                    MountiplexUtil.LOGGER.warning("Converter for " + s + " argument " + parameters[i].name.toString() + " not found: " + parameters[i].type.toString());
                }
                else if (this.args[i] instanceof NullConverter) {
                    this.args[i] = (Function<Object, ?>)Function.identity();
                }
                else {
                    b = true;
                }
            }
            else {
                this.args[i] = (Function<Object, ?>)Function.identity();
            }
        }
        if (b) {
            if (parameters.length >= 1) {
                this.arg0 = this.args[0];
            }
            if (parameters.length >= 2) {
                this.arg1 = this.args[1];
            }
            if (parameters.length >= 3) {
                this.arg2 = this.args[2];
            }
            if (parameters.length >= 4) {
                this.arg3 = this.args[3];
            }
            if (parameters.length >= 5) {
                this.arg4 = this.args[4];
            }
        }
        else {
            this.args = null;
        }
    }
    
    public final T convertResult(final Object o) {
        if (this.result != null) {
            return this.result.apply(o);
        }
        return (T)o;
    }
    
    public final Object[] convertArgs(final Object[] array) {
        if (this.args != null) {
            final Object[] array2 = new Object[array.length];
            for (int i = 0; i < array2.length; ++i) {
                array2[i] = this.args[i].apply(array[i]);
            }
            return array2;
        }
        return array;
    }
    
    static {
        UNINITIALIZED_SUPPLIER = new Supplier() {
            @Override
            public ParamsConverterList get() {
                throw new UnsupportedOperationException("Converters have not been initialized");
            }
            
            @Override
            public boolean available() {
                return false;
            }
        };
    }
    
    public interface Supplier<T>
    {
        ParamsConverterList<T> get();
        
        boolean available();
        
        default <T> Supplier<T> uninitialized() {
            return ParamsConverterList.UNINITIALIZED_SUPPLIER;
        }
        
        default <T> Supplier<T> unsupported(final String s) {
            return new Supplier<T>() {
                @Override
                public ParamsConverterList<T> get() {
                    throw new UnsupportedOperationException(s);
                }
                
                @Override
                public boolean available() {
                    return false;
                }
            };
        }
        
        default <T> Supplier<T> of(final ParamsConverterList<T> list) {
            return new Supplier<T>() {
                @Override
                public ParamsConverterList<T> get() {
                    return list;
                }
                
                @Override
                public boolean available() {
                    return true;
                }
            };
        }
        
        default <T> Supplier<T> lazy(final java.util.function.Supplier<Supplier<T>> supplier) {
            return new Supplier<T>() {
                @Override
                public ParamsConverterList<T> get() {
                    return supplier.get().get();
                }
                
                @Override
                public boolean available() {
                    return supplier.get().available();
                }
            };
        }
    }
}
