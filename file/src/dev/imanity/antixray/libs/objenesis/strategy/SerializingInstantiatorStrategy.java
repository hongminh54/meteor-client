// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.objenesis.strategy;

import dev.imanity.antixray.libs.objenesis.instantiator.perc.PercSerializationInstantiator;
import dev.imanity.antixray.libs.objenesis.instantiator.gcj.GCJSerializationInstantiator;
import dev.imanity.antixray.libs.objenesis.instantiator.android.AndroidSerializationInstantiator;
import dev.imanity.antixray.libs.objenesis.instantiator.basic.ObjectStreamClassInstantiator;
import dev.imanity.antixray.libs.objenesis.instantiator.sun.SunReflectionFactorySerializationInstantiator;
import dev.imanity.antixray.libs.objenesis.ObjenesisException;
import java.io.NotSerializableException;
import java.io.Serializable;
import dev.imanity.antixray.libs.objenesis.instantiator.ObjectInstantiator;

public class SerializingInstantiatorStrategy extends BaseInstantiatorStrategy
{
    @Override
    public <T> ObjectInstantiator<T> newInstantiatorOf(final Class<T> obj) {
        if (!Serializable.class.isAssignableFrom(obj)) {
            throw new ObjenesisException(new NotSerializableException(obj + " not serializable"));
        }
        if (PlatformDescription.JVM_NAME.startsWith("Java HotSpot") || PlatformDescription.isThisJVM("OpenJDK")) {
            return new SunReflectionFactorySerializationInstantiator<T>(obj);
        }
        if (PlatformDescription.JVM_NAME.startsWith("Dalvik")) {
            if (PlatformDescription.isAndroidOpenJDK()) {
                return new ObjectStreamClassInstantiator<T>(obj);
            }
            return new AndroidSerializationInstantiator<T>(obj);
        }
        else {
            if (PlatformDescription.JVM_NAME.startsWith("GNU libgcj")) {
                return new GCJSerializationInstantiator<T>(obj);
            }
            if (PlatformDescription.JVM_NAME.startsWith("PERC")) {
                return new PercSerializationInstantiator<T>(obj);
            }
            return new SunReflectionFactorySerializationInstantiator<T>(obj);
        }
    }
}
