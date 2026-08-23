// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.objenesis.instantiator.sun;

import dev.imanity.antixray.libs.objenesis.ObjenesisException;
import java.lang.reflect.Constructor;
import dev.imanity.antixray.libs.objenesis.instantiator.annotations.Typology;
import dev.imanity.antixray.libs.objenesis.instantiator.annotations.Instantiator;
import dev.imanity.antixray.libs.objenesis.instantiator.ObjectInstantiator;

@Instantiator(Typology.STANDARD)
public class SunReflectionFactoryInstantiator<T> implements ObjectInstantiator<T>
{
    private final Constructor<T> mungedConstructor;
    
    public SunReflectionFactoryInstantiator(final Class<T> clazz) {
        (this.mungedConstructor = SunReflectionFactoryHelper.newConstructorForSerialization(clazz, getJavaLangObjectConstructor())).setAccessible(true);
    }
    
    @Override
    public T newInstance() {
        try {
            return this.mungedConstructor.newInstance((Object[])null);
        }
        catch (final Exception ex) {
            throw new ObjenesisException(ex);
        }
    }
    
    private static Constructor<Object> getJavaLangObjectConstructor() {
        try {
            return Object.class.getConstructor((Class<?>[])null);
        }
        catch (final NoSuchMethodException ex) {
            throw new ObjenesisException(ex);
        }
    }
}
