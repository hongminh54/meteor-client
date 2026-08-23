// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.mountiplex.reflection.util;

import java.util.ArrayList;
import dev.imanity.antixray.libs.mountiplex.MountiplexUtil;
import java.util.List;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.Collection;
import java.util.Map;
import dev.imanity.antixray.libs.mountiplex.reflection.declarations.TypeDeclaration;
import java.util.HashMap;

public abstract class TypeMap<T>
{
    private final HashMap<TypeDeclaration, Bin> map;
    
    public TypeMap() {
        this.map = new HashMap<TypeDeclaration, Bin>();
    }
    
    protected TypeMap(final TypeMap<T> typeMap) {
        this.map = new HashMap<TypeDeclaration, Bin>(typeMap.map.size());
        for (final Map.Entry entry : typeMap.map.entrySet()) {
            this.putAll((TypeDeclaration)entry.getKey(), ((Bin)entry.getValue()).values);
        }
    }
    
    public T get(final Class<?> clazz) {
        return this.get(TypeDeclaration.fromClass(clazz));
    }
    
    public T get(final TypeDeclaration typeDeclaration) {
        final Iterator<T> iterator = this.getBin(typeDeclaration).getCache().iterator();
        if (iterator.hasNext()) {
            return iterator.next();
        }
        return null;
    }
    
    public Collection<T> getAll(final Class<?> clazz) {
        return this.getAll(TypeDeclaration.fromClass(clazz));
    }
    
    public Collection<T> getAll(final TypeDeclaration typeDeclaration) {
        return this.getBin(typeDeclaration).getCache();
    }
    
    public void add(final Class<?> clazz, final T t) {
        this.add(TypeDeclaration.fromClass(clazz), t);
    }
    
    public void add(final TypeDeclaration typeDeclaration, final T o) {
        final Bin bin = this.getBin(typeDeclaration);
        if (bin.values.isEmpty()) {
            bin.values = Collections.singletonList(o);
        }
        else {
            final Object[] a = new Object[bin.values.size() + 1];
            bin.values.toArray(a);
            a[((T[])a).length - 1] = o;
            bin.values = Arrays.asList((T[])a);
        }
        bin.invalidateCache(1);
    }
    
    public void remove(final Class<?> clazz, final T t) {
        this.remove(TypeDeclaration.fromClass(clazz), t);
    }
    
    public void remove(final TypeDeclaration typeDeclaration, final T t) {
        final Bin bin = this.getBin(typeDeclaration);
        final List<T> values = bin.values;
        final int index = values.indexOf(t);
        if (index != -1) {
            final int size = values.size();
            if (size == 1) {
                bin.values = Collections.emptyList();
            }
            else {
                final Object[] a = new Object[size - 1];
                for (int i = 0; i < index; ++i) {
                    a[i] = values.get(i);
                }
                for (int j = index + 1; j < size; ++j) {
                    a[j - 1] = values.get(j);
                }
                bin.values = Arrays.asList((T[])a);
            }
            bin.invalidateCache(-1);
        }
    }
    
    public void put(final Class<?> clazz, final T t) {
        this.put(TypeDeclaration.fromClass(clazz), t);
    }
    
    public void put(final TypeDeclaration typeDeclaration, final T o) {
        final Bin bin = this.getBin(typeDeclaration);
        final int n = 1 - bin.values.size();
        bin.values = Collections.singletonList(o);
        bin.invalidateCache(n);
    }
    
    public void putAll(final Class<?> clazz, final Collection<T> collection) {
        this.putAll(TypeDeclaration.fromClass(clazz), collection);
    }
    
    public void putAll(final TypeDeclaration typeDeclaration, final Collection<T> collection) {
        final Bin bin = this.getBin(typeDeclaration);
        final int n = -bin.values.size();
        bin.values = MountiplexUtil.createUmodifiableList(collection);
        bin.invalidateCache(n + bin.values.size());
    }
    
