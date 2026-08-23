// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.mountiplex.conversion.builtin;

import dev.imanity.antixray.libs.mountiplex.conversion.util.ConvertingMap;
import dev.imanity.antixray.libs.mountiplex.conversion.type.DuplexConverter;
import dev.imanity.antixray.libs.mountiplex.conversion.type.InputConverter;
import dev.imanity.antixray.libs.mountiplex.conversion.Conversion;
import java.util.Map;
import dev.imanity.antixray.libs.mountiplex.conversion.Converter;
import java.util.List;
import dev.imanity.antixray.libs.mountiplex.reflection.declarations.TypeDeclaration;
import dev.imanity.antixray.libs.mountiplex.conversion.ConverterProvider;

public class MapConversion
{
    public static void register() {
        Conversion.registerProvider(new ConverterProvider() {
            @Override
            public void getConverters(final TypeDeclaration typeDeclaration, final List<Converter<?, ?>> list) {
                if (typeDeclaration.type.equals(Map.class)) {
                    list.add(new MapConverter(typeDeclaration));
                }
            }
        });
    }
    
    private static final class MapConverter extends InputConverter<Map<?, ?>>
    {
        private final TypeDeclaration outputKeyType;
        private final TypeDeclaration outputValueType;
        
        public MapConverter(final TypeDeclaration typeDeclaration) {
            super(TypeDeclaration.fromClass(Map.class), typeDeclaration);
            final TypeDeclaration castAsType = typeDeclaration.castAsType(Map.class);
            this.outputKeyType = castAsType.getGenericType(0);
            this.outputValueType = castAsType.getGenericType(1);
        }
        
        @Override
        public final Converter<?, Map<?, ?>> getConverter(final TypeDeclaration typeDeclaration) {
            final TypeDeclaration castAsType = typeDeclaration.castAsType(Map.class);
            final TypeDeclaration genericType = castAsType.getGenericType(0);
            final TypeDeclaration genericType2 = castAsType.getGenericType(1);
            final boolean b = !genericType.equals(this.outputKeyType);
            final boolean b2 = !genericType2.equals(this.outputValueType);
            if (!b && !b2) {
                return null;
            }
            DuplexConverter<Object, Object> duplexConverter;
            if (b) {
                duplexConverter = Conversion.findDuplex(genericType, this.outputKeyType);
            }
            else {
                duplexConverter = DuplexConverter.createNull(this.outputKeyType);
            }
            DuplexConverter<Object, Object> duplexConverter2;
            if (b2) {
                duplexConverter2 = Conversion.findDuplex(genericType2, this.outputValueType);
            }
            else {
                duplexConverter2 = DuplexConverter.createNull(this.outputValueType);
            }
            if (duplexConverter != null && duplexConverter2 != null) {
                return new ElementConverter(typeDeclaration, this.output, duplexConverter, duplexConverter2);
            }
            return null;
        }
        
        private final class ElementConverter extends DuplexConverter<Map<?, ?>, Map<?, ?>>
        {
            private final DuplexConverter<Object, Object> keyConverter;
            private final DuplexConverter<Object, Object> valueConverter;
            
            public ElementConverter(final TypeDeclaration typeDeclaration, final TypeDeclaration typeDeclaration2, final DuplexConverter<Object, Object> keyConverter, final DuplexConverter<Object, Object> valueConverter) {
                super(typeDeclaration, typeDeclaration2);
                this.keyConverter = keyConverter;
                this.valueConverter = valueConverter;
            }
            
            @Override
            public final Map<?, ?> convertInput(final Map<?, ?> map) {
                return new ConvertingMap<Object, Object>(map, this.keyConverter, this.valueConverter);
            }
            
            @Override
            public final Map<?, ?> convertOutput(final Map<?, ?> map) {
                return new ConvertingMap<Object, Object>(map, this.keyConverter.reverse(), this.valueConverter.reverse());
            }
        }
    }
}
