// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.mountiplex.conversion.builtin;

import dev.imanity.antixray.libs.mountiplex.reflection.declarations.TypeDeclaration;
import dev.imanity.antixray.libs.mountiplex.conversion.util.ConvertingEntry;
import java.util.Map;
import dev.imanity.antixray.libs.mountiplex.conversion.type.DuplexConverter;

public class EntryConverter<K, V> extends DuplexConverter<Map.Entry<?, ?>, Map.Entry<K, V>>
{
    private final DuplexConverter<Object, K> keyConverter;
    private final DuplexConverter<Object, V> valueConverter;
    
    protected EntryConverter(final DuplexConverter<?, K> keyConverter, final DuplexConverter<?, V> valueConverter) {
        super(entryType(keyConverter.input, valueConverter.input), entryType(valueConverter.output, valueConverter.output));
        this.keyConverter = (DuplexConverter<Object, K>)keyConverter;
        this.valueConverter = (DuplexConverter<Object, V>)valueConverter;
    }
    
    @Override
    public Map.Entry<K, V> convertInput(final Map.Entry<?, ?> entry) {
        return new ConvertingEntry<K, V>(entry, this.keyConverter, this.valueConverter);
    }
    
    @Override
    public Map.Entry<?, ?> convertOutput(final Map.Entry<K, V> entry) {
        return new ConvertingEntry<Object, Object>(entry, this.keyConverter.reverse(), this.valueConverter.reverse());
    }
    
    private static TypeDeclaration entryType(final TypeDeclaration typeDeclaration, final TypeDeclaration typeDeclaration2) {
        return TypeDeclaration.fromClass(Map.Entry.class).setGenericTypes(typeDeclaration, typeDeclaration2);
    }
    
    public static <K, V> EntryConverter<K, V> create(final DuplexConverter<?, K> duplexConverter, final DuplexConverter<?, V> duplexConverter2) {
        return new EntryConverter<K, V>(duplexConverter, duplexConverter2);
    }
}
