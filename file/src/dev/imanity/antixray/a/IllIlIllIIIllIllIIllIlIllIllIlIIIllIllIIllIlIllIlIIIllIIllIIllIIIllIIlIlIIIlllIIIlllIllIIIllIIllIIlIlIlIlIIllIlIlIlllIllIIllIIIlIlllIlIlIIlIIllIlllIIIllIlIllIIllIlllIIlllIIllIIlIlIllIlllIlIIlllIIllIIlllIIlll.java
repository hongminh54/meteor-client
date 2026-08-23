// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.a;

import java.util.Iterator;
import it.unimi.dsi.fastutil.shorts.Short2ByteMap;
import kotlin.jvm.internal.Intrinsics;
import kotlin.Unit;
import kotlin.jvm.functions.Function4;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import org.jetbrains.annotations.NotNull;
import kotlin.jvm.internal.SourceDebugExtension;

@SourceDebugExtension({ "SMAP\nFakeChunk.kt\nKotlin\n*S Kotlin\n*F\n+ 1 FakeChunk.kt\ndev/imanity/antixray/core/strategy/fake/FakeChunk\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,94:1\n1855#2,2:95\n*S KotlinDebug\n*F\n+ 1 FakeChunk.kt\ndev/imanity/antixray/core/strategy/fake/FakeChunk\n*L\n31#1:95,2\n*E\n" })
public final class IllIlIllIIIllIllIIllIlIllIllIlIIIllIllIIllIlIllIlIIIllIIllIIllIIIllIIlIlIIIlllIIIlllIllIIIllIIllIIlIlIlIlIIllIlIlIlllIllIIllIIIlIlllIlIlIIlIIllIlllIIIllIlIllIIllIlllIIlllIIllIIlIlIllIlllIlIIlllIIllIIlllIIlll
{
    private final int bE;
    private final int bF;
    private final int bG;
    private final int bH;
    private boolean bI;
    @NotNull
    private final IlIllIlllIIlIIlllIllIllIlIlllIIllIIlIIlIIIlIIlIIIllIIllIIlllIIlIIlllIllIIIllIlllIIllIIlIIllIIlllIlllIIllIIIllIlllIIlIIllIIllIIllIIIlIllIIIlIlIIllIIllIIlllIIlIllIIllIIlllIllIlIlIlllIIIllIlIIlIIllIIIlllIIIllIIllIllIIlIIIllIIl[] bJ;
    @NotNull
    private final ReentrantReadWriteLock bK;
    
    public IllIlIllIIIllIllIIllIlIllIllIlIIIllIllIIllIlIllIlIIIllIIllIIllIIIllIIlIlIIIlllIIIlllIllIIIllIIllIIlIlIlIlIIllIlIlIlllIllIIllIIIlIlllIlIlIIlIIllIlllIIIllIlIllIIllIlllIIlllIIllIIlIlIllIlllIlIIlllIIllIIlllIIlll(final int be, final int bf, final int bg, final int bh) {
        this.bE = be;
        this.bF = bf;
        this.bG = bg;
        this.bH = bh;
        int i = 0;
        final int n = this.bF - this.bE;
        final IlIllIlllIIlIIlllIllIllIlIlllIIllIIlIIlIIIlIIlIIIllIIllIIlllIIlIIlllIllIIIllIlllIIllIIlIIllIIlllIlllIIllIIIllIlllIIlIIllIIllIIllIIIlIllIIIlIlIIllIIllIIlllIIlIllIIllIIlllIllIlIlIlllIIIllIlIIlIIllIIIlllIIIllIIllIllIIlIIIllIIl[] bj = new IlIllIlllIIlIIlllIllIllIlIlllIIllIIlIIlIIIlIIlIIIllIIllIIlllIIlIIlllIllIIIllIlllIIllIIlIIllIIlllIlllIIllIIIllIlllIIlIIllIIllIIllIIIlIllIIIlIlIIllIIllIIlllIIlIllIIllIIlllIllIlIlIlllIIIllIlIIlIIllIIIlllIIIllIIllIllIIlIIIllIIl[n];
        while (i < n) {
            bj[i] = null;
            ++i;
        }
        this.bJ = bj;
        this.bK = new ReentrantReadWriteLock();
    }
    
    public final int IIllIIIllIIlIIllIIlllIlllIIIllIIIllIIllIIIlIllIllIlIIllIIIlIIIlIIIlIlIIlIllIIIlIllIIllIIlIIlIllIlIllIIlIlllIIllIlllIIIllIIllIIllIIllIIllIIllIIllIIIlIllIlIIIllIIIllIIIllIllIlllIllIlIIIllIIIlIIlIIIlIlIllIIIlllIIlIIllIlllIIll() {
        return this.bE;
    }
    
