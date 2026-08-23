// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.mountiplex.conversion.builtin;

import java.util.Locale;
import dev.imanity.antixray.libs.mountiplex.MountiplexUtil;
import java.util.HashMap;
import java.util.Map;
import dev.imanity.antixray.libs.mountiplex.conversion.Conversion;
import dev.imanity.antixray.libs.mountiplex.conversion.Converter;
import java.util.List;
import dev.imanity.antixray.libs.mountiplex.reflection.declarations.TypeDeclaration;
import dev.imanity.antixray.libs.mountiplex.conversion.ConverterProvider;

public class EnumConversion
{
    public static void register() {
        Conversion.registerProvider(new ConverterProvider() {
            @Override
            public void getConverters(TypeDeclaration superType, final List<Converter<?, ?>> list) {
                if (!superType.isInstanceOf(TypeDeclaration.ENUM)) {
                    return;
                }
                if (superType.type != null && !superType.type.isEnum() && superType.type.getSuperclass().isEnum()) {
                    superType = superType.getSuperType();
                }
                final EnumStringCache enumStringCache = new EnumStringCache(superType.type);
                list.add(new Converter<Number, Enum>(TypeDeclaration.fromClass(Number.class), superType) {
                    @Override
                    public Enum convertInput(final Number n) {
                        final int intValue = n.intValue();
                        if (intValue >= 0 && intValue < enumStringCache.getConstants().length) {
                            return enumStringCache.getConstants()[intValue];
                        }
                        return null;
                    }
                });
                list.add(new Converter<String, Enum>(TypeDeclaration.fromClass(String.class), superType) {
                    @Override
                    public Enum convertInput(final String s) {
                        return enumStringCache.get(s);
                    }
                });
            }
        });
    }
    
    private static class EnumStringCache
    {
        private final Map<String, Enum> values;
        private final Class<?> _type;
        private Enum<?>[] _constants;
        
        public EnumStringCache(final Class<?> type) {
            this.values = new HashMap<String, Enum>();
            this._type = type;
            this._constants = null;
        }
        
        public Enum<?>[] getConstants() {
            if (this._constants == null) {
                this._constants = (Enum[])this._type.getEnumConstants();
                if (this._constants == null) {
                    this._constants = MountiplexUtil.createArray(this._type, 0);
                }
                for (final Enum<?> enum1 : this._constants) {
                    final String name = enum1.name();
                    this.values.put(name.toLowerCase(Locale.ENGLISH), enum1);
                    this.values.put(name.toUpperCase(Locale.ENGLISH), enum1);
                    this.values.put(name, enum1);
                }
            }
            return this._constants;
        }
        
        public Enum get(final String s) {
            Enum<?> enum1 = this.values.get(s);
            if (enum1 == null) {
                enum1 = MountiplexUtil.parseArray(this.getConstants(), s, null);
                if (enum1 != null) {
                    this.values.put(s, enum1);
                }
            }
            return enum1;
        }
    }
}
