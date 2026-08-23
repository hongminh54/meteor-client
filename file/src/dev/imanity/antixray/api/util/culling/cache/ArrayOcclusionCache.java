// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.api.util.culling.cache;

import java.util.Arrays;

public class ArrayOcclusionCache implements OcclusionCache
{
    private final int reachX2;
    private final byte[] cache;
    private int positionKey;
    private int entry;
    private int offset;
    
    public ArrayOcclusionCache(final int n) {
        this.reachX2 = n * 2;
        this.cache = new byte[this.reachX2 * this.reachX2 * this.reachX2 / 4];
    }
    
    @Override
    public void resetCache() {
        Arrays.fill(this.cache, (byte)0);
    }
    
    @Override
    public void setVisible(final int n, final int n2, final int n3) {
        this.positionKey = n + n2 * this.reachX2 + n3 * this.reachX2 * this.reachX2;
        this.entry = this.positionKey / 4;
        this.offset = this.positionKey % 4 * 2;
        final byte[] cache = this.cache;
        final int entry = this.entry;
        cache[entry] |= (byte)(1 << this.offset);
    }
    
    @Override
    public void setHidden(final int n, final int n2, final int n3) {
        this.positionKey = n + n2 * this.reachX2 + n3 * this.reachX2 * this.reachX2;
        this.entry = this.positionKey / 4;
        this.offset = this.positionKey % 4 * 2;
        final byte[] cache = this.cache;
        final int entry = this.entry;
        cache[entry] |= (byte)(1 << this.offset + 1);
    }
    
    @Override
    public int getState(final int n, final int n2, final int n3) {
        this.positionKey = n + n2 * this.reachX2 + n3 * this.reachX2 * this.reachX2;
        this.entry = this.positionKey / 4;
        this.offset = this.positionKey % 4 * 2;
        return this.cache[this.entry] >> this.offset & 0x3;
    }
    
    @Override
    public void setLastVisible() {
        final byte[] cache = this.cache;
        final int entry = this.entry;
        cache[entry] |= (byte)(1 << this.offset);
    }
    
    @Override
    public void setLastHidden() {
        final byte[] cache = this.cache;
        final int entry = this.entry;
        cache[entry] |= (byte)(1 << this.offset + 1);
    }
}
