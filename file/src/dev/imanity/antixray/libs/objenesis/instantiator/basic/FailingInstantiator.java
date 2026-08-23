// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.objenesis.instantiator.basic;

import dev.imanity.antixray.libs.objenesis.ObjenesisException;
import dev.imanity.antixray.libs.objenesis.instantiator.annotations.Typology;
import dev.imanity.antixray.libs.objenesis.instantiator.annotations.Instantiator;
import dev.imanity.antixray.libs.objenesis.instantiator.ObjectInstantiator;

@Instantiator(Typology.NOT_COMPLIANT)
public class FailingInstantiator<T> implements ObjectInstantiator<T>
{
    public FailingInstantiator(final Class<T> clazz) {
    }
    
    @Override
    public T newInstance() {
        throw new ObjenesisException("Always failing");
    }
}
