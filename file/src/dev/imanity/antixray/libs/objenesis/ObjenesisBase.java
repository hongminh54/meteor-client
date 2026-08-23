// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.objenesis;

import dev.imanity.antixray.libs.objenesis.instantiator.ObjectInstantiator;
import java.util.concurrent.ConcurrentHashMap;
import dev.imanity.antixray.libs.objenesis.strategy.InstantiatorStrategy;

public class ObjenesisBase implements Objenesis
{
    protected final InstantiatorStrategy strategy;
    protected ConcurrentHashMap<String, ObjectInstantiator<?>> cache;
    
    public ObjenesisBase(final InstantiatorStrategy instantiatorStrategy) {
        this(instantiatorStrategy, true);
    }
    
    public ObjenesisBase(final InstantiatorStrategy strategy, final boolean b) {
        if (strategy == null) {
            throw new IllegalArgumentException("A strategy can't be null");
        }
        this.strategy = strategy;
        this.cache = (b ? new ConcurrentHashMap<String, ObjectInstantiator<?>>() : null);
    }
    
    @Override
    public String toString() {
        return this.getClass().getName() + " using " + this.strategy.getClass().getName() + ((this.cache == null) ? " without" : " with") + " caching";
    }
    
    @Override
    public <T> T newInstance(final Class<T> clazz) {
        return this.getInstantiatorOf(clazz).newInstance();
    }
    
    @Override
    public <T> ObjectInstantiator<T> getInstantiatorOf(final Class<T> clazz) {
        if (clazz.isPrimitive()) {
            throw new IllegalArgumentException("Primitive types can't be instantiated in Java");
        }
        if (this.cache == null) {
            return this.strategy.newInstantiatorOf(clazz);
        }
        ObjectInstantiator objectInstantiator = this.cache.get(clazz.getName());
        if (objectInstantiator == null) {
            final ObjectInstantiator<T> instantiator = this.strategy.newInstantiatorOf(clazz);
            objectInstantiator = this.cache.putIfAbsent(clazz.getName(), instantiator);
            if (objectInstantiator == null) {
                objectInstantiator = instantiator;
            }
        }
        return objectInstantiator;
    }
}
