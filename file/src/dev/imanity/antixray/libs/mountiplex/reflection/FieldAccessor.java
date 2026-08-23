// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.mountiplex.reflection;

import dev.imanity.antixray.libs.mountiplex.reflection.declarations.Template;
import dev.imanity.antixray.libs.mountiplex.conversion.type.DuplexConverter;

public interface FieldAccessor<T>
{
    T get(final Object p0);
    
    boolean set(final Object p0, final T p1);
    
    default boolean isValid() {
        return true;
    }
    
    default T transfer(final Object o, final Object o2) {
        final T value = this.get(o2);
        this.set(o2, this.get(o));
        return value;
    }
    
    default <K> TranslatorFieldAccessor<K> translate(final DuplexConverter<?, K> duplexConverter) {
        return new TranslatorFieldAccessor<K>(this, duplexConverter);
    }
    
    default FieldAccessor<T> ignoreInvalid(final T t) {
        if (this.isValid()) {
            return this;
        }
        return new IgnoredFieldAccessor<T>(t);
    }
    
    default <T> FieldAccessor<T> wrapOptionalMethods(final Template.AbstractMethod<T> abstractMethod, final Template.AbstractMethod<Void> abstractMethod2, final T t) {
        if (abstractMethod.isAvailable() && abstractMethod2.isAvailable()) {
            return wrapMethods(abstractMethod, abstractMethod2);
        }
        if (!abstractMethod.isAvailable() && !abstractMethod2.isAvailable()) {
            return new SafeDirectField<T>() {
                @Override
                public T get(final Object o) {
                    return t;
                }
                
                @Override
                public boolean set(final Object o, final T t) {
                    return false;
                }
            };
        }
        return new SafeDirectField<T>() {
            @Override
            public T get(final Object o) {
                if (abstractMethod.isAvailable()) {
                    return (T)abstractMethod.invoker.invoke(o);
                }
                return t;
            }
            
            @Override
            public boolean set(final Object o, final T t) {
                if (abstractMethod2.isAvailable()) {
                    abstractMethod2.invoker.invoke(o, t);
                    return true;
                }
                return false;
            }
        };
    }
    
    default <T> FieldAccessor<T> wrapMethods(final Template.AbstractMethod<T> abstractMethod, final Template.AbstractMethod<Void> abstractMethod2) {
        return new SafeDirectField<T>() {
            final /* synthetic */ boolean val$valid = abstractMethod.isAvailable() && abstractMethod2.isAvailable();
            
            @Override
            public T get(final Object o) {
                return (T)abstractMethod.invoker.invoke(o);
            }
            
            @Override
            public boolean set(final Object o, final T t) {
                abstractMethod2.invoker.invoke(o, t);
                return true;
            }
            
            @Override
            public boolean isValid() {
                return this.val$valid;
            }
        };
    }
    
    default <T> FieldAccessor<T> wrapMethods(final Template.Method.Converted<T> converted, final Template.Method.Converted<Void> converted2) {
        return new SafeDirectField<T>() {
            final /* synthetic */ boolean val$valid = converted.isAvailable() && converted2.isAvailable();
            
            @Override
            public T get(final Object o) {
                return converted.invoke(o);
            }
            
            @Override
            public boolean set(final Object o, final T t) {
                converted2.invoke(o, t);
                return true;
            }
            
            @Override
            public boolean isValid() {
                return this.val$valid;
            }
        };
    }
    
    default <T> FieldAccessor<T> wrapMethods(final MethodAccessor<T> methodAccessor, final MethodAccessor<Void> methodAccessor2) {
        return new SafeDirectField<T>() {
            final /* synthetic */ boolean val$valid = methodAccessor.isValid() && methodAccessor2.isValid();
            
            @Override
            public T get(final Object o) {
                return methodAccessor.invoke(o, new Object[0]);
            }
            
            @Override
            public boolean set(final Object o, final T t) {
                methodAccessor2.invoke(o, t);
                return true;
            }
            
            @Override
            public boolean isValid() {
                return this.val$valid;
            }
        };
    }
}