    public final int IllIllIlIIllIlIlllIIlllIIIlIllIlIlIIlIIlllIIllIIlIIllIIlllIIIlIIIlIIllIIllIllIlIllIIllIIllIIlIIIlIllIIlIIlIlllIIIlIIllIIIllIlllIllIIIlIIlllIIlllIIlIIllIIlllIllIIlIllIlllIIlIIllIllIIIlllIIIllIIIlIlIIlIIllIIlIIIllIlIIll() {
        return this.bF;
    }
    
    public final int IIlIIllIIIlllIIlllIIIlIllIIIllIIllIIlllIIllIIlIIllIIllIllIIIllIIllIlIlIIllIIIlllIIIllIlIllIIIlllIIlIIlllIIlIlIllIlIIIllIIlllIllIIlllIIllIllIIllIlIIllIIIllIIlllIIlllIlIIIllIIIlIIllIIlIllIllIIIlIIIlIlIlIIlIIIlIllIIlllIIIlllIIlllIIIl() {
        return this.bG;
    }
    
    public final int IlIIlIIIlIlIlllIIlIIlIllIllIIllIlllIlIlIIllIllIIlllIIIlllIIIllIIlllIIIllIIllIlIIIlIIllIIlllIIlllIIllIIIlllIIlllIlIIIlIIlllIIlllIIIllIIlllIIlllIllIIllIIlIIIlIIlIIIllIIlIIIlllIIlllIIIllIIllIllIIIlIIIllIllIIlIIIlllIIIllIIllIIIllIIlIlllIlllIl() {
        return this.bH;
    }
    
    public final boolean IlIIlllIIlllIIllIIIlllIllIIlllIIlllIIlIIIllIllIIllIlIllIlIIlIlIlIIlIIlllIlIlllIIlllIIllIllIIIlIIIlIIllIIllIllIIIlllIIlllIIlIllIllIllIllIIIlllIllIIlIIllIIlllIllIlIIlIIllIllIIlIIllIlIllIlIlIIlllIIIllIlllIIlIllIIIlllIIll() {
        return this.bI;
    }
    
    public final void IIlIlllIIllIllIlllIIlIlIllIIlIIllIIllIIIllIlIIlllIIIllIIllIIlllIlllIIllIlllIllIIIlIIllIIllIIlllIIIllIIIlIIllIllIIlIIlIIlllIlIIlIllIlllIIlllIIIlIIIllIIllIIlIIIllIlllIIlIlIlllIIlIIllIIlIllIIlIIlllIIIlllIIlllIIlllIllIIlIllIIIlIIll(final boolean bi) {
        this.bI = bi;
    }
    
