// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.concurrentutil;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;

public final class ConcurrentUtil
{
    public static final int CACHE_LINE_SIZE = 64;
    
    public static void pause() {
        Thread.onSpinWait();
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
    
    public static VarHandle getArrayHandle(final Class<?> arrayClass) {
        return MethodHandles.arrayElementVarHandle(arrayClass);
    }
    
    private ConcurrentUtil() {
        throw new RuntimeException();
    }
}
