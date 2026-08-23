// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.objenesis.instantiator.android;

import java.io.ObjectInputStream;
import dev.imanity.antixray.libs.objenesis.ObjenesisException;
import java.lang.reflect.Method;
import dev.imanity.antixray.libs.objenesis.instantiator.annotations.Typology;
import dev.imanity.antixray.libs.objenesis.instantiator.annotations.Instantiator;
import dev.imanity.antixray.libs.objenesis.instantiator.ObjectInstantiator;

@Instantiator(Typology.STANDARD)
public class Android10Instantiator<T> implements ObjectInstantiator<T>
{
    private final Class<T> type;
    private final Method newStaticMethod;
    
    public Android10Instantiator(final Class<T> type) {
        this.type = type;
        this.newStaticMethod = getNewStaticMethod();
    }
    
    @Override
    public T newInstance() {
        try {
            return this.type.cast(this.newStaticMethod.invoke(null, this.type, Object.class));
        }
        catch (final Exception ex) {
            throw new ObjenesisException(ex);
        }
    }
    
    private static Method getNewStaticMethod() {
        try {
            final Method declaredMethod = ObjectInputStream.class.getDeclaredMethod("newInstance", Class.class, Class.class);
            declaredMethod.setAccessible(true);
            return declaredMethod;
        }
        catch (final RuntimeException | NoSuchMethodException ex) {
            throw new ObjenesisException((Throwable)ex);
        }
    }
}
