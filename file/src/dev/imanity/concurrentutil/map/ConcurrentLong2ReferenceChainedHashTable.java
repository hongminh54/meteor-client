// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.concurrentutil.map;

import java.util.NoSuchElementException;
import java.util.function.LongConsumer;
import java.util.function.Consumer;
import dev.imanity.concurrentutil.util.ConcurrentUtil;
import java.util.PrimitiveIterator;
import java.util.Iterator;
import java.util.function.BiFunction;
import java.util.function.LongFunction;
import dev.imanity.concurrentutil.util.ThrowUtil;
import java.util.function.Predicate;
import java.util.Arrays;
import dev.imanity.concurrentutil.util.Validate;
import dev.imanity.concurrentutil.util.HashUtil;
import dev.imanity.concurrentutil.util.IntegerUtil;
import java.lang.invoke.VarHandle;
import java.util.concurrent.atomic.LongAdder;

public class ConcurrentLong2ReferenceChainedHashTable<V> implements Iterable<TableEntry<V>>
{
    protected static final int DEFAULT_CAPACITY = 16;
    protected static final float DEFAULT_LOAD_FACTOR = 0.75f;
    protected static final int MAXIMUM_CAPACITY = 1073741824;
    protected final LongAdder size;
    protected final float loadFactor;
    protected volatile TableEntry<V>[] table;
    protected static final int THRESHOLD_NO_RESIZE = -1;
    protected static final int THRESHOLD_RESIZING = -2;
    protected volatile int threshold;
    protected static final VarHandle THRESHOLD_HANDLE;
    
    protected final int getThresholdAcquire() {
        return ConcurrentLong2ReferenceChainedHashTable.THRESHOLD_HANDLE.getAcquire(this);
    }
    
    protected final int getThresholdVolatile() {
        return ConcurrentLong2ReferenceChainedHashTable.THRESHOLD_HANDLE.getVolatile(this);
    }
    
    protected final void setThresholdPlain(final int n) {
        ConcurrentLong2ReferenceChainedHashTable.THRESHOLD_HANDLE.set(this, n);
    }
    
    protected final void setThresholdRelease(final int n) {
        ConcurrentLong2ReferenceChainedHashTable.THRESHOLD_HANDLE.setRelease(this, n);
    }
    
    protected final void setThresholdVolatile(final int n) {
        ConcurrentLong2ReferenceChainedHashTable.THRESHOLD_HANDLE.setVolatile(this, n);
    }
    
    protected final int compareExchangeThresholdVolatile(final int n, final int n2) {
        return ConcurrentLong2ReferenceChainedHashTable.THRESHOLD_HANDLE.compareAndExchange(this, n, n2);
    }
    
    public ConcurrentLong2ReferenceChainedHashTable() {
        this(16, 0.75f);
    }
    
    protected static int getTargetThreshold(final int n, final float n2) {
        final double n3 = n * (double)n2;
        if (Double.isInfinite(n3) || n3 >= 2.147483647E9) {
            return -1;
        }
        return (int)Math.ceil(n3);
    }
    
