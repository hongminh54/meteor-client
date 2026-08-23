// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.objenesis;

import dev.imanity.antixray.libs.objenesis.instantiator.ObjectInstantiator;

public interface Objenesis
{
     <T> T newInstance(final Class<T> p0);
    
     <T> ObjectInstantiator<T> getInstantiatorOf(final Class<T> p0);
}
