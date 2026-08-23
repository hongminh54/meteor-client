// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.mountiplex.conversion.builtin;

import dev.imanity.antixray.libs.mountiplex.conversion.type.NullConverter;
import dev.imanity.antixray.libs.mountiplex.conversion.type.InputConverter;
import dev.imanity.antixray.libs.mountiplex.conversion.Conversion;
import dev.imanity.antixray.libs.mountiplex.conversion.util.ConvertingSet;
import java.util.HashSet;
import java.util.Set;
import dev.imanity.antixray.libs.mountiplex.conversion.util.ConvertingList;
import dev.imanity.antixray.libs.mountiplex.conversion.util.ConvertingQueue;
import java.util.LinkedList;
import java.util.Queue;
import dev.imanity.antixray.libs.mountiplex.conversion.util.ConvertingCollection;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Collection;
import dev.imanity.antixray.libs.mountiplex.conversion.util.ConvertingIterable;
import dev.imanity.antixray.libs.mountiplex.conversion.type.DuplexConverter;
import dev.imanity.antixray.libs.mountiplex.conversion.Converter;
import java.util.List;
import dev.imanity.antixray.libs.mountiplex.reflection.declarations.TypeDeclaration;
import dev.imanity.antixray.libs.mountiplex.conversion.ConverterProvider;

public class CollectionConversion
{
    public static void register() {
        Conversion.registerProvider(new ConverterProvider() {
            @Override
            public void getConverters(final TypeDeclaration typeDeclaration, final List<Converter<?, ?>> list) {
                if (typeDeclaration.type.equals(Iterable.class)) {
                    list.add(new CollectionConverter<Iterable<?>>(typeDeclaration) {
                        @Override
                        protected boolean isOneWay() {
                            return true;
                        }
                        
                        @Override
                        protected Iterable<?> change(final Iterable<?> iterable) {
                            return iterable;
                        }
                        
                        @Override
                        protected Iterable<?> create(final Iterable<?> iterable, final DuplexConverter<Object, Object> duplexConverter) {
                            return new ConvertingIterable<Object>(iterable, duplexConverter.getInputConverter());
                        }
                    });
                }
                if (typeDeclaration.type.equals(Collection.class)) {
                    list.add(new CollectionConverter<Collection<?>>(typeDeclaration) {
                        @Override
                        protected Collection<?> change(final Iterable<?> iterable) {
                            if (iterable instanceof Collection) {
                                return (Collection<?>)iterable;
                            }
                            final ArrayList list = new ArrayList();
                            final Iterator<Object> iterator = iterable.iterator();
                            while (iterator.hasNext()) {
                                list.add(iterator.next());
                            }
                            return list;
                        }
                        
                        @Override
                        protected Collection<?> create(final Collection<?> collection, final DuplexConverter<Object, Object> duplexConverter) {
                            return new ConvertingCollection<Object>(collection, duplexConverter);
                        }
                    });
                }
                if (typeDeclaration.type.equals(Queue.class)) {
                    list.add(new CollectionConverter<Queue<?>>(typeDeclaration) {
                        @Override
                        protected Queue<?> change(final Iterable<?> iterable) {
                            if (iterable instanceof Collection) {
                                return new LinkedList<Object>((Collection<?>)iterable);
                            }
                            final LinkedList list = new LinkedList();
                            final Iterator<Object> iterator = iterable.iterator();
                            while (iterator.hasNext()) {
                                list.add(iterator.next());
                            }
                            return list;
                        }
                        
                        @Override
                        protected Queue<?> create(final Queue<?> queue, final DuplexConverter<Object, Object> duplexConverter) {
                            return new ConvertingQueue<Object>(queue, duplexConverter);
                        }
                    });
                }
                if (typeDeclaration.type.equals(List.class)) {
                    list.add(new CollectionConverter<List<?>>(typeDeclaration) {
                        @Override
                        protected List<?> change(final Iterable<?> iterable) {
                            if (iterable instanceof Collection) {
                                return new ArrayList<Object>((Collection<?>)iterable);
                            }
                            final ArrayList list = new ArrayList();
                            final Iterator<Object> iterator = iterable.iterator();
                            while (iterator.hasNext()) {
                                list.add(iterator.next());
                            }
                            return list;
                        }
                        
                        @Override
                        protected List<?> create(final List<?> list, final DuplexConverter<Object, Object> duplexConverter) {
                            return new ConvertingList<Object>(list, duplexConverter);
                        }
                    });
                }
                if (typeDeclaration.type.equals(Set.class)) {
                    list.add(new CollectionConverter<Set<?>>(typeDeclaration) {
                        @Override
                        protected Set<?> change(final Iterable<?> iterable) {
                            if (iterable instanceof Collection) {
                                return new HashSet<Object>((Collection<?>)iterable);
                            }
                            final HashSet set = new HashSet();
                            final Iterator<Object> iterator = iterable.iterator();
                            while (iterator.hasNext()) {
                                set.add(iterator.next());
                            }
                            return set;
                        }
                        
                        @Override
                        protected Set<?> create(final Set<?> set, final DuplexConverter<Object, Object> duplexConverter) {
                            return new ConvertingSet<Object>(set, duplexConverter);
                        }
                    });
                }
            }
        });
    }
    
