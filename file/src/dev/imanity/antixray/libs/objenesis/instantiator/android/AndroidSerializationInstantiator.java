// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.objenesis.instantiator.android;

import java.lang.reflect.InvocationTargetException;
import dev.imanity.antixray.libs.objenesis.ObjenesisException;
import java.lang.reflect.Method;
import java.io.ObjectStreamClass;
import dev.imanity.antixray.libs.objenesis.instantiator.annotations.Typology;
import dev.imanity.antixray.libs.objenesis.instantiator.annotations.Instantiator;
import dev.imanity.antixray.libs.objenesis.instantiator.ObjectInstantiator;

@Instantiator(Typology.SERIALIZATION)
public class AndroidSerializationInstantiator<T> implements ObjectInstantiator<T>
{
    private final Class<T> type;
    private final ObjectStreamClass objectStreamClass;
    private final Method newInstanceMethod;
    
    public AndroidSerializationInstantiator(final Class<T> type) {
        this.type = type;
        this.newInstanceMethod = getNewInstanceMethod();
        Method method;
        try {
            method = ObjectStreamClass.class.getMethod("lookupAny", Class.class);
        }
        catch (final NoSuchMethodException ex) {
            throw new ObjenesisException(ex);
        }
        try {
            this.objectStreamClass = (ObjectStreamClass)method.invoke(null, type);
        }
        catch (final IllegalAccessException | InvocationTargetException ex2) {
            throw new ObjenesisException((Throwable)ex2);
        }
    }
    
    @Override
    public T newInstance() {
        try {
            return this.type.cast(this.newInstanceMethod.invoke(this.objectStreamClass, this.type));
        }
        catch (final IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            throw new ObjenesisException((Throwable)ex);
        }
    }
    
    private static Method getNewInstanceMethod() {
        try {
            final Method declaredMethod = ObjectStreamClass.class.getDeclaredMethod("newInstance", Class.class);
            declaredMethod.setAccessible(true);
            return declaredMethod;
        }
        catch (final RuntimeException | NoSuchMethodException ex) {
            throw new ObjenesisException((Throwable)ex);
        }
    }
}
