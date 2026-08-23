// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.objenesis.strategy;

import dev.imanity.antixray.libs.objenesis.instantiator.ObjectInstantiator;

public interface InstantiatorStrategy
{
     <T> ObjectInstantiator<T> newInstantiatorOf(final Class<T> p0);
}
