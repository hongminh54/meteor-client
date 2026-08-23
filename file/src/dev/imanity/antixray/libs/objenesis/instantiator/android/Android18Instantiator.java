// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.objenesis.instantiator.android;

import java.lang.reflect.InvocationTargetException;
import java.io.ObjectStreamClass;
import dev.imanity.antixray.libs.objenesis.ObjenesisException;
import java.lang.reflect.Method;
import dev.imanity.antixray.libs.objenesis.instantiator.annotations.Typology;
import dev.imanity.antixray.libs.objenesis.instantiator.annotations.Instantiator;
import dev.imanity.antixray.libs.objenesis.instantiator.ObjectInstantiator;

@Instantiator(Typology.STANDARD)
public class Android18Instantiator<T> implements ObjectInstantiator<T>
{
    private final Class<T> type;
    private final Method newInstanceMethod;
    private final Long objectConstructorId;
    
    public Android18Instantiator(final Class<T> type) {
        this.type = type;
        this.newInstanceMethod = getNewInstanceMethod();
        this.objectConstructorId = findConstructorIdForJavaLangObjectConstructor();
    }
    
    @Override
    public T newInstance() {
        try {
            return this.type.cast(this.newInstanceMethod.invoke(null, this.type, this.objectConstructorId));
        }
        catch (final Exception ex) {
            throw new ObjenesisException(ex);
        }
    }
    
    private static Method getNewInstanceMethod() {
        try {
            final Method declaredMethod = ObjectStreamClass.class.getDeclaredMethod("newInstance", Class.class, Long.TYPE);
            declaredMethod.setAccessible(true);
            return declaredMethod;
        }
        catch (final RuntimeException | NoSuchMethodException ex) {
            throw new ObjenesisException((Throwable)ex);
        }
    }
    
    private static Long findConstructorIdForJavaLangObjectConstructor() {
        try {
            final Method declaredMethod = ObjectStreamClass.class.getDeclaredMethod("getConstructorId", Class.class);
            declaredMethod.setAccessible(true);
            return (Long)declaredMethod.invoke(null, Object.class);
        }
        catch (final RuntimeException | NoSuchMethodException | InvocationTargetException | IllegalAccessException ex) {
            throw new ObjenesisException((Throwable)ex);
        }
    }
}
