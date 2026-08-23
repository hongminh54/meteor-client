// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.objenesis.instantiator.basic;

import dev.imanity.antixray.libs.objenesis.instantiator.util.ClassUtils;
import dev.imanity.antixray.libs.objenesis.instantiator.annotations.Typology;
import dev.imanity.antixray.libs.objenesis.instantiator.annotations.Instantiator;
import dev.imanity.antixray.libs.objenesis.instantiator.ObjectInstantiator;

@Instantiator(Typology.NOT_COMPLIANT)
public class NewInstanceInstantiator<T> implements ObjectInstantiator<T>
{
    private final Class<T> type;
    
    public NewInstanceInstantiator(final Class<T> type) {
        this.type = type;
    }
    
    @Override
    public T newInstance() {
        return ClassUtils.newInstance(this.type);
    }
}
