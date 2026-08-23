// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.nms;

import kotlin.LazyKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.JvmName;
import kotlin.Lazy;
import org.jetbrains.annotations.NotNull;
import kotlin.Metadata;

@Metadata(mv = { 1, 9, 0 }, k = 1, xi = 48, d1 = { "\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u001b\u0010\t\u001a\u00020\u00048GX\u0087\u0084\u0002¢\u0006\f\n\u0004\b\u0005\u0010\u0006\u001a\u0004\b\u0007\u0010\b" }, d2 = { "Ldev/imanity/antixray/nms/Folia;", "", "<init>", "()V", "", "IS_FOLIA$delegate", "Lkotlin/Lazy;", "getIS_FOLIA", "()Z", "IS_FOLIA" })
public final class Folia
{
    @NotNull
    public static final Folia INSTANCE;
    @NotNull
    private static final Lazy IS_FOLIA$delegate;
    
    private Folia() {
    }
    
    @JvmName(name = "getIS_FOLIA")
    public final boolean getIS_FOLIA() {
        return (boolean)Folia.IS_FOLIA$delegate.getValue();
    }
    
    static {
        INSTANCE = new Folia();
        IS_FOLIA$delegate = LazyKt.lazy((Function0)Folia$IS_FOLIA.Folia$IS_FOLIA$2.INSTANCE);
    }
}
