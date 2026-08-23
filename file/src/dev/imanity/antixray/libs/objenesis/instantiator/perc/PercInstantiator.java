// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.objenesis.instantiator.perc;

import dev.imanity.antixray.libs.objenesis.ObjenesisException;
import java.io.ObjectInputStream;
import java.lang.reflect.Method;
import dev.imanity.antixray.libs.objenesis.instantiator.annotations.Typology;
import dev.imanity.antixray.libs.objenesis.instantiator.annotations.Instantiator;
import dev.imanity.antixray.libs.objenesis.instantiator.ObjectInstantiator;

@Instantiator(Typology.STANDARD)
public class PercInstantiator<T> implements ObjectInstantiator<T>
{
    private final Method newInstanceMethod;
    private final Object[] typeArgs;
    
    public PercInstantiator(final Class<T> clazz) {
        (this.typeArgs = new Object[] { null, Boolean.FALSE })[0] = clazz;
        try {
            (this.newInstanceMethod = ObjectInputStream.class.getDeclaredMethod("newInstance", Class.class, Boolean.TYPE)).setAccessible(true);
        }
        catch (final RuntimeException | NoSuchMethodException ex) {
            throw new ObjenesisException((Throwable)ex);
        }
    }
    
    @Override
    public T newInstance() {
        try {
            return (T)this.newInstanceMethod.invoke(null, this.typeArgs);
        }
        catch (final Exception ex) {
            throw new ObjenesisException(ex);
        }
    }
}
