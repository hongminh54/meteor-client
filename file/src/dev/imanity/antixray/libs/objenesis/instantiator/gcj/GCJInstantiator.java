// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.objenesis.instantiator.gcj;

import java.lang.reflect.InvocationTargetException;
import dev.imanity.antixray.libs.objenesis.ObjenesisException;
import dev.imanity.antixray.libs.objenesis.instantiator.annotations.Typology;
import dev.imanity.antixray.libs.objenesis.instantiator.annotations.Instantiator;

@Instantiator(Typology.STANDARD)
public class GCJInstantiator<T> extends GCJInstantiatorBase<T>
{
    public GCJInstantiator(final Class<T> clazz) {
        super(clazz);
    }
    
    @Override
    public T newInstance() {
        try {
            return this.type.cast(GCJInstantiator.newObjectMethod.invoke(GCJInstantiator.dummyStream, this.type, Object.class));
        }
        catch (final RuntimeException | IllegalAccessException | InvocationTargetException ex) {
            throw new ObjenesisException((Throwable)ex);
        }
    }
}
