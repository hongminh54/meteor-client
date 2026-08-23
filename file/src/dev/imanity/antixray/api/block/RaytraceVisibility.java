// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.api.block;

import org.jetbrains.annotations.NotNull;
import kotlin.jvm.functions.Function1;
import kotlin.Metadata;

@Metadata(mv = { 1, 9, 0 }, k = 1, xi = 48, d1 = { "\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J+\u0010\b\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004H&¢\u0006\u0004\b\b\u0010\t" }, d2 = { "Ldev/imanity/antixray/api/block/RaytraceVisibility;", "", "", "p0", "Lkotlin/Function1;", "Ldev/imanity/antixray/api/block/RaytraceBlock;", "", "p1", "checkVisibility", "(ILkotlin/jvm/functions/Function1;)Z" })
public interface RaytraceVisibility
{
    boolean checkVisibility(final int p0, @NotNull final Function1<? super RaytraceBlock, Boolean> p1);
}
