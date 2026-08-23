// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.concurrentutil.util;

public final class ThrowUtil
{
    private ThrowUtil() {
    }
    
    public static <T extends Throwable> void throwUnchecked(final Throwable t) throws T {
        throw t;
    }
}
