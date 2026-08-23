// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.objenesis.instantiator.basic;

import dev.imanity.antixray.libs.objenesis.ObjenesisException;
import java.io.ObjectStreamClass;
import java.lang.reflect.Method;
import dev.imanity.antixray.libs.objenesis.instantiator.annotations.Typology;
import dev.imanity.antixray.libs.objenesis.instantiator.annotations.Instantiator;
import dev.imanity.antixray.libs.objenesis.instantiator.ObjectInstantiator;

@Instantiator(Typology.SERIALIZATION)
public class ObjectStreamClassInstantiator<T> implements ObjectInstantiator<T>
{
    private static Method newInstanceMethod;
    private final ObjectStreamClass objStreamClass;
    
    private static void initialize() {
        if (ObjectStreamClassInstantiator.newInstanceMethod == null) {
            try {
                (ObjectStreamClassInstantiator.newInstanceMethod = ObjectStreamClass.class.getDeclaredMethod("newInstance", (Class<?>[])new Class[0])).setAccessible(true);
            }
            catch (final RuntimeException | NoSuchMethodException ex) {
                throw new ObjenesisException((Throwable)ex);
            }
        }
    }
    
    public ObjectStreamClassInstantiator(final Class<T> cl) {
        initialize();
        this.objStreamClass = ObjectStreamClass.lookup(cl);
    }
    
    @Override
    public T newInstance() {
        try {
            return (T)ObjectStreamClassInstantiator.newInstanceMethod.invoke(this.objStreamClass, new Object[0]);
        }
        catch (final Exception ex) {
            throw new ObjenesisException(ex);
        }
    }
}
