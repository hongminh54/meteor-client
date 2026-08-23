// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.mountiplex.conversion.builtin;

import dev.imanity.antixray.libs.mountiplex.conversion.Converter;
import dev.imanity.antixray.libs.mountiplex.conversion.Conversion;

public class NumberConversion
{
    public static void register() {
        Conversion.registerConverter(new IntegerNumberParser<Byte>(Byte.class) {
            @Override
            public Byte parse(final String s) {
                return Byte.parseByte(s);
            }
        });
        Conversion.registerConverter(new IntegerNumberParser<Short>(Short.class) {
            @Override
            public Short parse(final String s) {
                return Short.parseShort(s);
            }
        });
        Conversion.registerConverter(new IntegerNumberParser<Integer>(Integer.class) {
            @Override
            public Integer parse(final String s) {
                return Integer.parseInt(s);
            }
        });
        Conversion.registerConverter(new IntegerNumberParser<Long>(Long.class) {
            @Override
            public Long parse(final String s) {
                return Long.parseLong(s);
            }
        });
        Conversion.registerConverter(new FloatingNumberParser<Float>(Float.class) {
            @Override
            public Float parse(final String s) {
                return Float.parseFloat(s);
            }
        });
        Conversion.registerConverter(new FloatingNumberParser<Double>(Double.class) {
            @Override
            public Double parse(final String s) {
                return Double.parseDouble(s);
            }
        });
        Conversion.registerConverter(new NumberParser<Number>(Number.class) {
            @Override
            public Number parse(final String s, final boolean b) {
                if (b) {
                    final double double1 = Double.parseDouble(s);
                    final float f = (float)double1;
                    if (double1 == f) {
                        return f;
                    }
                    return double1;
                }
                else {
                    final long long1 = Long.parseLong(s);
                    if (long1 >= -128L && long1 <= 127L) {
                        return (byte)long1;
                    }
                    if (long1 >= -32768L && long1 <= 32767L) {
                        return (short)long1;
                    }
                    if (long1 >= -2147483648L && long1 < 2147483647L) {
                        return (int)long1;
                    }
                    return long1;
                }
            }
        });
        Conversion.registerConverter(new NumberCaster<Byte>(Byte.class) {
            @Override
            public Byte convertInput(final Number n) {
                return n.byteValue();
            }
        });
        Conversion.registerConverter(new NumberCaster<Short>(Short.class) {
            @Override
            public Short convertInput(final Number n) {
                return n.shortValue();
            }
        });
        Conversion.registerConverter(new NumberCaster<Integer>(Integer.class) {
            @Override
            public Integer convertInput(final Number n) {
                return n.intValue();
            }
        });
        Conversion.registerConverter(new NumberCaster<Long>(Long.class) {
            @Override
            public Long convertInput(final Number n) {
                return n.longValue();
            }
        });
        Conversion.registerConverter(new NumberCaster<Float>(Float.class) {
            @Override
            public Float convertInput(final Number n) {
                return n.floatValue();
            }
        });
        Conversion.registerConverter(new NumberCaster<Double>(Double.class) {
            @Override
            public Double convertInput(final Number n) {
                return n.doubleValue();
            }
        });
    }
    
    private abstract static class NumberParser<T> extends Converter<String, T>
    {
        public NumberParser(final Class<T> clazz) {
            super(String.class, clazz);
        }
        
        public abstract T parse(final String p0, final boolean p1);
        
        @Override
        public T convertInput(final String s) {
            try {
                final StringBuilder sb = new StringBuilder(s.length());
                boolean b = false;
                boolean b2 = false;
                for (int i = 0; i < s.length(); ++i) {
                    final char char1 = s.charAt(i);
                    if (Character.isDigit(char1)) {
                        sb.append(char1);
                        b2 = true;
                    }
                    else if (char1 == ' ') {
                        if (b2) {
                            break;
                        }
                    }
                    else if ((char1 == ',' || char1 == '.') && !b) {
                        sb.append('.');
                        b = true;
                    }
                    else if (char1 == '-' && sb.length() == 0) {
                        sb.append(char1);
                    }
                }
                if (b2) {
                    return this.parse(sb.toString(), b);
                }
            }
            catch (final NumberFormatException ex) {}
            return null;
        }
    }
    
    private abstract static class FloatingNumberParser<T> extends NumberParser<T>
    {
        public FloatingNumberParser(final Class<T> clazz) {
            super(clazz);
        }
        
        @Override
        public final T parse(final String str, final boolean b) {
            if (b) {
                return this.parse(str);
            }
            return this.parse(str + ".0");
        }
        
        public abstract T parse(final String p0);
    }
    
    private abstract static class IntegerNumberParser<T> extends NumberParser<T>
    {
        public IntegerNumberParser(final Class<T> clazz) {
            super(clazz);
        }
        
        @Override
        public final T parse(final String s, final boolean b) {
            if (b) {
                return this.parse(s.substring(0, s.indexOf(46)));
            }
            return this.parse(s);
        }
        
        public abstract T parse(final String p0);
    }
    
    private abstract static class NumberCaster<T> extends Converter<Number, T>
    {
        public NumberCaster(final Class<T> clazz) {
            super(Number.class, clazz);
        }
    }
}