    protected static int getCapacityFor(final int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Invalid capacity: " + n);
        }
        if (n >= 1073741824) {
            return 1073741824;
        }
        return IntegerUtil.roundCeilLog2(n);
    }
    
    protected ConcurrentLong2ReferenceChainedHashTable(final int n, final float n2) {
        this.size = new LongAdder();
        final int capacity = getCapacityFor(n);
        if (n2 <= 0.0 || !Float.isFinite(n2)) {
            throw new IllegalArgumentException("Invalid load factor: " + n2);
        }
        if (capacity == 1073741824) {
            this.setThresholdPlain(-1);
        }
        else {
            this.setThresholdPlain(getTargetThreshold(capacity, n2));
        }
        this.loadFactor = n2;
        this.table = new TableEntry[capacity];
    }
    
    public static <V> ConcurrentLong2ReferenceChainedHashTable<V> createWithCapacity(final int n) {
        return createWithCapacity(n, 0.75f);
    }
    
    public static <V> ConcurrentLong2ReferenceChainedHashTable<V> createWithCapacity(final int n, final float n2) {
        return new ConcurrentLong2ReferenceChainedHashTable<V>(n, n2);
    }
    
    public static <V> ConcurrentLong2ReferenceChainedHashTable<V> createWithExpected(final int n) {
        return createWithExpected(n, 0.75f);
    }
    
    public static <V> ConcurrentLong2ReferenceChainedHashTable<V> createWithExpected(final int n, final float n2) {
        return (ConcurrentLong2ReferenceChainedHashTable<V>)createWithCapacity((int)Math.ceil(n / (double)n2), n2);
    }
    
    protected static int getHash(final long n) {
        return (int)HashUtil.mix(n);
    }
    
    public final float getLoadFactor() {
        return this.loadFactor;
    }
    
    protected static <V> TableEntry<V> getAtIndexVolatile(final TableEntry<V>[] array, final int n) {
        return TableEntry.TABLE_ENTRY_ARRAY_HANDLE.getVolatile((TableEntry[])array, n);
    }
    
    protected static <V> void setAtIndexRelease(final TableEntry<V>[] array, final int n, final TableEntry<V> tableEntry) {
        TableEntry.TABLE_ENTRY_ARRAY_HANDLE.setRelease((TableEntry[])array, n, (TableEntry)tableEntry);
    }
    
    protected static <V> void setAtIndexVolatile(final TableEntry<V>[] array, final int n, final TableEntry<V> tableEntry) {
        TableEntry.TABLE_ENTRY_ARRAY_HANDLE.setVolatile((TableEntry[])array, n, (TableEntry)tableEntry);
    }
    
    protected static <V> TableEntry<V> compareAndExchangeAtIndexVolatile(final TableEntry<V>[] array, final int n, final TableEntry<V> tableEntry, final TableEntry<V> tableEntry2) {
        return TableEntry.TABLE_ENTRY_ARRAY_HANDLE.compareAndExchange((TableEntry[])array, n, (TableEntry)tableEntry, (TableEntry)tableEntry2);
    }
    
    protected final TableEntry<V> getNode(final long n) {
        final int hash = getHash(n);
        TableEntry<V>[] table = this.table;
        TableEntry<V> tableEntry;
        while (true) {
            tableEntry = getAtIndexVolatile(table, hash & table.length - 1);
            if (tableEntry == null) {
                return tableEntry;
            }
            if (!tableEntry.resize) {
                break;
            }
            table = (TableEntry[])(Object)tableEntry.getValuePlain();
        }
        while (tableEntry != null) {
            if (tableEntry.key == n) {
                return tableEntry;
            }
            tableEntry = tableEntry.getNextVolatile();
        }
        return tableEntry;
    }
    
    public V get(final long n) {
        final TableEntry<V> node = this.getNode(n);
        return (node == null) ? null : node.getValueVolatile();
    }
    
    public V getOrDefault(final long n, final V v) {
        final TableEntry<V> node = this.getNode(n);
        if (node == null) {
            return v;
        }
        final V valueVolatile = node.getValueVolatile();
        if (valueVolatile == null) {
            return v;
        }
        return valueVolatile;
    }
    
    public boolean containsKey(final long n) {
        return this.get(n) != null;
    }
    
    public boolean containsValue(final V v) {
        Validate.notNull(v, "Value cannot be null");
        TableEntry next;
        while ((next = new NodeIterator(this.table).findNext()) != null) {
            if (next.getValueAcquire() == v) {
                return true;
            }
        }
        return false;
    }
    
    public int size() {
        final long sum = this.size.sum();
        if (sum < 0L) {
            return 0;
        }
        if (sum > 2147483647L) {
            return Integer.MAX_VALUE;
        }
        return (int)sum;
    }
    
    public boolean isEmpty() {
        return this.size.sum() <= 0L;
    }
    
    protected final void addSize(final long x) {
        this.size.add(x);
        final int thresholdAcquire = this.getThresholdAcquire();
        if (thresholdAcquire < 0L) {
            return;
        }
        final long sum = this.size.sum();
        if (sum < thresholdAcquire) {
            return;
        }
        if (thresholdAcquire != this.compareExchangeThresholdVolatile(thresholdAcquire, -2)) {
            return;
        }
        this.resize(sum);
    }
    
    private void resize(final long n) {
        final double a = n / (double)this.loadFactor + 1.0;
        int roundCeilLog2;
        if (a >= 1.073741824E9) {
            roundCeilLog2 = 1073741824;
        }
        else {
            roundCeilLog2 = IntegerUtil.roundCeilLog2((int)Math.ceil(a));
            if (roundCeilLog2 > 1073741824) {
                roundCeilLog2 = 1073741824;
            }
        }
        final TableEntry[] table = new TableEntry[roundCeilLog2];
        final TableEntry tableEntry = new TableEntry<V>(0L, table, true);
        final TableEntry<V>[] table2 = this.table;
        final int floorLog2 = IntegerUtil.floorLog2(table2.length);
        final int n2 = IntegerUtil.floorLog2(roundCeilLog2) - floorLog2;
        if (n2 == 0) {
            throw new IllegalStateException("Resizing to same size");
        }
        final TableEntry[] a2 = new TableEntry[1 << n2];
        int i = 0;
        final int length = table2.length;
    Label_0350_Outer:
        while (i < length) {
            TableEntry<V> tableEntry2 = getAtIndexVolatile(table2, i);
            while (true) {
                while (tableEntry2 != null || null != (tableEntry2 = compareAndExchangeAtIndexVolatile(table2, i, null, (TableEntry<V>)tableEntry))) {
                    synchronized (tableEntry2) {
                        if (tableEntry2 != (tableEntry2 = getAtIndexVolatile(table2, i))) {
                            continue Label_0350_Outer;
                        }
                        if (tableEntry2.getNextPlain() == null) {
                            table[getHash(tableEntry2.key) & roundCeilLog2 - 1] = tableEntry2;
                        }
                        else {
                            Arrays.fill(a2, null);
                            while (tableEntry2 != null) {
                                final int n3 = getHash(tableEntry2.key) & roundCeilLog2 - 1;
                                final int n4 = n3 >>> floorLog2;
                                final TableEntry nextPlain = new TableEntry<V>(tableEntry2.key, tableEntry2.getValuePlain());
                                final TableEntry tableEntry3 = a2[n4];
                                a2[n4] = nextPlain;
                                if (tableEntry3 == null) {
                                    table[n3] = nextPlain;
                                }
                                else {
                                    tableEntry3.setNextPlain(nextPlain);
                                }
                                tableEntry2.getNextPlain();
                            }
                        }
                        setAtIndexRelease((TableEntry<V>[])table2, i, (TableEntry<V>)tableEntry);
                    }
                    ++i;
                    continue Label_0350_Outer;
                }
                continue;
            }
        }
        int targetThreshold;
        if (roundCeilLog2 == 1073741824) {
            targetThreshold = -1;
        }
        else {
            targetThreshold = getTargetThreshold(roundCeilLog2, this.loadFactor);
        }
        this.table = table;
        this.setThresholdVolatile(targetThreshold);
    }
    
    protected final void subSize(final long n) {
        this.size.add(-n);
    }
    
    public V put(final long n, final V valueVolatile) {
        Validate.notNull(valueVolatile, "Value may not be null");
        final int hash = getHash(n);
        TableEntry<V>[] table = this.table;
    Label_0020:
        while (true) {
            final int n2 = hash & table.length - 1;
            TableEntry<V> tableEntry = getAtIndexVolatile(table, n2);
            while (tableEntry != null || null != (tableEntry = compareAndExchangeAtIndexVolatile(table, n2, null, new TableEntry<V>(n, valueVolatile)))) {
                if (tableEntry.resize) {
                    table = (TableEntry[])(Object)tableEntry.getValuePlain();
                    continue Label_0020;
                }
                synchronized (tableEntry) {
                    if (tableEntry != (tableEntry = getAtIndexVolatile(table, n2))) {
                        continue;
                    }
                    while (tableEntry != null) {
                        if (tableEntry.key == n) {
                            final V valuePlain = tableEntry.getValuePlain();
                            tableEntry.setValueVolatile(valueVolatile);
                            return valuePlain;
                        }
                        tableEntry = tableEntry.getNextPlain();
                    }
                    tableEntry.setNextRelease(new TableEntry<V>(n, valueVolatile));
                }
                this.addSize(1L);
                return null;
            }
            this.addSize(1L);
            return null;
        }
    }
    
    public V putIfAbsent(final long n, final V v) {
        Validate.notNull(v, "Value may not be null");
        final int hash = getHash(n);
        TableEntry<V>[] table = this.table;
    Label_0020:
        while (true) {
            final int n2 = hash & table.length - 1;
            TableEntry<V> tableEntry = getAtIndexVolatile(table, n2);
            while (tableEntry != null || null != (tableEntry = compareAndExchangeAtIndexVolatile(table, n2, null, new TableEntry<V>(n, v)))) {
                if (tableEntry.resize) {
                    table = (TableEntry[])(Object)tableEntry.getValuePlain();
                    continue Label_0020;
                }
                if (tableEntry.key == n) {
                    final V valueVolatile = tableEntry.getValueVolatile();
                    if (valueVolatile != null) {
                        return valueVolatile;
                    }
                }
                synchronized (tableEntry) {
                    if (tableEntry != (tableEntry = getAtIndexVolatile(table, n2))) {
                        continue;
                    }
                    while (tableEntry != null) {
                        if (tableEntry.key == n) {
                            return tableEntry.getValuePlain();
                        }
                        tableEntry = tableEntry.getNextPlain();
                    }
                    tableEntry.setNextRelease(new TableEntry<V>(n, v));
                }
                this.addSize(1L);
                return null;
            }
            this.addSize(1L);
            return null;
        }
    }
    
    public V replace(final long n, final V valueVolatile) {
        Validate.notNull(valueVolatile, "Value may not be null");
        final int hash = getHash(n);
        TableEntry<V>[] table = this.table;
    Label_0020:
        while (true) {
            final int n2 = hash & table.length - 1;
            TableEntry<V> tableEntry = getAtIndexVolatile(table, n2);
            while (tableEntry != null) {
                if (tableEntry.resize) {
                    table = (TableEntry[])(Object)tableEntry.getValuePlain();
                    continue Label_0020;
                }
                synchronized (tableEntry) {
                    if (tableEntry != (tableEntry = getAtIndexVolatile(table, n2))) {
                        continue;
                    }
                    while (tableEntry != null) {
                        if (tableEntry.key == n) {
                            final V valuePlain = tableEntry.getValuePlain();
                            tableEntry.setValueVolatile(valueVolatile);
                            return valuePlain;
                        }
                        tableEntry = tableEntry.getNextPlain();
                    }
                }
                return null;
            }
            return null;
        }
    }
    
    public V replace(final long n, final V v, final V valueVolatile) {
        Validate.notNull(v, "Expect may not be null");
        Validate.notNull(valueVolatile, "Update may not be null");
        final int hash = getHash(n);
        TableEntry<V>[] table = this.table;
    Label_0029:
        while (true) {
            final int n2 = hash & table.length - 1;
            TableEntry<V> tableEntry = getAtIndexVolatile(table, n2);
            while (tableEntry != null) {
                if (tableEntry.resize) {
                    table = (TableEntry[])(Object)tableEntry.getValuePlain();
                    continue Label_0029;
                }
                synchronized (tableEntry) {
                    if (tableEntry != (tableEntry = getAtIndexVolatile(table, n2))) {
                        continue;
                    }
                    while (tableEntry != null) {
                        if (tableEntry.key == n) {
                            final V valuePlain = tableEntry.getValuePlain();
                            if (valuePlain != v) {
                                return valuePlain;
                            }
                            tableEntry.setValueVolatile(valueVolatile);
                            return valuePlain;
                        }
                        else {
                            tableEntry = tableEntry.getNextPlain();
                        }
                    }
                }
                return null;
            }
            return null;
        }
    }
    
    public V remove(final long n) {
        final int hash = getHash(n);
        TableEntry<V>[] table = this.table;
    Label_0011:
        while (true) {
            final int n2 = hash & table.length - 1;
            TableEntry<V> tableEntry = getAtIndexVolatile(table, n2);
            while (tableEntry != null) {
                if (tableEntry.resize) {
                    table = (TableEntry[])(Object)tableEntry.getValuePlain();
                    continue Label_0011;
                }
                boolean b = false;
                V valuePlain = null;
                synchronized (tableEntry) {
                    if (tableEntry != (tableEntry = getAtIndexVolatile(table, n2))) {
                        continue;
                    }
                    TableEntry<V> tableEntry2 = null;
                    while (tableEntry != null) {
                        if (tableEntry.key == n) {
                            valuePlain = tableEntry.getValuePlain();
                            b = true;
                            if (tableEntry2 == null) {
                                setAtIndexRelease(table, n2, tableEntry.getNextPlain());
                                break;
                            }
                            tableEntry2.setNextRelease(tableEntry.getNextPlain());
                            break;
                        }
                        else {
                            tableEntry2 = tableEntry;
                            tableEntry = tableEntry.getNextPlain();
                        }
                    }
                }
                if (b) {
                    this.subSize(1L);
                }
                return valuePlain;
            }
            return null;
        }
    }
    
    public V remove(final long n, final V v) {
        final int hash = getHash(n);
        TableEntry<V>[] table = this.table;
    Label_0012:
        while (true) {
            final int n2 = hash & table.length - 1;
            TableEntry<V> tableEntry = getAtIndexVolatile(table, n2);
            while (tableEntry != null) {
                if (tableEntry.resize) {
                    table = (TableEntry[])(Object)tableEntry.getValuePlain();
                    continue Label_0012;
                }
                boolean b = false;
                V valuePlain = null;
                synchronized (tableEntry) {
                    if (tableEntry != (tableEntry = getAtIndexVolatile(table, n2))) {
                        continue;
                    }
                    TableEntry<V> tableEntry2 = null;
                    while (tableEntry != null) {
                        if (tableEntry.key == n) {
                            valuePlain = tableEntry.getValuePlain();
                            if (valuePlain != v) {
                                break;
                            }
                            b = true;
                            if (tableEntry2 == null) {
                                setAtIndexRelease(table, n2, tableEntry.getNextPlain());
                                break;
                            }
                            tableEntry2.setNextRelease(tableEntry.getNextPlain());
                            break;
                        }
                        else {
                            tableEntry2 = tableEntry;
                            tableEntry = tableEntry.getNextPlain();
                        }
                    }
                }
                if (b) {
                    this.subSize(1L);
                }
                return valuePlain;
            }
            return null;
        }
    }
    
    public V removeIf(final long n, final Predicate<? super V> predicate) {
        Validate.notNull(predicate, "Predicate may not be null");
        final int hash = getHash(n);
        TableEntry<V>[] table = this.table;
    Label_0020:
        while (true) {
            final int n2 = hash & table.length - 1;
            TableEntry<V> tableEntry = getAtIndexVolatile(table, n2);
            while (tableEntry != null) {
                if (tableEntry.resize) {
                    table = (TableEntry[])(Object)tableEntry.getValuePlain();
                    continue Label_0020;
                }
                boolean b = false;
                V valuePlain = null;
                synchronized (tableEntry) {
                    if (tableEntry != (tableEntry = getAtIndexVolatile(table, n2))) {
                        continue;
                    }
                    TableEntry<V> tableEntry2 = null;
                    while (tableEntry != null) {
                        if (tableEntry.key == n) {
                            valuePlain = tableEntry.getValuePlain();
                            if (!predicate.test(valuePlain)) {
                                break;
                            }
                            b = true;
                            if (tableEntry2 == null) {
                                setAtIndexRelease(table, n2, tableEntry.getNextPlain());
                                break;
                            }
                            tableEntry2.setNextRelease(tableEntry.getNextPlain());
                            break;
                        }
                        else {
                            tableEntry2 = tableEntry;
                            tableEntry = tableEntry.getNextPlain();
                        }
                    }
                }
                if (b) {
                    this.subSize(1L);
                }
                return valuePlain;
            }
            return null;
        }
    }
    
    public V compute(final long n, final BiLong1Function<? super V, ? extends V> biLong1Function) {
        final int hash = getHash(n);
        TableEntry<V>[] table = this.table;
        while (true) {
            final int n2 = hash & table.length - 1;
            TableEntry<V> tableEntry = getAtIndexVolatile(table, n2);
            while (true) {
                V apply = null;
                if (tableEntry == null) {
                    final TableEntry<V> tableEntry2 = new TableEntry<V>(n, null);
                    boolean b = false;
                    synchronized (tableEntry2) {
                        if (null == (tableEntry = compareAndExchangeAtIndexVolatile(table, n2, null, tableEntry2))) {
                            try {
                                apply = (V)biLong1Function.apply(n, null);
                            }
                            catch (final Throwable t) {
                                setAtIndexVolatile(table, n2, null);
                                ThrowUtil.throwUnchecked(t);
                                return null;
                            }
                            if (apply == null) {
                                setAtIndexVolatile(table, n2, null);
                                return apply;
                            }
                            tableEntry2.setValueRelease(apply);
                            b = true;
                        }
                    }
                    if (b) {
                        this.addSize(1L);
                        return apply;
                    }
                }
                if (tableEntry.resize) {
                    table = (TableEntry[])(Object)tableEntry.getValuePlain();
                    break;
                }
                boolean b2 = false;
                boolean b3 = false;
                synchronized (tableEntry) {
                    if (tableEntry != (tableEntry = getAtIndexVolatile(table, n2))) {
                        continue;
                    }
                    TableEntry<V> tableEntry3 = null;
                    while (tableEntry != null) {
                        if (tableEntry.key == n) {
                            final V apply2 = (V)biLong1Function.apply(n, tableEntry.getValuePlain());
                            if (apply2 != null) {
                                tableEntry.setValueVolatile(apply2);
                                return apply2;
                            }
                            if (tableEntry3 == null) {
                                setAtIndexRelease(table, n2, tableEntry.getNextPlain());
                            }
                            else {
                                tableEntry3.setNextRelease(tableEntry.getNextPlain());
                            }
                            b2 = true;
                            break;
                        }
                        else {
                            tableEntry3 = tableEntry;
                            tableEntry = tableEntry.getNextPlain();
                        }
                    }
                    if (!b2) {
                        final V apply3 = (V)biLong1Function.apply(n, null);
                        if (apply3 != null) {
                            tableEntry3.setNextRelease(new TableEntry<V>(n, apply3));
                            apply = apply3;
                            b3 = true;
                        }
                    }
                }
                if (b2) {
                    this.subSize(1L);
                }
                if (b3) {
                    this.addSize(1L);
                }
                return apply;
            }
        }
    }
    
    public V computeIfAbsent(final long n, final LongFunction<? extends V> longFunction) {
        final int hash = getHash(n);
        TableEntry<V>[] table = this.table;
        while (true) {
            final int n2 = hash & table.length - 1;
            TableEntry<V> tableEntry = getAtIndexVolatile(table, n2);
            while (true) {
                V valueRelease = null;
                if (tableEntry == null) {
                    final TableEntry<V> tableEntry2 = new TableEntry<V>(n, null);
                    boolean b = false;
                    synchronized (tableEntry2) {
                        if (null == (tableEntry = compareAndExchangeAtIndexVolatile(table, n2, null, tableEntry2))) {
                            try {
                                valueRelease = (V)longFunction.apply(n);
                            }
                            catch (final Throwable t) {
                                setAtIndexVolatile(table, n2, null);
                                ThrowUtil.throwUnchecked(t);
                                return null;
                            }
                            if (valueRelease == null) {
                                setAtIndexVolatile(table, n2, null);
                                return null;
                            }
                            tableEntry2.setValueRelease(valueRelease);
                            b = true;
                        }
                    }
                    if (b) {
                        this.addSize(1L);
                        return valueRelease;
                    }
                }
                if (tableEntry.resize) {
                    table = (TableEntry[])(Object)tableEntry.getValuePlain();
                    break;
                }
                if (tableEntry.key == n) {
                    valueRelease = tableEntry.getValueVolatile();
                    if (valueRelease != null) {
                        return valueRelease;
                    }
                }
                boolean b2 = false;
                synchronized (tableEntry) {
                    if (tableEntry != (tableEntry = getAtIndexVolatile(table, n2))) {
                        continue;
                    }
                    TableEntry<V> tableEntry3 = null;
                    while (tableEntry != null) {
                        if (tableEntry.key == n) {
                            valueRelease = tableEntry.getValuePlain();
                            return valueRelease;
                        }
                        tableEntry3 = tableEntry;
                        tableEntry = tableEntry.getNextPlain();
                    }
                    final V apply = (V)longFunction.apply(n);
                    if (apply != null) {
                        tableEntry3.setNextRelease(new TableEntry<V>(n, apply));
                        valueRelease = apply;
                        b2 = true;
                    }
                }
                if (b2) {
                    this.addSize(1L);
                }
                return valueRelease;
            }
        }
    }
    
    public V computeIfPresent(final long n, final BiLong1Function<? super V, ? extends V> biLong1Function) {
        final int hash = getHash(n);
        TableEntry<V>[] table = this.table;
    Label_0012:
        while (true) {
            final int n2 = hash & table.length - 1;
            TableEntry<V> tableEntry = getAtIndexVolatile(table, n2);
            while (tableEntry != null) {
                if (tableEntry.resize) {
                    table = (TableEntry[])(Object)tableEntry.getValuePlain();
                    continue Label_0012;
                }
                boolean b = false;
                synchronized (tableEntry) {
                    if (tableEntry != (tableEntry = getAtIndexVolatile(table, n2))) {
                        continue;
                    }
                    TableEntry<V> tableEntry2 = null;
                    while (tableEntry != null) {
                        if (tableEntry.key == n) {
                            final V apply = (V)biLong1Function.apply(n, tableEntry.getValuePlain());
                            if (apply != null) {
                                tableEntry.setValueVolatile(apply);
                                return apply;
                            }
                            if (tableEntry2 == null) {
                                setAtIndexRelease(table, n2, tableEntry.getNextPlain());
                            }
                            else {
                                tableEntry2.setNextRelease(tableEntry.getNextPlain());
                            }
                            b = true;
                            break;
                        }
                        else {
                            tableEntry2 = tableEntry;
                            tableEntry = tableEntry.getNextPlain();
                        }
                    }
                }
                if (b) {
                    this.subSize(1L);
                }
                return null;
            }
            return null;
        }
    }
    
    public V merge(final long n, final V v, final BiFunction<? super V, ? super V, ? extends V> biFunction) {
        Validate.notNull(v, "Default value may not be null");
        final int hash = getHash(n);
        TableEntry<V>[] table = this.table;
    Label_0020:
        while (true) {
            final int n2 = hash & table.length - 1;
            TableEntry<V> tableEntry = getAtIndexVolatile(table, n2);
            while (tableEntry != null || null != (tableEntry = compareAndExchangeAtIndexVolatile(table, n2, null, new TableEntry<V>(n, v)))) {
                if (tableEntry.resize) {
                    table = (TableEntry[])(Object)tableEntry.getValuePlain();
                    continue Label_0020;
                }
                boolean b = false;
                boolean b2 = false;
                V v2 = null;
                synchronized (tableEntry) {
                    if (tableEntry != (tableEntry = getAtIndexVolatile(table, n2))) {
                        continue;
                    }
                    TableEntry<V> tableEntry2 = null;
                    while (tableEntry != null) {
                        if (tableEntry.key == n) {
                            final V apply = (V)biFunction.apply(tableEntry.getValuePlain(), v);
                            if (apply != null) {
                                tableEntry.setValueVolatile(apply);
                                return apply;
                            }
                            if (tableEntry2 == null) {
                                setAtIndexRelease(table, n2, tableEntry.getNextPlain());
                            }
                            else {
                                tableEntry2.setNextRelease(tableEntry.getNextPlain());
                            }
                            b = true;
                            break;
                        }
                        else {
                            tableEntry2 = tableEntry;
                            tableEntry = tableEntry.getNextPlain();
                        }
                    }
                    if (!b) {
                        tableEntry2.setNextRelease(new TableEntry<V>(n, v));
                        v2 = v;
                        b2 = true;
                    }
                }
                if (b) {
                    this.subSize(1L);
                }
                if (b2) {
                    this.addSize(1L);
                }
                return v2;
            }
            this.addSize(1L);
            return v;
        }
    }
    
    public void clear() {
        TableEntry next;
        while ((next = new NodeIterator(this.table).findNext()) != null) {
            this.remove(next.key);
        }
    }
    
    public Iterator<TableEntry<V>> entryIterator() {
        return (Iterator<TableEntry<V>>)new EntryIterator((ConcurrentLong2ReferenceChainedHashTable<Object>)this);
    }
    
    @Override
    public final Iterator<TableEntry<V>> iterator() {
        return this.entryIterator();
    }
    
    public PrimitiveIterator.OfLong keyIterator() {
        return new KeyIterator<Object>(this);
    }
    
    public Iterator<V> valueIterator() {
        return (Iterator<V>)new ValueIterator((ConcurrentLong2ReferenceChainedHashTable<Object>)this);
    }
    
    static {
        THRESHOLD_HANDLE = ConcurrentUtil.getVarHandle(ConcurrentLong2ReferenceChainedHashTable.class, "threshold", Integer.TYPE);
    }
    
    protected static final class EntryIterator<V> extends BaseIteratorImpl<V, TableEntry<V>>
    {
        public EntryIterator(final ConcurrentLong2ReferenceChainedHashTable<V> concurrentLong2ReferenceChainedHashTable) {
            super(concurrentLong2ReferenceChainedHashTable);
        }
        
        @Override
        public TableEntry<V> next() {
            return this.nextNode();
        }
        
        @Override
        public void forEachRemaining(final Consumer<? super TableEntry<V>> consumer) {
            Validate.notNull(consumer, "Action may not be null");
            while (this.hasNext()) {
                consumer.accept(this.next());
            }
        }
    }
    
    protected static final class KeyIterator<V> extends BaseIteratorImpl<V, Long> implements PrimitiveIterator.OfLong
    {
        public KeyIterator(final ConcurrentLong2ReferenceChainedHashTable<V> concurrentLong2ReferenceChainedHashTable) {
            super(concurrentLong2ReferenceChainedHashTable);
        }
        
        @Override
        public Long next() {
            return this.nextNode().key;
        }
        
        @Override
        public long nextLong() {
            return this.nextNode().key;
        }
        
        @Override
        public void forEachRemaining(final Consumer<? super Long> consumer) {
            Validate.notNull(consumer, "Action may not be null");
            if (consumer instanceof final LongConsumer longConsumer) {
                this.forEachRemaining(longConsumer);
                return;
            }
            while (this.hasNext()) {
                consumer.accept(this.next());
            }
        }
        
        @Override
        public void forEachRemaining(final LongConsumer longConsumer) {
            Validate.notNull(longConsumer, "Action may not be null");
            while (this.hasNext()) {
                longConsumer.accept(this.nextLong());
            }
        }
    }
    
    protected static final class ValueIterator<V> extends BaseIteratorImpl<V, V>
    {
        public ValueIterator(final ConcurrentLong2ReferenceChainedHashTable<V> concurrentLong2ReferenceChainedHashTable) {
            super(concurrentLong2ReferenceChainedHashTable);
        }
        
        @Override
        public V next() {
            return this.nextNode().getValueVolatile();
        }
        
        @Override
        public void forEachRemaining(final Consumer<? super V> consumer) {
            Validate.notNull(consumer, "Action may not be null");
            while (this.hasNext()) {
                consumer.accept(this.next());
            }
        }
    }
    
    protected abstract static class BaseIteratorImpl<V, T> extends NodeIterator<V> implements Iterator<T>
    {
        protected final ConcurrentLong2ReferenceChainedHashTable<V> map;
        protected TableEntry<V> lastReturned;
        protected TableEntry<V> nextToReturn;
        
        protected BaseIteratorImpl(final ConcurrentLong2ReferenceChainedHashTable<V> map) {
            super(map.table);
            this.map = map;
        }
        
        @Override
        public final boolean hasNext() {
            if (this.nextToReturn != null) {
                return true;
            }
            final TableEntry<V> next = this.findNext();
            this.nextToReturn = next;
            return next != null;
        }
        
        protected final TableEntry<V> nextNode() {
            final TableEntry<V> nextToReturn = this.nextToReturn;
            if (nextToReturn != null) {
                this.lastReturned = nextToReturn;
                this.nextToReturn = null;
                return nextToReturn;
            }
            final TableEntry<V> next = this.findNext();
            if (next != null) {
                return this.lastReturned = next;
            }
            throw new NoSuchElementException();
        }
        
        @Override
        public final void remove() {
            final TableEntry<V> lastReturned = this.lastReturned;
            if (lastReturned == null) {
                throw new NoSuchElementException();
            }
            this.lastReturned = null;
            this.map.remove(lastReturned.key);
        }
        
        @Override
        public abstract T next();
        
        @Override
        public abstract void forEachRemaining(final Consumer<? super T> p0);
    }
    
    protected static class NodeIterator<V>
    {
        protected TableEntry<V>[] currentTable;
        protected ResizeChain<V> resizeChain;
        protected TableEntry<V> last;
        protected int nextBin;
        protected int increment;
        
        protected NodeIterator(final TableEntry<V>[] currentTable) {
            this.currentTable = currentTable;
            this.increment = 1;
        }
        
        private TableEntry<V>[] pullResizeChain(final int n) {
            final ResizeChain<V> resizeChain = this.resizeChain;
            if (resizeChain == null) {
                return this.currentTable = null;
            }
            final ResizeChain<V> prev = resizeChain.prev;
            if ((this.resizeChain = prev) == null) {
                return this.currentTable = null;
            }
            final TableEntry<V>[] table = prev.table;
            final int n2 = n & table.length - 1;
            final ResizeChain<V> prev2 = prev.prev;
            int length;
            if (prev2 == null) {
                length = 1;
            }
            else {
                length = prev2.table.length;
            }
            final int nextBin = n2 + length;
            this.increment = length;
            this.nextBin = nextBin;
            return this.currentTable = table;
        }
        
        private TableEntry<V>[] pushResizeChain(final TableEntry<V>[] array, final TableEntry<V> tableEntry) {
            final ResizeChain<V> resizeChain = this.resizeChain;
            if (resizeChain == null) {
                final TableEntry[] currentTable = (Object)tableEntry.getValuePlain();
                final ResizeChain resizeChain2 = new ResizeChain((TableEntry<V>[])array, null, null);
                final ResizeChain resizeChain3 = new ResizeChain(currentTable, resizeChain2, null);
                resizeChain2.next = resizeChain3;
                this.increment = array.length;
                this.resizeChain = (ResizeChain<V>)resizeChain3;
                return this.currentTable = currentTable;
            }
            final ResizeChain<Object> next = (ResizeChain<Object>)resizeChain.next;
            if (next == null) {
                final TableEntry[] currentTable2 = (Object)tableEntry.getValuePlain();
                final ResizeChain resizeChain4 = new ResizeChain(currentTable2, resizeChain, null);
                resizeChain.next = (ResizeChain<V>)resizeChain4;
                this.increment = array.length;
                this.resizeChain = (ResizeChain<V>)resizeChain4;
                return this.currentTable = currentTable2;
            }
            this.increment = array.length;
            this.resizeChain = (ResizeChain<V>)next;
            return this.currentTable = (TableEntry<V>[])next.table;
        }
        
        protected final TableEntry<V> findNext() {
            while (true) {
                final TableEntry<V> last = this.last;
                if (last != null) {
                    final TableEntry<V> nextVolatile = last.getNextVolatile();
                    if (nextVolatile != null) {
                        this.last = nextVolatile;
                        if (nextVolatile.getValuePlain() == null) {
                            continue;
                        }
                        return nextVolatile;
                    }
                }
                TableEntry<V>[] array = this.currentTable;
                if (array == null) {
                    return null;
                }
                int n = this.nextBin;
                int n2 = this.increment;
                while (true) {
                    if (n >= array.length) {
                        array = this.pullResizeChain(n);
                        n = this.nextBin;
                        n2 = this.increment;
                        if (array != null) {
                            continue;
                        }
                        return this.last = null;
                    }
                    else {
                        final TableEntry<V> atIndexVolatile = ConcurrentLong2ReferenceChainedHashTable.getAtIndexVolatile(array, n);
                        if (atIndexVolatile == null) {
                            n += n2;
                        }
                        else if (atIndexVolatile.resize) {
                            array = this.pushResizeChain(array, atIndexVolatile);
                            n2 = this.increment;
                        }
                        else {
                            this.last = atIndexVolatile;
                            this.nextBin = n + n2;
                            if (atIndexVolatile.getValuePlain() != null) {
                                return atIndexVolatile;
                            }
                            break;
                        }
                    }
                }
            }
        }
        
        protected static final class ResizeChain<V>
        {
            public final TableEntry<V>[] table;
            public final ResizeChain<V> prev;
            public ResizeChain<V> next;
            
            public ResizeChain(final TableEntry<V>[] table, final ResizeChain<V> prev, final ResizeChain<V> next) {
                this.table = table;
                this.prev = prev;
                this.next = next;
            }
        }
    }
    
    public static final class TableEntry<V>
    {
        private static final VarHandle TABLE_ENTRY_ARRAY_HANDLE;
        private final boolean resize;
        private final long key;
        private volatile V value;
        private static final VarHandle VALUE_HANDLE;
        private volatile TableEntry<V> next;
        private static final VarHandle NEXT_HANDLE;
        
        private V getValuePlain() {
            return (V)TableEntry.VALUE_HANDLE.get(this);
        }
        
        private V getValueAcquire() {
            return (V)TableEntry.VALUE_HANDLE.getAcquire(this);
        }
        
        private V getValueVolatile() {
            return (V)TableEntry.VALUE_HANDLE.getVolatile(this);
        }
        
        private void setValuePlain(final V v) {
            TableEntry.VALUE_HANDLE.set(this, (Object)v);
        }
        
        private void setValueRelease(final V v) {
            TableEntry.VALUE_HANDLE.setRelease(this, (Object)v);
        }
        
        private void setValueVolatile(final V v) {
            TableEntry.VALUE_HANDLE.setVolatile(this, (Object)v);
        }
        
        private TableEntry<V> getNextPlain() {
            return TableEntry.NEXT_HANDLE.get(this);
        }
        
        private TableEntry<V> getNextVolatile() {
            return TableEntry.NEXT_HANDLE.getVolatile(this);
        }
        
        private void setNextPlain(final TableEntry<V> tableEntry) {
            TableEntry.NEXT_HANDLE.set(this, (TableEntry)tableEntry);
        }
        
        private void setNextRelease(final TableEntry<V> tableEntry) {
            TableEntry.NEXT_HANDLE.setRelease(this, (TableEntry)tableEntry);
        }
        
        private void setNextVolatile(final TableEntry<V> tableEntry) {
            TableEntry.NEXT_HANDLE.setVolatile(this, (TableEntry)tableEntry);
        }
        
        public TableEntry(final long key, final V valuePlain) {
            this.resize = false;
            this.key = key;
            this.setValuePlain(valuePlain);
        }
        
        public TableEntry(final long key, final V valuePlain, final boolean resize) {
            this.resize = resize;
            this.key = key;
            this.setValuePlain(valuePlain);
        }
        
        public long getKey() {
            return this.key;
        }
        
        public V getValue() {
            return this.getValueVolatile();
        }
        
        static {
            TABLE_ENTRY_ARRAY_HANDLE = ConcurrentUtil.getArrayHandle(TableEntry[].class);
            VALUE_HANDLE = ConcurrentUtil.getVarHandle(TableEntry.class, "value", Object.class);
            NEXT_HANDLE = ConcurrentUtil.getVarHandle(TableEntry.class, "next", TableEntry.class);
        }
    }
}
