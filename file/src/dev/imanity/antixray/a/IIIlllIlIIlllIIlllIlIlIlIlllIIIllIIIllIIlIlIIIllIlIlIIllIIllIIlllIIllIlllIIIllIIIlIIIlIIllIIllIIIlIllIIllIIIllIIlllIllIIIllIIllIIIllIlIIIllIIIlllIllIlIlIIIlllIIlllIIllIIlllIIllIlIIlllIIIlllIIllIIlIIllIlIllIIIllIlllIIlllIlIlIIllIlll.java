// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.a;

import kotlin.jvm.internal.Intrinsics;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import org.jetbrains.annotations.NotNull;
import kotlin.Metadata;

@Metadata(mv = { 1, 9, 0 }, k = 1, xi = 48, d1 = { "\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003JU\u0010\r\u001a\u00020\u000b2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u00042\u0018\u0010\f\u001a\u0014\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u000b0\nH\u0086\b\u00f8\u0001\u0000¢\u0006\u0004\b\r\u0010\u000e\u0082\u0002\u0007\n\u0005\b\u009920\u0001" }, d2 = { "Ldev/imanity/antixray/a/IIIlllIlIIlllIIlllIlIlIlIlllIIIllIIIllIIlIlIIIllIlIlIIllIIllIIlllIIllIlllIIIllIIIlIIIlIIllIIllIIIlIllIIllIIIllIIlllIllIIIllIIllIIIllIlIIIllIIIlllIllIlIlIIIlllIIlllIIllIIlllIIllIlIIlllIIIlllIIllIIlIIllIlIllIIIllIlllIIlllIlIlIIllIlll;", "", "<init>", "()V", "", "p0", "p1", "p2", "p3", "p4", "Lkotlin/Function2;", "", "p5", "IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll", "(IIIIILkotlin/jvm/functions/Function2;)V" })
public final class IIIlllIlIIlllIIlllIlIlIlIlllIIIllIIIllIIlIlIIIllIlIlIIllIIllIIlllIIllIlllIIIllIIIlIIIlIIllIIllIIIlIllIIllIIIllIIlllIllIIIllIIllIIIllIlIIIllIIIlllIllIlIlIIIlllIIlllIIllIIlllIIllIlIIlllIIIlllIIllIIlIIllIlIllIIIllIlllIIlllIlIlIIllIlll
{
    @NotNull
    public static final IIIlllIlIIlllIIlllIlIlIlIlllIIIllIIIllIIlIlIIIllIlIlIIllIIllIIlllIIllIlllIIIllIIIlIIIlIIllIIllIIIlIllIIllIIIllIIlllIllIIIllIIllIIIllIlIIIllIIIlllIllIlIlIIIlllIIlllIIllIIlllIIllIlIIlllIIIlllIIllIIlIIllIlIllIIIllIlllIIlllIlIlIIllIlll INSTANCE;
    
    private IIIlllIlIIlllIIlllIlIlIlIlllIIIllIIIllIIlIlIIIllIlIlIIllIIllIIlllIIllIlllIIIllIIIlIIIlIIllIIllIIIlIllIIllIIIllIIlllIllIIIllIIllIIIllIlIIIllIIIlllIllIlIlIIIlllIIlllIIllIIlllIIllIlIIlllIIIlllIIllIIlIIllIlIllIIIllIlllIIlllIlIlIIllIlll() {
    }
    
    public final void IIllIIlIllIIlIlllIIllIIIllIIIllIlIIlIIIllIIlllIIllIllIlIIlllIIlllIlIlIIlllIIlllIIIllIIllIIlIIIllIIIlllIIIllIllIIIlllIIllIllIlllIIIllIIIllIIlIlIIllIIlIIIlllIIllIIllIllIlllIIIlIIlIIIlIIllIlIIllIlllIlIIIllIIIlIIIllIIIlIlIllIIllIlIll(final int n, final int n2, final int n3, final int n4, final int n5, @NotNull final Function2<? super Integer, ? super Integer, Unit> function2) {
        Intrinsics.checkNotNullParameter((Object)function2, "");
        int i = n - n5;
        final int n6 = n + n5;
        if (i <= n6) {
            while (true) {
                int j = n2 - n5;
                final int n7 = n2 + n5;
                if (j <= n7) {
                    while (true) {
                        if (Math.abs(i - n3) > n5 || Math.abs(j - n4) > n5) {
                            function2.invoke((Object)i, (Object)j);
                        }
                        if (j == n7) {
                            break;
                        }
                        ++j;
                    }
                }
                if (i == n6) {
                    break;
                }
                ++i;
            }
        }
    }
    
    static {
        INSTANCE = new IIIlllIlIIlllIIlllIlIlIlIlllIIIllIIIllIIlIlIIIllIlIlIIllIIllIIlllIIllIlllIIIllIIIlIIIlIIllIIllIIIlIllIIllIIIllIIlllIllIIIllIIllIIIllIlIIIllIIIlllIllIlIlIIIlllIIlllIIllIIlllIIllIlIIlllIIIlllIIllIIlIIllIlIllIIIllIlllIIlllIlIlIIllIlll();
    }
}
