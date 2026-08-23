// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.objenesis.instantiator.util;

import java.lang.reflect.Field;
import dev.imanity.antixray.libs.objenesis.ObjenesisException;
import sun.misc.Unsafe;

public final class UnsafeUtils
{
    private static final Unsafe unsafe;
    
    private UnsafeUtils() {
    }
    
    public static Unsafe getUnsafe() {
        return UnsafeUtils.unsafe;
    }
    
    static {
        Field declaredField;
        try {
            declaredField = Unsafe.class.getDeclaredField("theUnsafe");
        }
        catch (final NoSuchFieldException ex) {
            throw new ObjenesisException(ex);
        }
        declaredField.setAccessible(true);
        try {
            unsafe = (Unsafe)declaredField.get(null);
        }
        catch (final IllegalAccessException ex2) {
            throw new ObjenesisException(ex2);
        }
    }
}
