// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.mountiplex.conversion.builtin;

import java.util.Collection;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import dev.imanity.antixray.libs.mountiplex.MountiplexUtil;
import java.util.function.Function;
import dev.imanity.antixray.libs.mountiplex.conversion.type.InputConverter;
import dev.imanity.antixray.libs.mountiplex.conversion.Conversion;
import java.util.stream.Stream;
import dev.imanity.antixray.libs.mountiplex.conversion.Converter;
import java.util.List;
import dev.imanity.antixray.libs.mountiplex.reflection.declarations.TypeDeclaration;
import dev.imanity.antixray.libs.mountiplex.conversion.ConverterProvider;

public class StreamConversion
{
    public static void register() {
        Conversion.registerProvider(new ConverterProvider() {
            @Override
            public void getConverters(final TypeDeclaration typeDeclaration, final List<Converter<?, ?>> list) {
                if (typeDeclaration.type.equals(Stream.class)) {
                    final TypeDeclaration genericType = typeDeclaration.getGenericType(0);
                    list.add(new StreamConverter(typeDeclaration));
                    list.add(new CollectionToStreamConverter(genericType));
                    list.add(new ElementToStreamConverter(genericType));
                }
                if (typeDeclaration.type.equals(List.class)) {
                    list.add(new StreamToListConverter(typeDeclaration.getGenericType(0)));
                }
            }
        });
    }
    
    private static final class StreamConverter extends InputConverter<Stream<?>>
    {
        private final TypeDeclaration outputElementType;
        
        public StreamConverter(final TypeDeclaration typeDeclaration) {
            super(TypeDeclaration.fromClass(Stream.class), typeDeclaration);
            this.outputElementType = typeDeclaration.getGenericType(0);
        }
        
        @Override
        public Converter<?, Stream<?>> getConverter(final TypeDeclaration typeDeclaration) {
            final Converter<Object, Object> find = Conversion.find(typeDeclaration.castAsType(Stream.class).getGenericType(0), this.outputElementType);
            if (find != null) {
                return new StreamConverterMapper(typeDeclaration, this.output, find);
            }
            return null;
        }
        
        @Override
        public int getCost() {
            return 10;
        }
    }
    
    private static final class StreamConverterMapper extends Converter<Stream<?>, Stream<?>>
    {
        private final Converter<?, ?> _elementConverter;
        
        public StreamConverterMapper(final TypeDeclaration typeDeclaration, final TypeDeclaration typeDeclaration2, final Converter<?, ?> elementConverter) {
            super(typeDeclaration, typeDeclaration2);
            this._elementConverter = elementConverter;
        }
        
        @Override
        public Stream<?> convertInput(final Stream<?> stream) {
            return stream.map((Function<?, ?>)this._elementConverter);
        }
    }
    
    private static final class ElementToStreamConverter<T> extends Converter<T, Stream<T>>
    {
        public ElementToStreamConverter(final TypeDeclaration typeDeclaration) {
            super(typeDeclaration, TypeDeclaration.createGeneric(Stream.class, typeDeclaration));
        }
        
        @Override
        public Stream<T> convertInput(final T t) {
            return MountiplexUtil.toStream(t);
        }
    }
    
    private static final class StreamToListConverter<T> extends Converter<Stream<T>, List<T>>
    {
        public StreamToListConverter(final TypeDeclaration typeDeclaration) {
            super(TypeDeclaration.createGeneric(Stream.class, typeDeclaration), TypeDeclaration.createGeneric(List.class, typeDeclaration));
        }
        
        @Override
        public List<T> convertInput(final Stream<T> stream) {
            return stream.collect((Collector<? super T, ?, List<T>>)Collectors.toList());
        }
        
        @Override
        public int getCost() {
            return 100;
        }
    }
    
    private static final class CollectionToStreamConverter<T> extends Converter<Collection<T>, Stream<T>>
    {
        public CollectionToStreamConverter(final TypeDeclaration typeDeclaration) {
            super(TypeDeclaration.createGeneric(Collection.class, typeDeclaration), TypeDeclaration.createGeneric(Stream.class, typeDeclaration));
        }
        
        @Override
        public Stream<T> convertInput(final Collection<T> collection) {
            return collection.stream();
        }
    }
}
