// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.mountiplex.conversion.builtin;

import dev.imanity.antixray.libs.mountiplex.conversion.type.RawConverter;
import java.util.Iterator;
import java.util.Collection;
import java.util.ArrayList;
import java.lang.reflect.Array;
import dev.imanity.antixray.libs.mountiplex.conversion.util.ConvertingList;
import dev.imanity.antixray.libs.mountiplex.conversion.type.DuplexConverter;
import dev.imanity.antixray.libs.mountiplex.conversion.type.InputConverter;
import java.util.Arrays;
import java.util.List;
import dev.imanity.antixray.libs.mountiplex.reflection.declarations.TypeDeclaration;
import dev.imanity.antixray.libs.mountiplex.conversion.ConverterProvider;
import dev.imanity.antixray.libs.mountiplex.conversion.Converter;
import dev.imanity.antixray.libs.mountiplex.conversion.Conversion;
import dev.imanity.antixray.libs.mountiplex.MountiplexUtil;
import dev.imanity.antixray.libs.mountiplex.reflection.util.BoxedType;

public class ArrayConversion
{
    public static void register() {
        for (final Class clazz : BoxedType.getUnboxedTypes()) {
            if (clazz.equals(Void.TYPE)) {
                continue;
            }
            final Class<?> boxedType = BoxedType.getBoxedType(clazz);
            final Class<?> arrayType = MountiplexUtil.getArrayType(clazz);
            final Class<?> arrayType2 = MountiplexUtil.getArrayType(boxedType);
            Conversion.registerConverter(new ArrayConverter(arrayType, arrayType2));
            Conversion.registerConverter(new ArrayConverter(arrayType2, arrayType));
        }
        Conversion.registerProvider(new ConverterProvider() {
            @Override
            public void getConverters(final TypeDeclaration typeDeclaration, final List<Converter<?, ?>> list) {
                if (typeDeclaration.type.equals(List.class)) {
                    final TypeDeclaration genericType = typeDeclaration.getGenericType(0);
                    list.add(new Converter<Object[], List<?>>(TypeDeclaration.fromClass(MountiplexUtil.getArrayType(genericType.type)).setGenericTypes(genericType.genericTypes), typeDeclaration) {
                        @Override
                        public List<?> convertInput(final Object[] a) {
                            return Arrays.asList((Object[])a);
                        }
                    });
                    list.add(new InputConverter<List<?>>(typeDeclaration) {
                        @Override
                        public Converter<?, List<?>> getConverter(final TypeDeclaration typeDeclaration) {
                            if (!typeDeclaration.isArray()) {
                                return null;
                            }
                            final TypeDeclaration componentType = typeDeclaration.getComponentType();
                            final Converter<Object, Object> find = Conversion.find(componentType, genericType);
                            if (find == null) {
                                return null;
                            }
                            if (!componentType.isPrimitive) {
                                final Converter<Object, Object> find2 = Conversion.find(genericType, componentType);
                                if (find2 != null) {
                                    return new Converter<Object, List<?>>(typeDeclaration, this.output) {
                                        final /* synthetic */ DuplexConverter val$duplexConverter = DuplexConverter.pair(find, find2);
                                        
                                        @Override
                                        public List<?> convertInput(final Object o) {
                                            return new ConvertingList<Object>(Arrays.asList((Object[])o), this.val$duplexConverter);
                                        }
                                    };
                                }
                            }
                            return new Converter<Object, List<?>>(typeDeclaration, this.output) {
                                @Override
                                public List<?> convertInput(final Object o) {
                                    final int length = Array.getLength(o);
                                    final ArrayList list = new ArrayList<Object>(length);
                                    for (int i = 0; i < length; ++i) {
                                        list.add(find.convert(Array.get(o, i)));
                                    }
                                    return list;
                                }
                            };
                        }
                        
                        @Override
                        public boolean isLazy() {
                            return true;
                        }
                    });
                }
                if (typeDeclaration.type.isArray()) {
                    if (typeDeclaration.type.isArray() && !typeDeclaration.type.getComponentType().isPrimitive()) {
                        list.add(new Converter<List<?>, Object[]>(TypeDeclaration.fromClass(List.class).setGenericTypes(TypeDeclaration.fromClass(typeDeclaration.type.getComponentType()).setGenericTypes(typeDeclaration.genericTypes)), typeDeclaration) {
                            @Override
                            public Object[] convertInput(final List<?> list) {
                                return list.toArray((Object[])Array.newInstance(this.output.type.getComponentType(), list.size()));
                            }
                        });
                    }
                    list.add(new InputConverter<Object>(TypeDeclaration.fromClass(Collection.class), typeDeclaration) {
                        final /* synthetic */ TypeDeclaration val$outputElementType = typeDeclaration.getComponentType();
                        
                        @Override
                        public Converter<?, Object> getConverter(final TypeDeclaration typeDeclaration) {
                            if (!Collection.class.isAssignableFrom(typeDeclaration.type)) {
                                return null;
                            }
                            final TypeDeclaration genericType = typeDeclaration.getGenericType(0);
                            final Converter<Object, Object> find = Conversion.find(genericType, this.val$outputElementType);
                            if (find == null) {
                                return null;
                            }
                            return new Converter<Collection<?>, Object>(typeDeclaration, this.output) {
                                final /* synthetic */ TypeDeclaration val$outputElementType = ArrayConversion$1$4.this.val$outputElementType;
                                
                                @Override
                                public Object convertInput(final Collection<?> collection) {
                                    final Object[] array = collection.toArray((Object[])MountiplexUtil.createArray(genericType.type, collection.size()));
                                    final Object instance = Array.newInstance(this.val$outputElementType.type, array.length);
                                    for (int i = 0; i < array.length; ++i) {
                                        Array.set(instance, i, find.convert(array[i]));
                                    }
                                    return instance;
                                }
                            };
                        }
                    });
                    list.add(new InputConverter<Object>(TypeDeclaration.parse("Object[]"), typeDeclaration) {
                        final /* synthetic */ TypeDeclaration val$elementOutput = typeDeclaration.getComponentType();
                        
                        @Override
                        public Converter<?, Object> getConverter(final TypeDeclaration typeDeclaration) {
                            if (!typeDeclaration.type.isArray()) {
                                return null;
                            }
                            final Converter<Object, Object> find = Conversion.find(typeDeclaration.getComponentType(), this.val$elementOutput);
                            if (find == null) {
                                return null;
                            }
                            return new Converter<Object, Object>(typeDeclaration, this.output) {
                                final /* synthetic */ TypeDeclaration val$elementOutput = ArrayConversion$1$5.this.val$elementOutput;
                                
                                @Override
                                public Object convertInput(final Object o) {
                                    final int length = Array.getLength(o);
                                    final Object instance = Array.newInstance(this.val$elementOutput.type, length);
                                    for (int i = 0; i < length; ++i) {
                                        Array.set(instance, i, find.convert(Array.get(o, i)));
                                    }
                                    return instance;
                                }
                            };
                        }
                    });
                }
            }
        });
    }
    
    private static final class ArrayConverter extends RawConverter
    {
        private final Converter<Object, Object> componentConverter;
        
        public ArrayConverter(final Class<?> clazz, final Class<?> clazz2) {
            super(clazz, clazz2);
            this.componentConverter = Conversion.find(clazz.getComponentType(), clazz2.getComponentType());
        }
        
        @Override
        public Object convertInput(final Object o) {
            final int length = Array.getLength(o);
            final Object instance = Array.newInstance(this.output.type.getComponentType(), length);
            for (int i = 0; i < length; ++i) {
                Array.set(instance, i, this.componentConverter.convert(Array.get(o, i)));
            }
            return instance;
        }
    }
}
