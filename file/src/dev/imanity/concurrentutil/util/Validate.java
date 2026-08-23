// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.concurrentutil.util;

public final class Validate
{
    public static <T> T notNull(final T t) {
        if (t == null) {
            throw new NullPointerException();
        }
        return t;
    }
    
    public static <T> T notNull(final T t, final String s) {
        if (t == null) {
            throw new NullPointerException(s);
        }
        return t;
    }
    
    public static void arrayBounds(final int n, final int n2, final int n3, final String s) {
        if (n < 0 || n2 < 0 || n3 - n < n2) {
            throw new ArrayIndexOutOfBoundsException(s + ": off: " + n + ", len: " + n2 + ", array length: " + n3);
        }
    }
    
    private Validate() {
        throw new RuntimeException();
    }
}
