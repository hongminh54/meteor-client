// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.mountiplex.reflection.util;

import java.util.concurrent.atomic.AtomicInteger;

public class UniqueHash
{
    private final AtomicInteger value;
    
    public UniqueHash() {
        this.value = new AtomicInteger(0);
    }
    
    public int next() {
        return hash(this.value.incrementAndGet());
    }
    
    public String nextHex() {
        return Integer.toHexString(this.next());
    }
    
    public static int hash(final int n) {
        final int n2 = n ^ 0x5BF03635;
        if (n2 >= Integer.MAX_VALUE) {
            return n2;
        }
        final int n3 = (int)(n2 * (long)n2 % 2147483647L);
        return (n2 <= 1073741823) ? n3 : (Integer.MAX_VALUE - n3);
    }
}
