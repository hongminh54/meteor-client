// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.objenesis;

import dev.imanity.antixray.libs.objenesis.strategy.InstantiatorStrategy;
import dev.imanity.antixray.libs.objenesis.strategy.SerializingInstantiatorStrategy;

public class ObjenesisSerializer extends ObjenesisBase
{
    public ObjenesisSerializer() {
        super(new SerializingInstantiatorStrategy());
    }
    
    public ObjenesisSerializer(final boolean b) {
        super(new SerializingInstantiatorStrategy(), b);
    }
}
