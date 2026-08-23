// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.concurrentutil;

import java.util.concurrent.atomic.AtomicLong;

public final class WeakSeqLockJ8 implements SeqLock
{
    protected final AtomicLong lock;
    
    public WeakSeqLockJ8() {
        this.lock = new AtomicLong();
    }
    
    @Override
    public void acquireWrite() {
        this.lock.lazySet(this.lock.get() + 1L);
    }
    
    @Override
    public boolean canRead(final long n) {
        return (n & 0x1L) == 0x0L;
    }
    
    @Override
    public boolean tryAcquireWrite() {
        this.acquireWrite();
        return true;
    }
    
    @Override
    public void releaseWrite() {
        this.lock.lazySet(this.lock.get() + 1L);
    }
    
    @Override
    public void abortWrite() {
        this.lock.lazySet(this.lock.get() ^ 0x1L);
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
