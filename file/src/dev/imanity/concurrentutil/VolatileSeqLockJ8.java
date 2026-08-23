// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.concurrentutil;

import java.util.concurrent.atomic.AtomicLong;

public class VolatileSeqLockJ8 implements SeqLock
{
    private final AtomicLong lock;
    
    public VolatileSeqLockJ8() {
        this.lock = new AtomicLong();
    }
    
    @Override
    public void acquireWrite() {
        int n = 0;
        long expectedValue = this.lock.get();
        while (true) {
            if (!this.canRead(expectedValue)) {
                if (++n > 5000) {
                    Thread.yield();
                }
                expectedValue = this.lock.get();
            }
            else {
                if (this.lock.compareAndSet(expectedValue, expectedValue | 0x1L)) {
                    break;
                }
                if (++n <= 5000) {
                    continue;
                }
                Thread.yield();
            }
        }
    }
    
    @Override
    public boolean tryAcquireWrite() {
        final long value = this.lock.get();
        return this.canRead(value) && this.lock.compareAndSet(value, value + 1L);
    }
    
    @Override
    public void releaseWrite() {
        this.lock.set(this.lock.get() + 1L);
    }
    
    @Override
    public void abortWrite() {
        this.lock.set(this.lock.get() ^ 0x1L);
    }
    
    @Override
    public long acquireRead() {
        int n = 0;
        long n2;
        for (n2 = this.lock.get(); !this.canRead(n2); n2 = this.lock.get()) {
            if (++n > 5000) {
                Thread.yield();
            }
        }
        return n2;
    }
    
    @Override
    public boolean tryReleaseRead(final long n) {
        return this.lock.get() == n;
    }
    
    @Override
    public long getSequentialCounter() {
        return this.lock.get();
    }
}
