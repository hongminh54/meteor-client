// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.a;

import java.util.Queue;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

public class IIllIIlllIlIIIllIIIllIIIlllIIllIIlllIllIIIllIllIIIllIlIllIlIlIIllIIllIllIIlIIlllIIlIlIlIllIllIIlIlllIIllIIllIIlIlllIIIllIIIllIIlIIIlllIIIlllIIllIIllIllIIIlllIIlIIllIlIIlllIIlIIIllIlllIIlIllIlIlIIllIIlIIllIIllIllIIIllIIIllIIlll<T extends Runnable>
{
    private final ConcurrentLinkedQueue<T> cW;
    private final ConcurrentLinkedQueue<T> cX;
    private final ConcurrentLinkedQueue<T> cY;
    private final ConcurrentLinkedQueue<T> cZ;
    
    public IIllIIlllIlIIIllIIIllIIIlllIIllIIlllIllIIIllIllIIIllIlIllIlIlIIllIIllIllIIlIIlllIIlIlIlIllIllIIlIlllIIllIIllIIlIlllIIIllIIIllIIlIIIlllIIIlllIIllIIllIllIIIlllIIlIIllIlIIlllIIlIIIllIlllIIlIllIlIlIIllIIlIIllIIllIllIIIllIIIllIIlll() {
        this.cW = new ConcurrentLinkedQueue<T>();
        this.cX = new ConcurrentLinkedQueue<T>();
        this.cY = new ConcurrentLinkedQueue<T>();
        this.cZ = new ConcurrentLinkedQueue<T>();
    }
    
    public List<Runnable> IIlIIllIIIlllIlllIIIlllIIlIIllIIlIIIlIllIlllIIIlIlIllIlllIIlllIIlIIlIIlIlIlIllIIlllIIIlIIlllIIlIllIIllIIlllIlllIlllIlIIlIIIlIIllIIlllIIllIIlIIllIIIlIllIIlIIllIIIlIIlllIIlllIIlIIlllIIllIIIlIIIllIIllIIllIllIIlIIIlllIlIIllIIIllIIIll() {
        final ArrayList list = new ArrayList(this.cW.size() + this.cX.size() + this.cY.size() + this.cZ.size());
        Runnable illlIlllIIlIlllIIlIIlIllIIlIIIlllIIllIIlIIIlllIIllIlIIlIIlIIlIlIIlIIlllIlIIlIIlIlIlllIIllIIlIIllIIlIIlIIIlllIIIlllIIlIIllIllIIlllIlIIIllIIllIIlllIIIlllIIlIlllIlIIlIIlIllIIIlIIllIIIllIIIlllIlllIIIlIIIlIIlllIIIlIllIIlllIIlIIll;
        while ((illlIlllIIlIlllIIlIIlIllIIlIIIlllIIllIIlIIIlllIIllIlIIlIIlIIlIlIIlIIlllIlIIlIIlIlIlllIIllIIlIIllIIlIIlIIIlllIIIlllIIlIIllIllIIlllIlIIIllIIllIIlllIIIlllIIlIlllIlIIlIIlIllIIIlIIllIIIllIIIlllIlllIIIlIIIlIIlllIIIlIllIIlllIIlIIll = this.IlllIlllIIlIlllIIlIIlIllIIlIIIlllIIllIIlIIIlllIIllIlIIlIIlIIlIlIIlIIlllIlIIlIIlIlIlllIIllIIlIIllIIlIIlIIIlllIIIlllIIlIIllIllIIlllIlIIIllIIllIIlllIIIlllIIlIlllIlIIlIIlIllIIIlIIllIIIllIIIlllIlllIIIlIIIlIIlllIIIlIllIIlllIIlIIll()) != null) {
            list.add(illlIlllIIlIlllIIlIIlIllIIlIIIlllIIllIIlIIIlllIIllIlIIlIIlIIlIlIIlIIlllIlIIlIIlIlIlllIIllIIlIIllIIlIIlIIIlllIIIlllIIlIIllIllIIlllIlIIIllIIllIIlllIIIlllIIlIlllIlIIlIIlIllIIIlIIllIIIllIIIlllIlllIIIlIIIlIIlllIIIlIllIIlllIIlIIll);
        }
        return list;
    }
    
    public boolean isEmpty() {
        return this.cW.isEmpty() && this.cX.isEmpty() && this.cY.isEmpty() && this.cZ.isEmpty();
    }
    
    public Runnable IIlllIIlIllIIIllIllIIIlIlIllIllIIlIIlIllIIllIlIIlllIIIllIIIlIIlIIIllIIIlIlllIllIIlllIIllIIIlllIIllIIlIIlIIlIIIlIIIllIIlIlIIIllIIIllIIllIIIlllIIIllIIIllIIlIIlIlIllIllIlIIlllIlllIIIllIIlIIIlIllIlIllIlIllIIllIllIllIIlIl() {
        return this.cW.poll();
    }
    
