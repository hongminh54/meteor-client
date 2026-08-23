// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.mountiplex.conversion.builtin;

import dev.imanity.antixray.libs.mountiplex.conversion.Conversion;
import dev.imanity.antixray.libs.mountiplex.conversion.Converter;

public class VoidTypeConverter
{
    public static void register() {
        Conversion.registerConverter(new Converter<Object, Object>(Object.class, Void.TYPE) {
            @Override
            public Object convertInput(final Object o) {
                return null;
            }
        });
    }
}
