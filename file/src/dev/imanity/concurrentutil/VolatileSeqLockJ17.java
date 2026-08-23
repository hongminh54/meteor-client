// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.concurrentutil;

import java.lang.invoke.VarHandle;

public class VolatileSeqLockJ17 implements SeqLock
{
    protected volatile long lock;
    protected static final VarHandle LOCK_HANDLE;
    
    protected final long getLockPlain() {
        return VolatileSeqLockJ17.LOCK_HANDLE.get(this);
    }
    
    protected final long getLockVolatile() {
        return VolatileSeqLockJ17.LOCK_HANDLE.getVolatile(this);
    }
    
    protected final long compareAndExchangeLockVolatile(final long n, final long n2) {
        return VolatileSeqLockJ17.LOCK_HANDLE.compareAndExchange(this, n, n2);
    }
    
    protected final void setLockVolatile(final long n) {
        VolatileSeqLockJ17.LOCK_HANDLE.setVolatile(this, n);
    }
    
    public VolatileSeqLockJ17() {
        VarHandle.storeStoreFence();
    }
    
    @Override
    public void acquireWrite() {
        int n = 0;
        long n2 = this.getLockVolatile();
        while (true) {
            for (int i = 0; i < n; ++i) {
                Thread.onSpinWait();
            }
            if (!this.canRead(n2)) {
                if (++n > 5000) {
                    Thread.yield();
                }
                n2 = this.getLockVolatile();
            }
            else {
                if (n2 == (n2 = this.compareAndExchangeLockVolatile(n2, n2 | 0x1L))) {
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
        final long lockVolatile = this.getLockVolatile();
        return this.canRead(lockVolatile) && lockVolatile == this.compareAndExchangeLockVolatile(lockVolatile, lockVolatile + 1L);
    }
    
    @Override
    public void releaseWrite() {
        this.setLockVolatile(this.getLockPlain() + 1L);
    }
    
    @Override
    public void abortWrite() {
        this.setLockVolatile(this.getLockPlain() ^ 0x1L);
    }
    
    @Override
    public long acquireRead() {
        int n = 0;
        long n2;
        for (n2 = this.getLockVolatile(); !this.canRead(n2); n2 = this.getLockVolatile()) {
            for (int i = 0; i < n; ++i) {
                Thread.onSpinWait();
            }
            if (++n > 5000) {
                Thread.yield();
            }
        }
        return n2;
    }
    
    @Override
    public boolean tryReleaseRead(final long n) {
        return this.getLockVolatile() == n;
    }
    
    @Override
    public long getSequentialCounter() {
        return this.getLockVolatile();
    }
    
    static {
        LOCK_HANDLE = ConcurrentUtil.getVarHandle(VolatileSeqLockJ17.class, "lock", Long.TYPE);
    }
}
