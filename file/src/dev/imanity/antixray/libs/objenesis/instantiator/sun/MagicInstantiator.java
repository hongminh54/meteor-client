// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.objenesis.instantiator.sun;

import dev.imanity.antixray.libs.objenesis.instantiator.annotations.Typology;
import dev.imanity.antixray.libs.objenesis.instantiator.annotations.Instantiator;
import dev.imanity.antixray.libs.objenesis.instantiator.basic.DelegatingToExoticInstantiator;

@Instantiator(Typology.STANDARD)
public class MagicInstantiator<T> extends DelegatingToExoticInstantiator<T>
{
    public MagicInstantiator(final Class<T> clazz) {
        super("dev.imanity.antixray.libs.objenesis.instantiator.exotic.MagicInstantiator", clazz);
    }
}
