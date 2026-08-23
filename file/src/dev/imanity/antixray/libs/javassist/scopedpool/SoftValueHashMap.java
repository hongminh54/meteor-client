// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.javassist.scopedpool;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.lang.ref.Reference;
import java.util.Iterator;
import java.util.AbstractMap;
import java.util.HashSet;
import java.util.Set;
import java.lang.ref.ReferenceQueue;
import java.util.Map;

public class SoftValueHashMap<K, V> implements Map<K, V>
{
    private Map<K, SoftValueRef<K, V>> hash;
    private ReferenceQueue<V> queue;
    
    @Override
    public Set<Entry<K, V>> entrySet() {
        this.processQueue();
        final HashSet set = new HashSet();
        for (final Entry entry : this.hash.entrySet()) {
            set.add(new AbstractMap.SimpleImmutableEntry(entry.getKey(), ((SoftValueRef)entry.getValue()).get()));
        }
        return set;
    }
    
    private void processQueue() {
        if (!this.hash.isEmpty()) {
            Reference<? extends V> poll;
            while ((poll = this.queue.poll()) != null) {
                if (poll instanceof SoftValueRef) {
                    final SoftValueRef softValueRef = (SoftValueRef)poll;
                    if (poll != this.hash.get(softValueRef.key)) {
                        continue;
                    }
                    this.hash.remove(softValueRef.key);
                }
            }
        }
    }
    
    public SoftValueHashMap(final int initialCapacity, final float loadFactor) {
        this.queue = new ReferenceQueue<V>();
        this.hash = new ConcurrentHashMap<K, SoftValueRef<K, V>>(initialCapacity, loadFactor);
    }
    
    public SoftValueHashMap(final int initialCapacity) {
        this.queue = new ReferenceQueue<V>();
        this.hash = new ConcurrentHashMap<K, SoftValueRef<K, V>>(initialCapacity);
    }
    
    public SoftValueHashMap() {
        this.queue = new ReferenceQueue<V>();
        this.hash = new ConcurrentHashMap<K, SoftValueRef<K, V>>();
    }
    
    public SoftValueHashMap(final Map<K, V> map) {
        this(Math.max(2 * map.size(), 11), 0.75f);
        this.putAll((Map<? extends K, ? extends V>)map);
    }
    
    @Override
    public int size() {
        this.processQueue();
        return this.hash.size();
    }
    
    @Override
    public boolean isEmpty() {
        this.processQueue();
        return this.hash.isEmpty();
    }
    
    @Override
    public boolean containsKey(final Object o) {
        this.processQueue();
        return this.hash.containsKey(o);
    }
    
    @Override
    public V get(final Object o) {
        this.processQueue();
        return this.valueOrNull(this.hash.get(o));
    }
    
    @Override
    public V put(final K k, final V v) {
        this.processQueue();
        return this.valueOrNull(this.hash.put(k, (SoftValueRef<K, V>)create(k, v, this.queue)));
    }
    
    @Override
    public V remove(final Object o) {
        this.processQueue();
        return this.valueOrNull(this.hash.remove(o));
    }
    
    @Override
    public void clear() {
        this.processQueue();
        this.hash.clear();
    }
    
    @Override
    public boolean containsValue(final Object o) {
        this.processQueue();
        if (null == o) {
            return false;
        }
        for (final SoftValueRef softValueRef : this.hash.values()) {
            if (null != softValueRef && o.equals(softValueRef.get())) {
                return true;
            }
        }
        return false;
    }
    
    @Override
    public Set<K> keySet() {
        this.processQueue();
        return this.hash.keySet();
    }
    
    @Override
    public void putAll(final Map<? extends K, ? extends V> map) {
        this.processQueue();
        for (final K next : map.keySet()) {
            this.put(next, map.get(next));
        }
    }
    
    @Override
    public Collection<V> values() {
        this.processQueue();
        final ArrayList list = new ArrayList();
        final Iterator<SoftValueRef<K, V>> iterator = this.hash.values().iterator();
        while (iterator.hasNext()) {
            list.add(iterator.next().get());
        }
        return list;
    }
    
    private V valueOrNull(final SoftValueRef<K, V> softValueRef) {
        if (null == softValueRef) {
            return null;
        }
        return softValueRef.get();
    }
    
    private static class SoftValueRef<K, V> extends SoftReference<V>
    {
        public K key;
        
        private SoftValueRef(final K key, final V referent, final ReferenceQueue<V> q) {
            super(referent, q);
            this.key = key;
        }
        
        private static <K, V> SoftValueRef<K, V> create(final K k, final V v, final ReferenceQueue<V> referenceQueue) {
            if (v == null) {
                return null;
            }
            return new SoftValueRef<K, V>(k, v, referenceQueue);
        }
    }
}