    private final IlIllIlllIIlIIlllIllIllIlIlllIIllIIlIIlIIIlIIlIIIllIIllIIlllIIlIIlllIllIIIllIlllIIllIIlIIllIIlllIlllIIllIIIllIlllIIlIIllIIllIIllIIIlIllIIIlIlIIllIIllIIlllIIlIllIIllIIlllIllIlIlIlllIIIllIlIIlIIllIIIlllIIIllIIllIllIIlIIIllIIl IllIIllIlIlIllIIllIIIllIIIllIIIllIIIlIlIlIllIlIIllIIlllIlllIIlIIIllIIlIllIIlllIIllIllIIIlIIlIlIlllIIIlllIIlIIIllIIIllIIIllIlIIlllIIlIlIIIlllIIlIlIIIlllIIlllIIlIllIllIIlIIIllIIllIlllIIllIIllIllIIllIIllIIlIIllIllIIllIIlIIl(final int n) {
        IlIllIlllIIlIIlllIllIllIlIlllIIllIIlIIlIIIlIIlIIIllIIllIIlllIIlIIlllIllIIIllIlllIIllIIlIIllIIlllIlllIIllIIIllIlllIIlIIllIIllIIllIIIlIllIIIlIlIIllIIllIIlllIIlIllIIllIIlllIllIlIlIlllIIIllIlIIlIIllIIIlllIIIllIIllIllIIlIIIllIIl ilIllIlllIIlIIlllIllIllIlIlllIIllIIlIIlIIIlIIlIIIllIIllIIlllIIlIIlllIllIIIllIlllIIllIIlIIllIIlllIlllIIllIIIllIlllIIlIIllIIllIIllIIIlIllIIIlIlIIllIIllIIlllIIlIllIIllIIlllIllIlIlIlllIIIllIlIIlIIllIIIlllIIIllIIllIllIIlIIIllIIl;
        if ((ilIllIlllIIlIIlllIllIllIlIlllIIllIIlIIlIIIlIIlIIIllIIllIIlllIIlIIlllIllIIIllIlllIIllIIlIIllIIlllIlllIIllIIIllIlllIIlIIllIIllIIllIIIlIllIIIlIlIIllIIllIIlllIIlIllIIllIIlllIllIlIlIlllIIIllIlIIlIIllIIIlllIIIllIIllIllIIlIIIllIIl = this.bJ[n - this.bE]) == null) {
            final IlIllIlllIIlIIlllIllIllIlIlllIIllIIlIIlIIIlIIlIIIllIIllIIlllIIlIIlllIllIIIllIlllIIllIIlIIllIIlllIlllIIllIIIllIlllIIlIIllIIllIIllIIIlIllIIIlIlIIllIIllIIlllIIlIllIIllIIlllIllIlIlIlllIIIllIlIIlIIllIIIlllIIIllIIllIllIIlIIIllIIl ilIllIlllIIlIIlllIllIllIlIlllIIllIIlIIlIIIlIIlIIIllIIllIIlllIIlIIlllIllIIIllIlllIIllIIlIIllIIlllIlllIIllIIIllIlllIIlIIllIIllIIllIIIlIllIIIlIlIIllIIllIIlllIIlIllIIllIIlllIllIlIlIlllIIIllIlIIlIIllIIIlllIIIllIIllIllIIlIIIllIIl2 = new IlIllIlllIIlIIlllIllIllIlIlllIIllIIlIIlIIIlIIlIIIllIIllIIlllIIlIIlllIllIIIllIlllIIllIIlIIllIIlllIlllIIllIIIllIlllIIlIIllIIllIIllIIIlIllIIIlIlIIllIIllIIlllIIlIllIIllIIlllIllIlIlIlllIIIllIlIIlIIllIIIlllIIIllIIllIllIIlIIIllIIl();
            this.bJ[n - this.bE] = ilIllIlllIIlIIlllIllIllIlIlllIIllIIlIIlIIIlIIlIIIllIIllIIlllIIlIIlllIllIIIllIlllIIllIIlIIllIIlllIlllIIllIIIllIlllIIlIIllIIllIIllIIIlIllIIIlIlIIllIIllIIlllIIlIllIIllIIlllIllIlIlIlllIIIllIlIIlIIllIIIlllIIIllIIllIllIIlIIIllIIl2;
            ilIllIlllIIlIIlllIllIllIlIlllIIllIIlIIlIIIlIIlIIIllIIllIIlllIIlIIlllIllIIIllIlllIIllIIlIIllIIlllIlllIIllIIIllIlllIIlIIllIIllIIllIIIlIllIIIlIlIIllIIllIIlllIIlIllIIllIIlllIllIlIlIlllIIIllIlIIlIIllIIIlllIIIllIIllIllIIlIIIllIIl = ilIllIlllIIlIIlllIllIllIlIlllIIllIIlIIlIIIlIIlIIIllIIllIIlllIIlIIlllIllIIIllIlllIIllIIlIIllIIlllIlllIIllIIIllIlllIIlIIllIIllIIllIIIlIllIIIlIlIIllIIllIIlllIIlIllIIllIIlllIllIlIlIlllIIIllIlIIlIIllIIIlllIIIllIIllIllIIlIIIllIIl2;
        }
        return ilIllIlllIIlIIlllIllIllIlIlllIIllIIlIIlIIIlIIlIIIllIIllIIlllIIlIIlllIllIIIllIlllIIllIIlIIllIIlllIlllIIllIIIllIlllIIlIIllIIllIIllIIIlIllIIIlIlIIllIIllIIlllIIlIllIIllIIlllIllIlIlIlllIIIllIlIIlIIllIIIlllIIIllIIllIllIIlIIIllIIl;
    }
    