    private abstract static class CollectionConverter<T extends Iterable<?>> extends InputConverter<T>
    {
        public CollectionConverter(final TypeDeclaration typeDeclaration) {
            super(TypeDeclaration.fromClass(Iterable.class), typeDeclaration);
        }
        
        protected abstract T change(final Iterable<?> p0);
        
        protected abstract T create(final T p0, final DuplexConverter<Object, Object> p1);
        
        protected boolean isOneWay() {
            return false;
        }
        
        @Override
        public int getCost() {
            return 2;
        }
        
        @Override
        public final Converter<?, T> getConverter(final TypeDeclaration typeDeclaration) {
            final TypeDeclaration castAsType = typeDeclaration.castAsType(Iterable.class);
            TypeDeclaration typeDeclaration2;
            if (castAsType == null) {
                typeDeclaration2 = TypeDeclaration.OBJECT;
            }
            else {
                typeDeclaration2 = castAsType.getGenericType(0);
            }
            final TypeDeclaration genericType = this.output.getGenericType(0);
            DuplexConverter<Object, Object> duplexConverter;
            if (this.isOneWay()) {
                Converter<Object, Object> find;
                if (genericType.isAssignableFrom(typeDeclaration2)) {
                    if (typeDeclaration.type.equals(this.output.type)) {
                        return (Converter<?, T>)new NullConverter(typeDeclaration, this.output);
                    }
                    find = new NullConverter(typeDeclaration2, genericType);
                }
                else {
                    find = Conversion.find(typeDeclaration2, genericType);
                    if (find == null) {
                        return null;
                    }
                }
                duplexConverter = DuplexConverter.pair(find, (Converter<?, Object>)new Converter<Object, Object>(genericType, typeDeclaration2) {
                    @Override
                    public Object convertInput(final Object o) {
                        throw new UnsupportedOperationException("This collection converter only works one-way!");
                    }
                });
            }
            else if (typeDeclaration2.equals(genericType)) {
                duplexConverter = DuplexConverter.createNull(typeDeclaration2);
            }
            else {
                duplexConverter = Conversion.findDuplex(typeDeclaration2, genericType);
                if (duplexConverter == null) {
                    return null;
                }
            }
            return new ElementConverter(typeDeclaration, this.output, duplexConverter);
        }
        
        private final class ElementConverter extends DuplexConverter<T, T>
        {
            private final DuplexConverter<Object, Object> elementConverter;
            private final boolean convertCollection;
            
            public ElementConverter(final TypeDeclaration typeDeclaration, final TypeDeclaration typeDeclaration2, final DuplexConverter<Object, Object> elementConverter) {
                super(typeDeclaration, typeDeclaration2);
                this.elementConverter = elementConverter;
                this.convertCollection = !typeDeclaration2.type.isAssignableFrom(typeDeclaration.type);
            }
            
            @Override
            public final T convertInput(T change) {
                if (this.convertCollection) {
                    if (!(change instanceof Iterable)) {
                        return null;
                    }
                    change = CollectionConverter.this.change(change);
                }
                return (T)CollectionConverter.this.create(change, this.elementConverter);
            }
            
            @Override
            public final T convertOutput(final T t) {
                Iterable<?> iterable = CollectionConverter.this.create(t, this.elementConverter.reverse());
                if (this.convertCollection) {
                    if (!(iterable instanceof Collection)) {
                        return null;
                    }
                    iterable = CollectionConverter.this.change(iterable);
                }
                return (T)iterable;
            }
        }
    }
}
