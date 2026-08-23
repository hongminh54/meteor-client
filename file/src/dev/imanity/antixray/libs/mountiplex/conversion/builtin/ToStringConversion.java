// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.mountiplex.conversion.builtin;

import java.util.Iterator;
import dev.imanity.antixray.libs.mountiplex.conversion.type.InputConverter;
import java.util.Collection;
import java.lang.reflect.Array;
import dev.imanity.antixray.libs.mountiplex.MountiplexUtil;
import dev.imanity.antixray.libs.mountiplex.conversion.Conversion;
import dev.imanity.antixray.libs.mountiplex.conversion.Converter;

public class ToStringConversion
{
    public static void register() {
        Conversion.registerConverter(new Converter<Object, String>(Object.class, String.class) {
            @Override
            public String convertInput(final Object o) {
                return o.toString();
            }
            
            @Override
            public int getCost() {
                return 100;
            }
        });
        Conversion.registerConverter(new Converter<CharSequence, String>(CharSequence.class, String.class) {
            @Override
            public String convertInput(final CharSequence charSequence) {
                return charSequence.toString();
            }
        });
        Conversion.registerConverter(new Converter<Double, String>(Double.class, String.class) {
            @Override
            public String convertInput(final Double n) {
                return Double.toString(n);
            }
        });
        Conversion.registerConverter(new Converter<Float, String>(Float.class, String.class) {
            @Override
            public String convertInput(final Float n) {
                return Float.toString(n);
            }
        });
        Conversion.registerConverter(new Converter<Byte, String>(Byte.class, String.class) {
            @Override
            public String convertInput(final Byte b) {
                return Byte.toString(b);
            }
        });
        Conversion.registerConverter(new Converter<Short, String>(Short.class, String.class) {
            @Override
            public String convertInput(final Short n) {
                return Short.toString(n);
            }
        });
        Conversion.registerConverter(new Converter<Integer, String>(Integer.class, String.class) {
            @Override
            public String convertInput(final Integer n) {
                return Integer.toString(n);
            }
        });
        Conversion.registerConverter(new Converter<Long, String>(Long.class, String.class) {
            @Override
            public String convertInput(final Long n) {
                return Long.toString(n);
            }
        });
        Conversion.registerConverter(new Converter<Boolean, String>(Boolean.class, String.class) {
            @Override
            public String convertInput(final Boolean b) {
                return b.toString();
            }
        });
        Conversion.registerConverter(new Converter<char[], String>(char[].class, String.class) {
            @Override
            public String convertInput(final char[] data) {
                return String.copyValueOf(data);
            }
        });
        for (final Class clazz : new Class[] { Double.TYPE, Float.TYPE, Long.TYPE, Integer.TYPE, Short.TYPE, Byte.TYPE, Boolean.TYPE }) {
            Conversion.registerConverter(new Converter<Object, String>(MountiplexUtil.getArrayType(clazz), String.class) {
                final /* synthetic */ Converter val$elToStrConv = Conversion.find((Class<Object>)clazz, String.class);
                
                @Override
                public String convertInput(final Object o) {
                    final int length = Array.getLength(o);
                    final StringBuilder sb = new StringBuilder(length * 5);
                    sb.append('[');
                    for (int i = 0; i < length; ++i) {
                        if (i > 0) {
                            sb.append(", ");
                        }
                        sb.append((String)this.val$elToStrConv.convertInput(Array.get(o, i)));
                    }
                    sb.append(']');
                    return sb.toString();
                }
            });
        }
        Conversion.registerConverter(new Converter<Object[], String>(Object[].class, String.class) {
            @Override
            public String convertInput(final Object[] array) {
                final Converter<?, String> find = Conversion.find(array.getClass().getComponentType(), String.class);
                final StringBuilder sb = new StringBuilder(array.length * 5);
                sb.append('[');
                for (int i = 0; i < array.length; ++i) {
                    if (i > 0) {
                        sb.append(", ");
                    }
                    sb.append(find.convert(array[i], ""));
                }
                sb.append(']');
                return sb.toString();
            }
        });
        Conversion.registerConverter(new Converter<Collection<?>, String>(Collection.class, String.class) {
            @Override
            public String convertInput(final Collection<?> collection) {
                final StringBuilder sb = new StringBuilder(collection.size() * 5);
                final InputConverter<String> find = Conversion.find(String.class);
                int n = 1;
                sb.append("[");
                for (final Object next : collection) {
                    if (n != 0) {
                        n = 0;
                    }
                    else {
                        sb.append(", ");
                    }
                    sb.append(find.convert(next, "null"));
                }
                sb.append("]");
                return sb.toString();
            }
        });
    }
}
