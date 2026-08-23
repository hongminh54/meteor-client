// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.concurrentutil;

import java.lang.invoke.VarHandle;

public class WeakSeqLockJ17 implements SeqLock
{
    protected long lock;
    protected static final VarHandle LOCK_HANDLE;
    
    protected final long getLockPlain() {
        return WeakSeqLockJ17.LOCK_HANDLE.get(this);
    }
    
    protected final long getLockOpaque() {
        return WeakSeqLockJ17.LOCK_HANDLE.getOpaque(this);
    }
    
    protected final void setLockOpaque(final long n) {
        WeakSeqLockJ17.LOCK_HANDLE.setOpaque(this, n);
    }
    
    public WeakSeqLockJ17() {
        VarHandle.storeStoreFence();
    }
    
    @Override
    public void acquireWrite() {
        this.setLockOpaque(this.getLockPlain() + 1L);
        VarHandle.storeStoreFence();
    }
    
    @Override
    public boolean tryAcquireWrite() {
        this.acquireWrite();
        return true;
    }
    
    @Override
    public void releaseWrite() {
        final long lockPlain = this.getLockPlain();
        VarHandle.storeStoreFence();
        this.setLockOpaque(lockPlain + 1L);
    }
    
    @Override
    public void abortWrite() {
        final long lockPlain = this.getLockPlain();
        VarHandle.storeStoreFence();
        this.setLockOpaque(lockPlain ^ 0x1L);
    }
    
    @Override
    public long acquireRead() {
        int n = 0;
        long n2;
        for (n2 = this.getLockOpaque(); !this.canRead(n2); n2 = this.getLockOpaque()) {
            for (int i = 0; i < n; ++i) {
                Thread.onSpinWait();
            }
            if (++n > 5000) {
                Thread.yield();
            }
        }
        VarHandle.loadLoadFence();
        return n2;
    }
    
    @Override
    public boolean tryReleaseRead(final long n) {
        VarHandle.loadLoadFence();
        return this.getLockOpaque() == n;
    }
    
    @Override
    public long getSequentialCounter() {
        final long lockOpaque = this.getLockOpaque();
        VarHandle.loadLoadFence();
        return lockOpaque;
    }
    
    static {
        LOCK_HANDLE = ConcurrentUtil.getVarHandle(WeakSeqLockJ17.class, "lock", Long.TYPE);
    }
}