    public final void IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(@NotNull final Function4<? super Integer, ? super Integer, ? super Integer, ? super Byte, Unit> function4) {
        Intrinsics.checkNotNullParameter((Object)function4, "");
        int n = this.bE * 16;
        final IlIllIlllIIlIIlllIllIllIlIlllIIllIIlIIlIIIlIIlIIIllIIllIIlllIIlIIlllIllIIIllIlllIIllIIlIIllIIlllIlllIIllIIIllIlllIIlIIllIIllIIllIIIlIllIIIlIlIIllIIllIIlllIIlIllIIllIIlllIllIlIlIlllIIIllIlIIlIIllIIIlllIIIllIIllIllIIlIIIllIIl[] bj = this.bJ;
        for (int i = 0; i < bj.length; ++i) {
            final IlIllIlllIIlIIlllIllIllIlIlllIIllIIlIIlIIIlIIlIIIllIIllIIlllIIlIIlllIllIIIllIlllIIllIIlIIllIIlllIlllIIllIIIllIlllIIlIIllIIllIIllIIIlIllIIIlIlIIllIIllIIlllIIlIllIIllIIlllIllIlIlIlllIIIllIlIIlIIllIIIlllIIIllIIllIllIIlIIIllIIl ilIllIlllIIlIIlllIllIllIlIlllIIllIIlIIlIIIlIIlIIIllIIllIIlllIIlIIlllIllIIIllIlllIIllIIlIIllIIlllIlllIIllIIIllIlllIIlIIllIIllIIllIIIlIllIIIlIlIIllIIllIIlllIIlIllIIllIIlllIllIlIlIlllIIIllIlIIlIIllIIIlllIIIllIIllIllIIlIIIllIIl = bj[i];
            if (ilIllIlllIIlIIlllIllIllIlIlllIIllIIlIIlIIIlIIlIIIllIIllIIlllIIlIIlllIllIIIllIlllIIllIIlIIllIIlllIlllIIllIIIllIlllIIlIIllIIllIIllIIIlIllIIIlIlIIllIIllIIlllIIlIllIIllIIlllIllIlIlIlllIIIllIlIIlIIllIIIlllIIIllIIllIllIIlIIIllIIl == null) {
                n += 16;
            }
            else {
                final ReentrantReadWriteLock.ReadLock lock = this.bK.readLock();
                lock.lock();
                try {
                    for (final Short2ByteMap.Entry entry : (Iterable)ilIllIlllIIlIIlllIllIllIlIlllIIllIIlIIlIIIlIIlIIIllIIllIIlllIIlIIlllIllIIIllIlllIIllIIlIIllIIlllIlllIIllIIIllIlllIIlIIllIIllIIllIIIlIllIIIlIlIIllIIllIIlllIIlIllIIllIIlllIllIlIlIlllIIIllIlIIlIIllIIIlllIIIllIIllIllIIlIIIllIIl.IIIlllIIlllIIIllIIlIIlllIIllIIllIIIllIIllIlIIllIIlllIIllIlllIlllIIIllIIllIIllIIlllIllIIIllIllIIIlllIlIlllIIlIIlllIIIlllIIlIIlIllIIllIIlIIllIIIllIIllIIllIlllIIIlIIIlIllIIlIllIIllIllIllIIIllIIIlllIllIIIlllIIIlllIllIllIIlIIIlllIlIIllIIIllIIllIIlll()) {
                        final short shortKey = entry.getShortKey();
                        function4.invoke((Object)(shortKey % 16), (Object)(n + shortKey / 256), (Object)(shortKey / 16 % 16), (Object)entry.getByteValue());
                    }
                    final Unit instance = Unit.INSTANCE;
                }
                finally {
                    lock.unlock();
                }
                n += 16;
            }
        }
    }
    
