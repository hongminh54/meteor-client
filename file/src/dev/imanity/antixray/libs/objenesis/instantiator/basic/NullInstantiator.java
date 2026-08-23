// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.objenesis.instantiator.basic;

import dev.imanity.antixray.libs.objenesis.instantiator.annotations.Typology;
import dev.imanity.antixray.libs.objenesis.instantiator.annotations.Instantiator;
import dev.imanity.antixray.libs.objenesis.instantiator.ObjectInstantiator;

@Instantiator(Typology.NOT_COMPLIANT)
public class NullInstantiator<T> implements ObjectInstantiator<T>
{
    public NullInstantiator(final Class<T> clazz) {
    }
    
    @Override
    public T newInstance() {
        return null;
    }
}