    public Runnable IlllIlllIIlIlllIIlIIlIllIIlIIIlllIIllIIlIIIlllIIllIlIIlIIlIIlIlIIlIIlllIlIIlIIlIlIlllIIllIIlIIllIIlIIlIIIlllIIIlllIIlIIllIllIIlllIlIIIllIIllIIlllIIIlllIIlIlllIlIIlIIlIllIIIlIIllIIIllIIIlllIlllIIIlIIIlIIlllIIIlIllIIlllIIlIIll() {
        final IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll[] df = IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll.df;
        for (int length = df.length, i = 0; i < length; ++i) {
            final Runnable illlIIlIIIlllIIIlIIllIlIllIIIlIIIllIlllIIIllIIlIIllIIllIlllIIllIIllIIllIIllIIlllIIlllIllIIllIlllIlIllIIlllIllIllIIlllIIllIIIlllIIIllIIIllIlllIIlllIIlIlIlIlIllIIIlllIIlIllIIllIIlllIIlIIIllIIIllIIlIIlllIlIllIllIIlIIIllIllIIlIIllIll = this.IlllIIlIIIlllIIIlIIllIlIllIIIlIIIllIlllIIIllIIlIIllIIllIlllIIllIIllIIllIIllIIlllIIlllIllIIllIlllIlIllIIlllIllIllIIlllIIllIIIlllIIIllIIIllIlllIIlllIIlIlIlIlIllIIIlllIIlIllIIllIIlllIIlIIIllIIIllIIlIIlllIlIllIllIIlIIIllIllIIlIIllIll(df[i]);
            if (illlIIlIIIlllIIIlIIllIlIllIIIlIIIllIlllIIIllIIlIIllIIllIlllIIllIIllIIllIIllIIlllIIlllIllIIllIlllIlIllIIlllIllIllIIlllIIllIIIlllIIIllIIIllIlllIIlllIIlIlIlIlIllIIIlllIIlIllIIllIIlllIIlIIIllIIIllIIlIIlllIlIllIllIIlIIIllIllIIlIIllIll != null) {
                return illlIIlIIIlllIIIlIIllIlIllIIIlIIIllIlllIIIllIIlIIllIIllIlllIIllIIllIIllIIllIIlllIIlllIllIIllIlllIlIllIIlllIllIllIIlllIIllIIIlllIIIllIIIllIlllIIlllIIlIlIlIlIllIIIlllIIlIllIIllIIlllIIlIIIllIIIllIIlIIlllIlIllIllIIlIIIllIllIIlIIllIll;
            }
        }
        return null;
    }
    
    private T IlllIIlIIIlllIIIlIIllIlIllIIIlIIIllIlllIIIllIIlIIllIIllIlllIIllIIllIIllIIllIIlllIIlllIllIIllIlllIlIllIIlllIllIllIIlllIIllIIIlllIIIllIIIllIlllIIlllIIlIlIlIlIllIIIlllIIlIllIIllIIlllIIlIIIllIIIllIIlIIlllIlIllIllIIlIIIllIllIIlIIllIll(final IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll illIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll) {
        final Runnable runnable;
        if ((runnable = this.IIllIIIllIllIIIlllIIIlllIIllIIlllIlIIIllIllIlIIlIIIlllIIlIIllIIllIlIllIIllIIIllIIlIllIIlIIlIIllIIlIlIIllIllIIlIlIIlIIIllIIlIllIllIIlllIllIIlIlllIIllIlIlllIIlllIIIlIIlIIllIlllIlIIllIIIllIlIlIIlIIllIllIlIllIIIllIIll(illIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll).poll()) != null) {
            return (T)runnable;
        }
        return null;
    }
    
    private Queue<T> IIllIIIllIllIIIlllIIIlllIIllIIlllIlIIIllIllIlIIlIIIlllIIlIIllIIllIlIllIIllIIIllIIlIllIIlIIlIIllIIlIlIIllIllIIlIlIIlIIIllIIlIllIllIIlllIllIIlIlllIIllIlIlllIIlllIIIlIIlIIllIlllIlIIllIIIllIlIlIIlIIllIllIlIllIIIllIIll(final IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll illIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll) {
        switch (IIllIIlllIlIIIllIIIllIIIlllIIllIIlllIllIIIllIllIIIllIlIllIlIlIIllIIllIllIIlIIlllIIlIlIlIllIllIIlIlllIIllIIllIIlIlllIIIllIIIllIIlIIIlllIIIlllIIllIIllIllIIIlllIIlIIllIlIIlllIIlIIIllIlllIIlIllIlIlIIllIIlIIllIIllIllIIIllIIIllIIlll$1.da[illIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll.ordinal()]) {
            case 1: {
                return this.cW;
            }
            case 2: {
                return this.cX;
            }
            case 3: {
                return this.cY;
            }
            default: {
                return this.cZ;
            }
        }
    }
    
    public void IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(final T t, final int n) {
        if (n == IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll.de.ordinal()) {
            this.cW.add(t);
        }
        else if (n == IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll.dd.ordinal()) {
            this.cX.add(t);
        }
        else if (n == IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll.dc.ordinal()) {
            this.cY.add(t);
        }
        else {
            this.cZ.add(t);
        }
    }
    
    public enum IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll
    {
        db, 
        dc, 
        dd, 
        de;
        
        public static final IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll[] df;
        
        static {
            df = new IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll[] { IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll.de, IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll.dd, IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll.dc, IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll.db };
        }
    }
}