    public boolean amend(final TypeDeclaration typeDeclaration, final T o) {
        final Bin bin = this.getBin(typeDeclaration);
        if (bin.values.isEmpty()) {
            bin.values = Collections.singletonList(o);
            bin.invalidateCache(1);
            return true;
        }
        return false;
    }
    
    public boolean amendAll(final TypeDeclaration typeDeclaration, final Collection<T> collection) {
        final Bin bin = this.getBin(typeDeclaration);
        if (bin.values.isEmpty()) {
            bin.values = MountiplexUtil.createUmodifiableList(collection);
            bin.invalidateCache(bin.values.size());
            return true;
        }
        return false;
    }
    
    public boolean containsKey(final TypeDeclaration typeDeclaration) {
        return !this.getBin(typeDeclaration).isEmpty();
    }
    
    public void clear() {
        this.map.clear();
    }
    
    public Collection<T> values() {
        if (this.map.isEmpty()) {
            return (Collection<T>)Collections.emptyList();
        }
        final ArrayList list = new ArrayList();
        final Iterator<Bin> iterator = this.map.values().iterator();
        while (iterator.hasNext()) {
            list.addAll(iterator.next().values);
        }
        return list;
    }
    
    private final Bin getBin(final TypeDeclaration typeDeclaration) {
        Bin value = this.map.get(typeDeclaration);
        if (value == null) {
            value = new Bin(typeDeclaration);
            for (final Map.Entry entry : this.map.entrySet()) {
                if (this.isParentTypeOf(typeDeclaration, (TypeDeclaration)entry.getKey())) {
                    ((Bin)entry.getValue()).link(value);
                }
                else {
                    if (!this.isParentTypeOf((TypeDeclaration)entry.getKey(), typeDeclaration)) {
                        continue;
                    }
                    value.link((Bin)entry.getValue());
                }
            }
            this.map.put(typeDeclaration, value);
        }
        return value;
    }
    
    protected abstract boolean isParentTypeOf(final TypeDeclaration p0, final TypeDeclaration p1);
    
    private class Bin implements Comparable<Bin>
    {
        public final TypeDeclaration type;
        public List<T> values;
        private final ArrayList<Bin> parents;
        private final ArrayList<Bin> children;
        private ArrayList<T> cache;
        private int expectedCacheSize;
        
        public Bin(final TypeDeclaration type) {
            this.values = Collections.emptyList();
            this.parents = new ArrayList<Bin>(1);
            this.children = new ArrayList<Bin>(1);
            this.cache = null;
            this.expectedCacheSize = 0;
            this.type = type;
        }
        
        public final void link(final Bin e) {
            this.parents.add(e);
            Collections.sort(this.parents);
            this.invalidateOwnCache(e.values.size());
            e.children.add(this);
        }
        
        public void invalidateCache(final int n) {
            this.invalidateOwnCache(n);
            final Iterator<Bin> iterator = this.children.iterator();
            while (iterator.hasNext()) {
                iterator.next().invalidateOwnCache(n);
            }
        }
        
        private void invalidateOwnCache(final int n) {
            this.cache = null;
            this.expectedCacheSize += n;
        }
        
        public boolean isEmpty() {
            return this.values.isEmpty() && this.getCache().isEmpty();
        }
        
        public Collection<T> getCache() {
            if (this.cache == null) {
                (this.cache = new ArrayList<T>(this.expectedCacheSize)).addAll((Collection<? extends T>)this.values);
                final Iterator<Bin> iterator = this.parents.iterator();
                while (iterator.hasNext()) {
                    this.cache.addAll((Collection<? extends T>)iterator.next().values);
                }
                this.expectedCacheSize = this.cache.size();
            }
            return this.cache;
        }
        
        @Override
        public int compareTo(final Bin bin) {
            if (bin.type.equals(this.type)) {
                return 0;
            }
            if (this.type.isAssignableFrom(bin.type)) {
                return 1;
            }
            return -1;
        }
    }
}
