// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.mountiplex.conversion.util;

import java.util.Collection;
import dev.imanity.antixray.libs.mountiplex.conversion.type.DuplexConverter;
import java.util.Set;

public class ConvertingSet<T> extends ConvertingCollection<T> implements Set<T>
{
    public ConvertingSet(final Set<?> set, final DuplexConverter<?, T> duplexConverter) {
        super(set, duplexConverter);
    }
}
