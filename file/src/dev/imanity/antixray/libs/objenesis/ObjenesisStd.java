// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.objenesis;

import dev.imanity.antixray.libs.objenesis.strategy.InstantiatorStrategy;
import dev.imanity.antixray.libs.objenesis.strategy.StdInstantiatorStrategy;

public class ObjenesisStd extends ObjenesisBase
{
    public ObjenesisStd() {
        super(new StdInstantiatorStrategy());
    }
    
    public ObjenesisStd(final boolean b) {
        super(new StdInstantiatorStrategy(), b);
    }
}
