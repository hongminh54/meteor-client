// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.concurrentutil;

public interface SeqLock
{
    void acquireWrite();
    
    boolean tryAcquireWrite();
    
    void releaseWrite();
    
    void abortWrite();
    
    long acquireRead();
    
    boolean tryReleaseRead(final long p0);
    
    long getSequentialCounter();
    
    default boolean canRead(final long n) {
        return (n & 0x1L) == 0x0L;
    }
}
