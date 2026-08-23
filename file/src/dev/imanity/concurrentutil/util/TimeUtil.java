// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.concurrentutil.util;

public final class TimeUtil
{
    public static int compareTimes(final long n, final long n2) {
        final long n3 = n - n2;
        return (int)(n3 >> 63 | -n3 >>> 63);
    }
    
    public static long getGreatestTime(final long n, final long n2) {
        return (n - n2 < 0L) ? n2 : n;
    }
    
    public static long getLeastTime(final long n, final long n2) {
        return (n - n2 > 0L) ? n2 : n;
    }
    
    public static long clampTime(final long n, final long n2, final long n3) {
        final long n4 = n - n3;
        final long n5 = n - n2;
        if (n4 > 0L) {
            return n3;
        }
        if (n5 < 0L) {
            return n2;
        }
        return n;
    }
    
    private TimeUtil() {
    }
}
