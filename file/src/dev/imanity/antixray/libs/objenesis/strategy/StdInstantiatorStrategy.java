// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.objenesis.strategy;

import dev.imanity.antixray.libs.objenesis.instantiator.perc.PercInstantiator;
import dev.imanity.antixray.libs.objenesis.instantiator.gcj.GCJInstantiator;
import dev.imanity.antixray.libs.objenesis.instantiator.android.Android18Instantiator;
import dev.imanity.antixray.libs.objenesis.instantiator.android.Android17Instantiator;
import dev.imanity.antixray.libs.objenesis.instantiator.android.Android10Instantiator;
import dev.imanity.antixray.libs.objenesis.instantiator.sun.UnsafeFactoryInstantiator;
import dev.imanity.antixray.libs.objenesis.instantiator.sun.SunReflectionFactoryInstantiator;
import dev.imanity.antixray.libs.objenesis.instantiator.ObjectInstantiator;

public class StdInstantiatorStrategy extends BaseInstantiatorStrategy
{
    @Override
    public <T> ObjectInstantiator<T> newInstantiatorOf(final Class<T> clazz) {
        if (PlatformDescription.isThisJVM("Java HotSpot") || PlatformDescription.isThisJVM("OpenJDK")) {
            return new SunReflectionFactoryInstantiator<T>(clazz);
        }
        if (PlatformDescription.isThisJVM("Dalvik")) {
            if (PlatformDescription.isAndroidOpenJDK()) {
                return new UnsafeFactoryInstantiator<T>(clazz);
            }
            if (PlatformDescription.ANDROID_VERSION <= 10) {
                return new Android10Instantiator<T>(clazz);
            }
            if (PlatformDescription.ANDROID_VERSION <= 17) {
                return new Android17Instantiator<T>(clazz);
            }
            return new Android18Instantiator<T>(clazz);
        }
        else {
            if (PlatformDescription.isThisJVM("GNU libgcj")) {
                return new GCJInstantiator<T>(clazz);
            }
            if (PlatformDescription.isThisJVM("PERC")) {
                return new PercInstantiator<T>(clazz);
            }
            return new UnsafeFactoryInstantiator<T>(clazz);
        }
    }
}
