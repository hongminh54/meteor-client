// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.nms.v1_12_R1;

import org.jetbrains.annotations.NotNull;
import kotlin.Metadata;

@Metadata(mv = { 1, 9, 0 }, k = 1, xi = 48, d1 = { "\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001d\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0004¢\u0006\u0004\b\u0007\u0010\b" }, d2 = { "Ldev/imanity/antixray/nms/v1_12_R1/CombinedID;", "", "<init>", "()V", "", "p0", "p1", "getId", "(II)I" })
public final class CombinedID
{
    @NotNull
    public static final CombinedID INSTANCE;
    
    private CombinedID() {
    }
    
    public final int getId(final int n, final int n2) {
        return n << 4 | n2;
    }
    
    static {
        INSTANCE = new CombinedID();
    }
}
