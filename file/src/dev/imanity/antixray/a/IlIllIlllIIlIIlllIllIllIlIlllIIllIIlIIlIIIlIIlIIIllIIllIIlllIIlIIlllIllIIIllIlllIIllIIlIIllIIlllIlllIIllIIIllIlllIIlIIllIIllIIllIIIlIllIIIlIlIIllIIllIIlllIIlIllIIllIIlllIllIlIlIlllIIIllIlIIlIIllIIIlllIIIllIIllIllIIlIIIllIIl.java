// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.a;

import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import it.unimi.dsi.fastutil.shorts.Short2ByteMap;
import org.jetbrains.annotations.NotNull;
import it.unimi.dsi.fastutil.shorts.Short2ByteOpenHashMap;

public final class IlIllIlllIIlIIlllIllIllIlIlllIIllIIlIIlIIIlIIlIIIllIIllIIlllIIlIIlllIllIIIllIlllIIllIIlIIllIIlllIlllIIllIIIllIlllIIlIIllIIllIIllIIIlIllIIIlIlIIllIIllIIlllIIlIllIIllIIlllIllIlIlIlllIIIllIlIIlIIllIIIlllIIIllIIllIllIIlIIIllIIl
{
    @NotNull
    private final Short2ByteOpenHashMap bD;
    
    public IlIllIlllIIlIIlllIllIllIlIlllIIllIIlIIlIIIlIIlIIIllIIllIIlllIIlIIlllIllIIIllIlllIIllIIlIIllIIlllIlllIIllIIIllIlllIIlIIllIIllIIllIIIlIllIIIlIlIIllIIllIIlllIIlIllIIllIIlllIllIlIlIlllIIIllIlIIlIIllIIIlllIIIllIIllIllIIlIIIllIIl() {
        this.bD = new Short2ByteOpenHashMap(24, 0.25f);
    }
    
    @NotNull
    public final Short2ByteMap.FastEntrySet IIIlllIIlllIIIllIIlIIlllIIllIIllIIIllIIllIlIIllIIlllIIllIlllIlllIIIllIIllIIllIIlllIllIIIllIllIIIlllIlIlllIIlIIlllIIIlllIIlIIlIllIIllIIlIIllIIIllIIllIIllIlllIIIlIIIlIllIIlIllIIllIllIllIIIllIIIlllIllIIIlllIIIlllIllIllIIlIIIlllIlIIllIIIllIIllIIlll() {
        final Short2ByteMap.FastEntrySet short2ByteEntrySet = this.bD.short2ByteEntrySet();
        Intrinsics.checkNotNullExpressionValue((Object)short2ByteEntrySet, "");
        return short2ByteEntrySet;
    }
    
    public final byte IIllIllIIllIlIIlIlIIlllIllIIllIIllIlIIllIIIllIIIllIIllIIIlIllIIlIIlllIllIIlIllIllIIlIIlIlIIIlIllIIllIlllIllIIIllIIlIIlllIllIIlIIIlIIllIIllIIlIlIIlIllIIlllIlIIlllIIlIlIIlIIlIlllIIlIIIlIIllIllIlllIIlllIIIlIIllIIIlll(final int n, final int n2, final int n3) {
        final short illlIIlIIIlllIIIlIIllIlIllIIIlIIIllIlllIIIllIIlIIllIIllIlllIIllIIllIIllIIllIIlllIIlllIllIIllIlllIlIllIIlllIllIllIIlllIIllIIIlllIIIllIIIllIlllIIlllIIlIlIlIlIllIIIlllIIlIllIIllIIlllIIlIIIllIIIllIIlIIlllIlIllIllIIlIIIllIllIIlIIllIll = this.IlllIIlIIIlllIIIlIIllIlIllIIIlIIIllIlllIIIllIIlIIllIIllIlllIIllIIllIIllIIllIIlllIIlllIllIIllIlllIlIllIIlllIllIllIIlllIIllIIIlllIIIllIIIllIlllIIlllIIlIlIlIlIllIIIlllIIlIllIIllIIlllIIlIIIllIIIllIIlIIlllIlIllIllIIlIIIllIllIIlIIllIll(n, n2, n3);
        if (!this.bD.containsKey(illlIIlIIIlllIIIlIIllIlIllIIIlIIIllIlllIIIllIIlIIllIIllIlllIIllIIllIIllIIllIIlllIIlllIllIIllIlllIlIllIIlllIllIllIIlllIIllIIIlllIIIllIIIllIlllIIlllIIlIlIlIlIllIIIlllIIlIllIIllIIlllIIlIIIllIIIllIIlIIlllIlIllIllIIlIIIllIllIIlIIllIll)) {
            return -1;
        }
        return this.bD.get(illlIIlIIIlllIIIlIIllIlIllIIIlIIIllIlllIIIllIIlIIllIIllIlllIIllIIllIIllIIllIIlllIIlllIllIIllIlllIlIllIIlllIllIllIIlllIIllIIIlllIIIllIIIllIlllIIlllIIlIlIlIlIllIIIlllIIlIllIIllIIlllIIlIIIllIIIllIIlIIlllIlIllIllIIlIIIllIllIIlIIllIll);
    }
    
    public final void IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(final int n, final int n2, final int n3, final byte b) {
        if (b == -1) {
            this.bD.remove(this.IlllIIlIIIlllIIIlIIllIlIllIIIlIIIllIlllIIIllIIlIIllIIllIlllIIllIIllIIllIIllIIlllIIlllIllIIllIlllIlIllIIlllIllIllIIlllIIllIIIlllIIIllIIIllIlllIIlllIIlIlIlIlIllIIIlllIIlIllIIllIIlllIIlIIIllIIIllIIlIIlllIlIllIllIIlIIIllIllIIlIIllIll(n, n2, n3));
        }
        ((Map)this.bD).put(this.IlllIIlIIIlllIIIlIIllIlIllIIIlIIIllIlllIIIllIIlIIllIIllIlllIIllIIllIIllIIllIIlllIIlllIllIIllIlllIlIllIIlllIllIllIIlllIIllIIIlllIIIllIIIllIlllIIlllIIlIlIlIlIllIIIlllIIlIllIIllIIlllIIlIIIllIIIllIIlIIlllIlIllIllIIlIIIllIllIIlIIllIll(n, n2, n3), b);
    }
    
    private final short IlllIIlIIIlllIIIlIIllIlIllIIIlIIIllIlllIIIllIIlIIllIIllIlllIIllIIllIIllIIllIIlllIIlllIllIIllIlllIlIllIIlllIllIllIIlllIIllIIIlllIIIllIIIllIlllIIlllIIlIlIlIlIllIIIlllIIlIllIIllIIlllIIlIIIllIIIllIIlIIlllIlIllIllIIlIIIllIllIIlIIllIll(final int n, final int n2, final int n3) {
        return (short)(n2 * 16 * 16 + n3 * 16 + n);
    }
}
