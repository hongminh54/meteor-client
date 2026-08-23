// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.objenesis.instantiator.perc;

import java.lang.reflect.InvocationTargetException;
import dev.imanity.antixray.libs.objenesis.ObjenesisException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.lang.reflect.Method;
import dev.imanity.antixray.libs.objenesis.instantiator.annotations.Typology;
import dev.imanity.antixray.libs.objenesis.instantiator.annotations.Instantiator;
import dev.imanity.antixray.libs.objenesis.instantiator.ObjectInstantiator;

@Instantiator(Typology.SERIALIZATION)
public class PercSerializationInstantiator<T> implements ObjectInstantiator<T>
{
    private final Object[] typeArgs;
    private final Method newInstanceMethod;
    
    public PercSerializationInstantiator(final Class<T> clazz) {
        Class<? super T> superclass;
        for (superclass = clazz; Serializable.class.isAssignableFrom(superclass); superclass = superclass.getSuperclass()) {}
        try {
            (this.newInstanceMethod = ObjectInputStream.class.getDeclaredMethod("noArgConstruct", Class.class, Object.class, Class.forName("COM.newmonics.PercClassLoader.Method"))).setAccessible(true);
            final Object invoke = Class.forName("COM.newmonics.PercClassLoader.PercClass").getDeclaredMethod("getPercClass", Class.class).invoke(null, superclass);
            this.typeArgs = new Object[] { superclass, clazz, invoke.getClass().getDeclaredMethod("findMethod", String.class).invoke(invoke, "<init>()V") };
        }
        catch (final ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException ex) {
            throw new ObjenesisException((Throwable)ex);
        }
    }
    
    @Override
    public T newInstance() {
        try {
            return (T)this.newInstanceMethod.invoke(null, this.typeArgs);
        }
        catch (final IllegalAccessException | InvocationTargetException ex) {
            throw new ObjenesisException((Throwable)ex);
        }
    }
}
