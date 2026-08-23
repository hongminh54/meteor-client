// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.api.util;

import kotlin.jvm.JvmStatic;
import org.jetbrains.annotations.NotNull;
import kotlin.Metadata;

@Metadata(mv = { 1, 9, 0 }, k = 1, xi = 48, d1 = { "\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0015\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0007\u0010\bJ\u0015\u0010\t\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\t\u0010\bJ\u0017\u0010\n\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0007¢\u0006\u0004\b\n\u0010\b" }, d2 = { "Ldev/imanity/antixray/api/util/MathUtilities;", "", "<init>", "()V", "", "p0", "", "ceil", "(D)I", "fastFloor", "floor" })
public final class MathUtilities
{
    @NotNull
    public static final MathUtilities INSTANCE;
    
    private MathUtilities() {
    }
    
    @JvmStatic
    public static final int floor(final double n) {
        final int n2 = (int)n;
        return (n < n2) ? (n2 - 1) : n2;
    }
    
    public final int fastFloor(final double n) {
        return (int)(n + 1024.0) - 1024;
    }
    
    public final int ceil(final double n) {
        final int n2 = (int)n;
        return (n > n2) ? (n2 + 1) : n2;
    }
    
    static {
        INSTANCE = new MathUtilities();
    }
}
