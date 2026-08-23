// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.api.util;

import org.jetbrains.annotations.NotNull;
import kotlin.Metadata;

@Metadata(mv = { 1, 9, 0 }, k = 1, xi = 48, d1 = { "\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u000b\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0015\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0007\u0010\bJ\u0015\u0010\t\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\t\u0010\bJ\u0015\u0010\n\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\n\u0010\bJ\u0015\u0010\u000b\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u000b\u0010\bJ\u0015\u0010\f\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\f\u0010\bJ\u001d\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\u0006¢\u0006\u0004\b\u000e\u0010\u000fJ%\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\u00062\u0006\u0010\u0010\u001a\u00020\u0006¢\u0006\u0004\b\u000e\u0010\u0011" }, d2 = { "Ldev/imanity/antixray/api/util/LongHash;", "", "<init>", "()V", "", "p0", "", "getX", "(J)I", "getY", "getZ", "lsw", "msw", "p1", "toLong", "(II)J", "p2", "(III)J" })
public final class LongHash
{
    @NotNull
    public static final LongHash INSTANCE;
    
    private LongHash() {
    }
    
    public final long toLong(final int n, final int n2) {
        return ((long)n << 32) + n2 - Integer.MIN_VALUE;
    }
    
    public final long toLong(final int n, final int n2, final int n3) {
        return ((long)n & 0x3FFFFFFL) << 38 | ((long)n2 & 0xFFFL) | ((long)n3 & 0x3FFFFFFL) << 12;
    }
    
    public final int getX(final long n) {
        return (int)(n >> 38);
    }
    
    public final int getY(final long n) {
        return (int)(n << 52 >> 52);
    }
    
    public final int getZ(final long n) {
        return (int)(n << 26 >> 38);
    }
    
    public final int msw(final long n) {
        return (int)(n >> 32);
    }
    
    public final int lsw(final long n) {
        return (int)(n & 0xFFFFFFFFL) + Integer.MIN_VALUE;
    }
    
    static {
        INSTANCE = new LongHash();
    }
}
