// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.objenesis.instantiator.sun;

import dev.imanity.antixray.libs.objenesis.ObjenesisException;
import java.io.NotSerializableException;
import dev.imanity.antixray.libs.objenesis.instantiator.SerializationInstantiatorHelper;
import java.lang.reflect.Constructor;
import dev.imanity.antixray.libs.objenesis.instantiator.annotations.Typology;
import dev.imanity.antixray.libs.objenesis.instantiator.annotations.Instantiator;
import dev.imanity.antixray.libs.objenesis.instantiator.ObjectInstantiator;

@Instantiator(Typology.SERIALIZATION)
public class SunReflectionFactorySerializationInstantiator<T> implements ObjectInstantiator<T>
{
    private final Constructor<T> mungedConstructor;
    
    public SunReflectionFactorySerializationInstantiator(final Class<T> obj) {
        final Class<? super T> nonSerializableSuperClass = SerializationInstantiatorHelper.getNonSerializableSuperClass(obj);
        Constructor<? super T> declaredConstructor;
        try {
            declaredConstructor = nonSerializableSuperClass.getDeclaredConstructor((Class<?>[])null);
        }
        catch (final NoSuchMethodException ex) {
            throw new ObjenesisException(new NotSerializableException(obj + " has no suitable superclass constructor"));
        }
        (this.mungedConstructor = SunReflectionFactoryHelper.newConstructorForSerialization(obj, declaredConstructor)).setAccessible(true);
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
}
