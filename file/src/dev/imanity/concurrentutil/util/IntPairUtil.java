// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.concurrentutil.util;

public final class IntPairUtil
{
    public static long key(final int n, final int n2) {
        return (long)n2 << 32 | ((long)n & 0xFFFFFFFFL);
    }
    
    public static int left(final long n) {
        return (int)n;
    }
    
    public static int right(final long n) {
        return (int)(n >>> 32);
    }
    
    public static String toString(final long n) {
        return "{left:" + left(n) + ", right:" + right(n);
    }
    
    public static String toString(final long[] array, final int n, final int n2) {
        final StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = n; i < n2; ++i) {
            if (i != n) {
                sb.append(", ");
            }
            sb.append(toString(array[i]));
        }
        sb.append("]");
        return sb.toString();
    }
    
    private IntPairUtil() {
    }
}
