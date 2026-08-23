// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.mountiplex.conversion.builtin;

import dev.imanity.antixray.libs.mountiplex.conversion.Conversion;
import dev.imanity.antixray.libs.mountiplex.conversion.Converter;

public class BooleanConversion
{
    public static void register() {
        Conversion.registerConverter(new Converter<Boolean, Byte>(Boolean.class, Byte.class) {
            @Override
            public Byte convertInput(final Boolean b) {
                return (byte)(((boolean)b) ? 1 : 0);
            }
            
            @Override
            public int getCost() {
                return 2;
            }
        });
        Conversion.registerConverter(new Converter<Boolean, Short>(Boolean.class, Short.class) {
            @Override
            public Short convertInput(final Boolean b) {
                return (short)(((boolean)b) ? 1 : 0);
            }
            
            @Override
            public int getCost() {
                return 2;
            }
        });
        Conversion.registerConverter(new Converter<Boolean, Integer>(Boolean.class, Integer.class) {
            @Override
            public Integer convertInput(final Boolean b) {
                return ((boolean)b) ? 1 : 0;
            }
            
            @Override
            public int getCost() {
                return 2;
            }
        });
        Conversion.registerConverter(new Converter<Boolean, Long>(Boolean.class, Long.class) {
            @Override
            public Long convertInput(final Boolean b) {
                return (long)(((boolean)b) ? 1 : 0);
            }
            
            @Override
            public int getCost() {
                return 2;
            }
        });
        Conversion.registerConverter(new Converter<Number, Boolean>(Number.class, Boolean.class) {
            @Override
            public Boolean convertInput(final Number n) {
                return n.intValue() != 0;
            }
            
            @Override
            public int getCost() {
                return 2;
            }
        });
    }
}
