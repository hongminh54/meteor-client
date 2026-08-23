// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.objenesis.instantiator.sun;

import dev.imanity.antixray.libs.objenesis.ObjenesisException;
import dev.imanity.antixray.libs.objenesis.instantiator.util.UnsafeUtils;
import sun.misc.Unsafe;
import dev.imanity.antixray.libs.objenesis.instantiator.annotations.Typology;
import dev.imanity.antixray.libs.objenesis.instantiator.annotations.Instantiator;
import dev.imanity.antixray.libs.objenesis.instantiator.ObjectInstantiator;

@Instantiator(Typology.STANDARD)
public class UnsafeFactoryInstantiator<T> implements ObjectInstantiator<T>
{
    private final Unsafe unsafe;
    private final Class<T> type;
    
    public UnsafeFactoryInstantiator(final Class<T> type) {
        this.unsafe = UnsafeUtils.getUnsafe();
        this.type = type;
    }
    
    @Override
    public T newInstance() {
        try {
            return this.type.cast(this.unsafe.allocateInstance(this.type));
        }
        catch (final InstantiationException ex) {
            throw new ObjenesisException(ex);
        }
    }
}
