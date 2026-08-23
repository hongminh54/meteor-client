// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.objenesis.instantiator.gcj;

import dev.imanity.antixray.libs.objenesis.ObjenesisException;
import dev.imanity.antixray.libs.objenesis.instantiator.SerializationInstantiatorHelper;
import dev.imanity.antixray.libs.objenesis.instantiator.annotations.Typology;
import dev.imanity.antixray.libs.objenesis.instantiator.annotations.Instantiator;

@Instantiator(Typology.SERIALIZATION)
public class GCJSerializationInstantiator<T> extends GCJInstantiatorBase<T>
{
    private final Class<? super T> superType;
    
    public GCJSerializationInstantiator(final Class<T> clazz) {
        super(clazz);
        this.superType = SerializationInstantiatorHelper.getNonSerializableSuperClass(clazz);
    }
    
    @Override
    public T newInstance() {
        try {
            return this.type.cast(GCJSerializationInstantiator.newObjectMethod.invoke(GCJSerializationInstantiator.dummyStream, this.type, this.superType));
        }
        catch (final Exception ex) {
            throw new ObjenesisException(ex);
        }
    }
}
