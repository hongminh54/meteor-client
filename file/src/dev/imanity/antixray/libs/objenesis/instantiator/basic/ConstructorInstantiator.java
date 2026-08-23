// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.objenesis.instantiator.basic;

import dev.imanity.antixray.libs.objenesis.ObjenesisException;
import java.lang.reflect.Constructor;
import dev.imanity.antixray.libs.objenesis.instantiator.annotations.Typology;
import dev.imanity.antixray.libs.objenesis.instantiator.annotations.Instantiator;
import dev.imanity.antixray.libs.objenesis.instantiator.ObjectInstantiator;

@Instantiator(Typology.NOT_COMPLIANT)
public class ConstructorInstantiator<T> implements ObjectInstantiator<T>
{
    protected Constructor<T> constructor;
    
    public ConstructorInstantiator(final Class<T> clazz) {
        try {
            this.constructor = clazz.getDeclaredConstructor((Class<?>[])null);
        }
        catch (final Exception ex) {
            throw new ObjenesisException(ex);
        }
    }
    
    @Override
    public T newInstance() {
        try {
            return this.constructor.newInstance((Object[])null);
        }
        catch (final Exception ex) {
            throw new ObjenesisException(ex);
        }
    }
}
