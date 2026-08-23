// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.concurrentutil.util;

public final class IntegerUtil
{
    public static final int HIGH_BIT_U32 = Integer.MIN_VALUE;
    public static final long HIGH_BIT_U64 = Long.MIN_VALUE;
    
    public static int ceilLog2(final int n) {
        return 32 - Integer.numberOfLeadingZeros(n - 1);
    }
    
    public static long ceilLog2(final long n) {
        return 64 - Long.numberOfLeadingZeros(n - 1L);
    }
    
    public static int floorLog2(final int i) {
        return 0x1F ^ Integer.numberOfLeadingZeros(i);
    }
    
    public static int floorLog2(final long i) {
        return 0x3F ^ Long.numberOfLeadingZeros(i);
    }
    
    public static int roundCeilLog2(final int n) {
        return Integer.MIN_VALUE >>> Integer.numberOfLeadingZeros(n - 1) - 1;
    }
    
    public static long roundCeilLog2(final long n) {
        return Long.MIN_VALUE >>> Long.numberOfLeadingZeros(n - 1L) - 1;
    }
    
    public static int roundFloorLog2(final int i) {
        return Integer.MIN_VALUE >>> Integer.numberOfLeadingZeros(i);
    }
    
    public static long roundFloorLog2(final long i) {
        return Long.MIN_VALUE >>> Long.numberOfLeadingZeros(i);
    }
    
    public static boolean isPowerOfTwo(final int n) {
        return getTrailingBit(n) == n;
    }
    
    public static boolean isPowerOfTwo(final long n) {
        return getTrailingBit(n) == n;
    }
    
    public static int getTrailingBit(final int n) {
        return -n & n;
    }
    
    public static long getTrailingBit(final long n) {
        return -n & n;
    }
    
    public static int trailingZeros(final int i) {
        return Integer.numberOfTrailingZeros(i);
    }
    
    public static int trailingZeros(final long i) {
        return Long.numberOfTrailingZeros(i);
    }
    
    public static int getDivisorMultiple(final long n) {
        return (int)(n >>> 32);
    }
    
    public static int getDivisorShift(final long n) {
        return (int)n;
    }
    
    public static long getDivisorNumbers(final int n) {
        final int branchlessAbs = branchlessAbs(n);
        if (branchlessAbs < 2) {
            throw new IllegalArgumentException("|number| must be in [2, 2^31 -1], not: " + n);
        }
        int n2 = 31;
        final int n3 = Integer.MIN_VALUE + (n >>> 31);
        final int n4 = n3 - 1 - (int)(((long)n3 & 0xFFFFFFFFL) % branchlessAbs);
        int n5 = (int)(2147483648L / ((long)n4 & 0xFFFFFFFFL));
        int n6 = Integer.MIN_VALUE - n5 * n4;
        int n7 = (int)(2147483648L / ((long)branchlessAbs & 0xFFFFFFFFL));
        int n8 = Integer.MIN_VALUE - n7 * branchlessAbs;
        int n9;
        do {
            ++n2;
            n5 *= 2;
            n6 *= 2;
            if (((long)n6 & 0xFFFFFFFFL) >= ((long)n4 & 0xFFFFFFFFL)) {
                ++n5;
                n6 -= n4;
            }
            n7 *= 2;
            n8 *= 2;
            if (((long)n8 & 0xFFFFFFFFL) >= ((long)branchlessAbs & 0xFFFFFFFFL)) {
                ++n7;
                n8 -= branchlessAbs;
            }
            n9 = branchlessAbs - n8;
        } while (((long)n5 & 0xFFFFFFFFL) < ((long)n9 & 0xFFFFFFFFL) || (n5 == n9 && n6 == 0));
        int n10 = n7 + 1;
        if (n < 0) {
            n10 = -n10;
        }
        return (long)n10 << 32 | (long)n2;
    }
    
    public static int branchlessAbs(final int n) {
        final int n2 = n >> 31;
        return (n2 ^ n) - n2;
    }
    
    public static long branchlessAbs(final long n) {
        final long n2 = n >> 63;
        return (n2 ^ n) - n2;
    }
    
    public static long getUnsignedDivisorMagic(final long n, final int n2) {
        return ((1L << n2) - 1L) / n + 1L;
    }
    
    private IntegerUtil() {
        throw new RuntimeException();
    }
}
