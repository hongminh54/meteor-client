// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.objenesis.instantiator;

import java.io.Serializable;

public class SerializationInstantiatorHelper
{
    public static <T> Class<? super T> getNonSerializableSuperClass(final Class<T> clazz) {
        Class<? super T> superclass = clazz;
        while (Serializable.class.isAssignableFrom(superclass)) {
            superclass = superclass.getSuperclass();
            if (superclass == null) {
                throw new Error("Bad class hierarchy: No non-serializable parents");
            }
        }
        return superclass;
    }
}