    public final byte IIllIIIllIllIIIlllIIIlllIIllIIlllIlIIIllIllIlIIlIIIlllIIlIIllIIllIlIllIIllIIIllIIlIllIIlIIlIIllIIlIlIIllIllIIlIlIIlIIIllIIlIllIllIIlllIllIIlIlllIIllIlIlllIIlllIIIlIIlIIllIlllIlIIllIIIllIlIlIIlIIllIllIlIllIIIllIIll(final int n, final int n2, final int n3) {
        final ReentrantReadWriteLock.ReadLock lock = this.bK.readLock();
        lock.lock();
        try {
            if (n2 < this.bE * 16 || n2 >= this.bF * 16) {
                return -1;
            }
            final IlIllIlllIIlIIlllIllIllIlIlllIIllIIlIIlIIIlIIlIIIllIIllIIlllIIlIIlllIllIIIllIlllIIllIIlIIllIIlllIlllIIllIIIllIlllIIlIIllIIllIIllIIIlIllIIIlIlIIllIIllIIlllIIlIllIIllIIlllIllIlIlIlllIIIllIlIIlIIllIIIlllIIIllIIllIllIIlIIIllIIl ilIllIlllIIlIIlllIllIllIlIlllIIllIIlIIlIIIlIIlIIIllIIllIIlllIIlIIlllIllIIIllIlllIIllIIlIIllIIlllIlllIIllIIIllIlllIIlIIllIIllIIllIIIlIllIIIlIlIIllIIllIIlllIIlIllIIllIIlllIllIlIlIlllIIIllIlIIlIIllIIIlllIIIllIIllIllIIlIIIllIIl = this.bJ[(n2 >> 4) - this.bE];
            return (byte)((ilIllIlllIIlIIlllIllIllIlIlllIIllIIlIIlIIIlIIlIIIllIIllIIlllIIlIIlllIllIIIllIlllIIllIIlIIllIIlllIlllIIllIIIllIlllIIlIIllIIllIIllIIIlIllIIIlIlIIllIIllIIlllIIlIllIIllIIlllIllIlIlIlllIIIllIlIIlIIllIIIlllIIIllIIllIllIIlIIIllIIl != null) ? ilIllIlllIIlIIlllIllIllIlIlllIIllIIlIIlIIIlIIlIIIllIIllIIlllIIlIIlllIllIIIllIlllIIllIIlIIllIIlllIlllIIllIIIllIlllIIlIIllIIllIIllIIIlIllIIIlIlIIllIIllIIlllIIlIllIIllIIlllIllIlIlIlllIIIllIlIIlIIllIIIlllIIIllIIllIllIIlIIIllIIl.IIllIllIIllIlIIlIlIIlllIllIIllIIllIlIIllIIIllIIIllIIllIIIlIllIIlIIlllIllIIlIllIllIIlIIlIlIIIlIllIIllIlllIllIIIllIIlIIlllIllIIlIIIlIIllIIllIIlIlIIlIllIIlllIlIIlllIIlIlIIlIIlIlllIIlIIIlIIllIllIlllIIlllIIIlIIllIIIlll(n, n2 & 0xF, n3) : -1);
        }
        finally {
            lock.unlock();
        }
    }
    
    public final void IIllIllIIllIlIIlIlIIlllIllIIllIIllIlIIllIIIllIIIllIIllIIIlIllIIlIIlllIllIIlIllIllIIlIIlIlIIIlIllIIllIlllIllIIIllIIlIIlllIllIIlIIIlIIllIIllIIlIlIIlIllIIlllIlIIlllIIlIlIIlIIlIlllIIlIIIlIIllIllIlllIIlllIIIlIIllIIIlll(final int n, final int n2, final int n3, final byte b) {
        final ReentrantReadWriteLock bk = this.bK;
        final ReentrantReadWriteLock.ReadLock lock = bk.readLock();
        final int n4 = (bk.getWriteHoldCount() == 0) ? bk.getReadHoldCount() : 0;
        for (int i = 0; i < n4; ++i) {
            lock.unlock();
        }
        final ReentrantReadWriteLock.WriteLock writeLock = bk.writeLock();
        writeLock.lock();
        try {
            if (n2 < this.bE * 16 || n2 >= this.bF * 16) {
                return;
            }
            this.IllIIllIlIlIllIIllIIIllIIIllIIIllIIIlIlIlIllIlIIllIIlllIlllIIlIIIllIIlIllIIlllIIllIllIIIlIIlIlIlllIIIlllIIlIIIllIIIllIIIllIlIIlllIIlIlIIIlllIIlIlIIIlllIIlllIIlIllIllIIlIIIllIIllIlllIIllIIllIllIIllIIllIIlIIllIllIIllIIlIIl(n2 >> 4).IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(n, n2 & 0xF, n3, b);
            final Unit instance = Unit.INSTANCE;
        }
        finally {
            for (int j = 0; j < n4; ++j) {
                lock.lock();
            }
            writeLock.unlock();
        }
    }
    
    public final void IlIIIlIIllIlllIlIIIlIIlllIIlIllIIlIIllIIIlllIIlllIIIlIIIlIIllIlllIIllIllIIIlllIlIllIIIlllIIlllIlIIllIIIllIlIIlIlllIllIIlIIllIllIlIlIIlIIIllIIllIIlIllIIllIIlIIlllIllIIIllIlllIIllIlllIlIIlIllIIlllIIIllIlllIIIllIIlIIllIlllIIll(final int n, final int n2, final int n3) {
        this.IIllIllIIllIlIIlIlIIlllIllIIllIIllIlIIllIIIllIIIllIIllIIIlIllIIlIIlllIllIIlIllIllIIlIIlIlIIIlIllIIllIlllIllIIIllIIlIIlllIllIIlIIIlIIllIIllIIlIlIIlIllIIlllIlIIlllIIlIlIIlIIlIlllIIlIIIlIIllIllIlllIIlllIIIlIIllIIIlll(n, n2, n3, (byte)(-1));
    }
}
