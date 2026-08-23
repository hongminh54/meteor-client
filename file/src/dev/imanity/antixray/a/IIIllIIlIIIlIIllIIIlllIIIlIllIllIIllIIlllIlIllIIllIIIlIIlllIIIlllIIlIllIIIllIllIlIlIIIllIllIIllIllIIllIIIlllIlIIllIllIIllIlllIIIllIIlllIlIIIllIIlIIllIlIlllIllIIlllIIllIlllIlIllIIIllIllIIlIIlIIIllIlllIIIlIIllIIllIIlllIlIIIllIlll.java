// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.a;

import io.fairyproject.libs.packetevents.PacketEvents;
import java.util.function.Supplier;
import kotlin.text.StringsKt;
import kotlin.jvm.internal.Intrinsics;
import io.fairyproject.libs.xseries.XMaterial;
import org.jetbrains.annotations.NotNull;
import io.fairyproject.libs.packetevents.protocol.world.states.WrappedBlockState;

public final class IIIllIIlIIIlIIllIIIlllIIIlIllIllIIllIIlllIlIllIIllIIIlIIlllIIIlllIIlIllIIIllIllIlIlIIIllIllIIllIllIIllIIIlllIlIIllIllIIllIlllIIIllIIlllIlIIIllIIlIIllIlIlllIllIIlllIIllIlllIlIllIIIllIllIIlIIlIIIllIlllIIIlIIllIIllIIlllIlIIIllIlll
{
    @NotNull
    public static final XMaterial IIllIllIIllIlIIlIlIIlllIllIIllIIllIlIIllIIIllIIIllIIllIIIlIllIIlIIlllIllIIlIllIllIIlIIlIlIIIlIllIIllIlllIllIIIllIIlIIlllIllIIlIIIlIIllIIllIIlIlIIlIllIIlllIlIIlllIIlIlIIlIIlIlllIIlIIIlIIllIllIlllIIlllIIIlIIllIIIlll(@NotNull final WrappedBlockState wrappedBlockState) {
        Intrinsics.checkNotNullParameter((Object)wrappedBlockState, "");
        final String string = wrappedBlockState.toString();
        Intrinsics.checkNotNullExpressionValue((Object)string, "");
        final String s = StringsKt.split$default((CharSequence)string, new String[] { "[" }, false, 0, 6, (Object)null).get(0);
        final Object orElseThrow = XMaterial.matchXMaterial(s).orElseThrow(IIIllIIlIIIlIIllIIIlllIIIlIllIllIIllIIlllIlIllIIllIIIlIIlllIIIlllIIlIllIIIllIllIlIlIIIllIllIIllIllIIllIIIlllIlIIllIllIIllIlllIIIllIIlllIlIIIllIIlIIllIlIlllIllIIlllIIllIlllIlIllIIIllIllIIlIIlIIIllIlllIIIlIIllIIllIIlllIlIIIllIlll::IIllIIIllIllIIIlllIIIlllIIllIIlllIlIIIllIllIlIIlIIIlllIIlIIllIIllIlIllIIllIIIllIIlIllIIlIIlIIllIIlIlIIllIllIIlIlIIlIIIllIIlIllIllIIlllIllIIlIlllIIllIlIlllIIlllIIIlIIlIIllIlllIlIIllIIIllIlIlIIlIIllIllIlIllIIIllIIll);
        Intrinsics.checkNotNullExpressionValue(orElseThrow, "");
        return (XMaterial)orElseThrow;
    }
    
    @NotNull
    public static final WrappedBlockState IIlIlllIIllIllIlllIIlIlIllIIlIIllIIllIIIllIlIIlllIIIllIIllIIlllIlllIIllIlllIllIIIlIIllIIllIIlllIIIllIIIlIIllIllIIlIIlIIlllIlIIlIllIlllIIlllIIIlIIIllIIllIIlIIIllIlllIIlIlIlllIIlIIllIIlIllIIlIIlllIIIlllIIlllIIlllIllIIlIllIIIlIIll(final int n) {
        final WrappedBlockState byGlobalId = WrappedBlockState.getByGlobalId(PacketEvents.getAPI().getServerManager().getVersion().toClientVersion(), n, false);
        Intrinsics.checkNotNullExpressionValue((Object)byGlobalId, "");
        return byGlobalId;
    }
    
    @NotNull
    public static final WrappedBlockState IlllIIlIIIlllIIIlIIllIlIllIIIlIIIllIlllIIIllIIlIIllIIllIlllIIllIIllIIllIIllIIlllIIlllIllIIllIlllIlIllIIlllIllIllIIlllIIllIIIlllIIIllIIIllIlllIIlllIIlIlIlIlIllIIIlllIIlIllIIllIIlllIIlIIIllIIIllIIlIIlllIlIllIllIIlIIIllIllIIlIIllIll(@NotNull final String s) {
        Intrinsics.checkNotNullParameter((Object)s, "");
        final WrappedBlockState byString = WrappedBlockState.getByString(PacketEvents.getAPI().getServerManager().getVersion().toClientVersion(), s, false);
        Intrinsics.checkNotNullExpressionValue((Object)byString, "");
        return byString;
    }
    
    private static final IllegalArgumentException IIllIIIllIllIIIlllIIIlllIIllIIlllIlIIIllIllIlIIlIIIlllIIlIIllIIllIlIllIIllIIIllIIlIllIIlIIlIIllIIlIlIIllIllIIlIlIIlIIIllIIlIllIllIIlllIllIIlIlllIIllIlIlllIIlllIIIlIIlIIllIlllIlIIllIIIllIlIlIIlIIllIllIlIllIIIllIIll(final String str) {
        Intrinsics.checkNotNullParameter((Object)str, "");
        return new IllegalArgumentException("Cannot find material for " + str);
    }
}
