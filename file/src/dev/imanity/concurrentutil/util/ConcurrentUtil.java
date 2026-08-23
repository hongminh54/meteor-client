// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.concurrentutil.util;

import java.util.concurrent.locks.LockSupport;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;

public final class ConcurrentUtil
{
    public static String genericToString(final Object o) {
        return (o == null) ? "null" : (o.getClass().getName() + ":" + o.hashCode() + ":" + o.toString());
    }
    
    public static void rethrow(final Throwable t) {
        rethrow0(t);
    }
    
    private static <T extends Throwable> void rethrow0(final Throwable t) throws T {
        throw t;
    }
    
    public static VarHandle getVarHandle(final Class<?> clazz, final String name, final Class<?> type) {
        try {
            return MethodHandles.privateLookupIn(clazz, MethodHandles.lookup()).findVarHandle(clazz, name, type);
        }
        catch (final Exception cause) {
            throw new RuntimeException(cause);
        }
    }
    
    public static VarHandle getStaticVarHandle(final Class<?> clazz, final String name, final Class<?> type) {
        try {
            return MethodHandles.privateLookupIn(clazz, MethodHandles.lookup()).findStaticVarHandle(clazz, name, type);
        }
        catch (final Exception cause) {
            throw new RuntimeException(cause);
        }
    }
    
    public static void backoff() {
        Thread.onSpinWait();
    }
    
    public static long exponentiallyBackoffSimple(final long n) {
        for (long n2 = 0L; n2 < n; ++n2) {
            backoff();
        }
        return n + 1L;
    }
    
    public static long exponentiallyBackoffComplex(final long n) {
        if (n < 100L) {
            return exponentiallyBackoffSimple(n);
        }
        if (n < 1200L) {
            Thread.yield();
            LockSupport.parkNanos(1000L);
            return n + 1L;
        }
        Thread.yield();
        LockSupport.parkNanos(100000L * n);
        return n + 1L;
    }
    
    public static long linearLongBackoff(long min, final long n, long max) {
        min = Math.min(Long.MAX_VALUE, min + 1L);
        max = Math.max(0L, max);
        if (n <= 0L) {
            return min;
        }
        long nanos = n * min;
        if (nanos > max || nanos / n != min) {
            nanos = max;
        }
        final boolean interrupted = Thread.interrupted();
        if (nanos > 1000000L) {
            Thread.yield();
        }
        LockSupport.parkNanos(nanos);
        if (interrupted) {
            Thread.currentThread().interrupt();
        }
        return min;
    }
    
    public static long linearLongBackoffDeadline(long min, final long n, long max, final long n2) {
        min = Math.min(Long.MAX_VALUE, min + 1L);
        max = Math.max(0L, max);
        if (n <= 0L) {
            return min;
        }
        long nanos = n * min;
        if (nanos / n != min) {
            --min;
            nanos = max;
        }
        else if (nanos > max) {
            nanos = max;
        }
        final long n3 = n2 - System.nanoTime();
        if (n3 <= 0L) {
            return min;
        }
        if (n3 <= 1500000L) {
            nanos = 100000L;
        }
        else if (nanos > 1000000L) {
            Thread.yield();
        }
        final boolean interrupted = Thread.interrupted();
        LockSupport.parkNanos(nanos);
        if (interrupted) {
            Thread.currentThread().interrupt();
        }
        return min;
    }
    
    public static VarHandle getArrayHandle(final Class<?> arrayClass) {
        return MethodHandles.arrayElementVarHandle(arrayClass);
    }
}
