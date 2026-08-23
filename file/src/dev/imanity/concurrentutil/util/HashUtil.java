// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.concurrentutil.util;

public final class HashUtil
{
    private static final int INT_PHI = -1640531527;
    private static final int INV_INT_PHI = 340573321;
    private static final long LONG_PHI = -7046029254386353131L;
    private static final long INV_LONG_PHI = -1018231460777725123L;
    
    public static int murmurHash3(int n) {
        n ^= n >>> 16;
        n *= -2048144789;
        n ^= n >>> 13;
        n *= -1028477387;
        n ^= n >>> 16;
        return n;
    }
    
    public static long murmurHash3(long n) {
        n ^= n >>> 33;
        n *= -49064778989728563L;
        n ^= n >>> 33;
        n *= -4265267296055464877L;
        n ^= n >>> 33;
        return n;
    }
    
    public static int mix(final int n) {
        final int n2 = n * -1640531527;
        return n2 ^ n2 >>> 16;
    }
    
    public static int invMix(final int n) {
        return (n ^ n >>> 16) * 340573321;
    }
    
    public static long mix(final long n) {
        final long n2 = n * -7046029254386353131L;
        final long n3 = n2 ^ n2 >>> 32;
        return n3 ^ n3 >>> 16;
    }
    
    public static long invMix(long n) {
        n ^= n >>> 32;
        n ^= n >>> 16;
        return (n ^ n >>> 32) * -1018231460777725123L;
    }
    
    private HashUtil() {
    }
}
