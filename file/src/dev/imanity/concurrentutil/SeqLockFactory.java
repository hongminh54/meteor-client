// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.concurrentutil;

public class SeqLockFactory
{
    private SeqLockFactory() {
    }
    
    public static SeqLock createWeakSeqLock() {
        final String property = System.getProperty("java.specification.version");
        try {
            if (Integer.parseInt(property) >= 17) {
                System.out.println("Using WeakSeqLockJ17");
                return new WeakSeqLockJ17();
            }
        }
        catch (final NumberFormatException ex) {}
        System.out.println("Using WeakSeqLockJ8");
        return new WeakSeqLockJ8();
    }
    
    public static SeqLock createVoliatileSeqLock() {
        final String property = System.getProperty("java.specification.version");
        try {
            if (Integer.parseInt(property) >= 17) {
                System.out.println("Using VolatileSeqLockJ17");
                return new VolatileSeqLockJ17();
            }
        }
        catch (final NumberFormatException ex) {}
        System.out.println("Using VolatileSeqLockJ8");
        return new VolatileSeqLockJ8();
    }
}
